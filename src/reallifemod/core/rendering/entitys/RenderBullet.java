package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import de.ItsAMysterious.mods.reallifemod.core.entitys.bullets.EntityBullet;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class RenderBullet extends Render {

   protected IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/weapons/Bullet.obj"));
   ResourceLocation MainTexture = new ResourceLocation("reallifemod:textures/general/iron-messing.png");


   public static FloatBuffer allocFloats(int howmany) {
      return ByteBuffer.allocateDirect(howmany * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
   }

   public static FloatBuffer allocFloats(float[] floatarray) {
      FloatBuffer fb = ByteBuffer.allocateDirect(floatarray.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
      fb.put(floatarray).flip();
      return fb;
   }

   protected void renderLowry(EntityBullet entity, double x, double y, double z, float rotatedYaw, float rotatedPitch) {
      GL11.glPushMatrix();
      GL11.glEnable(3008);
      GL11.glTranslatef((float)x, (float)y, (float)z);
      GL11.glRotatef(rotatedYaw - 90.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(rotatedPitch, 1.0F, 0.0F, 0.0F);
      GL11.glScalef(0.1F, 0.1F, 0.1F);
      this.field_76990_c.renderEngine.bindTexture(this.MainTexture);
      this.model.renderAll();
      GL11.glPopMatrix();
   }

   protected ResourceLocation func_110775_a(Entity p_110775_1_) {
      return null;
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

   public void func_76986_a(Entity entity, double x, double y, double z, float rotationYaw, float rotationPitch) {
      this.renderLowry((EntityBullet)entity, x, y, z, rotationYaw, rotationPitch);
   }
}
