package net.minecraft.server.network;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import io.netty.util.concurrent.GenericFutureListener;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.SecretKey;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.INetHandlerLoginServer;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.network.login.server.S02PacketLoginSuccess;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.NetHandlerLoginServer$1;
import net.minecraft.server.network.NetHandlerLoginServer$LoginState;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerLoginServer implements INetHandlerLoginServer {

   private static final AtomicInteger field_147331_b = new AtomicInteger(0);
   private static final Logger logger = LogManager.getLogger();
   private static final Random field_147329_d = new Random();
   private final byte[] field_147330_e = new byte[4];
   private final MinecraftServer field_147327_f;
   public final NetworkManager field_147333_a;
   private NetHandlerLoginServer$LoginState field_147328_g;
   private int field_147336_h;
   private GameProfile field_147337_i;
   private String field_147334_j;
   private SecretKey field_147335_k;


   public NetHandlerLoginServer(MinecraftServer var1, NetworkManager var2) {
      this.field_147328_g = NetHandlerLoginServer$LoginState.HELLO;
      this.field_147334_j = "";
      this.field_147327_f = var1;
      this.field_147333_a = var2;
      field_147329_d.nextBytes(this.field_147330_e);
   }

   public void onNetworkTick() {
      if(this.field_147328_g == NetHandlerLoginServer$LoginState.READY_TO_ACCEPT) {
         this.func_147326_c();
      }

      if(this.field_147336_h++ == 600) {
         this.func_147322_a("Took too long to log in");
      }

   }

   public void func_147322_a(String var1) {
      try {
         logger.info("Disconnecting " + this.func_147317_d() + ": " + var1);
         ChatComponentText var2 = new ChatComponentText(var1);
         this.field_147333_a.scheduleOutboundPacket(new S00PacketDisconnect(var2), new GenericFutureListener[0]);
         this.field_147333_a.closeChannel(var2);
      } catch (Exception var3) {
         logger.error("Error whilst disconnecting player", var3);
      }

   }

   public void func_147326_c() {
      if(!this.field_147337_i.isComplete()) {
         this.field_147337_i = this.func_152506_a(this.field_147337_i);
      }

      String var1 = this.field_147327_f.getConfigurationManager().allowUserToConnect(this.field_147333_a.getSocketAddress(), this.field_147337_i);
      if(var1 != null) {
         this.func_147322_a(var1);
      } else {
         this.field_147328_g = NetHandlerLoginServer$LoginState.ACCEPTED;
         this.field_147333_a.scheduleOutboundPacket(new S02PacketLoginSuccess(this.field_147337_i), new GenericFutureListener[0]);
         this.field_147327_f.getConfigurationManager().initializeConnectionToPlayer(this.field_147333_a, this.field_147327_f.getConfigurationManager().createPlayerForUser(this.field_147337_i));
      }

   }

   public void onDisconnect(IChatComponent var1) {
      logger.info(this.func_147317_d() + " lost connection: " + var1.getUnformattedText());
   }

   public String func_147317_d() {
      return this.field_147337_i != null?this.field_147337_i.toString() + " (" + this.field_147333_a.getSocketAddress().toString() + ")":String.valueOf(this.field_147333_a.getSocketAddress());
   }

   public void onConnectionStateTransition(EnumConnectionState var1, EnumConnectionState var2) {
      Validate.validState(this.field_147328_g == NetHandlerLoginServer$LoginState.ACCEPTED || this.field_147328_g == NetHandlerLoginServer$LoginState.HELLO, "Unexpected change in protocol", new Object[0]);
      Validate.validState(var2 == EnumConnectionState.PLAY || var2 == EnumConnectionState.LOGIN, "Unexpected protocol " + var2, new Object[0]);
   }

   public void processLoginStart(C00PacketLoginStart var1) {
      Validate.validState(this.field_147328_g == NetHandlerLoginServer$LoginState.HELLO, "Unexpected hello packet", new Object[0]);
      this.field_147337_i = var1.func_149304_c();
      if(this.field_147327_f.isServerInOnlineMode() && !this.field_147333_a.isLocalChannel()) {
         this.field_147328_g = NetHandlerLoginServer$LoginState.KEY;
         this.field_147333_a.scheduleOutboundPacket(new S01PacketEncryptionRequest(this.field_147334_j, this.field_147327_f.getKeyPair().getPublic(), this.field_147330_e), new GenericFutureListener[0]);
      } else {
         this.field_147328_g = NetHandlerLoginServer$LoginState.READY_TO_ACCEPT;
      }

   }

   public void processEncryptionResponse(C01PacketEncryptionResponse var1) {
      Validate.validState(this.field_147328_g == NetHandlerLoginServer$LoginState.KEY, "Unexpected key packet", new Object[0]);
      PrivateKey var2 = this.field_147327_f.getKeyPair().getPrivate();
      if(!Arrays.equals(this.field_147330_e, var1.func_149299_b(var2))) {
         throw new IllegalStateException("Invalid nonce!");
      } else {
         this.field_147335_k = var1.func_149300_a(var2);
         this.field_147328_g = NetHandlerLoginServer$LoginState.AUTHENTICATING;
         this.field_147333_a.enableEncryption(this.field_147335_k);
         (new NetHandlerLoginServer$1(this, "User Authenticator #" + field_147331_b.incrementAndGet())).start();
      }
   }

   protected GameProfile func_152506_a(GameProfile var1) {
      UUID var2 = UUID.nameUUIDFromBytes(("OfflinePlayer:" + var1.getName()).getBytes(Charsets.UTF_8));
      return new GameProfile(var2, var1.getName());
   }

   // $FF: synthetic method
   static GameProfile access$000(NetHandlerLoginServer var0) {
      return var0.field_147337_i;
   }

   // $FF: synthetic method
   static String access$100(NetHandlerLoginServer var0) {
      return var0.field_147334_j;
   }

   // $FF: synthetic method
   static MinecraftServer access$200(NetHandlerLoginServer var0) {
      return var0.field_147327_f;
   }

   // $FF: synthetic method
   static SecretKey access$300(NetHandlerLoginServer var0) {
      return var0.field_147335_k;
   }

   // $FF: synthetic method
   static GameProfile access$002(NetHandlerLoginServer var0, GameProfile var1) {
      return var0.field_147337_i = var1;
   }

   // $FF: synthetic method
   static Logger access$400() {
      return logger;
   }

   // $FF: synthetic method
   static NetHandlerLoginServer$LoginState access$502(NetHandlerLoginServer var0, NetHandlerLoginServer$LoginState var1) {
      return var0.field_147328_g = var1;
   }

}
