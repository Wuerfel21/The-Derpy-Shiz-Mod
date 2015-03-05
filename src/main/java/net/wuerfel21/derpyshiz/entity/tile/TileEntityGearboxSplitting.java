package net.wuerfel21.derpyshiz.entity.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.rotary.AxisChain;
import net.wuerfel21.derpyshiz.rotary.RotaryManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityGearboxSplitting extends AbstractGearbox {

	public int dir2;
	public AxisChain chain2;

	public TileEntityGearboxSplitting() {
		super();
		this.chain2 = new AxisChain(0, 5000000);
	}

	@Override
	public void updateEntity() {
		if (this.getWorldObj() != null) {
			this.setTier(this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
			if (!this.getWorldObj().isRemote) {
				if (!ready) {
					this.rotate(dir,false);
					this.rotate(dir2,true);
					ready = true;
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					this.sync_offset = this.worldObj.rand.nextInt(200);
					this.markDirty();
				}
				if (this.dir != this.chain.dir) {
					this.rotate(this.dir,false);
				}
				if (this.dir2 != this.chain2.dir) {
					this.rotate(this.dir2,true);
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
				int o = (int) Math.floor(RotaryManager.calcLoss(r, l, this.getTier() == 0 ? 4 : 6)/2);
				this.setRotaryOutput(this.dir, o);
				this.setRotaryOutput(this.dir2, o);
				RotaryManager.updateRotaryOutput(this, chain, this, dir);
				RotaryManager.updateRotaryOutput(this, chain2, this, dir2);
				if (this.worldObj.getTotalWorldTime() % 200 == this.sync_offset) {
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					this.chain.updateChainBlocksToClients(this.worldObj, xCoord, yCoord, zCoord);
					this.chain2.updateChainBlocksToClients(this.worldObj, xCoord, yCoord, zCoord);
				}
			}
			chain.updateVisualPosition();
			chain2.updateVisualPosition();
		}
	}

	@Override
	public boolean isOutputFace(int side) {
		return side == dir || side == dir2;
	}

	@Override
	public boolean isInputFace(int side) {
		return !(side == dir || side == dir2);
	}

	@Override
	public void rotate(int newDir) {
		this.rotate(newDir, false);
	}

	public void rotate(int newDir, boolean sneaky) {
		if (sneaky) {
			// second output
			this.dir2 = newDir;
			if (this.chain2 != null) {
				this.chain2.cleanup(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord);
			}
			this.chain2 = new AxisChain(this.dir2, maxChainLength[this.tier]);
		} else {
			// first output
			this.dir = newDir;
			if (this.chain != null) {
				this.chain.cleanup(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord);
			}
			this.chain = new AxisChain(this.dir, maxChainLength[this.tier]);
		}
	}

	@Override
	public void cleanup() {
		super.cleanup();
		if (this.chain != null) {
			this.chain2.cleanup(worldObj, xCoord, yCoord, zCoord);
		}
	}
	
	@Override
	public Packet getDescriptionPacket() {
		if (!ready) {
			this.setTier(this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
			this.rotate(dir,false);
			this.rotate(dir2,true);
			ready = true;
			this.sync_offset = this.worldObj.rand.nextInt(200);
			this.markDirty();
		}
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("direction", dir);
		tag.setInteger("direction2", dir2);
		tag.setTag("axis", chain.toNetworkNBT());
		tag.setTag("axis2", chain2.toNetworkNBT());
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkg) {
		NBTTagCompound tag = pkg.func_148857_g();
		this.dir = tag.getInteger("direction");
		this.dir2 = tag.getInteger("direction2");
		this.chain.fromNetworkNBT(tag.getCompoundTag("axis"));
		this.chain2.fromNetworkNBT(tag.getCompoundTag("axis2"));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.dir2 = tag.getInteger("direction2");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("direction2", dir2);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		AxisAlignedBB b1;
		AxisAlignedBB b2;
		switch (chain.dir) {
		default:
		case 0:
			b1=AxisAlignedBB.getBoundingBox(xCoord, yCoord-chain.length, zCoord, xCoord+1, yCoord+1, zCoord+1);
			break;
		case 1:
			b1=AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1, yCoord+1+chain.length, zCoord+1);
			break;
		case 2:
			b1=AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord-chain.length, xCoord+1, yCoord+1, zCoord+1);
			break;
		case 3:
			b1=AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1, yCoord+1, zCoord+1+chain.length);
			break;
		case 4:
			b1=AxisAlignedBB.getBoundingBox(xCoord-chain.length, yCoord, zCoord, xCoord+1, yCoord+1, zCoord+1);
			break;
		case 5:
			b1=AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1+chain.length, yCoord+1, zCoord+1);
			break;
		}
		switch (chain2.dir) {
		default:
		case 0:
			b2=AxisAlignedBB.getBoundingBox(xCoord, yCoord-chain2.length, zCoord, xCoord+1, yCoord+1, zCoord+1);
			break;
		case 1:
			b2=AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1, yCoord+1+chain2.length, zCoord+1);
			break;
		case 2:
			b2=AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord-chain2.length, xCoord+1, yCoord+1, zCoord+1);
			break;
		case 3:
			b2=AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1, yCoord+1, zCoord+1+chain2.length);
			break;
		case 4:
			b2=AxisAlignedBB.getBoundingBox(xCoord-chain2.length, yCoord, zCoord, xCoord+1, yCoord+1, zCoord+1);
			break;
		case 5:
			b2=AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1+chain2.length, yCoord+1, zCoord+1);
			break;
		}
		return b1.func_111270_a(b2);
	}
	
	@Override
	public double getMaxRenderDistanceSquared() {
		return Math.pow(Main.gearboxBaseDistance+Math.max(chain.length,chain2.length), 2);
	}
	
}
