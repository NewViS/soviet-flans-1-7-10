package net.minecraft.entity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityLeashKnot extends EntityHanging {

   public EntityLeashKnot(World var1) {
      super(var1);
   }

   public EntityLeashKnot(World var1, int var2, int var3, int var4) {
      super(var1, var2, var3, var4, 0);
      this.setPosition((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D);
   }

   protected void entityInit() {
      super.entityInit();
   }

   public void setDirection(int var1) {}

   public int getWidthPixels() {
      return 9;
   }

   public int getHeightPixels() {
      return 9;
   }

   public boolean isInRangeToRenderDist(double var1) {
      return var1 < 1024.0D;
   }

   public void onBroken(Entity var1) {}

   public boolean writeToNBTOptional(NBTTagCompound var1) {
      return false;
   }

   public void writeEntityToNBT(NBTTagCompound var1) {}

   public void readEntityFromNBT(NBTTagCompound var1) {}

   public boolean interactFirst(EntityPlayer var1) {
      ItemStack var2 = var1.getHeldItem();
      boolean var3 = false;
      double var4;
      List var6;
      Iterator var7;
      EntityLiving var8;
      if(var2 != null && var2.getItem() == Items.lead && !super.worldObj.isRemote) {
         var4 = 7.0D;
         var6 = super.worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(super.posX - var4, super.posY - var4, super.posZ - var4, super.posX + var4, super.posY + var4, super.posZ + var4));
         if(var6 != null) {
            var7 = var6.iterator();

            while(var7.hasNext()) {
               var8 = (EntityLiving)var7.next();
               if(var8.getLeashed() && var8.getLeashedToEntity() == var1) {
                  var8.setLeashedToEntity(this, true);
                  var3 = true;
               }
            }
         }
      }

      if(!super.worldObj.isRemote && !var3) {
         this.setDead();
         if(var1.capabilities.isCreativeMode) {
            var4 = 7.0D;
            var6 = super.worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(super.posX - var4, super.posY - var4, super.posZ - var4, super.posX + var4, super.posY + var4, super.posZ + var4));
            if(var6 != null) {
               var7 = var6.iterator();

               while(var7.hasNext()) {
                  var8 = (EntityLiving)var7.next();
                  if(var8.getLeashed() && var8.getLeashedToEntity() == this) {
                     var8.clearLeashed(true, false);
                  }
               }
            }
         }
      }

      return true;
   }

   public boolean onValidSurface() {
      return super.worldObj.getBlock(super.field_146063_b, super.field_146064_c, super.field_146062_d).getRenderType() == 11;
   }

   public static EntityLeashKnot func_110129_a(World var0, int var1, int var2, int var3) {
      EntityLeashKnot var4 = new EntityLeashKnot(var0, var1, var2, var3);
      var4.forceSpawn = true;
      var0.spawnEntityInWorld(var4);
      return var4;
   }

   public static EntityLeashKnot getKnotForBlock(World var0, int var1, int var2, int var3) {
      List var4 = var0.getEntitiesWithinAABB(EntityLeashKnot.class, AxisAlignedBB.getBoundingBox((double)var1 - 1.0D, (double)var2 - 1.0D, (double)var3 - 1.0D, (double)var1 + 1.0D, (double)var2 + 1.0D, (double)var3 + 1.0D));
      if(var4 != null) {
         Iterator var5 = var4.iterator();

         while(var5.hasNext()) {
            EntityLeashKnot var6 = (EntityLeashKnot)var5.next();
            if(var6.field_146063_b == var1 && var6.field_146064_c == var2 && var6.field_146062_d == var3) {
               return var6;
            }
         }
      }

      return null;
   }
}
