package net.wuerfel21.derpyshiz;

import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.world.biome.BiomeGenBase;
import net.wuerfel21.derpyshiz.entity.EntityPiggycorn;

public class MagicBiomeGen extends BiomeGenBase{
	
	public MagicBiomeGen(int id) {
		super(id);
		this.setHeight(new Height(-0.1f,0.2f));
		this.setColor(0xDB14AD);
        this.setTemperatureRainfall(0.5F, 0.9F);
        this.waterColorMultiplier = 0xDB14AD;
        this.theBiomeDecorator.bigMushroomsPerChunk = 1;
        this.theBiomeDecorator.flowersPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 1;
        this.theBiomeDecorator.treesPerChunk = 0;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		this.theBiomeDecorator.mushroomsPerChunk = 10;
		
		this.biomeName = "Magic Forest";
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityPiggycorn.class,20,2,6));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class,10,1,10));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class,5,5,7));
		
		this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class,3,1,2));
		
		//this.theBiomeDecorator
	}
	
	@Override
	public int getBiomeGrassColor(int x, int y, int z)
	{
		return 0x7DF9FF;
	}

	@Override
	public int getBiomeFoliageColor(int x, int y, int z)
	{
		return 0x7DF9FF;
	}

	
}
