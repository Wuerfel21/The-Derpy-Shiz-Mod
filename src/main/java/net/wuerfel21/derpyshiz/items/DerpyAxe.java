package net.wuerfel21.derpyshiz.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class DerpyAxe extends ItemAxe {
	
	public Item repair;
	public int meta;
	
	public DerpyAxe(ToolMaterial material, Item rep, int m) {
		super(material);
		repair = rep;
		meta = m;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack material) {
		return material.getItem() == repair && material.getItemDamage() == meta;
	}
	
}
