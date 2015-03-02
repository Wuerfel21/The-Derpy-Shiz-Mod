package net.wuerfel21.derpyshiz.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.wuerfel21.derpyshiz.DerpyItems;

public class ItemFirechargeLauncher extends Item {

	public ItemFirechargeLauncher() {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(384);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setFull3D();
		this.setTextureName("derpyshiz:firecharge_launcher");
		this.setUnlocalizedName("derpyshiz.firecharge_launcher");
	}

	public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
		return p_77654_1_;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count) {
		if (world.isRemote || (72000-count) < 20)
			return;
		if (player.capabilities.isCreativeMode || player.inventory.hasItem(Items.fire_charge)) {
			Vec3 vec = player.getLook(0.3f);
			EntityLargeFireball ball = new EntityLargeFireball(player.worldObj, player.posX, player.posY + player.eyeHeight, player.posZ, vec.xCoord, vec.yCoord, vec.zCoord);
			ball.shootingEntity = player;
			player.worldObj.spawnEntityInWorld(ball);
			player.worldObj.playSoundAtEntity(player, "mob.ghast.fireball", 1f, player.getRNG().nextFloat() * 0.1f + 0.9f);
			DerpyItems.damageItem(stack, 1, player);
		}
	}
	
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.bow;
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (player.capabilities.isCreativeMode || player.inventory.hasItem(Items.fire_charge)) {
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		}

		return stack;
	}

}
