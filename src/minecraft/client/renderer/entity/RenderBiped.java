package net.minecraft.client.renderer.entity;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor$ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;

public class RenderBiped extends RenderLiving {

   protected ModelBiped modelBipedMain;
   protected float field_77070_b;
   protected ModelBiped field_82423_g;
   protected ModelBiped field_82425_h;
   private static final Map field_110859_k = Maps.newHashMap();
   private static final String[] bipedArmorFilenamePrefix = new String[]{"leather", "chainmail", "iron", "diamond", "gold"};


   public RenderBiped(ModelBiped var1, float var2) {
      this(var1, var2, 1.0F);
   }

   public RenderBiped(ModelBiped var1, float var2, float var3) {
      super(var1, var2);
      this.modelBipedMain = var1;
      this.field_77070_b = var3;
      this.func_82421_b();
   }

   protected void func_82421_b() {
      this.field_82423_g = new ModelBiped(1.0F);
      this.field_82425_h = new ModelBiped(0.5F);
   }

   public static ResourceLocation func_110857_a(ItemArmor var0, int var1) {
      return func_110858_a(var0, var1, (String)null);
   }

   public static ResourceLocation func_110858_a(ItemArmor var0, int var1, String var2) {
      String var3 = String.format("textures/models/armor/%s_layer_%d%s.png", new Object[]{bipedArmorFilenamePrefix[var0.renderIndex], Integer.valueOf(var1 == 2?2:1), var2 == null?"":String.format("_%s", new Object[]{var2})});
      ResourceLocation var4 = (ResourceLocation)field_110859_k.get(var3);
      if(var4 == null) {
         var4 = new ResourceLocation(var3);
         field_110859_k.put(var3, var4);
      }

      return var4;
   }

