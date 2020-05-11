package net.minecraft.client.gui;

import java.net.URI;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class GuiScreenDemo extends GuiScreen {

   private static final Logger logger = LogManager.getLogger();
   private static final ResourceLocation field_146348_f = new ResourceLocation("textures/gui/demo_background.png");


   public void initGui() {
      super.buttonList.clear();
      byte var1 = -16;
      super.buttonList.add(new GuiButton(1, super.width / 2 - 116, super.height / 2 + 62 + var1, 114, 20, I18n.format("demo.help.buy", new Object[0])));
      super.buttonList.add(new GuiButton(2, super.width / 2 + 2, super.height / 2 + 62 + var1, 114, 20, I18n.format("demo.help.later", new Object[0])));
   }

   protected void actionPerformed(GuiButton var1) {
      switch(var1.id) {
      case 1:
         var1.enabled = false;

         try {
            Class var2 = Class.forName("java.awt.Desktop");
            Object var3 = var2.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
            var2.getMethod("browse", new Class[]{URI.class}).invoke(var3, new Object[]{new URI("http://www.minecraft.net/store?source=demo")});
         } catch (Throwable var4) {
            logger.error("Couldn\'t open link", var4);
         }
         break;
      case 2:
         super.mc.displayGuiScreen((GuiScreen)null);
         super.mc.setIngameFocus();
      }

   }

   public void updateScreen() {
      super.updateScreen();
   }

   public void drawDefaultBackground() {
      super.drawDefaultBackground();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(field_146348_f);
      int var1 = (super.width - 248) / 2;
      int var2 = (super.height - 166) / 2;
      this.drawTexturedModalRect(var1, var2, 0, 0, 248, 166);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      int var4 = (super.width - 248) / 2 + 10;
      int var5 = (super.height - 166) / 2 + 8;
      super.fontRendererObj.drawString(I18n.format("demo.help.title", new Object[0]), var4, var5, 2039583);
      var5 += 12;
      GameSettings var6 = super.mc.gameSettings;
      super.fontRendererObj.drawString(I18n.format("demo.help.movementShort", new Object[]{GameSettings.getKeyDisplayString(var6.keyBindForward.getKeyCode()), GameSettings.getKeyDisplayString(var6.keyBindLeft.getKeyCode()), GameSettings.getKeyDisplayString(var6.keyBindBack.getKeyCode()), GameSettings.getKeyDisplayString(var6.keyBindRight.getKeyCode())}), var4, var5, 5197647);
      super.fontRendererObj.drawString(I18n.format("demo.help.movementMouse", new Object[0]), var4, var5 + 12, 5197647);
      super.fontRendererObj.drawString(I18n.format("demo.help.jump", new Object[]{GameSettings.getKeyDisplayString(var6.keyBindJump.getKeyCode())}), var4, var5 + 24, 5197647);
      super.fontRendererObj.drawString(I18n.format("demo.help.inventory", new Object[]{GameSettings.getKeyDisplayString(var6.keyBindInventory.getKeyCode())}), var4, var5 + 36, 5197647);
      super.fontRendererObj.drawSplitString(I18n.format("demo.help.fullWrapped", new Object[0]), var4, var5 + 68, 218, 2039583);
      super.drawScreen(var1, var2, var3);
   }

}
