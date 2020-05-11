package net.minecraft.client.multiplayer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity$Action;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings$GameType;

public class PlayerControllerMP {

   private final Minecraft mc;
   private final NetHandlerPlayClient netClientHandler;
   private int currentBlockX = -1;
   private int currentBlockY = -1;
   private int currentblockZ = -1;
   private ItemStack currentItemHittingBlock;
   private float curBlockDamageMP;
   private float stepSoundTickCounter;
   private int blockHitDelay;
   private boolean isHittingBlock;
   private WorldSettings$GameType currentGameType;
   private int currentPlayerItem;


   public PlayerControllerMP(Minecraft var1, NetHandlerPlayClient var2) {
      this.currentGameType = WorldSettings$GameType.SURVIVAL;
      this.mc = var1;
      this.netClientHandler = var2;
   }

   public static void clickBlockCreative(Minecraft var0, PlayerControllerMP var1, int var2, int var3, int var4, int var5) {
      if(!var0.theWorld.extinguishFire(var0.thePlayer, var2, var3, var4, var5)) {
         var1.onPlayerDestroyBlock(var2, var3, var4, var5);
      }

   }

   public void setPlayerCapabilities(EntityPlayer var1) {
      this.currentGameType.configurePlayerCapabilities(var1.capabilities);
   }

   public boolean enableEverythingIsScrewedUpMode() {
      return false;
   }

   public void setGameType(WorldSettings$GameType var1) {
      this.currentGameType = var1;
      this.currentGameType.configurePlayerCapabilities(this.mc.thePlayer.capabilities);
   }

   public void flipPlayer(EntityPlayer var1) {
      var1.rotationYaw = -180.0F;
   }

   public boolean shouldDrawHUD() {
      return this.currentGameType.isSurvivalOrAdventure();
   }

   public boolean onPlayerDestroyBlock(int var1, int var2, int var3, int var4) {
      if(this.currentGameType.isAdventure() && !this.mc.thePlayer.isCurrentToolAdventureModeExempt(var1, var2, var3)) {
         return false;
      } else if(this.currentGameType.isCreative() && this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword) {
         return false;
      } else {
         WorldClient var5 = this.mc.theWorld;
         Block var6 = var5.getBlock(var1, var2, var3);
         if(var6.getMaterial() == Material.air) {
            return false;
         } else {
            var5.playAuxSFX(2001, var1, var2, var3, Block.getIdFromBlock(var6) + (var5.getBlockMetadata(var1, var2, var3) << 12));
            int var7 = var5.getBlockMetadata(var1, var2, var3);
            boolean var8 = var5.setBlockToAir(var1, var2, var3);
            if(var8) {
               var6.onBlockDestroyedByPlayer(var5, var1, var2, var3, var7);
            }

            this.currentBlockY = -1;
            if(!this.currentGameType.isCreative()) {
               ItemStack var9 = this.mc.thePlayer.getCurrentEquippedItem();
               if(var9 != null) {
                  var9.func_150999_a(var5, var6, var1, var2, var3, this.mc.thePlayer);
                  if(var9.stackSize == 0) {
                     this.mc.thePlayer.destroyCurrentEquippedItem();
                  }
               }
            }

            return var8;
         }
      }
   }

   public void clickBlock(int var1, int var2, int var3, int var4) {
      if(!this.currentGameType.isAdventure() || this.mc.thePlayer.isCurrentToolAdventureModeExempt(var1, var2, var3)) {
         if(this.currentGameType.isCreative()) {
            this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(0, var1, var2, var3, var4));
            clickBlockCreative(this.mc, this, var1, var2, var3, var4);
            this.blockHitDelay = 5;
         } else if(!this.isHittingBlock || !this.sameToolAndBlock(var1, var2, var3)) {
            if(this.isHittingBlock) {
               this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(1, this.currentBlockX, this.currentBlockY, this.currentblockZ, var4));
            }

            this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(0, var1, var2, var3, var4));
            Block var5 = this.mc.theWorld.getBlock(var1, var2, var3);
            boolean var6 = var5.getMaterial() != Material.air;
            if(var6 && this.curBlockDamageMP == 0.0F) {
               var5.onBlockClicked(this.mc.theWorld, var1, var2, var3, this.mc.thePlayer);
            }

