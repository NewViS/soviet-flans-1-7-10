package net.minecraft.entity;

import java.util.concurrent.Callable;
import net.minecraft.entity.EntityTracker;

class EntityTracker$1 implements Callable {

   // $FF: synthetic field
   final int field_96570_a;
   // $FF: synthetic field
   final EntityTracker theEntityTracker;


   EntityTracker$1(EntityTracker var1, int var2) {
      this.theEntityTracker = var1;
      this.field_96570_a = var2;
   }

   public String call() {
      String var1 = "Once per " + this.field_96570_a + " ticks";
      if(this.field_96570_a == Integer.MAX_VALUE) {
         var1 = "Maximum (" + var1 + ")";
      }

      return var1;
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
