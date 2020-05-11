package net.minecraft.network.play.server;

import java.util.List;
import net.minecraft.entity.DataWatcher;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S1CPacketEntityMetadata extends Packet {

   private int field_149379_a;
   private List field_149378_b;


   public S1CPacketEntityMetadata() {}

   public S1CPacketEntityMetadata(int var1, DataWatcher var2, boolean var3) {
      this.field_149379_a = var1;
      if(var3) {
         this.field_149378_b = var2.getAllWatched();
      } else {
         this.field_149378_b = var2.getChanged();
      }

   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149379_a = var1.readInt();
      this.field_149378_b = DataWatcher.readWatchedListFromPacketBuffer(var1);
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149379_a);
      DataWatcher.writeWatchedListToPacketBuffer(this.field_149378_b, var1);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityMetadata(this);
   }

   public List func_149376_c() {
      return this.field_149378_b;
   }

   public int func_149375_d() {
      return this.field_149379_a;
   }
}
