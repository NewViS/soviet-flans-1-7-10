package net.minecraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ItemWritableBook extends Item {

   public ItemWritableBook() {
      this.setMaxStackSize(1);
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      var3.displayGUIBook(var1);
      return var1;
   }

   public boolean getShareTag() {
      return true;
   }

   public static boolean func_150930_a(NBTTagCompound var0) {
      if(var0 == null) {
         return false;
      } else if(!var0.hasKey("pages", 9)) {
         return false;
      } else {
         NBTTagList var1 = var0.getTagList("pages", 8);

         for(int var2 = 0; var2 < var1.tagCount(); ++var2) {
            String var3 = var1.getStringTagAt(var2);
            if(var3 == null) {
               return false;
            }

            if(var3.length() > 256) {
               return false;
            }
         }

         return true;
      }
   }
}
