package system.handlers.quest.beluslan;

import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.utils.PacketSendUtility;

/*
 * author : Altaress
 */
public class _2515LeanorsErrand extends QuestHandler
{
	private final static int	questId	= 2515;

	public _2515LeanorsErrand()
	{
		super(questId);
	}

	@Override
	public void register()
	{
		qe.setNpcQuestData(790015).addOnQuestStart(questId);
		qe.setNpcQuestData(790015).addOnTalkEvent(questId);
		qe.setNpcQuestData(204192).addOnTalkEvent(questId);
		qe.setNpcQuestData(204205).addOnTalkEvent(questId);
		qe.setNpcQuestData(798081).addOnTalkEvent(questId);
	}

	@Override
	public boolean onDialogEvent(QuestEnv env)
	{
		final Player player = env.getPlayer();
		int targetId = 0;
		if (env.getVisibleObject() instanceof Npc)
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (targetId == 790015)
		{
			if (qs == null || qs.getStatus() == QuestStatus.NONE)
			{
				if (env.getDialogId() == 25)
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 1011);
				else
					return defaultQuestStartDialog(env);
			}
			else if (qs != null && qs.getStatus() == QuestStatus.START)
			{
				if (env.getDialogId() == 25)
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 2375);
				else if (env.getDialogId() == 1009)
				{
					qs.setStatus(QuestStatus.REWARD);
					updateQuestStatus(player, qs);
					PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					return true;
				}
				else
					return defaultQuestEndDialog(env);
			}
			else if (qs != null && qs.getStatus() == QuestStatus.REWARD)
			{
				return defaultQuestEndDialog(env);
			}
		}
		else if (targetId == 204192)
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
		else if (targetId == 204205)
		{
			if (qs != null && qs.getStatus() == QuestStatus.START && qs.getQuestVarById(0) == 1)
			{
				if (env.getDialogId() == 25)
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 1693);
				else if (env.getDialogId() == 10001)
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
		else if (targetId == 798081)
		{
			if (qs != null && qs.getStatus() == QuestStatus.START && qs.getQuestVarById(0) == 2)
			{
				if (env.getDialogId() == 25)
					return sendQuestDialog(player, env.getVisibleObject().getObjectId(), 2034);
				else if (env.getDialogId() == 10002)
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
		else if (targetId == 790015)
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
