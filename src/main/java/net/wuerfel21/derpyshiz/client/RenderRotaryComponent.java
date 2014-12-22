package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public class RenderRotaryComponent {

	public static final double wp = DerpyRenderHelper.wp;
	public static final double wp2 = wp * 2;

	public static void render(Tessellator tessellator, IIcon[] textures) {
		// body
		DerpyRenderHelper.addBox(tessellator, textures[0], wp, wp, wp, 1 - wp, 1 - wp, 1 - wp);
		// frame
		DerpyRenderHelper.addBox(tessellator, textures[1], 0, 0, 0, wp, 1, wp);
		DerpyRenderHelper.addBox(tessellator, textures[1], 1 - wp, 0, 0, 1, 1, wp);
		DerpyRenderHelper.addBox(tessellator, textures[1], 0, 0, 1 - wp, wp, 1, 1);
		DerpyRenderHelper.addBox(tessellator, textures[1], 1 - wp, 0, 1 - wp, 1, 1, 1);

		DerpyRenderHelper.addBox(tessellator, textures[1], wp, 0, 0, 1 - wp, wp, wp);
		DerpyRenderHelper.addBox(tessellator, textures[1], 0, 0, wp, wp, wp, 1 - wp);
		DerpyRenderHelper.addBox(tessellator, textures[1], wp, 0, 1 - wp, 1 - wp, wp, 1);
		DerpyRenderHelper.addBox(tessellator, textures[1], 1 - wp, 0, wp, 1, wp, 1 - wp);

		DerpyRenderHelper.addBox(tessellator, textures[1], wp, 1 - wp, 0, 1 - wp, 1, wp);
		DerpyRenderHelper.addBox(tessellator, textures[1], 0, 1 - wp, wp, wp, 1, 1 - wp);
		DerpyRenderHelper.addBox(tessellator, textures[1], wp, 1 - wp, 1 - wp, 1 - wp, 1, 1);
		DerpyRenderHelper.addBox(tessellator, textures[1], 1 - wp, 1 - wp, wp, 1, 1, 1 - wp);
	}
}
