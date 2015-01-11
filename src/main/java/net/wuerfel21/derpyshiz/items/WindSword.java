package net.wuerfel21.derpyshiz.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.IModeItem;
import net.wuerfel21.derpyshiz.ISpecialActionItem;
import net.wuerfel21.derpyshiz.ItemModeHelper;

public class WindSword extends DerpySword implements IModeItem, ISpecialActionItem {

	public WindSword(ToolMaterial material, Item rep, int m) {
		super(material, rep, m);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int lawl, boolean inHand) {
		if (inHand && entity instanceof EntityLivingBase) {
			switch (ItemModeHelper.getMode(stack)) {
				default:
				case 0:
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(1, 2, 0, true));
					break;
				case 1:
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(8, 2, 0, true));
					break;
			}
		}
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (entity instanceof EntityBlaze || entity instanceof EntityChicken || entity instanceof EntityBat || entity instanceof EntityGhast || entity instanceof EntityDragon || entity instanceof EntityWither || entity instanceof EntityFireball) {
			return true;
		}
		if (!entity.worldObj.isRemote) {
			entity.motionY += 2;
		}
		return false;
	}

	@Override
	public void specialAction(ItemStack stack, EntityPlayer player, boolean isClient) {
		if (player.onGround) {
			player.motionY += 2;
		}
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
		ItemModeHelper.displayMode(stack, list);
	}

	@Override
	public int getNumModes() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String getModeName(int index) {
		// TODO Auto-generated method stub
		return modes[index];
	}

	public static final String[] modes = { "mode.speed.name", "mode.jump.name" };

}
