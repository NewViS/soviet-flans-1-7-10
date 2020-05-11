package net.minecraft.client.particle;

import java.util.concurrent.Callable;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;

class EffectRenderer$3 implements Callable {

   // $FF: synthetic field
   final EntityFX field_147901_a;
   // $FF: synthetic field
   final EffectRenderer field_147900_b;


   EffectRenderer$3(EffectRenderer var1, EntityFX var2) {
      this.field_147900_b = var1;
      this.field_147901_a = var2;
   }

   public String call() {
      return this.field_147901_a.toString();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
