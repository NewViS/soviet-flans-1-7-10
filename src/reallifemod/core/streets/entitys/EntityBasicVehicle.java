package de.ItsAMysterious.mods.reallifemod.core.streets.entitys;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class EntityBasicVehicle extends Entity {

   public double speed;
   protected double mFzg;
   protected double mZu;
   public float deltaSteer;
   public double wheelRotation;
   protected float vehicleLength;
   protected double field_70130_N;
   protected double field_70131_O;
   protected boolean isEmpty;
   protected boolean shoulddrive;
   protected float R;
   protected float k;
   public double Damage;
   protected static final double RollWKoef = 0.066D;
   protected static final double g = 0.49050000000000005D;


   public EntityBasicVehicle(World world) {
      super(world);
      this.mZu = 0.0D;
      this.field_70156_m = true;
      this.field_70158_ak = true;
      this.isEmpty = true;
      this.Damage = 0.0D;
      this.shoulddrive = true;
   }

   public EntityBasicVehicle(World world, double x, double y, double z, float TheVehicleLength, float mass) {
      this(world);
      this.func_70012_b(x, y, z, 0.0F, 0.0F);
      this.func_70101_b(0.0F, 0.0F);
      this.mFzg = (double)mass;
      this.vehicleLength = TheVehicleLength;
      this.field_70169_q = x;
      this.field_70167_r = y;
      this.field_70166_s = z;
      this.field_70159_w = 0.0D;
      this.field_70181_x = 0.0D;
      this.field_70179_y = 0.0D;
   }

   protected void func_70088_a() {
      this.field_70180_af.addObject(17, new Integer(0));
      this.field_70180_af.addObject(18, new Integer(1));
      this.field_70180_af.addObject(19, new Float(0.0F));
   }

   public AxisAlignedBB func_70114_g(Entity entity) {
      return entity.boundingBox;
   }

   public AxisAlignedBB func_70046_E() {
      return this.field_70121_D;
   }

   public boolean func_70104_M() {
      return false;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if(this.func_85032_ar()) {
         return false;
      } else if(!this.field_70170_p.isRemote && !this.field_70128_L) {
         this.setForwardDirection(-this.getForwardDirection());
         this.setTimeSinceHit(10);
         this.setDamageTaken(this.getDamageTaken() + p_70097_2_ * 10.0F);
         this.func_70018_K();
         boolean flag = p_70097_1_.getEntity() instanceof EntityPlayer && ((EntityPlayer)p_70097_1_.getEntity()).capabilities.isCreativeMode;
         if(flag || this.getDamageTaken() > 40.0F) {
            if(this.field_70153_n != null) {
               this.field_70153_n.mountEntity(this);
            }

            this.func_70106_y();
         }

         return true;
      } else {
         return true;
      }
   }

   public void func_70071_h_() {
      super.onUpdate();
      if(this.getTimeSinceHit() > 0) {
         this.setTimeSinceHit(this.getTimeSinceHit() - 1);
      }

      if(this.getDamageTaken() > 0.0F) {
         this.setDamageTaken(this.getDamageTaken() - 1.0F);
      }

      if(this.field_70170_p.isRemote && (this.field_70170_p.blockExists((int)this.field_70165_t, 0, (int)this.field_70161_v) || !this.field_70170_p.getChunkFromBlockCoords((int)this.field_70165_t, (int)this.field_70161_v).isChunkLoaded)) {
         if(this.field_70170_p.blockExists((int)this.field_70165_t, 1, (int)this.field_70161_v)) {
            this.field_70181_x += 0.1D;
         }

         if(this.field_70163_u > 0.0D) {
            this.field_70181_x = -0.1D;
         } else {
            this.field_70181_x = 0.0D;
         }

         this.field_70181_x *= 0.9800000190734863D;
      } else {
         this.field_70181_x = 0.0D;
      }

      if(this.field_70153_n != null && this.field_70153_n instanceof EntityLivingBase) {
         if(Keyboard.isKeyDown(17)) {
            if(this.speed < 1.0D) {
               this.speed += 0.05D;
            }

            if(this.speed > 1.0D) {
               this.speed = 1.0D;
            }
         }

         if(Keyboard.isKeyDown(31)) {
            if(this.speed > -0.25D) {
               this.speed -= 0.025D;
            }

            if(this.speed < -0.25D) {
               this.speed = -0.25D;
            }
         }

         if(Keyboard.isKeyDown(30)) {
            if((double)this.deltaSteer < 45.0D) {
               this.deltaSteer += 10.0F;
            }
         } else if(this.deltaSteer > 0.0F && !Keyboard.isKeyDown(32)) {
            this.deltaSteer -= 10.0F;
         }

         if(Keyboard.isKeyDown(32)) {
            if((double)this.deltaSteer > -45.0D) {
               this.deltaSteer -= 10.0F;
            }
         } else if(this.deltaSteer < 0.0F && !Keyboard.isKeyDown(30)) {
            this.deltaSteer += 10.0F;
         }
      }

      if(this.deltaSteer != 0.0F) {
         this.R = this.vehicleLength / this.deltaSteer;
         this.k = 1.0F / this.R;
      } else {
         this.k = 0.0F;
      }

      this.field_70177_z += (float)((double)this.k * this.speed * 20.0D);
      if(this.field_70123_F) {
         this.field_70177_z += (float)this.speed;
         this.field_70165_t -= this.field_70159_w;
         this.field_70161_v -= this.field_70179_y;
      }

      this.func_70101_b(this.field_70177_z, this.field_70125_A);
      this.field_70159_w = Math.sin((double)this.field_70177_z * 3.141592653589793D / 180.0D) * this.speed;
      this.field_70179_y = Math.cos((double)this.field_70177_z * 3.141592653589793D / 180.0D) * this.speed;
      this.speed -= this.FLuft() + this.FSteig();
      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
   }

   public boolean func_70067_L() {
      return !this.field_70128_L;
   }

   public boolean func_130002_c(EntityPlayer player) {
      if(this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70153_n != player) {
         return true;
      } else if(!this.field_70170_p.isRemote) {
         player.mountEntity(this);
         this.isEmpty = false;
         return true;
      } else if(!this.shoulddrive) {
         player.addChatComponentMessage(new ChatComponentTranslation("This vehicle is broken! It must be repaired!", new Object[0]));
         return false;
      } else {
         return true;
      }
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

   protected void func_70037_a(NBTTagCompound p_70037_1_) {}

   protected void func_70014_b(NBTTagCompound p_70014_1_) {}

   protected double FLuft() {
      return 1.44D * this.speed * this.speed;
   }

   protected double FRoll() {
      return (this.mFzg + this.mZu) * 0.49050000000000005D * 0.066D * Math.cos((double)this.field_70125_A);
   }

   protected double FSteig() {
      return (this.mFzg + this.mZu) * 0.49050000000000005D * Math.sin((double)this.field_70125_A);
   }

   protected double FB() {
      return this.mF() * (this.mFzg + this.mZu) * this.aG();
   }

   protected double mF() {
      return this.FT();
   }

   protected double FT() {
      return -(this.mFzg + this.mZu) * this.aG();
   }

   protected double FWGes() {
      return this.FLuft() + this.FRoll();
   }

   protected double aG() {
      return Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 20.0D;
   }

   protected double getSpeed() {
      return this.speed;
   }

   protected void setSpeed(double speed) {
      this.speed = speed;
   }
}
