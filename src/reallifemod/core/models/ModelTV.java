package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTV extends ModelBase {

   ModelRenderer screen;
   ModelRenderer stand1;
   ModelRenderer stand2;


   public ModelTV() {
      this.field_78090_t = 64;
      this.field_78089_u = 32;
      this.screen = new ModelRenderer(this, 0, 0);
      this.screen.addBox(0.0F, 0.0F, 0.0F, 16, 10, 1);
      this.screen.setRotationPoint(-8.0F, 10.0F, -1.0F);
      this.screen.setTextureSize(64, 32);
      this.screen.mirror = true;
      this.setRotation(this.screen, 0.0F, 0.0F, 0.0F);
      this.stand1 = new ModelRenderer(this, 0, 15);
      this.stand1.addBox(0.0F, 0.0F, 0.0F, 5, 1, 3);
      this.stand1.setRotationPoint(-3.0F, 23.0F, -2.0F);
      this.stand1.setTextureSize(64, 32);
      this.stand1.mirror = true;
      this.setRotation(this.stand1, 0.0F, 0.0F, 0.0F);
      this.stand2 = new ModelRenderer(this, 0, 11);
      this.stand2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.stand2.setRotationPoint(-1.0F, 20.0F, -1.0F);
      this.stand2.setTextureSize(64, 32);
      this.stand2.mirror = true;
      this.setRotation(this.stand2, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.screen.render(f5);
      this.stand1.render(f5);
      this.stand2.render(f5);
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
