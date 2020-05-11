package net.minecraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySign extends TileEntity {

   public String[] signText = new String[]{"", "", "", ""};
   public int lineBeingEdited = -1;
   private boolean field_145916_j = true;
   private EntityPlayer field_145917_k;


   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setString("Text1", this.signText[0]);
      var1.setString("Text2", this.signText[1]);
      var1.setString("Text3", this.signText[2]);
      var1.setString("Text4", this.signText[3]);
   }

   public void readFromNBT(NBTTagCompound var1) {
      this.field_145916_j = false;
      super.readFromNBT(var1);

      for(int var2 = 0; var2 < 4; ++var2) {
         this.signText[var2] = var1.getString("Text" + (var2 + 1));
         if(this.signText[var2].length() > 15) {
            this.signText[var2] = this.signText[var2].substring(0, 15);
         }
      }

   }

   public Packet getDescriptionPacket() {
      String[] var1 = new String[4];
      System.arraycopy(this.signText, 0, var1, 0, 4);
      return new S33PacketUpdateSign(super.xCoord, super.yCoord, super.zCoord, var1);
   }

   public boolean func_145914_a() {
      return this.field_145916_j;
   }

   public void setEditable(boolean var1) {
      this.field_145916_j = var1;
      if(!var1) {
         this.field_145917_k = null;
      }

   }

   public void func_145912_a(EntityPlayer var1) {
      this.field_145917_k = var1;
   }

   public EntityPlayer func_145911_b() {
      return this.field_145917_k;
   }
}
