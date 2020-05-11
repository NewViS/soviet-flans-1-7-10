package net.minecraft.entity.player;

import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList$EntityEggInfo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer$EnumChatVisibility;
import net.minecraft.entity.player.EntityPlayer$EnumStatus;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.stats.StatisticsFile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonSerializableSet;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityPlayerMP extends EntityPlayer implements ICrafting {

   private static final Logger logger = LogManager.getLogger();
   private String translator = "en_US";
   public NetHandlerPlayServer playerNetServerHandler;
   public final MinecraftServer mcServer;
   public final ItemInWorldManager theItemInWorldManager;
   public double managedPosX;
   public double managedPosZ;
   public final List loadedChunks = new LinkedList();
   private final List destroyedItemsNetCache = new LinkedList();
   private final StatisticsFile field_147103_bO;
   private float field_130068_bO = Float.MIN_VALUE;
   private float lastHealth = -1.0E8F;
   private int lastFoodLevel = -99999999;
   private boolean wasHungry = true;
   private int lastExperience = -99999999;
   private int field_147101_bU = 60;
   private EntityPlayer$EnumChatVisibility chatVisibility;
   private boolean chatColours = true;
   private long field_143005_bX = System.currentTimeMillis();
   private int currentWindowId;
   public boolean isChangingQuantityOnly;
   public int ping;
   public boolean playerConqueredTheEnd;


   public EntityPlayerMP(MinecraftServer var1, WorldServer var2, GameProfile var3, ItemInWorldManager var4) {
      super(var2, var3);
      var4.thisPlayerMP = this;
      this.theItemInWorldManager = var4;
      ChunkCoordinates var5 = var2.getSpawnPoint();
      int var6 = var5.posX;
      int var7 = var5.posZ;
      int var8 = var5.posY;
      if(!var2.provider.hasNoSky && var2.getWorldInfo().getGameType() != WorldSettings$GameType.ADVENTURE) {
         int var9 = Math.max(5, var1.getSpawnProtectionSize() - 6);
         var6 += super.rand.nextInt(var9 * 2) - var9;
         var7 += super.rand.nextInt(var9 * 2) - var9;
         var8 = var2.getTopSolidOrLiquidBlock(var6, var7);
      }

      this.mcServer = var1;
      this.field_147103_bO = var1.getConfigurationManager().func_152602_a(this);
      super.stepHeight = 0.0F;
      super.yOffset = 0.0F;
      this.setLocationAndAngles((double)var6 + 0.5D, (double)var8, (double)var7 + 0.5D, 0.0F, 0.0F);

      while(!var2.getCollidingBoundingBoxes(this, super.boundingBox).isEmpty()) {
         this.setPosition(super.posX, super.posY + 1.0D, super.posZ);
      }

   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if(var1.hasKey("playerGameType", 99)) {
         if(MinecraftServer.getServer().getForceGamemode()) {
            this.theItemInWorldManager.setGameType(MinecraftServer.getServer().getGameType());
         } else {
            this.theItemInWorldManager.setGameType(WorldSettings$GameType.getByID(var1.getInteger("playerGameType")));
         }
      }

   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("playerGameType", this.theItemInWorldManager.getGameType().getID());
   }

   public void addExperienceLevel(int var1) {
      super.addExperienceLevel(var1);
      this.lastExperience = -1;
   }

   public void addSelfToInternalCraftingInventory() {
      super.openContainer.addCraftingToCrafters(this);
   }

   protected void resetHeight() {
      super.yOffset = 0.0F;
   }

   public float getEyeHeight() {
      return 1.62F;
   }

   public void onUpdate() {
      this.theItemInWorldManager.updateBlockRemoving();
      --this.field_147101_bU;
      if(super.hurtResistantTime > 0) {
         --super.hurtResistantTime;
      }

      super.openContainer.detectAndSendChanges();
      if(!super.worldObj.isRemote && !super.openContainer.canInteractWith(this)) {
         this.closeScreen();
         super.openContainer = super.inventoryContainer;
      }

      while(!this.destroyedItemsNetCache.isEmpty()) {
         int var1 = Math.min(this.destroyedItemsNetCache.size(), 127);
         int[] var2 = new int[var1];
         Iterator var3 = this.destroyedItemsNetCache.iterator();
         int var4 = 0;

         while(var3.hasNext() && var4 < var1) {
            var2[var4++] = ((Integer)var3.next()).intValue();
            var3.remove();
         }

         this.playerNetServerHandler.sendPacket(new S13PacketDestroyEntities(var2));
      }

      if(!this.loadedChunks.isEmpty()) {
         ArrayList var6 = new ArrayList();
         Iterator var7 = this.loadedChunks.iterator();
         ArrayList var8 = new ArrayList();

         Chunk var5;
         while(var7.hasNext() && var6.size() < S26PacketMapChunkBulk.func_149258_c()) {
            ChunkCoordIntPair var9 = (ChunkCoordIntPair)var7.next();
            if(var9 != null) {
               if(super.worldObj.blockExists(var9.chunkXPos << 4, 0, var9.chunkZPos << 4)) {
                  var5 = super.worldObj.getChunkFromChunkCoords(var9.chunkXPos, var9.chunkZPos);
                  if(var5.func_150802_k()) {
                     var6.add(var5);
                     var8.addAll(((WorldServer)super.worldObj).func_147486_a(var9.chunkXPos * 16, 0, var9.chunkZPos * 16, var9.chunkXPos * 16 + 16, 256, var9.chunkZPos * 16 + 16));
                     var7.remove();
                  }
               }
            } else {
               var7.remove();
            }
         }

         if(!var6.isEmpty()) {
            this.playerNetServerHandler.sendPacket(new S26PacketMapChunkBulk(var6));
            Iterator var10 = var8.iterator();

            while(var10.hasNext()) {
               TileEntity var11 = (TileEntity)var10.next();
               this.func_147097_b(var11);
            }

            var10 = var6.iterator();

            while(var10.hasNext()) {
               var5 = (Chunk)var10.next();
               this.getServerForPlayer().getEntityTracker().func_85172_a(this, var5);
            }
         }
      }

   }

   public void onUpdateEntity() {
      try {
         super.onUpdate();

         for(int var1 = 0; var1 < super.inventory.getSizeInventory(); ++var1) {
            ItemStack var6 = super.inventory.getStackInSlot(var1);
            if(var6 != null && var6.getItem().isMap()) {
               Packet var8 = ((ItemMapBase)var6.getItem()).func_150911_c(var6, super.worldObj, this);
               if(var8 != null) {
                  this.playerNetServerHandler.sendPacket(var8);
               }
            }
         }

         if(this.getHealth() != this.lastHealth || this.lastFoodLevel != super.foodStats.getFoodLevel() || super.foodStats.getSaturationLevel() == 0.0F != this.wasHungry) {
            this.playerNetServerHandler.sendPacket(new S06PacketUpdateHealth(this.getHealth(), super.foodStats.getFoodLevel(), super.foodStats.getSaturationLevel()));
            this.lastHealth = this.getHealth();
            this.lastFoodLevel = super.foodStats.getFoodLevel();
            this.wasHungry = super.foodStats.getSaturationLevel() == 0.0F;
         }

         if(this.getHealth() + this.getAbsorptionAmount() != this.field_130068_bO) {
            this.field_130068_bO = this.getHealth() + this.getAbsorptionAmount();
            Collection var5 = this.getWorldScoreboard().func_96520_a(IScoreObjectiveCriteria.health);
            Iterator var7 = var5.iterator();

            while(var7.hasNext()) {
               ScoreObjective var9 = (ScoreObjective)var7.next();
               this.getWorldScoreboard().func_96529_a(this.getCommandSenderName(), var9).func_96651_a(Arrays.asList(new EntityPlayer[]{this}));
            }
         }

         if(super.experienceTotal != this.lastExperience) {
            this.lastExperience = super.experienceTotal;
            this.playerNetServerHandler.sendPacket(new S1FPacketSetExperience(super.experience, super.experienceTotal, super.experienceLevel));
         }

         if(super.ticksExisted % 20 * 5 == 0 && !this.func_147099_x().hasAchievementUnlocked(AchievementList.field_150961_L)) {
            this.func_147098_j();
         }

      } catch (Throwable var4) {
         CrashReport var2 = CrashReport.makeCrashReport(var4, "Ticking player");
         CrashReportCategory var3 = var2.makeCategory("Player being ticked");
         this.addEntityCrashInfo(var3);
         throw new ReportedException(var2);
      }
   }

   protected void func_147098_j() {
      BiomeGenBase var1 = super.worldObj.getBiomeGenForCoords(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posZ));
      if(var1 != null) {
         String var2 = var1.biomeName;
         JsonSerializableSet var3 = (JsonSerializableSet)this.func_147099_x().func_150870_b(AchievementList.field_150961_L);
         if(var3 == null) {
            var3 = (JsonSerializableSet)this.func_147099_x().func_150872_a(AchievementList.field_150961_L, new JsonSerializableSet());
         }

         var3.add(var2);
         if(this.func_147099_x().canUnlockAchievement(AchievementList.field_150961_L) && var3.size() == BiomeGenBase.explorationBiomesList.size()) {
            HashSet var4 = Sets.newHashSet(BiomeGenBase.explorationBiomesList);
            Iterator var5 = var3.iterator();

            while(var5.hasNext()) {
               String var6 = (String)var5.next();
               Iterator var7 = var4.iterator();

               while(var7.hasNext()) {
                  BiomeGenBase var8 = (BiomeGenBase)var7.next();
                  if(var8.biomeName.equals(var6)) {
                     var7.remove();
                  }
               }

               if(var4.isEmpty()) {
                  break;
               }
            }

            if(var4.isEmpty()) {
               this.triggerAchievement(AchievementList.field_150961_L);
            }
         }
      }

   }

   public void onDeath(DamageSource var1) {
      this.mcServer.getConfigurationManager().sendChatMsg(this.func_110142_aN().func_151521_b());
      if(!super.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
         super.inventory.dropAllItems();
      }

      Collection var2 = super.worldObj.getScoreboard().func_96520_a(IScoreObjectiveCriteria.deathCount);
      Iterator var3 = var2.iterator();

      while(var3.hasNext()) {
         ScoreObjective var4 = (ScoreObjective)var3.next();
         Score var5 = this.getWorldScoreboard().func_96529_a(this.getCommandSenderName(), var4);
         var5.func_96648_a();
      }

      EntityLivingBase var6 = this.func_94060_bK();
      if(var6 != null) {
         int var7 = EntityList.getEntityID(var6);
         EntityList$EntityEggInfo var8 = (EntityList$EntityEggInfo)EntityList.entityEggs.get(Integer.valueOf(var7));
         if(var8 != null) {
            this.addStat(var8.field_151513_e, 1);
         }

         var6.addToPlayerScore(this, super.scoreValue);
      }

      this.addStat(StatList.deathsStat, 1);
      this.func_110142_aN().func_94549_h();
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else {
         boolean var3 = this.mcServer.isDedicatedServer() && this.mcServer.isPVPEnabled() && "fall".equals(var1.damageType);
         if(!var3 && this.field_147101_bU > 0 && var1 != DamageSource.outOfWorld) {
            return false;
         } else {
            if(var1 instanceof EntityDamageSource) {
               Entity var4 = var1.getEntity();
               if(var4 instanceof EntityPlayer && !this.canAttackPlayer((EntityPlayer)var4)) {
                  return false;
               }

               if(var4 instanceof EntityArrow) {
                  EntityArrow var5 = (EntityArrow)var4;
                  if(var5.shootingEntity instanceof EntityPlayer && !this.canAttackPlayer((EntityPlayer)var5.shootingEntity)) {
                     return false;
                  }
               }
            }

            return super.attackEntityFrom(var1, var2);
         }
      }
   }

   public boolean canAttackPlayer(EntityPlayer var1) {
      return !this.mcServer.isPVPEnabled()?false:super.canAttackPlayer(var1);
   }

   public void travelToDimension(int var1) {
      if(super.dimension == 1 && var1 == 1) {
         this.triggerAchievement(AchievementList.theEnd2);
         super.worldObj.removeEntity(this);
         this.playerConqueredTheEnd = true;
         this.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(4, 0.0F));
      } else {
         if(super.dimension == 0 && var1 == 1) {
            this.triggerAchievement(AchievementList.theEnd);
            ChunkCoordinates var2 = this.mcServer.worldServerForDimension(var1).getEntrancePortalLocation();
            if(var2 != null) {
               this.playerNetServerHandler.setPlayerLocation((double)var2.posX, (double)var2.posY, (double)var2.posZ, 0.0F, 0.0F);
            }

            var1 = 1;
         } else {
            this.triggerAchievement(AchievementList.portal);
         }

         this.mcServer.getConfigurationManager().transferPlayerToDimension(this, var1);
         this.lastExperience = -1;
         this.lastHealth = -1.0F;
         this.lastFoodLevel = -1;
      }

   }

   private void func_147097_b(TileEntity var1) {
      if(var1 != null) {
         Packet var2 = var1.getDescriptionPacket();
         if(var2 != null) {
            this.playerNetServerHandler.sendPacket(var2);
         }
      }

   }

   public void onItemPickup(Entity var1, int var2) {
      super.onItemPickup(var1, var2);
      super.openContainer.detectAndSendChanges();
   }

   public EntityPlayer$EnumStatus sleepInBedAt(int var1, int var2, int var3) {
      EntityPlayer$EnumStatus var4 = super.sleepInBedAt(var1, var2, var3);
      if(var4 == EntityPlayer$EnumStatus.OK) {
         S0APacketUseBed var5 = new S0APacketUseBed(this, var1, var2, var3);
         this.getServerForPlayer().getEntityTracker().func_151247_a(this, var5);
         this.playerNetServerHandler.setPlayerLocation(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
         this.playerNetServerHandler.sendPacket(var5);
      }

      return var4;
   }

   public void wakeUpPlayer(boolean var1, boolean var2, boolean var3) {
      if(this.isPlayerSleeping()) {
         this.getServerForPlayer().getEntityTracker().func_151248_b(this, new S0BPacketAnimation(this, 2));
      }

      super.wakeUpPlayer(var1, var2, var3);
      if(this.playerNetServerHandler != null) {
         this.playerNetServerHandler.setPlayerLocation(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
      }

   }

   public void mountEntity(Entity var1) {
      super.mountEntity(var1);
      this.playerNetServerHandler.sendPacket(new S1BPacketEntityAttach(0, this, super.ridingEntity));
      this.playerNetServerHandler.setPlayerLocation(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
   }

   protected void updateFallState(double var1, boolean var3) {}

   public void handleFalling(double var1, boolean var3) {
      super.updateFallState(var1, var3);
   }

   public void func_146100_a(TileEntity var1) {
      if(var1 instanceof TileEntitySign) {
         ((TileEntitySign)var1).func_145912_a(this);
         this.playerNetServerHandler.sendPacket(new S36PacketSignEditorOpen(var1.xCoord, var1.yCoord, var1.zCoord));
      }

   }

   private void getNextWindowId() {
      this.currentWindowId = this.currentWindowId % 100 + 1;
   }

   public void displayGUIWorkbench(int var1, int var2, int var3) {
      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, 1, "Crafting", 9, true));
      super.openContainer = new ContainerWorkbench(super.inventory, super.worldObj, var1, var2, var3);
      super.openContainer.windowId = this.currentWindowId;
      super.openContainer.addCraftingToCrafters(this);
   }

   public void displayGUIEnchantment(int var1, int var2, int var3, String var4) {
      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, 4, var4 == null?"":var4, 9, var4 != null));
      super.openContainer = new ContainerEnchantment(super.inventory, super.worldObj, var1, var2, var3);
      super.openContainer.windowId = this.currentWindowId;
      super.openContainer.addCraftingToCrafters(this);
   }

   public void displayGUIAnvil(int var1, int var2, int var3) {
      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, 8, "Repairing", 9, true));
      super.openContainer = new ContainerRepair(super.inventory, super.worldObj, var1, var2, var3, this);
      super.openContainer.windowId = this.currentWindowId;
      super.openContainer.addCraftingToCrafters(this);
   }

   public void displayGUIChest(IInventory var1) {
      if(super.openContainer != super.inventoryContainer) {
         this.closeScreen();
      }

      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, 0, var1.getInventoryName(), var1.getSizeInventory(), var1.hasCustomInventoryName()));
      super.openContainer = new ContainerChest(super.inventory, var1);
      super.openContainer.windowId = this.currentWindowId;
      super.openContainer.addCraftingToCrafters(this);
   }

   public void func_146093_a(TileEntityHopper var1) {
      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, 9, var1.getInventoryName(), var1.getSizeInventory(), var1.hasCustomInventoryName()));
      super.openContainer = new ContainerHopper(super.inventory, var1);
      super.openContainer.windowId = this.currentWindowId;
      super.openContainer.addCraftingToCrafters(this);
   }

   public void displayGUIHopperMinecart(EntityMinecartHopper var1) {
      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, 9, var1.getInventoryName(), var1.getSizeInventory(), var1.hasCustomInventoryName()));
      super.openContainer = new ContainerHopper(super.inventory, var1);
      super.openContainer.windowId = this.currentWindowId;
      super.openContainer.addCraftingToCrafters(this);
   }

   public void func_146101_a(TileEntityFurnace var1) {
      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, 2, var1.getInventoryName(), var1.getSizeInventory(), var1.hasCustomInventoryName()));
      super.openContainer = new ContainerFurnace(super.inventory, var1);
      super.openContainer.windowId = this.currentWindowId;
      super.openContainer.addCraftingToCrafters(this);
   }

   public void func_146102_a(TileEntityDispenser var1) {
      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, var1 instanceof TileEntityDropper?10:3, var1.getInventoryName(), var1.getSizeInventory(), var1.hasCustomInventoryName()));
      super.openContainer = new ContainerDispenser(super.inventory, var1);
      super.openContainer.windowId = this.currentWindowId;
      super.openContainer.addCraftingToCrafters(this);
   }

   public void func_146098_a(TileEntityBrewingStand var1) {
      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, 5, var1.getInventoryName(), var1.getSizeInventory(), var1.hasCustomInventoryName()));
      super.openContainer = new ContainerBrewingStand(super.inventory, var1);
      super.openContainer.windowId = this.currentWindowId;
      super.openContainer.addCraftingToCrafters(this);
   }

   public void func_146104_a(TileEntityBeacon var1) {
      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, 7, var1.getInventoryName(), var1.getSizeInventory(), var1.hasCustomInventoryName()));
      super.openContainer = new ContainerBeacon(super.inventory, var1);
      super.openContainer.windowId = this.currentWindowId;
      super.openContainer.addCraftingToCrafters(this);
   }

   public void displayGUIMerchant(IMerchant var1, String var2) {
      this.getNextWindowId();
      super.openContainer = new ContainerMerchant(super.inventory, var1, super.worldObj);
      super.openContainer.windowId = this.currentWindowId;
      super.openContainer.addCraftingToCrafters(this);
      InventoryMerchant var3 = ((ContainerMerchant)super.openContainer).getMerchantInventory();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, 6, var2 == null?"":var2, var3.getSizeInventory(), var2 != null));
      MerchantRecipeList var4 = var1.getRecipes(this);
      if(var4 != null) {
         PacketBuffer var5 = new PacketBuffer(Unpooled.buffer());

         try {
            var5.writeInt(this.currentWindowId);
            var4.func_151391_a(var5);
            this.playerNetServerHandler.sendPacket(new S3FPacketCustomPayload("MC|TrList", var5));
         } catch (IOException var10) {
            logger.error("Couldn\'t send trade list", var10);
         } finally {
            var5.release();
         }
      }

   }

   public void displayGUIHorse(EntityHorse var1, IInventory var2) {
      if(super.openContainer != super.inventoryContainer) {
         this.closeScreen();
      }

      this.getNextWindowId();
      this.playerNetServerHandler.sendPacket(new S2DPacketOpenWindow(this.currentWindowId, 11, var2.getInventoryName(), var2.getSizeInventory(), var2.hasCustomInventoryName(), var1.getEntityId()));
      super.openContainer = new ContainerHorseInventory(super.inventory, var2, var1);
      super.openContainer.windowId = this.currentWindowId;
      super.openContainer.addCraftingToCrafters(this);
   }

   public void sendSlotContents(Container var1, int var2, ItemStack var3) {
      if(!(var1.getSlot(var2) instanceof SlotCrafting)) {
         if(!this.isChangingQuantityOnly) {
            this.playerNetServerHandler.sendPacket(new S2FPacketSetSlot(var1.windowId, var2, var3));
         }
      }
   }

   public void sendContainerToPlayer(Container var1) {
      this.sendContainerAndContentsToPlayer(var1, var1.getInventory());
   }

   public void sendContainerAndContentsToPlayer(Container var1, List var2) {
      this.playerNetServerHandler.sendPacket(new S30PacketWindowItems(var1.windowId, var2));
      this.playerNetServerHandler.sendPacket(new S2FPacketSetSlot(-1, -1, super.inventory.getItemStack()));
   }

   public void sendProgressBarUpdate(Container var1, int var2, int var3) {
      this.playerNetServerHandler.sendPacket(new S31PacketWindowProperty(var1.windowId, var2, var3));
   }

   public void closeScreen() {
      this.playerNetServerHandler.sendPacket(new S2EPacketCloseWindow(super.openContainer.windowId));
      this.closeContainer();
   }

   public void updateHeldItem() {
      if(!this.isChangingQuantityOnly) {
         this.playerNetServerHandler.sendPacket(new S2FPacketSetSlot(-1, -1, super.inventory.getItemStack()));
      }
   }

   public void closeContainer() {
      super.openContainer.onContainerClosed(this);
      super.openContainer = super.inventoryContainer;
   }

   public void setEntityActionState(float var1, float var2, boolean var3, boolean var4) {
      if(super.ridingEntity != null) {
         if(var1 >= -1.0F && var1 <= 1.0F) {
            super.moveStrafing = var1;
         }

         if(var2 >= -1.0F && var2 <= 1.0F) {
            super.moveForward = var2;
         }

         super.isJumping = var3;
         this.setSneaking(var4);
      }

   }

   public void addStat(StatBase var1, int var2) {
      if(var1 != null) {
         this.field_147103_bO.func_150871_b(this, var1, var2);
         Iterator var3 = this.getWorldScoreboard().func_96520_a(var1.func_150952_k()).iterator();

         while(var3.hasNext()) {
            ScoreObjective var4 = (ScoreObjective)var3.next();
            this.getWorldScoreboard().func_96529_a(this.getCommandSenderName(), var4).func_96648_a();
         }

         if(this.field_147103_bO.func_150879_e()) {
            this.field_147103_bO.func_150876_a(this);
         }

      }
   }

   public void mountEntityAndWakeUp() {
      if(super.riddenByEntity != null) {
         super.riddenByEntity.mountEntity(this);
      }

      if(super.sleeping) {
         this.wakeUpPlayer(true, false, false);
      }

   }

   public void setPlayerHealthUpdated() {
      this.lastHealth = -1.0E8F;
   }

   public void addChatComponentMessage(IChatComponent var1) {
      this.playerNetServerHandler.sendPacket(new S02PacketChat(var1));
   }

   protected void onItemUseFinish() {
      this.playerNetServerHandler.sendPacket(new S19PacketEntityStatus(this, (byte)9));
      super.onItemUseFinish();
   }

   public void setItemInUse(ItemStack var1, int var2) {
      super.setItemInUse(var1, var2);
      if(var1 != null && var1.getItem() != null && var1.getItem().getItemUseAction(var1) == EnumAction.eat) {
         this.getServerForPlayer().getEntityTracker().func_151248_b(this, new S0BPacketAnimation(this, 3));
      }

   }

   public void clonePlayer(EntityPlayer var1, boolean var2) {
      super.clonePlayer(var1, var2);
      this.lastExperience = -1;
      this.lastHealth = -1.0F;
      this.lastFoodLevel = -1;
      this.destroyedItemsNetCache.addAll(((EntityPlayerMP)var1).destroyedItemsNetCache);
   }

   protected void onNewPotionEffect(PotionEffect var1) {
      super.onNewPotionEffect(var1);
      this.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(this.getEntityId(), var1));
   }

   protected void onChangedPotionEffect(PotionEffect var1, boolean var2) {
      super.onChangedPotionEffect(var1, var2);
      this.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(this.getEntityId(), var1));
   }

   protected void onFinishedPotionEffect(PotionEffect var1) {
      super.onFinishedPotionEffect(var1);
      this.playerNetServerHandler.sendPacket(new S1EPacketRemoveEntityEffect(this.getEntityId(), var1));
   }

   public void setPositionAndUpdate(double var1, double var3, double var5) {
      this.playerNetServerHandler.setPlayerLocation(var1, var3, var5, super.rotationYaw, super.rotationPitch);
   }

   public void onCriticalHit(Entity var1) {
      this.getServerForPlayer().getEntityTracker().func_151248_b(this, new S0BPacketAnimation(var1, 4));
   }

   public void onEnchantmentCritical(Entity var1) {
      this.getServerForPlayer().getEntityTracker().func_151248_b(this, new S0BPacketAnimation(var1, 5));
   }

   public void sendPlayerAbilities() {
      if(this.playerNetServerHandler != null) {
         this.playerNetServerHandler.sendPacket(new S39PacketPlayerAbilities(super.capabilities));
      }
   }

   public WorldServer getServerForPlayer() {
      return (WorldServer)super.worldObj;
   }

   public void setGameType(WorldSettings$GameType var1) {
      this.theItemInWorldManager.setGameType(var1);
      this.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(3, (float)var1.getID()));
   }

   public void addChatMessage(IChatComponent var1) {
      this.playerNetServerHandler.sendPacket(new S02PacketChat(var1));
   }

   public boolean canCommandSenderUseCommand(int var1, String var2) {
      if("seed".equals(var2) && !this.mcServer.isDedicatedServer()) {
         return true;
      } else if(!"tell".equals(var2) && !"help".equals(var2) && !"me".equals(var2)) {
         if(this.mcServer.getConfigurationManager().func_152596_g(this.getGameProfile())) {
            UserListOpsEntry var3 = (UserListOpsEntry)this.mcServer.getConfigurationManager().func_152603_m().func_152683_b(this.getGameProfile());
            return var3 != null?var3.func_152644_a() >= var1:this.mcServer.getOpPermissionLevel() >= var1;
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public String getPlayerIP() {
      String var1 = this.playerNetServerHandler.netManager.getSocketAddress().toString();
      var1 = var1.substring(var1.indexOf("/") + 1);
      var1 = var1.substring(0, var1.indexOf(":"));
      return var1;
   }

   public void func_147100_a(C15PacketClientSettings var1) {
      this.translator = var1.func_149524_c();
      int var2 = 256 >> var1.func_149521_d();
      if(var2 > 3 && var2 < 20) {
         ;
      }

      this.chatVisibility = var1.func_149523_e();
      this.chatColours = var1.func_149520_f();
      if(this.mcServer.isSinglePlayer() && this.mcServer.getServerOwner().equals(this.getCommandSenderName())) {
         this.mcServer.func_147139_a(var1.func_149518_g());
      }

      this.setHideCape(1, !var1.func_149519_h());
   }

   public EntityPlayer$EnumChatVisibility func_147096_v() {
      return this.chatVisibility;
   }

   public void requestTexturePackLoad(String var1) {
      this.playerNetServerHandler.sendPacket(new S3FPacketCustomPayload("MC|RPack", var1.getBytes(Charsets.UTF_8)));
   }

   public ChunkCoordinates getPlayerCoordinates() {
      return new ChunkCoordinates(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY + 0.5D), MathHelper.floor_double(super.posZ));
   }

   public void func_143004_u() {
      this.field_143005_bX = MinecraftServer.getSystemTimeMillis();
   }

   public StatisticsFile func_147099_x() {
      return this.field_147103_bO;
   }

   public void func_152339_d(Entity var1) {
      if(var1 instanceof EntityPlayer) {
         this.playerNetServerHandler.sendPacket(new S13PacketDestroyEntities(new int[]{var1.getEntityId()}));
      } else {
         this.destroyedItemsNetCache.add(Integer.valueOf(var1.getEntityId()));
      }

   }

   public long func_154331_x() {
      return this.field_143005_bX;
   }

}
