package net.wuerfel21.derpyshiz.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.IModeItem;
import net.wuerfel21.derpyshiz.ItemModeHelper;

public class LongSword extends DerpySword {

	public static boolean AOEEnabled = true;
	
	public LongSword(ToolMaterial material, Item rep, int m) {
		super(material, rep, m);
	}

}
