package de.ItsAMysterious.mods.reallifemod.core.gui.container;

import cpw.mods.fml.common.FMLCommonHandler;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MathHelper;

public class SlotOven extends Slot {

   private EntityPlayer thePlayer;
   private int field_75228_b;
   private static final String __OBFID = "CL_00001749";


   public SlotOven(EntityPlayer player, IInventory iInventory, int x, int y, int z) {
      super(iInventory, x, y, z);
      this.thePlayer = player;
   }

   public boolean func_75214_a(ItemStack p_75214_1_) {
      return false;
   }

   public ItemStack func_75209_a(int p_75209_1_) {
      if(this.func_75216_d()) {
         this.field_75228_b += Math.min(p_75209_1_, this.func_75211_c().stackSize);
      }

      return super.decrStackSize(p_75209_1_);
   }

   public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
      this.func_75208_c(p_82870_2_);
      super.onPickupFromSlot(p_82870_1_, p_82870_2_);
   }

   protected void func_75210_a(ItemStack itemStack, int id) {
      this.field_75228_b += id;
      this.func_75208_c(itemStack);
   }

   protected void func_75208_c(ItemStack itemStack) {
      itemStack.onCrafting(this.thePlayer.field_70170_p, this.thePlayer, this.field_75228_b);
      if(!this.thePlayer.field_70170_p.isRemote) {
         int i = this.field_75228_b;
         float f = FurnaceRecipes.smelting().func_151398_b(itemStack);
         int j;
         if(f == 0.0F) {
            i = 0;
         } else if(f < 1.0F) {
            j = MathHelper.floor_float((float)i * f);
            if(j < MathHelper.ceiling_float_int((float)i * f) && (float)Math.random() < (float)i * f - (float)j) {
               ++j;
            }

            i = j;
         }

         while(i > 0) {
            j = EntityXPOrb.getXPSplit(i);
            i -= j;
            this.thePlayer.field_70170_p.spawnEntityInWorld(new EntityXPOrb(this.thePlayer.field_70170_p, this.thePlayer.field_70165_t, this.thePlayer.field_70163_u + 0.5D, this.thePlayer.field_70161_v + 0.5D, j));
         }
      }

      this.field_75228_b = 0;
      FMLCommonHandler.instance().firePlayerSmeltedEvent(this.thePlayer, itemStack);
      if(itemStack.getItem() == RealLifeMod_Items.dirtySteel) {
         ;
      }

   }
}
