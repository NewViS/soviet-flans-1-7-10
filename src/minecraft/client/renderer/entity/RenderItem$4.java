package net.minecraft.client.renderer.entity;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;

class RenderItem$4 implements Callable {

   // $FF: synthetic field
   final ItemStack field_147932_a;
   // $FF: synthetic field
   final RenderItem field_147931_b;


   RenderItem$4(RenderItem var1, ItemStack var2) {
      this.field_147931_b = var1;
      this.field_147932_a = var2;
   }

   public String call() {
      return String.valueOf(this.field_147932_a.hasEffect());
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
