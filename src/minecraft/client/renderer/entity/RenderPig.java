package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;

public class RenderPig extends RenderLiving {

   private static final ResourceLocation saddledPigTextures = new ResourceLocation("textures/entity/pig/pig_saddle.png");
   private static final ResourceLocation pigTextures = new ResourceLocation("textures/entity/pig/pig.png");


   public RenderPig(ModelBase var1, ModelBase var2, float var3) {
      super(var1, var3);
      this.setRenderPassModel(var2);
   }

   protected int shouldRenderPass(EntityPig var1, int var2, float var3) {
      if(var2 == 0 && var1.getSaddled()) {
         this.bindTexture(saddledPigTextures);
         return 1;
      } else {
         return -1;
      }
   }

   protected ResourceLocation getEntityTexture(EntityPig var1) {
      return pigTextures;
   }

}
