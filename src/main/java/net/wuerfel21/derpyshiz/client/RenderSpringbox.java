package net.wuerfel21.derpyshiz.client;

import static net.wuerfel21.derpyshiz.client.DerpyRenderHelper.wp;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.DerpyBlocks;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.blocks.BlockAbstractGearbox;
import net.wuerfel21.derpyshiz.blocks.BlockSpringbox;
import net.wuerfel21.derpyshiz.blocks.RotaryHousing;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntitySpringbox;

public class RenderSpringbox extends TileEntitySpecialRenderer {
	
	public RenderSpringbox() {
		super();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tiletemp, double x, double y, double z, float f) {
		TileEntitySpringbox tile = (TileEntitySpringbox) tiletemp;
		this.bindTexture(TextureMap.locationBlocksTexture);
		BlockSpringbox block = null;
		if (!tile.inInventory) {
			block = (BlockSpringbox) tile.getBlockType();
		}
		
		int meta = tile.getTier();
		
		IIcon[] textures = getIcons(meta);

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		tessellator.startDrawingQuads();
		if (Main.fancyGearbox) {
			RenderRotaryComponent.render(tessellator, textures);
		} else {
			// do nothing, ugly springboxes are rendered by BlockSpringbox
		}
		if (!tile.inInventory) {
			if (Main.fancyGearbox) {
				RotaryRender.fancyConnection(tessellator, textures[2], RotaryHousing.overlays[0], tile.dir);
			} else {
				RotaryRender.uglyConnection(tessellator, RotaryHousing.overlays[0], tile.dir);
			}
		}
		tessellator.draw();
		if (!tile.inInventory) {
			this.bindTexture(RotaryRender.axisTexture);
			RotaryRender.renderAxisChain(tessellator, tile.chain);
		}
		GL11.glPopMatrix();
	}

	protected IIcon[] getIcons(int tier) {
		switch(tier) {
		default:
		case 0:
			return new IIcon[] {Blocks.planks.getIcon(0, 3),Blocks.planks.getIcon(0, 5),Blocks.brick_block.getIcon(0, 0)};
		case 1:
			return new IIcon[] {DerpyBlocks.oreBlocks.getIcon(0, 11),Blocks.gold_block.getIcon(0, 0),Blocks.end_stone.getIcon(0, 0)};
		}
	}
	
}
