package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderEnderCrystal extends Render {

   private static final ResourceLocation enderCrystalTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal.png");
   private ModelBase field_76995_b;


   public RenderEnderCrystal() {
      super.shadowSize = 0.5F;
      this.field_76995_b = new ModelEnderCrystal(0.0F, true);
   }

   public void doRender(EntityEnderCrystal var1, double var2, double var4, double var6, float var8, float var9) {
      float var10 = (float)var1.innerRotation + var9;
      GL11.glPushMatrix();
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      this.bindTexture(enderCrystalTextures);
      float var11 = MathHelper.sin(var10 * 0.2F) / 2.0F + 0.5F;
      var11 += var11 * var11;
      this.field_76995_b.render(var1, 0.0F, var10 * 3.0F, var11 * 0.2F, 0.0F, 0.0F, 0.0625F);
      GL11.glPopMatrix();
   }

   protected ResourceLocation getEntityTexture(EntityEnderCrystal var1) {
      return enderCrystalTextures;
   }

}
