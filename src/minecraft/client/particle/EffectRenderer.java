package net.minecraft.client.particle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer$1;
import net.minecraft.client.particle.EffectRenderer$2;
import net.minecraft.client.particle.EffectRenderer$3;
import net.minecraft.client.particle.EffectRenderer$4;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class EffectRenderer {

   private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
   protected World worldObj;
   private List[] fxLayers = new List[4];
   private TextureManager renderer;
   private Random rand = new Random();


   public EffectRenderer(World var1, TextureManager var2) {
      if(var1 != null) {
         this.worldObj = var1;
      }

      this.renderer = var2;

      for(int var3 = 0; var3 < 4; ++var3) {
         this.fxLayers[var3] = new ArrayList();
      }

   }

   public void addEffect(EntityFX var1) {
      int var2 = var1.getFXLayer();
      if(this.fxLayers[var2].size() >= 4000) {
         this.fxLayers[var2].remove(0);
      }

      this.fxLayers[var2].add(var1);
   }

   public void updateEffects() {
      for(int var1 = 0; var1 < 4; ++var1) {
         for(int var2 = 0; var2 < this.fxLayers[var1].size(); ++var2) {
            EntityFX var3 = (EntityFX)this.fxLayers[var1].get(var2);

            try {
               var3.onUpdate();
            } catch (Throwable var8) {
               CrashReport var5 = CrashReport.makeCrashReport(var8, "Ticking Particle");
               CrashReportCategory var6 = var5.makeCategory("Particle being ticked");
               var6.addCrashSectionCallable("Particle", new EffectRenderer$1(this, var3));
               var6.addCrashSectionCallable("Particle Type", new EffectRenderer$2(this, var1));
               throw new ReportedException(var5);
            }

            if(var3.isDead) {
               this.fxLayers[var1].remove(var2--);
            }
         }
      }

   }

   public void renderParticles(Entity var1, float var2) {
      float var3 = ActiveRenderInfo.rotationX;
      float var4 = ActiveRenderInfo.rotationZ;
      float var5 = ActiveRenderInfo.rotationYZ;
      float var6 = ActiveRenderInfo.rotationXY;
      float var7 = ActiveRenderInfo.rotationXZ;
      EntityFX.interpPosX = var1.lastTickPosX + (var1.posX - var1.lastTickPosX) * (double)var2;
      EntityFX.interpPosY = var1.lastTickPosY + (var1.posY - var1.lastTickPosY) * (double)var2;
      EntityFX.interpPosZ = var1.lastTickPosZ + (var1.posZ - var1.lastTickPosZ) * (double)var2;

      for(int var8 = 0; var8 < 3; ++var8) {
         if(!this.fxLayers[var8].isEmpty()) {
            switch(var8) {
            case 0:
            default:
               this.renderer.bindTexture(particleTextures);
               break;
            case 1:
               this.renderer.bindTexture(TextureMap.locationBlocksTexture);
               break;
            case 2:
               this.renderer.bindTexture(TextureMap.locationItemsTexture);
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDepthMask(false);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glAlphaFunc(516, 0.003921569F);
            Tessellator var9 = Tessellator.instance;
            var9.startDrawingQuads();

            for(int var10 = 0; var10 < this.fxLayers[var8].size(); ++var10) {
               EntityFX var11 = (EntityFX)this.fxLayers[var8].get(var10);
               var9.setBrightness(var11.getBrightnessForRender(var2));

               try {
                  var11.renderParticle(var9, var2, var3, var7, var4, var5, var6);
               } catch (Throwable var16) {
                  CrashReport var13 = CrashReport.makeCrashReport(var16, "Rendering Particle");
                  CrashReportCategory var14 = var13.makeCategory("Particle being rendered");
                  var14.addCrashSectionCallable("Particle", new EffectRenderer$3(this, var11));
                  var14.addCrashSectionCallable("Particle Type", new EffectRenderer$4(this, var8));
                  throw new ReportedException(var13);
               }
            }

            var9.draw();
            GL11.glDisable(3042);
            GL11.glDepthMask(true);
            GL11.glAlphaFunc(516, 0.1F);
         }
      }

   }

   public void renderLitParticles(Entity var1, float var2) {
      float var3 = 0.017453292F;
      float var4 = MathHelper.cos(var1.rotationYaw * 0.017453292F);
      float var5 = MathHelper.sin(var1.rotationYaw * 0.017453292F);
      float var6 = -var5 * MathHelper.sin(var1.rotationPitch * 0.017453292F);
      float var7 = var4 * MathHelper.sin(var1.rotationPitch * 0.017453292F);
      float var8 = MathHelper.cos(var1.rotationPitch * 0.017453292F);
      byte var9 = 3;
      List var10 = this.fxLayers[var9];
      if(!var10.isEmpty()) {
         Tessellator var11 = Tessellator.instance;

         for(int var12 = 0; var12 < var10.size(); ++var12) {
            EntityFX var13 = (EntityFX)var10.get(var12);
            var11.setBrightness(var13.getBrightnessForRender(var2));
            var13.renderParticle(var11, var2, var4, var8, var5, var6, var7);
         }

      }
   }

   public void clearEffects(World var1) {
      this.worldObj = var1;

      for(int var2 = 0; var2 < 4; ++var2) {
         this.fxLayers[var2].clear();
      }

   }

   public void addBlockDestroyEffects(int var1, int var2, int var3, Block var4, int var5) {
      if(var4.getMaterial() != Material.air) {
         byte var6 = 4;

         for(int var7 = 0; var7 < var6; ++var7) {
            for(int var8 = 0; var8 < var6; ++var8) {
               for(int var9 = 0; var9 < var6; ++var9) {
                  double var10 = (double)var1 + ((double)var7 + 0.5D) / (double)var6;
                  double var12 = (double)var2 + ((double)var8 + 0.5D) / (double)var6;
                  double var14 = (double)var3 + ((double)var9 + 0.5D) / (double)var6;
                  this.addEffect((new EntityDiggingFX(this.worldObj, var10, var12, var14, var10 - (double)var1 - 0.5D, var12 - (double)var2 - 0.5D, var14 - (double)var3 - 0.5D, var4, var5)).applyColourMultiplier(var1, var2, var3));
               }
            }
         }

      }
   }

   public void addBlockHitEffects(int var1, int var2, int var3, int var4) {
      Block var5 = this.worldObj.getBlock(var1, var2, var3);
      if(var5.getMaterial() != Material.air) {
         float var6 = 0.1F;
         double var7 = (double)var1 + this.rand.nextDouble() * (var5.getBlockBoundsMaxX() - var5.getBlockBoundsMinX() - (double)(var6 * 2.0F)) + (double)var6 + var5.getBlockBoundsMinX();
         double var9 = (double)var2 + this.rand.nextDouble() * (var5.getBlockBoundsMaxY() - var5.getBlockBoundsMinY() - (double)(var6 * 2.0F)) + (double)var6 + var5.getBlockBoundsMinY();
         double var11 = (double)var3 + this.rand.nextDouble() * (var5.getBlockBoundsMaxZ() - var5.getBlockBoundsMinZ() - (double)(var6 * 2.0F)) + (double)var6 + var5.getBlockBoundsMinZ();
         if(var4 == 0) {
            var9 = (double)var2 + var5.getBlockBoundsMinY() - (double)var6;
         }

         if(var4 == 1) {
            var9 = (double)var2 + var5.getBlockBoundsMaxY() + (double)var6;
         }

         if(var4 == 2) {
            var11 = (double)var3 + var5.getBlockBoundsMinZ() - (double)var6;
         }

         if(var4 == 3) {
            var11 = (double)var3 + var5.getBlockBoundsMaxZ() + (double)var6;
         }

         if(var4 == 4) {
            var7 = (double)var1 + var5.getBlockBoundsMinX() - (double)var6;
         }

         if(var4 == 5) {
            var7 = (double)var1 + var5.getBlockBoundsMaxX() + (double)var6;
         }

         this.addEffect((new EntityDiggingFX(this.worldObj, var7, var9, var11, 0.0D, 0.0D, 0.0D, var5, this.worldObj.getBlockMetadata(var1, var2, var3))).applyColourMultiplier(var1, var2, var3).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F));
      }
   }

   public String getStatistics() {
      return "" + (this.fxLayers[0].size() + this.fxLayers[1].size() + this.fxLayers[2].size());
   }

}
