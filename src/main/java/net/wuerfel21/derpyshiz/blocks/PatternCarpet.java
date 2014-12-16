package net.wuerfel21.derpyshiz.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCarpet;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class PatternCarpet extends BlockCarpet {
	
	public PatternCarpet() {
		super();
		this.setBlockName("pattern_carpet");
		this.setHardness(0.1f);
		this.setStepSound(soundTypeCloth);
		this.setLightOpacity(0);
	}
	
	/**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return GameRegistry.findBlock("derpyshiz", "pattern_wool").getIcon(side, meta);
    }
	
}
