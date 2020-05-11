package net.minecraft.client.resources;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository$1;
import net.minecraft.client.resources.ResourcePackRepository$2;
import net.minecraft.client.resources.ResourcePackRepository$Entry;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.HttpUtil;

public class ResourcePackRepository {

   protected static final FileFilter resourcePackFilter = new ResourcePackRepository$1();
   private final File dirResourcepacks;
   public final IResourcePack rprDefaultResourcePack;
   private final File field_148534_e;
   public final IMetadataSerializer rprMetadataSerializer;
   private IResourcePack field_148532_f;
   private boolean field_148533_g;
   private List repositoryEntriesAll = Lists.newArrayList();
   private List repositoryEntries = Lists.newArrayList();


   public ResourcePackRepository(File var1, File var2, IResourcePack var3, IMetadataSerializer var4, GameSettings var5) {
      this.dirResourcepacks = var1;
      this.field_148534_e = var2;
      this.rprDefaultResourcePack = var3;
      this.rprMetadataSerializer = var4;
      this.fixDirResourcepacks();
      this.updateRepositoryEntriesAll();
      Iterator var6 = var5.resourcePacks.iterator();

      while(var6.hasNext()) {
         String var7 = (String)var6.next();
         Iterator var8 = this.repositoryEntriesAll.iterator();

         while(var8.hasNext()) {
            ResourcePackRepository$Entry var9 = (ResourcePackRepository$Entry)var8.next();
            if(var9.getResourcePackName().equals(var7)) {
               this.repositoryEntries.add(var9);
               break;
            }
         }
      }

   }

   private void fixDirResourcepacks() {
      if(!this.dirResourcepacks.isDirectory()) {
         this.dirResourcepacks.delete();
         this.dirResourcepacks.mkdirs();
      }

   }

   private List getResourcePackFiles() {
      return this.dirResourcepacks.isDirectory()?Arrays.asList(this.dirResourcepacks.listFiles(resourcePackFilter)):Collections.emptyList();
   }

   public void updateRepositoryEntriesAll() {
      ArrayList var1 = Lists.newArrayList();
      Iterator var2 = this.getResourcePackFiles().iterator();

      while(var2.hasNext()) {
         File var3 = (File)var2.next();
         ResourcePackRepository$Entry var4 = new ResourcePackRepository$Entry(this, var3, (ResourcePackRepository$1)null);
         if(!this.repositoryEntriesAll.contains(var4)) {
            try {
               var4.updateResourcePack();
               var1.add(var4);
            } catch (Exception var6) {
               var1.remove(var4);
            }
         } else {
            int var5 = this.repositoryEntriesAll.indexOf(var4);
            if(var5 > -1 && var5 < this.repositoryEntriesAll.size()) {
               var1.add(this.repositoryEntriesAll.get(var5));
            }
         }
      }

      this.repositoryEntriesAll.removeAll(var1);
      var2 = this.repositoryEntriesAll.iterator();

      while(var2.hasNext()) {
         ResourcePackRepository$Entry var7 = (ResourcePackRepository$Entry)var2.next();
         var7.closeResourcePack();
      }

      this.repositoryEntriesAll = var1;
   }

   public List getRepositoryEntriesAll() {
      return ImmutableList.copyOf(this.repositoryEntriesAll);
   }

   public List getRepositoryEntries() {
      return ImmutableList.copyOf(this.repositoryEntries);
   }

   public void func_148527_a(List var1) {
      this.repositoryEntries.clear();
      this.repositoryEntries.addAll(var1);
   }

   public File getDirResourcepacks() {
      return this.dirResourcepacks;
   }

   public void func_148526_a(String var1) {
      String var2 = var1.substring(var1.lastIndexOf("/") + 1);
      if(var2.contains("?")) {
         var2 = var2.substring(0, var2.indexOf("?"));
      }

      if(var2.endsWith(".zip")) {
         File var3 = new File(this.field_148534_e, var2.replaceAll("\\W", ""));
         this.func_148529_f();
         this.func_148528_a(var1, var3);
      }
   }

   private void func_148528_a(String var1, File var2) {
      HashMap var3 = Maps.newHashMap();
      GuiScreenWorking var4 = new GuiScreenWorking();
      var3.put("X-Minecraft-Username", Minecraft.getMinecraft().getSession().getUsername());
      var3.put("X-Minecraft-UUID", Minecraft.getMinecraft().getSession().getPlayerID());
      var3.put("X-Minecraft-Version", "1.7.10");
      this.field_148533_g = true;
      Minecraft.getMinecraft().displayGuiScreen(var4);
      HttpUtil.func_151223_a(var2, var1, new ResourcePackRepository$2(this), var3, 52428800, var4, Minecraft.getMinecraft().getProxy());
   }

   public IResourcePack func_148530_e() {
      return this.field_148532_f;
   }

   public void func_148529_f() {
      this.field_148532_f = null;
      this.field_148533_g = false;
   }

   // $FF: synthetic method
   static boolean access$100(ResourcePackRepository var0) {
      return var0.field_148533_g;
   }

   // $FF: synthetic method
   static boolean access$102(ResourcePackRepository var0, boolean var1) {
      return var0.field_148533_g = var1;
   }

   // $FF: synthetic method
   static IResourcePack access$202(ResourcePackRepository var0, IResourcePack var1) {
      return var0.field_148532_f = var1;
   }

}
