package net.minecraft.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public abstract class Render {

   private static final ResourceLocation shadowTextures = new ResourceLocation("textures/misc/shadow.png");
   protected RenderManager renderManager;
   protected RenderBlocks field_147909_c = new RenderBlocks();
   protected float shadowSize;
   protected float shadowOpaque = 1.0F;
   private boolean staticEntity = false;


   public abstract void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9);

   protected abstract ResourceLocation getEntityTexture(Entity var1);

   public boolean isStaticEntity() {
      return this.staticEntity;
   }

   protected void bindEntityTexture(Entity var1) {
      this.bindTexture(this.getEntityTexture(var1));
   }

   protected void bindTexture(ResourceLocation var1) {
      this.renderManager.renderEngine.bindTexture(var1);
   }

   private void renderEntityOnFire(Entity var1, double var2, double var4, double var6, float var8) {
      GL11.glDisable(2896);
      IIcon var9 = Blocks.fire.getFireIcon(0);
      IIcon var10 = Blocks.fire.getFireIcon(1);
      GL11.glPushMatrix();
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      float var11 = var1.width * 1.4F;
      GL11.glScalef(var11, var11, var11);
      Tessellator var12 = Tessellator.instance;
      float var13 = 0.5F;
      float var14 = 0.0F;
      float var15 = var1.height / var11;
      float var16 = (float)(var1.posY - var1.boundingBox.minY);
      GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      GL11.glTranslatef(0.0F, 0.0F, -0.3F + (float)((int)var15) * 0.02F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      float var17 = 0.0F;
      int var18 = 0;
      var12.startDrawingQuads();

      while(var15 > 0.0F) {
         IIcon var19 = var18 % 2 == 0?var9:var10;
         this.bindTexture(TextureMap.locationBlocksTexture);
         float var20 = var19.getMinU();
         float var21 = var19.getMinV();
         float var22 = var19.getMaxU();
         float var23 = var19.getMaxV();
         if(var18 / 2 % 2 == 0) {
            float var24 = var22;
            var22 = var20;
            var20 = var24;
         }

         var12.addVertexWithUV((double)(var13 - var14), (double)(0.0F - var16), (double)var17, (double)var22, (double)var23);
         var12.addVertexWithUV((double)(-var13 - var14), (double)(0.0F - var16), (double)var17, (double)var20, (double)var23);
         var12.addVertexWithUV((double)(-var13 - var14), (double)(1.4F - var16), (double)var17, (double)var20, (double)var21);
         var12.addVertexWithUV((double)(var13 - var14), (double)(1.4F - var16), (double)var17, (double)var22, (double)var21);
         var15 -= 0.45F;
         var16 -= 0.45F;
         var13 *= 0.9F;
         var17 += 0.03F;
         ++var18;
      }

      var12.draw();
      GL11.glPopMatrix();
      GL11.glEnable(2896);
   }

   private void renderShadow(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      this.renderManager.renderEngine.bindTexture(shadowTextures);
      World var10 = this.getWorldFromRenderManager();
      GL11.glDepthMask(false);
      float var11 = this.shadowSize;
      if(var1 instanceof EntityLiving) {
         EntityLiving var12 = (EntityLiving)var1;
         var11 *= var12.getRenderSizeModifier();
         if(var12.isChild()) {
            var11 *= 0.5F;
         }
      }

      double var35 = var1.lastTickPosX + (var1.posX - var1.lastTickPosX) * (double)var9;
      double var14 = var1.lastTickPosY + (var1.posY - var1.lastTickPosY) * (double)var9 + (double)var1.getShadowSize();
      double var16 = var1.lastTickPosZ + (var1.posZ - var1.lastTickPosZ) * (double)var9;
      int var18 = MathHelper.floor_double(var35 - (double)var11);
      int var19 = MathHelper.floor_double(var35 + (double)var11);
      int var20 = MathHelper.floor_double(var14 - (double)var11);
      int var21 = MathHelper.floor_double(var14);
      int var22 = MathHelper.floor_double(var16 - (double)var11);
      int var23 = MathHelper.floor_double(var16 + (double)var11);
      double var24 = var2 - var35;
      double var26 = var4 - var14;
      double var28 = var6 - var16;
      Tessellator var30 = Tessellator.instance;
      var30.startDrawingQuads();

      for(int var31 = var18; var31 <= var19; ++var31) {
         for(int var32 = var20; var32 <= var21; ++var32) {
            for(int var33 = var22; var33 <= var23; ++var33) {
               Block var34 = var10.getBlock(var31, var32 - 1, var33);
               if(var34.getMaterial() != Material.air && var10.getBlockLightValue(var31, var32, var33) > 3) {
                  this.func_147907_a(var34, var2, var4 + (double)var1.getShadowSize(), var6, var31, var32, var33, var8, var11, var24, var26 + (double)var1.getShadowSize(), var28);
               }
            }
         }
      }

      var30.draw();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(3042);
      GL11.glDepthMask(true);
   }

   private World getWorldFromRenderManager() {
      return this.renderManager.worldObj;
   }

   private void func_147907_a(Block var1, double var2, double var4, double var6, int var8, int var9, int var10, float var11, float var12, double var13, double var15, double var17) {
      Tessellator var19 = Tessellator.instance;
      if(var1.renderAsNormalBlock()) {
         double var20 = ((double)var11 - (var4 - ((double)var9 + var15)) / 2.0D) * 0.5D * (double)this.getWorldFromRenderManager().getLightBrightness(var8, var9, var10);
         if(var20 >= 0.0D) {
            if(var20 > 1.0D) {
               var20 = 1.0D;
            }

            var19.setColorRGBA_F(1.0F, 1.0F, 1.0F, (float)var20);
            double var22 = (double)var8 + var1.getBlockBoundsMinX() + var13;
            double var24 = (double)var8 + var1.getBlockBoundsMaxX() + var13;
            double var26 = (double)var9 + var1.getBlockBoundsMinY() + var15 + 0.015625D;
            double var28 = (double)var10 + var1.getBlockBoundsMinZ() + var17;
            double var30 = (double)var10 + var1.getBlockBoundsMaxZ() + var17;
            float var32 = (float)((var2 - var22) / 2.0D / (double)var12 + 0.5D);
            float var33 = (float)((var2 - var24) / 2.0D / (double)var12 + 0.5D);
            float var34 = (float)((var6 - var28) / 2.0D / (double)var12 + 0.5D);
            float var35 = (float)((var6 - var30) / 2.0D / (double)var12 + 0.5D);
            var19.addVertexWithUV(var22, var26, var28, (double)var32, (double)var34);
            var19.addVertexWithUV(var22, var26, var30, (double)var32, (double)var35);
            var19.addVertexWithUV(var24, var26, var30, (double)var33, (double)var35);
            var19.addVertexWithUV(var24, var26, var28, (double)var33, (double)var34);
         }
      }
   }

   public static void renderOffsetAABB(AxisAlignedBB var0, double var1, double var3, double var5) {
      GL11.glDisable(3553);
      Tessellator var7 = Tessellator.instance;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      var7.startDrawingQuads();
      var7.setTranslation(var1, var3, var5);
      var7.setNormal(0.0F, 0.0F, -1.0F);
      var7.addVertex(var0.minX, var0.maxY, var0.minZ);
      var7.addVertex(var0.maxX, var0.maxY, var0.minZ);
      var7.addVertex(var0.maxX, var0.minY, var0.minZ);
      var7.addVertex(var0.minX, var0.minY, var0.minZ);
      var7.setNormal(0.0F, 0.0F, 1.0F);
      var7.addVertex(var0.minX, var0.minY, var0.maxZ);
      var7.addVertex(var0.maxX, var0.minY, var0.maxZ);
      var7.addVertex(var0.maxX, var0.maxY, var0.maxZ);
      var7.addVertex(var0.minX, var0.maxY, var0.maxZ);
      var7.setNormal(0.0F, -1.0F, 0.0F);
      var7.addVertex(var0.minX, var0.minY, var0.minZ);
      var7.addVertex(var0.maxX, var0.minY, var0.minZ);
      var7.addVertex(var0.maxX, var0.minY, var0.maxZ);
      var7.addVertex(var0.minX, var0.minY, var0.maxZ);
      var7.setNormal(0.0F, 1.0F, 0.0F);
      var7.addVertex(var0.minX, var0.maxY, var0.maxZ);
      var7.addVertex(var0.maxX, var0.maxY, var0.maxZ);
      var7.addVertex(var0.maxX, var0.maxY, var0.minZ);
      var7.addVertex(var0.minX, var0.maxY, var0.minZ);
      var7.setNormal(-1.0F, 0.0F, 0.0F);
      var7.addVertex(var0.minX, var0.minY, var0.maxZ);
      var7.addVertex(var0.minX, var0.maxY, var0.maxZ);
      var7.addVertex(var0.minX, var0.maxY, var0.minZ);
      var7.addVertex(var0.minX, var0.minY, var0.minZ);
      var7.setNormal(1.0F, 0.0F, 0.0F);
      var7.addVertex(var0.maxX, var0.minY, var0.minZ);
      var7.addVertex(var0.maxX, var0.maxY, var0.minZ);
      var7.addVertex(var0.maxX, var0.maxY, var0.maxZ);
      var7.addVertex(var0.maxX, var0.minY, var0.maxZ);
      var7.setTranslation(0.0D, 0.0D, 0.0D);
      var7.draw();
      GL11.glEnable(3553);
   }

   public static void renderAABB(AxisAlignedBB var0) {
      Tessellator var1 = Tessellator.instance;
      var1.startDrawingQuads();
      var1.addVertex(var0.minX, var0.maxY, var0.minZ);
      var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
      var1.addVertex(var0.maxX, var0.minY, var0.minZ);
      var1.addVertex(var0.minX, var0.minY, var0.minZ);
      var1.addVertex(var0.minX, var0.minY, var0.maxZ);
      var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
      var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
      var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
      var1.addVertex(var0.minX, var0.minY, var0.minZ);
      var1.addVertex(var0.maxX, var0.minY, var0.minZ);
      var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
      var1.addVertex(var0.minX, var0.minY, var0.maxZ);
      var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
      var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
      var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
      var1.addVertex(var0.minX, var0.maxY, var0.minZ);
      var1.addVertex(var0.minX, var0.minY, var0.maxZ);
      var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
      var1.addVertex(var0.minX, var0.maxY, var0.minZ);
      var1.addVertex(var0.minX, var0.minY, var0.minZ);
      var1.addVertex(var0.maxX, var0.minY, var0.minZ);
      var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
      var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
      var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
      var1.draw();
   }

   public void setRenderManager(RenderManager var1) {
      this.renderManager = var1;
   }

   public void doRenderShadowAndFire(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      if(this.renderManager.options.fancyGraphics && this.shadowSize > 0.0F && !var1.isInvisible()) {
         double var10 = this.renderManager.getDistanceToCamera(var1.posX, var1.posY, var1.posZ);
         float var12 = (float)((1.0D - var10 / 256.0D) * (double)this.shadowOpaque);
         if(var12 > 0.0F) {
            this.renderShadow(var1, var2, var4, var6, var12, var9);
         }
      }

      if(var1.canRenderOnFire()) {
         this.renderEntityOnFire(var1, var2, var4, var6, var9);
      }

   }

   public FontRenderer getFontRendererFromRenderManager() {
      return this.renderManager.getFontRenderer();
   }

   public void updateIcons(IIconRegister var1) {}

   protected void func_147906_a(Entity var1, String var2, double var3, double var5, double var7, int var9) {
      double var10 = var1.getDistanceSqToEntity(this.renderManager.livingPlayer);
      if(var10 <= (double)(var9 * var9)) {
         FontRenderer var12 = this.getFontRendererFromRenderManager();
         float var13 = 1.6F;
         float var14 = 0.016666668F * var13;
         GL11.glPushMatrix();
         GL11.glTranslatef((float)var3 + 0.0F, (float)var5 + var1.height + 0.5F, (float)var7);
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
         GL11.glScalef(-var14, -var14, var14);
         GL11.glDisable(2896);
         GL11.glDepthMask(false);
         GL11.glDisable(2929);
         GL11.glEnable(3042);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         Tessellator var15 = Tessellator.instance;
         byte var16 = 0;
         if(var2.equals("deadmau5")) {
            var16 = -10;
         }

         GL11.glDisable(3553);
         var15.startDrawingQuads();
         int var17 = var12.getStringWidth(var2) / 2;
         var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
         var15.addVertex((double)(-var17 - 1), (double)(-1 + var16), 0.0D);
         var15.addVertex((double)(-var17 - 1), (double)(8 + var16), 0.0D);
         var15.addVertex((double)(var17 + 1), (double)(8 + var16), 0.0D);
         var15.addVertex((double)(var17 + 1), (double)(-1 + var16), 0.0D);
         var15.draw();
         GL11.glEnable(3553);
         var12.drawString(var2, -var12.getStringWidth(var2) / 2, var16, 553648127);
         GL11.glEnable(2929);
         GL11.glDepthMask(true);
         var12.drawString(var2, -var12.getStringWidth(var2) / 2, var16, -1);
         GL11.glEnable(2896);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPopMatrix();
      }
   }

}
