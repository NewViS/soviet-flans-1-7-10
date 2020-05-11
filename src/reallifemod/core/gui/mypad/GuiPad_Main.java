package de.ItsAMysterious.mods.reallifemod.core.gui.mypad;

import cpw.mods.fml.client.FMLClientHandler;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public class GuiPad_Main extends GuiScreen {

   private static ResourceLocation Background = new ResourceLocation("tlm:textures/gui/GuiSteckbrief.png");
   private GuiButton NameChanging;
   private Entity thePlayer;
   private int GuiLifePage;
   private int middleX;
   private int middleY;


   public void func_73876_c() {
      super.updateScreen();
      this.field_146291_p = true;
      this.thePlayer = FMLClientHandler.instance().getClientPlayerEntity();
      Keyboard.enableRepeatEvents(true);
   }

   public void func_73866_w_() {
      super.initGui();
      this.field_146292_n.clear();
      this.NameChanging = new GuiButton(4, this.field_146294_l / 2 - 256, 60, 75, 20, "Namen aendern");
      if(this.GuiLifePage == 0) {
         this.field_146292_n.add(this.NameChanging);
      }

      this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 58, this.field_146295_m - 55, 20, 20, "<"));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 + 48, this.field_146295_m - 55, 20, 20, ">"));
      this.field_146292_n.add(new GuiButton(3, this.field_146294_l / 2 - 32, this.field_146295_m - 55, 64, 20, "OK"));
   }

   public boolean func_73868_f() {
      return false;
   }

   public void func_73863_a(int p1, int p2, float p3) {
      this.field_146297_k.getTextureManager().bindTexture(Background);
      this.func_73729_b(this.field_146294_l / 2 - 64, this.field_146295_m / 2 - 120, 0, 0, 256, 256);
      if(this.GuiLifePage == 0) {
         this.NameChanging.visible = true;
         this.func_73732_a(this.field_146289_q, Minecraft.getMinecraft().thePlayer.getDisplayName() + "\'s Health Data", this.field_146294_l / 2 + 20, 30, Color.white.getRGB());
      } else if(this.GuiLifePage == 1) {
         this.func_73732_a(this.field_146289_q, "World Data", this.field_146294_l / 2, 35, Color.WHITE.getRGB());
         this.drawProperty("Time", String.valueOf(this.field_146297_k.theWorld.func_72820_D()), this.field_146294_l / 2 - 64, 60);
         this.drawProperty("Position", this.field_146297_k.thePlayer.func_70666_h(0.0F).toString(), this.field_146294_l / 2 - 64, 70);
         this.NameChanging.visible = false;
      }

      super.drawScreen(p1, p2, p3);
   }

   protected void func_146284_a(GuiButton button) {
      if(button.id == 1 && this.GuiLifePage < 1) {
         ++this.GuiLifePage;
      }

      if(button.id == 0 && this.GuiLifePage > 0) {
         --this.GuiLifePage;
      }

      if(button.id == 3) {
         this.field_146297_k.displayGuiScreen((GuiScreen)null);
         this.field_146297_k.setIngameFocus();
      }

      if(button.id == 4) {
         ;
      }

      super.actionPerformed(button);
   }

   public void drawProperty(String Property, String Value, int posX, int posY) {
      this.func_73731_b(this.field_146289_q, Property + ":", posX, posY, Color.LIGHT_GRAY.getRGB());
      if(Value.length() > 30) {
         this.func_73731_b(this.field_146289_q, Value.substring(0, 30).toString(), posX + Property.length() * 6 + 36, posY, Color.LIGHT_GRAY.getRGB());
         this.func_73731_b(this.field_146289_q, Value.substring(30, Value.length()).toString(), posX + Property.length() * 6 + 3, posY + 10, Color.LIGHT_GRAY.getRGB());
      } else {
         this.func_73731_b(this.field_146289_q, Value, posX + Property.length() * 6 + 3, posY, Color.LIGHT_GRAY.getRGB());
      }

   }

}
