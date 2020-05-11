package net.minecraft.client.renderer.tileentity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderEnchantmentTable;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.RenderEndPortal;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityEnderChestRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererPiston;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class TileEntityRendererDispatcher {

   private Map mapSpecialRenderers = new HashMap();
   public static TileEntityRendererDispatcher instance = new TileEntityRendererDispatcher();
   private FontRenderer field_147557_n;
   public static double staticPlayerX;
   public static double staticPlayerY;
   public static double staticPlayerZ;
   public TextureManager field_147553_e;
   public World field_147550_f;
   public EntityLivingBase field_147551_g;
   public float field_147562_h;
   public float field_147563_i;
   public double field_147560_j;
   public double field_147561_k;
   public double field_147558_l;


   private TileEntityRendererDispatcher() {
      this.mapSpecialRenderers.put(TileEntitySign.class, new TileEntitySignRenderer());
      this.mapSpecialRenderers.put(TileEntityMobSpawner.class, new TileEntityMobSpawnerRenderer());
      this.mapSpecialRenderers.put(TileEntityPiston.class, new TileEntityRendererPiston());
      this.mapSpecialRenderers.put(TileEntityChest.class, new TileEntityChestRenderer());
      this.mapSpecialRenderers.put(TileEntityEnderChest.class, new TileEntityEnderChestRenderer());
      this.mapSpecialRenderers.put(TileEntityEnchantmentTable.class, new RenderEnchantmentTable());
      this.mapSpecialRenderers.put(TileEntityEndPortal.class, new RenderEndPortal());
      this.mapSpecialRenderers.put(TileEntityBeacon.class, new TileEntityBeaconRenderer());
      this.mapSpecialRenderers.put(TileEntitySkull.class, new TileEntitySkullRenderer());
      Iterator var1 = this.mapSpecialRenderers.values().iterator();

      while(var1.hasNext()) {
         TileEntitySpecialRenderer var2 = (TileEntitySpecialRenderer)var1.next();
         var2.func_147497_a(this);
      }

   }

   public TileEntitySpecialRenderer getSpecialRendererByClass(Class var1) {
      TileEntitySpecialRenderer var2 = (TileEntitySpecialRenderer)this.mapSpecialRenderers.get(var1);
      if(var2 == null && var1 != TileEntity.class) {
         var2 = this.getSpecialRendererByClass(var1.getSuperclass());
         this.mapSpecialRenderers.put(var1, var2);
      }

      return var2;
   }

   public boolean hasSpecialRenderer(TileEntity var1) {
      return this.getSpecialRenderer(var1) != null;
   }

   public TileEntitySpecialRenderer getSpecialRenderer(TileEntity var1) {
      return var1 == null?null:this.getSpecialRendererByClass(var1.getClass());
   }

   public void cacheActiveRenderInfo(World var1, TextureManager var2, FontRenderer var3, EntityLivingBase var4, float var5) {
      if(this.field_147550_f != var1) {
         this.func_147543_a(var1);
      }

      this.field_147553_e = var2;
      this.field_147551_g = var4;
      this.field_147557_n = var3;
      this.field_147562_h = var4.prevRotationYaw + (var4.rotationYaw - var4.prevRotationYaw) * var5;
      this.field_147563_i = var4.prevRotationPitch + (var4.rotationPitch - var4.prevRotationPitch) * var5;
      this.field_147560_j = var4.lastTickPosX + (var4.posX - var4.lastTickPosX) * (double)var5;
      this.field_147561_k = var4.lastTickPosY + (var4.posY - var4.lastTickPosY) * (double)var5;
      this.field_147558_l = var4.lastTickPosZ + (var4.posZ - var4.lastTickPosZ) * (double)var5;
   }

   public void renderTileEntity(TileEntity var1, float var2) {
      if(var1.getDistanceFrom(this.field_147560_j, this.field_147561_k, this.field_147558_l) < var1.getMaxRenderDistanceSquared()) {
         int var3 = this.field_147550_f.getLightBrightnessForSkyBlocks(var1.xCoord, var1.yCoord, var1.zCoord, 0);
         int var4 = var3 % 65536;
         int var5 = var3 / 65536;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var4 / 1.0F, (float)var5 / 1.0F);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.renderTileEntityAt(var1, (double)var1.xCoord - staticPlayerX, (double)var1.yCoord - staticPlayerY, (double)var1.zCoord - staticPlayerZ, var2);
      }

   }

   public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
      TileEntitySpecialRenderer var9 = this.getSpecialRenderer(var1);
      if(var9 != null) {
         try {
            var9.renderTileEntityAt(var1, var2, var4, var6, var8);
         } catch (Throwable var13) {
            CrashReport var11 = CrashReport.makeCrashReport(var13, "Rendering Block Entity");
            CrashReportCategory var12 = var11.makeCategory("Block Entity Details");
            var1.func_145828_a(var12);
            throw new ReportedException(var11);
         }
      }

   }

   public void func_147543_a(World var1) {
      this.field_147550_f = var1;
      Iterator var2 = this.mapSpecialRenderers.values().iterator();

      while(var2.hasNext()) {
         TileEntitySpecialRenderer var3 = (TileEntitySpecialRenderer)var2.next();
         if(var3 != null) {
            var3.func_147496_a(var1);
         }
      }

   }

   public FontRenderer getFontRenderer() {
      return this.field_147557_n;
   }

}
