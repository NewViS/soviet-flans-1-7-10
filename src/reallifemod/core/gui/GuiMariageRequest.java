package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.awt.Color;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiMariageRequest extends GuiScreen {

   private static final ResourceLocation TEXTURE = new ResourceLocation("reallifemod:textures/gui/parchement");
   public static final int GUI_ID = 60;
   private String requester;
   private String requested;


   public GuiMariageRequest(String requester, String requested) {
      this.requester = requester;
      this.requested = requested;
   }

   public void func_73863_a(int i1, int i2, float f1) {
      super.drawScreen(i1, i2, f1);
      this.func_73731_b(this.field_146289_q, this.requester + " want\'s to become your husband!" + EnumChatFormatting.GREEN + " ACCEPT?", this.field_146294_l / 2, this.field_146295_m / 2, Color.white.getRGB());
   }

   public void func_146284_a(GuiButton b) {
      if(b.id == 0) {
         ;
      }

   }

}
