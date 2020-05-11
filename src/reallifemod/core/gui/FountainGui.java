package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.client.config.GuiSlider;
import de.ItsAMysterious.mods.reallifemod.api.gui.GuiRadioGroup;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiFramebar;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FountainTE;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Mouse;

public class FountainGui extends GuiScreen {

   public static int GUI_ID = 57;
   private GuiSlider FountainWidth;
   private GuiSlider FountainHeight;
   private GuiSlider RotationSpeed;
   private GuiSlider RotationAtZero;
   private GuiSlider RayCount;
   private GuiRadioGroup shouldRotate;
   private FountainTE tile;
   private GuiFramebar rotationFrames;
   private GuiTextField Framenumber;
   private int mouseX;
   private int mouseY;


   public FountainGui(FountainTE theTile) {
      this.tile = theTile;
   }

   public void func_73866_w_() {
      super.initGui();
      this.Framenumber = new GuiTextField(this.field_146289_q, this.field_146294_l - 125, 5, 100, 20);
      this.Framenumber.setCanLoseFocus(true);
      this.FountainWidth = new GuiSlider(0, this.field_146294_l - 105, this.field_146295_m - 125, 100, 20, "Width: ", "", 0.0D, 15.0D, 0.0D, true, true);
      this.FountainHeight = new GuiSlider(1, this.field_146294_l - 105, this.field_146295_m - 100, 100, 20, "Height: ", "", 0.0D, 15.0D, 0.0D, true, true);
      this.RotationSpeed = new GuiSlider(2, this.field_146294_l - 105, this.field_146295_m - 75, 100, 20, "Rotationspeed: ", "", 0.0D, 10.0D, this.tile.RotationSpeed, true, true);
      this.RotationAtZero = new GuiSlider(3, this.field_146294_l - 105, this.field_146295_m - 50, 100, 20, "Rotation: ", "", -180.0D, 180.0D, 0.0D, true, true);
      this.RayCount = new GuiSlider(4, this.field_146294_l - 105, this.field_146295_m - 25, 100, 20, "RayCount: ", "", 1.0D, 64.0D, 1.0D, false, true);
      this.field_146292_n.add(this.FountainHeight);
      this.field_146292_n.add(this.FountainWidth);
      this.field_146292_n.add(this.RotationSpeed);
      this.field_146292_n.add(this.RotationAtZero);
      this.field_146292_n.add(this.RayCount);
      this.field_146292_n.add(new GuiButton(5, this.Framenumber.xPosition + 100 - 73, this.Framenumber.yPosition + 21, 75, 20, "Set Length"));
   }

   public void func_73863_a(int x, int y, float f1) {
      super.drawScreen(x, y, f1);
      if(this.Framenumber != null) {
         this.Framenumber.drawTextBox();
      }

      if(this.rotationFrames != null) {
         this.rotationFrames.drawBar(x, y, f1);
      }

   }

   public void func_73876_c() {
      this.Framenumber.updateCursorCounter();
      if(this.tile != null) {
         if(this.rotationFrames == null) {
            this.rotationFrames = new GuiFramebar(this.tile, 0, this.RotationAtZero.field_146129_i, this.field_146294_l - 125, 20);
         } else {
            this.rotationFrames.onUpdate();
         }

         this.tile.FountainWidth = this.FountainWidth.sliderValue;
         this.tile.FountainHeight = this.FountainHeight.sliderValue;
         this.tile.RotationSpeed = this.RotationSpeed.getValue();
         this.tile.rotation = this.RotationAtZero.sliderValue;
         this.tile.rayCount = (int)(this.RayCount.getValue() + 0.5D);
         this.tile.func_145831_w().markBlockForUpdate(this.tile.field_145851_c, this.tile.field_145848_d, this.tile.field_145849_e);
      }

   }

   protected void func_73864_a(int x, int y, int id) {
      this.rotationFrames.onMouseclicked(x, y, id);
      this.Framenumber.mouseClicked(x, y, id);
      super.mouseClicked(x, y, id);
   }

   protected void func_73869_a(char letter, int id) {
      super.keyTyped(letter, id);
      String validletters = "0123456789";
      int[] validids = new int[]{28, 14, 203, 205};
      boolean isvalid = false;

      for(int i = 0; i < validids.length; ++i) {
         if(i == validids[i]) {
            isvalid = true;
            break;
         }
      }

      if(validletters.contains(String.valueOf(letter)) || id == 28) {
         if(id == 28) {
            this.tile.setKeyframelength(Integer.parseInt(this.Framenumber.getText()));
            this.tile.func_145831_w().markBlockForUpdate(this.tile.field_145851_c, this.tile.field_145848_d, this.tile.field_145849_e);
         }

         if(isvalid) {
            ;
         }

         this.Framenumber.textboxKeyTyped(letter, id);
      }

   }

   public void func_146284_a(GuiButton button) {
      if(button.id == 5 && this.Framenumber.getText() != "") {
         this.tile.setKeyframelength(Integer.parseInt(this.Framenumber.getText()));
         this.tile.func_145831_w().markBlockForUpdate(this.tile.field_145851_c, this.tile.field_145848_d, this.tile.field_145849_e);
      }

   }

   public boolean func_73868_f() {
      return false;
   }

   public void func_146274_d() {
      super.handleMouseInput();
      Mouse.poll();
      this.mouseX = -(Mouse.getEventX() * this.field_146294_l / this.field_146297_k.displayWidth);
      this.mouseY = -(this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.displayHeight - 1);
   }

}
