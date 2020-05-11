package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.StringUtils;

public abstract class EntityAITarget extends EntityAIBase {

   protected EntityCreature taskOwner;
   protected boolean shouldCheckSight;
   private boolean nearbyOnly;
   private int targetSearchStatus;
   private int targetSearchDelay;
   private int field_75298_g;


   public EntityAITarget(EntityCreature var1, boolean var2) {
      this(var1, var2, false);
   }

   public EntityAITarget(EntityCreature var1, boolean var2, boolean var3) {
      this.taskOwner = var1;
      this.shouldCheckSight = var2;
      this.nearbyOnly = var3;
   }

   public boolean continueExecuting() {
      EntityLivingBase var1 = this.taskOwner.getAttackTarget();
      if(var1 == null) {
         return false;
      } else if(!var1.isEntityAlive()) {
         return false;
      } else {
         double var2 = this.getTargetDistance();
         if(this.taskOwner.getDistanceSqToEntity(var1) > var2 * var2) {
            return false;
         } else {
            if(this.shouldCheckSight) {
               if(this.taskOwner.getEntitySenses().canSee(var1)) {
                  this.field_75298_g = 0;
               } else if(++this.field_75298_g > 60) {
                  return false;
               }
            }

            return !(var1 instanceof EntityPlayerMP) || !((EntityPlayerMP)var1).theItemInWorldManager.isCreative();
         }
      }
   }

   protected double getTargetDistance() {
      IAttributeInstance var1 = this.taskOwner.getEntityAttribute(SharedMonsterAttributes.followRange);
      return var1 == null?16.0D:var1.getAttributeValue();
   }

   public void startExecuting() {
      this.targetSearchStatus = 0;
      this.targetSearchDelay = 0;
      this.field_75298_g = 0;
   }

   public void resetTask() {
      this.taskOwner.setAttackTarget((EntityLivingBase)null);
   }

   protected boolean isSuitableTarget(EntityLivingBase var1, boolean var2) {
      if(var1 == null) {
         return false;
      } else if(var1 == this.taskOwner) {
         return false;
      } else if(!var1.isEntityAlive()) {
         return false;
      } else if(!this.taskOwner.canAttackClass(var1.getClass())) {
         return false;
      } else {
         if(this.taskOwner instanceof IEntityOwnable && StringUtils.isNotEmpty(((IEntityOwnable)this.taskOwner).func_152113_b())) {
            if(var1 instanceof IEntityOwnable && ((IEntityOwnable)this.taskOwner).func_152113_b().equals(((IEntityOwnable)var1).func_152113_b())) {
               return false;
            }

            if(var1 == ((IEntityOwnable)this.taskOwner).getOwner()) {
               return false;
            }
         } else if(var1 instanceof EntityPlayer && !var2 && ((EntityPlayer)var1).capabilities.disableDamage) {
            return false;
         }

         if(!this.taskOwner.isWithinHomeDistance(MathHelper.floor_double(var1.posX), MathHelper.floor_double(var1.posY), MathHelper.floor_double(var1.posZ))) {
            return false;
         } else if(this.shouldCheckSight && !this.taskOwner.getEntitySenses().canSee(var1)) {
            return false;
         } else {
            if(this.nearbyOnly) {
               if(--this.targetSearchDelay <= 0) {
                  this.targetSearchStatus = 0;
               }

               if(this.targetSearchStatus == 0) {
                  this.targetSearchStatus = this.canEasilyReach(var1)?1:2;
               }

               if(this.targetSearchStatus == 2) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   private boolean canEasilyReach(EntityLivingBase var1) {
      this.targetSearchDelay = 10 + this.taskOwner.getRNG().nextInt(5);
      PathEntity var2 = this.taskOwner.getNavigator().getPathToEntityLiving(var1);
      if(var2 == null) {
         return false;
      } else {
         PathPoint var3 = var2.getFinalPathPoint();
         if(var3 == null) {
            return false;
         } else {
            int var4 = var3.xCoord - MathHelper.floor_double(var1.posX);
            int var5 = var3.zCoord - MathHelper.floor_double(var1.posZ);
            return (double)(var4 * var4 + var5 * var5) <= 2.25D;
         }
      }
   }
}
