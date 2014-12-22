package net.wuerfel21.derpyshiz;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.wuerfel21.derpyshiz.client.BlankIcon;
import net.wuerfel21.derpyshiz.client.ModelPiggycorn;
import net.wuerfel21.derpyshiz.client.RenderGearbox;
import net.wuerfel21.derpyshiz.client.RenderSeizureWool;
import net.wuerfel21.derpyshiz.client.RenderGearboxItem;
import net.wuerfel21.derpyshiz.client.RenderPiggycorn;
import net.wuerfel21.derpyshiz.entity.EntityPiggycorn;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntitySeizureWool;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        BlankIcon.register();
    }

    @Override
    public void init(FMLInitializationEvent e) {
    	RenderGearbox renderGearbox;
        super.init(e);
        RenderingRegistry.registerEntityRenderingHandler(EntityPiggycorn.class, new RenderPiggycorn(new ModelPiggycorn(), new ModelPiggycorn(0.5F), 0.7F));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearbox.class, renderGearbox = new RenderGearbox());
        if (Main.fancyGearbox) {
        	TileEntityGearbox inventoryGearbox = new TileEntityGearbox();
        	inventoryGearbox.inInventory = true;
        	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DerpyBlocks.gearbox), new RenderGearboxItem(renderGearbox, inventoryGearbox));
        }
        if (Main.flashy) {
        	ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySeizureWool.class, new RenderSeizureWool());
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
    
}
