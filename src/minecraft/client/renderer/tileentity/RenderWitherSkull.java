package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderWitherSkull extends Render {

   private static final ResourceLocation invulnerableWitherTextures = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
   private static final ResourceLocation witherTextures = new ResourceLocation("textures/entity/wither/wither.png");
   private final ModelSkeletonHead skeletonHeadModel = new ModelSkeletonHead();


   private float func_82400_a(float var1, float var2, float var3) {
      float var4;
      for(var4 = var2 - var1; var4 < -180.0F; var4 += 360.0F) {
         ;
      }

      while(var4 >= 180.0F) {
         var4 -= 360.0F;
      }

      return var1 + var3 * var4;
   }

   public void doRender(EntityWitherSkull var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      float var10 = this.func_82400_a(var1.prevRotationYaw, var1.rotationYaw, var9);
      float var11 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var9;
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      float var12 = 0.0625F;
      GL11.glEnable('\u803a');
      GL11.glScalef(-1.0F, -1.0F, 1.0F);
      GL11.glEnable(3008);
      this.bindEntityTexture(var1);
      this.skeletonHeadModel.render(var1, 0.0F, 0.0F, 0.0F, var10, var11, var12);
      GL11.glPopMatrix();
   }

   protected ResourceLocation getEntityTexture(EntityWitherSkull var1) {
      return var1.isInvulnerable()?invulnerableWitherTextures:witherTextures;
   }

}
