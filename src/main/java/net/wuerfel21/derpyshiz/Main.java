package net.wuerfel21.derpyshiz;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION)
public class Main {
	
	public static final String MODID = "derpyshiz";
    public static final String MODNAME = "The derpy shiz mod";
    public static final String VERSION = "alpha 0.1";
    
    public static Configuration config;
    public static int idPiggycorn = 3;
    public static int idMagicBiome = 69;
    
    @Instance
    public static Main instance = new Main();
    
    @SidedProxy(clientSide="net.wuerfel21.derpyshiz.ClientProxy",serverSide="net.wuerfel21.derpyshiz.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	config = new Configuration(e.getSuggestedConfigurationFile(),null);
    	this.config.load();
    	idPiggycorn = config.getInt("idPiggycorn", "ids", 3, 0, 255, "The id of the piggycorn");
    	idMagicBiome = config.getInt("idMagicBiome", "ids", 69, 0, 255, "The id of the Magic Forest");
    	config.save();
    	this.proxy.preInit(e);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent e) {
    	this.proxy.init(e);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	this.proxy.postInit(e);
    }
    
}
