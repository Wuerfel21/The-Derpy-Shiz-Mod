package net.wuerfel21.derpyshiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.primitives.Ints;

import scala.actors.threadpool.Arrays;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public abstract class DerpyRegistry {

	public static HashMap<ItemStack, TieredMachineEntry> millstoneRecipes = new HashMap<ItemStack, TieredMachineEntry>();

	public static List<BasicBlockEntry> leafTypes = new ArrayList<BasicBlockEntry>();

	public static TieredMachineEntry getMillstoneOutput(ItemStack stack, int tier) {
		for (Entry<ItemStack, TieredMachineEntry> entry : millstoneRecipes.entrySet()) {
			if (isValidForWithSize(stack, entry.getKey()) && isValidForWithTier(stack, entry.getKey(), entry.getValue(), tier)) {
				return (TieredMachineEntry) entry.getValue();
			}
		}
		return null;
	}

	public static boolean isValidForMillstone(ItemStack stack, int tier) {
		for (Entry<ItemStack, TieredMachineEntry> entry : millstoneRecipes.entrySet()) {
			if (isValidForWithTier(stack, entry.getKey(), entry.getValue(), tier)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidForWithTier(ItemStack stack, ItemStack key, TieredMachineEntry value, int tier) {
		return value.tier <= tier && isValidFor(stack, key);
	}

	public static boolean isValidForWithSize(ItemStack stack, ItemStack key) {
		return (stack.stackSize >= key.stackSize) && isValidFor(stack, key);

	}

	public static boolean isValidFor(ItemStack stack, ItemStack key) {

		return ((stack.getItem() == key.getItem()) && (stack.getItemDamage() == key.getItemDamage())) || matchesOredict(stack, key);
	}

	public static boolean matchesOredict(ItemStack stack, ItemStack key) {
		int[] stackDict = OreDictionary.getOreIDs(stack);
		int[] keyDict = OreDictionary.getOreIDs(key);
		for (int s : stackDict) {
			for (int e : keyDict) {
				if (s == e) {
					return true;
				}
			}
		}
		return false;
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
		public int energy;

		/**
		 * 
		 * @param output the output of the recipe
		 * @param exp the experience from the recipe (unimplemented)
		 * @param energy the energy required to make the recipe, in 1200/r
		 */
		public BasicMachineEntry(ItemStack output, float exp, int energy) {
			this.output = output;
			this.exp = exp;
			this.energy = energy;
		}

	}

	public static class TieredMachineEntry extends BasicMachineEntry {

		public int tier;

		/**
		 * @param output the output of the recipe
		 * @param exp the experience from the recipe (unimplemented)
		 * @param energy the energy required to make the recipe, in 1200/r
		 * @param tier the minimum tier of machine required to make the recipe
		 */
		public TieredMachineEntry(ItemStack output, float exp, int energy, int tier) {
			super(output, exp, energy);
			this.tier = tier;
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
