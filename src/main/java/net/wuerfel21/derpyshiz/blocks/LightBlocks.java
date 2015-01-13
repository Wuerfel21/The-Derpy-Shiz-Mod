package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
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
import net.wuerfel21.derpyshiz.ColoredLightHelper;
import net.wuerfel21.derpyshiz.IMetaItemBlock;

public class LightBlocks extends Block implements IMetaItemBlock {

	IIcon[] icons = new IIcon[this.names.length];

	public LightBlocks() {
		super(Material.redstoneLight);
		this.setBlockName("lamp");
		this.setBlockTextureName("derpyshiz:lamp");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypeGlass);
		this.setLightLevel(1.0f);

		this.setHardness(0.3f);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		if (Loader.isModLoaded("coloredlightscore")) {
			switch (world.getBlockMetadata(x, y, z)) {
			default:
			case 0:
				return ColoredLightHelper.makeRGBLightValue(15, 0, 0, 15);
			case 1:
				return ColoredLightHelper.makeRGBLightValue(0, 15, 0, 15);
			case 2:
				return ColoredLightHelper.makeRGBLightValue(0, 0, 15, 15);
			}

		} else {
			return super.getLightValue(world, x, y, z);
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < names.length; i++) {
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
		for (int i = 0; i < this.names.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName() + "_" +names[meta%names.length];
	}
	
	public static final String[] names = { "fluorite_red", "fluorite_green", "fluorite_blue" };
}
