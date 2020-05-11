package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSkeleton extends RenderBiped {

   private static final ResourceLocation skeletonTextures = new ResourceLocation("textures/entity/skeleton/skeleton.png");
   private static final ResourceLocation witherSkeletonTextures = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");


   public RenderSkeleton() {
      super(new ModelSkeleton(), 0.5F);
   }

   protected void preRenderCallback(EntitySkeleton var1, float var2) {
      if(var1.getSkeletonType() == 1) {
         GL11.glScalef(1.2F, 1.2F, 1.2F);
      }

   }

   protected void func_82422_c() {
      GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
   }

   protected ResourceLocation getEntityTexture(EntitySkeleton var1) {
      return var1.getSkeletonType() == 1?witherSkeletonTextures:skeletonTextures;
   }

}
