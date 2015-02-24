package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxDecoupling;

public class BlockDecouplingGearbox extends BlockAbstractGearbox {

	public BlockDecouplingGearbox() {
		super("gearbox_decoupling",TileEntityGearboxDecoupling.class);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("minecraft:block_coal");
		} else {
			this.setBlockTextureName("derpyshiz:gearbox_decoupling_ugly");
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (Main.fancyGearbox) {
			switch (meta) {
			default:
			case 0:
				return Blocks.coal_block.getIcon(0, 0);
			case 1:
				return Blocks.obsidian.getIcon(0, 0);
			}
		}
		return this.icons[meta];
	}

}
