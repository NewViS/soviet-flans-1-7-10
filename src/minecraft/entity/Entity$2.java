package net.minecraft.entity;

import java.util.concurrent.Callable;
import net.minecraft.entity.Entity;

class Entity$2 implements Callable {

   // $FF: synthetic field
   final Entity theEntity;


   Entity$2(Entity var1) {
      this.theEntity = var1;
   }

   public String call() {
      return this.theEntity.getCommandSenderName();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
