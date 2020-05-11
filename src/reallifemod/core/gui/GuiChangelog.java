package de.ItsAMysterious.mods.reallifemod.core.gui;

import com.google.common.collect.Lists;
import de.ItsAMysterious.mods.reallifemod.init.ConfigGui;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiChangelog extends GuiScreen {

   private GuiScreen parentScreen;
   private GuiTextField changes;
   private static final Logger logger = LogManager.getLogger();
   private List Lines;
   private int position;
   private boolean scroll;


   public GuiChangelog(GuiScreen parent) {
      this.parentScreen = parent;
   }

   public void func_73866_w_() {
      short short1 = 274;
      if(this.Lines == null) {
         this.Lines = Lists.newArrayList();

         try {
            BufferedReader bufferedreader = new BufferedReader(new FileReader(new File(this.field_146297_k.mcDataDir.getCanonicalPath().replace("eclipse", "src/main/resources/changelog.txt"))));

            String e;
            while((e = bufferedreader.readLine()) != null) {
               this.Lines.addAll(this.field_146297_k.fontRenderer.listFormattedStringToWidth(e, short1));
               this.Lines.add("");
            }

            bufferedreader.close();
         } catch (IOException var4) {
            logger.error("Couldn\'t load credits", var4);
         }
      }

      this.field_146292_n.add(new GuiButton(0, this.field_146294_l - 75, this.field_146295_m - 25, 75, 20, "OK"));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l - 55, this.field_146295_m - 45, 50, 20, "Down"));
      this.field_146292_n.add(new GuiButton(2, this.field_146294_l - 55, 45, 50, 20, "^"));
   }

   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
      this.func_146278_c(this.field_146294_l);
      if(this.scroll) {
         --this.position;
      }

      Tessellator tessellator = Tessellator.instance;
      GL11.glEnable(3042);
      GL11.glBlendFunc(0, 769);
      tessellator.startDrawingQuads();
      tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
      int j1 = this.field_146294_l;
      int k1 = this.field_146295_m;
      tessellator.addVertexWithUV(0.0D, (double)k1, (double)this.field_73735_i, 0.0D, 1.0D);
      tessellator.addVertexWithUV(0.0D, (double)k1, (double)this.field_73735_i, 1.0D, 1.0D);
      tessellator.addVertexWithUV(0.0D, 0.0D, (double)this.field_73735_i, 1.0D, 0.0D);
      tessellator.addVertexWithUV(0.0D, 0.0D, (double)this.field_73735_i, 0.0D, 0.0D);
      tessellator.draw();
      GL11.glDisable(3042);
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, (float)this.position, 0.0F);

      for(int i = 0; i < this.Lines.size(); ++i) {
         String s = (String)this.Lines.get(i);
         if(s.startsWith("[C][B]")) {
            this.func_73732_a(this.field_146289_q, EnumChatFormatting.BOLD + s.substring(6), this.field_146294_l / 2, 45 + i * 5, Color.white.getRGB());
         } else if(s.startsWith("[C]")) {
            this.func_73732_a(this.field_146289_q, s.substring(3), this.field_146294_l / 2, 45 + i * 5, Color.white.getRGB());
         } else if(s.startsWith("[B]")) {
            this.func_73731_b(this.field_146289_q, EnumChatFormatting.BOLD + s.substring(3), 125, 45 + i * 5, Color.white.getRGB());
         } else {
            this.func_73731_b(this.field_146289_q, s, 125, 45 + i * 5, Color.white.getRGB());
         }
      }

      GL11.glPopMatrix();
      this.func_73733_a(0, 0, this.field_146294_l, 45, Color.black.getRGB(), Color.white.getTransparency());
      this.func_73732_a(this.field_146289_q, "Changelog", this.field_146294_l / 2, 25, Color.white.getRGB());
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void func_146284_a(GuiButton button) {
      if(button.id == 0) {
         if(this.parentScreen instanceof ConfigGui) {
            ((ConfigGui)this.parentScreen).openchangelog = 0;
            ConfigGui parentConfigGui = (ConfigGui)this.parentScreen;
            parentConfigGui.needsRefresh = true;
            parentConfigGui.func_73866_w_();
            parentConfigGui.field_146297_k.displayGuiScreen(parentConfigGui);
         }

         if(!(this.parentScreen instanceof ConfigGui)) {
            Keyboard.enableRepeatEvents(false);
         }
      }

      if(button.id == 1) {
         this.scroll = !this.scroll;
      }

      if(button.id == 2 && this.position < 0) {
         ++this.position;
      }

   }

}
