package de.ItsAMysterious.mods.reallifemod.core.streets.entitys;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class EntityVehicle extends Entity {

   private static double fuelvalue;
   private static double tankvolume;
   private double minspeed;
   private double maxspeed;
   public double currentspeed;
   public double field_70130_N;
   public double length;
   private boolean isBraking;
   private boolean hasDriver;
   protected double health;
   private double maxhealth;
   protected double acceleration;
   protected double velocityX;
   protected double velocityY;
   protected double velocityZ;
   protected double vehicleYaw;
   protected double vehicleX;
   protected double vehicleY;
   protected double vehicleZ;
   protected double vehiclePitch;
   public static double deltasteer;
   public static double wheelrotation;
   public Vector3f color;


   public EntityVehicle(World world) {
      super(world);
      this.color = new Vector3f(1.0F, 1.0F, 1.0F);
      this.setSpeedMinMax(-0.25D, 0.75D);
      this.health = 100.0D;
      this.field_70159_w = 0.0D;
      this.field_70181_x = 0.0D;
      this.field_70179_y = 0.0D;
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
   }

   public EntityVehicle(World world, double x, double y, double z, float rotationYaw, double width, double length) {
      this(world);
      this.func_70012_b(x, y, z, rotationYaw, 0.0F);
      this.setBounds(width, length);
   }

   public void setBounds(double width, double length) {
      this.field_70130_N = width;
      this.length = length;
   }

   public void setSpeedMinMax(double min, double max) {
      this.minspeed = min;
      this.maxspeed = max;
   }

   public AxisAlignedBB func_70114_g(Entity entity) {
      return AxisAlignedBB.getBoundingBox(-1.0D, 0.0D, 0.0D, 1.0D, 1.0D, 3.0D);
   }

   public AxisAlignedBB func_70046_E() {
      return this.func_70114_g(this);
   }

   public void func_70071_h_() {
      super.onUpdate();
      if(this.getTimeSinceHit() > 0) {
         this.setTimeSinceHit(this.getTimeSinceHit() - 1);
      }

      if(this.getDamageTaken() > 0.0F) {
         this.setDamageTaken(this.getDamageTaken() - 1.0F);
      }

      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      this.field_70121_D.calculateXOffset(this.func_70046_E(), this.field_70159_w);
      this.field_70121_D.calculateYOffset(this.func_70046_E(), this.field_70181_x);
      this.field_70121_D.calculateZOffset(this.func_70046_E(), this.field_70179_y);
      if(!this.field_70170_p.isRemote && this.field_70153_n != null && this.field_70153_n instanceof EntityLivingBase) {
         this.checkInput();
         this.move();
      }

      if(this.field_70170_p.isRemote && Keyboard.isKeyDown(28) && this.field_70170_p.getClosestPlayer(this.field_70165_t, this.field_70163_u, this.field_70161_v, 5.0D) != null) {
         EntityPlayer entity = this.field_70170_p.getClosestPlayer(this.field_70165_t, this.field_70163_u, this.field_70161_v, 5.0D);
         this.func_130002_c(entity);
         this.field_70153_n = entity;
         this.func_85030_a("reallifemod:door_close", 1.0F, 1.0F);
         if(this.field_70153_n != null) {
            entity.func_110145_l(this);
            this.setHasDriver(false);
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
      this.vehicleX = p_70056_1_;
      this.vehicleY = p_70056_3_;
      this.vehicleZ = p_70056_5_;
      this.vehicleYaw = (double)p_70056_7_;
      this.vehiclePitch = (double)p_70056_8_;
      this.field_70159_w = this.velocityX;
      this.field_70181_x = this.velocityY;
      this.field_70179_y = this.velocityZ;
   }

   @SideOnly(Side.CLIENT)
   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.velocityX = (double)((float)(this.field_70159_w = p_70016_1_));
      this.velocityY = (double)((float)(this.field_70181_x = p_70016_3_));
      this.velocityZ = (double)((float)(this.field_70179_y = p_70016_5_));
   }

   private void setHasDriver(boolean b) {
      this.hasDriver = b;
   }

   public void checkInput() {
      if(Keyboard.isKeyDown(17)) {
         this.acceleration = 0.025D;
      } else if(Keyboard.isKeyDown(31)) {
         this.acceleration = -0.025D;
      } else {
         this.acceleration = 0.0D;
      }

      if(Keyboard.isKeyDown(32)) {
         if(deltasteer > -45.0D) {
            --deltasteer;
         }
      } else if(Keyboard.isKeyDown(30)) {
         if(deltasteer < 45.0D) {
            ++deltasteer;
         }
      } else if(deltasteer < 0.0D) {
         ++deltasteer;
      } else if(deltasteer > 0.0D) {
         --deltasteer;
      }

      if(Keyboard.isKeyDown(57)) {
         this.brake();
      }

      if(Keyboard.isKeyDown(35)) {
         this.field_70170_p.playSound(this.field_70165_t, this.field_70163_u, this.field_70161_v, "reallifemod:horn", 1.0F, 1.0F, true);
      }

   }

   public void func_70100_b_(EntityPlayer player) {
      if(player != this.field_70153_n && this.field_70153_n != null) {
         player.func_70606_j((float)((double)player.func_110143_aJ() - this.currentspeed * 5.0D));
      }

   }

   public void func_70108_f(Entity p_70108_1_) {
      if(p_70108_1_.riddenByEntity != this && p_70108_1_.ridingEntity != this) {
         double d0 = p_70108_1_.posX - this.field_70165_t;
         double d1 = p_70108_1_.posZ - this.field_70161_v;
         double d2 = MathHelper.abs_max(d0, d1);
         if(d2 >= 0.009999999776482582D) {
            d2 = (double)MathHelper.sqrt_double(d2);
            d0 /= d2;
            d1 /= d2;
            double d3 = 1.0D / d2;
            if(d3 > 1.0D) {
               d3 = 1.0D;
            }

            d0 *= d3;
            d1 *= d3;
            d0 *= 0.05000000074505806D;
            d1 *= 0.05000000074505806D;
            double var10000 = d0 * (double)(1.0F - this.field_70144_Y);
            var10000 = d1 * (double)(1.0F - this.field_70144_Y);
            this.func_70024_g(0.0D, 0.0D, 0.0D);
            p_70108_1_.addVelocity(0.0D, 0.0D, 0.0D);
         }
      }

   }

   public void move() {
      double R = 0.0D;
      double k = 0.0D;
      if(deltasteer != 0.0D) {
         R = 5.0D / deltasteer;
         k = 1.0D / R;
      }

      wheelrotation += this.currentspeed * 20.0D;
      double angleVelocity = this.currentspeed * k;
      if(this.currentspeed < 0.5D) {
         this.currentspeed += this.acceleration;
      }

      this.field_70177_z = (float)((double)this.field_70177_z + angleVelocity);
      this.field_70159_w = Math.sin((double)this.field_70177_z * 3.141592653589793D / 180.0D) * this.currentspeed;
      this.field_70179_y = Math.cos(Math.toRadians((double)this.field_70177_z)) * this.currentspeed;
      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
   }

   protected void brake() {
      if(this.currentspeed < 0.0D) {
         this.currentspeed += 0.2D;
      } else {
         this.currentspeed = 0.0D;
      }

      if(this.currentspeed > 0.1D) {
         this.currentspeed -= 0.2D;
      } else {
         this.currentspeed = 0.0D;
      }

      this.currentspeed = 0.0D;
   }

   protected void func_70088_a() {
      this.field_70180_af.addObject(17, new Integer(0));
      this.field_70180_af.addObject(18, new Integer(1));
      this.field_70180_af.addObject(19, new Float(0.0F));
   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {}

   protected void func_70014_b(NBTTagCompound p_70014_1_) {}

   public boolean func_130002_c(EntityPlayer player) {
      return true;
   }

   protected void func_70064_a(double p_70064_1_, boolean p_70064_3_) {
      int i = MathHelper.floor_double(this.field_70165_t);
      int j = MathHelper.floor_double(this.field_70163_u);
      int k = MathHelper.floor_double(this.field_70161_v);
      if(p_70064_3_) {
         if(this.field_70143_R > 3.0F) {
            this.func_70069_a(this.field_70143_R);
            if(!this.field_70170_p.isRemote && !this.field_70128_L) {
               this.func_70106_y();

               int l;
               for(l = 0; l < 3; ++l) {
                  ;
               }

               for(l = 0; l < 2; ++l) {
                  ;
               }
            }

            this.field_70143_R = 0.0F;
         }
      } else if(this.field_70170_p.getBlock(i, j - 1, k).getMaterial() != Material.water && p_70064_1_ < 0.0D) {
         this.field_70143_R = (float)((double)this.field_70143_R - p_70064_1_);
      }

   }

   public void func_70043_V() {
      double R = 0.0D;
      double k = 0.0D;
      if(deltasteer != 0.0D) {
         R = 5.0D / deltasteer;
         k = 1.0D / R;
      }

      double z = Math.cos((double)(this.field_70177_z + 16.0F) * 3.141592653589793D / 180.0D) * 2.2D;
      double y = this.field_70163_u + Math.tan((double)this.field_70125_A * 3.141592653589793D / 180.0D) * 3.0D;
      double x = Math.sin((double)(this.field_70177_z + 16.0F) * 3.141592653589793D / 180.0D) * 2.2D;
      this.field_70153_n.setPosition(this.field_70165_t + x, this.field_70163_u + 2.0D, this.field_70161_v + z);
      this.field_70153_n.rotationYaw = (float)((double)this.field_70153_n.rotationYaw - this.currentspeed * k);
      EntityPlayer p = (EntityPlayer)this.field_70153_n;
      p.field_70759_as = (float)((double)p.field_70759_as - this.currentspeed * k);
   }

   public void updateSeatPositions() {}

   public void setDamageTaken(float p_70266_1_) {
      this.field_70180_af.updateObject(19, Float.valueOf(p_70266_1_));
   }

   public float getDamageTaken() {
      return this.field_70180_af.getWatchableObjectFloat(19);
   }

   public void setTimeSinceHit(int p_70265_1_) {
      this.field_70180_af.updateObject(17, Integer.valueOf(p_70265_1_));
   }

   public int getTimeSinceHit() {
      return this.field_70180_af.getWatchableObjectInt(17);
   }

   public void setForwardDirection(int p_70269_1_) {
      this.field_70180_af.updateObject(18, Integer.valueOf(p_70269_1_));
   }

   public int getForwardDirection() {
      return this.field_70180_af.getWatchableObjectInt(18);
   }
}
