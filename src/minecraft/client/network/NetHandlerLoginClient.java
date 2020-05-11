package net.minecraft.client.network;

import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import io.netty.util.concurrent.GenericFutureListener;
import java.math.BigInteger;
import java.security.PublicKey;
import java.util.UUID;
import javax.crypto.SecretKey;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerLoginClient$1;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.network.login.server.S02PacketLoginSuccess;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.CryptManager;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerLoginClient implements INetHandlerLoginClient {

   private static final Logger logger = LogManager.getLogger();
   private final Minecraft field_147394_b;
   private final GuiScreen field_147395_c;
   private final NetworkManager field_147393_d;


   public NetHandlerLoginClient(NetworkManager var1, Minecraft var2, GuiScreen var3) {
      this.field_147393_d = var1;
      this.field_147394_b = var2;
      this.field_147395_c = var3;
   }

   public void handleEncryptionRequest(S01PacketEncryptionRequest var1) {
      SecretKey var2 = CryptManager.createNewSharedKey();
      String var3 = var1.func_149609_c();
      PublicKey var4 = var1.func_149608_d();
      String var5 = (new BigInteger(CryptManager.getServerIdHash(var3, var4, var2))).toString(16);
      boolean var6 = this.field_147394_b.func_147104_D() == null || !this.field_147394_b.func_147104_D().func_152585_d();

      try {
         this.func_147391_c().joinServer(this.field_147394_b.getSession().func_148256_e(), this.field_147394_b.getSession().getToken(), var5);
      } catch (AuthenticationUnavailableException var8) {
         if(var6) {
            this.field_147393_d.closeChannel(new ChatComponentTranslation("disconnect.loginFailedInfo", new Object[]{new ChatComponentTranslation("disconnect.loginFailedInfo.serversUnavailable", new Object[0])}));
            return;
         }
      } catch (InvalidCredentialsException var9) {
         if(var6) {
            this.field_147393_d.closeChannel(new ChatComponentTranslation("disconnect.loginFailedInfo", new Object[]{new ChatComponentTranslation("disconnect.loginFailedInfo.invalidSession", new Object[0])}));
            return;
         }
      } catch (AuthenticationException var10) {
         if(var6) {
            this.field_147393_d.closeChannel(new ChatComponentTranslation("disconnect.loginFailedInfo", new Object[]{var10.getMessage()}));
            return;
         }
      }

      this.field_147393_d.scheduleOutboundPacket(new C01PacketEncryptionResponse(var2, var4, var1.func_149607_e()), new GenericFutureListener[]{new NetHandlerLoginClient$1(this, var2)});
   }

   private MinecraftSessionService func_147391_c() {
      return (new YggdrasilAuthenticationService(this.field_147394_b.getProxy(), UUID.randomUUID().toString())).createMinecraftSessionService();
   }

   public void handleLoginSuccess(S02PacketLoginSuccess var1) {
      this.field_147393_d.setConnectionState(EnumConnectionState.PLAY);
   }

   public void onDisconnect(IChatComponent var1) {
      this.field_147394_b.displayGuiScreen(new GuiDisconnected(this.field_147395_c, "connect.failed", var1));
   }

   public void onConnectionStateTransition(EnumConnectionState var1, EnumConnectionState var2) {
      logger.debug("Switching protocol from " + var1 + " to " + var2);
      if(var2 == EnumConnectionState.PLAY) {
         this.field_147393_d.setNetHandler(new NetHandlerPlayClient(this.field_147394_b, this.field_147395_c, this.field_147393_d));
      }

   }

   public void onNetworkTick() {}

   public void handleDisconnect(S00PacketDisconnect var1) {
      this.field_147393_d.closeChannel(var1.func_149603_c());
   }

   // $FF: synthetic method
   static NetworkManager access$000(NetHandlerLoginClient var0) {
      return var0.field_147393_d;
   }

}
