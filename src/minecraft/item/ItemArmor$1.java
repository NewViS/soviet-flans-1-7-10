package net.minecraft.item;

import java.util.List;
import net.minecraft.block.BlockDispenser;
import net.minecraft.command.IEntitySelector$ArmoredMob;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;

final class ItemArmor$1 extends BehaviorDefaultDispenseItem {

   protected ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      EnumFacing var3 = BlockDispenser.func_149937_b(var1.getBlockMetadata());
      int var4 = var1.getXInt() + var3.getFrontOffsetX();
      int var5 = var1.getYInt() + var3.getFrontOffsetY();
      int var6 = var1.getZInt() + var3.getFrontOffsetZ();
      AxisAlignedBB var7 = AxisAlignedBB.getBoundingBox((double)var4, (double)var5, (double)var6, (double)(var4 + 1), (double)(var5 + 1), (double)(var6 + 1));
      List var8 = var1.getWorld().selectEntitiesWithinAABB(EntityLivingBase.class, var7, new IEntitySelector$ArmoredMob(var2));
      if(var8.size() > 0) {
         EntityLivingBase var9 = (EntityLivingBase)var8.get(0);
         int var10 = var9 instanceof EntityPlayer?1:0;
         int var11 = EntityLiving.getArmorPosition(var2);
         ItemStack var12 = var2.copy();
         var12.stackSize = 1;
         var9.setCurrentItemOrArmor(var11 - var10, var12);
         if(var9 instanceof EntityLiving) {
            ((EntityLiving)var9).setEquipmentDropChance(var11, 2.0F);
         }

         --var2.stackSize;
         return var2;
      } else {
         return super.dispenseStack(var1, var2);
      }
   }
}
