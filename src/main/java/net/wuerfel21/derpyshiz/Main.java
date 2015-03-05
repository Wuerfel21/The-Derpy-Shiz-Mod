package net.wuerfel21.derpyshiz;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;
import net.wuerfel21.derpyshiz.DerpyRegistry.BasicBlockEntry;

import com.google.common.primitives.UnsignedBytes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION)
public class Main {
	
	public static final Material machineMaterial = new Material(MapColor.woodColor);
	
	public static final int[] orientationHelper = {0,0,2,2,1,1};
	public static final int[] reverseHelper = {1,0,3,2,5,4};
	public static final int[] diodeHelper = {-1,-1,0,2,3,1};
	public static final int[] stairHelper = {-1,-1,2,3,0,1};
	
	public static final int[] armorTypeToSlot = {4,3,2,1};
	
	public static final String MODID = "derpyshiz";
    public static final String MODNAME = "The Derpy Shiz Mod";
    public static final String VERSION = "@VERSION@";
    
    public static Configuration config;
    public static int idMagicBiome;
    public static boolean fancyGearbox;
    public static boolean flashy;
    public static int maxWaterswordDistance;
    public static boolean checkForUpdates;
    public static String updateURL;
    public static int[] oreTries = new int[14];
    public static int[] oreVeinSize = new int[14];
    public static double gearboxBaseDistance;
    public static double seizureWoolDistance;
    
    public static boolean isDevEnv = false;
    
    public static SimpleNetworkWrapper derpnet;
    
    public static CreativeTabs tabRotary = new TabRotary();
    
    @Instance(Main.MODID)
    public static Main instance;
    
