package net.minecraft.entity.ai;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAvoidEntity;

class EntityAIAvoidEntity$1 implements IEntitySelector {

   // $FF: synthetic field
   final EntityAIAvoidEntity entityAvoiderAI;


   EntityAIAvoidEntity$1(EntityAIAvoidEntity var1) {
      this.entityAvoiderAI = var1;
   }

   public boolean isEntityApplicable(Entity var1) {
      return var1.isEntityAlive() && EntityAIAvoidEntity.access$000(this.entityAvoiderAI).getEntitySenses().canSee(var1);
   }
}
