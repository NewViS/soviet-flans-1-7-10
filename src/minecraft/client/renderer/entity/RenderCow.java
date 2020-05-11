package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;

public class RenderCow extends RenderLiving {

   private static final ResourceLocation cowTextures = new ResourceLocation("textures/entity/cow/cow.png");


   public RenderCow(ModelBase var1, float var2) {
      super(var1, var2);
   }

   protected ResourceLocation getEntityTexture(EntityCow var1) {
      return cowTextures;
   }

}
