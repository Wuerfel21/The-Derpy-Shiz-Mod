package net.wuerfel21.derpyshiz;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.wuerfel21.derpyshiz.client.BlankIcon;
import net.wuerfel21.derpyshiz.client.ModelPiggycorn;
import net.wuerfel21.derpyshiz.client.RenderCrank;
import net.wuerfel21.derpyshiz.client.RenderDarkSword;
import net.wuerfel21.derpyshiz.client.RenderGearbox;
import net.wuerfel21.derpyshiz.client.RenderGearboxCombination;
import net.wuerfel21.derpyshiz.client.RenderHousing;
import net.wuerfel21.derpyshiz.client.RenderMillstone;
import net.wuerfel21.derpyshiz.client.RenderPiggycorn;
import net.wuerfel21.derpyshiz.client.RenderSeizureWool;
import net.wuerfel21.derpyshiz.client.RenderTESRItem;
import net.wuerfel21.derpyshiz.entity.EntityPiggycorn;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCrank;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxCombination;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityHousing;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;
import net.wuerfel21.derpyshiz.entity.tile.TileEntitySeizureWool;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy {

	public static TileEntityGearbox inventoryGearbox;
	public static TileEntityGearboxCombination inventoryGearboxCombination;
	public static TileEntityHousing inventoryHousing;
	public static TileEntityMillstone inventoryMillstone;
	public static TileEntitySeizureWool inventorySeizureWool;
	public static TileEntityCrank inventoryCrank;

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		BlankIcon.register();
	}

	@Override
	public void init(FMLInitializationEvent e) {
		RenderGearbox renderGearbox;
		RenderGearboxCombination renderGearboxCombination;
		RenderHousing renderHousing;
		RenderMillstone renderMillstone;
		RenderSeizureWool renderSeizureWool;
		RenderCrank renderCrank;
		super.init(e);
		DerpyKeys.register();
		RenderingRegistry.registerEntityRenderingHandler(EntityPiggycorn.class, new RenderPiggycorn(new ModelPiggycorn(), new ModelPiggycorn(0.5F), 0.7F));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearbox.class, renderGearbox = new RenderGearbox());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearboxCombination.class, renderGearboxCombination = new RenderGearboxCombination());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHousing.class, renderHousing = new RenderHousing());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMillstone.class, renderMillstone = new RenderMillstone());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrank.class, renderCrank = new RenderCrank());

		if (Main.fancyGearbox) {
			inventoryGearbox = new TileEntityGearbox();
			inventoryGearboxCombination = new TileEntityGearboxCombination();
			inventoryHousing = new TileEntityHousing();
			inventoryMillstone = new TileEntityMillstone();
			inventoryGearbox.inInventory = true;
			inventoryGearboxCombination.inInventory = true;
			inventoryHousing.inInventory = true;
			inventoryMillstone.inInventory = true;
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.gearbox), new RenderTESRItem(renderGearbox, inventoryGearbox));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.gearboxCombination), new RenderTESRItem(renderGearboxCombination, inventoryGearboxCombination));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.housing), new RenderTESRItem(renderHousing, inventoryHousing));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.millstone), new RenderTESRItem(renderMillstone, inventoryMillstone));
		}
		inventoryCrank = new TileEntityCrank();
		inventoryCrank.inInventory = true;
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.crank), new RenderTESRItem(renderCrank, inventoryCrank));
		MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem("derpyshiz", "sword_darkness"), new RenderDarkSword());
		
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
