package net.wuerfel21.derpyshiz.world;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.wuerfel21.derpyshiz.MagicBiomeGen;
import net.wuerfel21.derpyshiz.Main;
import cpw.mods.fml.common.registry.GameRegistry;

public class DerpyWorld {
	
	public static void register() {
		new DerpyWorld().main();
	}
	
	public void main() {
		//Ores
		GameRegistry.registerWorldGenerator(new WorldGenDerpyOres(), 42);
		
		//Biome
		BiomeGenBase magicBiome = new MagicBiomeGen(Main.idMagicBiome);
		BiomeDictionary.registerBiomeType(magicBiome, BiomeDictionary.Type.MAGICAL);
		BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(magicBiome, 10));
		BiomeManager.addSpawnBiome(magicBiome);
		
		//Trees
		GameRegistry.registerWorldGenerator(new DerpyTreeGen(new WorldGenMagicTree(false),magicBiome,9,1),42);
		GameRegistry.registerWorldGenerator(new DerpyTreeGen(new WorldGenEbonyTree(false),magicBiome,10,1),42);
		
		BiomeGenBase[] forests = BiomeDictionary.getBiomesForType(Type.FOREST);
		for (int i=0;i<forests.length;i++) {
			BiomeGenBase b = forests[i];
			GameRegistry.registerWorldGenerator(new DerpyTreeGen(new WorldGenEbonyTree(false),b,2,3),1);
		}
	}
	
}
