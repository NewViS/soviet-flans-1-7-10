package net.minecraft.init;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

final class Bootstrap$11 extends BehaviorDefaultDispenseItem {

   private final BehaviorDefaultDispenseItem field_150840_b = new BehaviorDefaultDispenseItem();


   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      EnumFacing var3 = BlockDispenser.func_149937_b(var1.getBlockMetadata());
      World var4 = var1.getWorld();
      int var5 = var1.getXInt() + var3.getFrontOffsetX();
      int var6 = var1.getYInt() + var3.getFrontOffsetY();
      int var7 = var1.getZInt() + var3.getFrontOffsetZ();
      Material var8 = var4.getBlock(var5, var6, var7).getMaterial();
      int var9 = var4.getBlockMetadata(var5, var6, var7);
      Item var10;
      if(Material.water.equals(var8) && var9 == 0) {
         var10 = Items.water_bucket;
      } else {
         if(!Material.lava.equals(var8) || var9 != 0) {
            return super.dispenseStack(var1, var2);
         }

         var10 = Items.lava_bucket;
      }

      var4.setBlockToAir(var5, var6, var7);
      if(--var2.stackSize == 0) {
         var2.func_150996_a(var10);
         var2.stackSize = 1;
      } else if(((TileEntityDispenser)var1.getBlockTileEntity()).func_146019_a(new ItemStack(var10)) < 0) {
         this.field_150840_b.dispense(var1, new ItemStack(var10));
      }

      return var2;
   }
}
