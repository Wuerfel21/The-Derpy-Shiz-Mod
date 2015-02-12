package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

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
import net.wuerfel21.derpyshiz.ISmashable;
import net.wuerfel21.derpyshiz.Main;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearboxCombination;

public class BlockCombinationGearbox extends BlockAbstractGearbox {

	public BlockCombinationGearbox() {
		super("gearbox_combination",TileEntityGearboxCombination.class);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("minecraft:planks_spruce");
		} else {
			this.setBlockTextureName("derpyshiz:gearbox_combination_ugly");
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (Main.fancyGearbox) {
			switch (meta) {
			default:
			case 0:
				return Blocks.planks.getIcon(0, 1);
			case 1:
				return Blocks.gold_block.getIcon(0, 0);
			}
		}
		return this.icons[meta];
	}

}
