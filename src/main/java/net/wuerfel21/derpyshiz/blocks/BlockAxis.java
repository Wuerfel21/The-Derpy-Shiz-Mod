package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.ISmashable;
import net.wuerfel21.derpyshiz.Main;

public class BlockAxis extends Block implements ISmashable {
	
	public IIcon blank;
	
	public BlockAxis() {
		super(Material.wood);
		this.setBlockName("derpyshiz.axis");
		this.setBlockTextureName("minecraft:oak_plank");
		this.setHarvestLevel("ds_hammer", 0);
		this.setCreativeTab(Main.tabRotary);
		this.setBlockBounds(0.375f, 0.375f, 0.375f, 0.625f, 0.625f, 0.625f);
		this.setHardness(1f);
		this.setStepSound(soundTypeWood);
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
		world.setBlockMetadataWithNotify(x, y, z, Main.orientationHelper[Blocks.piston.determineOrientation(world, x, y, z, entity)], 2);
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
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean smashed(World world, int x, int y, int z, int dir, boolean sneaky) {
		int meta = world.getBlockMetadata(x, y, z);
		int newDir = Main.orientationHelper[dir];
		if ((meta & 3) == newDir) return true;
		world.setBlockMetadataWithNotify(x, y, z, newDir, 3);
		return false;
	}
	
}
