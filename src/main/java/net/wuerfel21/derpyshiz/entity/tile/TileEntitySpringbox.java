package net.wuerfel21.derpyshiz.entity.tile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.wuerfel21.derpyshiz.rotary.ISneakyRotameter;
import net.wuerfel21.derpyshiz.rotary.RotaryManager;

public class TileEntitySpringbox extends AbstractGearbox implements ISneakyRotameter {

	public long stored = 0;
	public int compOut = 0;
	public int compOutPrev = 0;
	
	public TileEntitySpringbox() {
		super();
	}
	
	@Override
	public void updateEntity() {
		if (this.getWorldObj() != null) {
			this.setTier(this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
			if (!ready) {
				this.rotate(dir);
				ready = true;
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				this.sync_offset = this.worldObj.rand.nextInt(200);
				this.markDirty();
			}
			if (!this.getWorldObj().isRemote) {
				if (this.dir != this.chain.dir) {
					this.rotate(this.dir);
				}
				for (int i = 0; i < 6; i++) {
					if (this.isInputFace(i)) {
						RotaryManager.updateRotaryInput(this, this, i);
					}
				}
				int r = this.input[RotaryManager.getMaxInput(this)];
				int l = this.length[RotaryManager.getMaxInput(this)];
				if (Math.abs(r) > breakSpeed[this.getTier()]) {
					this.worldObj.func_147480_a(xCoord, yCoord, zCoord, true);
					return;
				}
				stored += RotaryManager.calcLoss(r, l, this.getTier()==0?4:6);
				if (Math.abs(stored) > maxStored[this.getTier()]) {
					this.worldObj.func_147480_a(xCoord, yCoord, zCoord, true);
					return;
				}
				int red = this.worldObj.getBlockPowerInput(xCoord, yCoord, zCoord);
				int out = red*outputSpeedMulti[this.getTier()];
				if (stored < 0) {//reverse energy
					out = -out;
					if (out < stored) {
						out = (int) stored;
					}
				} else {
					if (out > stored) {
						out = (int) stored;
					}
				}
				stored -= out;
				this.setRotaryOutput(this.dir, out);
				RotaryManager.updateRotaryOutput(this, chain, this, dir);
				compOutPrev = compOut;
				compOut = (int) Math.ceil((double)(Math.abs(stored)*15)/(double)maxStored[this.getTier()]);
				if (compOut != compOutPrev) {
					this.worldObj.func_147453_f(xCoord, yCoord, zCoord, this.getBlockType());
				}
				if (this.worldObj.getTotalWorldTime() %200 == this.sync_offset) {
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					this.chain.updateChainBlocksToClients(this.worldObj, xCoord, yCoord, zCoord);
				}
			}
			chain.updateVisualPosition();
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setLong("stored", stored);
		tag.setByte("compOut", (byte) compOut);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		stored = tag.getLong("stored");
		compOut = tag.getByte("compOut");
	}
	
	public static final int[] outputSpeedMulti = {6,60};
	public static final int[] maxStored = {15000,75000};

	@Override
	public IChatComponent sneakyRotameter(EntityLivingBase entity) {
		return new ChatComponentTranslation("text.derpyshiz.stored.name").appendText(": "+Long.toString(stored)+" ").appendSibling(new ChatComponentTranslation("text.derpyshiz.mccr.name"));
	}

}
