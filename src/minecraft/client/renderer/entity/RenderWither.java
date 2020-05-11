package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelWither;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderWither extends RenderLiving {

   private static final ResourceLocation invulnerableWitherTextures = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
   private static final ResourceLocation witherTextures = new ResourceLocation("textures/entity/wither/wither.png");
   private int field_82419_a;


   public RenderWither() {
      super(new ModelWither(), 1.0F);
      this.field_82419_a = ((ModelWither)super.mainModel).func_82903_a();
   }

   public void doRender(EntityWither var1, double var2, double var4, double var6, float var8, float var9) {
      BossStatus.setBossStatus(var1, true);
      int var10 = ((ModelWither)super.mainModel).func_82903_a();
      if(var10 != this.field_82419_a) {
         this.field_82419_a = var10;
         super.mainModel = new ModelWither();
      }

      super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   protected ResourceLocation getEntityTexture(EntityWither var1) {
      int var2 = var1.func_82212_n();
      return var2 > 0 && (var2 > 80 || var2 / 5 % 2 != 1)?invulnerableWitherTextures:witherTextures;
   }

   protected void preRenderCallback(EntityWither var1, float var2) {
      int var3 = var1.func_82212_n();
      if(var3 > 0) {
         float var4 = 2.0F - ((float)var3 - var2) / 220.0F * 0.5F;
         GL11.glScalef(var4, var4, var4);
      } else {
         GL11.glScalef(2.0F, 2.0F, 2.0F);
      }

   }

   protected int shouldRenderPass(EntityWither var1, int var2, float var3) {
      if(var1.isArmored()) {
         if(var1.isInvisible()) {
            GL11.glDepthMask(false);
         } else {
            GL11.glDepthMask(true);
         }

         if(var2 == 1) {
            float var4 = (float)var1.ticksExisted + var3;
            this.bindTexture(invulnerableWitherTextures);
            GL11.glMatrixMode(5890);
            GL11.glLoadIdentity();
            float var5 = MathHelper.cos(var4 * 0.02F) * 3.0F;
            float var6 = var4 * 0.01F;
            GL11.glTranslatef(var5, var6, 0.0F);
            this.setRenderPassModel(super.mainModel);
            GL11.glMatrixMode(5888);
            GL11.glEnable(3042);
            float var7 = 0.5F;
            GL11.glColor4f(var7, var7, var7, 1.0F);
            GL11.glDisable(2896);
            GL11.glBlendFunc(1, 1);
            GL11.glTranslatef(0.0F, -0.01F, 0.0F);
            GL11.glScalef(1.1F, 1.1F, 1.1F);
            return 1;
         }

         if(var2 == 2) {
            GL11.glMatrixMode(5890);
            GL11.glLoadIdentity();
            GL11.glMatrixMode(5888);
            GL11.glEnable(2896);
            GL11.glDisable(3042);
         }
      }

      return -1;
   }

   protected int inheritRenderPass(EntityWither var1, int var2, float var3) {
      return -1;
   }

}
