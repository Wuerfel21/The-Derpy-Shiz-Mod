package net.wuerfel21.derpyshiz.items;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class Diesieben07 extends Item {
	
	public Diesieben07() {
		this.setTextureName("derpyshiz:diesieben07");
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setUnlocalizedName("diesieben07");
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int wtf, boolean inHand) {
		if (inHand && entity instanceof EntityLivingBase) {
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(11, 2, 0, true));
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean wtf) {
		list.add(ChatFormatting.ITALIC+StatCollector.translateToLocal("info.diesieben07.name"));
	}
	
}
