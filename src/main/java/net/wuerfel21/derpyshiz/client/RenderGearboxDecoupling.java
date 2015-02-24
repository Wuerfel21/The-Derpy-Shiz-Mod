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
import net.wuerfel21.derpyshiz.blocks.BlockCombinationGearbox;
import net.wuerfel21.derpyshiz.blocks.RotaryHousing;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxCombination;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxDecoupling;

public class RenderGearboxDecoupling extends TileEntitySpecialRenderer {

	public RenderGearboxDecoupling() {
		super();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tiletemp, double x, double y, double z, float f) {
		TileEntityGearboxDecoupling tile = (TileEntityGearboxDecoupling) tiletemp;
		this.bindTexture(TextureMap.locationBlocksTexture);
		
		int meta = tile.getTier();
		
		IIcon[] textures = getIcons(meta);

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		tessellator.startDrawingQuads();
		if (Main.fancyGearbox) {
			RenderRotaryComponent.render(tessellator, textures);
		} else {
			// do nothing, ugly gearboxes are rendered by BlockGearbox
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
			return new IIcon[] {Blocks.coal_block.getIcon(0, 0),Blocks.stained_hardened_clay.getIcon(0, 5),Blocks.planks.getIcon(0, 3)};
		case 1:
			return new IIcon[] {Blocks.obsidian.getIcon(0, 0),Blocks.emerald_block.getIcon(0, 0),Blocks.redstone_block.getIcon(0, 0)};
		}
	}

}
