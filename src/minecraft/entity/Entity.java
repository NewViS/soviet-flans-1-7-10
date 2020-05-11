package net.minecraft.entity;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.Block$SoundType;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity$1;
import net.minecraft.entity.Entity$2;
import net.minecraft.entity.Entity$EnumEntitySize;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class Entity {

   private static int nextEntityID;
   private int entityId;
   public double renderDistanceWeight;
   public boolean preventEntitySpawning;
   public Entity riddenByEntity;
   public Entity ridingEntity;
   public boolean forceSpawn;
   public World worldObj;
   public double prevPosX;
   public double prevPosY;
   public double prevPosZ;
   public double posX;
   public double posY;
   public double posZ;
   public double motionX;
   public double motionY;
   public double motionZ;
   public float rotationYaw;
   public float rotationPitch;
   public float prevRotationYaw;
   public float prevRotationPitch;
   public final AxisAlignedBB boundingBox;
   public boolean onGround;
   public boolean isCollidedHorizontally;
   public boolean isCollidedVertically;
   public boolean isCollided;
   public boolean velocityChanged;
   protected boolean isInWeb;
   public boolean field_70135_K;
   public boolean isDead;
   public float yOffset;
   public float width;
   public float height;
   public float prevDistanceWalkedModified;
   public float distanceWalkedModified;
   public float distanceWalkedOnStepModified;
   public float fallDistance;
   private int nextStepDistance;
   public double lastTickPosX;
   public double lastTickPosY;
   public double lastTickPosZ;
   public float ySize;
   public float stepHeight;
   public boolean noClip;
   public float entityCollisionReduction;
   protected Random rand;
   public int ticksExisted;
   public int fireResistance;
   private int fire;
   protected boolean inWater;
   public int hurtResistantTime;
   private boolean firstUpdate;
   protected boolean isImmuneToFire;
   protected DataWatcher dataWatcher;
   private double entityRiderPitchDelta;
   private double entityRiderYawDelta;
   public boolean addedToChunk;
   public int chunkCoordX;
   public int chunkCoordY;
   public int chunkCoordZ;
   public int serverPosX;
   public int serverPosY;
   public int serverPosZ;
   public boolean ignoreFrustumCheck;
   public boolean isAirBorne;
   public int timeUntilPortal;
   protected boolean inPortal;
   protected int portalCounter;
   public int dimension;
   protected int teleportDirection;
   private boolean invulnerable;
   protected UUID entityUniqueID;
   public Entity$EnumEntitySize myEntitySize;


   public int getEntityId() {
      return this.entityId;
   }

   public void setEntityId(int var1) {
      this.entityId = var1;
   }

   public Entity(World var1) {
      this.entityId = nextEntityID++;
      this.renderDistanceWeight = 1.0D;
      this.boundingBox = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      this.field_70135_K = true;
      this.width = 0.6F;
      this.height = 1.8F;
      this.nextStepDistance = 1;
      this.rand = new Random();
      this.fireResistance = 1;
      this.firstUpdate = true;
      this.entityUniqueID = UUID.randomUUID();
      this.myEntitySize = Entity$EnumEntitySize.SIZE_2;
      this.worldObj = var1;
      this.setPosition(0.0D, 0.0D, 0.0D);
      if(var1 != null) {
         this.dimension = var1.provider.dimensionId;
      }

      this.dataWatcher = new DataWatcher(this);
      this.dataWatcher.addObject(0, Byte.valueOf((byte)0));
      this.dataWatcher.addObject(1, Short.valueOf((short)300));
      this.entityInit();
   }

   protected abstract void entityInit();

   public DataWatcher getDataWatcher() {
      return this.dataWatcher;
   }

   public boolean equals(Object var1) {
      return var1 instanceof Entity?((Entity)var1).entityId == this.entityId:false;
   }

   public int hashCode() {
      return this.entityId;
   }

   protected void preparePlayerToSpawn() {
      if(this.worldObj != null) {
         while(this.posY > 0.0D) {
            this.setPosition(this.posX, this.posY, this.posZ);
            if(this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty()) {
               break;
            }

            ++this.posY;
         }

         this.motionX = this.motionY = this.motionZ = 0.0D;
         this.rotationPitch = 0.0F;
      }
   }

   public void setDead() {
      this.isDead = true;
   }

   protected void setSize(float var1, float var2) {
      float var3;
      if(var1 != this.width || var2 != this.height) {
         var3 = this.width;
         this.width = var1;
         this.height = var2;
         this.boundingBox.maxX = this.boundingBox.minX + (double)this.width;
         this.boundingBox.maxZ = this.boundingBox.minZ + (double)this.width;
         this.boundingBox.maxY = this.boundingBox.minY + (double)this.height;
         if(this.width > var3 && !this.firstUpdate && !this.worldObj.isRemote) {
            this.moveEntity((double)(var3 - this.width), 0.0D, (double)(var3 - this.width));
         }
      }

      var3 = var1 % 2.0F;
      if((double)var3 < 0.375D) {
         this.myEntitySize = Entity$EnumEntitySize.SIZE_1;
      } else if((double)var3 < 0.75D) {
         this.myEntitySize = Entity$EnumEntitySize.SIZE_2;
      } else if((double)var3 < 1.0D) {
         this.myEntitySize = Entity$EnumEntitySize.SIZE_3;
      } else if((double)var3 < 1.375D) {
         this.myEntitySize = Entity$EnumEntitySize.SIZE_4;
      } else if((double)var3 < 1.75D) {
         this.myEntitySize = Entity$EnumEntitySize.SIZE_5;
      } else {
         this.myEntitySize = Entity$EnumEntitySize.SIZE_6;
      }

   }

   protected void setRotation(float var1, float var2) {
      this.rotationYaw = var1 % 360.0F;
      this.rotationPitch = var2 % 360.0F;
   }

   public void setPosition(double var1, double var3, double var5) {
      this.posX = var1;
      this.posY = var3;
      this.posZ = var5;
      float var7 = this.width / 2.0F;
      float var8 = this.height;
      this.boundingBox.setBounds(var1 - (double)var7, var3 - (double)this.yOffset + (double)this.ySize, var5 - (double)var7, var1 + (double)var7, var3 - (double)this.yOffset + (double)this.ySize + (double)var8, var5 + (double)var7);
   }

   public void setAngles(float var1, float var2) {
      float var3 = this.rotationPitch;
      float var4 = this.rotationYaw;
      this.rotationYaw = (float)((double)this.rotationYaw + (double)var1 * 0.15D);
      this.rotationPitch = (float)((double)this.rotationPitch - (double)var2 * 0.15D);
      if(this.rotationPitch < -90.0F) {
         this.rotationPitch = -90.0F;
      }

      if(this.rotationPitch > 90.0F) {
         this.rotationPitch = 90.0F;
      }

      this.prevRotationPitch += this.rotationPitch - var3;
      this.prevRotationYaw += this.rotationYaw - var4;
   }

   public void onUpdate() {
      this.onEntityUpdate();
   }

   public void onEntityUpdate() {
      this.worldObj.theProfiler.startSection("entityBaseTick");
      if(this.ridingEntity != null && this.ridingEntity.isDead) {
         this.ridingEntity = null;
      }

      this.prevDistanceWalkedModified = this.distanceWalkedModified;
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.prevRotationPitch = this.rotationPitch;
      this.prevRotationYaw = this.rotationYaw;
      int var2;
      if(!this.worldObj.isRemote && this.worldObj instanceof WorldServer) {
         this.worldObj.theProfiler.startSection("portal");
         MinecraftServer var1 = ((WorldServer)this.worldObj).func_73046_m();
         var2 = this.getMaxInPortalTime();
         if(this.inPortal) {
            if(var1.getAllowNether()) {
               if(this.ridingEntity == null && this.portalCounter++ >= var2) {
                  this.portalCounter = var2;
                  this.timeUntilPortal = this.getPortalCooldown();
                  byte var3;
                  if(this.worldObj.provider.dimensionId == -1) {
                     var3 = 0;
                  } else {
                     var3 = -1;
                  }

                  this.travelToDimension(var3);
               }

               this.inPortal = false;
            }
         } else {
            if(this.portalCounter > 0) {
               this.portalCounter -= 4;
            }

            if(this.portalCounter < 0) {
               this.portalCounter = 0;
            }
         }

         if(this.timeUntilPortal > 0) {
            --this.timeUntilPortal;
         }

         this.worldObj.theProfiler.endSection();
      }

      if(this.isSprinting() && !this.isInWater()) {
         int var5 = MathHelper.floor_double(this.posX);
         var2 = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset);
         int var6 = MathHelper.floor_double(this.posZ);
         Block var4 = this.worldObj.getBlock(var5, var2, var6);
         if(var4.getMaterial() != Material.air) {
            this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(var4) + "_" + this.worldObj.getBlockMetadata(var5, var2, var6), this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.boundingBox.minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, -this.motionX * 4.0D, 1.5D, -this.motionZ * 4.0D);
         }
      }

      this.handleWaterMovement();
      if(this.worldObj.isRemote) {
         this.fire = 0;
      } else if(this.fire > 0) {
         if(this.isImmuneToFire) {
            this.fire -= 4;
            if(this.fire < 0) {
               this.fire = 0;
            }
         } else {
            if(this.fire % 20 == 0) {
               this.attackEntityFrom(DamageSource.onFire, 1.0F);
            }

            --this.fire;
         }
      }

      if(this.handleLavaMovement()) {
         this.setOnFireFromLava();
         this.fallDistance *= 0.5F;
      }

      if(this.posY < -64.0D) {
         this.kill();
      }

      if(!this.worldObj.isRemote) {
         this.setFlag(0, this.fire > 0);
      }

      this.firstUpdate = false;
      this.worldObj.theProfiler.endSection();
   }

   public int getMaxInPortalTime() {
      return 0;
   }

   protected void setOnFireFromLava() {
      if(!this.isImmuneToFire) {
         this.attackEntityFrom(DamageSource.lava, 4.0F);
         this.setFire(15);
      }

   }

   public void setFire(int var1) {
      int var2 = var1 * 20;
      var2 = EnchantmentProtection.getFireTimeForEntity(this, var2);
      if(this.fire < var2) {
         this.fire = var2;
      }

   }

   public void extinguish() {
      this.fire = 0;
   }

   protected void kill() {
      this.setDead();
   }

   public boolean isOffsetPositionInLiquid(double var1, double var3, double var5) {
      AxisAlignedBB var7 = this.boundingBox.getOffsetBoundingBox(var1, var3, var5);
      List var8 = this.worldObj.getCollidingBoundingBoxes(this, var7);
      return !var8.isEmpty()?false:!this.worldObj.isAnyLiquid(var7);
   }

   public void moveEntity(double var1, double var3, double var5) {
      if(this.noClip) {
         this.boundingBox.offset(var1, var3, var5);
         this.posX = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0D;
         this.posY = this.boundingBox.minY + (double)this.yOffset - (double)this.ySize;
         this.posZ = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0D;
      } else {
         this.worldObj.theProfiler.startSection("move");
         this.ySize *= 0.4F;
         double var7 = this.posX;
         double var9 = this.posY;
         double var11 = this.posZ;
         if(this.isInWeb) {
            this.isInWeb = false;
            var1 *= 0.25D;
            var3 *= 0.05000000074505806D;
            var5 *= 0.25D;
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
         }

         double var13 = var1;
         double var15 = var3;
         double var17 = var5;
         AxisAlignedBB var19 = this.boundingBox.copy();
         boolean var20 = this.onGround && this.isSneaking() && this instanceof EntityPlayer;
         if(var20) {
            double var21;
            for(var21 = 0.05D; var1 != 0.0D && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.getOffsetBoundingBox(var1, -1.0D, 0.0D)).isEmpty(); var13 = var1) {
               if(var1 < var21 && var1 >= -var21) {
                  var1 = 0.0D;
               } else if(var1 > 0.0D) {
                  var1 -= var21;
               } else {
                  var1 += var21;
               }
            }

            for(; var5 != 0.0D && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.getOffsetBoundingBox(0.0D, -1.0D, var5)).isEmpty(); var17 = var5) {
               if(var5 < var21 && var5 >= -var21) {
                  var5 = 0.0D;
               } else if(var5 > 0.0D) {
                  var5 -= var21;
               } else {
                  var5 += var21;
               }
            }

            while(var1 != 0.0D && var5 != 0.0D && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.getOffsetBoundingBox(var1, -1.0D, var5)).isEmpty()) {
               if(var1 < var21 && var1 >= -var21) {
                  var1 = 0.0D;
               } else if(var1 > 0.0D) {
                  var1 -= var21;
               } else {
                  var1 += var21;
               }

               if(var5 < var21 && var5 >= -var21) {
                  var5 = 0.0D;
               } else if(var5 > 0.0D) {
                  var5 -= var21;
               } else {
                  var5 += var21;
               }

               var13 = var1;
               var17 = var5;
            }
         }

         List var36 = this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.addCoord(var1, var3, var5));

         for(int var22 = 0; var22 < var36.size(); ++var22) {
            var3 = ((AxisAlignedBB)var36.get(var22)).calculateYOffset(this.boundingBox, var3);
         }

         this.boundingBox.offset(0.0D, var3, 0.0D);
         if(!this.field_70135_K && var15 != var3) {
            var5 = 0.0D;
            var3 = 0.0D;
            var1 = 0.0D;
         }

         boolean var37 = this.onGround || var15 != var3 && var15 < 0.0D;

         int var23;
         for(var23 = 0; var23 < var36.size(); ++var23) {
            var1 = ((AxisAlignedBB)var36.get(var23)).calculateXOffset(this.boundingBox, var1);
         }

         this.boundingBox.offset(var1, 0.0D, 0.0D);
         if(!this.field_70135_K && var13 != var1) {
            var5 = 0.0D;
            var3 = 0.0D;
            var1 = 0.0D;
         }

         for(var23 = 0; var23 < var36.size(); ++var23) {
            var5 = ((AxisAlignedBB)var36.get(var23)).calculateZOffset(this.boundingBox, var5);
         }

         this.boundingBox.offset(0.0D, 0.0D, var5);
         if(!this.field_70135_K && var17 != var5) {
            var5 = 0.0D;
            var3 = 0.0D;
            var1 = 0.0D;
         }

         double var25;
         double var27;
         int var30;
         double var38;
         if(this.stepHeight > 0.0F && var37 && (var20 || this.ySize < 0.05F) && (var13 != var1 || var17 != var5)) {
            var38 = var1;
            var25 = var3;
            var27 = var5;
            var1 = var13;
            var3 = (double)this.stepHeight;
            var5 = var17;
            AxisAlignedBB var29 = this.boundingBox.copy();
            this.boundingBox.setBB(var19);
            var36 = this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.addCoord(var13, var3, var17));

            for(var30 = 0; var30 < var36.size(); ++var30) {
               var3 = ((AxisAlignedBB)var36.get(var30)).calculateYOffset(this.boundingBox, var3);
            }

            this.boundingBox.offset(0.0D, var3, 0.0D);
            if(!this.field_70135_K && var15 != var3) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            }

            for(var30 = 0; var30 < var36.size(); ++var30) {
               var1 = ((AxisAlignedBB)var36.get(var30)).calculateXOffset(this.boundingBox, var1);
            }

            this.boundingBox.offset(var1, 0.0D, 0.0D);
            if(!this.field_70135_K && var13 != var1) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            }

            for(var30 = 0; var30 < var36.size(); ++var30) {
               var5 = ((AxisAlignedBB)var36.get(var30)).calculateZOffset(this.boundingBox, var5);
            }

            this.boundingBox.offset(0.0D, 0.0D, var5);
            if(!this.field_70135_K && var17 != var5) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            }

            if(!this.field_70135_K && var15 != var3) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            } else {
               var3 = (double)(-this.stepHeight);

               for(var30 = 0; var30 < var36.size(); ++var30) {
                  var3 = ((AxisAlignedBB)var36.get(var30)).calculateYOffset(this.boundingBox, var3);
               }

               this.boundingBox.offset(0.0D, var3, 0.0D);
            }

            if(var38 * var38 + var27 * var27 >= var1 * var1 + var5 * var5) {
               var1 = var38;
               var3 = var25;
               var5 = var27;
               this.boundingBox.setBB(var29);
            }
         }

         this.worldObj.theProfiler.endSection();
         this.worldObj.theProfiler.startSection("rest");
         this.posX = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0D;
         this.posY = this.boundingBox.minY + (double)this.yOffset - (double)this.ySize;
         this.posZ = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0D;
         this.isCollidedHorizontally = var13 != var1 || var17 != var5;
         this.isCollidedVertically = var15 != var3;
         this.onGround = var15 != var3 && var15 < 0.0D;
         this.isCollided = this.isCollidedHorizontally || this.isCollidedVertically;
         this.updateFallState(var3, this.onGround);
         if(var13 != var1) {
            this.motionX = 0.0D;
         }

         if(var15 != var3) {
            this.motionY = 0.0D;
         }

         if(var17 != var5) {
            this.motionZ = 0.0D;
         }

         var38 = this.posX - var7;
         var25 = this.posY - var9;
         var27 = this.posZ - var11;
         if(this.canTriggerWalking() && !var20 && this.ridingEntity == null) {
            int var39 = MathHelper.floor_double(this.posX);
            var30 = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset);
            int var31 = MathHelper.floor_double(this.posZ);
            Block var32 = this.worldObj.getBlock(var39, var30, var31);
            int var33 = this.worldObj.getBlock(var39, var30 - 1, var31).getRenderType();
            if(var33 == 11 || var33 == 32 || var33 == 21) {
               var32 = this.worldObj.getBlock(var39, var30 - 1, var31);
            }

            if(var32 != Blocks.ladder) {
               var25 = 0.0D;
            }

            this.distanceWalkedModified = (float)((double)this.distanceWalkedModified + (double)MathHelper.sqrt_double(var38 * var38 + var27 * var27) * 0.6D);
            this.distanceWalkedOnStepModified = (float)((double)this.distanceWalkedOnStepModified + (double)MathHelper.sqrt_double(var38 * var38 + var25 * var25 + var27 * var27) * 0.6D);
            if(this.distanceWalkedOnStepModified > (float)this.nextStepDistance && var32.getMaterial() != Material.air) {
               this.nextStepDistance = (int)this.distanceWalkedOnStepModified + 1;
               if(this.isInWater()) {
                  float var34 = MathHelper.sqrt_double(this.motionX * this.motionX * 0.20000000298023224D + this.motionY * this.motionY + this.motionZ * this.motionZ * 0.20000000298023224D) * 0.35F;
                  if(var34 > 1.0F) {
                     var34 = 1.0F;
                  }

                  this.playSound(this.getSwimSound(), var34, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
               }

               this.func_145780_a(var39, var30, var31, var32);
               var32.onEntityWalking(this.worldObj, var39, var30, var31, this);
            }
         }

         try {
            this.func_145775_I();
         } catch (Throwable var35) {
            CrashReport var42 = CrashReport.makeCrashReport(var35, "Checking entity block collision");
            CrashReportCategory var41 = var42.makeCategory("Entity being checked for collision");
            this.addEntityCrashInfo(var41);
            throw new ReportedException(var42);
         }

         boolean var40 = this.isWet();
         if(this.worldObj.func_147470_e(this.boundingBox.contract(0.001D, 0.001D, 0.001D))) {
            this.dealFireDamage(1);
            if(!var40) {
               ++this.fire;
               if(this.fire == 0) {
                  this.setFire(8);
               }
            }
         } else if(this.fire <= 0) {
            this.fire = -this.fireResistance;
         }

         if(var40 && this.fire > 0) {
            this.playSound("random.fizz", 0.7F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
            this.fire = -this.fireResistance;
         }

         this.worldObj.theProfiler.endSection();
      }
   }

   protected String getSwimSound() {
      return "game.neutral.swim";
   }

   protected void func_145775_I() {
      int var1 = MathHelper.floor_double(this.boundingBox.minX + 0.001D);
      int var2 = MathHelper.floor_double(this.boundingBox.minY + 0.001D);
      int var3 = MathHelper.floor_double(this.boundingBox.minZ + 0.001D);
      int var4 = MathHelper.floor_double(this.boundingBox.maxX - 0.001D);
      int var5 = MathHelper.floor_double(this.boundingBox.maxY - 0.001D);
      int var6 = MathHelper.floor_double(this.boundingBox.maxZ - 0.001D);
      if(this.worldObj.checkChunksExist(var1, var2, var3, var4, var5, var6)) {
         for(int var7 = var1; var7 <= var4; ++var7) {
            for(int var8 = var2; var8 <= var5; ++var8) {
               for(int var9 = var3; var9 <= var6; ++var9) {
                  Block var10 = this.worldObj.getBlock(var7, var8, var9);

                  try {
                     var10.onEntityCollidedWithBlock(this.worldObj, var7, var8, var9, this);
                  } catch (Throwable var14) {
                     CrashReport var12 = CrashReport.makeCrashReport(var14, "Colliding entity with block");
                     CrashReportCategory var13 = var12.makeCategory("Block being collided with");
                     CrashReportCategory.func_147153_a(var13, var7, var8, var9, var10, this.worldObj.getBlockMetadata(var7, var8, var9));
                     throw new ReportedException(var12);
                  }
               }
            }
         }
      }

   }

   protected void func_145780_a(int var1, int var2, int var3, Block var4) {
      Block$SoundType var5 = var4.stepSound;
      if(this.worldObj.getBlock(var1, var2 + 1, var3) == Blocks.snow_layer) {
         var5 = Blocks.snow_layer.stepSound;
         this.playSound(var5.getStepResourcePath(), var5.getVolume() * 0.15F, var5.getPitch());
      } else if(!var4.getMaterial().isLiquid()) {
         this.playSound(var5.getStepResourcePath(), var5.getVolume() * 0.15F, var5.getPitch());
      }

   }

   public void playSound(String var1, float var2, float var3) {
      this.worldObj.playSoundAtEntity(this, var1, var2, var3);
   }

   protected boolean canTriggerWalking() {
      return true;
   }

   protected void updateFallState(double var1, boolean var3) {
      if(var3) {
         if(this.fallDistance > 0.0F) {
            this.fall(this.fallDistance);
            this.fallDistance = 0.0F;
         }
      } else if(var1 < 0.0D) {
         this.fallDistance = (float)((double)this.fallDistance - var1);
      }

   }

   public AxisAlignedBB getBoundingBox() {
      return null;
   }

   protected void dealFireDamage(int var1) {
      if(!this.isImmuneToFire) {
         this.attackEntityFrom(DamageSource.inFire, (float)var1);
      }

   }

   public final boolean isImmuneToFire() {
      return this.isImmuneToFire;
   }

   protected void fall(float var1) {
      if(this.riddenByEntity != null) {
         this.riddenByEntity.fall(var1);
      }

   }

   public boolean isWet() {
      return this.inWater || this.worldObj.canLightningStrikeAt(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) || this.worldObj.canLightningStrikeAt(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + (double)this.height), MathHelper.floor_double(this.posZ));
   }

   public boolean isInWater() {
      return this.inWater;
   }

   public boolean handleWaterMovement() {
      if(this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.4000000059604645D, 0.0D).contract(0.001D, 0.001D, 0.001D), Material.water, this)) {
         if(!this.inWater && !this.firstUpdate) {
            float var1 = MathHelper.sqrt_double(this.motionX * this.motionX * 0.20000000298023224D + this.motionY * this.motionY + this.motionZ * this.motionZ * 0.20000000298023224D) * 0.2F;
            if(var1 > 1.0F) {
               var1 = 1.0F;
            }

            this.playSound(this.getSplashSound(), var1, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
            float var2 = (float)MathHelper.floor_double(this.boundingBox.minY);

            int var3;
            float var4;
            float var5;
            for(var3 = 0; (float)var3 < 1.0F + this.width * 20.0F; ++var3) {
               var4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
               var5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
               this.worldObj.spawnParticle("bubble", this.posX + (double)var4, (double)(var2 + 1.0F), this.posZ + (double)var5, this.motionX, this.motionY - (double)(this.rand.nextFloat() * 0.2F), this.motionZ);
            }

            for(var3 = 0; (float)var3 < 1.0F + this.width * 20.0F; ++var3) {
               var4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
               var5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
               this.worldObj.spawnParticle("splash", this.posX + (double)var4, (double)(var2 + 1.0F), this.posZ + (double)var5, this.motionX, this.motionY, this.motionZ);
            }
         }

         this.fallDistance = 0.0F;
         this.inWater = true;
         this.fire = 0;
      } else {
         this.inWater = false;
      }

      return this.inWater;
   }

   protected String getSplashSound() {
      return "game.neutral.swim.splash";
   }

   public boolean isInsideOfMaterial(Material var1) {
      double var2 = this.posY + (double)this.getEyeHeight();
      int var4 = MathHelper.floor_double(this.posX);
      int var5 = MathHelper.floor_float((float)MathHelper.floor_double(var2));
      int var6 = MathHelper.floor_double(this.posZ);
      Block var7 = this.worldObj.getBlock(var4, var5, var6);
      if(var7.getMaterial() == var1) {
         float var8 = BlockLiquid.getLiquidHeightPercent(this.worldObj.getBlockMetadata(var4, var5, var6)) - 0.11111111F;
         float var9 = (float)(var5 + 1) - var8;
         return var2 < (double)var9;
      } else {
         return false;
      }
   }

   public float getEyeHeight() {
      return 0.0F;
   }

   public boolean handleLavaMovement() {
      return this.worldObj.isMaterialInBB(this.boundingBox.expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.lava);
   }

   public void moveFlying(float var1, float var2, float var3) {
      float var4 = var1 * var1 + var2 * var2;
      if(var4 >= 1.0E-4F) {
         var4 = MathHelper.sqrt_float(var4);
         if(var4 < 1.0F) {
            var4 = 1.0F;
         }

         var4 = var3 / var4;
         var1 *= var4;
         var2 *= var4;
         float var5 = MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F);
         float var6 = MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F);
         this.motionX += (double)(var1 * var6 - var2 * var5);
         this.motionZ += (double)(var2 * var6 + var1 * var5);
      }
   }

   public int getBrightnessForRender(float var1) {
      int var2 = MathHelper.floor_double(this.posX);
      int var3 = MathHelper.floor_double(this.posZ);
      if(this.worldObj.blockExists(var2, 0, var3)) {
         double var4 = (this.boundingBox.maxY - this.boundingBox.minY) * 0.66D;
         int var6 = MathHelper.floor_double(this.posY - (double)this.yOffset + var4);
         return this.worldObj.getLightBrightnessForSkyBlocks(var2, var6, var3, 0);
      } else {
         return 0;
      }
   }

   public float getBrightness(float var1) {
      int var2 = MathHelper.floor_double(this.posX);
      int var3 = MathHelper.floor_double(this.posZ);
      if(this.worldObj.blockExists(var2, 0, var3)) {
         double var4 = (this.boundingBox.maxY - this.boundingBox.minY) * 0.66D;
         int var6 = MathHelper.floor_double(this.posY - (double)this.yOffset + var4);
         return this.worldObj.getLightBrightness(var2, var6, var3);
      } else {
         return 0.0F;
      }
   }

   public void setWorld(World var1) {
      this.worldObj = var1;
   }

   public void setPositionAndRotation(double var1, double var3, double var5, float var7, float var8) {
      this.prevPosX = this.posX = var1;
      this.prevPosY = this.posY = var3;
      this.prevPosZ = this.posZ = var5;
      this.prevRotationYaw = this.rotationYaw = var7;
      this.prevRotationPitch = this.rotationPitch = var8;
      this.ySize = 0.0F;
      double var9 = (double)(this.prevRotationYaw - var7);
      if(var9 < -180.0D) {
         this.prevRotationYaw += 360.0F;
      }

      if(var9 >= 180.0D) {
         this.prevRotationYaw -= 360.0F;
      }

      this.setPosition(this.posX, this.posY, this.posZ);
      this.setRotation(var7, var8);
   }

   public void setLocationAndAngles(double var1, double var3, double var5, float var7, float var8) {
      this.lastTickPosX = this.prevPosX = this.posX = var1;
      this.lastTickPosY = this.prevPosY = this.posY = var3 + (double)this.yOffset;
      this.lastTickPosZ = this.prevPosZ = this.posZ = var5;
      this.rotationYaw = var7;
      this.rotationPitch = var8;
      this.setPosition(this.posX, this.posY, this.posZ);
   }

   public float getDistanceToEntity(Entity var1) {
      float var2 = (float)(this.posX - var1.posX);
      float var3 = (float)(this.posY - var1.posY);
      float var4 = (float)(this.posZ - var1.posZ);
      return MathHelper.sqrt_float(var2 * var2 + var3 * var3 + var4 * var4);
   }

   public double getDistanceSq(double var1, double var3, double var5) {
      double var7 = this.posX - var1;
      double var9 = this.posY - var3;
      double var11 = this.posZ - var5;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   public double getDistance(double var1, double var3, double var5) {
      double var7 = this.posX - var1;
      double var9 = this.posY - var3;
      double var11 = this.posZ - var5;
      return (double)MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
   }

   public double getDistanceSqToEntity(Entity var1) {
      double var2 = this.posX - var1.posX;
      double var4 = this.posY - var1.posY;
      double var6 = this.posZ - var1.posZ;
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public void onCollideWithPlayer(EntityPlayer var1) {}

   public void applyEntityCollision(Entity var1) {
      if(var1.riddenByEntity != this && var1.ridingEntity != this) {
         double var2 = var1.posX - this.posX;
         double var4 = var1.posZ - this.posZ;
         double var6 = MathHelper.abs_max(var2, var4);
         if(var6 >= 0.009999999776482582D) {
            var6 = (double)MathHelper.sqrt_double(var6);
            var2 /= var6;
            var4 /= var6;
            double var8 = 1.0D / var6;
            if(var8 > 1.0D) {
               var8 = 1.0D;
            }

            var2 *= var8;
            var4 *= var8;
            var2 *= 0.05000000074505806D;
            var4 *= 0.05000000074505806D;
            var2 *= (double)(1.0F - this.entityCollisionReduction);
            var4 *= (double)(1.0F - this.entityCollisionReduction);
            this.addVelocity(-var2, 0.0D, -var4);
            var1.addVelocity(var2, 0.0D, var4);
         }

      }
   }

   public void addVelocity(double var1, double var3, double var5) {
      this.motionX += var1;
      this.motionY += var3;
      this.motionZ += var5;
      this.isAirBorne = true;
   }

   protected void setBeenAttacked() {
      this.velocityChanged = true;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         this.setBeenAttacked();
         return false;
      }
   }

   public boolean canBeCollidedWith() {
      return false;
   }

   public boolean canBePushed() {
      return false;
   }

   public void addToPlayerScore(Entity var1, int var2) {}

   public boolean isInRangeToRender3d(double var1, double var3, double var5) {
      double var7 = this.posX - var1;
      double var9 = this.posY - var3;
      double var11 = this.posZ - var5;
      double var13 = var7 * var7 + var9 * var9 + var11 * var11;
      return this.isInRangeToRenderDist(var13);
   }

   public boolean isInRangeToRenderDist(double var1) {
      double var3 = this.boundingBox.getAverageEdgeLength();
      var3 *= 64.0D * this.renderDistanceWeight;
      return var1 < var3 * var3;
   }

   public boolean writeMountToNBT(NBTTagCompound var1) {
      String var2 = this.getEntityString();
      if(!this.isDead && var2 != null) {
         var1.setString("id", var2);
         this.writeToNBT(var1);
         return true;
      } else {
         return false;
      }
   }

   public boolean writeToNBTOptional(NBTTagCompound var1) {
      String var2 = this.getEntityString();
      if(!this.isDead && var2 != null && this.riddenByEntity == null) {
         var1.setString("id", var2);
         this.writeToNBT(var1);
         return true;
      } else {
         return false;
      }
   }

   public void writeToNBT(NBTTagCompound var1) {
      try {
         var1.setTag("Pos", this.newDoubleNBTList(new double[]{this.posX, this.posY + (double)this.ySize, this.posZ}));
         var1.setTag("Motion", this.newDoubleNBTList(new double[]{this.motionX, this.motionY, this.motionZ}));
         var1.setTag("Rotation", this.newFloatNBTList(new float[]{this.rotationYaw, this.rotationPitch}));
         var1.setFloat("FallDistance", this.fallDistance);
         var1.setShort("Fire", (short)this.fire);
         var1.setShort("Air", (short)this.getAir());
         var1.setBoolean("OnGround", this.onGround);
         var1.setInteger("Dimension", this.dimension);
         var1.setBoolean("Invulnerable", this.invulnerable);
         var1.setInteger("PortalCooldown", this.timeUntilPortal);
         var1.setLong("UUIDMost", this.getUniqueID().getMostSignificantBits());
         var1.setLong("UUIDLeast", this.getUniqueID().getLeastSignificantBits());
         this.writeEntityToNBT(var1);
         if(this.ridingEntity != null) {
            NBTTagCompound var2 = new NBTTagCompound();
            if(this.ridingEntity.writeMountToNBT(var2)) {
               var1.setTag("Riding", var2);
            }
         }

      } catch (Throwable var5) {
         CrashReport var3 = CrashReport.makeCrashReport(var5, "Saving entity NBT");
         CrashReportCategory var4 = var3.makeCategory("Entity being saved");
         this.addEntityCrashInfo(var4);
         throw new ReportedException(var3);
      }
   }

   public void readFromNBT(NBTTagCompound var1) {
      try {
         NBTTagList var2 = var1.getTagList("Pos", 6);
         NBTTagList var6 = var1.getTagList("Motion", 6);
         NBTTagList var7 = var1.getTagList("Rotation", 5);
         this.motionX = var6.func_150309_d(0);
         this.motionY = var6.func_150309_d(1);
         this.motionZ = var6.func_150309_d(2);
         if(Math.abs(this.motionX) > 10.0D) {
            this.motionX = 0.0D;
         }

         if(Math.abs(this.motionY) > 10.0D) {
            this.motionY = 0.0D;
         }

         if(Math.abs(this.motionZ) > 10.0D) {
            this.motionZ = 0.0D;
         }

         this.prevPosX = this.lastTickPosX = this.posX = var2.func_150309_d(0);
         this.prevPosY = this.lastTickPosY = this.posY = var2.func_150309_d(1);
         this.prevPosZ = this.lastTickPosZ = this.posZ = var2.func_150309_d(2);
         this.prevRotationYaw = this.rotationYaw = var7.func_150308_e(0);
         this.prevRotationPitch = this.rotationPitch = var7.func_150308_e(1);
         this.fallDistance = var1.getFloat("FallDistance");
         this.fire = var1.getShort("Fire");
         this.setAir(var1.getShort("Air"));
         this.onGround = var1.getBoolean("OnGround");
         this.dimension = var1.getInteger("Dimension");
         this.invulnerable = var1.getBoolean("Invulnerable");
         this.timeUntilPortal = var1.getInteger("PortalCooldown");
         if(var1.hasKey("UUIDMost", 4) && var1.hasKey("UUIDLeast", 4)) {
            this.entityUniqueID = new UUID(var1.getLong("UUIDMost"), var1.getLong("UUIDLeast"));
         }

         this.setPosition(this.posX, this.posY, this.posZ);
         this.setRotation(this.rotationYaw, this.rotationPitch);
         this.readEntityFromNBT(var1);
         if(this.shouldSetPosAfterLoading()) {
            this.setPosition(this.posX, this.posY, this.posZ);
         }

      } catch (Throwable var5) {
         CrashReport var3 = CrashReport.makeCrashReport(var5, "Loading entity NBT");
         CrashReportCategory var4 = var3.makeCategory("Entity being loaded");
         this.addEntityCrashInfo(var4);
         throw new ReportedException(var3);
      }
   }

   protected boolean shouldSetPosAfterLoading() {
      return true;
   }

   protected final String getEntityString() {
      return EntityList.getEntityString(this);
   }

   protected abstract void readEntityFromNBT(NBTTagCompound var1);

   protected abstract void writeEntityToNBT(NBTTagCompound var1);

   public void onChunkLoad() {}

   protected NBTTagList newDoubleNBTList(double ... var1) {
      NBTTagList var2 = new NBTTagList();
      double[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         double var6 = var3[var5];
         var2.appendTag(new NBTTagDouble(var6));
      }

      return var2;
   }

   protected NBTTagList newFloatNBTList(float ... var1) {
      NBTTagList var2 = new NBTTagList();
      float[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         float var6 = var3[var5];
         var2.appendTag(new NBTTagFloat(var6));
      }

      return var2;
   }

   public float getShadowSize() {
      return this.height / 2.0F;
   }

   public EntityItem dropItem(Item var1, int var2) {
      return this.func_145778_a(var1, var2, 0.0F);
   }

   public EntityItem func_145778_a(Item var1, int var2, float var3) {
      return this.entityDropItem(new ItemStack(var1, var2, 0), var3);
   }

   public EntityItem entityDropItem(ItemStack var1, float var2) {
      if(var1.stackSize != 0 && var1.getItem() != null) {
         EntityItem var3 = new EntityItem(this.worldObj, this.posX, this.posY + (double)var2, this.posZ, var1);
         var3.delayBeforeCanPickup = 10;
         this.worldObj.spawnEntityInWorld(var3);
         return var3;
      } else {
         return null;
      }
   }

   public boolean isEntityAlive() {
      return !this.isDead;
   }

   public boolean isEntityInsideOpaqueBlock() {
      for(int var1 = 0; var1 < 8; ++var1) {
         float var2 = ((float)((var1 >> 0) % 2) - 0.5F) * this.width * 0.8F;
         float var3 = ((float)((var1 >> 1) % 2) - 0.5F) * 0.1F;
         float var4 = ((float)((var1 >> 2) % 2) - 0.5F) * this.width * 0.8F;
         int var5 = MathHelper.floor_double(this.posX + (double)var2);
         int var6 = MathHelper.floor_double(this.posY + (double)this.getEyeHeight() + (double)var3);
         int var7 = MathHelper.floor_double(this.posZ + (double)var4);
         if(this.worldObj.getBlock(var5, var6, var7).isNormalCube()) {
            return true;
         }
      }

      return false;
   }

   public boolean interactFirst(EntityPlayer var1) {
      return false;
   }

   public AxisAlignedBB getCollisionBox(Entity var1) {
      return null;
   }

   public void updateRidden() {
      if(this.ridingEntity.isDead) {
         this.ridingEntity = null;
      } else {
         this.motionX = 0.0D;
         this.motionY = 0.0D;
         this.motionZ = 0.0D;
         this.onUpdate();
         if(this.ridingEntity != null) {
            this.ridingEntity.updateRiderPosition();
            this.entityRiderYawDelta += (double)(this.ridingEntity.rotationYaw - this.ridingEntity.prevRotationYaw);

            for(this.entityRiderPitchDelta += (double)(this.ridingEntity.rotationPitch - this.ridingEntity.prevRotationPitch); this.entityRiderYawDelta >= 180.0D; this.entityRiderYawDelta -= 360.0D) {
               ;
            }

            while(this.entityRiderYawDelta < -180.0D) {
               this.entityRiderYawDelta += 360.0D;
            }

            while(this.entityRiderPitchDelta >= 180.0D) {
               this.entityRiderPitchDelta -= 360.0D;
            }

            while(this.entityRiderPitchDelta < -180.0D) {
               this.entityRiderPitchDelta += 360.0D;
            }

            double var1 = this.entityRiderYawDelta * 0.5D;
            double var3 = this.entityRiderPitchDelta * 0.5D;
            float var5 = 10.0F;
            if(var1 > (double)var5) {
               var1 = (double)var5;
            }

            if(var1 < (double)(-var5)) {
               var1 = (double)(-var5);
            }

            if(var3 > (double)var5) {
               var3 = (double)var5;
            }

            if(var3 < (double)(-var5)) {
               var3 = (double)(-var5);
            }

            this.entityRiderYawDelta -= var1;
            this.entityRiderPitchDelta -= var3;
         }
      }
   }

   public void updateRiderPosition() {
      if(this.riddenByEntity != null) {
         this.riddenByEntity.setPosition(this.posX, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ);
      }
   }

   public double getYOffset() {
      return (double)this.yOffset;
   }

   public double getMountedYOffset() {
      return (double)this.height * 0.75D;
   }

   public void mountEntity(Entity var1) {
      this.entityRiderPitchDelta = 0.0D;
      this.entityRiderYawDelta = 0.0D;
      if(var1 == null) {
         if(this.ridingEntity != null) {
            this.setLocationAndAngles(this.ridingEntity.posX, this.ridingEntity.boundingBox.minY + (double)this.ridingEntity.height, this.ridingEntity.posZ, this.rotationYaw, this.rotationPitch);
            this.ridingEntity.riddenByEntity = null;
         }

         this.ridingEntity = null;
      } else {
         if(this.ridingEntity != null) {
            this.ridingEntity.riddenByEntity = null;
         }

         if(var1 != null) {
            for(Entity var2 = var1.ridingEntity; var2 != null; var2 = var2.ridingEntity) {
               if(var2 == this) {
                  return;
               }
            }
         }

         this.ridingEntity = var1;
         var1.riddenByEntity = this;
      }
   }

   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9) {
      this.setPosition(var1, var3, var5);
      this.setRotation(var7, var8);
      List var10 = this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox.contract(0.03125D, 0.0D, 0.03125D));
      if(!var10.isEmpty()) {
         double var11 = 0.0D;

         for(int var13 = 0; var13 < var10.size(); ++var13) {
            AxisAlignedBB var14 = (AxisAlignedBB)var10.get(var13);
            if(var14.maxY > var11) {
               var11 = var14.maxY;
            }
         }

         var3 += var11 - this.boundingBox.minY;
         this.setPosition(var1, var3, var5);
      }

   }

   public float getCollisionBorderSize() {
      return 0.1F;
   }

   public Vec3 getLookVec() {
      return null;
   }

   public void setInPortal() {
      if(this.timeUntilPortal > 0) {
         this.timeUntilPortal = this.getPortalCooldown();
      } else {
         double var1 = this.prevPosX - this.posX;
         double var3 = this.prevPosZ - this.posZ;
         if(!this.worldObj.isRemote && !this.inPortal) {
            this.teleportDirection = Direction.getMovementDirection(var1, var3);
         }

         this.inPortal = true;
      }
   }

   public int getPortalCooldown() {
      return 300;
   }

   public void setVelocity(double var1, double var3, double var5) {
      this.motionX = var1;
      this.motionY = var3;
      this.motionZ = var5;
   }

   public void handleHealthUpdate(byte var1) {}

   public void performHurtAnimation() {}

   public ItemStack[] getLastActiveItems() {
      return null;
   }

   public void setCurrentItemOrArmor(int var1, ItemStack var2) {}

   public boolean isBurning() {
      boolean var1 = this.worldObj != null && this.worldObj.isRemote;
      return !this.isImmuneToFire && (this.fire > 0 || var1 && this.getFlag(0));
   }

   public boolean isRiding() {
      return this.ridingEntity != null;
   }

   public boolean isSneaking() {
      return this.getFlag(1);
   }

   public void setSneaking(boolean var1) {
      this.setFlag(1, var1);
   }

   public boolean isSprinting() {
      return this.getFlag(3);
   }

   public void setSprinting(boolean var1) {
      this.setFlag(3, var1);
   }

   public boolean isInvisible() {
      return this.getFlag(5);
   }

   public boolean isInvisibleToPlayer(EntityPlayer var1) {
      return this.isInvisible();
   }

   public void setInvisible(boolean var1) {
      this.setFlag(5, var1);
   }

   public boolean isEating() {
      return this.getFlag(4);
   }

   public void setEating(boolean var1) {
      this.setFlag(4, var1);
   }

   protected boolean getFlag(int var1) {
      return (this.dataWatcher.getWatchableObjectByte(0) & 1 << var1) != 0;
   }

   protected void setFlag(int var1, boolean var2) {
      byte var3 = this.dataWatcher.getWatchableObjectByte(0);
      if(var2) {
         this.dataWatcher.updateObject(0, Byte.valueOf((byte)(var3 | 1 << var1)));
      } else {
         this.dataWatcher.updateObject(0, Byte.valueOf((byte)(var3 & ~(1 << var1))));
      }

   }

   public int getAir() {
      return this.dataWatcher.getWatchableObjectShort(1);
   }

   public void setAir(int var1) {
      this.dataWatcher.updateObject(1, Short.valueOf((short)var1));
   }

   public void onStruckByLightning(EntityLightningBolt var1) {
      this.dealFireDamage(5);
      ++this.fire;
      if(this.fire == 0) {
         this.setFire(8);
      }

   }

   public void onKillEntity(EntityLivingBase var1) {}

   protected boolean func_145771_j(double var1, double var3, double var5) {
      int var7 = MathHelper.floor_double(var1);
      int var8 = MathHelper.floor_double(var3);
      int var9 = MathHelper.floor_double(var5);
      double var10 = var1 - (double)var7;
      double var12 = var3 - (double)var8;
      double var14 = var5 - (double)var9;
      List var16 = this.worldObj.func_147461_a(this.boundingBox);
      if(var16.isEmpty() && !this.worldObj.func_147469_q(var7, var8, var9)) {
         return false;
      } else {
         boolean var17 = !this.worldObj.func_147469_q(var7 - 1, var8, var9);
         boolean var18 = !this.worldObj.func_147469_q(var7 + 1, var8, var9);
         boolean var19 = !this.worldObj.func_147469_q(var7, var8 - 1, var9);
         boolean var20 = !this.worldObj.func_147469_q(var7, var8 + 1, var9);
         boolean var21 = !this.worldObj.func_147469_q(var7, var8, var9 - 1);
         boolean var22 = !this.worldObj.func_147469_q(var7, var8, var9 + 1);
         byte var23 = 3;
         double var24 = 9999.0D;
         if(var17 && var10 < var24) {
            var24 = var10;
            var23 = 0;
         }

         if(var18 && 1.0D - var10 < var24) {
            var24 = 1.0D - var10;
            var23 = 1;
         }

         if(var20 && 1.0D - var12 < var24) {
            var24 = 1.0D - var12;
            var23 = 3;
         }

         if(var21 && var14 < var24) {
            var24 = var14;
            var23 = 4;
         }

         if(var22 && 1.0D - var14 < var24) {
            var24 = 1.0D - var14;
            var23 = 5;
         }

         float var26 = this.rand.nextFloat() * 0.2F + 0.1F;
         if(var23 == 0) {
            this.motionX = (double)(-var26);
         }

         if(var23 == 1) {
            this.motionX = (double)var26;
         }

         if(var23 == 2) {
            this.motionY = (double)(-var26);
         }

         if(var23 == 3) {
            this.motionY = (double)var26;
         }

         if(var23 == 4) {
            this.motionZ = (double)(-var26);
         }

         if(var23 == 5) {
            this.motionZ = (double)var26;
         }

         return true;
      }
   }

   public void setInWeb() {
      this.isInWeb = true;
      this.fallDistance = 0.0F;
   }

   public String getCommandSenderName() {
      String var1 = EntityList.getEntityString(this);
      if(var1 == null) {
         var1 = "generic";
      }

      return StatCollector.translateToLocal("entity." + var1 + ".name");
   }

   public Entity[] getParts() {
      return null;
   }

   public boolean isEntityEqual(Entity var1) {
      return this == var1;
   }

   public float getRotationYawHead() {
      return 0.0F;
   }

   public void setRotationYawHead(float var1) {}

   public boolean canAttackWithItem() {
      return true;
   }

   public boolean hitByEntity(Entity var1) {
      return false;
   }

   public String toString() {
      return String.format("%s[\'%s\'/%d, l=\'%s\', x=%.2f, y=%.2f, z=%.2f]", new Object[]{this.getClass().getSimpleName(), this.getCommandSenderName(), Integer.valueOf(this.entityId), this.worldObj == null?"~NULL~":this.worldObj.getWorldInfo().getWorldName(), Double.valueOf(this.posX), Double.valueOf(this.posY), Double.valueOf(this.posZ)});
   }

   public boolean isEntityInvulnerable() {
      return this.invulnerable;
   }

   public void copyLocationAndAnglesFrom(Entity var1) {
      this.setLocationAndAngles(var1.posX, var1.posY, var1.posZ, var1.rotationYaw, var1.rotationPitch);
   }

   public void copyDataFrom(Entity var1, boolean var2) {
      NBTTagCompound var3 = new NBTTagCompound();
      var1.writeToNBT(var3);
      this.readFromNBT(var3);
      this.timeUntilPortal = var1.timeUntilPortal;
      this.teleportDirection = var1.teleportDirection;
   }

   public void travelToDimension(int var1) {
      if(!this.worldObj.isRemote && !this.isDead) {
         this.worldObj.theProfiler.startSection("changeDimension");
         MinecraftServer var2 = MinecraftServer.getServer();
         int var3 = this.dimension;
         WorldServer var4 = var2.worldServerForDimension(var3);
         WorldServer var5 = var2.worldServerForDimension(var1);
         this.dimension = var1;
         if(var3 == 1 && var1 == 1) {
            var5 = var2.worldServerForDimension(0);
            this.dimension = 0;
         }

         this.worldObj.removeEntity(this);
         this.isDead = false;
         this.worldObj.theProfiler.startSection("reposition");
         var2.getConfigurationManager().transferEntityToWorld(this, var3, var4, var5);
         this.worldObj.theProfiler.endStartSection("reloading");
         Entity var6 = EntityList.createEntityByName(EntityList.getEntityString(this), var5);
         if(var6 != null) {
            var6.copyDataFrom(this, true);
            if(var3 == 1 && var1 == 1) {
               ChunkCoordinates var7 = var5.getSpawnPoint();
               var7.posY = this.worldObj.getTopSolidOrLiquidBlock(var7.posX, var7.posZ);
               var6.setLocationAndAngles((double)var7.posX, (double)var7.posY, (double)var7.posZ, var6.rotationYaw, var6.rotationPitch);
            }

            var5.spawnEntityInWorld(var6);
         }

         this.isDead = true;
         this.worldObj.theProfiler.endSection();
         var4.resetUpdateEntityTick();
         var5.resetUpdateEntityTick();
         this.worldObj.theProfiler.endSection();
      }
   }

   public float func_145772_a(Explosion var1, World var2, int var3, int var4, int var5, Block var6) {
      return var6.getExplosionResistance(this);
   }

   public boolean func_145774_a(Explosion var1, World var2, int var3, int var4, int var5, Block var6, float var7) {
      return true;
   }

   public int getMaxSafePointTries() {
      return 3;
   }

   public int getTeleportDirection() {
      return this.teleportDirection;
   }

   public boolean doesEntityNotTriggerPressurePlate() {
      return false;
   }

   public void addEntityCrashInfo(CrashReportCategory var1) {
      var1.addCrashSectionCallable("Entity Type", new Entity$1(this));
      var1.addCrashSection("Entity ID", Integer.valueOf(this.entityId));
      var1.addCrashSectionCallable("Entity Name", new Entity$2(this));
      var1.addCrashSection("Entity\'s Exact location", String.format("%.2f, %.2f, %.2f", new Object[]{Double.valueOf(this.posX), Double.valueOf(this.posY), Double.valueOf(this.posZ)}));
      var1.addCrashSection("Entity\'s Block location", CrashReportCategory.getLocationInfo(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)));
      var1.addCrashSection("Entity\'s Momentum", String.format("%.2f, %.2f, %.2f", new Object[]{Double.valueOf(this.motionX), Double.valueOf(this.motionY), Double.valueOf(this.motionZ)}));
   }

   public boolean canRenderOnFire() {
      return this.isBurning();
   }

   public UUID getUniqueID() {
      return this.entityUniqueID;
   }

   public boolean isPushedByWater() {
      return true;
   }

   public IChatComponent func_145748_c_() {
      return new ChatComponentText(this.getCommandSenderName());
   }

   public void func_145781_i(int var1) {}
}
