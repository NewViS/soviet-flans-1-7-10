package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderWolf extends RenderLiving {

   private static final ResourceLocation wolfTextures = new ResourceLocation("textures/entity/wolf/wolf.png");
   private static final ResourceLocation tamedWolfTextures = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
   private static final ResourceLocation anrgyWolfTextures = new ResourceLocation("textures/entity/wolf/wolf_angry.png");
   private static final ResourceLocation wolfCollarTextures = new ResourceLocation("textures/entity/wolf/wolf_collar.png");


   public RenderWolf(ModelBase var1, ModelBase var2, float var3) {
      super(var1, var3);
      this.setRenderPassModel(var2);
   }

   protected float handleRotationFloat(EntityWolf var1, float var2) {
      return var1.getTailRotation();
   }

   protected int shouldRenderPass(EntityWolf var1, int var2, float var3) {
      if(var2 == 0 && var1.getWolfShaking()) {
         float var5 = var1.getBrightness(var3) * var1.getShadingWhileShaking(var3);
         this.bindTexture(wolfTextures);
         GL11.glColor3f(var5, var5, var5);
         return 1;
      } else if(var2 == 1 && var1.isTamed()) {
         this.bindTexture(wolfCollarTextures);
         int var4 = var1.getCollarColor();
         GL11.glColor3f(EntitySheep.fleeceColorTable[var4][0], EntitySheep.fleeceColorTable[var4][1], EntitySheep.fleeceColorTable[var4][2]);
         return 1;
      } else {
         return -1;
      }
   }

   protected ResourceLocation getEntityTexture(EntityWolf var1) {
      return var1.isTamed()?tamedWolfTextures:(var1.isAngry()?anrgyWolfTextures:wolfTextures);
   }

   // $FF: synthetic method
   protected float handleRotationFloat(EntityLivingBase var1, float var2) {
      return this.handleRotationFloat((EntityWolf)var1, var2);
   }

}
