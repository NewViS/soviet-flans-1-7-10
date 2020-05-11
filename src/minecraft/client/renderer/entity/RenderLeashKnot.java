package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelLeashKnot;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderLeashKnot extends Render {

   private static final ResourceLocation leashKnotTextures = new ResourceLocation("textures/entity/lead_knot.png");
   private ModelLeashKnot leashKnotModel = new ModelLeashKnot();


   public void doRender(EntityLeashKnot var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      float var10 = 0.0625F;
      GL11.glEnable('\u803a');
      GL11.glScalef(-1.0F, -1.0F, 1.0F);
      GL11.glEnable(3008);
      this.bindEntityTexture(var1);
      this.leashKnotModel.render(var1, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, var10);
      GL11.glPopMatrix();
   }

   protected ResourceLocation getEntityTexture(EntityLeashKnot var1) {
      return leashKnotTextures;
   }

}
