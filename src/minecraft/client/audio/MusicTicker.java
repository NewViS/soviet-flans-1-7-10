package net.minecraft.client.audio;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker$MusicType;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.MathHelper;

public class MusicTicker implements IUpdatePlayerListBox {

   private final Random field_147679_a = new Random();
   private final Minecraft field_147677_b;
   private ISound field_147678_c;
   private int field_147676_d = 100;


   public MusicTicker(Minecraft var1) {
      this.field_147677_b = var1;
   }

   public void update() {
      MusicTicker$MusicType var1 = this.field_147677_b.func_147109_W();
      if(this.field_147678_c != null) {
         if(!var1.getMusicTickerLocation().equals(this.field_147678_c.getPositionedSoundLocation())) {
            this.field_147677_b.getSoundHandler().stopSound(this.field_147678_c);
            this.field_147676_d = MathHelper.getRandomIntegerInRange(this.field_147679_a, 0, var1.func_148634_b() / 2);
         }

         if(!this.field_147677_b.getSoundHandler().isSoundPlaying(this.field_147678_c)) {
            this.field_147678_c = null;
            this.field_147676_d = Math.min(MathHelper.getRandomIntegerInRange(this.field_147679_a, var1.func_148634_b(), var1.func_148633_c()), this.field_147676_d);
         }
      }

      if(this.field_147678_c == null && this.field_147676_d-- <= 0) {
         this.field_147678_c = PositionedSoundRecord.func_147673_a(var1.getMusicTickerLocation());
         this.field_147677_b.getSoundHandler().playSound(this.field_147678_c);
         this.field_147676_d = Integer.MAX_VALUE;
      }

   }
}
