package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCabinet extends ModelBase {

   ModelRenderer door;
   ModelRenderer main;
   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape4;


   public ModelCabinet() {
      this.field_78090_t = 128;
      this.field_78089_u = 128;
      this.door = new ModelRenderer(this, 65, 0);
      this.door.addBox(0.0F, -8.0F, -1.0F, 16, 16, 1);
      this.door.setRotationPoint(-8.0F, 16.0F, -7.1F);
      this.door.setTextureSize(128, 128);
      this.door.mirror = true;
      this.setRotation(this.door, 0.0F, 0.0F, 0.0F);
      this.main = new ModelRenderer(this, 0, 0);
      this.main.addBox(0.0F, 0.0F, 0.0F, 16, 1, 15);
      this.main.setRotationPoint(-8.0F, 8.0F, -7.0F);
      this.main.setTextureSize(128, 128);
      this.main.mirror = true;
      this.setRotation(this.main, 0.0F, 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 0, 22);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 14, 15);
      this.Shape1.setRotationPoint(-8.0F, 9.0F, -7.0F);
      this.Shape1.setTextureSize(128, 128);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 19);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 1, 14, 15);
      this.Shape2.setRotationPoint(7.0F, 9.0F, -7.0F);
      this.Shape2.setTextureSize(128, 128);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 16, 1, 16);
      this.Shape3.setRotationPoint(-8.0F, 23.0F, -8.0F);
      this.Shape3.setTextureSize(128, 128);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 14, 14, 1);
      this.Shape4.setRotationPoint(-7.0F, 9.0F, 6.8F);
      this.Shape4.setTextureSize(128, 128);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.door.render(f5);
      this.main.render(f5);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
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
