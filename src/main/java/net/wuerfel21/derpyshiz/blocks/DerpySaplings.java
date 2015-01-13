package net.wuerfel21.derpyshiz.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.wuerfel21.derpyshiz.IMetaItemBlock;
import net.wuerfel21.derpyshiz.world.WorldGenEbonyTree;
import net.wuerfel21.derpyshiz.world.WorldGenMagicTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DerpySaplings extends BlockBush implements IGrowable, IMetaItemBlock{

	public static final String[] types = { "ebony", "magic" };
	public static final IIcon[] icons = new IIcon[2];

	public DerpySaplings() {
		super();
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F,0.5F + f);
		this.setStepSound(soundTypeGrass);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockName("sapling");
		this.setBlockTextureName("derpyshiz:sapling");
	}

	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
		if (!p_149674_1_.isRemote) {
			super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
			if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9 && p_149674_5_.nextInt(7) == 0) {
				this.func_149879_c(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
			}
		}
	}

	public void func_149879_c(World p_149879_1_, int p_149879_2_, int p_149879_3_, int p_149879_4_, Random p_149879_5_) {
		int l = p_149879_1_.getBlockMetadata(p_149879_2_, p_149879_3_, p_149879_4_);

		if ((l & 8) == 0) {
			p_149879_1_.setBlockMetadataWithNotify(p_149879_2_, p_149879_3_, p_149879_4_, l | 8, 4);
		} else {
			this.func_149878_d(p_149879_1_, p_149879_2_, p_149879_3_, p_149879_4_, p_149879_5_);
		}
	}

	public void func_149878_d(World world, int x, int y, int z, Random rand) {
		if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(world, rand, x, y, z)) {
			return;
		}
		int meta = world.getBlockMetadata(x, y, z) & 7;
		WorldGenerator gen;
		if ((meta & 1) == 0) {
			gen = new WorldGenEbonyTree(true);
		} else {
			gen = new WorldGenMagicTree(true);
		}
		
		int i1 = 0;
		int j1 = 0;
		boolean flag = false;

		Block block = Blocks.air;

		if (flag) {
			world.setBlock(x + i1, y, z + j1, block, 0, 4);
			world.setBlock(x + i1 + 1, y, z + j1, block, 0, 4);
			world.setBlock(x + i1, y, z + j1 + 1, block, 0, 4);
			world.setBlock(x + i1 + 1, y, z + j1 + 1, block, 0, 4);
		} else {
			world.setBlock(x, y, z, block, 0, 4);
		}

		if (!((WorldGenerator) gen).generate(world, rand, x + i1, y, z + j1)) {
			if (flag) {
				world.setBlock(x + i1, y, z + j1, this, meta, 4);
				world.setBlock(x + i1 + 1, y, z + j1, this, meta, 4);
				world.setBlock(x + i1, y, z + j1 + 1, this, meta, 4);
				world.setBlock(x + i1 + 1, y, z + j1 + 1, this, meta, 4);
			} else {
				world.setBlock(x, y, z, this, meta, 4);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[meta & 1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon(this.getTextureName() + "_" + types[0]);
		icons[1] = reg.registerIcon(this.getTextureName() + "_" + types[1]);
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}

	@Override
	public int damageDropped(int meta) {
		return meta & 1;
	}

	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
		return true;
	}

	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		return (double) p_149852_1_.rand.nextFloat() < 0.45D;
	}

	public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
		this.func_149879_c(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
	}
	
	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName() + "_" +types[meta%types.length];
	}

}
