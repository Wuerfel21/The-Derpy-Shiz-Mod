package net.wuerfel21.derpyshiz.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.blocks.DerpyOres;

public class WuerfeliumPickaxe extends DerpyPickaxe {
	
	public IIcon[] icons = new IIcon[2];
	
	public WuerfeliumPickaxe(ToolMaterial material, Item rep, int m) {
		super(material,rep,m);
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		return icons[this.isActive(stack)?1:0];
	}
	
	private boolean isActive(ItemStack stack) {
		return stack.getTagCompound() != null && stack.getTagCompound().getBoolean("active");
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[0];
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int p_77663_4_, boolean inHand) {
		super.onUpdate(stack,worldIn,entityIn,p_77663_4_,inHand);
		if (inHand && worldIn.getTotalWorldTime() % 10 == 0 && !worldIn.isRemote) {
			boolean found = false;
			int x = (int) entityIn.posX;
			int y = (int) entityIn.posY;
			int z = (int) entityIn.posZ;
			for (int i=x-10;i<x+10;i++) {
				for (int j=z-10;j<z+10;j++) {
					for (int k=y-5;k<y+5;k++) {
						Block b = worldIn.getBlock(i, k, j);
						if ((b instanceof BlockOre || b instanceof DerpyOres || b.getUnlocalizedName().contains("ore"))&&b != GameRegistry.findBlock("minecraft", "coal_ore")) {
							found = true;
						}
					}
				}
			}
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) tag = new NBTTagCompound();
			tag.setBoolean("active", found);
			stack.setTagCompound(tag);
		}
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon("derpyshiz:pickaxe_wuerfelium");
		icons[1] = reg.registerIcon("derpyshiz:pickaxe_wuerfelium_active");
	}
	
}
