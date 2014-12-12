package net.wuerfel21.derpyshiz.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class WuerfeliumSword extends DerpySword{
	
	public IIcon[] icons = new IIcon[2];
	
	public WuerfeliumSword(ToolMaterial material, Item rep, int m) {
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
		if (inHand && worldIn.getTotalWorldTime() % 10 == 0) {
			boolean found = false;
			AxisAlignedBB box = AxisAlignedBB.getBoundingBox(entityIn.posX-10,entityIn.posY-5,entityIn.posZ-10,entityIn.posX+10,entityIn.posY+5,entityIn.posZ+10);
			List list = worldIn.getEntitiesWithinAABB(IMob.class, box);
			 if (list != null && !list.isEmpty()) {
				 for (int i=0;i<list.size();i++) {
					 Entity e = (Entity)list.get(i);
					 if (e instanceof IMob && !(e instanceof EntityEnderman)) {
						 found = true;
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
		icons[0] = reg.registerIcon("derpyshiz:sword_wuerfelium");
		icons[1] = reg.registerIcon("derpyshiz:sword_wuerfelium_active");
	}
	
}
