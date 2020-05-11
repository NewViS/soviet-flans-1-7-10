package de.ItsAMysterious.mods.reallifemod.core.rendering.items;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.core.items.AK47Item;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class AK47Renderer implements IItemRenderer {

   protected IModelCustom AK = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/weapons/AK47.obj"));
   protected float rot = 0.0F;


   public boolean handleRenderType(ItemStack var1, ItemRenderType var2) {
      switch(null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[var2.ordinal()]) {
      case 1:
         return true;
      case 2:
         return true;
      default:
         return false;
      }
   }

   public boolean shouldUseRenderHelper(ItemRenderType var1, ItemStack var2, ItemRendererHelper data) {
      return false;
   }

   public void renderItem(ItemRenderType var1, ItemStack var2, Object ... data) {
      GL11.glPushMatrix();
      EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
      GL11.glDisable(2896);
      GL11.glDisable(2884);
      FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/items/AK47Texture.png"));
      switch(null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[var1.ordinal()]) {
      case 1:
         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glTranslatef(0.0F, -0.1F, -0.46F);
         float scale = 0.5F;
         GL11.glScalef(scale, scale, scale);
         this.AK.renderAll();
         break;
      case 2:
         AK47Item item = (AK47Item)var2.getItem();
         if(!item.aiming) {
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-0.85F, -0.1F, 0.6F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
         } else {
            GL11.glRotatef(-95.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-0.975F, -0.1F, 1.85F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
         }

         this.AK.renderAll();
         break;
      case 3:
         this.AK.renderAll();
      }

      GL11.glEnable(2896);
      GL11.glPopMatrix();
   }
}
