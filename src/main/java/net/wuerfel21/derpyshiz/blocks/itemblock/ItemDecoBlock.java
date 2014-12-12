package net.wuerfel21.derpyshiz.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.blocks.DecoBlocks;
import net.wuerfel21.derpyshiz.blocks.DerpyOres;

public class ItemDecoBlock extends ItemBlockWithMetadata {
	
	public ItemDecoBlock(Block block) {
		super(block,block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < DecoBlocks.numBlocks) {
			return this.getUnlocalizedName() + "_" + DecoBlocks.names[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
}
