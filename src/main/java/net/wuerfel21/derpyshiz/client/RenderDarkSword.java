package net.wuerfel21.derpyshiz.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public class RenderDarkSword implements IItemRenderer {
	
	public RenderDarkSword() {
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
		if (type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			if (!(data[1] instanceof EntityLivingBase) || ((EntityLivingBase)data[1]).isInvisible()) {
				return;
			}
		}
		IIcon icon = stack.getIconIndex();
		DerpyRenderHelper.renderItem(stack, 0);
	}

}
