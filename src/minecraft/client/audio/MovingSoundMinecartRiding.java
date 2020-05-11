package net.minecraft.client.audio;

import net.minecraft.client.audio.ISound$AttenuationType;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class MovingSoundMinecartRiding extends MovingSound {

   private final EntityPlayer field_147672_k;
   private final EntityMinecart field_147671_l;


   public MovingSoundMinecartRiding(EntityPlayer var1, EntityMinecart var2) {
      super(new ResourceLocation("minecraft:minecart.inside"));
      this.field_147672_k = var1;
      this.field_147671_l = var2;
      super.field_147666_i = ISound$AttenuationType.NONE;
      super.repeat = true;
      super.field_147665_h = 0;
   }

   public void update() {
      if(!this.field_147671_l.isDead && this.field_147672_k.isRiding() && this.field_147672_k.ridingEntity == this.field_147671_l) {
         float var1 = MathHelper.sqrt_double(this.field_147671_l.motionX * this.field_147671_l.motionX + this.field_147671_l.motionZ * this.field_147671_l.motionZ);
         if((double)var1 >= 0.01D) {
            super.volume = 0.0F + MathHelper.clamp_float(var1, 0.0F, 1.0F) * 0.75F;
         } else {
            super.volume = 0.0F;
         }

      } else {
         super.donePlaying = true;
      }
   }
}
