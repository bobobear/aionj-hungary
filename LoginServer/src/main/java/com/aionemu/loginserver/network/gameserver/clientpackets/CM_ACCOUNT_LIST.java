/*
 * This file is part of aion-emu <aion-emu.com>.
 *
 * aion-emu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aion-emu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aion-emu.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.aionemu.loginserver.network.gameserver.clientpackets;

import com.aionemu.commons.network.packet.AbstractClientPacket;
import com.aionemu.loginserver.GameServerTable;
import com.aionemu.loginserver.controller.AccountController;
import com.aionemu.loginserver.model.Account;
import com.aionemu.loginserver.network.gameserver.GameServerChannelHandler;
import com.aionemu.loginserver.network.gameserver.serverpackets.SM_REQUEST_KICK_ACCOUNT;

/**
 * Reads the list of accoutn id's that are logged to game server
 * 
 * @author SoulKeeper, Lyahim
 */
public class CM_ACCOUNT_LIST extends AbstractClientPacket<GameServerChannelHandler>
{
	/**
	 * Array with accounts that are logged in
	 */
	private String[]	accountNames;

	/**
	 * Creates new packet instance.
	 * 
	 * @param buf
	 *            packet data
	 * @param client
	 *            client
	 */
	public CM_ACCOUNT_LIST(int opcode)
	{
		super(opcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void readImpl()
	{
		accountNames = new String[readD()];
		for(int i = 0; i < accountNames.length; i++)
		{
			accountNames[i] = readS();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void runImpl()
	{
		for(String s : accountNames)
		{
			Account a = AccountController.getInstance().loadAccount(s);
			if(GameServerTable.isAccountOnAnyGameServer(a))
			{
				getChannelHandler().sendPacket(new SM_REQUEST_KICK_ACCOUNT(a.getId()));
				continue;
			}
			getChannelHandler().getGameServerInfo().addAccountToGameServer(a);
		}
	}
}
