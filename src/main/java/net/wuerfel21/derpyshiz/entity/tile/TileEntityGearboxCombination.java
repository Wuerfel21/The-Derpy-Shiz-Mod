package net.wuerfel21.derpyshiz.entity.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.wuerfel21.derpyshiz.rotary.AxisChain;
import net.wuerfel21.derpyshiz.rotary.IRotaryInput;
import net.wuerfel21.derpyshiz.rotary.IRotaryOutput;
import net.wuerfel21.derpyshiz.rotary.ITieredTE;
import net.wuerfel21.derpyshiz.rotary.RotaryManager;

public class TileEntityGearboxCombination extends AbstractGearbox {

	public TileEntityGearboxCombination() {
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
				int out = 0;
				for (int i = 0; i < 6; i++) {
					if (this.isInputFace(i)) {
						RotaryManager.updateRotaryInput(this, this, i);
						out += RotaryManager.calcLoss(input[i], length[i], this.getTier()==0?3:5);
					}
				}
				
				this.setRotaryOutput(this.dir, out);
				RotaryManager.updateRotaryOutput(this, chain, this, dir);
				if (this.worldObj.getTotalWorldTime() %200 == this.sync_offset) {
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					this.chain.updateChainBlocksToClients(this.worldObj, xCoord, yCoord, zCoord);
				}
			}
			chain.updateVisualPosition();
		}
	}
}
