package net.wuerfel21.derpyshiz.gui;

import org.apache.logging.log4j.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.GuiIds;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCompactEngine;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityMillstone;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null) {
			switch (ID) {
			case GuiIds.MILLSTONE:
				return new ContainerMillstone(player.inventory, (TileEntityMillstone) te);
			case GuiIds.COMPACT_ENGINE:
				return new ContainerCompactEngine(player.inventory, (TileEntityCompactEngine) te);

			default:
				FMLLog.warning("[TheDerpyShizMod] Unknown GUI ID %d, ignored", new Integer(ID));
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null) {
			switch (ID) {
			case GuiIds.MILLSTONE:
				return new GuiMillstone(player.inventory, (TileEntityMillstone) te);
			case GuiIds.COMPACT_ENGINE:
				return new GuiCompactEngine(player.inventory, (TileEntityCompactEngine) te);
			default:
				FMLLog.warning("[TheDerpyShizMod] Unknown GUI ID %d, ignored", new Integer(ID));
			}
		}
		return null;
	}

}
