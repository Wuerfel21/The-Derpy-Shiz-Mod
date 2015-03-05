package net.wuerfel21.derpyshiz.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCentrifuge;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;

public class GuiCentrifuge extends GuiContainer {

public ResourceLocation theTexture = new ResourceLocation("derpyshiz:textures/gui/centrifuge.png");
	
	public TileEntityCentrifuge centrifuge;
	public InventoryPlayer inventoryPlayer;
	
	public GuiCentrifuge(InventoryPlayer inv, TileEntityCentrifuge te) {
		super(new ContainerCentrifuge(inv, te));
		this.centrifuge = te;
		this.inventoryPlayer = inv;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String centName = centrifuge.getInventoryName();
		String invName = StatCollector.translateToLocal("container.inventory");
		this.fontRendererObj.drawString(centName, this.xSize / 2 - this.fontRendererObj.getStringWidth(centName) / 2, 5, 4210752);
		this.fontRendererObj.drawString(invName, 8, this.ySize - 92, 4210752);
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float gameTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(theTexture);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
		int progress = centrifuge.getProgressScaled(24);
		this.drawTexturedModalRect(x + 79, y + 35, 176, 17, progress+1, 16);
		int speed = centrifuge.getSpeedScaled(17);
		this.drawTexturedModalRect(x + 56, y + 35 + 17 - speed, 176, 17 - speed, 16, speed);
	}

}
