package net.minecraft.client.audio;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class MovingSoundMinecart extends MovingSound {

   private final EntityMinecart field_147670_k;
   private float field_147669_l = 0.0F;


   public MovingSoundMinecart(EntityMinecart var1) {
      super(new ResourceLocation("minecraft:minecart.base"));
      this.field_147670_k = var1;
      super.repeat = true;
      super.field_147665_h = 0;
   }

   public void update() {
      if(this.field_147670_k.isDead) {
         super.donePlaying = true;
      } else {
         super.xPosF = (float)this.field_147670_k.posX;
         super.yPosF = (float)this.field_147670_k.posY;
         super.zPosF = (float)this.field_147670_k.posZ;
         float var1 = MathHelper.sqrt_double(this.field_147670_k.motionX * this.field_147670_k.motionX + this.field_147670_k.motionZ * this.field_147670_k.motionZ);
         if((double)var1 >= 0.01D) {
            this.field_147669_l = MathHelper.clamp_float(this.field_147669_l + 0.0025F, 0.0F, 1.0F);
            super.volume = 0.0F + MathHelper.clamp_float(var1, 0.0F, 0.5F) * 0.7F;
         } else {
            this.field_147669_l = 0.0F;
            super.volume = 0.0F;
         }

      }
   }
}
