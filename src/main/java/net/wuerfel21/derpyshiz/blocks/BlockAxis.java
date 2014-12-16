package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAxis extends Block {
	
	public IIcon blank;
	
	public static final int[] orientationHelper = {0,0,2,2,1,1};
	
	public BlockAxis() {
		super(Material.wood);
		this.setBlockName("axis");
		this.setBlockTextureName("minecraft:oak_plank");
		this.setHarvestLevel("ds_hammer", 0);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockBounds(0.375f, 0.375f, 0.375f, 0.625f, 0.625f, 0.625f);
		this.setHardness(1f);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if ((meta & 8) != 0)return this.blank;
		return Blocks.planks.getIcon(0, 0);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
		int orient = block.getBlockMetadata(x, y, z) & 3;
		switch(orient) {
		case 0:
			this.setBlockBounds(0.375f, 0f, 0.375f, 0.625f, 1f, 0.625f);
			break;
		case 1:
			this.setBlockBounds(0f, 0.375f, 0.375f, 1f, 0.625f, 0.625f);
			break;
		case 2:
			this.setBlockBounds(0.375f, 0.375f, 0f, 0.625f, 0.625f, 1f);
			break;
		case 3: //Dummy
		default:
			this.setBlockBounds(0.375f, 0.375f, 0.375f, 0.625f, 0.625f, 0.625f);
			break;
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world,int x,int y,int z,EntityLivingBase entity,ItemStack stack) {
		super.onBlockPlacedBy(world,x,y,z,entity,stack);
		world.setBlockMetadataWithNotify(x, y, z, orientationHelper[Blocks.piston.determineOrientation(world, x, y, z, entity)], 2);
	}
	
	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.375f, 0.375f, 0f, 0.625f, 0.625f, 1f);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		blank = reg.registerIcon("derpyshiz:null");
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world,int x,int y,int z) {
		double dx = (double)x;
		double dy = (double)y;
		double dz = (double)z;
		return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
}
