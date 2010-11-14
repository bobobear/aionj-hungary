/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AbstractAionServerPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;

/**
 * 
 * @author Lyahim, Simple
 * 
 */
public class SM_LEGION_ADD_MEMBER extends AbstractAionServerPacket<AionChannelHandler>
{	
	private Player	player;
	private boolean isMember;
	private int msgId;
	private String text;

	public SM_LEGION_ADD_MEMBER(Player player, boolean isMember, int msgId, String text)
	{
		this.player = player;
		this.isMember = isMember;
		this.msgId = msgId;
		this.text = text;
	}

	@Override
	public void writeImpl(AionChannelHandler cHandler)
	{
		writeD(player.getObjectId());
		writeS(player.getName());
		writeC( player.getLegionMember().getRank().getRankId());
		writeC( isMember ? 0x01 : 0x00);// is New Member?
		writeC( player.getCommonData().getPlayerClass().getClassId());
		writeC( player.getLevel());
		writeD(player.getPosition().getMapId());
		writeD(msgId);
		writeS(text);
	}
}