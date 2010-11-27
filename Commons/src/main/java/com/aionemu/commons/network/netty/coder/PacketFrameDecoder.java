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
package com.aionemu.commons.network.netty.coder;

import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

/**
 * @author lyahim
 * 
 */
public class PacketFrameDecoder extends LengthFieldBasedFrameDecoder
{
	private static final int	MAX_PACKET_LENGTH		= 16384;
	private static final int	LENGTH_FIELD_OFFSET		= 0;
	private static final int	LENGTH_FIELD_LENGTH		= 2;
	private static final int	LENGTH_FIELD_ADJUSTMENT	= -2;
	private static final int	INITIAL_BYTES_TO_STRIP	= 2;

	public PacketFrameDecoder()
	{
		super(MAX_PACKET_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH, LENGTH_FIELD_ADJUSTMENT,
			INITIAL_BYTES_TO_STRIP);
	}
}
