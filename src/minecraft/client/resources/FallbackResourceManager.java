package net.minecraft.client.resources;

import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

public class FallbackResourceManager implements IResourceManager {

   protected final List resourcePacks = new ArrayList();
   private final IMetadataSerializer frmMetadataSerializer;


   public FallbackResourceManager(IMetadataSerializer var1) {
      this.frmMetadataSerializer = var1;
   }

   public void addResourcePack(IResourcePack var1) {
      this.resourcePacks.add(var1);
   }

   public Set getResourceDomains() {
      return null;
   }

   public IResource getResource(ResourceLocation var1) {
      IResourcePack var2 = null;
      ResourceLocation var3 = getLocationMcmeta(var1);

      for(int var4 = this.resourcePacks.size() - 1; var4 >= 0; --var4) {
         IResourcePack var5 = (IResourcePack)this.resourcePacks.get(var4);
         if(var2 == null && var5.resourceExists(var3)) {
            var2 = var5;
         }

         if(var5.resourceExists(var1)) {
            InputStream var6 = null;
            if(var2 != null) {
               var6 = var2.getInputStream(var3);
            }

            return new SimpleResource(var1, var5.getInputStream(var1), var6, this.frmMetadataSerializer);
         }
      }

      throw new FileNotFoundException(var1.toString());
   }

   public List getAllResources(ResourceLocation var1) {
      ArrayList var2 = Lists.newArrayList();
      ResourceLocation var3 = getLocationMcmeta(var1);
      Iterator var4 = this.resourcePacks.iterator();

      while(var4.hasNext()) {
         IResourcePack var5 = (IResourcePack)var4.next();
         if(var5.resourceExists(var1)) {
            InputStream var6 = var5.resourceExists(var3)?var5.getInputStream(var3):null;
            var2.add(new SimpleResource(var1, var5.getInputStream(var1), var6, this.frmMetadataSerializer));
         }
      }

      if(var2.isEmpty()) {
         throw new FileNotFoundException(var1.toString());
      } else {
         return var2;
      }
   }

   static ResourceLocation getLocationMcmeta(ResourceLocation var0) {
      return new ResourceLocation(var0.getResourceDomain(), var0.getResourcePath() + ".mcmeta");
   }
}
