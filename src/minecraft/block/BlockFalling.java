package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockFalling extends Block {

   public static boolean fallInstantly;


   public BlockFalling() {
      super(Material.sand);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public BlockFalling(Material var1) {
      super(var1);
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!var1.isRemote) {
         this.func_149830_m(var1, var2, var3, var4);
      }

   }

   private void func_149830_m(World var1, int var2, int var3, int var4) {
      if(func_149831_e(var1, var2, var3 - 1, var4) && var3 >= 0) {
         byte var8 = 32;
         if(!fallInstantly && var1.checkChunksExist(var2 - var8, var3 - var8, var4 - var8, var2 + var8, var3 + var8, var4 + var8)) {
            if(!var1.isRemote) {
               EntityFallingBlock var9 = new EntityFallingBlock(var1, (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), this, var1.getBlockMetadata(var2, var3, var4));
               this.func_149829_a(var9);
               var1.spawnEntityInWorld(var9);
            }
         } else {
            var1.setBlockToAir(var2, var3, var4);

            while(func_149831_e(var1, var2, var3 - 1, var4) && var3 > 0) {
               --var3;
            }

            if(var3 > 0) {
               var1.setBlock(var2, var3, var4, this);
            }
         }
      }

   }

   protected void func_149829_a(EntityFallingBlock var1) {}

   public int tickRate(World var1) {
      return 2;
   }

   public static boolean func_149831_e(World var0, int var1, int var2, int var3) {
      Block var4 = var0.getBlock(var1, var2, var3);
      if(var4.blockMaterial == Material.air) {
         return true;
      } else if(var4 == Blocks.fire) {
         return true;
      } else {
         Material var5 = var4.blockMaterial;
         return var5 == Material.water?true:var5 == Material.lava;
      }
   }

   public void func_149828_a(World var1, int var2, int var3, int var4, int var5) {}
}
