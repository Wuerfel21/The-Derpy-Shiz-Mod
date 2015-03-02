package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxCombination;

public class BlockCombinationGearbox extends BlockAbstractGearbox {

	public BlockCombinationGearbox() {
		super("gearbox_combination",TileEntityGearboxCombination.class);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("minecraft:planks_spruce");
		} else {
			this.setBlockTextureName("derpyshiz:gearbox_combination_ugly");
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (Main.fancyGearbox) {
			switch (meta) {
			default:
			case 0:
				return Blocks.planks.getIcon(0, 1);
			case 1:
				return Blocks.gold_block.getIcon(0, 0);
			}
		}
		return this.icons[meta];
	}

}
