package net.minecraft.entity.passive;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.world.World;

public abstract class EntityTameable extends EntityAnimal implements IEntityOwnable {

   protected EntityAISit aiSit = new EntityAISit(this);


   public EntityTameable(World var1) {
      super(var1);
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, Byte.valueOf((byte)0));
      super.dataWatcher.addObject(17, "");
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      if(this.func_152113_b() == null) {
         var1.setString("OwnerUUID", "");
      } else {
         var1.setString("OwnerUUID", this.func_152113_b());
      }

      var1.setBoolean("Sitting", this.isSitting());
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      String var2 = "";
      if(var1.hasKey("OwnerUUID", 8)) {
         var2 = var1.getString("OwnerUUID");
      } else {
         String var3 = var1.getString("Owner");
         var2 = PreYggdrasilConverter.func_152719_a(var3);
      }

      if(var2.length() > 0) {
         this.func_152115_b(var2);
         this.setTamed(true);
      }

      this.aiSit.setSitting(var1.getBoolean("Sitting"));
      this.setSitting(var1.getBoolean("Sitting"));
   }

   protected void playTameEffect(boolean var1) {
      String var2 = "heart";
      if(!var1) {
         var2 = "smoke";
      }

      for(int var3 = 0; var3 < 7; ++var3) {
         double var4 = super.rand.nextGaussian() * 0.02D;
         double var6 = super.rand.nextGaussian() * 0.02D;
         double var8 = super.rand.nextGaussian() * 0.02D;
         super.worldObj.spawnParticle(var2, super.posX + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, super.posY + 0.5D + (double)(super.rand.nextFloat() * super.height), super.posZ + (double)(super.rand.nextFloat() * super.width * 2.0F) - (double)super.width, var4, var6, var8);
      }

   }

   public void handleHealthUpdate(byte var1) {
      if(var1 == 7) {
         this.playTameEffect(true);
      } else if(var1 == 6) {
         this.playTameEffect(false);
      } else {
         super.handleHealthUpdate(var1);
      }

   }

   public boolean isTamed() {
      return (super.dataWatcher.getWatchableObjectByte(16) & 4) != 0;
   }

   public void setTamed(boolean var1) {
      byte var2 = super.dataWatcher.getWatchableObjectByte(16);
      if(var1) {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 4)));
      } else {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -5)));
      }

   }

   public boolean isSitting() {
      return (super.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   public void setSitting(boolean var1) {
      byte var2 = super.dataWatcher.getWatchableObjectByte(16);
      if(var1) {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 1)));
      } else {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -2)));
      }

   }

   public String func_152113_b() {
      return super.dataWatcher.getWatchableObjectString(17);
   }

   public void func_152115_b(String var1) {
      super.dataWatcher.updateObject(17, var1);
   }

   public EntityLivingBase getOwner() {
      try {
         UUID var1 = UUID.fromString(this.func_152113_b());
         return var1 == null?null:super.worldObj.func_152378_a(var1);
      } catch (IllegalArgumentException var2) {
         return null;
      }
   }

   public boolean func_152114_e(EntityLivingBase var1) {
      return var1 == this.getOwner();
   }

   public EntityAISit func_70907_r() {
      return this.aiSit;
   }

   public boolean func_142018_a(EntityLivingBase var1, EntityLivingBase var2) {
      return true;
   }

   public Team getTeam() {
      if(this.isTamed()) {
         EntityLivingBase var1 = this.getOwner();
         if(var1 != null) {
            return var1.getTeam();
         }
      }

      return super.getTeam();
   }

   public boolean isOnSameTeam(EntityLivingBase var1) {
      if(this.isTamed()) {
         EntityLivingBase var2 = this.getOwner();
         if(var1 == var2) {
            return true;
         }

         if(var2 != null) {
            return var2.isOnSameTeam(var1);
         }
      }

      return super.isOnSameTeam(var1);
   }

   // $FF: synthetic method
   public Entity getOwner() {
      return this.getOwner();
   }
}
