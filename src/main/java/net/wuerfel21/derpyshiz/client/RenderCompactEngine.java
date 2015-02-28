package net.wuerfel21.derpyshiz.client;

import static net.wuerfel21.derpyshiz.client.RotaryRender.maxAxisWidth;
import static net.wuerfel21.derpyshiz.client.RotaryRender.minAxisWidth;
import static net.wuerfel21.derpyshiz.client.RotaryRender.minC;
import static net.wuerfel21.derpyshiz.client.RotaryRender.maxC;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.blocks.BlockCompactEngine;
import net.wuerfel21.derpyshiz.blocks.BlockCrank;
import net.wuerfel21.derpyshiz.blocks.RotaryHousing;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCompactEngine;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCrank;

public class RenderCompactEngine extends TileEntitySpecialRenderer {

	public static final double wp = DerpyRenderHelper.wp;
	public static final double wp2 = wp * 2;
	public static final double wp3 = wp * 3;
	public static final double wp4 = wp * 4;

	public RenderCompactEngine() {
		super();
	}

	@Override
	public void renderTileEntityAt(TileEntity tiletemp, double x, double y, double z, float f) {
		TileEntityCompactEngine tile = (TileEntityCompactEngine) tiletemp;
		this.bindTexture(TextureMap.locationBlocksTexture);

		IIcon[] textures = new IIcon[] { Blocks.iron_block.getIcon(0, 1), Blocks.gold_block.getIcon(0, 0), Blocks.planks.getIcon(0, 0), Blocks.quartz_block.getIcon(0, 1), Blocks.stained_hardened_clay.getIcon(0, 0)};

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		tessellator.startDrawingQuads();

		switch (tile.dir) {
		default:
		case 0:
			RenderRotaryComponent.render(tessellator, textures, 0, wp*10, 0, 1, 1, 1);
			RenderRotaryComponent.renderFrame(tessellator, textures[1], wp3, wp, wp3, wp*13, wp*13, wp*13);
			DerpyRenderHelper.addBox(tessellator, textures[3], minC, wp, minC, maxC, wp*9, maxC, DerpyRenderHelper.drawAll, true);
			break;
		case 1:
			RenderRotaryComponent.render(tessellator, textures, 0, 0, 0, 1, wp*6, 1);
			RenderRotaryComponent.renderFrame(tessellator, textures[1], wp3, wp3, wp3, wp*13, wp*15, wp*13);
			DerpyRenderHelper.addBox(tessellator, textures[3], minC, wp*7, minC, maxC, wp*15, maxC, DerpyRenderHelper.drawAll, true);
			break;
		case 2:
			RenderRotaryComponent.render(tessellator, textures, 0, 0, wp*10, 1, 1, 1);
			RenderRotaryComponent.renderFrame(tessellator, textures[1], wp3, wp3, wp, wp*13, wp*13, wp*13);
			DerpyRenderHelper.addBox(tessellator, textures[3], minC, minC, wp, maxC, maxC, wp*9, DerpyRenderHelper.drawAll, true);
			break;
		case 3:
			RenderRotaryComponent.render(tessellator, textures, 0, 0, 0, 1, 1, wp*6);
			RenderRotaryComponent.renderFrame(tessellator, textures[1], wp3, wp3, wp3, wp*13, wp*13, wp*15);
			DerpyRenderHelper.addBox(tessellator, textures[3], minC, minC, wp*7, maxC, maxC, wp*15, DerpyRenderHelper.drawAll, true);
			break;
		case 4:
			RenderRotaryComponent.render(tessellator, textures, wp*10, 0, 0, 1, 1, 1);
			RenderRotaryComponent.renderFrame(tessellator, textures[1], wp, wp3, wp3, wp*13, wp*13, wp*13);
			DerpyRenderHelper.addBox(tessellator, textures[3], wp, minC, minC, wp*9, maxC, maxC, DerpyRenderHelper.drawAll, true);
			break;
		case 5:
			RenderRotaryComponent.render(tessellator, textures, 0, 0, 0, wp*6, 1, 1);
			RenderRotaryComponent.renderFrame(tessellator, textures[1], wp3, wp3, wp3, wp*15, wp*13, wp*13);
			DerpyRenderHelper.addBox(tessellator, textures[3], wp*7, minC, minC, wp*15, maxC, maxC, DerpyRenderHelper.drawAll, true);
			break;
		}

		if (!tile.inInventory) {
			RotaryRender.fancyConnection(tessellator, textures[4], RotaryHousing.overlays[0], tile.dir);
		}
		
		tessellator.draw();

		GL11.glPushMatrix();
		GL11.glTranslated(0.5, 0.5, 0.5);
		RotaryRender.rotateAxis(tile.chain.position, tile.dir);
		GL11.glTranslated(-0.5, -0.5, -0.5);
		tessellator.startDrawingQuads();

		switch (tile.dir) {
		default:
		case 0:
			DerpyRenderHelper.addBox(tessellator, textures[2], minAxisWidth, 0.25, minAxisWidth, maxAxisWidth, 0.75, maxAxisWidth, RotaryRender.axisFaces[0]);
			break;
		case 1:
			DerpyRenderHelper.addBox(tessellator, textures[2], minAxisWidth, 0.25, minAxisWidth, maxAxisWidth, 0.75, maxAxisWidth, RotaryRender.axisFaces[1]);
			break;
		case 2:
			DerpyRenderHelper.addBox(tessellator, textures[2], minAxisWidth, minAxisWidth, 0.25, maxAxisWidth, maxAxisWidth, 0.75, RotaryRender.axisFaces[2]);
			break;
		case 3:
			DerpyRenderHelper.addBox(tessellator, textures[2], minAxisWidth, minAxisWidth, 0.25, maxAxisWidth, maxAxisWidth, 0.75, RotaryRender.axisFaces[3]);
			break;
		case 4:
			DerpyRenderHelper.addBox(tessellator, textures[2], 0.25, minAxisWidth, minAxisWidth, 0.75, maxAxisWidth, maxAxisWidth, RotaryRender.axisFaces[4]);
			break;
		case 5:
			DerpyRenderHelper.addBox(tessellator, textures[2], 0.25, minAxisWidth, minAxisWidth, 0.75, maxAxisWidth, maxAxisWidth, RotaryRender.axisFaces[5]);
			break;

		}

		tessellator.draw();
		GL11.glPopMatrix();
		if (!tile.inInventory) {
			this.bindTexture(RotaryRender.axisTexture);
			RotaryRender.renderAxisChain(tessellator, tile.chain);
		}
		GL11.glPopMatrix();
	}

}
