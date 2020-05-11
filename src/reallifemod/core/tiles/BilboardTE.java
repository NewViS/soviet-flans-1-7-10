package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class BilboardTE extends TileEntity {

   private int imageNum;


   public BilboardTE() {
      this.setImageNum(0);
   }

   public void func_145839_a(NBTTagCompound nbttagcompound) {
      super.readFromNBT(nbttagcompound);
      this.setImageNum(nbttagcompound.getInteger("imageNumber"));
   }

   public void func_145841_b(NBTTagCompound nbttagcompound) {
      nbttagcompound.setInteger("imageNumber", this.getImageNum());
      super.writeToNBT(nbttagcompound);
   }

   public ResourceLocation image() {
      return new ResourceLocation("reallifemod:images/advert" + this.getImageNum() + ".png");
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

   public int getImageNum() {
      return this.imageNum;
   }

   public void setImageNum(int imageNum) {
      this.imageNum = imageNum;
   }
}
