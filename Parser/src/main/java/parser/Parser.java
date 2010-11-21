/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package parser;

import parser.serverData.cubeExpander.CubeExpanderSave;
import parser.serverData.tribeRelation.TribeRelationSave;
import parser.serverData.warehouseExpander.WarehouseExpanderSave;
import parser.util.DataManager;
import parser.util.FileDecoder;

/**
 * @author Mr. Poke
 *
 */
public class Parser
{

	private static boolean	decodeFile	= false;

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (decodeFile)
		{
			FileDecoder.decode("data", "L10N\\1_enu\\data\\", "strings/", "client_strings.xml");
			FileDecoder.decode("npcs", "data\\npcs\\", "", "npc_tribe_relation.xml");
			FileDecoder.decode("npcs", "data\\npcs\\", "", "client_npcs.xml");
			FileDecoder.decode("Items", "data\\Items\\", "", "client_items.xml");
		}
		DataManager.getInstance();
		
		TribeRelationSave.save();
		CubeExpanderSave.save();
		WarehouseExpanderSave.save();
	}
}
