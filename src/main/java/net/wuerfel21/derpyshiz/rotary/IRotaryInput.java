package net.wuerfel21.derpyshiz.rotary;

public interface IRotaryInput {

	public boolean isInputFace(int side);
	
	public void setRotaryInput(int side, int speed, int length);
	
	public int getRotaryInput(int side);
	
	public int getRotaryInputLength(int side);
}
