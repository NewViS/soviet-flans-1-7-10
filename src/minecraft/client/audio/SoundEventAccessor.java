package net.minecraft.client.audio;

import net.minecraft.client.audio.ISoundEventAccessor;
import net.minecraft.client.audio.SoundPoolEntry;

public class SoundEventAccessor implements ISoundEventAccessor {

   private final SoundPoolEntry field_148739_a;
   private final int field_148738_b;


   SoundEventAccessor(SoundPoolEntry var1, int var2) {
      this.field_148739_a = var1;
      this.field_148738_b = var2;
   }

   public int func_148721_a() {
      return this.field_148738_b;
   }

   public SoundPoolEntry func_148720_g() {
      return new SoundPoolEntry(this.field_148739_a);
   }

   // $FF: synthetic method
   public Object func_148720_g() {
      return this.func_148720_g();
   }
}
