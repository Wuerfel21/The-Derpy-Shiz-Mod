package net.wuerfel21.derpyshiz.entity.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.wuerfel21.derpyshiz.rotary.AxisChain;
import net.wuerfel21.derpyshiz.rotary.IRotaryOutput;
import net.wuerfel21.derpyshiz.rotary.RotaryManager;

public class TileEntityCrank extends TileEntity implements IRotaryOutput {

	public int dir = 0;
	public int output;
	public int meta = 0;
	public int cooldown = 40;

	public AxisChain chain;

	public boolean inInventory;
	
	public TileEntityCrank() {
		this.output = 0;
		this.rotate(dir);
	}

	@Override
	public void updateEntity() {
		if (this.getWorldObj() != null) {
			this.meta = this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
			if (!this.getWorldObj().isRemote) {
				if (this.dir != this.chain.dir) {
					this.rotate(this.dir);
				}
				this.setRotaryOutput(dir, 30);
				RotaryManager.updateRotaryOutput(this, chain, this, dir);
			}
		}
	}

	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.dir = tag.getInteger("direction");
		RotaryManager.outputFromNBT(this, tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("direction", dir);
		tag.setTag("output",RotaryManager.outputToNBT(this));
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkg) {
		NBTTagCompound tag = pkg.func_148857_g();
		this.dir = tag.getInteger("direction");
		this.output = tag.getInteger("speed");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("direction", dir);
		tag.setInteger("speed", this.output);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	public String getName() {
		return "ds_crank";
	}

	@Override
	public boolean isOutputFace(int side) {
		return side == dir;
	}

	@Override
	public int getRotaryOutput(int side) {
		if (this.isOutputFace(side)) {
			return this.output;
		} else {
			return 0;
		}
	}

	@Override
	public void setRotaryOutput(int side, int speed) {
		if (this.isOutputFace(side)) {
			output = speed;
		}
	}
	
	public void rotate(int newDir) {
		this.dir = newDir;
		if (this.chain != null) {
			this.chain.cleanup(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord);
		}
		this.chain = new AxisChain(this.dir, TileEntityGearbox.maxChainLength[this.meta]);

	}
	
	public void cleanup() {
		if (this.chain != null) {
			this.chain.cleanup(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord);
		}
	}
	
}
