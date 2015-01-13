package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public class RenderRotaryComponent {

	public static final double wp = DerpyRenderHelper.wp;
	public static final double wp2 = wp * 2;

	public static void render(Tessellator tessellator, IIcon[] textures) {
		render(tessellator, textures, 0, 0, 0, 1, 1, 1);
	}

	public static void render(Tessellator tessellator, IIcon[] textures, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		// body
		DerpyRenderHelper.addBox(tessellator, textures[0], minX + wp, minY + wp, minZ + wp, maxX - wp, maxY - wp, maxZ - wp);
		// frame
		DerpyRenderHelper.addBox(tessellator, textures[1], minX, minY, minZ, minX + wp, maxY, minZ + wp);
		DerpyRenderHelper.addBox(tessellator, textures[1], maxX - wp, minY, minZ, maxX, maxY, minZ + wp);
		DerpyRenderHelper.addBox(tessellator, textures[1], minX, minY, maxZ - wp, minX + wp, maxY, maxZ);
		DerpyRenderHelper.addBox(tessellator, textures[1], maxX - wp, minY, maxZ - wp, maxX, maxY, maxZ);

		DerpyRenderHelper.addBox(tessellator, textures[1], minX + wp, minY, minZ, maxX - wp, minY + wp, minZ + wp);
		DerpyRenderHelper.addBox(tessellator, textures[1], minX, minY, minZ + wp, minX + wp, minY + wp, maxZ - wp);
		DerpyRenderHelper.addBox(tessellator, textures[1], minX + wp, minY, maxZ - wp, maxX - wp, minY + wp, maxZ);
		DerpyRenderHelper.addBox(tessellator, textures[1], maxX - wp, minY, minZ+wp, maxX, minY+wp, maxZ - wp);

		DerpyRenderHelper.addBox(tessellator, textures[1], minX + wp, maxY - wp, minZ, maxX - wp, maxY, minZ + wp);
		DerpyRenderHelper.addBox(tessellator, textures[1], minX, maxY - wp, minZ + wp, minX + wp, maxY, maxZ - wp);
		DerpyRenderHelper.addBox(tessellator, textures[1], minX + wp, maxY - wp, maxZ - wp, maxX - wp, maxY, maxZ);
		DerpyRenderHelper.addBox(tessellator, textures[1], maxX - wp, maxY - wp, minZ + wp, maxX, maxY, maxZ - wp);
	}
}
