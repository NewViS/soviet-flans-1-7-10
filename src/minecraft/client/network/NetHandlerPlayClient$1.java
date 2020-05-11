package net.minecraft.client.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.multiplayer.ServerData$ServerResourceMode;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.NetHandlerPlayClient;

class NetHandlerPlayClient$1 implements GuiYesNoCallback {

   // $FF: synthetic field
   final String field_146300_a;
   // $FF: synthetic field
   final NetHandlerPlayClient field_146299_f;


   NetHandlerPlayClient$1(NetHandlerPlayClient var1, String var2) {
      this.field_146299_f = var1;
      this.field_146300_a = var2;
   }

   public void confirmClicked(boolean var1, int var2) {
      NetHandlerPlayClient.access$002(this.field_146299_f, Minecraft.getMinecraft());
      if(NetHandlerPlayClient.access$000(this.field_146299_f).func_147104_D() != null) {
         NetHandlerPlayClient.access$000(this.field_146299_f).func_147104_D().func_152584_a(ServerData$ServerResourceMode.ENABLED);
         ServerList.func_147414_b(NetHandlerPlayClient.access$000(this.field_146299_f).func_147104_D());
      }

      if(var1) {
         NetHandlerPlayClient.access$000(this.field_146299_f).getResourcePackRepository().func_148526_a(this.field_146300_a);
      }

      NetHandlerPlayClient.access$000(this.field_146299_f).displayGuiScreen((GuiScreen)null);
   }
}
