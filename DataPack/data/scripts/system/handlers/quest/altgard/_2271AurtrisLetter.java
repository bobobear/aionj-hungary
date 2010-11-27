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
package system.handlers.quest.altgard;

import java.util.Collections;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.quest.QuestItems;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.services.ItemService;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author DTA3000
 * 
 */
public class _2271AurtrisLetter extends QuestHandler
{
	private final static int	questId	= 2271;

	public _2271AurtrisLetter()
	{
		super(questId);
	}

	@Override
	public void register()
	{
		qe.setNpcQuestData(203655).addOnQuestStart(questId);
		qe.setNpcQuestData(203655).addOnTalkEvent(questId);
		qe.setNpcQuestData(203654).addOnTalkEvent(questId);
		qe.setNpcQuestData(203557).addOnTalkEvent(questId);
		deletebleItems = new int[]
		{ 182203247 };
	}

	@Override
	public boolean onDialogEvent(QuestEnv env)
	{
		final Player player = env.getPlayer();
		int targetId = 0;
		if (env.getVisibleObject() instanceof Npc)
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (targetId == 203655)
		{
			if (qs == null || qs.getStatus() == QuestStatus.NONE)
			{
				if (env.getDialogId() == 25)
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 1011);
				else if (env.getDialogId() == 1002)
				{
					if (ItemService.addItems(player, Collections.singletonList(new QuestItems(182203247, 1))))
						return defaultQuestStartDialog(env);
					else
						return true;
				}
				else
					return defaultQuestStartDialog(env);
			}
			else if (qs.getStatus() == QuestStatus.START)
			{
				if (env.getDialogId() == 25)
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 2375);
			}
		}
		else if (targetId == 203654)
		{
			if (qs != null && qs.getStatus() == QuestStatus.START && qs.getQuestVarById(0) == 0)
			{
				if (env.getDialogId() == 25)
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 1352);
				else if (env.getDialogId() == 10000)
				{
					qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
					updateQuestStatus(player, qs);
					PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					return true;
				}
				else
					return defaultQuestStartDialog(env);
			}
		}
		else if (targetId == 203557)
		{
			if (qs != null)
			{
				if (env.getDialogId() == 25 && qs.getStatus() == QuestStatus.START)
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 2375);
				else if (env.getDialogId() == 1009)
				{
					qs.setQuestVar(3);
					qs.setStatus(QuestStatus.REWARD);
					updateQuestStatus(player, qs);
					return defaultQuestEndDialog(env);
				}
				else
					return defaultQuestEndDialog(env);
			}
		}
		return false;
	}
}
