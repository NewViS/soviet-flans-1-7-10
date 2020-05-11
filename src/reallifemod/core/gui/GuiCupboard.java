package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.core.tiles.CupboardTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.SideboardTE;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCupboard extends GuiScreen {

   static int GUI_ID;
   private CupboardTE tile;


   public GuiCupboard(CupboardTE c) {
      this.tile = c;
   }

   public GuiCupboard(InventoryPlayer inventory, SideboardTE tileEntity) {}

   public void func_73866_w_() {
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l - 150, this.field_146295_m - 40, "close"));
   }

   public void func_73869_a(char taste, int id) {
      if(id == 1) {
         this.tile.open = false;
      }

      super.keyTyped(taste, id);
   }
}
