package net.minecraft.entity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityHanging extends Entity {

   private int tickCounter1;
   public int hangingDirection;
   public int field_146063_b;
   public int field_146064_c;
   public int field_146062_d;


   public EntityHanging(World var1) {
      super(var1);
      super.yOffset = 0.0F;
      this.setSize(0.5F, 0.5F);
   }

   public EntityHanging(World var1, int var2, int var3, int var4, int var5) {
      this(var1);
      this.field_146063_b = var2;
      this.field_146064_c = var3;
      this.field_146062_d = var4;
   }

   protected void entityInit() {}

   public void setDirection(int var1) {
      this.hangingDirection = var1;
      super.prevRotationYaw = super.rotationYaw = (float)(var1 * 90);
      float var2 = (float)this.getWidthPixels();
      float var3 = (float)this.getHeightPixels();
      float var4 = (float)this.getWidthPixels();
      if(var1 != 2 && var1 != 0) {
         var2 = 0.5F;
      } else {
         var4 = 0.5F;
         super.rotationYaw = super.prevRotationYaw = (float)(Direction.rotateOpposite[var1] * 90);
      }

      var2 /= 32.0F;
      var3 /= 32.0F;
      var4 /= 32.0F;
      float var5 = (float)this.field_146063_b + 0.5F;
      float var6 = (float)this.field_146064_c + 0.5F;
      float var7 = (float)this.field_146062_d + 0.5F;
      float var8 = 0.5625F;
      if(var1 == 2) {
         var7 -= var8;
      }

      if(var1 == 1) {
         var5 -= var8;
      }

      if(var1 == 0) {
         var7 += var8;
      }

      if(var1 == 3) {
         var5 += var8;
      }

      if(var1 == 2) {
         var5 -= this.func_70517_b(this.getWidthPixels());
      }

      if(var1 == 1) {
         var7 += this.func_70517_b(this.getWidthPixels());
      }

      if(var1 == 0) {
         var5 += this.func_70517_b(this.getWidthPixels());
      }

      if(var1 == 3) {
         var7 -= this.func_70517_b(this.getWidthPixels());
      }

      var6 += this.func_70517_b(this.getHeightPixels());
      this.setPosition((double)var5, (double)var6, (double)var7);
      float var9 = -0.03125F;
      super.boundingBox.setBounds((double)(var5 - var2 - var9), (double)(var6 - var3 - var9), (double)(var7 - var4 - var9), (double)(var5 + var2 + var9), (double)(var6 + var3 + var9), (double)(var7 + var4 + var9));
   }

   private float func_70517_b(int var1) {
      return var1 == 32?0.5F:(var1 == 64?0.5F:0.0F);
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(this.tickCounter1++ == 100 && !super.worldObj.isRemote) {
         this.tickCounter1 = 0;
         if(!super.isDead && !this.onValidSurface()) {
            this.setDead();
            this.onBroken((Entity)null);
         }
      }

   }

   public boolean onValidSurface() {
      if(!super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty()) {
         return false;
      } else {
         int var1 = Math.max(1, this.getWidthPixels() / 16);
         int var2 = Math.max(1, this.getHeightPixels() / 16);
         int var3 = this.field_146063_b;
         int var4 = this.field_146064_c;
         int var5 = this.field_146062_d;
         if(this.hangingDirection == 2) {
            var3 = MathHelper.floor_double(super.posX - (double)((float)this.getWidthPixels() / 32.0F));
         }

         if(this.hangingDirection == 1) {
            var5 = MathHelper.floor_double(super.posZ - (double)((float)this.getWidthPixels() / 32.0F));
         }

         if(this.hangingDirection == 0) {
            var3 = MathHelper.floor_double(super.posX - (double)((float)this.getWidthPixels() / 32.0F));
         }

         if(this.hangingDirection == 3) {
            var5 = MathHelper.floor_double(super.posZ - (double)((float)this.getWidthPixels() / 32.0F));
         }

         var4 = MathHelper.floor_double(super.posY - (double)((float)this.getHeightPixels() / 32.0F));

         for(int var6 = 0; var6 < var1; ++var6) {
            for(int var7 = 0; var7 < var2; ++var7) {
               Material var8;
               if(this.hangingDirection != 2 && this.hangingDirection != 0) {
                  var8 = super.worldObj.getBlock(this.field_146063_b, var4 + var7, var5 + var6).getMaterial();
               } else {
                  var8 = super.worldObj.getBlock(var3 + var6, var4 + var7, this.field_146062_d).getMaterial();
               }

               if(!var8.isSolid()) {
                  return false;
               }
            }
         }

         List var9 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox);
         Iterator var10 = var9.iterator();

         Entity var11;
         do {
            if(!var10.hasNext()) {
               return true;
            }

            var11 = (Entity)var10.next();
         } while(!(var11 instanceof EntityHanging));

         return false;
      }
   }

   public boolean canBeCollidedWith() {
      return true;
   }

   public boolean hitByEntity(Entity var1) {
      return var1 instanceof EntityPlayer?this.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)var1), 0.0F):false;
   }

   public void func_145781_i(int var1) {
      super.worldObj.func_147450_X();
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         if(!super.isDead && !super.worldObj.isRemote) {
            this.setDead();
            this.setBeenAttacked();
            this.onBroken(var1.getEntity());
         }

         return true;
      }
   }

   public void moveEntity(double var1, double var3, double var5) {
      if(!super.worldObj.isRemote && !super.isDead && var1 * var1 + var3 * var3 + var5 * var5 > 0.0D) {
         this.setDead();
         this.onBroken((Entity)null);
      }

   }

   public void addVelocity(double var1, double var3, double var5) {
      if(!super.worldObj.isRemote && !super.isDead && var1 * var1 + var3 * var3 + var5 * var5 > 0.0D) {
         this.setDead();
         this.onBroken((Entity)null);
      }

   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setByte("Direction", (byte)this.hangingDirection);
      var1.setInteger("TileX", this.field_146063_b);
      var1.setInteger("TileY", this.field_146064_c);
      var1.setInteger("TileZ", this.field_146062_d);
      switch(this.hangingDirection) {
      case 0:
         var1.setByte("Dir", (byte)2);
         break;
      case 1:
         var1.setByte("Dir", (byte)1);
         break;
      case 2:
         var1.setByte("Dir", (byte)0);
         break;
      case 3:
         var1.setByte("Dir", (byte)3);
      }

   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      if(var1.hasKey("Direction", 99)) {
         this.hangingDirection = var1.getByte("Direction");
      } else {
         switch(var1.getByte("Dir")) {
         case 0:
            this.hangingDirection = 2;
            break;
         case 1:
            this.hangingDirection = 1;
            break;
         case 2:
            this.hangingDirection = 0;
            break;
         case 3:
            this.hangingDirection = 3;
         }
      }

      this.field_146063_b = var1.getInteger("TileX");
      this.field_146064_c = var1.getInteger("TileY");
      this.field_146062_d = var1.getInteger("TileZ");
      this.setDirection(this.hangingDirection);
   }

   public abstract int getWidthPixels();

   public abstract int getHeightPixels();

   public abstract void onBroken(Entity var1);

   protected boolean shouldSetPosAfterLoading() {
      return false;
   }
}
