package net.minecraft.item;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.world.World;

public class ItemGlassBottle extends Item {

   public ItemGlassBottle() {
      this.setCreativeTab(CreativeTabs.tabBrewing);
   }

   public IIcon getIconFromDamage(int var1) {
      return Items.potionitem.getIconFromDamage(0);
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(var2, var3, true);
      if(var4 == null) {
         return var1;
      } else {
         if(var4.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK) {
            int var5 = var4.blockX;
            int var6 = var4.blockY;
            int var7 = var4.blockZ;
            if(!var2.canMineBlock(var3, var5, var6, var7)) {
               return var1;
            }

            if(!var3.canPlayerEdit(var5, var6, var7, var4.sideHit, var1)) {
               return var1;
            }

            if(var2.getBlock(var5, var6, var7).getMaterial() == Material.water) {
               --var1.stackSize;
               if(var1.stackSize <= 0) {
                  return new ItemStack(Items.potionitem);
               }

               if(!var3.inventory.addItemStackToInventory(new ItemStack(Items.potionitem))) {
                  var3.dropPlayerItemWithRandomChoice(new ItemStack(Items.potionitem, 1, 0), false);
               }
            }
         }

         return var1;
      }
   }

   public void registerIcons(IIconRegister var1) {}
}
