package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventAccessor;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler$1;
import net.minecraft.client.audio.SoundHandler$2;
import net.minecraft.client.audio.SoundHandler$SwitchType;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.audio.SoundList$SoundEntry;
import net.minecraft.client.audio.SoundListSerializer;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SoundHandler implements IResourceManagerReloadListener, IUpdatePlayerListBox {

   private static final Logger logger = LogManager.getLogger();
   private static final Gson field_147699_c = (new GsonBuilder()).registerTypeAdapter(SoundList.class, new SoundListSerializer()).create();
   private static final ParameterizedType field_147696_d = new SoundHandler$1();
   public static final SoundPoolEntry missing_sound = new SoundPoolEntry(new ResourceLocation("meta:missing_sound"), 0.0D, 0.0D, false);
   private final SoundRegistry sndRegistry = new SoundRegistry();
   private final SoundManager sndManager;
   private final IResourceManager mcResourceManager;


   public SoundHandler(IResourceManager var1, GameSettings var2) {
      this.mcResourceManager = var1;
      this.sndManager = new SoundManager(this, var2);
   }

   public void onResourceManagerReload(IResourceManager var1) {
      this.sndManager.reloadSoundSystem();
      this.sndRegistry.func_148763_c();
      Iterator var2 = var1.getResourceDomains().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();

         try {
            List var4 = var1.getAllResources(new ResourceLocation(var3, "sounds.json"));
            Iterator var5 = var4.iterator();

            while(var5.hasNext()) {
               IResource var6 = (IResource)var5.next();

               try {
                  Map var7 = (Map)field_147699_c.fromJson(new InputStreamReader(var6.getInputStream()), field_147696_d);
                  Iterator var8 = var7.entrySet().iterator();

                  while(var8.hasNext()) {
                     Entry var9 = (Entry)var8.next();
                     this.loadSoundResource(new ResourceLocation(var3, (String)var9.getKey()), (SoundList)var9.getValue());
                  }
               } catch (RuntimeException var10) {
                  logger.warn("Invalid sounds.json", var10);
               }
            }
         } catch (IOException var11) {
            ;
         }
      }

   }

   private void loadSoundResource(ResourceLocation var1, SoundList var2) {
      SoundEventAccessorComposite var3;
      if(this.sndRegistry.containsKey(var1) && !var2.canReplaceExisting()) {
         var3 = (SoundEventAccessorComposite)this.sndRegistry.getObject(var1);
      } else {
         logger.debug("Registered/replaced new sound event location {}", new Object[]{var1});
         var3 = new SoundEventAccessorComposite(var1, 1.0D, 1.0D, var2.getSoundCategory());
         this.sndRegistry.registerSound(var3);
      }

      Iterator var4 = var2.getSoundList().iterator();

      while(var4.hasNext()) {
         SoundList$SoundEntry var5 = (SoundList$SoundEntry)var4.next();
         String var6 = var5.getSoundEntryName();
         ResourceLocation var7 = new ResourceLocation(var6);
         String var8 = var6.contains(":")?var7.getResourceDomain():var1.getResourceDomain();
         Object var9;
         switch(SoundHandler$SwitchType.field_148765_a[var5.getSoundEntryType().ordinal()]) {
         case 1:
            ResourceLocation var10 = new ResourceLocation(var8, "sounds/" + var7.getResourcePath() + ".ogg");

            try {
               this.mcResourceManager.getResource(var10);
            } catch (FileNotFoundException var12) {
               logger.warn("File {} does not exist, cannot add it to event {}", new Object[]{var10, var1});
               continue;
            } catch (IOException var13) {
               logger.warn("Could not load sound file " + var10 + ", cannot add it to event " + var1, var13);
               continue;
            }

            var9 = new SoundEventAccessor(new SoundPoolEntry(var10, (double)var5.getSoundEntryPitch(), (double)var5.getSoundEntryVolume(), var5.isStreaming()), var5.getSoundEntryWeight());
            break;
         case 2:
            var9 = new SoundHandler$2(this, var8, var5);
            break;
         default:
            throw new IllegalStateException("IN YOU FACE");
         }

         var3.addSoundToEventPool((ISoundEventAccessor)var9);
      }

   }

   public SoundEventAccessorComposite getSound(ResourceLocation var1) {
      return (SoundEventAccessorComposite)this.sndRegistry.getObject(var1);
   }

   public void playSound(ISound var1) {
      this.sndManager.playSound(var1);
   }

   public void playDelayedSound(ISound var1, int var2) {
      this.sndManager.addDelayedSound(var1, var2);
   }

   public void setListener(EntityPlayer var1, float var2) {
      this.sndManager.setListener(var1, var2);
   }

   public void pauseSounds() {
      this.sndManager.pauseAllSounds();
   }

   public void stopSounds() {
      this.sndManager.stopAllSounds();
   }

   public void unloadSounds() {
      this.sndManager.unloadSoundSystem();
   }

   public void update() {
      this.sndManager.updateAllSounds();
   }

   public void resumeSounds() {
      this.sndManager.resumeAllSounds();
   }

   public void setSoundLevel(SoundCategory var1, float var2) {
      if(var1 == SoundCategory.MASTER && var2 <= 0.0F) {
         this.stopSounds();
      }

      this.sndManager.setSoundCategoryVolume(var1, var2);
   }

   public void stopSound(ISound var1) {
      this.sndManager.stopSound(var1);
   }

   public SoundEventAccessorComposite getRandomSoundFromCategories(SoundCategory ... var1) {
      ArrayList var2 = Lists.newArrayList();
      Iterator var3 = this.sndRegistry.getKeys().iterator();

      while(var3.hasNext()) {
         ResourceLocation var4 = (ResourceLocation)var3.next();
         SoundEventAccessorComposite var5 = (SoundEventAccessorComposite)this.sndRegistry.getObject(var4);
         if(ArrayUtils.contains(var1, var5.getSoundCategory())) {
            var2.add(var5);
         }
      }

      if(var2.isEmpty()) {
         return null;
      } else {
         return (SoundEventAccessorComposite)var2.get((new Random()).nextInt(var2.size()));
      }
   }

   public boolean isSoundPlaying(ISound var1) {
      return this.sndManager.isSoundPlaying(var1);
   }

   // $FF: synthetic method
   static SoundRegistry access$000(SoundHandler var0) {
      return var0.sndRegistry;
   }

}
