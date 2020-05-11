package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityTruck;
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
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class RenderTGX extends Render {

   protected IModelCustom model;
   protected IModelCustom backwheels;
   protected IModelCustom LowryWheelRight;
   protected IModelCustom LowryWheelLeft;
   protected IModelCustom Cabin;
   protected IModelCustom Steeringwheel;
   protected IModelCustom tacho;
   ResourceLocation MainTexture = new ResourceLocation("reallifemod:textures/entity/VehicleTextures/FullTexture.png");
   private IModelCustom backlight;
   public static final float DEFAULT_LIGHT_Z = 0.075F;
   public static final Vector4f LIGHT_COLOR = new Vector4f(1.0F, 0.8F, 0.6F, 1.0F);
   public static final Vector4f AMBIENT_COLOR = new Vector4f(0.6F, 0.6F, 1.0F, 0.2F);
   public static final Vector3f FALLOFF = new Vector3f(0.4F, 3.0F, 20.0F);


   public static FloatBuffer allocFloats(int howmany) {
      return ByteBuffer.allocateDirect(howmany * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
   }

   public static FloatBuffer allocFloats(float[] floatarray) {
      FloatBuffer fb = ByteBuffer.allocateDirect(floatarray.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
      fb.put(floatarray).flip();
      return fb;
   }

   public RenderTGX() {
      try {
         this.Steeringwheel = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW_Steeringwheel.obj"));
         this.model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW.obj"));
         this.backwheels = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW_Backwheelsection.obj"));
         this.Cabin = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW_Cabin.obj"));
         this.LowryWheelLeft = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW_LeftwheelF.obj"));
         this.LowryWheelRight = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW_RightwheelF.obj"));
         this.tacho = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW_Tacho.obj"));
         this.backlight = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/BackLights.obj"));
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void renderLowry(EntityTruck entity, double x, double y, double z, float rotatedYaw, float rotatedPitch) {
      GL11.glPushMatrix();
      GL11.glEnable(2896);
      GL11.glEnable(2929);
      double var10000 = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * x;
      var10000 = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * y;
      var10000 = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * z;
      GL11.glTranslated(x, y + 0.30000001192092896D, z);
      GL11.glRotatef(rotatedYaw, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(entity.field_70125_A - entity.field_70127_C, 1.0F, 0.0F, 0.0F);
      GL11.glPushMatrix();
      GL11.glScaled(0.75D, 0.75D, 0.75D);
      if(entity.isBraking) {
         this.field_76990_c.renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/entity/VehicleTextures/BacklightLit.png"));
      } else if(!entity.isBraking) {
         this.field_76990_c.renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/entity/VehicleTextures/BacklightOff.png"));
      }

      this.backlight.renderAll();
      GL11.glPopMatrix();
      GL11.glScaled(0.75D, 0.75D, 0.75D);
      this.field_76990_c.renderEngine.bindTexture(this.MainTexture);
      this.model.renderAll();
      this.Cabin.renderAll();
      GL11.glPushMatrix();
      GL11.glTranslatef(0.9F, 1.9F, 6.35F);
      GL11.glRotated(entity.velocity * 100.0D, 0.0D, 0.0D, 1.0D);
      this.tacho.renderAll();
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslatef(0.8F, 2.0F, 6.0F);
      GL11.glRotated((double)entity.DeltaSteer, 0.0D, 1.0D, 0.0D);
      this.Steeringwheel.renderAll();
      GL11.glPopMatrix();
      this.field_76990_c.renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/entity/VehicleTextures/Hinterachse.png"));
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 0.3F, 0.0F);
      GL11.glRotated(entity.velocity, 1.0D, 0.0D, 0.0D);
      this.backwheels.renderAll();
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslatef(1.0F, 0.4F, 5.0F);
      GL11.glPushMatrix();
      GL11.glRotated((double)entity.DeltaSteer, 0.0D, 1.0D, 0.0D);
      GL11.glRotated(entity.velocity, 1.0D, 0.0D, 0.0D);
      this.LowryWheelLeft.renderAll();
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslatef(-1.0F, 0.4F, 5.0F);
      GL11.glPushMatrix();
      GL11.glRotated((double)entity.DeltaSteer, 0.0D, 1.0D, 0.0D);
      GL11.glRotated(entity.velocity, 1.0D, 0.0D, 0.0D);
      this.LowryWheelRight.renderAll();
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

   protected ResourceLocation func_110775_a(Entity p_110775_1_) {
      return this.MainTexture;
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

   public FloatBuffer floatBuffer(float[] data) {
      FloatBuffer fb = BufferUtils.createFloatBuffer(data.length);
      fb.put(data);
      fb.flip();
      return fb;
   }

   public void func_76986_a(Entity entity, double x, double y, double z, float rotationYaw, float rotationPitch) {
      this.renderLowry((EntityTruck)entity, x, y, z, rotationYaw, rotationPitch);
   }

}
