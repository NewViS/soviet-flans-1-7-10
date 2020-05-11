package net.minecraft.client.renderer.entity;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public abstract class RendererLivingEntity extends Render {

   private static final Logger logger = LogManager.getLogger();
   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
   protected ModelBase mainModel;
   protected ModelBase renderPassModel;


   public RendererLivingEntity(ModelBase var1, float var2) {
      this.mainModel = var1;
      super.shadowSize = var2;
   }

   public void setRenderPassModel(ModelBase var1) {
      this.renderPassModel = var1;
   }

   private float interpolateRotation(float var1, float var2, float var3) {
      float var4;
      for(var4 = var2 - var1; var4 < -180.0F; var4 += 360.0F) {
         ;
      }

      while(var4 >= 180.0F) {
         var4 -= 360.0F;
      }

      return var1 + var3 * var4;
   }

   public void doRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      this.mainModel.onGround = this.renderSwingProgress(var1, var9);
      if(this.renderPassModel != null) {
         this.renderPassModel.onGround = this.mainModel.onGround;
      }

      this.mainModel.isRiding = var1.isRiding();
      if(this.renderPassModel != null) {
         this.renderPassModel.isRiding = this.mainModel.isRiding;
      }

      this.mainModel.isChild = var1.isChild();
      if(this.renderPassModel != null) {
         this.renderPassModel.isChild = this.mainModel.isChild;
      }

      try {
         float var10 = this.interpolateRotation(var1.prevRenderYawOffset, var1.renderYawOffset, var9);
         float var11 = this.interpolateRotation(var1.prevRotationYawHead, var1.rotationYawHead, var9);
         float var13;
         if(var1.isRiding() && var1.ridingEntity instanceof EntityLivingBase) {
            EntityLivingBase var12 = (EntityLivingBase)var1.ridingEntity;
            var10 = this.interpolateRotation(var12.prevRenderYawOffset, var12.renderYawOffset, var9);
            var13 = MathHelper.wrapAngleTo180_float(var11 - var10);
            if(var13 < -85.0F) {
               var13 = -85.0F;
            }

            if(var13 >= 85.0F) {
               var13 = 85.0F;
            }

            var10 = var11 - var13;
            if(var13 * var13 > 2500.0F) {
               var10 += var13 * 0.2F;
            }
         }

         float var26 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var9;
         this.renderLivingAt(var1, var2, var4, var6);
         var13 = this.handleRotationFloat(var1, var9);
         this.rotateCorpse(var1, var13, var10, var9);
         float var14 = 0.0625F;
         GL11.glEnable('\u803a');
         GL11.glScalef(-1.0F, -1.0F, 1.0F);
         this.preRenderCallback(var1, var9);
         GL11.glTranslatef(0.0F, -24.0F * var14 - 0.0078125F, 0.0F);
         float var15 = var1.prevLimbSwingAmount + (var1.limbSwingAmount - var1.prevLimbSwingAmount) * var9;
         float var16 = var1.limbSwing - var1.limbSwingAmount * (1.0F - var9);
         if(var1.isChild()) {
            var16 *= 3.0F;
         }

         if(var15 > 1.0F) {
            var15 = 1.0F;
         }

         GL11.glEnable(3008);
         this.mainModel.setLivingAnimations(var1, var16, var15, var9);
         this.renderModel(var1, var16, var15, var13, var11 - var10, var26, var14);

         int var18;
         float var19;
         float var20;
         float var22;
         for(int var17 = 0; var17 < 4; ++var17) {
            var18 = this.shouldRenderPass(var1, var17, var9);
            if(var18 > 0) {
               this.renderPassModel.setLivingAnimations(var1, var16, var15, var9);
               this.renderPassModel.render(var1, var16, var15, var13, var11 - var10, var26, var14);
               if((var18 & 240) == 16) {
                  this.func_82408_c(var1, var17, var9);
                  this.renderPassModel.render(var1, var16, var15, var13, var11 - var10, var26, var14);
               }

               if((var18 & 15) == 15) {
                  var19 = (float)var1.ticksExisted + var9;
                  this.bindTexture(RES_ITEM_GLINT);
                  GL11.glEnable(3042);
                  var20 = 0.5F;
                  GL11.glColor4f(var20, var20, var20, 1.0F);
                  GL11.glDepthFunc(514);
                  GL11.glDepthMask(false);

                  for(int var21 = 0; var21 < 2; ++var21) {
                     GL11.glDisable(2896);
                     var22 = 0.76F;
                     GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
                     GL11.glBlendFunc(768, 1);
                     GL11.glMatrixMode(5890);
                     GL11.glLoadIdentity();
                     float var23 = var19 * (0.001F + (float)var21 * 0.003F) * 20.0F;
                     float var24 = 0.33333334F;
                     GL11.glScalef(var24, var24, var24);
                     GL11.glRotatef(30.0F - (float)var21 * 60.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glTranslatef(0.0F, var23, 0.0F);
                     GL11.glMatrixMode(5888);
                     this.renderPassModel.render(var1, var16, var15, var13, var11 - var10, var26, var14);
                  }

                  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                  GL11.glMatrixMode(5890);
                  GL11.glDepthMask(true);
                  GL11.glLoadIdentity();
                  GL11.glMatrixMode(5888);
                  GL11.glEnable(2896);
                  GL11.glDisable(3042);
                  GL11.glDepthFunc(515);
               }

               GL11.glDisable(3042);
               GL11.glEnable(3008);
            }
         }

         GL11.glDepthMask(true);
         this.renderEquippedItems(var1, var9);
         float var27 = var1.getBrightness(var9);
         var18 = this.getColorMultiplier(var1, var27, var9);
         OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
         GL11.glDisable(3553);
         OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
         if((var18 >> 24 & 255) > 0 || var1.hurtTime > 0 || var1.deathTime > 0) {
            GL11.glDisable(3553);
            GL11.glDisable(3008);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glDepthFunc(514);
            if(var1.hurtTime > 0 || var1.deathTime > 0) {
               GL11.glColor4f(var27, 0.0F, 0.0F, 0.4F);
               this.mainModel.render(var1, var16, var15, var13, var11 - var10, var26, var14);

               for(int var28 = 0; var28 < 4; ++var28) {
                  if(this.inheritRenderPass(var1, var28, var9) >= 0) {
                     GL11.glColor4f(var27, 0.0F, 0.0F, 0.4F);
                     this.renderPassModel.render(var1, var16, var15, var13, var11 - var10, var26, var14);
                  }
               }
            }

            if((var18 >> 24 & 255) > 0) {
               var19 = (float)(var18 >> 16 & 255) / 255.0F;
               var20 = (float)(var18 >> 8 & 255) / 255.0F;
               float var29 = (float)(var18 & 255) / 255.0F;
               var22 = (float)(var18 >> 24 & 255) / 255.0F;
               GL11.glColor4f(var19, var20, var29, var22);
               this.mainModel.render(var1, var16, var15, var13, var11 - var10, var26, var14);

               for(int var30 = 0; var30 < 4; ++var30) {
                  if(this.inheritRenderPass(var1, var30, var9) >= 0) {
                     GL11.glColor4f(var19, var20, var29, var22);
                     this.renderPassModel.render(var1, var16, var15, var13, var11 - var10, var26, var14);
                  }
               }
            }

            GL11.glDepthFunc(515);
            GL11.glDisable(3042);
            GL11.glEnable(3008);
            GL11.glEnable(3553);
         }

         GL11.glDisable('\u803a');
      } catch (Exception var25) {
         logger.error("Couldn\'t render entity", var25);
      }

      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glEnable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
      GL11.glEnable(2884);
      GL11.glPopMatrix();
      this.passSpecialRender(var1, var2, var4, var6);
   }

   protected void renderModel(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      this.bindEntityTexture(var1);
      if(!var1.isInvisible()) {
         this.mainModel.render(var1, var2, var3, var4, var5, var6, var7);
      } else if(!var1.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
         GL11.glPushMatrix();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
         GL11.glDepthMask(false);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glAlphaFunc(516, 0.003921569F);
         this.mainModel.render(var1, var2, var3, var4, var5, var6, var7);
         GL11.glDisable(3042);
         GL11.glAlphaFunc(516, 0.1F);
         GL11.glPopMatrix();
         GL11.glDepthMask(true);
      } else {
         this.mainModel.setRotationAngles(var2, var3, var4, var5, var6, var7, var1);
      }

   }

   protected void renderLivingAt(EntityLivingBase var1, double var2, double var4, double var6) {
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
   }

   protected void rotateCorpse(EntityLivingBase var1, float var2, float var3, float var4) {
      GL11.glRotatef(180.0F - var3, 0.0F, 1.0F, 0.0F);
      if(var1.deathTime > 0) {
         float var5 = ((float)var1.deathTime + var4 - 1.0F) / 20.0F * 1.6F;
         var5 = MathHelper.sqrt_float(var5);
         if(var5 > 1.0F) {
            var5 = 1.0F;
         }

         GL11.glRotatef(var5 * this.getDeathMaxRotation(var1), 0.0F, 0.0F, 1.0F);
      } else {
         String var6 = EnumChatFormatting.getTextWithoutFormattingCodes(var1.getCommandSenderName());
         if((var6.equals("Dinnerbone") || var6.equals("Grumm")) && (!(var1 instanceof EntityPlayer) || !((EntityPlayer)var1).getHideCape())) {
            GL11.glTranslatef(0.0F, var1.height + 0.1F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         }
      }

   }

   protected float renderSwingProgress(EntityLivingBase var1, float var2) {
      return var1.getSwingProgress(var2);
   }

   protected float handleRotationFloat(EntityLivingBase var1, float var2) {
      return (float)var1.ticksExisted + var2;
   }

   protected void renderEquippedItems(EntityLivingBase var1, float var2) {}

   protected void renderArrowsStuckInEntity(EntityLivingBase var1, float var2) {
      int var3 = var1.getArrowCountInEntity();
      if(var3 > 0) {
         EntityArrow var4 = new EntityArrow(var1.worldObj, var1.posX, var1.posY, var1.posZ);
         Random var5 = new Random((long)var1.getEntityId());
         RenderHelper.disableStandardItemLighting();

         for(int var6 = 0; var6 < var3; ++var6) {
            GL11.glPushMatrix();
            ModelRenderer var7 = this.mainModel.getRandomModelBox(var5);
            ModelBox var8 = (ModelBox)var7.cubeList.get(var5.nextInt(var7.cubeList.size()));
            var7.postRender(0.0625F);
            float var9 = var5.nextFloat();
            float var10 = var5.nextFloat();
            float var11 = var5.nextFloat();
            float var12 = (var8.posX1 + (var8.posX2 - var8.posX1) * var9) / 16.0F;
            float var13 = (var8.posY1 + (var8.posY2 - var8.posY1) * var10) / 16.0F;
            float var14 = (var8.posZ1 + (var8.posZ2 - var8.posZ1) * var11) / 16.0F;
            GL11.glTranslatef(var12, var13, var14);
            var9 = var9 * 2.0F - 1.0F;
            var10 = var10 * 2.0F - 1.0F;
            var11 = var11 * 2.0F - 1.0F;
            var9 *= -1.0F;
            var10 *= -1.0F;
            var11 *= -1.0F;
            float var15 = MathHelper.sqrt_float(var9 * var9 + var11 * var11);
            var4.prevRotationYaw = var4.rotationYaw = (float)(Math.atan2((double)var9, (double)var11) * 180.0D / 3.1415927410125732D);
            var4.prevRotationPitch = var4.rotationPitch = (float)(Math.atan2((double)var10, (double)var15) * 180.0D / 3.1415927410125732D);
            double var16 = 0.0D;
            double var18 = 0.0D;
            double var20 = 0.0D;
            float var22 = 0.0F;
            super.renderManager.renderEntityWithPosYaw(var4, var16, var18, var20, var22, var2);
            GL11.glPopMatrix();
         }

         RenderHelper.enableStandardItemLighting();
      }

   }

   protected int inheritRenderPass(EntityLivingBase var1, int var2, float var3) {
      return this.shouldRenderPass(var1, var2, var3);
   }

   protected int shouldRenderPass(EntityLivingBase var1, int var2, float var3) {
      return -1;
   }

   protected void func_82408_c(EntityLivingBase var1, int var2, float var3) {}

   protected float getDeathMaxRotation(EntityLivingBase var1) {
      return 90.0F;
   }

   protected int getColorMultiplier(EntityLivingBase var1, float var2, float var3) {
      return 0;
   }

   protected void preRenderCallback(EntityLivingBase var1, float var2) {}

   protected void passSpecialRender(EntityLivingBase var1, double var2, double var4, double var6) {
      GL11.glAlphaFunc(516, 0.1F);
      if(this.func_110813_b(var1)) {
         float var8 = 1.6F;
         float var9 = 0.016666668F * var8;
         double var10 = var1.getDistanceSqToEntity(super.renderManager.livingPlayer);
         float var12 = var1.isSneaking()?32.0F:64.0F;
         if(var10 < (double)(var12 * var12)) {
            String var13 = var1.func_145748_c_().getFormattedText();
            if(var1.isSneaking()) {
               FontRenderer var14 = this.getFontRendererFromRenderManager();
               GL11.glPushMatrix();
               GL11.glTranslatef((float)var2 + 0.0F, (float)var4 + var1.height + 0.5F, (float)var6);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-super.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(super.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
               GL11.glScalef(-var9, -var9, var9);
               GL11.glDisable(2896);
               GL11.glTranslatef(0.0F, 0.25F / var9, 0.0F);
               GL11.glDepthMask(false);
               GL11.glEnable(3042);
               OpenGlHelper.glBlendFunc(770, 771, 1, 0);
               Tessellator var15 = Tessellator.instance;
               GL11.glDisable(3553);
               var15.startDrawingQuads();
               int var16 = var14.getStringWidth(var13) / 2;
               var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
               var15.addVertex((double)(-var16 - 1), -1.0D, 0.0D);
               var15.addVertex((double)(-var16 - 1), 8.0D, 0.0D);
               var15.addVertex((double)(var16 + 1), 8.0D, 0.0D);
               var15.addVertex((double)(var16 + 1), -1.0D, 0.0D);
               var15.draw();
               GL11.glEnable(3553);
               GL11.glDepthMask(true);
               var14.drawString(var13, -var14.getStringWidth(var13) / 2, 0, 553648127);
               GL11.glEnable(2896);
               GL11.glDisable(3042);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glPopMatrix();
            } else {
               this.func_96449_a(var1, var2, var4, var6, var13, var9, var10);
            }
         }
      }

   }

   protected boolean func_110813_b(EntityLivingBase var1) {
      return Minecraft.isGuiEnabled() && var1 != super.renderManager.livingPlayer && !var1.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer) && var1.riddenByEntity == null;
   }

   protected void func_96449_a(EntityLivingBase var1, double var2, double var4, double var6, String var8, float var9, double var10) {
      if(var1.isPlayerSleeping()) {
         this.func_147906_a(var1, var8, var2, var4 - 1.5D, var6, 64);
      } else {
         this.func_147906_a(var1, var8, var2, var4, var6, 64);
      }

   }

}
