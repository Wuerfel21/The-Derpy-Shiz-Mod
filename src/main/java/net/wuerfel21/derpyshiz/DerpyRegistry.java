package net.wuerfel21.derpyshiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public abstract class DerpyRegistry {

	public static HashMap<ItemStack, BasicMachineEntry> millstoneRecipes = new HashMap<ItemStack, BasicMachineEntry>();
	
	public static List<BasicBlockEntry> leafTypes = new ArrayList<BasicBlockEntry>();

	public static BasicMachineEntry getMillstoneOutput(ItemStack stack) {
		for (Entry entry : millstoneRecipes.entrySet()) {
			if (isValidForWithSize(stack, (ItemStack) entry.getKey())) {
				return (BasicMachineEntry) entry.getValue();
			}
		}
		return null;
	}

	public static boolean isValidForMillstone(ItemStack stack) {
		for (ItemStack entry : millstoneRecipes.keySet()) {
			if (isValidFor(stack, entry)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidForWithSize(ItemStack stack, ItemStack entry) {
		return isValidFor(stack, entry) && (stack.stackSize >= entry.stackSize);

	}

	public static boolean isValidFor(ItemStack stack, ItemStack entry) {
		return (stack.getItem() == entry.getItem()) && (stack.getItemDamage() == entry.getItemDamage());
	}

	/**
	 * A basic recipe output for a Machine
	 * 
	 * @author Wuerfel_21
	 *
	 */
	public static class BasicMachineEntry {

		public ItemStack output;
		public float exp;

		public BasicMachineEntry(ItemStack output, float exp) {
			this.output = output;
			this.exp = exp;
		}

	}

	public static class BasicBlockEntry {

		public Block block;
		public int meta;

		public BasicBlockEntry(Block block, int meta) {
			this.block = block;
			this.meta = meta;
		}

	}

}
