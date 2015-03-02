package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.DerpyBlocks;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxReversion;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockReversionGearbox extends BlockAbstractGearbox {

	public BlockReversionGearbox() {
		super("gearbox_reversion",TileEntityGearboxReversion.class);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("derpyshiz:planks_ebony");
		} else {
			this.setBlockTextureName("derpyshiz:gearbox_reversion_ugly");
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (Main.fancyGearbox) {
			switch (meta) {
			default:
			case 0:
				return DerpyBlocks.plank.getIcon(0, 0);
			case 1:
				return GameRegistry.findBlock("derpyshiz", "block").getIcon(0, 14);
			}
		}
		return this.icons[meta];
	}

}
