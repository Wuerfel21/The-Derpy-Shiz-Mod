package net.wuerfel21.derpyshiz.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class DerpyDusts extends Item {
	
	public IIcon[] icons = new IIcon[this.names.length];

	public DerpyDusts() {
		super();
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setUnlocalizedName("dust");
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < names.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int d = stack.getItemDamage();
		if (d < this.names.length) {
			return this.getUnlocalizedName() + "_" + this.names[d];
		} else {
			return this.getUnlocalizedName();
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta > this.names.length)
			return null;
		return this.icons[meta];
	}

	@Override
	public void registerIcons(IIconRegister reg) {
		for (int i = 0; i < names.length; i++) {
			this.icons[i] = reg.registerIcon("derpyshiz:dust_" + names[i]);
		}
	}

	public static final String[] names = {"iron","gold","diamond","ruby","copper","tin","lead","titanium","electrimite","ghast","amethyst","obsidian","amber","enderpearl"};
	
}
