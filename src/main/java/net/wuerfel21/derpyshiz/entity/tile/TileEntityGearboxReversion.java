package net.wuerfel21.derpyshiz.entity.tile;

import net.wuerfel21.derpyshiz.rotary.RotaryManager;

public class TileEntityGearboxReversion extends AbstractGearbox {

	public TileEntityGearboxReversion() {
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
				if (this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
					//Its powered, do nothing
					this.setRotaryOutput(this.dir, RotaryManager.calcLoss(r, l, this.getTier()==0?4:6));
				} else {
					//Reverse input
					this.setRotaryOutput(this.dir, -RotaryManager.calcLoss(r, l, this.getTier()==0?4:6));
				}
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
