package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBat;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderBat extends RenderLiving {

   private static final ResourceLocation batTextures = new ResourceLocation("textures/entity/bat.png");
   private int renderedBatSize;


   public RenderBat() {
      super(new ModelBat(), 0.25F);
      this.renderedBatSize = ((ModelBat)super.mainModel).getBatSize();
   }

   public void doRender(EntityBat var1, double var2, double var4, double var6, float var8, float var9) {
      int var10 = ((ModelBat)super.mainModel).getBatSize();
      if(var10 != this.renderedBatSize) {
         this.renderedBatSize = var10;
         super.mainModel = new ModelBat();
      }

      super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   protected ResourceLocation getEntityTexture(EntityBat var1) {
      return batTextures;
   }

   protected void preRenderCallback(EntityBat var1, float var2) {
      GL11.glScalef(0.35F, 0.35F, 0.35F);
   }

   protected void renderLivingAt(EntityBat var1, double var2, double var4, double var6) {
      super.renderLivingAt(var1, var2, var4, var6);
   }

   protected void rotateCorpse(EntityBat var1, float var2, float var3, float var4) {
      if(!var1.getIsBatHanging()) {
         GL11.glTranslatef(0.0F, MathHelper.cos(var2 * 0.3F) * 0.1F, 0.0F);
      } else {
         GL11.glTranslatef(0.0F, -0.1F, 0.0F);
      }

      super.rotateCorpse(var1, var2, var3, var4);
   }

}
