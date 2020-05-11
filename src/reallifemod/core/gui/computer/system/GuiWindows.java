package de.ItsAMysterious.mods.reallifemod.core.gui.computer.system;

import de.ItsAMysterious.mods.reallifemod.core.gui.computer.system.guiExplorerWindow;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.util.ResourceLocation;

public class GuiWindows extends GuiScreen implements GuiYesNoCallback {

   protected List windowList = new ArrayList();


   public void func_73866_w_() {
      this.field_146292_n.clear();
      this.field_146292_n.add(new GuiButton(0, 0, this.field_146295_m - 20, 40, 20, "Start"));
      super.initGui();
      this.windowList.clear();
   }

   public void func_73863_a(int x, int y, float f) {
      super.drawScreen(x, y, f);
      if(!this.windowList.isEmpty()) {
         for(int k = 0; k < this.windowList.size(); ++k) {
            ((guiExplorerWindow)this.windowList.get(k)).showExplorerWindow(x, y);
            ((guiExplorerWindow)this.windowList.get(k)).func_73866_w_();
         }
      }

      this.drawTaskbar();
   }

   public void func_73876_c() {
      super.updateScreen();
   }

   public void func_73869_a(char taste, int id) {
      super.keyTyped(taste, id);
      if(id == 18 && GuiScreen.isShiftKeyDown()) {
         this.windowList.add(new guiExplorerWindow(0, this.field_146294_l / 2 - 150, this.field_146295_m / 2 - 100, 500, 300, "Minedows Exlporer"));
      }

   }

   public void func_73864_a(int x, int y, int id) {
      super.mouseClicked(x, y, id);
   }

   protected void func_146284_a(GuiButton button) {
      if(button.id == 0) {
         ;
      }

   }

   public void drawTaskbar() {
      this.field_146297_k.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/System/taskBar.png"));
      this.func_73729_b(40, this.field_146295_m - 20, 0, 0, this.field_146294_l - 40, 20);
   }

   public void drawStartMenue(int posX, int posY) {
      this.func_73729_b(posX, posY, 0, 0, 150, 75);
      this.field_146292_n.add(new GuiButton(1, posX + 50, posY + 1, 20, 10, "Shutdown"));
   }
}
