package net.wuerfel21.derpyshiz.nei;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.StatCollector;
import net.wuerfel21.derpyshiz.DerpyRegistry;
import net.wuerfel21.derpyshiz.DerpyRegistry.TieredMachineEntry;
import net.wuerfel21.derpyshiz.gui.GuiMillstone;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.FurnaceRecipeHandler.SmeltingPair;

public class NEIMillstone extends TemplateRecipeHandler {

	public class MillstonePair extends CachedRecipe {

		public ItemStack input;
		public TieredMachineEntry output;

		public MillstonePair(ItemStack input, TieredMachineEntry output) {
			this.input = input;
			this.output = output;

		}

		@Override
		public PositionedStack getResult() {
			return new PositionedStack(output.output, 111, 24);
		}

		@Override
		public PositionedStack getIngredient() {
			return new PositionedStack(input, 51, 6);
		}

	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("container.derpyshiz.millstone.name");
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiMillstone.class;
	}

	@Override
	public void drawExtras(int recipe) {
		MillstonePair arecipe = (MillstonePair) arecipes.get(recipe);
		drawProgressBar(74, 23, 176, 17, 24, 16, arecipe.output.energy / 150, 0);
		String desc = (arecipe.output.tier == 0 ? "Normal, " : "Advanced, ") + Integer.toString(arecipe.output.energy) + " " + StatCollector.translateToLocal("text.derpyshiz.mccr.name");
		GuiDraw.drawStringC(desc, 88, 50, 4210752, false);
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("ds_grinding")) {
			for (Entry<ItemStack, TieredMachineEntry> entry : DerpyRegistry.millstoneRecipes.entrySet()) {
				this.arecipes.add(new MillstonePair(entry.getKey(), entry.getValue()));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for (Entry<ItemStack, TieredMachineEntry> recipe : DerpyRegistry.millstoneRecipes.entrySet()) {
			if (NEIServerUtils.areStacksSameType(recipe.getValue().output, result)) {
				arecipes.add(new MillstonePair(recipe.getKey(), recipe.getValue()));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for (Entry<ItemStack, TieredMachineEntry> recipe : DerpyRegistry.millstoneRecipes.entrySet())
			if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getKey(), ingredient)) {
				MillstonePair arecipe = new MillstonePair(recipe.getKey(), recipe.getValue());
				arecipe.setIngredientPermutation(Arrays.asList(arecipe.getIngredient()), ingredient);
				arecipes.add(arecipe);
			}
	}

	@Override
	public String getOverlayIdentifier() {
		return "ds_grinding";
	}

	// rekt!
	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 18), "ds_grinding"));
	}

	@Override
	public String getGuiTexture() {
		return "derpyshiz:gui/millstone.png";
	}

}
