package net.wuerfel21.derpyshiz.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.blocks.DecoBlocks;
import net.wuerfel21.derpyshiz.blocks.DerpySaplings;

public class ItemSapling extends ItemBlockWithMetadata {
	
	public ItemSapling(Block block) {
		super(block,block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < 2) {
			return this.getUnlocalizedName() + "_" + DerpySaplings.types[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
}
