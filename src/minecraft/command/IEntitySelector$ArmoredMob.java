package net.minecraft.command;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class IEntitySelector$ArmoredMob implements IEntitySelector {

   private final ItemStack field_96567_c;


   public IEntitySelector$ArmoredMob(ItemStack var1) {
      this.field_96567_c = var1;
   }

   public boolean isEntityApplicable(Entity var1) {
      if(!var1.isEntityAlive()) {
         return false;
      } else if(!(var1 instanceof EntityLivingBase)) {
         return false;
      } else {
         EntityLivingBase var2 = (EntityLivingBase)var1;
         return var2.getEquipmentInSlot(EntityLiving.getArmorPosition(this.field_96567_c)) != null?false:(var2 instanceof EntityLiving?((EntityLiving)var2).canPickUpLoot():var2 instanceof EntityPlayer);
      }
   }
}
