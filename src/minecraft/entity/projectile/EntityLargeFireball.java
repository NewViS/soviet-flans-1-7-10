package net.minecraft.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityLargeFireball extends EntityFireball {

   public int field_92057_e = 1;


   public EntityLargeFireball(World var1) {
      super(var1);
   }

   public EntityLargeFireball(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
   }

   public EntityLargeFireball(World var1, EntityLivingBase var2, double var3, double var5, double var7) {
      super(var1, var2, var3, var5, var7);
   }

   protected void onImpact(MovingObjectPosition var1) {
      if(!super.worldObj.isRemote) {
         if(var1.entityHit != null) {
            var1.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, super.shootingEntity), 6.0F);
         }

         super.worldObj.newExplosion((Entity)null, super.posX, super.posY, super.posZ, (float)this.field_92057_e, true, super.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
         this.setDead();
      }

   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("ExplosionPower", this.field_92057_e);
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if(var1.hasKey("ExplosionPower", 99)) {
         this.field_92057_e = var1.getInteger("ExplosionPower");
      }

   }
}
