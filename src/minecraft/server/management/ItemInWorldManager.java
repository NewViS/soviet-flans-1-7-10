package net.minecraft.server.management;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings$GameType;

public class ItemInWorldManager {

   public World theWorld;
   public EntityPlayerMP thisPlayerMP;
   private WorldSettings$GameType gameType;
   private boolean isDestroyingBlock;
   private int initialDamage;
   private int partiallyDestroyedBlockX;
   private int partiallyDestroyedBlockY;
   private int partiallyDestroyedBlockZ;
   private int curblockDamage;
   private boolean receivedFinishDiggingPacket;
   private int posX;
   private int posY;
   private int posZ;
   private int initialBlockDamage;
   private int durabilityRemainingOnBlock;


   public ItemInWorldManager(World var1) {
      this.gameType = WorldSettings$GameType.NOT_SET;
      this.durabilityRemainingOnBlock = -1;
      this.theWorld = var1;
   }

   public void setGameType(WorldSettings$GameType var1) {
      this.gameType = var1;
      var1.configurePlayerCapabilities(this.thisPlayerMP.capabilities);
      this.thisPlayerMP.sendPlayerAbilities();
   }

   public WorldSettings$GameType getGameType() {
      return this.gameType;
   }

   public boolean isCreative() {
      return this.gameType.isCreative();
   }

   public void initializeGameType(WorldSettings$GameType var1) {
      if(this.gameType == WorldSettings$GameType.NOT_SET) {
         this.gameType = var1;
      }

      this.setGameType(this.gameType);
   }

   public void updateBlockRemoving() {
      ++this.curblockDamage;
      float var3;
      int var4;
      if(this.receivedFinishDiggingPacket) {
         int var1 = this.curblockDamage - this.initialBlockDamage;
         Block var2 = this.theWorld.getBlock(this.posX, this.posY, this.posZ);
         if(var2.getMaterial() == Material.air) {
            this.receivedFinishDiggingPacket = false;
         } else {
            var3 = var2.getPlayerRelativeBlockHardness(this.thisPlayerMP, this.thisPlayerMP.worldObj, this.posX, this.posY, this.posZ) * (float)(var1 + 1);
            var4 = (int)(var3 * 10.0F);
            if(var4 != this.durabilityRemainingOnBlock) {
               this.theWorld.destroyBlockInWorldPartially(this.thisPlayerMP.getEntityId(), this.posX, this.posY, this.posZ, var4);
               this.durabilityRemainingOnBlock = var4;
            }

            if(var3 >= 1.0F) {
               this.receivedFinishDiggingPacket = false;
               this.tryHarvestBlock(this.posX, this.posY, this.posZ);
            }
         }
      } else if(this.isDestroyingBlock) {
         Block var5 = this.theWorld.getBlock(this.partiallyDestroyedBlockX, this.partiallyDestroyedBlockY, this.partiallyDestroyedBlockZ);
         if(var5.getMaterial() == Material.air) {
            this.theWorld.destroyBlockInWorldPartially(this.thisPlayerMP.getEntityId(), this.partiallyDestroyedBlockX, this.partiallyDestroyedBlockY, this.partiallyDestroyedBlockZ, -1);
            this.durabilityRemainingOnBlock = -1;
            this.isDestroyingBlock = false;
         } else {
            int var6 = this.curblockDamage - this.initialDamage;
            var3 = var5.getPlayerRelativeBlockHardness(this.thisPlayerMP, this.thisPlayerMP.worldObj, this.partiallyDestroyedBlockX, this.partiallyDestroyedBlockY, this.partiallyDestroyedBlockZ) * (float)(var6 + 1);
            var4 = (int)(var3 * 10.0F);
            if(var4 != this.durabilityRemainingOnBlock) {
               this.theWorld.destroyBlockInWorldPartially(this.thisPlayerMP.getEntityId(), this.partiallyDestroyedBlockX, this.partiallyDestroyedBlockY, this.partiallyDestroyedBlockZ, var4);
               this.durabilityRemainingOnBlock = var4;
            }
         }
      }

   }

   public void onBlockClicked(int var1, int var2, int var3, int var4) {
      if(!this.gameType.isAdventure() || this.thisPlayerMP.isCurrentToolAdventureModeExempt(var1, var2, var3)) {
         if(this.isCreative()) {
            if(!this.theWorld.extinguishFire((EntityPlayer)null, var1, var2, var3, var4)) {
               this.tryHarvestBlock(var1, var2, var3);
            }

         } else {
            this.theWorld.extinguishFire((EntityPlayer)null, var1, var2, var3, var4);
            this.initialDamage = this.curblockDamage;
            float var5 = 1.0F;
            Block var6 = this.theWorld.getBlock(var1, var2, var3);
            if(var6.getMaterial() != Material.air) {
               var6.onBlockClicked(this.theWorld, var1, var2, var3, this.thisPlayerMP);
               var5 = var6.getPlayerRelativeBlockHardness(this.thisPlayerMP, this.thisPlayerMP.worldObj, var1, var2, var3);
            }

            if(var6.getMaterial() != Material.air && var5 >= 1.0F) {
               this.tryHarvestBlock(var1, var2, var3);
            } else {
               this.isDestroyingBlock = true;
               this.partiallyDestroyedBlockX = var1;
               this.partiallyDestroyedBlockY = var2;
               this.partiallyDestroyedBlockZ = var3;
               int var7 = (int)(var5 * 10.0F);
               this.theWorld.destroyBlockInWorldPartially(this.thisPlayerMP.getEntityId(), var1, var2, var3, var7);
               this.durabilityRemainingOnBlock = var7;
            }

         }
      }
   }

