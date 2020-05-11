package de.ItsAMysterious.mods.reallifemod.core.entitys.seasonalstuff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class entitySanta extends EntityLivingBase {

   public entitySanta(World p_i1594_1_) {
      super(p_i1594_1_);
   }

   public entitySanta(World world, double x, double y, double z) {
      this(world);
      this.func_70107_b(x, y, z);
   }

   public ItemStack func_70694_bm() {
      return null;
   }

   public ItemStack func_71124_b(int p_71124_1_) {
      return null;
   }

   public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {}

   public ItemStack[] func_70035_c() {
      return null;
   }
}
