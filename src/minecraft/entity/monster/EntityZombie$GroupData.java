package net.minecraft.entity.monster;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombie$1;

class EntityZombie$GroupData implements IEntityLivingData {

   public boolean field_142048_a;
   public boolean field_142046_b;
   // $FF: synthetic field
   final EntityZombie field_142047_c;


   private EntityZombie$GroupData(EntityZombie var1, boolean var2, boolean var3) {
      this.field_142047_c = var1;
      this.field_142048_a = false;
      this.field_142046_b = false;
      this.field_142048_a = var2;
      this.field_142046_b = var3;
   }

   // $FF: synthetic method
   EntityZombie$GroupData(EntityZombie var1, boolean var2, boolean var3, EntityZombie$1 var4) {
      this(var1, var2, var3);
   }
}
