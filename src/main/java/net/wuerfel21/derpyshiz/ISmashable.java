package net.wuerfel21.derpyshiz;

import net.minecraft.world.World;

/*
 * Represents a block whose direction can be changed using a hammer
 * */
public interface ISmashable {

	
	//called when smashed
	public boolean smashed(World world, int x, int y, int z, int dir, boolean sneaky);

}
