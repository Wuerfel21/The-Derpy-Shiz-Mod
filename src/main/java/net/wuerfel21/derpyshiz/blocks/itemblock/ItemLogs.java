package net.wuerfel21.derpyshiz.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.blocks.DerpyLogs;

public class ItemLogs extends ItemBlockWithMetadata {
	
	public ItemLogs(Block block) {
		super(block,block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < DerpyLogs.types.length) {
			return this.getUnlocalizedName() + "_" + DerpyLogs.types[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
}
