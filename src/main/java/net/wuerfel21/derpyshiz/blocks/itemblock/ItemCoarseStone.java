package net.wuerfel21.derpyshiz.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.blocks.CoarseStone;
import net.wuerfel21.derpyshiz.blocks.DecoBlocks;

public class ItemCoarseStone extends ItemBlockWithMetadata {
	
	public ItemCoarseStone(Block block) {
		super(block,block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < CoarseStone.numBlocks) {
			return this.getUnlocalizedName() + "_" + CoarseStone.names[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
}
