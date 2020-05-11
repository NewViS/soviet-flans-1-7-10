package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBed extends ModelBase {

   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer Shape1;
   ModelRenderer shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer mattress;
   ModelRenderer Shape10;


   public ModelBed() {
      this.field_78090_t = 512;
      this.field_78089_u = 512;
      this.Shape2 = new ModelRenderer(this, 0, 128);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 11, 2);
      this.Shape2.setRotationPoint(-8.0F, 13.0F, -9.0F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 128, 128);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 12, 3, 1);
      this.Shape3.setRotationPoint(-6.0F, 18.0F, -8.0F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape1.mirror = true;
      this.Shape1 = new ModelRenderer(this, 0, 128);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 2, 11, 2);
      this.Shape1.setRotationPoint(6.0F, 13.0F, -9.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape1.mirror = false;
      this.shape4 = new ModelRenderer(this, 0, 128);
      this.shape4.addBox(0.0F, 0.0F, 0.0F, 2, 11, 2);
      this.shape4.setRotationPoint(-8.0F, 13.0F, 22.0F);
      this.shape4.setTextureSize(64, 32);
      this.shape4.mirror = true;
      this.setRotation(this.shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5.mirror = true;
      this.Shape5 = new ModelRenderer(this, 0, 128);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 2, 11, 2);
      this.Shape5.setRotationPoint(6.0F, 13.0F, 22.0F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape5.mirror = false;
      this.Shape6 = new ModelRenderer(this, 128, 128);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 12, 3, 1);
      this.Shape6.setRotationPoint(-6.0F, 18.0F, 22.0F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7.mirror = true;
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 3, 29);
      this.Shape7.setRotationPoint(7.0F, 18.0F, -7.0F);
      this.Shape7.setTextureSize(512, 512);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape7.mirror = false;
      this.Shape8 = new ModelRenderer(this, 0, 0);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 3, 29);
      this.Shape8.setRotationPoint(-8.0F, 18.0F, -7.0F);
      this.Shape8.setTextureSize(512, 512);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.mattress = new ModelRenderer(this, 128, 0);
      this.mattress.addBox(0.0F, 0.0F, 0.0F, 14, 3, 29);
      this.mattress.setRotationPoint(-7.0F, 17.0F, -7.0F);
      this.mattress.setTextureSize(512, 512);
      this.mattress.mirror = true;
      this.setRotation(this.mattress, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 128, 128);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 12, 3, 1);
      this.Shape10.setRotationPoint(-6.0F, 14.0F, -8.0F);
      this.Shape10.setTextureSize(512, 512);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.Shape1.render(f5);
      this.shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
      this.mattress.render(f5);
      this.Shape10.render(f5);
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
