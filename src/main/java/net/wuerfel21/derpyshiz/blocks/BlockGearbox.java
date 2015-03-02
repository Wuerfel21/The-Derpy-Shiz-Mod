package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockGearbox extends BlockAbstractGearbox {

	public BlockGearbox() {
		super("gearbox",TileEntityGearbox.class);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("minecraft:planks_big_oak");
		} else {
			this.setBlockTextureName("derpyshiz:gearbox_ugly");
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (Main.fancyGearbox) {
			switch (meta) {
			default:
			case 0:
				return Blocks.planks.getIcon(0, 5);
			case 1:
				return GameRegistry.findBlock("derpyshiz", "block").getIcon(0, 2);
			}
		}
		return this.icons[meta];
	}

}
