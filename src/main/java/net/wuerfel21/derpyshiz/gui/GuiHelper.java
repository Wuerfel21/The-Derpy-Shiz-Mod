package net.wuerfel21.derpyshiz.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class GuiHelper {

	public static void bindPlayerInventory(DerpyContainer container, InventoryPlayer inventory, int xOff, int yOff) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				container.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, xOff + j * 18, yOff + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			container.addSlotToContainer(new Slot(inventory, i, xOff + i * 18, yOff + 58));
		}

	}
}
