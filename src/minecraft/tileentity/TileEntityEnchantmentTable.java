package net.minecraft.tileentity;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEnchantmentTable extends TileEntity {

   public int field_145926_a;
   public float field_145933_i;
   public float field_145931_j;
   public float field_145932_k;
   public float field_145929_l;
   public float field_145930_m;
   public float field_145927_n;
   public float field_145928_o;
   public float field_145925_p;
   public float field_145924_q;
   private static Random field_145923_r = new Random();
   private String field_145922_s;


   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      if(this.func_145921_b()) {
         var1.setString("CustomName", this.field_145922_s);
      }

   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      if(var1.hasKey("CustomName", 8)) {
         this.field_145922_s = var1.getString("CustomName");
      }

   }

   public void updateEntity() {
      super.updateEntity();
      this.field_145927_n = this.field_145930_m;
      this.field_145925_p = this.field_145928_o;
      EntityPlayer var1 = super.worldObj.getClosestPlayer((double)((float)super.xCoord + 0.5F), (double)((float)super.yCoord + 0.5F), (double)((float)super.zCoord + 0.5F), 3.0D);
      if(var1 != null) {
         double var2 = var1.posX - (double)((float)super.xCoord + 0.5F);
         double var4 = var1.posZ - (double)((float)super.zCoord + 0.5F);
         this.field_145924_q = (float)Math.atan2(var4, var2);
         this.field_145930_m += 0.1F;
         if(this.field_145930_m < 0.5F || field_145923_r.nextInt(40) == 0) {
            float var6 = this.field_145932_k;

            do {
               this.field_145932_k += (float)(field_145923_r.nextInt(4) - field_145923_r.nextInt(4));
            } while(var6 == this.field_145932_k);
         }
      } else {
         this.field_145924_q += 0.02F;
         this.field_145930_m -= 0.1F;
      }

      while(this.field_145928_o >= 3.1415927F) {
         this.field_145928_o -= 6.2831855F;
      }

      while(this.field_145928_o < -3.1415927F) {
         this.field_145928_o += 6.2831855F;
      }

      while(this.field_145924_q >= 3.1415927F) {
         this.field_145924_q -= 6.2831855F;
      }

      while(this.field_145924_q < -3.1415927F) {
         this.field_145924_q += 6.2831855F;
      }

      float var7;
      for(var7 = this.field_145924_q - this.field_145928_o; var7 >= 3.1415927F; var7 -= 6.2831855F) {
         ;
      }

      while(var7 < -3.1415927F) {
         var7 += 6.2831855F;
      }

      this.field_145928_o += var7 * 0.4F;
      if(this.field_145930_m < 0.0F) {
         this.field_145930_m = 0.0F;
      }

      if(this.field_145930_m > 1.0F) {
         this.field_145930_m = 1.0F;
      }

      ++this.field_145926_a;
      this.field_145931_j = this.field_145933_i;
      float var3 = (this.field_145932_k - this.field_145933_i) * 0.4F;
      float var8 = 0.2F;
      if(var3 < -var8) {
         var3 = -var8;
      }

      if(var3 > var8) {
         var3 = var8;
      }

      this.field_145929_l += (var3 - this.field_145929_l) * 0.9F;
      this.field_145933_i += this.field_145929_l;
   }

   public String func_145919_a() {
      return this.func_145921_b()?this.field_145922_s:"container.enchant";
   }

   public boolean func_145921_b() {
      return this.field_145922_s != null && this.field_145922_s.length() > 0;
   }

   public void func_145920_a(String var1) {
      this.field_145922_s = var1;
   }

}
