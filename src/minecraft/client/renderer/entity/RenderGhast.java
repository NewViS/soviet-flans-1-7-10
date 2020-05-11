package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderGhast extends RenderLiving {

   private static final ResourceLocation ghastTextures = new ResourceLocation("textures/entity/ghast/ghast.png");
   private static final ResourceLocation ghastShootingTextures = new ResourceLocation("textures/entity/ghast/ghast_shooting.png");


   public RenderGhast() {
      super(new ModelGhast(), 0.5F);
   }

   protected ResourceLocation getEntityTexture(EntityGhast var1) {
      return var1.func_110182_bF()?ghastShootingTextures:ghastTextures;
   }

   protected void preRenderCallback(EntityGhast var1, float var2) {
      float var4 = ((float)var1.prevAttackCounter + (float)(var1.attackCounter - var1.prevAttackCounter) * var2) / 20.0F;
      if(var4 < 0.0F) {
         var4 = 0.0F;
      }

      var4 = 1.0F / (var4 * var4 * var4 * var4 * var4 * 2.0F + 1.0F);
      float var5 = (8.0F + var4) / 2.0F;
      float var6 = (8.0F + 1.0F / var4) / 2.0F;
      GL11.glScalef(var6, var5, var6);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

}
