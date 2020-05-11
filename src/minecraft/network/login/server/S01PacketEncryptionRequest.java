package net.minecraft.network.login.server;

import java.security.PublicKey;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.util.CryptManager;

public class S01PacketEncryptionRequest extends Packet {

   private String field_149612_a;
   private PublicKey field_149610_b;
   private byte[] field_149611_c;


   public S01PacketEncryptionRequest() {}

   public S01PacketEncryptionRequest(String var1, PublicKey var2, byte[] var3) {
      this.field_149612_a = var1;
      this.field_149610_b = var2;
      this.field_149611_c = var3;
   }

   public void readPacketData(PacketBuffer var1) {
      this.field_149612_a = var1.readStringFromBuffer(20);
      this.field_149610_b = CryptManager.decodePublicKey(readBlob(var1));
      this.field_149611_c = readBlob(var1);
   }

   public void writePacketData(PacketBuffer var1) {
      var1.writeStringToBuffer(this.field_149612_a);
      writeBlob(var1, this.field_149610_b.getEncoded());
      writeBlob(var1, this.field_149611_c);
   }

   public void processPacket(INetHandlerLoginClient var1) {
      var1.handleEncryptionRequest(this);
   }

   public String func_149609_c() {
      return this.field_149612_a;
   }

   public PublicKey func_149608_d() {
      return this.field_149610_b;
   }

   public byte[] func_149607_e() {
      return this.field_149611_c;
   }
}
