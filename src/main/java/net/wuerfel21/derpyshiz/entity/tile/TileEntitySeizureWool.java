package net.wuerfel21.derpyshiz.entity.tile;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;

public class TileEntitySeizureWool extends TileEntity {

	public int color = 0;

	public static Random rand = new Random();

	public TileEntitySeizureWool() {
		super();
	}

	public String getName() {
		return "ds_seizure_wool";
	}

	@Override
	public void updateEntity() {
		if (!this.worldObj.isRemote) {
			if (this.worldObj.getTotalWorldTime() % 2 == 0 && this.worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0) {
				this.updateRand();
				this.worldObj.func_147453_f(xCoord, yCoord, zCoord, this.getBlockType());
			}
		} else {
			this.updateRand();
		}
	}

	protected void updateRand() {
		this.color = rand.nextInt(16);
	}

}
