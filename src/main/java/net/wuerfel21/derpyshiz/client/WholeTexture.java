package net.wuerfel21.derpyshiz.client;

import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class WholeTexture implements IIcon {

	public ResourceLocation location;

	public WholeTexture(ResourceLocation l) {
		location = l;
	}

	@Override
	public int getIconWidth() {
		return 16;
	}

	@Override
	public int getIconHeight() {
		return 16;
	}

	@Override
	public float getMinU() {
		return 0;
	}

	@Override
	public float getMaxU() {
		return 1;
	}

	@Override
	public float getInterpolatedU(double mult) {
		return (float)mult/16;
	}

	@Override
	public float getMinV() {
		return 0;
	}

	@Override
	public float getMaxV() {
		return 1;
	}

	@Override
	public float getInterpolatedV(double mult) {
		return (float)mult/16;
	}

	@Override
	public String getIconName() {
		return location.getResourcePath();
	}

}
