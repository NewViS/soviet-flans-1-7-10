package net.minecraft.entity.ai;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.util.AxisAlignedBB;

public class EntityAIHurtByTarget extends EntityAITarget {

   boolean entityCallsForHelp;
   private int field_142052_b;


   public EntityAIHurtByTarget(EntityCreature var1, boolean var2) {
      super(var1, false);
      this.entityCallsForHelp = var2;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      int var1 = super.taskOwner.func_142015_aE();
      return var1 != this.field_142052_b && this.isSuitableTarget(super.taskOwner.getAITarget(), false);
   }

   public void startExecuting() {
      super.taskOwner.setAttackTarget(super.taskOwner.getAITarget());
      this.field_142052_b = super.taskOwner.func_142015_aE();
      if(this.entityCallsForHelp) {
         double var1 = this.getTargetDistance();
         List var3 = super.taskOwner.worldObj.getEntitiesWithinAABB(super.taskOwner.getClass(), AxisAlignedBB.getBoundingBox(super.taskOwner.posX, super.taskOwner.posY, super.taskOwner.posZ, super.taskOwner.posX + 1.0D, super.taskOwner.posY + 1.0D, super.taskOwner.posZ + 1.0D).expand(var1, 10.0D, var1));
         Iterator var4 = var3.iterator();

         while(var4.hasNext()) {
            EntityCreature var5 = (EntityCreature)var4.next();
            if(super.taskOwner != var5 && var5.getAttackTarget() == null && !var5.isOnSameTeam(super.taskOwner.getAITarget())) {
               var5.setAttackTarget(super.taskOwner.getAITarget());
            }
         }
      }

      super.startExecuting();
   }
}
