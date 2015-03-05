package net.wuerfel21.derpyshiz.entity.tile;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.wuerfel21.derpyshiz.Main;

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

	public void updateRand() {
		this.color = rand.nextInt(16);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setByte("color",(byte) color);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.color = tag.getByte("color");
	}
	
	@Override
	public double getMaxRenderDistanceSquared() {
		return Main.seizureWoolDistance;
	}

}
