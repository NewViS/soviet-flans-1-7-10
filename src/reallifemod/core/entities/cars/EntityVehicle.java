package de.ItsAMysterious.mods.reallifemod.core.entities.cars;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import de.ItsAMysterious.mods.reallifemod.api.entity.EntitySeat;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.VehicleData;
import de.ItsAMysterious.mods.reallifemod.core.entitys.bullets.EntityBullet;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiVehicleModification;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class EntityVehicle extends Entity implements IInventory {

   public double fuelvalue;
   @SideOnly(Side.CLIENT)
   protected Vector3f color;
   public double currentspeed;
   protected boolean isBraking;
   protected double health;
   protected double acceleration;
   public EntitySeat[] seats;
   private static final int inventorysize = 6;
   private ItemStack[] jeepItems;
   public double vehicleX;
   public double vehicleY;
   public double vehicleZ;
   public double vehicleYaw;
   public double vehiclePitch;
   @SideOnly(Side.CLIENT)
   public double velocityX;
   @SideOnly(Side.CLIENT)
   public double velocityY;
   @SideOnly(Side.CLIENT)
   public double velocityZ;
   public double deltasteer;
   public double wheelrotation;
   public VehicleData data;


   public EntityVehicle(World world, VehicleData data) {
      super(world);
      this.color = new Vector3f(1.0F, 1.0F, 1.0F);
      this.jeepItems = new ItemStack[6];
      this.func_70105_a(1.5F, 0.6F);
   }

   public EntityVehicle(World world, double x, double y, double z, VehicleData data) {
      this(world, data);
      this.data = data;
      this.func_70107_b(x, y, z);
      this.field_70159_w = 0.0D;
      this.field_70181_x = 0.0D;
      this.field_70179_y = 0.0D;
      this.field_70169_q = x;
      this.field_70167_r = y;
      this.field_70166_s = z;
      this.field_70138_W = 1.0F;
   }

   protected void func_70088_a() {
      this.field_70180_af.addObject(17, new Integer(0));
      this.field_70180_af.addObject(18, new Integer(1));
      this.field_70180_af.addObject(19, new Float(0.0F));
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
   }

   public boolean func_130002_c(EntityPlayer player) {
      player.mountEntity(this);
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.velocityX = (double)((float)(this.field_70159_w = p_70016_1_));
      this.velocityY = (double)((float)(this.field_70181_x = p_70016_3_));
      this.velocityZ = (double)((float)(this.field_70179_y = p_70016_5_));
   }

   public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
      super.setPositionAndRotation2(p_70056_1_, p_70056_3_, p_70056_5_, p_70056_7_, p_70056_8_, p_70056_9_);
      this.vehicleX = p_70056_1_;
      this.vehicleY = p_70056_3_;
      this.vehicleZ = p_70056_5_;
      this.vehicleYaw = (double)p_70056_7_;
      this.vehiclePitch = (double)p_70056_8_;
      this.field_70159_w = this.velocityX;
      this.field_70181_x = this.velocityY;
      this.field_70179_y = this.velocityZ;
   }

   public void func_70100_b_(EntityPlayer player) {
      if(this.hasDriver() && player != this.field_70153_n) {
         player.func_70606_j((float)((double)player.func_110143_aJ() - this.currentspeed * 5.0D));
      }

   }

   public void func_70108_f(Entity entity) {
      super.applyEntityCollision(entity);
      entity.addVelocity(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      if(entity instanceof EntityLivingBase) {
         ((EntityLivingBase)entity).setHealth((float)((double)((EntityLivingBase)entity).getHealth() - this.currentspeed / 0.3D * 2.0D));
      }

   }

   public AxisAlignedBB func_70114_g(Entity entity) {
      return entity.getBoundingBox();
   }

   public AxisAlignedBB func_70046_E() {
      return this.field_70121_D;
   }

   public boolean func_70104_M() {
      return true;
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

   protected boolean func_70041_e_() {
      return true;
   }

   public void checkInput() {
      if(this.hasDriver()) {
         if(Keyboard.isKeyDown(17)) {
            this.acceleration = 0.025D;
         } else if(Keyboard.isKeyDown(31)) {
            this.acceleration = -0.025D;
         } else {
            this.acceleration = 0.0D;
         }

         if(Keyboard.isKeyDown(32)) {
            if(this.deltasteer > -45.0D) {
               this.deltasteer -= 2.5D;
            }
         } else if(Keyboard.isKeyDown(30)) {
            if(this.deltasteer < 45.0D) {
               this.deltasteer += 2.5D;
            }
         } else if(this.deltasteer < 0.0D) {
            this.deltasteer += 2.5D;
         } else if(this.deltasteer > 0.0D) {
            this.deltasteer -= 2.5D;
         }

         if(Keyboard.isKeyDown(57)) {
            this.brake();
         }

         if(Keyboard.isKeyDown(35)) {
            this.field_70170_p.playSound(this.field_70165_t, this.field_70163_u, this.field_70161_v, "reallifemod:horn", 10.5F, 1.0F, true);
         }

         if(Keyboard.isKeyDown(50)) {
            ((EntityPlayer)this.field_70153_n).openGui(RealLifeMod.instance, this.func_145782_y() + GuiVehicleModification.GUI_ID, this.field_70170_p, this.field_70176_ah, this.field_70162_ai, this.field_70164_aj);
         }

         if(ClientProxy.Key_CarInv.isPressed() && this.hasDriver()) {
            EntityPlayer player = (EntityPlayer)this.field_70153_n;
            player.openGui(RealLifeMod.instance, this.func_145782_y(), this.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
         }
      }

   }

   public void move() {
      if(this.data != null) {
         double R = 0.0D;
         double k = 0.0D;
         if(this.deltasteer != 0.0D) {
            R = 5.0D / this.deltasteer;
            k = 1.0D / R;
         }

         this.wheelrotation += this.currentspeed * 20.0D;
         double angleVelocity = this.currentspeed * k;
         if(this.currentspeed < (double)this.data.getMaxSpeed() && this.currentspeed > this.data.getMinspeed()) {
            this.currentspeed += this.acceleration;
         }

         this.field_70177_z = (float)((double)this.field_70177_z + angleVelocity);
         this.field_70159_w = Math.sin(Math.toRadians((double)this.field_70177_z)) * this.currentspeed;
         this.field_70179_y = Math.cos(Math.toRadians((double)this.field_70177_z)) * this.currentspeed;
         this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
         if(!this.field_70170_p.blockExists((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v) && this.field_70170_p.getChunkFromBlockCoords((int)this.field_70165_t, (int)this.field_70161_v).isChunkLoaded) {
            this.field_70181_x = 0.0D;
         } else {
            if(this.field_70170_p.blockExists((int)this.field_70165_t, (int)this.field_70163_u - 1, (int)this.field_70161_v)) {
               this.field_70181_x += 0.1D;
            }

            if(this.field_70163_u > 0.0D) {
               this.field_70181_x = -0.1D;
            } else {
               this.field_70181_x = 0.0D;
            }

            this.field_70181_x *= 9.800000190734863D;
         }

         if(this.field_70124_G && this.getBlockForEntity(this.getPointForEntity(new Vector3f((float)this.field_70159_w, -0.5F, (float)this.field_70181_x))) == Blocks.air) {
            ;
         }

         if(this.acceleration == 0.0D) {
            if(this.currentspeed < -0.025D) {
               this.currentspeed += 0.025D;
            } else if(this.currentspeed > 0.0025D) {
               this.currentspeed -= 0.025D;
            } else {
               this.currentspeed = 0.0D;
            }
         }
      }

   }

   protected void brake() {
      if(this.currentspeed < -0.025D) {
         this.currentspeed *= 0.925D;
      } else if(this.currentspeed < 0.0D) {
         this.currentspeed = 0.0D;
      } else if(this.currentspeed > 0.0025D) {
         this.currentspeed *= 0.981D;
      } else {
         this.currentspeed = 0.0D;
      }

   }

   public void moveSeats() {}

   public void setupVehicle(VehicleData d) {
      this.data = d;
      RealLifeMod.log("Setted the Data for the Jeep!");
   }

   public boolean hasDriver() {
      return this.field_70153_n != null;
   }

   public Vector3f getColor() {
      return this.color;
   }

   public void setColor(Vector3f color) {
      this.color = color;
   }

   public Vector3f getPointForEntity(Vector3f point) {
      return new Vector3f((float)(this.field_70165_t + Math.sin(Math.toRadians((double)this.field_70177_z)) * (double)point.x), (float)(this.field_70163_u - Math.cos(Math.toRadians((double)this.field_70125_A)) * (double)point.y), (float)(this.field_70161_v + Math.cos(Math.toRadians((double)this.field_70177_z)) * (double)point.z));
   }

   private Block getBlockForEntity(Vector3f position) {
      return this.field_70170_p.getBlock((int)position.x, (int)position.y, (int)position.z);
   }

   protected void func_70037_a(NBTTagCompound compound) {
      NBTTagList colorlist = compound.getTagList("COLOR", 6);
      this.setColor(new Vector3f(colorlist.func_150308_e(0), colorlist.func_150308_e(1), colorlist.func_150308_e(2)));
      this.fuelvalue = compound.getDouble("FUELAMMOUNT");
      this.data.maxfuel = compound.getDouble("MaxFuel");
   }

   protected void func_70014_b(NBTTagCompound compound) {
      compound.setTag("COLOR", this.func_70087_a(new double[]{(double)this.getColor().x, (double)this.getColor().y, (double)this.getColor().z}));
      compound.setDouble("FUELAMMOUNT", this.fuelvalue);
      compound.setDouble("MaxFuel", this.data.maxfuel);
   }

   public void func_70043_V() {
      double R = 0.0D;
      double k = 0.0D;
      if(this.deltasteer != 0.0D) {
         R = 5.0D / this.deltasteer;
         k = 1.0D / R;
      }

      double z = Math.cos((double)(this.field_70177_z + 16.0F) * 3.141592653589793D / 180.0D) * 2.2D;
      double var10000 = this.field_70163_u + Math.cos((double)this.field_70125_A * 3.141592653589793D / 180.0D) * this.data.length;
      double x = Math.sin((double)(this.field_70177_z + 16.0F) * 3.141592653589793D / 180.0D) * 2.2D;
      this.field_70153_n.setPosition(this.field_70165_t + x, this.field_70163_u + 2.0D, this.field_70161_v + z);
   }

   public int func_70302_i_() {
      return 6;
   }

   public ItemStack func_70301_a(int slotID) {
      return slotID < 6?this.jeepItems[slotID]:null;
   }

   public ItemStack func_70298_a(int slot, int amount) {
      ItemStack stack = this.func_70301_a(slot);
      if(stack != null) {
         if(stack.stackSize > amount) {
            stack = stack.splitStack(amount);
            if(stack.stackSize == 0) {
               this.func_70299_a(slot, (ItemStack)null);
            }
         } else {
            this.func_70299_a(slot, (ItemStack)null);
         }

         this.onInventoryChanged();
      }

      return stack;
   }

   public ItemStack func_70304_b(int slot) {
      ItemStack stack = this.func_70301_a(slot);
      if(stack != null) {
         this.func_70299_a(slot, (ItemStack)null);
      }

      return stack;
   }

   public void onInventoryChanged() {
      for(int i = 0; i < this.jeepItems.length; ++i) {
         if(this.func_70301_a(i) != null && this.func_70301_a(i).stackSize == 0) {
            this.func_70299_a(i, (ItemStack)null);
         }
      }

   }

   public void func_70299_a(int slot, ItemStack stack) {
      if(slot >= 0 && slot < 6) {
         this.jeepItems[slot] = stack;
      }

      if(stack != null && stack.stackSize > this.func_70297_j_()) {
         stack.stackSize = this.func_70297_j_();
      }

      this.func_70296_d();
   }

   public String func_145825_b() {
      return "JeepTrunk";
   }

   public boolean func_145818_k_() {
      return true;
   }

   public int func_70297_j_() {
      return 64;
   }

   public void func_70296_d() {}

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return true;
   }

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public boolean func_94041_b(int slotID, ItemStack stack) {
      return slotID < this.jeepItems.length;
   }

   public boolean func_70097_a(DamageSource source, float p_70097_2_) {
      if(this.func_85032_ar()) {
         return false;
      } else if(!this.field_70170_p.isRemote && !this.field_70128_L) {
         this.setForwardDirection(-this.getForwardDirection());
         this.setTimeSinceHit(10);
         this.setDamageTaken(this.getDamageTaken() + p_70097_2_ * 10.0F);
         this.func_70018_K();
         boolean flag = source.getEntity() instanceof EntityPlayer && ((EntityPlayer)source.getEntity()).capabilities.isCreativeMode;
         if(flag || this.getDamageTaken() > 40.0F) {
            if(this.field_70153_n != null) {
               this.field_70153_n.mountEntity(this);
            }

            if(!flag) {
               this.func_145778_a(RealLifeMod_Items.jeep, 1, 0.0F);
            }
         }

         return true;
      } else {
         if(source.getEntity() instanceof EntityBullet) {
            this.field_70170_p.createExplosion(this, 0.0D, 0.0D, 0.0D, 5.0F, true);
         }

         return true;
      }
   }
}
