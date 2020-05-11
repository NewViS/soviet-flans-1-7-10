package net.minecraft.client.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.client.particle.EntityPickupFX;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MouseFilter;
import net.minecraft.util.MovementInput;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.world.World;

public class EntityPlayerSP extends AbstractClientPlayer {

   public MovementInput movementInput;
   protected Minecraft mc;
   protected int sprintToggleTimer;
   public int sprintingTicksLeft;
   public float renderArmYaw;
   public float renderArmPitch;
   public float prevRenderArmYaw;
   public float prevRenderArmPitch;
   private int horseJumpPowerCounter;
   private float horseJumpPower;
   private MouseFilter field_71162_ch = new MouseFilter();
   private MouseFilter field_71160_ci = new MouseFilter();
   private MouseFilter field_71161_cj = new MouseFilter();
   public float timeInPortal;
   public float prevTimeInPortal;


   public EntityPlayerSP(Minecraft var1, World var2, Session var3, int var4) {
      super(var2, var3.func_148256_e());
      this.mc = var1;
      super.dimension = var4;
   }

   public void updateEntityActionState() {
      super.updateEntityActionState();
      super.moveStrafing = this.movementInput.moveStrafe;
      super.moveForward = this.movementInput.moveForward;
      super.isJumping = this.movementInput.jump;
      this.prevRenderArmYaw = this.renderArmYaw;
      this.prevRenderArmPitch = this.renderArmPitch;
      this.renderArmPitch = (float)((double)this.renderArmPitch + (double)(super.rotationPitch - this.renderArmPitch) * 0.5D);
      this.renderArmYaw = (float)((double)this.renderArmYaw + (double)(super.rotationYaw - this.renderArmYaw) * 0.5D);
   }

   public void onLivingUpdate() {
      if(this.sprintingTicksLeft > 0) {
         --this.sprintingTicksLeft;
         if(this.sprintingTicksLeft == 0) {
            this.setSprinting(false);
         }
      }

      if(this.sprintToggleTimer > 0) {
         --this.sprintToggleTimer;
      }

      if(this.mc.playerController.enableEverythingIsScrewedUpMode()) {
         super.posX = super.posZ = 0.5D;
         super.posX = 0.0D;
         super.posZ = 0.0D;
         super.rotationYaw = (float)super.ticksExisted / 12.0F;
         super.rotationPitch = 10.0F;
         super.posY = 68.5D;
      } else {
         this.prevTimeInPortal = this.timeInPortal;
         if(super.inPortal) {
            if(this.mc.currentScreen != null) {
               this.mc.displayGuiScreen((GuiScreen)null);
            }

            if(this.timeInPortal == 0.0F) {
               this.mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("portal.trigger"), super.rand.nextFloat() * 0.4F + 0.8F));
            }

            this.timeInPortal += 0.0125F;
            if(this.timeInPortal >= 1.0F) {
               this.timeInPortal = 1.0F;
            }

            super.inPortal = false;
         } else if(this.isPotionActive(Potion.confusion) && this.getActivePotionEffect(Potion.confusion).getDuration() > 60) {
            this.timeInPortal += 0.006666667F;
            if(this.timeInPortal > 1.0F) {
               this.timeInPortal = 1.0F;
            }
         } else {
            if(this.timeInPortal > 0.0F) {
               this.timeInPortal -= 0.05F;
            }

            if(this.timeInPortal < 0.0F) {
               this.timeInPortal = 0.0F;
            }
         }

         if(super.timeUntilPortal > 0) {
            --super.timeUntilPortal;
         }

         boolean var1 = this.movementInput.jump;
         float var2 = 0.8F;
         boolean var3 = this.movementInput.moveForward >= var2;
         this.movementInput.updatePlayerMoveState();
         if(this.isUsingItem() && !this.isRiding()) {
            this.movementInput.moveStrafe *= 0.2F;
            this.movementInput.moveForward *= 0.2F;
            this.sprintToggleTimer = 0;
         }

         if(this.movementInput.sneak && super.ySize < 0.2F) {
            super.ySize = 0.2F;
         }

