package de.ItsAMysterious.mods.reallifemod.core.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

public class Config {

   public static void load(Configuration config) {
      try {
         config.load();
         Field[] e = Config.class.getFields();
         Field[] var2 = e;
         int var3 = e.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            Config.CfgId annotation = (Config.CfgId)field.getAnnotation(Config.CfgId.class);
         }
      } catch (Exception var10) {
         ;
      } finally {
         config.save();
      }

   }

   public static boolean isBlock(ItemStack stack) {
      String itemName = stack.getDisplayName().toLowerCase();
      return stack.getItem() instanceof ItemBlock || !itemName.contains("item") || !(stack.getItem() instanceof Item);
   }

   @Retention(RetentionPolicy.RUNTIME)
   private @interface CfgBool {
   }

   @Retention(RetentionPolicy.RUNTIME)
   private @interface CfgId {

      boolean block() default false;
   }
}
