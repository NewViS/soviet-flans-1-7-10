package net.minecraft.block;


public class Block$SoundType {

   public final String soundName;
   public final float volume;
   public final float frequency;


   public Block$SoundType(String var1, float var2, float var3) {
      this.soundName = var1;
      this.volume = var2;
      this.frequency = var3;
   }

   public float getVolume() {
      return this.volume;
   }

   public float getPitch() {
      return this.frequency;
   }

   public String getBreakSound() {
      return "dig." + this.soundName;
   }

   public String getStepResourcePath() {
      return "step." + this.soundName;
   }

   public String func_150496_b() {
      return this.getBreakSound();
   }
}
