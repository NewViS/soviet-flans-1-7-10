package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockSnowBlock extends Block {

   protected BlockSnowBlock() {
      super(Material.craftedSnow);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.snowball;
   }

   public int quantityDropped(Random var1) {
      return 4;
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(var1.getSavedLightValue(EnumSkyBlock.Block, var2, var3, var4) > 11) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockToAir(var2, var3, var4);
      }

   }
}
