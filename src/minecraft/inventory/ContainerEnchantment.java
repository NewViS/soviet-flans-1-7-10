package net.minecraft.inventory;

import java.util.List;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment$1;
import net.minecraft.inventory.ContainerEnchantment$2;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerEnchantment extends Container {

   public IInventory tableInventory = new ContainerEnchantment$1(this, "Enchant", true, 1);
   private World worldPointer;
   private int posX;
   private int posY;
   private int posZ;
   private Random rand = new Random();
   public long nameSeed;
   public int[] enchantLevels = new int[3];


   public ContainerEnchantment(InventoryPlayer var1, World var2, int var3, int var4, int var5) {
      this.worldPointer = var2;
      this.posX = var3;
      this.posY = var4;
      this.posZ = var5;
      this.addSlotToContainer(new ContainerEnchantment$2(this, this.tableInventory, 0, 25, 47));

      int var6;
      for(var6 = 0; var6 < 3; ++var6) {
         for(int var7 = 0; var7 < 9; ++var7) {
            this.addSlotToContainer(new Slot(var1, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
         }
      }

      for(var6 = 0; var6 < 9; ++var6) {
         this.addSlotToContainer(new Slot(var1, var6, 8 + var6 * 18, 142));
      }

   }

   public void addCraftingToCrafters(ICrafting var1) {
      super.addCraftingToCrafters(var1);
      var1.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
      var1.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
      var1.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int var1 = 0; var1 < super.crafters.size(); ++var1) {
         ICrafting var2 = (ICrafting)super.crafters.get(var1);
         var2.sendProgressBarUpdate(this, 0, this.enchantLevels[0]);
         var2.sendProgressBarUpdate(this, 1, this.enchantLevels[1]);
         var2.sendProgressBarUpdate(this, 2, this.enchantLevels[2]);
      }

   }

   public void updateProgressBar(int var1, int var2) {
      if(var1 >= 0 && var1 <= 2) {
         this.enchantLevels[var1] = var2;
      } else {
         super.updateProgressBar(var1, var2);
      }

   }

   public void onCraftMatrixChanged(IInventory var1) {
      if(var1 == this.tableInventory) {
         ItemStack var2 = var1.getStackInSlot(0);
         int var3;
         if(var2 != null && var2.isItemEnchantable()) {
            this.nameSeed = this.rand.nextLong();
            if(!this.worldPointer.isRemote) {
               var3 = 0;

               int var4;
               for(var4 = -1; var4 <= 1; ++var4) {
                  for(int var5 = -1; var5 <= 1; ++var5) {
                     if((var4 != 0 || var5 != 0) && this.worldPointer.isAirBlock(this.posX + var5, this.posY, this.posZ + var4) && this.worldPointer.isAirBlock(this.posX + var5, this.posY + 1, this.posZ + var4)) {
                        if(this.worldPointer.getBlock(this.posX + var5 * 2, this.posY, this.posZ + var4 * 2) == Blocks.bookshelf) {
                           ++var3;
                        }

                        if(this.worldPointer.getBlock(this.posX + var5 * 2, this.posY + 1, this.posZ + var4 * 2) == Blocks.bookshelf) {
                           ++var3;
                        }

                        if(var5 != 0 && var4 != 0) {
                           if(this.worldPointer.getBlock(this.posX + var5 * 2, this.posY, this.posZ + var4) == Blocks.bookshelf) {
                              ++var3;
                           }

                           if(this.worldPointer.getBlock(this.posX + var5 * 2, this.posY + 1, this.posZ + var4) == Blocks.bookshelf) {
                              ++var3;
                           }

                           if(this.worldPointer.getBlock(this.posX + var5, this.posY, this.posZ + var4 * 2) == Blocks.bookshelf) {
                              ++var3;
                           }

                           if(this.worldPointer.getBlock(this.posX + var5, this.posY + 1, this.posZ + var4 * 2) == Blocks.bookshelf) {
                              ++var3;
                           }
                        }
                     }
                  }
               }

               for(var4 = 0; var4 < 3; ++var4) {
                  this.enchantLevels[var4] = EnchantmentHelper.calcItemStackEnchantability(this.rand, var4, var3, var2);
               }

               this.detectAndSendChanges();
            }
         } else {
            for(var3 = 0; var3 < 3; ++var3) {
               this.enchantLevels[var3] = 0;
            }
         }
      }

   }

   public boolean enchantItem(EntityPlayer var1, int var2) {
      ItemStack var3 = this.tableInventory.getStackInSlot(0);
      if(this.enchantLevels[var2] > 0 && var3 != null && (var1.experienceLevel >= this.enchantLevels[var2] || var1.capabilities.isCreativeMode)) {
         if(!this.worldPointer.isRemote) {
            List var4 = EnchantmentHelper.buildEnchantmentList(this.rand, var3, this.enchantLevels[var2]);
            boolean var5 = var3.getItem() == Items.book;
            if(var4 != null) {
               var1.addExperienceLevel(-this.enchantLevels[var2]);
               if(var5) {
                  var3.func_150996_a(Items.enchanted_book);
               }

               int var6 = var5 && var4.size() > 1?this.rand.nextInt(var4.size()):-1;

               for(int var7 = 0; var7 < var4.size(); ++var7) {
                  EnchantmentData var8 = (EnchantmentData)var4.get(var7);
                  if(!var5 || var7 != var6) {
                     if(var5) {
                        Items.enchanted_book.addEnchantment(var3, var8);
                     } else {
                        var3.addEnchantment(var8.enchantmentobj, var8.enchantmentLevel);
                     }
                  }
               }

               this.onCraftMatrixChanged(this.tableInventory);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public void onContainerClosed(EntityPlayer var1) {
      super.onContainerClosed(var1);
      if(!this.worldPointer.isRemote) {
         ItemStack var2 = this.tableInventory.getStackInSlotOnClosing(0);
         if(var2 != null) {
            var1.dropPlayerItemWithRandomChoice(var2, false);
         }

      }
   }

   public boolean canInteractWith(EntityPlayer var1) {
      return this.worldPointer.getBlock(this.posX, this.posY, this.posZ) != Blocks.enchanting_table?false:var1.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
   }

   public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
      ItemStack var3 = null;
      Slot var4 = (Slot)super.inventorySlots.get(var2);
      if(var4 != null && var4.getHasStack()) {
         ItemStack var5 = var4.getStack();
         var3 = var5.copy();
         if(var2 == 0) {
            if(!this.mergeItemStack(var5, 1, 37, true)) {
               return null;
            }
         } else {
            if(((Slot)super.inventorySlots.get(0)).getHasStack() || !((Slot)super.inventorySlots.get(0)).isItemValid(var5)) {
               return null;
            }

            if(var5.hasTagCompound() && var5.stackSize == 1) {
               ((Slot)super.inventorySlots.get(0)).putStack(var5.copy());
               var5.stackSize = 0;
            } else if(var5.stackSize >= 1) {
               ((Slot)super.inventorySlots.get(0)).putStack(new ItemStack(var5.getItem(), 1, var5.getItemDamage()));
               --var5.stackSize;
            }
         }

         if(var5.stackSize == 0) {
            var4.putStack((ItemStack)null);
         } else {
            var4.onSlotChanged();
         }

         if(var5.stackSize == var3.stackSize) {
            return null;
         }

         var4.onPickupFromSlot(var1, var5);
      }

      return var3;
   }
}
