package net.wuerfel21.derpyshiz;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class SimpleMetaItemBlock extends ItemBlockWithMetadata {
	
	public SimpleMetaItemBlock(Block block) {
		super(block,block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (this.field_150939_a instanceof IMetaItemBlock) {
			return ((IMetaItemBlock)this.field_150939_a).getUnlocalizedName(d);
		} else {
			return super.getUnlocalizedName(stack);
		}
	}
	
}
