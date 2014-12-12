package net.wuerfel21.derpyshiz.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.blocks.LightBlocks;

public class ItemLamp extends ItemBlockWithMetadata {

	public ItemLamp(Block block) {
		super(block,block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < LightBlocks.numLamps) {
			return this.getUnlocalizedName() + "_" + LightBlocks.names[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
}
