package net.wuerfel21.derpyshiz.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.wuerfel21.derpyshiz.IModeItem;
import net.wuerfel21.derpyshiz.ItemModeHelper;

public class ItemRotameter extends Item implements IModeItem {
	
	public ItemRotameter() {
		this.setTextureName("derpyshiz:rotameter");
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setFull3D();
		this.setUnlocalizedName("derpyshiz.rotameter");
		this.setMaxStackSize(1);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean whatever) {
		super.addInformation(stack, player, list, whatever);
		ItemModeHelper.displayMode(stack, list);
	}
	
	public static final String[] modes = {"mode.derpyshiz.hammer_face.name","mode.derpyshiz.hammer_direction.name"};

	@Override
	public int getNumModes() {
		return 2;
	}

	@Override
	public String getModeName(int index) {
		return modes[index];
	}
	
}
