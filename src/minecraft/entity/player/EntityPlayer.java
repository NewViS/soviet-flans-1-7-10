package net.minecraft.entity.player;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList$EntityEggInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer$EnumStatus;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent$Action;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.chunk.IChunkProvider;

public abstract class EntityPlayer extends EntityLivingBase implements ICommandSender {

   public InventoryPlayer inventory = new InventoryPlayer(this);
   private InventoryEnderChest theInventoryEnderChest = new InventoryEnderChest();
   public Container inventoryContainer;
   public Container openContainer;
   protected FoodStats foodStats = new FoodStats();
   protected int flyToggleTimer;
   public float prevCameraYaw;
   public float cameraYaw;
   public int xpCooldown;
   public double field_71091_bM;
   public double field_71096_bN;
   public double field_71097_bO;
   public double field_71094_bP;
   public double field_71095_bQ;
   public double field_71085_bR;
   protected boolean sleeping;
   public ChunkCoordinates playerLocation;
   private int sleepTimer;
   public float field_71079_bU;
   public float field_71082_cx;
   public float field_71089_bV;
   private ChunkCoordinates spawnChunk;
   private boolean spawnForced;
   private ChunkCoordinates startMinecartRidingCoordinate;
   public PlayerCapabilities capabilities = new PlayerCapabilities();
   public int experienceLevel;
   public int experienceTotal;
   public float experience;
   private ItemStack itemInUse;
   private int itemInUseCount;
   protected float speedOnGround = 0.1F;
   protected float speedInAir = 0.02F;
   private int field_82249_h;
   private final GameProfile field_146106_i;
   public EntityFishHook fishEntity;


