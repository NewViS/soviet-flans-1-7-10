package net.minecraft.entity;

import net.minecraft.entity.Entity$SwitchEnumEntitySize;
import net.minecraft.util.MathHelper;

public enum Entity$EnumEntitySize {

   SIZE_1("SIZE_1", 0),
   SIZE_2("SIZE_2", 1),
   SIZE_3("SIZE_3", 2),
   SIZE_4("SIZE_4", 3),
   SIZE_5("SIZE_5", 4),
   SIZE_6("SIZE_6", 5);
   // $FF: synthetic field
   private static final Entity$EnumEntitySize[] $VALUES = new Entity$EnumEntitySize[]{SIZE_1, SIZE_2, SIZE_3, SIZE_4, SIZE_5, SIZE_6};


   private Entity$EnumEntitySize(String var1, int var2) {}

   public int multiplyBy32AndRound(double var1) {
      double var3 = var1 - ((double)MathHelper.floor_double(var1) + 0.5D);
      switch(Entity$SwitchEnumEntitySize.field_96565_a[this.ordinal()]) {
      case 1:
         if(var3 < 0.0D) {
            if(var3 < -0.3125D) {
               return MathHelper.ceiling_double_int(var1 * 32.0D);
            }
         } else if(var3 < 0.3125D) {
            return MathHelper.ceiling_double_int(var1 * 32.0D);
         }

         return MathHelper.floor_double(var1 * 32.0D);
      case 2:
         if(var3 < 0.0D) {
            if(var3 < -0.3125D) {
               return MathHelper.floor_double(var1 * 32.0D);
            }
         } else if(var3 < 0.3125D) {
            return MathHelper.floor_double(var1 * 32.0D);
         }

         return MathHelper.ceiling_double_int(var1 * 32.0D);
      case 3:
         if(var3 > 0.0D) {
            return MathHelper.floor_double(var1 * 32.0D);
         }

         return MathHelper.ceiling_double_int(var1 * 32.0D);
      case 4:
         if(var3 < 0.0D) {
            if(var3 < -0.1875D) {
               return MathHelper.ceiling_double_int(var1 * 32.0D);
            }
         } else if(var3 < 0.1875D) {
            return MathHelper.ceiling_double_int(var1 * 32.0D);
         }

         return MathHelper.floor_double(var1 * 32.0D);
      case 5:
         if(var3 < 0.0D) {
            if(var3 < -0.1875D) {
               return MathHelper.floor_double(var1 * 32.0D);
            }
         } else if(var3 < 0.1875D) {
            return MathHelper.floor_double(var1 * 32.0D);
         }

         return MathHelper.ceiling_double_int(var1 * 32.0D);
      case 6:
      default:
         if(var3 > 0.0D) {
            return MathHelper.ceiling_double_int(var1 * 32.0D);
         } else {
            return MathHelper.floor_double(var1 * 32.0D);
         }
      }
   }

}
