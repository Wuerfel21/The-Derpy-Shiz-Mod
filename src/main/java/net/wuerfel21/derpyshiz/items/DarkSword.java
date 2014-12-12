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

public class DarkSword extends DerpySword {
	
	private IIcon[] icons = new IIcon[2];
	private BufferedImage[] buf = {new BufferedImage(1,1,BufferedImage.TYPE_4BYTE_ABGR)};
	
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
	
	@Override
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		return icons[player.isInvisible()?1:0];
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[0];
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon("derpyshiz:sword_darkness");
		buf[0].setRGB(0, 0, 0);
		TextureAtlasSprite sprite = hackSprite("sword_darkness_gone");
		if (sprite==null) {System.out.println("SUPER UEBER MEGA ULTRA FATAL ERROR!!!!!!!! DARK SWORD ICON HACKERY FAILED!!!!"); icons[1] = icons[0];return;}
		List list = new LinkedList();
		list.add(new AnimationFrame(0));
		sprite.loadSprite(buf, new AnimationMetadataSection(list,1,1,-1), false);
	}
	
	private TextureAtlasSprite hackSprite(String s) {
		try {
			Constructor[] cons = TextureAtlasSprite.class.getDeclaredConstructors();
			cons[0].setAccessible(true);
			return (TextureAtlasSprite) cons[0].newInstance(s);
		} catch (Exception e) {
			return null;
		}
	}
	
}
