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
package com.aionemu.gameserver.network.loginserver.serverpackets;

import com.aionemu.commons.network.netty.packet.AbstractServerPacket;
import com.aionemu.gameserver.network.loginserver.LoginServerChannelHandler;


/**
 * In this packet Gameserver is asking if given account sessionKey is valid at Loginserver side. [if user that is
 * authenticating on Gameserver is already authenticated on Loginserver]
 * 
 * @author Lyahim, -Nemesiss-
 * 
 */
public class SM_ACCOUNT_AUTH extends AbstractServerPacket<LoginServerChannelHandler>
{
	/**
	 * accountId [part of session key]
	 */
	private final int	accountId;
	/**
	 * loginOk [part of session key]
	 */
	private final int	loginOk;
	/**
	 * playOk1 [part of session key]
	 */
	private final int	playOk1;
	/**
	 * playOk2 [part of session key]
	 */
	private final int	playOk2;

	/**
	 * Constructs new instance of <tt>SM_ACCOUNT_AUTH </tt> packet.
	 * 
	 * @param accountId
	 *            account identifier.
	 * @param loginOk
	 * @param playOk1
	 * @param playOk2
	 */
	public SM_ACCOUNT_AUTH(int accountId, int loginOk, int playOk1, int playOk2)
	{
		this.accountId = accountId;
		this.loginOk = loginOk;
		this.playOk1 = playOk1;
		this.playOk2 = playOk2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(LoginServerChannelHandler cHandler)
	{
		writeD(accountId);
		writeD(loginOk);
		writeD(playOk1);
		writeD(playOk2);
	}

	@Override
	public String toString()
	{
		return "SM_ACCOUNT_AUTH [accountId=" + accountId + ", loginOk=" + loginOk + ", playOk1=" + playOk1 + ", playOk2=" + playOk2 + "]";
	}
}
