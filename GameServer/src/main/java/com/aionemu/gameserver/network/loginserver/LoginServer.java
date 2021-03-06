/**
 * This file is part of aion-emu <aion-emu.com>.
 *
 *  aion-emu is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-emu is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-emu.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.network.loginserver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.aionemu.commons.network.netty.State;
import com.aionemu.gameserver.model.account.Account;
import com.aionemu.gameserver.model.account.AccountTime;
import com.aionemu.gameserver.network.NettyGameServer;
import com.aionemu.gameserver.network.aion.AionChannelHandler;
import com.aionemu.gameserver.network.aion.serverpackets.SM_L2AUTH_LOGIN_CHECK;
import com.aionemu.gameserver.network.aion.serverpackets.SM_RECONNECT_KEY;
import com.aionemu.gameserver.network.loginserver.serverpackets.SM_ACCOUNT_AUTH;
import com.aionemu.gameserver.network.loginserver.serverpackets.SM_ACCOUNT_DISCONNECTED;
import com.aionemu.gameserver.network.loginserver.serverpackets.SM_ACCOUNT_RECONNECT_KEY;
import com.aionemu.gameserver.network.loginserver.serverpackets.SM_BAN;
import com.aionemu.gameserver.network.loginserver.serverpackets.SM_LS_CONTROL;
import com.aionemu.gameserver.services.AccountService;
import com.aionemu.gameserver.utils.ThreadPoolManager;

/**
 * Utill class for connecting GameServer to LoginServer.
 * 
 * @author -Nemesiss-
 * 
 */
public class LoginServer
{
	/**
	 * Logger for this class.
	 */
	private static final Logger					log					= Logger.getLogger(LoginServer.class);

	/**
	 * Map<accountId,Connection> for waiting request. This request is send to LoginServer and GameServer is waiting for
	 * response.
	 */
	private Map<Integer, AionChannelHandler>	loginRequests		= new HashMap<Integer, AionChannelHandler>();

	/**
	 * Map<accountId,Connection> for all logged in accounts.
	 */
	private Map<Integer, AionChannelHandler>	loggedInAccounts	= new HashMap<Integer, AionChannelHandler>();

	/**
	 * Connection to LoginServer.
	 */
	private LoginServerChannelHandler			loginServer;
	
	private boolean shutDown = false;

	public static final LoginServer getInstance()
	{
		return SingletonHolder.instance;
	}

	private LoginServer()
	{

	}

	public void setChannelHandler(LoginServerChannelHandler lsch)
	{
		this.loginServer = lsch;
	}

	/**
	 * This method is called when we lost connection to LoginServer. We will disconnects all aionClients waiting for
	 * LoginServer response and also try reconnect to LoginServer.
	 */
	public void loginServerDown()
	{
		log.warn("Connection with LoginServer lost...");

		loginServer = null;
		synchronized (this)
		{
			/**
			 * We lost connection for LoginServer so client pending authentication should be disconnected [cuz
			 * authentication will never ends]
			 */
			for (AionChannelHandler client : loginRequests.values())
			{
				// TODO! somme error packet!
				client.close();
			}
			loginRequests.clear();
		}
		if (!shutDown)
			NettyGameServer.getInstance().connectToLoginServer();
	}

	/**
	 * Notify that client is disconnected - we must clear waiting request to LoginServer if any to prevent leaks. Also
	 * notify LoginServer that this account is no longer on GameServer side.
	 * 
	 * @param client
	 */
	public void aionClientDisconnected(int accountId)
	{
		synchronized (this)
		{
			loginRequests.remove(accountId);
			loggedInAccounts.remove(accountId);
		}
		sendAccountDisconnected(accountId);
	}

	/**
	 * 
	 * @param accountId
	 */
	private void sendAccountDisconnected(int accountId)
	{
		log.info("Sending account disconnected " + accountId);
		if (loginServer != null && loginServer.getState() == State.AUTHED)
			loginServer.sendPacket(new SM_ACCOUNT_DISCONNECTED(accountId));
	}

	/**
	 * Starts authentication procedure of this client - LoginServer will sends response with information about account
	 * name if authentication is ok.
	 * 
	 * @param accountId 
	 * @param client
	 * @param loginOk
	 * @param playOk1
	 * @param playOk2
	 */
	public void requestAuthenticationOfClient(int accountId, AionChannelHandler client, int loginOk, int playOk1, int playOk2)
	{
		/**
		 * There are no connection to LoginServer. We should disconnect this client since authentication is not
		 * possible.
		 */
		if (loginServer == null || loginServer.getState() != State.AUTHED)
		{
			log.warn("LS !!! " + (loginServer == null ? "NULL" : loginServer.getState()));
			// TODO! somme error packet!
			client.close();
			return;
		}

		synchronized (this)
		{
			if (loginRequests.containsKey(accountId))
				return;
			loginRequests.put(accountId, client);
		}
		loginServer.sendPacket(new SM_ACCOUNT_AUTH(accountId, loginOk, playOk1, playOk2));
	}

