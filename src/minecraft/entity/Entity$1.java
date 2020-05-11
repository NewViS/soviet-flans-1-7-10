package net.minecraft.entity;

import java.util.concurrent.Callable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

class Entity$1 implements Callable {

   // $FF: synthetic field
   final Entity theEntity;


   Entity$1(Entity var1) {
      this.theEntity = var1;
   }

   public String call() {
      return EntityList.getEntityString(this.theEntity) + " (" + this.theEntity.getClass().getCanonicalName() + ")";
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
