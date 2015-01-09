package net.wuerfel21.derpyshiz.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * This event is fired when a player presses the special action key. The item could
 * be not an ISpecialActionItem/null
 * 
 * This event is Cancelable
 * 
 * @author Wurfel_21
 *
 */
@Cancelable
public class SpecialActionEvent extends Event {
	
	public final World world;
	public final EntityPlayer player;
	public final ItemStack stack;
	
	public SpecialActionEvent(EntityPlayer p) {
		player = p;
		world = p.worldObj;
		stack = p.getHeldItem();
	}
	
}
