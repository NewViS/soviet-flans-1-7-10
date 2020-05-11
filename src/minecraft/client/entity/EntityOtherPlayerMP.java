package net.minecraft.client.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityOtherPlayerMP extends AbstractClientPlayer {

   private boolean isItemInUse;
   private int otherPlayerMPPosRotationIncrements;
   private double otherPlayerMPX;
   private double otherPlayerMPY;
   private double otherPlayerMPZ;
   private double otherPlayerMPYaw;
   private double otherPlayerMPPitch;


   public EntityOtherPlayerMP(World var1, GameProfile var2) {
      super(var1, var2);
      super.yOffset = 0.0F;
      super.stepHeight = 0.0F;
      super.noClip = true;
      super.field_71082_cx = 0.25F;
      super.renderDistanceWeight = 10.0D;
   }

   protected void resetHeight() {
      super.yOffset = 0.0F;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      return true;
   }

   public void setPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9) {
      this.otherPlayerMPX = var1;
      this.otherPlayerMPY = var3;
      this.otherPlayerMPZ = var5;
      this.otherPlayerMPYaw = (double)var7;
      this.otherPlayerMPPitch = (double)var8;
      this.otherPlayerMPPosRotationIncrements = var9;
   }

   public void onUpdate() {
      super.field_71082_cx = 0.0F;
      super.onUpdate();
      super.prevLimbSwingAmount = super.limbSwingAmount;
      double var1 = super.posX - super.prevPosX;
      double var3 = super.posZ - super.prevPosZ;
      float var5 = MathHelper.sqrt_double(var1 * var1 + var3 * var3) * 4.0F;
      if(var5 > 1.0F) {
         var5 = 1.0F;
      }

      super.limbSwingAmount += (var5 - super.limbSwingAmount) * 0.4F;
      super.limbSwing += super.limbSwingAmount;
      if(!this.isItemInUse && this.isEating() && super.inventory.mainInventory[super.inventory.currentItem] != null) {
         ItemStack var6 = super.inventory.mainInventory[super.inventory.currentItem];
         this.setItemInUse(super.inventory.mainInventory[super.inventory.currentItem], var6.getItem().getMaxItemUseDuration(var6));
         this.isItemInUse = true;
      } else if(this.isItemInUse && !this.isEating()) {
         this.clearItemInUse();
         this.isItemInUse = false;
      }

   }

   public float getShadowSize() {
      return 0.0F;
   }

   public void onLivingUpdate() {
      super.updateEntityActionState();
      if(this.otherPlayerMPPosRotationIncrements > 0) {
         double var1 = super.posX + (this.otherPlayerMPX - super.posX) / (double)this.otherPlayerMPPosRotationIncrements;
         double var3 = super.posY + (this.otherPlayerMPY - super.posY) / (double)this.otherPlayerMPPosRotationIncrements;
         double var5 = super.posZ + (this.otherPlayerMPZ - super.posZ) / (double)this.otherPlayerMPPosRotationIncrements;

         double var7;
         for(var7 = this.otherPlayerMPYaw - (double)super.rotationYaw; var7 < -180.0D; var7 += 360.0D) {
            ;
         }

         while(var7 >= 180.0D) {
            var7 -= 360.0D;
         }

         super.rotationYaw = (float)((double)super.rotationYaw + var7 / (double)this.otherPlayerMPPosRotationIncrements);
         super.rotationPitch = (float)((double)super.rotationPitch + (this.otherPlayerMPPitch - (double)super.rotationPitch) / (double)this.otherPlayerMPPosRotationIncrements);
         --this.otherPlayerMPPosRotationIncrements;
         this.setPosition(var1, var3, var5);
         this.setRotation(super.rotationYaw, super.rotationPitch);
      }

      super.prevCameraYaw = super.cameraYaw;
      float var9 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
      float var2 = (float)Math.atan(-super.motionY * 0.20000000298023224D) * 15.0F;
      if(var9 > 0.1F) {
         var9 = 0.1F;
      }

      if(!super.onGround || this.getHealth() <= 0.0F) {
         var9 = 0.0F;
      }

      if(super.onGround || this.getHealth() <= 0.0F) {
         var2 = 0.0F;
      }

      super.cameraYaw += (var9 - super.cameraYaw) * 0.4F;
      super.cameraPitch += (var2 - super.cameraPitch) * 0.8F;
   }

   public void setCurrentItemOrArmor(int var1, ItemStack var2) {
      if(var1 == 0) {
         super.inventory.mainInventory[super.inventory.currentItem] = var2;
      } else {
         super.inventory.armorInventory[var1 - 1] = var2;
      }

   }

   public float getEyeHeight() {
      return 1.82F;
   }

   public void addChatMessage(IChatComponent var1) {
      Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var1);
   }

   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return false;
   }

   public ChunkCoordinates getPlayerCoordinates() {
      return new ChunkCoordinates(MathHelper.floor_double(super.posX + 0.5D), MathHelper.floor_double(super.posY + 0.5D), MathHelper.floor_double(super.posZ + 0.5D));
   }
}
