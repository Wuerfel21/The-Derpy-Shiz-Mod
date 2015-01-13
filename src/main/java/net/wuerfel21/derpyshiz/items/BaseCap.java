package net.wuerfel21.derpyshiz.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.client.ModelCap;
import net.wuerfel21.derpyshiz.client.ModelTopHat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseCap extends DerpyArmor {
	
	@SideOnly(Side.CLIENT)
	public ModelCap model = new ModelCap(1f);
	
	public BaseCap(ArmorMaterial am, Item i, int m, String type) {
		super(am, 0, i, m, type);
		this.setUnlocalizedName("cap_"+type);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setTextureName("derpyshiz:cap_"+type);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
		return model;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "derpyshiz:armor/cap_" + this.armorTexture + ".png";
	}
	
}
