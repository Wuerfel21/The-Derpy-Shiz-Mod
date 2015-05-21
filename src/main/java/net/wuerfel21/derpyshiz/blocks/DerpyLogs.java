package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.wuerfel21.derpyshiz.IMetaItemBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DerpyLogs extends BlockLog implements IMetaItemBlock{
	
	public static final String[] types = new String[] {"ebony", "magic"};
	
	public DerpyLogs() {
		super();
		this.setHarvestLevel("axe",1);
	}
	
	@Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        this.field_150167_a = new IIcon[types.length];
        this.field_150166_b = new IIcon[types.length];

        for (int i = 0; i < this.field_150167_a.length; ++i)
        {
            this.field_150167_a[i] = reg.registerIcon(this.getTextureName() + "_" + types[i]);
            this.field_150166_b[i] = reg.registerIcon(this.getTextureName() + "_" + types[i] + "_top");
        }
    }
    
    @Override
	public int getHarvestLevel(int meta) {
		return meta%2==0?1:3;
	}
    
    @Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName() + "_" +types[meta%types.length];
	}
    
}

