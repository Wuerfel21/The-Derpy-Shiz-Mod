package net.wuerfel21.derpyshiz.items;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.wuerfel21.derpyshiz.DerpyItems;
import net.wuerfel21.derpyshiz.IModeItem;
import net.wuerfel21.derpyshiz.ISpecialActionItem;
import net.wuerfel21.derpyshiz.ItemModeHelper;

import com.google.common.collect.Multimap;

public class FireSword extends DerpySword implements IModeItem, ISpecialActionItem {

	public static final UUID someUUID = UUID.fromString("deadbeef-b1ef-4632-874a-87a039dba888");

	public FireSword(ToolMaterial material, Item rep, int m) {
		super(material, rep, m);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (player.worldObj.isRemote)
			return false;
		if (player.isWet()) {
			return true;
		} else {
			if (ItemModeHelper.getMode(stack) == 1) {
				entity.setFire(20);
				DerpyItems.damageItem(stack, 2, player);
			} else {
				entity.setFire(4);
			}
			return false;
		}
	}

	@Override
	public Multimap getAttributeModifiers(ItemStack stack) {
		Multimap map = super.getAttributeModifiers(stack);
		if (ItemModeHelper.getMode(stack) == 1) {
			map.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(someUUID, "Weapon modifier", 1.5, 0));
		}
		return map;
	}

	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		ItemModeHelper.displayMode(p_77624_1_, p_77624_3_);
	}

	@Override
	public int getNumModes() {
		return 2;
	}

	@Override
	public void specialAction(ItemStack stack, EntityPlayer player, boolean isClient) {
		if (isClient) {
			return;
		}
		int cost = player.dimension == -1 ? 0 : 3;
		if (!player.isWet()) {
			Vec3 vec = player.getLook(0.3f);
			EntitySmallFireball ball = new EntitySmallFireball(player.worldObj, player, vec.xCoord, vec.yCoord, vec.zCoord);
			ball.posY += player.eyeHeight;
			ball.accelerationX = vec.xCoord;
			ball.accelerationY = vec.yCoord;
			ball.accelerationZ = vec.zCoord;
			ball.shootingEntity = player;
			player.worldObj.spawnEntityInWorld(ball);
			player.worldObj.playSoundAtEntity(player, "mob.ghast.fireball", 1f, player.getRNG().nextFloat() * 0.1f + 0.9f);
			DerpyItems.damageItem(stack, 3, player);
		}
	}

	@Override
	public String getModeName(int index) {
		// TODO Auto-generated method stub
		return modes[index];
	}

	public static final String[] modes = { "mode.derpyshiz.firesword_normal.name", "mode.derpyshiz.firesword_hell.name" };

}
