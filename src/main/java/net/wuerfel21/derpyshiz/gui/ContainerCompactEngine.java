package net.wuerfel21.derpyshiz.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.wuerfel21.derpyshiz.DerpyRegistry;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCompactEngine;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;

public class ContainerCompactEngine extends DerpyContainer {

	public TileEntityCompactEngine engine;
	private InventoryPlayer inventoryPlayer;
	
	public int lastTicksLeft;
	public int lastTicksForUsedFuel;
	
	public ContainerCompactEngine(InventoryPlayer inv, TileEntityCompactEngine te) {
		this.engine = te;
		this.inventoryPlayer = inv;

		this.addSlotToContainer(new Slot(this.engine, 0, 80, 53));
		GuiHelper.bindPlayerInventory(this, this.inventoryPlayer, 8, 84);
	}

	@Override
	public void addCraftingToCrafters(ICrafting craft) {
		super.addCraftingToCrafters(craft);
		craft.sendProgressBarUpdate(this, 0, this.engine.ticksLeft);
		craft.sendProgressBarUpdate(this, 1, this.engine.ticksForUsedFuel);
	}
	
	public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting craft = (ICrafting)this.crafters.get(i);

            if (this.lastTicksLeft != this.engine.ticksLeft)
            {
                craft.sendProgressBarUpdate(this, 0, this.engine.ticksLeft);
            }
            
            if (this.lastTicksForUsedFuel != this.engine.ticksForUsedFuel) {
            	craft.sendProgressBarUpdate(this, 1, this.engine.ticksForUsedFuel);
            }

        }

        this.lastTicksLeft = this.engine.ticksLeft;
        this.lastTicksForUsedFuel = this.engine.ticksForUsedFuel;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int key, int value) {
		switch (key) {
		case 0:
			this.engine.ticksLeft = value;
			break;
		case 1:
			this.engine.ticksForUsedFuel = value;
			break;
		}
	}
	
	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 * 
	 * Stolen from EnderUtilitys
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNum) {
		ItemStack stack = null;
		Slot slot = (Slot) inventorySlots.get(slotNum);
		int invSize = this.engine.getSizeInventory();

		// Slot clicked on has items
		if (slot != null && slot.getHasStack() == true) {
			ItemStack stackInSlot = slot.getStack();
			stack = stackInSlot.copy();

			// Shift-click from the engine into the player inventory
			if (slotNum < invSize) {
				// Try to merge the stack into the player inventory
				if (mergeItemStack(stackInSlot, invSize, inventorySlots.size(), false) == false) {
					return null;
				}
			}
			// Shift-click from the player inventory into the engine
			else {
				// Is furnace fuel, try to put in in the input slot
				if (TileEntityFurnace.isItemFuel(stackInSlot)) {
					if (this.mergeItemStack(stackInSlot, 0, 1, false) == false) {
						return null;
					}
				}
				// Not burnable, transfer between player main inventory
				// and hotbar
				// From main inventory into hotbar
				else if (slotNum >= invSize && slotNum < (27 + invSize)) {
					if (this.mergeItemStack(stackInSlot, (27 + invSize), (36 + invSize), false) == false) {
						return null;
					}
				}
				// From hotbar into main inventory
				else if (slotNum >= (27 + invSize) && slotNum < (36 + invSize)) {
					if (this.mergeItemStack(stackInSlot, invSize, (27 + invSize), false) == false) {
						return null;
					}
				}
			}

			// All items moved, empty the slot
			if (stackInSlot.stackSize == 0) {
				slot.putStack(null);
			}
			// Update the slot
			else {
				slot.onSlotChanged();
			}

			// No items were moved
			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, stackInSlot);
		}

		return stack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

}
