package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSlime extends RenderLiving {

   private static final ResourceLocation slimeTextures = new ResourceLocation("textures/entity/slime/slime.png");
   private ModelBase scaleAmount;


   public RenderSlime(ModelBase var1, ModelBase var2, float var3) {
      super(var1, var3);
      this.scaleAmount = var2;
   }

   protected int shouldRenderPass(EntitySlime var1, int var2, float var3) {
      if(var1.isInvisible()) {
         return 0;
      } else if(var2 == 0) {
         this.setRenderPassModel(this.scaleAmount);
         GL11.glEnable(2977);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         return 1;
      } else {
         if(var2 == 1) {
            GL11.glDisable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }

         return -1;
      }
   }

   protected void preRenderCallback(EntitySlime var1, float var2) {
      float var3 = (float)var1.getSlimeSize();
      float var4 = (var1.prevSquishFactor + (var1.squishFactor - var1.prevSquishFactor) * var2) / (var3 * 0.5F + 1.0F);
      float var5 = 1.0F / (var4 + 1.0F);
      GL11.glScalef(var5 * var3, 1.0F / var5 * var3, var5 * var3);
   }

   protected ResourceLocation getEntityTexture(EntitySlime var1) {
      return slimeTextures;
   }

}
