package net.wuerfel21.derpyshiz.client;

import static net.wuerfel21.derpyshiz.client.RotaryRender.maxAxisWidth;
import static net.wuerfel21.derpyshiz.client.RotaryRender.minAxisWidth;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCrank;

import org.lwjgl.opengl.GL11;

public class RenderCrank extends TileEntitySpecialRenderer {

	public static final double wp = DerpyRenderHelper.wp;
	public static final double wp2 = wp * 2;
	public static final double wp3 = wp * 3;
	public static final double wp4 = wp * 4;

	public RenderCrank() {
		super();
	}

	@Override
	public void renderTileEntityAt(TileEntity tiletemp, double x, double y, double z, float f) {
		TileEntityCrank tile = (TileEntityCrank) tiletemp;
		this.bindTexture(TextureMap.locationBlocksTexture);

		int meta = tile.getTier();

		IIcon[] textures = new IIcon[] {Blocks.planks.getIcon(0, 0),Blocks.iron_block.getIcon(0, 0)};

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		GL11.glPushMatrix();
		GL11.glTranslated(0.5, 0.5, 0.5);
		RotaryRender.rotateAxis(tile.chain.position, tile.dir);
		GL11.glTranslated(-0.5, -0.5, -0.5);
		tessellator.startDrawingQuads();
		
		switch(tile.dir) {
		default:
		case 0:
			DerpyRenderHelper.addBox(tessellator, textures[meta], minAxisWidth, 0, minAxisWidth, maxAxisWidth, maxAxisWidth, maxAxisWidth);
			DerpyRenderHelper.addBox(tessellator, textures[meta], minAxisWidth, minAxisWidth, maxAxisWidth, maxAxisWidth, maxAxisWidth, 1);
			break;
		case 1:
			DerpyRenderHelper.addBox(tessellator, textures[meta], minAxisWidth, minAxisWidth, minAxisWidth, maxAxisWidth, 1, maxAxisWidth);
			DerpyRenderHelper.addBox(tessellator, textures[meta], minAxisWidth, minAxisWidth, 0, maxAxisWidth, maxAxisWidth, minAxisWidth);
			break;
		case 2:
			DerpyRenderHelper.addBox(tessellator, textures[meta], minAxisWidth, minAxisWidth, 0, maxAxisWidth, maxAxisWidth, maxAxisWidth);
			DerpyRenderHelper.addBox(tessellator, textures[meta], maxAxisWidth, minAxisWidth, minAxisWidth, 1, maxAxisWidth, maxAxisWidth);
			break;
		case 3:
			DerpyRenderHelper.addBox(tessellator, textures[meta], minAxisWidth, minAxisWidth, minAxisWidth, maxAxisWidth, maxAxisWidth, 1);
			DerpyRenderHelper.addBox(tessellator, textures[meta], 0, minAxisWidth, minAxisWidth, minAxisWidth, maxAxisWidth, maxAxisWidth);
			break;
		case 4:
			DerpyRenderHelper.addBox(tessellator, textures[meta], 0, minAxisWidth, minAxisWidth, maxAxisWidth, maxAxisWidth, maxAxisWidth);
			DerpyRenderHelper.addBox(tessellator, textures[meta], minAxisWidth, minAxisWidth, maxAxisWidth, maxAxisWidth, maxAxisWidth, 1);
			break;
		case 5:
			DerpyRenderHelper.addBox(tessellator, textures[meta], minAxisWidth, minAxisWidth, minAxisWidth, 1, maxAxisWidth, maxAxisWidth);
			DerpyRenderHelper.addBox(tessellator, textures[meta], minAxisWidth, minAxisWidth, 0, maxAxisWidth, maxAxisWidth, minAxisWidth);
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
