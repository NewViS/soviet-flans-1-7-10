package net.minecraft.block;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.block.BlockPressurePlate$Sensitivity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockPressurePlate extends BlockBasePressurePlate {

   private BlockPressurePlate$Sensitivity field_150069_a;


   protected BlockPressurePlate(String var1, Material var2, BlockPressurePlate$Sensitivity var3) {
      super(var1, var2);
      this.field_150069_a = var3;
   }

   protected int func_150066_d(int var1) {
      return var1 > 0?1:0;
   }

   protected int func_150060_c(int var1) {
      return var1 == 1?15:0;
   }

   protected int func_150065_e(World var1, int var2, int var3, int var4) {
      List var5 = null;
      if(this.field_150069_a == BlockPressurePlate$Sensitivity.everything) {
         var5 = var1.getEntitiesWithinAABBExcludingEntity((Entity)null, this.func_150061_a(var2, var3, var4));
      }

      if(this.field_150069_a == BlockPressurePlate$Sensitivity.mobs) {
         var5 = var1.getEntitiesWithinAABB(EntityLivingBase.class, this.func_150061_a(var2, var3, var4));
      }

      if(this.field_150069_a == BlockPressurePlate$Sensitivity.players) {
         var5 = var1.getEntitiesWithinAABB(EntityPlayer.class, this.func_150061_a(var2, var3, var4));
      }

      if(var5 != null && !var5.isEmpty()) {
         Iterator var6 = var5.iterator();

         while(var6.hasNext()) {
            Entity var7 = (Entity)var6.next();
            if(!var7.doesEntityNotTriggerPressurePlate()) {
               return 15;
            }
         }
      }

      return 0;
   }
}
