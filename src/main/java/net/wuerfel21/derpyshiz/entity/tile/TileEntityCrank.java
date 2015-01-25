package net.wuerfel21.derpyshiz.entity.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.wuerfel21.derpyshiz.rotary.AxisChain;
import net.wuerfel21.derpyshiz.rotary.IRotaryOutput;
import net.wuerfel21.derpyshiz.rotary.ITieredTE;
import net.wuerfel21.derpyshiz.rotary.RotaryManager;

public class TileEntityCrank extends TileEntity implements IRotaryOutput, ITieredTE {
	
	public boolean ready = false;
	public int dir = 0;
	public int output;
	public int tier = 0;
	
	public int cooldown;
	
	public int sync_offset;

	public AxisChain chain;

	public boolean inInventory;
	
	public TileEntityCrank() {
		super();
		this.chain = new AxisChain(0, 5000000);
		this.output = 0;
	}

	@Override
	public void updateEntity() {
		if (this.getWorldObj() != null) {
			this.setTier(this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
			if (!this.getWorldObj().isRemote) {
				if (this.dir != this.chain.dir) {
					this.rotate(this.dir);
				}
				if (cooldown > 0) {
					cooldown--;
					this.setRotaryOutput(dir, 30);
				} else {
					this.setRotaryOutput(dir, 0);
				}
				RotaryManager.updateRotaryOutput(this, chain, this, dir);
				if (this.worldObj.getTotalWorldTime() %200 == this.sync_offset) {
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					this.chain.updateChainBlocksToClients(this.worldObj, xCoord, yCoord, zCoord);
				}
			} else {
				chain.updateVisualPosition();
			}
		}
	}

	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.dir = tag.getInteger("direction");
		NBTTagCompound rotary = tag.getCompoundTag("rotary");
		RotaryManager.outputFromNBT(this, rotary);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("direction", dir);
		NBTTagCompound rotary = new NBTTagCompound();
		rotary.setTag("output", RotaryManager.outputToNBT(this));
		tag.setTag("rotary", rotary);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkg) {
		NBTTagCompound tag = pkg.func_148857_g();
		this.dir = tag.getInteger("direction");
		this.chain.fromNetworkNBT(tag.getCompoundTag("axis"));
	}

	@Override
	public Packet getDescriptionPacket() {
		if (!ready) {
			this.setTier(this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
			this.rotate(dir);
			ready = true;
			this.sync_offset = this.worldObj.rand.nextInt(200);
			this.markDirty();
		}
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("direction", dir);
		tag.setTag("axis", chain.toNetworkNBT());
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
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
		this.chain = new AxisChain(this.dir, TileEntityGearbox.maxChainLength[this.getTier()]);

	}
	
	public void cleanup() {
		if (this.chain != null) {
			this.chain.cleanup(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord);
		}
	}

	@Override
	public int getTier() {
		return this.tier;
	}

	@Override
	public void setTier(int tier) {
		this.tier = tier;
	}
	
	public static final int[] speeds = {30,40};
	public static final int[] cooldowns = {40,30};
	
}
