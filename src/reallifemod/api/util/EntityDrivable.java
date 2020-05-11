package de.ItsAMysterious.mods.reallifemod.api.util;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDrivable extends Entity {

   protected float speed;


   public EntityDrivable(World p_i1582_1_) {
      super(p_i1582_1_);
   }

   public void moveEntityWithHeading(double motionX, double motionZ) {
      float d1 = 0.91F;
      if(this.field_70122_E) {
         d1 = this.field_70170_p.getBlock(MathHelper.floor_double(this.field_70165_t), MathHelper.floor_double(this.field_70121_D.minY) - 1, MathHelper.floor_double(this.field_70161_v)).slipperiness * 0.91F;
      }

      float f3 = 0.16277136F / (d1 * d1 * d1);
      if(this.field_70122_E) {
         float var10000 = this.speed * f3;
      }

      if(this.field_70122_E) {
         d1 = this.field_70170_p.getBlock(MathHelper.floor_double(this.field_70165_t), MathHelper.floor_double(this.field_70121_D.minY) - 1, MathHelper.floor_double(this.field_70161_v)).slipperiness * 0.91F;
      }

      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      if(this.field_70170_p.isRemote && (!this.field_70170_p.blockExists((int)this.field_70165_t, 0, (int)this.field_70161_v) || !this.field_70170_p.getChunkFromBlockCoords((int)this.field_70165_t, (int)this.field_70161_v).isChunkLoaded)) {
         if(this.field_70163_u > 0.0D) {
            this.field_70181_x = -0.1D;
         } else {
            this.field_70181_x = 0.0D;
         }
      } else {
         this.field_70181_x -= 0.08D;
      }

      this.field_70181_x *= 0.9800000190734863D;
      this.field_70159_w *= (double)d1;
      this.field_70179_y *= (double)d1;
      double d0 = this.field_70165_t - this.field_70169_q;
      double d11 = this.field_70161_v - this.field_70166_s;
      float f6 = MathHelper.sqrt_double(d0 * d0 + d11 * d11) * 4.0F;
   }

   protected void func_70088_a() {}

   protected void func_70037_a(NBTTagCompound p_70037_1_) {}

   protected void func_70014_b(NBTTagCompound p_70014_1_) {}
}
