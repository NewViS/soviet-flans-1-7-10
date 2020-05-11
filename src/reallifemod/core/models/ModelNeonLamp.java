package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNeonLamp extends ModelBase {

   ModelRenderer lamp;


   public ModelNeonLamp() {
      this.field_78090_t = 128;
      this.field_78089_u = 32;
      this.lamp = new ModelRenderer(this, 0, 0);
      this.lamp.addBox(-24.0F, 0.0F, -1.0F, 48, 1, 3);
      this.lamp.setRotationPoint(0.0F, 8.0F, 0.0F);
      this.lamp.setTextureSize(64, 32);
      this.lamp.mirror = true;
      this.setRotation(this.lamp, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.lamp.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }
}
