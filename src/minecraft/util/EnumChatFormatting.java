package net.minecraft.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum EnumChatFormatting {

   BLACK("BLACK", 0, '0'),
   DARK_BLUE("DARK_BLUE", 1, '1'),
   DARK_GREEN("DARK_GREEN", 2, '2'),
   DARK_AQUA("DARK_AQUA", 3, '3'),
   DARK_RED("DARK_RED", 4, '4'),
   DARK_PURPLE("DARK_PURPLE", 5, '5'),
   GOLD("GOLD", 6, '6'),
   GRAY("GRAY", 7, '7'),
   DARK_GRAY("DARK_GRAY", 8, '8'),
   BLUE("BLUE", 9, '9'),
   GREEN("GREEN", 10, 'a'),
   AQUA("AQUA", 11, 'b'),
   RED("RED", 12, 'c'),
   LIGHT_PURPLE("LIGHT_PURPLE", 13, 'd'),
   YELLOW("YELLOW", 14, 'e'),
   WHITE("WHITE", 15, 'f'),
   OBFUSCATED("OBFUSCATED", 16, 'k', true),
   BOLD("BOLD", 17, 'l', true),
   STRIKETHROUGH("STRIKETHROUGH", 18, 'm', true),
   UNDERLINE("UNDERLINE", 19, 'n', true),
   ITALIC("ITALIC", 20, 'o', true),
   RESET("RESET", 21, 'r');
   private static final Map formattingCodeMapping = new HashMap();
   private static final Map nameMapping = new HashMap();
   private static final Pattern formattingCodePattern = Pattern.compile("(?i)" + String.valueOf('\u00a7') + "[0-9A-FK-OR]");
   private final char formattingCode;
   private final boolean fancyStyling;
   private final String controlString;
   // $FF: synthetic field
   private static final EnumChatFormatting[] $VALUES = new EnumChatFormatting[]{BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE, OBFUSCATED, BOLD, STRIKETHROUGH, UNDERLINE, ITALIC, RESET};


   private EnumChatFormatting(String var1, int var2, char var3) {
      this(var1, var2, var3, false);
   }

   private EnumChatFormatting(String var1, int var2, char var3, boolean var4) {
      this.formattingCode = var3;
      this.fancyStyling = var4;
      this.controlString = "§" + var3;
   }

   public char getFormattingCode() {
      return this.formattingCode;
   }

   public boolean isFancyStyling() {
      return this.fancyStyling;
   }

   public boolean isColor() {
      return !this.fancyStyling && this != RESET;
   }

   public String getFriendlyName() {
      return this.name().toLowerCase();
   }

   public String toString() {
      return this.controlString;
   }

   public static String getTextWithoutFormattingCodes(String var0) {
      return var0 == null?null:formattingCodePattern.matcher(var0).replaceAll("");
   }

   public static EnumChatFormatting getValueByName(String var0) {
      return var0 == null?null:(EnumChatFormatting)nameMapping.get(var0.toLowerCase());
   }

   public static Collection getValidValues(boolean var0, boolean var1) {
      ArrayList var2 = new ArrayList();
      EnumChatFormatting[] var3 = values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EnumChatFormatting var6 = var3[var5];
         if((!var6.isColor() || var0) && (!var6.isFancyStyling() || var1)) {
            var2.add(var6.getFriendlyName());
         }
      }

      return var2;
   }

   static {
      EnumChatFormatting[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         EnumChatFormatting var3 = var0[var2];
         formattingCodeMapping.put(Character.valueOf(var3.getFormattingCode()), var3);
         nameMapping.put(var3.getFriendlyName(), var3);
      }

   }
}
