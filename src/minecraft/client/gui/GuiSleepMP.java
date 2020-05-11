package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class GuiSleepMP extends GuiChat {

   public void initGui() {
      super.initGui();
      super.buttonList.add(new GuiButton(1, super.width / 2 - 100, super.height - 40, I18n.format("multiplayer.stopSleeping", new Object[0])));
   }

   protected void keyTyped(char var1, int var2) {
      if(var2 == 1) {
         this.func_146418_g();
      } else if(var2 != 28 && var2 != 156) {
         super.keyTyped(var1, var2);
      } else {
         String var3 = super.inputField.getText().trim();
         if(!var3.isEmpty()) {
            super.mc.thePlayer.sendChatMessage(var3);
         }

         super.inputField.setText("");
         super.mc.ingameGUI.getChatGUI().resetScroll();
      }

   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.id == 1) {
         this.func_146418_g();
      } else {
         super.actionPerformed(var1);
      }

   }

   private void func_146418_g() {
      NetHandlerPlayClient var1 = super.mc.thePlayer.sendQueue;
      var1.addToSendQueue(new C0BPacketEntityAction(super.mc.thePlayer, 3));
   }
}
