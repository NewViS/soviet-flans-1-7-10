package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import com.mojang.authlib.GameProfile;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.config.RealLifeModConfig;
import de.ItsAMysterious.mods.reallifemod.core.items.AK47Item;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.client.event.RenderPlayerEvent.Post;
import net.minecraftforge.client.event.RenderPlayerEvent.Specials.Pre;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class RealLifeModPlayerRenderer extends RenderPlayer {

   protected IModelCustom model;
   protected IModelCustom modelfemale;
   protected IModelCustom modelHead;
   protected IModelCustom leftArm;
   protected IModelCustom rightArm;
   protected IModelCustom leftleg;
   protected IModelCustom hairsfemale;
   protected IModelCustom rightleg;
   protected IModelCustom hairs;
   protected IModelCustom rightleg_thigh;
   protected IModelCustom leftleg_thigh;
   protected IModelCustom modelCloak;
   protected IModelCustom modelEars;
   protected IModelCustom headwear;
   private float bodyRotY;
   private float headRotX;
   private float rightArmRotZ = 0.0F;
   private float leftArmRotY = 0.0F;
   private float leftArmRotX = 0.0F;
   private float rightArmRotX = 0.0F;
   private float rightArmRotY = 0.0F;
   private float headRotY;
   private boolean aimedBow;
   private float bodyRotX;
   private boolean isSneak;
   private float headwearRotY = 0.0F;
   private float headwearRotX = 0.0F;
   private float rightlegRotX = 0.0F;
   private float leftlegRotX = 0.0F;
   private float rightlegRotY = 0.0F;
   private float leftlegRotY = 0.0F;
   private ItemStack heldItemLeft;
   private ItemStack heldItemRight;
   private float onGround;
   private float rightarmRotPointZ = 0.0F;
   private float rightarmRotPointX = 0.0F;
   private float leftarmRotPointZ = 0.0F;
   private float leftarmRotPointX = 0.0F;
   private boolean isRiding;
   private float delta = 0.0F;
   private float rightlegRotZ;
   private float leftlegRotZ;
   private float bodyRotZ;
   private float leftArmRotZ;
   private float rightArmRotWater;
   private double rightArmRotWaterX;
   private double swimmingdelta;
   private float rightlegKneeRotX;
   private float leftlegKneeRotX;
   private float d;
   public int KeyFrameArmHit;
   public int KeyFrameSwimming;
   public static List HitAnimation = new ArrayList(31);
   public static List SwimAnimationRight = new ArrayList(80);
   public static List SwimAnimationLeft = new ArrayList(80);
   protected RealLifeModPlayerRenderer.HitStates hitstate;
   private Object state;


   public static FloatBuffer allocFloats(int howmany) {
      return ByteBuffer.allocateDirect(howmany * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
   }

   public static FloatBuffer allocFloats(float[] floatarray) {
      FloatBuffer fb = ByteBuffer.allocateDirect(floatarray.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
      fb.put(floatarray).flip();
      return fb;
   }

   public RealLifeModPlayerRenderer() {
      this.hitstate = RealLifeModPlayerRenderer.HitStates.forward;
      this.model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/minecraftcharacter.obj"));
      this.modelfemale = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/female.obj"));
      this.modelHead = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/characterhead.obj"));
      this.leftArm = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/leftarm.obj"));
      this.rightArm = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/rightarm.obj"));
      this.leftleg = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/leftleg.obj"));
      this.rightleg = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/rightleg.obj"));
      this.hairs = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/characterhairs.obj"));
      this.rightleg_thigh = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/rightleg_thigh.obj"));
      this.leftleg_thigh = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/leftleg_thigh.obj"));
      this.headwear = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/clothes/cap_1.obj"));
      this.hairsfemale = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/characterhairsfemale.obj"));

      int i;
      for(i = 0; i < 30; ++i) {
         if(i < 10) {
            HitAnimation.add(AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/CharacterArmHit/RightArmhit_00000" + i + ".obj")));
         } else {
            HitAnimation.add(AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/CharacterArmHit/RightArmhit_0000" + i + ".obj")));
         }
      }

      for(i = 0; i < 80; ++i) {
         if(i < 10) {
            SwimAnimationRight.add(AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/RightArmSwimming/RightArmSwimming_00000" + i + ".obj")));
         } else {
            SwimAnimationRight.add(AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/RightArmSwimming/RightArmSwimming_0000" + i + ".obj")));
         }
      }

      for(i = 0; i < 80; ++i) {
         if(i < 10) {
            SwimAnimationLeft.add(AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/LeftArmSwimming/LeftArmSwimming_00000" + i + ".obj")));
         } else {
            SwimAnimationLeft.add(AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/LeftArmSwimming/LeftArmSwimming_0000" + i + ".obj")));
         }
      }

      this.hitstate = RealLifeModPlayerRenderer.HitStates.forward;
      this.KeyFrameArmHit = 0;
      this.KeyFrameSwimming = 0;
   }

   protected void doRendering(EntityPlayer entity, double x, double y, double z, float rotatedYaw, float rotatedPitch) {
      this.delta = (float)((double)entity.func_70654_ax() * 0.7499999999999999D);
      GL11.glPushMatrix();
      this.func_110776_a(new ResourceLocation("reallifemod:textures/corps.png"));
      GL11.glDisable(2896);
      GL11.glDisable(2884);
      GL11.glPushMatrix();
      GL11.glTranslatef((float)x, (float)(y - (double)entity.field_70129_M + 0.1D), (float)z);
      GL11.glRotatef(-entity.field_70177_z, 0.0F, 1.0F, 0.0F);
      if(!entity.field_70122_E && entity.capabilities.isFlying && RealLifeModConfig.mouseSteering) {
         GL11.glRotatef(entity.field_70125_A, 1.0F, 0.0F, 0.0F);
         entity.field_70181_x = -Math.atan((double)entity.field_70125_A / 3.141592653589793D * 180.0D) * 9.81D / 40.0D * (double)entity.field_70701_bs;
      }

      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 0.85F, 0.0F);
      GL11.glScaled(1.001D, 1.001D, 1.035D);
      GL11.glRotatef(this.bodyRotX, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(this.bodyRotY, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(this.bodyRotZ, 0.0F, 0.0F, 1.0F);
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 0.6F, 0.0F);
      GL11.glRotatef(-entity.field_70177_z, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(entity.field_70759_as, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(entity.field_70125_A, 1.0F, 0.0F, 0.0F);
      this.modelHead.renderAll();
      GL11.glPushMatrix();
      GL11.glTranslated(0.0025D, 0.0D, 0.0D);
      RealLifeProperties var10000 = (RealLifeProperties)entity.getExtendedProperties("RealLifeProperties");
      Vector3f haircolor = RealLifeProperties.haircolor;
      if(haircolor != null) {
         GL11.glColor4f(haircolor.x / 256.0F, haircolor.y / 256.0F, haircolor.z / 256.0F, 1.0F);
      } else {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
      }

      if(RealLifeProperties.get(entity).hascap) {
         GL11.glPushMatrix();
         GL11.glScaled(0.985D, 0.985D, 0.985D);
         GL11.glTranslated(0.0D, -0.014999999664723873D, 0.0D);
         this.headwear.renderAll();
         GL11.glPopMatrix();
      } else if(((RealLifeProperties)entity.getExtendedProperties("RealLifeProperties")).Gender == RealLifeProperties.gender.male) {
         this.hairs.renderAll();
      } else {
         GL11.glTranslated(0.0D, 0.025D, 0.0D);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
         this.hairs.renderAll();
      }

      GL11.glPopMatrix();
      GL11.glPopMatrix();
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      if(((RealLifeProperties)entity.getExtendedProperties("RealLifeProperties")).Gender == RealLifeProperties.gender.male) {
         this.model.renderAll();
      } else {
         this.modelfemale.renderAll();
      }

      GL11.glPushMatrix();
      GL11.glTranslatef(0.16F, 0.505F, 0.0F);
      if(!entity.func_70090_H()) {
         GL11.glRotatef(this.leftArmRotX, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(this.leftArmRotY, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(this.leftArmRotZ, 0.0F, 0.0F, 1.0F);
         this.leftArm.renderAll();
         if(this.KeyFrameSwimming > 0) {
            --this.KeyFrameSwimming;
         }
      } else {
         if(entity.field_70701_bs == 0.0F && entity.field_70702_br == 0.0F) {
            ((IModelCustom)SwimAnimationLeft.get(50)).renderAll();
         } else {
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            if(this.KeyFrameSwimming < 80) {
               ++this.KeyFrameSwimming;
            }

            ((IModelCustom)SwimAnimationLeft.get(this.KeyFrameSwimming)).renderAll();
         }

         if(this.KeyFrameSwimming == 80) {
            this.KeyFrameSwimming = 0;
         }
      }

      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslatef(-0.14525F, 0.5F, -0.035F);
      if(!entity.func_70090_H()) {
         GL11.glRotatef(this.rightArmRotX, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(this.rightArmRotY, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(this.rightArmRotZ, 0.0F, 0.0F, 1.0F);
         if(this.KeyFrameArmHit > 30) {
            this.KeyFrameArmHit = 0;
         }

         ((IModelCustom)HitAnimation.get(this.KeyFrameArmHit)).renderAll();
      } else if(entity.field_70701_bs == 0.0F && entity.field_70702_br == 0.0F) {
         ((IModelCustom)SwimAnimationRight.get(50)).renderAll();
      } else {
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         ((IModelCustom)SwimAnimationRight.get(this.KeyFrameSwimming)).renderAll();
      }

      GL11.glPopMatrix();
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 0.85F, 0.0F);
      GL11.glRotatef(this.rightlegRotX, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(this.rightlegRotY, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(this.rightlegRotZ, 0.0F, 0.0F, 1.0F);
      this.rightleg_thigh.renderAll();
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, -0.375F, 0.0F);
      GL11.glRotatef(this.rightlegKneeRotX, 1.0F, 0.0F, 0.0F);
      this.rightleg.renderAll();
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 0.85F, 0.0F);
      GL11.glRotatef(this.leftlegRotX, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(this.leftlegRotY, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(this.leftlegRotZ, 0.0F, 0.0F, 1.0F);
      this.leftleg_thigh.renderAll();
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, -0.375F, 0.0F);
      GL11.glRotatef(this.leftlegKneeRotX, 1.0F, 0.0F, 0.0F);
      this.leftleg.renderAll();
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      this.setRotationAngles(this.delta, 7.5F, (float)x, (float)z, entity.field_70177_z, entity.field_70125_A, entity);
      if(!entity.func_70090_H() && !entity.func_70115_ae()) {
         this.doDanceMovements(entity);
      }

      MinecraftForge.EVENT_BUS.post(new Post(entity, this, rotatedPitch));
   }

   public void func_82441_a(EntityPlayer player) {
      this.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
      if(player.field_70154_o == null) {
         GL11.glPushMatrix();
         GL11.glDisable(2896);
         Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/corps.png"));
         if(!player.field_70122_E && player.capabilities.isFlying && player.field_70160_al && RealLifeModConfig.mouseSteering) {
            player.field_70181_x = -Math.tanh((double)player.field_70125_A / 3.141592653589793D * 180.0D) * 9.81D / 20.0D * (double)player.field_70701_bs;
         }

         GL11.glPushMatrix();
         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         GL11.glDisable(2884);
         GL11.glPushMatrix();
         if(Mouse.isButtonDown(0)) {
            if(this.hitstate == RealLifeModPlayerRenderer.HitStates.forward) {
               if(this.KeyFrameArmHit == 30) {
                  this.hitstate = RealLifeModPlayerRenderer.HitStates.backward;
               }

               if(this.hitstate == RealLifeModPlayerRenderer.HitStates.backward && this.KeyFrameArmHit > 0) {
                  --this.KeyFrameArmHit;
               } else if(this.KeyFrameArmHit == 0) {
                  this.hitstate = RealLifeModPlayerRenderer.HitStates.forward;
               }
            }

            if(this.hitstate == RealLifeModPlayerRenderer.HitStates.forward) {
               ++this.KeyFrameArmHit;
            } else if(this.KeyFrameArmHit > 0) {
               --this.KeyFrameArmHit;
            }
         }

         if(this.KeyFrameArmHit <= 30) {
            ((IModelCustom)HitAnimation.get(this.KeyFrameArmHit)).renderAll();
         } else if(this.KeyFrameArmHit > 30) {
            System.out.println("To big!!");
         }

         GL11.glPopMatrix();
         GL11.glPopMatrix();
         GL11.glPopMatrix();
      } else if(player.func_70115_ae()) {
         GL11.glPushMatrix();
         GL11.glTranslated(1.0D, 1.0D, 1.0D);
         this.rightArm.renderAll();
         GL11.glPopMatrix();
      }

   }

   protected void func_77039_a(AbstractClientPlayer p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
      if(p_77039_1_.func_70089_S() && p_77039_1_.func_70608_bn()) {
         super.renderLivingAt(p_77039_1_, p_77039_2_ + (double)p_77039_1_.field_71079_bU, p_77039_4_ + (double)p_77039_1_.field_71082_cx, p_77039_6_ + (double)p_77039_1_.field_71089_bV);
      } else {
         super.renderLivingAt(p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
      }

   }

   protected void func_77043_a(AbstractClientPlayer p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
      if(p_77043_1_.func_70089_S() && p_77043_1_.func_70608_bn()) {
         GL11.glRotatef(p_77043_1_.func_71051_bG(), 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(this.func_77037_a(p_77043_1_), 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
      }

   }

   protected void func_96449_a(EntityLivingBase p_96449_1_, double p_96449_2_, double p_96449_4_, double p_96449_6_, String p_96449_8_, float p_96449_9_, double p_96449_10_) {
      this.func_96449_a((AbstractClientPlayer)p_96449_1_, p_96449_2_, p_96449_4_, p_96449_6_, p_96449_8_, p_96449_9_, p_96449_10_);
   }

   protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_) {
      this.func_77041_b((AbstractClientPlayer)p_77041_1_, p_77041_2_);
   }

   protected void func_82408_c(EntityLivingBase p_82408_1_, int p_82408_2_, float p_82408_3_) {
      this.func_82408_c((AbstractClientPlayer)p_82408_1_, p_82408_2_, p_82408_3_);
   }

   protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
      return this.func_77032_a((AbstractClientPlayer)p_77032_1_, p_77032_2_, p_77032_3_);
   }

   protected void func_77029_c(EntityLivingBase entity, float p_77029_2_) {
      this.func_77029_c((AbstractClientPlayer)entity, p_77029_2_);
   }

   protected void func_77043_a(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
      this.func_77043_a((AbstractClientPlayer)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
   }

   protected void func_77039_a(EntityLivingBase p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
      this.func_77039_a((AbstractClientPlayer)p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
   }

   protected ResourceLocation func_110775_a(Entity p_110775_1_) {
      return super.getEntityTexture(p_110775_1_);
   }

   public boolean func_147905_a() {
      return false;
   }

   private static FloatBuffer asFloatBuffer(float[] values) {
      FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
      buffer.put(values);
      buffer.flip();
      return buffer;
   }

   public void func_76986_a(Entity entity, double x, double y, double z, float rotationYaw, float rotationPitch) {
      this.func_77029_c((EntityLivingBase)entity, 1.0F);
      this.doRendering((EntityPlayer)entity, x, y, z, rotationPitch, rotationPitch);
   }

   protected ResourceLocation func_110775_a(AbstractClientPlayer p_110775_1_) {
      return null;
   }

   protected void func_77029_c(AbstractClientPlayer entity, float p_77029_2_) {
      GL11.glPushMatrix();
      Pre event = new Pre(entity, this, p_77029_2_);
      if(!MinecraftForge.EVENT_BUS.post(event)) {
         GL11.glColor3f(1.0F, 1.0F, 1.0F);
         super.func_85093_e(entity, p_77029_2_);
         ItemStack itemstack = entity.field_71071_by.armorItemInSlot(3);
         GL11.glRotated((double)(-entity.field_70177_z), 0.0D, 1.0D, 0.0D);
         float f2;
         if(itemstack != null && event.renderHelmet) {
            GL11.glPushMatrix();
            GL11.glTranslated(0.0D, -0.6525D, 0.0D);
            GL11.glScaled(1.05D, 1.05D, 1.05D);
            if(itemstack != null && itemstack.getItem() == Items.skull) {
               f2 = 1.0625F;
               GL11.glScalef(f2, -f2, -f2);
               GameProfile flag = null;
               if(itemstack.hasTagCompound()) {
                  NBTTagCompound f4 = itemstack.getTagCompound();
                  if(f4.hasKey("SkullOwner", 10)) {
                     flag = NBTUtil.func_152459_a(f4.getCompoundTag("SkullOwner"));
                  } else if(f4.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty(f4.getString("SkullOwner"))) {
                     flag = new GameProfile((UUID)null, f4.getString("SkullOwner"));
                  }
               }

               TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack.getItemDamage(), flag);
            }

            GL11.glPopMatrix();
         }

         boolean var17 = entity.func_152122_n();
         boolean var10000;
         if(event.renderCape && var17) {
            var10000 = true;
         } else {
            var10000 = false;
         }

         ItemStack itemstack1 = entity.field_71071_by.getCurrentItem();
         if(itemstack1 != null && event.renderItem) {
            GL11.glPushMatrix();
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            if(entity.field_71104_cf != null) {
               itemstack1 = new ItemStack(Items.stick);
            }

            EnumAction enumaction = null;
            if(entity.func_71052_bv() > 0) {
               enumaction = itemstack1.getItemUseAction();
            }

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, ItemRenderType.EQUIPPED);
            boolean is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(ItemRenderType.EQUIPPED, itemstack1, ItemRendererHelper.BLOCK_3D);
            if(customRenderer != null && customRenderer.shouldUseRenderHelper(ItemRenderType.EQUIPPED_FIRST_PERSON, itemstack1, ItemRendererHelper.BLOCK_3D)) {
               var10000 = true;
            } else {
               var10000 = false;
            }

            if(!is3D && (!(itemstack1.getItem() instanceof ItemBlock) || !RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack1.getItem()).getRenderType()))) {
               if(itemstack1.getItem() == Items.bow) {
                  f2 = 0.625F;
                  GL11.glScalef(f2, -f2, f2);
                  GL11.glTranslatef(0.4F, 2.25F, 1.8F);
                  GL11.glRotatef(180.0F, 0.0F, 0.0F, 0.0F);
                  GL11.glTranslated(-Math.sin((double)(this.rightArmRotZ / 180.0F) * 3.141592653589793D), -1.600000023841858D + Math.cos((double)(this.rightArmRotX / 180.0F) * 3.141592653589793D), 0.55D + Math.cos((double)(this.rightArmRotZ / 180.0F) * 3.141592653589793D));
                  GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
               } else if(itemstack1.getItem() == RealLifeMod_Items.ak) {
                  if(Mouse.isButtonDown(1)) {
                     GL11.glTranslatef(0.1F, -1.35F, 0.3125F);
                     GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
                     GL11.glRotated(45.0D, 0.0D, 1.0D, 0.0D);
                  } else {
                     GL11.glTranslatef(0.1F, -0.9F, 0.3125F);
                     GL11.glRotated(-15.0D, 0.0D, 0.0D, 1.0D);
                     GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
                     GL11.glRotated(100.0D, 0.0D, 1.0D, 0.0D);
                  }

                  GL11.glScaled(0.5D, 0.5D, 0.5D);
               } else if(itemstack1.getItem().isFull3D()) {
                  f2 = 0.625F;
                  GL11.glTranslated(0.25D, -1.6D, -0.085D);
                  GL11.glTranslated(Math.sin((double)(this.rightArmRotZ / 180.0F) * 3.141592653589793D) / 20.0D, Math.cos((double)(this.rightArmRotX / 180.0F) * 3.141592653589793D), Math.cos((double)(this.rightArmRotZ / 180.0F) * 3.141592653589793D));
                  if(itemstack1.getItem().shouldRotateAroundWhenRendering()) {
                     GL11.glTranslatef(0.0F, 5.0F, 0.0F);
                     GL11.glRotatef(1.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glRotated((double)entity.field_70177_z, 0.0D, 1.0D, 0.0D);
                  }

                  if(entity.func_71052_bv() > 0 && enumaction == EnumAction.block) {
                     GL11.glTranslatef(0.5F, -0.25F, 0.15F);
                     this.rightArmRotX = -10.0F;
                  }

                  if(entity.func_71052_bv() > 0 && enumaction == EnumAction.none) {
                     ;
                  }

                  if(entity.func_71052_bv() > 0 && enumaction == EnumAction.bow) {
                     GL11.glTranslatef(0.05F, 1.0F, -0.25F);
                     this.rightArmRotY -= 50.0F;
                     this.rightArmRotX = -90.0F;
                  }

                  GL11.glScalef(f2, -f2, f2);
                  GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
               } else {
                  f2 = 0.375F;
                  GL11.glTranslated(0.25D, -0.95D, -0.085D);
                  GL11.glTranslated(Math.sin((double)this.rightArmRotX * 3.141592653589793D / 180.0D), 0.0D, 0.0D);
                  GL11.glTranslated(0.0D, Math.tan((double)(-this.rightArmRotY) * 3.141592653589793D / 180.0D), 0.0D);
                  GL11.glTranslated(0.0D, 0.0D, Math.cos((double)(-this.rightArmRotZ) * 3.141592653589793D / 180.0D));
                  GL11.glScalef(f2, -f2, f2);
               }
            } else {
               f2 = 0.5F;
               GL11.glTranslated(-Math.sin((double)(this.rightArmRotZ / 180.0F) * 3.141592653589793D), -1.600000023841858D + Math.cos((double)(this.rightArmRotX / 180.0F) * 3.141592653589793D), 0.55D + Math.cos((double)(this.rightArmRotZ / 180.0F) * 3.141592653589793D));
               f2 *= 0.75F;
               GL11.glScalef(-f2, -f2, f2);
            }

            float f3;
            int k;
            float f12;
            if(itemstack1.getItem().requiresMultipleRenderPasses()) {
               for(k = 0; k < itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); ++k) {
                  int f11 = itemstack1.getItem().getColorFromItemStack(itemstack1, k);
                  f12 = (float)(f11 >> 16 & 255) / 255.0F;
                  f3 = (float)(f11 >> 8 & 255) / 255.0F;
                  float var18 = (float)(f11 & 255) / 255.0F;
                  GL11.glColor4f(f12, f3, var18, 1.0F);
                  this.field_76990_c.itemRenderer.renderItem(entity, itemstack1, k);
               }
            } else {
               k = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
               float var19 = (float)(k >> 16 & 255) / 255.0F;
               f12 = (float)(k >> 8 & 255) / 255.0F;
               f3 = (float)(k & 255) / 255.0F;
               GL11.glColor4f(var19, f12, f3, 1.0F);
               this.field_76990_c.itemRenderer.renderItem(entity, itemstack1, 0);
            }

            GL11.glPopMatrix();
         }

         GL11.glPopMatrix();
         MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Specials.Post(entity, this, p_77029_2_));
      }
   }

   public void setRotationAngles(float time, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, EntityPlayer entity) {
      if(this.swimmingdelta < 1.0D) {
         this.swimmingdelta += 0.01D;
      } else {
         this.swimmingdelta = 0.0D;
      }

      this.headwearRotY = this.headRotY;
      this.headwearRotX = this.headRotX;
      if(entity.func_70090_H()) {
         if(!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(57)) {
            if(entity.field_70170_p.getBlock((int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v) == Blocks.water && entity.field_70170_p.getBlock((int)(entity.field_70165_t - 0.5D), (int)(entity.field_70163_u + 0.05D), (int)(entity.field_70161_v - 0.5D)) == Blocks.water) {
               entity.field_70181_x = 0.04D;
            } else if(entity.field_70170_p.getBlock((int)(entity.field_70165_t - 0.5D), (int)entity.field_70163_u, (int)(entity.field_70161_v - 0.5D)) != Blocks.water) {
               entity.field_70181_x = -0.04D;
            } else {
               entity.field_70181_x = 0.0D;
            }
         }

         this.rightlegRotX = 90.0F + MathHelper.cos(time * 0.6662F) * 1.4F * p_78087_2_;
         this.leftlegRotX = 90.0F + MathHelper.cos(time * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_;
         if(this.leftArmRotZ < 0.0F) {
            this.leftArmRotZ = 180.0F;
            this.rightArmRotZ = 180.0F;
         }

         if(entity.field_70159_w == 0.0D && entity.field_70179_y == 0.0D) {
            if(this.bodyRotX - 5.0F > 0.0F) {
               this.bodyRotX -= 5.0F;
            }

            this.leftlegRotX = 0.0F;
            this.rightlegRotX = 0.0F;
         } else {
            this.leftArmRotZ = (float)((double)this.leftArmRotZ + (double)(-((float)(Math.cos(this.swimmingdelta) / 2.0D + 0.5D))) * 1.75D);
            if(this.bodyRotX + 5.0F < 90.0F) {
               this.bodyRotX += 5.0F;
            }

            this.leftlegRotX = 90.0F;
            this.rightlegRotX = 90.0F;
         }

         this.bodyRotZ = 0.0F;
         this.bodyRotY = 0.0F;
         this.rightArmRotY = 0.0F;
         this.leftlegRotZ = 0.0F;
         this.leftlegRotY = 0.0F;
         this.rightlegRotZ = 0.0F;
         this.rightlegRotY = 0.0F;
      } else if(!entity.func_70090_H()) {
         if(Mouse.isButtonDown(0)) {
            if(this.hitstate == RealLifeModPlayerRenderer.HitStates.forward && this.KeyFrameArmHit < 30) {
               ++this.KeyFrameArmHit;
            } else if(this.KeyFrameArmHit > 0 && this.hitstate == RealLifeModPlayerRenderer.HitStates.backward) {
               --this.KeyFrameArmHit;
            }
         } else {
            this.hitstate = RealLifeModPlayerRenderer.HitStates.forward;
            if(this.KeyFrameArmHit > 0) {
               --this.KeyFrameArmHit;
            }
         }

         if(entity.getCurrentEquippedItem() != null && entity.getCurrentEquippedItem().getItem() == RealLifeMod_Items.ak) {
            ;
         }

         if(entity.getCurrentEquippedItem() == null) {
            this.rightlegRotX = 0.0F;
            this.leftlegRotX = 0.0F;
         }

         if(entity.field_70701_bs != 0.0F && !entity.func_70115_ae()) {
            this.leftlegRotX = MathHelper.cos(time * 0.5F + 3.1415927F) * 2.0F * p_78087_2_;
            this.leftlegKneeRotX = MathHelper.sqrt_double((double)(this.leftlegRotX * 0.5F)) * 16.0F;
            this.rightlegRotX = MathHelper.cos((float)((double)(time * 0.5F) - 0.39269908169872414D)) * 2.0F * p_78087_2_;
            this.rightlegKneeRotX = MathHelper.sqrt_double((double)(this.rightlegRotX * 0.5F)) * 16.0F;
            this.rightlegRotY = 0.0F;
            this.leftlegRotY = 0.0F;
         }

         if(entity.field_70701_bs != 0.0F) {
            this.leftArmRotX = MathHelper.cos(time * 0.7562F) * 3.0F * p_78087_2_ * 0.5F;
            if(entity.getCurrentEquippedItem() != null) {
               this.rightArmRotX = this.rightArmRotX * 0.5F - 0.31415927F;
            }
         } else {
            this.rightArmRotX = (float)((double)this.rightArmRotX * 0.85D);
            this.rightArmRotX = (float)((double)this.rightArmRotX * 0.85D);
            this.rightArmRotZ = (float)((double)this.rightArmRotZ * 0.85D);
            this.leftArmRotX = (float)((double)this.leftArmRotX * 0.85D);
            this.leftArmRotX = (float)((double)this.leftArmRotX * 0.85D);
            this.leftArmRotZ = (float)((double)this.leftArmRotZ * 0.85D);
         }

         if(entity.getCurrentEquippedItem() != null) {
            if(entity.getCurrentEquippedItem().getItem() instanceof ItemBlock) {
               this.rightArmRotX = -35.0F;
               this.leftArmRotX = -35.0F;
            }

            if(entity.getCurrentEquippedItem().getItem() == RealLifeMod_Items.ak) {
               this.aim(entity);
            }
         }

         if(entity.field_70143_R > 2.0F && entity.field_70143_R < 7.0F) {
            this.leftlegRotX = -35.0F;
            this.leftlegKneeRotX = 35.0F;
            this.leftlegRotX = -35.0F;
            this.leftlegKneeRotX = 35.0F;
         }

         if(!entity.field_70122_E) {
            this.bodyRotX = 0.0F;
            ++this.leftArmRotX;
            this.leftArmRotY = 0.0F;
            this.rightArmRotX = 0.0F;
            this.rightArmRotY = 0.0F;
            this.leftlegRotX = 0.0F;
            this.leftlegRotY = 0.0F;
            this.leftlegRotZ = 0.0F;
            this.rightlegRotX = 0.0F;
            this.rightlegKneeRotX = 0.0F;
            this.leftlegKneeRotX = 0.0F;
            this.rightlegRotY = 0.0F;
            this.rightlegRotZ = 0.0F;
         }

         if(Keyboard.isKeyDown(30) && entity.field_70701_bs < entity.field_70702_br) {
            this.rightlegRotX = 0.0F;
            this.rightlegKneeRotX = 0.0F;
            this.rightlegRotZ = (float)((double)(MathHelper.cos(time * 0.7562F) * 1.5F * p_78087_2_ * 0.5F) - 1.5707963267948966D);
            this.leftlegRotZ = MathHelper.cos(time * 0.7562F + 0.5F) * 1.5F * p_78087_2_ * 0.5F;
         }

         if(Keyboard.isKeyDown(32) && entity.field_70702_br >= entity.field_70701_bs) {
            this.stepRight();
         }

         if(!entity.field_70122_E && entity.capabilities.isFlying && entity.field_70160_al) {
            if(Keyboard.isKeyDown(32)) {
               if(this.bodyRotY > -45.0F) {
                  this.bodyRotY -= 3.6F;
               }
            } else if(this.bodyRotY < 0.0F) {
               this.bodyRotY += 3.6F;
            }

            if(Keyboard.isKeyDown(30)) {
               if(this.bodyRotY < 45.0F) {
                  this.bodyRotY += 3.6F;
               }
            } else if(this.bodyRotY > 0.0F) {
               this.bodyRotY -= 3.6F;
            }

            this.bodyRotX = 90.0F;
            this.leftArmRotX = 0.0F;
            this.leftArmRotY = 0.0F;
            this.rightArmRotX = 0.0F;
            this.rightArmRotY = 0.0F;
            this.leftlegRotX = 90.0F;
            this.leftlegRotY = 0.0F;
            this.leftlegRotZ = 0.0F;
            this.rightlegRotX = 90.0F;
            this.rightlegRotY = 0.0F;
            this.rightlegRotZ = 0.0F;
         }

         if(entity.func_70617_f_() && entity.field_70181_x >= 0.0D) {
            this.rightArmRotX = -90.0F + MathHelper.cos(time * 0.6662F) * 1.4F * p_78087_2_;
            this.leftArmRotX = -90.0F + MathHelper.cos(time * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_;
         }

         if(entity.func_70115_ae()) {
            this.leftlegRotX = -45.0F;
            this.leftlegRotY = 0.0F;
            this.leftlegRotZ = 22.5F;
            this.rightlegRotX = -45.0F;
            this.rightlegRotY = 0.0F;
            this.rightlegRotZ = -22.5F;
            this.rightArmRotX = -45.0F;
            this.leftArmRotX = -45.0F;
         }

         if(entity.func_70093_af()) {
            if(this.bodyRotX < 45.0F) {
               ++this.bodyRotX;
            }

            if(this.leftArmRotX < 25.0F) {
               this.rightArmRotX = (float)((double)this.rightArmRotX + 2.5D);
               this.leftArmRotX = (float)((double)this.leftArmRotX + 2.5D);
            }

            if(this.leftlegRotX < 25.0F) {
               --this.leftlegRotX;
               --this.leftlegKneeRotX;
            }

            if(this.rightlegRotX < 25.0F) {
               --this.rightlegRotX;
               --this.rightlegKneeRotX;
            }
         } else if(this.bodyRotX > 0.0F) {
            --this.bodyRotX;
         }
      } else {
         this.rightArmRotX = (float)((double)this.rightArmRotX * 0.85D);
         this.rightArmRotY = (float)((double)this.rightArmRotY * 0.85D);
         this.rightArmRotZ = (float)((double)this.rightArmRotZ * 0.85D);
         this.leftArmRotX = (float)((double)this.leftArmRotX * 0.85D);
         this.leftArmRotY = (float)((double)this.leftArmRotY * 0.85D);
         this.leftArmRotZ = (float)((double)this.leftArmRotZ * 0.85D);
      }

   }

   private void stepLeft() {}

   private void stepRight() {}

   public void aim(EntityPlayer entity) {
      if(((AK47Item)entity.getCurrentEquippedItem().getItem()).aiming) {
         this.rightArmRotX = entity.field_70125_A - 90.0F;
         this.leftArmRotX = entity.field_70125_A - 45.0F;
         this.leftArmRotY = 45.0F;
         this.leftArmRotZ = -45.0F;
      } else {
         this.rightArmRotX = -25.0F;
         this.leftArmRotX = -45.0F;
         this.leftArmRotY = 90.0F;
         this.leftArmRotZ = 0.0F;
      }

   }

   public void doDanceMovements(EntityPlayer entity) {
      if(!entity.capabilities.isFlying) {
         if(Keyboard.isKeyDown(203)) {
            if(this.leftlegRotZ < 45.0F) {
               this.leftlegRotZ += 3.6F;
            }
         } else if(this.leftlegRotZ > 0.0F) {
            this.leftlegRotZ -= 3.6F;
         }

         if(Keyboard.isKeyDown(205)) {
            if(this.rightlegRotZ > -45.0F) {
               this.rightlegRotZ -= 3.6F;
            }
         } else if(this.rightlegRotZ < 0.0F) {
            this.rightlegRotZ += 3.6F;
         }

         if(Keyboard.isKeyDown(200)) {
            if(this.rightlegRotX > -45.0F) {
               this.rightlegRotX -= 3.6F;
               this.leftlegRotX -= 3.6F;
            }
         } else if(this.rightlegRotX < 0.0F) {
            this.rightlegRotX += 3.6F;
            this.leftlegRotX += 3.6F;
         }

         if(Keyboard.isKeyDown(208)) {
            if(this.leftlegRotX < 45.0F) {
               this.leftlegRotX += 3.6F;
               this.rightlegRotX += 3.6F;
            }
         } else if(this.leftlegRotX > 0.0F) {
            this.leftlegRotX -= 3.6F;
            this.rightlegRotX -= 3.6F;
         }

         if(Keyboard.isKeyDown(77)) {
            if(this.bodyRotZ < 45.0F) {
               this.bodyRotZ += 3.6F;
            }
         } else if(this.bodyRotZ > 0.0F) {
            this.bodyRotZ -= 3.6F;
         }

         if(Keyboard.isKeyDown(75)) {
            if(this.bodyRotZ > -45.0F) {
               this.bodyRotZ -= 3.6F;
            }
         } else if(this.bodyRotZ < 0.0F) {
            this.bodyRotZ += 3.6F;
         }

         if(Keyboard.isKeyDown(24)) {
            if(this.leftlegKneeRotX < 45.0F) {
               this.leftlegKneeRotX += 3.6F;
            }
         } else if(this.leftlegKneeRotX > 0.0F) {
            this.leftlegKneeRotX -= 3.6F;
         }

         if(!entity.func_70093_af()) {
            if(Keyboard.isKeyDown(80)) {
               if(this.bodyRotX > -45.0F) {
                  this.bodyRotX -= 3.6F;
               }
            } else if(!Keyboard.isKeyDown(72)) {
               if(this.bodyRotX < -0.36F) {
                  this.bodyRotX += 3.6F;
               } else {
                  this.bodyRotX = 0.0F;
               }
            }

            if(Keyboard.isKeyDown(72)) {
               if(this.bodyRotX < 45.0F) {
                  this.bodyRotX += 3.6F;
               }
            } else if(!Keyboard.isKeyDown(80)) {
               if(this.bodyRotX > 0.36F) {
                  this.bodyRotX -= 3.6F;
               } else {
                  this.bodyRotX = 0.0F;
               }
            }
         }
      }

   }


   public static enum HitStates {

      forward("forward", 0),
      backward("backward", 1);
      // $FF: synthetic field
      private static final RealLifeModPlayerRenderer.HitStates[] $VALUES = new RealLifeModPlayerRenderer.HitStates[]{forward, backward};


      private HitStates(String var1, int var2) {}

   }
}
