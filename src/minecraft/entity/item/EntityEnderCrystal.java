package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;

public class EntityEnderCrystal extends Entity {

   public int innerRotation;
   public int health;


   public EntityEnderCrystal(World var1) {
      super(var1);
      super.preventEntitySpawning = true;
      this.setSize(2.0F, 2.0F);
      super.yOffset = super.height / 2.0F;
      this.health = 5;
      this.innerRotation = super.rand.nextInt(100000);
   }

   public EntityEnderCrystal(World var1, double var2, double var4, double var6) {
      this(var1);
      this.setPosition(var2, var4, var6);
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void entityInit() {
      super.dataWatcher.addObject(8, Integer.valueOf(this.health));
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      ++this.innerRotation;
      super.dataWatcher.updateObject(8, Integer.valueOf(this.health));
      int var1 = MathHelper.floor_double(super.posX);
      int var2 = MathHelper.floor_double(super.posY);
      int var3 = MathHelper.floor_double(super.posZ);
      if(super.worldObj.provider instanceof WorldProviderEnd && super.worldObj.getBlock(var1, var2, var3) != Blocks.fire) {
         super.worldObj.setBlock(var1, var2, var3, Blocks.fire);
      }

   }

   protected void writeEntityToNBT(NBTTagCompound var1) {}

   protected void readEntityFromNBT(NBTTagCompound var1) {}

   public float getShadowSize() {
      return 0.0F;
   }

   public boolean canBeCollidedWith() {
      return true;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         if(!super.isDead && !super.worldObj.isRemote) {
            this.health = 0;
            if(this.health <= 0) {
               this.setDead();
               if(!super.worldObj.isRemote) {
                  super.worldObj.createExplosion((Entity)null, super.posX, super.posY, super.posZ, 6.0F, true);
               }
            }
         }

         return true;
      }
   }
}
