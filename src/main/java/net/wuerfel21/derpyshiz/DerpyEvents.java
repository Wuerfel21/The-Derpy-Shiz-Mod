package net.wuerfel21.derpyshiz;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.wuerfel21.derpyshiz.blocks.BlockGearbox;
import net.wuerfel21.derpyshiz.blocks.DerpyOres;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
import net.wuerfel21.derpyshiz.items.DerpyHammer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class DerpyEvents {

	public static void register() {
		DerpyEvents derp = new DerpyEvents();
		MinecraftForge.EVENT_BUS.register(derp);
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
		if ((!event.world.isRemote) && event.action == event.action.RIGHT_CLICK_BLOCK && event.entityLiving.getHeldItem() != null && event.entityLiving.getHeldItem().getItem() instanceof DerpyHammer) {
			Block block = event.world.getBlock(event.x, event.y, event.z);
			if (block instanceof BlockGearbox) {
				TileEntityGearbox box = (TileEntityGearbox) event.world.getTileEntity(event.x, event.y, event.z);
				int newDir = Blocks.piston.determineOrientation(event.world, event.x, event.y, event.z, event.entityLiving);
				if (box.dir == newDir) return;
				box.dir = newDir;
				box.markDirty();
				event.world.markBlockForUpdate(event.x, event.y, event.z);
				event.entityLiving.getHeldItem().damageItem(1, event.entityLiving);
				event.world.playSoundEffect(event.x + 0.5d, event.y + 0.5d, event.z + 0.5d, "random.anvil_land", 1f, event.world.rand.nextFloat() * 0.1f + 0.9f);
				event.entityLiving.swingItem();
			}
		}
	}

	@SubscribeEvent
	public void onLivingDrop(LivingDropsEvent event) {
		if (event.entityLiving instanceof EntityHorse && !event.entityLiving.isChild()) {
			event.entityLiving.dropItem(GameRegistry.findItem("derpyshiz", "lasagne"), 2 + event.lootingLevel);
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

}
