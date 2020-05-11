package net.minecraft.command;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

class CommandSpreadPlayers$Position {

   double field_111101_a;
   double field_111100_b;


   CommandSpreadPlayers$Position() {}

   CommandSpreadPlayers$Position(double var1, double var3) {
      this.field_111101_a = var1;
      this.field_111100_b = var3;
   }

   double func_111099_a(CommandSpreadPlayers$Position var1) {
      double var2 = this.field_111101_a - var1.field_111101_a;
      double var4 = this.field_111100_b - var1.field_111100_b;
      return Math.sqrt(var2 * var2 + var4 * var4);
   }

   void func_111095_a() {
      double var1 = (double)this.func_111096_b();
      this.field_111101_a /= var1;
      this.field_111100_b /= var1;
   }

   float func_111096_b() {
      return MathHelper.sqrt_double(this.field_111101_a * this.field_111101_a + this.field_111100_b * this.field_111100_b);
   }

   public void func_111094_b(CommandSpreadPlayers$Position var1) {
      this.field_111101_a -= var1.field_111101_a;
      this.field_111100_b -= var1.field_111100_b;
   }

   public boolean func_111093_a(double var1, double var3, double var5, double var7) {
      boolean var9 = false;
      if(this.field_111101_a < var1) {
         this.field_111101_a = var1;
         var9 = true;
      } else if(this.field_111101_a > var5) {
         this.field_111101_a = var5;
         var9 = true;
      }

      if(this.field_111100_b < var3) {
         this.field_111100_b = var3;
         var9 = true;
      } else if(this.field_111100_b > var7) {
         this.field_111100_b = var7;
         var9 = true;
      }

      return var9;
   }

   public int func_111092_a(World var1) {
      int var2 = MathHelper.floor_double(this.field_111101_a);
      int var3 = MathHelper.floor_double(this.field_111100_b);

      for(int var4 = 256; var4 > 0; --var4) {
         if(var1.getBlock(var2, var4, var3).getMaterial() != Material.air) {
            return var4 + 1;
         }
      }

      return 257;
   }

   public boolean func_111098_b(World var1) {
      int var2 = MathHelper.floor_double(this.field_111101_a);
      int var3 = MathHelper.floor_double(this.field_111100_b);
      short var4 = 256;
      if(var4 <= 0) {
         return false;
      } else {
         Material var5 = var1.getBlock(var2, var4, var3).getMaterial();
         return !var5.isLiquid() && var5 != Material.fire;
      }
   }

   public void func_111097_a(Random var1, double var2, double var4, double var6, double var8) {
      this.field_111101_a = MathHelper.getRandomDoubleInRange(var1, var2, var6);
      this.field_111100_b = MathHelper.getRandomDoubleInRange(var1, var4, var8);
   }
}
