package net.wuerfel21.derpyshiz;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ISpecialActionItem {
	
	public void specialAction(ItemStack stack, EntityPlayer player, boolean isClient);
	
}
