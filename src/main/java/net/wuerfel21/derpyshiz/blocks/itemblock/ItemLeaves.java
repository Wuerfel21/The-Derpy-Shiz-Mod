package net.wuerfel21.derpyshiz.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.blocks.DerpyLeaves;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLeaves extends ItemBlockWithMetadata {
	
	public BlockLeaves block;
	
	public ItemLeaves(Block block) {
		this((BlockLeaves)block);
	}
	
	public ItemLeaves(BlockLeaves block) {
		super(block,block);
		this.block = block; 
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < 2) {
			return this.getUnlocalizedName() + "_" + DerpyLeaves.types[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack p_82790_1_, int p_82790_2_)
    {
        return this.block.getRenderColor(p_82790_1_.getItemDamage());
    }
	
	 /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
	@Override
    public int getMetadata(int p_77647_1_)
    {
        return p_77647_1_ | 4;
    }
	
}