    @SidedProxy(clientSide="net.wuerfel21.derpyshiz.ClientProxy",serverSide="net.wuerfel21.derpyshiz.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	try {
    		isDevEnv = ((Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment")).booleanValue();
    	} catch(Exception ex) {
    		//lawl
    	}
    	instance = this;
    	derpnet = NetworkRegistry.INSTANCE.newSimpleChannel(this.MODID);
    	config = new Configuration(e.getSuggestedConfigurationFile(),null);
    	this.config.load();
    	idMagicBiome = UnsignedBytes.saturatedCast(config.getInt("idMagicBiome", "ids", 69, 0, 255, "The id of the Magic Forest"));
    	fancyGearbox = config.getBoolean("fancyGearbox", "client", true, "If gearboxes should be uber fancy");
    	flashy = config.getBoolean("flashy", "client", true, "If flashing textures should be used. false also saves some tiny bits of performance when near such things.");
    	maxWaterswordDistance = config.getInt("maxWaterswordDistance", "server", 64, 0, 255,"Maximum distance one can go up/down with a flood cutlass. Normally you wont have 64 blocks deep oceans, so you wont notice this setting too much. This is used to reduce server load on servers with loads of flood cutlasses/lots of oceans.");
    	checkForUpdates = config.getBoolean("checkForUpdates", "updates", !isDevEnv , "Wheter to check for updates or not");
    	updateURL = config.getString("updateURL", "updates", "https://raw.githubusercontent.com/Wuerfel21/The-Derpy-Shiz-Mod/master/currentVersion.thisIsUsedForUpdateChecking", "where to check for updates. dont change unless you know what a tacco is.");
    	gearboxBaseDistance = config.getFloat("gearboxBaseDistance", "client", 128f, 32f, 4096f, "Base render distance For gearboxes and other rotary output things, in Blocks. Actually used value will be this + the length of the axis");
    	seizureWoolDistance = Math.pow(config.getFloat("seizureWoolDistance", "client", 128f, 32f, 4096f, "Render distance for seizure wool. Increase to be able to see seizure wool from further away."), 2);
    	{
    		oreTries[0] = config.getInt("triesAmber", "oreTries", 6, 0, Integer.MAX_VALUE, "Maximum amount of Amber veins per chunk");
    		oreTries[1] = config.getInt("triesFakediamond", "oreTries", 5, 0, Integer.MAX_VALUE, "Maximum amount of Fakediamond veins per chunk");
    		oreTries[2] = config.getInt("triesTitanium", "oreTries", 5, 0, Integer.MAX_VALUE, "Maximum amount of Titanium veins per chunk");
    		oreTries[3] = config.getInt("triesRuby", "oreTries", 1, 0, Integer.MAX_VALUE, "Maximum amount of Ruby veins per chunk");
    		oreTries[4] = config.getInt("triesTurquoise", "oreTries", 20, 0, Integer.MAX_VALUE, "Maximum amount of Turquoise veins per chunk");
    		oreTries[5] = config.getInt("triesAmethyst", "oreTries", 2, 0, Integer.MAX_VALUE, "Maximum amount of Amethyst veins per chunk");
    		oreTries[6] = config.getInt("triesFluorite", "oreTries", 6, 0, Integer.MAX_VALUE, "Maximum amount of Fluorite veins per chunk (All colors combined)");
    		oreTries[7] = config.getInt("triesCopper", "oreTries", 16, 0, Integer.MAX_VALUE, "Maximum amount of Copper veins per chunk");
    		oreTries[8] = config.getInt("triesEnderium", "oreTries", 19, 0, Integer.MAX_VALUE, "Maximum amount of Enderium veins per chunk");
    		oreTries[9] = config.getInt("triesElectrimite", "oreTries", 9, 0, Integer.MAX_VALUE, "Maximum amount of Electrimite veins per chunk");
    		oreTries[10] = config.getInt("triesDarkness", "oreTries", 5, 0, Integer.MAX_VALUE, "Maximum amount of Dark Ore veins per chunk (Different reduction mechanic!)");
    		oreTries[11] = config.getInt("triesTin", "oreTries", 17, 0, Integer.MAX_VALUE, "Maximum amount of Tin veins per chunk");
    		oreTries[12] = config.getInt("triesLead", "oreTries", 4, 0, Integer.MAX_VALUE, "Maximum amount of Lead veins per chunk");
    		oreTries[13] = config.getInt("triesWuerfelium", "oreTries", 8, 0, Integer.MAX_VALUE, "Maximum amount of Wuerfelium veins per chunk");
    		
    		oreVeinSize[0] = config.getInt("veinSizeAmber", "oreVeinSize", 8, 0, Integer.MAX_VALUE, "Maximum size of Amber veins");
    		oreVeinSize[1] = config.getInt("veinSizeFakediamond", "oreVeinSize", 5, 0, Integer.MAX_VALUE, "Maximum size of Fakediamond veins");
    		oreVeinSize[2] = config.getInt("veinSizeTitanium", "oreVeinSize", 7, 0, Integer.MAX_VALUE, "Maximum size of Titanium veins");
    		oreVeinSize[3] = config.getInt("veinSizeRuby", "oreVeinSize", 7, 0, Integer.MAX_VALUE, "Maximum size of Ruby veins");
    		oreVeinSize[4] = config.getInt("veinSizeTurquoise", "oreVeinSize", 3, 0, Integer.MAX_VALUE, "Maximum size of Turquoise veins");
    		oreVeinSize[5] = config.getInt("veinSizeAmethyst", "oreVeinSize", 12, 0, Integer.MAX_VALUE, "Maximum size of Amethyst veins");
    		oreVeinSize[6] = config.getInt("veinSizeFluorite", "oreVeinSize", 10, 0, Integer.MAX_VALUE, "Maximum size of Fluorite veins (All colors combined)");
    		oreVeinSize[7] = config.getInt("veinSizeCopper", "oreVeinSize", 10, 0, Integer.MAX_VALUE, "Maximum size of Copper veins");
    		oreVeinSize[8] = config.getInt("veinSizeEnderium", "oreVeinSize", 8, 0, Integer.MAX_VALUE, "Maximum size of Enderium veins");
    		oreVeinSize[9] = config.getInt("veinSizeElectrimite", "oreVeinSize", 7, 0, Integer.MAX_VALUE, "Maximum size of Electrimite veins");
    		oreVeinSize[10] = config.getInt("veinSizeDarkness", "oreVeinSize", 5, 0, Integer.MAX_VALUE, "Maximum size of Dark Ore veins");
    		oreVeinSize[11] = config.getInt("veinSizeTin", "oreVeinSize", 10, 0, Integer.MAX_VALUE, "Maximum size of Tin veins");
    		oreVeinSize[12] = config.getInt("veinSizeLead", "oreVeinSize", 15, 0, Integer.MAX_VALUE, "Maximum size of Lead veins");
    		oreVeinSize[13] = config.getInt("veinSizeWuerfelium", "oreVeinSize", 10, 0, Integer.MAX_VALUE, "Maximum size of Wuerfelium veins");
    	}
    	config.save();
    	this.proxy.preInit(e);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent e) {
    	this.proxy.init(e);
    	NBTTagCompound tag = new NBTTagCompound();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	this.proxy.postInit(e);
    }
    
    @EventHandler
    public void IMCCallback(IMCEvent event) {
    	for (IMCMessage msg : event.getMessages()) {
    		switch (msg.key) {
    		case "registerLeafType":
    			DerpyRegistry.leafTypes.add(new BasicBlockEntry(GameRegistry.findBlock(msg.getNBTValue().getString("sourceMod"), msg.getNBTValue().getString("blockID")), msg.getNBTValue().getInteger("meta")));
    			break;
    		}
    	}
    }
    
}
