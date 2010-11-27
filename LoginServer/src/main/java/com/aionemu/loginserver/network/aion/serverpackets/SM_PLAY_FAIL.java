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
package com.aionemu.loginserver.network.aion.serverpackets;

import com.aionemu.commons.network.netty.packet.AbstractServerPacket;
import com.aionemu.loginserver.network.aion.AionAuthResponse;
import com.aionemu.loginserver.network.aion.AionChannelHandler;

/**
 * @author -Nemesiss-, Lyahim
 * 
 */
public class SM_PLAY_FAIL extends AbstractServerPacket<AionChannelHandler> {
	/**
	 * response - why play fail
	 */
	private AionAuthResponse response;

	/**
	 * Constructs new instance of <tt>SM_PLAY_FAIL</tt> packet.
	 * 
	 * @param response
	 *            auth response
	 */
	public SM_PLAY_FAIL(AionAuthResponse response) {
		this.response = response;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void writeImpl(AionChannelHandler cHandler) {
		writeD(response.getMessageId());
	}
}
