package net.minecraft.world.chunk.storage;

import java.io.File;
import java.io.FilenameFilter;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;

class AnvilSaveConverter$1 implements FilenameFilter {

   // $FF: synthetic field
   final AnvilSaveConverter parent;


   AnvilSaveConverter$1(AnvilSaveConverter var1) {
      this.parent = var1;
   }

   public boolean accept(File var1, String var2) {
      return var2.endsWith(".mcr");
   }
}
