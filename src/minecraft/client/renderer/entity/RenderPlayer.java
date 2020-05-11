package net.minecraft.client.renderer.entity;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor$ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;

public class RenderPlayer extends RendererLivingEntity {

   private static final ResourceLocation steveTextures = new ResourceLocation("textures/entity/steve.png");
   private ModelBiped modelBipedMain;
   private ModelBiped modelArmorChestplate;
   private ModelBiped modelArmor;


   public RenderPlayer() {
      super(new ModelBiped(0.0F), 0.5F);
      this.modelBipedMain = (ModelBiped)super.mainModel;
      this.modelArmorChestplate = new ModelBiped(1.0F);
      this.modelArmor = new ModelBiped(0.5F);
   }

   protected int shouldRenderPass(AbstractClientPlayer var1, int var2, float var3) {
      ItemStack var4 = var1.inventory.armorItemInSlot(3 - var2);
      if(var4 != null) {
         Item var5 = var4.getItem();
         if(var5 instanceof ItemArmor) {
            ItemArmor var6 = (ItemArmor)var5;
            this.bindTexture(RenderBiped.func_110857_a(var6, var2));
            ModelBiped var7 = var2 == 2?this.modelArmor:this.modelArmorChestplate;
            var7.bipedHead.showModel = var2 == 0;
            var7.bipedHeadwear.showModel = var2 == 0;
            var7.bipedBody.showModel = var2 == 1 || var2 == 2;
            var7.bipedRightArm.showModel = var2 == 1;
            var7.bipedLeftArm.showModel = var2 == 1;
            var7.bipedRightLeg.showModel = var2 == 2 || var2 == 3;
            var7.bipedLeftLeg.showModel = var2 == 2 || var2 == 3;
            this.setRenderPassModel(var7);
            var7.onGround = super.mainModel.onGround;
            var7.isRiding = super.mainModel.isRiding;
            var7.isChild = super.mainModel.isChild;
            if(var6.getArmorMaterial() == ItemArmor$ArmorMaterial.CLOTH) {
               int var8 = var6.getColor(var4);
               float var9 = (float)(var8 >> 16 & 255) / 255.0F;
               float var10 = (float)(var8 >> 8 & 255) / 255.0F;
               float var11 = (float)(var8 & 255) / 255.0F;
               GL11.glColor3f(var9, var10, var11);
               if(var4.isItemEnchanted()) {
                  return 31;
               }

               return 16;
            }

            GL11.glColor3f(1.0F, 1.0F, 1.0F);
            if(var4.isItemEnchanted()) {
               return 15;
            }

            return 1;
         }
      }

      return -1;
   }

   protected void func_82408_c(AbstractClientPlayer var1, int var2, float var3) {
      ItemStack var4 = var1.inventory.armorItemInSlot(3 - var2);
      if(var4 != null) {
         Item var5 = var4.getItem();
         if(var5 instanceof ItemArmor) {
            this.bindTexture(RenderBiped.func_110858_a((ItemArmor)var5, var2, "overlay"));
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
         }
      }

   }

   public void doRender(AbstractClientPlayer var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      ItemStack var10 = var1.inventory.getCurrentItem();
      this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = var10 != null?1:0;
      if(var10 != null && var1.getItemInUseCount() > 0) {
         EnumAction var11 = var10.getItemUseAction();
         if(var11 == EnumAction.block) {
            this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 3;
         } else if(var11 == EnumAction.bow) {
            this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = true;
         }
      }

      this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = var1.isSneaking();
      double var13 = var4 - (double)var1.yOffset;
      if(var1.isSneaking() && !(var1 instanceof EntityPlayerSP)) {
         var13 -= 0.125D;
      }

      super.doRender((EntityLivingBase)var1, var2, var13, var6, var8, var9);
      this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = false;
      this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = false;
      this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 0;
   }

   protected ResourceLocation getEntityTexture(AbstractClientPlayer var1) {
      return var1.getLocationSkin();
   }

