package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.wuerfel21.derpyshiz.GuiIds;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;
import cpw.mods.fml.common.eventhandler.Event.Result;

public class BlockMillstone extends BlockContainer {

	public BlockMillstone() {
		super(Main.machineMaterial);
		this.setBlockName("millstone");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypePiston);
		this.setHarvestLevel("pickaxe", 0);
		this.setResistance(10f);
		this.setHardness(1.5f);
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
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (!(te instanceof TileEntityMillstone)) {
			return;
		}
		((TileEntityMillstone)te).dropInv(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

}
