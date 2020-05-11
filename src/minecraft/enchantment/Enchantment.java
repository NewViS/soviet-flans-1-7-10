package net.minecraft.enchantment;

import java.util.ArrayList;
import net.minecraft.enchantment.EnchantmentArrowDamage;
import net.minecraft.enchantment.EnchantmentArrowFire;
import net.minecraft.enchantment.EnchantmentArrowInfinite;
import net.minecraft.enchantment.EnchantmentArrowKnockback;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentDigging;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentFireAspect;
import net.minecraft.enchantment.EnchantmentFishingSpeed;
import net.minecraft.enchantment.EnchantmentKnockback;
import net.minecraft.enchantment.EnchantmentLootBonus;
import net.minecraft.enchantment.EnchantmentOxygen;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.enchantment.EnchantmentUntouching;
import net.minecraft.enchantment.EnchantmentWaterWorker;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public abstract class Enchantment {

   public static final Enchantment[] enchantmentsList = new Enchantment[256];
   public static final Enchantment[] enchantmentsBookList;
   public static final Enchantment protection = new EnchantmentProtection(0, 10, 0);
   public static final Enchantment fireProtection = new EnchantmentProtection(1, 5, 1);
   public static final Enchantment featherFalling = new EnchantmentProtection(2, 5, 2);
   public static final Enchantment blastProtection = new EnchantmentProtection(3, 2, 3);
   public static final Enchantment projectileProtection = new EnchantmentProtection(4, 5, 4);
   public static final Enchantment respiration = new EnchantmentOxygen(5, 2);
   public static final Enchantment aquaAffinity = new EnchantmentWaterWorker(6, 2);
   public static final Enchantment thorns = new EnchantmentThorns(7, 1);
   public static final Enchantment sharpness = new EnchantmentDamage(16, 10, 0);
   public static final Enchantment smite = new EnchantmentDamage(17, 5, 1);
   public static final Enchantment baneOfArthropods = new EnchantmentDamage(18, 5, 2);
   public static final Enchantment knockback = new EnchantmentKnockback(19, 5);
   public static final Enchantment fireAspect = new EnchantmentFireAspect(20, 2);
   public static final Enchantment looting = new EnchantmentLootBonus(21, 2, EnumEnchantmentType.weapon);
   public static final Enchantment efficiency = new EnchantmentDigging(32, 10);
   public static final Enchantment silkTouch = new EnchantmentUntouching(33, 1);
   public static final Enchantment unbreaking = new EnchantmentDurability(34, 5);
   public static final Enchantment fortune = new EnchantmentLootBonus(35, 2, EnumEnchantmentType.digger);
   public static final Enchantment power = new EnchantmentArrowDamage(48, 10);
   public static final Enchantment punch = new EnchantmentArrowKnockback(49, 2);
   public static final Enchantment flame = new EnchantmentArrowFire(50, 2);
   public static final Enchantment infinity = new EnchantmentArrowInfinite(51, 1);
   public static final Enchantment field_151370_z = new EnchantmentLootBonus(61, 2, EnumEnchantmentType.fishing_rod);
   public static final Enchantment field_151369_A = new EnchantmentFishingSpeed(62, 2, EnumEnchantmentType.fishing_rod);
   public final int effectId;
   private final int weight;
   public EnumEnchantmentType type;
   protected String name;


   protected Enchantment(int var1, int var2, EnumEnchantmentType var3) {
      this.effectId = var1;
      this.weight = var2;
      this.type = var3;
      if(enchantmentsList[var1] != null) {
         throw new IllegalArgumentException("Duplicate enchantment id!");
      } else {
         enchantmentsList[var1] = this;
      }
   }

   public int getWeight() {
      return this.weight;
   }

   public int getMinLevel() {
      return 1;
   }

   public int getMaxLevel() {
      return 1;
   }

   public int getMinEnchantability(int var1) {
      return 1 + var1 * 10;
   }

   public int getMaxEnchantability(int var1) {
      return this.getMinEnchantability(var1) + 5;
   }

   public int calcModifierDamage(int var1, DamageSource var2) {
      return 0;
   }

   public float func_152376_a(int var1, EnumCreatureAttribute var2) {
      return 0.0F;
   }

   public boolean canApplyTogether(Enchantment var1) {
      return this != var1;
   }

   public Enchantment setName(String var1) {
      this.name = var1;
      return this;
   }

   public String getName() {
      return "enchantment." + this.name;
   }

   public String getTranslatedName(int var1) {
      String var2 = StatCollector.translateToLocal(this.getName());
      return var2 + " " + StatCollector.translateToLocal("enchantment.level." + var1);
   }

   public boolean canApply(ItemStack var1) {
      return this.type.canEnchantItem(var1.getItem());
   }

   public void func_151368_a(EntityLivingBase var1, Entity var2, int var3) {}

   public void func_151367_b(EntityLivingBase var1, Entity var2, int var3) {}

   static {
      ArrayList var0 = new ArrayList();
      Enchantment[] var1 = enchantmentsList;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Enchantment var4 = var1[var3];
         if(var4 != null) {
            var0.add(var4);
         }
      }

      enchantmentsBookList = (Enchantment[])var0.toArray(new Enchantment[0]);
   }
}
