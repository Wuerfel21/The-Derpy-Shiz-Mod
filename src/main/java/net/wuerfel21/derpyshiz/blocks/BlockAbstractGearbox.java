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
import net.wuerfel21.derpyshiz.IMetaItemBlock;
import net.wuerfel21.derpyshiz.ISmashable;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.AbstractGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
import cpw.mods.fml.common.FMLLog;

public abstract class BlockAbstractGearbox extends Block implements ISmashable, ITileEntityProvider, IMetaItemBlock {

	public IIcon[] icons = new IIcon[2];
	
	public String type;
	public Class gearboxClass;

	public BlockAbstractGearbox(String type, Class gearboxClass) {
		super(Main.machineMaterial);
		this.type = type;
		this.gearboxClass = gearboxClass;
		this.setHardness(3f);
		this.setBlockName("derpyshiz."+type);
		this.setCreativeTab(Main.tabRotary);
		this.setHarvestLevel("ds_hammer", 0);
		this.setStepSound(soundTypeWood);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		try {
			return (TileEntity) gearboxClass.newInstance();
		} catch (Exception e) {
			FMLLog.bigWarning("[Wuerfel_21] OH NOES! Gearbox instantiation failed horribly: "+e.toString());
			return new TileEntityGearbox();
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
		if (te instanceof AbstractGearbox) {
			((AbstractGearbox) te).rotate(Blocks.piston.determineOrientation(world, x, y, z, entity));
		} else {
			System.out.println("WTF BOOM!!!11!!!!!");
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
		TileEntity t = world.getTileEntity(x, y, z);
		if (t instanceof AbstractGearbox) {
			((AbstractGearbox) t).cleanup();
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
		AbstractGearbox t = (AbstractGearbox) world.getTileEntity(x, y, z);
		if (t.dir == dir)
			return true;
		t.rotate(dir);
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

	public static final String[] types = { "normal", "advanced" };

	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName() + "_" + types[meta % types.length];
	}

}
