package net.minecraft.realms;

import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.realms.DisconnectedOnlineScreen;
import net.minecraft.realms.Realms;
import net.minecraft.realms.RealmsConnect;
import net.minecraft.util.ChatComponentTranslation;

public class RealmsConnect$1 extends Thread {

   // $FF: synthetic field
   final String field_154355_a;
   // $FF: synthetic field
   final int field_154356_b;
   // $FF: synthetic field
   final RealmsConnect field_154357_c;


   public RealmsConnect$1(RealmsConnect var1, String var2, String var3, int var4) {
      super(var2);
      this.field_154357_c = var1;
      this.field_154355_a = var3;
      this.field_154356_b = var4;
   }

   public void run() {
      InetAddress var1 = null;

      try {
         var1 = InetAddress.getByName(this.field_154355_a);
         if(RealmsConnect.access$000(this.field_154357_c)) {
            return;
         }

         RealmsConnect.access$102(this.field_154357_c, NetworkManager.provideLanClient(var1, this.field_154356_b));
         if(RealmsConnect.access$000(this.field_154357_c)) {
            return;
         }

         RealmsConnect.access$100(this.field_154357_c).setNetHandler(new NetHandlerLoginClient(RealmsConnect.access$100(this.field_154357_c), Minecraft.getMinecraft(), RealmsConnect.access$200(this.field_154357_c).getProxy()));
         if(RealmsConnect.access$000(this.field_154357_c)) {
            return;
         }

         RealmsConnect.access$100(this.field_154357_c).scheduleOutboundPacket(new C00Handshake(5, this.field_154355_a, this.field_154356_b, EnumConnectionState.LOGIN), new GenericFutureListener[0]);
         if(RealmsConnect.access$000(this.field_154357_c)) {
            return;
         }

         RealmsConnect.access$100(this.field_154357_c).scheduleOutboundPacket(new C00PacketLoginStart(Minecraft.getMinecraft().getSession().func_148256_e()), new GenericFutureListener[0]);
      } catch (UnknownHostException var5) {
         if(RealmsConnect.access$000(this.field_154357_c)) {
            return;
         }

         RealmsConnect.access$300().error("Couldn\'t connect to world", var5);
         Realms.setScreen(new DisconnectedOnlineScreen(RealmsConnect.access$200(this.field_154357_c), "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[]{"Unknown host \'" + this.field_154355_a + "\'"})));
      } catch (Exception var6) {
         if(RealmsConnect.access$000(this.field_154357_c)) {
            return;
         }

         RealmsConnect.access$300().error("Couldn\'t connect to world", var6);
         String var3 = var6.toString();
         if(var1 != null) {
            String var4 = var1.toString() + ":" + this.field_154356_b;
            var3 = var3.replaceAll(var4, "");
         }

         Realms.setScreen(new DisconnectedOnlineScreen(RealmsConnect.access$200(this.field_154357_c), "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[]{var3})));
      }

   }
}