   public EntityPlayer(World var1, GameProfile var2) {
      super(var1);
      super.entityUniqueID = func_146094_a(var2);
      this.field_146106_i = var2;
      this.inventoryContainer = new ContainerPlayer(this.inventory, !var1.isRemote, this);
      this.openContainer = this.inventoryContainer;
      super.yOffset = 1.62F;
      ChunkCoordinates var3 = var1.getSpawnPoint();
      this.setLocationAndAngles((double)var3.posX + 0.5D, (double)(var3.posY + 1), (double)var3.posZ + 0.5D, 0.0F, 0.0F);
      super.field_70741_aB = 180.0F;
      super.fireResistance = 20;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, Byte.valueOf((byte)0));
      super.dataWatcher.addObject(17, Float.valueOf(0.0F));
      super.dataWatcher.addObject(18, Integer.valueOf(0));
   }

   public ItemStack getItemInUse() {
      return this.itemInUse;
   }

   public int getItemInUseCount() {
      return this.itemInUseCount;
   }

   public boolean isUsingItem() {
      return this.itemInUse != null;
   }

   public int getItemInUseDuration() {
      return this.isUsingItem()?this.itemInUse.getMaxItemUseDuration() - this.itemInUseCount:0;
   }

   public void stopUsingItem() {
      if(this.itemInUse != null) {
         this.itemInUse.onPlayerStoppedUsing(super.worldObj, this, this.itemInUseCount);
      }

      this.clearItemInUse();
   }

   public void clearItemInUse() {
      this.itemInUse = null;
      this.itemInUseCount = 0;
      if(!super.worldObj.isRemote) {
         this.setEating(false);
      }

   }

   public boolean isBlocking() {
      return this.isUsingItem() && this.itemInUse.getItem().getItemUseAction(this.itemInUse) == EnumAction.block;
   }

   public void onUpdate() {
      if(this.itemInUse != null) {
         ItemStack var1 = this.inventory.getCurrentItem();
         if(var1 == this.itemInUse) {
            if(this.itemInUseCount <= 25 && this.itemInUseCount % 4 == 0) {
               this.updateItemUse(var1, 5);
            }

            if(--this.itemInUseCount == 0 && !super.worldObj.isRemote) {
               this.onItemUseFinish();
            }
         } else {
            this.clearItemInUse();
         }
      }

      if(this.xpCooldown > 0) {
         --this.xpCooldown;
      }

      if(this.isPlayerSleeping()) {
         ++this.sleepTimer;
         if(this.sleepTimer > 100) {
            this.sleepTimer = 100;
         }

         if(!super.worldObj.isRemote) {
            if(!this.isInBed()) {
               this.wakeUpPlayer(true, true, false);
            } else if(super.worldObj.isDaytime()) {
               this.wakeUpPlayer(false, true, true);
            }
         }
      } else if(this.sleepTimer > 0) {
         ++this.sleepTimer;
         if(this.sleepTimer >= 110) {
            this.sleepTimer = 0;
         }
      }

      super.onUpdate();
      if(!super.worldObj.isRemote && this.openContainer != null && !this.openContainer.canInteractWith(this)) {
         this.closeScreen();
         this.openContainer = this.inventoryContainer;
      }

      if(this.isBurning() && this.capabilities.disableDamage) {
         this.extinguish();
      }

      this.field_71091_bM = this.field_71094_bP;
      this.field_71096_bN = this.field_71095_bQ;
      this.field_71097_bO = this.field_71085_bR;
      double var9 = super.posX - this.field_71094_bP;
      double var3 = super.posY - this.field_71095_bQ;
      double var5 = super.posZ - this.field_71085_bR;
      double var7 = 10.0D;
      if(var9 > var7) {
         this.field_71091_bM = this.field_71094_bP = super.posX;
      }

      if(var5 > var7) {
         this.field_71097_bO = this.field_71085_bR = super.posZ;
      }

      if(var3 > var7) {
         this.field_71096_bN = this.field_71095_bQ = super.posY;
      }

      if(var9 < -var7) {
         this.field_71091_bM = this.field_71094_bP = super.posX;
      }

      if(var5 < -var7) {
         this.field_71097_bO = this.field_71085_bR = super.posZ;
      }

      if(var3 < -var7) {
         this.field_71096_bN = this.field_71095_bQ = super.posY;
      }

      this.field_71094_bP += var9 * 0.25D;
      this.field_71085_bR += var5 * 0.25D;
      this.field_71095_bQ += var3 * 0.25D;
      if(super.ridingEntity == null) {
         this.startMinecartRidingCoordinate = null;
      }

      if(!super.worldObj.isRemote) {
         this.foodStats.onUpdate(this);
         this.addStat(StatList.minutesPlayedStat, 1);
      }

   }

   public int getMaxInPortalTime() {
      return this.capabilities.disableDamage?0:80;
   }

   protected String getSwimSound() {
      return "game.player.swim";
   }

   protected String getSplashSound() {
      return "game.player.swim.splash";
   }

   public int getPortalCooldown() {
      return 10;
   }

   public void playSound(String var1, float var2, float var3) {
      super.worldObj.playSoundToNearExcept(this, var1, var2, var3);
   }

   protected void updateItemUse(ItemStack var1, int var2) {
      if(var1.getItemUseAction() == EnumAction.drink) {
         this.playSound("random.drink", 0.5F, super.worldObj.rand.nextFloat() * 0.1F + 0.9F);
      }

      if(var1.getItemUseAction() == EnumAction.eat) {
         for(int var3 = 0; var3 < var2; ++var3) {
            Vec3 var4 = Vec3.createVectorHelper(((double)super.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
            var4.rotateAroundX(-super.rotationPitch * 3.1415927F / 180.0F);
            var4.rotateAroundY(-super.rotationYaw * 3.1415927F / 180.0F);
            Vec3 var5 = Vec3.createVectorHelper(((double)super.rand.nextFloat() - 0.5D) * 0.3D, (double)(-super.rand.nextFloat()) * 0.6D - 0.3D, 0.6D);
            var5.rotateAroundX(-super.rotationPitch * 3.1415927F / 180.0F);
            var5.rotateAroundY(-super.rotationYaw * 3.1415927F / 180.0F);
            var5 = var5.addVector(super.posX, super.posY + (double)this.getEyeHeight(), super.posZ);
            String var6 = "iconcrack_" + Item.getIdFromItem(var1.getItem());
            if(var1.getHasSubtypes()) {
               var6 = var6 + "_" + var1.getItemDamage();
            }

            super.worldObj.spawnParticle(var6, var5.xCoord, var5.yCoord, var5.zCoord, var4.xCoord, var4.yCoord + 0.05D, var4.zCoord);
         }

         this.playSound("random.eat", 0.5F + 0.5F * (float)super.rand.nextInt(2), (super.rand.nextFloat() - super.rand.nextFloat()) * 0.2F + 1.0F);
      }

   }

   protected void onItemUseFinish() {
      if(this.itemInUse != null) {
         this.updateItemUse(this.itemInUse, 16);
         int var1 = this.itemInUse.stackSize;
         ItemStack var2 = this.itemInUse.onFoodEaten(super.worldObj, this);
         if(var2 != this.itemInUse || var2 != null && var2.stackSize != var1) {
            this.inventory.mainInventory[this.inventory.currentItem] = var2;
            if(var2.stackSize == 0) {
               this.inventory.mainInventory[this.inventory.currentItem] = null;
            }
         }

         this.clearItemInUse();
      }

   }

   public void handleHealthUpdate(byte var1) {
      if(var1 == 9) {
         this.onItemUseFinish();
      } else {
         super.handleHealthUpdate(var1);
      }

   }

   protected boolean isMovementBlocked() {
      return this.getHealth() <= 0.0F || this.isPlayerSleeping();
   }

   protected void closeScreen() {
      this.openContainer = this.inventoryContainer;
   }

   public void mountEntity(Entity var1) {
      if(super.ridingEntity != null && var1 == null) {
         if(!super.worldObj.isRemote) {
            this.dismountEntity(super.ridingEntity);
         }

         if(super.ridingEntity != null) {
            super.ridingEntity.riddenByEntity = null;
         }

         super.ridingEntity = null;
      } else {
         super.mountEntity(var1);
      }
   }

   public void updateRidden() {
      if(!super.worldObj.isRemote && this.isSneaking()) {
         this.mountEntity((Entity)null);
         this.setSneaking(false);
      } else {
         double var1 = super.posX;
         double var3 = super.posY;
         double var5 = super.posZ;
         float var7 = super.rotationYaw;
         float var8 = super.rotationPitch;
         super.updateRidden();
         this.prevCameraYaw = this.cameraYaw;
         this.cameraYaw = 0.0F;
         this.addMountedMovementStat(super.posX - var1, super.posY - var3, super.posZ - var5);
         if(super.ridingEntity instanceof EntityPig) {
            super.rotationPitch = var8;
            super.rotationYaw = var7;
            super.renderYawOffset = ((EntityPig)super.ridingEntity).renderYawOffset;
         }

      }
   }

   public void preparePlayerToSpawn() {
      super.yOffset = 1.62F;
      this.setSize(0.6F, 1.8F);
      super.preparePlayerToSpawn();
      this.setHealth(this.getMaxHealth());
      super.deathTime = 0;
   }

   protected void updateEntityActionState() {
      super.updateEntityActionState();
      this.updateArmSwingProgress();
   }

   public void onLivingUpdate() {
      if(this.flyToggleTimer > 0) {
         --this.flyToggleTimer;
      }

      if(super.worldObj.difficultySetting == EnumDifficulty.PEACEFUL && this.getHealth() < this.getMaxHealth() && super.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && super.ticksExisted % 20 * 12 == 0) {
         this.heal(1.0F);
      }

      this.inventory.decrementAnimations();
      this.prevCameraYaw = this.cameraYaw;
      super.onLivingUpdate();
      IAttributeInstance var1 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
      if(!super.worldObj.isRemote) {
         var1.setBaseValue((double)this.capabilities.getWalkSpeed());
      }

      super.jumpMovementFactor = this.speedInAir;
      if(this.isSprinting()) {
         super.jumpMovementFactor = (float)((double)super.jumpMovementFactor + (double)this.speedInAir * 0.3D);
      }

      this.setAIMoveSpeed((float)var1.getAttributeValue());
      float var2 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
      float var3 = (float)Math.atan(-super.motionY * 0.20000000298023224D) * 15.0F;
      if(var2 > 0.1F) {
         var2 = 0.1F;
      }

      if(!super.onGround || this.getHealth() <= 0.0F) {
         var2 = 0.0F;
      }

      if(super.onGround || this.getHealth() <= 0.0F) {
         var3 = 0.0F;
      }

      this.cameraYaw += (var2 - this.cameraYaw) * 0.4F;
      super.cameraPitch += (var3 - super.cameraPitch) * 0.8F;
      if(this.getHealth() > 0.0F) {
         AxisAlignedBB var4 = null;
         if(super.ridingEntity != null && !super.ridingEntity.isDead) {
            var4 = super.boundingBox.func_111270_a(super.ridingEntity.boundingBox).expand(1.0D, 0.0D, 1.0D);
         } else {
            var4 = super.boundingBox.expand(1.0D, 0.5D, 1.0D);
         }

         List var5 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, var4);
         if(var5 != null) {
            for(int var6 = 0; var6 < var5.size(); ++var6) {
               Entity var7 = (Entity)var5.get(var6);
               if(!var7.isDead) {
                  this.collideWithPlayer(var7);
               }
            }
         }
      }

   }

   private void collideWithPlayer(Entity var1) {
      var1.onCollideWithPlayer(this);
   }

   public int getScore() {
      return super.dataWatcher.getWatchableObjectInt(18);
   }

   public void setScore(int var1) {
      super.dataWatcher.updateObject(18, Integer.valueOf(var1));
   }

   public void addScore(int var1) {
      int var2 = this.getScore();
      super.dataWatcher.updateObject(18, Integer.valueOf(var2 + var1));
   }

   public void onDeath(DamageSource var1) {
      super.onDeath(var1);
      this.setSize(0.2F, 0.2F);
      this.setPosition(super.posX, super.posY, super.posZ);
      super.motionY = 0.10000000149011612D;
      if(this.getCommandSenderName().equals("Notch")) {
         this.func_146097_a(new ItemStack(Items.apple, 1), true, false);
      }

      if(!super.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
         this.inventory.dropAllItems();
      }

      if(var1 != null) {
         super.motionX = (double)(-MathHelper.cos((super.attackedAtYaw + super.rotationYaw) * 3.1415927F / 180.0F) * 0.1F);
         super.motionZ = (double)(-MathHelper.sin((super.attackedAtYaw + super.rotationYaw) * 3.1415927F / 180.0F) * 0.1F);
      } else {
         super.motionX = super.motionZ = 0.0D;
      }

      super.yOffset = 0.1F;
      this.addStat(StatList.deathsStat, 1);
   }

   protected String getHurtSound() {
      return "game.player.hurt";
   }

   protected String getDeathSound() {
      return "game.player.die";
   }

   public void addToPlayerScore(Entity var1, int var2) {
      this.addScore(var2);
      Collection var3 = this.getWorldScoreboard().func_96520_a(IScoreObjectiveCriteria.totalKillCount);
      if(var1 instanceof EntityPlayer) {
         this.addStat(StatList.playerKillsStat, 1);
         var3.addAll(this.getWorldScoreboard().func_96520_a(IScoreObjectiveCriteria.playerKillCount));
      } else {
         this.addStat(StatList.mobKillsStat, 1);
      }

      Iterator var4 = var3.iterator();

      while(var4.hasNext()) {
         ScoreObjective var5 = (ScoreObjective)var4.next();
         Score var6 = this.getWorldScoreboard().func_96529_a(this.getCommandSenderName(), var5);
         var6.func_96648_a();
      }

   }

   public EntityItem dropOneItem(boolean var1) {
      return this.func_146097_a(this.inventory.decrStackSize(this.inventory.currentItem, var1 && this.inventory.getCurrentItem() != null?this.inventory.getCurrentItem().stackSize:1), false, true);
   }

   public EntityItem dropPlayerItemWithRandomChoice(ItemStack var1, boolean var2) {
      return this.func_146097_a(var1, false, false);
   }

   public EntityItem func_146097_a(ItemStack var1, boolean var2, boolean var3) {
      if(var1 == null) {
         return null;
      } else if(var1.stackSize == 0) {
         return null;
      } else {
         EntityItem var4 = new EntityItem(super.worldObj, super.posX, super.posY - 0.30000001192092896D + (double)this.getEyeHeight(), super.posZ, var1);
         var4.delayBeforeCanPickup = 40;
         if(var3) {
            var4.func_145799_b(this.getCommandSenderName());
         }

         float var5 = 0.1F;
         float var6;
         if(var2) {
            var6 = super.rand.nextFloat() * 0.5F;
            float var7 = super.rand.nextFloat() * 3.1415927F * 2.0F;
            var4.motionX = (double)(-MathHelper.sin(var7) * var6);
            var4.motionZ = (double)(MathHelper.cos(var7) * var6);
            var4.motionY = 0.20000000298023224D;
         } else {
            var5 = 0.3F;
            var4.motionX = (double)(-MathHelper.sin(super.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(super.rotationPitch / 180.0F * 3.1415927F) * var5);
            var4.motionZ = (double)(MathHelper.cos(super.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(super.rotationPitch / 180.0F * 3.1415927F) * var5);
            var4.motionY = (double)(-MathHelper.sin(super.rotationPitch / 180.0F * 3.1415927F) * var5 + 0.1F);
            var5 = 0.02F;
            var6 = super.rand.nextFloat() * 3.1415927F * 2.0F;
            var5 *= super.rand.nextFloat();
            var4.motionX += Math.cos((double)var6) * (double)var5;
            var4.motionY += (double)((super.rand.nextFloat() - super.rand.nextFloat()) * 0.1F);
            var4.motionZ += Math.sin((double)var6) * (double)var5;
         }

         this.joinEntityItemWithWorld(var4);
         this.addStat(StatList.dropStat, 1);
         return var4;
      }
   }

   protected void joinEntityItemWithWorld(EntityItem var1) {
      super.worldObj.spawnEntityInWorld(var1);
   }

   public float getCurrentPlayerStrVsBlock(Block var1, boolean var2) {
      float var3 = this.inventory.func_146023_a(var1);
      if(var3 > 1.0F) {
         int var4 = EnchantmentHelper.getEfficiencyModifier(this);
         ItemStack var5 = this.inventory.getCurrentItem();
         if(var4 > 0 && var5 != null) {
            float var6 = (float)(var4 * var4 + 1);
            if(!var5.func_150998_b(var1) && var3 <= 1.0F) {
               var3 += var6 * 0.08F;
            } else {
               var3 += var6;
            }
         }
      }

      if(this.isPotionActive(Potion.digSpeed)) {
         var3 *= 1.0F + (float)(this.getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2F;
      }

      if(this.isPotionActive(Potion.digSlowdown)) {
         var3 *= 1.0F - (float)(this.getActivePotionEffect(Potion.digSlowdown).getAmplifier() + 1) * 0.2F;
      }

      if(this.isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(this)) {
         var3 /= 5.0F;
      }

      if(!super.onGround) {
         var3 /= 5.0F;
      }

      return var3;
   }

   public boolean canHarvestBlock(Block var1) {
      return this.inventory.func_146025_b(var1);
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      super.entityUniqueID = func_146094_a(this.field_146106_i);
      NBTTagList var2 = var1.getTagList("Inventory", 10);
      this.inventory.readFromNBT(var2);
      this.inventory.currentItem = var1.getInteger("SelectedItemSlot");
      this.sleeping = var1.getBoolean("Sleeping");
      this.sleepTimer = var1.getShort("SleepTimer");
      this.experience = var1.getFloat("XpP");
      this.experienceLevel = var1.getInteger("XpLevel");
      this.experienceTotal = var1.getInteger("XpTotal");
      this.setScore(var1.getInteger("Score"));
      if(this.sleeping) {
         this.playerLocation = new ChunkCoordinates(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ));
         this.wakeUpPlayer(true, true, false);
      }

      if(var1.hasKey("SpawnX", 99) && var1.hasKey("SpawnY", 99) && var1.hasKey("SpawnZ", 99)) {
         this.spawnChunk = new ChunkCoordinates(var1.getInteger("SpawnX"), var1.getInteger("SpawnY"), var1.getInteger("SpawnZ"));
         this.spawnForced = var1.getBoolean("SpawnForced");
      }

      this.foodStats.readNBT(var1);
      this.capabilities.readCapabilitiesFromNBT(var1);
      if(var1.hasKey("EnderItems", 9)) {
         NBTTagList var3 = var1.getTagList("EnderItems", 10);
         this.theInventoryEnderChest.loadInventoryFromNBT(var3);
      }

   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setTag("Inventory", this.inventory.writeToNBT(new NBTTagList()));
      var1.setInteger("SelectedItemSlot", this.inventory.currentItem);
      var1.setBoolean("Sleeping", this.sleeping);
      var1.setShort("SleepTimer", (short)this.sleepTimer);
      var1.setFloat("XpP", this.experience);
      var1.setInteger("XpLevel", this.experienceLevel);
      var1.setInteger("XpTotal", this.experienceTotal);
      var1.setInteger("Score", this.getScore());
      if(this.spawnChunk != null) {
         var1.setInteger("SpawnX", this.spawnChunk.posX);
         var1.setInteger("SpawnY", this.spawnChunk.posY);
         var1.setInteger("SpawnZ", this.spawnChunk.posZ);
         var1.setBoolean("SpawnForced", this.spawnForced);
      }

      this.foodStats.writeNBT(var1);
      this.capabilities.writeCapabilitiesToNBT(var1);
      var1.setTag("EnderItems", this.theInventoryEnderChest.saveInventoryToNBT());
   }

   public void displayGUIChest(IInventory var1) {}

   public void func_146093_a(TileEntityHopper var1) {}

   public void displayGUIHopperMinecart(EntityMinecartHopper var1) {}

   public void displayGUIHorse(EntityHorse var1, IInventory var2) {}

   public void displayGUIEnchantment(int var1, int var2, int var3, String var4) {}

   public void displayGUIAnvil(int var1, int var2, int var3) {}

   public void displayGUIWorkbench(int var1, int var2, int var3) {}

   public float getEyeHeight() {
      return 0.12F;
   }

   protected void resetHeight() {
      super.yOffset = 1.62F;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else if(this.capabilities.disableDamage && !var1.canHarmInCreative()) {
         return false;
      } else {
         super.entityAge = 0;
         if(this.getHealth() <= 0.0F) {
            return false;
         } else {
            if(this.isPlayerSleeping() && !super.worldObj.isRemote) {
               this.wakeUpPlayer(true, true, false);
            }

            if(var1.isDifficultyScaled()) {
               if(super.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
                  var2 = 0.0F;
               }

               if(super.worldObj.difficultySetting == EnumDifficulty.EASY) {
                  var2 = var2 / 2.0F + 1.0F;
               }

               if(super.worldObj.difficultySetting == EnumDifficulty.HARD) {
                  var2 = var2 * 3.0F / 2.0F;
               }
            }

            if(var2 == 0.0F) {
               return false;
            } else {
               Entity var3 = var1.getEntity();
               if(var3 instanceof EntityArrow && ((EntityArrow)var3).shootingEntity != null) {
                  var3 = ((EntityArrow)var3).shootingEntity;
               }

               this.addStat(StatList.damageTakenStat, Math.round(var2 * 10.0F));
               return super.attackEntityFrom(var1, var2);
            }
         }
      }
   }

   public boolean canAttackPlayer(EntityPlayer var1) {
      Team var2 = this.getTeam();
      Team var3 = var1.getTeam();
      return var2 == null?true:(!var2.isSameTeam(var3)?true:var2.getAllowFriendlyFire());
   }

   protected void damageArmor(float var1) {
      this.inventory.damageArmor(var1);
   }

   public int getTotalArmorValue() {
      return this.inventory.getTotalArmorValue();
   }

   public float getArmorVisibility() {
      int var1 = 0;
      ItemStack[] var2 = this.inventory.armorInventory;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ItemStack var5 = var2[var4];
         if(var5 != null) {
            ++var1;
         }
      }

      return (float)var1 / (float)this.inventory.armorInventory.length;
   }

   protected void damageEntity(DamageSource var1, float var2) {
      if(!this.isEntityInvulnerable()) {
         if(!var1.isUnblockable() && this.isBlocking() && var2 > 0.0F) {
            var2 = (1.0F + var2) * 0.5F;
         }

         var2 = this.applyArmorCalculations(var1, var2);
         var2 = this.applyPotionDamageCalculations(var1, var2);
         float var3 = var2;
         var2 = Math.max(var2 - this.getAbsorptionAmount(), 0.0F);
         this.setAbsorptionAmount(this.getAbsorptionAmount() - (var3 - var2));
         if(var2 != 0.0F) {
            this.addExhaustion(var1.getHungerDamage());
            float var4 = this.getHealth();
            this.setHealth(this.getHealth() - var2);
            this.func_110142_aN().func_94547_a(var1, var4, var2);
         }
      }
   }

   public void func_146101_a(TileEntityFurnace var1) {}

   public void func_146102_a(TileEntityDispenser var1) {}

   public void func_146100_a(TileEntity var1) {}

   public void func_146095_a(CommandBlockLogic var1) {}

   public void func_146098_a(TileEntityBrewingStand var1) {}

   public void func_146104_a(TileEntityBeacon var1) {}

   public void displayGUIMerchant(IMerchant var1, String var2) {}

   public void displayGUIBook(ItemStack var1) {}

   public boolean interactWith(Entity var1) {
      ItemStack var2 = this.getCurrentEquippedItem();
      ItemStack var3 = var2 != null?var2.copy():null;
      if(!var1.interactFirst(this)) {
         if(var2 != null && var1 instanceof EntityLivingBase) {
            if(this.capabilities.isCreativeMode) {
               var2 = var3;
            }

            if(var2.interactWithEntity(this, (EntityLivingBase)var1)) {
               if(var2.stackSize <= 0 && !this.capabilities.isCreativeMode) {
                  this.destroyCurrentEquippedItem();
               }

               return true;
            }
         }

         return false;
      } else {
         if(var2 != null && var2 == this.getCurrentEquippedItem()) {
            if(var2.stackSize <= 0 && !this.capabilities.isCreativeMode) {
               this.destroyCurrentEquippedItem();
            } else if(var2.stackSize < var3.stackSize && this.capabilities.isCreativeMode) {
               var2.stackSize = var3.stackSize;
            }
         }

         return true;
      }
   }

   public ItemStack getCurrentEquippedItem() {
      return this.inventory.getCurrentItem();
   }

   public void destroyCurrentEquippedItem() {
      this.inventory.setInventorySlotContents(this.inventory.currentItem, (ItemStack)null);
   }

   public double getYOffset() {
      return (double)(super.yOffset - 0.5F);
   }

   public void attackTargetEntityWithCurrentItem(Entity var1) {
      if(var1.canAttackWithItem()) {
         if(!var1.hitByEntity(this)) {
            float var2 = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
            int var3 = 0;
            float var4 = 0.0F;
            if(var1 instanceof EntityLivingBase) {
               var4 = EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)var1);
               var3 += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)var1);
            }

            if(this.isSprinting()) {
               ++var3;
            }

            if(var2 > 0.0F || var4 > 0.0F) {
               boolean var5 = super.fallDistance > 0.0F && !super.onGround && !this.isOnLadder() && !this.isInWater() && !this.isPotionActive(Potion.blindness) && super.ridingEntity == null && var1 instanceof EntityLivingBase;
               if(var5 && var2 > 0.0F) {
                  var2 *= 1.5F;
               }

               var2 += var4;
               boolean var6 = false;
               int var7 = EnchantmentHelper.getFireAspectModifier(this);
               if(var1 instanceof EntityLivingBase && var7 > 0 && !var1.isBurning()) {
                  var6 = true;
                  var1.setFire(1);
               }

               boolean var8 = var1.attackEntityFrom(DamageSource.causePlayerDamage(this), var2);
               if(var8) {
                  if(var3 > 0) {
                     var1.addVelocity((double)(-MathHelper.sin(super.rotationYaw * 3.1415927F / 180.0F) * (float)var3 * 0.5F), 0.1D, (double)(MathHelper.cos(super.rotationYaw * 3.1415927F / 180.0F) * (float)var3 * 0.5F));
                     super.motionX *= 0.6D;
                     super.motionZ *= 0.6D;
                     this.setSprinting(false);
                  }

                  if(var5) {
                     this.onCriticalHit(var1);
                  }

                  if(var4 > 0.0F) {
                     this.onEnchantmentCritical(var1);
                  }

                  if(var2 >= 18.0F) {
                     this.triggerAchievement(AchievementList.overkill);
                  }

                  this.setLastAttacker(var1);
                  if(var1 instanceof EntityLivingBase) {
                     EnchantmentHelper.func_151384_a((EntityLivingBase)var1, this);
                  }

                  EnchantmentHelper.func_151385_b(this, var1);
                  ItemStack var9 = this.getCurrentEquippedItem();
                  Object var10 = var1;
                  if(var1 instanceof EntityDragonPart) {
                     IEntityMultiPart var11 = ((EntityDragonPart)var1).entityDragonObj;
                     if(var11 != null && var11 instanceof EntityLivingBase) {
                        var10 = (EntityLivingBase)var11;
                     }
                  }

                  if(var9 != null && var10 instanceof EntityLivingBase) {
                     var9.hitEntity((EntityLivingBase)var10, this);
                     if(var9.stackSize <= 0) {
                        this.destroyCurrentEquippedItem();
                     }
                  }

                  if(var1 instanceof EntityLivingBase) {
                     this.addStat(StatList.damageDealtStat, Math.round(var2 * 10.0F));
                     if(var7 > 0) {
                        var1.setFire(var7 * 4);
                     }
                  }

                  this.addExhaustion(0.3F);
               } else if(var6) {
                  var1.extinguish();
               }
            }

         }
      }
   }

   public void onCriticalHit(Entity var1) {}

   public void onEnchantmentCritical(Entity var1) {}

   public void respawnPlayer() {}

   public void setDead() {
      super.setDead();
      this.inventoryContainer.onContainerClosed(this);
      if(this.openContainer != null) {
         this.openContainer.onContainerClosed(this);
      }

   }

   public boolean isEntityInsideOpaqueBlock() {
      return !this.sleeping && super.isEntityInsideOpaqueBlock();
   }

   public GameProfile getGameProfile() {
      return this.field_146106_i;
   }

   public EntityPlayer$EnumStatus sleepInBedAt(int var1, int var2, int var3) {
      if(!super.worldObj.isRemote) {
         if(this.isPlayerSleeping() || !this.isEntityAlive()) {
            return EntityPlayer$EnumStatus.OTHER_PROBLEM;
         }

         if(!super.worldObj.provider.isSurfaceWorld()) {
            return EntityPlayer$EnumStatus.NOT_POSSIBLE_HERE;
         }

         if(super.worldObj.isDaytime()) {
            return EntityPlayer$EnumStatus.NOT_POSSIBLE_NOW;
         }

         if(Math.abs(super.posX - (double)var1) > 3.0D || Math.abs(super.posY - (double)var2) > 2.0D || Math.abs(super.posZ - (double)var3) > 3.0D) {
            return EntityPlayer$EnumStatus.TOO_FAR_AWAY;
         }

         double var4 = 8.0D;
         double var6 = 5.0D;
         List var8 = super.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox((double)var1 - var4, (double)var2 - var6, (double)var3 - var4, (double)var1 + var4, (double)var2 + var6, (double)var3 + var4));
         if(!var8.isEmpty()) {
            return EntityPlayer$EnumStatus.NOT_SAFE;
         }
      }

      if(this.isRiding()) {
         this.mountEntity((Entity)null);
      }

      this.setSize(0.2F, 0.2F);
      super.yOffset = 0.2F;
      if(super.worldObj.blockExists(var1, var2, var3)) {
         int var9 = super.worldObj.getBlockMetadata(var1, var2, var3);
         int var5 = BlockBed.getDirection(var9);
         float var10 = 0.5F;
         float var7 = 0.5F;
         switch(var5) {
         case 0:
            var7 = 0.9F;
            break;
         case 1:
            var10 = 0.1F;
            break;
         case 2:
            var7 = 0.1F;
            break;
         case 3:
            var10 = 0.9F;
         }

         this.func_71013_b(var5);
         this.setPosition((double)((float)var1 + var10), (double)((float)var2 + 0.9375F), (double)((float)var3 + var7));
      } else {
         this.setPosition((double)((float)var1 + 0.5F), (double)((float)var2 + 0.9375F), (double)((float)var3 + 0.5F));
      }

      this.sleeping = true;
      this.sleepTimer = 0;
      this.playerLocation = new ChunkCoordinates(var1, var2, var3);
      super.motionX = super.motionZ = super.motionY = 0.0D;
      if(!super.worldObj.isRemote) {
         super.worldObj.updateAllPlayersSleepingFlag();
      }

      return EntityPlayer$EnumStatus.OK;
   }

   private void func_71013_b(int var1) {
      this.field_71079_bU = 0.0F;
      this.field_71089_bV = 0.0F;
      switch(var1) {
      case 0:
         this.field_71089_bV = -1.8F;
         break;
      case 1:
         this.field_71079_bU = 1.8F;
         break;
      case 2:
         this.field_71089_bV = 1.8F;
         break;
      case 3:
         this.field_71079_bU = -1.8F;
      }

   }

   public void wakeUpPlayer(boolean var1, boolean var2, boolean var3) {
      this.setSize(0.6F, 1.8F);
      this.resetHeight();
      ChunkCoordinates var4 = this.playerLocation;
      ChunkCoordinates var5 = this.playerLocation;
      if(var4 != null && super.worldObj.getBlock(var4.posX, var4.posY, var4.posZ) == Blocks.bed) {
         BlockBed.func_149979_a(super.worldObj, var4.posX, var4.posY, var4.posZ, false);
         var5 = BlockBed.func_149977_a(super.worldObj, var4.posX, var4.posY, var4.posZ, 0);
         if(var5 == null) {
            var5 = new ChunkCoordinates(var4.posX, var4.posY + 1, var4.posZ);
         }

         this.setPosition((double)((float)var5.posX + 0.5F), (double)((float)var5.posY + super.yOffset + 0.1F), (double)((float)var5.posZ + 0.5F));
      }

      this.sleeping = false;
      if(!super.worldObj.isRemote && var2) {
         super.worldObj.updateAllPlayersSleepingFlag();
      }

      if(var1) {
         this.sleepTimer = 0;
      } else {
         this.sleepTimer = 100;
      }

      if(var3) {
         this.setSpawnChunk(this.playerLocation, false);
      }

   }

   private boolean isInBed() {
      return super.worldObj.getBlock(this.playerLocation.posX, this.playerLocation.posY, this.playerLocation.posZ) == Blocks.bed;
   }

   public static ChunkCoordinates verifyRespawnCoordinates(World var0, ChunkCoordinates var1, boolean var2) {
      IChunkProvider var3 = var0.getChunkProvider();
      var3.loadChunk(var1.posX - 3 >> 4, var1.posZ - 3 >> 4);
      var3.loadChunk(var1.posX + 3 >> 4, var1.posZ - 3 >> 4);
      var3.loadChunk(var1.posX - 3 >> 4, var1.posZ + 3 >> 4);
      var3.loadChunk(var1.posX + 3 >> 4, var1.posZ + 3 >> 4);
      if(var0.getBlock(var1.posX, var1.posY, var1.posZ) == Blocks.bed) {
         ChunkCoordinates var8 = BlockBed.func_149977_a(var0, var1.posX, var1.posY, var1.posZ, 0);
         return var8;
      } else {
         Material var4 = var0.getBlock(var1.posX, var1.posY, var1.posZ).getMaterial();
         Material var5 = var0.getBlock(var1.posX, var1.posY + 1, var1.posZ).getMaterial();
         boolean var6 = !var4.isSolid() && !var4.isLiquid();
         boolean var7 = !var5.isSolid() && !var5.isLiquid();
         return var2 && var6 && var7?var1:null;
      }
   }

   public float getBedOrientationInDegrees() {
      if(this.playerLocation != null) {
         int var1 = super.worldObj.getBlockMetadata(this.playerLocation.posX, this.playerLocation.posY, this.playerLocation.posZ);
         int var2 = BlockBed.getDirection(var1);
         switch(var2) {
         case 0:
            return 90.0F;
         case 1:
            return 0.0F;
         case 2:
            return 270.0F;
         case 3:
            return 180.0F;
         }
      }

      return 0.0F;
   }

   public boolean isPlayerSleeping() {
      return this.sleeping;
   }

   public boolean isPlayerFullyAsleep() {
      return this.sleeping && this.sleepTimer >= 100;
   }

   public int getSleepTimer() {
      return this.sleepTimer;
   }

   protected boolean getHideCape(int var1) {
      return (super.dataWatcher.getWatchableObjectByte(16) & 1 << var1) != 0;
   }

   protected void setHideCape(int var1, boolean var2) {
      byte var3 = super.dataWatcher.getWatchableObjectByte(16);
      if(var2) {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var3 | 1 << var1)));
      } else {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(var3 & ~(1 << var1))));
      }

   }

   public void addChatComponentMessage(IChatComponent var1) {}

   public ChunkCoordinates getBedLocation() {
      return this.spawnChunk;
   }

   public boolean isSpawnForced() {
      return this.spawnForced;
   }

   public void setSpawnChunk(ChunkCoordinates var1, boolean var2) {
      if(var1 != null) {
         this.spawnChunk = new ChunkCoordinates(var1);
         this.spawnForced = var2;
      } else {
         this.spawnChunk = null;
         this.spawnForced = false;
      }

   }

   public void triggerAchievement(StatBase var1) {
      this.addStat(var1, 1);
   }

   public void addStat(StatBase var1, int var2) {}

   public void jump() {
      super.jump();
      this.addStat(StatList.jumpStat, 1);
      if(this.isSprinting()) {
         this.addExhaustion(0.8F);
      } else {
         this.addExhaustion(0.2F);
      }

   }

   public void moveEntityWithHeading(float var1, float var2) {
      double var3 = super.posX;
      double var5 = super.posY;
      double var7 = super.posZ;
      if(this.capabilities.isFlying && super.ridingEntity == null) {
         double var9 = super.motionY;
         float var11 = super.jumpMovementFactor;
         super.jumpMovementFactor = this.capabilities.getFlySpeed();
         super.moveEntityWithHeading(var1, var2);
         super.motionY = var9 * 0.6D;
         super.jumpMovementFactor = var11;
      } else {
         super.moveEntityWithHeading(var1, var2);
      }

      this.addMovementStat(super.posX - var3, super.posY - var5, super.posZ - var7);
   }

   public float getAIMoveSpeed() {
      return (float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
   }

   public void addMovementStat(double var1, double var3, double var5) {
      if(super.ridingEntity == null) {
         int var7;
         if(this.isInsideOfMaterial(Material.water)) {
            var7 = Math.round(MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
            if(var7 > 0) {
               this.addStat(StatList.distanceDoveStat, var7);
               this.addExhaustion(0.015F * (float)var7 * 0.01F);
            }
         } else if(this.isInWater()) {
            var7 = Math.round(MathHelper.sqrt_double(var1 * var1 + var5 * var5) * 100.0F);
            if(var7 > 0) {
               this.addStat(StatList.distanceSwumStat, var7);
               this.addExhaustion(0.015F * (float)var7 * 0.01F);
            }
         } else if(this.isOnLadder()) {
            if(var3 > 0.0D) {
               this.addStat(StatList.distanceClimbedStat, (int)Math.round(var3 * 100.0D));
            }
         } else if(super.onGround) {
            var7 = Math.round(MathHelper.sqrt_double(var1 * var1 + var5 * var5) * 100.0F);
            if(var7 > 0) {
               this.addStat(StatList.distanceWalkedStat, var7);
               if(this.isSprinting()) {
                  this.addExhaustion(0.099999994F * (float)var7 * 0.01F);
               } else {
                  this.addExhaustion(0.01F * (float)var7 * 0.01F);
               }
            }
         } else {
            var7 = Math.round(MathHelper.sqrt_double(var1 * var1 + var5 * var5) * 100.0F);
            if(var7 > 25) {
               this.addStat(StatList.distanceFlownStat, var7);
            }
         }

      }
   }

   private void addMountedMovementStat(double var1, double var3, double var5) {
      if(super.ridingEntity != null) {
         int var7 = Math.round(MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
         if(var7 > 0) {
            if(super.ridingEntity instanceof EntityMinecart) {
               this.addStat(StatList.distanceByMinecartStat, var7);
               if(this.startMinecartRidingCoordinate == null) {
                  this.startMinecartRidingCoordinate = new ChunkCoordinates(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ));
               } else if((double)this.startMinecartRidingCoordinate.getDistanceSquared(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ)) >= 1000000.0D) {
                  this.addStat(AchievementList.onARail, 1);
               }
            } else if(super.ridingEntity instanceof EntityBoat) {
               this.addStat(StatList.distanceByBoatStat, var7);
            } else if(super.ridingEntity instanceof EntityPig) {
               this.addStat(StatList.distanceByPigStat, var7);
            } else if(super.ridingEntity instanceof EntityHorse) {
               this.addStat(StatList.field_151185_q, var7);
            }
         }
      }

   }

   protected void fall(float var1) {
      if(!this.capabilities.allowFlying) {
         if(var1 >= 2.0F) {
            this.addStat(StatList.distanceFallenStat, (int)Math.round((double)var1 * 100.0D));
         }

         super.fall(var1);
      }
   }

   protected String func_146067_o(int var1) {
      return var1 > 4?"game.player.hurt.fall.big":"game.player.hurt.fall.small";
   }

   public void onKillEntity(EntityLivingBase var1) {
      if(var1 instanceof IMob) {
         this.triggerAchievement(AchievementList.killEnemy);
      }

      int var2 = EntityList.getEntityID(var1);
      EntityList$EntityEggInfo var3 = (EntityList$EntityEggInfo)EntityList.entityEggs.get(Integer.valueOf(var2));
      if(var3 != null) {
         this.addStat(var3.field_151512_d, 1);
      }

   }

   public void setInWeb() {
      if(!this.capabilities.isFlying) {
         super.setInWeb();
      }

   }

   public IIcon getItemIcon(ItemStack var1, int var2) {
      IIcon var3 = super.getItemIcon(var1, var2);
      if(var1.getItem() == Items.fishing_rod && this.fishEntity != null) {
         var3 = Items.fishing_rod.func_94597_g();
      } else {
         if(var1.getItem().requiresMultipleRenderPasses()) {
            return var1.getItem().getIconFromDamageForRenderPass(var1.getItemDamage(), var2);
         }

         if(this.itemInUse != null && var1.getItem() == Items.bow) {
            int var4 = var1.getMaxItemUseDuration() - this.itemInUseCount;
            if(var4 >= 18) {
               return Items.bow.getItemIconForUseDuration(2);
            }

            if(var4 > 13) {
               return Items.bow.getItemIconForUseDuration(1);
            }

            if(var4 > 0) {
               return Items.bow.getItemIconForUseDuration(0);
            }
         }
      }

      return var3;
   }

   public ItemStack getCurrentArmor(int var1) {
      return this.inventory.armorItemInSlot(var1);
   }

   public void addExperience(int var1) {
      this.addScore(var1);
      int var2 = Integer.MAX_VALUE - this.experienceTotal;
      if(var1 > var2) {
         var1 = var2;
      }

      this.experience += (float)var1 / (float)this.xpBarCap();

      for(this.experienceTotal += var1; this.experience >= 1.0F; this.experience /= (float)this.xpBarCap()) {
         this.experience = (this.experience - 1.0F) * (float)this.xpBarCap();
         this.addExperienceLevel(1);
      }

   }

   public void addExperienceLevel(int var1) {
      this.experienceLevel += var1;
      if(this.experienceLevel < 0) {
         this.experienceLevel = 0;
         this.experience = 0.0F;
         this.experienceTotal = 0;
      }

      if(var1 > 0 && this.experienceLevel % 5 == 0 && (float)this.field_82249_h < (float)super.ticksExisted - 100.0F) {
         float var2 = this.experienceLevel > 30?1.0F:(float)this.experienceLevel / 30.0F;
         super.worldObj.playSoundAtEntity(this, "random.levelup", var2 * 0.75F, 1.0F);
         this.field_82249_h = super.ticksExisted;
      }

   }

   public int xpBarCap() {
      return this.experienceLevel >= 30?62 + (this.experienceLevel - 30) * 7:(this.experienceLevel >= 15?17 + (this.experienceLevel - 15) * 3:17);
   }

   public void addExhaustion(float var1) {
      if(!this.capabilities.disableDamage) {
         if(!super.worldObj.isRemote) {
            this.foodStats.addExhaustion(var1);
         }

      }
   }

   public FoodStats getFoodStats() {
      return this.foodStats;
   }

   public boolean canEat(boolean var1) {
      return (var1 || this.foodStats.needFood()) && !this.capabilities.disableDamage;
   }

   public boolean shouldHeal() {
      return this.getHealth() > 0.0F && this.getHealth() < this.getMaxHealth();
   }

   public void setItemInUse(ItemStack var1, int var2) {
      if(var1 != this.itemInUse) {
         this.itemInUse = var1;
         this.itemInUseCount = var2;
         if(!super.worldObj.isRemote) {
            this.setEating(true);
         }

      }
   }

   public boolean isCurrentToolAdventureModeExempt(int var1, int var2, int var3) {
      if(this.capabilities.allowEdit) {
         return true;
      } else {
         Block var4 = super.worldObj.getBlock(var1, var2, var3);
         if(var4.getMaterial() != Material.air) {
            if(var4.getMaterial().isAdventureModeExempt()) {
               return true;
            }

            if(this.getCurrentEquippedItem() != null) {
               ItemStack var5 = this.getCurrentEquippedItem();
               if(var5.func_150998_b(var4) || var5.func_150997_a(var4) > 1.0F) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean canPlayerEdit(int var1, int var2, int var3, int var4, ItemStack var5) {
      return this.capabilities.allowEdit?true:(var5 != null?var5.canEditBlocks():false);
   }

   protected int getExperiencePoints(EntityPlayer var1) {
      if(super.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
         return 0;
      } else {
         int var2 = this.experienceLevel * 7;
         return var2 > 100?100:var2;
      }
   }

   protected boolean isPlayer() {
      return true;
   }

   public boolean getAlwaysRenderNameTagForRender() {
      return true;
   }

   public void clonePlayer(EntityPlayer var1, boolean var2) {
      if(var2) {
         this.inventory.copyInventory(var1.inventory);
         this.setHealth(var1.getHealth());
         this.foodStats = var1.foodStats;
         this.experienceLevel = var1.experienceLevel;
         this.experienceTotal = var1.experienceTotal;
         this.experience = var1.experience;
         this.setScore(var1.getScore());
         super.teleportDirection = var1.teleportDirection;
      } else if(super.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
         this.inventory.copyInventory(var1.inventory);
         this.experienceLevel = var1.experienceLevel;
         this.experienceTotal = var1.experienceTotal;
         this.experience = var1.experience;
         this.setScore(var1.getScore());
      }

      this.theInventoryEnderChest = var1.theInventoryEnderChest;
   }

   protected boolean canTriggerWalking() {
      return !this.capabilities.isFlying;
   }

   public void sendPlayerAbilities() {}

   public void setGameType(WorldSettings$GameType var1) {}

   public String getCommandSenderName() {
      return this.field_146106_i.getName();
   }

   public World getEntityWorld() {
      return super.worldObj;
   }

   public InventoryEnderChest getInventoryEnderChest() {
      return this.theInventoryEnderChest;
   }

   public ItemStack getEquipmentInSlot(int var1) {
      return var1 == 0?this.inventory.getCurrentItem():this.inventory.armorInventory[var1 - 1];
   }

   public ItemStack getHeldItem() {
      return this.inventory.getCurrentItem();
   }

   public void setCurrentItemOrArmor(int var1, ItemStack var2) {
      this.inventory.armorInventory[var1] = var2;
   }

   public boolean isInvisibleToPlayer(EntityPlayer var1) {
      if(!this.isInvisible()) {
         return false;
      } else {
         Team var2 = this.getTeam();
         return var2 == null || var1 == null || var1.getTeam() != var2 || !var2.func_98297_h();
      }
   }

   public ItemStack[] getLastActiveItems() {
      return this.inventory.armorInventory;
   }

   public boolean getHideCape() {
      return this.getHideCape(1);
   }

   public boolean isPushedByWater() {
      return !this.capabilities.isFlying;
   }

   public Scoreboard getWorldScoreboard() {
      return super.worldObj.getScoreboard();
   }

   public Team getTeam() {
      return this.getWorldScoreboard().getPlayersTeam(this.getCommandSenderName());
   }

   public IChatComponent func_145748_c_() {
      ChatComponentText var1 = new ChatComponentText(ScorePlayerTeam.formatPlayerName(this.getTeam(), this.getCommandSenderName()));
      var1.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent$Action.SUGGEST_COMMAND, "/msg " + this.getCommandSenderName() + " "));
      return var1;
   }

   public void setAbsorptionAmount(float var1) {
      if(var1 < 0.0F) {
         var1 = 0.0F;
      }

      this.getDataWatcher().updateObject(17, Float.valueOf(var1));
   }

   public float getAbsorptionAmount() {
      return this.getDataWatcher().getWatchableObjectFloat(17);
   }

   public static UUID func_146094_a(GameProfile var0) {
      UUID var1 = var0.getId();
      if(var1 == null) {
         var1 = UUID.nameUUIDFromBytes(("OfflinePlayer:" + var0.getName()).getBytes(Charsets.UTF_8));
      }

      return var1;
   }
}
