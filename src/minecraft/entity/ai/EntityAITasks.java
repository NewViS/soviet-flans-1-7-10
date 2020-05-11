package net.minecraft.entity.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry;
import net.minecraft.profiler.Profiler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAITasks {

   private static final Logger logger = LogManager.getLogger();
   private List taskEntries = new ArrayList();
   private List executingTaskEntries = new ArrayList();
   private final Profiler theProfiler;
   private int tickCount;
   private int tickRate = 3;


   public EntityAITasks(Profiler var1) {
      this.theProfiler = var1;
   }

   public void addTask(int var1, EntityAIBase var2) {
      this.taskEntries.add(new EntityAITasks$EntityAITaskEntry(this, var1, var2));
   }

   public void removeTask(EntityAIBase var1) {
      Iterator var2 = this.taskEntries.iterator();

      while(var2.hasNext()) {
         EntityAITasks$EntityAITaskEntry var3 = (EntityAITasks$EntityAITaskEntry)var2.next();
         EntityAIBase var4 = var3.action;
         if(var4 == var1) {
            if(this.executingTaskEntries.contains(var3)) {
               var4.resetTask();
               this.executingTaskEntries.remove(var3);
            }

            var2.remove();
         }
      }

   }

   public void onUpdateTasks() {
      ArrayList var1 = new ArrayList();
      Iterator var2;
      EntityAITasks$EntityAITaskEntry var3;
      if(this.tickCount++ % this.tickRate == 0) {
         var2 = this.taskEntries.iterator();

         while(var2.hasNext()) {
            var3 = (EntityAITasks$EntityAITaskEntry)var2.next();
            boolean var4 = this.executingTaskEntries.contains(var3);
            if(var4) {
               if(this.canUse(var3) && this.canContinue(var3)) {
                  continue;
               }

               var3.action.resetTask();
               this.executingTaskEntries.remove(var3);
            }

            if(this.canUse(var3) && var3.action.shouldExecute()) {
               var1.add(var3);
               this.executingTaskEntries.add(var3);
            }
         }
      } else {
         var2 = this.executingTaskEntries.iterator();

         while(var2.hasNext()) {
            var3 = (EntityAITasks$EntityAITaskEntry)var2.next();
            if(!var3.action.continueExecuting()) {
               var3.action.resetTask();
               var2.remove();
            }
         }
      }

      this.theProfiler.startSection("goalStart");
      var2 = var1.iterator();

      while(var2.hasNext()) {
         var3 = (EntityAITasks$EntityAITaskEntry)var2.next();
         this.theProfiler.startSection(var3.action.getClass().getSimpleName());
         var3.action.startExecuting();
         this.theProfiler.endSection();
      }

      this.theProfiler.endSection();
      this.theProfiler.startSection("goalTick");
      var2 = this.executingTaskEntries.iterator();

      while(var2.hasNext()) {
         var3 = (EntityAITasks$EntityAITaskEntry)var2.next();
         var3.action.updateTask();
      }

      this.theProfiler.endSection();
   }

   private boolean canContinue(EntityAITasks$EntityAITaskEntry var1) {
      this.theProfiler.startSection("canContinue");
      boolean var2 = var1.action.continueExecuting();
      this.theProfiler.endSection();
      return var2;
   }

   private boolean canUse(EntityAITasks$EntityAITaskEntry var1) {
      this.theProfiler.startSection("canUse");
      Iterator var2 = this.taskEntries.iterator();

      while(var2.hasNext()) {
         EntityAITasks$EntityAITaskEntry var3 = (EntityAITasks$EntityAITaskEntry)var2.next();
         if(var3 != var1) {
            if(var1.priority >= var3.priority) {
               if(this.executingTaskEntries.contains(var3) && !this.areTasksCompatible(var1, var3)) {
                  this.theProfiler.endSection();
                  return false;
               }
            } else if(this.executingTaskEntries.contains(var3) && !var3.action.isInterruptible()) {
               this.theProfiler.endSection();
               return false;
            }
         }
      }

      this.theProfiler.endSection();
      return true;
   }

   private boolean areTasksCompatible(EntityAITasks$EntityAITaskEntry var1, EntityAITasks$EntityAITaskEntry var2) {
      return (var1.action.getMutexBits() & var2.action.getMutexBits()) == 0;
   }

}
