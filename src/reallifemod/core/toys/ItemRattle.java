package de.ItsAMysterious.mods.reallifemod.core.toys;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRattle extends Item {

   public ItemRattle() {
      this.func_111206_d("reallifemod:rattle");
      this.func_77655_b("BabyRattle");
   }

   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
      if(world.isRemote) {
         player.playSound("reallifemod:rattle", 1.0F, 1.0F);
      }

      return stack;
   }
}
