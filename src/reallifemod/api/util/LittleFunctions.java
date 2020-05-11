package de.ItsAMysterious.mods.reallifemod.api.util;

import de.ItsAMysterious.mods.reallifemod.api.interfaces.Buyable;
import java.awt.image.ColorModel;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LittleFunctions {

   private static final int ScreenHeight = Minecraft.getMinecraft().displayHeight;
   private static final int ScreenWidth = Minecraft.getMinecraft().displayWidth;
   public static final int W = 17;
   public static final int L = 28;
   public static final int Z = 29;
   public static final int A = 30;
   public static final int D = 32;
   public static final String numbers = "1234567890";
   public static final int[] policenumbers = new int[]{101, 112, 160, 133, 100, 17, 45, 997, 911, 117, 158, 113, 92, 158, 155, 107};
   public static final int[] emergencynumbers = new int[]{100, 113, 150, 144, 112, 999, 166, 151, 17, 49, 151, 3, 144, 118, 155, 94, 49, 94, 104};


   public static int divideValue(int value, int divor) {
      return value / divor;
   }

   public static final String calculateAge(int milliseconds) {
      int seconds = Math.round((float)(milliseconds / 100));
      int minutes = Math.round((float)(seconds / 60));
      int hours = Math.round((float)(minutes / 60));
      int days = (int)Math.round((double)hours / 24.0D);
      int weeks = days / 7;
      int months = days / 30;
      int years = Math.round((float)(days / 365));
      seconds -= 60 * minutes;
      minutes -= 60 * hours;
      hours -= 24 * days;
      days -= 7 * weeks;
      weeks -= 52 * years;
      int var10000 = months - 12 * years;
      return years > 0?years + " Years, " + weeks + " weeks, " + days + " days, " + hours + " hours," + minutes + " minutes," + seconds + " seconds":(weeks > 0?weeks + " weeks, " + days + " days, " + hours + " hours," + minutes + " minutes," + seconds + " seconds":(days > 0?days + " days, " + hours + " hours," + minutes + " minutes," + seconds + " seconds":hours + " hours," + minutes + " minutes," + seconds + " seconds"));
   }

   static double calculateSum(Buyable price1, Buyable ... prices) {
      double result = price1.price();
      Buyable[] var4 = prices;
      int var5 = prices.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         Buyable price = var4[var6];
         result += price.price();
      }

      return result;
   }

   static double reduce(Buyable object) {
      double result = object.price() / 100.0D * (double)object.percentage();
      return result;
   }

   static String getAgeInYears(EntityPlayer player) {
      int age = player.func_70654_ax();
      int lnge = calculateAge(age).indexOf("Years");
      String s = calculateAge(age).substring(0, lnge);
      return s;
   }

   public static double getPrice(Buyable object) {
      return object.price();
   }

   public static Object Color(ColorModel color) {
      return Integer.valueOf(color.getRGB(color));
   }

   public static float sq(float x) {
      return x * x;
   }

   public static float roundDouble(double number, int decimalPlaces) {
      float precision = 1.0F;

      for(int i = 0; i < decimalPlaces; precision *= 10.0F) {
         ++i;
      }

      return (float)((int)(number * (double)precision + 0.5D)) / precision;
   }

   public static float roundFloat(float number, int decimalPlaces) {
      float precision = 1.0F;

      for(int i = 0; i < decimalPlaces; precision *= 10.0F) {
         ++i;
      }

      return (float)((int)((double)(number * precision) + 0.5D)) / precision;
   }

   public static float getRealTimeInSeconds(float minecraftTime) {
      return minecraftTime / 20.0F;
   }

   public static double realTimeToMinecraft(double e) {
      return e * 1.0D / 20.0D;
   }

   public static Block getBlock(World world, double x, double y, double z) {
      return world.getBlock((int)x, (int)y, (int)z);
   }

   public static double randMinMax(double min, double max) {
      double random = (new Random()).nextDouble();
      double result = min + random * (max - min);
      return result;
   }

   public static double square(double number) {
      return number * number;
   }


   public static enum WeekDay {

      MONDAY("MONDAY", 0),
      TUESDAY("TUESDAY", 1),
      WEDNESDAY("WEDNESDAY", 2),
      THURSDAY("THURSDAY", 3),
      FRIDAY("FRIDAY", 4),
      SATURDAY("SATURDAY", 5),
      SUNDAY("SUNDAY", 6);
      // $FF: synthetic field
      private static final LittleFunctions.WeekDay[] $VALUES = new LittleFunctions.WeekDay[]{MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};


      private WeekDay(String var1, int var2) {}

   }
}
