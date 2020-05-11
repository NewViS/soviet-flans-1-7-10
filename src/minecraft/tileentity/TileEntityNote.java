package net.minecraft.tileentity;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityNote extends TileEntity {

   public byte note;
   public boolean previousRedstoneState;


   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setByte("note", this.note);
   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.note = var1.getByte("note");
      if(this.note < 0) {
         this.note = 0;
      }

      if(this.note > 24) {
         this.note = 24;
      }

   }

   public void changePitch() {
      this.note = (byte)((this.note + 1) % 25);
      this.markDirty();
   }

   public void triggerNote(World var1, int var2, int var3, int var4) {
      if(var1.getBlock(var2, var3 + 1, var4).getMaterial() == Material.air) {
         Material var5 = var1.getBlock(var2, var3 - 1, var4).getMaterial();
         byte var6 = 0;
         if(var5 == Material.rock) {
            var6 = 1;
         }

         if(var5 == Material.sand) {
            var6 = 2;
         }

         if(var5 == Material.glass) {
            var6 = 3;
         }

         if(var5 == Material.wood) {
            var6 = 4;
         }

         var1.addBlockEvent(var2, var3, var4, Blocks.noteblock, var6, this.note);
      }
   }
}
