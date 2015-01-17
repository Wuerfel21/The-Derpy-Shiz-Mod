package net.wuerfel21.derpyshiz.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.IMetaItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DerpyOres extends Block implements IMetaItemBlock {
	
	public IIcon[] icons = new IIcon[16];
	
	public DerpyOres() {
		super(Material.rock);
		this.setBlockName("derpyshiz.ore");
		this.setBlockTextureName("derpyshiz:ore");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypePiston);
		this.setResistance(5.0f);
		this.setHarvestLevel("pickaxe",2);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
	    for (int i = 0; i < 16; i ++) {
	        this.icons[i] = reg.registerIcon(this.textureName + "_" + names[i]);
	    }
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
	    return this.icons[meta];
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 16; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	@Override
	public float getBlockHardness(World world,int x,int y,int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta >= 16) return 1.5f;
		else return hardness[meta];
	}
	
	@Override
	public int getHarvestLevel(int meta) {
		if (meta >= 16) return 1;
		else return level[meta];
	}
	
	public int calcFortune(int meta, int level, int normal,Random rand) {
		if (level > 0) {
			int j = rand.nextInt(level + 2) - 1;
			if (j < 0) {
				j = 0;
			}
			return this.quantityDropped(rand) * (j + 1);
		} else {
			return normal;
		}
    }
	
	@Override
	public int quantityDropped(int meta, int fortune, Random rand) {
		if (meta >= 16)
			return 64;
		if (dropself[meta])
			return 1;
		switch (meta) {
		case amberIndex:
			return this.calcFortune(meta, fortune, rand.nextInt(2) + 1, rand);
		case amethystIndex:
			return calcFortune(meta, fortune,
					rand.nextInt(20 + (fortune * 2)) == 0 ? 0 : 1, rand);
		case darknessIndex:
			return 1;
		default:
			return this.calcFortune(meta, fortune, 1, rand);
		}
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		if (meta >= 16) return Item.getItemFromBlock(Blocks.deadbush);
		if(dropself[meta])  {
			return Item.getItemFromBlock(this);
		} else {
			return GameRegistry.findItem("derpyshiz", "ore_item");
		}
	}
	
	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName() + "_" +names[meta%16];
	}
	
	public static final String[] names = {"amber","fakediamond","titanium","ruby","turquoise","amethyst","fluorite_brown","fluorite_red","fluorite_pink","copper","enderium","electrimite","darkness","tin","lead","wuerfelium"};
	public static final int amberIndex = 0;
	public static final int amethystIndex = 5;
	public static final int darknessIndex = 12;
	public static final float[] hardness = {2f,1f,4f,3f,3f,10f,2f,2f,2f,2f,15f,2f,20f,1.5f,2.5f,8f};
	public static final int[] level = {1,0,2,2,1,3,1,1,1,1,4,2,3,1,2,2};
	public static final boolean[] dropself = {false,false,true,false,false,false,false,false,false,true,true,true,false,true,true,false};
	
}
