package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockOre extends Block {

   public BlockOre() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return this == Blocks.coal_ore?Items.coal:(this == Blocks.diamond_ore?Items.diamond:(this == Blocks.lapis_ore?Items.dye:(this == Blocks.emerald_ore?Items.emerald:(this == Blocks.quartz_ore?Items.quartz:Item.getItemFromBlock(this)))));
   }

   public int quantityDropped(Random var1) {
      return this == Blocks.lapis_ore?4 + var1.nextInt(5):1;
   }

   public int quantityDroppedWithBonus(int var1, Random var2) {
      if(var1 > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, var2, var1)) {
         int var3 = var2.nextInt(var1 + 2) - 1;
         if(var3 < 0) {
            var3 = 0;
         }

         return this.quantityDropped(var2) * (var3 + 1);
      } else {
         return this.quantityDropped(var2);
      }
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);
      if(this.getItemDropped(var5, var1.rand, var7) != Item.getItemFromBlock(this)) {
         int var8 = 0;
         if(this == Blocks.coal_ore) {
            var8 = MathHelper.getRandomIntegerInRange(var1.rand, 0, 2);
         } else if(this == Blocks.diamond_ore) {
            var8 = MathHelper.getRandomIntegerInRange(var1.rand, 3, 7);
         } else if(this == Blocks.emerald_ore) {
            var8 = MathHelper.getRandomIntegerInRange(var1.rand, 3, 7);
         } else if(this == Blocks.lapis_ore) {
            var8 = MathHelper.getRandomIntegerInRange(var1.rand, 2, 5);
         } else if(this == Blocks.quartz_ore) {
            var8 = MathHelper.getRandomIntegerInRange(var1.rand, 2, 5);
         }

         this.dropXpOnBlockBreak(var1, var2, var3, var4, var8);
      }

   }

   public int damageDropped(int var1) {
      return this == Blocks.lapis_ore?4:0;
   }
}
