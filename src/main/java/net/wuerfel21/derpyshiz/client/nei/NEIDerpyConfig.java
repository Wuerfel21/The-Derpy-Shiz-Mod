package net.wuerfel21.derpyshiz.client.nei;

import net.wuerfel21.derpyshiz.Main;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.common.Optional;

@Optional.Interface(iface="codechicken.nei.api.IConfigureNEI",modid="notenoughitems",striprefs=true)

public class NEIDerpyConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		
	}

	@Override
	public String getName() {
		return Main.MODNAME;
	}

	@Override
	public String getVersion() {
		return Main.VERSION;
	}

}
