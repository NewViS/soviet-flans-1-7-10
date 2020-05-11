package net.minecraft.client.resources;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager$1;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleReloadableResourceManager implements IReloadableResourceManager {

   private static final Logger logger = LogManager.getLogger();
   private static final Joiner joinerResourcePacks = Joiner.on(", ");
   private final Map domainResourceManagers = Maps.newHashMap();
   private final List reloadListeners = Lists.newArrayList();
   private final Set setResourceDomains = Sets.newLinkedHashSet();
   private final IMetadataSerializer rmMetadataSerializer;


   public SimpleReloadableResourceManager(IMetadataSerializer var1) {
      this.rmMetadataSerializer = var1;
   }

   public void reloadResourcePack(IResourcePack var1) {
      FallbackResourceManager var4;
      for(Iterator var2 = var1.getResourceDomains().iterator(); var2.hasNext(); var4.addResourcePack(var1)) {
         String var3 = (String)var2.next();
         this.setResourceDomains.add(var3);
         var4 = (FallbackResourceManager)this.domainResourceManagers.get(var3);
         if(var4 == null) {
            var4 = new FallbackResourceManager(this.rmMetadataSerializer);
            this.domainResourceManagers.put(var3, var4);
         }
      }

   }

   public Set getResourceDomains() {
      return this.setResourceDomains;
   }

   public IResource getResource(ResourceLocation var1) {
      IResourceManager var2 = (IResourceManager)this.domainResourceManagers.get(var1.getResourceDomain());
      if(var2 != null) {
         return var2.getResource(var1);
      } else {
         throw new FileNotFoundException(var1.toString());
      }
   }

   public List getAllResources(ResourceLocation var1) {
      IResourceManager var2 = (IResourceManager)this.domainResourceManagers.get(var1.getResourceDomain());
      if(var2 != null) {
         return var2.getAllResources(var1);
      } else {
         throw new FileNotFoundException(var1.toString());
      }
   }

   private void clearResources() {
      this.domainResourceManagers.clear();
      this.setResourceDomains.clear();
   }

   public void reloadResources(List var1) {
      this.clearResources();
      logger.info("Reloading ResourceManager: " + joinerResourcePacks.join(Iterables.transform(var1, new SimpleReloadableResourceManager$1(this))));
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         IResourcePack var3 = (IResourcePack)var2.next();
         this.reloadResourcePack(var3);
      }

      this.notifyReloadListeners();
   }

   public void registerReloadListener(IResourceManagerReloadListener var1) {
      this.reloadListeners.add(var1);
      var1.onResourceManagerReload(this);
   }

   private void notifyReloadListeners() {
      Iterator var1 = this.reloadListeners.iterator();

      while(var1.hasNext()) {
         IResourceManagerReloadListener var2 = (IResourceManagerReloadListener)var1.next();
         var2.onResourceManagerReload(this);
      }

   }

}
