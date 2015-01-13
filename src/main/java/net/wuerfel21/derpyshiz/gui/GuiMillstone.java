package net.wuerfel21.derpyshiz.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;

import org.lwjgl.opengl.GL11;

public class GuiMillstone extends GuiContainer {
	
	public ResourceLocation theTexture = new ResourceLocation("derpyshiz:gui/millstone.png");
	
	public TileEntityMillstone millstone;
	public InventoryPlayer inventoryPlayer;
	
	public GuiMillstone(InventoryPlayer inv, TileEntityMillstone te) {
		super(new ContainerMillstone(inv, te));
		this.millstone = te;
		this.inventoryPlayer = inv;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String millName = millstone.getInventoryName();
		String invName = StatCollector.translateToLocal("container.inventory");
		this.fontRendererObj.drawString(millName, this.xSize / 2 - this.fontRendererObj.getStringWidth(millName) / 2, 5, 4210752);
		this.fontRendererObj.drawString(invName, 8, this.ySize - 92, 4210752);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("info.budget.name"), 8, this.ySize - 108, 0xFF0000);
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float gameTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(theTexture);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}

}
