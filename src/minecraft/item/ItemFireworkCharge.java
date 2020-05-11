package net.minecraft.item;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemFireworkCharge extends Item {

   private IIcon field_150904_a;


   public IIcon getIconFromDamageForRenderPass(int var1, int var2) {
      return var2 > 0?this.field_150904_a:super.getIconFromDamageForRenderPass(var1, var2);
   }

   public int getColorFromItemStack(ItemStack var1, int var2) {
      if(var2 != 1) {
         return super.getColorFromItemStack(var1, var2);
      } else {
         NBTBase var3 = func_150903_a(var1, "Colors");
         if(var3 != null && var3 instanceof NBTTagIntArray) {
            NBTTagIntArray var4 = (NBTTagIntArray)var3;
            int[] var5 = var4.func_150302_c();
            if(var5.length == 1) {
               return var5[0];
            } else {
               int var6 = 0;
               int var7 = 0;
               int var8 = 0;
               int[] var9 = var5;
               int var10 = var5.length;

               for(int var11 = 0; var11 < var10; ++var11) {
                  int var12 = var9[var11];
                  var6 += (var12 & 16711680) >> 16;
                  var7 += (var12 & '\uff00') >> 8;
                  var8 += (var12 & 255) >> 0;
               }

               var6 /= var5.length;
               var7 /= var5.length;
               var8 /= var5.length;
               return var6 << 16 | var7 << 8 | var8;
            }
         } else {
            return 9079434;
         }
      }
   }

   public boolean requiresMultipleRenderPasses() {
      return true;
   }

   public static NBTBase func_150903_a(ItemStack var0, String var1) {
      if(var0.hasTagCompound()) {
         NBTTagCompound var2 = var0.getTagCompound().getCompoundTag("Explosion");
         if(var2 != null) {
            return var2.getTag(var1);
         }
      }

      return null;
   }

   public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4) {
      if(var1.hasTagCompound()) {
         NBTTagCompound var5 = var1.getTagCompound().getCompoundTag("Explosion");
         if(var5 != null) {
            func_150902_a(var5, var3);
         }
      }

   }

   public static void func_150902_a(NBTTagCompound var0, List var1) {
      byte var2 = var0.getByte("Type");
      if(var2 >= 0 && var2 <= 4) {
         var1.add(StatCollector.translateToLocal("item.fireworksCharge.type." + var2).trim());
      } else {
         var1.add(StatCollector.translateToLocal("item.fireworksCharge.type").trim());
      }

      int[] var3 = var0.getIntArray("Colors");
      int var8;
      int var9;
      if(var3.length > 0) {
         boolean var4 = true;
         String var5 = "";
         int[] var6 = var3;
         int var7 = var3.length;

         for(var8 = 0; var8 < var7; ++var8) {
            var9 = var6[var8];
            if(!var4) {
               var5 = var5 + ", ";
            }

            var4 = false;
            boolean var10 = false;

            for(int var11 = 0; var11 < 16; ++var11) {
               if(var9 == ItemDye.field_150922_c[var11]) {
                  var10 = true;
                  var5 = var5 + StatCollector.translateToLocal("item.fireworksCharge." + ItemDye.field_150923_a[var11]);
                  break;
               }
            }

            if(!var10) {
               var5 = var5 + StatCollector.translateToLocal("item.fireworksCharge.customColor");
            }
         }

         var1.add(var5);
      }

      int[] var13 = var0.getIntArray("FadeColors");
      boolean var15;
      if(var13.length > 0) {
         var15 = true;
         String var14 = StatCollector.translateToLocal("item.fireworksCharge.fadeTo") + " ";
         int[] var16 = var13;
         var8 = var13.length;

         for(var9 = 0; var9 < var8; ++var9) {
            int var18 = var16[var9];
            if(!var15) {
               var14 = var14 + ", ";
            }

            var15 = false;
            boolean var19 = false;

            for(int var12 = 0; var12 < 16; ++var12) {
               if(var18 == ItemDye.field_150922_c[var12]) {
                  var19 = true;
                  var14 = var14 + StatCollector.translateToLocal("item.fireworksCharge." + ItemDye.field_150923_a[var12]);
                  break;
               }
            }

            if(!var19) {
               var14 = var14 + StatCollector.translateToLocal("item.fireworksCharge.customColor");
            }
         }

         var1.add(var14);
      }

      var15 = var0.getBoolean("Trail");
      if(var15) {
         var1.add(StatCollector.translateToLocal("item.fireworksCharge.trail"));
      }

      boolean var17 = var0.getBoolean("Flicker");
      if(var17) {
         var1.add(StatCollector.translateToLocal("item.fireworksCharge.flicker"));
      }

   }

   public void registerIcons(IIconRegister var1) {
      super.registerIcons(var1);
      this.field_150904_a = var1.registerIcon(this.getIconString() + "_overlay");
   }
}
