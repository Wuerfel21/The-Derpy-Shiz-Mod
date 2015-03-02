package net.wuerfel21.derpyshiz.entity.tile;

import net.wuerfel21.derpyshiz.rotary.RotaryManager;

public class TileEntityDetectorBox extends AbstractGearbox {

	public boolean reverse;
	public boolean reversePrev;
	public int compOut;
	public int compOutPrev;
	
	public TileEntityDetectorBox() {
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
				this.setRotaryOutput(this.dir, RotaryManager.calcLoss(r, l, this.getTier()==0?4:6));
				RotaryManager.updateRotaryOutput(this, chain, this, dir);
				reversePrev = reverse;
				reverse = r<0;
				if (reverse != reversePrev) {
					//wow, redstone is damn hacky
					this.worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType());
				}
				compOutPrev = compOut;
				compOut = (int) Math.ceil((double)(Math.abs(r)*15)/(double)breakSpeed[this.getTier()]);
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
	
}
