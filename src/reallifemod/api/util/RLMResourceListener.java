package de.ItsAMysterious.mods.reallifemod.api.util;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.driveables.EnumTypes;
import de.ItsAMysterious.mods.reallifemod.core.driveables.InfoType;
import de.ItsAMysterious.mods.reallifemod.core.driveables.TypeFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.util.ResourceLocation;

public class RLMResourceListener implements IResourceManagerReloadListener, IResourceManager {

   public void func_110549_a(IResourceManager manager) {
      if(manager instanceof SimpleReloadableResourceManager) {
         SimpleReloadableResourceManager e = (SimpleReloadableResourceManager)manager;
         FolderResourcePack l = new FolderResourcePack(RealLifeMod.Dir);
         e.reloadResourcePack(l);
      }

      try {
         for(int var7 = 0; var7 < this.func_135056_b(new ResourceLocation("reallifemod:models")).size() - 1; ++var7) {
            List var8 = this.func_135056_b(new ResourceLocation("reallifemod:models"));
            File file = (File)var8.get(var7);
            new InfoType(new TypeFile(EnumTypes.car, file.getAbsolutePath()));
            System.out.println("Added" + file.getAbsolutePath() + "To the resources");
         }
      } catch (IOException var6) {
         var6.printStackTrace();
      }

   }

   public Set func_135055_a() {
      return null;
   }

   public IResource func_110536_a(ResourceLocation p_110536_1_) throws IOException {
      return null;
   }

   public List func_135056_b(ResourceLocation location) throws IOException {
      File folder = new File(location.getResourcePath());
      ArrayList list = new ArrayList();
      list.add(folder.listFiles());
      return list;
   }
}
