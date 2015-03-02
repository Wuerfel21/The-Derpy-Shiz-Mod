package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityDetectorBox;

public class BlockDetectorBox extends BlockAbstractGearbox {

	public BlockDetectorBox() {
		super("detectorbox", TileEntityDetectorBox.class);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("derpyshiz:planks_spruce");
		} else {
			this.setBlockTextureName("derpyshiz:detectorbox_ugly");
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
				return Blocks.lapis_block.getIcon(0, 0);
			}
		}
		return this.icons[meta];
	}

	@Override
	public boolean hasComparatorInputOverride() {	
		return true;
	}
	
	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int p_149736_5_) {
		TileEntityDetectorBox t = (TileEntityDetectorBox) world.getTileEntity(x, y, z);
		return t.compOut;
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int p_149748_5_) {
		TileEntityDetectorBox t = (TileEntityDetectorBox) world.getTileEntity(x, y, z);
		return t.reverse?15:0;
	}
	
	public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_)
    {
        return p_149748_5_ == 1 ? this.isProvidingWeakPower(p_149748_1_, p_149748_2_, p_149748_3_, p_149748_4_, p_149748_5_) : 0;
    }
	
	@Override
	public boolean canProvidePower() {
		return true;
	}
	
}
