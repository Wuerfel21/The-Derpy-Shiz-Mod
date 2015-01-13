package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.IMetaItemBlock;

public class CoarseStone extends Block implements IMetaItemBlock{

	public IIcon[] icons = new IIcon[this.names.length];

	public CoarseStone() {
		super(Material.rock);
		this.setBlockName("coarsestone");
		this.setBlockTextureName("derpyshiz:coarsestone");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypePiston);
		this.setHarvestLevel("pickaxe", 0);
		this.setResistance(10f);
		this.setHardness(1.5f);
	}
	/*
	//fail
	@Override
	public void onFallenUpon(World world,int x,int y,int z,Entity entity,float dist) {
		if (dist > 5f) {
			System.out.println("fallen from"+dist);
			System.out.println(entity.attackEntityFrom(DamageSource.fall, dist-3f));
		}
	}
	*/
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < names.length; i++) {
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
		for (int i = 0; i < names.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	public static final String[] names = { "normal", "framed" };

	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName() + "_" +names[meta%names.length];
	}

}
