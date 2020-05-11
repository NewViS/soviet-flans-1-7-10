package net.minecraft.client.audio;

import net.minecraft.client.audio.ISoundEventAccessor;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundList$SoundEntry;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.util.ResourceLocation;

class SoundHandler$2 implements ISoundEventAccessor {

   final ResourceLocation field_148726_a;
   // $FF: synthetic field
   final String field_148724_b;
   // $FF: synthetic field
   final SoundList$SoundEntry field_148725_c;
   // $FF: synthetic field
   final SoundHandler field_148723_d;


   SoundHandler$2(SoundHandler var1, String var2, SoundList$SoundEntry var3) {
      this.field_148723_d = var1;
      this.field_148724_b = var2;
      this.field_148725_c = var3;
      this.field_148726_a = new ResourceLocation(this.field_148724_b, this.field_148725_c.getSoundEntryName());
   }

   public int func_148721_a() {
      SoundEventAccessorComposite var1 = (SoundEventAccessorComposite)SoundHandler.access$000(this.field_148723_d).getObject(this.field_148726_a);
      return var1 == null?0:var1.func_148721_a();
   }

   public SoundPoolEntry func_148720_g() {
      SoundEventAccessorComposite var1 = (SoundEventAccessorComposite)SoundHandler.access$000(this.field_148723_d).getObject(this.field_148726_a);
      return var1 == null?SoundHandler.missing_sound:var1.func_148720_g();
   }

   // $FF: synthetic method
   public Object func_148720_g() {
      return this.func_148720_g();
   }
}
