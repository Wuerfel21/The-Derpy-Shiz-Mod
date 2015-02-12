package net.wuerfel21.derpyshiz.blocks;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
import net.wuerfel21.derpyshiz.entity.tile.TileEntityCrank;
import net.wuerfel21.derpyshiz.entity.tile.TileEntityGearbox;

public class BlockGearbox extends BlockAbstractGearbox {

	public BlockGearbox() {
		super("gearbox",TileEntityGearbox.class);
		if (Main.fancyGearbox) {
			this.setBlockTextureName("minecraft:planks_big_oak");
		} else {
			this.setBlockTextureName("derpyshiz:gearbox_ugly");
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (Main.fancyGearbox) {
			switch (meta) {
			default:
			case 0:
				return Blocks.planks.getIcon(0, 5);
			case 1:
				return GameRegistry.findBlock("derpyshiz", "block").getIcon(0, 2);
			}
		}
		return this.icons[meta];
	}

}
