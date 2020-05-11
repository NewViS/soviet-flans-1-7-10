package de.ItsAMysterious.mods.reallifemod.core.entities.cars;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import org.lwjgl.input.Keyboard;

public class EntityRidingmower extends Entity {

   private final double maxSpeed;
   private final double minSpeed;
   public boolean hasBasket;
   public float wheelrotation;
   public double speed;
   public float deltaSteer;
   private double vehicleX;
   private double vehicleY;
   private double vehicleZ;
   private double vehicleYaw;
   private double vehiclePitch;
   private double acceleration;
   private double velocityX;
   private double velocityY;
   private double velocityZ;


   public EntityRidingmower(World world) {
      super(world);
      this.maxSpeed = 0.5D;
      this.minSpeed = -0.15D;
      this.hasBasket = true;
      this.wheelrotation = 0.0F;
      this.func_70105_a(1.0F, 1.0F);
      this.field_70138_W = 1.0F;
   }

   public EntityRidingmower(World world, float x, float y, float z) {
      this(world);
      this.func_70107_b((double)x, (double)y, (double)z);
      this.velocityX = 0.0D;
      this.velocityY = 0.0D;
      this.velocityZ = 0.0D;
      this.field_70142_S = (double)x;
      this.field_70137_T = (double)y;
      this.field_70136_U = (double)z;
   }

   protected void func_70088_a() {
      this.field_70180_af.addObject(17, new Integer(0));
      this.field_70180_af.addObject(18, new Integer(1));
      this.field_70180_af.addObject(19, new Float(0.0F));
   }

   public AxisAlignedBB func_70114_g(Entity entity) {
      return entity.getBoundingBox();
   }

   public AxisAlignedBB func_70046_E() {
      return this.field_70121_D;
   }

   public boolean func_70067_L() {
      return !this.field_70128_L;
   }

   @SideOnly(Side.CLIENT)
   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.velocityX = (double)((float)(this.field_70159_w = p_70016_1_));
      this.velocityY = (double)((float)(this.field_70181_x = p_70016_3_));
      this.velocityZ = (double)((float)(this.field_70179_y = p_70016_5_));
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
      if(this.field_70153_n != null && this.field_70153_n instanceof EntityLivingBase) {
         this.checkInput();
         this.move();
         if(this.field_70170_p.getBlock((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v) instanceof IShearable) {
            this.field_70170_p.getBlock((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v).dropBlockAsItem(this.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 1, 1);
            this.field_70170_p.setBlockToAir((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
         }
      }

   }

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

   private void checkInput() {
      if(Keyboard.isKeyDown(17)) {
         this.acceleration = 0.025D;
      } else if(Keyboard.isKeyDown(31)) {
         this.acceleration = -0.025D;
      } else {
         this.acceleration = 0.0D;
      }

      if(Keyboard.isKeyDown(32)) {
         if(this.deltaSteer > -45.0F) {
            this.deltaSteer -= 2.5F;
         }
      } else if(Keyboard.isKeyDown(30)) {
         if(this.deltaSteer < 45.0F) {
            this.deltaSteer = (float)((double)this.deltaSteer + 2.5D);
         }
      } else if(this.deltaSteer < 0.0F) {
         ++this.deltaSteer;
      } else if(this.deltaSteer > 0.0F) {
         --this.deltaSteer;
      }

      if(Keyboard.isKeyDown(57)) {
         this.brake();
      }

   }

   private void brake() {
      if(this.speed < 0.0D) {
         this.speed += 0.02500000037252903D;
      }

      if(this.speed > 0.0D) {
         this.speed -= 0.02500000037252903D;
      }

   }

   public void move() {
      double R = 0.0D;
      double k = 0.0D;
      if(this.deltaSteer != 0.0F) {
         R = (double)(2.0F / this.deltaSteer);
         k = 1.0D / R;
      }

      this.wheelrotation = (float)((double)this.wheelrotation + this.speed);
      double angleVelocity = this.speed * k;
      if(this.speed < 0.25D) {
         this.speed += this.acceleration;
      }

      this.field_70177_z = (float)((double)this.field_70177_z + angleVelocity);
      this.field_70159_w = -Math.sin(Math.toRadians((double)this.field_70177_z)) * this.speed;
      this.field_70179_y = -Math.cos(Math.toRadians((double)this.field_70177_z)) * this.speed;
      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
   }

   public void func_70108_f(Entity p_70108_1_) {
      super.applyEntityCollision(p_70108_1_);
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

   protected void func_70037_a(NBTTagCompound compound) {}

   public void func_70014_b(NBTTagCompound compound) {}

   public void func_70109_d(NBTTagCompound compound) {
      super.writeToNBT(compound);
      compound.setBoolean("hasBasket", this.hasBasket);
   }

   public void func_70020_e(NBTTagCompound compound) {
      super.readFromNBT(compound);
      this.hasBasket = compound.getBoolean("hasBasket");
   }

   public boolean func_130002_c(EntityPlayer player) {
      super.interactFirst(player);
      if(this.field_70153_n == null) {
         player.mountEntity(this);
      } else {
         player.addChatComponentMessage(new ChatComponentText("This vehicle is ocupied!"));
      }

      return true;
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
}
