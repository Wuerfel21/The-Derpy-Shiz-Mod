package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockLasagne extends Block {
	
	IIcon[] icons = new IIcon[2];
	
	public BlockLasagne() {
		super(Material.cloth);
		this.setStepSound(soundTypeCloth);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1f);
		this.setBlockName("block_lasagne");
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
	    this.icons[0] = reg.registerIcon("derpyshiz:lasagne_0");
	    this.icons[1] = reg.registerIcon("derpyshiz:lasagne_1");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return side > 1 ? this.icons[1] : this.icons[0];
	}
	
}
