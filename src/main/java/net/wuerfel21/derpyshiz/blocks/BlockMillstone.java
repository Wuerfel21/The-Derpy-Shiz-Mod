package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.wuerfel21.derpyshiz.DerpyBlocks;
import net.wuerfel21.derpyshiz.GuiIds;
import net.wuerfel21.derpyshiz.IMetaItemBlock;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;
import cpw.mods.fml.common.eventhandler.Event.Result;

public class BlockMillstone extends BlockContainer implements IMetaItemBlock {

	public static IIcon[] icons = new IIcon[4];
	
	public BlockMillstone() {
		super(Main.machineMaterial);
		this.setBlockName("derpyshiz.millstone");
		this.setCreativeTab(Main.tabRotary);
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
		return new TileEntityMillstone();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack stack) {
		if (stack.hasDisplayName())
        {
            ((TileEntityMillstone)world.getTileEntity(x, y, z)).name = stack.getDisplayName();
        }
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float offsetX, float offsetY, float offsetZ) {

		TileEntity te = world.getTileEntity(x, y, z);
		if (te == null || te instanceof TileEntityMillstone == false) {
			return false;
		}

		if (!world.isRemote) {
			player.openGui(Main.instance, GuiIds.MILLSTONE, world, x, y, z);
		}

		return true;
	}

	public void dropBlockAsItem(World world, int x, int y, int z,ItemStack stack) {
		//Because visibility :-/
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
				return Blocks.obsidian.getIcon(side, 0);
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
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < types.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	public static final String[] types = {"normal","advanced"};

	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName() + "_" +types[meta%types.length];
	}
	
}
