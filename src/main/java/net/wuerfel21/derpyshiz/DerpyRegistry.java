package net.wuerfel21.derpyshiz;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;

public abstract class DerpyRegistry {

	public static LinkedHashMap<ItemStack, TieredMachineEntry> millstoneRecipes = new LinkedHashMap<ItemStack, TieredMachineEntry>();
	public static LinkedHashMap<ItemStack, CentrifugeEntry> centrifugeRecipes = new LinkedHashMap<ItemStack, CentrifugeEntry>();

	public static List<BasicBlockEntry> leafTypes = new ArrayList<BasicBlockEntry>();

	public static ItemStack getKey(Map<ItemStack, ? extends BasicMachineEntry> machine, ItemStack stack) {
		for (Entry<ItemStack, ? extends BasicMachineEntry> entry : machine.entrySet()) {
			if (isValidFor(stack, entry.getKey())) {
				return (ItemStack) entry.getKey();
			}
		}
		return null;
	}

	public static TieredMachineEntry getOutput(Map<ItemStack, ? extends TieredMachineEntry> machine, ItemStack stack, int tier) {
		for (Entry<ItemStack, ? extends TieredMachineEntry> entry : machine.entrySet()) {
			if (isValidForWithTier(stack, entry.getKey(), entry.getValue(), tier)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public static boolean isValidForMachine(Map<ItemStack, ? extends TieredMachineEntry> machine, ItemStack stack, int tier) {
		if (stack == null)
			return false;
		for (Entry<ItemStack, ? extends TieredMachineEntry> entry : machine.entrySet()) {
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
		 * @param output
		 *            the output of the recipe
		 * @param exp
		 *            the experience from the recipe
		 * @param energy
		 *            the energy required to make the recipe, in MCC/R
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
		 * @param output
		 *            the output of the recipe
		 * @param exp
		 *            the experience from the recipe
		 * @param energy
		 *            the energy required to make the recipe, in MCC/R
		 * @param tier
		 *            the minimum tier of machine required to make the recipe
		 */
		public TieredMachineEntry(ItemStack output, float exp, int energy, int tier) {
			super(output, exp, energy);
			this.tier = tier;
		}

	}

	public static class CentrifugeEntry extends TieredMachineEntry {

		public ItemStack output2;
		public ItemStack output3;

		/**
		 * @param output
		 *            the first output of the recipe
		 * @param output2
		 *            the second output of the recipe
		 * @param output3
		 *            the third output of the recipe
		 * @param exp
		 *            the experience from the recipe
		 * @param energy
		 *            the energy required to make the recipe, in MCC/R
		 * @param tier
		 *            the minimum tier of machine required to make the recipe
		 */
		public CentrifugeEntry(ItemStack output, ItemStack output2, ItemStack output3, float exp, int energy, int tier) {
			super(output, exp, energy, tier);
			this.output2 = output2;
			this.output3 = output3;
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
