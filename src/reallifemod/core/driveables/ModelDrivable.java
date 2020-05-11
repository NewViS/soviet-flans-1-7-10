package de.ItsAMysterious.mods.reallifemod.core.driveables;

import net.minecraftforge.client.model.IModelCustom;

public class ModelDrivable {

   IModelCustom model;
   IModelCustom lights;
   IModelCustom colored;
   IModelCustom wheels;
   IModelCustom rightwheel;
   IModelCustom leftwheel;


   public IModelCustom getLights() {
      return this.lights;
   }

   public IModelCustom getColored() {
      return this.colored;
   }

   public IModelCustom getWheels() {
      return this.wheels;
   }

   public IModelCustom getRightwheel() {
      return this.rightwheel;
   }

   public IModelCustom getLeftwheel() {
      return this.leftwheel;
   }
}
