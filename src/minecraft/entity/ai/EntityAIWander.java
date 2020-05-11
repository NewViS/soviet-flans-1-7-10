package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAIWander extends EntityAIBase {

   private EntityCreature entity;
   private double xPosition;
   private double yPosition;
   private double zPosition;
   private double speed;


   public EntityAIWander(EntityCreature var1, double var2) {
      this.entity = var1;
      this.speed = var2;
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      if(this.entity.getAge() >= 100) {
         return false;
      } else if(this.entity.getRNG().nextInt(120) != 0) {
         return false;
      } else {
         Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
         if(var1 == null) {
            return false;
         } else {
            this.xPosition = var1.xCoord;
            this.yPosition = var1.yCoord;
            this.zPosition = var1.zCoord;
            return true;
         }
      }
   }

   public boolean continueExecuting() {
      return !this.entity.getNavigator().noPath();
   }

   public void startExecuting() {
      this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
   }
}
