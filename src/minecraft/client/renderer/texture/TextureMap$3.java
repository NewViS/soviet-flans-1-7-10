package net.minecraft.client.renderer.texture;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;

class TextureMap$3 implements Callable {

   // $FF: synthetic field
   final TextureAtlasSprite field_147974_a;
   // $FF: synthetic field
   final TextureMap field_147973_b;


   TextureMap$3(TextureMap var1, TextureAtlasSprite var2) {
      this.field_147973_b = var1;
      this.field_147974_a = var2;
   }

   public String call() {
      return this.field_147974_a.getFrameCount() + " frames";
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
