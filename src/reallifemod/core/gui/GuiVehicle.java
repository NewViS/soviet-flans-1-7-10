package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.EntityVehicle;
import java.awt.Color;
import org.lwjgl.input.Mouse;

public class GuiVehicle extends RLM_Gui {

   public EntityVehicle vehicle;


   public GuiVehicle(EntityVehicle entityVehicle) {
      this.vehicle = entityVehicle;
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      Mouse.setCursorPosition(this.field_146294_l / 2, this.field_146295_m / 2);
   }

   public boolean func_73868_f() {
      return false;
   }

   public void func_73876_c() {
      super.func_73876_c();
   }

   public void func_73863_a(int i1, int i2, float f1) {
      super.func_73863_a(i1, i2, f1);
      this.func_73731_b(this.field_146289_q, "Speed: " + this.vehicle.currentspeed / 20.0D + "km/h", 0, 100, Color.white.getRGB());
   }

   protected void func_73869_a(char p_73869_1_, int id) {
      if(id == 1) {
         this.field_146297_k.gameSettings.pauseOnLostFocus = true;
      }

   }
}
