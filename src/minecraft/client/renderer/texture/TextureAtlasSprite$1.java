package net.minecraft.client.renderer.texture;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

class TextureAtlasSprite$1 implements Callable {

   // $FF: synthetic field
   final int[][] field_147983_a;
   // $FF: synthetic field
   final TextureAtlasSprite field_147982_b;


   TextureAtlasSprite$1(TextureAtlasSprite var1, int[][] var2) {
      this.field_147982_b = var1;
      this.field_147983_a = var2;
   }

   public String call() {
      StringBuilder var1 = new StringBuilder();
      int[][] var2 = this.field_147983_a;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         int[] var5 = var2[var4];
         if(var1.length() > 0) {
            var1.append(", ");
         }

         var1.append(var5 == null?"null":Integer.valueOf(var5.length));
      }

      return var1.toString();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
