package net.wuerfel21.derpyshiz.items;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.DerpyItems;
import net.wuerfel21.derpyshiz.Main;

public class ArmorWater extends DerpyArmor {

	public ArmorWater(ArmorMaterial am, int type, Item i, int m, String at) {
		super(am, type, i, m, at);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		super.onArmorTick(world, player, stack);
		if (world.isMaterialInBB(player.boundingBox.expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.lava)) {
			player.setCurrentItemOrArmor(Main.armorTypeToSlot[this.armorType], new ItemStack(DerpyItems.stoneArmor[this.armorType], 1, (stack.getItemDamage() - (stack.getItemDamage() % 3)) / 3));
			world.playSoundEffect(player.posX, player.posY, player.posZ, "random.fizz", 1f, world.rand.nextFloat() * 0.1f + 0.9f);
		}
		if (player.isInWater()) {
			switch (this.armorType) {
			default:
			case 0:
				player.addPotionEffect(new PotionEffect(16, 2, 0, true));
				break;
			case 1:
				player.addPotionEffect(new PotionEffect(13, 2, 0, true));
				break;
			case 2:
				player.addPotionEffect(new PotionEffect(10, 2, 0, true));
				break;
			case 3:
				player.addPotionEffect(new PotionEffect(5, 2, 0, true));
				break;
			}
		}

	}

}