   public void uncheckedTryHarvestBlock(int var1, int var2, int var3) {
      if(var1 == this.partiallyDestroyedBlockX && var2 == this.partiallyDestroyedBlockY && var3 == this.partiallyDestroyedBlockZ) {
         int var4 = this.curblockDamage - this.initialDamage;
         Block var5 = this.theWorld.getBlock(var1, var2, var3);
         if(var5.getMaterial() != Material.air) {
            float var6 = var5.getPlayerRelativeBlockHardness(this.thisPlayerMP, this.thisPlayerMP.worldObj, var1, var2, var3) * (float)(var4 + 1);
            if(var6 >= 0.7F) {
               this.isDestroyingBlock = false;
               this.theWorld.destroyBlockInWorldPartially(this.thisPlayerMP.getEntityId(), var1, var2, var3, -1);
               this.tryHarvestBlock(var1, var2, var3);
            } else if(!this.receivedFinishDiggingPacket) {
               this.isDestroyingBlock = false;
               this.receivedFinishDiggingPacket = true;
               this.posX = var1;
               this.posY = var2;
               this.posZ = var3;
               this.initialBlockDamage = this.initialDamage;
            }
         }
      }

   }

   public void cancelDestroyingBlock(int var1, int var2, int var3) {
      this.isDestroyingBlock = false;
      this.theWorld.destroyBlockInWorldPartially(this.thisPlayerMP.getEntityId(), this.partiallyDestroyedBlockX, this.partiallyDestroyedBlockY, this.partiallyDestroyedBlockZ, -1);
   }

   private boolean removeBlock(int var1, int var2, int var3) {
      Block var4 = this.theWorld.getBlock(var1, var2, var3);
      int var5 = this.theWorld.getBlockMetadata(var1, var2, var3);
      var4.onBlockHarvested(this.theWorld, var1, var2, var3, var5, this.thisPlayerMP);
      boolean var6 = this.theWorld.setBlockToAir(var1, var2, var3);
      if(var6) {
         var4.onBlockDestroyedByPlayer(this.theWorld, var1, var2, var3, var5);
      }

      return var6;
   }

   public boolean tryHarvestBlock(int var1, int var2, int var3) {
      if(this.gameType.isAdventure() && !this.thisPlayerMP.isCurrentToolAdventureModeExempt(var1, var2, var3)) {
         return false;
      } else if(this.gameType.isCreative() && this.thisPlayerMP.getHeldItem() != null && this.thisPlayerMP.getHeldItem().getItem() instanceof ItemSword) {
         return false;
      } else {
         Block var4 = this.theWorld.getBlock(var1, var2, var3);
         int var5 = this.theWorld.getBlockMetadata(var1, var2, var3);
         this.theWorld.playAuxSFXAtEntity(this.thisPlayerMP, 2001, var1, var2, var3, Block.getIdFromBlock(var4) + (this.theWorld.getBlockMetadata(var1, var2, var3) << 12));
         boolean var6 = this.removeBlock(var1, var2, var3);
         if(this.isCreative()) {
            this.thisPlayerMP.playerNetServerHandler.sendPacket(new S23PacketBlockChange(var1, var2, var3, this.theWorld));
         } else {
            ItemStack var7 = this.thisPlayerMP.getCurrentEquippedItem();
            boolean var8 = this.thisPlayerMP.canHarvestBlock(var4);
            if(var7 != null) {
               var7.func_150999_a(this.theWorld, var4, var1, var2, var3, this.thisPlayerMP);
               if(var7.stackSize == 0) {
                  this.thisPlayerMP.destroyCurrentEquippedItem();
               }
            }

            if(var6 && var8) {
               var4.harvestBlock(this.theWorld, this.thisPlayerMP, var1, var2, var3, var5);
            }
         }

         return var6;
      }
   }

   public boolean tryUseItem(EntityPlayer var1, World var2, ItemStack var3) {
      int var4 = var3.stackSize;
      int var5 = var3.getItemDamage();
      ItemStack var6 = var3.useItemRightClick(var2, var1);
      if(var6 == var3 && (var6 == null || var6.stackSize == var4 && var6.getMaxItemUseDuration() <= 0 && var6.getItemDamage() == var5)) {
         return false;
      } else {
         var1.inventory.mainInventory[var1.inventory.currentItem] = var6;
         if(this.isCreative()) {
            var6.stackSize = var4;
            if(var6.isItemStackDamageable()) {
               var6.setItemDamage(var5);
            }
         }

         if(var6.stackSize == 0) {
            var1.inventory.mainInventory[var1.inventory.currentItem] = null;
         }

         if(!var1.isUsingItem()) {
            ((EntityPlayerMP)var1).sendContainerToPlayer(var1.inventoryContainer);
         }

         return true;
      }
   }

   public boolean activateBlockOrUseItem(EntityPlayer var1, World var2, ItemStack var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if((!var1.isSneaking() || var1.getHeldItem() == null) && var2.getBlock(var4, var5, var6).onBlockActivated(var2, var4, var5, var6, var1, var7, var8, var9, var10)) {
         return true;
      } else if(var3 == null) {
         return false;
      } else if(this.isCreative()) {
         int var11 = var3.getItemDamage();
         int var12 = var3.stackSize;
         boolean var13 = var3.tryPlaceItemIntoWorld(var1, var2, var4, var5, var6, var7, var8, var9, var10);
         var3.setItemDamage(var11);
         var3.stackSize = var12;
         return var13;
      } else {
         return var3.tryPlaceItemIntoWorld(var1, var2, var4, var5, var6, var7, var8, var9, var10);
      }
   }

   public void setWorld(WorldServer var1) {
      this.theWorld = var1;
   }
}
