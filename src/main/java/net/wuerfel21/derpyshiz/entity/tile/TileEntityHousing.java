package net.wuerfel21.derpyshiz.entity.tile;

import net.minecraft.tileentity.TileEntity;
import net.wuerfel21.derpyshiz.rotary.ITieredTE;

public class TileEntityHousing extends TileEntity implements ITieredTE {

	public int tier;

	public boolean inInventory;

	@Override
	public boolean canUpdate() {
		return false;
	}
	
	@Override
	public int getTier() {
		if (this.worldObj != null) {
			return this.tier = this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		} else {
			return tier;
		}
	}

	@Override
	public void setTier(int tier) {
		this.tier = tier;
	}

}
