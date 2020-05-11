package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEspresso extends ModelBase {

   ModelRenderer Bottom;
   ModelRenderer Leg;
   ModelRenderer Leg1;
   ModelRenderer Leg3;
   ModelRenderer Leg4;
   ModelRenderer Back;
   ModelRenderer Top;
   ModelRenderer Cup;
   ModelRenderer Cup2;
   ModelRenderer Handle;
   ModelRenderer Hose;
   ModelRenderer Hose2;
   ModelRenderer Hose3;


   public ModelEspresso() {
      this.field_78090_t = 64;
      this.field_78089_u = 128;
      this.Bottom = new ModelRenderer(this, 0, 75);
      this.Bottom.addBox(0.0F, 0.0F, 0.0F, 16, 2, 16);
      this.Bottom.setRotationPoint(-8.0F, 21.0F, -8.0F);
      this.Bottom.setTextureSize(64, 128);
      this.Bottom.mirror = true;
      this.setRotation(this.Bottom, 0.0F, 0.0F, 0.0F);
      this.Leg = new ModelRenderer(this, 0, 124);
      this.Leg.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.Leg.setRotationPoint(-8.0F, 23.0F, -8.0F);
      this.Leg.setTextureSize(64, 128);
      this.Leg.mirror = true;
      this.setRotation(this.Leg, 0.0F, 0.0F, 0.0F);
      this.Leg1 = new ModelRenderer(this, 0, 124);
      this.Leg1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.Leg1.setRotationPoint(6.0F, 23.0F, -8.0F);
      this.Leg1.setTextureSize(64, 128);
      this.Leg1.mirror = true;
      this.setRotation(this.Leg1, 0.0F, 0.0F, 0.0F);
      this.Leg3 = new ModelRenderer(this, 0, 124);
      this.Leg3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.Leg3.setRotationPoint(6.0F, 23.0F, 6.0F);
      this.Leg3.setTextureSize(64, 128);
      this.Leg3.mirror = true;
      this.setRotation(this.Leg3, 0.0F, 0.0F, 0.0F);
      this.Leg4 = new ModelRenderer(this, 0, 124);
      this.Leg4.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.Leg4.setRotationPoint(-8.0F, 23.0F, 6.0F);
      this.Leg4.setTextureSize(64, 128);
      this.Leg4.mirror = true;
      this.setRotation(this.Leg4, 0.0F, 0.0F, 0.0F);
      this.Back = new ModelRenderer(this, 0, 98);
      this.Back.addBox(0.0F, 0.0F, 0.0F, 16, 16, 8);
      this.Back.setRotationPoint(-8.0F, 5.0F, 0.0F);
      this.Back.setTextureSize(64, 128);
      this.Back.mirror = true;
      this.setRotation(this.Back, 0.0F, 0.0F, 0.0F);
      this.Top = new ModelRenderer(this, 0, 52);
      this.Top.addBox(0.0F, 0.0F, 0.0F, 14, 4, 16);
      this.Top.setRotationPoint(-7.0F, 4.0F, -8.0F);
      this.Top.setTextureSize(64, 128);
      this.Top.mirror = true;
      this.setRotation(this.Top, 0.0F, 0.0F, 0.0F);
      this.Cup = new ModelRenderer(this, 0, 0);
      this.Cup.addBox(0.0F, 0.0F, 0.0F, 5, 3, 5);
      this.Cup.setRotationPoint(-1.0F, 8.0F, -6.0F);
      this.Cup.setTextureSize(64, 128);
      this.Cup.mirror = true;
      this.setRotation(this.Cup, 0.0F, 0.0F, 0.0F);
      this.Cup2 = new ModelRenderer(this, 0, 0);
      this.Cup2.addBox(0.0F, 0.0F, 0.0F, 3, 2, 3);
      this.Cup2.setRotationPoint(0.0F, 11.0F, -5.0F);
      this.Cup2.setTextureSize(64, 128);
      this.Cup2.mirror = true;
      this.setRotation(this.Cup2, 0.0F, 0.0F, 0.0F);
      this.Handle = new ModelRenderer(this, 0, 15);
      this.Handle.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Handle.setRotationPoint(1.0F, 9.0F, -11.0F);
      this.Handle.setTextureSize(64, 128);
      this.Handle.mirror = true;
      this.setRotation(this.Handle, 0.0F, 0.0F, 0.0F);
      this.Hose = new ModelRenderer(this, 0, 26);
      this.Hose.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Hose.setRotationPoint(-5.0F, 8.0F, -4.0F);
      this.Hose.setTextureSize(64, 128);
      this.Hose.mirror = true;
      this.setRotation(this.Hose, 0.0F, 0.0F, 0.0F);
      this.Hose2 = new ModelRenderer(this, 0, 26);
      this.Hose2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Hose2.setRotationPoint(-6.0F, 11.0F, -4.0F);
      this.Hose2.setTextureSize(64, 128);
      this.Hose2.mirror = true;
      this.setRotation(this.Hose2, 0.0F, 0.0F, 0.0F);
      this.Hose3 = new ModelRenderer(this, 0, 30);
      this.Hose3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Hose3.setRotationPoint(-6.0F, 12.0F, -4.0F);
      this.Hose3.setTextureSize(64, 128);
      this.Hose3.mirror = true;
      this.setRotation(this.Hose3, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      this.Bottom.render(f5);
      this.Leg.render(f5);
      this.Leg1.render(f5);
      this.Leg3.render(f5);
      this.Leg4.render(f5);
      this.Back.render(f5);
      this.Top.render(f5);
      this.Cup.render(f5);
      this.Cup2.render(f5);
      this.Handle.render(f5);
      this.Hose.render(f5);
      this.Hose2.render(f5);
      this.Hose3.render(f5);
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
