package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;

public class BlockGearbox extends Block implements ITileEntityProvider {
	
	public BlockGearbox() {
		super(Material.wood);
		this.setBlockName("gearbox");
		this.setBlockTextureName("derpyshiz:null");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGearbox();
	}
	
	@Override
    public boolean hasTileEntity(int meta) {
        return true;
    }
	
	@Override
	public boolean isOpaqueCube() {return false;}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return true;
	}

}
