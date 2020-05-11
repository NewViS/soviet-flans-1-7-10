package net.minecraft.client.renderer.texture;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class TextureCompass extends TextureAtlasSprite {

   public double currentAngle;
   public double angleDelta;


   public TextureCompass(String var1) {
      super(var1);
   }

   public void updateAnimation() {
      Minecraft var1 = Minecraft.getMinecraft();
      if(var1.theWorld != null && var1.thePlayer != null) {
         this.updateCompass(var1.theWorld, var1.thePlayer.posX, var1.thePlayer.posZ, (double)var1.thePlayer.rotationYaw, false, false);
      } else {
         this.updateCompass((World)null, 0.0D, 0.0D, 0.0D, true, false);
      }

   }

   public void updateCompass(World var1, double var2, double var4, double var6, boolean var8, boolean var9) {
      if(!super.framesTextureData.isEmpty()) {
         double var10 = 0.0D;
         if(var1 != null && !var8) {
            ChunkCoordinates var12 = var1.getSpawnPoint();
            double var13 = (double)var12.posX - var2;
            double var15 = (double)var12.posZ - var4;
            var6 %= 360.0D;
            var10 = -((var6 - 90.0D) * 3.141592653589793D / 180.0D - Math.atan2(var15, var13));
            if(!var1.provider.isSurfaceWorld()) {
               var10 = Math.random() * 3.1415927410125732D * 2.0D;
            }
         }

         if(var9) {
            this.currentAngle = var10;
         } else {
            double var17;
            for(var17 = var10 - this.currentAngle; var17 < -3.141592653589793D; var17 += 6.283185307179586D) {
               ;
            }

            while(var17 >= 3.141592653589793D) {
               var17 -= 6.283185307179586D;
            }

            if(var17 < -1.0D) {
               var17 = -1.0D;
            }

            if(var17 > 1.0D) {
               var17 = 1.0D;
            }

            this.angleDelta += var17 * 0.1D;
            this.angleDelta *= 0.8D;
            this.currentAngle += this.angleDelta;
         }

         int var18;
         for(var18 = (int)((this.currentAngle / 6.283185307179586D + 1.0D) * (double)super.framesTextureData.size()) % super.framesTextureData.size(); var18 < 0; var18 = (var18 + super.framesTextureData.size()) % super.framesTextureData.size()) {
            ;
         }

         if(var18 != super.frameCounter) {
            super.frameCounter = var18;
            TextureUtil.uploadTextureMipmap((int[][])super.framesTextureData.get(super.frameCounter), super.width, super.height, super.originX, super.originY, false, false);
         }

      }
   }
}
