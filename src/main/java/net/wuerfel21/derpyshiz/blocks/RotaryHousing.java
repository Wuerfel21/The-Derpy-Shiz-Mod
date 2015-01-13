package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.IMetaItemBlock;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityHousing;

public class RotaryHousing extends Block implements ITileEntityProvider, IMetaItemBlock{
	
	public IIcon[] overlay = new IIcon[1];
	public IIcon[] icons = new IIcon[2];
	
	public RotaryHousing() {
		super(Main.machineMaterial);
		this.setHardness(3f);
		this.setBlockName("housing");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHarvestLevel("ds_hammer", 0);
		this.setStepSound(soundTypeWood);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("minecraft:planks_oak");
		} else {
			this.setBlockTextureName("derpyshiz:housing_ugly");
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityHousing();
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		if (!Main.fancyGearbox) {
			icons[0] = reg.registerIcon(this.getTextureName() + "_" + this.types[0]);
			icons[1] = reg.registerIcon(this.getTextureName() + "_" + this.types[1]);
		}
		overlay[0] = reg.registerIcon("derpyshiz:overlay/rotary_output");
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return !Main.fancyGearbox;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (Main.fancyGearbox) {
			switch (meta) {
			default:
			case 0:
				return Blocks.planks.getIcon(0, 0);
			case 1:
				return GameRegistry.findBlock("derpyshiz", "block").getIcon(0, 13);
			}
		}
		return this.icons[meta];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < types.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName() + "_" +types[meta%types.length];
	}
	
	public static final String[] types = { "normal", "advanced" };
	
}
