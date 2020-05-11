package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class EntityAIOcelotSit extends EntityAIBase {

   private final EntityOcelot field_151493_a;
   private final double field_151491_b;
   private int field_151492_c;
   private int field_151489_d;
   private int field_151490_e;
   private int field_151487_f;
   private int field_151488_g;
   private int field_151494_h;


   public EntityAIOcelotSit(EntityOcelot var1, double var2) {
      this.field_151493_a = var1;
      this.field_151491_b = var2;
      this.setMutexBits(5);
   }

   public boolean shouldExecute() {
      return this.field_151493_a.isTamed() && !this.field_151493_a.isSitting() && this.field_151493_a.getRNG().nextDouble() <= 0.006500000134110451D && this.func_151485_f();
   }

   public boolean continueExecuting() {
      return this.field_151492_c <= this.field_151490_e && this.field_151489_d <= 60 && this.func_151486_a(this.field_151493_a.worldObj, this.field_151487_f, this.field_151488_g, this.field_151494_h);
   }

   public void startExecuting() {
      this.field_151493_a.getNavigator().tryMoveToXYZ((double)((float)this.field_151487_f) + 0.5D, (double)(this.field_151488_g + 1), (double)((float)this.field_151494_h) + 0.5D, this.field_151491_b);
      this.field_151492_c = 0;
      this.field_151489_d = 0;
      this.field_151490_e = this.field_151493_a.getRNG().nextInt(this.field_151493_a.getRNG().nextInt(1200) + 1200) + 1200;
      this.field_151493_a.func_70907_r().setSitting(false);
   }

   public void resetTask() {
      this.field_151493_a.setSitting(false);
   }

   public void updateTask() {
      ++this.field_151492_c;
      this.field_151493_a.func_70907_r().setSitting(false);
      if(this.field_151493_a.getDistanceSq((double)this.field_151487_f, (double)(this.field_151488_g + 1), (double)this.field_151494_h) > 1.0D) {
         this.field_151493_a.setSitting(false);
         this.field_151493_a.getNavigator().tryMoveToXYZ((double)((float)this.field_151487_f) + 0.5D, (double)(this.field_151488_g + 1), (double)((float)this.field_151494_h) + 0.5D, this.field_151491_b);
         ++this.field_151489_d;
      } else if(!this.field_151493_a.isSitting()) {
         this.field_151493_a.setSitting(true);
      } else {
         --this.field_151489_d;
      }

   }

   private boolean func_151485_f() {
      int var1 = (int)this.field_151493_a.posY;
      double var2 = 2.147483647E9D;

      for(int var4 = (int)this.field_151493_a.posX - 8; (double)var4 < this.field_151493_a.posX + 8.0D; ++var4) {
         for(int var5 = (int)this.field_151493_a.posZ - 8; (double)var5 < this.field_151493_a.posZ + 8.0D; ++var5) {
            if(this.func_151486_a(this.field_151493_a.worldObj, var4, var1, var5) && this.field_151493_a.worldObj.isAirBlock(var4, var1 + 1, var5)) {
               double var6 = this.field_151493_a.getDistanceSq((double)var4, (double)var1, (double)var5);
               if(var6 < var2) {
                  this.field_151487_f = var4;
                  this.field_151488_g = var1;
                  this.field_151494_h = var5;
                  var2 = var6;
               }
            }
         }
      }

      return var2 < 2.147483647E9D;
   }

   private boolean func_151486_a(World var1, int var2, int var3, int var4) {
      Block var5 = var1.getBlock(var2, var3, var4);
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      if(var5 == Blocks.chest) {
         TileEntityChest var7 = (TileEntityChest)var1.getTileEntity(var2, var3, var4);
         if(var7.numPlayersUsing < 1) {
            return true;
         }
      } else {
         if(var5 == Blocks.lit_furnace) {
            return true;
         }

         if(var5 == Blocks.bed && !BlockBed.isBlockHeadOfBed(var6)) {
            return true;
         }
      }

      return false;
   }
}
