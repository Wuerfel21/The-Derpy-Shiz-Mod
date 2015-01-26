package net.wuerfel21.derpyshiz.items;

import java.util.List;

import scala.reflect.internal.Trees.New;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author based off
 *         http://jabelarminecraft.blogspot.de/p/minecraft-forge-1721710
 *         -creating-custom.html
 *
 */
public class DerpySpawnEgg extends ItemMonsterPlacer implements IBehaviorDispenseItem {
	@SideOnly(Side.CLIENT)
	private IIcon theIcon;

	public DerpySpawnEgg() {
		super();
		this.setUnlocalizedName("monsterPlacer");
		this.setTextureName("spawn_egg");
		setHasSubtypes(false);
		maxStackSize = 64;
		setCreativeTab(CreativeTabs.tabMisc);
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, this);
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par3World.isRemote) {
			return true;
		} else {
			Block block = par3World.getBlock(par4, par5, par6);
			par4 += Facing.offsetsXForSide[par7];
			par5 += Facing.offsetsYForSide[par7];
			par6 += Facing.offsetsZForSide[par7];
			double d0 = 0.0D;

			if (par7 == 1 && block.getRenderType() == 11) {
				d0 = 0.5D;
			}

			Entity entity = spawnEntity(par3World, par4 + 0.5D, par5 + d0, par6 + 0.5D, stack);

			if (entity != null) {
				if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
					((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());
				}

				if (!par2EntityPlayer.capabilities.isCreativeMode) {
					--stack.stackSize;
				}
			}

			return true;
		}
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer) {
		if (par2World.isRemote) {
			return stack;
		} else {
			MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

			if (movingobjectposition == null) {
				return stack;
			} else {
				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					int i = movingobjectposition.blockX;
					int j = movingobjectposition.blockY;
					int k = movingobjectposition.blockZ;

					if (!par2World.canMineBlock(par3EntityPlayer, i, j, k)) {
						return stack;
					}

					if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack)) {
						return stack;
					}

					if (par2World.getBlock(i, j, k) instanceof BlockLiquid) {
						Entity entity = spawnEntity(par2World, i, j, k, stack);

						if (entity != null) {
							if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
								((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());
							}

							if (!par3EntityPlayer.capabilities.isCreativeMode) {
								--stack.stackSize;
							}
						}
					}
				}

				return stack;
			}
		}
	}

	/**
	 * Spawns the creature specified by the egg's type in the location specified
	 * by the last three parameters. Parameters: world, entityID, x, y, z.
	 */
	public Entity spawnEntity(World parWorld, double parX, double parY, double parZ, ItemStack stack) {

		if (!parWorld.isRemote) // never spawn entity on client side
		{
			if (EntityList.stringToClassMapping.containsKey(stack.getTagCompound().getString("entity"))) {
				EntityLiving entityToSpawn = (EntityLiving) EntityList.createEntityByName(stack.getTagCompound().getString("entity"), parWorld);
				entityToSpawn.setLocationAndAngles(parX, parY, parZ, MathHelper.wrapAngleTo180_float(parWorld.rand.nextFloat() * 360.0F), 0.0F);
				parWorld.spawnEntityInWorld(entityToSpawn);
				entityToSpawn.onSpawnWithEgg((IEntityLivingData) null);
				entityToSpawn.playLivingSound();
				return entityToSpawn;
			}
		}

		return null;
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye
	 * returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs parTab, List list) {
		for (int i = 0; i < entities.length; i++) {
			ItemStack stack = new ItemStack(item, 1, 0);
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("entity", entities[i]);
			tag.setInteger("base", bases[i]);
			tag.setInteger("spot", spots[i]);
			stack.setTagCompound(tag);
			list.add(stack);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int parColorType) {
		return (parColorType == 0) ? stack.getTagCompound().getInteger("base") : stack.getTagCompound().getInteger("spot");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	// Doing this override means that there is no localization for language
	// unless you specifically check for localization here and convert
	public String getItemStackDisplayName(ItemStack stack) {
		return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name") + " " + StatCollector.translateToLocal("entity." + stack.getTagCompound().getString("entity") + ".name");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		super.registerIcons(reg);
		theIcon = reg.registerIcon(getIconString() + "_overlay");
	}

	/**
	 * Gets an icon index based on an item's damage value and the given render
	 * pass
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int parDamageVal, int parRenderPass) {
		return parRenderPass > 0 ? theIcon : super.getIconFromDamageForRenderPass(parDamageVal, parRenderPass);
	}

	public static final String[] entities = { "derpyshiz.piggycorn" };
	public static final int[] bases = { 15771042 };
	public static final int[] spots = { 14377823 };

	@Override
	public ItemStack dispense(IBlockSource src, ItemStack stack) {
		ForgeDirection dir = ForgeDirection.getOrientation(src.getBlockMetadata()&7);
		this.spawnEntity(src.getWorld(), src.getX()+dir.offsetX, src.getY()+dir.offsetY, src.getZ()+dir.offsetZ, stack);
		ItemStack tmp = stack.copy();
		tmp.stackSize--;
		return tmp;

	}

}