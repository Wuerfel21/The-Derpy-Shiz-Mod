package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class DerpyLogs extends BlockLog {
	
	public static final String[] field_150169_M = new String[] {"ebony", "magic"};
	public static final String[] types = field_150169_M;
	
	public DerpyLogs() {
		super();
		this.setHarvestLevel("axe",1);
	}
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        this.field_150167_a = new IIcon[field_150169_M.length];
        this.field_150166_b = new IIcon[field_150169_M.length];

        for (int i = 0; i < this.field_150167_a.length; ++i)
        {
            this.field_150167_a[i] = reg.registerIcon(this.getTextureName() + "_" + field_150169_M[i]);
            this.field_150166_b[i] = reg.registerIcon(this.getTextureName() + "_" + field_150169_M[i] + "_top");
        }
    }
    
    @Override
	public int getHarvestLevel(int meta) {
		return meta%2==0?1:3;
	}
    
}

