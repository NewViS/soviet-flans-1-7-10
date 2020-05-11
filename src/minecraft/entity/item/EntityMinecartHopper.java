package net.minecraft.entity.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMinecartHopper extends EntityMinecartContainer implements IHopper {

   private boolean isBlocked = true;
   private int transferTicker = -1;


   public EntityMinecartHopper(World var1) {
      super(var1);
   }

   public EntityMinecartHopper(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   public int getMinecartType() {
      return 5;
   }

   public Block func_145817_o() {
      return Blocks.hopper;
   }

   public int getDefaultDisplayTileOffset() {
      return 1;
   }

   public int getSizeInventory() {
      return 5;
   }

   public boolean interactFirst(EntityPlayer var1) {
      if(!super.worldObj.isRemote) {
         var1.displayGUIHopperMinecart(this);
      }

      return true;
   }

   public void onActivatorRailPass(int var1, int var2, int var3, boolean var4) {
      boolean var5 = !var4;
      if(var5 != this.getBlocked()) {
         this.setBlocked(var5);
      }

   }

   public boolean getBlocked() {
      return this.isBlocked;
   }

   public void setBlocked(boolean var1) {
      this.isBlocked = var1;
   }

   public World getWorldObj() {
      return super.worldObj;
   }

   public double getXPos() {
      return super.posX;
   }

   public double getYPos() {
      return super.posY;
   }

   public double getZPos() {
      return super.posZ;
   }

   public void onUpdate() {
      super.onUpdate();
      if(!super.worldObj.isRemote && this.isEntityAlive() && this.getBlocked()) {
         --this.transferTicker;
         if(!this.canTransfer()) {
            this.setTransferTicker(0);
            if(this.func_96112_aD()) {
               this.setTransferTicker(4);
               this.markDirty();
            }
         }
      }

   }

   public boolean func_96112_aD() {
      if(TileEntityHopper.func_145891_a(this)) {
         return true;
      } else {
         List var1 = super.worldObj.selectEntitiesWithinAABB(EntityItem.class, super.boundingBox.expand(0.25D, 0.0D, 0.25D), IEntitySelector.selectAnything);
         if(var1.size() > 0) {
            TileEntityHopper.func_145898_a(this, (EntityItem)var1.get(0));
         }

         return false;
      }
   }

   public void killMinecart(DamageSource var1) {
      super.killMinecart(var1);
      this.func_145778_a(Item.getItemFromBlock(Blocks.hopper), 1, 0.0F);
   }

   protected void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("TransferCooldown", this.transferTicker);
   }

   protected void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.transferTicker = var1.getInteger("TransferCooldown");
   }

   public void setTransferTicker(int var1) {
      this.transferTicker = var1;
   }

   public boolean canTransfer() {
      return this.transferTicker > 0;
   }
}
