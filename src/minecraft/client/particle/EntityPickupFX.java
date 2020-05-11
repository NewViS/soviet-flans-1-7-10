package net.minecraft.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class EntityPickupFX extends EntityFX {

   private Entity entityToPickUp;
   private Entity entityPickingUp;
   private int age;
   private int maxAge;
   private float yOffs;


   public EntityPickupFX(World var1, Entity var2, Entity var3, float var4) {
      super(var1, var2.posX, var2.posY, var2.posZ, var2.motionX, var2.motionY, var2.motionZ);
      this.entityToPickUp = var2;
      this.entityPickingUp = var3;
      this.maxAge = 3;
      this.yOffs = var4;
   }

   public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = ((float)this.age + var2) / (float)this.maxAge;
      var8 *= var8;
      double var9 = this.entityToPickUp.posX;
      double var11 = this.entityToPickUp.posY;
      double var13 = this.entityToPickUp.posZ;
      double var15 = this.entityPickingUp.lastTickPosX + (this.entityPickingUp.posX - this.entityPickingUp.lastTickPosX) * (double)var2;
      double var17 = this.entityPickingUp.lastTickPosY + (this.entityPickingUp.posY - this.entityPickingUp.lastTickPosY) * (double)var2 + (double)this.yOffs;
      double var19 = this.entityPickingUp.lastTickPosZ + (this.entityPickingUp.posZ - this.entityPickingUp.lastTickPosZ) * (double)var2;
      double var21 = var9 + (var15 - var9) * (double)var8;
      double var23 = var11 + (var17 - var11) * (double)var8;
      double var25 = var13 + (var19 - var13) * (double)var8;
      int var27 = this.getBrightnessForRender(var2);
      int var28 = var27 % 65536;
      int var29 = var27 / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var28 / 1.0F, (float)var29 / 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      var21 -= EntityFX.interpPosX;
      var23 -= EntityFX.interpPosY;
      var25 -= EntityFX.interpPosZ;
      RenderManager.instance.renderEntityWithPosYaw(this.entityToPickUp, (double)((float)var21), (double)((float)var23), (double)((float)var25), this.entityToPickUp.rotationYaw, var2);
   }

   public void onUpdate() {
      ++this.age;
      if(this.age == this.maxAge) {
         this.setDead();
      }

   }

   public int getFXLayer() {
      return 3;
   }
}
