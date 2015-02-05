package net.wuerfel21.derpyshiz;

import java.io.InputStream;
import java.net.URL;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.wuerfel21.derpyshiz.blocks.DerpyOres;
import net.wuerfel21.derpyshiz.items.ItemRotameter;
import net.wuerfel21.derpyshiz.items.LongAxe;
import net.wuerfel21.derpyshiz.items.LongHoe;
import net.wuerfel21.derpyshiz.items.LongPickaxe;
import net.wuerfel21.derpyshiz.items.LongShovel;
import net.wuerfel21.derpyshiz.items.LongSword;
import net.wuerfel21.derpyshiz.items.WindSword;
import net.wuerfel21.derpyshiz.rotary.IRotaryInput;
import net.wuerfel21.derpyshiz.rotary.IRotaryOutput;

import org.apache.commons.io.IOUtils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class DerpyEvents {

	public static void register() {
		DerpyEvents derp = new DerpyEvents();
		MinecraftForge.EVENT_BUS.register(derp);
		FMLCommonHandler.instance().bus().register(derp);
		derp.registerShiz();
	}

	public void registerShiz() {
		dropsHand = new WoodStack[] { new WoodStack(0, 1, true), new WoodStack(1, 1, true) };
		dropsPerLevel = new WoodStack[][] { { new WoodStack(0, 2, true), new WoodStack(1, 1, true) }, { new WoodStack(0, 1, false), new WoodStack(1, 2, true) }, { new WoodStack(0, 1, false), new WoodStack(1, 3, true) }, { new WoodStack(0, 1, false), new WoodStack(1, 1, false) } };
	}

	@SubscribeEvent
	public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
		Item tool = event.entityLiving.getHeldItem() == null ? Items.rotten_flesh : event.entityLiving.getHeldItem().getItem();
		if (tool == Items.shears && event.block.getMaterial() == Material.cloth) {
			event.newSpeed = 5f;
		}
	}

	@SubscribeEvent
	public void onBlockDrop(HarvestDropsEvent event) {
		if (event.block == GameRegistry.findBlock("derpyshiz", "log")) {
			event.drops.removeAll(event.drops);
			ItemStack is = event.harvester.getHeldItem();
			if (is == null) {
				event.drops.add(this.dropsHand[event.blockMetadata % 2].get());
			} else {
				int hl = is.getItem().getHarvestLevel(is, "axe");
				System.out.println(hl);
				if (hl < 0) {
					event.drops.add(this.dropsHand[event.blockMetadata % 2].get());
					return;
				}
				if (hl > 3)
					hl = 3;
				event.drops.add(this.dropsPerLevel[hl][event.blockMetadata % 2].get());
			}
		} else if (event.block == GameRegistry.findBlock("derpyshiz", "ore") && event.blockMetadata == DerpyOres.darknessIndex) {
			if (event.world.getBlockLightValue(event.x, event.y, event.z) != 0) {
				event.drops.clear();
			}
		}
	}

	@SubscribeEvent
	public void onInteract(PlayerInteractEvent event) {
		if (event.action == event.action.RIGHT_CLICK_BLOCK && event.entityLiving.getHeldItem() != null) {
			Block block = event.world.getBlock(event.x, event.y, event.z);
			if (event.entityLiving.getHeldItem().getItem().getToolClasses(event.entityLiving.getHeldItem()).contains("ds_hammer")) {
				if (event.world.isRemote) {
					event.entityLiving.swingItem();
				} else {
					int dir = 0;
					if (ItemModeHelper.getMode(event.entityLiving.getHeldItem()) == 0) {
						dir = event.face;
					} else {
						dir = Blocks.piston.determineOrientation(event.world, event.x, event.y, event.z, event.entityLiving);
					}
					if (block instanceof ISmashable) {
						if (((ISmashable) event.world.getBlock(event.x, event.y, event.z)).smashed(event.world, event.x, event.y, event.z, dir, event.entityLiving.isSneaking()))
							return;
						;
						event.world.markBlockForUpdate(event.x, event.y, event.z);
						DerpyItems.damageItem(event.entityLiving.getHeldItem(), 1, event.entityLiving);
						event.world.playSoundEffect(event.x + 0.5d, event.y + 0.5d, event.z + 0.5d, "random.anvil_land", 1f, event.world.rand.nextFloat() * 0.1f + 0.9f);
					} else if (block instanceof BlockPistonBase) {
						int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
						int newDir = dir;
						if (newDir == (meta & 7) || (meta & 8) != 0) {
							return;
						}
						event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, newDir | (meta & 8), 3);
						event.world.markBlockForUpdate(event.x, event.y, event.z);
						DerpyItems.damageItem(event.entityLiving.getHeldItem(), 1, event.entityLiving);
						event.world.playSoundEffect(event.x + 0.5d, event.y + 0.5d, event.z + 0.5d, "random.anvil_land", 1f, event.world.rand.nextFloat() * 0.1f + 0.9f);
					} else if (block instanceof BlockQuartz) {
						int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
						int newDir = Main.orientationHelper[dir];
						if (meta < 2 || newDir + 2 == meta) {
							return;
						}
						event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, newDir + 2, 3);
						event.world.markBlockForUpdate(event.x, event.y, event.z);
						DerpyItems.damageItem(event.entityLiving.getHeldItem(), 1, event.entityLiving);
						event.world.playSoundEffect(event.x + 0.5d, event.y + 0.5d, event.z + 0.5d, "random.anvil_land", 1f, event.world.rand.nextFloat() * 0.1f + 0.9f);
					} else if (block instanceof BlockLog) {
						int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
						int newDir = Main.orientationHelper[dir];
						if (newDir << 2 == (meta & 12)) {
							return;
						}
						event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, newDir << 2 | (meta & 3), 3);
						event.world.markBlockForUpdate(event.x, event.y, event.z);
						DerpyItems.damageItem(event.entityLiving.getHeldItem(), 1, event.entityLiving);
						event.world.playSoundEffect(event.x + 0.5d, event.y + 0.5d, event.z + 0.5d, "random.anvil_land", 1f, event.world.rand.nextFloat() * 0.1f + 0.9f);
					} else if (block instanceof BlockDispenser) {
						int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
						int newDir = dir;
						if (newDir == (meta & 7)) {
							return;
						}
						event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, newDir | (meta & 8), 3);
						event.world.markBlockForUpdate(event.x, event.y, event.z);
						DerpyItems.damageItem(event.entityLiving.getHeldItem(), 1, event.entityLiving);
						event.world.playSoundEffect(event.x + 0.5d, event.y + 0.5d, event.z + 0.5d, "random.anvil_land", 1f, event.world.rand.nextFloat() * 0.1f + 0.9f);
						event.useBlock = Event.Result.DENY;
					} else if (block instanceof BlockFurnace) {
						int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
						int newDir = dir;
						if (newDir == (meta & 7) || newDir < 2) {
							return;
						}
						event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, newDir | (meta & 8), 3);
						event.world.markBlockForUpdate(event.x, event.y, event.z);
						DerpyItems.damageItem(event.entityLiving.getHeldItem(), 1, event.entityLiving);
						event.world.playSoundEffect(event.x + 0.5d, event.y + 0.5d, event.z + 0.5d, "random.anvil_land", 1f, event.world.rand.nextFloat() * 0.1f + 0.9f);
						event.useBlock = Event.Result.DENY;
					} else if (block instanceof BlockRedstoneDiode) {
						int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
						int newDir = Main.diodeHelper[dir];
						if (newDir == (meta & 3) || newDir == -1) {
							return;
						}
						event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, newDir | (meta & 12), 3);
						event.world.markBlockForUpdate(event.x, event.y, event.z);
						DerpyItems.damageItem(event.entityLiving.getHeldItem(), 1, event.entityLiving);
						event.world.playSoundEffect(event.x + 0.5d, event.y + 0.5d, event.z + 0.5d, "random.anvil_land", 1f, event.world.rand.nextFloat() * 0.1f + 0.9f);
						event.useBlock = Event.Result.DENY;
					} else if (block instanceof BlockStairs) {
						int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
						if (event.entityLiving.isSneaking()) {
							event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, meta ^ 4, 3);
						} else {
							int newDir = Main.stairHelper[dir];
							if (newDir == (meta & 3) || newDir == -1) {
								return;
							}
							event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, newDir | (meta & 12), 3);
						}
						event.world.markBlockForUpdate(event.x, event.y, event.z);
						DerpyItems.damageItem(event.entityLiving.getHeldItem(), 1, event.entityLiving);
						event.world.playSoundEffect(event.x + 0.5d, event.y + 0.5d, event.z + 0.5d, "random.anvil_land", 1f, event.world.rand.nextFloat() * 0.1f + 0.9f);
					}
				}
			} else if ((!event.world.isRemote) && event.entityLiving.getHeldItem().getItem() instanceof ItemRotameter) {
				int side = 0;
				if (ItemModeHelper.getMode(event.entityLiving.getHeldItem()) == 0) {
					side = event.face;
				} else {
					side = Blocks.piston.determineOrientation(event.world, event.x, event.y, event.z, event.entityLiving);
				}
				IChatComponent c = new ChatComponentText("[").appendSibling(new ChatComponentTranslation(event.entityLiving.getHeldItem().getUnlocalizedName() + ".name")).appendText("] ");
				TileEntity t = event.world.getTileEntity(event.x, event.y, event.z);
				if (t instanceof IRotaryOutput && ((IRotaryOutput) t).isOutputFace(side)) {
					IRotaryOutput o = (IRotaryOutput) t;
					c = c.appendSibling(new ChatComponentTranslation("text.derpyshiz.output.name")).appendText(": ").appendText(Integer.toString(o.getRotaryOutput(side)) + " ").appendSibling(new ChatComponentTranslation("text.derpyshiz.rpm.name"));
				} else if (t instanceof IRotaryInput && ((IRotaryInput) t).isInputFace(side)) {
					IRotaryInput i = (IRotaryInput) t;
					c = c.appendSibling(new ChatComponentTranslation("text.derpyshiz.input.name")).appendText(": ").appendText(Integer.toString(i.getRotaryInput(side)) + " ").appendSibling(new ChatComponentTranslation("text.derpyshiz.rpm.name"));
				} else {
					return;
				}
				event.entityPlayer.addChatMessage(c);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onLivingHurt(LivingHurtEvent event) {
		if (event.source.damageType == "fall" && event.entityLiving instanceof EntityPlayer && ((EntityPlayer) event.entityLiving).isBlocking() && ((EntityPlayer) event.entityLiving).getHeldItem() != null && ((EntityPlayer) event.entityLiving).getHeldItem().getItem() instanceof WindSword) {
			event.setCanceled(true);
			DerpyItems.damageItem(((EntityPlayer) event.entityLiving).getHeldItem(), 1, event.entityLiving);
		}
	}

	@SubscribeEvent
	public void onLivingAttack(LivingAttackEvent event) {
		if (LongSword.AOEEnabled && event.source.getSourceOfDamage() instanceof EntityLivingBase && ((EntityLivingBase) event.source.getSourceOfDamage()).getHeldItem() != null && ((EntityLivingBase) event.source.getSourceOfDamage()).getHeldItem().getItem() instanceof LongSword) {
			LongSword.AOEEnabled = false;
			EntityLivingBase source = (EntityLivingBase) event.source.getSourceOfDamage();
			for (Object e : event.entityLiving.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(event.entity.posX - 2, event.entity.posY - 0.5, event.entity.posZ - 2, event.entity.posX + 2, event.entity.posY + 0.5, event.entity.posZ + 2))) {
				if (!(e == event.entity || e == source)) {
					if (source instanceof EntityPlayer) {
						((EntityPlayer) source).attackTargetEntityWithCurrentItem((Entity) e);
					} else {
						source.attackEntityAsMob((Entity) e);
					}
					DerpyItems.damageItem(source.getHeldItem(), 1, source);
				}
			}
			LongSword.AOEEnabled = true;
		}
	}

	@SubscribeEvent
	public void onUseHoe(UseHoeEvent event) {
		Block oblock = event.world.getBlock(event.x, event.y, event.z);
		if (LongHoe.AOEEnabled && event.current.getItem() instanceof LongHoe && (oblock instanceof BlockDirt || oblock instanceof BlockGrass)) {
			LongHoe.AOEEnabled = false;
			for (int x = event.x - 3; x < event.x + 3; x++) {
				for (int z = event.z - 3; z < event.z + 3; z++) {
					Block block = event.world.getBlock(x, event.y, z);
					if ((!(x == event.x && z == event.z)) && (block instanceof BlockDirt || block instanceof BlockGrass) && (!MinecraftForge.EVENT_BUS.post(new UseHoeEvent(event.entityPlayer, event.current, event.world, x, event.y, z)))) {
						event.world.setBlock(x, event.y, z, Blocks.farmland);
						if (event.world.getBlock(x, event.y + 1, z).isReplaceable(event.world, x, event.y + 1, z)) {
							event.world.setBlockToAir(x, event.y + 1, z);
						}
						DerpyItems.damageItem(event.current, 1, event.entityPlayer);
					}
				}
			}
			LongHoe.AOEEnabled = true;
		}
	}

	@SubscribeEvent
	public void onLivingDrop(LivingDropsEvent event) {
		if (event.entityLiving instanceof EntityHorse && !event.entityLiving.isChild()) {
			event.entityLiving.dropItem(GameRegistry.findItem("derpyshiz", "lasagne"), 2 + event.lootingLevel);
		}
	}

	@SubscribeEvent
	public void onBreak(BreakEvent event) {
		// Yes i know, not very DRY
		if (LongPickaxe.AOEEnabled && (!event.world.isRemote) && event.getPlayer().getHeldItem() != null && event.getPlayer().getHeldItem().getItem() instanceof LongPickaxe) {
			if (!event.block.isToolEffective("pickaxe", event.blockMetadata)) {
				return;
			}
			LongPickaxe.AOEEnabled = false;
			ohitbreak: for (int x = event.x - 1; x <= event.x + 1; x++) {
				for (int y = event.y - 1; y <= event.y + 1; y++) {
					for (int z = event.z - 1; z <= event.z + 1; z++) {
						Block block = event.world.getBlock(x, y, z);
						int meta = event.world.getBlockMetadata(x, y, z);
						if (event.getPlayer().getHeldItem() == null) {
							break ohitbreak;
						} else if ((!(x == event.x && y == event.y && z == event.z)) && block.isToolEffective("pickaxe", meta) && block.getHarvestLevel(meta) <= event.getPlayer().getHeldItem().getItem().getHarvestLevel(event.getPlayer().getHeldItem(), "pickaxe")) {
							((EntityPlayerMP) event.getPlayer()).theItemInWorldManager.tryHarvestBlock(x, y, z);
						}
					}
				}
			}
			LongPickaxe.AOEEnabled = true;
		} else if (LongShovel.AOEEnabled && (!event.world.isRemote) && event.getPlayer().getHeldItem() != null && event.getPlayer().getHeldItem().getItem() instanceof LongShovel) {
			if (!event.block.isToolEffective("shovel", event.blockMetadata)) {
				return;
			}
			LongShovel.AOEEnabled = false;
			ohitbreak: for (int x = event.x - 1; x <= event.x + 1; x++) {
				for (int y = event.y - 1; y <= event.y + 1; y++) {
					for (int z = event.z - 1; z <= event.z + 1; z++) {
						Block block = event.world.getBlock(x, y, z);
						int meta = event.world.getBlockMetadata(x, y, z);
						if (event.getPlayer().getHeldItem() == null) {
							break ohitbreak;
						} else if ((!(x == event.x && y == event.y && z == event.z)) && block.isToolEffective("shovel", meta) && block.getHarvestLevel(meta) <= event.getPlayer().getHeldItem().getItem().getHarvestLevel(event.getPlayer().getHeldItem(), "shovel")) {
							((EntityPlayerMP) event.getPlayer()).theItemInWorldManager.tryHarvestBlock(x, y, z);
						}
					}
				}
			}
			LongShovel.AOEEnabled = true;
		} else if (LongAxe.AOEEnabled && (!event.world.isRemote) && event.getPlayer().getHeldItem() != null && event.getPlayer().getHeldItem().getItem() instanceof LongAxe) {
			if (!event.block.isToolEffective("axe", event.blockMetadata)) {
				return;
			}
			LongAxe.AOEEnabled = false;
			if (event.block instanceof BlockLog) {
				ohitbreak: for (int x = event.x - 1; x <= event.x + 1; x++) {
					for (int z = event.z - 1; z <= event.z + 1; z++) {
						for (int y = event.y-32; y <= event.y+32;y++) {
							Block block = event.world.getBlock(x, y, z);
							int meta = event.world.getBlockMetadata(x, y, z);
							if (event.getPlayer().getHeldItem() == null) {
								break ohitbreak;
							} else if ((!(x == event.x && y == event.y && z == event.z)) && block == event.block && meta == event.blockMetadata) {
								((EntityPlayerMP) event.getPlayer()).theItemInWorldManager.tryHarvestBlock(x, y, z);
							}
						}
					}
				}
			} else {
				ohitbreak: for (int x = event.x - 1; x <= event.x + 1; x++) {
					for (int y = event.y - 1; y <= event.y + 1; y++) {
						for (int z = event.z - 1; z <= event.z + 1; z++) {
							Block block = event.world.getBlock(x, y, z);
							int meta = event.world.getBlockMetadata(x, y, z);
							if (event.getPlayer().getHeldItem() == null) {
								break ohitbreak;
							} else if ((!(x == event.x && y == event.y && z == event.z)) && block.isToolEffective("axe", meta) && block.getHarvestLevel(meta) <= event.getPlayer().getHeldItem().getItem().getHarvestLevel(event.getPlayer().getHeldItem(), "axe")) {
								((EntityPlayerMP) event.getPlayer()).theItemInWorldManager.tryHarvestBlock(x, y, z);
							}
						}
					}
				}
			}
			LongAxe.AOEEnabled = true;
		}
	}

	@SubscribeEvent
	public void onJoin(PlayerLoggedInEvent event) {
		if (Main.checkForUpdates && (!event.player.worldObj.isRemote)) {
			new UpdateChecker(event.player).start();
		}
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (Main.flashy && ClientProxy.inventorySeizureWool != null) {
			ClientProxy.inventorySeizureWool.updateRand();
		}
	}

	public static WoodStack[] dropsHand;
	public static WoodStack[][] dropsPerLevel;

	public class WoodStack {

		int meta;
		int amount;
		boolean isPlank;

		public WoodStack(int meta, int amount, boolean isPlank) {
			this.meta = meta;
			this.amount = amount;
			this.isPlank = isPlank;
		}

		public ItemStack get() {
			return new ItemStack(GameRegistry.findBlock("derpyshiz", this.isPlank ? "plank" : "log"), this.amount, this.meta);
		}

	}

	public class UpdateChecker extends Thread {

		public EntityPlayer player;

		public UpdateChecker(EntityPlayer p) {
			super();
			this.player = p;
		}

		@Override
		public void run() {
			try {
				URL updateUrl = new URL(Main.updateURL);
				InputStream stream = updateUrl.openStream();
				String updatedVersion = IOUtils.toString(stream);
				stream.close();
				if (!updatedVersion.equals(Main.VERSION)) {
					this.player.addChatComponentMessage(new ChatComponentText("[Wuerfel_21] Hey, " + this.player.getGameProfile().getName() + ", you know, theres an update for The Derpy Shiz Mod! you should be able to download it from http://bit.ly/1zoeyNO ! If you dont like me chatting with you about updates, you can disable update checking in the config! Your Version: " + Main.VERSION + ", Updated Version: " + updatedVersion));
				}
			} catch (Exception e) {
				FMLLog.warning("[Wuerfel_21] Something definitly wrent wrong ..." + e.toString());
				return;
			}
		}
	}
}
