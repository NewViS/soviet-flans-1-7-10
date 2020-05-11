package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public abstract class BlockRotatedPillar extends Block {

   protected IIcon field_150164_N;


   protected BlockRotatedPillar(Material var1) {
      super(var1);
   }

   public int getRenderType() {
      return 31;
   }

   public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9) {
      int var10 = var9 & 3;
      byte var11 = 0;
      switch(var5) {
      case 0:
      case 1:
         var11 = 0;
         break;
      case 2:
      case 3:
         var11 = 8;
         break;
      case 4:
      case 5:
         var11 = 4;
      }

      return var10 | var11;
   }

   public IIcon getIcon(int var1, int var2) {
      int var3 = var2 & 12;
      int var4 = var2 & 3;
      return var3 == 0 && (var1 == 1 || var1 == 0)?this.getTopIcon(var4):(var3 == 4 && (var1 == 5 || var1 == 4)?this.getTopIcon(var4):(var3 == 8 && (var1 == 2 || var1 == 3)?this.getTopIcon(var4):this.getSideIcon(var4)));
   }

   protected abstract IIcon getSideIcon(int var1);

   protected IIcon getTopIcon(int var1) {
      return this.field_150164_N;
   }

   public int damageDropped(int var1) {
      return var1 & 3;
   }

   public int func_150162_k(int var1) {
      return var1 & 3;
   }

   protected ItemStack createStackedBlock(int var1) {
      return new ItemStack(Item.getItemFromBlock(this), 1, this.func_150162_k(var1));
   }
}
