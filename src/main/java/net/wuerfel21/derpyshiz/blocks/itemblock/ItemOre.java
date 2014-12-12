package net.wuerfel21.derpyshiz.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.blocks.DerpyOres;
import net.wuerfel21.derpyshiz.blocks.PatternWool;

public class ItemOre extends ItemBlockWithMetadata {
	
	public ItemOre(Block block) {
		super(block,block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < DerpyOres.numOres) {
			return this.getUnlocalizedName() + "_" + DerpyOres.names[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
}
