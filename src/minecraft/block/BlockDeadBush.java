package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class BlockDeadBush extends BlockBush {

   protected BlockDeadBush() {
      super(Material.vine);
      float var1 = 0.4F;
      this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.8F, 0.5F + var1);
   }

   protected boolean canPlaceBlockOn(Block var1) {
      return var1 == Blocks.sand || var1 == Blocks.hardened_clay || var1 == Blocks.stained_hardened_clay || var1 == Blocks.dirt;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return null;
   }

   public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6) {
      if(!var1.isRemote && var2.getCurrentEquippedItem() != null && var2.getCurrentEquippedItem().getItem() == Items.shears) {
         var2.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(this)], 1);
         this.dropBlockAsItem(var1, var3, var4, var5, new ItemStack(Blocks.deadbush, 1, var6));
      } else {
         super.harvestBlock(var1, var2, var3, var4, var5, var6);
      }

   }
}
