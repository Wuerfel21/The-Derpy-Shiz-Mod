package net.wuerfel21.derpyshiz.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemTDM extends Item {
	
	public ItemTDM() {
		this.setTextureName("derpyshiz:tdm");
		this.setCreativeTab(CreativeTabs.tabTransport);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("derpyshiz.tdm");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer player) {
		String s = "[Wuerfel_21] ";
		if (player.getGameProfile().getName().equals("DanTDM")) {
			s += "Well, sorry, i needed to focus on the rotary stuff. BTW best way of putting messages == NYI message. Anyways, this item currently has no function! Greet Trayaurus!";
		} else {
			s += "Go away, this is not yet implemented!";
		}
		player.addChatComponentMessage(new ChatComponentText(s));
		return super.onItemRightClick(p_77659_1_, p_77659_2_, player);
	}
	
}
