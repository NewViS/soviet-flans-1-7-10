package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSpider extends RenderLiving {

   private static final ResourceLocation spiderEyesTextures = new ResourceLocation("textures/entity/spider_eyes.png");
   private static final ResourceLocation spiderTextures = new ResourceLocation("textures/entity/spider/spider.png");


   public RenderSpider() {
      super(new ModelSpider(), 1.0F);
      this.setRenderPassModel(new ModelSpider());
   }

   protected float getDeathMaxRotation(EntitySpider var1) {
      return 180.0F;
   }

   protected int shouldRenderPass(EntitySpider var1, int var2, float var3) {
      if(var2 != 0) {
         return -1;
      } else {
         this.bindTexture(spiderEyesTextures);
         GL11.glEnable(3042);
         GL11.glDisable(3008);
         GL11.glBlendFunc(1, 1);
         if(var1.isInvisible()) {
            GL11.glDepthMask(false);
         } else {
            GL11.glDepthMask(true);
         }

         char var4 = '\uf0f0';
         int var5 = var4 % 65536;
         int var6 = var4 / 65536;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var5 / 1.0F, (float)var6 / 1.0F);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         return 1;
      }
   }

   protected ResourceLocation getEntityTexture(EntitySpider var1) {
      return spiderTextures;
   }

   // $FF: synthetic method
   protected float getDeathMaxRotation(EntityLivingBase var1) {
      return this.getDeathMaxRotation((EntitySpider)var1);
   }

}
