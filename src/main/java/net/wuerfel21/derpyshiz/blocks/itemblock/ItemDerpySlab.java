package net.wuerfel21.derpyshiz.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.wuerfel21.derpyshiz.blocks.DerpySlabs;

public class ItemDerpySlab extends ItemSlab {
	
	public ItemDerpySlab(Block block, DerpySlabs single_slab, DerpySlabs double_slab, Boolean isDoubleSlab) {
		super(block, single_slab, double_slab, isDoubleSlab);
		
	}
}
