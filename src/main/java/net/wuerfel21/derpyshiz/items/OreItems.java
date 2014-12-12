package net.wuerfel21.derpyshiz.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.blocks.DerpyOres;

public class OreItems extends Item {
	
	public IIcon[] icons = new IIcon[this.numItems];
	
	public OreItems() {
		super();
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setUnlocalizedName("oi");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < this.numItems; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d=stack.getItemDamage();
		if (d < this.numItems) {
			return this.getUnlocalizedName() + "_" + this.names[d];
		} else {
			return this.getUnlocalizedName();
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
	    if (meta > this.numItems) return null;
	    return this.icons[meta];
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
	    for (int i = 0; i < this.numItems; i ++) {
	        this.icons[i] = reg.registerIcon("derpyshiz:"+names[i]);
	    }
	}
	
	@Override
	public boolean isBeaconPayment(ItemStack stack) {
		return true;
	}
	
	public static final int numItems = 16;
	public static final String[] names ={"chunk_amber","fakediamond","ingot_titanium","ruby","turquoise","amethyst","fluorite_brown","fluorite_red","fluorite_pink","ingot_copper","ingot_enderium","ingot_electrimite","gem_darkness","ingot_tin","ingot_lead","gem_wuerfelium"};
	
}
