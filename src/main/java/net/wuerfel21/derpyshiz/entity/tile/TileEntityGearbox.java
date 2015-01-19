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

public class TileEntityGearbox extends TileEntity implements IRotaryInput, IRotaryOutput, ITieredTE {

	public boolean ready = false;
	public int dir = 0;
	public int output;
	public int[] input = new int[6];
	public int[] length = new int[6];
	public int tier = 0;

	public AxisChain chain;

	public boolean inInventory;

	public TileEntityGearbox() {
		super();
		this.output = 0;
		for (int i = 0; i < input.length; i++) {
			this.input[i] = 0;
		}
	}
	
	@Override
	public void updateEntity() {
		if (this.getWorldObj() != null) {
			this.setTier(this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
			if (!ready) {
				this.rotate(dir);
				ready = true;
			}
			if (!this.getWorldObj().isRemote) {
				if (this.dir != this.chain.dir) {
					this.rotate(this.dir);
				}
				for (int i = 0; i < 6; i++) {
					if (this.isInputFace(i)) {
						RotaryManager.updateRotaryInput(this, this, i);
					}
				}
				int r = this.input[RotaryManager.getMaxInput(this)];
				int l = this.length[RotaryManager.getMaxInput(this)];
				this.setRotaryOutput(this.dir, RotaryManager.calcLoss(r, l, this.getTier()==0?4:6));
				RotaryManager.updateRotaryOutput(this, chain, this, dir);
			} else {
				chain.updateVisualPosition();
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
		NBTTagCompound rotary = tag.getCompoundTag("rotary");
		RotaryManager.inputFromNBT(this, rotary);
		RotaryManager.outputFromNBT(this, rotary);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("direction", dir);
		NBTTagCompound rotary = new NBTTagCompound();
		rotary.setTag("input", RotaryManager.inputToNBT(this));
		rotary.setTag("output", RotaryManager.outputToNBT(this));
		tag.setTag("rotary", rotary);
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

	@Override
	public boolean isOutputFace(int side) {
		return side == dir;
	}

	@Override
	public boolean isInputFace(int side) {
		return side != dir;
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

	@Override
	public void setRotaryInput(int side, int speed, int length) {
		if (this.isInputFace(side)) {
			this.input[side] = speed;
			this.length[side] = length;
		}
	}

	@Override
	public int getRotaryInput(int side) {
		if (this.isInputFace(side)) {
			return this.input[side];
		} else {
			return 0;
		}
	}

	public void cleanup() {
		if (this.chain != null) {
			this.chain.cleanup(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord);
		}
	}

	public static final int[] maxChainLength = { 16, 32 };

	@Override
	public int getTier() {
		return this.tier;
	}

	@Override
	public void setTier(int tier) {
		this.tier = tier;
	}

	@Override
	public int getRotaryInputLength(int side) {
		if (this.isInputFace(side)) {
			return this.length[side];
		} else {
			return 0;
		}
	}

}
