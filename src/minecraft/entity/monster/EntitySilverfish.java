package net.minecraft.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class EntitySilverfish extends EntityMob {

   private int allySummonCooldown;


   public EntitySilverfish(World var1) {
      super(var1);
      this.setSize(0.3F, 0.7F);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6000000238418579D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected Entity findPlayerToAttack() {
      double var1 = 8.0D;
      return super.worldObj.getClosestVulnerablePlayerToEntity(this, var1);
   }

   protected String getLivingSound() {
      return "mob.silverfish.say";
   }

   protected String getHurtSound() {
      return "mob.silverfish.hit";
   }

   protected String getDeathSound() {
      return "mob.silverfish.kill";
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         if(this.allySummonCooldown <= 0 && (var1 instanceof EntityDamageSource || var1 == DamageSource.magic)) {
            this.allySummonCooldown = 20;
         }

         return super.attackEntityFrom(var1, var2);
      }
   }

   protected void attackEntity(Entity var1, float var2) {
      if(super.attackTime <= 0 && var2 < 1.2F && var1.boundingBox.maxY > super.boundingBox.minY && var1.boundingBox.minY < super.boundingBox.maxY) {
         super.attackTime = 20;
         this.attackEntityAsMob(var1);
      }

   }

   protected void func_145780_a(int var1, int var2, int var3, Block var4) {
      this.playSound("mob.silverfish.step", 0.15F, 1.0F);
   }

   protected Item getDropItem() {
      return Item.getItemById(0);
   }

   public void onUpdate() {
      super.renderYawOffset = super.rotationYaw;
      super.onUpdate();
   }

   protected void updateEntityActionState() {
      super.updateEntityActionState();
      if(!super.worldObj.isRemote) {
         int var1;
         int var2;
         int var3;
         int var6;
         if(this.allySummonCooldown > 0) {
            --this.allySummonCooldown;
            if(this.allySummonCooldown == 0) {
               var1 = MathHelper.floor_double(super.posX);
               var2 = MathHelper.floor_double(super.posY);
               var3 = MathHelper.floor_double(super.posZ);
               boolean var4 = false;

               for(int var5 = 0; !var4 && var5 <= 5 && var5 >= -5; var5 = var5 <= 0?1 - var5:0 - var5) {
                  for(var6 = 0; !var4 && var6 <= 10 && var6 >= -10; var6 = var6 <= 0?1 - var6:0 - var6) {
                     for(int var7 = 0; !var4 && var7 <= 10 && var7 >= -10; var7 = var7 <= 0?1 - var7:0 - var7) {
                        if(super.worldObj.getBlock(var1 + var6, var2 + var5, var3 + var7) == Blocks.monster_egg) {
                           if(!super.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
                              int var8 = super.worldObj.getBlockMetadata(var1 + var6, var2 + var5, var3 + var7);
                              ImmutablePair var9 = BlockSilverfish.func_150197_b(var8);
                              super.worldObj.setBlock(var1 + var6, var2 + var5, var3 + var7, (Block)var9.getLeft(), ((Integer)var9.getRight()).intValue(), 3);
                           } else {
                              super.worldObj.func_147480_a(var1 + var6, var2 + var5, var3 + var7, false);
                           }

                           Blocks.monster_egg.onBlockDestroyedByPlayer(super.worldObj, var1 + var6, var2 + var5, var3 + var7, 0);
                           if(super.rand.nextBoolean()) {
                              var4 = true;
                              break;
                           }
                        }
                     }
                  }
               }
            }
         }

         if(super.entityToAttack == null && !this.hasPath()) {
            var1 = MathHelper.floor_double(super.posX);
            var2 = MathHelper.floor_double(super.posY + 0.5D);
            var3 = MathHelper.floor_double(super.posZ);
            int var10 = super.rand.nextInt(6);
            Block var11 = super.worldObj.getBlock(var1 + Facing.offsetsXForSide[var10], var2 + Facing.offsetsYForSide[var10], var3 + Facing.offsetsZForSide[var10]);
            var6 = super.worldObj.getBlockMetadata(var1 + Facing.offsetsXForSide[var10], var2 + Facing.offsetsYForSide[var10], var3 + Facing.offsetsZForSide[var10]);
            if(BlockSilverfish.func_150196_a(var11)) {
               super.worldObj.setBlock(var1 + Facing.offsetsXForSide[var10], var2 + Facing.offsetsYForSide[var10], var3 + Facing.offsetsZForSide[var10], Blocks.monster_egg, BlockSilverfish.func_150195_a(var11, var6), 3);
               this.spawnExplosionParticle();
               this.setDead();
            } else {
               this.updateWanderPath();
            }
         } else if(super.entityToAttack != null && !this.hasPath()) {
            super.entityToAttack = null;
         }

      }
   }

   public float getBlockPathWeight(int var1, int var2, int var3) {
      return super.worldObj.getBlock(var1, var2 - 1, var3) == Blocks.stone?10.0F:super.getBlockPathWeight(var1, var2, var3);
   }

   protected boolean isValidLightLevel() {
      return true;
   }

   public boolean getCanSpawnHere() {
      if(super.getCanSpawnHere()) {
         EntityPlayer var1 = super.worldObj.getClosestPlayerToEntity(this, 5.0D);
         return var1 == null;
      } else {
         return false;
      }
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.ARTHROPOD;
   }
}
