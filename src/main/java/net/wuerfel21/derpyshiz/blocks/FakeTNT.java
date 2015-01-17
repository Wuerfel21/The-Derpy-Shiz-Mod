package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class FakeTNT extends Block {
	
	public IIcon[] icons = new IIcon[3];
	
	public FakeTNT() {
		super(Material.tnt);
		this.setHardness(0.0f);
		this.setBlockName("derpyshiz.fake_tnt");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		switch (side) {
		case 0:
			return this.icons[2];
		case 1:
			return this.icons[1];
		default:
			return this.icons[0];
		}
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		this.icons[0] = reg.registerIcon("minecraft:tnt_side");
		this.icons[1] = reg.registerIcon("minecraft:tnt_top");
		this.icons[2] = reg.registerIcon("minecraft:tnt_bottom");
	}
	
}
