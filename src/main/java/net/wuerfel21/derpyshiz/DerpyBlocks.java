package net.wuerfel21.derpyshiz;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.wuerfel21.derpyshiz.blocks.BlockAxis;
import net.wuerfel21.derpyshiz.blocks.BlockCombinationGearbox;
import net.wuerfel21.derpyshiz.blocks.BlockCrank;
import net.wuerfel21.derpyshiz.blocks.BlockGearbox;
import net.wuerfel21.derpyshiz.blocks.BlockLasagne;
import net.wuerfel21.derpyshiz.blocks.BlockMillstone;
import net.wuerfel21.derpyshiz.blocks.BlockReversionGearbox;
import net.wuerfel21.derpyshiz.blocks.BlockSplittingGearbox;
import net.wuerfel21.derpyshiz.blocks.CoarseStone;
import net.wuerfel21.derpyshiz.blocks.DecoBlocks;
import net.wuerfel21.derpyshiz.blocks.DerpyLeaves;
import net.wuerfel21.derpyshiz.blocks.DerpyLogs;
import net.wuerfel21.derpyshiz.blocks.DerpyOres;
import net.wuerfel21.derpyshiz.blocks.DerpyPlanks;
import net.wuerfel21.derpyshiz.blocks.DerpySaplings;
import net.wuerfel21.derpyshiz.blocks.DerpySlabs;
import net.wuerfel21.derpyshiz.blocks.DerpyStairs;
import net.wuerfel21.derpyshiz.blocks.DerpyTorch;
import net.wuerfel21.derpyshiz.blocks.FakeTNT;
import net.wuerfel21.derpyshiz.blocks.KewlBush;
import net.wuerfel21.derpyshiz.blocks.LightBlocks;
import net.wuerfel21.derpyshiz.blocks.PatternCarpet;
import net.wuerfel21.derpyshiz.blocks.PatternWool;
import net.wuerfel21.derpyshiz.blocks.RotaryHousing;
import net.wuerfel21.derpyshiz.blocks.SeizureWool;
import net.wuerfel21.derpyshiz.blocks.SnowflakeObsidian;
import net.wuerfel21.derpyshiz.blocks.itemblock.ItemDerpySlab;
import net.wuerfel21.derpyshiz.blocks.itemblock.ItemLeaves;
import cpw.mods.fml.common.registry.GameRegistry;

public class DerpyBlocks {

	public static Block oreBlocks;
	public static Block patternWool;
	public static Block log;
	public static Block plank;
	public static Block coarseStone;
	public static Block leaves;
	public static Block[] stairs = new Block[2];
	public static Block slab;
	public static Block slabDouble;
	public static Block axis;
	public static Block housing;
	public static Block gearbox;
	public static Block gearboxCombination;
	public static Block gearboxReversion;
	public static Block gearboxSplitting;
	public static Block seizureWool;
	public static Block millstone;
	public static Block crank;

