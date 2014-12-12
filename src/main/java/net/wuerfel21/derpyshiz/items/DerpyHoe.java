package net.wuerfel21.derpyshiz.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class DerpyHoe extends ItemHoe {
	
	public Item repair;
	public int meta;
	
	public DerpyHoe(ToolMaterial material, Item rep, int m) {
		super(material);
		repair = rep;
		meta = m;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack material) {
		return material.getItem() == repair && material.getItemDamage() == meta;
	}
	
}
