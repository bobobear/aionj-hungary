/*
 * This file is part of aion-unique <aion-unique.smfnew.com>.
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

import com.aionemu.gameserver.network.aion.AbstractAionServerPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;

/**
 * @author Lyahim, ATracer
 */
public class SM_ITEM_USAGE_ANIMATION extends AbstractAionServerPacket<AionChannelHandler>
{
	private int	playerObjId;
	private int	itemObjId;
	private int	itemId;
	private int	time;
	private int	end;
	private int	unk;

	public SM_ITEM_USAGE_ANIMATION(int playerObjId, int itemObjId, int itemId)
	{
		this.playerObjId = playerObjId;
		this.itemObjId = itemObjId;
		this.itemId = itemId;
		this.time = 0;
		this.end = 1;
		this.unk = 1;
	}

	public SM_ITEM_USAGE_ANIMATION(int playerObjId, int itemObjId, int itemId, int time, int end, int unk)
	{
		this.playerObjId = playerObjId;
		this.itemObjId = itemObjId;
		this.itemId = itemId;
		this.time = time;
		this.end = end;
		this.unk = unk;
	}

	public SM_ITEM_USAGE_ANIMATION(int playerObjId, int itemObjId, int itemId, int time, int end)
	{
		this.playerObjId = playerObjId;
		this.itemObjId = itemObjId;
		this.itemId = itemId;
		this.time = time;
		this.end = end;
	}

	@Override
	protected void writeImpl(AionChannelHandler cHandler)
	{
		writeD(playerObjId); // player obj id
		writeD(playerObjId); // player obj id 2x - other player? maybe item can be used on other player.

		writeD(itemObjId); // itemObjId
		writeD(itemId); // item id

		writeD(time); // unk
		writeC(end); // unk
		writeC(1); // unk
		writeC(0);
		writeD(unk);
	}
}