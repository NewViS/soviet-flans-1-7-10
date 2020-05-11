package de.ItsAMysterious.mods.reallifemod.core.streets.entitys;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.api.util.LittleFunctions;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityTrailer;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class EntityTruck extends Entity {

   private double acceleration;
   private boolean isEmpty;
   private double speedMultiplier;
   private int posRotationIncrements;
   private double truckX;
   private double truckY;
   private double truckZ;
   private double truckYaw;
   private double truckPitch;
   public float truckRoll;
   private float velocityX;
   private float velocityY;
   private float velocityZ;
   public float DeltaSteer;
   public double velocity;
   public double wheelRotation;
   protected double delta1;
   protected double delta2;
   protected double k;
   protected double R;
   public double Damage;
   public float FuelAmount = 100.0F;
   private double burning;
   public boolean damaged;
   public boolean isBraking;
   private float ccm = 1.0F;
   private float m = 15500.0F;
   private float radstand = 3600.0F;
   private float PS = 0.0F;
   private double AFront = 10.2D;


   public EntityTruck(World world) {
      super(world);
      this.isEmpty = true;
      this.speedMultiplier = 0.07D;
      this.field_70156_m = true;
      this.func_70105_a(3.0F, 1.0F);
      this.field_70158_ak = true;
   }

   protected boolean func_70041_e_() {
      return true;
   }

   protected void func_70088_a() {
      this.field_70180_af.addObject(17, new Integer(0));
      this.field_70180_af.addObject(18, new Integer(1));
      this.field_70180_af.addObject(19, new Float(0.0F));
   }

   public AxisAlignedBB func_70114_g(Entity entity) {
      return AxisAlignedBB.getBoundingBox(-1.0D, 0.0D, 0.0D, 1.0D, 1.0D, 3.0D);
   }

   public AxisAlignedBB func_70046_E() {
      return this.func_70114_g(this);
   }

   public boolean func_70104_M() {
      return !this.field_70132_H;
   }

   public EntityTruck(World world, double posX, double posY, double posZ) {
      super(world);
      this.field_70159_w = 0.0D;
      this.field_70181_x = 0.0D;
      this.field_70179_y = 0.0D;
      this.field_70169_q = posX;
      this.field_70167_r = posY;
      this.field_70166_s = posZ;
      this.func_70107_b(posX, posY, posZ);
   }

   public double func_70042_X() {
      return (double)(this.field_70131_O + 2.0F);
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

            if(!flag) {
               this.func_145778_a(Items.boat, 1, 0.0F);
            }

            this.func_70106_y();
         }

         return true;
      } else {
         return true;
      }
   }

   @SideOnly(Side.CLIENT)
   public void func_70057_ab() {
      this.setForwardDirection(-this.getForwardDirection());
      this.setTimeSinceHit(10);
      this.setDamageTaken(this.getDamageTaken() * 11.0F);
   }

   public boolean func_70067_L() {
      return !this.field_70128_L;
   }

   @SideOnly(Side.CLIENT)
   public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
      this.truckX = p_70056_1_;
      this.truckY = p_70056_3_;
      this.truckZ = p_70056_5_;
      this.truckYaw = (double)p_70056_7_;
      this.truckPitch = (double)p_70056_8_;
      this.field_70159_w = (double)this.velocityX;
      this.field_70181_x = (double)this.velocityY;
      this.field_70179_y = (double)this.velocityZ;
   }

   @SideOnly(Side.CLIENT)
   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.velocityX = (float)(this.field_70159_w = p_70016_1_);
      this.velocityY = (float)(this.field_70181_x = p_70016_3_);
      this.velocityZ = (float)(this.field_70179_y = p_70016_5_);
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
         if(Keyboard.isKeyDown(17)) {
            if((double)this.ccm < 1.5D) {
               this.ccm += 0.05F;
            } else if((double)this.ccm == 1.5D) {
               this.ccm -= 0.05F;
            }

            if(this.velocity < 0.5D) {
               this.velocity += 0.02500000037252903D;
            } else if(this.velocity > 1.0D) {
               this.velocity = 1.0D;
            }

            ++this.PS;
         } else {
            if(this.ccm > 1.0F) {
               this.ccm -= 0.01F;
            }

            this.PS = 0.0F;
         }

         if(Keyboard.isKeyDown(31)) {
            this.velocity -= 0.02500000037252903D;
            this.isBraking = true;
            if(this.field_70173_aa % 20 == 5) {
               this.field_70170_p.playSoundAtEntity(this, "reallifemod:reversebeep", 1.0F, 1.0F);
            }
         }

         if(Keyboard.isKeyDown(30)) {
            if(this.DeltaSteer < 45.0F) {
               this.DeltaSteer = (float)((double)this.DeltaSteer + 2.5D);
            }
         } else if(this.DeltaSteer > 0.0F) {
            this.DeltaSteer = (float)((double)this.DeltaSteer - 2.5D);
         }

         if(Keyboard.isKeyDown(32)) {
            if(this.DeltaSteer > -45.0F) {
               this.DeltaSteer = (float)((double)this.DeltaSteer - 2.5D);
            }
         } else if(this.DeltaSteer < 0.0F) {
            this.DeltaSteer = (float)((double)this.DeltaSteer + 2.5D);
         }

         if(Keyboard.isKeyDown(57)) {
            this.brake();
            this.isBraking = true;
         } else {
            this.isBraking = false;
         }

         if(!this.field_70170_p.isRemote && Keyboard.isKeyDown(ClientProxy.Key_Horn.getKeyCode()) && this.field_70173_aa % 20 == 2) {
            this.func_85030_a("reallifemod:horn", 1.0F, 1.0F);
         }

         if(this.FuelAmount != 0.0F) {
            if(this.field_70173_aa % 20 == 1) {
               ;
            }
         } else if(this.field_70173_aa % 20 == 1) {
            ;
         }

         if(!this.field_70170_p.isRemote && this.field_70173_aa % 50 == 1) {
            this.field_70170_p.playSoundAtEntity(this, "reallifemod:diesel", 1.0F, this.ccm);
         }
      }

      if(Keyboard.isKeyDown(28) && this.field_70170_p.getClosestPlayer(this.field_70165_t, this.field_70163_u, this.field_70161_v, 5.0D) != null) {
         EntityPlayer entity = this.field_70170_p.getClosestPlayer(this.field_70165_t, this.field_70163_u, this.field_70161_v, 5.0D);
         this.func_130002_c(entity);
         this.field_70153_n = entity;
         this.func_85030_a("tlm:door_close", 1.0F, 1.0F);
         if(this.field_70153_n != null) {
            entity.func_110145_l(this);
            this.setIsEmpty(true);
            this.field_70170_p.playSoundEffect(this.field_70165_t, this.field_70163_u, this.field_70161_v, "tlm:door_close", 1.0F, 1.0F);
         }
      }

      this.field_70121_D.setBB(this.func_70046_E());
      double sqrtMotion = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      if(this.field_70153_n != null && this.field_70153_n.isDead) {
         this.field_70153_n = null;
      }

      if(this.field_70128_L) {
         FMLClientHandler.instance().getClient().displayGuiScreen((GuiScreen)null);
      }

      if(this.DeltaSteer != 0.0F) {
         this.R = (double)(7.0F / this.DeltaSteer);
         this.k = 1.0D / this.R;
      }

      if(this.Damage == 100.0D) {
         this.func_70015_d(10);
         this.Damage = 0.0D;
         this.damaged = true;
         this.field_70170_p.createExplosion(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 10.0F, false);
      } else if(this.field_70123_F || this.field_70124_G) {
         this.calculateDamage(sqrtMotion);
      }

      this.wheelRotation += this.velocity;
      if(this.velocity > 0.006D) {
         this.velocity -= 0.006000000052154064D;
      } else if(this.velocity < -0.006D) {
         this.velocity += 0.006000000052154064D;
      } else {
         this.velocity = 0.0D;
      }

      double f = this.velocity * this.k;
      this.field_70177_z = (float)((double)this.field_70177_z + MathHelper.wrapAngleTo180_double(f));
      this.field_70159_w = Math.sin((double)(this.field_70177_z * 0.017453292F)) * this.velocity;
      this.field_70179_y = Math.cos((double)(this.field_70177_z * 0.017453292F)) * this.velocity;
      this.FuelAmount = (float)((double)this.FuelAmount - sqrtMotion);
      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      double x = Math.sin((double)this.field_70177_z * 3.141592653589793D / 180.0D) * 4.0D;
      double z = Math.cos((double)this.field_70177_z * 3.141592653589793D / 180.0D) * 4.0D;
      if(this.field_70170_p.isRemote && (this.field_70170_p.blockExists((int)(this.field_70165_t + x), (int)this.field_70163_u, (int)(this.field_70161_v + z)) || !this.field_70170_p.getChunkFromBlockCoords((int)this.field_70165_t, (int)this.field_70161_v).isChunkLoaded)) {
         if(this.field_70170_p.blockExists((int)(this.field_70165_t + x), (int)this.field_70163_u - 1, (int)(this.field_70161_v + z))) {
            this.field_70181_x += 0.1D;
         }

         if(this.field_70163_u > 0.0D) {
            this.field_70181_x = -0.1D;
         } else {
            this.field_70181_x = 0.0D;
         }

         this.field_70181_x *= 9.800000190734863D;
      } else {
         this.field_70181_x = 0.0D;
      }

      if(LittleFunctions.getBlock(this.field_70170_p, this.field_70165_t + Math.sin((double)this.field_70177_z * 3.141592653589793D / 180.0D) * 2.0D, 0.0D, this.field_70161_v + Math.cos((double)this.field_70177_z * 3.141592653589793D / 180.0D) * 2.0D) != Blocks.air && LittleFunctions.getBlock(this.field_70170_p, this.field_70165_t + Math.sin((double)this.field_70177_z * 3.141592653589793D / 180.0D) * 2.0D, Math.cos((double)this.field_70125_A * 3.141592653589793D / 180.0D) * 7.0D, this.field_70161_v + Math.cos((double)this.field_70177_z * 3.141592653589793D / 180.0D) * 2.0D) == Blocks.air) {
         ++this.field_70163_u;
      }

   }

   private void calculateDamage(double speed) {
      this.Damage += speed;
   }

   public void brake() {
      if(this.velocity > 0.0D) {
         this.velocity -= 0.012D;
      } else if(this.velocity < 0.0D) {
         this.velocity += 0.012000000104308128D;
      }

   }

   public void roll() {
      this.velocity -= this.FR();
   }

   public double FN(float FG) {
      return Math.cos((double)this.field_70125_A) * (double)FG;
   }

   public double FT(float FG) {
      return Math.sin((double)this.field_70125_A) * (double)FG;
   }

   public double FR() {
      return 0.006000000052154064D * this.FN(-9.81F * this.m);
   }

   public double FA() {
      return this.AFront / 2.0D * this.CW() * 1.29D * this.velocity;
   }

   public double CW() {
      return 0.29D / (this.velocity * 20.0D * 10.2D);
   }

   public double traegheitsmomQuad() {
      return (double)(0.0F * this.m * 4800000.0F);
   }

   public double mRed() {
      return this.traegheitsmomQuad() / 1.2D * 1.2D;
   }

   public double FV() {
      return this.FP() - (this.velocity * this.velocity + -9.81D * (double)this.m);
   }

   public double FP() {
      return (double)this.PS;
   }

   public void func_70043_V() {
      double z = Math.cos((double)(this.field_70177_z + 16.0F) * 3.141592653589793D / 180.0D) * 2.2D;
      double y = this.field_70163_u + Math.tan((double)this.field_70125_A * 3.141592653589793D / 180.0D) * 3.0D;
      double x = Math.sin((double)(this.field_70177_z + 16.0F) * 3.141592653589793D / 180.0D) * 2.2D;
      this.field_70153_n.setPosition(this.field_70165_t + x, this.field_70163_u + 1.6D, this.field_70161_v + z);
      this.field_70153_n.rotationYaw = (float)((double)this.field_70153_n.rotationYaw - this.velocity * this.k);
      EntityPlayer p = (EntityPlayer)this.field_70153_n;
      p.field_70759_as = (float)((double)p.field_70759_as - this.velocity * this.k);
   }

   protected void func_70037_a(NBTTagCompound tag) {}

   protected void func_70014_b(NBTTagCompound tag) {}

   public boolean func_130002_c(EntityPlayer player) {
      if(this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70153_n != player) {
         return true;
      } else {
         if(!this.field_70170_p.isRemote && !this.damaged) {
            player.mountEntity(this);
            this.isEmpty = false;
         }

         if(this.damaged) {
            player.addChatComponentMessage(new ChatComponentTranslation("This vehicle is broken! It must be repaired!", new Object[0]));
         }

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

   @SideOnly(Side.CLIENT)
   public void setIsEmpty(boolean empty) {
      this.isEmpty = empty;
   }

   public double getDelta2() {
      return this.delta2;
   }

   public double getDelta1() {
      return this.delta1;
   }

   public double getWheelRotation() {
      return this.wheelRotation;
   }

   public void setWheelRotation(double wheelRotation) {
      this.wheelRotation = wheelRotation;
   }

   public float getDeltaSteer() {
      return this.DeltaSteer;
   }

   public void setDeltaSteer(float deltaSteer) {
      this.DeltaSteer = deltaSteer;
   }

   public void setTrailer(EntityTrailer entityTrailer) {
      this.field_70170_p.spawnEntityInWorld(entityTrailer);
      entityTrailer.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }
}
