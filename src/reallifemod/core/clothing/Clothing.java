package de.ItsAMysterious.mods.reallifemod.core.clothing;

import de.ItsAMysterious.mods.reallifemod.core.clothing.ClothingTypes;

public class Clothing {

   public ClothingTypes type;
   public String name;


   public Clothing(String type, String name) {
      try {
         this.type = ClothingTypes.valueOf(type);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      this.name = name;
   }

   public int getModelCount(ClothingTypes type) {
      return type == ClothingTypes.trousers?2:(type == ClothingTypes.tshirt?3:(type == ClothingTypes.socks?2:(type == ClothingTypes.handshoe?2:(type == ClothingTypes.headwear?1:(type == ClothingTypes.jacket?3:(type == ClothingTypes.scarf?1:(type == ClothingTypes.shoes?2:(type == ClothingTypes.pants?2:(type == ClothingTypes.sweatshirt?3:0)))))))));
   }

   public float getWarmth() {
      return 20.0F;
   }
}
