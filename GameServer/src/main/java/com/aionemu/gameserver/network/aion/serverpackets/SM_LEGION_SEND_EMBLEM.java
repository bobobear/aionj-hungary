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

import com.aionemu.gameserver.network.aion.AbstractAionServerPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;

/**
 * @author Lyahim, Simple
 * 
 */
public class SM_LEGION_SEND_EMBLEM extends AbstractAionServerPacket<AionChannelHandler>
{
	/** Legion information **/
	private int		legionId;
	private int		emblemId;
	private int		color_r;
	private int		color_g;
	private int		color_b;
	private String	legionName;

	/**
	 * This constructor will handle legion emblem info
	 * 
	 * @param legionId
	 */
	public SM_LEGION_SEND_EMBLEM(int legionId, int emblemId, int color_r, int color_g, int color_b, String legionName)
	{
		this.legionId = legionId;
		this.emblemId = emblemId;
		this.color_r = color_r;
		this.color_g = color_g;
		this.color_b = color_b;
		this.legionName = legionName;
	}

	@Override
	public void writeImpl(AionChannelHandler cHandler)
	{
		writeD(legionId);
		writeH(emblemId);
		writeD(0x00);
		writeC(0xFF); // unk
		writeC(color_r);
		writeC(color_g);
		writeC(color_b);
		writeS(legionName);
		writeC(0x01);

		// ED 55 8A 6C 04 00 00 01 80 00 00 00 00 FF FF FF .U.l............
		// FF 44 00 72 00 61 00 6B 00 65 00 73 00 00 00 01 .D.r.a.k.e.s....

	}
}
