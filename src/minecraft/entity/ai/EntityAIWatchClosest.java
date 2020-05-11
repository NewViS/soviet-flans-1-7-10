package net.minecraft.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIWatchClosest extends EntityAIBase {

   private EntityLiving theWatcher;
   protected Entity closestEntity;
   private float maxDistanceForPlayer;
   private int lookTime;
   private float field_75331_e;
   private Class watchedClass;


   public EntityAIWatchClosest(EntityLiving var1, Class var2, float var3) {
      this.theWatcher = var1;
      this.watchedClass = var2;
      this.maxDistanceForPlayer = var3;
      this.field_75331_e = 0.02F;
      this.setMutexBits(2);
   }

   public EntityAIWatchClosest(EntityLiving var1, Class var2, float var3, float var4) {
      this.theWatcher = var1;
      this.watchedClass = var2;
      this.maxDistanceForPlayer = var3;
      this.field_75331_e = var4;
      this.setMutexBits(2);
   }

   public boolean shouldExecute() {
      if(this.theWatcher.getRNG().nextFloat() >= this.field_75331_e) {
         return false;
      } else {
         if(this.theWatcher.getAttackTarget() != null) {
            this.closestEntity = this.theWatcher.getAttackTarget();
         }

         if(this.watchedClass == EntityPlayer.class) {
            this.closestEntity = this.theWatcher.worldObj.getClosestPlayerToEntity(this.theWatcher, (double)this.maxDistanceForPlayer);
         } else {
            this.closestEntity = this.theWatcher.worldObj.findNearestEntityWithinAABB(this.watchedClass, this.theWatcher.boundingBox.expand((double)this.maxDistanceForPlayer, 3.0D, (double)this.maxDistanceForPlayer), this.theWatcher);
         }

         return this.closestEntity != null;
      }
   }

   public boolean continueExecuting() {
      return !this.closestEntity.isEntityAlive()?false:(this.theWatcher.getDistanceSqToEntity(this.closestEntity) > (double)(this.maxDistanceForPlayer * this.maxDistanceForPlayer)?false:this.lookTime > 0);
   }

   public void startExecuting() {
      this.lookTime = 40 + this.theWatcher.getRNG().nextInt(40);
   }

   public void resetTask() {
      this.closestEntity = null;
   }

   public void updateTask() {
      this.theWatcher.getLookHelper().setLookPosition(this.closestEntity.posX, this.closestEntity.posY + (double)this.closestEntity.getEyeHeight(), this.closestEntity.posZ, 10.0F, (float)this.theWatcher.getVerticalFaceSpeed());
      --this.lookTime;
   }
}
