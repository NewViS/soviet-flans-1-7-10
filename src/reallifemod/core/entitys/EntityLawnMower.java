package de.ItsAMysterious.mods.reallifemod.core.entitys;

import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.BlockLawnRobotRim;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class EntityLawnMower extends Entity implements IInventory {

   private double speed;
   private boolean isRotated;


   public EntityLawnMower(World world) {
      super(world);
      this.isRotated = false;
      this.func_70105_a(0.75F, 0.25F);
   }

   public EntityLawnMower(World world, double x, double y, double z) {
      this(world);
      this.func_70107_b(x, y, z);
      this.field_70156_m = true;
   }

   protected boolean isAIEnabled() {
      return false;
   }

   public void func_70071_h_() {
      super.onUpdate();
      this.speed = 0.05000000074505806D;
      this.field_70159_w = Math.sin(Math.toRadians((double)this.field_70177_z)) * this.speed;
      this.field_70179_y = -Math.cos(Math.toRadians((double)this.field_70177_z)) * this.speed;
      float blockX = (float)(this.field_70165_t + this.field_70159_w);
      float blockZ = (float)(this.field_70161_v + this.field_70179_y);
      Block frontblock = this.field_70170_p.getBlock((int)blockX, (int)this.field_70163_u - 1, (int)blockZ);
      Block frontblock2 = this.field_70170_p.getBlock((int)blockX, (int)this.field_70163_u, (int)blockZ);
      if(!(frontblock instanceof BlockLawnRobotRim) && (frontblock2 == Blocks.air || frontblock2 instanceof IShearable || frontblock2 instanceof BlockFlower) && frontblock == Blocks.grass) {
         this.isRotated = false;
         frontblock2.dropBlockAsItem(this.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 100, 10);
         this.field_70170_p.setBlockToAir((int)blockX, (int)this.field_70163_u, (int)blockZ);
      } else {
         if(!this.field_70170_p.isRemote) {
            this.field_70177_z += 50.0F;
         }

         this.isRotated = true;
      }

      Random rand = new Random();
      if(this.func_70090_H() && this.field_70170_p.isRemote) {
         this.field_70170_p.spawnParticle("smoke", this.field_70165_t, this.field_70163_u, this.field_70161_v, rand.nextDouble() / 2.0D, 0.25D + rand.nextDouble() / 2.0D, rand.nextDouble() / 2.0D);
         if(this.field_70173_aa % 40 == 0) {
            this.field_70170_p.createExplosion(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.5F, true);
            this.func_70106_y();
         }
      }

      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
   }

   protected void func_70088_a() {}

   protected void func_70037_a(NBTTagCompound p_70037_1_) {}

   protected void func_70014_b(NBTTagCompound p_70014_1_) {}

   public int func_70302_i_() {
      return 0;
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      return null;
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      return null;
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      return null;
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {}

   public String func_145825_b() {
      return null;
   }

   public boolean func_145818_k_() {
      return false;
   }

   public int func_70297_j_() {
      return 0;
   }

   public void func_70296_d() {}

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return false;
   }

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return false;
   }
}
