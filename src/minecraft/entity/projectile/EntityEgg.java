package net.minecraft.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEgg extends EntityThrowable {

   public EntityEgg(World var1) {
      super(var1);
   }

   public EntityEgg(World var1, EntityLivingBase var2) {
      super(var1, var2);
   }

   public EntityEgg(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   protected void onImpact(MovingObjectPosition var1) {
      if(var1.entityHit != null) {
         var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
      }

      if(!super.worldObj.isRemote && super.rand.nextInt(8) == 0) {
         byte var2 = 1;
         if(super.rand.nextInt(32) == 0) {
            var2 = 4;
         }

         for(int var3 = 0; var3 < var2; ++var3) {
            EntityChicken var4 = new EntityChicken(super.worldObj);
            var4.setGrowingAge(-24000);
            var4.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, 0.0F);
            super.worldObj.spawnEntityInWorld(var4);
         }
      }

      for(int var5 = 0; var5 < 8; ++var5) {
         super.worldObj.spawnParticle("snowballpoof", super.posX, super.posY, super.posZ, 0.0D, 0.0D, 0.0D);
      }

      if(!super.worldObj.isRemote) {
         this.setDead();
      }

   }
}
