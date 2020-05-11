package net.minecraft.entity.item;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityFallingBlock extends Entity {

   private Block field_145811_e;
   public int field_145814_a;
   public int field_145812_b;
   public boolean field_145813_c;
   private boolean field_145808_f;
   private boolean field_145809_g;
   private int field_145815_h;
   private float field_145816_i;
   public NBTTagCompound field_145810_d;


   public EntityFallingBlock(World var1) {
      super(var1);
      this.field_145813_c = true;
      this.field_145815_h = 40;
      this.field_145816_i = 2.0F;
   }

   public EntityFallingBlock(World var1, double var2, double var4, double var6, Block var8) {
      this(var1, var2, var4, var6, var8, 0);
   }

   public EntityFallingBlock(World var1, double var2, double var4, double var6, Block var8, int var9) {
      super(var1);
      this.field_145813_c = true;
      this.field_145815_h = 40;
      this.field_145816_i = 2.0F;
      this.field_145811_e = var8;
      this.field_145814_a = var9;
      super.preventEntitySpawning = true;
      this.setSize(0.98F, 0.98F);
      super.yOffset = super.height / 2.0F;
      this.setPosition(var2, var4, var6);
      super.motionX = 0.0D;
      super.motionY = 0.0D;
      super.motionZ = 0.0D;
      super.prevPosX = var2;
      super.prevPosY = var4;
      super.prevPosZ = var6;
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void entityInit() {}

   public boolean canBeCollidedWith() {
      return !super.isDead;
   }

   public void onUpdate() {
      if(this.field_145811_e.getMaterial() == Material.air) {
         this.setDead();
      } else {
         super.prevPosX = super.posX;
         super.prevPosY = super.posY;
         super.prevPosZ = super.posZ;
         ++this.field_145812_b;
         super.motionY -= 0.03999999910593033D;
         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         super.motionX *= 0.9800000190734863D;
         super.motionY *= 0.9800000190734863D;
         super.motionZ *= 0.9800000190734863D;
         if(!super.worldObj.isRemote) {
            int var1 = MathHelper.floor_double(super.posX);
            int var2 = MathHelper.floor_double(super.posY);
            int var3 = MathHelper.floor_double(super.posZ);
            if(this.field_145812_b == 1) {
               if(super.worldObj.getBlock(var1, var2, var3) != this.field_145811_e) {
                  this.setDead();
                  return;
               }

               super.worldObj.setBlockToAir(var1, var2, var3);
            }

            if(super.onGround) {
               super.motionX *= 0.699999988079071D;
               super.motionZ *= 0.699999988079071D;
               super.motionY *= -0.5D;
               if(super.worldObj.getBlock(var1, var2, var3) != Blocks.piston_extension) {
                  this.setDead();
                  if(!this.field_145808_f && super.worldObj.canPlaceEntityOnSide(this.field_145811_e, var1, var2, var3, true, 1, (Entity)null, (ItemStack)null) && !BlockFalling.func_149831_e(super.worldObj, var1, var2 - 1, var3) && super.worldObj.setBlock(var1, var2, var3, this.field_145811_e, this.field_145814_a, 3)) {
                     if(this.field_145811_e instanceof BlockFalling) {
                        ((BlockFalling)this.field_145811_e).func_149828_a(super.worldObj, var1, var2, var3, this.field_145814_a);
                     }

                     if(this.field_145810_d != null && this.field_145811_e instanceof ITileEntityProvider) {
                        TileEntity var4 = super.worldObj.getTileEntity(var1, var2, var3);
                        if(var4 != null) {
                           NBTTagCompound var5 = new NBTTagCompound();
                           var4.writeToNBT(var5);
                           Iterator var6 = this.field_145810_d.func_150296_c().iterator();

                           while(var6.hasNext()) {
                              String var7 = (String)var6.next();
                              NBTBase var8 = this.field_145810_d.getTag(var7);
                              if(!var7.equals("x") && !var7.equals("y") && !var7.equals("z")) {
                                 var5.setTag(var7, var8.copy());
                              }
                           }

                           var4.readFromNBT(var5);
                           var4.markDirty();
                        }
                     }
                  } else if(this.field_145813_c && !this.field_145808_f) {
                     this.entityDropItem(new ItemStack(this.field_145811_e, 1, this.field_145811_e.damageDropped(this.field_145814_a)), 0.0F);
                  }
               }
            } else if(this.field_145812_b > 100 && !super.worldObj.isRemote && (var2 < 1 || var2 > 256) || this.field_145812_b > 600) {
               if(this.field_145813_c) {
                  this.entityDropItem(new ItemStack(this.field_145811_e, 1, this.field_145811_e.damageDropped(this.field_145814_a)), 0.0F);
               }

               this.setDead();
            }
         }

      }
   }

   protected void fall(float var1) {
      if(this.field_145809_g) {
         int var2 = MathHelper.ceiling_float_int(var1 - 1.0F);
         if(var2 > 0) {
            ArrayList var3 = new ArrayList(super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox));
            boolean var4 = this.field_145811_e == Blocks.anvil;
            DamageSource var5 = var4?DamageSource.anvil:DamageSource.fallingBlock;
            Iterator var6 = var3.iterator();

            while(var6.hasNext()) {
               Entity var7 = (Entity)var6.next();
               var7.attackEntityFrom(var5, (float)Math.min(MathHelper.floor_float((float)var2 * this.field_145816_i), this.field_145815_h));
            }

            if(var4 && (double)super.rand.nextFloat() < 0.05000000074505806D + (double)var2 * 0.05D) {
               int var8 = this.field_145814_a >> 2;
               int var9 = this.field_145814_a & 3;
               ++var8;
               if(var8 > 2) {
                  this.field_145808_f = true;
               } else {
                  this.field_145814_a = var9 | var8 << 2;
               }
            }
         }
      }

   }

   protected void writeEntityToNBT(NBTTagCompound var1) {
      var1.setByte("Tile", (byte)Block.getIdFromBlock(this.field_145811_e));
      var1.setInteger("TileID", Block.getIdFromBlock(this.field_145811_e));
      var1.setByte("Data", (byte)this.field_145814_a);
      var1.setByte("Time", (byte)this.field_145812_b);
      var1.setBoolean("DropItem", this.field_145813_c);
      var1.setBoolean("HurtEntities", this.field_145809_g);
      var1.setFloat("FallHurtAmount", this.field_145816_i);
      var1.setInteger("FallHurtMax", this.field_145815_h);
      if(this.field_145810_d != null) {
         var1.setTag("TileEntityData", this.field_145810_d);
      }

   }

   protected void readEntityFromNBT(NBTTagCompound var1) {
      if(var1.hasKey("TileID", 99)) {
         this.field_145811_e = Block.getBlockById(var1.getInteger("TileID"));
      } else {
         this.field_145811_e = Block.getBlockById(var1.getByte("Tile") & 255);
      }

      this.field_145814_a = var1.getByte("Data") & 255;
      this.field_145812_b = var1.getByte("Time") & 255;
      if(var1.hasKey("HurtEntities", 99)) {
         this.field_145809_g = var1.getBoolean("HurtEntities");
         this.field_145816_i = var1.getFloat("FallHurtAmount");
         this.field_145815_h = var1.getInteger("FallHurtMax");
      } else if(this.field_145811_e == Blocks.anvil) {
         this.field_145809_g = true;
      }

      if(var1.hasKey("DropItem", 99)) {
         this.field_145813_c = var1.getBoolean("DropItem");
      }

      if(var1.hasKey("TileEntityData", 10)) {
         this.field_145810_d = var1.getCompoundTag("TileEntityData");
      }

      if(this.field_145811_e.getMaterial() == Material.air) {
         this.field_145811_e = Blocks.sand;
      }

   }

   public float getShadowSize() {
      return 0.0F;
   }

   public World func_145807_e() {
      return super.worldObj;
   }

   public void func_145806_a(boolean var1) {
      this.field_145809_g = var1;
   }

   public boolean canRenderOnFire() {
      return false;
   }

   public void addEntityCrashInfo(CrashReportCategory var1) {
      super.addEntityCrashInfo(var1);
      var1.addCrashSection("Immitating block ID", Integer.valueOf(Block.getIdFromBlock(this.field_145811_e)));
      var1.addCrashSection("Immitating block data", Integer.valueOf(this.field_145814_a));
   }

   public Block func_145805_f() {
      return this.field_145811_e;
   }
}
