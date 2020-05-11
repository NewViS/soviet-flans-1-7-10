package net.minecraft.client.renderer.texture;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;

class TextureMap$1 implements Callable {

   // $FF: synthetic field
   final TextureAtlasSprite field_147980_a;
   // $FF: synthetic field
   final TextureMap field_147979_b;


   TextureMap$1(TextureMap var1, TextureAtlasSprite var2) {
      this.field_147979_b = var1;
      this.field_147980_a = var2;
   }

   public String call() {
      return this.field_147980_a.getIconName();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