	/**
	 * This method is called by CM_ACCOUNT_AUTH_RESPONSE LoginServer packets to notify GameServer about results of
	 * client authentication.
	 * 
	 * @param accountId
	 * @param accountName
	 * @param result
	 * @param accountTime 
	 */
	public void accountAuthenticationResponse(int accountId, String accountName, boolean result, AccountTime accountTime, byte accessLevel, byte membership)
	{
		AionChannelHandler client = loginRequests.remove(accountId);

		if (client == null)
			return;

		if (result)
		{
			client.setState(State.AUTHED);
			loggedInAccounts.put(accountId, client);
			log.info("Account authed: " + accountId + " = " + accountName);
			client.setAccount(AccountService.getAccount(accountId, accountName, accountTime, accessLevel, membership));
			client.sendPacket(new SM_L2AUTH_LOGIN_CHECK(true));
		}
		else
		{
			log.info("Account not authed: " + accountId);
			client.close(new SM_L2AUTH_LOGIN_CHECK(false));
		}
	}

	/**
	 * Starts reconnection to LoginServer procedure. LoginServer in response will send reconnection key.
	 * 
	 * @param client
	 */
	public void requestAuthReconnection(AionChannelHandler client)
	{
		/**
		 * There are no connection to LoginServer. We should disconnect this client since authentication is not
		 * possible.
		 */
		if (loginServer == null || loginServer.getState() != State.AUTHED)
		{
			// TODO! somme error packet!
			client.close();
			return;
		}

		synchronized (this)
		{
			if (loginRequests.containsKey(client.getAccount().getId()))
				return;
			loginRequests.put(client.getAccount().getId(), client);

		}
		loginServer.sendPacket(new SM_ACCOUNT_RECONNECT_KEY(client.getAccount().getId()));
	}

	/**
	 * This method is called by CM_ACCOUNT_RECONNECT_KEY LoginServer packets to give GameServer reconnection key for
	 * client that was requesting reconnection.
	 * 
	 * @param accountId
	 * @param reconnectKey
	 */
	public void authReconnectionResponse(int accountId, int reconnectKey)
	{
		AionChannelHandler client = loginRequests.remove(accountId);

		if (client == null)
			return;

		log.info("Account reconnectimg: " + accountId + " = " + client.getAccount().getName());
		client.close(new SM_RECONNECT_KEY(reconnectKey));
	}

	/**
	 * This method is called by CM_REQUEST_KICK_ACCOUNT LoginServer packets to request GameServer to disconnect client
	 * with given account id.
	 * 
	 * @param accountId
	 */
	public void kickAccount(int accountId)
	{
		synchronized (this)
		{
			AionChannelHandler client = loggedInAccounts.get(accountId);
			if (client != null)
			{
				closeClientWithCheck(client, accountId);
			}
			//This account is not logged in on this GameServer but LS thinks different...
			else
			{
				sendAccountDisconnected(accountId);
			}
		}
	}

	private void closeClientWithCheck(AionChannelHandler client, final int accountId)
	{
		log.info("Closing client connection " + accountId);
		client.close();
		ThreadPoolManager.getInstance().schedule(new Runnable()
		{

			@Override
			public void run()
			{
				AionChannelHandler client = loggedInAccounts.get(accountId);
				if (client != null)
				{
					log.warn("Removing client from server because of stalled connection");
					client.close();
					loggedInAccounts.remove(accountId);
					sendAccountDisconnected(accountId);
				}
			}
		}, 5000);
	}

	/**
	 * Returns unmodifiable map with accounts that are logged in to current GS Map Key: Account ID Map Value:
	 * AionConnectionObject
	 * 
	 * @return unmodifiable map wwith accounts
	 */
	public Map<Integer, AionChannelHandler> getLoggedInAccounts()
	{
		return Collections.unmodifiableMap(loggedInAccounts);
	}

	/**
	 * When Game Server shutdown, have to close all pending client connection
	 */
	public void gameServerDisconnected()
	{
		synchronized (this)
		{
			shutDown = true;

			if (loginServer == null)
				return;
			/**
			 * GameServer shutting down, must close all pending login requests
			 */
			for (AionChannelHandler client : loginRequests.values())
			{
				// TODO! somme error packet!
				client.close();
			}
			loginRequests.clear();

			loginServer.close();
		}

		log.info("GameServer disconnected from the Login Server...");
	}

	public void sendLsControlPacket(String accountName, String playerName, String adminName, int param, int type)
	{
		if (loginServer != null && loginServer.getState() == State.AUTHED)
			loginServer.sendPacket(new SM_LS_CONTROL(accountName, playerName, adminName, param, type));
	}

	public void accountUpdate(int accountId, byte param, int type)
	{
		synchronized (this)
		{
			AionChannelHandler client = loggedInAccounts.get(accountId);
			if (client != null)
			{
				Account account = client.getAccount();
				if (type == 1)
					account.setAccessLevel(param);
				if (type == 2)
					account.setMembership(param);
			}
		}
	}

	public void sendBanPacket(byte type, int accountId, String ip, int time, int adminObjId)
	{
		if (loginServer != null && loginServer.getState() == State.AUTHED)
			loginServer.sendPacket(new SM_BAN(type, accountId, ip, time, adminObjId));
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final LoginServer	instance	= new LoginServer();
	}
}
