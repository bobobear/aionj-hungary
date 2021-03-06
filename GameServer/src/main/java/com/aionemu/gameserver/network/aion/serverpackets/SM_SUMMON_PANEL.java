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

import com.aionemu.gameserver.model.gameobjects.instance.Summon;
import com.aionemu.gameserver.model.gameobjects.stats.StatEnum;
import com.aionemu.gameserver.network.aion.AbstractAionServerPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;

/**
 * @author Lyahim, ATracer
 *
 */
public class SM_SUMMON_PANEL extends AbstractAionServerPacket<AionChannelHandler>
{
	private Summon	summon;

	public SM_SUMMON_PANEL(Summon summon)
	{
		this.summon = summon;
	}

	@Override
	protected void writeImpl(AionChannelHandler cHandler)
	{

		writeD(summon.getObjectId());
		writeH(summon.getLevel());
		writeD(0);//unk
		writeD(0);//unk
		writeD(summon.getLifeStats().getCurrentHp());
		writeD(summon.getGameStats().getCurrentStat(StatEnum.MAXHP));
		writeD(summon.getGameStats().getCurrentStat(StatEnum.MAIN_HAND_POWER));
		writeH(summon.getGameStats().getCurrentStat(StatEnum.PHYSICAL_DEFENSE));
		writeH(summon.getGameStats().getCurrentStat(StatEnum.MAGICAL_RESIST));
		writeD(0);//unk
		writeH(0);//unk
	}

}
