package net.wuerfel21.derpyshiz.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class DerpyArmor extends ItemArmor {
	
	public String armorTexture;
	public Item repair;
	public int meta;
	
	public DerpyArmor(ArmorMaterial am, int type, Item i, int m, String at) {
		super(am,0,type);
		this.armorTexture = at;
		this.repair = i;
		this.meta = m;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack material) {
		return material.getItem() == repair && material.getItemDamage() == meta;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
	    return "derpyshiz:armor/" + this.armorTexture + "_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
	
}
