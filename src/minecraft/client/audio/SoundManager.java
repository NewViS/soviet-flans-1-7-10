package net.minecraft.client.audio;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager$1;
import net.minecraft.client.audio.SoundManager$2;
import net.minecraft.client.audio.SoundManager$SoundSystemStarterThread;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public class SoundManager {

   private static final Marker field_148623_a = MarkerManager.getMarker("SOUNDS");
   private static final Logger logger = LogManager.getLogger();
   private final SoundHandler sndHandler;
   private final GameSettings options;
   private SoundManager$SoundSystemStarterThread sndSystem;
   private boolean loaded;
   private int playTime = 0;
   private final Map playingSounds = HashBiMap.create();
   private final Map invPlayingSounds;
   private Map playingSoundPoolEntries;
   private final Multimap categorySounds;
   private final List tickableSounds;
   private final Map delayedSounds;
   private final Map playingSoundsStopTime;


   public SoundManager(SoundHandler var1, GameSettings var2) {
      this.invPlayingSounds = ((BiMap)this.playingSounds).inverse();
      this.playingSoundPoolEntries = Maps.newHashMap();
      this.categorySounds = HashMultimap.create();
      this.tickableSounds = Lists.newArrayList();
      this.delayedSounds = Maps.newHashMap();
      this.playingSoundsStopTime = Maps.newHashMap();
      this.sndHandler = var1;
      this.options = var2;

      try {
         SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
         SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
      } catch (SoundSystemException var4) {
         logger.error(field_148623_a, "Error linking with the LibraryJavaSound plug-in", var4);
      }

   }

   public void reloadSoundSystem() {
      this.unloadSoundSystem();
      this.loadSoundSystem();
   }

   private synchronized void loadSoundSystem() {
      if(!this.loaded) {
         try {
            (new Thread(new SoundManager$1(this), "Sound Library Loader")).start();
         } catch (RuntimeException var2) {
            logger.error(field_148623_a, "Error starting SoundSystem. Turning off sounds & music", var2);
            this.options.setSoundLevel(SoundCategory.MASTER, 0.0F);
            this.options.saveOptions();
         }

      }
   }

   private float getSoundCategoryVolume(SoundCategory var1) {
      return var1 != null && var1 != SoundCategory.MASTER?this.options.getSoundLevel(var1):1.0F;
   }

   public void setSoundCategoryVolume(SoundCategory var1, float var2) {
      if(this.loaded) {
         if(var1 == SoundCategory.MASTER) {
            this.sndSystem.setMasterVolume(var2);
         } else {
            Iterator var3 = this.categorySounds.get(var1).iterator();

            while(var3.hasNext()) {
               String var4 = (String)var3.next();
               ISound var5 = (ISound)this.playingSounds.get(var4);
               float var6 = this.getNormalizedVolume(var5, (SoundPoolEntry)this.playingSoundPoolEntries.get(var5), var1);
               if(var6 <= 0.0F) {
                  this.stopSound(var5);
               } else {
                  this.sndSystem.setVolume(var4, var6);
               }
            }

         }
      }
   }

   public void unloadSoundSystem() {
      if(this.loaded) {
         this.stopAllSounds();
         this.sndSystem.cleanup();
         this.loaded = false;
      }

   }

   public void stopAllSounds() {
      if(this.loaded) {
         Iterator var1 = this.playingSounds.keySet().iterator();

         while(var1.hasNext()) {
            String var2 = (String)var1.next();
            this.sndSystem.stop(var2);
         }

         this.playingSounds.clear();
         this.delayedSounds.clear();
         this.tickableSounds.clear();
         this.categorySounds.clear();
         this.playingSoundPoolEntries.clear();
         this.playingSoundsStopTime.clear();
      }

   }

   public void updateAllSounds() {
      ++this.playTime;
      Iterator var1 = this.tickableSounds.iterator();

      String var3;
      while(var1.hasNext()) {
         ITickableSound var2 = (ITickableSound)var1.next();
         var2.update();
         if(var2.isDonePlaying()) {
            this.stopSound(var2);
         } else {
            var3 = (String)this.invPlayingSounds.get(var2);
            this.sndSystem.setVolume(var3, this.getNormalizedVolume(var2, (SoundPoolEntry)this.playingSoundPoolEntries.get(var2), this.sndHandler.getSound(var2.getPositionedSoundLocation()).getSoundCategory()));
            this.sndSystem.setPitch(var3, this.getNormalizedPitch(var2, (SoundPoolEntry)this.playingSoundPoolEntries.get(var2)));
            this.sndSystem.setPosition(var3, var2.getXPosF(), var2.getYPosF(), var2.getZPosF());
         }
      }

      var1 = this.playingSounds.entrySet().iterator();

      ISound var4;
      while(var1.hasNext()) {
         Entry var9 = (Entry)var1.next();
         var3 = (String)var9.getKey();
         var4 = (ISound)var9.getValue();
         if(!this.sndSystem.playing(var3)) {
            int var5 = ((Integer)this.playingSoundsStopTime.get(var3)).intValue();
            if(var5 <= this.playTime) {
               int var6 = var4.getRepeatDelay();
               if(var4.canRepeat() && var6 > 0) {
                  this.delayedSounds.put(var4, Integer.valueOf(this.playTime + var6));
               }

               var1.remove();
               logger.debug(field_148623_a, "Removed channel {} because it\'s not playing anymore", new Object[]{var3});
               this.sndSystem.removeSource(var3);
               this.playingSoundsStopTime.remove(var3);
               this.playingSoundPoolEntries.remove(var4);

               try {
                  this.categorySounds.remove(this.sndHandler.getSound(var4.getPositionedSoundLocation()).getSoundCategory(), var3);
               } catch (RuntimeException var8) {
                  ;
               }

               if(var4 instanceof ITickableSound) {
                  this.tickableSounds.remove(var4);
               }
            }
         }
      }

      Iterator var10 = this.delayedSounds.entrySet().iterator();

      while(var10.hasNext()) {
         Entry var11 = (Entry)var10.next();
         if(this.playTime >= ((Integer)var11.getValue()).intValue()) {
            var4 = (ISound)var11.getKey();
            if(var4 instanceof ITickableSound) {
               ((ITickableSound)var4).update();
            }

            this.playSound(var4);
            var10.remove();
         }
      }

   }

   public boolean isSoundPlaying(ISound var1) {
      if(!this.loaded) {
         return false;
      } else {
         String var2 = (String)this.invPlayingSounds.get(var1);
         return var2 == null?false:this.sndSystem.playing(var2) || this.playingSoundsStopTime.containsKey(var2) && ((Integer)this.playingSoundsStopTime.get(var2)).intValue() <= this.playTime;
      }
   }

   public void stopSound(ISound var1) {
      if(this.loaded) {
         String var2 = (String)this.invPlayingSounds.get(var1);
         if(var2 != null) {
            this.sndSystem.stop(var2);
         }

      }
   }

   public void playSound(ISound var1) {
      if(this.loaded) {
         if(this.sndSystem.getMasterVolume() <= 0.0F) {
            logger.debug(field_148623_a, "Skipped playing soundEvent: {}, master volume was zero", new Object[]{var1.getPositionedSoundLocation()});
         } else {
            SoundEventAccessorComposite var2 = this.sndHandler.getSound(var1.getPositionedSoundLocation());
            if(var2 == null) {
               logger.warn(field_148623_a, "Unable to play unknown soundEvent: {}", new Object[]{var1.getPositionedSoundLocation()});
            } else {
               SoundPoolEntry var3 = var2.func_148720_g();
               if(var3 == SoundHandler.missing_sound) {
                  logger.warn(field_148623_a, "Unable to play empty soundEvent: {}", new Object[]{var2.getSoundEventLocation()});
               } else {
                  float var4 = var1.getVolume();
                  float var5 = 16.0F;
                  if(var4 > 1.0F) {
                     var5 *= var4;
                  }

                  SoundCategory var6 = var2.getSoundCategory();
                  float var7 = this.getNormalizedVolume(var1, var3, var6);
                  double var8 = (double)this.getNormalizedPitch(var1, var3);
                  ResourceLocation var10 = var3.getSoundPoolEntryLocation();
                  if(var7 == 0.0F) {
                     logger.debug(field_148623_a, "Skipped playing sound {}, volume was zero.", new Object[]{var10});
                  } else {
                     boolean var11 = var1.canRepeat() && var1.getRepeatDelay() == 0;
                     String var12 = UUID.randomUUID().toString();
                     if(var3.func_148648_d()) {
                        this.sndSystem.newStreamingSource(false, var12, getURLForSoundResource(var10), var10.toString(), var11, var1.getXPosF(), var1.getYPosF(), var1.getZPosF(), var1.getAttenuationType().getTypeInt(), var5);
                     } else {
                        this.sndSystem.newSource(false, var12, getURLForSoundResource(var10), var10.toString(), var11, var1.getXPosF(), var1.getYPosF(), var1.getZPosF(), var1.getAttenuationType().getTypeInt(), var5);
                     }

                     logger.debug(field_148623_a, "Playing sound {} for event {} as channel {}", new Object[]{var3.getSoundPoolEntryLocation(), var2.getSoundEventLocation(), var12});
                     this.sndSystem.setPitch(var12, (float)var8);
                     this.sndSystem.setVolume(var12, var7);
                     this.sndSystem.play(var12);
                     this.playingSoundsStopTime.put(var12, Integer.valueOf(this.playTime + 20));
                     this.playingSounds.put(var12, var1);
                     this.playingSoundPoolEntries.put(var1, var3);
                     if(var6 != SoundCategory.MASTER) {
                        this.categorySounds.put(var6, var12);
                     }

                     if(var1 instanceof ITickableSound) {
                        this.tickableSounds.add((ITickableSound)var1);
                     }

                  }
               }
            }
         }
      }
   }

   private float getNormalizedPitch(ISound var1, SoundPoolEntry var2) {
      return (float)MathHelper.clamp_double((double)var1.getPitch() * var2.getPitch(), 0.5D, 2.0D);
   }

   private float getNormalizedVolume(ISound var1, SoundPoolEntry var2, SoundCategory var3) {
      return (float)MathHelper.clamp_double((double)var1.getVolume() * var2.getVolume() * (double)this.getSoundCategoryVolume(var3), 0.0D, 1.0D);
   }

   public void pauseAllSounds() {
      Iterator var1 = this.playingSounds.keySet().iterator();

      while(var1.hasNext()) {
         String var2 = (String)var1.next();
         logger.debug(field_148623_a, "Pausing channel {}", new Object[]{var2});
         this.sndSystem.pause(var2);
      }

   }

   public void resumeAllSounds() {
      Iterator var1 = this.playingSounds.keySet().iterator();

      while(var1.hasNext()) {
         String var2 = (String)var1.next();
         logger.debug(field_148623_a, "Resuming channel {}", new Object[]{var2});
         this.sndSystem.play(var2);
      }

   }

   public void addDelayedSound(ISound var1, int var2) {
      this.delayedSounds.put(var1, Integer.valueOf(this.playTime + var2));
   }

   private static URL getURLForSoundResource(ResourceLocation var0) {
      String var1 = String.format("%s:%s:%s", new Object[]{"mcsounddomain", var0.getResourceDomain(), var0.getResourcePath()});
      SoundManager$2 var2 = new SoundManager$2(var0);

      try {
         return new URL((URL)null, var1, var2);
      } catch (MalformedURLException var4) {
         throw new Error("TODO: Sanely handle url exception! :D");
      }
   }

   public void setListener(EntityPlayer var1, float var2) {
      if(this.loaded && var1 != null) {
         float var3 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var2;
         float var4 = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var2;
         double var5 = var1.prevPosX + (var1.posX - var1.prevPosX) * (double)var2;
         double var7 = var1.prevPosY + (var1.posY - var1.prevPosY) * (double)var2;
         double var9 = var1.prevPosZ + (var1.posZ - var1.prevPosZ) * (double)var2;
         float var11 = MathHelper.cos((var4 + 90.0F) * 0.017453292F);
         float var12 = MathHelper.sin((var4 + 90.0F) * 0.017453292F);
         float var13 = MathHelper.cos(-var3 * 0.017453292F);
         float var14 = MathHelper.sin(-var3 * 0.017453292F);
         float var15 = MathHelper.cos((-var3 + 90.0F) * 0.017453292F);
         float var16 = MathHelper.sin((-var3 + 90.0F) * 0.017453292F);
         float var17 = var11 * var13;
         float var19 = var12 * var13;
         float var20 = var11 * var15;
         float var22 = var12 * var15;
         this.sndSystem.setListenerPosition((float)var5, (float)var7, (float)var9);
         this.sndSystem.setListenerOrientation(var17, var14, var19, var20, var16, var22);
      }
   }

   // $FF: synthetic method
   static SoundManager$SoundSystemStarterThread access$002(SoundManager var0, SoundManager$SoundSystemStarterThread var1) {
      return var0.sndSystem = var1;
   }

   // $FF: synthetic method
   static boolean access$202(SoundManager var0, boolean var1) {
      return var0.loaded = var1;
   }

   // $FF: synthetic method
   static GameSettings access$300(SoundManager var0) {
      return var0.options;
   }

   // $FF: synthetic method
   static SoundManager$SoundSystemStarterThread access$000(SoundManager var0) {
      return var0.sndSystem;
   }

   // $FF: synthetic method
   static Marker access$400() {
      return field_148623_a;
   }

   // $FF: synthetic method
   static Logger access$500() {
      return logger;
   }

}
