package net.minecraft.client.renderer.texture;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;

class TextureMap$2 implements Callable {

   // $FF: synthetic field
   final TextureAtlasSprite field_147977_a;
   // $FF: synthetic field
   final TextureMap field_147976_b;


   TextureMap$2(TextureMap var1, TextureAtlasSprite var2) {
      this.field_147976_b = var1;
      this.field_147977_a = var2;
   }

   public String call() {
      return this.field_147977_a.getIconWidth() + " x " + this.field_147977_a.getIconHeight();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
