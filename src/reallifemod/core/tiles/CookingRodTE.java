package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.fireplaceBlock;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FireplaceTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class CookingRodTE extends TileEntity implements IInventory {

   private static final int INV_SIZE = 4;
   private ItemStack[] CookingRodItems = new ItemStack[4];
   private ItemStack[] OutPut = new ItemStack[4];
   private float[] cookingprogress = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
   private float[] cookinglength = new float[]{0.0F, 0.0F, 0.0F, 0.0F};
   public double rodRotation;


   public void func_145845_h() {
      super.updateEntity();
      if(this.field_145850_b.getBlock(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) != null && this.field_145850_b.getBlock(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof fireplaceBlock) {
         if(((FireplaceTE)this.field_145850_b.getTileEntity(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)).burning && !this.field_145850_b.isRemote) {
            for(int i = 0; i < 4; ++i) {
               ItemStack stack = this.func_70301_a(i);
               if(stack != null && FurnaceRecipes.smelting().getSmeltingResult(stack) != null) {
                  this.cookinglength[i] = (float)stack.stackSize;
                  ++this.cookingprogress[i];
                  if(this.cookingprogress[i] % 20.0F == 0.0F) {
                     this.func_70298_a(i, 1);
                     this.func_70299_a(4 + i, FurnaceRecipes.smelting().getSmeltingResult(stack));
                     if(this.cookingprogress[i] >= this.cookinglength[i]) {
                        this.cookingprogress[i] = 0.0F;
                        this.cookinglength[i] = 0.0F;
                     }
                  }
               }

               if(stack == null) {
                  this.cookinglength[i] = 0.0F;
                  this.cookingprogress[i] = 0.0F;
               }
            }
         }

         if(this.cookingprogress[0] + this.cookingprogress[1] + this.cookingprogress[2] + this.cookingprogress[3] >= 0.0F) {
            ++this.rodRotation;
            this.field_145850_b.spawnParticle("smoke", (double)this.field_145851_c, (double)this.field_145848_d + 0.9D, (double)this.field_145849_e, 0.0D, 0.25D, 0.0D);
         }
      }

   }

   public int func_70302_i_() {
      return 8;
   }

   public ItemStack func_70301_a(int id) {
      return id >= 0 && id < 4?this.CookingRodItems[id]:(id >= 4 && id < 8?this.OutPut[id - 4]:null);
   }

   public ItemStack func_70298_a(int id, int value) {
      ItemStack stack = this.func_70301_a(id);
      if(stack != null) {
         if(stack.stackSize > value) {
            stack = stack.splitStack(value);
            if(stack.stackSize == 0) {
               this.func_70299_a(id, (ItemStack)null);
            }
         } else {
            this.func_70299_a(id, (ItemStack)null);
         }

         this.onInventoryChanged();
      }

      return stack;
   }

   public ItemStack func_70304_b(int id) {
      return id >= 0 && id < 4?this.CookingRodItems[id]:(id >= 4 && id < 8?this.OutPut[id - 4]:null);
   }

   public void func_70299_a(int id, ItemStack stack) {
      if(id >= 0 && id < 4) {
         if(this.CookingRodItems[id] != null && stack != null && stack.getItem().equals(this.CookingRodItems[id].getItem())) {
            this.CookingRodItems[id] = new ItemStack(stack.getItem(), this.CookingRodItems[id].stackSize + 1);
         } else {
            this.CookingRodItems[id] = stack;
         }

         this.CookingRodItems[id] = stack;
      } else if(id >= 4 && id < 8) {
         if(this.OutPut[id - 4] != null && stack != null && stack.getItem().equals(this.OutPut[id - 4].getItem())) {
            this.OutPut[id - 4] = new ItemStack(stack.getItem(), this.OutPut[id - 4].stackSize + 1);
         } else {
            this.OutPut[id - 4] = stack;
         }
      }

   }

   public String func_145825_b() {
      return "CookingRod";
   }

   public boolean func_145818_k_() {
      return true;
   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_70300_a(EntityPlayer player) {
      return true;
   }

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public boolean func_94041_b(int id, ItemStack stack) {
      return stack.getItem() instanceof ItemFood && FurnaceRecipes.smelting().getSmeltingResult(stack) != null;
   }

   public int func_145832_p() {
      if(this.field_145850_b != null) {
         if(this.field_145847_g == -1) {
            this.field_145847_g = this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, this.field_145849_e);
         }

         return this.field_145847_g;
      } else {
         return 1;
      }
   }

   public int getNextEmptySlot() {
      for(int i = 0; i < 4; ++i) {
         if(this.CookingRodItems[i] == null) {
            return i;
         }
      }

      return -1;
   }

   public void func_145841_b(NBTTagCompound combound) {
      for(int i = 0; i < 4; ++i) {
         combound.setFloat("CookinProgress_" + i, this.cookingprogress[i]);
      }

      super.writeToNBT(combound);
   }

   public void func_145839_a(NBTTagCompound combound) {
      super.readFromNBT(combound);

      for(int i = 0; i < 4; ++i) {
         this.cookingprogress[i] = combound.getFloat("CookinProgress_" + i);
      }

   }

   public void onInventoryChanged() {
      for(int i = 0; i < this.func_70302_i_(); ++i) {
         if(this.func_70301_a(i) != null && this.func_70301_a(i).stackSize == 0) {
            this.func_70299_a(i, (ItemStack)null);
         }
      }

   }

   public boolean isEmpty() {
      return this.CookingRodItems[0] != null && this.CookingRodItems[0].getItem() != null?false:this.CookingRodItems[0] == null && this.CookingRodItems[1] == null && this.CookingRodItems[2] == null && this.CookingRodItems[3] == null;
   }

   public ItemStack[] getItems() {
      return this.CookingRodItems;
   }
}
