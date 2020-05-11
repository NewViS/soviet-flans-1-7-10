package net.minecraft.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;

class EntityAITasks$EntityAITaskEntry {

   public EntityAIBase action;
   public int priority;
   // $FF: synthetic field
   final EntityAITasks tasks;


   public EntityAITasks$EntityAITaskEntry(EntityAITasks var1, int var2, EntityAIBase var3) {
      this.tasks = var1;
      this.priority = var2;
      this.action = var3;
   }
}
