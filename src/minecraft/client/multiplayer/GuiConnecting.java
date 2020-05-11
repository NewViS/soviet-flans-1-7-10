package net.minecraft.client.multiplayer;

import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting$1;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ChatComponentText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiConnecting extends GuiScreen {

   private static final AtomicInteger field_146372_a = new AtomicInteger(0);
   private static final Logger logger = LogManager.getLogger();
   private NetworkManager field_146371_g;
   private boolean field_146373_h;
   private final GuiScreen field_146374_i;


   public GuiConnecting(GuiScreen var1, Minecraft var2, ServerData var3) {
      super.mc = var2;
      this.field_146374_i = var1;
      ServerAddress var4 = ServerAddress.func_78860_a(var3.serverIP);
      var2.loadWorld((WorldClient)null);
      var2.setServerData(var3);
      this.func_146367_a(var4.getIP(), var4.getPort());
   }

   public GuiConnecting(GuiScreen var1, Minecraft var2, String var3, int var4) {
      super.mc = var2;
      this.field_146374_i = var1;
      var2.loadWorld((WorldClient)null);
      this.func_146367_a(var3, var4);
   }

   private void func_146367_a(String var1, int var2) {
      logger.info("Connecting to " + var1 + ", " + var2);
      (new GuiConnecting$1(this, "Server Connector #" + field_146372_a.incrementAndGet(), var1, var2)).start();
   }

   public void updateScreen() {
      if(this.field_146371_g != null) {
         if(this.field_146371_g.isChannelOpen()) {
            this.field_146371_g.processReceivedPackets();
         } else if(this.field_146371_g.getExitMessage() != null) {
            this.field_146371_g.getNetHandler().onDisconnect(this.field_146371_g.getExitMessage());
         }
      }

   }

   protected void keyTyped(char var1, int var2) {}

   public void initGui() {
      super.buttonList.clear();
      super.buttonList.add(new GuiButton(0, super.width / 2 - 100, super.height / 2 + 50, I18n.format("gui.cancel", new Object[0])));
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.id == 0) {
         this.field_146373_h = true;
         if(this.field_146371_g != null) {
            this.field_146371_g.closeChannel(new ChatComponentText("Aborted"));
         }

         super.mc.displayGuiScreen(this.field_146374_i);
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      if(this.field_146371_g == null) {
         this.drawCenteredString(super.fontRendererObj, I18n.format("connect.connecting", new Object[0]), super.width / 2, super.height / 2 - 50, 16777215);
      } else {
         this.drawCenteredString(super.fontRendererObj, I18n.format("connect.authorizing", new Object[0]), super.width / 2, super.height / 2 - 50, 16777215);
      }

      super.drawScreen(var1, var2, var3);
   }

   // $FF: synthetic method
   static boolean access$000(GuiConnecting var0) {
      return var0.field_146373_h;
   }

   // $FF: synthetic method
   static NetworkManager access$102(GuiConnecting var0, NetworkManager var1) {
      return var0.field_146371_g = var1;
   }

   // $FF: synthetic method
   static NetworkManager access$100(GuiConnecting var0) {
      return var0.field_146371_g;
   }

   // $FF: synthetic method
   static GuiScreen access$200(GuiConnecting var0) {
      return var0.field_146374_i;
   }

   // $FF: synthetic method
   static Logger access$300() {
      return logger;
   }

}
