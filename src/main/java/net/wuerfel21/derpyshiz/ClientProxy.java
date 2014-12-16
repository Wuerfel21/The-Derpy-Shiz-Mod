package net.wuerfel21.derpyshiz;

import net.wuerfel21.derpyshiz.client.BlankIcon;
import net.wuerfel21.derpyshiz.client.ModelPiggycorn;
import net.wuerfel21.derpyshiz.client.RenderGearbox;
import net.wuerfel21.derpyshiz.client.RenderPiggycorn;
import net.wuerfel21.derpyshiz.entity.EntityPiggycorn;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
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
        RenderingRegistry.registerEntityRenderingHandler(EntityPiggycorn.class, new RenderPiggycorn(new ModelPiggycorn(), new ModelPiggycorn(0.5F), 0.7F));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearbox.class, new RenderGearbox());
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
    
}
