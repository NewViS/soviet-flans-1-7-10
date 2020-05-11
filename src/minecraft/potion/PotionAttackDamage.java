package net.minecraft.potion;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Potion;

public class PotionAttackDamage extends Potion {

   protected PotionAttackDamage(int var1, boolean var2, int var3) {
      super(var1, var2, var3);
   }

   public double func_111183_a(int var1, AttributeModifier var2) {
      return super.id == Potion.weakness.id?(double)(-0.5F * (float)(var1 + 1)):1.3D * (double)(var1 + 1);
   }
}
