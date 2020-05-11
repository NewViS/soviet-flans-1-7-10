package net.minecraft.client.renderer;

import java.util.concurrent.Callable;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;

class EntityRenderer$3 implements Callable {

   // $FF: synthetic field
   final ScaledResolution theScaledResolution;
   // $FF: synthetic field
   final EntityRenderer theEntityRenderer;


   EntityRenderer$3(EntityRenderer var1, ScaledResolution var2) {
      this.theEntityRenderer = var1;
      this.theScaledResolution = var2;
   }

   public String call() {
      return String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", new Object[]{Integer.valueOf(this.theScaledResolution.getScaledWidth()), Integer.valueOf(this.theScaledResolution.getScaledHeight()), Integer.valueOf(EntityRenderer.access$000(this.theEntityRenderer).displayWidth), Integer.valueOf(EntityRenderer.access$000(this.theEntityRenderer).displayHeight), Integer.valueOf(this.theScaledResolution.getScaleFactor())});
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
