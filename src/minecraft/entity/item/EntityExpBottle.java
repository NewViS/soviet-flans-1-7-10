package net.minecraft.entity.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityExpBottle extends EntityThrowable {

   public EntityExpBottle(World var1) {
      super(var1);
   }

   public EntityExpBottle(World var1, EntityLivingBase var2) {
      super(var1, var2);
   }

   public EntityExpBottle(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   protected float getGravityVelocity() {
      return 0.07F;
   }

   protected float func_70182_d() {
      return 0.7F;
   }

   protected float func_70183_g() {
      return -20.0F;
   }

   protected void onImpact(MovingObjectPosition var1) {
      if(!super.worldObj.isRemote) {
         super.worldObj.playAuxSFX(2002, (int)Math.round(super.posX), (int)Math.round(super.posY), (int)Math.round(super.posZ), 0);
         int var2 = 3 + super.worldObj.rand.nextInt(5) + super.worldObj.rand.nextInt(5);

         while(var2 > 0) {
            int var3 = EntityXPOrb.getXPSplit(var2);
            var2 -= var3;
            super.worldObj.spawnEntityInWorld(new EntityXPOrb(super.worldObj, super.posX, super.posY, super.posZ, var3));
         }

         this.setDead();
      }

   }
}
