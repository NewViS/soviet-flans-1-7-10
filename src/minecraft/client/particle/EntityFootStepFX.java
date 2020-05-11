package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class EntityFootStepFX extends EntityFX {

   private static final ResourceLocation field_110126_a = new ResourceLocation("textures/particle/footprint.png");
   private int footstepAge;
   private int footstepMaxAge;
   private TextureManager currentFootSteps;


   public EntityFootStepFX(TextureManager var1, World var2, double var3, double var5, double var7) {
      super(var2, var3, var5, var7, 0.0D, 0.0D, 0.0D);
      this.currentFootSteps = var1;
      super.motionX = super.motionY = super.motionZ = 0.0D;
      this.footstepMaxAge = 200;
   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = ((float)this.footstepAge + var2) / (float)this.footstepMaxAge;
      var8 *= var8;
      float var9 = 2.0F - var8 * 2.0F;
      if(var9 > 1.0F) {
         var9 = 1.0F;
      }

      var9 *= 0.2F;
      GL11.glDisable(2896);
      float var10 = 0.125F;
      float var11 = (float)(super.posX - EntityFX.interpPosX);
      float var12 = (float)(super.posY - EntityFX.interpPosY);
      float var13 = (float)(super.posZ - EntityFX.interpPosZ);
      float var14 = super.worldObj.getLightBrightness(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ));
      this.currentFootSteps.bindTexture(field_110126_a);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      var1.startDrawingQuads();
      var1.setColorRGBA_F(var14, var14, var14, var9);
      var1.addVertexWithUV((double)(var11 - var10), (double)var12, (double)(var13 + var10), 0.0D, 1.0D);
      var1.addVertexWithUV((double)(var11 + var10), (double)var12, (double)(var13 + var10), 1.0D, 1.0D);
      var1.addVertexWithUV((double)(var11 + var10), (double)var12, (double)(var13 - var10), 1.0D, 0.0D);
      var1.addVertexWithUV((double)(var11 - var10), (double)var12, (double)(var13 - var10), 0.0D, 0.0D);
      var1.draw();
      GL11.glDisable(3042);
      GL11.glEnable(2896);
   }

   public void onUpdate() {
      ++this.footstepAge;
      if(this.footstepAge == this.footstepMaxAge) {
         this.setDead();
      }

   }

   public int getFXLayer() {
      return 3;
   }

}
