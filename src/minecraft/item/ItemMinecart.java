package net.minecraft.item;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMinecart$1;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMinecart extends Item {

   private static final IBehaviorDispenseItem dispenserMinecartBehavior = new ItemMinecart$1();
   public int minecartType;


   public ItemMinecart(int var1) {
      super.maxStackSize = 1;
      this.minecartType = var1;
      this.setCreativeTab(CreativeTabs.tabTransport);
      BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserMinecartBehavior);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(BlockRailBase.func_150051_a(var3.getBlock(var4, var5, var6))) {
         if(!var3.isRemote) {
            EntityMinecart var11 = EntityMinecart.createMinecart(var3, (double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), this.minecartType);
            if(var1.hasDisplayName()) {
               var11.setMinecartName(var1.getDisplayName());
            }

            var3.spawnEntityInWorld(var11);
         }

         --var1.stackSize;
         return true;
      } else {
         return false;
      }
   }

}
