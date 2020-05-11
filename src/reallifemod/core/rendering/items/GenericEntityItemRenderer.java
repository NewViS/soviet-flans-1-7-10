package de.ItsAMysterious.mods.reallifemod.core.rendering.items;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class GenericEntityItemRenderer implements IItemRenderer {

   Render render;
   private Entity entity;


   public GenericEntityItemRenderer(Render render, Entity entity) {
      this.entity = entity;
      this.render = render;
   }

   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      return true;
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return true;
   }

   public void renderItem(ItemRenderType type, ItemStack item, Object ... data) {
      if(type == ItemRenderType.ENTITY) {
         GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
      }

      GL11.glPushMatrix();
      renderEntity(0, 0, 0, 0.0F, 0.0F, this.entity);
      GL11.glPopMatrix();
   }

   public static void renderEntity(int p_147046_0_, int p_147046_1_, int p_147046_2_, float rot, float p_147046_4_, Entity p_147046_5_) {
      GL11.glPushMatrix();
      GL11.glTranslatef((float)p_147046_0_, (float)p_147046_1_, 100.0F);
      GL11.glScalef((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      float f2 = 0.0F;
      float f3 = 0.0F;
      float f4 = p_147046_5_.rotationPitch;
      float f5 = 0.0F;
      float f6 = 0.0F;
      GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      p_147046_5_.rotationYaw = 180.0F;
      p_147046_5_.rotationPitch = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
      GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
      RenderManager.instance.playerViewY = rot;
      RenderManager.instance.renderEntityWithPosYaw(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
      p_147046_5_.rotationYaw = f3;
      p_147046_5_.rotationPitch = f4;
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable('\u803a');
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glDisable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }
}
