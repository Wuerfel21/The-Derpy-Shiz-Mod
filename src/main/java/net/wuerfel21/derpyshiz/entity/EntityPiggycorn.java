package net.wuerfel21.derpyshiz.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.wuerfel21.derpyshiz.DerpyItems;
import net.wuerfel21.derpyshiz.items.DerpySpawnEgg;
import cpw.mods.fml.common.registry.GameRegistry;

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
	public ItemStack getPickedResult(MovingObjectPosition target) {
		ItemStack stack = new ItemStack(DerpyItems.spawnegg,1,0);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("entity", DerpySpawnEgg.entities[0]);
		tag.setInteger("base", DerpySpawnEgg.bases[0]);
		tag.setInteger("spot", DerpySpawnEgg.spots[0]);
		stack.setTagCompound(tag);
		return stack;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15);
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
	
	@Override
	public boolean interact(EntityPlayer player)
    {
		super.interact(player);
        ItemStack itemstack = player.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() == DerpyItems.spawnegg)
        {
            if (!this.worldObj.isRemote)
            {
                Class oclass = (Class) EntityList.stringToClassMapping.get(itemstack.getTagCompound().getString("entity"));

                if (oclass != null && oclass.isAssignableFrom(this.getClass()))
                {
                    EntityAgeable entityageable = this.createChild(this);

                    if (entityageable != null)
                    {
                        entityageable.setGrowingAge(-24000);
                        entityageable.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
                        this.worldObj.spawnEntityInWorld(entityageable);

                        if (itemstack.hasDisplayName())
                        {
                            entityageable.setCustomNameTag(itemstack.getDisplayName());
                        }

                        if (!player.capabilities.isCreativeMode)
                        {
                            --itemstack.stackSize;

                            if (itemstack.stackSize <= 0)
                            {
                                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                            }
                        }
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }
	
}
