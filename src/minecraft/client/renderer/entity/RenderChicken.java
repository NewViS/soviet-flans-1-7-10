package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderChicken extends RenderLiving {

   private static final ResourceLocation chickenTextures = new ResourceLocation("textures/entity/chicken.png");


   public RenderChicken(ModelBase var1, float var2) {
      super(var1, var2);
   }

   public void doRender(EntityChicken var1, double var2, double var4, double var6, float var8, float var9) {
      super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   protected ResourceLocation getEntityTexture(EntityChicken var1) {
      return chickenTextures;
   }

   protected float handleRotationFloat(EntityChicken var1, float var2) {
      float var3 = var1.field_70888_h + (var1.field_70886_e - var1.field_70888_h) * var2;
      float var4 = var1.field_70884_g + (var1.destPos - var1.field_70884_g) * var2;
      return (MathHelper.sin(var3) + 1.0F) * var4;
   }

   // $FF: synthetic method
   protected float handleRotationFloat(EntityLivingBase var1, float var2) {
      return this.handleRotationFloat((EntityChicken)var1, var2);
   }

}
