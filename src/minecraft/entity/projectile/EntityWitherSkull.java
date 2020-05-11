package net.minecraft.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityWitherSkull extends EntityFireball {

   public EntityWitherSkull(World var1) {
      super(var1);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntityWitherSkull(World var1, EntityLivingBase var2, double var3, double var5, double var7) {
      super(var1, var2, var3, var5, var7);
      this.setSize(0.3125F, 0.3125F);
   }

   protected float getMotionFactor() {
      return this.isInvulnerable()?0.73F:super.getMotionFactor();
   }

   public EntityWitherSkull(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.setSize(0.3125F, 0.3125F);
   }

   public boolean isBurning() {
      return false;
   }

   public float func_145772_a(Explosion var1, World var2, int var3, int var4, int var5, Block var6) {
      float var7 = super.func_145772_a(var1, var2, var3, var4, var5, var6);
      if(this.isInvulnerable() && var6 != Blocks.bedrock && var6 != Blocks.end_portal && var6 != Blocks.end_portal_frame && var6 != Blocks.command_block) {
         var7 = Math.min(0.8F, var7);
      }

      return var7;
   }

   protected void onImpact(MovingObjectPosition var1) {
      if(!super.worldObj.isRemote) {
         if(var1.entityHit != null) {
            if(super.shootingEntity != null) {
               if(var1.entityHit.attackEntityFrom(DamageSource.causeMobDamage(super.shootingEntity), 8.0F) && !var1.entityHit.isEntityAlive()) {
                  super.shootingEntity.heal(5.0F);
               }
            } else {
               var1.entityHit.attackEntityFrom(DamageSource.magic, 5.0F);
            }

            if(var1.entityHit instanceof EntityLivingBase) {
               byte var2 = 0;
               if(super.worldObj.difficultySetting == EnumDifficulty.NORMAL) {
                  var2 = 10;
               } else if(super.worldObj.difficultySetting == EnumDifficulty.HARD) {
                  var2 = 40;
               }

               if(var2 > 0) {
                  ((EntityLivingBase)var1.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * var2, 1));
               }
            }
         }

         super.worldObj.newExplosion(this, super.posX, super.posY, super.posZ, 1.0F, false, super.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
         this.setDead();
      }

   }

   public boolean canBeCollidedWith() {
      return false;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      return false;
   }

   protected void entityInit() {
      super.dataWatcher.addObject(10, Byte.valueOf((byte)0));
   }

   public boolean isInvulnerable() {
      return super.dataWatcher.getWatchableObjectByte(10) == 1;
   }

   public void setInvulnerable(boolean var1) {
      super.dataWatcher.updateObject(10, Byte.valueOf((byte)(var1?1:0)));
   }
}
