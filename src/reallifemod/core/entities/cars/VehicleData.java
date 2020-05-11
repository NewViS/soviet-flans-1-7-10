package de.ItsAMysterious.mods.reallifemod.core.entities.cars;


public class VehicleData {

   protected double width;
   protected double length;
   protected double maxhealth;
   protected int numPassengers;
   public double maxfuel;
   public float maxSpeed;
   protected double minspeed;
   public String modellocation;
   public static String name;


   public VehicleData(String name, double width, double length, double maxhealth, int numPassengers, double maxfuel, float maxSpeed, double minspeed) {
      name = name;
      this.width = width;
      this.length = length;
      this.maxhealth = maxhealth;
      this.numPassengers = numPassengers;
      this.maxfuel = maxfuel;
      this.maxSpeed = maxSpeed;
      this.minspeed = minspeed;
   }

   public double getWidth() {
      return this.width;
   }

   public double getLength() {
      return this.length;
   }

   public double getMaxhealth() {
      return this.maxhealth;
   }

   public int getNumPassengers() {
      return this.numPassengers;
   }

   public double getMaxfuel() {
      return this.maxfuel;
   }

   public float getMaxSpeed() {
      return this.maxSpeed;
   }

   public double getMinspeed() {
      return this.minspeed;
   }

   public String getModellocation() {
      return this.modellocation;
   }

   public static String getName() {
      return name;
   }
}
