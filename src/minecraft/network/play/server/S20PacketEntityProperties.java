package net.minecraft.network.play.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S20PacketEntityProperties$Snapshot;

public class S20PacketEntityProperties extends Packet {

   private int field_149445_a;
   private final List field_149444_b = new ArrayList();


   public S20PacketEntityProperties() {}

   public S20PacketEntityProperties(int var1, Collection var2) {
      this.field_149445_a = var1;
      Iterator var3 = var2.iterator();

      while(var3.hasNext()) {
         IAttributeInstance var4 = (IAttributeInstance)var3.next();
         this.field_149444_b.add(new S20PacketEntityProperties$Snapshot(this, var4.getAttribute().getAttributeUnlocalizedName(), var4.getBaseValue(), var4.func_111122_c()));
      }

   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149445_a = var1.readInt();
      int var2 = var1.readInt();

      for(int var3 = 0; var3 < var2; ++var3) {
         String var4 = var1.readStringFromBuffer(64);
         double var5 = var1.readDouble();
         ArrayList var7 = new ArrayList();
         short var8 = var1.readShort();

         for(int var9 = 0; var9 < var8; ++var9) {
            UUID var10 = new UUID(var1.readLong(), var1.readLong());
            var7.add(new AttributeModifier(var10, "Unknown synced attribute modifier", var1.readDouble(), var1.readByte()));
         }

         this.field_149444_b.add(new S20PacketEntityProperties$Snapshot(this, var4, var5, var7));
      }

   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.field_149445_a);
      var1.writeInt(this.field_149444_b.size());
      Iterator var2 = this.field_149444_b.iterator();

      while(var2.hasNext()) {
         S20PacketEntityProperties$Snapshot var3 = (S20PacketEntityProperties$Snapshot)var2.next();
         var1.writeStringToBuffer(var3.func_151409_a());
         var1.writeDouble(var3.func_151410_b());
         var1.writeShort(var3.func_151408_c().size());
         Iterator var4 = var3.func_151408_c().iterator();

         while(var4.hasNext()) {
            AttributeModifier var5 = (AttributeModifier)var4.next();
            var1.writeLong(var5.getID().getMostSignificantBits());
            var1.writeLong(var5.getID().getLeastSignificantBits());
            var1.writeDouble(var5.getAmount());
            var1.writeByte(var5.getOperation());
         }
      }

   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityProperties(this);
   }

   public int func_149442_c() {
      return this.field_149445_a;
   }

   public List func_149441_d() {
      return this.field_149444_b;
   }
}
