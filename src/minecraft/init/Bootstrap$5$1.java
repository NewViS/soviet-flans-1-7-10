package net.minecraft.init;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Bootstrap$5;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

class Bootstrap$5$1 extends BehaviorProjectileDispense {

   // $FF: synthetic field
   final ItemStack field_150836_b;
   // $FF: synthetic field
   final Bootstrap$5 field_150837_c;


   Bootstrap$5$1(Bootstrap$5 var1, ItemStack var2) {
      this.field_150837_c = var1;
      this.field_150836_b = var2;
   }

   protected IProjectile getProjectileEntity(World var1, IPosition var2) {
      return new EntityPotion(var1, var2.getX(), var2.getY(), var2.getZ(), this.field_150836_b.copy());
   }

   protected float func_82498_a() {
      return super.func_82498_a() * 0.5F;
   }

   protected float func_82500_b() {
      return super.func_82500_b() * 1.25F;
   }
}
