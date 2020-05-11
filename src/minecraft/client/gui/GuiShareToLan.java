package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSettings$GameType;

public class GuiShareToLan extends GuiScreen {

   private final GuiScreen field_146598_a;
   private GuiButton field_146596_f;
   private GuiButton field_146597_g;
   private String field_146599_h = "survival";
   private boolean field_146600_i;


   public GuiShareToLan(GuiScreen var1) {
      this.field_146598_a = var1;
   }

   public void initGui() {
      super.buttonList.clear();
      super.buttonList.add(new GuiButton(101, super.width / 2 - 155, super.height - 28, 150, 20, I18n.format("lanServer.start", new Object[0])));
      super.buttonList.add(new GuiButton(102, super.width / 2 + 5, super.height - 28, 150, 20, I18n.format("gui.cancel", new Object[0])));
      super.buttonList.add(this.field_146597_g = new GuiButton(104, super.width / 2 - 155, 100, 150, 20, I18n.format("selectWorld.gameMode", new Object[0])));
      super.buttonList.add(this.field_146596_f = new GuiButton(103, super.width / 2 + 5, 100, 150, 20, I18n.format("selectWorld.allowCommands", new Object[0])));
      this.func_146595_g();
   }

   private void func_146595_g() {
      this.field_146597_g.displayString = I18n.format("selectWorld.gameMode", new Object[0]) + " " + I18n.format("selectWorld.gameMode." + this.field_146599_h, new Object[0]);
      this.field_146596_f.displayString = I18n.format("selectWorld.allowCommands", new Object[0]) + " ";
      if(this.field_146600_i) {
         this.field_146596_f.displayString = this.field_146596_f.displayString + I18n.format("options.on", new Object[0]);
      } else {
         this.field_146596_f.displayString = this.field_146596_f.displayString + I18n.format("options.off", new Object[0]);
      }

   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.id == 102) {
         super.mc.displayGuiScreen(this.field_146598_a);
      } else if(var1.id == 104) {
         if(this.field_146599_h.equals("survival")) {
            this.field_146599_h = "creative";
         } else if(this.field_146599_h.equals("creative")) {
            this.field_146599_h = "adventure";
         } else {
            this.field_146599_h = "survival";
         }

         this.func_146595_g();
      } else if(var1.id == 103) {
         this.field_146600_i = !this.field_146600_i;
         this.func_146595_g();
      } else if(var1.id == 101) {
         super.mc.displayGuiScreen((GuiScreen)null);
         String var2 = super.mc.getIntegratedServer().shareToLAN(WorldSettings$GameType.getByName(this.field_146599_h), this.field_146600_i);
         Object var3;
         if(var2 != null) {
            var3 = new ChatComponentTranslation("commands.publish.started", new Object[]{var2});
         } else {
            var3 = new ChatComponentText("commands.publish.failed");
         }

         super.mc.ingameGUI.getChatGUI().printChatMessage((IChatComponent)var3);
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(super.fontRendererObj, I18n.format("lanServer.title", new Object[0]), super.width / 2, 50, 16777215);
      this.drawCenteredString(super.fontRendererObj, I18n.format("lanServer.otherPlayers", new Object[0]), super.width / 2, 82, 16777215);
      super.drawScreen(var1, var2, var3);
   }
}
