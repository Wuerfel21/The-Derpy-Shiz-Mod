package net.wuerfel21.derpyshiz;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.wuerfel21.derpyshiz.events.SpecialActionEvent;
import net.wuerfel21.derpyshiz.network.ItemModeMessage;
import net.wuerfel21.derpyshiz.network.SpecialActionMessage;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DerpyKeys {

	public static KeyBinding mode = new KeyBinding("key.derpy_mode.name", Keyboard.KEY_J, "key.categories.gameplay");
	public static KeyBinding action = new KeyBinding("key.derpy_action.name", Keyboard.KEY_F, "key.categories.gameplay");

	public static void register() {
		DerpyKeys derp = new DerpyKeys();
		ClientRegistry.registerKeyBinding(mode);
		ClientRegistry.registerKeyBinding(action);
		FMLCommonHandler.instance().bus().register(derp);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onKeyPressed(InputEvent.KeyInputEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if (player == null)
			return;
		if (mode.isPressed()) {
			Main.derpnet.sendToServer(new ItemModeMessage());
		}
		if (action.isPressed()) {
			Main.derpnet.sendToServer(new SpecialActionMessage());
			if (!MinecraftForge.EVENT_BUS.post(new SpecialActionEvent(player))) {
				ItemStack stack = player.getHeldItem();
				if (stack != null && stack.getItem() instanceof ISpecialActionItem) {
					((ISpecialActionItem) stack.getItem()).specialAction(stack, player, true);
				}
			}
		}
	}

}
