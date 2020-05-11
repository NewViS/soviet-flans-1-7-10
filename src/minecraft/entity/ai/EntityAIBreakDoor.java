package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIDoorInteract;
import net.minecraft.world.EnumDifficulty;

public class EntityAIBreakDoor extends EntityAIDoorInteract {

   private int breakingTime;
   private int field_75358_j = -1;


   public EntityAIBreakDoor(EntityLiving var1) {
      super(var1);
   }

   public boolean shouldExecute() {
      return !super.shouldExecute()?false:(!super.theEntity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")?false:!super.field_151504_e.func_150015_f(super.theEntity.worldObj, super.entityPosX, super.entityPosY, super.entityPosZ));
   }

   public void startExecuting() {
      super.startExecuting();
      this.breakingTime = 0;
   }

   public boolean continueExecuting() {
      double var1 = super.theEntity.getDistanceSq((double)super.entityPosX, (double)super.entityPosY, (double)super.entityPosZ);
      return this.breakingTime <= 240 && !super.field_151504_e.func_150015_f(super.theEntity.worldObj, super.entityPosX, super.entityPosY, super.entityPosZ) && var1 < 4.0D;
   }

   public void resetTask() {
      super.resetTask();
      super.theEntity.worldObj.destroyBlockInWorldPartially(super.theEntity.getEntityId(), super.entityPosX, super.entityPosY, super.entityPosZ, -1);
   }

   public void updateTask() {
      super.updateTask();
      if(super.theEntity.getRNG().nextInt(20) == 0) {
         super.theEntity.worldObj.playAuxSFX(1010, super.entityPosX, super.entityPosY, super.entityPosZ, 0);
      }

      ++this.breakingTime;
      int var1 = (int)((float)this.breakingTime / 240.0F * 10.0F);
      if(var1 != this.field_75358_j) {
         super.theEntity.worldObj.destroyBlockInWorldPartially(super.theEntity.getEntityId(), super.entityPosX, super.entityPosY, super.entityPosZ, var1);
         this.field_75358_j = var1;
      }

      if(this.breakingTime == 240 && super.theEntity.worldObj.difficultySetting == EnumDifficulty.HARD) {
         super.theEntity.worldObj.setBlockToAir(super.entityPosX, super.entityPosY, super.entityPosZ);
         super.theEntity.worldObj.playAuxSFX(1012, super.entityPosX, super.entityPosY, super.entityPosZ, 0);
         super.theEntity.worldObj.playAuxSFX(2001, super.entityPosX, super.entityPosY, super.entityPosZ, Block.getIdFromBlock(super.field_151504_e));
      }

   }
}
