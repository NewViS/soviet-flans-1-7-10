package net.minecraft.client.resources;

import net.minecraft.client.resources.Locale;

public class I18n {

   private static Locale i18nLocale;


   static void setLocale(Locale var0) {
      i18nLocale = var0;
   }

   public static String format(String var0, Object ... var1) {
      return i18nLocale.formatMessage(var0, var1);
   }
}
