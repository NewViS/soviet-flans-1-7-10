package net.minecraft.client.gui;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;

class GuiOptions$1 extends GuiButton {

   // $FF: synthetic field
   final GuiOptions field_146130_o;


   GuiOptions$1(GuiOptions var1, int var2, int var3, int var4, int var5, int var6, String var7) {
      super(var2, var3, var4, var5, var6, var7);
      this.field_146130_o = var1;
   }

   public void func_146113_a(SoundHandler var1) {
      SoundEventAccessorComposite var2 = var1.getRandomSoundFromCategories(new SoundCategory[]{SoundCategory.ANIMALS, SoundCategory.BLOCKS, SoundCategory.MOBS, SoundCategory.PLAYERS, SoundCategory.WEATHER});
      if(var2 != null) {
         var1.playSound(PositionedSoundRecord.func_147674_a(var2.getSoundEventLocation(), 0.5F));
      }

   }
}
