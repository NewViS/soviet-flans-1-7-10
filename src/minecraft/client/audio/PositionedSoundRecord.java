package net.minecraft.client.audio;

import net.minecraft.client.audio.ISound$AttenuationType;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.util.ResourceLocation;

public class PositionedSoundRecord extends PositionedSound {

   public static PositionedSoundRecord func_147674_a(ResourceLocation var0, float var1) {
      return new PositionedSoundRecord(var0, 0.25F, var1, false, 0, ISound$AttenuationType.NONE, 0.0F, 0.0F, 0.0F);
   }

   public static PositionedSoundRecord func_147673_a(ResourceLocation var0) {
      return new PositionedSoundRecord(var0, 1.0F, 1.0F, false, 0, ISound$AttenuationType.NONE, 0.0F, 0.0F, 0.0F);
   }

   public static PositionedSoundRecord func_147675_a(ResourceLocation var0, float var1, float var2, float var3) {
      return new PositionedSoundRecord(var0, 4.0F, 1.0F, false, 0, ISound$AttenuationType.LINEAR, var1, var2, var3);
   }

   public PositionedSoundRecord(ResourceLocation var1, float var2, float var3, float var4, float var5, float var6) {
      this(var1, var2, var3, false, 0, ISound$AttenuationType.LINEAR, var4, var5, var6);
   }

   private PositionedSoundRecord(ResourceLocation var1, float var2, float var3, boolean var4, int var5, ISound$AttenuationType var6, float var7, float var8, float var9) {
      super(var1);
      super.volume = var2;
      super.field_147663_c = var3;
      super.xPosF = var7;
      super.yPosF = var8;
      super.zPosF = var9;
      super.repeat = var4;
      super.field_147665_h = var5;
      super.field_147666_i = var6;
   }
}
