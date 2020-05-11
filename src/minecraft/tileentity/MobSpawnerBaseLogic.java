package net.minecraft.tileentity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.MobSpawnerBaseLogic$WeightedRandomMinecart;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

public abstract class MobSpawnerBaseLogic {

   public int spawnDelay = 20;
   private String entityTypeName = "Pig";
   private List potentialEntitySpawns;
   private MobSpawnerBaseLogic$WeightedRandomMinecart randomEntity;
   public double field_98287_c;
   public double field_98284_d;
   private int minSpawnDelay = 200;
   private int maxSpawnDelay = 800;
   private int spawnCount = 4;
   private Entity field_98291_j;
   private int maxNearbyEntities = 6;
   private int activatingRangeFromPlayer = 16;
   private int spawnRange = 4;


   public String getEntityNameToSpawn() {
      if(this.getRandomEntity() == null) {
         if(this.entityTypeName.equals("Minecart")) {
            this.entityTypeName = "MinecartRideable";
         }

         return this.entityTypeName;
      } else {
         return this.getRandomEntity().entityTypeName;
      }
   }

   public void setEntityName(String var1) {
      this.entityTypeName = var1;
   }

   public boolean isActivated() {
      return this.getSpawnerWorld().getClosestPlayer((double)this.getSpawnerX() + 0.5D, (double)this.getSpawnerY() + 0.5D, (double)this.getSpawnerZ() + 0.5D, (double)this.activatingRangeFromPlayer) != null;
   }

   public void updateSpawner() {
      if(this.isActivated()) {
         double var5;
         if(this.getSpawnerWorld().isRemote) {
            double var1 = (double)((float)this.getSpawnerX() + this.getSpawnerWorld().rand.nextFloat());
            double var3 = (double)((float)this.getSpawnerY() + this.getSpawnerWorld().rand.nextFloat());
            var5 = (double)((float)this.getSpawnerZ() + this.getSpawnerWorld().rand.nextFloat());
            this.getSpawnerWorld().spawnParticle("smoke", var1, var3, var5, 0.0D, 0.0D, 0.0D);
            this.getSpawnerWorld().spawnParticle("flame", var1, var3, var5, 0.0D, 0.0D, 0.0D);
            if(this.spawnDelay > 0) {
               --this.spawnDelay;
            }

            this.field_98284_d = this.field_98287_c;
            this.field_98287_c = (this.field_98287_c + (double)(1000.0F / ((float)this.spawnDelay + 200.0F))) % 360.0D;
         } else {
            if(this.spawnDelay == -1) {
               this.resetTimer();
            }

            if(this.spawnDelay > 0) {
               --this.spawnDelay;
               return;
            }

            boolean var12 = false;

            for(int var2 = 0; var2 < this.spawnCount; ++var2) {
               Entity var13 = EntityList.createEntityByName(this.getEntityNameToSpawn(), this.getSpawnerWorld());
               if(var13 == null) {
                  return;
               }

               int var4 = this.getSpawnerWorld().getEntitiesWithinAABB(var13.getClass(), AxisAlignedBB.getBoundingBox((double)this.getSpawnerX(), (double)this.getSpawnerY(), (double)this.getSpawnerZ(), (double)(this.getSpawnerX() + 1), (double)(this.getSpawnerY() + 1), (double)(this.getSpawnerZ() + 1)).expand((double)(this.spawnRange * 2), 4.0D, (double)(this.spawnRange * 2))).size();
               if(var4 >= this.maxNearbyEntities) {
                  this.resetTimer();
                  return;
               }

               var5 = (double)this.getSpawnerX() + (this.getSpawnerWorld().rand.nextDouble() - this.getSpawnerWorld().rand.nextDouble()) * (double)this.spawnRange;
               double var7 = (double)(this.getSpawnerY() + this.getSpawnerWorld().rand.nextInt(3) - 1);
               double var9 = (double)this.getSpawnerZ() + (this.getSpawnerWorld().rand.nextDouble() - this.getSpawnerWorld().rand.nextDouble()) * (double)this.spawnRange;
               EntityLiving var11 = var13 instanceof EntityLiving?(EntityLiving)var13:null;
               var13.setLocationAndAngles(var5, var7, var9, this.getSpawnerWorld().rand.nextFloat() * 360.0F, 0.0F);
               if(var11 == null || var11.getCanSpawnHere()) {
                  this.func_98265_a(var13);
                  this.getSpawnerWorld().playAuxSFX(2004, this.getSpawnerX(), this.getSpawnerY(), this.getSpawnerZ(), 0);
                  if(var11 != null) {
                     var11.spawnExplosionParticle();
                  }

                  var12 = true;
               }
            }

            if(var12) {
               this.resetTimer();
            }
         }

      }
   }

   public Entity func_98265_a(Entity var1) {
      if(this.getRandomEntity() != null) {
         NBTTagCompound var2 = new NBTTagCompound();
         var1.writeToNBTOptional(var2);
         Iterator var3 = this.getRandomEntity().field_98222_b.func_150296_c().iterator();

         while(var3.hasNext()) {
            String var4 = (String)var3.next();
            NBTBase var5 = this.getRandomEntity().field_98222_b.getTag(var4);
            var2.setTag(var4, var5.copy());
         }

         var1.readFromNBT(var2);
         if(var1.worldObj != null) {
            var1.worldObj.spawnEntityInWorld(var1);
         }

         NBTTagCompound var11;
         for(Entity var10 = var1; var2.hasKey("Riding", 10); var2 = var11) {
            var11 = var2.getCompoundTag("Riding");
            Entity var12 = EntityList.createEntityByName(var11.getString("id"), var1.worldObj);
            if(var12 != null) {
               NBTTagCompound var6 = new NBTTagCompound();
               var12.writeToNBTOptional(var6);
               Iterator var7 = var11.func_150296_c().iterator();

               while(var7.hasNext()) {
                  String var8 = (String)var7.next();
                  NBTBase var9 = var11.getTag(var8);
                  var6.setTag(var8, var9.copy());
               }

               var12.readFromNBT(var6);
               var12.setLocationAndAngles(var10.posX, var10.posY, var10.posZ, var10.rotationYaw, var10.rotationPitch);
               if(var1.worldObj != null) {
                  var1.worldObj.spawnEntityInWorld(var12);
               }

               var10.mountEntity(var12);
            }

            var10 = var12;
         }
      } else if(var1 instanceof EntityLivingBase && var1.worldObj != null) {
         ((EntityLiving)var1).onSpawnWithEgg((IEntityLivingData)null);
         this.getSpawnerWorld().spawnEntityInWorld(var1);
      }

      return var1;
   }

