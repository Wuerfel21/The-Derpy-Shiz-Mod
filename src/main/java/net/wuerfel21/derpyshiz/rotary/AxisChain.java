package net.wuerfel21.derpyshiz.rotary;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.blocks.BlockAxis;

public class AxisChain {

	public int dir;

	public int maxLength = 20;
	
	public int[] tilePos = new int[3];
	
	public boolean connected = false;

	public int length = 0;
	public int speed = 0;

	// client stuff
	public double position;
	
	public int lastLength = 0;
	public int lastSpeed = 0;

	public AxisChain(int direction, int maxL) {
		this.dir = direction;
		this.maxLength = maxL;
	}
	
	public void updateVisualPosition() {
		position += ((double) speed*0.3d);
		position %= 360;
	}
	
	public boolean updateChain(IRotaryOutput output, World world, int rotation, int x, int y, int z) {
		lastLength = length;
		lastSpeed = speed;
		speed = rotation;
		ForgeDirection direction = ForgeDirection.getOrientation(this.dir);
		boolean usedFlag = false;
		int l = 0;// length counter
		while (true) {
			x += direction.offsetX;
			y += direction.offsetY;
			z += direction.offsetZ;
			Block block = world.getBlock(x, y, z);
			if (block instanceof BlockAxis) {
				if ((world.getBlockMetadata(x, y, z) & 7) == Main.orientationHelper[this.dir]) {
					if (l > this.maxLength) {
						world.func_147480_a(x, y, z, true);
						this.connected = false;
						break;
					}
					l++;
					if ((world.getBlockMetadata(x, y, z) & 8) == 0) {
						world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 2);
					}
				} else {
					this.connected = false;
					break;
				}
			} else if (world.getTileEntity(x, y, z) instanceof IRotaryOutput && ((IRotaryOutput) world.getTileEntity(x, y, z)).isOutputFace(Main.reverseHelper[this.dir])) {
				world.func_147480_a(x, y, z, true);
				this.connected = false;
				break;
			} else if (world.getTileEntity(x, y, z) instanceof IRotaryInput && ((IRotaryInput) world.getTileEntity(x, y, z)).isInputFace(Main.reverseHelper[this.dir])) {
				((IRotaryInput) world.getTileEntity(x, y, z)).setRotaryInput(Main.reverseHelper[this.dir], rotation, l);
				this.tilePos[0] = x;
				this.tilePos[1] = y;
				this.tilePos[2] = z;
				this.connected = true;
				break;
			} else {
				this.connected = false;
				break;
			}
		}
		
		if (this.length > l) {
			this.cleanup(world, x, y, z);
		}
		
		this.length = l;
		return (lastSpeed != speed) || (lastLength != length);
	}
	
	public void cleanup(World world, int x, int y, int z) {
		ForgeDirection direction = ForgeDirection.getOrientation(this.dir);
		for (int i=0;i<this.length;i++) {
			x += direction.offsetX;
			y += direction.offsetY;
			z += direction.offsetZ;
			Block block = world.getBlock(x, y, z);
			if (block instanceof BlockAxis) {
				world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) & 7, 2);
			}
		}
		TileEntity t = world.getTileEntity(tilePos[0], tilePos[1], tilePos[2]);
		if (t != null && t instanceof IRotaryInput) {
			((IRotaryInput)t).setRotaryInput(Main.reverseHelper[this.dir], 0, 0);
		}
	}
	
	//lawl bug workaround
	public void updateChainBlocksToClients(World world, int x, int y, int z) {
		ForgeDirection direction = ForgeDirection.getOrientation(this.dir);
		for (int i=0;i<this.length;i++) {
			x += direction.offsetX;
			y += direction.offsetY;
			z += direction.offsetZ;
			if (world.getBlock(x, y, z) instanceof BlockAxis && (world.getBlockMetadata(x, y, z) & 8) != 0) {
				world.markBlockForUpdate(x, y, z);
			}
		}
		TileEntity t = world.getTileEntity(tilePos[0], tilePos[1], tilePos[2]);
		if (t != null && t instanceof IRotaryInput) {
			((IRotaryInput)t).setRotaryInput(Main.reverseHelper[this.dir], 0, 0);
		}
	}
	
	public NBTTagCompound toNetworkNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("speed", speed);
		tag.setInteger("length", length);
		tag.setInteger("dir", dir);
		tag.setDouble("visual_position", position);
		return tag;
	}
	
	public void fromNetworkNBT(NBTTagCompound tag) {
		this.speed = tag.getInteger("speed");
		this.length = tag.getInteger("length");
		this.dir = tag.getInteger("dir");
		this.position = tag.getDouble("visual_position");
	}
	
}
