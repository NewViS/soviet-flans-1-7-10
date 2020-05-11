package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTNTPrimed extends Render {

   private RenderBlocks blockRenderer = new RenderBlocks();


   public RenderTNTPrimed() {
      super.shadowSize = 0.5F;
   }

   public void doRender(EntityTNTPrimed var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      float var10;
      if((float)var1.fuse - var9 + 1.0F < 10.0F) {
         var10 = 1.0F - ((float)var1.fuse - var9 + 1.0F) / 10.0F;
         if(var10 < 0.0F) {
            var10 = 0.0F;
         }

         if(var10 > 1.0F) {
            var10 = 1.0F;
         }

         var10 *= var10;
         var10 *= var10;
         float var11 = 1.0F + var10 * 0.3F;
         GL11.glScalef(var11, var11, var11);
      }

      var10 = (1.0F - ((float)var1.fuse - var9 + 1.0F) / 100.0F) * 0.8F;
      this.bindEntityTexture(var1);
      this.blockRenderer.renderBlockAsItem(Blocks.tnt, 0, var1.getBrightness(var9));
      if(var1.fuse / 5 % 2 == 0) {
         GL11.glDisable(3553);
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 772);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, var10);
         this.blockRenderer.renderBlockAsItem(Blocks.tnt, 0, 1.0F);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(3042);
         GL11.glEnable(2896);
         GL11.glEnable(3553);
      }

      GL11.glPopMatrix();
   }

   protected ResourceLocation getEntityTexture(EntityTNTPrimed var1) {
      return TextureMap.locationBlocksTexture;
   }
}
