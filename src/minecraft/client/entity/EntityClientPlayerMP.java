package net.minecraft.client.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSoundMinecartRiding;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer$C04PacketPlayerPosition;
import net.minecraft.network.play.client.C03PacketPlayer$C05PacketPlayerLook;
import net.minecraft.network.play.client.C03PacketPlayer$C06PacketPlayerPosLook;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C16PacketClientStatus$EnumState;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Session;
import net.minecraft.world.World;

public class EntityClientPlayerMP extends EntityPlayerSP {

   public final NetHandlerPlayClient sendQueue;
   private final StatFileWriter field_146108_bO;
   private double oldPosX;
   private double oldMinY;
   private double oldPosY;
   private double oldPosZ;
   private float oldRotationYaw;
   private float oldRotationPitch;
   private boolean wasOnGround;
   private boolean wasSneaking;
   private boolean wasSprinting;
   private int ticksSinceMovePacket;
   private boolean hasSetHealth;
   private String field_142022_ce;


   public EntityClientPlayerMP(Minecraft var1, World var2, Session var3, NetHandlerPlayClient var4, StatFileWriter var5) {
      super(var1, var2, var3, 0);
      this.sendQueue = var4;
      this.field_146108_bO = var5;
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      return false;
   }

   public void heal(float var1) {}

   public void mountEntity(Entity var1) {
      super.mountEntity(var1);
      if(var1 instanceof EntityMinecart) {
         super.mc.getSoundHandler().playSound(new MovingSoundMinecartRiding(this, (EntityMinecart)var1));
      }

   }

   public void onUpdate() {
      if(super.worldObj.blockExists(MathHelper.floor_double(super.posX), 0, MathHelper.floor_double(super.posZ))) {
         super.onUpdate();
         if(this.isRiding()) {
            this.sendQueue.addToSendQueue(new C03PacketPlayer$C05PacketPlayerLook(super.rotationYaw, super.rotationPitch, super.onGround));
            this.sendQueue.addToSendQueue(new C0CPacketInput(super.moveStrafing, super.moveForward, super.movementInput.jump, super.movementInput.sneak));
         } else {
            this.sendMotionUpdates();
         }

      }
   }

   public void sendMotionUpdates() {
      boolean var1 = this.isSprinting();
      if(var1 != this.wasSprinting) {
         if(var1) {
            this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, 4));
         } else {
            this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, 5));
         }

         this.wasSprinting = var1;
      }

      boolean var2 = this.isSneaking();
      if(var2 != this.wasSneaking) {
         if(var2) {
            this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, 1));
         } else {
            this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, 2));
         }

         this.wasSneaking = var2;
      }

      double var3 = super.posX - this.oldPosX;
      double var5 = super.boundingBox.minY - this.oldMinY;
      double var7 = super.posZ - this.oldPosZ;
      double var9 = (double)(super.rotationYaw - this.oldRotationYaw);
      double var11 = (double)(super.rotationPitch - this.oldRotationPitch);
      boolean var13 = var3 * var3 + var5 * var5 + var7 * var7 > 9.0E-4D || this.ticksSinceMovePacket >= 20;
      boolean var14 = var9 != 0.0D || var11 != 0.0D;
      if(super.ridingEntity != null) {
         this.sendQueue.addToSendQueue(new C03PacketPlayer$C06PacketPlayerPosLook(super.motionX, -999.0D, -999.0D, super.motionZ, super.rotationYaw, super.rotationPitch, super.onGround));
         var13 = false;
      } else if(var13 && var14) {
         this.sendQueue.addToSendQueue(new C03PacketPlayer$C06PacketPlayerPosLook(super.posX, super.boundingBox.minY, super.posY, super.posZ, super.rotationYaw, super.rotationPitch, super.onGround));
      } else if(var13) {
         this.sendQueue.addToSendQueue(new C03PacketPlayer$C04PacketPlayerPosition(super.posX, super.boundingBox.minY, super.posY, super.posZ, super.onGround));
      } else if(var14) {
         this.sendQueue.addToSendQueue(new C03PacketPlayer$C05PacketPlayerLook(super.rotationYaw, super.rotationPitch, super.onGround));
      } else {
         this.sendQueue.addToSendQueue(new C03PacketPlayer(super.onGround));
      }

      ++this.ticksSinceMovePacket;
      this.wasOnGround = super.onGround;
      if(var13) {
         this.oldPosX = super.posX;
         this.oldMinY = super.boundingBox.minY;
         this.oldPosY = super.posY;
         this.oldPosZ = super.posZ;
         this.ticksSinceMovePacket = 0;
      }

      if(var14) {
         this.oldRotationYaw = super.rotationYaw;
         this.oldRotationPitch = super.rotationPitch;
      }

   }

   public EntityItem dropOneItem(boolean var1) {
      int var2 = var1?3:4;
      this.sendQueue.addToSendQueue(new C07PacketPlayerDigging(var2, 0, 0, 0, 0));
      return null;
   }

   protected void joinEntityItemWithWorld(EntityItem var1) {}

   public void sendChatMessage(String var1) {
      this.sendQueue.addToSendQueue(new C01PacketChatMessage(var1));
   }

   public void swingItem() {
      super.swingItem();
      this.sendQueue.addToSendQueue(new C0APacketAnimation(this, 1));
   }

   public void respawnPlayer() {
      this.sendQueue.addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus$EnumState.PERFORM_RESPAWN));
   }

   protected void damageEntity(DamageSource var1, float var2) {
      if(!this.isEntityInvulnerable()) {
         this.setHealth(this.getHealth() - var2);
      }
   }

   public void closeScreen() {
      this.sendQueue.addToSendQueue(new C0DPacketCloseWindow(super.openContainer.windowId));
      this.closeScreenNoPacket();
   }

   public void closeScreenNoPacket() {
      super.inventory.setItemStack((ItemStack)null);
      super.closeScreen();
   }

   public void setPlayerSPHealth(float var1) {
      if(this.hasSetHealth) {
         super.setPlayerSPHealth(var1);
      } else {
         this.setHealth(var1);
         this.hasSetHealth = true;
      }

   }

   public void addStat(StatBase var1, int var2) {
      if(var1 != null) {
         if(var1.isIndependent) {
            super.addStat(var1, var2);
         }

      }
   }

   public void sendPlayerAbilities() {
      this.sendQueue.addToSendQueue(new C13PacketPlayerAbilities(super.capabilities));
   }

   protected void func_110318_g() {
      this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, 6, (int)(this.getHorseJumpPower() * 100.0F)));
   }

   public void func_110322_i() {
      this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, 7));
   }

   public void func_142020_c(String var1) {
      this.field_142022_ce = var1;
   }

   public String func_142021_k() {
      return this.field_142022_ce;
   }

   public StatFileWriter getStatFileWriter() {
      return this.field_146108_bO;
   }
}
