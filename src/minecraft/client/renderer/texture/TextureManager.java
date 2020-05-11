package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager$1;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextureManager implements ITickable, IResourceManagerReloadListener {

   private static final Logger logger = LogManager.getLogger();
   private final Map mapTextureObjects = Maps.newHashMap();
   private final Map mapResourceLocations = Maps.newHashMap();
   private final List listTickables = Lists.newArrayList();
   private final Map mapTextureCounters = Maps.newHashMap();
   private IResourceManager theResourceManager;


   public TextureManager(IResourceManager var1) {
      this.theResourceManager = var1;
   }

   public void bindTexture(ResourceLocation var1) {
      Object var2 = (ITextureObject)this.mapTextureObjects.get(var1);
      if(var2 == null) {
         var2 = new SimpleTexture(var1);
         this.loadTexture(var1, (ITextureObject)var2);
      }

      TextureUtil.bindTexture(((ITextureObject)var2).getGlTextureId());
   }

   public ResourceLocation getResourceLocation(int var1) {
      return (ResourceLocation)this.mapResourceLocations.get(Integer.valueOf(var1));
   }

   public boolean loadTextureMap(ResourceLocation var1, TextureMap var2) {
      if(this.loadTickableTexture(var1, var2)) {
         this.mapResourceLocations.put(Integer.valueOf(var2.getTextureType()), var1);
         return true;
      } else {
         return false;
      }
   }

   public boolean loadTickableTexture(ResourceLocation var1, ITickableTextureObject var2) {
      if(this.loadTexture(var1, var2)) {
         this.listTickables.add(var2);
         return true;
      } else {
         return false;
      }
   }

   public boolean loadTexture(ResourceLocation var1, ITextureObject var2) {
      boolean var3 = true;

      try {
         ((ITextureObject)var2).loadTexture(this.theResourceManager);
      } catch (IOException var8) {
         logger.warn("Failed to load texture: " + var1, var8);
         var2 = TextureUtil.missingTexture;
         this.mapTextureObjects.put(var1, var2);
         var3 = false;
      } catch (Throwable var9) {
         CrashReport var5 = CrashReport.makeCrashReport(var9, "Registering texture");
         CrashReportCategory var6 = var5.makeCategory("Resource location being registered");
         var6.addCrashSection("Resource location", var1);
         var6.addCrashSectionCallable("Texture object class", new TextureManager$1(this, (ITextureObject)var2));
         throw new ReportedException(var5);
      }

      this.mapTextureObjects.put(var1, var2);
      return var3;
   }

   public ITextureObject getTexture(ResourceLocation var1) {
      return (ITextureObject)this.mapTextureObjects.get(var1);
   }

   public ResourceLocation getDynamicTextureLocation(String var1, DynamicTexture var2) {
      Integer var3 = (Integer)this.mapTextureCounters.get(var1);
      if(var3 == null) {
         var3 = Integer.valueOf(1);
      } else {
         var3 = Integer.valueOf(var3.intValue() + 1);
      }

      this.mapTextureCounters.put(var1, var3);
      ResourceLocation var4 = new ResourceLocation(String.format("dynamic/%s_%d", new Object[]{var1, var3}));
      this.loadTexture(var4, var2);
      return var4;
   }

   public void tick() {
      Iterator var1 = this.listTickables.iterator();

      while(var1.hasNext()) {
         ITickable var2 = (ITickable)var1.next();
         var2.tick();
      }

   }

   public void deleteTexture(ResourceLocation var1) {
      ITextureObject var2 = this.getTexture(var1);
      if(var2 != null) {
         TextureUtil.deleteTexture(var2.getGlTextureId());
      }

   }

   public void onResourceManagerReload(IResourceManager var1) {
      Iterator var2 = this.mapTextureObjects.entrySet().iterator();

      while(var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         this.loadTexture((ResourceLocation)var3.getKey(), (ITextureObject)var3.getValue());
      }

   }

}
