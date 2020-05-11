package net.minecraft.client.audio;

import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundManager$1;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.Source;

class SoundManager$SoundSystemStarterThread extends SoundSystem {

   // $FF: synthetic field
   final SoundManager field_148591_a;


   private SoundManager$SoundSystemStarterThread(SoundManager var1) {
      this.field_148591_a = var1;
   }

   public boolean playing(String var1) {
      Object var2 = SoundSystemConfig.THREAD_SYNC;
      synchronized(SoundSystemConfig.THREAD_SYNC) {
         if(this.soundLibrary == null) {
            return false;
         } else {
            Source var3 = (Source)this.soundLibrary.getSources().get(var1);
            return var3 == null?false:var3.playing() || var3.paused() || var3.preLoad;
         }
      }
   }

   // $FF: synthetic method
   SoundManager$SoundSystemStarterThread(SoundManager var1, SoundManager$1 var2) {
      this(var1);
   }
}
