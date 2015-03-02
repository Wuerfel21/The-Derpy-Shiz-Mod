package net.wuerfel21.derpyshiz.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.DerpyRegistry;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerMillstone extends DerpyContainer {

	public TileEntityMillstone millstone;
	public InventoryPlayer inventoryPlayer;
	
	public int lastProgress;
	public int lastEnergyNeeded;
	public int lastSpeed;

	public ContainerMillstone(InventoryPlayer inv, TileEntityMillstone te) {
		this.millstone = te;
		this.inventoryPlayer = inv;

		this.addSlotToContainer(new Slot(this.millstone, 0, 56, 17));
		this.addSlotToContainer(new SlotFurnace(this.inventoryPlayer.player, this.millstone, 1, 116, 35));
		GuiHelper.bindPlayerInventory(this, this.inventoryPlayer, 8, 84);
	}

	@Override
	public void addCraftingToCrafters(ICrafting craft) {
		super.addCraftingToCrafters(craft);
		craft.sendProgressBarUpdate(this, 0, this.millstone.progress);
		craft.sendProgressBarUpdate(this, 1, this.millstone.energyNeeded);
		craft.sendProgressBarUpdate(this, 2, this.millstone.inputSpeed);
	}
	
	public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting craft = (ICrafting)this.crafters.get(i);

            if (this.lastProgress != this.millstone.progress)
            {
                craft.sendProgressBarUpdate(this, 0, this.millstone.progress);
            }

            if (this.lastEnergyNeeded != this.millstone.energyNeeded)
            {
                craft.sendProgressBarUpdate(this, 1, this.millstone.energyNeeded);
            }
            
            if (this.lastSpeed != this.millstone.inputSpeed) {
            	craft.sendProgressBarUpdate(this, 2, this.millstone.inputSpeed);
            }

        }

        this.lastProgress = millstone.progress;
        this.lastEnergyNeeded = millstone.energyNeeded;
        this.lastSpeed = millstone.inputSpeed;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int key, int value) {
		switch (key) {
		case 0:
			this.millstone.progress = value;
			break;
		case 1:
			this.millstone.energyNeeded = value;
			break;
		case 2:
			this.millstone.inputSpeed = value;
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
		int invSize = this.millstone.getSizeInventory();

		// Slot clicked on has items
		if (slot != null && slot.getHasStack() == true) {
			ItemStack stackInSlot = slot.getStack();
			stack = stackInSlot.copy();

			// Shift-click from the millstone into the player inventory
			if (slotNum < invSize) {
				// Try to merge the stack into the player inventory
				if (mergeItemStack(stackInSlot, invSize, inventorySlots.size(), false) == false) {
					return null;
				}

				// Shift-click from the output slot
				if (slotNum == 1) {
					slot.onSlotChange(stackInSlot, stack);
				}
			}
			// Shift-click from the player inventory into the millstone
			else {
				// Has a millstone recipe, try to put in in the input slot
				if (DerpyRegistry.isValidForMillstone(stackInSlot,millstone.getTier())) {
					if (this.mergeItemStack(stackInSlot, 0, 1, false) == false) {
						return null;
					}
				}
				// Not millstoneable, transfer between player main inventory
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
