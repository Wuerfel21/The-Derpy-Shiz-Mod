package net.wuerfel21.derpyshiz.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class RenderBigItem implements IItemRenderer {

	public static final float ip = 0.0625F;
	
	public RenderBigItem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
		GL11.glPushMatrix();
		if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glTranslatef(-0.7F, -0.25F, 0.0F);
		} else {
			GL11.glTranslatef(-1.0F-(ip*0), -0.25F+(ip*5), 0.01F);
		}
		GL11.glScaled(2, 2, 1);
		IIcon icon = stack.getIconIndex();
		ItemRenderer.renderItemIn2D(Tessellator.instance, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), ip);
		GL11.glPopMatrix();
	}

}