         this.func_145771_j(super.posX - (double)super.width * 0.35D, super.boundingBox.minY + 0.5D, super.posZ + (double)super.width * 0.35D);
         this.func_145771_j(super.posX - (double)super.width * 0.35D, super.boundingBox.minY + 0.5D, super.posZ - (double)super.width * 0.35D);
         this.func_145771_j(super.posX + (double)super.width * 0.35D, super.boundingBox.minY + 0.5D, super.posZ - (double)super.width * 0.35D);
         this.func_145771_j(super.posX + (double)super.width * 0.35D, super.boundingBox.minY + 0.5D, super.posZ + (double)super.width * 0.35D);
         boolean var4 = (float)this.getFoodStats().getFoodLevel() > 6.0F || super.capabilities.allowFlying;
         if(super.onGround && !var3 && this.movementInput.moveForward >= var2 && !this.isSprinting() && var4 && !this.isUsingItem() && !this.isPotionActive(Potion.blindness)) {
            if(this.sprintToggleTimer <= 0 && !this.mc.gameSettings.keyBindSprint.getIsKeyPressed()) {
               this.sprintToggleTimer = 7;
            } else {
               this.setSprinting(true);
            }
         }

         if(!this.isSprinting() && this.movementInput.moveForward >= var2 && var4 && !this.isUsingItem() && !this.isPotionActive(Potion.blindness) && this.mc.gameSettings.keyBindSprint.getIsKeyPressed()) {
            this.setSprinting(true);
         }

         if(this.isSprinting() && (this.movementInput.moveForward < var2 || super.isCollidedHorizontally || !var4)) {
            this.setSprinting(false);
         }

         if(super.capabilities.allowFlying && !var1 && this.movementInput.jump) {
            if(super.flyToggleTimer == 0) {
               super.flyToggleTimer = 7;
            } else {
               super.capabilities.isFlying = !super.capabilities.isFlying;
               this.sendPlayerAbilities();
               super.flyToggleTimer = 0;
            }
         }

         if(super.capabilities.isFlying) {
            if(this.movementInput.sneak) {
               super.motionY -= 0.15D;
            }

            if(this.movementInput.jump) {
               super.motionY += 0.15D;
            }
         }

         if(this.isRidingHorse()) {
            if(this.horseJumpPowerCounter < 0) {
               ++this.horseJumpPowerCounter;
               if(this.horseJumpPowerCounter == 0) {
                  this.horseJumpPower = 0.0F;
               }
            }

            if(var1 && !this.movementInput.jump) {
               this.horseJumpPowerCounter = -10;
               this.func_110318_g();
            } else if(!var1 && this.movementInput.jump) {
               this.horseJumpPowerCounter = 0;
               this.horseJumpPower = 0.0F;
            } else if(var1) {
               ++this.horseJumpPowerCounter;
               if(this.horseJumpPowerCounter < 10) {
                  this.horseJumpPower = (float)this.horseJumpPowerCounter * 0.1F;
               } else {
                  this.horseJumpPower = 0.8F + 2.0F / (float)(this.horseJumpPowerCounter - 9) * 0.1F;
               }
            }
         } else {
            this.horseJumpPower = 0.0F;
         }

