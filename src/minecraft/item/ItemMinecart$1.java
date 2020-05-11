package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

final class ItemMinecart$1 extends BehaviorDefaultDispenseItem {

   private final BehaviorDefaultDispenseItem behaviourDefaultDispenseItem = new BehaviorDefaultDispenseItem();


   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      EnumFacing var3 = BlockDispenser.func_149937_b(var1.getBlockMetadata());
      World var4 = var1.getWorld();
      double var5 = var1.getX() + (double)((float)var3.getFrontOffsetX() * 1.125F);
      double var7 = var1.getY() + (double)((float)var3.getFrontOffsetY() * 1.125F);
      double var9 = var1.getZ() + (double)((float)var3.getFrontOffsetZ() * 1.125F);
      int var11 = var1.getXInt() + var3.getFrontOffsetX();
      int var12 = var1.getYInt() + var3.getFrontOffsetY();
      int var13 = var1.getZInt() + var3.getFrontOffsetZ();
      Block var14 = var4.getBlock(var11, var12, var13);
      double var15;
      if(BlockRailBase.func_150051_a(var14)) {
         var15 = 0.0D;
      } else {
         if(var14.getMaterial() != Material.air || !BlockRailBase.func_150051_a(var4.getBlock(var11, var12 - 1, var13))) {
            return this.behaviourDefaultDispenseItem.dispense(var1, var2);
         }

         var15 = -1.0D;
      }

      EntityMinecart var17 = EntityMinecart.createMinecart(var4, var5, var7 + var15, var9, ((ItemMinecart)var2.getItem()).minecartType);
      if(var2.hasDisplayName()) {
         var17.setMinecartName(var2.getDisplayName());
      }

      var4.spawnEntityInWorld(var17);
      var2.splitStack(1);
      return var2;
   }

   protected void playDispenseSound(IBlockSource var1) {
      var1.getWorld().playAuxSFX(1000, var1.getXInt(), var1.getYInt(), var1.getZInt(), 0);
   }
}
