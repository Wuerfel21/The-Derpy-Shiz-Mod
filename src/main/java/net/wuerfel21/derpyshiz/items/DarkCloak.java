package net.wuerfel21.derpyshiz.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class DarkCloak extends DerpyArmor {
	
	public DarkCloak(ArmorMaterial am, int type, Item i, int m) {
		super(am,type,i,m,"dark");
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		super.onArmorTick(world, player, itemStack);
		if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof DarkSword) {
			player.addPotionEffect(new PotionEffect(14,2,0,true));
		}
		player.addPotionEffect(new PotionEffect(1,2,0,true));
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
	    if(entity instanceof EntityLivingBase) {
	    	EntityLivingBase entityl = (EntityLivingBase)entity;
	    	if (entityl.isInvisible()) {
	    		return "derpyshiz:armor/null.png";
	    	}
	    }
	    return "derpyshiz:armor/dark_cloak.png";
	}
	
}
