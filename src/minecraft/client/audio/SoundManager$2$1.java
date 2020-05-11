package net.minecraft.client.audio;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager$2;

class SoundManager$2$1 extends URLConnection {

   // $FF: synthetic field
   final SoundManager$2 field_148593_a;


   SoundManager$2$1(SoundManager$2 var1, URL var2) {
      super(var2);
      this.field_148593_a = var1;
   }

   public void connect() {}

   public InputStream getInputStream() {
      return Minecraft.getMinecraft().getResourceManager().getResource(this.field_148593_a.field_148592_a).getInputStream();
   }
}
