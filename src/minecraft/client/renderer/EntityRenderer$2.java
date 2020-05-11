package net.minecraft.client.renderer;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.EntityRenderer;
import org.lwjgl.input.Mouse;

class EntityRenderer$2 implements Callable {

   // $FF: synthetic field
   final int field_90026_a;
   // $FF: synthetic field
   final int field_90024_b;
   // $FF: synthetic field
   final EntityRenderer theEntityRenderer;


   EntityRenderer$2(EntityRenderer var1, int var2, int var3) {
      this.theEntityRenderer = var1;
      this.field_90026_a = var2;
      this.field_90024_b = var3;
   }

   public String call() {
      return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", new Object[]{Integer.valueOf(this.field_90026_a), Integer.valueOf(this.field_90024_b), Integer.valueOf(Mouse.getX()), Integer.valueOf(Mouse.getY())});
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
