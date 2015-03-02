package net.wuerfel21.derpyshiz.items;

import java.util.HashSet;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.server.MinecraftServer;
import net.wuerfel21.derpyshiz.IModeItem;
import net.wuerfel21.derpyshiz.ItemModeHelper;

public class AdminHammer extends ItemTool implements IModeItem {

	public AdminHammer(ToolMaterial m) {
		super(1f, m, new HashSet());
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (entity instanceof EntityPlayer) {
			if (!player.worldObj.isRemote) {
				EntityPlayer target = (EntityPlayer) entity;
				EntityPlayerMP root = (EntityPlayerMP) player;
				switch (ItemModeHelper.getMode(stack)) {
				default:
				case 0: // kick
					MinecraftServer.getServer().getCommandManager().executeCommand(player, "kick "+target.getGameProfile().getName());
					break;
				case 1: // ban
					MinecraftServer.getServer().getCommandManager().executeCommand(player, "ban "+target.getGameProfile().getName());
					break;
				case 2: // ip ban
					MinecraftServer.getServer().getCommandManager().executeCommand(player, "ip-ban "+target.getGameProfile().getName());
					break;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hasEffect(ItemStack stack, int pass) {
		return true;
	}
	
	@Override
	public int getNumModes() {
		return 3;
	}

	@Override
	public String getModeName(int index) {
		return modes[index];
	}

	public static final String[] modes = { "mode.derpyshiz.admin_kick.name", "mode.derpyshiz.admin_ban.name", "mode.derpyshiz.admin_ipban.name" };

}
