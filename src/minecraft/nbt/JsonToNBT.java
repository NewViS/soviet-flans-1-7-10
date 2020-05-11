package net.minecraft.nbt;

import java.util.Stack;
import net.minecraft.nbt.JsonToNBT$Any;
import net.minecraft.nbt.JsonToNBT$Compound;
import net.minecraft.nbt.JsonToNBT$List;
import net.minecraft.nbt.JsonToNBT$Primitive;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonToNBT {

   private static final Logger logger = LogManager.getLogger();


   public static NBTBase func_150315_a(String var0) {
      var0 = var0.trim();
      int var1 = func_150310_b(var0);
      if(var1 != 1) {
         throw new NBTException("Encountered multiple top tags, only one expected");
      } else {
         JsonToNBT$Any var2 = null;
         if(var0.startsWith("{")) {
            var2 = func_150316_a("tag", var0);
         } else {
            var2 = func_150316_a(func_150313_b(var0, false), func_150311_c(var0, false));
         }

         return var2.func_150489_a();
      }
   }

   static int func_150310_b(String var0) {
      int var1 = 0;
      boolean var2 = false;
      Stack var3 = new Stack();

      for(int var4 = 0; var4 < var0.length(); ++var4) {
         char var5 = var0.charAt(var4);
         if(var5 == 34) {
            if(var4 > 0 && var0.charAt(var4 - 1) == 92) {
               if(!var2) {
                  throw new NBTException("Illegal use of \\\": " + var0);
               }
            } else {
               var2 = !var2;
            }
         } else if(!var2) {
            if(var5 != 123 && var5 != 91) {
               if(var5 == 125 && (var3.isEmpty() || ((Character)var3.pop()).charValue() != 123)) {
                  throw new NBTException("Unbalanced curly brackets {}: " + var0);
               }

               if(var5 == 93 && (var3.isEmpty() || ((Character)var3.pop()).charValue() != 91)) {
                  throw new NBTException("Unbalanced square brackets []: " + var0);
               }
            } else {
               if(var3.isEmpty()) {
                  ++var1;
               }

               var3.push(Character.valueOf(var5));
            }
         }
      }

      if(var2) {
         throw new NBTException("Unbalanced quotation: " + var0);
      } else if(!var3.isEmpty()) {
         throw new NBTException("Unbalanced brackets: " + var0);
      } else if(var1 == 0 && !var0.isEmpty()) {
         return 1;
      } else {
         return var1;
      }
   }

   static JsonToNBT$Any func_150316_a(String var0, String var1) {
      var1 = var1.trim();
      func_150310_b(var1);
      String var3;
      String var4;
      String var5;
      char var6;
      if(var1.startsWith("{")) {
         if(!var1.endsWith("}")) {
            throw new NBTException("Unable to locate ending bracket for: " + var1);
         } else {
            var1 = var1.substring(1, var1.length() - 1);
            JsonToNBT$Compound var7 = new JsonToNBT$Compound(var0);

            while(var1.length() > 0) {
               var3 = func_150314_a(var1, false);
               if(var3.length() > 0) {
                  var4 = func_150313_b(var3, false);
                  var5 = func_150311_c(var3, false);
                  var7.field_150491_b.add(func_150316_a(var4, var5));
                  if(var1.length() < var3.length() + 1) {
                     break;
                  }

                  var6 = var1.charAt(var3.length());
                  if(var6 != 44 && var6 != 123 && var6 != 125 && var6 != 91 && var6 != 93) {
                     throw new NBTException("Unexpected token \'" + var6 + "\' at: " + var1.substring(var3.length()));
                  }

                  var1 = var1.substring(var3.length() + 1);
               }
            }

            return var7;
         }
      } else if(var1.startsWith("[") && !var1.matches("\\[[-\\d|,\\s]+\\]")) {
         if(!var1.endsWith("]")) {
            throw new NBTException("Unable to locate ending bracket for: " + var1);
         } else {
            var1 = var1.substring(1, var1.length() - 1);
            JsonToNBT$List var2 = new JsonToNBT$List(var0);

            while(var1.length() > 0) {
               var3 = func_150314_a(var1, true);
               if(var3.length() > 0) {
                  var4 = func_150313_b(var3, true);
                  var5 = func_150311_c(var3, true);
                  var2.field_150492_b.add(func_150316_a(var4, var5));
                  if(var1.length() < var3.length() + 1) {
                     break;
                  }

                  var6 = var1.charAt(var3.length());
                  if(var6 != 44 && var6 != 123 && var6 != 125 && var6 != 91 && var6 != 93) {
                     throw new NBTException("Unexpected token \'" + var6 + "\' at: " + var1.substring(var3.length()));
                  }

                  var1 = var1.substring(var3.length() + 1);
               } else {
                  logger.debug(var1);
               }
            }

            return var2;
         }
      } else {
         return new JsonToNBT$Primitive(var0, var1);
      }
   }

   private static String func_150314_a(String var0, boolean var1) {
      int var2 = func_150312_a(var0, ':');
      if(var2 < 0 && !var1) {
         throw new NBTException("Unable to locate name/value separator for string: " + var0);
      } else {
         int var3 = func_150312_a(var0, ',');
         if(var3 >= 0 && var3 < var2 && !var1) {
            throw new NBTException("Name error at: " + var0);
         } else {
            if(var1 && (var2 < 0 || var2 > var3)) {
               var2 = -1;
            }

            Stack var4 = new Stack();
            int var5 = var2 + 1;
            boolean var6 = false;
            boolean var7 = false;
            boolean var8 = false;

            for(int var9 = 0; var5 < var0.length(); ++var5) {
               char var10 = var0.charAt(var5);
               if(var10 == 34) {
                  if(var5 > 0 && var0.charAt(var5 - 1) == 92) {
                     if(!var6) {
                        throw new NBTException("Illegal use of \\\": " + var0);
                     }
                  } else {
                     var6 = !var6;
                     if(var6 && !var8) {
                        var7 = true;
                     }

                     if(!var6) {
                        var9 = var5;
                     }
                  }
               } else if(!var6) {
                  if(var10 != 123 && var10 != 91) {
                     if(var10 == 125 && (var4.isEmpty() || ((Character)var4.pop()).charValue() != 123)) {
                        throw new NBTException("Unbalanced curly brackets {}: " + var0);
                     }

                     if(var10 == 93 && (var4.isEmpty() || ((Character)var4.pop()).charValue() != 91)) {
                        throw new NBTException("Unbalanced square brackets []: " + var0);
                     }

                     if(var10 == 44 && var4.isEmpty()) {
                        return var0.substring(0, var5);
                     }
                  } else {
                     var4.push(Character.valueOf(var10));
                  }
               }

               if(!Character.isWhitespace(var10)) {
                  if(!var6 && var7 && var9 != var5) {
                     return var0.substring(0, var9 + 1);
                  }

                  var8 = true;
               }
            }

            return var0.substring(0, var5);
         }
      }
   }

   private static String func_150313_b(String var0, boolean var1) {
      if(var1) {
         var0 = var0.trim();
         if(var0.startsWith("{") || var0.startsWith("[")) {
            return "";
         }
      }

      int var2 = var0.indexOf(58);
      if(var2 < 0) {
         if(var1) {
            return "";
         } else {
            throw new NBTException("Unable to locate name/value separator for string: " + var0);
         }
      } else {
         return var0.substring(0, var2).trim();
      }
   }

   private static String func_150311_c(String var0, boolean var1) {
      if(var1) {
         var0 = var0.trim();
         if(var0.startsWith("{") || var0.startsWith("[")) {
            return var0;
         }
      }

      int var2 = var0.indexOf(58);
      if(var2 < 0) {
         if(var1) {
            return var0;
         } else {
            throw new NBTException("Unable to locate name/value separator for string: " + var0);
         }
      } else {
         return var0.substring(var2 + 1).trim();
      }
   }

   private static int func_150312_a(String var0, char var1) {
      int var2 = 0;

      for(boolean var3 = false; var2 < var0.length(); ++var2) {
         char var4 = var0.charAt(var2);
         if(var4 == 34) {
            if(var2 <= 0 || var0.charAt(var2 - 1) != 92) {
               var3 = !var3;
            }
         } else if(!var3) {
            if(var4 == var1) {
               return var2;
            }

            if(var4 == 123 || var4 == 91) {
               return -1;
            }
         }
      }

      return -1;
   }

}
