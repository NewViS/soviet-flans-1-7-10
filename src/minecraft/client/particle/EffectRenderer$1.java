package net.minecraft.client.particle;

import java.util.concurrent.Callable;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;

class EffectRenderer$1 implements Callable {

   // $FF: synthetic field
   final EntityFX field_147214_a;
   // $FF: synthetic field
   final EffectRenderer field_147213_b;


   EffectRenderer$1(EffectRenderer var1, EntityFX var2) {
      this.field_147213_b = var1;
      this.field_147214_a = var2;
   }

   public String call() {
      return this.field_147214_a.toString();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
