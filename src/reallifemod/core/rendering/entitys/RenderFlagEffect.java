package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFlagEffect extends Render {

   private float wavetime;
   private float waveWidth;
   private float waveHeight;


   public void func_76986_a(Entity entity, double x, double y, double z, float rotationYaw, float rotationPitch) {}

   public void doWaving(Entity entity, double x, double y, double z, float rotationYaw, float rotationPitch) {
      float TS = 0.025F;
      GL11.glBegin(7);

      for(int i = -20; i < 20; ++i) {
         for(int j = -20; j < 20; ++j) {
            float startX = TS * (float)(i + 20);
            float startY = TS * (float)(j + 20);
            GL11.glTexCoord2f(startX + 0.0F, startY + 0.0F);
            GL11.glVertex2f((float)i, (float)j);
            GL11.glTexCoord2f(startX + TS, startY + 0.0F);
            GL11.glVertex2f((float)(i + 1), (float)j);
            GL11.glTexCoord2f(startX + TS, startY + TS);
            GL11.glVertex2f((float)(i + 1), (float)(j + 1));
            GL11.glTexCoord2f(startX + 0.0F, startY + TS);
            GL11.glVertex2f((float)i, (float)(j + 1));
         }
      }

      GL11.glEnd();
   }

   protected ResourceLocation func_110775_a(Entity entity) {
      return null;
   }
}
