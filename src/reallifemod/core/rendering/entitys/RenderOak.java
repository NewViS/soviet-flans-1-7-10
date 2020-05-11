package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class RenderOak extends Render {

   public static IModelCustom logmodel;
   public static IModelCustom leavesmodel;


   public RenderOak() {
      logmodel = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/outdoor/Trees/OakTrunk.obj"));
      leavesmodel = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/outdoor/Trees/OakLeaves.obj"));
   }

   public void func_76986_a(Entity log, double x, double y, double z, float rotationPitch, float rotationYaw) {
      GL11.glPushMatrix();
      GL11.glTranslated(x, y, z);
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      GL11.glRotated((double)log.rotationYaw, 0.0D, 1.0D, 0.0D);
      GL11.glRotated((double)log.rotationPitch, 1.0D, 0.0D, 0.0D);
      GL11.glPushMatrix();
      Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/tiles/oakbark.png"));
      logmodel.renderAll();
      GL11.glPopMatrix();
      Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/tiles/oakleaves.png"));
      leavesmodel.renderAll();
      GL11.glPopMatrix();
   }

   protected ResourceLocation func_110775_a(Entity log) {
      return null;
   }
}
