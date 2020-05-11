package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEnderPearl extends EntityThrowable {

   public EntityEnderPearl(World var1) {
      super(var1);
   }

   public EntityEnderPearl(World var1, EntityLivingBase var2) {
      super(var1, var2);
   }

   public EntityEnderPearl(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   protected void onImpact(MovingObjectPosition var1) {
      if(var1.entityHit != null) {
         var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
      }

      for(int var2 = 0; var2 < 32; ++var2) {
         super.worldObj.spawnParticle("portal", super.posX, super.posY + super.rand.nextDouble() * 2.0D, super.posZ, super.rand.nextGaussian(), 0.0D, super.rand.nextGaussian());
      }

      if(!super.worldObj.isRemote) {
         if(this.getThrower() != null && this.getThrower() instanceof EntityPlayerMP) {
            EntityPlayerMP var3 = (EntityPlayerMP)this.getThrower();
            if(var3.playerNetServerHandler.func_147362_b().isChannelOpen() && var3.worldObj == super.worldObj) {
               if(this.getThrower().isRiding()) {
                  this.getThrower().mountEntity((Entity)null);
               }

               this.getThrower().setPositionAndUpdate(super.posX, super.posY, super.posZ);
               this.getThrower().fallDistance = 0.0F;
               this.getThrower().attackEntityFrom(DamageSource.fall, 5.0F);
            }
         }

         this.setDead();
      }

   }
}
