package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIDoorInteract;

public class EntityAIOpenDoor extends EntityAIDoorInteract {

   boolean field_75361_i;
   int field_75360_j;


   public EntityAIOpenDoor(EntityLiving var1, boolean var2) {
      super(var1);
      super.theEntity = var1;
      this.field_75361_i = var2;
   }

   public boolean continueExecuting() {
      return this.field_75361_i && this.field_75360_j > 0 && super.continueExecuting();
   }

   public void startExecuting() {
      this.field_75360_j = 20;
      super.field_151504_e.func_150014_a(super.theEntity.worldObj, super.entityPosX, super.entityPosY, super.entityPosZ, true);
   }

   public void resetTask() {
      if(this.field_75361_i) {
         super.field_151504_e.func_150014_a(super.theEntity.worldObj, super.entityPosX, super.entityPosY, super.entityPosZ, false);
      }

   }

   public void updateTask() {
      --this.field_75360_j;
      super.updateTask();
   }
}
