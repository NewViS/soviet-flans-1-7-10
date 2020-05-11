package net.minecraft.client.audio;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.util.RegistrySimple;

public class SoundRegistry extends RegistrySimple {

   private Map field_148764_a;


   protected Map createUnderlyingMap() {
      this.field_148764_a = Maps.newHashMap();
      return this.field_148764_a;
   }

   public void registerSound(SoundEventAccessorComposite var1) {
      this.putObject(var1.getSoundEventLocation(), var1);
   }

   public void func_148763_c() {
      this.field_148764_a.clear();
   }
}
