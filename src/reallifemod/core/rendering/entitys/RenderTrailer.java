package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityTrailer;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class RenderTrailer extends Render {

   protected IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/Trailer.obj"));
   protected IModelCustom wheels = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/TrailerWheels.obj"));
   static ResourceLocation MainTexture = new ResourceLocation("reallifemod:models/streets/trailer.png");


   public void renderLowry(EntityTrailer entity, double x, double y, double z, float rotatedYaw, float rotatedPitch) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glTranslatef((float)x, (float)y, (float)z);
      GL11.glRotatef(entity.field_70177_z, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(entity.field_70125_A, 1.0F, 0.0F, 0.0F);
      this.func_110776_a(MainTexture);
      this.wheels.renderAll();
      this.model.renderAll();
      GL11.glPopMatrix();
   }

   public void func_76986_a(Entity entity, double x, double y, double z, float rotatedYaw, float rotatedPitch) {
      this.renderLowry((EntityTrailer)entity, x, y, z, rotatedYaw, rotatedPitch);
   }

   protected ResourceLocation func_110775_a(Entity p_110775_1_) {
      return MainTexture;
   }

   public boolean func_147905_a() {
      return false;
   }

   private static FloatBuffer asFloatBuffer(float[] values) {
      FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
      buffer.put(values);
      buffer.flip();
      return buffer;
   }

}
