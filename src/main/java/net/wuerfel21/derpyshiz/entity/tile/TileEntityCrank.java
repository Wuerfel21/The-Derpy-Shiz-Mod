package net.wuerfel21.derpyshiz.entity.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.rotary.AxisChain;
import net.wuerfel21.derpyshiz.rotary.IRotaryOutput;
import net.wuerfel21.derpyshiz.rotary.RotaryManager;
import net.wuerfel21.derpyshiz.rotary.Rotation;

public class TileEntityCrank extends TileEntity implements IRotaryOutput {

	public int dir = 0;
	public Rotation output;
	public int meta = 0;
	public int cooldown = 40;

	public AxisChain chain;

	public boolean inInventory;
	
	public TileEntityCrank() {
		this.output = new Rotation(0, 0);
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
				this.setRotaryOutput(dir, new Rotation(69,69));
				RotaryManager.updateRotaryOutput(this, chain, this, dir);
			}
		}
	}

	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		int newDir = tag.getInteger("direction");
		if (this.dir != newDir) {
			this.rotate(newDir);
		}
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
		this.output.speed = tag.getInteger("speed");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("direction", dir);
		tag.setInteger("speed", this.output.speed);
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
	public Rotation getRotaryOutput(int side) {
		if (this.isOutputFace(side)) {
			return (Rotation) this.output.clone();
		} else {
			return new Rotation(0, 0);
		}
	}

	@Override
	public void setRotaryOutput(int side, Rotation rotation) {
		if (this.isOutputFace(side)) {
			output = (Rotation) rotation.clone();
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
