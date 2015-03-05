package net.wuerfel21.derpyshiz.nei;

import net.wuerfel21.derpyshiz.Main;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cpw.mods.fml.common.Optional;

@Optional.Interface(iface="codechicken.nei.api.IConfigureNEI",modid="NotEnoughItems",striprefs=true)

public class NEIDerpyConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new NEIMillstone());
		API.registerUsageHandler(new NEIMillstone());
		API.registerRecipeHandler(new NEICentrifuge());
		API.registerUsageHandler(new NEICentrifuge());
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
