package de.ItsAMysterious.mods.reallifemod.core.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiCharacterSetup;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiModinfo;
import de.ItsAMysterious.mods.reallifemod.core.gui.guiPhone;
import net.minecraft.client.Minecraft;

@SideOnly(Side.CLIENT)
public class TLMKeysHandler {

   @SubscribeEvent
   public void onKeyInput(KeyInputEvent event) {
      if(ClientProxy.character.isPressed()) {
         Minecraft.getMinecraft().displayGuiScreen(new GuiCharacterSetup());
      }

      if(ClientProxy.Key_Phone.isPressed()) {
         Minecraft.getMinecraft().displayGuiScreen(new guiPhone());
      }

      if(ClientProxy.Key_Info.isPressed()) {
         Minecraft.getMinecraft().displayGuiScreen(new GuiModinfo());
      }

   }
}
