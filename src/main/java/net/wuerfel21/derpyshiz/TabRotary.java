package net.wuerfel21.derpyshiz;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabRotary extends CreativeTabs {

	public TabRotary() {
		super("rotary");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(DerpyBlocks.axis);
	}

}
