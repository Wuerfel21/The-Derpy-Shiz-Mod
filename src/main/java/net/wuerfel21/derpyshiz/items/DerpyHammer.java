package net.wuerfel21.derpyshiz.items;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.IModeItem;
import net.wuerfel21.derpyshiz.ItemModeHelper;

public class DerpyHammer extends ItemTool implements IModeItem {
	
	public DerpyHammer(ToolMaterial m) {
		super(1f,m,new HashSet());
		this.setMaxDamage(getMaxDamage()/2);
	}
	
	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		Set set = new HashSet();
		set.add("ds_hammer");
		return set;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean whatever) {
		super.addInformation(stack, player, list, whatever);
		ItemModeHelper.displayMode(stack, list);
	}
	
	public static final String[] modes = {"mode.hammer_face.name","mode.hammer_direction.name"};

	@Override
	public int getNumModes() {
		return 2;
	}

	@Override
	public String getModeName(int index) {
		return modes[index];
	}
	
}
