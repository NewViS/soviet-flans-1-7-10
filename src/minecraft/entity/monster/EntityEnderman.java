package net.minecraft.entity.monster;

import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEnderman extends EntityMob {

   private static final UUID attackingSpeedBoostModifierUUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
   private static final AttributeModifier attackingSpeedBoostModifier = (new AttributeModifier(attackingSpeedBoostModifierUUID, "Attacking speed boost", 6.199999809265137D, 0)).setSaved(false);
   private static boolean[] carriableBlocks = new boolean[256];
   private int teleportDelay;
   private int stareTimer;
   private Entity lastEntityToAttack;
   private boolean isAggressive;


   public EntityEnderman(World var1) {
      super(var1);
      this.setSize(0.6F, 2.9F);
      super.stepHeight = 1.0F;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
      this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0D);
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, new Byte((byte)0));
      super.dataWatcher.addObject(17, new Byte((byte)0));
      super.dataWatcher.addObject(18, new Byte((byte)0));
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setShort("carried", (short)Block.getIdFromBlock(this.func_146080_bZ()));
      var1.setShort("carriedData", (short)this.getCarryingData());
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.func_146081_a(Block.getBlockById(var1.getShort("carried")));
      this.setCarryingData(var1.getShort("carriedData"));
   }

   protected Entity findPlayerToAttack() {
      EntityPlayer var1 = super.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);
      if(var1 != null) {
         if(this.shouldAttackPlayer(var1)) {
            this.isAggressive = true;
            if(this.stareTimer == 0) {
               super.worldObj.playSoundEffect(var1.posX, var1.posY, var1.posZ, "mob.endermen.stare", 1.0F, 1.0F);
            }

            if(this.stareTimer++ == 5) {
               this.stareTimer = 0;
               this.setScreaming(true);
               return var1;
            }
         } else {
            this.stareTimer = 0;
         }
      }

      return null;
   }

   private boolean shouldAttackPlayer(EntityPlayer var1) {
      ItemStack var2 = var1.inventory.armorInventory[3];
      if(var2 != null && var2.getItem() == Item.getItemFromBlock(Blocks.pumpkin)) {
         return false;
      } else {
         Vec3 var3 = var1.getLook(1.0F).normalize();
         Vec3 var4 = Vec3.createVectorHelper(super.posX - var1.posX, super.boundingBox.minY + (double)(super.height / 2.0F) - (var1.posY + (double)var1.getEyeHeight()), super.posZ - var1.posZ);
         double var5 = var4.lengthVector();
         var4 = var4.normalize();
         double var7 = var3.dotProduct(var4);
         return var7 > 1.0D - 0.025D / var5 && var1.canEntityBeSeen(this);
      }
   }

   public void onLivingUpdate() {
      if(this.isWet()) {
         this.attackEntityFrom(DamageSource.drown, 1.0F);
      }

      if(this.lastEntityToAttack != super.entityToAttack) {
         IAttributeInstance var1 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
         var1.removeModifier(attackingSpeedBoostModifier);
         if(super.entityToAttack != null) {
            var1.applyModifier(attackingSpeedBoostModifier);
         }
      }

      this.lastEntityToAttack = super.entityToAttack;
      int var6;
      if(!super.worldObj.isRemote && super.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
         int var2;
         int var3;
         Block var4;
         if(this.func_146080_bZ().getMaterial() == Material.air) {
            if(super.rand.nextInt(20) == 0) {
               var6 = MathHelper.floor_double(super.posX - 2.0D + super.rand.nextDouble() * 4.0D);
               var2 = MathHelper.floor_double(super.posY + super.rand.nextDouble() * 3.0D);
               var3 = MathHelper.floor_double(super.posZ - 2.0D + super.rand.nextDouble() * 4.0D);
               var4 = super.worldObj.getBlock(var6, var2, var3);
               if(carriableBlocks[Block.getIdFromBlock(var4)]) {
                  this.func_146081_a(var4);
                  this.setCarryingData(super.worldObj.getBlockMetadata(var6, var2, var3));
                  super.worldObj.setBlock(var6, var2, var3, Blocks.air);
               }
            }
         } else if(super.rand.nextInt(2000) == 0) {
            var6 = MathHelper.floor_double(super.posX - 1.0D + super.rand.nextDouble() * 2.0D);
            var2 = MathHelper.floor_double(super.posY + super.rand.nextDouble() * 2.0D);
            var3 = MathHelper.floor_double(super.posZ - 1.0D + super.rand.nextDouble() * 2.0D);
            var4 = super.worldObj.getBlock(var6, var2, var3);
            Block var5 = super.worldObj.getBlock(var6, var2 - 1, var3);
            if(var4.getMaterial() == Material.air && var5.getMaterial() != Material.air && var5.renderAsNormalBlock()) {
               super.worldObj.setBlock(var6, var2, var3, this.func_146080_bZ(), this.getCarryingData(), 3);
               this.func_146081_a(Blocks.air);
            }
         }
      }

      for(var6 = 0; var6 < 2; ++var6) {
         super.worldObj.spawnParticle("portal", super.posX + (super.rand.nextDouble() - 0.5D) * (double)super.width, super.posY + super.rand.nextDouble() * (double)super.height - 0.25D, super.posZ + (super.rand.nextDouble() - 0.5D) * (double)super.width, (super.rand.nextDouble() - 0.5D) * 2.0D, -super.rand.nextDouble(), (super.rand.nextDouble() - 0.5D) * 2.0D);
      }

      if(super.worldObj.isDaytime() && !super.worldObj.isRemote) {
         float var7 = this.getBrightness(1.0F);
         if(var7 > 0.5F && super.worldObj.canBlockSeeTheSky(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ)) && super.rand.nextFloat() * 30.0F < (var7 - 0.4F) * 2.0F) {
            super.entityToAttack = null;
            this.setScreaming(false);
            this.isAggressive = false;
            this.teleportRandomly();
         }
      }

      if(this.isWet() || this.isBurning()) {
         super.entityToAttack = null;
         this.setScreaming(false);
         this.isAggressive = false;
         this.teleportRandomly();
      }

      if(this.isScreaming() && !this.isAggressive && super.rand.nextInt(100) == 0) {
         this.setScreaming(false);
      }

      super.isJumping = false;
      if(super.entityToAttack != null) {
         this.faceEntity(super.entityToAttack, 100.0F, 100.0F);
      }

      if(!super.worldObj.isRemote && this.isEntityAlive()) {
         if(super.entityToAttack != null) {
            if(super.entityToAttack instanceof EntityPlayer && this.shouldAttackPlayer((EntityPlayer)super.entityToAttack)) {
               if(super.entityToAttack.getDistanceSqToEntity(this) < 16.0D) {
                  this.teleportRandomly();
               }

               this.teleportDelay = 0;
            } else if(super.entityToAttack.getDistanceSqToEntity(this) > 256.0D && this.teleportDelay++ >= 30 && this.teleportToEntity(super.entityToAttack)) {
               this.teleportDelay = 0;
            }
         } else {
            this.setScreaming(false);
            this.teleportDelay = 0;
         }
      }

      super.onLivingUpdate();
   }

   protected boolean teleportRandomly() {
      double var1 = super.posX + (super.rand.nextDouble() - 0.5D) * 64.0D;
      double var3 = super.posY + (double)(super.rand.nextInt(64) - 32);
      double var5 = super.posZ + (super.rand.nextDouble() - 0.5D) * 64.0D;
      return this.teleportTo(var1, var3, var5);
   }

   protected boolean teleportToEntity(Entity var1) {
      Vec3 var2 = Vec3.createVectorHelper(super.posX - var1.posX, super.boundingBox.minY + (double)(super.height / 2.0F) - var1.posY + (double)var1.getEyeHeight(), super.posZ - var1.posZ);
      var2 = var2.normalize();
      double var3 = 16.0D;
      double var5 = super.posX + (super.rand.nextDouble() - 0.5D) * 8.0D - var2.xCoord * var3;
      double var7 = super.posY + (double)(super.rand.nextInt(16) - 8) - var2.yCoord * var3;
      double var9 = super.posZ + (super.rand.nextDouble() - 0.5D) * 8.0D - var2.zCoord * var3;
      return this.teleportTo(var5, var7, var9);
   }

   protected boolean teleportTo(double var1, double var3, double var5) {
      double var7 = super.posX;
      double var9 = super.posY;
      double var11 = super.posZ;
      super.posX = var1;
      super.posY = var3;
      super.posZ = var5;
      boolean var13 = false;
      int var14 = MathHelper.floor_double(super.posX);
      int var15 = MathHelper.floor_double(super.posY);
      int var16 = MathHelper.floor_double(super.posZ);
      if(super.worldObj.blockExists(var14, var15, var16)) {
         boolean var17 = false;

         while(!var17 && var15 > 0) {
            Block var18 = super.worldObj.getBlock(var14, var15 - 1, var16);
            if(var18.getMaterial().blocksMovement()) {
               var17 = true;
            } else {
               --super.posY;
               --var15;
            }
         }

         if(var17) {
            this.setPosition(super.posX, super.posY, super.posZ);
            if(super.worldObj.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty() && !super.worldObj.isAnyLiquid(super.boundingBox)) {
               var13 = true;
            }
         }
      }

      if(!var13) {
         this.setPosition(var7, var9, var11);
         return false;
      } else {
         short var30 = 128;

         for(int var31 = 0; var31 < var30; ++var31) {
            double var19 = (double)var31 / ((double)var30 - 1.0D);
            float var21 = (super.rand.nextFloat() - 0.5F) * 0.2F;
            float var22 = (super.rand.nextFloat() - 0.5F) * 0.2F;
            float var23 = (super.rand.nextFloat() - 0.5F) * 0.2F;
            double var24 = var7 + (super.posX - var7) * var19 + (super.rand.nextDouble() - 0.5D) * (double)super.width * 2.0D;
            double var26 = var9 + (super.posY - var9) * var19 + super.rand.nextDouble() * (double)super.height;
            double var28 = var11 + (super.posZ - var11) * var19 + (super.rand.nextDouble() - 0.5D) * (double)super.width * 2.0D;
            super.worldObj.spawnParticle("portal", var24, var26, var28, (double)var21, (double)var22, (double)var23);
         }

         super.worldObj.playSoundEffect(var7, var9, var11, "mob.endermen.portal", 1.0F, 1.0F);
         this.playSound("mob.endermen.portal", 1.0F, 1.0F);
         return true;
      }
   }

   protected String getLivingSound() {
      return this.isScreaming()?"mob.endermen.scream":"mob.endermen.idle";
   }

   protected String getHurtSound() {
      return "mob.endermen.hit";
   }

   protected String getDeathSound() {
      return "mob.endermen.death";
   }

   protected Item getDropItem() {
      return Items.ender_pearl;
   }

   protected void dropFewItems(boolean var1, int var2) {
      Item var3 = this.getDropItem();
      if(var3 != null) {
         int var4 = super.rand.nextInt(2 + var2);

         for(int var5 = 0; var5 < var4; ++var5) {
            this.dropItem(var3, 1);
         }
      }

   }

   public void func_146081_a(Block var1) {
      super.dataWatcher.updateObject(16, Byte.valueOf((byte)(Block.getIdFromBlock(var1) & 255)));
   }

   public Block func_146080_bZ() {
      return Block.getBlockById(super.dataWatcher.getWatchableObjectByte(16));
   }

   public void setCarryingData(int var1) {
      super.dataWatcher.updateObject(17, Byte.valueOf((byte)(var1 & 255)));
   }

   public int getCarryingData() {
      return super.dataWatcher.getWatchableObjectByte(17);
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         this.setScreaming(true);
         if(var1 instanceof EntityDamageSource && var1.getEntity() instanceof EntityPlayer) {
            this.isAggressive = true;
         }

         if(var1 instanceof EntityDamageSourceIndirect) {
            this.isAggressive = false;

            for(int var3 = 0; var3 < 64; ++var3) {
               if(this.teleportRandomly()) {
                  return true;
               }
            }

            return false;
         } else {
            return super.attackEntityFrom(var1, var2);
         }
      }
   }

   public boolean isScreaming() {
      return super.dataWatcher.getWatchableObjectByte(18) > 0;
   }

   public void setScreaming(boolean var1) {
      super.dataWatcher.updateObject(18, Byte.valueOf((byte)(var1?1:0)));
   }

   static {
      carriableBlocks[Block.getIdFromBlock(Blocks.grass)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.dirt)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.sand)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.gravel)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.yellow_flower)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.red_flower)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.brown_mushroom)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.red_mushroom)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.tnt)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.cactus)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.clay)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.pumpkin)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.melon_block)] = true;
      carriableBlocks[Block.getIdFromBlock(Blocks.mycelium)] = true;
   }
}