   protected void renderEquippedItems(AbstractClientPlayer var1, float var2) {
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      super.renderEquippedItems(var1, var2);
      super.renderArrowsStuckInEntity(var1, var2);
      ItemStack var3 = var1.inventory.armorItemInSlot(3);
      if(var3 != null) {
         GL11.glPushMatrix();
         this.modelBipedMain.bipedHead.postRender(0.0625F);
         float var4;
         if(var3.getItem() instanceof ItemBlock) {
            if(RenderBlocks.renderItemIn3d(Block.getBlockFromItem(var3.getItem()).getRenderType())) {
               var4 = 0.625F;
               GL11.glTranslatef(0.0F, -0.25F, 0.0F);
               GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScalef(var4, -var4, -var4);
            }

            super.renderManager.itemRenderer.renderItem(var1, var3, 0);
         } else if(var3.getItem() == Items.skull) {
            var4 = 1.0625F;
            GL11.glScalef(var4, -var4, -var4);
            GameProfile var5 = null;
            if(var3.hasTagCompound()) {
               NBTTagCompound var6 = var3.getTagCompound();
               if(var6.hasKey("SkullOwner", 10)) {
                  var5 = NBTUtil.func_152459_a(var6.getCompoundTag("SkullOwner"));
               } else if(var6.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty(var6.getString("SkullOwner"))) {
                  var5 = new GameProfile((UUID)null, var6.getString("SkullOwner"));
               }
            }

            TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var3.getItemDamage(), var5);
         }

         GL11.glPopMatrix();
      }

      float var7;
      if(var1.getCommandSenderName().equals("deadmau5") && var1.func_152123_o()) {
         this.bindTexture(var1.getLocationSkin());

         for(int var20 = 0; var20 < 2; ++var20) {
            float var22 = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var2 - (var1.prevRenderYawOffset + (var1.renderYawOffset - var1.prevRenderYawOffset) * var2);
            float var23 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var2;
            GL11.glPushMatrix();
            GL11.glRotatef(var22, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var23, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.375F * (float)(var20 * 2 - 1), 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.375F, 0.0F);
            GL11.glRotatef(-var23, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-var22, 0.0F, 1.0F, 0.0F);
            var7 = 1.3333334F;
            GL11.glScalef(var7, var7, var7);
            this.modelBipedMain.renderEars(0.0625F);
            GL11.glPopMatrix();
         }
      }

      boolean var21 = var1.func_152122_n();
      float var11;
      if(var21 && !var1.isInvisible() && !var1.getHideCape()) {
         this.bindTexture(var1.getLocationCape());
         GL11.glPushMatrix();
         GL11.glTranslatef(0.0F, 0.0F, 0.125F);
         double var24 = var1.field_71091_bM + (var1.field_71094_bP - var1.field_71091_bM) * (double)var2 - (var1.prevPosX + (var1.posX - var1.prevPosX) * (double)var2);
         double var27 = var1.field_71096_bN + (var1.field_71095_bQ - var1.field_71096_bN) * (double)var2 - (var1.prevPosY + (var1.posY - var1.prevPosY) * (double)var2);
         double var9 = var1.field_71097_bO + (var1.field_71085_bR - var1.field_71097_bO) * (double)var2 - (var1.prevPosZ + (var1.posZ - var1.prevPosZ) * (double)var2);
         var11 = var1.prevRenderYawOffset + (var1.renderYawOffset - var1.prevRenderYawOffset) * var2;
         double var12 = (double)MathHelper.sin(var11 * 3.1415927F / 180.0F);
         double var14 = (double)(-MathHelper.cos(var11 * 3.1415927F / 180.0F));
         float var16 = (float)var27 * 10.0F;
         if(var16 < -6.0F) {
            var16 = -6.0F;
         }

         if(var16 > 32.0F) {
            var16 = 32.0F;
         }

         float var17 = (float)(var24 * var12 + var9 * var14) * 100.0F;
         float var18 = (float)(var24 * var14 - var9 * var12) * 100.0F;
         if(var17 < 0.0F) {
            var17 = 0.0F;
         }

         float var19 = var1.prevCameraYaw + (var1.cameraYaw - var1.prevCameraYaw) * var2;
         var16 += MathHelper.sin((var1.prevDistanceWalkedModified + (var1.distanceWalkedModified - var1.prevDistanceWalkedModified) * var2) * 6.0F) * 32.0F * var19;
         if(var1.isSneaking()) {
            var16 += 25.0F;
         }

         GL11.glRotatef(6.0F + var17 / 2.0F + var16, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(var18 / 2.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-var18 / 2.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
         this.modelBipedMain.renderCloak(0.0625F);
         GL11.glPopMatrix();
      }

      ItemStack var26 = var1.inventory.getCurrentItem();
      if(var26 != null) {
         GL11.glPushMatrix();
         this.modelBipedMain.bipedRightArm.postRender(0.0625F);
         GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
         if(var1.fishEntity != null) {
            var26 = new ItemStack(Items.stick);
         }

         EnumAction var25 = null;
         if(var1.getItemInUseCount() > 0) {
            var25 = var26.getItemUseAction();
         }

         if(var26.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(var26.getItem()).getRenderType())) {
            var7 = 0.5F;
            GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
            var7 *= 0.75F;
            GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(-var7, -var7, var7);
         } else if(var26.getItem() == Items.bow) {
            var7 = 0.625F;
            GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
            GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(var7, -var7, var7);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         } else if(var26.getItem().isFull3D()) {
            var7 = 0.625F;
            if(var26.getItem().shouldRotateAroundWhenRendering()) {
               GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
               GL11.glTranslatef(0.0F, -0.125F, 0.0F);
            }

            if(var1.getItemInUseCount() > 0 && var25 == EnumAction.block) {
               GL11.glTranslatef(0.05F, 0.0F, -0.1F);
               GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
            }

            GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
            GL11.glScalef(var7, -var7, var7);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         } else {
            var7 = 0.375F;
            GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
            GL11.glScalef(var7, var7, var7);
            GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         }

         float var10;
         int var29;
         float var30;
         if(var26.getItem().requiresMultipleRenderPasses()) {
            for(var29 = 0; var29 <= 1; ++var29) {
               int var8 = var26.getItem().getColorFromItemStack(var26, var29);
               var30 = (float)(var8 >> 16 & 255) / 255.0F;
               var10 = (float)(var8 >> 8 & 255) / 255.0F;
               var11 = (float)(var8 & 255) / 255.0F;
               GL11.glColor4f(var30, var10, var11, 1.0F);
               super.renderManager.itemRenderer.renderItem(var1, var26, var29);
            }
         } else {
            var29 = var26.getItem().getColorFromItemStack(var26, 0);
            float var28 = (float)(var29 >> 16 & 255) / 255.0F;
            var30 = (float)(var29 >> 8 & 255) / 255.0F;
            var10 = (float)(var29 & 255) / 255.0F;
            GL11.glColor4f(var28, var30, var10, 1.0F);
            super.renderManager.itemRenderer.renderItem(var1, var26, 0);
         }

         GL11.glPopMatrix();
      }

   }

