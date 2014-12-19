package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntitySeizureWool;

public class SeizureWool extends Block implements ITileEntityProvider {
	
	public SeizureWool() {
		super(Material.cloth);
		this.setBlockName("seizure_wool");
		this.setBlockTextureName("derpyshiz:pattern_wool_pig");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(0.8f);
		this.setStepSound(soundTypeCloth);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySeizureWool();
	}
	
	@Override
    public boolean hasTileEntity() {
        return Main.flashy;
    }
	
	@Override
	public boolean isOpaqueCube() {return false;}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return !Main.flashy;
	}
	
}
