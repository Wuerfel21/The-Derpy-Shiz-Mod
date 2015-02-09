package net.wuerfel21.derpyshiz.client;

import static net.wuerfel21.derpyshiz.client.DerpyRenderHelper.wp;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.DerpyBlocks;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.blocks.BlockSplittingGearbox;
import net.wuerfel21.derpyshiz.blocks.RotaryHousing;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxSplitting;

public class RenderGearboxSplitting extends TileEntitySpecialRenderer {

	public static final double wp2 = wp * 2;
	
	public RenderGearboxSplitting() {
		super();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tiletemp, double x, double y, double z, float f) {
		TileEntityGearboxSplitting tile = (TileEntityGearboxSplitting) tiletemp;
		this.bindTexture(TextureMap.locationBlocksTexture);
		BlockSplittingGearbox block = null;
		if (!tile.inInventory) {
			block = (BlockSplittingGearbox) tile.getBlockType();
		}
		
		int meta = tile.getTier();
		
		IIcon[][] textures = new IIcon[][] {{Blocks.planks.getIcon(0, 4),DerpyBlocks.plank.getIcon(0, 0),Blocks.planks.getIcon(0, 5)},{DerpyBlocks.oreBlocks.getIcon(0, 9),DerpyBlocks.oreBlocks.getIcon(0, 2),Blocks.emerald_block.getIcon(0, 0)}};

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		tessellator.startDrawingQuads();
		if (Main.fancyGearbox) {
			RenderRotaryComponent.render(tessellator, textures[meta]);
		} else {
			// do nothing, ugly splitting gearboxes are rendered by BlockGearbox
		}
		if (!tile.inInventory) {
			if (Main.fancyGearbox) {
				RotaryRender.fancyConnection(tessellator, textures[meta][2], RotaryHousing.overlays[0], tile.dir);
				RotaryRender.fancyConnection(tessellator, textures[meta][2], RotaryHousing.overlays[0], tile.dir2);
			} else {
				RotaryRender.uglyConnection(tessellator, RotaryHousing.overlays[0], tile.dir);
				RotaryRender.uglyConnection(tessellator, RotaryHousing.overlays[0], tile.dir2);
			}
		}
		tessellator.draw();
		if (!tile.inInventory) {
			this.bindTexture(RotaryRender.axisTexture);
			RotaryRender.renderAxisChain(tessellator, tile.chain);
			RotaryRender.renderAxisChain(tessellator, tile.chain2);
		}
		GL11.glPopMatrix();
	}

}
