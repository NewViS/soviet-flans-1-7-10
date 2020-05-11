package net.minecraft.client.renderer.texture;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;

public class TextureClock extends TextureAtlasSprite {

   private double field_94239_h;
   private double field_94240_i;


   public TextureClock(String var1) {
      super(var1);
   }

   public void updateAnimation() {
      if(!super.framesTextureData.isEmpty()) {
         Minecraft var1 = Minecraft.getMinecraft();
         double var2 = 0.0D;
         if(var1.theWorld != null && var1.thePlayer != null) {
            float var4 = var1.theWorld.getCelestialAngle(1.0F);
            var2 = (double)var4;
            if(!var1.theWorld.provider.isSurfaceWorld()) {
               var2 = Math.random();
            }
         }

         double var7;
         for(var7 = var2 - this.field_94239_h; var7 < -0.5D; ++var7) {
            ;
         }

         while(var7 >= 0.5D) {
            --var7;
         }

         if(var7 < -1.0D) {
            var7 = -1.0D;
         }

         if(var7 > 1.0D) {
            var7 = 1.0D;
         }

         this.field_94240_i += var7 * 0.1D;
         this.field_94240_i *= 0.8D;
         this.field_94239_h += this.field_94240_i;

         int var6;
         for(var6 = (int)((this.field_94239_h + 1.0D) * (double)super.framesTextureData.size()) % super.framesTextureData.size(); var6 < 0; var6 = (var6 + super.framesTextureData.size()) % super.framesTextureData.size()) {
            ;
         }

         if(var6 != super.frameCounter) {
            super.frameCounter = var6;
            TextureUtil.uploadTextureMipmap((int[][])super.framesTextureData.get(super.frameCounter), super.width, super.height, super.originX, super.originY, false, false);
         }

      }
   }
}
