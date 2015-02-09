package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.DerpyBlocks;
import net.wuerfel21.derpyshiz.IMetaItemBlock;
import net.wuerfel21.derpyshiz.ISmashable;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxSplitting;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockSplittingGearbox extends Block implements ISmashable, ITileEntityProvider, IMetaItemBlock {

	public IIcon[] icons = new IIcon[2];

	public BlockSplittingGearbox() {
		super(Main.machineMaterial);
		this.setHardness(3f);
		this.setBlockName("derpyshiz.gearbox_splitting");
		this.setCreativeTab(Main.tabRotary);
		this.setHarvestLevel("ds_hammer", 0);
		this.setStepSound(soundTypeWood);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("minecraft:planks_acacia");
		} else {
			this.setBlockTextureName("derpyshiz:gearbox_splitting_ugly");
		}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGearboxSplitting();
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityGearboxSplitting) {
			((TileEntityGearboxSplitting) te).rotate(Blocks.piston.determineOrientation(world, x, y, z, entity), false);
			((TileEntityGearboxSplitting) te).rotate(Main.reverseHelper[Blocks.piston.determineOrientation(world, x, y, z, entity)], true);
		} else {
			System.out.println("WTF BOOM!!!11!!!!!");
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
		TileEntity t = world.getTileEntity(x, y, z);
		if (t instanceof TileEntityGearboxSplitting) {
			((TileEntityGearboxSplitting) t).cleanup();
		}
		super.breakBlock(world, x, y, z, block, p_149749_6_);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		if (!Main.fancyGearbox) {
			icons[0] = reg.registerIcon(this.getTextureName() + "_" + this.types[0]);
			icons[1] = reg.registerIcon(this.getTextureName() + "_" + this.types[1]);
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return !Main.fancyGearbox;
	}

	@Override
	public boolean smashed(World world, int x, int y, int z, int dir, boolean sneaky) {
		TileEntityGearboxSplitting t = (TileEntityGearboxSplitting) world.getTileEntity(x, y, z);
		if (t.dir == dir || t.dir2 == dir)
			return true;
		t.rotate(dir, sneaky);

		return false;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (Main.fancyGearbox) {
			switch (meta) {
			default:
			case 0:
				return DerpyBlocks.plank.getIcon(0, 4);
			case 1:
				return GameRegistry.findBlock("derpyshiz", "block").getIcon(0, 9);
			}
		}
		return this.icons[meta];
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < types.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	public static final String[] types = { "normal", "advanced" };

	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName() + "_" + types[meta % types.length];
	}

}
