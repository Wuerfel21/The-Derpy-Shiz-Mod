package net.wuerfel21.derpyshiz.rotary;

import net.minecraftforge.common.util.ForgeDirection;

public class AxisChain {
	
	protected int dir;
	
	public int length = -1;
	public Rotation rotation;
	
	//client stuff
	public double position;
	
	public AxisChain(int direction) {
		this.dir = direction;
	}
	
	public boolean updateChain(IRotaryOutput output, Rotation rotation) {
		ForgeDirection direction = ForgeDirection.getOrientation(this.dir);
		boolean usedFlag = false;
		
		
		
		return usedFlag;
	}
	
}
