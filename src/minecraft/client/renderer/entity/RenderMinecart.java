package net.minecraft.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class RenderMinecart extends Render {

   private static final ResourceLocation minecartTextures = new ResourceLocation("textures/entity/minecart.png");
   protected ModelBase modelMinecart = new ModelMinecart();
   protected final RenderBlocks field_94145_f;


   public RenderMinecart() {
      super.shadowSize = 0.5F;
      this.field_94145_f = new RenderBlocks();
   }

   public void doRender(EntityMinecart var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      this.bindEntityTexture(var1);
      long var10 = (long)var1.getEntityId() * 493286711L;
      var10 = var10 * var10 * 4392167121L + var10 * 98761L;
      float var12 = (((float)(var10 >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float var13 = (((float)(var10 >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float var14 = (((float)(var10 >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      GL11.glTranslatef(var12, var13, var14);
      double var15 = var1.lastTickPosX + (var1.posX - var1.lastTickPosX) * (double)var9;
      double var17 = var1.lastTickPosY + (var1.posY - var1.lastTickPosY) * (double)var9;
      double var19 = var1.lastTickPosZ + (var1.posZ - var1.lastTickPosZ) * (double)var9;
      double var21 = 0.30000001192092896D;
      Vec3 var23 = var1.func_70489_a(var15, var17, var19);
      float var24 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var9;
      if(var23 != null) {
         Vec3 var25 = var1.func_70495_a(var15, var17, var19, var21);
         Vec3 var26 = var1.func_70495_a(var15, var17, var19, -var21);
         if(var25 == null) {
            var25 = var23;
         }

         if(var26 == null) {
            var26 = var23;
         }

         var2 += var23.xCoord - var15;
         var4 += (var25.yCoord + var26.yCoord) / 2.0D - var17;
         var6 += var23.zCoord - var19;
         Vec3 var27 = var26.addVector(-var25.xCoord, -var25.yCoord, -var25.zCoord);
         if(var27.lengthVector() != 0.0D) {
            var27 = var27.normalize();
            var8 = (float)(Math.atan2(var27.zCoord, var27.xCoord) * 180.0D / 3.141592653589793D);
            var24 = (float)(Math.atan(var27.yCoord) * 73.0D);
         }
      }

      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      GL11.glRotatef(180.0F - var8, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-var24, 0.0F, 0.0F, 1.0F);
      float var31 = (float)var1.getRollingAmplitude() - var9;
      float var32 = var1.getDamage() - var9;
      if(var32 < 0.0F) {
         var32 = 0.0F;
      }

      if(var31 > 0.0F) {
         GL11.glRotatef(MathHelper.sin(var31) * var31 * var32 / 10.0F * (float)var1.getRollingDirection(), 1.0F, 0.0F, 0.0F);
      }

      int var33 = var1.getDisplayTileOffset();
      Block var28 = var1.func_145820_n();
      int var29 = var1.getDisplayTileData();
      if(var28.getRenderType() != -1) {
         GL11.glPushMatrix();
         this.bindTexture(TextureMap.locationBlocksTexture);
         float var30 = 0.75F;
         GL11.glScalef(var30, var30, var30);
         GL11.glTranslatef(0.0F, (float)var33 / 16.0F, 0.0F);
         this.func_147910_a(var1, var9, var28, var29);
         GL11.glPopMatrix();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.bindEntityTexture(var1);
      }

      GL11.glScalef(-1.0F, -1.0F, 1.0F);
      this.modelMinecart.render(var1, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GL11.glPopMatrix();
   }

   protected ResourceLocation getEntityTexture(EntityMinecart var1) {
      return minecartTextures;
   }

   protected void func_147910_a(EntityMinecart var1, float var2, Block var3, int var4) {
      float var5 = var1.getBrightness(var2);
      GL11.glPushMatrix();
      this.field_94145_f.renderBlockAsItem(var3, var4, var5);
      GL11.glPopMatrix();
   }

}
