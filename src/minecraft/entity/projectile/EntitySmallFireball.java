package net.minecraft.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySmallFireball extends EntityFireball {

   public EntitySmallFireball(World var1) {
      super(var1);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntitySmallFireball(World var1, EntityLivingBase var2, double var3, double var5, double var7) {
      super(var1, var2, var3, var5, var7);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntitySmallFireball(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.setSize(0.3125F, 0.3125F);
   }

   protected void onImpact(MovingObjectPosition var1) {
      if(!super.worldObj.isRemote) {
         if(var1.entityHit != null) {
            if(!var1.entityHit.isImmuneToFire() && var1.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, super.shootingEntity), 5.0F)) {
               var1.entityHit.setFire(5);
            }
         } else {
            int var2 = var1.blockX;
            int var3 = var1.blockY;
            int var4 = var1.blockZ;
            switch(var1.sideHit) {
            case 0:
               --var3;
               break;
            case 1:
               ++var3;
               break;
            case 2:
               --var4;
               break;
            case 3:
               ++var4;
               break;
            case 4:
               --var2;
               break;
            case 5:
               ++var2;
            }

            if(super.worldObj.isAirBlock(var2, var3, var4)) {
               super.worldObj.setBlock(var2, var3, var4, Blocks.fire);
            }
         }

         this.setDead();
      }

   }

   public boolean canBeCollidedWith() {
      return false;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      return false;
   }
}
