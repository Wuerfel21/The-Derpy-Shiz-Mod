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

	public static void renderItem(ItemStack stack, int pass) {
		GL11.glPushMatrix();
		TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
		TextureUtil.func_152777_a(false, false, 1.0F);
		Tessellator tessellator = Tessellator.instance;
		IIcon iicon = stack.getIconIndex();
		float f = iicon.getMinU();
		float f1 = iicon.getMaxU();
		float f2 = iicon.getMinV();
		float f3 = iicon.getMaxV();
		float f4 = 0.0F;
		float f5 = 0.3F;
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef(-f4, -f5, 0.0F);
		float f6 = 1.5F;
		GL11.glScalef(f6, f6, f6);
		GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
		ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625F);

		if (stack.hasEffect(pass)) {
			GL11.glDepthFunc(GL11.GL_EQUAL);
			GL11.glDisable(GL11.GL_LIGHTING);
			texturemanager.bindTexture(RES_ITEM_GLINT);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(768, 1, 1, 0);
			float f7 = 0.76F;
			GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
			GL11.glMatrixMode(GL11.GL_TEXTURE);
			GL11.glPushMatrix();
			float f8 = 0.125F;
			GL11.glScalef(f8, f8, f8);
			float f9 = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
			GL11.glTranslatef(f9, 0.0F, 0.0F);
			GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
			ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(f8, f8, f8);
			f9 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
			GL11.glTranslatef(-f9, 0.0F, 0.0F);
			GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
			ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDepthFunc(GL11.GL_LEQUAL);
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		texturemanager.bindTexture(texturemanager.getResourceLocation(stack.getItemSpriteNumber()));
		TextureUtil.func_147945_b();
		GL11.glPopMatrix();
	}

}
