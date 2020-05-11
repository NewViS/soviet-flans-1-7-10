package net.minecraft.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderWitch extends RenderLiving {

   private static final ResourceLocation witchTextures = new ResourceLocation("textures/entity/witch.png");
   private final ModelWitch witchModel;


   public RenderWitch() {
      super(new ModelWitch(0.0F), 0.5F);
      this.witchModel = (ModelWitch)super.mainModel;
   }

   public void doRender(EntityWitch var1, double var2, double var4, double var6, float var8, float var9) {
      ItemStack var10 = var1.getHeldItem();
      this.witchModel.field_82900_g = var10 != null;
      super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   protected ResourceLocation getEntityTexture(EntityWitch var1) {
      return witchTextures;
   }

   protected void renderEquippedItems(EntityWitch var1, float var2) {
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      super.renderEquippedItems(var1, var2);
      ItemStack var3 = var1.getHeldItem();
      if(var3 != null) {
         GL11.glPushMatrix();
         float var4;
         if(super.mainModel.isChild) {
            var4 = 0.5F;
            GL11.glTranslatef(0.0F, 0.625F, 0.0F);
            GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
            GL11.glScalef(var4, var4, var4);
         }

         this.witchModel.villagerNose.postRender(0.0625F);
         GL11.glTranslatef(-0.0625F, 0.53125F, 0.21875F);
         if(var3.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(var3.getItem()).getRenderType())) {
            var4 = 0.5F;
            GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
            var4 *= 0.75F;
            GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(var4, -var4, var4);
         } else if(var3.getItem() == Items.bow) {
            var4 = 0.625F;
            GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
            GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(var4, -var4, var4);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         } else if(var3.getItem().isFull3D()) {
            var4 = 0.625F;
            if(var3.getItem().shouldRotateAroundWhenRendering()) {
               GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
               GL11.glTranslatef(0.0F, -0.125F, 0.0F);
            }

            this.func_82410_b();
            GL11.glScalef(var4, -var4, var4);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         } else {
            var4 = 0.375F;
            GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
            GL11.glScalef(var4, var4, var4);
            GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         }

         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
         super.renderManager.itemRenderer.renderItem(var1, var3, 0);
         if(var3.getItem().requiresMultipleRenderPasses()) {
            super.renderManager.itemRenderer.renderItem(var1, var3, 1);
         }

         GL11.glPopMatrix();
      }

   }

   protected void func_82410_b() {
      GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
   }

   protected void preRenderCallback(EntityWitch var1, float var2) {
      float var3 = 0.9375F;
      GL11.glScalef(var3, var3, var3);
   }

   // $FF: synthetic method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityWitch)var1, var2);
   }

   // $FF: synthetic method
   protected void renderEquippedItems(EntityLivingBase var1, float var2) {
      this.renderEquippedItems((EntityWitch)var1, var2);
   }

}
