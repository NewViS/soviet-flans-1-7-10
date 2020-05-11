package net.minecraft.entity.ai;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

class EntityAINearestAttackableTarget$1 implements IEntitySelector {

   // $FF: synthetic field
   final IEntitySelector field_111103_c;
   // $FF: synthetic field
   final EntityAINearestAttackableTarget field_111102_d;


   EntityAINearestAttackableTarget$1(EntityAINearestAttackableTarget var1, IEntitySelector var2) {
      this.field_111102_d = var1;
      this.field_111103_c = var2;
   }

   public boolean isEntityApplicable(Entity var1) {
      return !(var1 instanceof EntityLivingBase)?false:(this.field_111103_c != null && !this.field_111103_c.isEntityApplicable(var1)?false:this.field_111102_d.isSuitableTarget((EntityLivingBase)var1, false));
   }
}
