package net.wuerfel21.derpyshiz.entity;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.DerpyItems;

public class EntityPiggycorn extends EntityPig {
	
	private final EntityAIControlledByPlayer aiControlledByPlayer;
	
	public EntityPiggycorn(World world) {
		super(world);
		this.tasks.taskEntries.clear();
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, this.aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.3F));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Items.cookie, false));
        this.tasks.addTask(4, new EntityAITempt(this, 1.2D, DerpyItems.cookie, false));
        this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
	}
	
	@Override
	public boolean canBeSteered() {
		return true;
	}
	
	/**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
	@Override
    public boolean isBreedingItem(ItemStack p_70877_1_)
    {
        return p_70877_1_ != null && (p_70877_1_.getItem() == Items.cookie || p_70877_1_.getItem() == DerpyItems.cookie);
    }
	
	@Override
	public void onStruckByLightning(EntityLightningBolt p_70077_1_)
    {
        return;
    }
	
	@Override
	public EntityPig createChild(EntityAgeable p_90011_1_)
    {
        return new EntityPiggycorn(this.worldObj);
    }
	
	@Override
	public void dropFewItems(boolean hit,int fortune) {
		super.dropFewItems(hit, fortune);
		this.dropItem(GameRegistry.findItem("derpyshiz", "horn"), 1);
	}
	
}
