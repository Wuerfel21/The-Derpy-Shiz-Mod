package net.wuerfel21.derpyshiz.rotary;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.wuerfel21.derpyshiz.blocks.BlockAxis;

public class RotaryManager {

	public static void updateRotaryOutput(IRotaryOutput output, AxisChain chain, TileEntity tile, int dir) {
		if (output.getRotaryOutput(dir).getEnergy() < 0) {
			output.setRotaryOutput(dir, new Rotation(0,0));
		}
		chain.updateChain(output, tile.getWorldObj(), output.getRotaryOutput(dir), tile.xCoord, tile.yCoord, tile.zCoord);
	}
	
	public static void updateRotaryOutput(IRotaryInput input, TileEntity tile, int dir) {
		ForgeDirection direction = ForgeDirection.getOrientation(dir);
		Block block = tile.getWorldObj().getBlock(tile.xCoord+direction.offsetX, tile.yCoord+direction.offsetY, tile.zCoord+direction.offsetZ);
		TileEntity te = tile.getWorldObj().getTileEntity(tile.xCoord+direction.offsetX, tile.yCoord+direction.offsetY, tile.zCoord+direction.offsetZ);
		if (input.getRotaryInput(dir).isValid() && !(block instanceof BlockAxis || te instanceof IRotaryOutput)) {
			input.setRotaryInput(dir, new Rotation(0,0));
		}
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
		int value = 0;
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
