package de.ItsAMysterious.mods.reallifemod.core.entitys.npcs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class EntityLanz extends Entity {

   private boolean isEmpty;
   private int lanzPosRotationIncrements;
   private double lanzX;
   private double lanzY;
   private double lanzZ;
   private double lanzYaw;
   private double lanzPitch;
   @SideOnly(Side.CLIENT)
   private double velocityX;
   @SideOnly(Side.CLIENT)
   private double velocityY;
   @SideOnly(Side.CLIENT)
   private double velocityZ;
   public float speed;
   private final float acceleration;
   float timepressed;
   private float move;
   private float radEinschlag;


   public EntityLanz(World par1World) {
      super(par1World);
      this.acceleration = 0.1F;
      this.field_70153_n = null;
      this.isEmpty = true;
      this.field_70156_m = true;
      this.speed = 0.0F;
      this.func_70105_a(1.0F, 2.0F);
   }

   public EntityLanz(World world, double x, double y, double z) {
      this(world);
      this.func_70107_b(x, y, z);
      this.func_70101_b(this.field_70177_z, this.field_70125_A);
      this.field_70159_w = 0.0D;
      this.field_70181_x = 0.0D;
      this.field_70179_y = 0.0D;
   }

   protected void func_70088_a() {
      this.field_70180_af.addObject(17, new Integer(0));
      this.field_70180_af.addObject(18, new Integer(1));
      this.field_70180_af.addObject(19, new Float(0.0F));
   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {}

   protected void func_70014_b(NBTTagCompound p_70014_1_) {}

   protected void func_70064_a(double p_70064_1_, boolean p_70064_3_) {
      int i = MathHelper.floor_double(this.field_70165_t);
      int j = MathHelper.floor_double(this.field_70163_u);
      int k = MathHelper.floor_double(this.field_70161_v);
      if(p_70064_3_) {
         if(this.field_70143_R > 3.0F) {
            this.func_70069_a(this.field_70143_R);
            if(!this.field_70170_p.isRemote && !this.field_70128_L) {
               this.func_70106_y();
            }

            this.field_70143_R = 0.0F;
         }
      } else if(this.field_70170_p.getBlock(i, j - 1, k).getMaterial() == Material.air && p_70064_1_ < 0.0D) {
         this.field_70143_R = (float)((double)this.field_70143_R - p_70064_1_);
      }

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

   public boolean func_130002_c(EntityPlayer entityPlayer) {
      if(this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer) {
         return true;
      } else {
         if(!this.field_70170_p.isRemote) {
            entityPlayer.mountEntity(this);
         }

         return true;
      }
   }

   @SideOnly(Side.CLIENT)
   public void func_70057_ab() {
      int i;
      for(i = 0; i < 100; ++i) {
         this.field_70163_u += (double)(i / 1000);
      }

      for(i = 100; i > 0; --i) {
         this.field_70163_u -= (double)(i / 1000);
      }

      this.setForwardDirection(-this.getForwardDirection());
      this.setTimeSinceHit(1);
      this.setDamageTaken(this.getDamageTaken() * 11.0F);
   }

   public void func_70071_h_() {
      super.onUpdate();
      if(this.getTimeSinceHit() > 0) {
         this.setTimeSinceHit(this.getTimeSinceHit() - 1);
      }

      if(this.getDamageTaken() > 0.0F) {
         this.setDamageTaken(this.getDamageTaken() - 1.0F);
      }

      if(this.field_70153_n != null && this.field_70153_n instanceof EntityLivingBase) {
         if(Keyboard.isKeyDown(17)) {
            if(this.timepressed < 10.0F) {
               this.timepressed = (float)((double)this.timepressed + 0.1D);
            }
         } else if(this.timepressed > 1.0F) {
            this.timepressed -= 2.0F;
         } else {
            this.timepressed = 0.0F;
         }

         System.out.println(this.speed);
         if(Keyboard.isKeyDown(30)) {
            ++this.field_70177_z;
         }

         if(Keyboard.isKeyDown(32)) {
            --this.field_70177_z;
         }

         this.getClass();
         this.speed = (float)Math.sqrt((double)(2.0F * 0.1F * this.timepressed));
         this.field_70177_z += this.radEinschlag;
         this.field_70159_w = -Math.sin((double)this.field_70177_z * 3.141592653589793D / -180.0D);
         this.field_70179_y = Math.cos((double)this.field_70177_z * 3.141592653589793D / 180.0D);
         this.field_70159_w *= (double)(this.speed / 5.0F);
         this.field_70179_y *= (double)(this.speed / 5.0F);
         this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      }

   }

   public void func_70043_V() {
      if(this.field_70153_n != null) {
         double d0 = Math.cos((double)this.field_70177_z * 3.141592653589793D / 180.0D) * 0.4D;
         double d1 = Math.sin((double)this.field_70177_z * 3.141592653589793D / 180.0D) * 0.4D;
         this.field_70153_n.setPosition(this.field_70165_t + d0, this.field_70163_u + 2.0D, this.field_70161_v + d1);
      }

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

   public boolean func_70067_L() {
      return !this.field_70128_L;
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
