package net.wuerfel21.derpyshiz;

import com.google.common.primitives.UnsignedBytes;

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
    public static final String VERSION = "beta 0.2";
    
    public static Configuration config;
    public static int idPiggycorn;
    public static int idMagicBiome;
    
    @Instance
    public static Main instance = new Main();
    
    @SidedProxy(clientSide="net.wuerfel21.derpyshiz.ClientProxy",serverSide="net.wuerfel21.derpyshiz.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	config = new Configuration(e.getSuggestedConfigurationFile(),null);
    	this.config.load();
    	idPiggycorn = UnsignedBytes.saturatedCast(config.getInt("idPiggycorn", "ids", 3, 0, 255, "The id of the piggycorn"));
    	idMagicBiome = UnsignedBytes.saturatedCast(config.getInt("idMagicBiome", "ids", 69, 0, 255, "The id of the Magic Forest"));
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
