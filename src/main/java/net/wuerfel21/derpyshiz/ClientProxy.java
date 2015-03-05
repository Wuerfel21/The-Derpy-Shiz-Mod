package net.wuerfel21.derpyshiz;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.wuerfel21.derpyshiz.client.ModelPiggycorn;
import net.wuerfel21.derpyshiz.client.RenderBigItem;
import net.wuerfel21.derpyshiz.client.RenderCentrifuge;
import net.wuerfel21.derpyshiz.client.RenderCompactEngine;
import net.wuerfel21.derpyshiz.client.RenderCrank;
import net.wuerfel21.derpyshiz.client.RenderDarkSword;
import net.wuerfel21.derpyshiz.client.RenderDetectorBox;
import net.wuerfel21.derpyshiz.client.RenderGearbox;
import net.wuerfel21.derpyshiz.client.RenderGearboxCombination;
import net.wuerfel21.derpyshiz.client.RenderGearboxDecoupling;
import net.wuerfel21.derpyshiz.client.RenderGearboxReversion;
import net.wuerfel21.derpyshiz.client.RenderGearboxSplitting;
import net.wuerfel21.derpyshiz.client.RenderHousing;
import net.wuerfel21.derpyshiz.client.RenderMillstone;
import net.wuerfel21.derpyshiz.client.RenderPiggycorn;
import net.wuerfel21.derpyshiz.client.RenderSeizureWool;
import net.wuerfel21.derpyshiz.client.RenderSpringbox;
import net.wuerfel21.derpyshiz.client.RenderTESRItem;
import net.wuerfel21.derpyshiz.entity.EntityPiggycorn;
import net.wuerfel21.derpyshiz.entity.tile.AbstractGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCentrifuge;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCompactEngine;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCrank;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityDetectorBox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxCombination;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxDecoupling;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxReversion;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxSplitting;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityHousing;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;
import net.wuerfel21.derpyshiz.entity.tile.TileEntitySeizureWool;
import net.wuerfel21.derpyshiz.entity.tile.TileEntitySpringbox;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy {

	public static TileEntitySeizureWool inventorySeizureWool;
	
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		RenderGearbox renderGearbox;
		RenderGearboxCombination renderGearboxCombination;
		RenderGearboxReversion renderGearboxReversion;
		RenderGearboxSplitting renderGearboxSplitting;
		RenderGearboxDecoupling renderGearboxDecoupling;
		RenderSpringbox renderSpringbox;
		RenderDetectorBox renderDetectorBox;
		RenderHousing renderHousing;
		RenderMillstone renderMillstone;
		RenderSeizureWool renderSeizureWool;
		RenderCrank renderCrank;
		RenderCompactEngine renderCompactEngine;
		RenderCentrifuge renderCentrifuge;
		super.init(e);
		DerpyKeys.register();
		RenderingRegistry.registerEntityRenderingHandler(EntityPiggycorn.class, new RenderPiggycorn(new ModelPiggycorn(), new ModelPiggycorn(0.5F), 0.7F));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearbox.class, renderGearbox = new RenderGearbox());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearboxCombination.class, renderGearboxCombination = new RenderGearboxCombination());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearboxReversion.class, renderGearboxReversion = new RenderGearboxReversion());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearboxSplitting.class, renderGearboxSplitting = new RenderGearboxSplitting());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearboxDecoupling.class, renderGearboxDecoupling = new RenderGearboxDecoupling());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpringbox.class, renderSpringbox = new RenderSpringbox());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDetectorBox.class, renderDetectorBox = new RenderDetectorBox());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHousing.class, renderHousing = new RenderHousing());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMillstone.class, renderMillstone = new RenderMillstone());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrank.class, renderCrank = new RenderCrank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCompactEngine.class, renderCompactEngine = new RenderCompactEngine());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCentrifuge.class, renderCentrifuge = new RenderCentrifuge());

		if (Main.fancyGearbox) {
			AbstractGearbox inventoryGearbox = new TileEntityGearbox();
			AbstractGearbox inventoryGearboxCombination = new TileEntityGearboxCombination();
			AbstractGearbox inventoryGearboxReversion = new TileEntityGearboxReversion();
			AbstractGearbox inventoryGearboxSplitting = new TileEntityGearboxSplitting();
			AbstractGearbox inventoryGearboxDecoupling = new TileEntityGearboxDecoupling();
			AbstractGearbox inventorySpringbox = new TileEntitySpringbox();
			AbstractGearbox inventoryDetectorBox = new TileEntityDetectorBox();
			TileEntityHousing inventoryHousing = new TileEntityHousing();
			TileEntityMillstone inventoryMillstone = new TileEntityMillstone();
			TileEntityCompactEngine inventoryCompactEngine = new TileEntityCompactEngine();
			TileEntityCentrifuge inventoryCentrifuge = new TileEntityCentrifuge();
			inventoryGearbox.inInventory = true;
			inventoryGearboxCombination.inInventory = true;
			inventoryGearboxReversion.inInventory = true;
			inventoryGearboxSplitting.inInventory = true;
			inventoryGearboxDecoupling.inInventory = true;
			inventorySpringbox.inInventory = true;
			inventoryDetectorBox.inInventory = true;
			inventoryHousing.inInventory = true;
			inventoryMillstone.inInventory = true;
			inventoryCompactEngine.inInventory = true;
			inventoryCompactEngine.dir = 5;
			inventoryCentrifuge.inInventory = true;
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.gearbox), new RenderTESRItem(renderGearbox, inventoryGearbox));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.gearboxCombination), new RenderTESRItem(renderGearboxCombination, inventoryGearboxCombination));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.gearboxReversion), new RenderTESRItem(renderGearboxReversion, inventoryGearboxReversion));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.gearboxSplitting), new RenderTESRItem(renderGearboxSplitting, inventoryGearboxSplitting));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.gearboxDecoupling), new RenderTESRItem(renderGearboxDecoupling, inventoryGearboxDecoupling));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.springbox), new RenderTESRItem(renderSpringbox, inventorySpringbox));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.detectorBox), new RenderTESRItem(renderDetectorBox, inventoryDetectorBox));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.housing), new RenderTESRItem(renderHousing, inventoryHousing));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.millstone), new RenderTESRItem(renderMillstone, inventoryMillstone));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.compactEngine), new RenderTESRItem(renderCompactEngine, inventoryCompactEngine));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.centrifuge), new RenderTESRItem(renderCentrifuge, inventoryCentrifuge));
		}
		TileEntityCrank inventoryCrank = new TileEntityCrank();
		inventoryCrank.inInventory = true;
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.crank), new RenderTESRItem(renderCrank, inventoryCrank));
		MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem("derpyshiz", "sword_darkness"), new RenderDarkSword());
		MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem("derpyshiz", "pickaxe_long"), new RenderBigItem());
		MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem("derpyshiz", "sword_long"), new RenderBigItem());
		MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem("derpyshiz", "hoe_long"), new RenderBigItem());
		MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem("derpyshiz", "shovel_long"), new RenderBigItem());
		MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem("derpyshiz", "axe_long"), new RenderBigItem());
		MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem("derpyshiz", "hammer_admin"), new RenderBigItem());
		
		if (Main.flashy) {
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySeizureWool.class, renderSeizureWool = new RenderSeizureWool());
			inventorySeizureWool = new TileEntitySeizureWool();
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.seizureWool), new RenderTESRItem(renderSeizureWool, inventorySeizureWool));
		}

	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

}
