package net.wuerfel21.derpyshiz.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemRotameter extends Item {
	
	public ItemRotameter() {
		this.setTextureName("derpyshiz:rotameter");
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setFull3D();
		this.setUnlocalizedName("rotameter");
		this.setMaxStackSize(1);
	}
	
}
