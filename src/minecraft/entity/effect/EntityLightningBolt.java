package net.minecraft.entity.effect;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityLightningBolt extends EntityWeatherEffect {

   private int lightningState;
   public long boltVertex;
   private int boltLivingTime;


   public EntityLightningBolt(World var1, double var2, double var4, double var6) {
      super(var1);
      this.setLocationAndAngles(var2, var4, var6, 0.0F, 0.0F);
      this.lightningState = 2;
      this.boltVertex = super.rand.nextLong();
      this.boltLivingTime = super.rand.nextInt(3) + 1;
      if(!var1.isRemote && var1.getGameRules().getGameRuleBooleanValue("doFireTick") && (var1.difficultySetting == EnumDifficulty.NORMAL || var1.difficultySetting == EnumDifficulty.HARD) && var1.doChunksNearChunkExist(MathHelper.floor_double(var2), MathHelper.floor_double(var4), MathHelper.floor_double(var6), 10)) {
         int var8 = MathHelper.floor_double(var2);
         int var9 = MathHelper.floor_double(var4);
         int var10 = MathHelper.floor_double(var6);
         if(var1.getBlock(var8, var9, var10).getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(var1, var8, var9, var10)) {
            var1.setBlock(var8, var9, var10, Blocks.fire);
         }

         for(var8 = 0; var8 < 4; ++var8) {
            var9 = MathHelper.floor_double(var2) + super.rand.nextInt(3) - 1;
            var10 = MathHelper.floor_double(var4) + super.rand.nextInt(3) - 1;
            int var11 = MathHelper.floor_double(var6) + super.rand.nextInt(3) - 1;
            if(var1.getBlock(var9, var10, var11).getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(var1, var9, var10, var11)) {
               var1.setBlock(var9, var10, var11, Blocks.fire);
            }
         }
      }

   }

   public void onUpdate() {
      super.onUpdate();
      if(this.lightningState == 2) {
         super.worldObj.playSoundEffect(super.posX, super.posY, super.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + super.rand.nextFloat() * 0.2F);
         super.worldObj.playSoundEffect(super.posX, super.posY, super.posZ, "random.explode", 2.0F, 0.5F + super.rand.nextFloat() * 0.2F);
      }

      --this.lightningState;
      if(this.lightningState < 0) {
         if(this.boltLivingTime == 0) {
            this.setDead();
         } else if(this.lightningState < -super.rand.nextInt(10)) {
            --this.boltLivingTime;
            this.lightningState = 1;
            this.boltVertex = super.rand.nextLong();
            if(!super.worldObj.isRemote && super.worldObj.getGameRules().getGameRuleBooleanValue("doFireTick") && super.worldObj.doChunksNearChunkExist(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ), 10)) {
               int var1 = MathHelper.floor_double(super.posX);
               int var2 = MathHelper.floor_double(super.posY);
               int var3 = MathHelper.floor_double(super.posZ);
               if(super.worldObj.getBlock(var1, var2, var3).getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(super.worldObj, var1, var2, var3)) {
                  super.worldObj.setBlock(var1, var2, var3, Blocks.fire);
               }
            }
         }
      }

      if(this.lightningState >= 0) {
         if(super.worldObj.isRemote) {
            super.worldObj.lastLightningBolt = 2;
         } else {
            double var6 = 3.0D;
            List var7 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(super.posX - var6, super.posY - var6, super.posZ - var6, super.posX + var6, super.posY + 6.0D + var6, super.posZ + var6));

            for(int var4 = 0; var4 < var7.size(); ++var4) {
               Entity var5 = (Entity)var7.get(var4);
               var5.onStruckByLightning(this);
            }
         }
      }

   }

   protected void entityInit() {}

   protected void readEntityFromNBT(NBTTagCompound var1) {}

   protected void writeEntityToNBT(NBTTagCompound var1) {}
}
