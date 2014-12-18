package net.wuerfel21.derpyshiz.entity.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.wuerfel21.derpyshiz.rotary.IRotaryInput;
import net.wuerfel21.derpyshiz.rotary.IRotaryOutput;

public class TileEntityGearbox extends TileEntity implements IRotaryInput, IRotaryOutput {
	
	public int dir = 0;
	
	public boolean inInventory;
	
	public TileEntityGearbox() {
		super();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.dir = tag.getInteger("direction");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("direction", dir);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkg) {
		NBTTagCompound tag = pkg.func_148857_g();
		this.dir = tag.getInteger("direction");
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("direction", dir);
		return new S35PacketUpdateTileEntity(this.xCoord,this.yCoord,this.zCoord,1,tag);
	}
	
	public String getName() {return "ds_gearbox";}

	@Override
	public boolean isOutputFace(int side) {
		return side == dir;
	}

	@Override
	public boolean isInputFace(int side) {
		return side != dir;
	}
	
}