   private void resetTimer() {
      if(this.maxSpawnDelay <= this.minSpawnDelay) {
         this.spawnDelay = this.minSpawnDelay;
      } else {
         int var10003 = this.maxSpawnDelay - this.minSpawnDelay;
         this.spawnDelay = this.minSpawnDelay + this.getSpawnerWorld().rand.nextInt(var10003);
      }

      if(this.potentialEntitySpawns != null && this.potentialEntitySpawns.size() > 0) {
         this.setRandomEntity((MobSpawnerBaseLogic$WeightedRandomMinecart)WeightedRandom.getRandomItem(this.getSpawnerWorld().rand, (Collection)this.potentialEntitySpawns));
      }

      this.func_98267_a(1);
   }

   public void readFromNBT(NBTTagCompound var1) {
      this.entityTypeName = var1.getString("EntityId");
      this.spawnDelay = var1.getShort("Delay");
      if(var1.hasKey("SpawnPotentials", 9)) {
         this.potentialEntitySpawns = new ArrayList();
         NBTTagList var2 = var1.getTagList("SpawnPotentials", 10);

         for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
            this.potentialEntitySpawns.add(new MobSpawnerBaseLogic$WeightedRandomMinecart(this, var2.getCompoundTagAt(var3)));
         }
      } else {
         this.potentialEntitySpawns = null;
      }

      if(var1.hasKey("SpawnData", 10)) {
         this.setRandomEntity(new MobSpawnerBaseLogic$WeightedRandomMinecart(this, var1.getCompoundTag("SpawnData"), this.entityTypeName));
      } else {
         this.setRandomEntity((MobSpawnerBaseLogic$WeightedRandomMinecart)null);
      }

      if(var1.hasKey("MinSpawnDelay", 99)) {
         this.minSpawnDelay = var1.getShort("MinSpawnDelay");
         this.maxSpawnDelay = var1.getShort("MaxSpawnDelay");
         this.spawnCount = var1.getShort("SpawnCount");
      }

      if(var1.hasKey("MaxNearbyEntities", 99)) {
         this.maxNearbyEntities = var1.getShort("MaxNearbyEntities");
         this.activatingRangeFromPlayer = var1.getShort("RequiredPlayerRange");
      }

      if(var1.hasKey("SpawnRange", 99)) {
         this.spawnRange = var1.getShort("SpawnRange");
      }

      if(this.getSpawnerWorld() != null && this.getSpawnerWorld().isRemote) {
         this.field_98291_j = null;
      }

   }

   public void writeToNBT(NBTTagCompound var1) {
      var1.setString("EntityId", this.getEntityNameToSpawn());
      var1.setShort("Delay", (short)this.spawnDelay);
      var1.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
      var1.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
      var1.setShort("SpawnCount", (short)this.spawnCount);
      var1.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
      var1.setShort("RequiredPlayerRange", (short)this.activatingRangeFromPlayer);
      var1.setShort("SpawnRange", (short)this.spawnRange);
      if(this.getRandomEntity() != null) {
         var1.setTag("SpawnData", this.getRandomEntity().field_98222_b.copy());
      }

      if(this.getRandomEntity() != null || this.potentialEntitySpawns != null && this.potentialEntitySpawns.size() > 0) {
         NBTTagList var2 = new NBTTagList();
         if(this.potentialEntitySpawns != null && this.potentialEntitySpawns.size() > 0) {
            Iterator var3 = this.potentialEntitySpawns.iterator();

            while(var3.hasNext()) {
               MobSpawnerBaseLogic$WeightedRandomMinecart var4 = (MobSpawnerBaseLogic$WeightedRandomMinecart)var3.next();
               var2.appendTag(var4.func_98220_a());
            }
         } else {
            var2.appendTag(this.getRandomEntity().func_98220_a());
         }

         var1.setTag("SpawnPotentials", var2);
      }

   }

   public Entity func_98281_h() {
      if(this.field_98291_j == null) {
         Entity var1 = EntityList.createEntityByName(this.getEntityNameToSpawn(), (World)null);
         var1 = this.func_98265_a(var1);
         this.field_98291_j = var1;
      }

      return this.field_98291_j;
   }

   public boolean setDelayToMin(int var1) {
      if(var1 == 1 && this.getSpawnerWorld().isRemote) {
         this.spawnDelay = this.minSpawnDelay;
         return true;
      } else {
         return false;
      }
   }

   public MobSpawnerBaseLogic$WeightedRandomMinecart getRandomEntity() {
      return this.randomEntity;
   }

   public void setRandomEntity(MobSpawnerBaseLogic$WeightedRandomMinecart var1) {
      this.randomEntity = var1;
   }

   public abstract void func_98267_a(int var1);

   public abstract World getSpawnerWorld();

   public abstract int getSpawnerX();

   public abstract int getSpawnerY();

   public abstract int getSpawnerZ();
}
