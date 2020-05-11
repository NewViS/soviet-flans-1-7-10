package net.minecraft.network.play.server;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S12PacketEntityVelocity extends Packet {

   private int field_149417_a;
   private int field_149415_b;
   private int field_149416_c;
   private int field_149414_d;


   public S12PacketEntityVelocity() {}

   public S12PacketEntityVelocity(Entity var1) {
      this(var1.getEntityId(), var1.motionX, var1.motionY, var1.motionZ);
   }

   public S12PacketEntityVelocity(int var1, double var2, double var4, double var6) {
      this.field_149417_a = var1;
      double var8 = 3.9D;
      if(var2 < -var8) {
         var2 = -var8;
      }

      if(var4 < -var8) {
         var4 = -var8;
      }

      if(var6 < -var8) {
         var6 = -var8;
      }

      if(var2 > var8) {
         var2 = var8;
      }

      if(var4 > var8) {
         var4 = var8;
      }

      if(var6 > var8) {
         var6 = var8;
      }

      this.field_149415_b = (int)(var2 * 8000.0D);
      this.field_149416_c = (int)(var4 * 8000.0D);
      this.field_149414_d = (int)(var6 * 8000.0D);
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149417_a = var1.readInt();
      this.field_149415_b = var1.readShort();
      this.field_149416_c = var1.readShort();
      this.field_149414_d = var1.readShort();
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149417_a);
      var1.writeShort(this.field_149415_b);
      var1.writeShort(this.field_149416_c);
      var1.writeShort(this.field_149414_d);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityVelocity(this);
   }

   public String serialize() {
      return String.format("id=%d, x=%.2f, y=%.2f, z=%.2f", new Object[]{Integer.valueOf(this.field_149417_a), Float.valueOf((float)this.field_149415_b / 8000.0F), Float.valueOf((float)this.field_149416_c / 8000.0F), Float.valueOf((float)this.field_149414_d / 8000.0F)});
   }

   public int func_149412_c() {
      return this.field_149417_a;
   }

   public int func_149411_d() {
      return this.field_149415_b;
   }

   public int func_149410_e() {
      return this.field_149416_c;
   }

   public int func_149409_f() {
      return this.field_149414_d;
   }
}
