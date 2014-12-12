package net.wuerfel21.derpyshiz.hacks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class DerpyShears extends ItemShears {
	
	public IIcon icon;
	
	public DerpyShears() {
		super();
		this.setUnlocalizedName("shears");
		this.setTextureName("minecraft:shears");
	}
	
	//Copied directly from mojang code with little modification
	@Override
    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        return p_150893_2_ != Blocks.web && p_150893_2_.getMaterial() != Material.leaves ? (p_150893_2_.getMaterial() == Material.cloth ? 5.0F : super.func_150893_a(p_150893_1_, p_150893_2_)) : 15.0F;
    }
	
	@Override
	public IIcon getIconFromDamage(int d) {
		return GameRegistry.findItem("derpyshiz", "dont_look_at_this").getIconFromDamage(0);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack,World p_150894_2_,Block p_150894_3_,int p_150894_4_,int p_150894_5_,int p_150894_6_,EntityLivingBase player) {
		stack.damageItem(1, player);
		return false;
		
	}
	
}
