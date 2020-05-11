package net.minecraft.client.renderer.entity;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem$1;
import net.minecraft.client.renderer.entity.RenderItem$2;
import net.minecraft.client.renderer.entity.RenderItem$3;
import net.minecraft.client.renderer.entity.RenderItem$4;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderItem extends Render {

   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
   private RenderBlocks renderBlocksRi = new RenderBlocks();
   private Random random = new Random();
   public boolean renderWithColor = true;
   public float zLevel;
   public static boolean renderInFrame;


   public RenderItem() {
      super.shadowSize = 0.15F;
      super.shadowOpaque = 0.75F;
   }

   public void doRender(EntityItem var1, double var2, double var4, double var6, float var8, float var9) {
      ItemStack var10 = var1.getEntityItem();
      if(var10.getItem() != null) {
         this.bindEntityTexture(var1);
         TextureUtil.func_152777_a(false, false, 1.0F);
         this.random.setSeed(187L);
         GL11.glPushMatrix();
         float var11 = MathHelper.sin(((float)var1.age + var9) / 10.0F + var1.hoverStart) * 0.1F + 0.1F;
         float var12 = (((float)var1.age + var9) / 20.0F + var1.hoverStart) * 57.295776F;
         byte var13 = 1;
         if(var1.getEntityItem().stackSize > 1) {
            var13 = 2;
         }

         if(var1.getEntityItem().stackSize > 5) {
            var13 = 3;
         }

         if(var1.getEntityItem().stackSize > 20) {
            var13 = 4;
         }

         if(var1.getEntityItem().stackSize > 40) {
            var13 = 5;
         }

         GL11.glTranslatef((float)var2, (float)var4 + var11, (float)var6);
         GL11.glEnable('\u803a');
         float var18;
         float var19;
         int var25;
         if(var10.getItemSpriteNumber() == 0 && var10.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(var10.getItem()).getRenderType())) {
            Block var22 = Block.getBlockFromItem(var10.getItem());
            GL11.glRotatef(var12, 0.0F, 1.0F, 0.0F);
            if(renderInFrame) {
               GL11.glScalef(1.25F, 1.25F, 1.25F);
               GL11.glTranslatef(0.0F, 0.05F, 0.0F);
               GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            }

            float var24 = 0.25F;
            var25 = var22.getRenderType();
            if(var25 == 1 || var25 == 19 || var25 == 12 || var25 == 2) {
               var24 = 0.5F;
            }

            if(var22.getRenderBlockPass() > 0) {
               GL11.glAlphaFunc(516, 0.1F);
               GL11.glEnable(3042);
               OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            }

            GL11.glScalef(var24, var24, var24);

            for(int var26 = 0; var26 < var13; ++var26) {
               GL11.glPushMatrix();
               if(var26 > 0) {
                  var18 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / var24;
                  var19 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / var24;
                  float var20 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / var24;
                  GL11.glTranslatef(var18, var19, var20);
               }

               this.renderBlocksRi.renderBlockAsItem(var22, var10.getItemDamage(), 1.0F);
               GL11.glPopMatrix();
            }

            if(var22.getRenderBlockPass() > 0) {
               GL11.glDisable(3042);
            }
         } else {
            float var17;
            if(var10.getItemSpriteNumber() == 1 && var10.getItem().requiresMultipleRenderPasses()) {
               if(renderInFrame) {
                  GL11.glScalef(0.5128205F, 0.5128205F, 0.5128205F);
                  GL11.glTranslatef(0.0F, -0.05F, 0.0F);
               } else {
                  GL11.glScalef(0.5F, 0.5F, 0.5F);
               }

               for(int var21 = 0; var21 <= 1; ++var21) {
                  this.random.setSeed(187L);
                  IIcon var23 = var10.getItem().getIconFromDamageForRenderPass(var10.getItemDamage(), var21);
                  if(this.renderWithColor) {
                     var25 = var10.getItem().getColorFromItemStack(var10, var21);
                     var17 = (float)(var25 >> 16 & 255) / 255.0F;
                     var18 = (float)(var25 >> 8 & 255) / 255.0F;
                     var19 = (float)(var25 & 255) / 255.0F;
                     GL11.glColor4f(var17, var18, var19, 1.0F);
                     this.renderDroppedItem(var1, var23, var13, var9, var17, var18, var19);
                  } else {
                     this.renderDroppedItem(var1, var23, var13, var9, 1.0F, 1.0F, 1.0F);
                  }
               }
            } else {
               if(var10 != null && var10.getItem() instanceof ItemCloth) {
                  GL11.glAlphaFunc(516, 0.1F);
                  GL11.glEnable(3042);
                  OpenGlHelper.glBlendFunc(770, 771, 1, 0);
               }

               if(renderInFrame) {
                  GL11.glScalef(0.5128205F, 0.5128205F, 0.5128205F);
                  GL11.glTranslatef(0.0F, -0.05F, 0.0F);
               } else {
                  GL11.glScalef(0.5F, 0.5F, 0.5F);
               }

               IIcon var14 = var10.getIconIndex();
               if(this.renderWithColor) {
                  int var15 = var10.getItem().getColorFromItemStack(var10, 0);
                  float var16 = (float)(var15 >> 16 & 255) / 255.0F;
                  var17 = (float)(var15 >> 8 & 255) / 255.0F;
                  var18 = (float)(var15 & 255) / 255.0F;
                  this.renderDroppedItem(var1, var14, var13, var9, var16, var17, var18);
               } else {
                  this.renderDroppedItem(var1, var14, var13, var9, 1.0F, 1.0F, 1.0F);
               }

               if(var10 != null && var10.getItem() instanceof ItemCloth) {
                  GL11.glDisable(3042);
               }
            }
         }

         GL11.glDisable('\u803a');
         GL11.glPopMatrix();
         this.bindEntityTexture(var1);
         TextureUtil.func_147945_b();
      }
   }

   protected ResourceLocation getEntityTexture(EntityItem var1) {
      return super.renderManager.renderEngine.getResourceLocation(var1.getEntityItem().getItemSpriteNumber());
   }

   private void renderDroppedItem(EntityItem var1, IIcon var2, int var3, float var4, float var5, float var6, float var7) {
      Tessellator var8 = Tessellator.instance;
      if(var2 == null) {
         TextureManager var9 = Minecraft.getMinecraft().getTextureManager();
         ResourceLocation var10 = var9.getResourceLocation(var1.getEntityItem().getItemSpriteNumber());
         var2 = ((TextureMap)var9.getTexture(var10)).getAtlasSprite("missingno");
      }

      float var25 = ((IIcon)var2).getMinU();
      float var26 = ((IIcon)var2).getMaxU();
      float var11 = ((IIcon)var2).getMinV();
      float var12 = ((IIcon)var2).getMaxV();
      float var13 = 1.0F;
      float var14 = 0.5F;
      float var15 = 0.25F;
      float var17;
      if(super.renderManager.options.fancyGraphics) {
         GL11.glPushMatrix();
         if(renderInFrame) {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
         } else {
            GL11.glRotatef((((float)var1.age + var4) / 20.0F + var1.hoverStart) * 57.295776F, 0.0F, 1.0F, 0.0F);
         }

         float var16 = 0.0625F;
         var17 = 0.021875F;
         ItemStack var18 = var1.getEntityItem();
         int var19 = var18.stackSize;
         byte var24;
         if(var19 < 2) {
            var24 = 1;
         } else if(var19 < 16) {
            var24 = 2;
         } else if(var19 < 32) {
            var24 = 3;
         } else {
            var24 = 4;
         }

         GL11.glTranslatef(-var14, -var15, -((var16 + var17) * (float)var24 / 2.0F));

         for(int var20 = 0; var20 < var24; ++var20) {
            GL11.glTranslatef(0.0F, 0.0F, var16 + var17);
            if(var18.getItemSpriteNumber() == 0) {
               this.bindTexture(TextureMap.locationBlocksTexture);
            } else {
               this.bindTexture(TextureMap.locationItemsTexture);
            }

            GL11.glColor4f(var5, var6, var7, 1.0F);
            ItemRenderer.renderItemIn2D(var8, var26, var11, var25, var12, ((IIcon)var2).getIconWidth(), ((IIcon)var2).getIconHeight(), var16);
            if(var18.hasEffect()) {
               GL11.glDepthFunc(514);
               GL11.glDisable(2896);
               super.renderManager.renderEngine.bindTexture(RES_ITEM_GLINT);
               GL11.glEnable(3042);
               GL11.glBlendFunc(768, 1);
               float var21 = 0.76F;
               GL11.glColor4f(0.5F * var21, 0.25F * var21, 0.8F * var21, 1.0F);
               GL11.glMatrixMode(5890);
               GL11.glPushMatrix();
               float var22 = 0.125F;
               GL11.glScalef(var22, var22, var22);
               float var23 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
               GL11.glTranslatef(var23, 0.0F, 0.0F);
               GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
               ItemRenderer.renderItemIn2D(var8, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, var16);
               GL11.glPopMatrix();
               GL11.glPushMatrix();
               GL11.glScalef(var22, var22, var22);
               var23 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
               GL11.glTranslatef(-var23, 0.0F, 0.0F);
               GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
               ItemRenderer.renderItemIn2D(var8, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, var16);
               GL11.glPopMatrix();
               GL11.glMatrixMode(5888);
               GL11.glDisable(3042);
               GL11.glEnable(2896);
               GL11.glDepthFunc(515);
            }
         }

         GL11.glPopMatrix();
      } else {
         for(int var27 = 0; var27 < var3; ++var27) {
            GL11.glPushMatrix();
            if(var27 > 0) {
               var17 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
               float var28 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
               float var29 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
               GL11.glTranslatef(var17, var28, var29);
            }

            if(!renderInFrame) {
               GL11.glRotatef(180.0F - super.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            }

            GL11.glColor4f(var5, var6, var7, 1.0F);
            var8.startDrawingQuads();
            var8.setNormal(0.0F, 1.0F, 0.0F);
            var8.addVertexWithUV((double)(0.0F - var14), (double)(0.0F - var15), 0.0D, (double)var25, (double)var12);
            var8.addVertexWithUV((double)(var13 - var14), (double)(0.0F - var15), 0.0D, (double)var26, (double)var12);
            var8.addVertexWithUV((double)(var13 - var14), (double)(1.0F - var15), 0.0D, (double)var26, (double)var11);
            var8.addVertexWithUV((double)(0.0F - var14), (double)(1.0F - var15), 0.0D, (double)var25, (double)var11);
            var8.draw();
            GL11.glPopMatrix();
         }
      }

   }

   public void renderItemIntoGUI(FontRenderer var1, TextureManager var2, ItemStack var3, int var4, int var5) {
      int var6 = var3.getItemDamage();
      Object var7 = var3.getIconIndex();
      int var9;
      float var12;
      float var17;
      float var18;
      if(var3.getItemSpriteNumber() == 0 && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(var3.getItem()).getRenderType())) {
         var2.bindTexture(TextureMap.locationBlocksTexture);
         Block var16 = Block.getBlockFromItem(var3.getItem());
         GL11.glEnable(3008);
         if(var16.getRenderBlockPass() != 0) {
            GL11.glAlphaFunc(516, 0.1F);
            GL11.glEnable(3042);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         } else {
            GL11.glAlphaFunc(516, 0.5F);
            GL11.glDisable(3042);
         }

         GL11.glPushMatrix();
         GL11.glTranslatef((float)(var4 - 2), (float)(var5 + 3), -3.0F + this.zLevel);
         GL11.glScalef(10.0F, 10.0F, 10.0F);
         GL11.glTranslatef(1.0F, 0.5F, 1.0F);
         GL11.glScalef(1.0F, 1.0F, -1.0F);
         GL11.glRotatef(210.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         var9 = var3.getItem().getColorFromItemStack(var3, 0);
         var17 = (float)(var9 >> 16 & 255) / 255.0F;
         var18 = (float)(var9 >> 8 & 255) / 255.0F;
         var12 = (float)(var9 & 255) / 255.0F;
         if(this.renderWithColor) {
            GL11.glColor4f(var17, var18, var12, 1.0F);
         }

         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
         this.renderBlocksRi.useInventoryTint = this.renderWithColor;
         this.renderBlocksRi.renderBlockAsItem(var16, var6, 1.0F);
         this.renderBlocksRi.useInventoryTint = true;
         if(var16.getRenderBlockPass() == 0) {
            GL11.glAlphaFunc(516, 0.1F);
         }

         GL11.glPopMatrix();
      } else if(var3.getItem().requiresMultipleRenderPasses()) {
         GL11.glDisable(2896);
         GL11.glEnable(3008);
         var2.bindTexture(TextureMap.locationItemsTexture);
         GL11.glDisable(3008);
         GL11.glDisable(3553);
         GL11.glEnable(3042);
         OpenGlHelper.glBlendFunc(0, 0, 0, 0);
         GL11.glColorMask(false, false, false, true);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         Tessellator var8 = Tessellator.instance;
         var8.startDrawingQuads();
         var8.setColorOpaque_I(-1);
         var8.addVertex((double)(var4 - 2), (double)(var5 + 18), (double)this.zLevel);
         var8.addVertex((double)(var4 + 18), (double)(var5 + 18), (double)this.zLevel);
         var8.addVertex((double)(var4 + 18), (double)(var5 - 2), (double)this.zLevel);
         var8.addVertex((double)(var4 - 2), (double)(var5 - 2), (double)this.zLevel);
         var8.draw();
         GL11.glColorMask(true, true, true, true);
         GL11.glEnable(3553);
         GL11.glEnable(3008);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);

         for(var9 = 0; var9 <= 1; ++var9) {
            IIcon var10 = var3.getItem().getIconFromDamageForRenderPass(var6, var9);
            int var11 = var3.getItem().getColorFromItemStack(var3, var9);
            var12 = (float)(var11 >> 16 & 255) / 255.0F;
            float var13 = (float)(var11 >> 8 & 255) / 255.0F;
            float var14 = (float)(var11 & 255) / 255.0F;
            if(this.renderWithColor) {
               GL11.glColor4f(var12, var13, var14, 1.0F);
            }

            this.renderIcon(var4, var5, var10, 16, 16);
         }

         GL11.glEnable(2896);
      } else {
         GL11.glDisable(2896);
         GL11.glEnable(3042);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         ResourceLocation var15 = var2.getResourceLocation(var3.getItemSpriteNumber());
         var2.bindTexture(var15);
         if(var7 == null) {
            var7 = ((TextureMap)Minecraft.getMinecraft().getTextureManager().getTexture(var15)).getAtlasSprite("missingno");
         }

         var9 = var3.getItem().getColorFromItemStack(var3, 0);
         var17 = (float)(var9 >> 16 & 255) / 255.0F;
         var18 = (float)(var9 >> 8 & 255) / 255.0F;
         var12 = (float)(var9 & 255) / 255.0F;
         if(this.renderWithColor) {
            GL11.glColor4f(var17, var18, var12, 1.0F);
         }

         this.renderIcon(var4, var5, (IIcon)var7, 16, 16);
         GL11.glEnable(2896);
         GL11.glDisable(3042);
      }

      GL11.glEnable(2884);
   }

   public void renderItemAndEffectIntoGUI(FontRenderer var1, TextureManager var2, ItemStack var3, int var4, int var5) {
      if(var3 != null) {
         this.zLevel += 50.0F;

         try {
            this.renderItemIntoGUI(var1, var2, var3, var4, var5);
         } catch (Throwable var9) {
            CrashReport var7 = CrashReport.makeCrashReport(var9, "Rendering item");
            CrashReportCategory var8 = var7.makeCategory("Item being rendered");
            var8.addCrashSectionCallable("Item Type", new RenderItem$1(this, var3));
            var8.addCrashSectionCallable("Item Aux", new RenderItem$2(this, var3));
            var8.addCrashSectionCallable("Item NBT", new RenderItem$3(this, var3));
            var8.addCrashSectionCallable("Item Foil", new RenderItem$4(this, var3));
            throw new ReportedException(var7);
         }

         if(var3.hasEffect()) {
            GL11.glDepthFunc(514);
            GL11.glDisable(2896);
            GL11.glDepthMask(false);
            var2.bindTexture(RES_ITEM_GLINT);
            GL11.glEnable(3008);
            GL11.glEnable(3042);
            GL11.glColor4f(0.5F, 0.25F, 0.8F, 1.0F);
            this.renderGlint(var4 * 431278612 + var5 * 32178161, var4 - 2, var5 - 2, 20, 20);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glDepthMask(true);
            GL11.glEnable(2896);
            GL11.glDepthFunc(515);
         }

         this.zLevel -= 50.0F;
      }
   }

   private void renderGlint(int var1, int var2, int var3, int var4, int var5) {
      for(int var6 = 0; var6 < 2; ++var6) {
         OpenGlHelper.glBlendFunc(772, 1, 0, 0);
         float var7 = 0.00390625F;
         float var8 = 0.00390625F;
         float var9 = (float)(Minecraft.getSystemTime() % (long)(3000 + var6 * 1873)) / (3000.0F + (float)(var6 * 1873)) * 256.0F;
         float var10 = 0.0F;
         Tessellator var11 = Tessellator.instance;
         float var12 = 4.0F;
         if(var6 == 1) {
            var12 = -1.0F;
         }

         var11.startDrawingQuads();
         var11.addVertexWithUV((double)(var2 + 0), (double)(var3 + var5), (double)this.zLevel, (double)((var9 + (float)var5 * var12) * var7), (double)((var10 + (float)var5) * var8));
         var11.addVertexWithUV((double)(var2 + var4), (double)(var3 + var5), (double)this.zLevel, (double)((var9 + (float)var4 + (float)var5 * var12) * var7), (double)((var10 + (float)var5) * var8));
         var11.addVertexWithUV((double)(var2 + var4), (double)(var3 + 0), (double)this.zLevel, (double)((var9 + (float)var4) * var7), (double)((var10 + 0.0F) * var8));
         var11.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)this.zLevel, (double)((var9 + 0.0F) * var7), (double)((var10 + 0.0F) * var8));
         var11.draw();
      }

   }

   public void renderItemOverlayIntoGUI(FontRenderer var1, TextureManager var2, ItemStack var3, int var4, int var5) {
      this.renderItemOverlayIntoGUI(var1, var2, var3, var4, var5, (String)null);
   }

   public void renderItemOverlayIntoGUI(FontRenderer var1, TextureManager var2, ItemStack var3, int var4, int var5, String var6) {
      if(var3 != null) {
         if(var3.stackSize > 1 || var6 != null) {
            String var7 = var6 == null?String.valueOf(var3.stackSize):var6;
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glDisable(3042);
            var1.drawStringWithShadow(var7, var4 + 19 - 2 - var1.getStringWidth(var7), var5 + 6 + 3, 16777215);
            GL11.glEnable(2896);
            GL11.glEnable(2929);
         }

         if(var3.isItemDamaged()) {
            int var12 = (int)Math.round(13.0D - (double)var3.getItemDamageForDisplay() * 13.0D / (double)var3.getMaxDamage());
            int var8 = (int)Math.round(255.0D - (double)var3.getItemDamageForDisplay() * 255.0D / (double)var3.getMaxDamage());
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glDisable(3553);
            GL11.glDisable(3008);
            GL11.glDisable(3042);
            Tessellator var9 = Tessellator.instance;
            int var10 = 255 - var8 << 16 | var8 << 8;
            int var11 = (255 - var8) / 4 << 16 | 16128;
            this.renderQuad(var9, var4 + 2, var5 + 13, 13, 2, 0);
            this.renderQuad(var9, var4 + 2, var5 + 13, 12, 1, var11);
            this.renderQuad(var9, var4 + 2, var5 + 13, var12, 1, var10);
            GL11.glEnable(3042);
            GL11.glEnable(3008);
            GL11.glEnable(3553);
            GL11.glEnable(2896);
            GL11.glEnable(2929);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }

      }
   }

   private void renderQuad(Tessellator var1, int var2, int var3, int var4, int var5, int var6) {
      var1.startDrawingQuads();
      var1.setColorOpaque_I(var6);
      var1.addVertex((double)(var2 + 0), (double)(var3 + 0), 0.0D);
      var1.addVertex((double)(var2 + 0), (double)(var3 + var5), 0.0D);
      var1.addVertex((double)(var2 + var4), (double)(var3 + var5), 0.0D);
      var1.addVertex((double)(var2 + var4), (double)(var3 + 0), 0.0D);
      var1.draw();
   }

   public void renderIcon(int var1, int var2, IIcon var3, int var4, int var5) {
      Tessellator var6 = Tessellator.instance;
      var6.startDrawingQuads();
      var6.addVertexWithUV((double)(var1 + 0), (double)(var2 + var5), (double)this.zLevel, (double)var3.getMinU(), (double)var3.getMaxV());
      var6.addVertexWithUV((double)(var1 + var4), (double)(var2 + var5), (double)this.zLevel, (double)var3.getMaxU(), (double)var3.getMaxV());
      var6.addVertexWithUV((double)(var1 + var4), (double)(var2 + 0), (double)this.zLevel, (double)var3.getMaxU(), (double)var3.getMinV());
      var6.addVertexWithUV((double)(var1 + 0), (double)(var2 + 0), (double)this.zLevel, (double)var3.getMinU(), (double)var3.getMinV());
      var6.draw();
   }

}
