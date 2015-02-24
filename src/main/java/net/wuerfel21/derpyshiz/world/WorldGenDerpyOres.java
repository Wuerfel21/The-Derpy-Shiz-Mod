package net.wuerfel21.derpyshiz.world;

import static net.wuerfel21.derpyshiz.Main.oreTries;
import static net.wuerfel21.derpyshiz.Main.oreVeinSize;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.wuerfel21.derpyshiz.Int2;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class WorldGenDerpyOres implements IWorldGenerator {

	public static Int2 getXZ(int chunkX, int chunkZ, Random rand) {
		return new Int2((chunkX * 16) + rand.nextInt(16), (chunkZ * 16) + rand.nextInt(16));
	}

	public static int getY(int min, int max, Random rand) {
		return min + rand.nextInt(max - min);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
		case 0: // Overworld
			genAmber(chunkX, chunkZ, world, random);
			genFakediamond(chunkX, chunkZ, world, random);
			genTitanium(chunkX, chunkZ, world, random);
			genRuby(chunkX, chunkZ, world, random);
			genFluorite(chunkX, chunkZ, world, random);
			genTurquoise(chunkX, chunkZ, world, random);
			genCopper(chunkX, chunkZ, world, random);
			genElectrimite(chunkX, chunkZ, world, random);
			genDarkness(chunkX, chunkZ, world, random);
			genTin(chunkX, chunkZ, world, random);
			genLead(chunkX, chunkZ, world, random);
			genWuerfelium(chunkX, chunkZ, world, random);
			break;
		case -1: // Nether
			genAmethyst(chunkX, chunkZ, world, random);
			break;
		case 1: // End
			genEnderium(chunkX, chunkZ, world, random);
			break;
		}
	}

	public void genAmber(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[0]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 0, oreVeinSize[0], Blocks.stone).generate(world, rand, pos.x, getY(32, 64, rand), pos.z);
		}
	}

	public void genFakediamond(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[1]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 1, oreVeinSize[1], Blocks.stone).generate(world, rand, pos.x, getY(24, 48, rand), pos.z);
		}
	}

	public void genTitanium(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[2]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 2, oreVeinSize[2], Blocks.stone).generate(world, rand, pos.x, getY(16, 32, rand), pos.z);
		}
	}

	public void genRuby(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[3]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 3, oreVeinSize[3], Blocks.stone).generate(world, rand, pos.x, getY(8, 24, rand), pos.z);
		}
	}

	public void genTurquoise(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[4]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 4, oreVeinSize[4], Blocks.stone).generate(world, rand, pos.x, getY(24, 80, rand), pos.z);
		}
	}

	public void genAmethyst(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[5]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 5, oreVeinSize[5], Blocks.netherrack).generate(world, rand, pos.x, getY(16, 112, rand), pos.z);
		}
	}

	public void genFluorite(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[6]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 6 + rand.nextInt(3), oreVeinSize[6], Blocks.stone).generate(world, rand, pos.x, getY(8, 96, rand), pos.z);
		}
	}

	public void genCopper(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[7]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 9, oreVeinSize[7], Blocks.stone).generate(world, rand, pos.x, getY(16, 64, rand), pos.z);
		}
	}

	public void genEnderium(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[8]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 10, oreVeinSize[8], Blocks.end_stone).generate(world, rand, pos.x, getY(16, 64, rand), pos.z);
		}
	}

	public void genElectrimite(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[9]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 11, oreVeinSize[9], Blocks.stone).generate(world, rand, pos.x, getY(16, 64, rand), pos.z);
		}
	}

	public void genDarkness(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < rand.nextInt(oreTries[10]); i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 12, oreVeinSize[10], Blocks.stone).generate(world, rand, pos.x, getY(0, 16, rand), pos.z);
		}
	}

	public void genTin(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[11]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 13, oreVeinSize[11], Blocks.stone).generate(world, rand, pos.x, getY(0, 48, rand), pos.z);
		}
	}

	public void genLead(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[12]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 14, oreVeinSize[12], Blocks.stone).generate(world, rand, pos.x, getY(16, 32, rand), pos.z);
		}
	}

	public void genWuerfelium(int chunkX, int chunkZ, World world, Random rand) {
		for (int i = 0; i < oreTries[13]; i++) {
			Int2 pos = getXZ(chunkX, chunkZ, rand);
			new WorldGenMinable(GameRegistry.findBlock("derpyshiz", "ore"), 15, oreVeinSize[13], Blocks.stone).generate(world, rand, pos.x, getY(64, 128, rand), pos.z);
		}
	}

}
