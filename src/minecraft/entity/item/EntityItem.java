package net.minecraft.entity.item;

import java.util.Iterator;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityItem extends Entity {

   private static final Logger logger = LogManager.getLogger();
   public int age;
   public int delayBeforeCanPickup;
   private int health;
   private String field_145801_f;
   private String field_145802_g;
   public float hoverStart;


   public EntityItem(World var1, double var2, double var4, double var6) {
      super(var1);
      this.health = 5;
      this.hoverStart = (float)(Math.random() * 3.141592653589793D * 2.0D);
      this.setSize(0.25F, 0.25F);
      super.yOffset = super.height / 2.0F;
      this.setPosition(var2, var4, var6);
      super.rotationYaw = (float)(Math.random() * 360.0D);
      super.motionX = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
      super.motionY = 0.20000000298023224D;
      super.motionZ = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
   }

   public EntityItem(World var1, double var2, double var4, double var6, ItemStack var8) {
      this(var1, var2, var4, var6);
      this.setEntityItemStack(var8);
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   public EntityItem(World var1) {
      super(var1);
      this.health = 5;
      this.hoverStart = (float)(Math.random() * 3.141592653589793D * 2.0D);
      this.setSize(0.25F, 0.25F);
      super.yOffset = super.height / 2.0F;
   }

   protected void entityInit() {
      this.getDataWatcher().addObjectByDataType(10, 5);
   }

   public void onUpdate() {
      if(this.getEntityItem() == null) {
         this.setDead();
      } else {
         super.onUpdate();
         if(this.delayBeforeCanPickup > 0) {
            --this.delayBeforeCanPickup;
         }

         super.prevPosX = super.posX;
         super.prevPosY = super.posY;
         super.prevPosZ = super.posZ;
         super.motionY -= 0.03999999910593033D;
         super.noClip = this.func_145771_j(super.posX, (super.boundingBox.minY + super.boundingBox.maxY) / 2.0D, super.posZ);
         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         boolean var1 = (int)super.prevPosX != (int)super.posX || (int)super.prevPosY != (int)super.posY || (int)super.prevPosZ != (int)super.posZ;
         if(var1 || super.ticksExisted % 25 == 0) {
            if(super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ)).getMaterial() == Material.lava) {
               super.motionY = 0.20000000298023224D;
               super.motionX = (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F);
               super.motionZ = (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F);
               this.playSound("random.fizz", 0.4F, 2.0F + super.rand.nextFloat() * 0.4F);
            }

            if(!super.worldObj.isRemote) {
               this.searchForOtherItemsNearby();
            }
         }

         float var2 = 0.98F;
         if(super.onGround) {
            var2 = super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.boundingBox.minY) - 1, MathHelper.floor_double(super.posZ)).slipperiness * 0.98F;
         }

         super.motionX *= (double)var2;
         super.motionY *= 0.9800000190734863D;
         super.motionZ *= (double)var2;
         if(super.onGround) {
            super.motionY *= -0.5D;
         }

         ++this.age;
         if(!super.worldObj.isRemote && this.age >= 6000) {
            this.setDead();
         }

      }
   }

   private void searchForOtherItemsNearby() {
      Iterator var1 = super.worldObj.getEntitiesWithinAABB(EntityItem.class, super.boundingBox.expand(0.5D, 0.0D, 0.5D)).iterator();

      while(var1.hasNext()) {
         EntityItem var2 = (EntityItem)var1.next();
         this.combineItems(var2);
      }

   }

   public boolean combineItems(EntityItem var1) {
      if(var1 == this) {
         return false;
      } else if(var1.isEntityAlive() && this.isEntityAlive()) {
         ItemStack var2 = this.getEntityItem();
         ItemStack var3 = var1.getEntityItem();
         if(var3.getItem() != var2.getItem()) {
            return false;
         } else if(var3.hasTagCompound() ^ var2.hasTagCompound()) {
            return false;
         } else if(var3.hasTagCompound() && !var3.getTagCompound().equals(var2.getTagCompound())) {
            return false;
         } else if(var3.getItem() == null) {
            return false;
         } else if(var3.getItem().getHasSubtypes() && var3.getItemDamage() != var2.getItemDamage()) {
            return false;
         } else if(var3.stackSize < var2.stackSize) {
            return var1.combineItems(this);
         } else if(var3.stackSize + var2.stackSize > var3.getMaxStackSize()) {
            return false;
         } else {
            var3.stackSize += var2.stackSize;
            var1.delayBeforeCanPickup = Math.max(var1.delayBeforeCanPickup, this.delayBeforeCanPickup);
            var1.age = Math.min(var1.age, this.age);
            var1.setEntityItemStack(var3);
            this.setDead();
            return true;
         }
      } else {
         return false;
      }
   }

   public void setAgeToCreativeDespawnTime() {
      this.age = 4800;
   }

   public boolean handleWaterMovement() {
      return super.worldObj.handleMaterialAcceleration(super.boundingBox, Material.water, this);
   }

   protected void dealFireDamage(int var1) {
      this.attackEntityFrom(DamageSource.inFire, (float)var1);
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else if(this.getEntityItem() != null && this.getEntityItem().getItem() == Items.nether_star && var1.isExplosion()) {
         return false;
      } else {
         this.setBeenAttacked();
         this.health = (int)((float)this.health - var2);
         if(this.health <= 0) {
            this.setDead();
         }

         return false;
      }
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      var1.setShort("Health", (short)((byte)this.health));
      var1.setShort("Age", (short)this.age);
      if(this.func_145800_j() != null) {
         var1.setString("Thrower", this.field_145801_f);
      }

      if(this.func_145798_i() != null) {
         var1.setString("Owner", this.field_145802_g);
      }

      if(this.getEntityItem() != null) {
         var1.setTag("Item", this.getEntityItem().writeToNBT(new NBTTagCompound()));
      }

   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      this.health = var1.getShort("Health") & 255;
      this.age = var1.getShort("Age");
      if(var1.hasKey("Owner")) {
         this.field_145802_g = var1.getString("Owner");
      }

      if(var1.hasKey("Thrower")) {
         this.field_145801_f = var1.getString("Thrower");
      }

      NBTTagCompound var2 = var1.getCompoundTag("Item");
      this.setEntityItemStack(ItemStack.loadItemStackFromNBT(var2));
      if(this.getEntityItem() == null) {
         this.setDead();
      }

   }

   public void onCollideWithPlayer(EntityPlayer var1) {
      if(!super.worldObj.isRemote) {
         ItemStack var2 = this.getEntityItem();
         int var3 = var2.stackSize;
         if(this.delayBeforeCanPickup == 0 && (this.field_145802_g == null || 6000 - this.age <= 200 || this.field_145802_g.equals(var1.getCommandSenderName())) && var1.inventory.addItemStackToInventory(var2)) {
            if(var2.getItem() == Item.getItemFromBlock(Blocks.log)) {
               var1.triggerAchievement(AchievementList.mineWood);
            }

            if(var2.getItem() == Item.getItemFromBlock(Blocks.log2)) {
               var1.triggerAchievement(AchievementList.mineWood);
            }

            if(var2.getItem() == Items.leather) {
               var1.triggerAchievement(AchievementList.killCow);
            }

            if(var2.getItem() == Items.diamond) {
               var1.triggerAchievement(AchievementList.diamonds);
            }

            if(var2.getItem() == Items.blaze_rod) {
               var1.triggerAchievement(AchievementList.blazeRod);
            }

            if(var2.getItem() == Items.diamond && this.func_145800_j() != null) {
               EntityPlayer var4 = super.worldObj.getPlayerEntityByName(this.func_145800_j());
               if(var4 != null && var4 != var1) {
                  var4.triggerAchievement(AchievementList.field_150966_x);
               }
            }

            super.worldObj.playSoundAtEntity(var1, "random.pop", 0.2F, ((super.rand.nextFloat() - super.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            var1.onItemPickup(this, var3);
            if(var2.stackSize <= 0) {
               this.setDead();
            }
         }

      }
   }

   public String getCommandSenderName() {
      return StatCollector.translateToLocal("item." + this.getEntityItem().getUnlocalizedName());
   }

   public boolean canAttackWithItem() {
      return false;
   }

   public void travelToDimension(int var1) {
      super.travelToDimension(var1);
      if(!super.worldObj.isRemote) {
         this.searchForOtherItemsNearby();
      }

   }

   public ItemStack getEntityItem() {
      ItemStack var1 = this.getDataWatcher().getWatchableObjectItemStack(10);
      return var1 == null?new ItemStack(Blocks.stone):var1;
   }

   public void setEntityItemStack(ItemStack var1) {
      this.getDataWatcher().updateObject(10, var1);
      this.getDataWatcher().setObjectWatched(10);
   }

   public String func_145798_i() {
      return this.field_145802_g;
   }

   public void func_145797_a(String var1) {
      this.field_145802_g = var1;
   }

   public String func_145800_j() {
      return this.field_145801_f;
   }

   public void func_145799_b(String var1) {
      this.field_145801_f = var1;
   }

}
