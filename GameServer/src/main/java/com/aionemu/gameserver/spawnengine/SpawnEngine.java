/*
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
package com.aionemu.gameserver.spawnengine;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.instance.GroupGate;
import com.aionemu.gameserver.model.gameobjects.instance.Kisk;
import com.aionemu.gameserver.model.gameobjects.instance.Postman;
import com.aionemu.gameserver.model.gameobjects.instance.Servant;
import com.aionemu.gameserver.model.gameobjects.instance.Summon;
import com.aionemu.gameserver.model.gameobjects.instance.Trap;
import com.aionemu.gameserver.model.gameobjects.knownList.StaticObjectKnownList;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.NpcInfo;
import com.aionemu.gameserver.model.templates.NpcTemplate;
import com.aionemu.gameserver.model.templates.WorldMapTemplate;
import com.aionemu.gameserver.model.templates.spawn.SpawnTemplate;
import com.aionemu.gameserver.model.templates.stats.SummonStatsTemplate;
import com.aionemu.gameserver.taskmanager.tasks.RespawnTaskManager;
import com.aionemu.gameserver.utils.idfactory.IDFactory;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.exceptions.NotSetPositionException;

/**
 * 
 * This class is responsible for NPCs spawn management. Current implementation is temporal and will be replaced in the
 * future.
 * 
 * @author Luno
 * 
 *         modified by ATracer
 * 
 */
public class SpawnEngine
{
	private static Logger						log					= Logger.getLogger(SpawnEngine.class);

	private Map<Integer, List<SpawnTemplate>>	spawnByTemplateId	= new HashMap<Integer, List<SpawnTemplate>>();
	private Map<Integer, List<SpawnTemplate>>	spawnByMapId		= new HashMap<Integer, List<SpawnTemplate>>();

	/** Counter counting number of npc spawns */
	private int									npcCounter			= 0;
	/** Counter counting number of gatherable spawns */
	private int									gatherableCounter	= 0;

	public static final SpawnEngine getInstance()
	{
		return SingletonHolder.instance;
	}

	private SpawnEngine()
	{
	}

	public void addSpawn(SpawnTemplate spawnTemplate)
	{
		int mapId = spawnTemplate.getMapId();
		if (!spawnByMapId.containsKey(mapId))
		{
			spawnByMapId.put(mapId, new ArrayList<SpawnTemplate>());
		}
		spawnByMapId.get(mapId).add(spawnTemplate);

		int templateId = spawnTemplate.getTemplateId();
		if (!spawnByTemplateId.containsKey(templateId))
		{
			spawnByTemplateId.put(templateId, new ArrayList<SpawnTemplate>());
		}
		spawnByTemplateId.get(templateId).add(spawnTemplate);
	}

	public SpawnTemplate getFirstSpawnByNpcId(int templateId)
	{
		if (!spawnByTemplateId.containsKey(templateId))
			throw new NotSetPositionException();
		return spawnByTemplateId.get(templateId).get(0);
	}

	/**
	 * Creates VisibleObject instance and spawns it using given {@link SpawnTemplate} instance.
	 * 
	 * @param spawn
	 * @return created and spawned VisibleObject
	 */
	public VisibleObject spawnObject(SpawnTemplate spawn, int instanceIndex)
	{
		int objectId = spawn.getTemplateId();
		
		NpcInfo npcInfo = DataManager.OBJECT_INFOS_DATA.getNpcInfoByTemplateId(spawn.getTemplateId());
		Class<?> clazz;
		if (objectId > 400000 && objectId < 499999)// gatherable
		{
			try
			{
				clazz = Class.forName("com.aionemu.gameserver.model.gameobjects.instance.Gatherable");
			}
			catch (ClassNotFoundException e)
			{
				log.warn(e);
				return null;
			}
			gatherableCounter++;
		}
		else
		// npc
		{
			if (DataManager.NPC_DATA.getNpcTemplate(spawn.getTemplateId()) == null)
			{
				log.warn("Missing npc template!! Template id: " + spawn.getTemplateId());
				return null;
			}
			try
			{
				if (npcInfo == null)
					clazz = Class.forName("com.aionemu.gameserver.model.gameobjects.instance.SpawnedObject");
				else
					clazz = Class.forName("com.aionemu.gameserver.model.gameobjects.instance." + npcInfo.getClassName());
			}
			catch (ClassNotFoundException e)
			{
				log.warn(e);
				return null;
			}
			npcCounter++;
		}
		IDFactory iDFactory = IDFactory.getInstance();

		VisibleObject object;
		try
		{
			Object[] parameters =
			{ iDFactory.nextId(), spawn };
			Constructor<?> constructor = clazz.getConstructor(Integer.TYPE, SpawnTemplate.class);
			object = (VisibleObject) constructor.newInstance(parameters);
		}
		catch (Exception e)
		{
			log.error("Spawn error create class: " + npcInfo.getClassName(), e);
			return null;
		}
		object.getPosition().setInstanceId(instanceIndex);
		World.getInstance().storeObject(object);
		long respawnTime = spawn.getNextRespawntTime(instanceIndex);
		if (respawnTime != 0)
		{
			RespawnTaskManager.getInstance().addRespawnTask(object, respawnTime);
			return object;
		}
		object.onRespawn();
		return object;
	}

