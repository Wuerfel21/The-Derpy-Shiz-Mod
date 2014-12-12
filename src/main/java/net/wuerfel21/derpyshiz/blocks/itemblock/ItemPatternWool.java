package net.wuerfel21.derpyshiz.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.blocks.PatternWool;

public class ItemPatternWool extends ItemBlockWithMetadata {
	
	public ItemPatternWool(Block block) {
		super(block,block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < 16) {
			return this.getUnlocalizedName() + "_" + PatternWool.patterns[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
}
