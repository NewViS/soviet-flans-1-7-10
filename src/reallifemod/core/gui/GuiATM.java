package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GuiATM extends RLM_Gui {

   public int buttonCountY = 0;
   public int rowX = 0;
   private int timesJumpedToTop = 0;


   public void func_73876_c() {
      this.func_73866_w_();
   }

   public void func_146284_a(GuiButton button) {
      this.field_146297_k.thePlayer.field_71071_by.addItemStackToInventory(new ItemStack((Item)RealLifeMod_Items.itemList.get(button.id)));
   }

   public void func_73866_w_() {
      this.buttonCountY = this.field_146297_k.displayHeight / 20;

      for(int i = 0; i < RealLifeMod_Items.itemList.size(); ++i) {
         Item item = (Item)RealLifeMod_Items.itemList.get(i);
         if(i * 20 + 20 > this.buttonCountY * 20) {
            ++this.rowX;
            ++this.timesJumpedToTop;
         }

         this.field_146292_n.add(new GuiButton(i, this.rowX * 100, i * 20 - this.buttonCountY * 20 * this.timesJumpedToTop, 100, 20, "buy " + item.getUnlocalizedName().split("item.")[1]));
      }

   }
}
