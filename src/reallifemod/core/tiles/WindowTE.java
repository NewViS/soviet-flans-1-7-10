package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class WindowTE extends TileEntity {

   public boolean isOpen;
   public double wingrotX = 0.0D;
   public double wingrotZ = 0.0D;
   public boolean shouldrotatewing;
   private boolean shouldrotateX;
   int windowcount;


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

   public void func_145845_h() {
      if(this.field_145850_b.isRemote && !(this.field_145850_b.getTileEntity(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) instanceof WindowTE)) {
         while(this.field_145850_b.getTileEntity(this.field_145851_c + this.windowcount + 1, this.field_145848_d, this.field_145849_e) instanceof WindowTE) {
            ++this.windowcount;
         }

         if(this.windowcount == 1) {
            WindowTE i = (WindowTE)this.field_145850_b.getTileEntity(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
            if(i != null) {
               if(i != this) {
                  i.shouldrotatewing = true;
               }

               this.shouldrotateX = false;
               i.isOpen = this.isOpen;
               if(i == this) {
                  System.out.println("Thats the reason!");
               }
            }
         }

         if(this.windowcount == 0) {
            ;
         }

         if(this.windowcount > 1) {
            this.shouldrotateX = true;
            this.shouldrotatewing = false;

            for(int var3 = this.field_145851_c; var3 < this.field_145851_c + this.windowcount + 1; ++var3) {
               WindowTE tile = (WindowTE)this.field_145850_b.getTileEntity(var3, this.field_145848_d, this.field_145849_e);
               tile.isOpen = this.isOpen;
               tile.wingrotZ = 0.0D;
               tile.wingrotX = this.wingrotX;
               tile.shouldrotateX = true;
               tile.shouldrotatewing = false;
            }
         }
      }

      if(this.shouldrotateX) {
         this.wingrotZ = 0.0D;
         if(this.isOpen && this.wingrotX > -25.0D) {
            --this.wingrotX;
         }

         if(!this.isOpen && this.wingrotX < 0.0D) {
            ++this.wingrotX;
         }
      } else {
         this.wingrotX = 0.0D;
         if(this.isOpen && this.wingrotZ > -90.0D) {
            --this.wingrotZ;
         }

         if(!this.isOpen && this.wingrotZ < 0.0D) {
            ++this.wingrotZ;
         }
      }

   }

   public void func_145841_b(NBTTagCompound compound) {
      super.writeToNBT(compound);
      compound.setBoolean("IsWindowOpen", this.isOpen);
   }

   public void func_145839_a(NBTTagCompound compound) {
      super.readFromNBT(compound);
      this.isOpen = compound.getBoolean("IsWindowOpen");
   }
}
