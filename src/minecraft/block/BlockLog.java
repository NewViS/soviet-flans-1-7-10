package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public abstract class BlockLog extends BlockRotatedPillar {

   protected IIcon[] field_150167_a;
   protected IIcon[] field_150166_b;


   public BlockLog() {
      super(Material.wood);
      this.setCreativeTab(CreativeTabs.tabBlock);
      this.setHardness(2.0F);
      this.setStepSound(Block.soundTypeWood);
   }

   public static int func_150165_c(int var0) {
      return var0 & 3;
   }

   public int quantityDropped(Random var1) {
      return 1;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Item.getItemFromBlock(this);
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      byte var7 = 4;
      int var8 = var7 + 1;
      if(var1.checkChunksExist(var2 - var8, var3 - var8, var4 - var8, var2 + var8, var3 + var8, var4 + var8)) {
         for(int var9 = -var7; var9 <= var7; ++var9) {
            for(int var10 = -var7; var10 <= var7; ++var10) {
               for(int var11 = -var7; var11 <= var7; ++var11) {
                  if(var1.getBlock(var2 + var9, var3 + var10, var4 + var11).getMaterial() == Material.leaves) {
                     int var12 = var1.getBlockMetadata(var2 + var9, var3 + var10, var4 + var11);
                     if((var12 & 8) == 0) {
                        var1.setBlockMetadataWithNotify(var2 + var9, var3 + var10, var4 + var11, var12 | 8, 4);
                     }
                  }
               }
            }
         }
      }

   }

   protected IIcon getSideIcon(int var1) {
      return this.field_150167_a[var1 % this.field_150167_a.length];
   }

   protected IIcon getTopIcon(int var1) {
      return this.field_150166_b[var1 % this.field_150166_b.length];
   }
}
