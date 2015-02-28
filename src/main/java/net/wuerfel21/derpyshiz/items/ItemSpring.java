package net.wuerfel21.derpyshiz.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemSpring extends Item {

public IIcon[] icons = new IIcon[this.types.length];
	
	public ItemSpring() {
		super();
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setUnlocalizedName("derpyshiz.spring");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < this.types.length; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < this.types.length) {
			return this.getUnlocalizedName() + "_" + this.types[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
	    if (meta > this.types.length) return null;
	    return this.icons[meta];
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
	    for (int i = 0; i < this.types.length; i ++) {
	        this.icons[i] = reg.registerIcon("derpyshiz:spring_"+types[i]);
	    }
	}
	
	public static final String[] types ={"iron","gold","electrimite"};
	
}
