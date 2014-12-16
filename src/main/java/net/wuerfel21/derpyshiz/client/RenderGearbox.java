package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;


public class RenderGearbox extends TileEntitySpecialRenderer {
	
	public RenderGearbox() {
		super();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		this.bindTexture(TextureMap.locationBlocksTexture);
		
		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0, 0, 0, 0, 0);
	    tessellator.addVertexWithUV(0, 1, 0, 0, 1);
	    tessellator.addVertexWithUV(1, 1, 0, 1, 1);
	    tessellator.addVertexWithUV(1, 0, 0, 1, 0);

	    tessellator.addVertexWithUV(0, 0, 0, 0, 0);
	    tessellator.addVertexWithUV(1, 0, 0, 1, 0);
	    tessellator.addVertexWithUV(1, 1, 0, 1, 1);
	    tessellator.addVertexWithUV(0, 1, 0, 0, 1);

	    tessellator.draw();
	    GL11.glPopMatrix();
	}

}
