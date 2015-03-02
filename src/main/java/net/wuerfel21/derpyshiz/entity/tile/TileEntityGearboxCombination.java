package net.wuerfel21.derpyshiz.entity.tile;

import net.wuerfel21.derpyshiz.rotary.RotaryManager;

public class TileEntityGearboxCombination extends AbstractGearbox {

	public TileEntityGearboxCombination() {
		super();
	}
	
	@Override
	public void updateEntity() {
		if (this.getWorldObj() != null) {
			this.setTier(this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
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
				int out = 0;
				for (int i = 0; i < 6; i++) {
					if (this.isInputFace(i)) {
						RotaryManager.updateRotaryInput(this, this, i);
						out += RotaryManager.calcLoss(input[i], length[i], this.getTier()==0?3:5);
					}
				}
				if (Math.abs(out) > breakSpeed[this.getTier()]) {
					this.worldObj.func_147480_a(xCoord, yCoord, zCoord, true);
					return;
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
