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
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;

import org.lwjgl.opengl.GL11;

public class RenderMillstone extends TileEntitySpecialRenderer {

	public static final double wp = DerpyRenderHelper.wp;
	public static final double wp2 = wp * 2;
	public static final double wp3 = wp * 3;
	public static final double wp4 = wp * 4;

	public RenderMillstone() {
		super();
	}

	@Override
	public void renderTileEntityAt(TileEntity tiletemp, double x, double y, double z, float f) {
		TileEntityMillstone tile = (TileEntityMillstone) tiletemp;
		this.bindTexture(TextureMap.locationBlocksTexture);

		int meta = tile.getTier();

		IIcon[] textures = getIcons(meta);

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		tessellator.startDrawingQuads();
		if (Main.fancyGearbox) {
			// lower part
			RenderRotaryComponent.render(tessellator, textures, 0, 0, 0, 1, 0.5 - wp, 1);
			// upper part
			RenderRotaryComponent.render(tessellator, textures, 0, 0.5 + wp, 0, 1, 1, 1);
			// middle thing
			RenderRotaryComponent.render(tessellator, textures, 0.5 - wp3, 0.5 - wp4, 0.5 - wp3, 0.5 + wp3, 0.5 + wp4, 0.5 + wp3);
			// connections
			if (!tile.inInventory) {
				RotaryRender.fancyConnection(tessellator, textures[2], RotaryHousing.overlays[1], 0);
				RotaryRender.fancyConnection(tessellator, textures[2], RotaryHousing.overlays[1], 1);
			}
		} else {
			// do nothing except rendering overlays, ugly millstones are
			// rendered by BlockMillstone
			if (!tile.inInventory) {
				RotaryRender.uglyConnection(tessellator, RotaryHousing.overlays[1], 0);
				RotaryRender.uglyConnection(tessellator, RotaryHousing.overlays[1], 1);
			}
		}
		tessellator.draw();
		GL11.glPopMatrix();
	}

	protected IIcon[] getIcons(int tier) {
		switch(tier) {
		default:
		case 0:
			return new IIcon[] { DerpyBlocks.coarseStone.getIcon(0, 0), Blocks.planks.getIcon(0, 0), DerpyBlocks.oreBlocks.getIcon(0, 9) };
		case 1:
			return new IIcon[] { Blocks.obsidian.getIcon(0, 0), Blocks.diamond_block.getIcon(0, 0), DerpyBlocks.oreBlocks.getIcon(0, 3) };
		}
	}
}
