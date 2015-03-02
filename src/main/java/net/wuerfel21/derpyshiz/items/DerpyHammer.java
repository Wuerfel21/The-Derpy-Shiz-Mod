package net.wuerfel21.derpyshiz.items;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.wuerfel21.derpyshiz.IModeItem;
import net.wuerfel21.derpyshiz.ItemModeHelper;

import com.google.common.collect.Multimap;

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
	public Multimap getAttributeModifiers(ItemStack stack) {
		Multimap map = super.getAttributeModifiers(stack);
		map.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 1+this.toolMaterial.getDamageVsEntity(), 0));
		return map;
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
