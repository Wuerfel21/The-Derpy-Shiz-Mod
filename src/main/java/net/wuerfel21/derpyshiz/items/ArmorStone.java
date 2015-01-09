package net.wuerfel21.derpyshiz.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ArmorStone extends DerpyArmor {

	public ArmorStone(ArmorMaterial am, int type, Item i, int m, String at) {
		super(am, type, i, m, at);
	}

	public static void addSlowness(EntityPlayer player) {
		int cnt = 0;
		for (int i = 1; i <= 2; i++) {
			if (player.getEquipmentInSlot(i) != null)
				if (player.getEquipmentInSlot(i).getItem() instanceof ArmorStone)
					cnt++;
		}
		if (cnt != 0) {
			player.addPotionEffect(new PotionEffect(2, 2, cnt-1, true));
		}
	}
	
	public static void addFatigue(EntityPlayer player) {
		int cnt = 0;
		for (int i = 3; i <= 4; i++) {
			if (player.getEquipmentInSlot(i) != null)
				if (player.getEquipmentInSlot(i).getItem() instanceof ArmorStone)
					cnt++;
		}
		if (cnt != 0) {
			player.addPotionEffect(new PotionEffect(4, 2, cnt-1, true));
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		super.onArmorTick(world, player, itemStack);
		if (player.isAirBorne)
			player.addVelocity(0, -0.01, 0);
		if (player.isInWater()) {
			player.addVelocity(0, -0.005, 0);
		}
		if (player.isSprinting()) {
			player.setSprinting(false);
		}
		addSlowness(player);
		addFatigue(player);
	}

}
