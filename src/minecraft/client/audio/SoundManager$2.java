package net.minecraft.client.audio;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import net.minecraft.client.audio.SoundManager$2$1;
import net.minecraft.util.ResourceLocation;

final class SoundManager$2 extends URLStreamHandler {

   // $FF: synthetic field
   final ResourceLocation field_148592_a;


   SoundManager$2(ResourceLocation var1) {
      this.field_148592_a = var1;
   }

   protected URLConnection openConnection(URL var1) {
      return new SoundManager$2$1(this, var1);
   }
}
