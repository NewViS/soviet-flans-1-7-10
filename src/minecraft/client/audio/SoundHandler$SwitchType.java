package net.minecraft.client.audio;

import net.minecraft.client.audio.SoundList$SoundEntry$Type;

// $FF: synthetic class
class SoundHandler$SwitchType {

   // $FF: synthetic field
   static final int[] field_148765_a = new int[SoundList$SoundEntry$Type.values().length];


   static {
      try {
         field_148765_a[SoundList$SoundEntry$Type.FILE.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
         ;
      }

      try {
         field_148765_a[SoundList$SoundEntry$Type.SOUND_EVENT.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
         ;
      }

   }
}
