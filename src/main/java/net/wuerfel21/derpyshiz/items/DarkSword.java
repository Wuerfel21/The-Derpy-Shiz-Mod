package net.wuerfel21.derpyshiz.items;

import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.client.BlankIcon;

public class DarkSword extends DerpySword {
		
	public DarkSword(ToolMaterial material, Item rep, int m) {
		super(material,rep,m);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase dest, EntityLivingBase src) {
		dest.addPotionEffect(new PotionEffect(15,200,1));
		return super.hitEntity(stack, dest, src);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int p_77663_4_, boolean inHand) {
		super.onUpdate(stack,worldIn,entityIn,p_77663_4_,inHand);
		if (inHand && entityIn instanceof EntityLivingBase) {
			EntityLivingBase entity = (EntityLivingBase) entityIn;
			entity.addPotionEffect(new PotionEffect(3,2,0,true));
		}
	}
	
}
