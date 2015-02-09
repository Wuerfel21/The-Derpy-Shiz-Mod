package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.DerpyBlocks;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.blocks.RotaryHousing;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityHousing;

import org.lwjgl.opengl.GL11;

public class RenderHousing extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tiletemp, double x, double y, double z, float f) {
		TileEntityHousing tile = (TileEntityHousing) tiletemp;
		this.bindTexture(TextureMap.locationBlocksTexture);
		RotaryHousing block = null;
		if (!tile.inInventory) {
			block = (RotaryHousing) tile.getBlockType();
		}
		
		int meta = tile.getTier();
		
		IIcon[][] textures = new IIcon[][] {{Blocks.planks.getIcon(0, 0),Blocks.planks.getIcon(0, 4)},{DerpyBlocks.oreBlocks.getIcon(0, 13),DerpyBlocks.oreBlocks.getIcon(0, 2)}};

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		tessellator.startDrawingQuads();
		// tessellator.setBrightness(tile.getBlockType().getMixedBrightnessForBlock(tile.getWorldObj(),
		// tile.xCoord, tile.yCoord, tile.zCoord));
		if (Main.fancyGearbox) {
			RenderRotaryComponent.render(tessellator, textures[meta]);
		} else {
			// do nothing, ugly housings are rendered by RotaryHousing
		}
		tessellator.draw();
		GL11.glPopMatrix();
	}
	
	
}
