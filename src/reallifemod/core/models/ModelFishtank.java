package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFishtank extends ModelBase {

   ModelRenderer tank;


   public ModelFishtank() {
      this.field_78090_t = 128;
      this.field_78089_u = 32;
      this.tank = new ModelRenderer(this, 0, 0);
      this.tank.addBox(-8.0F, 0.0F, -8.0F, 32, 16, 16);
      this.tank.setRotationPoint(0.0F, 8.0F, 0.0F);
      this.tank.setTextureSize(64, 32);
      this.tank.mirror = true;
      this.setRotation(this.tank, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.tank.render(f5);
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
