package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxSplitting;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockSplittingGearbox extends BlockAbstractGearbox {

	public BlockSplittingGearbox() {
		super("gearbox_splitting",TileEntityGearboxSplitting.class);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("minecraft:planks_acacia");
		} else {
			this.setBlockTextureName("derpyshiz:gearbox_splitting_ugly");
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityGearboxSplitting) {
			((TileEntityGearboxSplitting) te).rotate(Blocks.piston.determineOrientation(world, x, y, z, entity), false);
			((TileEntityGearboxSplitting) te).rotate(Main.reverseHelper[Blocks.piston.determineOrientation(world, x, y, z, entity)], true);
		} else {
			System.out.println("WTF BOOM!!!11!!!!!");
		}
	}

	@Override
	public boolean smashed(World world, int x, int y, int z, int dir, boolean sneaky) {
		TileEntityGearboxSplitting t = (TileEntityGearboxSplitting) world.getTileEntity(x, y, z);
		if (t.dir == dir || t.dir2 == dir)
			return true;
		t.rotate(dir, sneaky);

		return false;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (Main.fancyGearbox) {
			switch (meta) {
			default:
			case 0:
				return Blocks.planks.getIcon(0, 4);
			case 1:
				return GameRegistry.findBlock("derpyshiz", "block").getIcon(0, 9);
			}
		}
		return this.icons[meta];
	}

}
