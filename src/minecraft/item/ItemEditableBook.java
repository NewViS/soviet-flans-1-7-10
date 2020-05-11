package net.minecraft.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

public class ItemEditableBook extends Item {

   public ItemEditableBook() {
      this.setMaxStackSize(1);
   }

   public static boolean validBookTagContents(NBTTagCompound var0) {
      if(!ItemWritableBook.func_150930_a(var0)) {
         return false;
      } else if(!var0.hasKey("title", 8)) {
         return false;
      } else {
         String var1 = var0.getString("title");
         return var1 != null && var1.length() <= 16?var0.hasKey("author", 8):false;
      }
   }

   public String getItemStackDisplayName(ItemStack var1) {
      if(var1.hasTagCompound()) {
         NBTTagCompound var2 = var1.getTagCompound();
         String var3 = var2.getString("title");
         if(!StringUtils.isNullOrEmpty(var3)) {
            return var3;
         }
      }

      return super.getItemStackDisplayName(var1);
   }

   public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4) {
      if(var1.hasTagCompound()) {
         NBTTagCompound var5 = var1.getTagCompound();
         String var6 = var5.getString("author");
         if(!StringUtils.isNullOrEmpty(var6)) {
            var3.add(EnumChatFormatting.GRAY + StatCollector.translateToLocalFormatted("book.byAuthor", new Object[]{var6}));
         }
      }

   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      var3.displayGUIBook(var1);
      return var1;
   }

   public boolean getShareTag() {
      return true;
   }

   public boolean hasEffect(ItemStack var1) {
      return true;
   }
}
