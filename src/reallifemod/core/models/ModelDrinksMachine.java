package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDrinksMachine extends ModelBase {

   ModelRenderer machine;


   public ModelDrinksMachine() {
      this.field_78090_t = 256;
      this.field_78089_u = 128;
      this.machine = new ModelRenderer(this, 0, 0);
      this.machine.addBox(0.0F, 0.0F, 0.0F, 16, 32, 16);
      this.machine.setRotationPoint(-8.0F, -8.0F, -8.0F);
      this.machine.setTextureSize(64, 32);
      this.machine.mirror = true;
      this.setRotation(this.machine, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.machine.render(f5);
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