	/**
	 * 
	 * @param spawn
	 * @param instanceIndex
	 * @param creator
	 * @return
	 */
	public Trap spawnTrap(SpawnTemplate spawn, int instanceIndex, Creature creator, int skillId)
	{
		Trap trap = new Trap(IDFactory.getInstance().nextId(), spawn);
		trap.setMaster(creator);
		trap.setSkillId(skillId);
		trap.getPosition().setInstanceId(instanceIndex);
		World.getInstance().storeObject(trap);
		trap.onRespawn();
		return trap;
	}

	/**
	*
	 * @param spawn
	 * @param instanceIndex
	 * @param creator
	 * @return
	 */
	public GroupGate spawnGroupGate(SpawnTemplate spawn, int instanceIndex, Creature creator)
	{
		GroupGate groupgate = new GroupGate(IDFactory.getInstance().nextId(), spawn);
		groupgate.setKnownlist(new StaticObjectKnownList(groupgate));
		groupgate.setMaster(creator);
		groupgate.getPosition().setInstanceId(instanceIndex);
		World.getInstance().storeObject(groupgate);
		groupgate.onRespawn();
		return groupgate;
	}

	/**
	 * @param spawn
	 * @param instanceIndex
	 * @param creator
	 * @return
	 */
	public Kisk spawnKisk(SpawnTemplate spawn, int instanceIndex, Player creator)
	{
		Kisk kisk = new Kisk(IDFactory.getInstance().nextId(), spawn);
		kisk.setKnownlist(new StaticObjectKnownList(kisk));
		kisk.getPosition().setInstanceId(instanceIndex);
		kisk.setMaster(creator);
		World.getInstance().storeObject(kisk);
		kisk.onRespawn();
		return kisk;
	}

	/**
	 * @param recipient
	 * @author leo
	 * 
	 * Spawns postman for express mail
	 */
	public void spawnPostman(Player recipient)
	{
		IDFactory iDFactory = IDFactory.getInstance();
		int worldId = recipient.getWorldId();
		int instanceId = recipient.getInstanceId();
		float x = recipient.getX();
		float y = recipient.getY();
		float z = recipient.getZ();
		byte heading = recipient.getHeading();
		SpawnTemplate spawn = addNewSpawn(worldId, instanceId, 798044, x, y, z, heading, 0, 0, false, true);
		Postman postman = new Postman(iDFactory.nextId(), spawn);
		postman.setKnownlist(new StaticObjectKnownList(postman));
		postman.getPosition().setInstanceId(instanceId);
		World.getInstance().storeObject(postman);
	}

	/**
	 * 
	 * @param spawn
	 * @param instanceIndex
	 * @param creator
	 * @param skillId
	 * @return
	 */
	public Servant spawnServant(SpawnTemplate spawn, int instanceIndex, Creature creator, int skillId, int hpRatio)
	{
		Servant servant = new Servant(IDFactory.getInstance().nextId(), spawn);
		servant.setMaster(creator);
		servant.setSkillId(skillId);
		servant.setTarget(creator.getTarget());
		servant.setHpRatio(hpRatio);
		servant.getPosition().setInstanceId(instanceIndex);
		World.getInstance().storeObject(servant);
		servant.onRespawn();
		return servant;
	}

