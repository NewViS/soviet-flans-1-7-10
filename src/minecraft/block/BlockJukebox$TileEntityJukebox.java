package net.minecraft.block;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class BlockJukebox$TileEntityJukebox extends TileEntity {

   private ItemStack field_145858_a;


   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      if(var1.hasKey("RecordItem", 10)) {
         this.func_145857_a(ItemStack.loadItemStackFromNBT(var1.getCompoundTag("RecordItem")));
      } else if(var1.getInteger("Record") > 0) {
         this.func_145857_a(new ItemStack(Item.getItemById(var1.getInteger("Record")), 1, 0));
      }

   }

   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      if(this.func_145856_a() != null) {
         var1.setTag("RecordItem", this.func_145856_a().writeToNBT(new NBTTagCompound()));
         var1.setInteger("Record", Item.getIdFromItem(this.func_145856_a().getItem()));
      }

   }

   public ItemStack func_145856_a() {
      return this.field_145858_a;
   }

   public void func_145857_a(ItemStack var1) {
      this.field_145858_a = var1;
      this.markDirty();
   }
}
