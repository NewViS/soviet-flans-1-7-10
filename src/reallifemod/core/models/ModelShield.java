package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShield extends ModelBase {

   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape22;
   ModelRenderer Shape3;
   ModelRenderer Shape32;
   ModelRenderer Shape4;
   ModelRenderer Shape42;
   ModelRenderer Shape5;
   ModelRenderer Shape52;


   public ModelShield() {
      this.field_78090_t = 1024;
      this.field_78089_u = 1024;
      this.Shape1 = new ModelRenderer(this, 0, 150);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 40, 30, 0);
      this.Shape1.setRotationPoint(-20.0F, -13.0F, 0.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 43);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 34, 2);
      this.Shape2.setRotationPoint(20.0F, -15.0F, -1.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape22 = new ModelRenderer(this, 300, 43);
      this.Shape22.addBox(0.0F, 0.0F, 0.0F, 2, 34, 2);
      this.Shape22.setRotationPoint(-22.0F, -15.0F, -1.0F);
      this.Shape22.setTextureSize(64, 32);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 50, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 40, 2, 2);
      this.Shape3.setRotationPoint(-20.0F, -15.0F, -1.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape32 = new ModelRenderer(this, 350, 0);
      this.Shape32.addBox(0.0F, 0.0F, 0.0F, 40, 2, 2);
      this.Shape32.setRotationPoint(-20.0F, 17.0F, -1.0F);
      this.Shape32.setTextureSize(64, 32);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 500, 0);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 2, 9, 2);
      this.Shape4.setRotationPoint(-22.0F, 15.0F, 1.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape42 = new ModelRenderer(this, 500, 0);
      this.Shape42.addBox(0.0F, 0.0F, 0.0F, 2, 9, 2);
      this.Shape42.setRotationPoint(20.0F, 15.0F, 1.0F);
      this.Shape42.setTextureSize(64, 32);
      this.Shape42.mirror = true;
      this.setRotation(this.Shape42, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 600, 0);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 50, 1);
      this.Shape5.setRotationPoint(20.0F, -14.0F, 0.1F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.9337511F);
      this.Shape52 = new ModelRenderer(this, 600, 0);
      this.Shape52.addBox(0.0F, 0.0F, 0.0F, 1, 50, 1);
      this.Shape52.setRotationPoint(-20.0F, -13.0F, 0.1F);
      this.Shape52.setTextureSize(64, 32);
      this.Shape52.mirror = true;
      this.setRotation(this.Shape52, 0.0F, 0.0F, -0.9337511F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.Shape2.render(f5);
      this.Shape22.render(f5);
      this.Shape3.render(f5);
      this.Shape32.render(f5);
      this.Shape4.render(f5);
      this.Shape42.render(f5);
      this.Shape5.render(f5);
      this.Shape52.render(f5);
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
