package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderIronGolem extends RenderLiving {

   private static final ResourceLocation ironGolemTextures = new ResourceLocation("textures/entity/iron_golem.png");
   private final ModelIronGolem ironGolemModel;


   public RenderIronGolem() {
      super(new ModelIronGolem(), 0.5F);
      this.ironGolemModel = (ModelIronGolem)super.mainModel;
   }

   public void doRender(EntityIronGolem var1, double var2, double var4, double var6, float var8, float var9) {
      super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   protected ResourceLocation getEntityTexture(EntityIronGolem var1) {
      return ironGolemTextures;
   }

   protected void rotateCorpse(EntityIronGolem var1, float var2, float var3, float var4) {
      super.rotateCorpse(var1, var2, var3, var4);
      if((double)var1.limbSwingAmount >= 0.01D) {
         float var5 = 13.0F;
         float var6 = var1.limbSwing - var1.limbSwingAmount * (1.0F - var4) + 6.0F;
         float var7 = (Math.abs(var6 % var5 - var5 * 0.5F) - var5 * 0.25F) / (var5 * 0.25F);
         GL11.glRotatef(6.5F * var7, 0.0F, 0.0F, 1.0F);
      }
   }

   protected void renderEquippedItems(EntityIronGolem var1, float var2) {
      super.renderEquippedItems(var1, var2);
      if(var1.getHoldRoseTick() != 0) {
         GL11.glEnable('\u803a');
         GL11.glPushMatrix();
         GL11.glRotatef(5.0F + 180.0F * this.ironGolemModel.ironGolemRightArm.rotateAngleX / 3.1415927F, 1.0F, 0.0F, 0.0F);
         GL11.glTranslatef(-0.6875F, 1.25F, -0.9375F);
         GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
         float var3 = 0.8F;
         GL11.glScalef(var3, -var3, var3);
         int var4 = var1.getBrightnessForRender(var2);
         int var5 = var4 % 65536;
         int var6 = var4 / 65536;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var5 / 1.0F, (float)var6 / 1.0F);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bindTexture(TextureMap.locationBlocksTexture);
         super.field_147909_c.renderBlockAsItem(Blocks.red_flower, 0, 1.0F);
         GL11.glPopMatrix();
         GL11.glDisable('\u803a');
      }
   }

   // $FF: synthetic method
   protected void renderEquippedItems(EntityLivingBase var1, float var2) {
      this.renderEquippedItems((EntityIronGolem)var1, var2);
   }

}
