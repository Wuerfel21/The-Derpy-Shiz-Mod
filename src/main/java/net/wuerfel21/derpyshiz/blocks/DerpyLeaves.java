package net.wuerfel21.derpyshiz.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DerpyLeaves extends BlockLeaves {
	
	public static final String[][] textures = new String[][] {{"derpyshiz:leaves_ebony", "derpyshiz:leaves_magic"}, {"derpyshiz:leaves_ebony_opaque", "derpyshiz:leaves_magic_opaque"}};
    public static final String[] types = new String[] {"ebony", "magic"};
	
    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube() {return !Minecraft.getMinecraft().gameSettings.fancyGraphics;}
    
    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
    {
        return p_149643_1_.getBlockMetadata(p_149643_2_, p_149643_3_, p_149643_4_) & 3;
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
    	int graphics = Minecraft.getMinecraft().gameSettings.fancyGraphics ? 0:1;
        return (meta & 3) == 1 ? this.field_150129_M[graphics][1] : this.field_150129_M[graphics][0];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        for (int i = 0; i < textures.length; ++i)
        {
            this.field_150129_M[i] = new IIcon[textures[i].length];

            for (int j = 0; j < textures[i].length; ++j)
            {
                this.field_150129_M[i][j] = reg.registerIcon(textures[i][j]);
            }
        }
    }

    public String[] func_150125_e()
    {
        return types;
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 2; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess a, int x, int y, int z) {
		if (a.getBlockMetadata(x, y, z)%2==1) {
			return 0xff69b4;
		} else {
			return super.colorMultiplier(a,x,y,z);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public int getRenderColor(int meta) {
		if (meta%2==1) {
			return 0xff69b4;
		} else {
			return super.getRenderColor(meta);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return true;
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return GameRegistry.findItem("derpyshiz", "sapling");
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta&1;
	}
	
}
