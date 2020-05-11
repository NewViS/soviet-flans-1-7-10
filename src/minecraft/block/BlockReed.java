package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockReed extends Block {

   protected BlockReed() {
      super(Material.plants);
      float var1 = 0.375F;
      this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 1.0F, 0.5F + var1);
      this.setTickRandomly(true);
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(var1.getBlock(var2, var3 - 1, var4) == Blocks.reeds || this.func_150170_e(var1, var2, var3, var4)) {
         if(var1.isAirBlock(var2, var3 + 1, var4)) {
            int var6;
            for(var6 = 1; var1.getBlock(var2, var3 - var6, var4) == this; ++var6) {
               ;
            }

            if(var6 < 3) {
               int var7 = var1.getBlockMetadata(var2, var3, var4);
               if(var7 == 15) {
                  var1.setBlock(var2, var3 + 1, var4, this);
                  var1.setBlockMetadataWithNotify(var2, var3, var4, 0, 4);
               } else {
                  var1.setBlockMetadataWithNotify(var2, var3, var4, var7 + 1, 4);
               }
            }
         }

      }
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      Block var5 = var1.getBlock(var2, var3 - 1, var4);
      return var5 == this?true:(var5 != Blocks.grass && var5 != Blocks.dirt && var5 != Blocks.sand?false:(var1.getBlock(var2 - 1, var3 - 1, var4).getMaterial() == Material.water?true:(var1.getBlock(var2 + 1, var3 - 1, var4).getMaterial() == Material.water?true:(var1.getBlock(var2, var3 - 1, var4 - 1).getMaterial() == Material.water?true:var1.getBlock(var2, var3 - 1, var4 + 1).getMaterial() == Material.water))));
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      this.func_150170_e(var1, var2, var3, var4);
   }

   protected final boolean func_150170_e(World var1, int var2, int var3, int var4) {
      if(!this.canBlockStay(var1, var2, var3, var4)) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockToAir(var2, var3, var4);
         return false;
      } else {
         return true;
      }
   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      return this.canPlaceBlockAt(var1, var2, var3, var4);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return null;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.reeds;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 1;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Items.reeds;
   }

   public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4) {
      return var1.getBiomeGenForCoords(var2, var4).getBiomeGrassColor(var2, var3, var4);
   }
}
