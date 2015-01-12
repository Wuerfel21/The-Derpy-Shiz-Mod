package net.wuerfel21.derpyshiz.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.DerpyItems;
import net.wuerfel21.derpyshiz.IModeItem;
import net.wuerfel21.derpyshiz.ISpecialActionItem;
import net.wuerfel21.derpyshiz.ItemModeHelper;
import net.wuerfel21.derpyshiz.Main;

public class WaterSword extends DerpySword implements IModeItem, ISpecialActionItem {

	public WaterSword(ToolMaterial material, Item rep, int m) {
		super(material, rep, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean inHand) {
		if (inHand && entity.isInWater() && entity instanceof EntityLivingBase) {
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(13, 2, 0 ,true));
		}
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(2, 60, ItemModeHelper.getMode(stack) == 0 ? 0 : 127));
			if (ItemModeHelper.getMode(stack) == 1) {
				DerpyItems.damageItem(stack, 2, (EntityLivingBase) entity);
			}
		}
		return false;
	}

	@Override
	public void specialAction(ItemStack stack, EntityPlayer player, boolean isClient) {
		if (isClient) {
			return;
		}
		if (player.isInWater()) {
			int x = (int) Math.floor(player.posX);
			int y = (int) Math.floor(player.posY);
			int z = (int) Math.floor(player.posZ);
			double xoff = player.posX - x;
			double zoff = player.posZ - z;
			int blockcnt = Main.maxWaterswordDistance;
			boolean down = player.isBlocking();
			boolean flag = false;
			if (!down) {
				while (!flag) {
					if (blockcnt == 0) {
						return;
					}
					if (player.worldObj.isAirBlock(x, y, z)) {
						player.setPositionAndUpdate(x+xoff, y, z+zoff);
						flag = true;
					} else {
						if (player.worldObj.getBlock(x, y, z) != Blocks.flowing_water && player.worldObj.getBlock(x, y, z) != Blocks.water) {
							return;
						}
						y++;
						blockcnt--;
					}
				}
			} else {
				while (!flag) {
					if (blockcnt == 0) {
						return;
					}
					if (player.worldObj.getBlock(x, y, z) != Blocks.flowing_water && player.worldObj.getBlock(x, y, z) != Blocks.water) {
						player.setPositionAndUpdate(x+xoff, y+1, z+zoff);
						flag = true;
					} else {
						y--;
						blockcnt--;
					}
				}
			}
			player.worldObj.playSoundAtEntity(player, "random.splash", 1f, player.getRNG().nextFloat() * 0.1f + 0.9f);
			DerpyItems.damageItem(stack, (int) Math.ceil(((double) Main.maxWaterswordDistance - blockcnt) / (double) 8), player);
		}
	}

	@Override
	public int getNumModes() {
		return 2;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
		ItemModeHelper.displayMode(stack, list);
	}

	@Override
	public String getModeName(int index) {
		return modes[index];
	}

	public static final String[] modes = { "mode.watersword_normal.name", "mode.watersword_freeze.name" };

}
