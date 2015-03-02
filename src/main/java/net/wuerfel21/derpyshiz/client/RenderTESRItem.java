package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import net.wuerfel21.derpyshiz.rotary.ITieredTE;

import org.lwjgl.opengl.GL11;

public class RenderTESRItem implements IItemRenderer {

	public TileEntitySpecialRenderer render;

	public TileEntity entity;

	public RenderTESRItem(TileEntitySpecialRenderer render, TileEntity entity) {
		this.entity = entity;
		this.render = render;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (this.entity instanceof ITieredTE) {
			((ITieredTE) this.entity).setTier(item.getItemDamage());
		}
		if (type == IItemRenderer.ItemRenderType.ENTITY) {
			GL11.glTranslatef(-0.5F, -0.5f, -0.5F);
		}
		if (type == IItemRenderer.ItemRenderType.INVENTORY) {
			GL11.glTranslatef(0, -0.1f, 0);
		}
		this.render.renderTileEntityAt(this.entity, 0.0D, 0.0D, 0.0D, 0.0F);
	}

}
