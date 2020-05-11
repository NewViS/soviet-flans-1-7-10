package net.minecraft.client.renderer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.shader.TesselatorVertexState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.lwjgl.opengl.GL11;

public class WorldRenderer {

   private TesselatorVertexState vertexState;
   public World worldObj;
   private int glRenderList = -1;
   private static Tessellator tessellator = Tessellator.instance;
   public static int chunksUpdated;
   public int posX;
   public int posY;
   public int posZ;
   public int posXMinus;
   public int posYMinus;
   public int posZMinus;
   public int posXClip;
   public int posYClip;
   public int posZClip;
   public boolean isInFrustum;
   public boolean[] skipRenderPass = new boolean[2];
   public int posXPlus;
   public int posYPlus;
   public int posZPlus;
   public boolean needsUpdate;
   public AxisAlignedBB rendererBoundingBox;
   public int chunkIndex;
   public boolean isVisible = true;
   public boolean isWaitingOnOcclusionQuery;
   public int glOcclusionQuery;
   public boolean isChunkLit;
   private boolean isInitialized;
   public List tileEntityRenderers = new ArrayList();
   private List tileEntities;
   private int bytesDrawn;


   public WorldRenderer(World var1, List var2, int var3, int var4, int var5, int var6) {
      this.worldObj = var1;
      this.vertexState = null;
      this.tileEntities = var2;
      this.glRenderList = var6;
      this.posX = -999;
      this.setPosition(var3, var4, var5);
      this.needsUpdate = false;
   }

   public void setPosition(int var1, int var2, int var3) {
      if(var1 != this.posX || var2 != this.posY || var3 != this.posZ) {
         this.setDontDraw();
         this.posX = var1;
         this.posY = var2;
         this.posZ = var3;
         this.posXPlus = var1 + 8;
         this.posYPlus = var2 + 8;
         this.posZPlus = var3 + 8;
         this.posXClip = var1 & 1023;
         this.posYClip = var2;
         this.posZClip = var3 & 1023;
         this.posXMinus = var1 - this.posXClip;
         this.posYMinus = var2 - this.posYClip;
         this.posZMinus = var3 - this.posZClip;
         float var4 = 6.0F;
         this.rendererBoundingBox = AxisAlignedBB.getBoundingBox((double)((float)var1 - var4), (double)((float)var2 - var4), (double)((float)var3 - var4), (double)((float)(var1 + 16) + var4), (double)((float)(var2 + 16) + var4), (double)((float)(var3 + 16) + var4));
         GL11.glNewList(this.glRenderList + 2, 4864);
         RenderItem.renderAABB(AxisAlignedBB.getBoundingBox((double)((float)this.posXClip - var4), (double)((float)this.posYClip - var4), (double)((float)this.posZClip - var4), (double)((float)(this.posXClip + 16) + var4), (double)((float)(this.posYClip + 16) + var4), (double)((float)(this.posZClip + 16) + var4)));
         GL11.glEndList();
         this.markDirty();
      }
   }

   private void setupGLTranslation() {
      GL11.glTranslatef((float)this.posXClip, (float)this.posYClip, (float)this.posZClip);
   }

