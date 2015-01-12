package net.wuerfel21.derpyshiz.items;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.DerpyBlocks;
import net.wuerfel21.derpyshiz.DerpyItems;
import net.wuerfel21.derpyshiz.DerpyRegistry;
import net.wuerfel21.derpyshiz.IModeItem;
import net.wuerfel21.derpyshiz.ISpecialActionItem;
import net.wuerfel21.derpyshiz.ItemModeHelper;
import net.wuerfel21.derpyshiz.blocks.DerpyOres;

public class NaturalSword extends DerpySword implements IModeItem, ISpecialActionItem {

	public NaturalSword(ToolMaterial material, Item rep, int m) {
		super(material, rep, m);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (entity instanceof EntityAnimal || entity instanceof EntitySquid) {
			return true;
		}
		if (entity instanceof EntityLivingBase) {
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(2, 180));
		}
		return false;
	}

	@Override
	public void specialAction(ItemStack stack, EntityPlayer player, boolean isClient) {
		if (isClient || !player.onGround || player.isInWater() || player.isOnLadder()) {
			return;
		}
		int blocksGenerated = 0;
		boolean blocking = player.isBlocking();
		int blockX = (int) Math.floor(player.posX);
		int blockY = (int) Math.floor(player.posY);
		int blockZ = (int) Math.floor(player.posZ);
		int playerDir = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		DerpyRegistry.BasicBlockEntry leafType = DerpyRegistry.leafTypes.get(player.getRNG().nextInt(DerpyRegistry.leafTypes.size()));
		int unnaturalBlocks = 0;
		int totalBlocks = 0;
		for (int i = blockX - 10; i < blockX + 10; i++) {
			for (int j = blockZ - 10; j < blockZ + 10; j++) {
				for (int k = blockY - 5; k < blockY + 5; k++) {
					if (!player.worldObj.isAirBlock(i, k, j)) {
						Block block = player.worldObj.getBlock(i, k, j);
						Material m = block.getMaterial();
						if (m == Material.cactus || m == Material.grass || m == Material.ground || m == Material.leaves || m == Material.web || m == Material.wood || m == Material.plants) {
							//lawl nothing
						} else {
							unnaturalBlocks++;
						}
						totalBlocks++;
					}
				}
			}
		}
		switch (ItemModeHelper.getMode(stack)) {
		default:
		case 0:
			blocksGenerated += genLeafWall(player.worldObj, blockX, blockY, blockZ, leafType, playerDir, 2);
			if (blocking) {
				blocksGenerated += genLeafWall(player.worldObj, blockX + xOff[playerDir], blockY + 1, blockZ + zOff[playerDir], leafType, playerDir, 1);
			}
			break;
		case 1:
			blocksGenerated += genLeafWall(player.worldObj, blockX, blockY, blockZ, leafType, revDir[playerDir], 2);
			if (blocking) {
				blocksGenerated += genLeafWall(player.worldObj, blockX + xOff[revDir[playerDir]], blockY + 1, blockZ + zOff[revDir[playerDir]], leafType, revDir[playerDir], 1);
			}
			break;
		case 2:
			for (int d = 0; d < 4; d++) {
				blocksGenerated += genLeafWall(player.worldObj, blockX, blockY, blockZ, leafType, d, 2);
			}
			if (blocking) {
				blocksGenerated += genLeafWall(player.worldObj, blockX, blockY + 2, blockZ, leafType, 4, 1);
			}
			break;
		}
		if (blocksGenerated < 1) {
			return;
		}
		if (totalBlocks == 0) {
			return;
		}
		double rawMulti = (double)unnaturalBlocks / (double)totalBlocks;
		double multi = rawMulti * 0.9 + 0.1;
		double damage = ((double)blocksGenerated / 1.5d) * multi;
		DerpyItems.damageItem(stack, (int)Math.ceil(damage), player);
		player.worldObj.playSoundAtEntity(player, "dig.grass", 1f, player.getRNG().nextFloat() * 0.1f + 0.9f);
	}

	public static int genLeafWall(World world, int blockX, int blockY, int blockZ, DerpyRegistry.BasicBlockEntry block, int dir, int height) {
		int blocksGenerated = 0;
		int minX;
		int maxX;
		int minZ;
		int maxZ;
		switch (dir) {
		default:
		case 0:
			minZ = blockZ + 2;
			maxZ = blockZ + 2;
			minX = blockX - 2;
			maxX = blockX + 2;
			break;
		case 1:
			minZ = blockZ - 2;
			maxZ = blockZ + 2;
			minX = blockX - 2;
			maxX = blockX - 2;
			break;
		case 2:
			minZ = blockZ - 2;
			maxZ = blockZ - 2;
			minX = blockX - 2;
			maxX = blockX + 2;
			break;
		case 3:
			minZ = blockZ - 2;
			maxZ = blockZ + 2;
			minX = blockX + 2;
			maxX = blockX + 2;
			break;
		case 4:
			minZ = blockZ - 2;
			maxZ = blockZ + 2;
			minX = blockX - 2;
			maxX = blockX + 2;
			break;
		}
		for (int x = minX; x <= maxX; x++) {
			for (int z = minZ; z <= maxZ; z++) {
				for (int y = blockY; y < blockY + height; y++) {
					if (genLeafBlock(world, x, y, z, block))
						blocksGenerated++;
				}
			}
		}
		return blocksGenerated;
	}

	public static boolean genLeafBlock(World world, int x, int y, int z, DerpyRegistry.BasicBlockEntry block) {
		if (world.blockExists(x, y, z) && world.isAirBlock(x, y, z)) {
			System.out.println("Generating Leaf at X:" + x + " Y:" + y + " Z:" + z);
			world.setBlock(x, y, z, block.block, block.meta | 8, 2);
			return true;
		}
		return false;
	}

	public static void registerLeafTypes() {
		List<DerpyRegistry.BasicBlockEntry> lt = DerpyRegistry.leafTypes;
		lt.add(new DerpyRegistry.BasicBlockEntry(Blocks.leaves, 0));
		lt.add(new DerpyRegistry.BasicBlockEntry(Blocks.leaves, 1));
		lt.add(new DerpyRegistry.BasicBlockEntry(Blocks.leaves, 2));
		lt.add(new DerpyRegistry.BasicBlockEntry(Blocks.leaves, 3));
		lt.add(new DerpyRegistry.BasicBlockEntry(Blocks.leaves2, 0));
		lt.add(new DerpyRegistry.BasicBlockEntry(Blocks.leaves2, 1));
		lt.add(new DerpyRegistry.BasicBlockEntry(DerpyBlocks.leaves, 0));
		lt.add(new DerpyRegistry.BasicBlockEntry(DerpyBlocks.leaves, 1));
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
		ItemModeHelper.displayMode(stack, list);
	}

	@Override
	public int getNumModes() {
		return 3;
	}

	@Override
	public String getModeName(int index) {
		return modes[index];
	}

	public static final int[] xOff = { 0, -1, 0, 1 };
	public static final int[] zOff = { 1, 0, -1, 0 };
	public static final int[] revDir = { 2, 3, 0, 1 };
	public static final String[] modes = { "mode.naturalsword_front.name", "mode.naturalsword_back.name", "mode.naturalsword_all.name" };

}
