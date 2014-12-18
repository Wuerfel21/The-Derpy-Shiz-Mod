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
	
	public static final int[] orientationHelper = {0,0,2,2,1,1};
	public static final int[] reverseHelper = {1,0,3,2,5,4};
	public static final int[] diodeHelper = {-1,-1,0,2,3,1};
	public static final int[] stairHelper = {-1,-1,2,3,0,1};
	
	public static final String MODID = "derpyshiz";
    public static final String MODNAME = "The derpy shiz mod";
    public static final String VERSION = "beta 0.2.1";
    
    public static Configuration config;
    public static int idPiggycorn;
    public static int idMagicBiome;
    public static boolean fancyGearbox;
    
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
    	fancyGearbox = config.getBoolean("fancyGearbox", "client", true, "If gearboxes should be uber fancy");
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