   public void updateRenderer(EntityLivingBase var1) {
      if(this.needsUpdate) {
         this.needsUpdate = false;
         int var2 = this.posX;
         int var3 = this.posY;
         int var4 = this.posZ;
         int var5 = this.posX + 16;
         int var6 = this.posY + 16;
         int var7 = this.posZ + 16;

         for(int var8 = 0; var8 < 2; ++var8) {
            this.skipRenderPass[var8] = true;
         }

         Chunk.isLit = false;
         HashSet var26 = new HashSet();
         var26.addAll(this.tileEntityRenderers);
         this.tileEntityRenderers.clear();
         Minecraft var9 = Minecraft.getMinecraft();
         EntityLivingBase var10 = var9.renderViewEntity;
         int var11 = MathHelper.floor_double(var10.posX);
         int var12 = MathHelper.floor_double(var10.posY);
         int var13 = MathHelper.floor_double(var10.posZ);
         byte var14 = 1;
         ChunkCache var15 = new ChunkCache(this.worldObj, var2 - var14, var3 - var14, var4 - var14, var5 + var14, var6 + var14, var7 + var14, var14);
         if(!var15.extendedLevelsInChunkCache()) {
            ++chunksUpdated;
            RenderBlocks var16 = new RenderBlocks(var15);
            this.bytesDrawn = 0;
            this.vertexState = null;

            for(int var17 = 0; var17 < 2; ++var17) {
               boolean var18 = false;
               boolean var19 = false;
               boolean var20 = false;

               for(int var21 = var3; var21 < var6; ++var21) {
                  for(int var22 = var4; var22 < var7; ++var22) {
                     for(int var23 = var2; var23 < var5; ++var23) {
                        Block var24 = var15.getBlock(var23, var21, var22);
                        if(var24.getMaterial() != Material.air) {
                           if(!var20) {
                              var20 = true;
                              this.preRenderBlocks(var17);
                           }

                           if(var17 == 0 && var24.hasTileEntity()) {
                              TileEntity var25 = var15.getTileEntity(var23, var21, var22);
                              if(TileEntityRendererDispatcher.instance.hasSpecialRenderer(var25)) {
                                 this.tileEntityRenderers.add(var25);
                              }
                           }

                           int var28 = var24.getRenderBlockPass();
                           if(var28 > var17) {
                              var18 = true;
                           } else if(var28 == var17) {
                              var19 |= var16.renderBlockByRenderType(var24, var23, var21, var22);
                              if(var24.getRenderType() == 0 && var23 == var11 && var21 == var12 && var22 == var13) {
                                 var16.setRenderFromInside(true);
                                 var16.setRenderAllFaces(true);
                                 var16.renderBlockByRenderType(var24, var23, var21, var22);
                                 var16.setRenderFromInside(false);
                                 var16.setRenderAllFaces(false);
                              }
                           }
                        }
                     }
                  }
               }

               if(var19) {
                  this.skipRenderPass[var17] = false;
               }

               if(var20) {
                  this.postRenderBlocks(var17, var1);
               } else {
                  var19 = false;
               }

               if(!var18) {
                  break;
               }
            }
         }

         HashSet var27 = new HashSet();
         var27.addAll(this.tileEntityRenderers);
         var27.removeAll(var26);
         this.tileEntities.addAll(var27);
         var26.removeAll(this.tileEntityRenderers);
         this.tileEntities.removeAll(var26);
         this.isChunkLit = Chunk.isLit;
         this.isInitialized = true;
      }
   }

   private void preRenderBlocks(int var1) {
      GL11.glNewList(this.glRenderList + var1, 4864);
      GL11.glPushMatrix();
      this.setupGLTranslation();
      float var2 = 1.000001F;
      GL11.glTranslatef(-8.0F, -8.0F, -8.0F);
      GL11.glScalef(var2, var2, var2);
      GL11.glTranslatef(8.0F, 8.0F, 8.0F);
      tessellator.startDrawingQuads();
      tessellator.setTranslation((double)(-this.posX), (double)(-this.posY), (double)(-this.posZ));
   }

   private void postRenderBlocks(int var1, EntityLivingBase var2) {
      if(var1 == 1 && !this.skipRenderPass[var1]) {
         this.vertexState = tessellator.getVertexState((float)var2.posX, (float)var2.posY, (float)var2.posZ);
      }

      this.bytesDrawn += tessellator.draw();
      GL11.glPopMatrix();
      GL11.glEndList();
      tessellator.setTranslation(0.0D, 0.0D, 0.0D);
   }

   public void updateRendererSort(EntityLivingBase var1) {
      if(this.vertexState != null && !this.skipRenderPass[1]) {
         this.preRenderBlocks(1);
         tessellator.setVertexState(this.vertexState);
         this.postRenderBlocks(1, var1);
      }
   }

   public float distanceToEntitySquared(Entity var1) {
      float var2 = (float)(var1.posX - (double)this.posXPlus);
      float var3 = (float)(var1.posY - (double)this.posYPlus);
      float var4 = (float)(var1.posZ - (double)this.posZPlus);
      return var2 * var2 + var3 * var3 + var4 * var4;
   }

   public void setDontDraw() {
      for(int var1 = 0; var1 < 2; ++var1) {
         this.skipRenderPass[var1] = true;
      }

      this.isInFrustum = false;
      this.isInitialized = false;
      this.vertexState = null;
   }

   public void stopRendering() {
      this.setDontDraw();
      this.worldObj = null;
   }

   public int getGLCallListForPass(int var1) {
      return !this.isInFrustum?-1:(!this.skipRenderPass[var1]?this.glRenderList + var1:-1);
   }

   public void updateInFrustum(ICamera var1) {
      this.isInFrustum = var1.isBoundingBoxInFrustum(this.rendererBoundingBox);
   }

   public void callOcclusionQueryList() {
      GL11.glCallList(this.glRenderList + 2);
   }

   public boolean skipAllRenderPasses() {
      return !this.isInitialized?false:this.skipRenderPass[0] && this.skipRenderPass[1];
   }

   public void markDirty() {
      this.needsUpdate = true;
   }

}
