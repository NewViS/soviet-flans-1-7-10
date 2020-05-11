package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderCaveSpider extends RenderSpider {

   private static final ResourceLocation caveSpiderTextures = new ResourceLocation("textures/entity/spider/cave_spider.png");


   public RenderCaveSpider() {
      super.shadowSize *= 0.7F;
   }

   protected void preRenderCallback(EntityCaveSpider var1, float var2) {
      GL11.glScalef(0.7F, 0.7F, 0.7F);
   }

   protected ResourceLocation getEntityTexture(EntityCaveSpider var1) {
      return caveSpiderTextures;
   }

}
