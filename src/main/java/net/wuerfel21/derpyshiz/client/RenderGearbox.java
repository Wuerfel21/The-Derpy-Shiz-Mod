package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.blocks.BlockGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.GameRegistry;

public class RenderGearbox extends TileEntitySpecialRenderer {

	public static final double wp = DerpyRenderHelper.wp;
	public static final double wp2 = wp * 2;

	public RenderGearbox() {
		super();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tiletemp, double x, double y, double z, float f) {
		TileEntityGearbox tile = (TileEntityGearbox) tiletemp;
		this.bindTexture(TextureMap.locationBlocksTexture);
		BlockGearbox block = (BlockGearbox) tile.blockType;
		
		int meta = tile.meta;
		
		IIcon[][] textures = new IIcon[][] {{Blocks.planks.getIcon(0, 5),Blocks.planks.getIcon(0, 2),Blocks.planks.getIcon(0, 0)},{GameRegistry.findBlock("derpyshiz", "block").getIcon(0, 2),Blocks.iron_block.getIcon(0, 0),Blocks.gold_block.getIcon(0, 0)}};

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		tessellator.startDrawingQuads();
		// tessellator.setBrightness(tile.getBlockType().getMixedBrightnessForBlock(tile.getWorldObj(),
		// tile.xCoord, tile.yCoord, tile.zCoord));
		if (Main.fancyGearbox) {
			RenderRotaryComponent.render(tessellator, textures[meta]);
		} else {
			// do nothing, ugly gearboxes are rendered by BlockGearbox
		}
		if (!tile.inInventory) {
			if (Main.fancyGearbox) {
				RotaryRender.fancyConnection(tessellator, textures[meta][2], block.overlay[0], tile.dir);
			} else {
				RotaryRender.uglyConnection(tessellator, block.overlay[0], tile.dir);
			}
		}
		tessellator.draw();
		GL11.glPopMatrix();
	}

}
