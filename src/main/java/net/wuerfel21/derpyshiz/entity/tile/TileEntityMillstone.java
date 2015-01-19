package net.wuerfel21.derpyshiz.entity.tile;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.wuerfel21.derpyshiz.DerpyRegistry;
import net.wuerfel21.derpyshiz.DerpyRegistry.TieredMachineEntry;
import net.wuerfel21.derpyshiz.blocks.BlockMillstone;
import net.wuerfel21.derpyshiz.rotary.IRotaryInput;
import net.wuerfel21.derpyshiz.rotary.ITieredTE;
import net.wuerfel21.derpyshiz.rotary.RotaryManager;

public class TileEntityMillstone extends TileEntity implements ISidedInventory, ITieredTE, IRotaryInput {

	public ItemStack[] stacks = new ItemStack[2];
	protected String name = null;
	public int tier = 0;
	public boolean inInventory = false;
	public int progress = 0;
	public int energyNeeded = 0;
	public int[] input = new int[2];
	public int[] length = new int[2];

	@Override
	public void updateEntity() {
		if (this.worldObj != null) {
			this.setTier(this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
			RotaryManager.updateRotaryInput(this, this, 0);
			RotaryManager.updateRotaryInput(this, this, 1);
			if (stacks[0] != null && DerpyRegistry.isValidForMillstone(stacks[0], tier)) {
				TieredMachineEntry output = DerpyRegistry.getMillstoneOutput(stacks[0], this.getTier());
				if (DerpyRegistry.canMillstoneOperate(this)) {
					energyNeeded = output.energy;
					progress += input[RotaryManager.getMaxInput(this)];
					if (progress >= energyNeeded) {
						stacks[0].stackSize -= DerpyRegistry.getMillstoneKey(stacks[0]).stackSize;
						if (stacks[0].stackSize < 1) {
							stacks[0] = null;
						}
						if (stacks[1] == null) {
							stacks[1] = output.output.copy();
						} else {
							stacks[1].stackSize += output.output.stackSize;
						}
						progress -= energyNeeded;
					}
				} else {
					progress = 0;
					energyNeeded = 0;
				}
			} else {
				progress = 0;
				energyNeeded = 0;
			}
		}
	}

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
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		NBTTagCompound inventory = new NBTTagCompound();
		if (stacks[0] != null)
			inventory.setTag("input", stacks[0].writeToNBT(new NBTTagCompound()));
		else
			inventory.removeTag("input");
		if (stacks[1] != null)
			inventory.setTag("output", stacks[1].writeToNBT(new NBTTagCompound()));
		else
			inventory.removeTag("output");
		tag.setTag("inventory", inventory);
		NBTTagCompound rotary = new NBTTagCompound();
		rotary.setTag("input", RotaryManager.inputToNBT(this));
		tag.setTag("rotary", rotary);
		tag.setInteger("progress", this.progress);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagCompound inventory = tag.getCompoundTag("inventory");
		if (inventory.hasKey("input"))
			stacks[0] = ItemStack.loadItemStackFromNBT(inventory.getCompoundTag("input"));
		if (inventory.hasKey("output"))
			stacks[1] = ItemStack.loadItemStackFromNBT(inventory.getCompoundTag("output"));
		NBTTagCompound rotary = tag.getCompoundTag("rotary");
		RotaryManager.inputFromNBT(this, rotary);
		this.progress = tag.getInteger("progress");
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
		return this.hasCustomInventoryName() ? this.name : StatCollector.translateToLocal("container.derpyshiz.millstone.name");
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
		return new int[] { 0, 1 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return slot != 1;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot == 1;
	}

	public void dropInv(World world, int x, int y, int z) {
		Block block = this.getBlockType();
		if (!(block instanceof BlockMillstone)) {
			return;
		}
		for (ItemStack stack : this.stacks) {
			if (stack != null) {
				((BlockMillstone) block).dropBlockAsItem(world, x, y, z, stack);
			}

		}
	}

	@Override
	public int getTier() {
		return tier;
	}

	@Override
	public void setTier(int tier) {
		this.tier = tier;
	}

	@Override
	public boolean isInputFace(int side) {
		return side <= 1;
	}

	@Override
	public void setRotaryInput(int side, int speed, int length) {
		if (isInputFace(side)) {
			this.input[side] = speed;
			this.length[side] = length;
		}
	}

	@Override
	public int getRotaryInput(int side) {
		if (isInputFace(side)) {
			return this.input[side];
		} else {
			return 0;
		}
	}

	@Override
	public int getRotaryInputLength(int side) {
		if (isInputFace(side)) {
			return this.length[side];
		} else {
			return 0;
		}
	}

	public int getProgressScaled(int scale) {
		if (energyNeeded != 0) {
			return this.progress * scale / energyNeeded;
		} else {
			return 0;
		}
	}

}
