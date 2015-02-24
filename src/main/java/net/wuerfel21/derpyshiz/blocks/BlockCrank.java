package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.wuerfel21.derpyshiz.IMetaItemBlock;
import net.wuerfel21.derpyshiz.ISmashable;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCrank;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;

public class BlockCrank extends Block implements ITileEntityProvider, ISmashable, IMetaItemBlock {

	public BlockCrank() {
		super(Main.machineMaterial);
		this.setHardness(3f);
		this.setBlockName("derpyshiz.crank");
		this.setCreativeTab(Main.tabRotary);
		this.setHarvestLevel("ds_hammer", 0);
		this.setStepSound(soundTypeWood);
		this.setLightOpacity(0);
	}

	@Override
	public boolean smashed(World world, int x, int y, int z, int dir, boolean sneaky) {
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
	public IIcon getIcon(int side, int meta) {
		switch (meta) {
		default:
		case 0:
			return Blocks.planks.getIcon(0, 0);
		case 1:
			return Blocks.iron_block.getIcon(0, 0);
		}
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
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return false;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
		TileEntity t = world.getTileEntity(x, y, z);
		if (t instanceof TileEntityCrank) {
			((TileEntityCrank) t).cleanup();
		}
		super.breakBlock(world, x, y, z, block, p_149749_6_);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (!world.isRemote) {
			TileEntity datTemp = world.getTileEntity(x, y, z);
			if (datTemp instanceof TileEntityCrank) {
				TileEntityCrank te = (TileEntityCrank) datTemp;
				if (te.cooldown <= 0) {
					te.cooldown = te.cooldowns[te.getTier()];
					player.getFoodStats().addExhaustion(te.getTier() == 0 ? 0.5f : 0.8f);
				}
			}
		}
		return true;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName() + "_" + RotaryHousing.types[meta];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < RotaryHousing.types.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister p_149651_1_) {
	}

}
