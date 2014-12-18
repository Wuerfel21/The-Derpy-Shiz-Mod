package net.wuerfel21.derpyshiz.rotary;

import net.minecraft.nbt.NBTTagCompound;

public class RotaryManager {

	public static void updateRotaryOutput(IRotaryOutput output) {

	}

	public static NBTTagCompound inputToNBT(IRotaryInput input) {
		NBTTagCompound tag = new NBTTagCompound();
		for (int i = 0; i < 6; i++) {
			tag.setTag(Integer.toString(i), input.getRotaryInput(i).getNBT());
		}
		return tag;
	}

	public static void inputFromNBT(IRotaryInput input, NBTTagCompound tag) {
		for (int i=0;i<6;i++) {
			if (tag.hasKey(Integer.toString(i))) {
				input.setRotaryInput(i, new Rotation(tag.getCompoundTag(Integer.toString(i))));
			}
		}
	}
	
	public static NBTTagCompound outputToNBT(IRotaryOutput output) {
		NBTTagCompound tag = new NBTTagCompound();
		for (int i = 0; i < 6; i++) {
			tag.setTag(Integer.toString(i), output.getRotaryOutput(i).getNBT());
		}
		return tag;
	}

	public static void outputFromNBT(IRotaryOutput output, NBTTagCompound tag) {
		for (int i=0;i<6;i++) {
			if (tag.hasKey(Integer.toString(i))) {
				output.setRotaryOutput(i, new Rotation(tag.getCompoundTag(Integer.toString(i))));
			}
		}
	}
	
	public static int getMaxInput(IRotaryInput input) {
		int index = 0;
		int value = Integer.MAX_VALUE;
		for (int i = 0; i < 6; i++) {
			int e = input.getRotaryInput(i).getEnergy();
			if (e > value) {
				index = i;
				value = e;
			}
		}
		return index;
	}

}
