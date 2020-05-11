package net.minecraft.client.renderer.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.entity.RenderCaveSpider;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderFallingBlock;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraft.client.renderer.entity.RenderGhast;
import net.minecraft.client.renderer.entity.RenderGiantZombie;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderLeashKnot;
import net.minecraft.client.renderer.entity.RenderLightningBolt;
import net.minecraft.client.renderer.entity.RenderMagmaCube;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.entity.RenderMinecartMobSpawner;
import net.minecraft.client.renderer.entity.RenderMooshroom;
import net.minecraft.client.renderer.entity.RenderOcelot;
import net.minecraft.client.renderer.entity.RenderPainting;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.client.renderer.entity.RenderSquid;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.client.renderer.entity.RenderTntMinecart;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.client.renderer.entity.RenderWitch;
import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraft.client.renderer.tileentity.RenderItemFrame;
import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderManager {

   private Map entityRenderMap = new HashMap();
   public static RenderManager instance = new RenderManager();
   private FontRenderer fontRenderer;
   public static double renderPosX;
   public static double renderPosY;
   public static double renderPosZ;
   public TextureManager renderEngine;
   public ItemRenderer itemRenderer;
   public World worldObj;
   public EntityLivingBase livingPlayer;
   public Entity field_147941_i;
   public float playerViewY;
   public float playerViewX;
   public GameSettings options;
   public double viewerPosX;
   public double viewerPosY;
   public double viewerPosZ;
   public static boolean debugBoundingBox;


   private RenderManager() {
      this.entityRenderMap.put(EntityCaveSpider.class, new RenderCaveSpider());
      this.entityRenderMap.put(EntitySpider.class, new RenderSpider());
      this.entityRenderMap.put(EntityPig.class, new RenderPig(new ModelPig(), new ModelPig(0.5F), 0.7F));
      this.entityRenderMap.put(EntitySheep.class, new RenderSheep(new ModelSheep2(), new ModelSheep1(), 0.7F));
      this.entityRenderMap.put(EntityCow.class, new RenderCow(new ModelCow(), 0.7F));
      this.entityRenderMap.put(EntityMooshroom.class, new RenderMooshroom(new ModelCow(), 0.7F));
      this.entityRenderMap.put(EntityWolf.class, new RenderWolf(new ModelWolf(), new ModelWolf(), 0.5F));
      this.entityRenderMap.put(EntityChicken.class, new RenderChicken(new ModelChicken(), 0.3F));
      this.entityRenderMap.put(EntityOcelot.class, new RenderOcelot(new ModelOcelot(), 0.4F));
      this.entityRenderMap.put(EntitySilverfish.class, new RenderSilverfish());
      this.entityRenderMap.put(EntityCreeper.class, new RenderCreeper());
      this.entityRenderMap.put(EntityEnderman.class, new RenderEnderman());
      this.entityRenderMap.put(EntitySnowman.class, new RenderSnowMan());
      this.entityRenderMap.put(EntitySkeleton.class, new RenderSkeleton());
      this.entityRenderMap.put(EntityWitch.class, new RenderWitch());
      this.entityRenderMap.put(EntityBlaze.class, new RenderBlaze());
      this.entityRenderMap.put(EntityZombie.class, new RenderZombie());
      this.entityRenderMap.put(EntitySlime.class, new RenderSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
      this.entityRenderMap.put(EntityMagmaCube.class, new RenderMagmaCube());
      this.entityRenderMap.put(EntityPlayer.class, new RenderPlayer());
      this.entityRenderMap.put(EntityGiantZombie.class, new RenderGiantZombie(new ModelZombie(), 0.5F, 6.0F));
      this.entityRenderMap.put(EntityGhast.class, new RenderGhast());
      this.entityRenderMap.put(EntitySquid.class, new RenderSquid(new ModelSquid(), 0.7F));
      this.entityRenderMap.put(EntityVillager.class, new RenderVillager());
      this.entityRenderMap.put(EntityIronGolem.class, new RenderIronGolem());
      this.entityRenderMap.put(EntityBat.class, new RenderBat());
      this.entityRenderMap.put(EntityDragon.class, new RenderDragon());
      this.entityRenderMap.put(EntityEnderCrystal.class, new RenderEnderCrystal());
      this.entityRenderMap.put(EntityWither.class, new RenderWither());
      this.entityRenderMap.put(Entity.class, new RenderEntity());
      this.entityRenderMap.put(EntityPainting.class, new RenderPainting());
      this.entityRenderMap.put(EntityItemFrame.class, new RenderItemFrame());
      this.entityRenderMap.put(EntityLeashKnot.class, new RenderLeashKnot());
      this.entityRenderMap.put(EntityArrow.class, new RenderArrow());
      this.entityRenderMap.put(EntitySnowball.class, new RenderSnowball(Items.snowball));
      this.entityRenderMap.put(EntityEnderPearl.class, new RenderSnowball(Items.ender_pearl));
      this.entityRenderMap.put(EntityEnderEye.class, new RenderSnowball(Items.ender_eye));
      this.entityRenderMap.put(EntityEgg.class, new RenderSnowball(Items.egg));
      this.entityRenderMap.put(EntityPotion.class, new RenderSnowball(Items.potionitem, 16384));
      this.entityRenderMap.put(EntityExpBottle.class, new RenderSnowball(Items.experience_bottle));
      this.entityRenderMap.put(EntityFireworkRocket.class, new RenderSnowball(Items.fireworks));
      this.entityRenderMap.put(EntityLargeFireball.class, new RenderFireball(2.0F));
      this.entityRenderMap.put(EntitySmallFireball.class, new RenderFireball(0.5F));
      this.entityRenderMap.put(EntityWitherSkull.class, new RenderWitherSkull());
      this.entityRenderMap.put(EntityItem.class, new RenderItem());
      this.entityRenderMap.put(EntityXPOrb.class, new RenderXPOrb());
      this.entityRenderMap.put(EntityTNTPrimed.class, new RenderTNTPrimed());
      this.entityRenderMap.put(EntityFallingBlock.class, new RenderFallingBlock());
      this.entityRenderMap.put(EntityMinecartTNT.class, new RenderTntMinecart());
      this.entityRenderMap.put(EntityMinecartMobSpawner.class, new RenderMinecartMobSpawner());
      this.entityRenderMap.put(EntityMinecart.class, new RenderMinecart());
      this.entityRenderMap.put(EntityBoat.class, new RenderBoat());
      this.entityRenderMap.put(EntityFishHook.class, new RenderFish());
      this.entityRenderMap.put(EntityHorse.class, new RenderHorse(new ModelHorse(), 0.75F));
      this.entityRenderMap.put(EntityLightningBolt.class, new RenderLightningBolt());
      Iterator var1 = this.entityRenderMap.values().iterator();

      while(var1.hasNext()) {
         Render var2 = (Render)var1.next();
         var2.setRenderManager(this);
      }

   }

   public Render getEntityClassRenderObject(Class var1) {
      Render var2 = (Render)this.entityRenderMap.get(var1);
      if(var2 == null && var1 != Entity.class) {
         var2 = this.getEntityClassRenderObject(var1.getSuperclass());
         this.entityRenderMap.put(var1, var2);
      }

      return var2;
   }

   public Render getEntityRenderObject(Entity var1) {
      return this.getEntityClassRenderObject(var1.getClass());
   }

   public void cacheActiveRenderInfo(World var1, TextureManager var2, FontRenderer var3, EntityLivingBase var4, Entity var5, GameSettings var6, float var7) {
      this.worldObj = var1;
      this.renderEngine = var2;
      this.options = var6;
      this.livingPlayer = var4;
      this.field_147941_i = var5;
      this.fontRenderer = var3;
      if(var4.isPlayerSleeping()) {
         Block var8 = var1.getBlock(MathHelper.floor_double(var4.posX), MathHelper.floor_double(var4.posY), MathHelper.floor_double(var4.posZ));
         if(var8 == Blocks.bed) {
            int var9 = var1.getBlockMetadata(MathHelper.floor_double(var4.posX), MathHelper.floor_double(var4.posY), MathHelper.floor_double(var4.posZ));
            int var10 = var9 & 3;
            this.playerViewY = (float)(var10 * 90 + 180);
            this.playerViewX = 0.0F;
         }
      } else {
         this.playerViewY = var4.prevRotationYaw + (var4.rotationYaw - var4.prevRotationYaw) * var7;
         this.playerViewX = var4.prevRotationPitch + (var4.rotationPitch - var4.prevRotationPitch) * var7;
      }

      if(var6.thirdPersonView == 2) {
         this.playerViewY += 180.0F;
      }

      this.viewerPosX = var4.lastTickPosX + (var4.posX - var4.lastTickPosX) * (double)var7;
      this.viewerPosY = var4.lastTickPosY + (var4.posY - var4.lastTickPosY) * (double)var7;
      this.viewerPosZ = var4.lastTickPosZ + (var4.posZ - var4.lastTickPosZ) * (double)var7;
   }

   public boolean renderEntitySimple(Entity var1, float var2) {
      return this.renderEntityStatic(var1, var2, false);
   }

   public boolean renderEntityStatic(Entity var1, float var2, boolean var3) {
      if(var1.ticksExisted == 0) {
         var1.lastTickPosX = var1.posX;
         var1.lastTickPosY = var1.posY;
         var1.lastTickPosZ = var1.posZ;
      }

      double var4 = var1.lastTickPosX + (var1.posX - var1.lastTickPosX) * (double)var2;
      double var6 = var1.lastTickPosY + (var1.posY - var1.lastTickPosY) * (double)var2;
      double var8 = var1.lastTickPosZ + (var1.posZ - var1.lastTickPosZ) * (double)var2;
      float var10 = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var2;
      int var11 = var1.getBrightnessForRender(var2);
      if(var1.isBurning()) {
         var11 = 15728880;
      }

      int var12 = var11 % 65536;
      int var13 = var11 / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var12 / 1.0F, (float)var13 / 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      return this.func_147939_a(var1, var4 - renderPosX, var6 - renderPosY, var8 - renderPosZ, var10, var2, var3);
   }

   public boolean renderEntityWithPosYaw(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      return this.func_147939_a(var1, var2, var4, var6, var8, var9, false);
   }

   public boolean func_147939_a(Entity var1, double var2, double var4, double var6, float var8, float var9, boolean var10) {
      Render var11 = null;

      try {
         var11 = this.getEntityRenderObject(var1);
         if(var11 != null && this.renderEngine != null) {
            if(!var11.isStaticEntity() || var10) {
               try {
                  var11.doRender(var1, var2, var4, var6, var8, var9);
               } catch (Throwable var18) {
                  throw new ReportedException(CrashReport.makeCrashReport(var18, "Rendering entity in world"));
               }

               try {
                  var11.doRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
               } catch (Throwable var17) {
                  throw new ReportedException(CrashReport.makeCrashReport(var17, "Post-rendering entity in world"));
               }

               if(debugBoundingBox && !var1.isInvisible() && !var10) {
                  try {
                     this.renderDebugBoundingBox(var1, var2, var4, var6, var8, var9);
                  } catch (Throwable var16) {
                     throw new ReportedException(CrashReport.makeCrashReport(var16, "Rendering entity hitbox in world"));
                  }
               }
            }
         } else if(this.renderEngine != null) {
            return false;
         }

         return true;
      } catch (Throwable var19) {
         CrashReport var13 = CrashReport.makeCrashReport(var19, "Rendering entity in world");
         CrashReportCategory var14 = var13.makeCategory("Entity being rendered");
         var1.addEntityCrashInfo(var14);
         CrashReportCategory var15 = var13.makeCategory("Renderer details");
         var15.addCrashSection("Assigned renderer", var11);
         var15.addCrashSection("Location", CrashReportCategory.func_85074_a(var2, var4, var6));
         var15.addCrashSection("Rotation", Float.valueOf(var8));
         var15.addCrashSection("Delta", Float.valueOf(var9));
         throw new ReportedException(var13);
      }
   }

   private void renderDebugBoundingBox(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glDepthMask(false);
      GL11.glDisable(3553);
      GL11.glDisable(2896);
      GL11.glDisable(2884);
      GL11.glDisable(3042);
      float var10 = var1.width / 2.0F;
      AxisAlignedBB var11 = AxisAlignedBB.getBoundingBox(var2 - (double)var10, var4, var6 - (double)var10, var2 + (double)var10, var4 + (double)var1.height, var6 + (double)var10);
      RenderGlobal.drawOutlinedBoundingBox(var11, 16777215);
      GL11.glEnable(3553);
      GL11.glEnable(2896);
      GL11.glEnable(2884);
      GL11.glDisable(3042);
      GL11.glDepthMask(true);
   }

   public void set(World var1) {
      this.worldObj = var1;
   }

   public double getDistanceToCamera(double var1, double var3, double var5) {
      double var7 = var1 - this.viewerPosX;
      double var9 = var3 - this.viewerPosY;
      double var11 = var5 - this.viewerPosZ;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   public FontRenderer getFontRenderer() {
      return this.fontRenderer;
   }

   public void updateIcons(IIconRegister var1) {
      Iterator var2 = this.entityRenderMap.values().iterator();

      while(var2.hasNext()) {
         Render var3 = (Render)var2.next();
         var3.updateIcons(var1);
      }

   }

}
