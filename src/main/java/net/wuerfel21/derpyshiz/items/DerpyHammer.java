package net.wuerfel21.derpyshiz.items;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class DerpyHammer extends ItemTool {
	
	public DerpyHammer(ToolMaterial m) {
		super(1f,m,new HashSet());
		this.setMaxDamage(getMaxDamage()/2);
	}
	
	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		Set set = new HashSet();
		set.add("ds_hammer");
		return set;
	}
	
}
