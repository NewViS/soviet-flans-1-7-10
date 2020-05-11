package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderVillager extends RenderLiving {

   private static final ResourceLocation villagerTextures = new ResourceLocation("textures/entity/villager/villager.png");
   private static final ResourceLocation farmerVillagerTextures = new ResourceLocation("textures/entity/villager/farmer.png");
   private static final ResourceLocation librarianVillagerTextures = new ResourceLocation("textures/entity/villager/librarian.png");
   private static final ResourceLocation priestVillagerTextures = new ResourceLocation("textures/entity/villager/priest.png");
   private static final ResourceLocation smithVillagerTextures = new ResourceLocation("textures/entity/villager/smith.png");
   private static final ResourceLocation butcherVillagerTextures = new ResourceLocation("textures/entity/villager/butcher.png");
   protected ModelVillager villagerModel;


   public RenderVillager() {
      super(new ModelVillager(0.0F), 0.5F);
      this.villagerModel = (ModelVillager)super.mainModel;
   }

   protected int shouldRenderPass(EntityVillager var1, int var2, float var3) {
      return -1;
   }

   public void doRender(EntityVillager var1, double var2, double var4, double var6, float var8, float var9) {
      super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   protected ResourceLocation getEntityTexture(EntityVillager var1) {
      switch(var1.getProfession()) {
      case 0:
         return farmerVillagerTextures;
      case 1:
         return librarianVillagerTextures;
      case 2:
         return priestVillagerTextures;
      case 3:
         return smithVillagerTextures;
      case 4:
         return butcherVillagerTextures;
      default:
         return villagerTextures;
      }
   }

   protected void renderEquippedItems(EntityVillager var1, float var2) {
      super.renderEquippedItems(var1, var2);
   }

   protected void preRenderCallback(EntityVillager var1, float var2) {
      float var3 = 0.9375F;
      if(var1.getGrowingAge() < 0) {
         var3 = (float)((double)var3 * 0.5D);
         super.shadowSize = 0.25F;
      } else {
         super.shadowSize = 0.5F;
      }

      GL11.glScalef(var3, var3, var3);
   }

   // $FF: synthetic method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityVillager)var1, var2);
   }

   // $FF: synthetic method
   protected void renderEquippedItems(EntityLivingBase var1, float var2) {
      this.renderEquippedItems((EntityVillager)var1, var2);
   }

}
