package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiEspresso extends GuiScreen {

   public String Displaymessage = "";
   public EntityPlayer player;


   public void func_73866_w_() {
      super.initGui();
      this.player = this.field_146297_k.thePlayer;
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 + 40, this.field_146295_m / 2 - 45, 60, 20, "Coffee"));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 + 40, this.field_146295_m / 2 - 26, 60, 20, "Espresso"));
      this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 2 + 40, this.field_146295_m / 2 - 8, 60, 20, "Cappucino"));
      this.field_146292_n.add(new GuiButton(3, this.field_146294_l / 2 + 40, this.field_146295_m / 2 + 10, 60, 20, "Hot Choc"));
      this.field_146292_n.add(new GuiButton(4, this.field_146294_l / 2 + 40, this.field_146295_m / 2 + 28, 60, 20, "Latte"));
      this.field_146292_n.add(new GuiButton(5, this.field_146294_l / 2 + 40, this.field_146295_m / 2 + 46, 60, 20, "Tea"));
      this.field_146292_n.add(new GuiButton(6, this.field_146294_l / 2 - 95, this.field_146295_m / 2 + 50, 60, 20, EnumChatFormatting.GREEN + "OK"));
      this.field_146292_n.add(new GuiButton(7, this.field_146294_l - 20, 0, 20, 20, "X"));
   }

   public void func_73863_a(int x, int y, float f) {
      Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/gui/guiEspresso.png"));
      this.func_73729_b(this.field_146294_l / 2 - 100, this.field_146295_m / 2 - 75, 0, 0, 200, 150);
      this.func_73732_a(this.field_146289_q, EnumChatFormatting.ITALIC + this.Displaymessage, this.field_146294_l / 2 + 68, this.field_146295_m / 2 - 63, Color.white.getRGB());
      super.drawScreen(x, y, f);
   }

   public boolean func_73868_f() {
      return false;
   }

   public void func_146284_a(GuiButton button) {
      if(button.id < 6) {
         this.Displaymessage = button.displayString;
      }

      if(button.id == 6) {
         financialProps var10000 = (financialProps)this.player.getExtendedProperties("financialProps");
         if(financialProps.Cash - 1.5D > 0.0D) {
            if(this.Displaymessage == "Cappuccino") {
               this.field_146297_k.thePlayer.field_71071_by.addItemStackToInventory(new ItemStack(RealLifeMod_Items.cupCappuccino));
            }

            if(this.Displaymessage == "Coffee") {
               this.field_146297_k.thePlayer.field_71071_by.addItemStackToInventory(new ItemStack(RealLifeMod_Items.cupCoffee));
            }

            if(this.Displaymessage == "Hot Choc") {
               this.field_146297_k.thePlayer.field_71071_by.addItemStackToInventory(new ItemStack(RealLifeMod_Items.cupHotChocolate));
            }

            if(this.Displaymessage == "Espresso") {
               this.field_146297_k.thePlayer.field_71071_by.addItemStackToInventory(new ItemStack(RealLifeMod_Items.cupEspresso));
            }

            if(this.Displaymessage == "Latte") {
               this.field_146297_k.thePlayer.field_71071_by.addItemStackToInventory(new ItemStack(RealLifeMod_Items.cupLatte));
            }

            if(this.Displaymessage == "Tea") {
               this.field_146297_k.thePlayer.field_71071_by.addItemStackToInventory(new ItemStack(RealLifeMod_Items.cupTea));
            } else {
               this.Displaymessage = EnumChatFormatting.RED + "Not enough Money!";
            }
         }

         this.func_73878_a(true, 6);
      } else if(button.id == 7) {
         this.func_73878_a(true, 7);
         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
      }

   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) {
      super.keyTyped(p_73869_1_, p_73869_2_);
   }
}
