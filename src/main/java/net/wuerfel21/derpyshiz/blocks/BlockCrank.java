package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.ISmashable;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCrank;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;

public class BlockCrank extends Block implements ITileEntityProvider, ISmashable {

	public BlockCrank() {
		super(Material.wood);
		this.setHardness(3f);
		this.setBlockName("crank");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHarvestLevel("ds_hammer", 0);
		this.setStepSound(soundTypeWood);
	}
	
	@Override
	public boolean smashed(World world, int x, int y, int z, int dir) {
		TileEntityCrank t = (TileEntityCrank) world.getTileEntity(x, y, z);
		if (t.dir == Main.reverseHelper[dir])
			return true;
		t.rotate(Main.reverseHelper[dir]);
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCrank();
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityCrank) {
			((TileEntityCrank) te).rotate(Main.reverseHelper[Blocks.piston.determineOrientation(world, x, y, z, entity)]);
		} else {
			System.out.println("WTF BOOM!!!11!!!!!");
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
		TileEntity t = world.getTileEntity(x, y, z);
		if (t instanceof TileEntityGearbox) {
			((TileEntityCrank) t).cleanup();
		}
		super.breakBlock(world, x, y, z, block, p_149749_6_);
	}
	
}
