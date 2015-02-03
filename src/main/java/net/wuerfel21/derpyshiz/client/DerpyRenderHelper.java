package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

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
		if (drawFace[0]) {
			// bottom face
			tessellator.setNormal(0, -1, 0);
			tessellator.addVertexWithUV(maxX, minY, minZ, getU(maxX, t), getV(minZ, t));
			tessellator.addVertexWithUV(maxX, minY, maxZ, getU(maxX, t), getV(maxZ, t));
			tessellator.addVertexWithUV(minX, minY, maxZ, getU(minX, t), getV(maxZ, t));
			tessellator.addVertexWithUV(minX, minY, minZ, getU(minX, t), getV(minZ, t));
		}
		if (drawFace[1]) {
			// top face
			tessellator.setNormal(0, 1, 0);
			tessellator.addVertexWithUV(maxX, maxY, minZ, getU(maxX, t), getV(minZ, t));
			tessellator.addVertexWithUV(minX, maxY, minZ, getU(minX, t), getV(minZ, t));
			tessellator.addVertexWithUV(minX, maxY, maxZ, getU(minX, t), getV(maxZ, t));
			tessellator.addVertexWithUV(maxX, maxY, maxZ, getU(maxX, t), getV(maxZ, t));
		}
		if (drawFace[2]) {
			// north face
			tessellator.setNormal(0, 0, -1);
			tessellator.addVertexWithUV(maxX, minY, minZ, getU(minX, t), getV(maxY, t));
			tessellator.addVertexWithUV(minX, minY, minZ, getU(maxX, t), getV(maxY, t));
			tessellator.addVertexWithUV(minX, maxY, minZ, getU(maxX, t), getV(minY, t));
			tessellator.addVertexWithUV(maxX, maxY, minZ, getU(minX, t), getV(minY, t));
		}
		if (drawFace[3]) {
			// south face
			tessellator.setNormal(0, 0, 1);
			tessellator.addVertexWithUV(maxX, minY, maxZ, getU(maxX, t), getV(maxY, t));
			tessellator.addVertexWithUV(maxX, maxY, maxZ, getU(maxX, t), getV(minY, t));
			tessellator.addVertexWithUV(minX, maxY, maxZ, getU(minX, t), getV(minY, t));
			tessellator.addVertexWithUV(minX, minY, maxZ, getU(minX, t), getV(maxY, t));
		}
		if (drawFace[4]) {
			// west face
			tessellator.setNormal(-1, 0, 0);
			tessellator.addVertexWithUV(minX, minY, maxZ, getU(maxZ, t), getV(maxY, t));
			tessellator.addVertexWithUV(minX, maxY, maxZ, getU(maxZ, t), getV(minY, t));
			tessellator.addVertexWithUV(minX, maxY, minZ, getU(minZ, t), getV(minY, t));
			tessellator.addVertexWithUV(minX, minY, minZ, getU(minZ, t), getV(maxY, t));
		}
		if (drawFace[5]) {
			// east face
			tessellator.setNormal(1, 0, 0);
			tessellator.addVertexWithUV(maxX, minY, maxZ, getU(minZ, t), getV(maxY, t));
			tessellator.addVertexWithUV(maxX, minY, minZ, getU(maxZ, t), getV(maxY, t));
			tessellator.addVertexWithUV(maxX, maxY, minZ, getU(maxZ, t), getV(minY, t));
			tessellator.addVertexWithUV(maxX, maxY, maxZ, getU(minZ, t), getV(minY, t));
		}
	}

}
