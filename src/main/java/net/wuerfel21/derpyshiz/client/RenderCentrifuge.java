package net.wuerfel21.derpyshiz.client;

import static net.wuerfel21.derpyshiz.client.DerpyRenderHelper.wp;
import static net.wuerfel21.derpyshiz.client.RotaryRender.maxAxisWidth;
import static net.wuerfel21.derpyshiz.client.RotaryRender.maxC;
import static net.wuerfel21.derpyshiz.client.RotaryRender.minAxisWidth;
import static net.wuerfel21.derpyshiz.client.RotaryRender.minC;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.DerpyBlocks;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.blocks.RotaryHousing;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCentrifuge;

public class RenderCentrifuge extends TileEntitySpecialRenderer {

	public static final double wp2 = wp * 2;
	public static final double wp3 = wp * 3;
	public static final double wp4 = wp * 4;

	public RenderCentrifuge() {
		super();
	}

	@Override
	public void renderTileEntityAt(TileEntity tiletemp, double x, double y, double z, float f) {
		TileEntityCentrifuge tile = (TileEntityCentrifuge) tiletemp;
		this.bindTexture(TextureMap.locationBlocksTexture);

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		tessellator.startDrawingQuads();
		if (Main.fancyGearbox) {
			IIcon[][] textures = getIcons(tile.getTier());

			RenderRotaryComponent.render(tessellator, textures[0], 0, 1-wp3, 0, 1, 1, 1);
			RenderRotaryComponent.render(tessellator, textures[0], 0, 0, 0, 1, wp3, 1);
			
			if (!tile.inInventory) {
				RotaryRender.fancyConnection(tessellator, textures[0][2], RotaryHousing.overlays[0], 0);
				RotaryRender.fancyConnection(tessellator, textures[0][2], RotaryHousing.overlays[0], 1);
			}

			tessellator.draw();

			GL11.glPushMatrix();
			GL11.glTranslated(0.5, 0.5, 0.5);
			RotaryRender.rotateAxis(tile.pseudoChain.position, 0);
			GL11.glTranslated(-0.5, -0.5, -0.5);
			tessellator.startDrawingQuads();
			IIcon planks = Blocks.planks.getIcon(0, 0);

			DerpyRenderHelper.addBox(tessellator, planks, minAxisWidth, wp2, minAxisWidth, maxAxisWidth, 0.5-wp3, maxAxisWidth, RotaryRender.axisFaces[0]);
			DerpyRenderHelper.addBox(tessellator, planks, minAxisWidth, 0.5+wp3, minAxisWidth, maxAxisWidth, 1-wp2, maxAxisWidth, RotaryRender.axisFaces[0]);
			
			RenderRotaryComponent.renderFrame(tessellator, textures[1][1], wp*5, wp*5, wp*5, wp*11, wp*11, wp*11);
			
			RenderRotaryComponent.render(tessellator, textures[1], wp*1, wp*6, wp*6, wp*5, wp*10, wp*10);
			RenderRotaryComponent.render(tessellator, textures[1], wp*6, wp*6, wp*1, wp*10, wp*10, wp*5);
			RenderRotaryComponent.render(tessellator, textures[1], wp*11, wp*6, wp*6, wp*15, wp*10, wp*10);
			RenderRotaryComponent.render(tessellator, textures[1], wp*6, wp*6, wp*11, wp*10, wp*10, wp*15);
			
			
		} else {
			if (!tile.inInventory) {
				RotaryRender.uglyConnection(tessellator, RotaryHousing.overlays[0], 0);
				RotaryRender.uglyConnection(tessellator, RotaryHousing.overlays[0], 1);
			}
		}
		tessellator.draw();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	protected IIcon[][] getIcons(int tier) {
		switch(tier) {
		default:
		case 0:
			return new IIcon[][] {{Blocks.brick_block.getIcon(0, 0), Blocks.nether_brick.getIcon(0, 0), Blocks.planks.getIcon(0, 2)},{DerpyBlocks.oreBlocks.getIcon(0, 14),Blocks.iron_block.getIcon(0, 0)}};
		case 1:
			return new IIcon[][] {{DerpyBlocks.oreBlocks.getIcon(0, 4),Blocks.iron_block.getIcon(0, 0), DerpyBlocks.oreBlocks.getIcon(0, 2)},{DerpyBlocks.oreBlocks.getIcon(0, 11),Blocks.gold_block.getIcon(0, 0)}};
		}
	}

}
