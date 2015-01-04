package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.blocks.SeizureWool;
import net.wuerfel21.derpyshiz.entity.tile.TileEntitySeizureWool;

import org.lwjgl.opengl.GL11;

public class RenderSeizureWool extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tiletemp, double x, double y, double z, float f) {
		TileEntitySeizureWool tile = (TileEntitySeizureWool) tiletemp;
		this.bindTexture(TextureMap.locationBlocksTexture);
		SeizureWool block = (SeizureWool) tile.getBlockType();
		IIcon[] wool = new IIcon[16];
		for(int i=0;i<16;i++) {
			wool[i] = Blocks.wool.getIcon(0, i);
		}
		
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		tessellator.startDrawingQuads();
		
		DerpyRenderHelper.addBox(tessellator, wool[tile.color], 0, 0, 0, 1, 1, 1);
		
		tessellator.draw();
		GL11.glPopMatrix();
	}

}
