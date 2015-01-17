package net.wuerfel21.derpyshiz;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public abstract class ItemModeHelper {
	
	public static void displayMode(ItemStack stack, List list) {
		if (!(stack.getItem() instanceof IModeItem)) return;
		list.add(StatCollector.translateToLocal("mode.derpyshiz.mode.name")+": " + StatCollector.translateToLocal(((IModeItem)stack.getItem()).getModeName(getMode(stack))));
	}
	
	public static int getMode(ItemStack stack) {
		if (!(stack.getItem() instanceof IModeItem)) return 0;
		int numModes = ((IModeItem)stack.getItem()).getNumModes();
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {setMode(stack, 0); tag = stack.getTagCompound();}
		return tag.getInteger("mode")%numModes;
	}
	
	public static void setMode(ItemStack stack, int mode) {
		if (!(stack.getItem() instanceof IModeItem)) return;
		int numModes = ((IModeItem)stack.getItem()).getNumModes();
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		tag.setInteger("mode", mode%numModes);
		stack.setTagCompound(tag);
	}
	
}
