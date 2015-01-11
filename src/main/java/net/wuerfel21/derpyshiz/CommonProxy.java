package net.wuerfel21.derpyshiz;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.wuerfel21.derpyshiz.entity.EntityPiggycorn;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCrank;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityHousing;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;
import net.wuerfel21.derpyshiz.entity.tile.TileEntitySeizureWool;
import net.wuerfel21.derpyshiz.gui.GuiHandler;
import net.wuerfel21.derpyshiz.items.NaturalSword;
import net.wuerfel21.derpyshiz.network.ItemModeMessage;
import net.wuerfel21.derpyshiz.network.SpecialActionMessage;
import net.wuerfel21.derpyshiz.world.DerpyWorld;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
		DerpyItems.init();
		DerpyBlocks.init();
    }

    public void init(FMLInitializationEvent e) {
    	Main.derpnet.registerMessage(ItemModeMessage.Handler.class, ItemModeMessage.class, 0, Side.SERVER);
    	Main.derpnet.registerMessage(SpecialActionMessage.Handler.class, SpecialActionMessage.class, 1, Side.SERVER);
    	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
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
		GameRegistry.registerTileEntity(TileEntityHousing.class, "ds_housing");
		GameRegistry.registerTileEntity(TileEntityGearbox.class, "ds_gearbox");
		GameRegistry.registerTileEntity(TileEntityCrank.class, "ds_crank");
		GameRegistry.registerTileEntity(TileEntityMillstone.class, "ds_millstone");
		
		GameRegistry.registerTileEntity(TileEntitySeizureWool.class, "ds_seizure_wool");
		NaturalSword.registerLeafTypes();
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
