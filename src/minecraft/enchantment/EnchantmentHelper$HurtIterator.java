package net.minecraft.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper$1;
import net.minecraft.enchantment.EnchantmentHelper$IModifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

final class EnchantmentHelper$HurtIterator implements EnchantmentHelper$IModifier {

   public EntityLivingBase field_151364_a;
   public Entity field_151363_b;


   private EnchantmentHelper$HurtIterator() {}

   public void calculateModifier(Enchantment var1, int var2) {
      var1.func_151367_b(this.field_151364_a, this.field_151363_b, var2);
   }

   // $FF: synthetic method
   EnchantmentHelper$HurtIterator(EnchantmentHelper$1 var1) {
      this();
   }
}
