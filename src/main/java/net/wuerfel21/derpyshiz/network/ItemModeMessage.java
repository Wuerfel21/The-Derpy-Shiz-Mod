package net.wuerfel21.derpyshiz.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.wuerfel21.derpyshiz.IModeItem;
import net.wuerfel21.derpyshiz.ItemModeHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ItemModeMessage implements IMessage {

	public ItemModeMessage() {
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class Handler implements IMessageHandler<ItemModeMessage, IMessage> {

		@Override
		public IMessage onMessage(ItemModeMessage message, MessageContext ctx) {
			ItemStack stack = ctx.getServerHandler().playerEntity.getHeldItem();
			if (!(stack.getItem() instanceof IModeItem)) return null;
			ItemModeHelper.setMode(stack, ItemModeHelper.getMode(stack)+1);
			ctx.getServerHandler().playerEntity.addChatMessage(new ChatComponentText("[")
				.appendSibling(new ChatComponentTranslation(stack.getUnlocalizedName()+".name")
				.appendText("] ")
				.appendSibling(new ChatComponentTranslation("message.mode.name"))
				.appendSibling(new ChatComponentTranslation(((IModeItem)stack.getItem()).getModeName(ItemModeHelper.getMode(stack))))));
			return null;
		}

	}

}
