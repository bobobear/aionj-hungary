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
package com.aionemu.gameserver.newmodel.world;

import com.aionemu.gameserver.newmodel.gameobject.SpawnedObject;
import com.aionemu.gameserver.newmodel.gameobject.player.Player;

import javolution.util.FastList;
import javolution.util.FastMap;

/**
 * Just some part of map.
 * 
 * @author -Nemesiss-
 * 
 */
public class MapRegion
{

	/**
	 * WorldMapInstance witch is parent of this map region.
	 */
	private final WorldMapInstance					parent;

	/**
	 * Surrounding regions + self.
	 */
	private final FastList<MapRegion>				neighbours;

	/**
	 * Objects on this map region.
	 */
	private final FastMap<Integer, SpawnedObject>	objects		= new FastMap<Integer, SpawnedObject>().shared();

	private int										playerCount	= 0;

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param parent
	 */
	MapRegion(WorldMapInstance parent, boolean is3D)
	{
		if (is3D)
			neighbours		= new FastList<MapRegion>(27);
		else
			neighbours		= new FastList<MapRegion>(9);
		this.parent = parent;
		this.neighbours.add(this);
	}

	/**
	 * Return World map id.
	 * 
	 * @return world map id
	 */
	public Integer getMapId()
	{
		return getParent().getMapId();
	}

	/** Return an instance of {@link World}, which keeps map, to which belongs this region */
	public World getWorld()
	{
		return getParent().getWorld();
	}

	public boolean isActive()
	{
		return playerCount > 0;
	}
	
	/**
	 * Returns WorldMapInstance witch is parent of this instance
	 * 
	 * @return parent
	 */
	public WorldMapInstance getParent()
	{
		return parent;
	}

	/**
	 * Returns iterator over AionObjects on this region
	 * 
	 * @return objects iterator
	 */
	public FastMap<Integer, SpawnedObject> getVisibleObjects()
	{
		return objects;
	}

	/**
	 * @return the neighbours
	 */
	public FastList<MapRegion> getNeighbours()
	{
		return neighbours;
	}

	/**
	 * Add neighbour region to this region neighbours list.
	 * 
	 * @param neighbour
	 */
	void addNeighbourRegion(MapRegion neighbour)
	{
		neighbours.add(neighbour);
	}

	/**
	 * Add AionObject to this region objects list.
	 * 
	 * @param object
	 */
	void add(SpawnedObject object)
	{
		if(objects.put(object.getObjectId(), object) == null)
		{
			if(object instanceof Player)
			{
				playerCount++;
			}
		}
	}

	/**
	 * Remove AionObject from region objects list.
	 * 
	 * @param object
	 */
	void remove(SpawnedObject object)
	{
		if(objects.remove(object.getObjectId()) != null)
		{
			if(object instanceof Player)
			{
				playerCount--;
			}
		}
	}
}
