package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.audio.ISoundEventAccessor;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.util.ResourceLocation;

public class SoundEventAccessorComposite implements ISoundEventAccessor {

   private final List soundPool = Lists.newArrayList();
   private final Random rnd = new Random();
   private final ResourceLocation field_148735_c;
   private final SoundCategory field_148732_d;
   private double eventPitch;
   private double eventVolume;


   public SoundEventAccessorComposite(ResourceLocation var1, double var2, double var4, SoundCategory var6) {
      this.field_148735_c = var1;
      this.eventVolume = var4;
      this.eventPitch = var2;
      this.field_148732_d = var6;
   }

   public int func_148721_a() {
      int var1 = 0;

      ISoundEventAccessor var3;
      for(Iterator var2 = this.soundPool.iterator(); var2.hasNext(); var1 += var3.func_148721_a()) {
         var3 = (ISoundEventAccessor)var2.next();
      }

      return var1;
   }

   public SoundPoolEntry func_148720_g() {
      int var1 = this.func_148721_a();
      if(!this.soundPool.isEmpty() && var1 != 0) {
         int var2 = this.rnd.nextInt(var1);
         Iterator var3 = this.soundPool.iterator();

         ISoundEventAccessor var4;
         do {
            if(!var3.hasNext()) {
               return SoundHandler.missing_sound;
            }

            var4 = (ISoundEventAccessor)var3.next();
            var2 -= var4.func_148721_a();
         } while(var2 >= 0);

         SoundPoolEntry var5 = (SoundPoolEntry)var4.func_148720_g();
         var5.setPitch(var5.getPitch() * this.eventPitch);
         var5.setVolume(var5.getVolume() * this.eventVolume);
         return var5;
      } else {
         return SoundHandler.missing_sound;
      }
   }

   public void addSoundToEventPool(ISoundEventAccessor var1) {
      this.soundPool.add(var1);
   }

   public ResourceLocation getSoundEventLocation() {
      return this.field_148735_c;
   }

   public SoundCategory getSoundCategory() {
      return this.field_148732_d;
   }

   // $FF: synthetic method
   public Object func_148720_g() {
      return this.func_148720_g();
   }
}
