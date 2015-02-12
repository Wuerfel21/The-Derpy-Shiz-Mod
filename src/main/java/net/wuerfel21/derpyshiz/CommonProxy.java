package net.wuerfel21.derpyshiz;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.wuerfel21.derpyshiz.entity.EntityPiggycorn;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCrank;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityDetectorBox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxCombination;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxReversion;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxSplitting;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityHousing;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;
import net.wuerfel21.derpyshiz.entity.tile.TileEntitySeizureWool;
import net.wuerfel21.derpyshiz.entity.tile.TileEntitySpringbox;
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
		//EntityRegistry.registerGlobalEntityID(EntityPiggycorn.class, "piggycorn", Main.idPiggycorn,15771042, 14377823);
		EntityRegistry.registerModEntity(EntityPiggycorn.class, "piggycorn", 0, Main.instance, 80, 3, true);
		EntityList.stringToClassMapping.put("piggycorn", EntityPiggycorn.class);
		//Registering Entity Spawns
		EntityRegistry.addSpawn(EntityPiggycorn.class, 2, 1, 5, EnumCreatureType.creature, BiomeGenBase.birchForest);
		EntityRegistry.addSpawn(EntityPiggycorn.class, 1, 1, 3, EnumCreatureType.creature, BiomeGenBase.birchForestHills);
		EntityRegistry.addSpawn(EntityPiggycorn.class, 1, 1, 3, EnumCreatureType.creature, BiomeGenBase.forest);
		EntityRegistry.addSpawn(EntityPiggycorn.class, 1, 1, 1, EnumCreatureType.creature, BiomeGenBase.forestHills);
		EntityRegistry.addSpawn(EntityPiggycorn.class, 3, 2, 4, EnumCreatureType.creature, BiomeGenBase.roofedForest);
		EntityRegistry.addSpawn(EntityPiggycorn.class, 1, 1, 1, EnumCreatureType.creature, BiomeGenBase.plains);
		GameRegistry.registerTileEntity(TileEntityHousing.class, "ds_housing");
		GameRegistry.registerTileEntity(TileEntityGearbox.class, "ds_gearbox");
		GameRegistry.registerTileEntity(TileEntityGearboxCombination.class, "ds_gearbox_combination");
		GameRegistry.registerTileEntity(TileEntityGearboxReversion.class, "ds_gearbox_reversion");
		GameRegistry.registerTileEntity(TileEntityGearboxSplitting.class, "ds_gearbox_splitting");
		GameRegistry.registerTileEntity(TileEntitySpringbox.class, "ds_springbox");
		GameRegistry.registerTileEntity(TileEntityDetectorBox.class, "ds_detectorBox");
		GameRegistry.registerTileEntity(TileEntityCrank.class, "ds_crank");
		GameRegistry.registerTileEntity(TileEntityMillstone.class, "ds_millstone");
		
		GameRegistry.registerTileEntity(TileEntitySeizureWool.class, "ds_seizure_wool");
		NaturalSword.registerLeafTypes();
    }

    public void postInit(FMLPostInitializationEvent e) {
    	
    }
    
}
