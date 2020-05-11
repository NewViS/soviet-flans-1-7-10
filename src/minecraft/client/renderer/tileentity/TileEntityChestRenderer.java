package net.minecraft.client.renderer.tileentity;

import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityChestRenderer extends TileEntitySpecialRenderer {

   private static final ResourceLocation field_147507_b = new ResourceLocation("textures/entity/chest/trapped_double.png");
   private static final ResourceLocation field_147508_c = new ResourceLocation("textures/entity/chest/christmas_double.png");
   private static final ResourceLocation field_147505_d = new ResourceLocation("textures/entity/chest/normal_double.png");
   private static final ResourceLocation field_147506_e = new ResourceLocation("textures/entity/chest/trapped.png");
   private static final ResourceLocation field_147503_f = new ResourceLocation("textures/entity/chest/christmas.png");
   private static final ResourceLocation field_147504_g = new ResourceLocation("textures/entity/chest/normal.png");
   private ModelChest field_147510_h = new ModelChest();
   private ModelChest field_147511_i = new ModelLargeChest();
   private boolean field_147509_j;


   public TileEntityChestRenderer() {
      Calendar var1 = Calendar.getInstance();
      if(var1.get(2) + 1 == 12 && var1.get(5) >= 24 && var1.get(5) <= 26) {
         this.field_147509_j = true;
      }

   }

   public void renderTileEntityAt(TileEntityChest var1, double var2, double var4, double var6, float var8) {
      int var9;
      if(!var1.hasWorldObj()) {
         var9 = 0;
      } else {
         Block var10 = var1.getBlockType();
         var9 = var1.getBlockMetadata();
         if(var10 instanceof BlockChest && var9 == 0) {
            ((BlockChest)var10).func_149954_e(var1.getWorldObj(), var1.xCoord, var1.yCoord, var1.zCoord);
            var9 = var1.getBlockMetadata();
         }

         var1.checkForAdjacentChests();
      }

      if(var1.adjacentChestZNeg == null && var1.adjacentChestXNeg == null) {
         ModelChest var14;
         if(var1.adjacentChestXPos == null && var1.adjacentChestZPos == null) {
            var14 = this.field_147510_h;
            if(var1.func_145980_j() == 1) {
               this.bindTexture(field_147506_e);
            } else if(this.field_147509_j) {
               this.bindTexture(field_147503_f);
            } else {
               this.bindTexture(field_147504_g);
            }
         } else {
            var14 = this.field_147511_i;
            if(var1.func_145980_j() == 1) {
               this.bindTexture(field_147507_b);
            } else if(this.field_147509_j) {
               this.bindTexture(field_147508_c);
            } else {
               this.bindTexture(field_147505_d);
            }
         }

         GL11.glPushMatrix();
         GL11.glEnable('\u803a');
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glTranslatef((float)var2, (float)var4 + 1.0F, (float)var6 + 1.0F);
         GL11.glScalef(1.0F, -1.0F, -1.0F);
         GL11.glTranslatef(0.5F, 0.5F, 0.5F);
         short var11 = 0;
         if(var9 == 2) {
            var11 = 180;
         }

         if(var9 == 3) {
            var11 = 0;
         }

         if(var9 == 4) {
            var11 = 90;
         }

         if(var9 == 5) {
            var11 = -90;
         }

         if(var9 == 2 && var1.adjacentChestXPos != null) {
            GL11.glTranslatef(1.0F, 0.0F, 0.0F);
         }

         if(var9 == 5 && var1.adjacentChestZPos != null) {
            GL11.glTranslatef(0.0F, 0.0F, -1.0F);
         }

         GL11.glRotatef((float)var11, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
         float var12 = var1.prevLidAngle + (var1.lidAngle - var1.prevLidAngle) * var8;
         float var13;
         if(var1.adjacentChestZNeg != null) {
            var13 = var1.adjacentChestZNeg.prevLidAngle + (var1.adjacentChestZNeg.lidAngle - var1.adjacentChestZNeg.prevLidAngle) * var8;
            if(var13 > var12) {
               var12 = var13;
            }
         }

         if(var1.adjacentChestXNeg != null) {
            var13 = var1.adjacentChestXNeg.prevLidAngle + (var1.adjacentChestXNeg.lidAngle - var1.adjacentChestXNeg.prevLidAngle) * var8;
            if(var13 > var12) {
               var12 = var13;
            }
         }

         var12 = 1.0F - var12;
         var12 = 1.0F - var12 * var12 * var12;
         var14.chestLid.rotateAngleX = -(var12 * 3.1415927F / 2.0F);
         var14.renderAll();
         GL11.glDisable('\u803a');
         GL11.glPopMatrix();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

}
