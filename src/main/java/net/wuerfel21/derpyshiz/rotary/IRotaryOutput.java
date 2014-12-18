package net.wuerfel21.derpyshiz.rotary;

public interface IRotaryOutput {
	
	public boolean isOutputFace(int side);
	
	public Rotation getRotaryOutput(int side);
	
	public void setRotaryOutput(int side, Rotation rotation);
	
}
