package de.ItsAMysterious.mods.reallifemod.core.gui.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.core.gui.container.OvenTileEntity;
import de.ItsAMysterious.mods.reallifemod.core.gui.container.SlotOven;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerOven extends Container {

   private OvenTileEntity tileOven;
   private int lastCookTime;
   private int lastBurnTime;
   private int lastItemBurnTime;
   private static final String __OBFID = "CL_00001748";


   public ContainerOven(InventoryPlayer p_i1812_1_, OvenTileEntity tile) {
      this.tileOven = tile;
      this.func_75146_a(new Slot(tile, 0, 56, 17));
      this.func_75146_a(new Slot(tile, 1, 56, 53));
      this.func_75146_a(new SlotOven(p_i1812_1_.player, tile, 2, 116, 35));

      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.func_75146_a(new Slot(p_i1812_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.func_75146_a(new Slot(p_i1812_1_, i, 8 + i * 18, 142));
      }

   }

   public void func_75132_a(ICrafting p_75132_1_) {
      super.addCraftingToCrafters(p_75132_1_);
      p_75132_1_.sendProgressBarUpdate(this, 1, this.tileOven.ovenBurnTime);
      p_75132_1_.sendProgressBarUpdate(this, 2, this.tileOven.currentItemBurnTime);
   }

   public void func_75142_b() {
      super.detectAndSendChanges();

      for(int i = 0; i < this.field_75149_d.size(); ++i) {
         ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
         if(this.lastBurnTime != this.tileOven.ovenBurnTime) {
            icrafting.sendProgressBarUpdate(this, 1, this.tileOven.ovenBurnTime);
         }

         if(this.lastItemBurnTime != this.tileOven.currentItemBurnTime) {
            icrafting.sendProgressBarUpdate(this, 2, this.tileOven.currentItemBurnTime);
         }
      }

      this.lastBurnTime = this.tileOven.ovenBurnTime;
      this.lastItemBurnTime = this.tileOven.currentItemBurnTime;
   }

   @SideOnly(Side.CLIENT)
   public void func_75137_b(int ovenB, int cItemB) {
      if(ovenB == 1) {
         this.tileOven.ovenBurnTime = cItemB;
      }

      if(ovenB == 2) {
         this.tileOven.currentItemBurnTime = cItemB;
      }

   }

   public boolean func_75145_c(EntityPlayer player) {
      return this.tileOven.func_70300_a(player);
   }

   public ItemStack func_82846_b(EntityPlayer player, int slotID) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.field_75151_b.get(slotID);
      if(slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if(slotID == 2) {
            if(!this.func_75135_a(itemstack1, 3, 39, true)) {
               return null;
            }

            slot.onSlotChange(itemstack1, itemstack);
         } else if(slotID != 1 && slotID != 0) {
            if(FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null) {
               if(!this.func_75135_a(itemstack1, 0, 1, false)) {
                  return null;
               }
            } else if(TileEntityFurnace.isItemFuel(itemstack1)) {
               if(!this.func_75135_a(itemstack1, 1, 2, false)) {
                  return null;
               }
            } else if(slotID >= 3 && slotID < 30) {
               if(!this.func_75135_a(itemstack1, 30, 39, false)) {
                  return null;
               }
            } else if(slotID >= 30 && slotID < 39 && !this.func_75135_a(itemstack1, 3, 30, false)) {
               return null;
            }
         } else if(!this.func_75135_a(itemstack1, 3, 39, false)) {
            return null;
         }

         if(itemstack1.stackSize == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }

         if(itemstack1.stackSize == itemstack.stackSize) {
            return null;
         }

         slot.onPickupFromSlot(player, itemstack1);
      }

      return itemstack;
   }
}
