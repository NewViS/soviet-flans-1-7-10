package de.ItsAMysterious.mods.reallifemod.core.entitys;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.api.util.LittleFunctions;
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

public class EntityFlyingBlock extends Entity {

   public static Block theBlock;
   public int field_145814_a;
   public int field_145812_b;
   public boolean field_145813_c;
   private boolean field_145808_f;
   private boolean field_145809_g;
   private int field_145815_h;
   private float field_145816_i;
   public NBTTagCompound field_145810_d;
   private static final String __OBFID = "CL_00001668";


   public EntityFlyingBlock(World p_i1706_1_) {
      super(p_i1706_1_);
      this.field_145813_c = true;
      this.field_145815_h = 40;
      this.field_145816_i = 2.0F;
   }

   public EntityFlyingBlock(World world, double xPos, double yPos, double zPos, Block block) {
      this(world, xPos, yPos, zPos, 1.0D, block, 0);
   }

   public EntityFlyingBlock(World p_i45319_1_, double p_i45319_2_, double p_i45319_4_, double p_i45319_6_, double strength, Block p_i45319_8_, int p_i45319_9_) {
      super(p_i45319_1_);
      this.field_145813_c = true;
      this.field_145815_h = 40;
      this.field_145816_i = 2.0F;
      theBlock = p_i45319_8_;
      this.field_145814_a = p_i45319_9_;
      this.field_70156_m = true;
      this.func_70105_a(0.98F, 0.98F);
      this.field_70129_M = this.field_70131_O / 2.0F;
      this.func_70107_b(p_i45319_2_, p_i45319_4_, p_i45319_6_);
      this.field_70159_w = strength * LittleFunctions.randMinMax(0.5D, 1.0D);
      this.field_70181_x = -9.81D * strength * LittleFunctions.randMinMax(2.5D, 5.0D);
      this.field_70179_y = strength * LittleFunctions.randMinMax(0.5D, 1.0D);
      this.field_70169_q = p_i45319_2_;
      this.field_70167_r = p_i45319_4_;
      this.field_70166_s = p_i45319_6_;
   }

   protected boolean func_70041_e_() {
      return false;
   }

   protected void func_70088_a() {}

   public boolean func_70067_L() {
      return !this.field_70128_L;
   }

   public void func_70071_h_() {
      if(theBlock.getMaterial() == Material.air) {
         this.func_70106_y();
      } else {
         this.field_70169_q = this.field_70165_t;
         this.field_70167_r = this.field_70163_u;
         this.field_70166_s = this.field_70161_v;
         ++this.field_145812_b;
         this.field_70181_x -= 0.03999999910593033D;
         this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
         this.field_70159_w *= 0.9800000190734863D;
         this.field_70181_x *= 0.9800000190734863D;
         this.field_70179_y *= 0.9800000190734863D;
         if(!this.field_70170_p.isRemote) {
            int i = MathHelper.floor_double(this.field_70165_t);
            int j = MathHelper.floor_double(this.field_70163_u);
            int k = MathHelper.floor_double(this.field_70161_v);
            if(this.field_145812_b == 1) {
               if(this.field_70170_p.getBlock(i, j, k) != theBlock) {
                  this.func_70106_y();
                  return;
               }

               this.field_70170_p.setBlockToAir(i, j, k);
            }

            if(this.field_70122_E) {
               this.field_70159_w *= 0.699999988079071D;
               this.field_70179_y *= 0.699999988079071D;
               this.field_70181_x *= -0.5D;
               if(this.field_70170_p.getBlock(i, j, k) != Blocks.piston_extension) {
                  this.func_70106_y();
                  if(!this.field_145808_f && this.field_70170_p.canPlaceEntityOnSide(theBlock, i, j, k, true, 1, (Entity)null, (ItemStack)null) && !BlockFalling.func_149831_e(this.field_70170_p, i, j - 1, k) && this.field_70170_p.setBlock(i, j, k, theBlock, this.field_145814_a, 3)) {
                     if(theBlock instanceof BlockFalling) {
                        ((BlockFalling)theBlock).func_149828_a(this.field_70170_p, i, j, k, this.field_145814_a);
                     }

                     if(this.field_145810_d != null && theBlock instanceof ITileEntityProvider) {
                        TileEntity tileentity = this.field_70170_p.getTileEntity(i, j, k);
                        if(tileentity != null) {
                           NBTTagCompound nbttagcompound = new NBTTagCompound();
                           tileentity.writeToNBT(nbttagcompound);
                           Iterator iterator = this.field_145810_d.func_150296_c().iterator();

                           while(iterator.hasNext()) {
                              String s = (String)iterator.next();
                              NBTBase nbtbase = this.field_145810_d.getTag(s);
                              if(!s.equals("x") && !s.equals("y") && !s.equals("z")) {
                                 nbttagcompound.setTag(s, nbtbase.copy());
                              }
                           }

                           tileentity.readFromNBT(nbttagcompound);
                           tileentity.markDirty();
                        }
                     }
                  } else if(this.field_145813_c && !this.field_145808_f) {
                     this.func_70099_a(new ItemStack(theBlock, 1, theBlock.damageDropped(this.field_145814_a)), 0.0F);
                  }
               }
            } else if(this.field_145812_b > 100 && !this.field_70170_p.isRemote && (j < 1 || j > 256) || this.field_145812_b > 600) {
               if(this.field_145813_c) {
                  this.func_70099_a(new ItemStack(theBlock, 1, theBlock.damageDropped(this.field_145814_a)), 0.0F);
               }

               this.func_70106_y();
            }
         }
      }

   }

