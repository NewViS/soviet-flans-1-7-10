package net.minecraft.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCreeper extends EntityMob {

   private int lastActiveTime;
   private int timeSinceIgnited;
   private int fuseTime = 30;
   private int explosionRadius = 3;


   public EntityCreeper(World var1) {
      super(var1);
      super.tasks.addTask(1, new EntityAISwimming(this));
      super.tasks.addTask(2, new EntityAICreeperSwell(this));
      super.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
      super.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, false));
      super.tasks.addTask(5, new EntityAIWander(this, 0.8D));
      super.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      super.tasks.addTask(6, new EntityAILookIdle(this));
      super.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
      super.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
   }

   public boolean isAIEnabled() {
      return true;
   }

   public int getMaxSafePointTries() {
      return this.getAttackTarget() == null?3:3 + (int)(this.getHealth() - 1.0F);
   }

   protected void fall(float var1) {
      super.fall(var1);
      this.timeSinceIgnited = (int)((float)this.timeSinceIgnited + var1 * 1.5F);
      if(this.timeSinceIgnited > this.fuseTime - 5) {
         this.timeSinceIgnited = this.fuseTime - 5;
      }

   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, Byte.valueOf((byte)-1));
      super.dataWatcher.addObject(17, Byte.valueOf((byte)0));
      super.dataWatcher.addObject(18, Byte.valueOf((byte)0));
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      if(super.dataWatcher.getWatchableObjectByte(17) == 1) {
         var1.setBoolean("powered", true);
      }

      var1.setShort("Fuse", (short)this.fuseTime);
      var1.setByte("ExplosionRadius", (byte)this.explosionRadius);
      var1.setBoolean("ignited", this.func_146078_ca());
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      super.dataWatcher.updateObject(17, Byte.valueOf((byte)(var1.getBoolean("powered")?1:0)));
      if(var1.hasKey("Fuse", 99)) {
         this.fuseTime = var1.getShort("Fuse");
      }

      if(var1.hasKey("ExplosionRadius", 99)) {
         this.explosionRadius = var1.getByte("ExplosionRadius");
      }

      if(var1.getBoolean("ignited")) {
         this.func_146079_cb();
      }

   }

   public void onUpdate() {
      if(this.isEntityAlive()) {
         this.lastActiveTime = this.timeSinceIgnited;
         if(this.func_146078_ca()) {
            this.setCreeperState(1);
         }

         int var1 = this.getCreeperState();
         if(var1 > 0 && this.timeSinceIgnited == 0) {
            this.playSound("creeper.primed", 1.0F, 0.5F);
         }

         this.timeSinceIgnited += var1;
         if(this.timeSinceIgnited < 0) {
            this.timeSinceIgnited = 0;
         }

         if(this.timeSinceIgnited >= this.fuseTime) {
            this.timeSinceIgnited = this.fuseTime;
            this.func_146077_cc();
         }
      }

      super.onUpdate();
   }

   protected String getHurtSound() {
      return "mob.creeper.say";
   }

   protected String getDeathSound() {
      return "mob.creeper.death";
   }

   public void onDeath(DamageSource var1) {
      super.onDeath(var1);
      if(var1.getEntity() instanceof EntitySkeleton) {
         int var2 = Item.getIdFromItem(Items.record_13);
         int var3 = Item.getIdFromItem(Items.record_wait);
         int var4 = var2 + super.rand.nextInt(var3 - var2 + 1);
         this.dropItem(Item.getItemById(var4), 1);
      }

   }

   public boolean attackEntityAsMob(Entity var1) {
      return true;
   }

   public boolean getPowered() {
      return super.dataWatcher.getWatchableObjectByte(17) == 1;
   }

   public float getCreeperFlashIntensity(float var1) {
      return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * var1) / (float)(this.fuseTime - 2);
   }

   protected Item getDropItem() {
      return Items.gunpowder;
   }

   public int getCreeperState() {
      return super.dataWatcher.getWatchableObjectByte(16);
   }

   public void setCreeperState(int var1) {
      super.dataWatcher.updateObject(16, Byte.valueOf((byte)var1));
   }

   public void onStruckByLightning(EntityLightningBolt var1) {
      super.onStruckByLightning(var1);
      super.dataWatcher.updateObject(17, Byte.valueOf((byte)1));
   }

   protected boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.inventory.getCurrentItem();
      if(var2 != null && var2.getItem() == Items.flint_and_steel) {
         super.worldObj.playSoundEffect(super.posX + 0.5D, super.posY + 0.5D, super.posZ + 0.5D, "fire.ignite", 1.0F, super.rand.nextFloat() * 0.4F + 0.8F);
         var1.swingItem();
         if(!super.worldObj.isRemote) {
            this.func_146079_cb();
            var2.damageItem(1, var1);
            return true;
         }
      }

      return super.interact(var1);
   }

   private void func_146077_cc() {
      if(!super.worldObj.isRemote) {
         boolean var1 = super.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
         if(this.getPowered()) {
            super.worldObj.createExplosion(this, super.posX, super.posY, super.posZ, (float)(this.explosionRadius * 2), var1);
         } else {
            super.worldObj.createExplosion(this, super.posX, super.posY, super.posZ, (float)this.explosionRadius, var1);
         }

         this.setDead();
      }

   }

   public boolean func_146078_ca() {
      return super.dataWatcher.getWatchableObjectByte(18) != 0;
   }

   public void func_146079_cb() {
      super.dataWatcher.updateObject(18, Byte.valueOf((byte)1));
   }
}
