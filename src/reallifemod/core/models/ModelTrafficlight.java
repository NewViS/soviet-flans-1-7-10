package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTrafficlight extends ModelBase {

   ModelRenderer light;
   ModelRenderer post;


   public ModelTrafficlight() {
      this.field_78090_t = 64;
      this.field_78089_u = 128;
      this.light = new ModelRenderer(this, 0, 0);
      this.light.addBox(-2.0F, 0.0F, -3.0F, 10, 21, 6);
      this.light.setRotationPoint(-3.0F, -40.0F, 0.0F);
      this.light.setTextureSize(64, 32);
      this.light.mirror = true;
      this.setRotation(this.light, 0.0F, 0.0F, 0.0F);
      this.post = new ModelRenderer(this, 0, 27);
      this.post.addBox(-2.0F, 0.0F, -2.0F, 4, 48, 4);
      this.post.setRotationPoint(0.0F, -24.0F, 0.0F);
      this.post.setTextureSize(64, 32);
      this.post.mirror = true;
      this.setRotation(this.post, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.light.render(f5);
      this.post.render(f5);
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
