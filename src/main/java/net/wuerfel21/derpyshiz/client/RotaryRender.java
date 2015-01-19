package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import net.wuerfel21.derpyshiz.DerpyBlocks;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.rotary.AxisChain;
import static net.wuerfel21.derpyshiz.client.DerpyRenderHelper.getU;
import static net.wuerfel21.derpyshiz.client.DerpyRenderHelper.getV;

import org.lwjgl.opengl.GL11;

public class RotaryRender {

	public static final boolean[][] axisFaces = new boolean[][] { { false, false, true, true, true, true }, { false, false, true, true, true, true }, { true, true, false, false, true, true }, { true, true, false, false, true, true }, { true, true, true, true, false, false }, { true, true, true, true, false, false } };

	public static final boolean[][] axisEnds = new boolean[][] { { true, false, true, true, true, true }, { false, true, true, true, true, true }, { true, true, true, false, true, true }, { true, true, false, true, true, true }, { true, true, true, true, true, false }, { true, true, true, true, false, true } };

	public static final double wp = DerpyRenderHelper.wp;

	public static final double minAxis = 0.5 - (wp * 4);
	public static final double maxAxis = 0.5 + (wp * 4);

	public static final double minC = wp * 4;
	public static final double maxC = wp * 12;

	public static void rotateAxis(double position, int dir) {
		switch (dir) {
		default:
		case 0:
			GL11.glRotated(position, 0, -1, 0);
			break;
		case 1:
			GL11.glRotated(position, 0, 1, 0);
			break;
		case 2:
			GL11.glRotated(position, 0, 0, -1);
			break;
		case 3:
			GL11.glRotated(position, 0, 0, 1);
			break;
		case 4:
			GL11.glRotated(position, -1, 0, 0);
			break;
		case 5:
			GL11.glRotated(position, 1, 0, 0);
			break;
		}
	}

	public static void renderAxisChain(Tessellator tessellator, AxisChain chain) {
		//if (chain.length <= 0)
		//	return;
		System.out.println("dat render"+chain.speed+"lawl"+chain.length);
		GL11.glPushMatrix();
		rotateAxis(chain.position, chain.dir);
		tessellator.startDrawingQuads();
		renderConnectionPiece(tessellator, chain.dir);
		int x = 0, y = 0, z = 0;
		ForgeDirection fdir = ForgeDirection.getOrientation(chain.dir);
		for (int i = 0; i < chain.length; i++) {
			tessellator.addTranslation(fdir.offsetX, fdir.offsetY, fdir.offsetZ);
			renderAxis(tessellator, chain.dir, i-1 == chain.length?axisEnds:axisFaces);
		}
		tessellator.addTranslation(fdir.offsetX, fdir.offsetY, fdir.offsetZ);
		renderConnectionPiece(tessellator, Main.reverseHelper[chain.dir]);
		tessellator.draw();
		GL11.glPopMatrix();
	}

	public static void renderAxis(Tessellator tessellator, int dir, boolean[][] renderFace) {
		switch (dir) {
		default:
		case 0:
			DerpyRenderHelper.addBox(tessellator, Blocks.planks.getIcon(0, 0), minAxis, 0, minAxis, maxAxis, 1, maxAxis, renderFace[0]);
			break;
		case 1:
			DerpyRenderHelper.addBox(tessellator, Blocks.planks.getIcon(0, 0), minAxis, 0, minAxis, maxAxis, 1, maxAxis, renderFace[1]);
			break;
		case 2:
			DerpyRenderHelper.addBox(tessellator, Blocks.planks.getIcon(0, 0), minAxis, minAxis, 0, maxAxis, maxAxis, 1, renderFace[2]);
			break;
		case 3:
			DerpyRenderHelper.addBox(tessellator, Blocks.planks.getIcon(0, 0), minAxis, minAxis, 0, maxAxis, maxAxis, 1, renderFace[3]);
			break;
		case 4:
			DerpyRenderHelper.addBox(tessellator, Blocks.planks.getIcon(0, 0), 0, minAxis, minAxis, 1, maxAxis, maxAxis, renderFace[4]);
			break;
		case 5:
			DerpyRenderHelper.addBox(tessellator, Blocks.planks.getIcon(0, 0), 0, minAxis, minAxis, 1, maxAxis, maxAxis, renderFace[5]);
			break;
		}
	}

