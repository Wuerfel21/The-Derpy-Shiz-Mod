package net.wuerfel21.derpyshiz.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RainbowCookie extends ItemFood {
	
	public RainbowCookie() {
		super(4,0.5f,false);
		this.setTextureName("derpyshiz:rainbow_cookie");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setUnlocalizedName("rainbow_cookie");
	}
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(1,0,20));
		return super.onEaten(stack, world, player);
	}

}