         super.onLivingUpdate();
         if(super.onGround && super.capabilities.isFlying) {
            super.capabilities.isFlying = false;
            this.sendPlayerAbilities();
         }

      }
   }

   public float getFOVMultiplier() {
      float var1 = 1.0F;
      if(super.capabilities.isFlying) {
         var1 *= 1.1F;
      }

      IAttributeInstance var2 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
      var1 = (float)((double)var1 * ((var2.getAttributeValue() / (double)super.capabilities.getWalkSpeed() + 1.0D) / 2.0D));
      if(super.capabilities.getWalkSpeed() == 0.0F || Float.isNaN(var1) || Float.isInfinite(var1)) {
         var1 = 1.0F;
      }

      if(this.isUsingItem() && this.getItemInUse().getItem() == Items.bow) {
         int var3 = this.getItemInUseDuration();
         float var4 = (float)var3 / 20.0F;
         if(var4 > 1.0F) {
            var4 = 1.0F;
         } else {
            var4 *= var4;
         }

         var1 *= 1.0F - var4 * 0.15F;
      }

      return var1;
   }

   public void closeScreen() {
      super.closeScreen();
      this.mc.displayGuiScreen((GuiScreen)null);
   }

   public void func_146100_a(TileEntity var1) {
      if(var1 instanceof TileEntitySign) {
         this.mc.displayGuiScreen(new GuiEditSign((TileEntitySign)var1));
      } else if(var1 instanceof TileEntityCommandBlock) {
         this.mc.displayGuiScreen(new GuiCommandBlock(((TileEntityCommandBlock)var1).func_145993_a()));
      }

   }

   public void func_146095_a(CommandBlockLogic var1) {
      this.mc.displayGuiScreen(new GuiCommandBlock(var1));
   }

   public void displayGUIBook(ItemStack var1) {
      Item var2 = var1.getItem();
      if(var2 == Items.written_book) {
         this.mc.displayGuiScreen(new GuiScreenBook(this, var1, false));
      } else if(var2 == Items.writable_book) {
         this.mc.displayGuiScreen(new GuiScreenBook(this, var1, true));
      }

   }

   public void displayGUIChest(IInventory var1) {
      this.mc.displayGuiScreen(new GuiChest(super.inventory, var1));
   }

   public void func_146093_a(TileEntityHopper var1) {
      this.mc.displayGuiScreen(new GuiHopper(super.inventory, var1));
   }

   public void displayGUIHopperMinecart(EntityMinecartHopper var1) {
      this.mc.displayGuiScreen(new GuiHopper(super.inventory, var1));
   }

   public void displayGUIHorse(EntityHorse var1, IInventory var2) {
      this.mc.displayGuiScreen(new GuiScreenHorseInventory(super.inventory, var2, var1));
   }

   public void displayGUIWorkbench(int var1, int var2, int var3) {
      this.mc.displayGuiScreen(new GuiCrafting(super.inventory, super.worldObj, var1, var2, var3));
   }

   public void displayGUIEnchantment(int var1, int var2, int var3, String var4) {
      this.mc.displayGuiScreen(new GuiEnchantment(super.inventory, super.worldObj, var1, var2, var3, var4));
   }

   public void displayGUIAnvil(int var1, int var2, int var3) {
      this.mc.displayGuiScreen(new GuiRepair(super.inventory, super.worldObj, var1, var2, var3));
   }

   public void func_146101_a(TileEntityFurnace var1) {
      this.mc.displayGuiScreen(new GuiFurnace(super.inventory, var1));
   }

   public void func_146098_a(TileEntityBrewingStand var1) {
      this.mc.displayGuiScreen(new GuiBrewingStand(super.inventory, var1));
   }

   public void func_146104_a(TileEntityBeacon var1) {
      this.mc.displayGuiScreen(new GuiBeacon(super.inventory, var1));
   }

   public void func_146102_a(TileEntityDispenser var1) {
      this.mc.displayGuiScreen(new GuiDispenser(super.inventory, var1));
   }

   public void displayGUIMerchant(IMerchant var1, String var2) {
      this.mc.displayGuiScreen(new GuiMerchant(super.inventory, var1, super.worldObj, var2));
   }

   public void onCriticalHit(Entity var1) {
      this.mc.effectRenderer.addEffect(new EntityCrit2FX(this.mc.theWorld, var1));
   }

   public void onEnchantmentCritical(Entity var1) {
      EntityCrit2FX var2 = new EntityCrit2FX(this.mc.theWorld, var1, "magicCrit");
      this.mc.effectRenderer.addEffect(var2);
   }

   public void onItemPickup(Entity var1, int var2) {
      this.mc.effectRenderer.addEffect(new EntityPickupFX(this.mc.theWorld, var1, this, -0.5F));
   }

   public boolean isSneaking() {
      return this.movementInput.sneak && !super.sleeping;
   }

   public void setPlayerSPHealth(float var1) {
      float var2 = this.getHealth() - var1;
      if(var2 <= 0.0F) {
         this.setHealth(var1);
         if(var2 < 0.0F) {
            super.hurtResistantTime = super.maxHurtResistantTime / 2;
         }
      } else {
         super.lastDamage = var2;
         this.setHealth(this.getHealth());
         super.hurtResistantTime = super.maxHurtResistantTime;
         this.damageEntity(DamageSource.generic, var2);
         super.hurtTime = super.maxHurtTime = 10;
      }

   }

   public void addChatComponentMessage(IChatComponent var1) {
      this.mc.ingameGUI.getChatGUI().printChatMessage(var1);
   }

   private boolean isBlockTranslucent(int var1, int var2, int var3) {
      return super.worldObj.getBlock(var1, var2, var3).isNormalCube();
   }

   protected boolean func_145771_j(double var1, double var3, double var5) {
      int var7 = MathHelper.floor_double(var1);
      int var8 = MathHelper.floor_double(var3);
      int var9 = MathHelper.floor_double(var5);
      double var10 = var1 - (double)var7;
      double var12 = var5 - (double)var9;
      if(this.isBlockTranslucent(var7, var8, var9) || this.isBlockTranslucent(var7, var8 + 1, var9)) {
         boolean var14 = !this.isBlockTranslucent(var7 - 1, var8, var9) && !this.isBlockTranslucent(var7 - 1, var8 + 1, var9);
         boolean var15 = !this.isBlockTranslucent(var7 + 1, var8, var9) && !this.isBlockTranslucent(var7 + 1, var8 + 1, var9);
         boolean var16 = !this.isBlockTranslucent(var7, var8, var9 - 1) && !this.isBlockTranslucent(var7, var8 + 1, var9 - 1);
         boolean var17 = !this.isBlockTranslucent(var7, var8, var9 + 1) && !this.isBlockTranslucent(var7, var8 + 1, var9 + 1);
         byte var18 = -1;
         double var19 = 9999.0D;
         if(var14 && var10 < var19) {
            var19 = var10;
            var18 = 0;
         }

         if(var15 && 1.0D - var10 < var19) {
            var19 = 1.0D - var10;
            var18 = 1;
         }

         if(var16 && var12 < var19) {
            var19 = var12;
            var18 = 4;
         }

         if(var17 && 1.0D - var12 < var19) {
            var19 = 1.0D - var12;
            var18 = 5;
         }

         float var21 = 0.1F;
         if(var18 == 0) {
            super.motionX = (double)(-var21);
         }

         if(var18 == 1) {
            super.motionX = (double)var21;
         }

         if(var18 == 4) {
            super.motionZ = (double)(-var21);
         }

         if(var18 == 5) {
            super.motionZ = (double)var21;
         }
      }

      return false;
   }

   public void setSprinting(boolean var1) {
      super.setSprinting(var1);
      this.sprintingTicksLeft = var1?600:0;
   }

   public void setXPStats(float var1, int var2, int var3) {
      super.experience = var1;
      super.experienceTotal = var2;
      super.experienceLevel = var3;
   }

   public void addChatMessage(IChatComponent var1) {
      this.mc.ingameGUI.getChatGUI().printChatMessage(var1);
   }

   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return var1 <= 0;
   }

   public ChunkCoordinates getPlayerCoordinates() {
      return new ChunkCoordinates(MathHelper.floor_double(super.posX + 0.5D), MathHelper.floor_double(super.posY + 0.5D), MathHelper.floor_double(super.posZ + 0.5D));
   }

   public void playSound(String var1, float var2, float var3) {
      super.worldObj.playSound(super.posX, super.posY - (double)super.yOffset, super.posZ, var1, var2, var3, false);
   }

   public boolean isClientWorld() {
      return true;
   }

   public boolean isRidingHorse() {
      return super.ridingEntity != null && super.ridingEntity instanceof EntityHorse;
   }

   public float getHorseJumpPower() {
      return this.horseJumpPower;
   }

   protected void func_110318_g() {}
}
