package de.ItsAMysterious.mods.reallifemod.api.util;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import de.ItsAMysterious.mods.reallifemod.api.util.RLMResourceHelper;
import java.io.File;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import org.apache.commons.lang3.StringUtils;

public class ResourceLoader {

   private String folder;
   public HashSet folders = new HashSet();
   public HashSet files = new HashSet();
   public String[] extensions;
   private int depth;
   private boolean isRoot;


   public ResourceLoader(String folder, String[] extensions) {
      this.extensions = extensions;
      this.setFolder(folder);
   }

   public void setFolder(String folder) {
      if(!folder.endsWith("/")) {
         folder = folder + "/";
      }

      this.isRoot = folder.length() <= 1;
      this.folder = "/assets" + folder;
      this.depth = StringUtils.countMatches(this.folder, "/");
      this.getFiles();
   }

   public ResourceLoader(String[] extensions) {
      this.extensions = extensions;
   }

   private void getFiles() {
      this.folders.clear();
      this.files.clear();
      ResourcePackRepository repos = Minecraft.getMinecraft().getResourcePackRepository();
      SimpleReloadableResourceManager simplemanager = (SimpleReloadableResourceManager)Minecraft.getMinecraft().getResourceManager();
      Map map = (Map)ObfuscationReflectionHelper.getPrivateValue(SimpleReloadableResourceManager.class, simplemanager, 2);
      HashSet set = new HashSet();
      Iterator iterator = map.keySet().iterator();

      String file;
      while(iterator.hasNext()) {
         file = (String)iterator.next();
         if(map.get(file) instanceof FallbackResourceManager) {
            FallbackResourceManager mod = (FallbackResourceManager)map.get(file);
            List list = (List)ObfuscationReflectionHelper.getPrivateValue(FallbackResourceManager.class, mod, 0);
            Iterator iterator2 = list.iterator();

            while(iterator2.hasNext()) {
               IResourcePack pack = (IResourcePack)iterator2.next();
               if(pack instanceof AbstractResourcePack) {
                  AbstractResourcePack p = (AbstractResourcePack)pack;
                  File file1 = RLMResourceHelper.getPackFile(p);
                  set.add(file1.getAbsolutePath());
               }
            }
         }
      }

      iterator = set.iterator();

      while(iterator.hasNext()) {
         file = (String)iterator.next();
         this.progressFile(new File(file));
      }

      iterator = Loader.instance().getModList().iterator();

      while(iterator.hasNext()) {
         ModContainer mod1 = (ModContainer)iterator.next();
         if(mod1.getSource().exists()) {
            this.progressFile(mod1.getSource());
         }
      }

   }

   private void progressFile(File file) {
      try {
         if(!file.isDirectory() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip"))) {
            ZipFile e1 = new ZipFile(file);
            Enumeration entries = e1.entries();

            while(entries.hasMoreElements()) {
               ZipEntry zipentry = (ZipEntry)entries.nextElement();
               String entryName = zipentry.getName();
               this.checkFile(entryName);
            }

            e1.close();
         } else if(file.isDirectory()) {
            int e = file.getAbsolutePath().length();
            this.checkFolder(file, e);
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   private void checkFolder(File file, int length) {
      File[] files = file.listFiles();
      if(files != null) {
         File[] arr$ = files;
         int len$ = files.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            File f = arr$[i$];
            String name = f.getAbsolutePath().substring(length);
            name = name.replace("\\", "/");
            if(!name.startsWith("/")) {
               name = "/" + name;
            }

            if(f.isDirectory() && (this.folder.startsWith(name) || name.startsWith(this.folder))) {
               this.checkFile(name + "/");
               this.checkFolder(f, length);
            } else {
               this.checkFile(name);
            }
         }

      }
   }

   private void checkFile(String name) {
      if(!name.startsWith("/")) {
         name = "/" + name;
      }

      if(name.startsWith(this.folder)) {
         String[] split = name.split("/");
         int count = split.length;
         if(count == this.depth + 1) {
            if(this.validExtension(name)) {
               this.files.add(split[this.depth]);
            }
         } else if(this.depth + 1 < count) {
            this.folders.add(split[this.depth]);
         }

      }
   }

   private boolean validExtension(String entryName) {
      int index = entryName.indexOf(".");
      if(index < 0) {
         return false;
      } else {
         String extension = entryName.substring(index + 1);
         String[] arr$ = this.extensions;
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            String ex = arr$[i$];
            if(ex.equalsIgnoreCase(extension)) {
               return true;
            }
         }

         return false;
      }
   }

   public String getAsset(String asset) {
      String[] split = this.folder.split("/");
      if(split.length < 3) {
         return null;
      } else {
         String texture = split[2] + ":";
         texture = texture + this.folder.substring(texture.length() + 8) + asset;
         return texture;
      }
   }

   public static String getRoot(String asset) {
      String mod = "minecraft";
      int index = asset.indexOf(":");
      if(index > 0) {
         mod = asset.substring(0, index);
         asset = asset.substring(index + 1);
      }

      if(asset.startsWith("/")) {
         asset = asset.substring(1);
      }

      String location = "/" + mod + "/" + asset;
      index = location.lastIndexOf("/");
      if(index > 0) {
         location = location.substring(0, index);
      }

      return location;
   }
}