   protected void preRenderCallback(AbstractClientPlayer var1, float var2) {
      float var3 = 0.9375F;
      GL11.glScalef(var3, var3, var3);
   }

   protected void func_96449_a(AbstractClientPlayer var1, double var2, double var4, double var6, String var8, float var9, double var10) {
      if(var10 < 100.0D) {
         Scoreboard var12 = var1.getWorldScoreboard();
         ScoreObjective var13 = var12.func_96539_a(2);
         if(var13 != null) {
            Score var14 = var12.func_96529_a(var1.getCommandSenderName(), var13);
            if(var1.isPlayerSleeping()) {
               this.func_147906_a(var1, var14.getScorePoints() + " " + var13.getDisplayName(), var2, var4 - 1.5D, var6, 64);
            } else {
               this.func_147906_a(var1, var14.getScorePoints() + " " + var13.getDisplayName(), var2, var4, var6, 64);
            }

            var4 += (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * var9);
         }
      }

      super.func_96449_a(var1, var2, var4, var6, var8, var9, var10);
   }

   public void renderFirstPersonArm(EntityPlayer var1) {
      float var2 = 1.0F;
      GL11.glColor3f(var2, var2, var2);
      this.modelBipedMain.onGround = 0.0F;
      this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, var1);
      this.modelBipedMain.bipedRightArm.render(0.0625F);
   }

   protected void renderLivingAt(AbstractClientPlayer var1, double var2, double var4, double var6) {
      if(var1.isEntityAlive() && var1.isPlayerSleeping()) {
         super.renderLivingAt(var1, var2 + (double)var1.field_71079_bU, var4 + (double)var1.field_71082_cx, var6 + (double)var1.field_71089_bV);
      } else {
         super.renderLivingAt(var1, var2, var4, var6);
      }

   }

   protected void rotateCorpse(AbstractClientPlayer var1, float var2, float var3, float var4) {
      if(var1.isEntityAlive() && var1.isPlayerSleeping()) {
         GL11.glRotatef(var1.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(this.getDeathMaxRotation(var1), 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
      } else {
         super.rotateCorpse(var1, var2, var3, var4);
      }

   }

   // $FF: synthetic method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((AbstractClientPlayer)var1, var2);
   }

   // $FF: synthetic method
   protected void func_82408_c(EntityLivingBase var1, int var2, float var3) {
      this.func_82408_c((AbstractClientPlayer)var1, var2, var3);
   }

   // $FF: synthetic method
   protected void renderEquippedItems(EntityLivingBase var1, float var2) {
      this.renderEquippedItems((AbstractClientPlayer)var1, var2);
   }

}
