package net.wuerfel21.derpyshiz.items;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.DerpyItems;

public class LongPickaxe extends DerpyPickaxe {
	
	public static boolean AOEEnabled = true;

	public LongPickaxe(ToolMaterial material, Item rep, int m) {
		super(material, rep, m);
	}

}
