package de.ItsAMysterious.mods.reallifemod.core.items;

import de.ItsAMysterious.mods.reallifemod.api.interfaces.Buyable;
import net.minecraft.item.Item;

public class ExtendedItem extends Item implements Buyable {

   private double price;


   public void setPrice(double thePrice) {
      this.price = thePrice;
   }

   public double price() {
      return this.price;
   }

   public int percentage() {
      return 0;
   }
}
