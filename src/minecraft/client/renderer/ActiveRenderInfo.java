package net.minecraft.client.renderer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class ActiveRenderInfo {

   public static float objectX;
   public static float objectY;
   public static float objectZ;
   private static IntBuffer viewport = GLAllocation.createDirectIntBuffer(16);
   private static FloatBuffer modelview = GLAllocation.createDirectFloatBuffer(16);
   private static FloatBuffer projection = GLAllocation.createDirectFloatBuffer(16);
   private static FloatBuffer objectCoords = GLAllocation.createDirectFloatBuffer(3);
   public static float rotationX;
   public static float rotationXZ;
   public static float rotationZ;
   public static float rotationYZ;
   public static float rotationXY;


   public static void updateRenderInfo(EntityPlayer var0, boolean var1) {
      GL11.glGetFloat(2982, modelview);
      GL11.glGetFloat(2983, projection);
      GL11.glGetInteger(2978, viewport);
      float var2 = (float)((viewport.get(0) + viewport.get(2)) / 2);
      float var3 = (float)((viewport.get(1) + viewport.get(3)) / 2);
      GLU.gluUnProject(var2, var3, 0.0F, modelview, projection, viewport, objectCoords);
      objectX = objectCoords.get(0);
      objectY = objectCoords.get(1);
      objectZ = objectCoords.get(2);
      int var4 = var1?1:0;
      float var5 = var0.rotationPitch;
      float var6 = var0.rotationYaw;
      rotationX = MathHelper.cos(var6 * 3.1415927F / 180.0F) * (float)(1 - var4 * 2);
      rotationZ = MathHelper.sin(var6 * 3.1415927F / 180.0F) * (float)(1 - var4 * 2);
      rotationYZ = -rotationZ * MathHelper.sin(var5 * 3.1415927F / 180.0F) * (float)(1 - var4 * 2);
      rotationXY = rotationX * MathHelper.sin(var5 * 3.1415927F / 180.0F) * (float)(1 - var4 * 2);
      rotationXZ = MathHelper.cos(var5 * 3.1415927F / 180.0F);
   }

   public static Vec3 projectViewFromEntity(EntityLivingBase var0, double var1) {
      double var3 = var0.prevPosX + (var0.posX - var0.prevPosX) * var1;
      double var5 = var0.prevPosY + (var0.posY - var0.prevPosY) * var1 + (double)var0.getEyeHeight();
      double var7 = var0.prevPosZ + (var0.posZ - var0.prevPosZ) * var1;
      double var9 = var3 + (double)(objectX * 1.0F);
      double var11 = var5 + (double)(objectY * 1.0F);
      double var13 = var7 + (double)(objectZ * 1.0F);
      return Vec3.createVectorHelper(var9, var11, var13);
   }

   public static Block getBlockAtEntityViewpoint(World var0, EntityLivingBase var1, float var2) {
      Vec3 var3 = projectViewFromEntity(var1, (double)var2);
      ChunkPosition var4 = new ChunkPosition(var3);
      Block var5 = var0.getBlock(var4.chunkPosX, var4.chunkPosY, var4.chunkPosZ);
      if(var5.getMaterial().isLiquid()) {
         float var6 = BlockLiquid.getLiquidHeightPercent(var0.getBlockMetadata(var4.chunkPosX, var4.chunkPosY, var4.chunkPosZ)) - 0.11111111F;
         float var7 = (float)(var4.chunkPosY + 1) - var6;
         if(var3.yCoord >= (double)var7) {
            var5 = var0.getBlock(var4.chunkPosX, var4.chunkPosY + 1, var4.chunkPosZ);
         }
      }

      return var5;
   }

}