	public static final void init() {
		GameRegistry.registerBlock(patternWool = new PatternWool(), SimpleMetaItemBlock.class, "pattern_wool");
		GameRegistry.registerBlock(new PatternCarpet(), SimpleMetaItemBlock.class, "pattern_carpet");
		Blocks.fire.setFireInfo(GameRegistry.findBlock("derpyshiz", "pattern_wool"), 30, 60);
		Blocks.fire.setFireInfo(GameRegistry.findBlock("derpyshiz", "pattern_carpet"), 60, 20);
		GameRegistry.registerBlock(new DerpyOres(), SimpleMetaItemBlock.class, "ore");
		GameRegistry.registerBlock(oreBlocks = new DecoBlocks(), SimpleMetaItemBlock.class, "block");
		GameRegistry.registerBlock(new LightBlocks(), SimpleMetaItemBlock.class, "lamp");
		GameRegistry.registerBlock(new DerpyTorch("reddust", "portal", ColoredLightHelper.makeRGBLightValue(14, 0, 0, 14)).setHardness(0.0F).setStepSound(Block.soundTypeWood).setBlockName("derpyshiz.torch_fluorite_red").setBlockTextureName("derpyshiz:torch_fluorite_red"), "torch_fluorite_red");
		GameRegistry.registerBlock(new DerpyTorch("spell", "slime", ColoredLightHelper.makeRGBLightValue(0, 14, 0, 14)).setHardness(0.0F).setStepSound(Block.soundTypeWood).setBlockName("derpyshiz.torch_fluorite_green").setBlockTextureName("derpyshiz:torch_fluorite_green"), "torch_fluorite_green");
		GameRegistry.registerBlock(new DerpyTorch("magicCrit", "splash", ColoredLightHelper.makeRGBLightValue(0, 0, 14, 14)).setHardness(0.0F).setStepSound(Block.soundTypeWood).setBlockName("derpyshiz.torch_fluorite_blue").setBlockTextureName("derpyshiz:torch_fluorite_blue"), "torch_fluorite_blue");
		GameRegistry.registerBlock(log = new DerpyLogs().setBlockName("derpyshiz.log").setBlockTextureName("derpyshiz:log"), SimpleMetaItemBlock.class, "log");
		Blocks.fire.setFireInfo(log, 5, 5);
		GameRegistry.registerBlock(plank = new DerpyPlanks().setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("derpyshiz.planks").setBlockTextureName("derpyshiz:planks"), SimpleMetaItemBlock.class, "plank");
		Blocks.fire.setFireInfo(plank, 5, 20);
		GameRegistry.registerBlock(stairs[0] = new DerpyStairs(plank, 0).setBlockName("derpyshiz.stairs_ebony"), "stairs_ebony");
		GameRegistry.registerBlock(stairs[1] = new DerpyStairs(plank, 1).setBlockName("derpyshiz.stairs_magic"), "stairs_magic");
		Blocks.fire.setFireInfo(stairs[0], 5, 20);
		Blocks.fire.setFireInfo(stairs[1], 5, 20);
		slab = new DerpySlabs(false, Material.wood);
		slabDouble = new DerpySlabs(true, Material.wood);
		GameRegistry.registerBlock(slab, ItemDerpySlab.class, "slab", slab, slabDouble, false);
		GameRegistry.registerBlock(slabDouble, ItemDerpySlab.class, "slab_double", slab, slabDouble, true);
		Blocks.fire.setFireInfo(slab, 5, 20);
		Blocks.fire.setFireInfo(slabDouble, 5, 20);
		GameRegistry.registerBlock(coarseStone = new CoarseStone(), SimpleMetaItemBlock.class, "coarse_stone");
		GameRegistry.registerBlock(new SnowflakeObsidian(), "snowflake_obsidian");
		GameRegistry.registerBlock(leaves = new DerpyLeaves().setBlockName("leaves"), ItemLeaves.class, "leaves");
		Blocks.fire.setFireInfo(GameRegistry.findBlock("derpyshiz", "leaves"), 30, 60);
		GameRegistry.registerBlock(new KewlBush(), "kewl_bush");
		GameRegistry.registerBlock(new DerpySaplings(), SimpleMetaItemBlock.class, "sapling");
		GameRegistry.registerBlock(new BlockLasagne(), "block_lasagne");
		GameRegistry.registerBlock(new FakeTNT(), "fake_tnt");

		GameRegistry.registerBlock(axis = new BlockAxis(), "axis");
		GameRegistry.registerBlock(housing = new RotaryHousing(), SimpleMetaItemBlock.class, "housing");
		GameRegistry.registerBlock(gearbox = new BlockGearbox(), SimpleMetaItemBlock.class, "gearbox");
		GameRegistry.registerBlock(gearboxCombination = new BlockCombinationGearbox(), SimpleMetaItemBlock.class, "gearbox_combination");
		GameRegistry.registerBlock(gearboxReversion = new BlockReversionGearbox(), SimpleMetaItemBlock.class, "gearbox_reversion");
		GameRegistry.registerBlock(gearboxSplitting = new BlockSplittingGearbox(), SimpleMetaItemBlock.class, "gearbox_splitting");
		GameRegistry.registerBlock(crank = new BlockCrank(), SimpleMetaItemBlock.class, "crank");
		GameRegistry.registerBlock(millstone = new BlockMillstone(), SimpleMetaItemBlock.class, "millstone");

		GameRegistry.registerBlock(seizureWool = new SeizureWool(), "seizure_wool");
	}

}
