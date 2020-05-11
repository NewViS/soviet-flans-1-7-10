package net.minecraft.entity.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.entity.item.EntityMinecartFurnace;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityMinecart extends Entity {

   private boolean isInReverse;
   private String entityName;
   private static final int[][][] matrix = new int[][][]{{{0, 0, -1}, {0, 0, 1}}, {{-1, 0, 0}, {1, 0, 0}}, {{-1, -1, 0}, {1, 0, 0}}, {{-1, 0, 0}, {1, -1, 0}}, {{0, 0, -1}, {0, -1, 1}}, {{0, -1, -1}, {0, 0, 1}}, {{0, 0, 1}, {1, 0, 0}}, {{0, 0, 1}, {-1, 0, 0}}, {{0, 0, -1}, {-1, 0, 0}}, {{0, 0, -1}, {1, 0, 0}}};
   private int turnProgress;
   private double minecartX;
   private double minecartY;
   private double minecartZ;
   private double minecartYaw;
   private double minecartPitch;
   private double velocityX;
   private double velocityY;
   private double velocityZ;


   public EntityMinecart(World var1) {
      super(var1);
      super.preventEntitySpawning = true;
      this.setSize(0.98F, 0.7F);
      super.yOffset = super.height / 2.0F;
   }

   public static EntityMinecart createMinecart(World var0, double var1, double var3, double var5, int var7) {
      switch(var7) {
      case 1:
         return new EntityMinecartChest(var0, var1, var3, var5);
      case 2:
         return new EntityMinecartFurnace(var0, var1, var3, var5);
      case 3:
         return new EntityMinecartTNT(var0, var1, var3, var5);
      case 4:
         return new EntityMinecartMobSpawner(var0, var1, var3, var5);
      case 5:
         return new EntityMinecartHopper(var0, var1, var3, var5);
      case 6:
         return new EntityMinecartCommandBlock(var0, var1, var3, var5);
      default:
         return new EntityMinecartEmpty(var0, var1, var3, var5);
      }
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void entityInit() {
      super.dataWatcher.addObject(17, new Integer(0));
      super.dataWatcher.addObject(18, new Integer(1));
      super.dataWatcher.addObject(19, new Float(0.0F));
      super.dataWatcher.addObject(20, new Integer(0));
      super.dataWatcher.addObject(21, new Integer(6));
      super.dataWatcher.addObject(22, Byte.valueOf((byte)0));
   }

   public AxisAlignedBB getCollisionBox(Entity var1) {
      return var1.canBePushed()?var1.boundingBox:null;
   }

   public AxisAlignedBB getBoundingBox() {
      return null;
   }

   public boolean canBePushed() {
      return true;
   }

   public EntityMinecart(World var1, double var2, double var4, double var6) {
      this(var1);
      this.setPosition(var2, var4, var6);
      super.motionX = 0.0D;
      super.motionY = 0.0D;
      super.motionZ = 0.0D;
      super.prevPosX = var2;
      super.prevPosY = var4;
      super.prevPosZ = var6;
   }

   public double getMountedYOffset() {
      return (double)super.height * 0.0D - 0.30000001192092896D;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(!super.worldObj.isRemote && !super.isDead) {
         if(this.isEntityInvulnerable()) {
            return false;
         } else {
            this.setRollingDirection(-this.getRollingDirection());
            this.setRollingAmplitude(10);
            this.setBeenAttacked();
            this.setDamage(this.getDamage() + var2 * 10.0F);
            boolean var3 = var1.getEntity() instanceof EntityPlayer && ((EntityPlayer)var1.getEntity()).capabilities.isCreativeMode;
            if(var3 || this.getDamage() > 40.0F) {
               if(super.riddenByEntity != null) {
                  super.riddenByEntity.mountEntity(this);
               }

               if(var3 && !this.hasCustomInventoryName()) {
                  this.setDead();
               } else {
                  this.killMinecart(var1);
               }
            }

            return true;
         }
      } else {
         return true;
      }
   }

   public void killMinecart(DamageSource var1) {
      this.setDead();
      ItemStack var2 = new ItemStack(Items.minecart, 1);
      if(this.entityName != null) {
         var2.setStackDisplayName(this.entityName);
      }

      this.entityDropItem(var2, 0.0F);
   }

   public void performHurtAnimation() {
      this.setRollingDirection(-this.getRollingDirection());
      this.setRollingAmplitude(10);
      this.setDamage(this.getDamage() + this.getDamage() * 10.0F);
   }

   public boolean canBeCollidedWith() {
      return !super.isDead;
   }

   public void setDead() {
      super.setDead();
   }

   public void onUpdate() {
      if(this.getRollingAmplitude() > 0) {
         this.setRollingAmplitude(this.getRollingAmplitude() - 1);
      }

      if(this.getDamage() > 0.0F) {
         this.setDamage(this.getDamage() - 1.0F);
      }

      if(super.posY < -64.0D) {
         this.kill();
      }

      int var2;
      if(!super.worldObj.isRemote && super.worldObj instanceof WorldServer) {
         super.worldObj.theProfiler.startSection("portal");
         MinecraftServer var1 = ((WorldServer)super.worldObj).func_73046_m();
         var2 = this.getMaxInPortalTime();
         if(super.inPortal) {
            if(var1.getAllowNether()) {
               if(super.ridingEntity == null && super.portalCounter++ >= var2) {
                  super.portalCounter = var2;
                  super.timeUntilPortal = this.getPortalCooldown();
                  byte var3;
                  if(super.worldObj.provider.dimensionId == -1) {
                     var3 = 0;
                  } else {
                     var3 = -1;
                  }

                  this.travelToDimension(var3);
               }

               super.inPortal = false;
            }
         } else {
            if(super.portalCounter > 0) {
               super.portalCounter -= 4;
            }

            if(super.portalCounter < 0) {
               super.portalCounter = 0;
            }
         }

         if(super.timeUntilPortal > 0) {
            --super.timeUntilPortal;
         }

         super.worldObj.theProfiler.endSection();
      }

      if(super.worldObj.isRemote) {
         if(this.turnProgress > 0) {
            double var19 = super.posX + (this.minecartX - super.posX) / (double)this.turnProgress;
            double var21 = super.posY + (this.minecartY - super.posY) / (double)this.turnProgress;
            double var5 = super.posZ + (this.minecartZ - super.posZ) / (double)this.turnProgress;
            double var7 = MathHelper.wrapAngleTo180_double(this.minecartYaw - (double)super.rotationYaw);
            super.rotationYaw = (float)((double)super.rotationYaw + var7 / (double)this.turnProgress);
            super.rotationPitch = (float)((double)super.rotationPitch + (this.minecartPitch - (double)super.rotationPitch) / (double)this.turnProgress);
            --this.turnProgress;
            this.setPosition(var19, var21, var5);
            this.setRotation(super.rotationYaw, super.rotationPitch);
         } else {
            this.setPosition(super.posX, super.posY, super.posZ);
            this.setRotation(super.rotationYaw, super.rotationPitch);
         }

      } else {
         super.prevPosX = super.posX;
         super.prevPosY = super.posY;
         super.prevPosZ = super.posZ;
         super.motionY -= 0.03999999910593033D;
         int var18 = MathHelper.floor_double(super.posX);
         var2 = MathHelper.floor_double(super.posY);
         int var20 = MathHelper.floor_double(super.posZ);
         if(BlockRailBase.func_150049_b_(super.worldObj, var18, var2 - 1, var20)) {
            --var2;
         }

         double var4 = 0.4D;
         double var6 = 0.0078125D;
         Block var8 = super.worldObj.getBlock(var18, var2, var20);
         if(BlockRailBase.func_150051_a(var8)) {
            int var9 = super.worldObj.getBlockMetadata(var18, var2, var20);
            this.func_145821_a(var18, var2, var20, var4, var6, var8, var9);
            if(var8 == Blocks.activator_rail) {
               this.onActivatorRailPass(var18, var2, var20, (var9 & 8) != 0);
            }
         } else {
            this.func_94088_b(var4);
         }

         this.func_145775_I();
         super.rotationPitch = 0.0F;
         double var22 = super.prevPosX - super.posX;
         double var11 = super.prevPosZ - super.posZ;
         if(var22 * var22 + var11 * var11 > 0.001D) {
            super.rotationYaw = (float)(Math.atan2(var11, var22) * 180.0D / 3.141592653589793D);
            if(this.isInReverse) {
               super.rotationYaw += 180.0F;
            }
         }

         double var13 = (double)MathHelper.wrapAngleTo180_float(super.rotationYaw - super.prevRotationYaw);
         if(var13 < -170.0D || var13 >= 170.0D) {
            super.rotationYaw += 180.0F;
            this.isInReverse = !this.isInReverse;
         }

         this.setRotation(super.rotationYaw, super.rotationPitch);
         List var15 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
         if(var15 != null && !var15.isEmpty()) {
            for(int var16 = 0; var16 < var15.size(); ++var16) {
               Entity var17 = (Entity)var15.get(var16);
               if(var17 != super.riddenByEntity && var17.canBePushed() && var17 instanceof EntityMinecart) {
                  var17.applyEntityCollision(this);
               }
            }
         }

         if(super.riddenByEntity != null && super.riddenByEntity.isDead) {
            if(super.riddenByEntity.ridingEntity == this) {
               super.riddenByEntity.ridingEntity = null;
            }

            super.riddenByEntity = null;
         }

      }
   }

   public void onActivatorRailPass(int var1, int var2, int var3, boolean var4) {}

   protected void func_94088_b(double var1) {
      if(super.motionX < -var1) {
         super.motionX = -var1;
      }

      if(super.motionX > var1) {
         super.motionX = var1;
      }

      if(super.motionZ < -var1) {
         super.motionZ = -var1;
      }

      if(super.motionZ > var1) {
         super.motionZ = var1;
      }

      if(super.onGround) {
         super.motionX *= 0.5D;
         super.motionY *= 0.5D;
         super.motionZ *= 0.5D;
      }

      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      if(!super.onGround) {
         super.motionX *= 0.949999988079071D;
         super.motionY *= 0.949999988079071D;
         super.motionZ *= 0.949999988079071D;
      }

   }

   protected void func_145821_a(int var1, int var2, int var3, double var4, double var6, Block var8, int var9) {
      super.fallDistance = 0.0F;
      Vec3 var10 = this.func_70489_a(super.posX, super.posY, super.posZ);
      super.posY = (double)var2;
      boolean var11 = false;
      boolean var12 = false;
      if(var8 == Blocks.golden_rail) {
         var11 = (var9 & 8) != 0;
         var12 = !var11;
      }

      if(((BlockRailBase)var8).isPowered()) {
         var9 &= 7;
      }

      if(var9 >= 2 && var9 <= 5) {
         super.posY = (double)(var2 + 1);
      }

      if(var9 == 2) {
         super.motionX -= var6;
      }

      if(var9 == 3) {
         super.motionX += var6;
      }

      if(var9 == 4) {
         super.motionZ += var6;
      }

      if(var9 == 5) {
         super.motionZ -= var6;
      }

      int[][] var13 = matrix[var9];
      double var14 = (double)(var13[1][0] - var13[0][0]);
      double var16 = (double)(var13[1][2] - var13[0][2]);
      double var18 = Math.sqrt(var14 * var14 + var16 * var16);
      double var20 = super.motionX * var14 + super.motionZ * var16;
      if(var20 < 0.0D) {
         var14 = -var14;
         var16 = -var16;
      }

      double var22 = Math.sqrt(super.motionX * super.motionX + super.motionZ * super.motionZ);
      if(var22 > 2.0D) {
         var22 = 2.0D;
      }

      super.motionX = var22 * var14 / var18;
      super.motionZ = var22 * var16 / var18;
      double var24;
      double var26;
      double var28;
      double var30;
      if(super.riddenByEntity != null && super.riddenByEntity instanceof EntityLivingBase) {
         var24 = (double)((EntityLivingBase)super.riddenByEntity).moveForward;
         if(var24 > 0.0D) {
            var26 = -Math.sin((double)(super.riddenByEntity.rotationYaw * 3.1415927F / 180.0F));
            var28 = Math.cos((double)(super.riddenByEntity.rotationYaw * 3.1415927F / 180.0F));
            var30 = super.motionX * super.motionX + super.motionZ * super.motionZ;
            if(var30 < 0.01D) {
               super.motionX += var26 * 0.1D;
               super.motionZ += var28 * 0.1D;
               var12 = false;
            }
         }
      }

      if(var12) {
         var24 = Math.sqrt(super.motionX * super.motionX + super.motionZ * super.motionZ);
         if(var24 < 0.03D) {
            super.motionX *= 0.0D;
            super.motionY *= 0.0D;
            super.motionZ *= 0.0D;
         } else {
            super.motionX *= 0.5D;
            super.motionY *= 0.0D;
            super.motionZ *= 0.5D;
         }
      }

      var24 = 0.0D;
      var26 = (double)var1 + 0.5D + (double)var13[0][0] * 0.5D;
      var28 = (double)var3 + 0.5D + (double)var13[0][2] * 0.5D;
      var30 = (double)var1 + 0.5D + (double)var13[1][0] * 0.5D;
      double var32 = (double)var3 + 0.5D + (double)var13[1][2] * 0.5D;
      var14 = var30 - var26;
      var16 = var32 - var28;
      double var34;
      double var36;
      if(var14 == 0.0D) {
         super.posX = (double)var1 + 0.5D;
         var24 = super.posZ - (double)var3;
      } else if(var16 == 0.0D) {
         super.posZ = (double)var3 + 0.5D;
         var24 = super.posX - (double)var1;
      } else {
         var34 = super.posX - var26;
         var36 = super.posZ - var28;
         var24 = (var34 * var14 + var36 * var16) * 2.0D;
      }

      super.posX = var26 + var14 * var24;
      super.posZ = var28 + var16 * var24;
      this.setPosition(super.posX, super.posY + (double)super.yOffset, super.posZ);
      var34 = super.motionX;
      var36 = super.motionZ;
      if(super.riddenByEntity != null) {
         var34 *= 0.75D;
         var36 *= 0.75D;
      }

      if(var34 < -var4) {
         var34 = -var4;
      }

      if(var34 > var4) {
         var34 = var4;
      }

      if(var36 < -var4) {
         var36 = -var4;
      }

      if(var36 > var4) {
         var36 = var4;
      }

      this.moveEntity(var34, 0.0D, var36);
      if(var13[0][1] != 0 && MathHelper.floor_double(super.posX) - var1 == var13[0][0] && MathHelper.floor_double(super.posZ) - var3 == var13[0][2]) {
         this.setPosition(super.posX, super.posY + (double)var13[0][1], super.posZ);
      } else if(var13[1][1] != 0 && MathHelper.floor_double(super.posX) - var1 == var13[1][0] && MathHelper.floor_double(super.posZ) - var3 == var13[1][2]) {
         this.setPosition(super.posX, super.posY + (double)var13[1][1], super.posZ);
      }

      this.applyDrag();
      Vec3 var38 = this.func_70489_a(super.posX, super.posY, super.posZ);
      if(var38 != null && var10 != null) {
         double var39 = (var10.yCoord - var38.yCoord) * 0.05D;
         var22 = Math.sqrt(super.motionX * super.motionX + super.motionZ * super.motionZ);
         if(var22 > 0.0D) {
            super.motionX = super.motionX / var22 * (var22 + var39);
            super.motionZ = super.motionZ / var22 * (var22 + var39);
         }

         this.setPosition(super.posX, var38.yCoord, super.posZ);
      }

      int var45 = MathHelper.floor_double(super.posX);
      int var40 = MathHelper.floor_double(super.posZ);
      if(var45 != var1 || var40 != var3) {
         var22 = Math.sqrt(super.motionX * super.motionX + super.motionZ * super.motionZ);
         super.motionX = var22 * (double)(var45 - var1);
         super.motionZ = var22 * (double)(var40 - var3);
      }

      if(var11) {
         double var41 = Math.sqrt(super.motionX * super.motionX + super.motionZ * super.motionZ);
         if(var41 > 0.01D) {
            double var43 = 0.06D;
            super.motionX += super.motionX / var41 * var43;
            super.motionZ += super.motionZ / var41 * var43;
         } else if(var9 == 1) {
            if(super.worldObj.getBlock(var1 - 1, var2, var3).isNormalCube()) {
               super.motionX = 0.02D;
            } else if(super.worldObj.getBlock(var1 + 1, var2, var3).isNormalCube()) {
               super.motionX = -0.02D;
            }
         } else if(var9 == 0) {
            if(super.worldObj.getBlock(var1, var2, var3 - 1).isNormalCube()) {
               super.motionZ = 0.02D;
            } else if(super.worldObj.getBlock(var1, var2, var3 + 1).isNormalCube()) {
               super.motionZ = -0.02D;
            }
         }
      }

   }

   protected void applyDrag() {
      if(super.riddenByEntity != null) {
         super.motionX *= 0.996999979019165D;
         super.motionY *= 0.0D;
         super.motionZ *= 0.996999979019165D;
      } else {
         super.motionX *= 0.9599999785423279D;
         super.motionY *= 0.0D;
         super.motionZ *= 0.9599999785423279D;
      }

   }

   public Vec3 func_70495_a(double var1, double var3, double var5, double var7) {
      int var9 = MathHelper.floor_double(var1);
      int var10 = MathHelper.floor_double(var3);
      int var11 = MathHelper.floor_double(var5);
      if(BlockRailBase.func_150049_b_(super.worldObj, var9, var10 - 1, var11)) {
         --var10;
      }

      Block var12 = super.worldObj.getBlock(var9, var10, var11);
      if(!BlockRailBase.func_150051_a(var12)) {
         return null;
      } else {
         int var13 = super.worldObj.getBlockMetadata(var9, var10, var11);
         if(((BlockRailBase)var12).isPowered()) {
            var13 &= 7;
         }

         var3 = (double)var10;
         if(var13 >= 2 && var13 <= 5) {
            var3 = (double)(var10 + 1);
         }

         int[][] var14 = matrix[var13];
         double var15 = (double)(var14[1][0] - var14[0][0]);
         double var17 = (double)(var14[1][2] - var14[0][2]);
         double var19 = Math.sqrt(var15 * var15 + var17 * var17);
         var15 /= var19;
         var17 /= var19;
         var1 += var15 * var7;
         var5 += var17 * var7;
         if(var14[0][1] != 0 && MathHelper.floor_double(var1) - var9 == var14[0][0] && MathHelper.floor_double(var5) - var11 == var14[0][2]) {
            var3 += (double)var14[0][1];
         } else if(var14[1][1] != 0 && MathHelper.floor_double(var1) - var9 == var14[1][0] && MathHelper.floor_double(var5) - var11 == var14[1][2]) {
            var3 += (double)var14[1][1];
         }

         return this.func_70489_a(var1, var3, var5);
      }
   }

   public Vec3 func_70489_a(double var1, double var3, double var5) {
      int var7 = MathHelper.floor_double(var1);
      int var8 = MathHelper.floor_double(var3);
      int var9 = MathHelper.floor_double(var5);
      if(BlockRailBase.func_150049_b_(super.worldObj, var7, var8 - 1, var9)) {
         --var8;
      }

      Block var10 = super.worldObj.getBlock(var7, var8, var9);
      if(BlockRailBase.func_150051_a(var10)) {
         int var11 = super.worldObj.getBlockMetadata(var7, var8, var9);
         var3 = (double)var8;
         if(((BlockRailBase)var10).isPowered()) {
            var11 &= 7;
         }

         if(var11 >= 2 && var11 <= 5) {
            var3 = (double)(var8 + 1);
         }

         int[][] var12 = matrix[var11];
         double var13 = 0.0D;
         double var15 = (double)var7 + 0.5D + (double)var12[0][0] * 0.5D;
         double var17 = (double)var8 + 0.5D + (double)var12[0][1] * 0.5D;
         double var19 = (double)var9 + 0.5D + (double)var12[0][2] * 0.5D;
         double var21 = (double)var7 + 0.5D + (double)var12[1][0] * 0.5D;
         double var23 = (double)var8 + 0.5D + (double)var12[1][1] * 0.5D;
         double var25 = (double)var9 + 0.5D + (double)var12[1][2] * 0.5D;
         double var27 = var21 - var15;
         double var29 = (var23 - var17) * 2.0D;
         double var31 = var25 - var19;
         if(var27 == 0.0D) {
            var1 = (double)var7 + 0.5D;
            var13 = var5 - (double)var9;
         } else if(var31 == 0.0D) {
            var5 = (double)var9 + 0.5D;
            var13 = var1 - (double)var7;
         } else {
            double var33 = var1 - var15;
            double var35 = var5 - var19;
            var13 = (var33 * var27 + var35 * var31) * 2.0D;
         }

         var1 = var15 + var27 * var13;
         var3 = var17 + var29 * var13;
         var5 = var19 + var31 * var13;
         if(var29 < 0.0D) {
            ++var3;
         }

         if(var29 > 0.0D) {
            var3 += 0.5D;
         }

         return Vec3.createVectorHelper(var1, var3, var5);
      } else {
         return null;
      }
   }

   protected void readEntityFromNBT(NBTTagCompound var1) {
      if(var1.getBoolean("CustomDisplayTile")) {
         this.func_145819_k(var1.getInteger("DisplayTile"));
         this.setDisplayTileData(var1.getInteger("DisplayData"));
         this.setDisplayTileOffset(var1.getInteger("DisplayOffset"));
      }

      if(var1.hasKey("CustomName", 8) && var1.getString("CustomName").length() > 0) {
         this.entityName = var1.getString("CustomName");
      }

   }

   protected void writeEntityToNBT(NBTTagCompound var1) {
      if(this.hasDisplayTile()) {
         var1.setBoolean("CustomDisplayTile", true);
         var1.setInteger("DisplayTile", this.func_145820_n().getMaterial() == Material.air?0:Block.getIdFromBlock(this.func_145820_n()));
         var1.setInteger("DisplayData", this.getDisplayTileData());
         var1.setInteger("DisplayOffset", this.getDisplayTileOffset());
      }

      if(this.entityName != null && this.entityName.length() > 0) {
         var1.setString("CustomName", this.entityName);
      }

   }

   public float getShadowSize() {
      return 0.0F;
   }

   public void applyEntityCollision(Entity var1) {
      if(!super.worldObj.isRemote) {
         if(var1 != super.riddenByEntity) {
            if(var1 instanceof EntityLivingBase && !(var1 instanceof EntityPlayer) && !(var1 instanceof EntityIronGolem) && this.getMinecartType() == 0 && super.motionX * super.motionX + super.motionZ * super.motionZ > 0.01D && super.riddenByEntity == null && var1.ridingEntity == null) {
               var1.mountEntity(this);
            }

            double var2 = var1.posX - super.posX;
            double var4 = var1.posZ - super.posZ;
            double var6 = var2 * var2 + var4 * var4;
            if(var6 >= 9.999999747378752E-5D) {
               var6 = (double)MathHelper.sqrt_double(var6);
               var2 /= var6;
               var4 /= var6;
               double var8 = 1.0D / var6;
               if(var8 > 1.0D) {
                  var8 = 1.0D;
               }

               var2 *= var8;
               var4 *= var8;
               var2 *= 0.10000000149011612D;
               var4 *= 0.10000000149011612D;
               var2 *= (double)(1.0F - super.entityCollisionReduction);
               var4 *= (double)(1.0F - super.entityCollisionReduction);
               var2 *= 0.5D;
               var4 *= 0.5D;
               if(var1 instanceof EntityMinecart) {
                  double var10 = var1.posX - super.posX;
                  double var12 = var1.posZ - super.posZ;
                  Vec3 var14 = Vec3.createVectorHelper(var10, 0.0D, var12).normalize();
                  Vec3 var15 = Vec3.createVectorHelper((double)MathHelper.cos(super.rotationYaw * 3.1415927F / 180.0F), 0.0D, (double)MathHelper.sin(super.rotationYaw * 3.1415927F / 180.0F)).normalize();
                  double var16 = Math.abs(var14.dotProduct(var15));
                  if(var16 < 0.800000011920929D) {
                     return;
                  }

                  double var18 = var1.motionX + super.motionX;
                  double var20 = var1.motionZ + super.motionZ;
                  if(((EntityMinecart)var1).getMinecartType() == 2 && this.getMinecartType() != 2) {
                     super.motionX *= 0.20000000298023224D;
                     super.motionZ *= 0.20000000298023224D;
                     this.addVelocity(var1.motionX - var2, 0.0D, var1.motionZ - var4);
                     var1.motionX *= 0.949999988079071D;
                     var1.motionZ *= 0.949999988079071D;
                  } else if(((EntityMinecart)var1).getMinecartType() != 2 && this.getMinecartType() == 2) {
                     var1.motionX *= 0.20000000298023224D;
                     var1.motionZ *= 0.20000000298023224D;
                     var1.addVelocity(super.motionX + var2, 0.0D, super.motionZ + var4);
                     super.motionX *= 0.949999988079071D;
                     super.motionZ *= 0.949999988079071D;
                  } else {
                     var18 /= 2.0D;
                     var20 /= 2.0D;
                     super.motionX *= 0.20000000298023224D;
                     super.motionZ *= 0.20000000298023224D;
                     this.addVelocity(var18 - var2, 0.0D, var20 - var4);
                     var1.motionX *= 0.20000000298023224D;
                     var1.motionZ *= 0.20000000298023224D;
                     var1.addVelocity(var18 + var2, 0.0D, var20 + var4);
                  }
               } else {
                  this.addVelocity(-var2, 0.0D, -var4);
                  var1.addVelocity(var2 / 4.0D, 0.0D, var4 / 4.0D);
               }
            }

         }
      }
   }

   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9) {
      this.minecartX = var1;
      this.minecartY = var3;
      this.minecartZ = var5;
      this.minecartYaw = (double)var7;
      this.minecartPitch = (double)var8;
      this.turnProgress = var9 + 2;
      super.motionX = this.velocityX;
      super.motionY = this.velocityY;
      super.motionZ = this.velocityZ;
   }

   public void setVelocity(double var1, double var3, double var5) {
      this.velocityX = super.motionX = var1;
      this.velocityY = super.motionY = var3;
      this.velocityZ = super.motionZ = var5;
   }

   public void setDamage(float var1) {
      super.dataWatcher.updateObject(19, Float.valueOf(var1));
   }

   public float getDamage() {
      return super.dataWatcher.getWatchableObjectFloat(19);
   }

   public void setRollingAmplitude(int var1) {
      super.dataWatcher.updateObject(17, Integer.valueOf(var1));
   }

   public int getRollingAmplitude() {
      return super.dataWatcher.getWatchableObjectInt(17);
   }

   public void setRollingDirection(int var1) {
      super.dataWatcher.updateObject(18, Integer.valueOf(var1));
   }

   public int getRollingDirection() {
      return super.dataWatcher.getWatchableObjectInt(18);
   }

   public abstract int getMinecartType();

   public Block func_145820_n() {
      if(!this.hasDisplayTile()) {
         return this.func_145817_o();
      } else {
         int var1 = this.getDataWatcher().getWatchableObjectInt(20) & '\uffff';
         return Block.getBlockById(var1);
      }
   }

   public Block func_145817_o() {
      return Blocks.air;
   }

   public int getDisplayTileData() {
      return !this.hasDisplayTile()?this.getDefaultDisplayTileData():this.getDataWatcher().getWatchableObjectInt(20) >> 16;
   }

   public int getDefaultDisplayTileData() {
      return 0;
   }

   public int getDisplayTileOffset() {
      return !this.hasDisplayTile()?this.getDefaultDisplayTileOffset():this.getDataWatcher().getWatchableObjectInt(21);
   }

   public int getDefaultDisplayTileOffset() {
      return 6;
   }

   public void func_145819_k(int var1) {
      this.getDataWatcher().updateObject(20, Integer.valueOf(var1 & '\uffff' | this.getDisplayTileData() << 16));
      this.setHasDisplayTile(true);
   }

   public void setDisplayTileData(int var1) {
      this.getDataWatcher().updateObject(20, Integer.valueOf(Block.getIdFromBlock(this.func_145820_n()) & '\uffff' | var1 << 16));
      this.setHasDisplayTile(true);
   }

   public void setDisplayTileOffset(int var1) {
      this.getDataWatcher().updateObject(21, Integer.valueOf(var1));
      this.setHasDisplayTile(true);
   }

   public boolean hasDisplayTile() {
      return this.getDataWatcher().getWatchableObjectByte(22) == 1;
   }

   public void setHasDisplayTile(boolean var1) {
      this.getDataWatcher().updateObject(22, Byte.valueOf((byte)(var1?1:0)));
   }

   public void setMinecartName(String var1) {
      this.entityName = var1;
   }

   public String getCommandSenderName() {
      return this.entityName != null?this.entityName:super.getCommandSenderName();
   }

   public boolean hasCustomInventoryName() {
      return this.entityName != null;
   }

   public String func_95999_t() {
      return this.entityName;
   }

}
