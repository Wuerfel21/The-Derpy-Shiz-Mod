package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import net.minecraft.block.BlockWood;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.IMetaItemBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DerpyPlanks extends BlockWood implements IMetaItemBlock {
	
	public IIcon[] icons = new IIcon[DerpyLogs.types.length];
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = reg.registerIcon(this.getTextureName() + "_" + DerpyLogs.types[i]);
        }
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return meta < icons.length ? icons[meta] : icons[0];
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName()+"_"+DerpyLogs.types[meta];
	}
	
}
