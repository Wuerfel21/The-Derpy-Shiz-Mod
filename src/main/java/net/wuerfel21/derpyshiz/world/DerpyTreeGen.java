package net.wuerfel21.derpyshiz.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import cpw.mods.fml.common.IWorldGenerator;

public class DerpyTreeGen implements IWorldGenerator {
	
	public WorldGenAbstractTree tree;
	public BiomeGenBase biome;
	public int tries;
	public int chance;
	
	public DerpyTreeGen(WorldGenAbstractTree tree, BiomeGenBase biome, int tries, int chance) {
		super();
		this.tree = tree;
		this.biome = biome;
		this.tries = tries;
		this.chance = chance;
	}
	
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i=0;i<tries;i++) {
			int x = chunkX*16+rand.nextInt(16);
			int z = chunkZ*16+rand.nextInt(16);
			int y = world.getHeightValue(x, z);
			if (world.getBiomeGenForCoords(x, z) == this.biome && rand.nextInt(this.chance) == 0) {
				tree.generate(world, rand, x, y, z);
			}
		}
	}

}
