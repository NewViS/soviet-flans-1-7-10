package net.minecraft.client.renderer;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.EntityRenderer;

class EntityRenderer$1 implements Callable {

   // $FF: synthetic field
   final EntityRenderer entityRender;


   EntityRenderer$1(EntityRenderer var1) {
      this.entityRender = var1;
   }

   public String call() {
      return EntityRenderer.access$000(this.entityRender).currentScreen.getClass().getCanonicalName();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
