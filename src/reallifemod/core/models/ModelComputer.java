package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelComputer extends ModelBase {

   ModelRenderer keyboard;
   ModelRenderer mouse;
   ModelRenderer screen;
   ModelRenderer screen2;
   ModelRenderer screen3;


   public ModelComputer() {
      this.field_78090_t = 512;
      this.field_78089_u = 256;
      this.keyboard = new ModelRenderer(this, 0, 0);
      this.keyboard.addBox(0.0F, 0.0F, 0.0F, 12, 1, 5);
      this.keyboard.setRotationPoint(-7.0F, 23.0F, -6.0F);
      this.keyboard.setTextureSize(512, 256);
      this.keyboard.mirror = false;
      this.setRotation(this.keyboard, 0.0F, 0.0F, 0.0F);
      this.mouse = new ModelRenderer(this, 0, 13);
      this.mouse.addBox(0.0F, 0.0F, 0.0F, 2, 1, 3);
      this.mouse.setRotationPoint(6.0F, 23.0F, -5.0F);
      this.mouse.setTextureSize(512, 256);
      this.mouse.mirror = true;
      this.setRotation(this.mouse, 0.0F, 0.0F, 0.0F);
      this.screen = new ModelRenderer(this, 0, 18);
      this.screen.addBox(0.0F, 0.0F, 0.0F, 14, 10, 1);
      this.screen.setRotationPoint(-7.5F, 10.0F, 3.5F);
      this.screen.setTextureSize(512, 256);
      this.screen.mirror = true;
      this.setRotation(this.screen, 0.0F, 0.0F, 0.0F);
      this.screen2 = new ModelRenderer(this, 0, 7);
      this.screen2.addBox(0.0F, 0.0F, 0.0F, 2, 5, 1);
      this.screen2.setRotationPoint(-1.5F, 18.0F, 4.5F);
      this.screen2.setTextureSize(512, 256);
      this.screen2.mirror = true;
      this.setRotation(this.screen2, 0.0F, 0.0F, 0.0F);
      this.screen3 = new ModelRenderer(this, 9, 7);
      this.screen3.addBox(0.0F, 0.0F, 0.0F, 5, 1, 3);
      this.screen3.setRotationPoint(-3.0F, 23.0F, 3.0F);
      this.screen3.setTextureSize(512, 256);
      this.screen3.mirror = true;
      this.setRotation(this.screen3, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.keyboard.render(f5);
      this.mouse.render(f5);
      this.screen.render(f5);
      this.screen2.render(f5);
      this.screen3.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }
}
