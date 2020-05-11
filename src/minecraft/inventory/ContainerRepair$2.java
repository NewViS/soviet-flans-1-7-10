package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

class ContainerRepair$2 extends Slot {

   // $FF: synthetic field
   final World field_135071_a;
   // $FF: synthetic field
   final int field_135069_b;
   // $FF: synthetic field
   final int field_135070_c;
   // $FF: synthetic field
   final int field_135067_d;
   // $FF: synthetic field
   final ContainerRepair repairContainer;


   ContainerRepair$2(ContainerRepair var1, IInventory var2, int var3, int var4, int var5, World var6, int var7, int var8, int var9) {
      super(var2, var3, var4, var5);
      this.repairContainer = var1;
      this.field_135071_a = var6;
      this.field_135069_b = var7;
      this.field_135070_c = var8;
      this.field_135067_d = var9;
   }

   public boolean isItemValid(ItemStack var1) {
      return false;
   }

   public boolean canTakeStack(EntityPlayer var1) {
      return (var1.capabilities.isCreativeMode || var1.experienceLevel >= this.repairContainer.maximumCost) && this.repairContainer.maximumCost > 0 && this.getHasStack();
   }

   public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
      if(!var1.capabilities.isCreativeMode) {
         var1.addExperienceLevel(-this.repairContainer.maximumCost);
      }

      ContainerRepair.access$000(this.repairContainer).setInventorySlotContents(0, (ItemStack)null);
      if(ContainerRepair.access$100(this.repairContainer) > 0) {
         ItemStack var3 = ContainerRepair.access$000(this.repairContainer).getStackInSlot(1);
         if(var3 != null && var3.stackSize > ContainerRepair.access$100(this.repairContainer)) {
            var3.stackSize -= ContainerRepair.access$100(this.repairContainer);
            ContainerRepair.access$000(this.repairContainer).setInventorySlotContents(1, var3);
         } else {
            ContainerRepair.access$000(this.repairContainer).setInventorySlotContents(1, (ItemStack)null);
         }
      } else {
         ContainerRepair.access$000(this.repairContainer).setInventorySlotContents(1, (ItemStack)null);
      }

      this.repairContainer.maximumCost = 0;
      if(!var1.capabilities.isCreativeMode && !this.field_135071_a.isRemote && this.field_135071_a.getBlock(this.field_135069_b, this.field_135070_c, this.field_135067_d) == Blocks.anvil && var1.getRNG().nextFloat() < 0.12F) {
         int var6 = this.field_135071_a.getBlockMetadata(this.field_135069_b, this.field_135070_c, this.field_135067_d);
         int var4 = var6 & 3;
         int var5 = var6 >> 2;
         ++var5;
         if(var5 > 2) {
            this.field_135071_a.setBlockToAir(this.field_135069_b, this.field_135070_c, this.field_135067_d);
            this.field_135071_a.playAuxSFX(1020, this.field_135069_b, this.field_135070_c, this.field_135067_d, 0);
         } else {
            this.field_135071_a.setBlockMetadataWithNotify(this.field_135069_b, this.field_135070_c, this.field_135067_d, var4 | var5 << 2, 2);
            this.field_135071_a.playAuxSFX(1021, this.field_135069_b, this.field_135070_c, this.field_135067_d, 0);
         }
      } else if(!this.field_135071_a.isRemote) {
         this.field_135071_a.playAuxSFX(1021, this.field_135069_b, this.field_135070_c, this.field_135067_d, 0);
      }

   }
}
