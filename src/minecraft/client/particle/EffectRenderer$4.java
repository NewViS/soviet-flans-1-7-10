package net.minecraft.client.particle;

import java.util.concurrent.Callable;
import net.minecraft.client.particle.EffectRenderer;

class EffectRenderer$4 implements Callable {

   // $FF: synthetic field
   final int field_147904_a;
   // $FF: synthetic field
   final EffectRenderer field_147903_b;


   EffectRenderer$4(EffectRenderer var1, int var2) {
      this.field_147903_b = var1;
      this.field_147904_a = var2;
   }

   public String call() {
      return this.field_147904_a == 0?"MISC_TEXTURE":(this.field_147904_a == 1?"TERRAIN_TEXTURE":(this.field_147904_a == 2?"ITEM_TEXTURE":(this.field_147904_a == 3?"ENTITY_PARTICLE_TEXTURE":"Unknown - " + this.field_147904_a)));
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
