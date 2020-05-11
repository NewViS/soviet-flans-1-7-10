package net.minecraft.realms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.realms.RealmsLevelSummary;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.SaveFormatComparator;

public class RealmsAnvilLevelStorageSource {

   private ISaveFormat levelStorageSource;


   public RealmsAnvilLevelStorageSource(ISaveFormat var1) {
      this.levelStorageSource = var1;
   }

   public String getName() {
      return this.levelStorageSource.func_154333_a();
   }

   public boolean levelExists(String var1) {
      return this.levelStorageSource.canLoadWorld(var1);
   }

   public boolean convertLevel(String var1, IProgressUpdate var2) {
      return this.levelStorageSource.convertMapFormat(var1, var2);
   }

   public boolean requiresConversion(String var1) {
      return this.levelStorageSource.isOldMapFormat(var1);
   }

   public boolean isNewLevelIdAcceptable(String var1) {
      return this.levelStorageSource.func_154335_d(var1);
   }

   public boolean deleteLevel(String var1) {
      return this.levelStorageSource.deleteWorldDirectory(var1);
   }

   public boolean isConvertible(String var1) {
      return this.levelStorageSource.func_154334_a(var1);
   }

   public void renameLevel(String var1, String var2) {
      this.levelStorageSource.renameWorld(var1, var2);
   }

   public void clearAll() {
      this.levelStorageSource.flushCache();
   }

   public List getLevelList() {
      ArrayList var1 = new ArrayList();
      Iterator var2 = this.levelStorageSource.getSaveList().iterator();

      while(var2.hasNext()) {
         SaveFormatComparator var3 = (SaveFormatComparator)var2.next();
         var1.add(new RealmsLevelSummary(var3));
      }

      return var1;
   }
}
