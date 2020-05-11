package net.minecraft.entity.item;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMinecartFurnace extends EntityMinecart {

   private int fuel;
   public double pushX;
   public double pushZ;


   public EntityMinecartFurnace(World var1) {
      super(var1);
   }

   public EntityMinecartFurnace(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   public int getMinecartType() {
      return 2;
   }

   protected void entityInit() {
      super.entityInit();
      super.dataWatcher.addObject(16, new Byte((byte)0));
   }

   public void onUpdate() {
      super.onUpdate();
      if(this.fuel > 0) {
         --this.fuel;
      }

      if(this.fuel <= 0) {
         this.pushX = this.pushZ = 0.0D;
      }

      this.setMinecartPowered(this.fuel > 0);
      if(this.isMinecartPowered() && super.rand.nextInt(4) == 0) {
         super.worldObj.spawnParticle("largesmoke", super.posX, super.posY + 0.8D, super.posZ, 0.0D, 0.0D, 0.0D);
      }

   }

   public void killMinecart(DamageSource var1) {
      super.killMinecart(var1);
      if(!var1.isExplosion()) {
         this.entityDropItem(new ItemStack(Blocks.furnace, 1), 0.0F);
      }

   }

   protected void func_145821_a(int var1, int var2, int var3, double var4, double var6, Block var8, int var9) {
      super.func_145821_a(var1, var2, var3, var4, var6, var8, var9);
      double var10 = this.pushX * this.pushX + this.pushZ * this.pushZ;
      if(var10 > 1.0E-4D && super.motionX * super.motionX + super.motionZ * super.motionZ > 0.001D) {
         var10 = (double)MathHelper.sqrt_double(var10);
         this.pushX /= var10;
         this.pushZ /= var10;
         if(this.pushX * super.motionX + this.pushZ * super.motionZ < 0.0D) {
            this.pushX = 0.0D;
            this.pushZ = 0.0D;
         } else {
            this.pushX = super.motionX;
            this.pushZ = super.motionZ;
         }
      }

   }

   protected void applyDrag() {
      double var1 = this.pushX * this.pushX + this.pushZ * this.pushZ;
      if(var1 > 1.0E-4D) {
         var1 = (double)MathHelper.sqrt_double(var1);
         this.pushX /= var1;
         this.pushZ /= var1;
         double var3 = 0.05D;
         super.motionX *= 0.800000011920929D;
         super.motionY *= 0.0D;
         super.motionZ *= 0.800000011920929D;
         super.motionX += this.pushX * var3;
         super.motionZ += this.pushZ * var3;
      } else {
         super.motionX *= 0.9800000190734863D;
         super.motionY *= 0.0D;
         super.motionZ *= 0.9800000190734863D;
      }

      super.applyDrag();
   }

   public boolean interactFirst(EntityPlayer var1) {
      ItemStack var2 = var1.inventory.getCurrentItem();
      if(var2 != null && var2.getItem() == Items.coal) {
         if(!var1.capabilities.isCreativeMode && --var2.stackSize == 0) {
            var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
         }

         this.fuel += 3600;
      }

      this.pushX = super.posX - var1.posX;
      this.pushZ = super.posZ - var1.posZ;
      return true;
   }

   protected void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setDouble("PushX", this.pushX);
      var1.setDouble("PushZ", this.pushZ);
      var1.setShort("Fuel", (short)this.fuel);
   }

   protected void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.pushX = var1.getDouble("PushX");
      this.pushZ = var1.getDouble("PushZ");
      this.fuel = var1.getShort("Fuel");
   }

   protected boolean isMinecartPowered() {
      return (super.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
   }

   protected void setMinecartPowered(boolean var1) {
      if(var1) {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(super.dataWatcher.getWatchableObjectByte(16) | 1)));
      } else {
         super.dataWatcher.updateObject(16, Byte.valueOf((byte)(super.dataWatcher.getWatchableObjectByte(16) & -2)));
      }

   }

   public Block func_145817_o() {
      return Blocks.lit_furnace;
   }

   public int getDefaultDisplayTileData() {
      return 2;
   }
}
