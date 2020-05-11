package de.ItsAMysterious.mods.reallifemod.core.rendering.items;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class RPGRenderer implements IItemRenderer {

   protected IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/weapons/RPG.obj"));
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
      GL11.glDisable(2896);
      GL11.glDisable(2884);
      FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/items/RPG.png"));
      switch(null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[var1.ordinal()]) {
      case 1:
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(110.0F, 1.0F, 0.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.0F, -0.56F);
         GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
         float scale = 0.5F;
         GL11.glScalef(scale, scale, scale);
         this.model.renderAll();
         break;
      case 2:
         GL11.glRotatef(-95.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glTranslatef(0.975F, -0.15F, 1.85F);
         GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
         GL11.glScalef(1.5F, 1.5F, 1.5F);
         this.model.renderAll();
         break;
      case 3:
         this.model.renderAll();
         break;
      case 4:
         this.model.renderAll();
      }

      GL11.glEnable(2896);
      GL11.glPopMatrix();
   }
}