	/**
	 * 
	 * @param creator
	 * @param npcId
	 * @return
	 */
	public Summon spawnSummon(Player creator, int npcId, int skillLvl)
	{
		float x = creator.getX();
		float y = creator.getY();
		float z = creator.getZ();
		byte heading = creator.getHeading();
		int worldId = creator.getWorldId();
		int instanceId = creator.getInstanceId();

		SpawnTemplate spawn = createSpawnTemplate(worldId, npcId, x, y, z, heading, 0, 0);
		NpcTemplate npcTemplate = DataManager.NPC_DATA.getNpcTemplate(npcId);

		byte level = (byte) (npcTemplate.getLevel() + skillLvl - 1);
		SummonStatsTemplate statsTemplate = DataManager.SUMMON_STATS_DATA.getSummonTemplate(npcId, level);
		Summon summon = new Summon(IDFactory.getInstance().nextId(), spawn, npcTemplate, statsTemplate, level);
		summon.setMaster(creator);
		summon.getPosition().setInstanceId(instanceId);
		World.getInstance().storeObject(summon);
		summon.onRespawn();
		return summon;
	}

	/**
	 * 
	 * @param worldId
	 * @param objectId
	 * @param x
	 * @param y
	 * @param z
	 * @param heading
	 * @param walkerid
	 * @param randomwalk
	 * @return
	 */
	private SpawnTemplate createSpawnTemplate(int worldId, int templateId, float x, float y, float z, byte heading, int walkerid, int randomwalk)
	{
		SpawnTemplate spawnTemplate = new SpawnTemplate(worldId, templateId, x, y, z, heading);
		return spawnTemplate;
	}

	/**
	 * Should be used when need to define whether spawn will be deleted after death Using this method spawns will not be
	 * saved with //save_spawn command
	 * 
	 * @param worldId
	 * @param objectId
	 * @param x
	 * @param y
	 * @param z
	 * @param heading
	 * @param walkerid
	 * @param randomwalk
	 * @param noRespawn
	 * @return SpawnTemplate
	 */
	public SpawnTemplate addNewSpawn(int worldId, int instanceId, int objectId, float x, float y, float z, byte heading, int walkerid, int randomwalk,
			boolean noRespawn)
	{
		return this.addNewSpawn(worldId, instanceId, objectId, x, y, z, heading, walkerid, randomwalk, noRespawn, false);
	}

	/**
	 * 
	 * @param worldId
	 * @param instanceId
	 * @param objectId
	 * @param x
	 * @param y
	 * @param z
	 * @param heading
	 * @param walkerid
	 * @param randomwalk
	 * @param noRespawn
	 * @param isNewSpawn
	 * @return SpawnTemplate
	 */
	public SpawnTemplate addNewSpawn(int worldId, int instanceId, int objectId, float x, float y, float z, byte heading, int walkerid, int randomwalk,
			boolean noRespawn, boolean isNewSpawn)
	{
		SpawnTemplate spawnTemplate = createSpawnTemplate(worldId, objectId, x, y, z, heading, walkerid, randomwalk);

		if (spawnTemplate == null)
		{
			log.warn("Object couldn't be spawned");
			return null;
		}

		return spawnTemplate;
	}

	/**
	 * Spawn all NPC's from templates
	 */
	public void spawnAll()
	{
		this.npcCounter = 0;
		this.gatherableCounter = 0;

		for (WorldMapTemplate worldMapTemplate : DataManager.WORLD_MAPS_DATA)
		{
			if (worldMapTemplate.isInstance())
				continue;
			int maxTwin = worldMapTemplate.getTwinCount();
			final int mapId = worldMapTemplate.getMapId();
			int numberToSpawn = maxTwin > 0 ? maxTwin : 1;

			for (int i = 1; i <= numberToSpawn; i++)
			{
				spawnInstance(mapId, i);
			}
		}

		log.info("Loaded " + npcCounter + " npc spawns");
		log.info("Loaded " + gatherableCounter + " gatherable spawns");

		RiftSpawnManager.startRiftPool();
	}

	/**
	 * 
	 * @param worldId
	 * @param instanceIndex
	 */
	public void spawnInstance(int worldId, int instanceIndex)
	{

		int instanceSpawnCounter = 0;
		List<SpawnTemplate> spawns = spawnByMapId.get(worldId);
		if (spawns != null)
		{
			for (SpawnTemplate spawnTemplate : spawns)
			{
				spawnObject(spawnTemplate, instanceIndex);

				instanceSpawnCounter++;
			}
		}
		log.info("Spawned " + worldId + " [" + instanceIndex + "] : " + instanceSpawnCounter);
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final SpawnEngine	instance	= new SpawnEngine();
	}
}
