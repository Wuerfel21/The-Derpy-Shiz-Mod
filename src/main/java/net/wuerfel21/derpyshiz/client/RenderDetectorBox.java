package net.wuerfel21.derpyshiz.client;

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
import net.wuerfel21.derpyshiz.blocks.RotaryHousing;
import net.wuerfel21.derpyshiz.entity.tile.AbstractGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityDetectorBox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;

public class RenderDetectorBox extends TileEntitySpecialRenderer {

	public RenderDetectorBox() {
		super();
	}

	@Override
	public void renderTileEntityAt(TileEntity tiletemp, double x, double y, double z, float f) {
		AbstractGearbox tile = (AbstractGearbox) tiletemp;
		this.bindTexture(TextureMap.locationBlocksTexture);
		BlockAbstractGearbox block = null;
		if (!tile.inInventory) {
			block = (BlockAbstractGearbox) tile.getBlockType();
		}
		
		int meta = tile.getTier();
		
		IIcon[][] textures = new IIcon[][] {{Blocks.planks.getIcon(0, 1),DerpyBlocks.plank.getIcon(0, 1),DerpyBlocks.plank.getIcon(0, 0)},{Blocks.lapis_block.getIcon(0, 0),Blocks.coal_block.getIcon(0, 0),Blocks.gold_block.getIcon(0, 0)}};

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		tessellator.startDrawingQuads();
		if (Main.fancyGearbox) {
			RenderRotaryComponent.render(tessellator, textures[meta]);
		} else {
			// do nothing, ugly gearboxes are rendered by BlockGearbox
		}
		if (!tile.inInventory) {
			if (Main.fancyGearbox) {
				RotaryRender.fancyConnection(tessellator, textures[meta][2], RotaryHousing.overlays[0], tile.dir);
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

}
