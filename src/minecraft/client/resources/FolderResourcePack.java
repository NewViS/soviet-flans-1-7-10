package net.minecraft.client.resources;

import com.google.common.collect.Sets;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.resources.AbstractResourcePack;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class FolderResourcePack extends AbstractResourcePack {

   public FolderResourcePack(File var1) {
      super(var1);
   }

   protected InputStream getInputStreamByName(String var1) {
      return new BufferedInputStream(new FileInputStream(new File(super.resourcePackFile, var1)));
   }

   protected boolean hasResourceName(String var1) {
      return (new File(super.resourcePackFile, var1)).isFile();
   }

   public Set getResourceDomains() {
      HashSet var1 = Sets.newHashSet();
      File var2 = new File(super.resourcePackFile, "assets/");
      if(var2.isDirectory()) {
         File[] var3 = var2.listFiles(DirectoryFileFilter.DIRECTORY);
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            File var6 = var3[var5];
            String var7 = getRelativeName(var2, var6);
            if(!var7.equals(var7.toLowerCase())) {
               this.logNameNotLowercase(var7);
            } else {
               var1.add(var7.substring(0, var7.length() - 1));
            }
         }
      }

      return var1;
   }
}
