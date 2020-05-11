package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.core.handlers.SpeechHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class guiPhone extends GuiScreen {

   public GuiTextField numberField;
   int color = 1;


   public void func_73866_w_() {
      super.initGui();
      int buttonPosX = 0;
      int row = 0;

      for(int i = 0; i < 12; ++i) {
         if(buttonPosX == 80) {
            ++row;
            buttonPosX = 0;
         }

         if(buttonPosX == 80) {
            ++row;
            buttonPosX = 0;
         }

         if(i < 9) {
            this.field_146292_n.add(new GuiButton(i, this.field_146294_l / 2 - 38 + buttonPosX, this.field_146295_m / 2 - 10 + row * 20, 20, 20, String.valueOf(i + 1)));
         } else {
            if(i == 9) {
               this.field_146292_n.add(new GuiButton(i, this.field_146294_l / 2 - 38 + buttonPosX, this.field_146295_m / 2 - 10 + row * 20, 20, 20, "0"));
            }

            if(i == 10) {
               this.field_146292_n.add(new GuiButton(i, this.field_146294_l / 2 - 38 + buttonPosX, this.field_146295_m / 2 - 10 + row * 20, 20, 20, "<-"));
            }

            if(i == 11) {
               this.field_146292_n.add(new GuiButton(i, this.field_146294_l / 2 - 38 + buttonPosX, this.field_146295_m / 2 - 10 + row * 20, 20, 20, EnumChatFormatting.GREEN + "ok"));
            }
         }

         buttonPosX += 20;
      }

      this.numberField = new GuiTextField(this.field_146289_q, this.field_146294_l / 2 - 35, this.field_146295_m / 2 - 52, 75, 15);
      this.numberField.setCanLoseFocus(true);
      this.numberField.setFocused(true);
   }

   public void func_73863_a(int x, int y, float f) {
      this.color += 2;
      if(this.color == 255) {
         this.color = 0;
      }

      this.field_146297_k.renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/gui/phone.png"));
      this.func_73729_b(this.field_146294_l / 2 - 46, this.field_146295_m / 2 - 80, 0, 0, 95, 1000);
      this.numberField.drawTextBox();
      super.drawScreen(x, y, f);
      FontRenderer var10001 = this.field_146289_q;
      RealLifeProperties var10002 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProps");
      RealLifeProperties.phone var4 = RealLifeProperties.ThePhone;
      this.func_73731_b(var10001, RealLifeProperties.phone.partner.name(), this.field_146294_l / 2 - 38, this.field_146295_m / 2 - 30, this.color);
   }

   public void func_146284_a(GuiButton button) {
      switch(button.id) {
      case 10:
         if(this.numberField.getText().length() - 1 >= 0) {
            this.numberField.setText(this.numberField.getText().substring(0, this.numberField.getText().length() - 1));
         }
         break;
      case 11:
         RealLifeProperties var10000;
         RealLifeProperties.phone var2;
         if(!this.numberField.getText().equals("411") && !this.numberField.getText().equals("911") && !this.numberField.getText().equals("110")) {
            if(this.numberField.getText().equals("000")) {
               var10000 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProps");
               var2 = RealLifeProperties.ThePhone;
               RealLifeProperties.phone.partner = RealLifeProperties.phone.phonepartners.TUTORIALHOTLINE;
            } else {
               SpeechHandler.speechSynth(0.5D, 10.0F, -75.0F, 2.5F, "The number you have dialed is not available!");
            }
         } else {
            var10000 = (RealLifeProperties)this.field_146297_k.thePlayer.getExtendedProperties("RealLifeProps");
            var2 = RealLifeProperties.ThePhone;
            RealLifeProperties.phone.partner = RealLifeProperties.phone.phonepartners.POLICE;
         }
      }

      if("1234567890".contains(button.displayString)) {
         this.numberField.setText(this.numberField.getText() + button.displayString);
      }

   }

   protected void func_73869_a(char theChar, int id) {
      super.keyTyped(theChar, id);
      if(id == 28) {
         this.func_146284_a((GuiButton)this.field_146292_n.get(11));
      }

      if("1234567890".contains(String.valueOf(theChar)) || id == 14 || id == 203 || id == 205) {
         this.numberField.textboxKeyTyped(theChar, id);
      }

   }

   protected void func_146286_b(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
      this.numberField.mouseClicked(p_146286_1_, p_146286_2_, p_146286_3_);
   }
}