   protected int shouldRenderPass(EntityLiving var1, int var2, float var3) {
      ItemStack var4 = var1.func_130225_q(3 - var2);
      if(var4 != null) {
         Item var5 = var4.getItem();
         if(var5 instanceof ItemArmor) {
            ItemArmor var6 = (ItemArmor)var5;
            this.bindTexture(func_110857_a(var6, var2));
            ModelBiped var7 = var2 == 2?this.field_82425_h:this.field_82423_g;
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

   protected void func_82408_c(EntityLiving var1, int var2, float var3) {
      ItemStack var4 = var1.func_130225_q(3 - var2);
      if(var4 != null) {
         Item var5 = var4.getItem();
         if(var5 instanceof ItemArmor) {
            this.bindTexture(func_110858_a((ItemArmor)var5, var2, "overlay"));
            float var6 = 1.0F;
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
         }
      }

   }

   public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      ItemStack var10 = var1.getHeldItem();
      this.func_82420_a(var1, var10);
      double var11 = var4 - (double)var1.yOffset;
      if(var1.isSneaking()) {
         var11 -= 0.125D;
      }

      super.doRender(var1, var2, var11, var6, var8, var9);
      this.field_82423_g.aimedBow = this.field_82425_h.aimedBow = this.modelBipedMain.aimedBow = false;
      this.field_82423_g.isSneak = this.field_82425_h.isSneak = this.modelBipedMain.isSneak = false;
      this.field_82423_g.heldItemRight = this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = 0;
   }

   protected ResourceLocation getEntityTexture(EntityLiving var1) {
      return null;
   }

   protected void func_82420_a(EntityLiving var1, ItemStack var2) {
      this.field_82423_g.heldItemRight = this.field_82425_h.heldItemRight = this.modelBipedMain.heldItemRight = var2 != null?1:0;
      this.field_82423_g.isSneak = this.field_82425_h.isSneak = this.modelBipedMain.isSneak = var1.isSneaking();
   }

   protected void renderEquippedItems(EntityLiving var1, float var2) {
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      super.renderEquippedItems(var1, var2);
      ItemStack var3 = var1.getHeldItem();
      ItemStack var4 = var1.func_130225_q(3);
      Item var5;
      float var6;
      if(var4 != null) {
         GL11.glPushMatrix();
         this.modelBipedMain.bipedHead.postRender(0.0625F);
         var5 = var4.getItem();
         if(var5 instanceof ItemBlock) {
            if(RenderBlocks.renderItemIn3d(Block.getBlockFromItem(var5).getRenderType())) {
               var6 = 0.625F;
               GL11.glTranslatef(0.0F, -0.25F, 0.0F);
               GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScalef(var6, -var6, -var6);
            }

            super.renderManager.itemRenderer.renderItem(var1, var4, 0);
         } else if(var5 == Items.skull) {
            var6 = 1.0625F;
            GL11.glScalef(var6, -var6, -var6);
            GameProfile var7 = null;
            if(var4.hasTagCompound()) {
               NBTTagCompound var8 = var4.getTagCompound();
               if(var8.hasKey("SkullOwner", 10)) {
                  var7 = NBTUtil.func_152459_a(var8.getCompoundTag("SkullOwner"));
               } else if(var8.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty(var8.getString("SkullOwner"))) {
                  var7 = new GameProfile((UUID)null, var8.getString("SkullOwner"));
               }
            }

            TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var4.getItemDamage(), var7);
         }

         GL11.glPopMatrix();
      }

      if(var3 != null && var3.getItem() != null) {
         var5 = var3.getItem();
         GL11.glPushMatrix();
         if(super.mainModel.isChild) {
            var6 = 0.5F;
            GL11.glTranslatef(0.0F, 0.625F, 0.0F);
            GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
            GL11.glScalef(var6, var6, var6);
         }

         this.modelBipedMain.bipedRightArm.postRender(0.0625F);
         GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
         if(var5 instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(var5).getRenderType())) {
            var6 = 0.5F;
            GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
            var6 *= 0.75F;
            GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(-var6, -var6, var6);
         } else if(var5 == Items.bow) {
            var6 = 0.625F;
            GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
            GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(var6, -var6, var6);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         } else if(var5.isFull3D()) {
            var6 = 0.625F;
            if(var5.shouldRotateAroundWhenRendering()) {
               GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
               GL11.glTranslatef(0.0F, -0.125F, 0.0F);
            }

            this.func_82422_c();
            GL11.glScalef(var6, -var6, var6);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         } else {
            var6 = 0.375F;
            GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
            GL11.glScalef(var6, var6, var6);
            GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         }

         float var9;
         float var12;
         int var14;
         if(var3.getItem().requiresMultipleRenderPasses()) {
            for(var14 = 0; var14 <= 1; ++var14) {
               int var11 = var3.getItem().getColorFromItemStack(var3, var14);
               var12 = (float)(var11 >> 16 & 255) / 255.0F;
               var9 = (float)(var11 >> 8 & 255) / 255.0F;
               float var10 = (float)(var11 & 255) / 255.0F;
               GL11.glColor4f(var12, var9, var10, 1.0F);
               super.renderManager.itemRenderer.renderItem(var1, var3, var14);
            }
         } else {
            var14 = var3.getItem().getColorFromItemStack(var3, 0);
            float var13 = (float)(var14 >> 16 & 255) / 255.0F;
            var12 = (float)(var14 >> 8 & 255) / 255.0F;
            var9 = (float)(var14 & 255) / 255.0F;
            GL11.glColor4f(var13, var12, var9, 1.0F);
            super.renderManager.itemRenderer.renderItem(var1, var3, 0);
         }

         GL11.glPopMatrix();
      }

   }

   protected void func_82422_c() {
      GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
   }

   // $FF: synthetic method
   protected void func_82408_c(EntityLivingBase var1, int var2, float var3) {
      this.func_82408_c((EntityLiving)var1, var2, var3);
   }

   // $FF: synthetic method
   protected void renderEquippedItems(EntityLivingBase var1, float var2) {
      this.renderEquippedItems((EntityLiving)var1, var2);
   }

}
