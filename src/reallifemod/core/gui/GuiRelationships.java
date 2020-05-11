package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiCharacterSetup;
import java.awt.Color;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class GuiRelationships extends GuiScreen {

   private EntityPlayer player;
   private GuiCharacterSetup prevScreen;


   public GuiRelationships(EntityPlayer thePlayer, GuiCharacterSetup guiCharacterSetup) {
      this.player = thePlayer;
      this.prevScreen = guiCharacterSetup;
   }

   public void func_73866_w_() {
      super.initGui();
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 50, this.field_146295_m - 50, 100, 25, "OK"));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l - 25, this.field_146295_m - 50, 25, 25, "x"));
   }

   public void func_73876_c() {
      super.updateScreen();
   }

   public void func_73863_a(int i1, int i2, float f1) {
      this.func_146278_c(0);
      if(RealLifeProperties.get(this.player) != null) {
         String line;
         if(RealLifeProperties.get(this.player).partner == null) {
            if(RealLifeProperties.get(this.player).requester != null) {
               line = "Last Mariage request:" + RealLifeProperties.get(RealLifeProperties.get(this.player).requester).getFullName();
            } else {
               line = "Last Mariage request:" + EnumChatFormatting.RED + "NONE";
            }

            this.func_73731_b(this.field_146289_q, line, 25, 50, Color.white.getRGB());
         } else {
            line = "Partner: " + EnumChatFormatting.GOLD + RealLifeProperties.get(this.player).partner;
            this.func_73731_b(this.field_146289_q, line, 25, 50, Color.white.getRGB());
         }
      }

      super.drawScreen(i1, i2, f1);
   }

   public void func_146284_a(GuiButton b) {
      super.updateScreen();
      if(b.id == 2 && RealLifeProperties.get(this.player).requester != null) {
         RealLifeProperties.get(this.player).partner = RealLifeProperties.get(this.player).requester.getCommandSenderName();
      }

      if(b.id == 1) {
         this.prevScreen.field_146297_k.displayGuiScreen(this.prevScreen);
      }

      if(b.id == 0) {
         this.prevScreen.field_146297_k.displayGuiScreen(this.prevScreen);
      }

   }
}
