package net.wuerfel21.derpyshiz.nei;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.wuerfel21.derpyshiz.DerpyRegistry;
import net.wuerfel21.derpyshiz.DerpyRegistry.CentrifugeEntry;
import net.wuerfel21.derpyshiz.DerpyRegistry.TieredMachineEntry;
import net.wuerfel21.derpyshiz.gui.GuiCentrifuge;
import net.wuerfel21.derpyshiz.gui.GuiCentrifuge;
import net.wuerfel21.derpyshiz.nei.NEICentrifuge.CentrifugePair;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler.RecipeTransferRect;

public class NEICentrifuge extends TemplateRecipeHandler {

	public class CentrifugePair extends CachedRecipe {

		public ItemStack input;
		public CentrifugeEntry output;

		public CentrifugePair(ItemStack input, CentrifugeEntry output) {
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
		
		@Override
		public List<PositionedStack> getOtherStacks() {
			return Arrays.asList(new PositionedStack[] {new PositionedStack(output.output2, 111, 2), new PositionedStack(output.output3, 111, 46)});
		}

	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("container.derpyshiz.centrifuge.name");
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiCentrifuge.class;
	}

	@Override
	public void drawExtras(int recipe) {
		CentrifugePair arecipe = (CentrifugePair) arecipes.get(recipe);
		drawProgressBar(74, 23, 176, 17, 24, 16, arecipe.output.energy / 150, 0);
		String desc = (arecipe.output.tier == 0 ? "Normal, " : "Advanced, ") + Integer.toString(arecipe.output.energy) + " " + StatCollector.translateToLocal("text.derpyshiz.mccr.name");
		GuiDraw.drawStringC(desc, 88, 70, 4210752, false);
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("ds_centrifuging")) {
			for (Entry<ItemStack, CentrifugeEntry> entry : DerpyRegistry.centrifugeRecipes.entrySet()) {
				this.arecipes.add(new CentrifugePair(entry.getKey(), entry.getValue()));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for (Entry<ItemStack, CentrifugeEntry> recipe : DerpyRegistry.centrifugeRecipes.entrySet()) {
			if (NEIServerUtils.areStacksSameType(recipe.getValue().output, result) || NEIServerUtils.areStacksSameType(recipe.getValue().output2, result) || NEIServerUtils.areStacksSameType(recipe.getValue().output3, result)) {
				arecipes.add(new CentrifugePair(recipe.getKey(), recipe.getValue()));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for (Entry<ItemStack, CentrifugeEntry> recipe : DerpyRegistry.centrifugeRecipes.entrySet())
			if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getKey(), ingredient)) {
				CentrifugePair arecipe = new CentrifugePair(recipe.getKey(), recipe.getValue());
				arecipe.setIngredientPermutation(Arrays.asList(arecipe.getIngredient()), ingredient);
				arecipes.add(arecipe);
			}
	}

	@Override
	public String getOverlayIdentifier() {
		return "ds_centrifuging";
	}
	
	@Override
	public int recipiesPerPage() {
		return 1;
	}

	// rekt!
	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 18), "ds_centrifuging"));
	}

	@Override
	public String getGuiTexture() {
		return "derpyshiz:textures/gui/centrifuge.png";
	}

}
