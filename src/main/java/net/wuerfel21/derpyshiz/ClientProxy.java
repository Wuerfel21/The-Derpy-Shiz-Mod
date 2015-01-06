package net.wuerfel21.derpyshiz;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.wuerfel21.derpyshiz.blocks.SeizureWool;
import net.wuerfel21.derpyshiz.client.BlankIcon;
import net.wuerfel21.derpyshiz.client.ModelPiggycorn;
import net.wuerfel21.derpyshiz.client.RenderGearbox;
import net.wuerfel21.derpyshiz.client.RenderHousing;
import net.wuerfel21.derpyshiz.client.RenderPiggycorn;
import net.wuerfel21.derpyshiz.client.RenderSeizureWool;
import net.wuerfel21.derpyshiz.client.RenderTESRItem;
import net.wuerfel21.derpyshiz.entity.EntityPiggycorn;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityHousing;
import net.wuerfel21.derpyshiz.entity.tile.TileEntitySeizureWool;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	public static TileEntityGearbox inventoryGearbox;
	public static TileEntityHousing inventoryHousing;
	public static TileEntitySeizureWool inventorySeizureWool;

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		BlankIcon.register();
	}

	@Override
	public void init(FMLInitializationEvent e) {
		RenderGearbox renderGearbox;
		RenderHousing renderHousing;
		RenderSeizureWool renderSeizureWool;
		super.init(e);
		DerpyKeys.register();
		RenderingRegistry.registerEntityRenderingHandler(EntityPiggycorn.class, new RenderPiggycorn(new ModelPiggycorn(), new ModelPiggycorn(0.5F), 0.7F));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearbox.class, renderGearbox = new RenderGearbox());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHousing.class, renderHousing = new RenderHousing());

		if (Main.fancyGearbox) {
			inventoryGearbox = new TileEntityGearbox();
			inventoryHousing = new TileEntityHousing();
			inventoryGearbox.inInventory = true;
			inventoryHousing.inInventory = true;
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.gearbox), new RenderTESRItem(renderGearbox, inventoryGearbox));
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.housing), new RenderTESRItem(renderHousing, inventoryHousing));
		}
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
