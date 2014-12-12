package net.wuerfel21.derpyshiz;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.wuerfel21.derpyshiz.entity.EntityPiggycorn;
import net.wuerfel21.derpyshiz.world.DerpyWorld;
import net.wuerfel21.derpyshiz.world.WorldGenDerpyOres;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
	public static int modEntityID = 0;
	
	public void preInit(FMLPreInitializationEvent e) {
		DerpyItems.init();
		DerpyBlocks.init();
    }

    public void init(FMLInitializationEvent e) {
    	DerpyCrafting.registerOredict();
		DerpyCrafting.registerCrafting();
		DerpyEvents.register();
		DerpyWorld.register();
		EntityRegistry.registerGlobalEntityID(EntityPiggycorn.class, "piggycorn", 3,15771042, 14377823);
    }

    public void postInit(FMLPostInitializationEvent e) {
    	
    }
    
}
