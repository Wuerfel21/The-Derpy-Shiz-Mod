package net.wuerfel21.derpyshiz.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.wuerfel21.derpyshiz.IModeItem;
import net.wuerfel21.derpyshiz.ISpecialActionItem;
import net.wuerfel21.derpyshiz.ItemModeHelper;
import net.wuerfel21.derpyshiz.events.SpecialActionEvent;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SpecialActionMessage implements IMessage {

	public SpecialActionMessage() {
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub

	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub

	}
	
	public static class Handler implements IMessageHandler<SpecialActionMessage, IMessage> {

		@Override
		public IMessage onMessage(SpecialActionMessage message, MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			if (MinecraftForge.EVENT_BUS.post(new SpecialActionEvent(player))) {
				return null;
			}
			if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ISpecialActionItem) {
				((ISpecialActionItem)player.getHeldItem().getItem()).specialAction(player.getHeldItem(), player, false);
			}
			return null;
		}

	}
}
