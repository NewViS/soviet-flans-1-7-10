package net.minecraft.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

public class EnchantmentProtection extends Enchantment {

   private static final String[] protectionName = new String[]{"all", "fire", "fall", "explosion", "projectile"};
   private static final int[] baseEnchantability = new int[]{1, 10, 5, 5, 3};
   private static final int[] levelEnchantability = new int[]{11, 8, 6, 8, 6};
   private static final int[] thresholdEnchantability = new int[]{20, 12, 10, 12, 15};
   public final int protectionType;


   public EnchantmentProtection(int var1, int var2, int var3) {
      super(var1, var2, EnumEnchantmentType.armor);
      this.protectionType = var3;
      if(var3 == 2) {
         super.type = EnumEnchantmentType.armor_feet;
      }

   }

   public int getMinEnchantability(int var1) {
      return baseEnchantability[this.protectionType] + (var1 - 1) * levelEnchantability[this.protectionType];
   }

   public int getMaxEnchantability(int var1) {
      return this.getMinEnchantability(var1) + thresholdEnchantability[this.protectionType];
   }

   public int getMaxLevel() {
      return 4;
   }

   public int calcModifierDamage(int var1, DamageSource var2) {
      if(var2.canHarmInCreative()) {
         return 0;
      } else {
         float var3 = (float)(6 + var1 * var1) / 3.0F;
         return this.protectionType == 0?MathHelper.floor_float(var3 * 0.75F):(this.protectionType == 1 && var2.isFireDamage()?MathHelper.floor_float(var3 * 1.25F):(this.protectionType == 2 && var2 == DamageSource.fall?MathHelper.floor_float(var3 * 2.5F):(this.protectionType == 3 && var2.isExplosion()?MathHelper.floor_float(var3 * 1.5F):(this.protectionType == 4 && var2.isProjectile()?MathHelper.floor_float(var3 * 1.5F):0))));
      }
   }

   public String getName() {
      return "enchantment.protect." + protectionName[this.protectionType];
   }

   public boolean canApplyTogether(Enchantment var1) {
      if(var1 instanceof EnchantmentProtection) {
         EnchantmentProtection var2 = (EnchantmentProtection)var1;
         return var2.protectionType == this.protectionType?false:this.protectionType == 2 || var2.protectionType == 2;
      } else {
         return super.canApplyTogether(var1);
      }
   }

   public static int getFireTimeForEntity(Entity var0, int var1) {
      int var2 = EnchantmentHelper.getMaxEnchantmentLevel(Enchantment.fireProtection.effectId, var0.getLastActiveItems());
      if(var2 > 0) {
         var1 -= MathHelper.floor_float((float)var1 * (float)var2 * 0.15F);
      }

      return var1;
   }

   public static double func_92092_a(Entity var0, double var1) {
      int var3 = EnchantmentHelper.getMaxEnchantmentLevel(Enchantment.blastProtection.effectId, var0.getLastActiveItems());
      if(var3 > 0) {
         var1 -= (double)MathHelper.floor_double(var1 * (double)((float)var3 * 0.15F));
      }

      return var1;
   }

}
