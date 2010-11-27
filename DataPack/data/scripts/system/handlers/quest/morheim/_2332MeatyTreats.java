/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 * aion-unique is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aion-unique is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package system.handlers.quest.morheim;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.QuestService;

/**
 * @author stpavel
 * 
 */

public class _2332MeatyTreats extends QuestHandler
{
	private final static int	questId	= 2332;

	public _2332MeatyTreats()
	{
		super(questId);
	}

	@Override
	public void register()
	{
		qe.setNpcQuestData(798084).addOnQuestStart(questId);
		qe.setNpcQuestData(798084).addOnTalkEvent(questId);
	}

	@Override
	public boolean onDialogEvent(QuestEnv env)
	{
		final Player player = env.getPlayer();
		int targetId = 0;
		if (env.getVisibleObject() instanceof Npc)
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs == null || qs.getStatus() == QuestStatus.NONE)
		{
			if (targetId == 798084)
			{
				if (env.getDialogId() == 25)
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 4762);
				else
					return defaultQuestStartDialog(env);
			}
		}
		else if (qs != null && qs.getStatus() == QuestStatus.START)
		{
			if (targetId == 798084)
			{
				if (env.getDialogId() == 25)
				{
					if (QuestService.collectItemCheck(env, true))
						return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 1352);
					else
						return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 1693);
				}
				else if (env.getDialogId() >= 10000 && env.getDialogId() <= 10002)
				{
					qs.setQuestVarById(0, qs.getQuestVarById(0) + (env.getDialogId() - 10000));
					qs.setStatus(QuestStatus.REWARD);
					updateQuestStatus(player, qs);
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), env.getDialogId() - 9995);

				}
			}
		}
		else if (env.getDialogId() == 17 && qs.getStatus() == QuestStatus.REWARD && targetId == 798084)
		{
			QuestService.questFinish(env, qs.getQuestVarById(0));
			return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 1008);
		}
		else if (qs.getStatus() == QuestStatus.COMPLETE && targetId == 798084)
		{
			return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 1008);
		}
		return false;
	}
}
