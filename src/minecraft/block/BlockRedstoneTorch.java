package net.minecraft.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneTorch$Toggle;
import net.minecraft.block.BlockTorch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneTorch extends BlockTorch {

   private boolean field_150113_a;
   private static Map field_150112_b = new HashMap();


   private boolean func_150111_a(World var1, int var2, int var3, int var4, boolean var5) {
      if(!field_150112_b.containsKey(var1)) {
         field_150112_b.put(var1, new ArrayList());
      }

      List var6 = (List)field_150112_b.get(var1);
      if(var5) {
         var6.add(new BlockRedstoneTorch$Toggle(var2, var3, var4, var1.getTotalWorldTime()));
      }

      int var7 = 0;

      for(int var8 = 0; var8 < var6.size(); ++var8) {
         BlockRedstoneTorch$Toggle var9 = (BlockRedstoneTorch$Toggle)var6.get(var8);
         if(var9.field_150847_a == var2 && var9.field_150845_b == var3 && var9.field_150846_c == var4) {
            ++var7;
            if(var7 >= 8) {
               return true;
            }
         }
      }

      return false;
   }

   protected BlockRedstoneTorch(boolean var1) {
      this.field_150113_a = var1;
      this.setTickRandomly(true);
      this.setCreativeTab((CreativeTabs)null);
   }

   public int tickRate(World var1) {
      return 2;
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      if(var1.getBlockMetadata(var2, var3, var4) == 0) {
         super.onBlockAdded(var1, var2, var3, var4);
      }

      if(this.field_150113_a) {
         var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
         var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
      }

   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      if(this.field_150113_a) {
         var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this);
         var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this);
         var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this);
      }

   }

   public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      if(!this.field_150113_a) {
         return 0;
      } else {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         return var6 == 5 && var5 == 1?0:(var6 == 3 && var5 == 3?0:(var6 == 4 && var5 == 2?0:(var6 == 1 && var5 == 5?0:(var6 == 2 && var5 == 4?0:15))));
      }
   }

   private boolean func_150110_m(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      return var5 == 5 && var1.getIndirectPowerOutput(var2, var3 - 1, var4, 0)?true:(var5 == 3 && var1.getIndirectPowerOutput(var2, var3, var4 - 1, 2)?true:(var5 == 4 && var1.getIndirectPowerOutput(var2, var3, var4 + 1, 3)?true:(var5 == 1 && var1.getIndirectPowerOutput(var2 - 1, var3, var4, 4)?true:var5 == 2 && var1.getIndirectPowerOutput(var2 + 1, var3, var4, 5))));
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      boolean var6 = this.func_150110_m(var1, var2, var3, var4);
      List var7 = (List)field_150112_b.get(var1);

      while(var7 != null && !var7.isEmpty() && var1.getTotalWorldTime() - ((BlockRedstoneTorch$Toggle)var7.get(0)).field_150844_d > 60L) {
         var7.remove(0);
      }

      if(this.field_150113_a) {
         if(var6) {
            var1.setBlock(var2, var3, var4, Blocks.unlit_redstone_torch, var1.getBlockMetadata(var2, var3, var4), 3);
            if(this.func_150111_a(var1, var2, var3, var4, true)) {
               var1.playSoundEffect((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), "random.fizz", 0.5F, 2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);

               for(int var8 = 0; var8 < 5; ++var8) {
                  double var9 = (double)var2 + var5.nextDouble() * 0.6D + 0.2D;
                  double var11 = (double)var3 + var5.nextDouble() * 0.6D + 0.2D;
                  double var13 = (double)var4 + var5.nextDouble() * 0.6D + 0.2D;
                  var1.spawnParticle("smoke", var9, var11, var13, 0.0D, 0.0D, 0.0D);
               }
            }
         }
      } else if(!var6 && !this.func_150111_a(var1, var2, var3, var4, false)) {
         var1.setBlock(var2, var3, var4, Blocks.redstone_torch, var1.getBlockMetadata(var2, var3, var4), 3);
      }

   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!this.func_150108_b(var1, var2, var3, var4, var5)) {
         boolean var6 = this.func_150110_m(var1, var2, var3, var4);
         if(this.field_150113_a && var6 || !this.field_150113_a && !var6) {
            var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
         }

      }
   }

   public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var5 == 0?this.isProvidingWeakPower(var1, var2, var3, var4, var5):0;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.redstone_torch);
   }

   public boolean canProvidePower() {
      return true;
   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      if(this.field_150113_a) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         double var7 = (double)((float)var2 + 0.5F) + (double)(var5.nextFloat() - 0.5F) * 0.2D;
         double var9 = (double)((float)var3 + 0.7F) + (double)(var5.nextFloat() - 0.5F) * 0.2D;
         double var11 = (double)((float)var4 + 0.5F) + (double)(var5.nextFloat() - 0.5F) * 0.2D;
         double var13 = 0.2199999988079071D;
         double var15 = 0.27000001072883606D;
         if(var6 == 1) {
            var1.spawnParticle("reddust", var7 - var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
         } else if(var6 == 2) {
            var1.spawnParticle("reddust", var7 + var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
         } else if(var6 == 3) {
            var1.spawnParticle("reddust", var7, var9 + var13, var11 - var15, 0.0D, 0.0D, 0.0D);
         } else if(var6 == 4) {
            var1.spawnParticle("reddust", var7, var9 + var13, var11 + var15, 0.0D, 0.0D, 0.0D);
         } else {
            var1.spawnParticle("reddust", var7, var9, var11, 0.0D, 0.0D, 0.0D);
         }

      }
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Item.getItemFromBlock(Blocks.redstone_torch);
   }

   public boolean isAssociatedBlock(Block var1) {
      return var1 == Blocks.unlit_redstone_torch || var1 == Blocks.redstone_torch;
   }

}
