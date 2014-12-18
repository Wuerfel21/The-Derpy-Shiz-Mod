package net.wuerfel21.derpyshiz.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public class DerpyRenderHelper {
	
	public static final double wp = 1d/16d;
	
	public static double getU(double pos, IIcon texture) {
		return texture.getMinU()+((texture.getMaxU()-texture.getMinU())*pos);
	}
	
	public static double getV(double pos, IIcon texture) {
		return texture.getMinV()+((texture.getMaxV()-texture.getMinV())*pos);
	}
	
	public static void addBox(Tessellator tessellator, IIcon t, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		// north face
		tessellator.setNormal(0, 0, -1);
		tessellator.addVertexWithUV(maxX, minY, minZ, getU(minX,t), getV(maxY,t));
		tessellator.addVertexWithUV(minX, minY, minZ, getU(maxX,t), getV(maxY,t));
		tessellator.addVertexWithUV(minX, maxY, minZ, getU(maxX,t), getV(minY,t));
		tessellator.addVertexWithUV(maxX, maxY, minZ, getU(minX,t), getV(minY,t));
		// south face
		tessellator.setNormal(0, 0, 1);
		tessellator.addVertexWithUV(maxX, minY, maxZ, getU(maxX,t), getV(maxY,t));
		tessellator.addVertexWithUV(maxX, maxY, maxZ, getU(maxX,t), getV(minY,t));
		tessellator.addVertexWithUV(minX, maxY, maxZ, getU(minX,t), getV(minY,t));
		tessellator.addVertexWithUV(minX, minY, maxZ, getU(minX,t), getV(maxY,t));
		// west face
		tessellator.setNormal(-1, 0, 0);
		tessellator.addVertexWithUV(minX, minY, maxZ, getU(maxZ,t), getV(maxY,t));
		tessellator.addVertexWithUV(minX, maxY, maxZ, getU(maxZ,t), getV(minY,t));
		tessellator.addVertexWithUV(minX, maxY, minZ, getU(minZ,t), getV(minY,t));
		tessellator.addVertexWithUV(minX, minY, minZ, getU(minZ,t), getV(maxY,t));
		// east face
		tessellator.setNormal(1, 0, 0);
		tessellator.addVertexWithUV(maxX, minY, maxZ, getU(minZ,t), getV(maxY,t));
		tessellator.addVertexWithUV(maxX, minY, minZ, getU(maxZ,t), getV(maxY,t));
		tessellator.addVertexWithUV(maxX, maxY, minZ, getU(maxZ,t), getV(minY,t));
		tessellator.addVertexWithUV(maxX, maxY, maxZ, getU(minZ,t), getV(minY,t));
		// bottom face
		tessellator.setNormal(0, -1, 0);
		tessellator.addVertexWithUV(maxX, minY, minZ, getU(maxX,t), getV(minZ,t));
		tessellator.addVertexWithUV(maxX, minY, maxZ, getU(maxX,t), getV(maxZ,t));
		tessellator.addVertexWithUV(minX, minY, maxZ, getU(minX,t), getV(maxZ,t));
		tessellator.addVertexWithUV(minX, minY, minZ, getU(minX,t), getV(minZ,t));
		// top face
		tessellator.setNormal(0, 1, 0);
		tessellator.addVertexWithUV(maxX, maxY, minZ, getU(maxX,t), getV(minZ,t));
		tessellator.addVertexWithUV(minX, maxY, minZ, getU(minX,t), getV(minZ,t));
		tessellator.addVertexWithUV(minX, maxY, maxZ, getU(minX,t), getV(maxZ,t));
		tessellator.addVertexWithUV(maxX, maxY, maxZ, getU(maxX,t), getV(maxZ,t));
	}
	
}
