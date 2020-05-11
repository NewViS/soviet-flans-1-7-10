package de.ItsAMysterious.mods.reallifemod.core.items.meds;

import de.ItsAMysterious.mods.reallifemod.core.items.food.ExtendedItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class itemMed extends ExtendedItemFood {

   public itemMed(int p_i45340_1_, boolean p_i45340_2_) {
      super(p_i45340_1_, p_i45340_2_);
   }

   public itemMed(int p_i45340_1_, float saturation, boolean p_i45340_2_) {
      super(p_i45340_1_, saturation, p_i45340_2_);
   }

   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
      if(!world.isRemote) {
         if(player.func_110143_aJ() > 19.0F) {
            player.func_70690_d(new PotionEffect(Potion.confusion.getId(), 100));
            player.func_70606_j(player.func_110143_aJ() - 1.0F);
         } else {
            player.func_70691_i(6.0F);
         }

         --stack.stackSize;
      }

      return stack;
   }

   protected void func_77849_c(ItemStack stack, World world, EntityPlayer player) {}
}
