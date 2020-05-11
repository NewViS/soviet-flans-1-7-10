package de.ItsAMysterious.mods.reallifemod.core.rendering.items;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.core.items.ItemUzi;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class uziRenderer implements IItemRenderer {

   protected IModelCustom AK = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/weapons/uzi.obj"));
   protected float rot = 0.0F;


   public boolean handleRenderType(ItemStack var1, ItemRenderType var2) {
      return true;
   }

   public boolean shouldUseRenderHelper(ItemRenderType var1, ItemStack var2, ItemRendererHelper data) {
      return false;
   }

   public void renderItem(ItemRenderType var1, ItemStack var2, Object ... data) {
      GL11.glPushMatrix();
      FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/items/uzi.png"));
      ItemUzi item;
      switch(null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[var1.ordinal()]) {
      case 1:
         item = (ItemUzi)var2.getItem();
         if(!item.aiming) {
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-0.85F, 0.0F, 0.6F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
         } else {
            GL11.glRotatef(-95.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(20.0F, 10.1F, 0.0F, 0.0F);
            GL11.glTranslatef(-1.0F, 0.2F, 0.6F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
         }

         float scale = 0.5F;
         GL11.glScalef(scale, scale, scale);
         this.AK.renderAll();
         break;
      case 2:
         item = (ItemUzi)var2.getItem();
         if(!item.aiming) {
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-0.85F, 0.0F, 0.6F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
         } else {
            GL11.glRotatef(-95.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(20.0F, 10.1F, 0.0F, 0.0F);
            GL11.glTranslatef(-1.0F, 0.2F, 0.6F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
         }

         this.AK.renderAll();
         break;
      case 3:
         item = (ItemUzi)var2.getItem();
         if(!item.aiming) {
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-0.85F, 0.0F, 0.6F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
         } else {
            GL11.glRotatef(-95.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(20.0F, 10.1F, 0.0F, 0.0F);
            GL11.glTranslatef(-1.0F, 0.2F, 0.6F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
         }

         this.AK.renderAll();
      }

      GL11.glPopMatrix();
   }
}
