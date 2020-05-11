package de.ItsAMysterious.mods.reallifemod.core.models;

import de.ItsAMysterious.mods.reallifemod.core.entities.cars.EntityRidingmower;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelRidingmower extends ModelBase {

   ModelRenderer Shape1;
   ModelRenderer Shape2;
   ModelRenderer Shape3;
   ModelRenderer LeftWheel;
   ModelRenderer RightWheel;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shape9;
   ModelRenderer Shape10;
   ModelRenderer Shape11;
   ModelRenderer Shape112;
   ModelRenderer Shape102;
   ModelRenderer Lefttbackwheel;
   ModelRenderer Rightbackwheel;
   ModelRenderer Shape12;
   ModelRenderer Shape122;
   ModelRenderer Basket1;
   ModelRenderer Basket2;
   public boolean hasBasket = false;


   public ModelRidingmower() {
      this.field_78090_t = 256;
      this.field_78089_u = 256;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 7, 8, 14);
      this.Shape1.setRotationPoint(-3.0F, 12.0F, -32.0F);
      this.Shape1.setTextureSize(256, 256);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0872665F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 38);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 6, 4, 3);
      this.Shape2.setRotationPoint(-2.5F, 11.0F, -18.0F);
      this.Shape2.setTextureSize(256, 256);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, -0.5934119F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 29);
      this.Shape3.addBox(-2.5F, 0.0F, -2.5F, 5, 1, 5);
      this.Shape3.setRotationPoint(0.5F, 10.0F, -15.5F);
      this.Shape3.setTextureSize(256, 256);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, -0.8179294F, 0.0F, 0.0F);
      this.LeftWheel = new ModelRenderer(this, 0, 79);
      this.LeftWheel.addBox(0.0F, -2.5F, -2.5F, 2, 5, 5);
      this.LeftWheel.setRotationPoint(5.0F, 21.5F, -28.0F);
      this.LeftWheel.setTextureSize(256, 256);
      this.LeftWheel.mirror = true;
      this.setRotation(this.LeftWheel, 0.0F, 0.0F, 0.0F);
      this.RightWheel = new ModelRenderer(this, 0, 79);
      this.RightWheel.addBox(-2.0F, -2.5F, -2.5F, 2, 5, 5);
      this.RightWheel.setRotationPoint(-4.0F, 21.5F, -28.0F);
      this.RightWheel.setTextureSize(256, 256);
      this.RightWheel.mirror = true;
      this.setRotation(this.RightWheel, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 96);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 11, 1, 11);
      this.Shape4.setRotationPoint(-5.0F, 19.0F, -19.0F);
      this.Shape4.setTextureSize(256, 256);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 48, 0);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 14, 2, 14);
      this.Shape5.setRotationPoint(-6.5F, 21.0F, -20.5F);
      this.Shape5.setTextureSize(256, 256);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 48, 20);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10);
      this.Shape6.setRotationPoint(-4.5F, 20.0F, -18.5F);
      this.Shape6.setTextureSize(256, 256);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 50);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 8, 3, 5);
      this.Shape7.setRotationPoint(-3.5F, 18.0F, -21.0F);
      this.Shape7.setTextureSize(256, 256);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 48, 34);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 7, 6, 7);
      this.Shape8.setRotationPoint(-3.0F, 14.0F, -8.0F);
      this.Shape8.setTextureSize(256, 256);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.Shape9 = new ModelRenderer(this, 48, 51);
      this.Shape9.addBox(0.0F, 0.0F, 0.0F, 7, 6, 2);
      this.Shape9.setRotationPoint(-3.0F, 15.0F, -10.0F);
      this.Shape9.setTextureSize(256, 256);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 49, 77);
      this.Shape10.addBox(0.0F, 0.0F, 0.0F, 6, 1, 10);
      this.Shape10.setRotationPoint(4.0F, 13.0F, -7.0F);
      this.Shape10.setTextureSize(256, 256);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 49, 64);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 6, 8, 1);
      this.Shape11.setRotationPoint(4.0F, 13.0F, -7.0F);
      this.Shape11.setTextureSize(256, 256);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, -0.3346075F, 0.0F, 0.0F);
      this.Shape112 = new ModelRenderer(this, 49, 64);
      this.Shape112.addBox(0.0F, 0.0F, 0.0F, 6, 8, 1);
      this.Shape112.setRotationPoint(-9.0F, 13.0F, -7.0F);
      this.Shape112.mirror = true;
      this.setRotation(this.Shape112, -0.3346075F, 0.0F, 0.0F);
      this.Shape102 = new ModelRenderer(this, 49, 77);
      this.Shape102.addBox(0.0F, 0.0F, 0.0F, 6, 1, 10);
      this.Shape102.setRotationPoint(-9.0F, 13.0F, -7.0F);
      this.Shape102.setTextureSize(256, 256);
      this.Shape102.mirror = true;
      this.setRotation(this.Shape102, 0.0F, 0.0F, 0.0F);
      this.Lefttbackwheel = new ModelRenderer(this, 109, 0);
      this.Lefttbackwheel.addBox(0.03333334F, -4.5F, -4.5F, 5, 9, 9);
      this.Lefttbackwheel.setRotationPoint(5.0F, 19.5F, -1.0F);
      this.Lefttbackwheel.setTextureSize(256, 256);
      this.Lefttbackwheel.mirror = true;
      this.setRotation(this.Lefttbackwheel, -3.141593F, 0.0F, 0.0F);
      this.Rightbackwheel = new ModelRenderer(this, 109, 0);
      this.Rightbackwheel.addBox(-5.0F, -4.5F, -4.5F, 5, 9, 9);
      this.Rightbackwheel.setRotationPoint(-4.0F, 19.5F, -1.0F);
      this.Rightbackwheel.setTextureSize(256, 256);
      this.Rightbackwheel.mirror = true;
      this.setRotation(this.Rightbackwheel, -3.141593F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 108, 26);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 8, 2, 8);
      this.Shape12.setRotationPoint(-3.5F, 12.0F, -6.0F);
      this.Shape12.setTextureSize(256, 256);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape122 = new ModelRenderer(this, 108, 26);
      this.Shape122.addBox(0.0F, 0.0F, 0.0F, 8, 2, 8);
      this.Shape122.setRotationPoint(-3.5F, 12.0F, 0.0F);
      this.Shape122.setTextureSize(256, 256);
      this.Shape122.mirror = true;
      this.setRotation(this.Shape122, 1.239184F, 0.0F, 0.0F);
      this.Basket1 = new ModelRenderer(this, 147, 0);
      this.Basket1.addBox(0.0F, 0.0F, 0.0F, 7, 6, 5);
      this.Basket1.setRotationPoint(-3.0F, 15.0F, 0.0F);
      this.Basket1.setTextureSize(256, 256);
      this.Basket1.mirror = true;
      this.setRotation(this.Basket1, 0.4363323F, 0.0F, 0.0F);
      this.Basket2 = new ModelRenderer(this, 147, 19);
      this.Basket2.addBox(0.0F, 0.0F, 0.0F, 16, 10, 13);
      this.Basket2.setRotationPoint(-8.0F, 13.0F, 4.0F);
      this.Basket2.setTextureSize(256, 256);
      this.Basket2.mirror = true;
      this.setRotation(this.Basket2, 0.1919862F, 0.0F, 0.0F);
   }

   public void render(EntityRidingmower entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape2.render(f5);
      this.Shape3.render(f5);
      this.LeftWheel.render(f5);
      this.RightWheel.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape7.render(f5);
      this.Shape8.render(f5);
      this.Shape9.render(f5);
      this.Shape10.render(f5);
      this.Shape11.render(f5);
      this.Shape11.render(f5);
      this.Shape10.render(f5);
      this.Lefttbackwheel.render(f5);
      this.Rightbackwheel.render(f5);
      this.Shape12.render(f5);
      this.Shape12.render(f5);
      if(this.hasBasket) {
         this.Basket1.render(f5);
         this.Basket2.render(f5);
      }

   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, EntityRidingmower entity) {
      super.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
      this.Lefttbackwheel.rotateAngleX = entity.wheelrotation;
      this.Rightbackwheel.rotateAngleX = entity.wheelrotation;
      this.LeftWheel.rotateAngleX = entity.wheelrotation;
      this.LeftWheel.rotateAngleY = -entity.deltaSteer / 60.0F;
      this.RightWheel.rotateAngleX = entity.wheelrotation;
      this.RightWheel.rotateAngleY = -entity.deltaSteer / 60.0F;
      this.hasBasket = entity.hasBasket;
   }
}
