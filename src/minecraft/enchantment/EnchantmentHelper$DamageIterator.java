package net.minecraft.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper$1;
import net.minecraft.enchantment.EnchantmentHelper$IModifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

final class EnchantmentHelper$DamageIterator implements EnchantmentHelper$IModifier {

   public EntityLivingBase field_151366_a;
   public Entity field_151365_b;


   private EnchantmentHelper$DamageIterator() {}

   public void calculateModifier(Enchantment var1, int var2) {
      var1.func_151368_a(this.field_151366_a, this.field_151365_b, var2);
   }

   // $FF: synthetic method
   EnchantmentHelper$DamageIterator(EnchantmentHelper$1 var1) {
      this();
   }
}
