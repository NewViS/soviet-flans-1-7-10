package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.Item$ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.AchievementList;

public class SlotCrafting extends Slot {

   private final IInventory craftMatrix;
   private EntityPlayer thePlayer;
   private int amountCrafted;


   public SlotCrafting(EntityPlayer var1, IInventory var2, IInventory var3, int var4, int var5, int var6) {
      super(var3, var4, var5, var6);
      this.thePlayer = var1;
      this.craftMatrix = var2;
   }

   public boolean isItemValid(ItemStack var1) {
      return false;
   }

   public ItemStack decrStackSize(int var1) {
      if(this.getHasStack()) {
         this.amountCrafted += Math.min(var1, this.getStack().stackSize);
      }

      return super.decrStackSize(var1);
   }

   protected void onCrafting(ItemStack var1, int var2) {
      this.amountCrafted += var2;
      this.onCrafting(var1);
   }

   protected void onCrafting(ItemStack var1) {
      var1.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.amountCrafted);
      this.amountCrafted = 0;
      if(var1.getItem() == Item.getItemFromBlock(Blocks.crafting_table)) {
         this.thePlayer.addStat(AchievementList.buildWorkBench, 1);
      }

      if(var1.getItem() instanceof ItemPickaxe) {
         this.thePlayer.addStat(AchievementList.buildPickaxe, 1);
      }

      if(var1.getItem() == Item.getItemFromBlock(Blocks.furnace)) {
         this.thePlayer.addStat(AchievementList.buildFurnace, 1);
      }

      if(var1.getItem() instanceof ItemHoe) {
         this.thePlayer.addStat(AchievementList.buildHoe, 1);
      }

      if(var1.getItem() == Items.bread) {
         this.thePlayer.addStat(AchievementList.makeBread, 1);
      }

      if(var1.getItem() == Items.cake) {
         this.thePlayer.addStat(AchievementList.bakeCake, 1);
      }

      if(var1.getItem() instanceof ItemPickaxe && ((ItemPickaxe)var1.getItem()).func_150913_i() != Item$ToolMaterial.WOOD) {
         this.thePlayer.addStat(AchievementList.buildBetterPickaxe, 1);
      }

      if(var1.getItem() instanceof ItemSword) {
         this.thePlayer.addStat(AchievementList.buildSword, 1);
      }

      if(var1.getItem() == Item.getItemFromBlock(Blocks.enchanting_table)) {
         this.thePlayer.addStat(AchievementList.enchantments, 1);
      }

      if(var1.getItem() == Item.getItemFromBlock(Blocks.bookshelf)) {
         this.thePlayer.addStat(AchievementList.bookcase, 1);
      }

   }

   public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
      this.onCrafting(var2);

      for(int var3 = 0; var3 < this.craftMatrix.getSizeInventory(); ++var3) {
         ItemStack var4 = this.craftMatrix.getStackInSlot(var3);
         if(var4 != null) {
            this.craftMatrix.decrStackSize(var3, 1);
            if(var4.getItem().hasContainerItem()) {
               ItemStack var5 = new ItemStack(var4.getItem().getContainerItem());
               if(!var4.getItem().doesContainerItemLeaveCraftingGrid(var4) || !this.thePlayer.inventory.addItemStackToInventory(var5)) {
                  if(this.craftMatrix.getStackInSlot(var3) == null) {
                     this.craftMatrix.setInventorySlotContents(var3, var5);
                  } else {
                     this.thePlayer.dropPlayerItemWithRandomChoice(var5, false);
                  }
               }
            }
         }
      }

   }
}
