package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderBoat extends Render {

   private static final ResourceLocation boatTextures = new ResourceLocation("textures/entity/boat.png");
   protected ModelBase modelBoat;


   public RenderBoat() {
      super.shadowSize = 0.5F;
      this.modelBoat = new ModelBoat();
   }

   public void doRender(EntityBoat var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      GL11.glRotatef(180.0F - var8, 0.0F, 1.0F, 0.0F);
      float var10 = (float)var1.getTimeSinceHit() - var9;
      float var11 = var1.getDamageTaken() - var9;
      if(var11 < 0.0F) {
         var11 = 0.0F;
      }

      if(var10 > 0.0F) {
         GL11.glRotatef(MathHelper.sin(var10) * var10 * var11 / 10.0F * (float)var1.getForwardDirection(), 1.0F, 0.0F, 0.0F);
      }

      float var12 = 0.75F;
      GL11.glScalef(var12, var12, var12);
      GL11.glScalef(1.0F / var12, 1.0F / var12, 1.0F / var12);
      this.bindEntityTexture(var1);
      GL11.glScalef(-1.0F, -1.0F, 1.0F);
      this.modelBoat.render(var1, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GL11.glPopMatrix();
   }

   protected ResourceLocation getEntityTexture(EntityBoat var1) {
      return boatTextures;
   }

}
