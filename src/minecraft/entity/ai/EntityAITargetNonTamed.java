package net.minecraft.entity.ai;

import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAITargetNonTamed extends EntityAINearestAttackableTarget {

   private EntityTameable theTameable;


   public EntityAITargetNonTamed(EntityTameable var1, Class var2, int var3, boolean var4) {
      super(var1, var2, var3, var4);
      this.theTameable = var1;
   }

   public boolean shouldExecute() {
      return !this.theTameable.isTamed() && super.shouldExecute();
   }
}
