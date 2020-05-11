package net.minecraft.client.renderer.entity;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderEnderman extends RenderLiving {

   private static final ResourceLocation endermanEyesTexture = new ResourceLocation("textures/entity/enderman/enderman_eyes.png");
   private static final ResourceLocation endermanTextures = new ResourceLocation("textures/entity/enderman/enderman.png");
   private ModelEnderman endermanModel;
   private Random rnd = new Random();


   public RenderEnderman() {
      super(new ModelEnderman(), 0.5F);
      this.endermanModel = (ModelEnderman)super.mainModel;
      this.setRenderPassModel(this.endermanModel);
   }

   public void doRender(EntityEnderman var1, double var2, double var4, double var6, float var8, float var9) {
      this.endermanModel.isCarrying = var1.func_146080_bZ().getMaterial() != Material.air;
      this.endermanModel.isAttacking = var1.isScreaming();
      if(var1.isScreaming()) {
         double var10 = 0.02D;
         var2 += this.rnd.nextGaussian() * var10;
         var6 += this.rnd.nextGaussian() * var10;
      }

      super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   protected ResourceLocation getEntityTexture(EntityEnderman var1) {
      return endermanTextures;
   }

   protected void renderEquippedItems(EntityEnderman var1, float var2) {
      super.renderEquippedItems(var1, var2);
      if(var1.func_146080_bZ().getMaterial() != Material.air) {
         GL11.glEnable('\u803a');
         GL11.glPushMatrix();
         float var3 = 0.5F;
         GL11.glTranslatef(0.0F, 0.6875F, -0.75F);
         var3 *= 1.0F;
         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(-var3, -var3, var3);
         int var4 = var1.getBrightnessForRender(var2);
         int var5 = var4 % 65536;
         int var6 = var4 / 65536;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var5 / 1.0F, (float)var6 / 1.0F);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bindTexture(TextureMap.locationBlocksTexture);
         super.field_147909_c.renderBlockAsItem(var1.func_146080_bZ(), var1.getCarryingData(), 1.0F);
         GL11.glPopMatrix();
         GL11.glDisable('\u803a');
      }

   }

   protected int shouldRenderPass(EntityEnderman var1, int var2, float var3) {
      if(var2 != 0) {
         return -1;
      } else {
         this.bindTexture(endermanEyesTexture);
         float var4 = 1.0F;
         GL11.glEnable(3042);
         GL11.glDisable(3008);
         GL11.glBlendFunc(1, 1);
         GL11.glDisable(2896);
         if(var1.isInvisible()) {
            GL11.glDepthMask(false);
         } else {
            GL11.glDepthMask(true);
         }

         char var5 = '\uf0f0';
         int var6 = var5 % 65536;
         int var7 = var5 / 65536;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var6 / 1.0F, (float)var7 / 1.0F);
         GL11.glEnable(2896);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, var4);
         return 1;
      }
   }

   // $FF: synthetic method
   protected void renderEquippedItems(EntityLivingBase var1, float var2) {
      this.renderEquippedItems((EntityEnderman)var1, var2);
   }

}
