package net.minecraft.network.play.client;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C02PacketUseEntity$Action;
import net.minecraft.world.World;

public class C02PacketUseEntity extends Packet {

   private int field_149567_a;
   private C02PacketUseEntity$Action field_149566_b;


   public C02PacketUseEntity() {}

   public C02PacketUseEntity(Entity var1, C02PacketUseEntity$Action var2) {
      this.field_149567_a = var1.getEntityId();
      this.field_149566_b = var2;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149567_a = var1.readInt();
      this.field_149566_b = C02PacketUseEntity$Action.access$000()[var1.readByte() % C02PacketUseEntity$Action.access$000().length];
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149567_a);
      var1.writeByte(C02PacketUseEntity$Action.access$100(this.field_149566_b));
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processUseEntity(this);
   }

   public Entity func_149564_a(World var1) {
      return var1.getEntityByID(this.field_149567_a);
   }

   public C02PacketUseEntity$Action func_149565_c() {
      return this.field_149566_b;
   }
}
