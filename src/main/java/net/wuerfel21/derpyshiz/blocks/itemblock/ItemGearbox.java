package net.wuerfel21.derpyshiz.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.blocks.BlockGearbox;
import net.wuerfel21.derpyshiz.blocks.DecoBlocks;

public class ItemGearbox extends ItemBlockWithMetadata {
	
	public ItemGearbox(Block block) {
		super(block,block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < 2) {
			return this.getUnlocalizedName() + "_" + BlockGearbox.types[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
}
