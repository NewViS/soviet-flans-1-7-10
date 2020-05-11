package net.minecraft.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.WeightedRandom$Item;

public class WeightedRandom {

   public static int getTotalWeight(Collection var0) {
      int var1 = 0;

      WeightedRandom$Item var3;
      for(Iterator var2 = var0.iterator(); var2.hasNext(); var1 += var3.itemWeight) {
         var3 = (WeightedRandom$Item)var2.next();
      }

      return var1;
   }

   public static WeightedRandom$Item getRandomItem(Random var0, Collection var1, int var2) {
      if(var2 <= 0) {
         throw new IllegalArgumentException();
      } else {
         int var3 = var0.nextInt(var2);
         Iterator var4 = var1.iterator();

         WeightedRandom$Item var5;
         do {
            if(!var4.hasNext()) {
               return null;
            }

            var5 = (WeightedRandom$Item)var4.next();
            var3 -= var5.itemWeight;
         } while(var3 >= 0);

         return var5;
      }
   }

   public static WeightedRandom$Item getRandomItem(Random var0, Collection var1) {
      return getRandomItem(var0, var1, getTotalWeight(var1));
   }

   public static int getTotalWeight(WeightedRandom$Item[] var0) {
      int var1 = 0;
      WeightedRandom$Item[] var2 = var0;
      int var3 = var0.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         WeightedRandom$Item var5 = var2[var4];
         var1 += var5.itemWeight;
      }

      return var1;
   }

   public static WeightedRandom$Item getRandomItem(Random var0, WeightedRandom$Item[] var1, int var2) {
      if(var2 <= 0) {
         throw new IllegalArgumentException();
      } else {
         int var3 = var0.nextInt(var2);
         WeightedRandom$Item[] var4 = var1;
         int var5 = var1.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            WeightedRandom$Item var7 = var4[var6];
            var3 -= var7.itemWeight;
            if(var3 < 0) {
               return var7;
            }
         }

         return null;
      }
   }

   public static WeightedRandom$Item getRandomItem(Random var0, WeightedRandom$Item[] var1) {
      return getRandomItem(var0, var1, getTotalWeight(var1));
   }
}
