package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderMooshroom extends RenderLiving {

   private static final ResourceLocation mooshroomTextures = new ResourceLocation("textures/entity/cow/mooshroom.png");


   public RenderMooshroom(ModelBase var1, float var2) {
      super(var1, var2);
   }

   public void doRender(EntityMooshroom var1, double var2, double var4, double var6, float var8, float var9) {
      super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   protected ResourceLocation getEntityTexture(EntityMooshroom var1) {
      return mooshroomTextures;
   }

   protected void renderEquippedItems(EntityMooshroom var1, float var2) {
      super.renderEquippedItems(var1, var2);
      if(!var1.isChild()) {
         this.bindTexture(TextureMap.locationBlocksTexture);
         GL11.glEnable(2884);
         GL11.glPushMatrix();
         GL11.glScalef(1.0F, -1.0F, 1.0F);
         GL11.glTranslatef(0.2F, 0.4F, 0.5F);
         GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
         super.field_147909_c.renderBlockAsItem(Blocks.red_mushroom, 0, 1.0F);
         GL11.glTranslatef(0.1F, 0.0F, -0.6F);
         GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
         super.field_147909_c.renderBlockAsItem(Blocks.red_mushroom, 0, 1.0F);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         ((ModelQuadruped)super.mainModel).head.postRender(0.0625F);
         GL11.glScalef(1.0F, -1.0F, 1.0F);
         GL11.glTranslatef(0.0F, 0.75F, -0.2F);
         GL11.glRotatef(12.0F, 0.0F, 1.0F, 0.0F);
         super.field_147909_c.renderBlockAsItem(Blocks.red_mushroom, 0, 1.0F);
         GL11.glPopMatrix();
         GL11.glDisable(2884);
      }
   }

   // $FF: synthetic method
   protected void renderEquippedItems(EntityLivingBase var1, float var2) {
      this.renderEquippedItems((EntityMooshroom)var1, var2);
   }

}
