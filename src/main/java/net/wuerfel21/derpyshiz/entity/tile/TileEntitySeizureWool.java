package net.wuerfel21.derpyshiz.entity.tile;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;

public class TileEntitySeizureWool extends TileEntity {
	
	public int color = 0;
	
	public static Random rand = new Random();
	
	public TileEntitySeizureWool() {
		super();
	}
	
	public String getName() {return "ds_seizure_wool";}
	
	@Override
	public void updateEntity() {
		this.color = rand.nextInt(16);
	}
	
}
