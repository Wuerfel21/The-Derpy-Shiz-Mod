package net.wuerfel21.derpyshiz.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class DerpyGears extends Item {
	
public IIcon[] icons = new IIcon[this.numGears];
	
	public DerpyGears() {
		super();
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setUnlocalizedName("derpyshiz.gear");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < this.numGears; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < this.numGears) {
			return this.getUnlocalizedName() + "_" + this.types[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
	    if (meta > this.numGears) return null;
	    return this.icons[meta];
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
	    for (int i = 0; i < this.numGears; i ++) {
	        this.icons[i] = reg.registerIcon("derpyshiz:gear_"+types[i]);
	    }
	}
	
	public static final int numGears = 13;
	public static final String[] types ={"wood","ebony","magic","iron","lasagne","copper","tin","lead","gold","titanium","ruby","electrimite","enderium"};
	
}
