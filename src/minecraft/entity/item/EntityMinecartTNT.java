package net.minecraft.entity.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityMinecartTNT extends EntityMinecart {

   private int minecartTNTFuse = -1;


   public EntityMinecartTNT(World var1) {
      super(var1);
   }

   public EntityMinecartTNT(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   public int getMinecartType() {
      return 3;
   }

   public Block func_145817_o() {
      return Blocks.tnt;
   }

   public void onUpdate() {
      super.onUpdate();
      if(this.minecartTNTFuse > 0) {
         --this.minecartTNTFuse;
         super.worldObj.spawnParticle("smoke", super.posX, super.posY + 0.5D, super.posZ, 0.0D, 0.0D, 0.0D);
      } else if(this.minecartTNTFuse == 0) {
         this.explodeCart(super.motionX * super.motionX + super.motionZ * super.motionZ);
      }

      if(super.isCollidedHorizontally) {
         double var1 = super.motionX * super.motionX + super.motionZ * super.motionZ;
         if(var1 >= 0.009999999776482582D) {
            this.explodeCart(var1);
         }
      }

   }

   public void killMinecart(DamageSource var1) {
      super.killMinecart(var1);
      double var2 = super.motionX * super.motionX + super.motionZ * super.motionZ;
      if(!var1.isExplosion()) {
         this.entityDropItem(new ItemStack(Blocks.tnt, 1), 0.0F);
      }

      if(var1.isFireDamage() || var1.isExplosion() || var2 >= 0.009999999776482582D) {
         this.explodeCart(var2);
      }

   }

   protected void explodeCart(double var1) {
      if(!super.worldObj.isRemote) {
         double var3 = Math.sqrt(var1);
         if(var3 > 5.0D) {
            var3 = 5.0D;
         }

         super.worldObj.createExplosion(this, super.posX, super.posY, super.posZ, (float)(4.0D + super.rand.nextDouble() * 1.5D * var3), true);
         this.setDead();
      }

   }

   protected void fall(float var1) {
      if(var1 >= 3.0F) {
         float var2 = var1 / 10.0F;
         this.explodeCart((double)(var2 * var2));
      }

      super.fall(var1);
   }

   public void onActivatorRailPass(int var1, int var2, int var3, boolean var4) {
      if(var4 && this.minecartTNTFuse < 0) {
         this.ignite();
      }

   }

   public void handleHealthUpdate(byte var1) {
      if(var1 == 10) {
         this.ignite();
      } else {
         super.handleHealthUpdate(var1);
      }

   }

   public void ignite() {
      this.minecartTNTFuse = 80;
      if(!super.worldObj.isRemote) {
         super.worldObj.setEntityState(this, (byte)10);
         super.worldObj.playSoundAtEntity(this, "game.tnt.primed", 1.0F, 1.0F);
      }

   }

   public int func_94104_d() {
      return this.minecartTNTFuse;
   }

   public boolean isIgnited() {
      return this.minecartTNTFuse > -1;
   }

   public float func_145772_a(Explosion var1, World var2, int var3, int var4, int var5, Block var6) {
      return this.isIgnited() && (BlockRailBase.func_150051_a(var6) || BlockRailBase.func_150049_b_(var2, var3, var4 + 1, var5))?0.0F:super.func_145772_a(var1, var2, var3, var4, var5, var6);
   }

   public boolean func_145774_a(Explosion var1, World var2, int var3, int var4, int var5, Block var6, float var7) {
      return this.isIgnited() && (BlockRailBase.func_150051_a(var6) || BlockRailBase.func_150049_b_(var2, var3, var4 + 1, var5))?false:super.func_145774_a(var1, var2, var3, var4, var5, var6, var7);
   }

   protected void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      if(var1.hasKey("TNTFuse", 99)) {
         this.minecartTNTFuse = var1.getInteger("TNTFuse");
      }

   }

   protected void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      var1.setInteger("TNTFuse", this.minecartTNTFuse);
   }
}
