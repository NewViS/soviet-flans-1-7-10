package de.ItsAMysterious.mods.reallifemod.core.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCupboard extends ModelBase {

   public static ModelRenderer Boottom;
   public static ModelRenderer WallLeft;
   public static ModelRenderer WallRight;
   public static ModelRenderer Top;
   public static ModelRenderer MiddleWall;
   public static ModelRenderer drawer2Front;
   public static ModelRenderer drawer1Front;
   public static ModelRenderer LittleDoor;
   public static ModelRenderer DrawerButton1;
   public static ModelRenderer DrawerButton2;
   public static ModelRenderer LeftDoor;
   public static ModelRenderer RightDoor;
   public static ModelRenderer BackWall;
   public static ModelRenderer LittleDoorButton;
   public static ModelRenderer LeftDoorButton;
   public static ModelRenderer RightDoorButton;


   public ModelCupboard() {
      this.field_78090_t = 512;
      this.field_78089_u = 512;
      Boottom = new ModelRenderer(this, 0, 134);
      Boottom.addBox(0.0F, 0.0F, 0.0F, 48, 1, 16);
      Boottom.setRotationPoint(-24.0F, 23.0F, -8.0F);
      Boottom.setTextureSize(512, 512);
      Boottom.mirror = true;
      this.setRotation(Boottom, 0.0F, 0.0F, 0.0F);
      WallLeft = new ModelRenderer(this, 46, 70);
      WallLeft.addBox(0.0F, 0.0F, 0.0F, 1, 39, 16);
      WallLeft.setRotationPoint(-24.0F, -16.0F, -8.0F);
      WallLeft.setTextureSize(512, 512);
      WallLeft.mirror = true;
      this.setRotation(WallLeft, 0.0F, 0.0F, 0.0F);
      WallRight = new ModelRenderer(this, 87, 70);
      WallRight.addBox(0.0F, 0.0F, 0.0F, 1, 39, 16);
      WallRight.setRotationPoint(23.0F, -16.0F, -8.0F);
      WallRight.setTextureSize(512, 512);
      WallRight.mirror = true;
      this.setRotation(WallRight, 0.0F, 0.0F, 0.0F);
      Top = new ModelRenderer(this, 0, 46);
      Top.addBox(0.0F, 0.0F, 0.0F, 48, 1, 17);
      Top.setRotationPoint(-24.0F, -17.0F, -9.0F);
      Top.setTextureSize(512, 512);
      Top.mirror = true;
      this.setRotation(Top, 0.0F, 0.0F, 0.0F);
      MiddleWall = new ModelRenderer(this, 0, 70);
      MiddleWall.addBox(0.0F, 0.0F, 0.0F, 2, 39, 15);
      MiddleWall.setRotationPoint(-8.0F, -16.0F, -8.0F);
      MiddleWall.setTextureSize(512, 512);
      MiddleWall.mirror = true;
      this.setRotation(MiddleWall, 0.0F, 0.0F, 0.0F);
      drawer2Front = new ModelRenderer(this, 0, 32);
      drawer2Front.addBox(0.0F, 0.0F, 0.0F, 17, 6, 1);
      drawer2Front.setRotationPoint(-24.0F, 17.0F, -9.0F);
      drawer2Front.setTextureSize(512, 512);
      drawer2Front.mirror = true;
      this.setRotation(drawer2Front, 0.0F, 0.0F, 0.0F);
      drawer1Front = new ModelRenderer(this, 0, 0);
      drawer1Front.addBox(0.0F, 0.0F, 0.0F, 17, 6, 1);
      drawer1Front.setRotationPoint(-24.0F, 10.9F, -9.0F);
      drawer1Front.setTextureSize(512, 512);
      drawer1Front.mirror = true;
      this.setRotation(drawer1Front, 0.0F, 0.0F, 0.0F);
      LittleDoor = new ModelRenderer(this, 0, 0);
      LittleDoor.addBox(0.0F, 0.0F, 0.0F, 17, 27, 1);
      LittleDoor.setRotationPoint(-24.0F, -16.0F, -9.0F);
      LittleDoor.setTextureSize(512, 512);
      LittleDoor.mirror = true;
      this.setRotation(LittleDoor, 0.0F, 0.0F, 0.0F);
      DrawerButton1 = new ModelRenderer(this, 110, 0);
      DrawerButton1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      DrawerButton1.setRotationPoint(-17.0F, 13.0F, -10.0F);
      DrawerButton1.setTextureSize(512, 512);
      DrawerButton1.mirror = true;
      this.setRotation(DrawerButton1, 0.0F, 0.0F, 0.0F);
      DrawerButton2 = new ModelRenderer(this, 127, 0);
      DrawerButton2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      DrawerButton2.setRotationPoint(-17.0F, 19.0F, -10.0F);
      DrawerButton2.setTextureSize(512, 512);
      DrawerButton2.mirror = true;
      this.setRotation(DrawerButton2, 0.0F, 0.0F, 0.0F);
      LeftDoor = new ModelRenderer(this, 50, 200);
      LeftDoor.addBox(0.0F, 0.0F, 0.0F, 15, 39, 1);
      LeftDoor.setRotationPoint(-7.0F, -16.0F, -9.0F);
      LeftDoor.setTextureSize(512, 512);
      LeftDoor.mirror = true;
      this.setRotation(LeftDoor, 0.0F, 0.0F, 0.0F);
      RightDoor = new ModelRenderer(this, 0, 200);
      RightDoor.addBox(0.0F, 0.0F, 0.0F, 16, 39, 1);
      RightDoor.setRotationPoint(24.0F, -16.0F, -8.0F);
      RightDoor.setTextureSize(512, 512);
      RightDoor.mirror = true;
      this.setRotation(RightDoor, 0.0F, 3.141593F, 0.0F);
      BackWall = new ModelRenderer(this, 0, 154);
      BackWall.addBox(0.0F, 0.0F, 0.0F, 46, 39, 0);
      BackWall.setRotationPoint(-23.0F, -16.0F, 7.6F);
      BackWall.setTextureSize(512, 512);
      BackWall.mirror = true;
      this.setRotation(BackWall, 0.0F, 0.0F, 0.0F);
      LittleDoorButton = new ModelRenderer(this, 100, 0);
      LittleDoorButton.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      LittleDoorButton.setRotationPoint(-10.0F, 0.0F, -10.0F);
      LittleDoorButton.setTextureSize(512, 512);
      LittleDoorButton.mirror = true;
      this.setRotation(LittleDoorButton, 0.0F, 0.0F, 0.0F);
      LeftDoorButton = new ModelRenderer(this, 142, 0);
      LeftDoorButton.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      LeftDoorButton.setRotationPoint(6.0F, 5.0F, -10.0F);
      LeftDoorButton.setTextureSize(512, 512);
      LeftDoorButton.mirror = true;
      this.setRotation(LeftDoorButton, 0.0F, 0.0F, 0.0F);
      RightDoorButton = new ModelRenderer(this, 140, 0);
      RightDoorButton.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      RightDoorButton.setRotationPoint(9.0F, 5.0F, -10.0F);
      RightDoorButton.setTextureSize(512, 512);
      RightDoorButton.mirror = true;
      this.setRotation(RightDoorButton, 0.0F, 0.0F, 0.0F);
   }

   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
      Boottom.render(f5);
      WallLeft.render(f5);
      WallRight.render(f5);
      Top.render(f5);
      MiddleWall.render(f5);
      drawer2Front.render(f5);
      drawer1Front.render(f5);
      LittleDoor.render(f5);
      DrawerButton1.render(f5);
      DrawerButton2.render(f5);
      LeftDoor.render(f5);
      RightDoor.render(f5);
      BackWall.render(f5);
      LittleDoorButton.render(f5);
      LeftDoorButton.render(f5);
      RightDoorButton.render(f5);
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
