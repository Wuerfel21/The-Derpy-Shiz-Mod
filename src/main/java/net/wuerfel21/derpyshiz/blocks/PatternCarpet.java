package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.BlockCarpet;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.IMetaItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PatternCarpet extends BlockCarpet implements IMetaItemBlock {
	
	public PatternCarpet() {
		super();
		this.setBlockName("derpyshiz.pattern_carpet");
		this.setHardness(0.1f);
		this.setStepSound(soundTypeCloth);
		this.setLightOpacity(0);
	}
	
	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName() + "_" +PatternWool.patterns[meta%16];
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
