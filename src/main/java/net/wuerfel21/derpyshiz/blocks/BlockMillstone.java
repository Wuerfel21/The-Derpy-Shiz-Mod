package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.wuerfel21.derpyshiz.DerpyBlocks;
import net.wuerfel21.derpyshiz.GuiIds;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;
import cpw.mods.fml.common.eventhandler.Event.Result;

public class BlockMillstone extends BlockContainer {

	public static IIcon[] icons = new IIcon[4];
	
	public BlockMillstone() {
		super(Main.machineMaterial);
		this.setBlockName("millstone");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypePiston);
		this.setHarvestLevel("ds_hammer", 0);
		this.setResistance(10f);
		this.setHardness(1.5f);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("derpyshiz:coarse_stone_framed");
		} else {
			this.setBlockTextureName("derpyshiz:millstone_ugly");
		}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new TileEntityMillstone();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float offsetX, float offsetY, float offsetZ) {
		PlayerInteractEvent e = new PlayerInteractEvent(player, Action.RIGHT_CLICK_BLOCK, x, y, z, side, world);
		if (MinecraftForge.EVENT_BUS.post(e) || e.getResult() == Result.DENY || e.useBlock == Result.DENY) {
			return false;
		}

		TileEntity te = (TileEntityMillstone) world.getTileEntity(x, y, z);
		if (te == null || te instanceof TileEntityMillstone == false) {
			return false;
		}

		if (!world.isRemote) {
			player.openGui(Main.instance, GuiIds.MILLSTONE, world, x, y, z);
		}

		return true;
	}

	public void dropBlockAsItem(World world, int x, int y, int z,ItemStack stack) {
		super.dropBlockAsItem(world, x, y, z, stack);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (Main.fancyGearbox) {
			switch (meta) {
			default:
			case 0:
				return DerpyBlocks.coarseStone.getIcon(side, 1);
			case 1:
				return DerpyBlocks.oreBlocks.getIcon(side, 2);
			}
		} else {
			return icons[((meta & 1)*2)+(side<2?1:0)];
		}
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		if (!Main.fancyGearbox) {
			icons[0] = reg.registerIcon(this.getTextureName()+"_normal_0");
			icons[1] = reg.registerIcon(this.getTextureName()+"_normal_1");
			icons[2] = reg.registerIcon(this.getTextureName()+"_advanced_0");
			icons[3] = reg.registerIcon(this.getTextureName()+"_advanced_1");
		}
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return !Main.fancyGearbox;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (!(te instanceof TileEntityMillstone)) {
			return;
		}
		((TileEntityMillstone)te).dropInv(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
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
	public String getUnlocalizedName() {
		// TODO Auto-generated method stub
		return super.getUnlocalizedName();
	}
	
	public static final String[] types = {"normal","advanced"};
	
}
