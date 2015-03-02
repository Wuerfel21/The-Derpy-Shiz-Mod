package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class DerpyRenderHelper {

	public static final double wp = 1d / 16d;
	public static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

	public static final boolean[] drawAll = new boolean[] { true, true, true, true, true, true };

	public static double getU(double pos, IIcon texture) {
		return texture.getMinU() + ((texture.getMaxU() - texture.getMinU()) * pos);
	}

	public static double getV(double pos, IIcon texture) {
		return texture.getMinV() + ((texture.getMaxV() - texture.getMinV()) * pos);
	}

	public static void addBox(Tessellator tessellator, IIcon t, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		addBox(tessellator, t, minX, minY, minZ, maxX, maxY, maxZ, drawAll);
	}

	public static void addBox(Tessellator tessellator, IIcon t, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, boolean[] drawFace) {
		addBox(tessellator, t, minX, minY, minZ, maxX, maxY, maxZ, drawFace, false);
	}
	
	public static void addBox(Tessellator tessellator, IIcon t, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, boolean[] drawFace, boolean whole) {
		
		double minXt = whole?0:minX;
		double minYt = whole?0:minY;
		double minZt = whole?0:minZ;
		double maxXt = whole?1:maxX;
		double maxYt = whole?1:maxY;
		double maxZt = whole?1:maxZ;
		
		if (drawFace[0]) {
			// bottom face
			tessellator.setNormal(0, -1, 0);
			tessellator.addVertexWithUV(maxX, minY, minZ, getU(maxXt, t), getV(minZt, t));
			tessellator.addVertexWithUV(maxX, minY, maxZ, getU(maxXt, t), getV(maxZt, t));
			tessellator.addVertexWithUV(minX, minY, maxZ, getU(minXt, t), getV(maxZt, t));
			tessellator.addVertexWithUV(minX, minY, minZ, getU(minXt, t), getV(minZt, t));
		}
		if (drawFace[1]) {
			// top face
			tessellator.setNormal(0, 1, 0);
			tessellator.addVertexWithUV(maxX, maxY, minZ, getU(maxXt, t), getV(minZt, t));
			tessellator.addVertexWithUV(minX, maxY, minZ, getU(minXt, t), getV(minZt, t));
			tessellator.addVertexWithUV(minX, maxY, maxZ, getU(minXt, t), getV(maxZt, t));
			tessellator.addVertexWithUV(maxX, maxY, maxZ, getU(maxXt, t), getV(maxZt, t));
		}
		if (drawFace[2]) {
			// north face
			tessellator.setNormal(0, 0, -1);
			tessellator.addVertexWithUV(maxX, minY, minZ, getU(minXt, t), getV(maxYt, t));
			tessellator.addVertexWithUV(minX, minY, minZ, getU(maxXt, t), getV(maxYt, t));
			tessellator.addVertexWithUV(minX, maxY, minZ, getU(maxXt, t), getV(minYt, t));
			tessellator.addVertexWithUV(maxX, maxY, minZ, getU(minXt, t), getV(minYt, t));
		}
		if (drawFace[3]) {
			// south face
			tessellator.setNormal(0, 0, 1);
			tessellator.addVertexWithUV(maxX, minY, maxZ, getU(maxXt, t), getV(maxYt, t));
			tessellator.addVertexWithUV(maxX, maxY, maxZ, getU(maxXt, t), getV(minYt, t));
			tessellator.addVertexWithUV(minX, maxY, maxZ, getU(minXt, t), getV(minYt, t));
			tessellator.addVertexWithUV(minX, minY, maxZ, getU(minXt, t), getV(maxYt, t));
		}
		if (drawFace[4]) {
			// west face
			tessellator.setNormal(-1, 0, 0);
			tessellator.addVertexWithUV(minX, minY, maxZ, getU(maxZt, t), getV(maxYt, t));
			tessellator.addVertexWithUV(minX, maxY, maxZ, getU(maxZt, t), getV(minYt, t));
			tessellator.addVertexWithUV(minX, maxY, minZ, getU(minZt, t), getV(minYt, t));
			tessellator.addVertexWithUV(minX, minY, minZ, getU(minZt, t), getV(maxYt, t));
		}
		if (drawFace[5]) {
			// east face
			tessellator.setNormal(1, 0, 0);
			tessellator.addVertexWithUV(maxX, minY, maxZ, getU(minZt, t), getV(maxYt, t));
			tessellator.addVertexWithUV(maxX, minY, minZ, getU(maxZt, t), getV(maxYt, t));
			tessellator.addVertexWithUV(maxX, maxY, minZ, getU(maxZt, t), getV(minYt, t));
			tessellator.addVertexWithUV(maxX, maxY, maxZ, getU(minZt, t), getV(minYt, t));
		}
	}

}
