/**
 * This file is part of aion-emu <aion-unique.com>.
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
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AbstractAionServerPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;

/**
 * @author Lyahim, -Avol-
 * 
 */
public class SM_EXCHANGE_CONFIRMATION extends AbstractAionServerPacket<AionChannelHandler>
{
	private int	action;

	public SM_EXCHANGE_CONFIRMATION(int action)
	{
		this.action = action;
	}

	@Override
	protected void writeImpl(AionChannelHandler cHandler)
	{
		writeC(action);
	}
}