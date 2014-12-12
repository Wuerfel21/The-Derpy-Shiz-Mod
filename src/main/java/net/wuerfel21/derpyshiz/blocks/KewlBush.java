package net.wuerfel21.derpyshiz.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

public class KewlBush extends BlockDeadBush {
	
	public KewlBush() {
		super();
		this.setBlockTextureName("derpyshiz:kewl_bush");
		this.setBlockName("kewl_bush");
		this.setHardness(0.0f);
		this.setStepSound(soundTypeGrass);
	}
	
	@Override public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) { return false; }
	
	@Override
	protected boolean canPlaceBlockOn(Block p_149854_1_)
    {
        return true;
    }
	
	@Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(this);
    }
	
}
