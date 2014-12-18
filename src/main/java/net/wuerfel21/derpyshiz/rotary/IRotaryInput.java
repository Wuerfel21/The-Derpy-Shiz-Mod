package net.wuerfel21.derpyshiz.rotary;

public interface IRotaryInput {

	public boolean isInputFace(int side);
	
	public void setRotaryInput(int side, Rotation rotation);
	
	public Rotation getRotaryInput(int side);
}
