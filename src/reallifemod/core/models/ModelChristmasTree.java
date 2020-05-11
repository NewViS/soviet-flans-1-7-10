package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelChristmasTree extends ModelBase {

   ModelRenderer stump;
   ModelRenderer part1;
   ModelRenderer part2;
   ModelRenderer part3;
   ModelRenderer part4;
   ModelRenderer part5;


   public ModelChristmasTree() {
      this.field_78090_t = 256;
      this.field_78089_u = 64;
      this.stump = new ModelRenderer(this, 96, 0);
      this.stump.addBox(-4.0F, 0.0F, -4.0F, 8, 10, 8);
      this.stump.setRotationPoint(0.0F, 14.0F, 0.0F);
      this.stump.setTextureSize(64, 32);
      this.stump.mirror = true;
      this.setRotation(this.stump, 0.0F, 0.0F, 0.0F);
      this.part1 = new ModelRenderer(this, 0, 0);
      this.part1.addBox(-12.0F, 0.0F, -12.0F, 24, 16, 24);
      this.part1.setRotationPoint(0.0F, -2.0F, 0.0F);
      this.part1.setTextureSize(64, 32);
      this.part1.mirror = true;
      this.setRotation(this.part1, 0.0F, 0.0F, 0.0F);
      this.part2 = new ModelRenderer(this, 129, 0);
      this.part2.addBox(-4.0F, 0.0F, -12.0F, 8, 16, 24);
      this.part2.setRotationPoint(0.0F, -18.0F, 0.0F);
      this.part2.setTextureSize(64, 32);
      this.part2.mirror = true;
      this.setRotation(this.part2, 0.0F, 0.0F, 0.0F);
      this.part3 = new ModelRenderer(this, 0, 40);
      this.part3.addBox(-4.0F, 0.0F, -4.0F, 8, 16, 8);
      this.part3.setRotationPoint(8.0F, -18.0F, 0.0F);
      this.part3.setTextureSize(64, 32);
      this.part3.mirror = true;
      this.setRotation(this.part3, 0.0F, 0.0F, 0.0F);
      this.part4 = new ModelRenderer(this, 193, 0);
      this.part4.addBox(-4.0F, 0.0F, -4.0F, 8, 16, 8);
      this.part4.setRotationPoint(-8.0F, -18.0F, 0.0F);
      this.part4.setTextureSize(64, 32);
      this.part4.mirror = true;
      this.setRotation(this.part4, 0.0F, 0.0F, 0.0F);
      this.part5 = new ModelRenderer(this, 96, 20);
      this.part5.addBox(-4.0F, 0.0F, -4.0F, 8, 8, 8);
      this.part5.setRotationPoint(0.0F, -26.0F, 0.0F);
      this.part5.setTextureSize(64, 32);
      this.part5.mirror = true;
      this.setRotation(this.part5, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.stump.render(f5);
      Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft:textures/blocks/leaves_spruce.png"));
      this.part1.render(f5);
      this.part2.render(f5);
      this.part3.render(f5);
      this.part4.render(f5);
      this.part5.render(f5);
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
