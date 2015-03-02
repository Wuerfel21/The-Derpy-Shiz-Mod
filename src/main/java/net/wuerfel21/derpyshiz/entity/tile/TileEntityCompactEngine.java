package net.wuerfel21.derpyshiz.entity.tile;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.blocks.BlockMillstone;
import net.wuerfel21.derpyshiz.rotary.AxisChain;
import net.wuerfel21.derpyshiz.rotary.IRotaryOutput;
import net.wuerfel21.derpyshiz.rotary.RotaryManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityCompactEngine extends TileEntity implements IRotaryOutput, IInventory {

	public boolean ready = false;
	public int dir = 0;
	public int output;
	public int ticksLeft, ticksForUsedFuel;

	public ItemStack fuel;

	public String name;

	public int sync_offset;

	public AxisChain chain;

	public boolean inInventory;

	public TileEntityCompactEngine() {
		super();
		this.chain = new AxisChain(0, 5000000);
		this.output = 0;
	}

	@Override
	public void updateEntity() {
		if (this.getWorldObj() != null) {
			if (ticksLeft > 0) {
				ticksLeft--;
			}
			if (!this.getWorldObj().isRemote) {
				if (!ready) {
					this.rotate(dir);
					ready = true;
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					this.sync_offset = this.worldObj.rand.nextInt(200);
					this.markDirty();
				}
				if (this.dir != this.chain.dir) {
					this.rotate(this.dir);
				}
				// logic here
				if (ticksLeft > 0) {
					this.setRotaryOutput(dir, 10);
				} else {
					this.setRotaryOutput(dir, 0);
				}
				if (ticksLeft <= 0) {
					if (TileEntityFurnace.isItemFuel(fuel)) {
						ticksLeft = ticksForUsedFuel = TileEntityFurnace.getItemBurnTime(fuel);
						fuel.stackSize--;
						this.setRotaryOutput(dir, 10);
						if (fuel.stackSize <= 0) {
							fuel = fuel.getItem().getContainerItem(fuel);
						}
					}
				}
				RotaryManager.updateRotaryOutput(this, chain, this, dir);
				if (this.worldObj.getTotalWorldTime() % 200 == this.sync_offset) {
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					this.chain.updateChainBlocksToClients(worldObj, xCoord, yCoord, zCoord);
				}
			}
			chain.updateVisualPosition();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		if (tag.hasKey("fuel")) {
			fuel = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("fuel"));
		}
		this.dir = tag.getInteger("direction");
		this.ticksLeft = tag.getInteger("ticksLeft");
		this.ticksForUsedFuel = tag.getInteger("ticksForUsedFuel");
		NBTTagCompound rotary = tag.getCompoundTag("rotary");
		RotaryManager.outputFromNBT(this, rotary);
		if (tag.hasKey("CustomName")) {
			name = tag.getString("CustomName");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		if (fuel != null) {
			tag.setTag("fuel", fuel.writeToNBT(new NBTTagCompound()));
		}
		tag.setInteger("direction", dir);
		tag.setInteger("ticksLeft", ticksLeft);
		tag.setInteger("ticksForUsedFuel", ticksForUsedFuel);
		NBTTagCompound rotary = new NBTTagCompound();
		rotary.setTag("output", RotaryManager.outputToNBT(this));
		tag.setTag("rotary", rotary);
		if (hasCustomInventoryName()) {
			tag.setString("CustomName", name);
		}
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkg) {
		NBTTagCompound tag = pkg.func_148857_g();
		this.dir = tag.getInteger("direction");
		this.chain.fromNetworkNBT(tag.getCompoundTag("axis"));
		this.name = tag.getString("CustomName");
	}

	@Override
	public Packet getDescriptionPacket() {
		if (!ready) {
			this.rotate(dir);
			ready = true;
			this.sync_offset = this.worldObj.rand.nextInt(200);
			this.markDirty();
		}
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("direction", dir);
		tag.setTag("axis", chain.toNetworkNBT());
		if (hasCustomInventoryName()) {
			tag.setString("CustomName", name);
		}
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	@Override
	public boolean isOutputFace(int side) {
		return dir == side;
	}

	@Override
	public int getRotaryOutput(int side) {
		if (isOutputFace(side)) {
			return output;
		} else {
			return 0;
		}
	}

	@Override
	public void setRotaryOutput(int side, int speed) {
		if (isOutputFace(side)) {
			output = speed;
		}
	}

	public void rotate(int newDir) {
		this.dir = newDir;
		if (this.chain != null) {
			this.chain.cleanup(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord);
		}
		this.chain = new AxisChain(this.dir, TileEntityGearbox.maxChainLength[1]);

	}

	public void cleanup() {
		if (this.chain != null) {
			this.chain.cleanup(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord);
		}
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return slot == 0 ? fuel : null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (this.fuel != null) {
			ItemStack itemstack;

			if (this.fuel.stackSize <= amount) {
				itemstack = this.fuel;
				this.fuel = null;
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.fuel.splitStack(amount);

				if (this.fuel.stackSize == 0) {
					this.fuel = null;
				}
				this.markDirty();
				return itemstack;
			}
		}
		this.markDirty();
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if (slot == 0) {
			fuel = stack;
		}
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.name : StatCollector.translateToLocal("container.derpyshiz.compact_engine.name");
	}

	@Override
	public boolean hasCustomInventoryName() {
		return name != null && name.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : p_70300_1_.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

	public void dropInv(World world, int x, int y, int z) {
		Block block = this.getBlockType();
		if (!(block instanceof BlockMillstone)) {
			return;
		}
		if (fuel != null) {
			((BlockMillstone) block).dropBlockAsItem(world, x, y, z, fuel);
		}

	}
	
	public int getTicksLeftScaled(int scale) {
		if (ticksLeft != 0 && ticksForUsedFuel != 0) {
			return this.ticksLeft * scale / ticksForUsedFuel;
		} else {
			return 0;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		switch (chain.dir) {
		default:
		case 0:
			return AxisAlignedBB.getBoundingBox(xCoord, yCoord-chain.length, zCoord, xCoord+1, yCoord+1, zCoord+1);
		case 1:
			return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1, yCoord+1+chain.length, zCoord+1);
		case 2:
			return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord-chain.length, xCoord+1, yCoord+1, zCoord+1);
		case 3:
			return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1, yCoord+1, zCoord+1+chain.length);
		case 4:
			return AxisAlignedBB.getBoundingBox(xCoord-chain.length, yCoord, zCoord, xCoord+1, yCoord+1, zCoord+1);
		case 5:
			return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1+chain.length, yCoord+1, zCoord+1);
		}
	}
	
}
