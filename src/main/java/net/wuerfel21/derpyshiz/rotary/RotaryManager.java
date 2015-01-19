package net.wuerfel21.derpyshiz.rotary;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.wuerfel21.derpyshiz.blocks.BlockAxis;

public abstract class RotaryManager {

	public static void updateRotaryOutput(IRotaryOutput output, AxisChain chain, TileEntity tile, int dir) {
		if (chain.updateChain(output, tile.getWorldObj(), output.getRotaryOutput(dir), tile.xCoord, tile.yCoord, tile.zCoord)) {
			tile.getWorldObj().markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
		}
	}
	
	public static void updateRotaryInput(IRotaryInput input, TileEntity tile, int dir) {
		ForgeDirection direction = ForgeDirection.getOrientation(dir);
		Block block = tile.getWorldObj().getBlock(tile.xCoord+direction.offsetX, tile.yCoord+direction.offsetY, tile.zCoord+direction.offsetZ);
		TileEntity te = tile.getWorldObj().getTileEntity(tile.xCoord+direction.offsetX, tile.yCoord+direction.offsetY, tile.zCoord+direction.offsetZ);
		if (input.getRotaryInput(dir) != 0 && !(block instanceof BlockAxis || te instanceof IRotaryOutput)) {
			input.setRotaryInput(dir, 0, 0);
		}
	}

	public static NBTTagCompound inputToNBT(IRotaryInput input) {
		NBTTagCompound tag = new NBTTagCompound();
		for (int i = 0; i < 6; i++) {
			tag.setInteger("input"+Integer.toString(i), input.getRotaryInput(i));
			tag.setInteger("length"+Integer.toString(i), input.getRotaryInputLength(i));
		}
		return tag;
	}

	public static void inputFromNBT(IRotaryInput input, NBTTagCompound tag) {
		for (int i=0;i<6;i++) {
			if (tag.hasKey("input"+Integer.toString(i))) {
				input.setRotaryInput(i, tag.getInteger("input"+Integer.toString(i)) ,tag.getInteger("length"+Integer.toString(i)));
			}
		}
	}
	
	public static NBTTagCompound outputToNBT(IRotaryOutput output) {
		NBTTagCompound tag = new NBTTagCompound();
		for (int i = 0; i < 6; i++) {
			tag.setInteger("output"+Integer.toString(i), output.getRotaryOutput(i));
		}
		return tag;
	}

	public static void outputFromNBT(IRotaryOutput output, NBTTagCompound tag) {
		for (int i=0;i<6;i++) {
			if (tag.hasKey("output"+Integer.toString(i))) {
				output.setRotaryOutput(i, tag.getInteger("output"+Integer.toString(i)));
			}
		}
	}
	
	public static int getMaxInput(IRotaryInput input) {
		int index = 0;
		int value = 0;
		for (int i = 0; i < 6; i++) {
			int e = input.getRotaryInput(i);
			if (e > value) {
				index = i;
				value = e;
			}
		}
		return index;
	}

	public static int removeRotation(int speed, int op) {
		if(speed>0) {
			speed -= op;
			return speed>0?speed:0;
		} else if(speed<0) {
			speed += op;
			return speed<0?speed:0;
		} else {
			return 0;
		}
	}
	
	public static int calcLoss(int input, int length, int div) {
		int loss = 1+(int)Math.floor((double)length/(double)div);
		return removeRotation(input, loss);
	}
	
}
