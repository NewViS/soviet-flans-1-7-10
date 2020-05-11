package net.minecraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class EntityDamageSource extends DamageSource {

   protected Entity damageSourceEntity;


   public EntityDamageSource(String var1, Entity var2) {
      super(var1);
      this.damageSourceEntity = var2;
   }

   public Entity getEntity() {
      return this.damageSourceEntity;
   }

   public IChatComponent func_151519_b(EntityLivingBase var1) {
      ItemStack var2 = this.damageSourceEntity instanceof EntityLivingBase?((EntityLivingBase)this.damageSourceEntity).getHeldItem():null;
      String var3 = "death.attack." + super.damageType;
      String var4 = var3 + ".item";
      return var2 != null && var2.hasDisplayName() && StatCollector.canTranslate(var4)?new ChatComponentTranslation(var4, new Object[]{var1.func_145748_c_(), this.damageSourceEntity.func_145748_c_(), var2.func_151000_E()}):new ChatComponentTranslation(var3, new Object[]{var1.func_145748_c_(), this.damageSourceEntity.func_145748_c_()});
   }

   public boolean isDifficultyScaled() {
      return this.damageSourceEntity != null && this.damageSourceEntity instanceof EntityLivingBase && !(this.damageSourceEntity instanceof EntityPlayer);
   }
}