            if(var6 && var5.getPlayerRelativeBlockHardness(this.mc.thePlayer, this.mc.thePlayer.worldObj, var1, var2, var3) >= 1.0F) {
               this.onPlayerDestroyBlock(var1, var2, var3, var4);
            } else {
               this.isHittingBlock = true;
               this.currentBlockX = var1;
               this.currentBlockY = var2;
               this.currentblockZ = var3;
               this.currentItemHittingBlock = this.mc.thePlayer.getHeldItem();
               this.curBlockDamageMP = 0.0F;
               this.stepSoundTickCounter = 0.0F;
               this.mc.theWorld.destroyBlockInWorldPartially(this.mc.thePlayer.getEntityId(), this.currentBlockX, this.currentBlockY, this.currentblockZ, (int)(this.curBlockDamageMP * 10.0F) - 1);
            }
         }

      }
   }

   public void resetBlockRemoving() {
      if(this.isHittingBlock) {
         this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(1, this.currentBlockX, this.currentBlockY, this.currentblockZ, -1));
      }

      this.isHittingBlock = false;
      this.curBlockDamageMP = 0.0F;
      this.mc.theWorld.destroyBlockInWorldPartially(this.mc.thePlayer.getEntityId(), this.currentBlockX, this.currentBlockY, this.currentblockZ, -1);
   }

   public void onPlayerDamageBlock(int var1, int var2, int var3, int var4) {
      this.syncCurrentPlayItem();
      if(this.blockHitDelay > 0) {
         --this.blockHitDelay;
      } else if(this.currentGameType.isCreative()) {
         this.blockHitDelay = 5;
         this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(0, var1, var2, var3, var4));
         clickBlockCreative(this.mc, this, var1, var2, var3, var4);
      } else {
         if(this.sameToolAndBlock(var1, var2, var3)) {
            Block var5 = this.mc.theWorld.getBlock(var1, var2, var3);
            if(var5.getMaterial() == Material.air) {
               this.isHittingBlock = false;
               return;
            }

            this.curBlockDamageMP += var5.getPlayerRelativeBlockHardness(this.mc.thePlayer, this.mc.thePlayer.worldObj, var1, var2, var3);
            if(this.stepSoundTickCounter % 4.0F == 0.0F) {
               this.mc.getSoundHandler().playSound(new PositionedSoundRecord(new ResourceLocation(var5.stepSound.getStepResourcePath()), (var5.stepSound.getVolume() + 1.0F) / 8.0F, var5.stepSound.getPitch() * 0.5F, (float)var1 + 0.5F, (float)var2 + 0.5F, (float)var3 + 0.5F));
            }

            ++this.stepSoundTickCounter;
            if(this.curBlockDamageMP >= 1.0F) {
               this.isHittingBlock = false;
               this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(2, var1, var2, var3, var4));
               this.onPlayerDestroyBlock(var1, var2, var3, var4);
               this.curBlockDamageMP = 0.0F;
               this.stepSoundTickCounter = 0.0F;
               this.blockHitDelay = 5;
            }

            this.mc.theWorld.destroyBlockInWorldPartially(this.mc.thePlayer.getEntityId(), this.currentBlockX, this.currentBlockY, this.currentblockZ, (int)(this.curBlockDamageMP * 10.0F) - 1);
         } else {
            this.clickBlock(var1, var2, var3, var4);
         }

      }
   }

   public float getBlockReachDistance() {
      return this.currentGameType.isCreative()?5.0F:4.5F;
   }

   public void updateController() {
      this.syncCurrentPlayItem();
      if(this.netClientHandler.getNetworkManager().isChannelOpen()) {
         this.netClientHandler.getNetworkManager().processReceivedPackets();
      } else if(this.netClientHandler.getNetworkManager().getExitMessage() != null) {
         this.netClientHandler.getNetworkManager().getNetHandler().onDisconnect(this.netClientHandler.getNetworkManager().getExitMessage());
      } else {
         this.netClientHandler.getNetworkManager().getNetHandler().onDisconnect(new ChatComponentText("Disconnected from server"));
      }

   }

   private boolean sameToolAndBlock(int var1, int var2, int var3) {
      ItemStack var4 = this.mc.thePlayer.getHeldItem();
      boolean var5 = this.currentItemHittingBlock == null && var4 == null;
      if(this.currentItemHittingBlock != null && var4 != null) {
         var5 = var4.getItem() == this.currentItemHittingBlock.getItem() && ItemStack.areItemStackTagsEqual(var4, this.currentItemHittingBlock) && (var4.isItemStackDamageable() || var4.getItemDamage() == this.currentItemHittingBlock.getItemDamage());
      }

      return var1 == this.currentBlockX && var2 == this.currentBlockY && var3 == this.currentblockZ && var5;
   }

   private void syncCurrentPlayItem() {
      int var1 = this.mc.thePlayer.inventory.currentItem;
      if(var1 != this.currentPlayerItem) {
         this.currentPlayerItem = var1;
         this.netClientHandler.addToSendQueue(new C09PacketHeldItemChange(this.currentPlayerItem));
      }

   }

   public boolean onPlayerRightClick(EntityPlayer var1, World var2, ItemStack var3, int var4, int var5, int var6, int var7, Vec3 var8) {
      this.syncCurrentPlayItem();
      float var9 = (float)var8.xCoord - (float)var4;
      float var10 = (float)var8.yCoord - (float)var5;
      float var11 = (float)var8.zCoord - (float)var6;
      boolean var12 = false;
      if((!var1.isSneaking() || var1.getHeldItem() == null) && var2.getBlock(var4, var5, var6).onBlockActivated(var2, var4, var5, var6, var1, var7, var9, var10, var11)) {
         var12 = true;
      }

      if(!var12 && var3 != null && var3.getItem() instanceof ItemBlock) {
         ItemBlock var13 = (ItemBlock)var3.getItem();
         if(!var13.func_150936_a(var2, var4, var5, var6, var7, var1, var3)) {
            return false;
         }
      }

      this.netClientHandler.addToSendQueue(new C08PacketPlayerBlockPlacement(var4, var5, var6, var7, var1.inventory.getCurrentItem(), var9, var10, var11));
      if(var12) {
         return true;
      } else if(var3 == null) {
         return false;
      } else if(this.currentGameType.isCreative()) {
         int var16 = var3.getItemDamage();
         int var14 = var3.stackSize;
         boolean var15 = var3.tryPlaceItemIntoWorld(var1, var2, var4, var5, var6, var7, var9, var10, var11);
         var3.setItemDamage(var16);
         var3.stackSize = var14;
         return var15;
      } else {
         return var3.tryPlaceItemIntoWorld(var1, var2, var4, var5, var6, var7, var9, var10, var11);
      }
   }

   public boolean sendUseItem(EntityPlayer var1, World var2, ItemStack var3) {
      this.syncCurrentPlayItem();
      this.netClientHandler.addToSendQueue(new C08PacketPlayerBlockPlacement(-1, -1, -1, 255, var1.inventory.getCurrentItem(), 0.0F, 0.0F, 0.0F));
      int var4 = var3.stackSize;
      ItemStack var5 = var3.useItemRightClick(var2, var1);
      if(var5 == var3 && (var5 == null || var5.stackSize == var4)) {
         return false;
      } else {
         var1.inventory.mainInventory[var1.inventory.currentItem] = var5;
         if(var5.stackSize == 0) {
            var1.inventory.mainInventory[var1.inventory.currentItem] = null;
         }

         return true;
      }
   }

   public EntityClientPlayerMP func_147493_a(World var1, StatFileWriter var2) {
      return new EntityClientPlayerMP(this.mc, var1, this.mc.getSession(), this.netClientHandler, var2);
   }

   public void attackEntity(EntityPlayer var1, Entity var2) {
      this.syncCurrentPlayItem();
      this.netClientHandler.addToSendQueue(new C02PacketUseEntity(var2, C02PacketUseEntity$Action.ATTACK));
      var1.attackTargetEntityWithCurrentItem(var2);
   }

   public boolean interactWithEntitySendPacket(EntityPlayer var1, Entity var2) {
      this.syncCurrentPlayItem();
      this.netClientHandler.addToSendQueue(new C02PacketUseEntity(var2, C02PacketUseEntity$Action.INTERACT));
      return var1.interactWith(var2);
   }

   public ItemStack windowClick(int var1, int var2, int var3, int var4, EntityPlayer var5) {
      short var6 = var5.openContainer.getNextTransactionID(var5.inventory);
      ItemStack var7 = var5.openContainer.slotClick(var2, var3, var4, var5);
      this.netClientHandler.addToSendQueue(new C0EPacketClickWindow(var1, var2, var3, var4, var7, var6));
      return var7;
   }

   public void sendEnchantPacket(int var1, int var2) {
      this.netClientHandler.addToSendQueue(new C11PacketEnchantItem(var1, var2));
   }

   public void sendSlotPacket(ItemStack var1, int var2) {
      if(this.currentGameType.isCreative()) {
         this.netClientHandler.addToSendQueue(new C10PacketCreativeInventoryAction(var2, var1));
      }

   }

   public void sendPacketDropItem(ItemStack var1) {
      if(this.currentGameType.isCreative() && var1 != null) {
         this.netClientHandler.addToSendQueue(new C10PacketCreativeInventoryAction(-1, var1));
      }

   }

   public void onStoppedUsingItem(EntityPlayer var1) {
      this.syncCurrentPlayItem();
      this.netClientHandler.addToSendQueue(new C07PacketPlayerDigging(5, 0, 0, 0, 255));
      var1.stopUsingItem();
   }

   public boolean gameIsSurvivalOrAdventure() {
      return this.currentGameType.isSurvivalOrAdventure();
   }

   public boolean isNotCreative() {
      return !this.currentGameType.isCreative();
   }

   public boolean isInCreativeMode() {
      return this.currentGameType.isCreative();
   }

   public boolean extendedReach() {
      return this.currentGameType.isCreative();
   }

   public boolean func_110738_j() {
      return this.mc.thePlayer.isRiding() && this.mc.thePlayer.ridingEntity instanceof EntityHorse;
   }
}
