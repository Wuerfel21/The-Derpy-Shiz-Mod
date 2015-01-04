package net.wuerfel21.derpyshiz.entity.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.wuerfel21.derpyshiz.rotary.AxisChain;
import net.wuerfel21.derpyshiz.rotary.IRotaryInput;
import net.wuerfel21.derpyshiz.rotary.IRotaryOutput;
import net.wuerfel21.derpyshiz.rotary.ITieredTE;
import net.wuerfel21.derpyshiz.rotary.RotaryManager;
import net.wuerfel21.derpyshiz.rotary.Rotation;

public class TileEntityGearbox extends TileEntity implements IRotaryInput, IRotaryOutput, ITieredTE {

	public int dir = 0;
	public Rotation output;
	public Rotation[] input = new Rotation[6];
	public int tier = 0;

	public AxisChain chain;

	public boolean inInventory;

	public TileEntityGearbox() {
		super();
		this.output = new Rotation(0, 0);
		for (int i = 0; i < input.length; i++) {
			this.input[i] = new Rotation(0, 0);
		}
		this.rotate(dir);
	}

	@Override
	public void updateEntity() {
		if (this.getWorldObj() != null) {
			this.tier = this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
			if (!this.getWorldObj().isRemote) {
				if (this.dir != this.chain.dir) {
					this.rotate(this.dir);
				}
				for (int i=0;i<6;i++) {
					if (this.isInputFace(i)) {
						RotaryManager.updateRotaryInput(this, this, i);
					}
				}
				Rotation r = (Rotation) this.input[RotaryManager.getMaxInput(this)].clone();
				r.speed -= speedLoss[this.tier];
				if (r.isValid()) {
					this.setRotaryOutput(this.dir, r);
				} else {
					this.setRotaryOutput(this.dir, new Rotation(0,0));
				}
				RotaryManager.updateRotaryOutput(this, chain, this, dir);
			}
		}
	}

	public void rotate(int newDir) {
		this.dir = newDir;
		if (this.chain != null) {
			this.chain.cleanup(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord);
		}
		this.chain = new AxisChain(this.dir, maxChainLength[this.tier]);

	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.dir = tag.getInteger("direction");
		RotaryManager.inputFromNBT(this, tag);
		RotaryManager.outputFromNBT(this, tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("direction", dir);
		tag.setTag("input", RotaryManager.inputToNBT(this));
		tag.setTag("output", RotaryManager.outputToNBT(this));
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
		return "ds_gearbox";
	}

	@Override
	public boolean isOutputFace(int side) {
		return side == dir;
	}

	@Override
	public boolean isInputFace(int side) {
		return side != dir;
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

	@Override
	public void setRotaryInput(int side, Rotation rotation) {
		if (this.isInputFace(side)) {
			input[side] = (Rotation) rotation.clone();
		}
	}

	@Override
	public Rotation getRotaryInput(int side) {
		if (this.isInputFace(side)) {
			return (Rotation) this.input[side].clone();
		} else {
			return new Rotation(0, 0);
		}
	}

	public void cleanup() {
		if (this.chain != null) {
			this.chain.cleanup(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord);
		}
	}

	public static final int[] maxChainLength = { 16, 32 };
	public static final int[] speedLoss = { 5, 2 };

	@Override
	public int getTier() {
		return this.tier;
	}

	@Override
	public void setTier(int tier) {
		this.tier = tier;
	}

}
