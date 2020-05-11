package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.util.ResourceLocation;

public class RenderSilverfish extends RenderLiving {

   private static final ResourceLocation silverfishTextures = new ResourceLocation("textures/entity/silverfish.png");


   public RenderSilverfish() {
      super(new ModelSilverfish(), 0.3F);
   }

   protected float getDeathMaxRotation(EntitySilverfish var1) {
      return 180.0F;
   }

   public void doRender(EntitySilverfish var1, double var2, double var4, double var6, float var8, float var9) {
      super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   protected ResourceLocation getEntityTexture(EntitySilverfish var1) {
      return silverfishTextures;
   }

   protected int shouldRenderPass(EntitySilverfish var1, int var2, float var3) {
      return -1;
   }

   // $FF: synthetic method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntitySilverfish)var1);
   }

}
