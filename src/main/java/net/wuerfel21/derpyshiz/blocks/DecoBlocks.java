package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.IMetaItemBlock;

public class DecoBlocks extends Block implements IMetaItemBlock {
	
	public IIcon[] icons = new IIcon[16];
	
	public DecoBlocks() {
		super(Material.iron);
		this.setBlockName("block");
		this.setBlockTextureName("derpyshiz:block");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypeMetal);
		this.setHarvestLevel("pickaxe", 1);
		this.setResistance(5f);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
	    for (int i = 0; i < 16; i ++) {
	        this.icons[i] = reg.registerIcon(this.textureName + "_" + names[i]);
	    }
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
	    return this.icons[meta];
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 16; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	@Override
	public float getBlockHardness(World world,int x,int y,int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta >= 16) return 1.5f;
		else return hardness[meta];
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj,int x,int y,int z,int beaconX,int beaconY,int beaconZ) {
		return true;
	}
	
	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName() + "_" +names[meta%16];
	}
	
	public static final String[] names = {"amber","fakediamond","titanium","ruby","turquoise","amethyst","fluorite_brown","fluorite_red","fluorite_pink","copper","enderium","electrimite","darkness","tin","lead","wuerfelium"};
	public static final float[] hardness = {2f,1f,4f,3f,3f,10f,2f,2f,2f,2f,15f,2f,5f,2f,3f,4f};
}
