package net.wuerfel21.derpyshiz.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.blocks.RotaryHousing;

public class ItemHousing extends ItemBlockWithMetadata {
	
	public ItemHousing(Block block) {
		super(block,block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < 2) {
			return this.getUnlocalizedName() + "_" + RotaryHousing.types[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
}
