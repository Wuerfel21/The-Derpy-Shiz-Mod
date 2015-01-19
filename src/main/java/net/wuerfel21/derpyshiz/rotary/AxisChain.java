package net.wuerfel21.derpyshiz.rotary;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.blocks.BlockAxis;

public class AxisChain {

	public int dir;

	public int maxLength = 20;
	
	public int[] tilePos = new int[3];

	public int length = -1;
	public int speed;

	// client stuff
	public double position;

	public AxisChain(int direction, int maxL) {
		this.dir = direction;
		this.maxLength = maxL;
	}
	
	public boolean updateChain(IRotaryOutput output, World world, int rotation, int x, int y, int z) {
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
						break;
					}
					l++;
					if ((world.getBlockMetadata(x, y, z) & 8) == 0) {
						world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 2);
					}
				} else {
					break;
				}
			} else if (world.getTileEntity(x, y, z) instanceof IRotaryOutput && ((IRotaryOutput) world.getTileEntity(x, y, z)).isOutputFace(Main.reverseHelper[this.dir])) {
				world.func_147480_a(x, y, z, true);
				break;
			} else if (world.getTileEntity(x, y, z) instanceof IRotaryInput && ((IRotaryInput) world.getTileEntity(x, y, z)).isInputFace(Main.reverseHelper[this.dir])) {
				((IRotaryInput) world.getTileEntity(x, y, z)).setRotaryInput(Main.reverseHelper[this.dir], rotation, l);
				this.tilePos[0] = x;
				this.tilePos[1] = y;
				this.tilePos[2] = z;
				break;
			} else {
				break;
			}
		}
		
		if (this.length > l) {
			this.cleanup(world, x, y, z);
		}
		
		this.length = l;
		return usedFlag;
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
	
}
