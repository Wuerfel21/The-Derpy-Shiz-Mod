package net.wuerfel21.derpyshiz;

import java.nio.channels.NetworkChannel;

import com.google.common.primitives.UnsignedBytes;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.network.NetworkManager;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION)
public class Main {
	
	public static final Material machineMaterial = new Material(MapColor.woodColor);
	
	public static final int[] orientationHelper = {0,0,2,2,1,1};
	public static final int[] reverseHelper = {1,0,3,2,5,4};
	public static final int[] diodeHelper = {-1,-1,0,2,3,1};
	public static final int[] stairHelper = {-1,-1,2,3,0,1};
	
	public static final String MODID = "derpyshiz";
    public static final String MODNAME = "The derpy shiz mod";
    public static final String VERSION = "beta 0.3 pre";
    
    public static Configuration config;
    public static int idPiggycorn;
    public static int idMagicBiome;
    public static boolean fancyGearbox;
    public static boolean flashy;
    public static boolean checkForUpdates;
    public static String updateURL;
    
    public static SimpleNetworkWrapper derpnet;
    
    @Instance(Main.MODID)
    public static Main instance;
    
    @SidedProxy(clientSide="net.wuerfel21.derpyshiz.ClientProxy",serverSide="net.wuerfel21.derpyshiz.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	instance = this;
    	derpnet = NetworkRegistry.INSTANCE.newSimpleChannel(this.MODID);
    	config = new Configuration(e.getSuggestedConfigurationFile(),null);
    	this.config.load();
    	idPiggycorn = UnsignedBytes.saturatedCast(config.getInt("idPiggycorn", "ids", 3, 0, 255, "The id of the piggycorn"));
    	idMagicBiome = UnsignedBytes.saturatedCast(config.getInt("idMagicBiome", "ids", 69, 0, 255, "The id of the Magic Forest"));
    	fancyGearbox = config.getBoolean("fancyGearbox", "client", true, "If gearboxes should be uber fancy");
    	flashy = config.getBoolean("flashy", "client", true, "If flashing textures should be used. false also saves some tiny bits of performance when near such things.");
    	checkForUpdates = config.getBoolean("checkForUpdates", "updates", true, "Wheter to check for updates or not");
    	updateURL = config.getString("updateURL", "updates", "https://raw.githubusercontent.com/Wuerfel21/The-Derpy-Shiz-Mod/master/currentVersion.thisIsUsedForUpdateChecking", "where to check for updates. dont change unless you know what a tacco is.");
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
