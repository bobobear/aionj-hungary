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
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AbstractAionServerPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;

/**
 * This packet is notify client what map should be loaded.
 * 
 * @author Lyahim, -Nemesiss-
 * 
 */
public class SM_PLAYER_SPAWN extends AbstractAionServerPacket<AionChannelHandler>
{
	/**
	 * Player that is entering game.
	 */
	private final Player	player;

	/**
	 * Constructor.
	 * 
	 * @param player
	 */
	public SM_PLAYER_SPAWN(Player player)
	{
		super();
		this.player = player;
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionChannelHandler cHandler)
	{
		writeD(player.getWorldId());
		writeD(player.getWorldId());//world + chnl
		writeD(0x00);// unk
		writeC( 0x00);// unk
		writeF(player.getX());// x
		writeF(player.getY());// y
		writeF(player.getZ());// z
		writeC( player.getHeading());// heading
	}
}
