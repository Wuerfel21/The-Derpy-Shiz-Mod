package net.wuerfel21.derpyshiz;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.wuerfel21.derpyshiz.blocks.BlockAxis;
import net.wuerfel21.derpyshiz.blocks.BlockGearbox;
import net.wuerfel21.derpyshiz.blocks.CoarseStone;
import net.wuerfel21.derpyshiz.blocks.DecoBlocks;
import net.wuerfel21.derpyshiz.blocks.DerpyLeaves;
import net.wuerfel21.derpyshiz.blocks.DerpyLogs;
import net.wuerfel21.derpyshiz.blocks.DerpyOres;
import net.wuerfel21.derpyshiz.blocks.DerpyPlanks;
import net.wuerfel21.derpyshiz.blocks.DerpySaplings;
import net.wuerfel21.derpyshiz.blocks.DerpyTorch;
import net.wuerfel21.derpyshiz.blocks.KewlBush;
import net.wuerfel21.derpyshiz.blocks.LightBlocks;
import net.wuerfel21.derpyshiz.blocks.PatternCarpet;
import net.wuerfel21.derpyshiz.blocks.PatternWool;
import net.wuerfel21.derpyshiz.blocks.SeizureWool;
import net.wuerfel21.derpyshiz.blocks.SnowflakeObsidian;
import net.wuerfel21.derpyshiz.blocks.itemblock.ItemCoarseStone;
import net.wuerfel21.derpyshiz.blocks.itemblock.ItemDecoBlock;
import net.wuerfel21.derpyshiz.blocks.itemblock.ItemLamp;
import net.wuerfel21.derpyshiz.blocks.itemblock.ItemLeaves;
import net.wuerfel21.derpyshiz.blocks.itemblock.ItemLogs;
import net.wuerfel21.derpyshiz.blocks.itemblock.ItemOre;
import net.wuerfel21.derpyshiz.blocks.itemblock.ItemPatternWool;
import net.wuerfel21.derpyshiz.blocks.itemblock.ItemSapling;
import cpw.mods.fml.common.registry.GameRegistry;

public class DerpyBlocks {
	
	public static Block gearbox;
	public static Block seizureWool;
	
	public static final void init() {
		GameRegistry.registerBlock(new PatternWool(), ItemPatternWool.class, "pattern_wool");
		GameRegistry.registerBlock(new PatternCarpet(), ItemPatternWool.class, "pattern_carpet");
		Blocks.fire.setFireInfo(GameRegistry.findBlock("derpyshiz", "pattern_wool"), 30, 60);
		Blocks.fire.setFireInfo(GameRegistry.findBlock("derpyshiz", "pattern_carpet"), 60, 20);
		GameRegistry.registerBlock(new DerpyOres(), ItemOre.class, "ore");
		GameRegistry.registerBlock(new DecoBlocks(), ItemDecoBlock.class, "block");
		GameRegistry.registerBlock(new LightBlocks(), ItemLamp.class, "lamp");
		GameRegistry.registerBlock(new DerpyTorch("reddust","portal").setHardness(0.0F).setLightLevel(0.9375F).setStepSound(Block.soundTypeWood).setBlockName("torch_fluorite_red").setBlockTextureName("derpyshiz:torch_fluorite_red"), "torch_fluorite_red");
		GameRegistry.registerBlock(new DerpyTorch("spell","slime").setHardness(0.0F).setLightLevel(0.9375F).setStepSound(Block.soundTypeWood).setBlockName("torch_fluorite_green").setBlockTextureName("derpyshiz:torch_fluorite_green"), "torch_fluorite_green");
		GameRegistry.registerBlock(new DerpyTorch("magicCrit","splash").setHardness(0.0F).setLightLevel(0.9375F).setStepSound(Block.soundTypeWood).setBlockName("torch_fluorite_blue").setBlockTextureName("derpyshiz:torch_fluorite_blue"), "torch_fluorite_blue");
		GameRegistry.registerBlock(new DerpyLogs().setBlockName("log").setBlockTextureName("derpyshiz:log"), ItemLogs.class, "log");
		Blocks.fire.setFireInfo(GameRegistry.findBlock("derpyshiz", "log"), 5, 5);
		GameRegistry.registerBlock(new DerpyPlanks().setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("planks").setBlockTextureName("derpyshiz:planks"), ItemLogs.class, "plank");
		Blocks.fire.setFireInfo(GameRegistry.findBlock("derpyshiz", "plank"), 5, 20);
		GameRegistry.registerBlock(new CoarseStone(), ItemCoarseStone.class, "coarse_stone");
		GameRegistry.registerBlock(new SnowflakeObsidian(), "snowflake_obsidian");
		GameRegistry.registerBlock(new DerpyLeaves().setBlockName("leaves"), ItemLeaves.class, "leaves");
		Blocks.fire.setFireInfo(GameRegistry.findBlock("derpyshiz", "leaves"), 30, 60);
		GameRegistry.registerBlock(new KewlBush(), "kewl_bush");
		GameRegistry.registerBlock(new DerpySaplings(), ItemSapling.class, "sapling");
		
		GameRegistry.registerBlock(new BlockAxis(), "axis");
		GameRegistry.registerBlock(gearbox = new BlockGearbox(), "gearbox");
		GameRegistry.registerBlock(seizureWool = new SeizureWool(), "seizure_wool");
	}
	
}
