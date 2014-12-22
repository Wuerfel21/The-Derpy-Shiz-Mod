package net.wuerfel21.derpyshiz.rotary;

import net.minecraft.nbt.NBTTagCompound;

public class Rotation {

	public int speed = 0;
	public int torque = 0;

	public Rotation(int s, int t) {
		this.speed = s;
		this.torque = t;
	}
	
	public Rotation(NBTTagCompound tag) {
		this.speed = tag.getInteger("speed");
		this.torque = tag.getInteger("torque");
	}

	public NBTTagCompound getNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("speed", this.speed);
		tag.setInteger("torque", this.torque);
		return tag;
	}

	@Override
	public Object clone() {
		return new Rotation(this.speed, this.torque);
	}

	public int getEnergy() {
		return speed + torque;
	}
	
	public boolean isValid() {
		return this.speed > 0 && this.torque > 0;
	}
	
}
