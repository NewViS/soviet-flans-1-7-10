package net.minecraft.block;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.Facing;
import net.minecraft.world.World;

public class BlockDropper extends BlockDispenser {

   private final IBehaviorDispenseItem field_149947_P = new BehaviorDefaultDispenseItem();


   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon("furnace_side");
      super.field_149944_M = var1.registerIcon("furnace_top");
      super.field_149945_N = var1.registerIcon(this.getTextureName() + "_front_horizontal");
      super.field_149946_O = var1.registerIcon(this.getTextureName() + "_front_vertical");
   }

   protected IBehaviorDispenseItem func_149940_a(ItemStack var1) {
      return this.field_149947_P;
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityDropper();
   }

   protected void func_149941_e(World var1, int var2, int var3, int var4) {
      BlockSourceImpl var5 = new BlockSourceImpl(var1, var2, var3, var4);
      TileEntityDispenser var6 = (TileEntityDispenser)var5.getBlockTileEntity();
      if(var6 != null) {
         int var7 = var6.func_146017_i();
         if(var7 < 0) {
            var1.playAuxSFX(1001, var2, var3, var4, 0);
         } else {
            ItemStack var8 = var6.getStackInSlot(var7);
            int var9 = var1.getBlockMetadata(var2, var3, var4) & 7;
            IInventory var10 = TileEntityHopper.func_145893_b(var1, (double)(var2 + Facing.offsetsXForSide[var9]), (double)(var3 + Facing.offsetsYForSide[var9]), (double)(var4 + Facing.offsetsZForSide[var9]));
            ItemStack var11;
            if(var10 != null) {
               var11 = TileEntityHopper.func_145889_a(var10, var8.copy().splitStack(1), Facing.oppositeSide[var9]);
               if(var11 == null) {
                  var11 = var8.copy();
                  if(--var11.stackSize == 0) {
                     var11 = null;
                  }
               } else {
                  var11 = var8.copy();
               }
            } else {
               var11 = this.field_149947_P.dispense(var5, var8);
               if(var11 != null && var11.stackSize == 0) {
                  var11 = null;
               }
            }

            var6.setInventorySlotContents(var7, var11);
         }

      }
   }
}
