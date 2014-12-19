package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class PatternWool extends Block {
	
	public IIcon[] icons = new IIcon[16];
	
	public PatternWool() {
		super(Material.cloth);
		this.setBlockName("pattern_wool");
		this.setBlockTextureName("derpyshiz:pattern_wool");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(0.8f);
		this.setStepSound(soundTypeCloth);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
	    for (int i = 0; i < 16; i ++) {
	        this.icons[i] = reg.registerIcon(this.textureName + "_" + patterns[i]);
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
	
	
	
	public static final String[] patterns = {"colordots","lines","checker","dotted","striped","squares","bright","heart","rainbow","swiss","germany","bavaria","mojang","notch","jeb","pig"};
}
