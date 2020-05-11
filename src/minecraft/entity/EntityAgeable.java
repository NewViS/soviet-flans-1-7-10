package net.minecraft.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntityAgeable extends EntityCreature {

   private float field_98056_d = -1.0F;
   private float field_98057_e;


   public EntityAgeable(World var1) {
      super(var1);
   }

   public abstract EntityAgeable createChild(EntityAgeable var1);

   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.inventory.getCurrentItem();
      if(var2 != null && var2.getItem() == Items.spawn_egg) {
         if(!super.worldObj.isRemote) {
            Class var3 = EntityList.getClassFromID(var2.getItemDamage());
            if(var3 != null && var3.isAssignableFrom(this.getClass())) {
               EntityAgeable var4 = this.createChild(this);
               if(var4 != null) {
                  var4.setGrowingAge(-24000);
                  var4.setLocationAndAngles(super.posX, super.posY, super.posZ, 0.0F, 0.0F);
                  super.worldObj.spawnEntityInWorld(var4);
                  if(var2.hasDisplayName()) {
                     var4.setCustomNameTag(var2.getDisplayName());
                  }

                  if(!var1.capabilities.isCreativeMode) {
                     --var2.stackSize;
                     if(var2.stackSize <= 0) {
                        var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
                     }
                  }
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(12, new Integer(0));
   }

   public int getGrowingAge() {
      return super.dataWatcher.getWatchableObjectInt(12);
   }

   public void addGrowth(int var1) {
      int var2 = this.getGrowingAge();
      var2 += var1 * 20;
      if(var2 > 0) {
         var2 = 0;
      }

      this.setGrowingAge(var2);
   }

   public void setGrowingAge(int var1) {
      super.dataWatcher.updateObject(12, Integer.valueOf(var1));
      this.setScaleForAge(this.isChild());
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("Age", this.getGrowingAge());
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.setGrowingAge(var1.getInteger("Age"));
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if(super.worldObj.isRemote) {
         this.setScaleForAge(this.isChild());
      } else {
         int var1 = this.getGrowingAge();
         if(var1 < 0) {
            ++var1;
            this.setGrowingAge(var1);
         } else if(var1 > 0) {
            --var1;
            this.setGrowingAge(var1);
         }
      }

   }

   public boolean isChild() {
      return this.getGrowingAge() < 0;
   }

   public void setScaleForAge(boolean var1) {
      this.setScale(var1?0.5F:1.0F);
   }

   protected final void setSize(float var1, float var2) {
      boolean var3 = this.field_98056_d > 0.0F;
      this.field_98056_d = var1;
      this.field_98057_e = var2;
      if(!var3) {
         this.setScale(1.0F);
      }

   }

   protected final void setScale(float var1) {
      super.setSize(this.field_98056_d * var1, this.field_98057_e * var1);
   }
}
