package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAIOwnerHurtByTarget extends EntityAITarget {

   EntityTameable theDefendingTameable;
   EntityLivingBase theOwnerAttacker;
   private int field_142051_e;


   public EntityAIOwnerHurtByTarget(EntityTameable var1) {
      super(var1, false);
      this.theDefendingTameable = var1;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      if(!this.theDefendingTameable.isTamed()) {
         return false;
      } else {
         EntityLivingBase var1 = this.theDefendingTameable.getOwner();
         if(var1 == null) {
            return false;
         } else {
            this.theOwnerAttacker = var1.getAITarget();
            int var2 = var1.func_142015_aE();
            return var2 != this.field_142051_e && this.isSuitableTarget(this.theOwnerAttacker, false) && this.theDefendingTameable.func_142018_a(this.theOwnerAttacker, var1);
         }
      }
   }

   public void startExecuting() {
      super.taskOwner.setAttackTarget(this.theOwnerAttacker);
      EntityLivingBase var1 = this.theDefendingTameable.getOwner();
      if(var1 != null) {
         this.field_142051_e = var1.func_142015_aE();
      }

      super.startExecuting();
   }
}
