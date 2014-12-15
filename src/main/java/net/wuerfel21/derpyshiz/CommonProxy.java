package net.wuerfel21.derpyshiz;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.world.biome.BiomeGenBase;
import net.wuerfel21.derpyshiz.entity.EntityPiggycorn;
import net.wuerfel21.derpyshiz.world.DerpyWorld;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
		DerpyItems.init();
		DerpyBlocks.init();
    }

    public void init(FMLInitializationEvent e) {
    	DerpyCrafting.registerOredict();
		DerpyCrafting.registerCrafting();
		DerpyEvents.register();
		DerpyWorld.register();
		EntityRegistry.registerGlobalEntityID(EntityPiggycorn.class, "piggycorn", Main.idPiggycorn,15771042, 14377823);
		//Registering Entity Spawns
		addSpawn(BiomeGenBase.birchForest,EntityPiggycorn.class,2,1,5);
		addSpawn(BiomeGenBase.birchForestHills,EntityPiggycorn.class,1,1,3);
		addSpawn(BiomeGenBase.forest,EntityPiggycorn.class,1,1,3);
		addSpawn(BiomeGenBase.forestHills,EntityPiggycorn.class,1,1,1);
		addSpawn(BiomeGenBase.roofedForest,EntityPiggycorn.class,3,2,4);
		addSpawn(BiomeGenBase.plains,EntityPiggycorn.class,1,1,1);
    }

    public void postInit(FMLPostInitializationEvent e) {
    	
    }
    
	public void addSpawn(BiomeGenBase biome, Class entity, int weight, int min, int max) {
		Field f = null;
		try {
			f = ReflectionHelper.findField(BiomeGenBase.class, "spawnableCreatureList");
			f.setAccessible(true);
			List l = (List) f.get(biome);
			l.add(new BiomeGenBase.SpawnListEntry(entity, weight, min, max));
		} catch (Exception e) {
			System.out.println("Cant reflect derpyshiz spawn! "+biome.toString()+entity.toString()+e.toString());
			return;
		}
	}
    
}
