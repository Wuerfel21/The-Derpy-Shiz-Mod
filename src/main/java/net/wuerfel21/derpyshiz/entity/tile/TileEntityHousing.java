package net.wuerfel21.derpyshiz.entity.tile;

import net.minecraft.tileentity.TileEntity;
import net.wuerfel21.derpyshiz.rotary.ITieredTE;

public class TileEntityHousing extends TileEntity implements ITieredTE {

	public int tier;

	public boolean inInventory;

	@Override
	public void updateEntity() {
		if (!this.inInventory) {
			this.tier = this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		}
	}

	@Override
	public int getTier() {
		return this.tier;
	}

	@Override
	public void setTier(int tier) {
		this.tier = tier;
	}

}
