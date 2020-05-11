package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModMetadata;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

public class GuiModinfo extends GuiScreen {

   private float version;
   private String text;
   private String[][] links;
   private String YouTube = "https://www.youtube.com/channel/UCyKlPlyI9lZi3vATCv8zKgA?subconfirmation=1";
   private String Tutorials = "https://www.youtube.com/playlist?list=PL9f4QGcDBVZpHPpP6N3ATAxebN2tyuTZd";


   public void func_73866_w_() {
      super.initGui();
      int i = this.field_146294_l / 4;
      this.field_146292_n.add(new GuiButton(0, 5, this.field_146295_m / 2, i, 20, "Read the" + EnumChatFormatting.GREEN + " Forum Page"));
      this.field_146292_n.add(new GuiButton(1, 5, this.field_146295_m / 2 + 20, i, 20, "Visit our" + EnumChatFormatting.RED + " YouTube"));
      this.field_146292_n.add(new GuiButton(2, 5, this.field_146295_m / 2 + 40, i, 20, "Follow us on" + EnumChatFormatting.BLUE + " Twitter"));
      this.field_146292_n.add(new GuiButton(3, 5, this.field_146295_m / 2 + 60, i, 20, "Tutorials"));
      this.field_146292_n.add(new GuiButton(10, this.field_146294_l - 90, this.field_146295_m - 30, 75, 20, " OK "));
   }

   public void func_73876_c() {
      super.updateScreen();
   }

   public void func_73863_a(int x, int y, float f1) {
      ModMetadata info = FMLCommonHandler.instance().findContainerFor(RealLifeMod.instance).getMetadata();
      int i = this.field_146294_l / 4;
      this.func_146278_c(0);
      this.func_73732_a(this.field_146289_q, EnumChatFormatting.GOLD + "Real Life Mod - Info", this.field_146294_l / 2, 35, Color.white.getRGB());
      this.func_73731_b(this.field_146289_q, EnumChatFormatting.UNDERLINE + "Current Version: ", 5, 50, Color.white.getRGB());
      this.func_73731_b(this.field_146289_q, info.version, 20 + this.field_146289_q.getStringWidth("Current Version:_"), 50, Color.white.getRGB());
      this.func_73731_b(this.field_146289_q, EnumChatFormatting.UNDERLINE + "Next Update: ", 5, 64, Color.white.getRGB());
      this.func_73731_b(this.field_146289_q, "Unknown(Depends on how we get on updating).", 5, 76, Color.white.getRGB());
      this.func_73731_b(this.field_146289_q, "Make sure you check out our YouTube to keep up to date.", 5, 85, Color.white.getRGB());
      this.func_73731_b(this.field_146289_q, EnumChatFormatting.UNDERLINE + "Info: ", 5, 97, Color.white.getRGB());
      this.func_73731_b(this.field_146289_q, "This mod is currently being updated to 1.8! ", 5, 108, Color.white.getRGB());
      this.func_73731_b(this.field_146289_q, "All news can be found on the" + EnumChatFormatting.DARK_GREEN + " Forum Page, " + EnumChatFormatting.RED + "YouTube" + EnumChatFormatting.RESET + " and " + EnumChatFormatting.BLUE + "Twitter!", 5, 117, Color.white.getRGB());
      super.drawScreen(x, y, f1);
   }

   public void func_146284_a(GuiButton button) {
      URI uri = null;
      URI yt = null;
      URI tut = null;
      URI twitter = null;

      try {
         uri = new URI("http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2196915-real-life-mod-taken-from-reality-directly-into");
         yt = new URI(this.YouTube);
         tut = new URI(this.Tutorials);
         twitter = new URI("http://www.twitter.com/EmeraldMinors");
      } catch (URISyntaxException var11) {
         var11.printStackTrace();
      }

      if(button.id == 0) {
         try {
            Desktop.getDesktop().browse(uri);
         } catch (IOException var10) {
            var10.printStackTrace();
         }
      }

      if(button.id == 1) {
         try {
            Desktop.getDesktop().browse(yt);
         } catch (IOException var9) {
            var9.printStackTrace();
         }
      }

      if(button.id == 2) {
         try {
            Desktop.getDesktop().browse(twitter);
         } catch (IOException var8) {
            var8.printStackTrace();
         }
      }

      if(button.id == 3) {
         try {
            Desktop.getDesktop().browse(tut);
         } catch (IOException var7) {
            var7.printStackTrace();
         }
      }

      this.func_73878_a(true, button.id);
   }
}
