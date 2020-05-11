package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDragonEgg extends Block {

   public BlockDragonEgg() {
      super(Material.dragonEgg);
      this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      this.func_150018_e(var1, var2, var3, var4);
   }

   private void func_150018_e(World var1, int var2, int var3, int var4) {
      if(BlockFalling.func_149831_e(var1, var2, var3 - 1, var4) && var3 >= 0) {
         byte var5 = 32;
         if(!BlockFalling.fallInstantly && var1.checkChunksExist(var2 - var5, var3 - var5, var4 - var5, var2 + var5, var3 + var5, var4 + var5)) {
            EntityFallingBlock var6 = new EntityFallingBlock(var1, (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), this);
            var1.spawnEntityInWorld(var6);
         } else {
            var1.setBlockToAir(var2, var3, var4);

            while(BlockFalling.func_149831_e(var1, var2, var3 - 1, var4) && var3 > 0) {
               --var3;
            }

            if(var3 > 0) {
               var1.setBlock(var2, var3, var4, this, 0, 2);
            }
         }
      }

   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      this.func_150019_m(var1, var2, var3, var4);
      return true;
   }

   public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {
      this.func_150019_m(var1, var2, var3, var4);
   }

   private void func_150019_m(World var1, int var2, int var3, int var4) {
      if(var1.getBlock(var2, var3, var4) == this) {
         for(int var5 = 0; var5 < 1000; ++var5) {
            int var6 = var2 + var1.rand.nextInt(16) - var1.rand.nextInt(16);
            int var7 = var3 + var1.rand.nextInt(8) - var1.rand.nextInt(8);
            int var8 = var4 + var1.rand.nextInt(16) - var1.rand.nextInt(16);
            if(var1.getBlock(var6, var7, var8).blockMaterial == Material.air) {
               if(!var1.isRemote) {
                  var1.setBlock(var6, var7, var8, this, var1.getBlockMetadata(var2, var3, var4), 2);
                  var1.setBlockToAir(var2, var3, var4);
               } else {
                  short var9 = 128;

                  for(int var10 = 0; var10 < var9; ++var10) {
                     double var11 = var1.rand.nextDouble();
                     float var13 = (var1.rand.nextFloat() - 0.5F) * 0.2F;
                     float var14 = (var1.rand.nextFloat() - 0.5F) * 0.2F;
                     float var15 = (var1.rand.nextFloat() - 0.5F) * 0.2F;
                     double var16 = (double)var6 + (double)(var2 - var6) * var11 + (var1.rand.nextDouble() - 0.5D) * 1.0D + 0.5D;
                     double var18 = (double)var7 + (double)(var3 - var7) * var11 + var1.rand.nextDouble() * 1.0D - 0.5D;
                     double var20 = (double)var8 + (double)(var4 - var8) * var11 + (var1.rand.nextDouble() - 0.5D) * 1.0D + 0.5D;
                     var1.spawnParticle("portal", var16, var18, var20, (double)var13, (double)var14, (double)var15);
                  }
               }

               return;
            }
         }

      }
   }

   public int tickRate(World var1) {
      return 5;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return true;
   }

   public int getRenderType() {
      return 27;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Item.getItemById(0);
   }
}
