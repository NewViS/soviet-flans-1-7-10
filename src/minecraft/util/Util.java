package net.minecraft.util;

import net.minecraft.util.Util$EnumOS;

public class Util {

   public static Util$EnumOS getOSType() {
      String var0 = System.getProperty("os.name").toLowerCase();
      return var0.contains("win")?Util$EnumOS.WINDOWS:(var0.contains("mac")?Util$EnumOS.OSX:(var0.contains("solaris")?Util$EnumOS.SOLARIS:(var0.contains("sunos")?Util$EnumOS.SOLARIS:(var0.contains("linux")?Util$EnumOS.LINUX:(var0.contains("unix")?Util$EnumOS.LINUX:Util$EnumOS.UNKNOWN)))));
   }
}
