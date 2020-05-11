package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import de.ItsAMysterious.mods.reallifemod.core.entitys.particles.EntityDrop;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderDrop extends Render {

   public void func_76986_a(Entity p_76986_1_, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
      EntityDrop drop = (EntityDrop)p_76986_1_;
      GL11.glPushMatrix();
      GL11.glTranslated(x, y, z);
      GL11.glShadeModel(7425);
      GL11.glEnable(3008);
      GL11.glDisable(2884);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glPointSize(5.0F);
      Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/particles/waterdrop.png"));
      Tessellator tessellator = Tessellator.instance;
      GL11.glPushMatrix();
      GL11.glRotated((double)Minecraft.getMinecraft().thePlayer.field_70759_as, 0.0D, -1.0D, 0.0D);
      GL11.glRotated((double)Minecraft.getMinecraft().thePlayer.field_70125_A, 1.0D, 0.0D, 0.0D);
      GL11.glScaled((double)(1 + drop.field_70173_aa / 20), (double)(1 - drop.field_70173_aa / 20), 1.0D);
      int number = drop.getParticleMaxAge() / (drop.field_70173_aa + 1);
      float var10000 = (float)(drop.getParticleMaxAge() / (drop.field_70173_aa + 1));
      tessellator.startDrawing(7);
      tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      tessellator.addVertexWithUV(0.05D, 0.0D, 0.0D, 1.0D, 0.0D);
      tessellator.addVertexWithUV(0.05D, 0.05D, 0.0D, 1.0D, 1.0D);
      tessellator.addVertexWithUV(0.0D, 0.05D, 0.0D, 0.0D, 1.0D);
      tessellator.draw();
      GL11.glPopMatrix();
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   protected ResourceLocation func_110775_a(Entity p_110775_1_) {
      return null;
   }
}