	public static void renderConnectionPiece(Tessellator tessellator, int dir) {
		switch (dir) {
		default:
		case 0:
			DerpyRenderHelper.addBox(tessellator, Blocks.planks.getIcon(0, 0), minAxis, 0, minAxis, maxAxis, wp, maxAxis);
			break;
		case 1:
			DerpyRenderHelper.addBox(tessellator, Blocks.planks.getIcon(0, 0), minAxis, 1 - wp, minAxis, maxAxis, 1, maxAxis);
			break;
		case 2:
			DerpyRenderHelper.addBox(tessellator, Blocks.planks.getIcon(0, 0), minAxis, minAxis, 0, maxAxis, maxAxis, wp);
			break;
		case 3:
			DerpyRenderHelper.addBox(tessellator, Blocks.planks.getIcon(0, 0), minAxis, minAxis, 1 - wp, maxAxis, maxAxis, 1);
			break;
		case 4:
			DerpyRenderHelper.addBox(tessellator, Blocks.planks.getIcon(0, 0), 0, minAxis, minAxis, wp, maxAxis, maxAxis);
			break;
		case 5:
			DerpyRenderHelper.addBox(tessellator, Blocks.planks.getIcon(0, 0), 1 - wp, minAxis, minAxis, 1, maxAxis, maxAxis);
			break;
		}
	}

	public static void fancyConnection(Tessellator tessellator, IIcon texture, IIcon overlay, int dir) {
		switch (dir) {
		default:
		case 0:// bottom
			DerpyRenderHelper.addBox(tessellator, texture, minC, 0.005, minC, maxC, 0.1, maxC);
			tessellator.setNormal(0, -1, 0);
			tessellator.addVertexWithUV(maxC, 0, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(maxC, 0, maxC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, 0, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, 0, minC, overlay.getMinU(), overlay.getMinV());
			break;
		case 1:// top
			DerpyRenderHelper.addBox(tessellator, texture, minC, 0.9, minC, maxC, 0.995, maxC);
			tessellator.setNormal(0, 1, 0);
			tessellator.addVertexWithUV(maxC, 1, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, 1, minC, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, 1, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(maxC, 1, maxC, overlay.getMaxU(), overlay.getMaxV());
			break;
		case 2:// north
			DerpyRenderHelper.addBox(tessellator, texture, minC, minC, 0.005, maxC, maxC, 0.1);
			tessellator.setNormal(0, 0, -1);
			tessellator.addVertexWithUV(maxC, minC, 0, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, minC, 0, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, maxC, 0, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(maxC, maxC, 0, overlay.getMinU(), overlay.getMinV());
			break;
		case 3:// south
			DerpyRenderHelper.addBox(tessellator, texture, minC, minC, 0.9, maxC, maxC, 0.995);
			tessellator.setNormal(0, 0, 1);
			tessellator.addVertexWithUV(maxC, minC, 1, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(maxC, maxC, 1, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, maxC, 1, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, minC, 1, overlay.getMinU(), overlay.getMaxV());
			break;
		case 4:// west
			DerpyRenderHelper.addBox(tessellator, texture, 0.005, minC, minC, 0.1, maxC, maxC);
			tessellator.setNormal(-1, 0, 0);
			tessellator.addVertexWithUV(0, minC, maxC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(0, maxC, maxC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(0, maxC, minC, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(0, minC, minC, overlay.getMinU(), overlay.getMaxV());
			break;
		case 5:// east
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
		case 0:// bottom
			tessellator.setNormal(0, -1, 0);
			tessellator.addVertexWithUV(maxC, -0.005, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(maxC, -0.005, maxC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, -0.005, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, -0.005, minC, overlay.getMinU(), overlay.getMinV());
			break;
		case 1:// top
			tessellator.setNormal(0, 1, 0);
			tessellator.addVertexWithUV(maxC, 1.005, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, 1.005, minC, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, 1.005, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(maxC, 1.005, maxC, overlay.getMaxU(), overlay.getMaxV());
			break;
		case 2:// north
			tessellator.setNormal(0, 0, -1);
			tessellator.addVertexWithUV(maxC, minC, -0.005, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, minC, -0.005, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(minC, maxC, -0.005, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(maxC, maxC, -0.005, overlay.getMinU(), overlay.getMinV());
			break;
		case 3:// south
			tessellator.setNormal(0, 0, 1);
			tessellator.addVertexWithUV(maxC, minC, 1.005, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(maxC, maxC, 1.005, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, maxC, 1.005, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(minC, minC, 1.005, overlay.getMinU(), overlay.getMaxV());
			break;
		case 4:// west
			tessellator.setNormal(-1, 0, 0);
			tessellator.addVertexWithUV(-0.005, minC, maxC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(-0.005, maxC, maxC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(-0.005, maxC, minC, overlay.getMinU(), overlay.getMinV());
			tessellator.addVertexWithUV(-0.005, minC, minC, overlay.getMinU(), overlay.getMaxV());
			break;
		case 5:// east
			tessellator.setNormal(1, 0, 0);
			tessellator.addVertexWithUV(1.005, minC, maxC, overlay.getMinU(), overlay.getMaxV());
			tessellator.addVertexWithUV(1.005, minC, minC, overlay.getMaxU(), overlay.getMaxV());
			tessellator.addVertexWithUV(1.005, maxC, minC, overlay.getMaxU(), overlay.getMinV());
			tessellator.addVertexWithUV(1.005, maxC, maxC, overlay.getMinU(), overlay.getMinV());
			break;
		}
	}

}
