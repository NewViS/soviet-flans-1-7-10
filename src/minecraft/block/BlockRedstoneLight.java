package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockRedstoneLight extends Block {

   private final boolean field_150171_a;


   public BlockRedstoneLight(boolean var1) {
      super(Material.redstoneLight);
      this.field_150171_a = var1;
      if(var1) {
         this.setLightLevel(1.0F);
      }

   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      if(!var1.isRemote) {
         if(this.field_150171_a && !var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
            var1.scheduleBlockUpdate(var2, var3, var4, this, 4);
         } else if(!this.field_150171_a && var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
            var1.setBlock(var2, var3, var4, Blocks.lit_redstone_lamp, 0, 2);
         }
      }

   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!var1.isRemote) {
         if(this.field_150171_a && !var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
            var1.scheduleBlockUpdate(var2, var3, var4, this, 4);
         } else if(!this.field_150171_a && var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
            var1.setBlock(var2, var3, var4, Blocks.lit_redstone_lamp, 0, 2);
         }
      }

   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!var1.isRemote && this.field_150171_a && !var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
         var1.setBlock(var2, var3, var4, Blocks.redstone_lamp, 0, 2);
      }

   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.redstone_lamp);
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Item.getItemFromBlock(Blocks.redstone_lamp);
   }

   protected ItemStack createStackedBlock(int var1) {
      return new ItemStack(Blocks.redstone_lamp);
   }
}
