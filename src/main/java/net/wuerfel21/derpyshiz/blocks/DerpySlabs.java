package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.DerpyBlocks;

public class DerpySlabs extends BlockSlab {

	public DerpySlabs(boolean isDoubleSlab, Material material) {
		super(isDoubleSlab, material);
		this.setBlockName("derpyslab");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	// This method returns the unlocalized name based on the metadata. It is
	// canned by ItemSlab
	public String func_150002_b(int metadata) {
		switch (metadata & 7) {
		case 0:
			return this.getUnlocalizedName() + "_ebony";
		case 1:
			return this.getUnlocalizedName() + "_magic";
		default:
			return this.getUnlocalizedName();
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		switch (meta & 7) {
		default:
		case 0:
			return DerpyBlocks.plank.getIcon(side, 0);
		case 1:
			return DerpyBlocks.plank.getIcon(side, 1);
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		if (!this.field_150004_a) { // Only if this is a single slab
			list.add(new ItemStack(item, 1, 0));
			list.add(new ItemStack(item, 1, 1));
		}
	}

	public boolean isDouble() {
		return this.field_150004_a;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);

		if (block == null)
			return null;
		if (block instanceof DerpySlabs && ((DerpySlabs)block).isDouble())
			block = DerpyBlocks.slab;

		int meta = world.getBlockMetadata(x, y, z) & 7;
		return new ItemStack(block, 1, meta);
	}
}