   protected void func_70069_a(float p_70069_1_) {
      if(this.field_145809_g) {
         int i = MathHelper.ceiling_float_int(p_70069_1_ - 1.0F);
         if(i > 0) {
            ArrayList arraylist = new ArrayList(this.field_70170_p.getEntitiesWithinAABBExcludingEntity(this, this.field_70121_D));
            boolean flag = theBlock == Blocks.anvil;
            DamageSource damagesource = flag?DamageSource.anvil:DamageSource.fallingBlock;
            Iterator iterator = arraylist.iterator();

            while(iterator.hasNext()) {
               Entity j = (Entity)iterator.next();
               j.attackEntityFrom(damagesource, (float)Math.min(MathHelper.floor_float((float)i * this.field_145816_i), this.field_145815_h));
            }

            if(flag && (double)this.field_70146_Z.nextFloat() < 0.05000000074505806D + (double)i * 0.05D) {
               int var9 = this.field_145814_a >> 2;
               int k = this.field_145814_a & 3;
               ++var9;
               if(var9 > 2) {
                  this.field_145808_f = true;
               } else {
                  this.field_145814_a = k | var9 << 2;
               }
            }
         }
      }

   }

   protected void func_70014_b(NBTTagCompound p_70014_1_) {
      p_70014_1_.setByte("Tile", (byte)Block.getIdFromBlock(theBlock));
      p_70014_1_.setInteger("TileID", Block.getIdFromBlock(theBlock));
      p_70014_1_.setByte("Data", (byte)this.field_145814_a);
      p_70014_1_.setByte("Time", (byte)this.field_145812_b);
      p_70014_1_.setBoolean("DropItem", this.field_145813_c);
      p_70014_1_.setBoolean("HurtEntities", this.field_145809_g);
      p_70014_1_.setFloat("FallHurtAmount", this.field_145816_i);
      p_70014_1_.setInteger("FallHurtMax", this.field_145815_h);
      if(this.field_145810_d != null) {
         p_70014_1_.setTag("TileEntityData", this.field_145810_d);
      }

   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {
      if(p_70037_1_.hasKey("TileID", 99)) {
         theBlock = Block.getBlockById(p_70037_1_.getInteger("TileID"));
      } else {
         theBlock = Block.getBlockById(p_70037_1_.getByte("Tile") & 255);
      }

      this.field_145814_a = p_70037_1_.getByte("Data") & 255;
      this.field_145812_b = p_70037_1_.getByte("Time") & 255;
      if(p_70037_1_.hasKey("HurtEntities", 99)) {
         this.field_145809_g = p_70037_1_.getBoolean("HurtEntities");
         this.field_145816_i = p_70037_1_.getFloat("FallHurtAmount");
         this.field_145815_h = p_70037_1_.getInteger("FallHurtMax");
      } else if(theBlock == Blocks.anvil) {
         this.field_145809_g = true;
      }

      if(p_70037_1_.hasKey("DropItem", 99)) {
         this.field_145813_c = p_70037_1_.getBoolean("DropItem");
      }

      if(p_70037_1_.hasKey("TileEntityData", 10)) {
         this.field_145810_d = p_70037_1_.getCompoundTag("TileEntityData");
      }

      if(theBlock.getMaterial() == Material.air) {
         theBlock = Blocks.sand;
      }

   }

   public void func_145806_a(boolean p_145806_1_) {
      this.field_145809_g = p_145806_1_;
   }

   public void func_85029_a(CrashReportCategory p_85029_1_) {
      super.addEntityCrashInfo(p_85029_1_);
      p_85029_1_.addCrashSection("Immitating block ID", Integer.valueOf(Block.getIdFromBlock(theBlock)));
      p_85029_1_.addCrashSection("Immitating block data", Integer.valueOf(this.field_145814_a));
   }

   @SideOnly(Side.CLIENT)
   public float func_70053_R() {
      return 0.0F;
   }

   @SideOnly(Side.CLIENT)
   public World func_145807_e() {
      return this.field_70170_p;
   }

   @SideOnly(Side.CLIENT)
   public boolean func_90999_ad() {
      return false;
   }

   public Block func_145805_f() {
      return theBlock;
   }
}
