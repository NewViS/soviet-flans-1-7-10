package net.minecraft.entity.ai;

import java.util.Collections;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget$1;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget$Sorter;
import net.minecraft.entity.ai.EntityAITarget;

public class EntityAINearestAttackableTarget extends EntityAITarget {

   private final Class targetClass;
   private final int targetChance;
   private final EntityAINearestAttackableTarget$Sorter theNearestAttackableTargetSorter;
   private final IEntitySelector targetEntitySelector;
   private EntityLivingBase targetEntity;


   public EntityAINearestAttackableTarget(EntityCreature var1, Class var2, int var3, boolean var4) {
      this(var1, var2, var3, var4, false);
   }

   public EntityAINearestAttackableTarget(EntityCreature var1, Class var2, int var3, boolean var4, boolean var5) {
      this(var1, var2, var3, var4, var5, (IEntitySelector)null);
   }

   public EntityAINearestAttackableTarget(EntityCreature var1, Class var2, int var3, boolean var4, boolean var5, IEntitySelector var6) {
      super(var1, var4, var5);
      this.targetClass = var2;
      this.targetChance = var3;
      this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget$Sorter(var1);
      this.setMutexBits(1);
      this.targetEntitySelector = new EntityAINearestAttackableTarget$1(this, var6);
   }

   public boolean shouldExecute() {
      if(this.targetChance > 0 && super.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
         return false;
      } else {
         double var1 = this.getTargetDistance();
         List var3 = super.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, super.taskOwner.boundingBox.expand(var1, 4.0D, var1), this.targetEntitySelector);
         Collections.sort(var3, this.theNearestAttackableTargetSorter);
         if(var3.isEmpty()) {
            return false;
         } else {
            this.targetEntity = (EntityLivingBase)var3.get(0);
            return true;
         }
      }
   }

   public void startExecuting() {
      super.taskOwner.setAttackTarget(this.targetEntity);
      super.startExecuting();
   }
}
