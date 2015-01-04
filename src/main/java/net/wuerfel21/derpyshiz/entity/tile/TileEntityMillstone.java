package net.wuerfel21.derpyshiz.entity.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;

public class TileEntityMillstone extends TileEntity implements ISidedInventory {

	protected ItemStack[] stacks = new ItemStack[2];
	protected String name = null;

	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return slot < this.getSizeInventory() ? stacks[slot] : null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (this.stacks[slot] != null) {
			ItemStack itemstack;

			if (this.stacks[slot].stackSize <= amount) {
				itemstack = this.stacks[slot];
				this.stacks[slot] = null;
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.stacks[slot].splitStack(amount);

				if (this.stacks[slot].stackSize == 0) {
					this.stacks[slot] = null;
				}
				this.markDirty();
				return itemstack;
			}
		}
		this.markDirty();
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if (slot < this.getSizeInventory()) {
			this.markDirty();
			this.stacks[slot] = stack;
		}
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.name : StatCollector.translateToLocal("container.millstone.name");
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.name != null;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot != 1;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] {0,1};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return slot != 1;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot == 1;
	}

}
