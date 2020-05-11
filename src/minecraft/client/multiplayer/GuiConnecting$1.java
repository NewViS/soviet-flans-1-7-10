package net.minecraft.client.multiplayer;

import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.util.ChatComponentTranslation;

class GuiConnecting$1 extends Thread {

   // $FF: synthetic field
   final String field_148231_a;
   // $FF: synthetic field
   final int field_148229_b;
   // $FF: synthetic field
   final GuiConnecting field_148230_c;


   GuiConnecting$1(GuiConnecting var1, String var2, String var3, int var4) {
      super(var2);
      this.field_148230_c = var1;
      this.field_148231_a = var3;
      this.field_148229_b = var4;
   }

   public void run() {
      InetAddress var1 = null;

      try {
         if(GuiConnecting.access$000(this.field_148230_c)) {
            return;
         }

         var1 = InetAddress.getByName(this.field_148231_a);
         GuiConnecting.access$102(this.field_148230_c, NetworkManager.provideLanClient(var1, this.field_148229_b));
         GuiConnecting.access$100(this.field_148230_c).setNetHandler(new NetHandlerLoginClient(GuiConnecting.access$100(this.field_148230_c), this.field_148230_c.mc, GuiConnecting.access$200(this.field_148230_c)));
         GuiConnecting.access$100(this.field_148230_c).scheduleOutboundPacket(new C00Handshake(5, this.field_148231_a, this.field_148229_b, EnumConnectionState.LOGIN), new GenericFutureListener[0]);
         GuiConnecting.access$100(this.field_148230_c).scheduleOutboundPacket(new C00PacketLoginStart(this.field_148230_c.mc.getSession().func_148256_e()), new GenericFutureListener[0]);
      } catch (UnknownHostException var5) {
         if(GuiConnecting.access$000(this.field_148230_c)) {
            return;
         }

         GuiConnecting.access$300().error("Couldn\'t connect to server", var5);
         this.field_148230_c.mc.displayGuiScreen(new GuiDisconnected(GuiConnecting.access$200(this.field_148230_c), "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[]{"Unknown host"})));
      } catch (Exception var6) {
         if(GuiConnecting.access$000(this.field_148230_c)) {
            return;
         }

         GuiConnecting.access$300().error("Couldn\'t connect to server", var6);
         String var3 = var6.toString();
         if(var1 != null) {
            String var4 = var1.toString() + ":" + this.field_148229_b;
            var3 = var3.replaceAll(var4, "");
         }

         this.field_148230_c.mc.displayGuiScreen(new GuiDisconnected(GuiConnecting.access$200(this.field_148230_c), "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[]{var3})));
      }

   }
}
