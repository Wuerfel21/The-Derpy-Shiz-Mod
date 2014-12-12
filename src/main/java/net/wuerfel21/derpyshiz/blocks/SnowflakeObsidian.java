package net.wuerfel21.derpyshiz.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;

public class SnowflakeObsidian extends Block {
	
	public SnowflakeObsidian() {
		super(Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockTextureName("derpyshiz:snowflake_obsidian");
		this.setBlockName("snowflake_obsidian");
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(50f);
		this.setResistance(2000f);
		this.setStepSound(soundTypePiston);
	}
	
}
