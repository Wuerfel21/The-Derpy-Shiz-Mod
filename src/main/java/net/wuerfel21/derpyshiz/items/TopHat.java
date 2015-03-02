package net.wuerfel21.derpyshiz.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.client.ModelTopHat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TopHat extends DerpyArmor {

	public ModelTopHat model;
	
	public TopHat(ArmorMaterial am, Item i, int m, String type) {
		super(am, 0, i, m, type);
		this.setUnlocalizedName("derpyshiz.tophat_"+type);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setTextureName("derpyshiz:tophat_"+type);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
		if (model == null) {
			model = new ModelTopHat(1f);
		}
		return model;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "derpyshiz:armor/tophat_" + this.armorTexture + ".png";
	}

}
