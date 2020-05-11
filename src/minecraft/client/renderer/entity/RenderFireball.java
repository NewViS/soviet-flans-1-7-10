package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFireball extends Render {

   private float field_77002_a;


   public RenderFireball(float var1) {
      this.field_77002_a = var1;
   }

   public void doRender(EntityFireball var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      this.bindEntityTexture(var1);
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      GL11.glEnable('\u803a');
      float var10 = this.field_77002_a;
      GL11.glScalef(var10 / 1.0F, var10 / 1.0F, var10 / 1.0F);
      IIcon var11 = Items.fire_charge.getIconFromDamage(0);
      Tessellator var12 = Tessellator.instance;
      float var13 = var11.getMinU();
      float var14 = var11.getMaxU();
      float var15 = var11.getMinV();
      float var16 = var11.getMaxV();
      float var17 = 1.0F;
      float var18 = 0.5F;
      float var19 = 0.25F;
      GL11.glRotatef(180.0F - super.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-super.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      var12.startDrawingQuads();
      var12.setNormal(0.0F, 1.0F, 0.0F);
      var12.addVertexWithUV((double)(0.0F - var18), (double)(0.0F - var19), 0.0D, (double)var13, (double)var16);
      var12.addVertexWithUV((double)(var17 - var18), (double)(0.0F - var19), 0.0D, (double)var14, (double)var16);
      var12.addVertexWithUV((double)(var17 - var18), (double)(1.0F - var19), 0.0D, (double)var14, (double)var15);
      var12.addVertexWithUV((double)(0.0F - var18), (double)(1.0F - var19), 0.0D, (double)var13, (double)var15);
      var12.draw();
      GL11.glDisable('\u803a');
      GL11.glPopMatrix();
   }

   protected ResourceLocation getEntityTexture(EntityFireball var1) {
      return TextureMap.locationItemsTexture;
   }
}
