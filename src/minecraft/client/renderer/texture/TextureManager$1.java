package net.minecraft.client.renderer.texture;

import java.util.concurrent.Callable;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;

class TextureManager$1 implements Callable {

   // $FF: synthetic field
   final ITextureObject theTextureObject;
   // $FF: synthetic field
   final TextureManager theTextureManager;


   TextureManager$1(TextureManager var1, ITextureObject var2) {
      this.theTextureManager = var1;
      this.theTextureObject = var2;
   }

   public String call() {
      return this.theTextureObject.getClass().getName();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
