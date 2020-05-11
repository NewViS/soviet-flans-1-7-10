package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChristmasPyramid extends ModelBase {

   public ModelRenderer rotor1;
   public ModelRenderer rotor2;
   ModelRenderer pyramid1;
   ModelRenderer pyramid2;
   ModelRenderer pyramid3;
   ModelRenderer pyramid4;
   ModelRenderer middle;


   public ModelChristmasPyramid() {
      this.field_78090_t = 64;
      this.field_78089_u = 32;
      this.rotor1 = new ModelRenderer(this, 0, 0);
      this.rotor1.addBox(-5.0F, 0.0F, -0.5F, 10, 0, 1);
      this.rotor1.setRotationPoint(0.0F, 14.0F, 0.0F);
      this.rotor1.setTextureSize(64, 32);
      this.rotor1.mirror = true;
      this.setRotation(this.rotor1, 0.0F, 0.0F, 0.0F);
      this.rotor2 = new ModelRenderer(this, 0, 0);
      this.rotor2.addBox(-0.5F, 0.0F, -5.0F, 1, 0, 10);
      this.rotor2.setRotationPoint(0.0F, 14.0F, 0.0F);
      this.rotor2.setTextureSize(64, 32);
      this.rotor2.mirror = true;
      this.setRotation(this.rotor2, 0.0F, 0.0F, 0.0F);
      this.pyramid1 = new ModelRenderer(this, 0, 10);
      this.pyramid1.addBox(-5.0F, 0.0F, -5.0F, 10, 1, 10);
      this.pyramid1.setRotationPoint(0.0F, 23.0F, 0.0F);
      this.pyramid1.setTextureSize(64, 32);
      this.pyramid1.mirror = true;
      this.setRotation(this.pyramid1, 0.0F, 0.0F, 0.0F);
      this.pyramid2 = new ModelRenderer(this, 0, 21);
      this.pyramid2.addBox(-4.0F, 0.0F, -4.0F, 8, 1, 8);
      this.pyramid2.setRotationPoint(0.0F, 20.0F, 0.0F);
      this.pyramid2.setTextureSize(64, 32);
      this.pyramid2.mirror = true;
      this.setRotation(this.pyramid2, 0.0F, 0.0F, 0.0F);
      this.pyramid3 = new ModelRenderer(this, 22, 0);
      this.pyramid3.addBox(-3.0F, 0.0F, -3.0F, 6, 1, 6);
      this.pyramid3.setRotationPoint(0.0F, 17.0F, 0.0F);
      this.pyramid3.setTextureSize(64, 32);
      this.pyramid3.mirror = true;
      this.setRotation(this.pyramid3, 0.0F, 0.0F, 0.0F);
      this.pyramid4 = new ModelRenderer(this, 47, 0);
      this.pyramid4.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4);
      this.pyramid4.setRotationPoint(0.0F, 14.0F, 0.0F);
      this.pyramid4.setTextureSize(64, 32);
      this.pyramid4.mirror = true;
      this.setRotation(this.pyramid4, 0.0F, 0.0F, 0.0F);
      this.middle = new ModelRenderer(this, 40, 10);
      this.middle.addBox(0.0F, 0.0F, 0.0F, 1, 9, 1);
      this.middle.setRotationPoint(-0.5F, 15.0F, -0.5F);
      this.middle.setTextureSize(64, 32);
      this.middle.mirror = true;
      this.setRotation(this.middle, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.rotor1.render(f5);
      this.rotor2.render(f5);
      this.pyramid1.render(f5);
      this.pyramid2.render(f5);
      this.pyramid3.render(f5);
      this.pyramid4.render(f5);
      this.middle.render(f5);
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
