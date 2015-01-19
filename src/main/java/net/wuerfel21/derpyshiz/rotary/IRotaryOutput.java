package net.wuerfel21.derpyshiz.rotary;

public interface IRotaryOutput {
	
	public boolean isOutputFace(int side);
	
	public int getRotaryOutput(int side);
	
	public void setRotaryOutput(int side, int speed);
	
}
