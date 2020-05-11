package net.minecraft.entity.passive;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

class EntitySheep$1 extends Container {

   // $FF: synthetic field
   final EntitySheep field_90034_a;


   EntitySheep$1(EntitySheep var1) {
      this.field_90034_a = var1;
   }

   public boolean canInteractWith(EntityPlayer var1) {
      return false;
   }
}
