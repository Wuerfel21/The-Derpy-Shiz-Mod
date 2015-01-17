package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public class RotaryRender {
	
	public static final double wp = DerpyRenderHelper.wp;
	
	public static final double minC = wp*4;
	public static final double maxC = wp*12;
	
	public static void fancyConnection(Tessellator tessellator, IIcon texture, IIcon overlay, int dir) {
		switch (dir) {
		default:
		case 0://bottom
			DerpyRenderHelper.addBox(tessellator, texture, minC, 0.005, minC, maxC, 0.1, maxC);
			tessellator.setNormal(0, -1, 0);
			tessellator.addVertexWithUV(maxC, 0, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(maxC, 0, maxC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, 0, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, 0, minC, overlay.getMinU(), overlay.getMinV());
			break;
		case 1://top
			DerpyRenderHelper.addBox(tessellator, texture, minC, 0.9, minC, maxC, 0.995, maxC);
			tessellator.setNormal(0, 1, 0);
			tessellator.addVertexWithUV(maxC, 1, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, 1, minC, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, 1, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(maxC, 1, maxC, overlay.getMaxU(), overlay.getMaxV());
			break;
		case 2://north
			DerpyRenderHelper.addBox(tessellator, texture, minC, minC, 0.005, maxC, maxC, 0.1);
			tessellator.setNormal(0, 0, -1);
			tessellator.addVertexWithUV(maxC, minC, 0, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, minC, 0, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, maxC, 0, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(maxC, maxC, 0, overlay.getMinU(), overlay.getMinV());
			break;
		case 3://south
			DerpyRenderHelper.addBox(tessellator, texture, minC, minC, 0.9, maxC, maxC, 0.995);
			tessellator.setNormal(0, 0, 1);
			tessellator.addVertexWithUV(maxC, minC, 1, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(maxC, maxC, 1, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, maxC, 1, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, minC, 1, overlay.getMinU(), overlay.getMaxV());
			break;
		case 4://west
			DerpyRenderHelper.addBox(tessellator, texture, 0.005, minC, minC, 0.1, maxC, maxC);
			tessellator.setNormal(-1, 0, 0);
			tessellator.addVertexWithUV(0, minC, maxC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(0, maxC, maxC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(0, maxC, minC, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(0, minC, minC, overlay.getMinU(), overlay.getMaxV());
			break;
		case 5://east
			DerpyRenderHelper.addBox(tessellator, texture, 0.9, minC, minC, 0.995, maxC, maxC);
			tessellator.setNormal(1, 0, 0);
			tessellator.addVertexWithUV(1, minC, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(1, minC, minC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(1, maxC, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(1, maxC, maxC, overlay.getMinU(), overlay.getMinV());
			break;
		}
	}
	
	public static void uglyConnection(Tessellator tessellator, IIcon overlay, int dir) {
		switch (dir) {
		default:
		case 0://bottom
			tessellator.setNormal(0, -1, 0);
			tessellator.addVertexWithUV(maxC, -0.005, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(maxC, -0.005, maxC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, -0.005, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, -0.005, minC, overlay.getMinU(), overlay.getMinV());
			break;
		case 1://top
			tessellator.setNormal(0, 1, 0);
			tessellator.addVertexWithUV(maxC, 1.005, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, 1.005, minC, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, 1.005, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(maxC, 1.005, maxC, overlay.getMaxU(), overlay.getMaxV());
			break;
		case 2://north
			tessellator.setNormal(0, 0, -1);
			tessellator.addVertexWithUV(maxC, minC, -0.005, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, minC, -0.005, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, maxC, -0.005, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(maxC, maxC, -0.005, overlay.getMinU(), overlay.getMinV());
			break;
		case 3://south
			tessellator.setNormal(0, 0, 1);
			tessellator.addVertexWithUV(maxC, minC, 1.005, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(maxC, maxC, 1.005, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, maxC, 1.005, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, minC, 1.005, overlay.getMinU(), overlay.getMaxV());
			break;
		case 4://west
			tessellator.setNormal(-1, 0, 0);
			tessellator.addVertexWithUV(-0.005, minC, maxC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(-0.005, maxC, maxC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(-0.005, maxC, minC, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(-0.005, minC, minC, overlay.getMinU(), overlay.getMaxV());
			break;
		case 5://east
			tessellator.setNormal(1, 0, 0);
			tessellator.addVertexWithUV(1.005, minC, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(1.005, minC, minC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(1.005, maxC, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(1.005, maxC, maxC, overlay.getMinU(), overlay.getMinV());
			break;
		}
	}
	
}
