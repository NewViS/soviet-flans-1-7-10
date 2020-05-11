package net.minecraft.entity.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityGiantZombie extends EntityMob {

   public EntityGiantZombie(World var1) {
      super(var1);
      super.yOffset *= 6.0F;
      this.setSize(super.width * 6.0F, super.height * 6.0F);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(50.0D);
   }

   public float getBlockPathWeight(int var1, int var2, int var3) {
      return super.worldObj.getLightBrightness(var1, var2, var3) - 0.5F;
   }
}
