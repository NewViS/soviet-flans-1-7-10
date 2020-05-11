package net.minecraft.client.audio;

import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundManager$SoundSystemStarterThread;

class SoundManager$1 implements Runnable {

   // $FF: synthetic field
   final SoundManager field_148631_a;


   SoundManager$1(SoundManager var1) {
      this.field_148631_a = var1;
   }

   public void run() {
      SoundManager.access$002(this.field_148631_a, new SoundManager$SoundSystemStarterThread(this.field_148631_a, (SoundManager$1)null));
      SoundManager.access$202(this.field_148631_a, true);
      SoundManager.access$000(this.field_148631_a).setMasterVolume(SoundManager.access$300(this.field_148631_a).getSoundLevel(SoundCategory.MASTER));
      SoundManager.access$500().info(SoundManager.access$400(), "Sound engine started");
   }
}
