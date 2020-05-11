package net.minecraft.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockTripWire;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderBlocks {

   public IBlockAccess blockAccess;
   private IIcon overrideBlockTexture;
   private boolean flipTexture;
   private boolean field_152631_f;
   private boolean renderAllFaces;
   public static boolean fancyGrass = true;
   public boolean useInventoryTint = true;
   private boolean renderFromInside = false;
   private double renderMinX;
   private double renderMaxX;
   private double renderMinY;
   private double renderMaxY;
   private double renderMinZ;
   private double renderMaxZ;
   private boolean lockBlockBounds;
   private boolean partialRenderBounds;
   private final Minecraft minecraftRB;
   private int uvRotateEast;
   private int uvRotateWest;
   private int uvRotateSouth;
   private int uvRotateNorth;
   private int uvRotateTop;
   private int uvRotateBottom;
   private boolean enableAO;
   private float aoLightValueScratchXYZNNN;
   private float aoLightValueScratchXYNN;
   private float aoLightValueScratchXYZNNP;
   private float aoLightValueScratchYZNN;
   private float aoLightValueScratchYZNP;
   private float aoLightValueScratchXYZPNN;
   private float aoLightValueScratchXYPN;
   private float aoLightValueScratchXYZPNP;
   private float aoLightValueScratchXYZNPN;
   private float aoLightValueScratchXYNP;
   private float aoLightValueScratchXYZNPP;
   private float aoLightValueScratchYZPN;
   private float aoLightValueScratchXYZPPN;
   private float aoLightValueScratchXYPP;
   private float aoLightValueScratchYZPP;
   private float aoLightValueScratchXYZPPP;
   private float aoLightValueScratchXZNN;
   private float aoLightValueScratchXZPN;
   private float aoLightValueScratchXZNP;
   private float aoLightValueScratchXZPP;
   private int aoBrightnessXYZNNN;
   private int aoBrightnessXYNN;
   private int aoBrightnessXYZNNP;
   private int aoBrightnessYZNN;
   private int aoBrightnessYZNP;
   private int aoBrightnessXYZPNN;
   private int aoBrightnessXYPN;
   private int aoBrightnessXYZPNP;
   private int aoBrightnessXYZNPN;
   private int aoBrightnessXYNP;
   private int aoBrightnessXYZNPP;
   private int aoBrightnessYZPN;
   private int aoBrightnessXYZPPN;
   private int aoBrightnessXYPP;
   private int aoBrightnessYZPP;
   private int aoBrightnessXYZPPP;
   private int aoBrightnessXZNN;
   private int aoBrightnessXZPN;
   private int aoBrightnessXZNP;
   private int aoBrightnessXZPP;
   private int brightnessTopLeft;
   private int brightnessBottomLeft;
   private int brightnessBottomRight;
   private int brightnessTopRight;
   private float colorRedTopLeft;
   private float colorRedBottomLeft;
   private float colorRedBottomRight;
   private float colorRedTopRight;
   private float colorGreenTopLeft;
   private float colorGreenBottomLeft;
   private float colorGreenBottomRight;
   private float colorGreenTopRight;
   private float colorBlueTopLeft;
   private float colorBlueBottomLeft;
   private float colorBlueBottomRight;
   private float colorBlueTopRight;


   public RenderBlocks(IBlockAccess var1) {
      this.blockAccess = var1;
      this.field_152631_f = false;
      this.flipTexture = false;
      this.minecraftRB = Minecraft.getMinecraft();
   }

   public RenderBlocks() {
      this.minecraftRB = Minecraft.getMinecraft();
   }

   public void setOverrideBlockTexture(IIcon var1) {
      this.overrideBlockTexture = var1;
   }

   public void clearOverrideBlockTexture() {
      this.overrideBlockTexture = null;
   }

   public boolean hasOverrideBlockTexture() {
      return this.overrideBlockTexture != null;
   }

   public void setRenderFromInside(boolean var1) {
      this.renderFromInside = var1;
   }

   public void setRenderAllFaces(boolean var1) {
      this.renderAllFaces = var1;
   }

   public void setRenderBounds(double var1, double var3, double var5, double var7, double var9, double var11) {
      if(!this.lockBlockBounds) {
         this.renderMinX = var1;
         this.renderMaxX = var7;
         this.renderMinY = var3;
         this.renderMaxY = var9;
         this.renderMinZ = var5;
         this.renderMaxZ = var11;
         this.partialRenderBounds = this.minecraftRB.gameSettings.ambientOcclusion >= 2 && (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D);
      }

   }

   public void setRenderBoundsFromBlock(Block var1) {
      if(!this.lockBlockBounds) {
         this.renderMinX = var1.getBlockBoundsMinX();
         this.renderMaxX = var1.getBlockBoundsMaxX();
         this.renderMinY = var1.getBlockBoundsMinY();
         this.renderMaxY = var1.getBlockBoundsMaxY();
         this.renderMinZ = var1.getBlockBoundsMinZ();
         this.renderMaxZ = var1.getBlockBoundsMaxZ();
         this.partialRenderBounds = this.minecraftRB.gameSettings.ambientOcclusion >= 2 && (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D);
      }

   }

   public void overrideBlockBounds(double var1, double var3, double var5, double var7, double var9, double var11) {
      this.renderMinX = var1;
      this.renderMaxX = var7;
      this.renderMinY = var3;
      this.renderMaxY = var9;
      this.renderMinZ = var5;
      this.renderMaxZ = var11;
      this.lockBlockBounds = true;
      this.partialRenderBounds = this.minecraftRB.gameSettings.ambientOcclusion >= 2 && (this.renderMinX > 0.0D || this.renderMaxX < 1.0D || this.renderMinY > 0.0D || this.renderMaxY < 1.0D || this.renderMinZ > 0.0D || this.renderMaxZ < 1.0D);
   }

   public void unlockBlockBounds() {
      this.lockBlockBounds = false;
   }

   public void renderBlockUsingTexture(Block var1, int var2, int var3, int var4, IIcon var5) {
      this.setOverrideBlockTexture(var5);
      this.renderBlockByRenderType(var1, var2, var3, var4);
      this.clearOverrideBlockTexture();
   }

   public void renderBlockAllFaces(Block var1, int var2, int var3, int var4) {
      this.renderAllFaces = true;
      this.renderBlockByRenderType(var1, var2, var3, var4);
      this.renderAllFaces = false;
   }

   public boolean renderBlockByRenderType(Block var1, int var2, int var3, int var4) {
      int var5 = var1.getRenderType();
      if(var5 == -1) {
         return false;
      } else {
         var1.setBlockBoundsBasedOnState(this.blockAccess, var2, var3, var4);
         this.setRenderBoundsFromBlock(var1);
         return var5 == 0?this.renderStandardBlock(var1, var2, var3, var4):(var5 == 4?this.renderBlockLiquid(var1, var2, var3, var4):(var5 == 31?this.renderBlockLog(var1, var2, var3, var4):(var5 == 1?this.renderCrossedSquares(var1, var2, var3, var4):(var5 == 40?this.renderBlockDoublePlant((BlockDoublePlant)var1, var2, var3, var4):(var5 == 2?this.renderBlockTorch(var1, var2, var3, var4):(var5 == 20?this.renderBlockVine(var1, var2, var3, var4):(var5 == 11?this.renderBlockFence((BlockFence)var1, var2, var3, var4):(var5 == 39?this.renderBlockQuartz(var1, var2, var3, var4):(var5 == 5?this.renderBlockRedstoneWire(var1, var2, var3, var4):(var5 == 13?this.renderBlockCactus(var1, var2, var3, var4):(var5 == 9?this.renderBlockMinecartTrack((BlockRailBase)var1, var2, var3, var4):(var5 == 19?this.renderBlockStem(var1, var2, var3, var4):(var5 == 23?this.renderBlockLilyPad(var1, var2, var3, var4):(var5 == 6?this.renderBlockCrops(var1, var2, var3, var4):(var5 == 3?this.renderBlockFire((BlockFire)var1, var2, var3, var4):(var5 == 8?this.renderBlockLadder(var1, var2, var3, var4):(var5 == 7?this.renderBlockDoor(var1, var2, var3, var4):(var5 == 10?this.renderBlockStairs((BlockStairs)var1, var2, var3, var4):(var5 == 27?this.renderBlockDragonEgg((BlockDragonEgg)var1, var2, var3, var4):(var5 == 32?this.renderBlockWall((BlockWall)var1, var2, var3, var4):(var5 == 12?this.renderBlockLever(var1, var2, var3, var4):(var5 == 29?this.renderBlockTripWireSource(var1, var2, var3, var4):(var5 == 30?this.renderBlockTripWire(var1, var2, var3, var4):(var5 == 14?this.renderBlockBed(var1, var2, var3, var4):(var5 == 15?this.renderBlockRepeater((BlockRedstoneRepeater)var1, var2, var3, var4):(var5 == 36?this.renderBlockRedstoneDiode((BlockRedstoneDiode)var1, var2, var3, var4):(var5 == 37?this.renderBlockRedstoneComparator((BlockRedstoneComparator)var1, var2, var3, var4):(var5 == 16?this.renderPistonBase(var1, var2, var3, var4, false):(var5 == 17?this.renderPistonExtension(var1, var2, var3, var4, true):(var5 == 18?this.renderBlockPane((BlockPane)var1, var2, var3, var4):(var5 == 41?this.renderBlockStainedGlassPane(var1, var2, var3, var4):(var5 == 21?this.renderBlockFenceGate((BlockFenceGate)var1, var2, var3, var4):(var5 == 24?this.renderBlockCauldron((BlockCauldron)var1, var2, var3, var4):(var5 == 33?this.renderBlockFlowerpot((BlockFlowerPot)var1, var2, var3, var4):(var5 == 35?this.renderBlockAnvil((BlockAnvil)var1, var2, var3, var4):(var5 == 25?this.renderBlockBrewingStand((BlockBrewingStand)var1, var2, var3, var4):(var5 == 26?this.renderBlockEndPortalFrame((BlockEndPortalFrame)var1, var2, var3, var4):(var5 == 28?this.renderBlockCocoa((BlockCocoa)var1, var2, var3, var4):(var5 == 34?this.renderBlockBeacon((BlockBeacon)var1, var2, var3, var4):(var5 == 38?this.renderBlockHopper((BlockHopper)var1, var2, var3, var4):false))))))))))))))))))))))))))))))))))))))));
      }
   }

   private boolean renderBlockEndPortalFrame(BlockEndPortalFrame var1, int var2, int var3, int var4) {
      int var5 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      int var6 = var5 & 3;
      if(var6 == 0) {
         this.uvRotateTop = 3;
      } else if(var6 == 3) {
         this.uvRotateTop = 1;
      } else if(var6 == 1) {
         this.uvRotateTop = 2;
      }

      if(!BlockEndPortalFrame.isEnderEyeInserted(var5)) {
         this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.uvRotateTop = 0;
         return true;
      } else {
         this.renderAllFaces = true;
         this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.setOverrideBlockTexture(var1.getIconEndPortalFrameEye());
         this.setRenderBounds(0.25D, 0.8125D, 0.25D, 0.75D, 1.0D, 0.75D);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.renderAllFaces = false;
         this.clearOverrideBlockTexture();
         this.uvRotateTop = 0;
         return true;
      }
   }

   private boolean renderBlockBed(Block var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      int var6 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      int var7 = BlockBed.getDirection(var6);
      boolean var8 = BlockBed.isBlockHeadOfBed(var6);
      float var9 = 0.5F;
      float var10 = 1.0F;
      float var11 = 0.8F;
      float var12 = 0.6F;
      int var25 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4);
      var5.setBrightness(var25);
      var5.setColorOpaque_F(var9, var9, var9);
      IIcon var26 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 0);
      double var27 = (double)var26.getMinU();
      double var29 = (double)var26.getMaxU();
      double var31 = (double)var26.getMinV();
      double var33 = (double)var26.getMaxV();
      double var35 = (double)var2 + this.renderMinX;
      double var37 = (double)var2 + this.renderMaxX;
      double var39 = (double)var3 + this.renderMinY + 0.1875D;
      double var41 = (double)var4 + this.renderMinZ;
      double var43 = (double)var4 + this.renderMaxZ;
      var5.addVertexWithUV(var35, var39, var43, var27, var33);
      var5.addVertexWithUV(var35, var39, var41, var27, var31);
      var5.addVertexWithUV(var37, var39, var41, var29, var31);
      var5.addVertexWithUV(var37, var39, var43, var29, var33);
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4));
      var5.setColorOpaque_F(var10, var10, var10);
      var26 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 1);
      var27 = (double)var26.getMinU();
      var29 = (double)var26.getMaxU();
      var31 = (double)var26.getMinV();
      var33 = (double)var26.getMaxV();
      var35 = var27;
      var37 = var29;
      var39 = var31;
      var41 = var31;
      var43 = var27;
      double var45 = var29;
      double var47 = var33;
      double var49 = var33;
      if(var7 == 0) {
         var37 = var27;
         var39 = var33;
         var43 = var29;
         var49 = var31;
      } else if(var7 == 2) {
         var35 = var29;
         var41 = var33;
         var45 = var27;
         var47 = var31;
      } else if(var7 == 3) {
         var35 = var29;
         var41 = var33;
         var45 = var27;
         var47 = var31;
         var37 = var27;
         var39 = var33;
         var43 = var29;
         var49 = var31;
      }

      double var51 = (double)var2 + this.renderMinX;
      double var53 = (double)var2 + this.renderMaxX;
      double var55 = (double)var3 + this.renderMaxY;
      double var57 = (double)var4 + this.renderMinZ;
      double var59 = (double)var4 + this.renderMaxZ;
      var5.addVertexWithUV(var53, var55, var59, var43, var47);
      var5.addVertexWithUV(var53, var55, var57, var35, var39);
      var5.addVertexWithUV(var51, var55, var57, var37, var41);
      var5.addVertexWithUV(var51, var55, var59, var45, var49);
      int var61 = Direction.directionToFacing[var7];
      if(var8) {
         var61 = Direction.directionToFacing[Direction.rotateOpposite[var7]];
      }

      byte var62 = 4;
      switch(var7) {
      case 0:
         var62 = 5;
         break;
      case 1:
         var62 = 3;
      case 2:
      default:
         break;
      case 3:
         var62 = 2;
      }

      if(var61 != 2 && (this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3, var4 - 1, 2))) {
         var5.setBrightness(this.renderMinZ > 0.0D?var25:var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1));
         var5.setColorOpaque_F(var11, var11, var11);
         this.flipTexture = var62 == 2;
         this.renderFaceZNeg(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 2));
      }

      if(var61 != 3 && (this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3, var4 + 1, 3))) {
         var5.setBrightness(this.renderMaxZ < 1.0D?var25:var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1));
         var5.setColorOpaque_F(var11, var11, var11);
         this.flipTexture = var62 == 3;
         this.renderFaceZPos(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 3));
      }

      if(var61 != 4 && (this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2 - 1, var3, var4, 4))) {
         var5.setBrightness(this.renderMinZ > 0.0D?var25:var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4));
         var5.setColorOpaque_F(var12, var12, var12);
         this.flipTexture = var62 == 4;
         this.renderFaceXNeg(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 4));
      }

      if(var61 != 5 && (this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2 + 1, var3, var4, 5))) {
         var5.setBrightness(this.renderMaxZ < 1.0D?var25:var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4));
         var5.setColorOpaque_F(var12, var12, var12);
         this.flipTexture = var62 == 5;
         this.renderFaceXPos(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 5));
      }

      this.flipTexture = false;
      return true;
   }

   private boolean renderBlockBrewingStand(BlockBrewingStand var1, int var2, int var3, int var4) {
      this.setRenderBounds(0.4375D, 0.0D, 0.4375D, 0.5625D, 0.875D, 0.5625D);
      this.renderStandardBlock(var1, var2, var3, var4);
      this.setOverrideBlockTexture(var1.getIconBrewingStandBase());
      this.renderAllFaces = true;
      this.setRenderBounds(0.5625D, 0.0D, 0.3125D, 0.9375D, 0.125D, 0.6875D);
      this.renderStandardBlock(var1, var2, var3, var4);
      this.setRenderBounds(0.125D, 0.0D, 0.0625D, 0.5D, 0.125D, 0.4375D);
      this.renderStandardBlock(var1, var2, var3, var4);
      this.setRenderBounds(0.125D, 0.0D, 0.5625D, 0.5D, 0.125D, 0.9375D);
      this.renderStandardBlock(var1, var2, var3, var4);
      this.renderAllFaces = false;
      this.clearOverrideBlockTexture();
      Tessellator var5 = Tessellator.instance;
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      int var6 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var7 = (float)(var6 >> 16 & 255) / 255.0F;
      float var8 = (float)(var6 >> 8 & 255) / 255.0F;
      float var9 = (float)(var6 & 255) / 255.0F;
      if(EntityRenderer.anaglyphEnable) {
         float var10 = (var7 * 30.0F + var8 * 59.0F + var9 * 11.0F) / 100.0F;
         float var11 = (var7 * 30.0F + var8 * 70.0F) / 100.0F;
         float var12 = (var7 * 30.0F + var9 * 70.0F) / 100.0F;
         var7 = var10;
         var8 = var11;
         var9 = var12;
      }

      var5.setColorOpaque_F(var7, var8, var9);
      IIcon var31 = this.getBlockIconFromSideAndMetadata(var1, 0, 0);
      if(this.hasOverrideBlockTexture()) {
         var31 = this.overrideBlockTexture;
      }

      double var32 = (double)var31.getMinV();
      double var13 = (double)var31.getMaxV();
      int var15 = this.blockAccess.getBlockMetadata(var2, var3, var4);

      for(int var16 = 0; var16 < 3; ++var16) {
         double var17 = (double)var16 * 3.141592653589793D * 2.0D / 3.0D + 1.5707963267948966D;
         double var19 = (double)var31.getInterpolatedU(8.0D);
         double var21 = (double)var31.getMaxU();
         if((var15 & 1 << var16) != 0) {
            var21 = (double)var31.getMinU();
         }

         double var23 = (double)var2 + 0.5D;
         double var25 = (double)var2 + 0.5D + Math.sin(var17) * 8.0D / 16.0D;
         double var27 = (double)var4 + 0.5D;
         double var29 = (double)var4 + 0.5D + Math.cos(var17) * 8.0D / 16.0D;
         var5.addVertexWithUV(var23, (double)(var3 + 1), var27, var19, var32);
         var5.addVertexWithUV(var23, (double)(var3 + 0), var27, var19, var13);
         var5.addVertexWithUV(var25, (double)(var3 + 0), var29, var21, var13);
         var5.addVertexWithUV(var25, (double)(var3 + 1), var29, var21, var32);
         var5.addVertexWithUV(var25, (double)(var3 + 1), var29, var21, var32);
         var5.addVertexWithUV(var25, (double)(var3 + 0), var29, var21, var13);
         var5.addVertexWithUV(var23, (double)(var3 + 0), var27, var19, var13);
         var5.addVertexWithUV(var23, (double)(var3 + 1), var27, var19, var32);
      }

      var1.setBlockBoundsForItemRender();
      return true;
   }

   private boolean renderBlockCauldron(BlockCauldron var1, int var2, int var3, int var4) {
      this.renderStandardBlock(var1, var2, var3, var4);
      Tessellator var5 = Tessellator.instance;
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      int var6 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var7 = (float)(var6 >> 16 & 255) / 255.0F;
      float var8 = (float)(var6 >> 8 & 255) / 255.0F;
      float var9 = (float)(var6 & 255) / 255.0F;
      float var11;
      if(EntityRenderer.anaglyphEnable) {
         float var10 = (var7 * 30.0F + var8 * 59.0F + var9 * 11.0F) / 100.0F;
         var11 = (var7 * 30.0F + var8 * 70.0F) / 100.0F;
         float var12 = (var7 * 30.0F + var9 * 70.0F) / 100.0F;
         var7 = var10;
         var8 = var11;
         var9 = var12;
      }

      var5.setColorOpaque_F(var7, var8, var9);
      IIcon var15 = var1.getBlockTextureFromSide(2);
      var11 = 0.125F;
      this.renderFaceXPos(var1, (double)((float)var2 - 1.0F + var11), (double)var3, (double)var4, var15);
      this.renderFaceXNeg(var1, (double)((float)var2 + 1.0F - var11), (double)var3, (double)var4, var15);
      this.renderFaceZPos(var1, (double)var2, (double)var3, (double)((float)var4 - 1.0F + var11), var15);
      this.renderFaceZNeg(var1, (double)var2, (double)var3, (double)((float)var4 + 1.0F - var11), var15);
      IIcon var16 = BlockCauldron.getCauldronIcon("inner");
      this.renderFaceYPos(var1, (double)var2, (double)((float)var3 - 1.0F + 0.25F), (double)var4, var16);
      this.renderFaceYNeg(var1, (double)var2, (double)((float)var3 + 1.0F - 0.75F), (double)var4, var16);
      int var13 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      if(var13 > 0) {
         IIcon var14 = BlockLiquid.getLiquidIcon("water_still");
         this.renderFaceYPos(var1, (double)var2, (double)((float)var3 - 1.0F + BlockCauldron.getRenderLiquidLevel(var13)), (double)var4, var14);
      }

      return true;
   }

   private boolean renderBlockFlowerpot(BlockFlowerPot var1, int var2, int var3, int var4) {
      this.renderStandardBlock(var1, var2, var3, var4);
      Tessellator var5 = Tessellator.instance;
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      int var6 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      IIcon var7 = this.getBlockIconFromSide(var1, 0);
      float var8 = (float)(var6 >> 16 & 255) / 255.0F;
      float var9 = (float)(var6 >> 8 & 255) / 255.0F;
      float var10 = (float)(var6 & 255) / 255.0F;
      float var11;
      if(EntityRenderer.anaglyphEnable) {
         var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
         float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
         float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
         var8 = var11;
         var9 = var12;
         var10 = var13;
      }

      var5.setColorOpaque_F(var8, var9, var10);
      var11 = 0.1865F;
      this.renderFaceXPos(var1, (double)((float)var2 - 0.5F + var11), (double)var3, (double)var4, var7);
      this.renderFaceXNeg(var1, (double)((float)var2 + 0.5F - var11), (double)var3, (double)var4, var7);
      this.renderFaceZPos(var1, (double)var2, (double)var3, (double)((float)var4 - 0.5F + var11), var7);
      this.renderFaceZNeg(var1, (double)var2, (double)var3, (double)((float)var4 + 0.5F - var11), var7);
      this.renderFaceYPos(var1, (double)var2, (double)((float)var3 - 0.5F + var11 + 0.1875F), (double)var4, this.getBlockIcon(Blocks.dirt));
      TileEntity var21 = this.blockAccess.getTileEntity(var2, var3, var4);
      if(var21 != null && var21 instanceof TileEntityFlowerPot) {
         Item var22 = ((TileEntityFlowerPot)var21).getFlowerPotItem();
         int var14 = ((TileEntityFlowerPot)var21).getFlowerPotData();
         if(var22 instanceof ItemBlock) {
            Block var15 = Block.getBlockFromItem(var22);
            int var16 = var15.getRenderType();
            float var17 = 0.0F;
            float var18 = 4.0F;
            float var19 = 0.0F;
            var5.addTranslation(var17 / 16.0F, var18 / 16.0F, var19 / 16.0F);
            var6 = var15.colorMultiplier(this.blockAccess, var2, var3, var4);
            if(var6 != 16777215) {
               var8 = (float)(var6 >> 16 & 255) / 255.0F;
               var9 = (float)(var6 >> 8 & 255) / 255.0F;
               var10 = (float)(var6 & 255) / 255.0F;
               var5.setColorOpaque_F(var8, var9, var10);
            }

            if(var16 == 1) {
               this.drawCrossedSquares(this.getBlockIconFromSideAndMetadata(var15, 0, var14), (double)var2, (double)var3, (double)var4, 0.75F);
            } else if(var16 == 13) {
               this.renderAllFaces = true;
               float var20 = 0.125F;
               this.setRenderBounds((double)(0.5F - var20), 0.0D, (double)(0.5F - var20), (double)(0.5F + var20), 0.25D, (double)(0.5F + var20));
               this.renderStandardBlock(var15, var2, var3, var4);
               this.setRenderBounds((double)(0.5F - var20), 0.25D, (double)(0.5F - var20), (double)(0.5F + var20), 0.5D, (double)(0.5F + var20));
               this.renderStandardBlock(var15, var2, var3, var4);
               this.setRenderBounds((double)(0.5F - var20), 0.5D, (double)(0.5F - var20), (double)(0.5F + var20), 0.75D, (double)(0.5F + var20));
               this.renderStandardBlock(var15, var2, var3, var4);
               this.renderAllFaces = false;
               this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }

            var5.addTranslation(-var17 / 16.0F, -var18 / 16.0F, -var19 / 16.0F);
         }
      }

      return true;
   }

   private boolean renderBlockAnvil(BlockAnvil var1, int var2, int var3, int var4) {
      return this.renderBlockAnvilMetadata(var1, var2, var3, var4, this.blockAccess.getBlockMetadata(var2, var3, var4));
   }

   public boolean renderBlockAnvilMetadata(BlockAnvil var1, int var2, int var3, int var4, int var5) {
      Tessellator var6 = Tessellator.instance;
      var6.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      int var7 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var8 = (float)(var7 >> 16 & 255) / 255.0F;
      float var9 = (float)(var7 >> 8 & 255) / 255.0F;
      float var10 = (float)(var7 & 255) / 255.0F;
      if(EntityRenderer.anaglyphEnable) {
         float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
         float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
         float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
         var8 = var11;
         var9 = var12;
         var10 = var13;
      }

      var6.setColorOpaque_F(var8, var9, var10);
      return this.renderBlockAnvilOrient(var1, var2, var3, var4, var5, false);
   }

   private boolean renderBlockAnvilOrient(BlockAnvil var1, int var2, int var3, int var4, int var5, boolean var6) {
      int var7 = var6?0:var5 & 3;
      boolean var8 = false;
      float var9 = 0.0F;
      switch(var7) {
      case 0:
         this.uvRotateSouth = 2;
         this.uvRotateNorth = 1;
         this.uvRotateTop = 3;
         this.uvRotateBottom = 3;
         break;
      case 1:
         this.uvRotateEast = 1;
         this.uvRotateWest = 2;
         this.uvRotateTop = 2;
         this.uvRotateBottom = 1;
         var8 = true;
         break;
      case 2:
         this.uvRotateSouth = 1;
         this.uvRotateNorth = 2;
         break;
      case 3:
         this.uvRotateEast = 2;
         this.uvRotateWest = 1;
         this.uvRotateTop = 1;
         this.uvRotateBottom = 2;
         var8 = true;
      }

      var9 = this.renderBlockAnvilRotate(var1, var2, var3, var4, 0, var9, 0.75F, 0.25F, 0.75F, var8, var6, var5);
      var9 = this.renderBlockAnvilRotate(var1, var2, var3, var4, 1, var9, 0.5F, 0.0625F, 0.625F, var8, var6, var5);
      var9 = this.renderBlockAnvilRotate(var1, var2, var3, var4, 2, var9, 0.25F, 0.3125F, 0.5F, var8, var6, var5);
      this.renderBlockAnvilRotate(var1, var2, var3, var4, 3, var9, 0.625F, 0.375F, 1.0F, var8, var6, var5);
      this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
      this.uvRotateEast = 0;
      this.uvRotateWest = 0;
      this.uvRotateSouth = 0;
      this.uvRotateNorth = 0;
      this.uvRotateTop = 0;
      this.uvRotateBottom = 0;
      return true;
   }

   private float renderBlockAnvilRotate(BlockAnvil var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, float var9, boolean var10, boolean var11, int var12) {
      if(var10) {
         float var13 = var7;
         var7 = var9;
         var9 = var13;
      }

      var7 /= 2.0F;
      var9 /= 2.0F;
      var1.anvilRenderSide = var5;
      this.setRenderBounds((double)(0.5F - var7), (double)var6, (double)(0.5F - var9), (double)(0.5F + var7), (double)(var6 + var8), (double)(0.5F + var9));
      if(var11) {
         Tessellator var14 = Tessellator.instance;
         var14.startDrawingQuads();
         var14.setNormal(0.0F, -1.0F, 0.0F);
         this.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 0, var12));
         var14.draw();
         var14.startDrawingQuads();
         var14.setNormal(0.0F, 1.0F, 0.0F);
         this.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 1, var12));
         var14.draw();
         var14.startDrawingQuads();
         var14.setNormal(0.0F, 0.0F, -1.0F);
         this.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 2, var12));
         var14.draw();
         var14.startDrawingQuads();
         var14.setNormal(0.0F, 0.0F, 1.0F);
         this.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 3, var12));
         var14.draw();
         var14.startDrawingQuads();
         var14.setNormal(-1.0F, 0.0F, 0.0F);
         this.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 4, var12));
         var14.draw();
         var14.startDrawingQuads();
         var14.setNormal(1.0F, 0.0F, 0.0F);
         this.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 5, var12));
         var14.draw();
      } else {
         this.renderStandardBlock(var1, var2, var3, var4);
      }

      return var6 + var8;
   }

   public boolean renderBlockTorch(Block var1, int var2, int var3, int var4) {
      int var5 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      Tessellator var6 = Tessellator.instance;
      var6.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      double var7 = 0.4000000059604645D;
      double var9 = 0.5D - var7;
      double var11 = 0.20000000298023224D;
      if(var5 == 1) {
         this.renderTorchAtAngle(var1, (double)var2 - var9, (double)var3 + var11, (double)var4, -var7, 0.0D, 0);
      } else if(var5 == 2) {
         this.renderTorchAtAngle(var1, (double)var2 + var9, (double)var3 + var11, (double)var4, var7, 0.0D, 0);
      } else if(var5 == 3) {
         this.renderTorchAtAngle(var1, (double)var2, (double)var3 + var11, (double)var4 - var9, 0.0D, -var7, 0);
      } else if(var5 == 4) {
         this.renderTorchAtAngle(var1, (double)var2, (double)var3 + var11, (double)var4 + var9, 0.0D, var7, 0);
      } else {
         this.renderTorchAtAngle(var1, (double)var2, (double)var3, (double)var4, 0.0D, 0.0D, 0);
      }

      return true;
   }

   private boolean renderBlockRepeater(BlockRedstoneRepeater var1, int var2, int var3, int var4) {
      int var5 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      int var6 = var5 & 3;
      int var7 = (var5 & 12) >> 2;
      Tessellator var8 = Tessellator.instance;
      var8.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      var8.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      double var9 = -0.1875D;
      boolean var11 = var1.func_149910_g(this.blockAccess, var2, var3, var4, var5);
      double var12 = 0.0D;
      double var14 = 0.0D;
      double var16 = 0.0D;
      double var18 = 0.0D;
      switch(var6) {
      case 0:
         var18 = -0.3125D;
         var14 = BlockRedstoneRepeater.repeaterTorchOffset[var7];
         break;
      case 1:
         var16 = 0.3125D;
         var12 = -BlockRedstoneRepeater.repeaterTorchOffset[var7];
         break;
      case 2:
         var18 = 0.3125D;
         var14 = -BlockRedstoneRepeater.repeaterTorchOffset[var7];
         break;
      case 3:
         var16 = -0.3125D;
         var12 = BlockRedstoneRepeater.repeaterTorchOffset[var7];
      }

      if(!var11) {
         this.renderTorchAtAngle(var1, (double)var2 + var12, (double)var3 + var9, (double)var4 + var14, 0.0D, 0.0D, 0);
      } else {
         IIcon var20 = this.getBlockIcon(Blocks.bedrock);
         this.setOverrideBlockTexture(var20);
         float var21 = 2.0F;
         float var22 = 14.0F;
         float var23 = 7.0F;
         float var24 = 9.0F;
         switch(var6) {
         case 1:
         case 3:
            var21 = 7.0F;
            var22 = 9.0F;
            var23 = 2.0F;
            var24 = 14.0F;
         case 0:
         case 2:
         default:
            this.setRenderBounds((double)(var21 / 16.0F + (float)var12), 0.125D, (double)(var23 / 16.0F + (float)var14), (double)(var22 / 16.0F + (float)var12), 0.25D, (double)(var24 / 16.0F + (float)var14));
            double var25 = (double)var20.getInterpolatedU((double)var21);
            double var27 = (double)var20.getInterpolatedV((double)var23);
            double var29 = (double)var20.getInterpolatedU((double)var22);
            double var31 = (double)var20.getInterpolatedV((double)var24);
            var8.addVertexWithUV((double)((float)var2 + var21 / 16.0F) + var12, (double)((float)var3 + 0.25F), (double)((float)var4 + var23 / 16.0F) + var14, var25, var27);
            var8.addVertexWithUV((double)((float)var2 + var21 / 16.0F) + var12, (double)((float)var3 + 0.25F), (double)((float)var4 + var24 / 16.0F) + var14, var25, var31);
            var8.addVertexWithUV((double)((float)var2 + var22 / 16.0F) + var12, (double)((float)var3 + 0.25F), (double)((float)var4 + var24 / 16.0F) + var14, var29, var31);
            var8.addVertexWithUV((double)((float)var2 + var22 / 16.0F) + var12, (double)((float)var3 + 0.25F), (double)((float)var4 + var23 / 16.0F) + var14, var29, var27);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
            this.clearOverrideBlockTexture();
         }
      }

      var8.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      var8.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      this.renderTorchAtAngle(var1, (double)var2 + var16, (double)var3 + var9, (double)var4 + var18, 0.0D, 0.0D, 0);
      this.renderBlockRedstoneDiode(var1, var2, var3, var4);
      return true;
   }

   private boolean renderBlockRedstoneComparator(BlockRedstoneComparator var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      int var6 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      int var7 = var6 & 3;
      double var8 = 0.0D;
      double var10 = -0.1875D;
      double var12 = 0.0D;
      double var14 = 0.0D;
      double var16 = 0.0D;
      IIcon var18;
      if(var1.func_149969_d(var6)) {
         var18 = Blocks.redstone_torch.getBlockTextureFromSide(0);
      } else {
         var10 -= 0.1875D;
         var18 = Blocks.unlit_redstone_torch.getBlockTextureFromSide(0);
      }

      switch(var7) {
      case 0:
         var12 = -0.3125D;
         var16 = 1.0D;
         break;
      case 1:
         var8 = 0.3125D;
         var14 = -1.0D;
         break;
      case 2:
         var12 = 0.3125D;
         var16 = -1.0D;
         break;
      case 3:
         var8 = -0.3125D;
         var14 = 1.0D;
      }

      this.renderTorchAtAngle(var1, (double)var2 + 0.25D * var14 + 0.1875D * var16, (double)((float)var3 - 0.1875F), (double)var4 + 0.25D * var16 + 0.1875D * var14, 0.0D, 0.0D, var6);
      this.renderTorchAtAngle(var1, (double)var2 + 0.25D * var14 + -0.1875D * var16, (double)((float)var3 - 0.1875F), (double)var4 + 0.25D * var16 + -0.1875D * var14, 0.0D, 0.0D, var6);
      this.setOverrideBlockTexture(var18);
      this.renderTorchAtAngle(var1, (double)var2 + var8, (double)var3 + var10, (double)var4 + var12, 0.0D, 0.0D, var6);
      this.clearOverrideBlockTexture();
      this.renderBlockRedstoneDiodeMetadata(var1, var2, var3, var4, var7);
      return true;
   }

   private boolean renderBlockRedstoneDiode(BlockRedstoneDiode var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      this.renderBlockRedstoneDiodeMetadata(var1, var2, var3, var4, this.blockAccess.getBlockMetadata(var2, var3, var4) & 3);
      return true;
   }

   private void renderBlockRedstoneDiodeMetadata(BlockRedstoneDiode var1, int var2, int var3, int var4, int var5) {
      this.renderStandardBlock(var1, var2, var3, var4);
      Tessellator var6 = Tessellator.instance;
      var6.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      int var7 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      IIcon var8 = this.getBlockIconFromSideAndMetadata(var1, 1, var7);
      double var9 = (double)var8.getMinU();
      double var11 = (double)var8.getMaxU();
      double var13 = (double)var8.getMinV();
      double var15 = (double)var8.getMaxV();
      double var17 = 0.125D;
      double var19 = (double)(var2 + 1);
      double var21 = (double)(var2 + 1);
      double var23 = (double)(var2 + 0);
      double var25 = (double)(var2 + 0);
      double var27 = (double)(var4 + 0);
      double var29 = (double)(var4 + 1);
      double var31 = (double)(var4 + 1);
      double var33 = (double)(var4 + 0);
      double var35 = (double)var3 + var17;
      if(var5 == 2) {
         var19 = var21 = (double)(var2 + 0);
         var23 = var25 = (double)(var2 + 1);
         var27 = var33 = (double)(var4 + 1);
         var29 = var31 = (double)(var4 + 0);
      } else if(var5 == 3) {
         var19 = var25 = (double)(var2 + 0);
         var21 = var23 = (double)(var2 + 1);
         var27 = var29 = (double)(var4 + 0);
         var31 = var33 = (double)(var4 + 1);
      } else if(var5 == 1) {
         var19 = var25 = (double)(var2 + 1);
         var21 = var23 = (double)(var2 + 0);
         var27 = var29 = (double)(var4 + 1);
         var31 = var33 = (double)(var4 + 0);
      }

      var6.addVertexWithUV(var25, var35, var33, var9, var13);
      var6.addVertexWithUV(var23, var35, var31, var9, var15);
      var6.addVertexWithUV(var21, var35, var29, var11, var15);
      var6.addVertexWithUV(var19, var35, var27, var11, var13);
   }

   public void renderPistonBaseAllFaces(Block var1, int var2, int var3, int var4) {
      this.renderAllFaces = true;
      this.renderPistonBase(var1, var2, var3, var4, true);
      this.renderAllFaces = false;
   }

   private boolean renderPistonBase(Block var1, int var2, int var3, int var4, boolean var5) {
      int var6 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      boolean var7 = var5 || (var6 & 8) != 0;
      int var8 = BlockPistonBase.getPistonOrientation(var6);
      float var9 = 0.25F;
      if(var7) {
         switch(var8) {
         case 0:
            this.uvRotateEast = 3;
            this.uvRotateWest = 3;
            this.uvRotateSouth = 3;
            this.uvRotateNorth = 3;
            this.setRenderBounds(0.0D, 0.25D, 0.0D, 1.0D, 1.0D, 1.0D);
            break;
         case 1:
            this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
            break;
         case 2:
            this.uvRotateSouth = 1;
            this.uvRotateNorth = 2;
            this.setRenderBounds(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D);
            break;
         case 3:
            this.uvRotateSouth = 2;
            this.uvRotateNorth = 1;
            this.uvRotateTop = 3;
            this.uvRotateBottom = 3;
            this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D);
            break;
         case 4:
            this.uvRotateEast = 1;
            this.uvRotateWest = 2;
            this.uvRotateTop = 2;
            this.uvRotateBottom = 1;
            this.setRenderBounds(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            break;
         case 5:
            this.uvRotateEast = 2;
            this.uvRotateWest = 1;
            this.uvRotateTop = 1;
            this.uvRotateBottom = 2;
            this.setRenderBounds(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D);
         }

         ((BlockPistonBase)var1).func_150070_b((float)this.renderMinX, (float)this.renderMinY, (float)this.renderMinZ, (float)this.renderMaxX, (float)this.renderMaxY, (float)this.renderMaxZ);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.uvRotateEast = 0;
         this.uvRotateWest = 0;
         this.uvRotateSouth = 0;
         this.uvRotateNorth = 0;
         this.uvRotateTop = 0;
         this.uvRotateBottom = 0;
         this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
         ((BlockPistonBase)var1).func_150070_b((float)this.renderMinX, (float)this.renderMinY, (float)this.renderMinZ, (float)this.renderMaxX, (float)this.renderMaxY, (float)this.renderMaxZ);
      } else {
         switch(var8) {
         case 0:
            this.uvRotateEast = 3;
            this.uvRotateWest = 3;
            this.uvRotateSouth = 3;
            this.uvRotateNorth = 3;
         case 1:
         default:
            break;
         case 2:
            this.uvRotateSouth = 1;
            this.uvRotateNorth = 2;
            break;
         case 3:
            this.uvRotateSouth = 2;
            this.uvRotateNorth = 1;
            this.uvRotateTop = 3;
            this.uvRotateBottom = 3;
            break;
         case 4:
            this.uvRotateEast = 1;
            this.uvRotateWest = 2;
            this.uvRotateTop = 2;
            this.uvRotateBottom = 1;
            break;
         case 5:
            this.uvRotateEast = 2;
            this.uvRotateWest = 1;
            this.uvRotateTop = 1;
            this.uvRotateBottom = 2;
         }

         this.renderStandardBlock(var1, var2, var3, var4);
         this.uvRotateEast = 0;
         this.uvRotateWest = 0;
         this.uvRotateSouth = 0;
         this.uvRotateNorth = 0;
         this.uvRotateTop = 0;
         this.uvRotateBottom = 0;
      }

      return true;
   }

   private void renderPistonRodUD(double var1, double var3, double var5, double var7, double var9, double var11, float var13, double var14) {
      IIcon var16 = BlockPistonBase.getPistonBaseIcon("piston_side");
      if(this.hasOverrideBlockTexture()) {
         var16 = this.overrideBlockTexture;
      }

      Tessellator var17 = Tessellator.instance;
      double var18 = (double)var16.getMinU();
      double var20 = (double)var16.getMinV();
      double var22 = (double)var16.getInterpolatedU(var14);
      double var24 = (double)var16.getInterpolatedV(4.0D);
      var17.setColorOpaque_F(var13, var13, var13);
      var17.addVertexWithUV(var1, var7, var9, var22, var20);
      var17.addVertexWithUV(var1, var5, var9, var18, var20);
      var17.addVertexWithUV(var3, var5, var11, var18, var24);
      var17.addVertexWithUV(var3, var7, var11, var22, var24);
   }

   private void renderPistonRodSN(double var1, double var3, double var5, double var7, double var9, double var11, float var13, double var14) {
      IIcon var16 = BlockPistonBase.getPistonBaseIcon("piston_side");
      if(this.hasOverrideBlockTexture()) {
         var16 = this.overrideBlockTexture;
      }

      Tessellator var17 = Tessellator.instance;
      double var18 = (double)var16.getMinU();
      double var20 = (double)var16.getMinV();
      double var22 = (double)var16.getInterpolatedU(var14);
      double var24 = (double)var16.getInterpolatedV(4.0D);
      var17.setColorOpaque_F(var13, var13, var13);
      var17.addVertexWithUV(var1, var5, var11, var22, var20);
      var17.addVertexWithUV(var1, var5, var9, var18, var20);
      var17.addVertexWithUV(var3, var7, var9, var18, var24);
      var17.addVertexWithUV(var3, var7, var11, var22, var24);
   }

   private void renderPistonRodEW(double var1, double var3, double var5, double var7, double var9, double var11, float var13, double var14) {
      IIcon var16 = BlockPistonBase.getPistonBaseIcon("piston_side");
      if(this.hasOverrideBlockTexture()) {
         var16 = this.overrideBlockTexture;
      }

      Tessellator var17 = Tessellator.instance;
      double var18 = (double)var16.getMinU();
      double var20 = (double)var16.getMinV();
      double var22 = (double)var16.getInterpolatedU(var14);
      double var24 = (double)var16.getInterpolatedV(4.0D);
      var17.setColorOpaque_F(var13, var13, var13);
      var17.addVertexWithUV(var3, var5, var9, var22, var20);
      var17.addVertexWithUV(var1, var5, var9, var18, var20);
      var17.addVertexWithUV(var1, var7, var11, var18, var24);
      var17.addVertexWithUV(var3, var7, var11, var22, var24);
   }

   public void renderPistonExtensionAllFaces(Block var1, int var2, int var3, int var4, boolean var5) {
      this.renderAllFaces = true;
      this.renderPistonExtension(var1, var2, var3, var4, var5);
      this.renderAllFaces = false;
   }

   private boolean renderPistonExtension(Block var1, int var2, int var3, int var4, boolean var5) {
      int var6 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      int var7 = BlockPistonExtension.getDirectionMeta(var6);
      float var8 = 0.25F;
      float var9 = 0.375F;
      float var10 = 0.625F;
      float var11 = var5?1.0F:0.5F;
      double var12 = var5?16.0D:8.0D;
      switch(var7) {
      case 0:
         this.uvRotateEast = 3;
         this.uvRotateWest = 3;
         this.uvRotateSouth = 3;
         this.uvRotateNorth = 3;
         this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.renderPistonRodUD((double)((float)var2 + 0.375F), (double)((float)var2 + 0.625F), (double)((float)var3 + 0.25F), (double)((float)var3 + 0.25F + var11), (double)((float)var4 + 0.625F), (double)((float)var4 + 0.625F), 0.8F, var12);
         this.renderPistonRodUD((double)((float)var2 + 0.625F), (double)((float)var2 + 0.375F), (double)((float)var3 + 0.25F), (double)((float)var3 + 0.25F + var11), (double)((float)var4 + 0.375F), (double)((float)var4 + 0.375F), 0.8F, var12);
         this.renderPistonRodUD((double)((float)var2 + 0.375F), (double)((float)var2 + 0.375F), (double)((float)var3 + 0.25F), (double)((float)var3 + 0.25F + var11), (double)((float)var4 + 0.375F), (double)((float)var4 + 0.625F), 0.6F, var12);
         this.renderPistonRodUD((double)((float)var2 + 0.625F), (double)((float)var2 + 0.625F), (double)((float)var3 + 0.25F), (double)((float)var3 + 0.25F + var11), (double)((float)var4 + 0.625F), (double)((float)var4 + 0.375F), 0.6F, var12);
         break;
      case 1:
         this.setRenderBounds(0.0D, 0.75D, 0.0D, 1.0D, 1.0D, 1.0D);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.renderPistonRodUD((double)((float)var2 + 0.375F), (double)((float)var2 + 0.625F), (double)((float)var3 - 0.25F + 1.0F - var11), (double)((float)var3 - 0.25F + 1.0F), (double)((float)var4 + 0.625F), (double)((float)var4 + 0.625F), 0.8F, var12);
         this.renderPistonRodUD((double)((float)var2 + 0.625F), (double)((float)var2 + 0.375F), (double)((float)var3 - 0.25F + 1.0F - var11), (double)((float)var3 - 0.25F + 1.0F), (double)((float)var4 + 0.375F), (double)((float)var4 + 0.375F), 0.8F, var12);
         this.renderPistonRodUD((double)((float)var2 + 0.375F), (double)((float)var2 + 0.375F), (double)((float)var3 - 0.25F + 1.0F - var11), (double)((float)var3 - 0.25F + 1.0F), (double)((float)var4 + 0.375F), (double)((float)var4 + 0.625F), 0.6F, var12);
         this.renderPistonRodUD((double)((float)var2 + 0.625F), (double)((float)var2 + 0.625F), (double)((float)var3 - 0.25F + 1.0F - var11), (double)((float)var3 - 0.25F + 1.0F), (double)((float)var4 + 0.625F), (double)((float)var4 + 0.375F), 0.6F, var12);
         break;
      case 2:
         this.uvRotateSouth = 1;
         this.uvRotateNorth = 2;
         this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.25D);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.renderPistonRodSN((double)((float)var2 + 0.375F), (double)((float)var2 + 0.375F), (double)((float)var3 + 0.625F), (double)((float)var3 + 0.375F), (double)((float)var4 + 0.25F), (double)((float)var4 + 0.25F + var11), 0.6F, var12);
         this.renderPistonRodSN((double)((float)var2 + 0.625F), (double)((float)var2 + 0.625F), (double)((float)var3 + 0.375F), (double)((float)var3 + 0.625F), (double)((float)var4 + 0.25F), (double)((float)var4 + 0.25F + var11), 0.6F, var12);
         this.renderPistonRodSN((double)((float)var2 + 0.375F), (double)((float)var2 + 0.625F), (double)((float)var3 + 0.375F), (double)((float)var3 + 0.375F), (double)((float)var4 + 0.25F), (double)((float)var4 + 0.25F + var11), 0.5F, var12);
         this.renderPistonRodSN((double)((float)var2 + 0.625F), (double)((float)var2 + 0.375F), (double)((float)var3 + 0.625F), (double)((float)var3 + 0.625F), (double)((float)var4 + 0.25F), (double)((float)var4 + 0.25F + var11), 1.0F, var12);
         break;
      case 3:
         this.uvRotateSouth = 2;
         this.uvRotateNorth = 1;
         this.uvRotateTop = 3;
         this.uvRotateBottom = 3;
         this.setRenderBounds(0.0D, 0.0D, 0.75D, 1.0D, 1.0D, 1.0D);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.renderPistonRodSN((double)((float)var2 + 0.375F), (double)((float)var2 + 0.375F), (double)((float)var3 + 0.625F), (double)((float)var3 + 0.375F), (double)((float)var4 - 0.25F + 1.0F - var11), (double)((float)var4 - 0.25F + 1.0F), 0.6F, var12);
         this.renderPistonRodSN((double)((float)var2 + 0.625F), (double)((float)var2 + 0.625F), (double)((float)var3 + 0.375F), (double)((float)var3 + 0.625F), (double)((float)var4 - 0.25F + 1.0F - var11), (double)((float)var4 - 0.25F + 1.0F), 0.6F, var12);
         this.renderPistonRodSN((double)((float)var2 + 0.375F), (double)((float)var2 + 0.625F), (double)((float)var3 + 0.375F), (double)((float)var3 + 0.375F), (double)((float)var4 - 0.25F + 1.0F - var11), (double)((float)var4 - 0.25F + 1.0F), 0.5F, var12);
         this.renderPistonRodSN((double)((float)var2 + 0.625F), (double)((float)var2 + 0.375F), (double)((float)var3 + 0.625F), (double)((float)var3 + 0.625F), (double)((float)var4 - 0.25F + 1.0F - var11), (double)((float)var4 - 0.25F + 1.0F), 1.0F, var12);
         break;
      case 4:
         this.uvRotateEast = 1;
         this.uvRotateWest = 2;
         this.uvRotateTop = 2;
         this.uvRotateBottom = 1;
         this.setRenderBounds(0.0D, 0.0D, 0.0D, 0.25D, 1.0D, 1.0D);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.renderPistonRodEW((double)((float)var2 + 0.25F), (double)((float)var2 + 0.25F + var11), (double)((float)var3 + 0.375F), (double)((float)var3 + 0.375F), (double)((float)var4 + 0.625F), (double)((float)var4 + 0.375F), 0.5F, var12);
         this.renderPistonRodEW((double)((float)var2 + 0.25F), (double)((float)var2 + 0.25F + var11), (double)((float)var3 + 0.625F), (double)((float)var3 + 0.625F), (double)((float)var4 + 0.375F), (double)((float)var4 + 0.625F), 1.0F, var12);
         this.renderPistonRodEW((double)((float)var2 + 0.25F), (double)((float)var2 + 0.25F + var11), (double)((float)var3 + 0.375F), (double)((float)var3 + 0.625F), (double)((float)var4 + 0.375F), (double)((float)var4 + 0.375F), 0.6F, var12);
         this.renderPistonRodEW((double)((float)var2 + 0.25F), (double)((float)var2 + 0.25F + var11), (double)((float)var3 + 0.625F), (double)((float)var3 + 0.375F), (double)((float)var4 + 0.625F), (double)((float)var4 + 0.625F), 0.6F, var12);
         break;
      case 5:
         this.uvRotateEast = 2;
         this.uvRotateWest = 1;
         this.uvRotateTop = 1;
         this.uvRotateBottom = 2;
         this.setRenderBounds(0.75D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.renderPistonRodEW((double)((float)var2 - 0.25F + 1.0F - var11), (double)((float)var2 - 0.25F + 1.0F), (double)((float)var3 + 0.375F), (double)((float)var3 + 0.375F), (double)((float)var4 + 0.625F), (double)((float)var4 + 0.375F), 0.5F, var12);
         this.renderPistonRodEW((double)((float)var2 - 0.25F + 1.0F - var11), (double)((float)var2 - 0.25F + 1.0F), (double)((float)var3 + 0.625F), (double)((float)var3 + 0.625F), (double)((float)var4 + 0.375F), (double)((float)var4 + 0.625F), 1.0F, var12);
         this.renderPistonRodEW((double)((float)var2 - 0.25F + 1.0F - var11), (double)((float)var2 - 0.25F + 1.0F), (double)((float)var3 + 0.375F), (double)((float)var3 + 0.625F), (double)((float)var4 + 0.375F), (double)((float)var4 + 0.375F), 0.6F, var12);
         this.renderPistonRodEW((double)((float)var2 - 0.25F + 1.0F - var11), (double)((float)var2 - 0.25F + 1.0F), (double)((float)var3 + 0.625F), (double)((float)var3 + 0.375F), (double)((float)var4 + 0.625F), (double)((float)var4 + 0.625F), 0.6F, var12);
      }

      this.uvRotateEast = 0;
      this.uvRotateWest = 0;
      this.uvRotateSouth = 0;
      this.uvRotateNorth = 0;
      this.uvRotateTop = 0;
      this.uvRotateBottom = 0;
      this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
      return true;
   }

   public boolean renderBlockLever(Block var1, int var2, int var3, int var4) {
      int var5 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      int var6 = var5 & 7;
      boolean var7 = (var5 & 8) > 0;
      Tessellator var8 = Tessellator.instance;
      boolean var9 = this.hasOverrideBlockTexture();
      if(!var9) {
         this.setOverrideBlockTexture(this.getBlockIcon(Blocks.cobblestone));
      }

      float var10 = 0.25F;
      float var11 = 0.1875F;
      float var12 = 0.1875F;
      if(var6 == 5) {
         this.setRenderBounds((double)(0.5F - var11), 0.0D, (double)(0.5F - var10), (double)(0.5F + var11), (double)var12, (double)(0.5F + var10));
      } else if(var6 == 6) {
         this.setRenderBounds((double)(0.5F - var10), 0.0D, (double)(0.5F - var11), (double)(0.5F + var10), (double)var12, (double)(0.5F + var11));
      } else if(var6 == 4) {
         this.setRenderBounds((double)(0.5F - var11), (double)(0.5F - var10), (double)(1.0F - var12), (double)(0.5F + var11), (double)(0.5F + var10), 1.0D);
      } else if(var6 == 3) {
         this.setRenderBounds((double)(0.5F - var11), (double)(0.5F - var10), 0.0D, (double)(0.5F + var11), (double)(0.5F + var10), (double)var12);
      } else if(var6 == 2) {
         this.setRenderBounds((double)(1.0F - var12), (double)(0.5F - var10), (double)(0.5F - var11), 1.0D, (double)(0.5F + var10), (double)(0.5F + var11));
      } else if(var6 == 1) {
         this.setRenderBounds(0.0D, (double)(0.5F - var10), (double)(0.5F - var11), (double)var12, (double)(0.5F + var10), (double)(0.5F + var11));
      } else if(var6 == 0) {
         this.setRenderBounds((double)(0.5F - var10), (double)(1.0F - var12), (double)(0.5F - var11), (double)(0.5F + var10), 1.0D, (double)(0.5F + var11));
      } else if(var6 == 7) {
         this.setRenderBounds((double)(0.5F - var11), (double)(1.0F - var12), (double)(0.5F - var10), (double)(0.5F + var11), 1.0D, (double)(0.5F + var10));
      }

      this.renderStandardBlock(var1, var2, var3, var4);
      if(!var9) {
         this.clearOverrideBlockTexture();
      }

      var8.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      var8.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      IIcon var13 = this.getBlockIconFromSide(var1, 0);
      if(this.hasOverrideBlockTexture()) {
         var13 = this.overrideBlockTexture;
      }

      double var14 = (double)var13.getMinU();
      double var16 = (double)var13.getMinV();
      double var18 = (double)var13.getMaxU();
      double var20 = (double)var13.getMaxV();
      Vec3[] var22 = new Vec3[8];
      float var23 = 0.0625F;
      float var24 = 0.0625F;
      float var25 = 0.625F;
      var22[0] = Vec3.createVectorHelper((double)(-var23), 0.0D, (double)(-var24));
      var22[1] = Vec3.createVectorHelper((double)var23, 0.0D, (double)(-var24));
      var22[2] = Vec3.createVectorHelper((double)var23, 0.0D, (double)var24);
      var22[3] = Vec3.createVectorHelper((double)(-var23), 0.0D, (double)var24);
      var22[4] = Vec3.createVectorHelper((double)(-var23), (double)var25, (double)(-var24));
      var22[5] = Vec3.createVectorHelper((double)var23, (double)var25, (double)(-var24));
      var22[6] = Vec3.createVectorHelper((double)var23, (double)var25, (double)var24);
      var22[7] = Vec3.createVectorHelper((double)(-var23), (double)var25, (double)var24);

      for(int var26 = 0; var26 < 8; ++var26) {
         if(var7) {
            var22[var26].zCoord -= 0.0625D;
            var22[var26].rotateAroundX(0.69813174F);
         } else {
            var22[var26].zCoord += 0.0625D;
            var22[var26].rotateAroundX(-0.69813174F);
         }

         if(var6 == 0 || var6 == 7) {
            var22[var26].rotateAroundZ(3.1415927F);
         }

         if(var6 == 6 || var6 == 0) {
            var22[var26].rotateAroundY(1.5707964F);
         }

         if(var6 > 0 && var6 < 5) {
            var22[var26].yCoord -= 0.375D;
            var22[var26].rotateAroundX(1.5707964F);
            if(var6 == 4) {
               var22[var26].rotateAroundY(0.0F);
            }

            if(var6 == 3) {
               var22[var26].rotateAroundY(3.1415927F);
            }

            if(var6 == 2) {
               var22[var26].rotateAroundY(1.5707964F);
            }

            if(var6 == 1) {
               var22[var26].rotateAroundY(-1.5707964F);
            }

            var22[var26].xCoord += (double)var2 + 0.5D;
            var22[var26].yCoord += (double)((float)var3 + 0.5F);
            var22[var26].zCoord += (double)var4 + 0.5D;
         } else if(var6 != 0 && var6 != 7) {
            var22[var26].xCoord += (double)var2 + 0.5D;
            var22[var26].yCoord += (double)((float)var3 + 0.125F);
            var22[var26].zCoord += (double)var4 + 0.5D;
         } else {
            var22[var26].xCoord += (double)var2 + 0.5D;
            var22[var26].yCoord += (double)((float)var3 + 0.875F);
            var22[var26].zCoord += (double)var4 + 0.5D;
         }
      }

      Vec3 var31 = null;
      Vec3 var27 = null;
      Vec3 var28 = null;
      Vec3 var29 = null;

      for(int var30 = 0; var30 < 6; ++var30) {
         if(var30 == 0) {
            var14 = (double)var13.getInterpolatedU(7.0D);
            var16 = (double)var13.getInterpolatedV(6.0D);
            var18 = (double)var13.getInterpolatedU(9.0D);
            var20 = (double)var13.getInterpolatedV(8.0D);
         } else if(var30 == 2) {
            var14 = (double)var13.getInterpolatedU(7.0D);
            var16 = (double)var13.getInterpolatedV(6.0D);
            var18 = (double)var13.getInterpolatedU(9.0D);
            var20 = (double)var13.getMaxV();
         }

         if(var30 == 0) {
            var31 = var22[0];
            var27 = var22[1];
            var28 = var22[2];
            var29 = var22[3];
         } else if(var30 == 1) {
            var31 = var22[7];
            var27 = var22[6];
            var28 = var22[5];
            var29 = var22[4];
         } else if(var30 == 2) {
            var31 = var22[1];
            var27 = var22[0];
            var28 = var22[4];
            var29 = var22[5];
         } else if(var30 == 3) {
            var31 = var22[2];
            var27 = var22[1];
            var28 = var22[5];
            var29 = var22[6];
         } else if(var30 == 4) {
            var31 = var22[3];
            var27 = var22[2];
            var28 = var22[6];
            var29 = var22[7];
         } else if(var30 == 5) {
            var31 = var22[0];
            var27 = var22[3];
            var28 = var22[7];
            var29 = var22[4];
         }

         var8.addVertexWithUV(var31.xCoord, var31.yCoord, var31.zCoord, var14, var20);
         var8.addVertexWithUV(var27.xCoord, var27.yCoord, var27.zCoord, var18, var20);
         var8.addVertexWithUV(var28.xCoord, var28.yCoord, var28.zCoord, var18, var16);
         var8.addVertexWithUV(var29.xCoord, var29.yCoord, var29.zCoord, var14, var16);
      }

      return true;
   }

   public boolean renderBlockTripWireSource(Block var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      int var6 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      int var7 = var6 & 3;
      boolean var8 = (var6 & 4) == 4;
      boolean var9 = (var6 & 8) == 8;
      boolean var10 = !World.doesBlockHaveSolidTopSurface(this.blockAccess, var2, var3 - 1, var4);
      boolean var11 = this.hasOverrideBlockTexture();
      if(!var11) {
         this.setOverrideBlockTexture(this.getBlockIcon(Blocks.planks));
      }

      float var12 = 0.25F;
      float var13 = 0.125F;
      float var14 = 0.125F;
      float var15 = 0.3F - var12;
      float var16 = 0.3F + var12;
      if(var7 == 2) {
         this.setRenderBounds((double)(0.5F - var13), (double)var15, (double)(1.0F - var14), (double)(0.5F + var13), (double)var16, 1.0D);
      } else if(var7 == 0) {
         this.setRenderBounds((double)(0.5F - var13), (double)var15, 0.0D, (double)(0.5F + var13), (double)var16, (double)var14);
      } else if(var7 == 1) {
         this.setRenderBounds((double)(1.0F - var14), (double)var15, (double)(0.5F - var13), 1.0D, (double)var16, (double)(0.5F + var13));
      } else if(var7 == 3) {
         this.setRenderBounds(0.0D, (double)var15, (double)(0.5F - var13), (double)var14, (double)var16, (double)(0.5F + var13));
      }

      this.renderStandardBlock(var1, var2, var3, var4);
      if(!var11) {
         this.clearOverrideBlockTexture();
      }

      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      IIcon var17 = this.getBlockIconFromSide(var1, 0);
      if(this.hasOverrideBlockTexture()) {
         var17 = this.overrideBlockTexture;
      }

      double var18 = (double)var17.getMinU();
      double var20 = (double)var17.getMinV();
      double var22 = (double)var17.getMaxU();
      double var24 = (double)var17.getMaxV();
      Vec3[] var26 = new Vec3[8];
      float var27 = 0.046875F;
      float var28 = 0.046875F;
      float var29 = 0.3125F;
      var26[0] = Vec3.createVectorHelper((double)(-var27), 0.0D, (double)(-var28));
      var26[1] = Vec3.createVectorHelper((double)var27, 0.0D, (double)(-var28));
      var26[2] = Vec3.createVectorHelper((double)var27, 0.0D, (double)var28);
      var26[3] = Vec3.createVectorHelper((double)(-var27), 0.0D, (double)var28);
      var26[4] = Vec3.createVectorHelper((double)(-var27), (double)var29, (double)(-var28));
      var26[5] = Vec3.createVectorHelper((double)var27, (double)var29, (double)(-var28));
      var26[6] = Vec3.createVectorHelper((double)var27, (double)var29, (double)var28);
      var26[7] = Vec3.createVectorHelper((double)(-var27), (double)var29, (double)var28);

      for(int var30 = 0; var30 < 8; ++var30) {
         var26[var30].zCoord += 0.0625D;
         if(var9) {
            var26[var30].rotateAroundX(0.5235988F);
            var26[var30].yCoord -= 0.4375D;
         } else if(var8) {
            var26[var30].rotateAroundX(0.08726647F);
            var26[var30].yCoord -= 0.4375D;
         } else {
            var26[var30].rotateAroundX(-0.69813174F);
            var26[var30].yCoord -= 0.375D;
         }

         var26[var30].rotateAroundX(1.5707964F);
         if(var7 == 2) {
            var26[var30].rotateAroundY(0.0F);
         }

         if(var7 == 0) {
            var26[var30].rotateAroundY(3.1415927F);
         }

         if(var7 == 1) {
            var26[var30].rotateAroundY(1.5707964F);
         }

         if(var7 == 3) {
            var26[var30].rotateAroundY(-1.5707964F);
         }

         var26[var30].xCoord += (double)var2 + 0.5D;
         var26[var30].yCoord += (double)((float)var3 + 0.3125F);
         var26[var30].zCoord += (double)var4 + 0.5D;
      }

      Vec3 var60 = null;
      Vec3 var31 = null;
      Vec3 var32 = null;
      Vec3 var33 = null;
      byte var34 = 7;
      byte var35 = 9;
      byte var36 = 9;
      byte var37 = 16;

      for(int var38 = 0; var38 < 6; ++var38) {
         if(var38 == 0) {
            var60 = var26[0];
            var31 = var26[1];
            var32 = var26[2];
            var33 = var26[3];
            var18 = (double)var17.getInterpolatedU((double)var34);
            var20 = (double)var17.getInterpolatedV((double)var36);
            var22 = (double)var17.getInterpolatedU((double)var35);
            var24 = (double)var17.getInterpolatedV((double)(var36 + 2));
         } else if(var38 == 1) {
            var60 = var26[7];
            var31 = var26[6];
            var32 = var26[5];
            var33 = var26[4];
         } else if(var38 == 2) {
            var60 = var26[1];
            var31 = var26[0];
            var32 = var26[4];
            var33 = var26[5];
            var18 = (double)var17.getInterpolatedU((double)var34);
            var20 = (double)var17.getInterpolatedV((double)var36);
            var22 = (double)var17.getInterpolatedU((double)var35);
            var24 = (double)var17.getInterpolatedV((double)var37);
         } else if(var38 == 3) {
            var60 = var26[2];
            var31 = var26[1];
            var32 = var26[5];
            var33 = var26[6];
         } else if(var38 == 4) {
            var60 = var26[3];
            var31 = var26[2];
            var32 = var26[6];
            var33 = var26[7];
         } else if(var38 == 5) {
            var60 = var26[0];
            var31 = var26[3];
            var32 = var26[7];
            var33 = var26[4];
         }

         var5.addVertexWithUV(var60.xCoord, var60.yCoord, var60.zCoord, var18, var24);
         var5.addVertexWithUV(var31.xCoord, var31.yCoord, var31.zCoord, var22, var24);
         var5.addVertexWithUV(var32.xCoord, var32.yCoord, var32.zCoord, var22, var20);
         var5.addVertexWithUV(var33.xCoord, var33.yCoord, var33.zCoord, var18, var20);
      }

      float var61 = 0.09375F;
      float var39 = 0.09375F;
      float var40 = 0.03125F;
      var26[0] = Vec3.createVectorHelper((double)(-var61), 0.0D, (double)(-var39));
      var26[1] = Vec3.createVectorHelper((double)var61, 0.0D, (double)(-var39));
      var26[2] = Vec3.createVectorHelper((double)var61, 0.0D, (double)var39);
      var26[3] = Vec3.createVectorHelper((double)(-var61), 0.0D, (double)var39);
      var26[4] = Vec3.createVectorHelper((double)(-var61), (double)var40, (double)(-var39));
      var26[5] = Vec3.createVectorHelper((double)var61, (double)var40, (double)(-var39));
      var26[6] = Vec3.createVectorHelper((double)var61, (double)var40, (double)var39);
      var26[7] = Vec3.createVectorHelper((double)(-var61), (double)var40, (double)var39);

      for(int var41 = 0; var41 < 8; ++var41) {
         var26[var41].zCoord += 0.21875D;
         if(var9) {
            var26[var41].yCoord -= 0.09375D;
            var26[var41].zCoord -= 0.1625D;
            var26[var41].rotateAroundX(0.0F);
         } else if(var8) {
            var26[var41].yCoord += 0.015625D;
            var26[var41].zCoord -= 0.171875D;
            var26[var41].rotateAroundX(0.17453294F);
         } else {
            var26[var41].rotateAroundX(0.87266463F);
         }

         if(var7 == 2) {
            var26[var41].rotateAroundY(0.0F);
         }

         if(var7 == 0) {
            var26[var41].rotateAroundY(3.1415927F);
         }

         if(var7 == 1) {
            var26[var41].rotateAroundY(1.5707964F);
         }

         if(var7 == 3) {
            var26[var41].rotateAroundY(-1.5707964F);
         }

         var26[var41].xCoord += (double)var2 + 0.5D;
         var26[var41].yCoord += (double)((float)var3 + 0.3125F);
         var26[var41].zCoord += (double)var4 + 0.5D;
      }

      byte var62 = 5;
      byte var42 = 11;
      byte var43 = 3;
      byte var44 = 9;

      for(int var45 = 0; var45 < 6; ++var45) {
         if(var45 == 0) {
            var60 = var26[0];
            var31 = var26[1];
            var32 = var26[2];
            var33 = var26[3];
            var18 = (double)var17.getInterpolatedU((double)var62);
            var20 = (double)var17.getInterpolatedV((double)var43);
            var22 = (double)var17.getInterpolatedU((double)var42);
            var24 = (double)var17.getInterpolatedV((double)var44);
         } else if(var45 == 1) {
            var60 = var26[7];
            var31 = var26[6];
            var32 = var26[5];
            var33 = var26[4];
         } else if(var45 == 2) {
            var60 = var26[1];
            var31 = var26[0];
            var32 = var26[4];
            var33 = var26[5];
            var18 = (double)var17.getInterpolatedU((double)var62);
            var20 = (double)var17.getInterpolatedV((double)var43);
            var22 = (double)var17.getInterpolatedU((double)var42);
            var24 = (double)var17.getInterpolatedV((double)(var43 + 2));
         } else if(var45 == 3) {
            var60 = var26[2];
            var31 = var26[1];
            var32 = var26[5];
            var33 = var26[6];
         } else if(var45 == 4) {
            var60 = var26[3];
            var31 = var26[2];
            var32 = var26[6];
            var33 = var26[7];
         } else if(var45 == 5) {
            var60 = var26[0];
            var31 = var26[3];
            var32 = var26[7];
            var33 = var26[4];
         }

         var5.addVertexWithUV(var60.xCoord, var60.yCoord, var60.zCoord, var18, var24);
         var5.addVertexWithUV(var31.xCoord, var31.yCoord, var31.zCoord, var22, var24);
         var5.addVertexWithUV(var32.xCoord, var32.yCoord, var32.zCoord, var22, var20);
         var5.addVertexWithUV(var33.xCoord, var33.yCoord, var33.zCoord, var18, var20);
      }

      if(var8) {
         double var63 = var26[0].yCoord;
         float var47 = 0.03125F;
         float var48 = 0.5F - var47 / 2.0F;
         float var49 = var48 + var47;
         double var50 = (double)var17.getMinU();
         double var52 = (double)var17.getInterpolatedV(var8?2.0D:0.0D);
         double var54 = (double)var17.getMaxU();
         double var56 = (double)var17.getInterpolatedV(var8?4.0D:2.0D);
         double var58 = (double)(var10?3.5F:1.5F) / 16.0D;
         var5.setColorOpaque_F(0.75F, 0.75F, 0.75F);
         if(var7 == 2) {
            var5.addVertexWithUV((double)((float)var2 + var48), (double)var3 + var58, (double)var4 + 0.25D, var50, var52);
            var5.addVertexWithUV((double)((float)var2 + var49), (double)var3 + var58, (double)var4 + 0.25D, var50, var56);
            var5.addVertexWithUV((double)((float)var2 + var49), (double)var3 + var58, (double)var4, var54, var56);
            var5.addVertexWithUV((double)((float)var2 + var48), (double)var3 + var58, (double)var4, var54, var52);
            var5.addVertexWithUV((double)((float)var2 + var48), var63, (double)var4 + 0.5D, var50, var52);
            var5.addVertexWithUV((double)((float)var2 + var49), var63, (double)var4 + 0.5D, var50, var56);
            var5.addVertexWithUV((double)((float)var2 + var49), (double)var3 + var58, (double)var4 + 0.25D, var54, var56);
            var5.addVertexWithUV((double)((float)var2 + var48), (double)var3 + var58, (double)var4 + 0.25D, var54, var52);
         } else if(var7 == 0) {
            var5.addVertexWithUV((double)((float)var2 + var48), (double)var3 + var58, (double)var4 + 0.75D, var50, var52);
            var5.addVertexWithUV((double)((float)var2 + var49), (double)var3 + var58, (double)var4 + 0.75D, var50, var56);
            var5.addVertexWithUV((double)((float)var2 + var49), var63, (double)var4 + 0.5D, var54, var56);
            var5.addVertexWithUV((double)((float)var2 + var48), var63, (double)var4 + 0.5D, var54, var52);
            var5.addVertexWithUV((double)((float)var2 + var48), (double)var3 + var58, (double)(var4 + 1), var50, var52);
            var5.addVertexWithUV((double)((float)var2 + var49), (double)var3 + var58, (double)(var4 + 1), var50, var56);
            var5.addVertexWithUV((double)((float)var2 + var49), (double)var3 + var58, (double)var4 + 0.75D, var54, var56);
            var5.addVertexWithUV((double)((float)var2 + var48), (double)var3 + var58, (double)var4 + 0.75D, var54, var52);
         } else if(var7 == 1) {
            var5.addVertexWithUV((double)var2, (double)var3 + var58, (double)((float)var4 + var49), var50, var56);
            var5.addVertexWithUV((double)var2 + 0.25D, (double)var3 + var58, (double)((float)var4 + var49), var54, var56);
            var5.addVertexWithUV((double)var2 + 0.25D, (double)var3 + var58, (double)((float)var4 + var48), var54, var52);
            var5.addVertexWithUV((double)var2, (double)var3 + var58, (double)((float)var4 + var48), var50, var52);
            var5.addVertexWithUV((double)var2 + 0.25D, (double)var3 + var58, (double)((float)var4 + var49), var50, var56);
            var5.addVertexWithUV((double)var2 + 0.5D, var63, (double)((float)var4 + var49), var54, var56);
            var5.addVertexWithUV((double)var2 + 0.5D, var63, (double)((float)var4 + var48), var54, var52);
            var5.addVertexWithUV((double)var2 + 0.25D, (double)var3 + var58, (double)((float)var4 + var48), var50, var52);
         } else {
            var5.addVertexWithUV((double)var2 + 0.5D, var63, (double)((float)var4 + var49), var50, var56);
            var5.addVertexWithUV((double)var2 + 0.75D, (double)var3 + var58, (double)((float)var4 + var49), var54, var56);
            var5.addVertexWithUV((double)var2 + 0.75D, (double)var3 + var58, (double)((float)var4 + var48), var54, var52);
            var5.addVertexWithUV((double)var2 + 0.5D, var63, (double)((float)var4 + var48), var50, var52);
            var5.addVertexWithUV((double)var2 + 0.75D, (double)var3 + var58, (double)((float)var4 + var49), var50, var56);
            var5.addVertexWithUV((double)(var2 + 1), (double)var3 + var58, (double)((float)var4 + var49), var54, var56);
            var5.addVertexWithUV((double)(var2 + 1), (double)var3 + var58, (double)((float)var4 + var48), var54, var52);
            var5.addVertexWithUV((double)var2 + 0.75D, (double)var3 + var58, (double)((float)var4 + var48), var50, var52);
         }
      }

      return true;
   }

   public boolean renderBlockTripWire(Block var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      IIcon var6 = this.getBlockIconFromSide(var1, 0);
      int var7 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      boolean var8 = (var7 & 4) == 4;
      boolean var9 = (var7 & 2) == 2;
      if(this.hasOverrideBlockTexture()) {
         var6 = this.overrideBlockTexture;
      }

      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      double var10 = (double)var6.getMinU();
      double var12 = (double)var6.getInterpolatedV(var8?2.0D:0.0D);
      double var14 = (double)var6.getMaxU();
      double var16 = (double)var6.getInterpolatedV(var8?4.0D:2.0D);
      double var18 = (double)(var9?3.5F:1.5F) / 16.0D;
      boolean var20 = BlockTripWire.func_150139_a(this.blockAccess, var2, var3, var4, var7, 1);
      boolean var21 = BlockTripWire.func_150139_a(this.blockAccess, var2, var3, var4, var7, 3);
      boolean var22 = BlockTripWire.func_150139_a(this.blockAccess, var2, var3, var4, var7, 2);
      boolean var23 = BlockTripWire.func_150139_a(this.blockAccess, var2, var3, var4, var7, 0);
      float var24 = 0.03125F;
      float var25 = 0.5F - var24 / 2.0F;
      float var26 = var25 + var24;
      if(!var22 && !var21 && !var23 && !var20) {
         var22 = true;
         var23 = true;
      }

      if(var22) {
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4 + 0.25D, var10, var12);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4 + 0.25D, var10, var16);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4, var14, var16);
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4, var14, var12);
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4, var14, var12);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4, var14, var16);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4 + 0.25D, var10, var16);
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4 + 0.25D, var10, var12);
      }

      if(var22 || var23 && !var21 && !var20) {
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4 + 0.5D, var10, var12);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4 + 0.5D, var10, var16);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4 + 0.25D, var14, var16);
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4 + 0.25D, var14, var12);
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4 + 0.25D, var14, var12);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4 + 0.25D, var14, var16);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4 + 0.5D, var10, var16);
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4 + 0.5D, var10, var12);
      }

      if(var23 || var22 && !var21 && !var20) {
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4 + 0.75D, var10, var12);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4 + 0.75D, var10, var16);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4 + 0.5D, var14, var16);
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4 + 0.5D, var14, var12);
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4 + 0.5D, var14, var12);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4 + 0.5D, var14, var16);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4 + 0.75D, var10, var16);
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4 + 0.75D, var10, var12);
      }

      if(var23) {
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)(var4 + 1), var10, var12);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)(var4 + 1), var10, var16);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4 + 0.75D, var14, var16);
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4 + 0.75D, var14, var12);
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)var4 + 0.75D, var14, var12);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)var4 + 0.75D, var14, var16);
         var5.addVertexWithUV((double)((float)var2 + var26), (double)var3 + var18, (double)(var4 + 1), var10, var16);
         var5.addVertexWithUV((double)((float)var2 + var25), (double)var3 + var18, (double)(var4 + 1), var10, var12);
      }

      if(var20) {
         var5.addVertexWithUV((double)var2, (double)var3 + var18, (double)((float)var4 + var26), var10, var16);
         var5.addVertexWithUV((double)var2 + 0.25D, (double)var3 + var18, (double)((float)var4 + var26), var14, var16);
         var5.addVertexWithUV((double)var2 + 0.25D, (double)var3 + var18, (double)((float)var4 + var25), var14, var12);
         var5.addVertexWithUV((double)var2, (double)var3 + var18, (double)((float)var4 + var25), var10, var12);
         var5.addVertexWithUV((double)var2, (double)var3 + var18, (double)((float)var4 + var25), var10, var12);
         var5.addVertexWithUV((double)var2 + 0.25D, (double)var3 + var18, (double)((float)var4 + var25), var14, var12);
         var5.addVertexWithUV((double)var2 + 0.25D, (double)var3 + var18, (double)((float)var4 + var26), var14, var16);
         var5.addVertexWithUV((double)var2, (double)var3 + var18, (double)((float)var4 + var26), var10, var16);
      }

      if(var20 || var21 && !var22 && !var23) {
         var5.addVertexWithUV((double)var2 + 0.25D, (double)var3 + var18, (double)((float)var4 + var26), var10, var16);
         var5.addVertexWithUV((double)var2 + 0.5D, (double)var3 + var18, (double)((float)var4 + var26), var14, var16);
         var5.addVertexWithUV((double)var2 + 0.5D, (double)var3 + var18, (double)((float)var4 + var25), var14, var12);
         var5.addVertexWithUV((double)var2 + 0.25D, (double)var3 + var18, (double)((float)var4 + var25), var10, var12);
         var5.addVertexWithUV((double)var2 + 0.25D, (double)var3 + var18, (double)((float)var4 + var25), var10, var12);
         var5.addVertexWithUV((double)var2 + 0.5D, (double)var3 + var18, (double)((float)var4 + var25), var14, var12);
         var5.addVertexWithUV((double)var2 + 0.5D, (double)var3 + var18, (double)((float)var4 + var26), var14, var16);
         var5.addVertexWithUV((double)var2 + 0.25D, (double)var3 + var18, (double)((float)var4 + var26), var10, var16);
      }

      if(var21 || var20 && !var22 && !var23) {
         var5.addVertexWithUV((double)var2 + 0.5D, (double)var3 + var18, (double)((float)var4 + var26), var10, var16);
         var5.addVertexWithUV((double)var2 + 0.75D, (double)var3 + var18, (double)((float)var4 + var26), var14, var16);
         var5.addVertexWithUV((double)var2 + 0.75D, (double)var3 + var18, (double)((float)var4 + var25), var14, var12);
         var5.addVertexWithUV((double)var2 + 0.5D, (double)var3 + var18, (double)((float)var4 + var25), var10, var12);
         var5.addVertexWithUV((double)var2 + 0.5D, (double)var3 + var18, (double)((float)var4 + var25), var10, var12);
         var5.addVertexWithUV((double)var2 + 0.75D, (double)var3 + var18, (double)((float)var4 + var25), var14, var12);
         var5.addVertexWithUV((double)var2 + 0.75D, (double)var3 + var18, (double)((float)var4 + var26), var14, var16);
         var5.addVertexWithUV((double)var2 + 0.5D, (double)var3 + var18, (double)((float)var4 + var26), var10, var16);
      }

      if(var21) {
         var5.addVertexWithUV((double)var2 + 0.75D, (double)var3 + var18, (double)((float)var4 + var26), var10, var16);
         var5.addVertexWithUV((double)(var2 + 1), (double)var3 + var18, (double)((float)var4 + var26), var14, var16);
         var5.addVertexWithUV((double)(var2 + 1), (double)var3 + var18, (double)((float)var4 + var25), var14, var12);
         var5.addVertexWithUV((double)var2 + 0.75D, (double)var3 + var18, (double)((float)var4 + var25), var10, var12);
         var5.addVertexWithUV((double)var2 + 0.75D, (double)var3 + var18, (double)((float)var4 + var25), var10, var12);
         var5.addVertexWithUV((double)(var2 + 1), (double)var3 + var18, (double)((float)var4 + var25), var14, var12);
         var5.addVertexWithUV((double)(var2 + 1), (double)var3 + var18, (double)((float)var4 + var26), var14, var16);
         var5.addVertexWithUV((double)var2 + 0.75D, (double)var3 + var18, (double)((float)var4 + var26), var10, var16);
      }

      return true;
   }

   public boolean renderBlockFire(BlockFire var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      IIcon var6 = var1.getFireIcon(0);
      IIcon var7 = var1.getFireIcon(1);
      IIcon var8 = var6;
      if(this.hasOverrideBlockTexture()) {
         var8 = this.overrideBlockTexture;
      }

      var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      double var9 = (double)var8.getMinU();
      double var11 = (double)var8.getMinV();
      double var13 = (double)var8.getMaxU();
      double var15 = (double)var8.getMaxV();
      float var17 = 1.4F;
      double var20;
      double var22;
      double var24;
      double var26;
      double var28;
      double var30;
      double var32;
      if(!World.doesBlockHaveSolidTopSurface(this.blockAccess, var2, var3 - 1, var4) && !Blocks.fire.canBlockCatchFire(this.blockAccess, var2, var3 - 1, var4)) {
         float var36 = 0.2F;
         float var19 = 0.0625F;
         if((var2 + var3 + var4 & 1) == 1) {
            var9 = (double)var7.getMinU();
            var11 = (double)var7.getMinV();
            var13 = (double)var7.getMaxU();
            var15 = (double)var7.getMaxV();
         }

         if((var2 / 2 + var3 / 2 + var4 / 2 & 1) == 1) {
            var20 = var13;
            var13 = var9;
            var9 = var20;
         }

         if(Blocks.fire.canBlockCatchFire(this.blockAccess, var2 - 1, var3, var4)) {
            var5.addVertexWithUV((double)((float)var2 + var36), (double)((float)var3 + var17 + var19), (double)(var4 + 1), var13, var11);
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)(var3 + 0) + var19), (double)(var4 + 1), var13, var15);
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)(var3 + 0) + var19), (double)(var4 + 0), var9, var15);
            var5.addVertexWithUV((double)((float)var2 + var36), (double)((float)var3 + var17 + var19), (double)(var4 + 0), var9, var11);
            var5.addVertexWithUV((double)((float)var2 + var36), (double)((float)var3 + var17 + var19), (double)(var4 + 0), var9, var11);
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)(var3 + 0) + var19), (double)(var4 + 0), var9, var15);
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)(var3 + 0) + var19), (double)(var4 + 1), var13, var15);
            var5.addVertexWithUV((double)((float)var2 + var36), (double)((float)var3 + var17 + var19), (double)(var4 + 1), var13, var11);
         }

         if(Blocks.fire.canBlockCatchFire(this.blockAccess, var2 + 1, var3, var4)) {
            var5.addVertexWithUV((double)((float)(var2 + 1) - var36), (double)((float)var3 + var17 + var19), (double)(var4 + 0), var9, var11);
            var5.addVertexWithUV((double)(var2 + 1 - 0), (double)((float)(var3 + 0) + var19), (double)(var4 + 0), var9, var15);
            var5.addVertexWithUV((double)(var2 + 1 - 0), (double)((float)(var3 + 0) + var19), (double)(var4 + 1), var13, var15);
            var5.addVertexWithUV((double)((float)(var2 + 1) - var36), (double)((float)var3 + var17 + var19), (double)(var4 + 1), var13, var11);
            var5.addVertexWithUV((double)((float)(var2 + 1) - var36), (double)((float)var3 + var17 + var19), (double)(var4 + 1), var13, var11);
            var5.addVertexWithUV((double)(var2 + 1 - 0), (double)((float)(var3 + 0) + var19), (double)(var4 + 1), var13, var15);
            var5.addVertexWithUV((double)(var2 + 1 - 0), (double)((float)(var3 + 0) + var19), (double)(var4 + 0), var9, var15);
            var5.addVertexWithUV((double)((float)(var2 + 1) - var36), (double)((float)var3 + var17 + var19), (double)(var4 + 0), var9, var11);
         }

         if(Blocks.fire.canBlockCatchFire(this.blockAccess, var2, var3, var4 - 1)) {
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var17 + var19), (double)((float)var4 + var36), var13, var11);
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)(var3 + 0) + var19), (double)(var4 + 0), var13, var15);
            var5.addVertexWithUV((double)(var2 + 1), (double)((float)(var3 + 0) + var19), (double)(var4 + 0), var9, var15);
            var5.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17 + var19), (double)((float)var4 + var36), var9, var11);
            var5.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17 + var19), (double)((float)var4 + var36), var9, var11);
            var5.addVertexWithUV((double)(var2 + 1), (double)((float)(var3 + 0) + var19), (double)(var4 + 0), var9, var15);
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)(var3 + 0) + var19), (double)(var4 + 0), var13, var15);
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var17 + var19), (double)((float)var4 + var36), var13, var11);
         }

         if(Blocks.fire.canBlockCatchFire(this.blockAccess, var2, var3, var4 + 1)) {
            var5.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17 + var19), (double)((float)(var4 + 1) - var36), var9, var11);
            var5.addVertexWithUV((double)(var2 + 1), (double)((float)(var3 + 0) + var19), (double)(var4 + 1 - 0), var9, var15);
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)(var3 + 0) + var19), (double)(var4 + 1 - 0), var13, var15);
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var17 + var19), (double)((float)(var4 + 1) - var36), var13, var11);
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var17 + var19), (double)((float)(var4 + 1) - var36), var13, var11);
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)(var3 + 0) + var19), (double)(var4 + 1 - 0), var13, var15);
            var5.addVertexWithUV((double)(var2 + 1), (double)((float)(var3 + 0) + var19), (double)(var4 + 1 - 0), var9, var15);
            var5.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17 + var19), (double)((float)(var4 + 1) - var36), var9, var11);
         }

         if(Blocks.fire.canBlockCatchFire(this.blockAccess, var2, var3 + 1, var4)) {
            var20 = (double)var2 + 0.5D + 0.5D;
            var22 = (double)var2 + 0.5D - 0.5D;
            var24 = (double)var4 + 0.5D + 0.5D;
            var26 = (double)var4 + 0.5D - 0.5D;
            var28 = (double)var2 + 0.5D - 0.5D;
            var30 = (double)var2 + 0.5D + 0.5D;
            var32 = (double)var4 + 0.5D - 0.5D;
            double var34 = (double)var4 + 0.5D + 0.5D;
            var9 = (double)var6.getMinU();
            var11 = (double)var6.getMinV();
            var13 = (double)var6.getMaxU();
            var15 = (double)var6.getMaxV();
            ++var3;
            var17 = -0.2F;
            if((var2 + var3 + var4 & 1) == 0) {
               var5.addVertexWithUV(var28, (double)((float)var3 + var17), (double)(var4 + 0), var13, var11);
               var5.addVertexWithUV(var20, (double)(var3 + 0), (double)(var4 + 0), var13, var15);
               var5.addVertexWithUV(var20, (double)(var3 + 0), (double)(var4 + 1), var9, var15);
               var5.addVertexWithUV(var28, (double)((float)var3 + var17), (double)(var4 + 1), var9, var11);
               var9 = (double)var7.getMinU();
               var11 = (double)var7.getMinV();
               var13 = (double)var7.getMaxU();
               var15 = (double)var7.getMaxV();
               var5.addVertexWithUV(var30, (double)((float)var3 + var17), (double)(var4 + 1), var13, var11);
               var5.addVertexWithUV(var22, (double)(var3 + 0), (double)(var4 + 1), var13, var15);
               var5.addVertexWithUV(var22, (double)(var3 + 0), (double)(var4 + 0), var9, var15);
               var5.addVertexWithUV(var30, (double)((float)var3 + var17), (double)(var4 + 0), var9, var11);
            } else {
               var5.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var17), var34, var13, var11);
               var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), var26, var13, var15);
               var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), var26, var9, var15);
               var5.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17), var34, var9, var11);
               var9 = (double)var7.getMinU();
               var11 = (double)var7.getMinV();
               var13 = (double)var7.getMaxU();
               var15 = (double)var7.getMaxV();
               var5.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17), var32, var13, var11);
               var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), var24, var13, var15);
               var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), var24, var9, var15);
               var5.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var17), var32, var9, var11);
            }
         }
      } else {
         double var18 = (double)var2 + 0.5D + 0.2D;
         var20 = (double)var2 + 0.5D - 0.2D;
         var22 = (double)var4 + 0.5D + 0.2D;
         var24 = (double)var4 + 0.5D - 0.2D;
         var26 = (double)var2 + 0.5D - 0.3D;
         var28 = (double)var2 + 0.5D + 0.3D;
         var30 = (double)var4 + 0.5D - 0.3D;
         var32 = (double)var4 + 0.5D + 0.3D;
         var5.addVertexWithUV(var26, (double)((float)var3 + var17), (double)(var4 + 1), var13, var11);
         var5.addVertexWithUV(var18, (double)(var3 + 0), (double)(var4 + 1), var13, var15);
         var5.addVertexWithUV(var18, (double)(var3 + 0), (double)(var4 + 0), var9, var15);
         var5.addVertexWithUV(var26, (double)((float)var3 + var17), (double)(var4 + 0), var9, var11);
         var5.addVertexWithUV(var28, (double)((float)var3 + var17), (double)(var4 + 0), var13, var11);
         var5.addVertexWithUV(var20, (double)(var3 + 0), (double)(var4 + 0), var13, var15);
         var5.addVertexWithUV(var20, (double)(var3 + 0), (double)(var4 + 1), var9, var15);
         var5.addVertexWithUV(var28, (double)((float)var3 + var17), (double)(var4 + 1), var9, var11);
         var9 = (double)var7.getMinU();
         var11 = (double)var7.getMinV();
         var13 = (double)var7.getMaxU();
         var15 = (double)var7.getMaxV();
         var5.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17), var32, var13, var11);
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), var24, var13, var15);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), var24, var9, var15);
         var5.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var17), var32, var9, var11);
         var5.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var17), var30, var13, var11);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), var22, var13, var15);
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), var22, var9, var15);
         var5.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17), var30, var9, var11);
         var18 = (double)var2 + 0.5D - 0.5D;
         var20 = (double)var2 + 0.5D + 0.5D;
         var22 = (double)var4 + 0.5D - 0.5D;
         var24 = (double)var4 + 0.5D + 0.5D;
         var26 = (double)var2 + 0.5D - 0.4D;
         var28 = (double)var2 + 0.5D + 0.4D;
         var30 = (double)var4 + 0.5D - 0.4D;
         var32 = (double)var4 + 0.5D + 0.4D;
         var5.addVertexWithUV(var26, (double)((float)var3 + var17), (double)(var4 + 0), var9, var11);
         var5.addVertexWithUV(var18, (double)(var3 + 0), (double)(var4 + 0), var9, var15);
         var5.addVertexWithUV(var18, (double)(var3 + 0), (double)(var4 + 1), var13, var15);
         var5.addVertexWithUV(var26, (double)((float)var3 + var17), (double)(var4 + 1), var13, var11);
         var5.addVertexWithUV(var28, (double)((float)var3 + var17), (double)(var4 + 1), var9, var11);
         var5.addVertexWithUV(var20, (double)(var3 + 0), (double)(var4 + 1), var9, var15);
         var5.addVertexWithUV(var20, (double)(var3 + 0), (double)(var4 + 0), var13, var15);
         var5.addVertexWithUV(var28, (double)((float)var3 + var17), (double)(var4 + 0), var13, var11);
         var9 = (double)var6.getMinU();
         var11 = (double)var6.getMinV();
         var13 = (double)var6.getMaxU();
         var15 = (double)var6.getMaxV();
         var5.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var17), var32, var9, var11);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), var24, var9, var15);
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), var24, var13, var15);
         var5.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17), var32, var13, var11);
         var5.addVertexWithUV((double)(var2 + 1), (double)((float)var3 + var17), var30, var9, var11);
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), var22, var9, var15);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), var22, var13, var15);
         var5.addVertexWithUV((double)(var2 + 0), (double)((float)var3 + var17), var30, var13, var11);
      }

      return true;
   }

   public boolean renderBlockRedstoneWire(Block var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      int var6 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      IIcon var7 = BlockRedstoneWire.getRedstoneWireIcon("cross");
      IIcon var8 = BlockRedstoneWire.getRedstoneWireIcon("line");
      IIcon var9 = BlockRedstoneWire.getRedstoneWireIcon("cross_overlay");
      IIcon var10 = BlockRedstoneWire.getRedstoneWireIcon("line_overlay");
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      float var11 = (float)var6 / 15.0F;
      float var12 = var11 * 0.6F + 0.4F;
      if(var6 == 0) {
         var12 = 0.3F;
      }

      float var13 = var11 * var11 * 0.7F - 0.5F;
      float var14 = var11 * var11 * 0.6F - 0.7F;
      if(var13 < 0.0F) {
         var13 = 0.0F;
      }

      if(var14 < 0.0F) {
         var14 = 0.0F;
      }

      var5.setColorOpaque_F(var12, var13, var14);
      double var15 = 0.015625D;
      double var17 = 0.015625D;
      boolean var19 = BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, var2 - 1, var3, var4, 1) || !this.blockAccess.getBlock(var2 - 1, var3, var4).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, var2 - 1, var3 - 1, var4, -1);
      boolean var20 = BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, var2 + 1, var3, var4, 3) || !this.blockAccess.getBlock(var2 + 1, var3, var4).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, var2 + 1, var3 - 1, var4, -1);
      boolean var21 = BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, var2, var3, var4 - 1, 2) || !this.blockAccess.getBlock(var2, var3, var4 - 1).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, var2, var3 - 1, var4 - 1, -1);
      boolean var22 = BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, var2, var3, var4 + 1, 0) || !this.blockAccess.getBlock(var2, var3, var4 + 1).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, var2, var3 - 1, var4 + 1, -1);
      if(!this.blockAccess.getBlock(var2, var3 + 1, var4).isBlockNormalCube()) {
         if(this.blockAccess.getBlock(var2 - 1, var3, var4).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, var2 - 1, var3 + 1, var4, -1)) {
            var19 = true;
         }

         if(this.blockAccess.getBlock(var2 + 1, var3, var4).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, var2 + 1, var3 + 1, var4, -1)) {
            var20 = true;
         }

         if(this.blockAccess.getBlock(var2, var3, var4 - 1).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, var2, var3 + 1, var4 - 1, -1)) {
            var21 = true;
         }

         if(this.blockAccess.getBlock(var2, var3, var4 + 1).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(this.blockAccess, var2, var3 + 1, var4 + 1, -1)) {
            var22 = true;
         }
      }

      float var23 = (float)(var2 + 0);
      float var24 = (float)(var2 + 1);
      float var25 = (float)(var4 + 0);
      float var26 = (float)(var4 + 1);
      boolean var27 = false;
      if((var19 || var20) && !var21 && !var22) {
         var27 = true;
      }

      if((var21 || var22) && !var20 && !var19) {
         var27 = true;
      }

      if(!var27) {
         int var28 = 0;
         int var29 = 0;
         int var30 = 16;
         int var31 = 16;
         boolean var32 = true;
         if(!var19) {
            var23 += 0.3125F;
         }

         if(!var19) {
            var28 += 5;
         }

         if(!var20) {
            var24 -= 0.3125F;
         }

         if(!var20) {
            var30 -= 5;
         }

         if(!var21) {
            var25 += 0.3125F;
         }

         if(!var21) {
            var29 += 5;
         }

         if(!var22) {
            var26 -= 0.3125F;
         }

         if(!var22) {
            var31 -= 5;
         }

         var5.addVertexWithUV((double)var24, (double)var3 + 0.015625D, (double)var26, (double)var7.getInterpolatedU((double)var30), (double)var7.getInterpolatedV((double)var31));
         var5.addVertexWithUV((double)var24, (double)var3 + 0.015625D, (double)var25, (double)var7.getInterpolatedU((double)var30), (double)var7.getInterpolatedV((double)var29));
         var5.addVertexWithUV((double)var23, (double)var3 + 0.015625D, (double)var25, (double)var7.getInterpolatedU((double)var28), (double)var7.getInterpolatedV((double)var29));
         var5.addVertexWithUV((double)var23, (double)var3 + 0.015625D, (double)var26, (double)var7.getInterpolatedU((double)var28), (double)var7.getInterpolatedV((double)var31));
         var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
         var5.addVertexWithUV((double)var24, (double)var3 + 0.015625D, (double)var26, (double)var9.getInterpolatedU((double)var30), (double)var9.getInterpolatedV((double)var31));
         var5.addVertexWithUV((double)var24, (double)var3 + 0.015625D, (double)var25, (double)var9.getInterpolatedU((double)var30), (double)var9.getInterpolatedV((double)var29));
         var5.addVertexWithUV((double)var23, (double)var3 + 0.015625D, (double)var25, (double)var9.getInterpolatedU((double)var28), (double)var9.getInterpolatedV((double)var29));
         var5.addVertexWithUV((double)var23, (double)var3 + 0.015625D, (double)var26, (double)var9.getInterpolatedU((double)var28), (double)var9.getInterpolatedV((double)var31));
      } else if(var27) {
         var5.addVertexWithUV((double)var24, (double)var3 + 0.015625D, (double)var26, (double)var8.getMaxU(), (double)var8.getMaxV());
         var5.addVertexWithUV((double)var24, (double)var3 + 0.015625D, (double)var25, (double)var8.getMaxU(), (double)var8.getMinV());
         var5.addVertexWithUV((double)var23, (double)var3 + 0.015625D, (double)var25, (double)var8.getMinU(), (double)var8.getMinV());
         var5.addVertexWithUV((double)var23, (double)var3 + 0.015625D, (double)var26, (double)var8.getMinU(), (double)var8.getMaxV());
         var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
         var5.addVertexWithUV((double)var24, (double)var3 + 0.015625D, (double)var26, (double)var10.getMaxU(), (double)var10.getMaxV());
         var5.addVertexWithUV((double)var24, (double)var3 + 0.015625D, (double)var25, (double)var10.getMaxU(), (double)var10.getMinV());
         var5.addVertexWithUV((double)var23, (double)var3 + 0.015625D, (double)var25, (double)var10.getMinU(), (double)var10.getMinV());
         var5.addVertexWithUV((double)var23, (double)var3 + 0.015625D, (double)var26, (double)var10.getMinU(), (double)var10.getMaxV());
      } else {
         var5.addVertexWithUV((double)var24, (double)var3 + 0.015625D, (double)var26, (double)var8.getMaxU(), (double)var8.getMaxV());
         var5.addVertexWithUV((double)var24, (double)var3 + 0.015625D, (double)var25, (double)var8.getMinU(), (double)var8.getMaxV());
         var5.addVertexWithUV((double)var23, (double)var3 + 0.015625D, (double)var25, (double)var8.getMinU(), (double)var8.getMinV());
         var5.addVertexWithUV((double)var23, (double)var3 + 0.015625D, (double)var26, (double)var8.getMaxU(), (double)var8.getMinV());
         var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
         var5.addVertexWithUV((double)var24, (double)var3 + 0.015625D, (double)var26, (double)var10.getMaxU(), (double)var10.getMaxV());
         var5.addVertexWithUV((double)var24, (double)var3 + 0.015625D, (double)var25, (double)var10.getMinU(), (double)var10.getMaxV());
         var5.addVertexWithUV((double)var23, (double)var3 + 0.015625D, (double)var25, (double)var10.getMinU(), (double)var10.getMinV());
         var5.addVertexWithUV((double)var23, (double)var3 + 0.015625D, (double)var26, (double)var10.getMaxU(), (double)var10.getMinV());
      }

      if(!this.blockAccess.getBlock(var2, var3 + 1, var4).isBlockNormalCube()) {
         float var33 = 0.021875F;
         if(this.blockAccess.getBlock(var2 - 1, var3, var4).isBlockNormalCube() && this.blockAccess.getBlock(var2 - 1, var3 + 1, var4) == Blocks.redstone_wire) {
            var5.setColorOpaque_F(var12, var13, var14);
            var5.addVertexWithUV((double)var2 + 0.015625D, (double)((float)(var3 + 1) + 0.021875F), (double)(var4 + 1), (double)var8.getMaxU(), (double)var8.getMinV());
            var5.addVertexWithUV((double)var2 + 0.015625D, (double)(var3 + 0), (double)(var4 + 1), (double)var8.getMinU(), (double)var8.getMinV());
            var5.addVertexWithUV((double)var2 + 0.015625D, (double)(var3 + 0), (double)(var4 + 0), (double)var8.getMinU(), (double)var8.getMaxV());
            var5.addVertexWithUV((double)var2 + 0.015625D, (double)((float)(var3 + 1) + 0.021875F), (double)(var4 + 0), (double)var8.getMaxU(), (double)var8.getMaxV());
            var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            var5.addVertexWithUV((double)var2 + 0.015625D, (double)((float)(var3 + 1) + 0.021875F), (double)(var4 + 1), (double)var10.getMaxU(), (double)var10.getMinV());
            var5.addVertexWithUV((double)var2 + 0.015625D, (double)(var3 + 0), (double)(var4 + 1), (double)var10.getMinU(), (double)var10.getMinV());
            var5.addVertexWithUV((double)var2 + 0.015625D, (double)(var3 + 0), (double)(var4 + 0), (double)var10.getMinU(), (double)var10.getMaxV());
            var5.addVertexWithUV((double)var2 + 0.015625D, (double)((float)(var3 + 1) + 0.021875F), (double)(var4 + 0), (double)var10.getMaxU(), (double)var10.getMaxV());
         }

         if(this.blockAccess.getBlock(var2 + 1, var3, var4).isBlockNormalCube() && this.blockAccess.getBlock(var2 + 1, var3 + 1, var4) == Blocks.redstone_wire) {
            var5.setColorOpaque_F(var12, var13, var14);
            var5.addVertexWithUV((double)(var2 + 1) - 0.015625D, (double)(var3 + 0), (double)(var4 + 1), (double)var8.getMinU(), (double)var8.getMaxV());
            var5.addVertexWithUV((double)(var2 + 1) - 0.015625D, (double)((float)(var3 + 1) + 0.021875F), (double)(var4 + 1), (double)var8.getMaxU(), (double)var8.getMaxV());
            var5.addVertexWithUV((double)(var2 + 1) - 0.015625D, (double)((float)(var3 + 1) + 0.021875F), (double)(var4 + 0), (double)var8.getMaxU(), (double)var8.getMinV());
            var5.addVertexWithUV((double)(var2 + 1) - 0.015625D, (double)(var3 + 0), (double)(var4 + 0), (double)var8.getMinU(), (double)var8.getMinV());
            var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            var5.addVertexWithUV((double)(var2 + 1) - 0.015625D, (double)(var3 + 0), (double)(var4 + 1), (double)var10.getMinU(), (double)var10.getMaxV());
            var5.addVertexWithUV((double)(var2 + 1) - 0.015625D, (double)((float)(var3 + 1) + 0.021875F), (double)(var4 + 1), (double)var10.getMaxU(), (double)var10.getMaxV());
            var5.addVertexWithUV((double)(var2 + 1) - 0.015625D, (double)((float)(var3 + 1) + 0.021875F), (double)(var4 + 0), (double)var10.getMaxU(), (double)var10.getMinV());
            var5.addVertexWithUV((double)(var2 + 1) - 0.015625D, (double)(var3 + 0), (double)(var4 + 0), (double)var10.getMinU(), (double)var10.getMinV());
         }

         if(this.blockAccess.getBlock(var2, var3, var4 - 1).isBlockNormalCube() && this.blockAccess.getBlock(var2, var3 + 1, var4 - 1) == Blocks.redstone_wire) {
            var5.setColorOpaque_F(var12, var13, var14);
            var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)var4 + 0.015625D, (double)var8.getMinU(), (double)var8.getMaxV());
            var5.addVertexWithUV((double)(var2 + 1), (double)((float)(var3 + 1) + 0.021875F), (double)var4 + 0.015625D, (double)var8.getMaxU(), (double)var8.getMaxV());
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)(var3 + 1) + 0.021875F), (double)var4 + 0.015625D, (double)var8.getMaxU(), (double)var8.getMinV());
            var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)var4 + 0.015625D, (double)var8.getMinU(), (double)var8.getMinV());
            var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)var4 + 0.015625D, (double)var10.getMinU(), (double)var10.getMaxV());
            var5.addVertexWithUV((double)(var2 + 1), (double)((float)(var3 + 1) + 0.021875F), (double)var4 + 0.015625D, (double)var10.getMaxU(), (double)var10.getMaxV());
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)(var3 + 1) + 0.021875F), (double)var4 + 0.015625D, (double)var10.getMaxU(), (double)var10.getMinV());
            var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)var4 + 0.015625D, (double)var10.getMinU(), (double)var10.getMinV());
         }

         if(this.blockAccess.getBlock(var2, var3, var4 + 1).isBlockNormalCube() && this.blockAccess.getBlock(var2, var3 + 1, var4 + 1) == Blocks.redstone_wire) {
            var5.setColorOpaque_F(var12, var13, var14);
            var5.addVertexWithUV((double)(var2 + 1), (double)((float)(var3 + 1) + 0.021875F), (double)(var4 + 1) - 0.015625D, (double)var8.getMaxU(), (double)var8.getMinV());
            var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)(var4 + 1) - 0.015625D, (double)var8.getMinU(), (double)var8.getMinV());
            var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 1) - 0.015625D, (double)var8.getMinU(), (double)var8.getMaxV());
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)(var3 + 1) + 0.021875F), (double)(var4 + 1) - 0.015625D, (double)var8.getMaxU(), (double)var8.getMaxV());
            var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            var5.addVertexWithUV((double)(var2 + 1), (double)((float)(var3 + 1) + 0.021875F), (double)(var4 + 1) - 0.015625D, (double)var10.getMaxU(), (double)var10.getMinV());
            var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)(var4 + 1) - 0.015625D, (double)var10.getMinU(), (double)var10.getMinV());
            var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 1) - 0.015625D, (double)var10.getMinU(), (double)var10.getMaxV());
            var5.addVertexWithUV((double)(var2 + 0), (double)((float)(var3 + 1) + 0.021875F), (double)(var4 + 1) - 0.015625D, (double)var10.getMaxU(), (double)var10.getMaxV());
         }
      }

      return true;
   }

   public boolean renderBlockMinecartTrack(BlockRailBase var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      int var6 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      IIcon var7 = this.getBlockIconFromSideAndMetadata(var1, 0, var6);
      if(this.hasOverrideBlockTexture()) {
         var7 = this.overrideBlockTexture;
      }

      if(var1.isPowered()) {
         var6 &= 7;
      }

      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      double var8 = (double)var7.getMinU();
      double var10 = (double)var7.getMinV();
      double var12 = (double)var7.getMaxU();
      double var14 = (double)var7.getMaxV();
      double var16 = 0.0625D;
      double var18 = (double)(var2 + 1);
      double var20 = (double)(var2 + 1);
      double var22 = (double)(var2 + 0);
      double var24 = (double)(var2 + 0);
      double var26 = (double)(var4 + 0);
      double var28 = (double)(var4 + 1);
      double var30 = (double)(var4 + 1);
      double var32 = (double)(var4 + 0);
      double var34 = (double)var3 + var16;
      double var36 = (double)var3 + var16;
      double var38 = (double)var3 + var16;
      double var40 = (double)var3 + var16;
      if(var6 != 1 && var6 != 2 && var6 != 3 && var6 != 7) {
         if(var6 == 8) {
            var18 = var20 = (double)(var2 + 0);
            var22 = var24 = (double)(var2 + 1);
            var26 = var32 = (double)(var4 + 1);
            var28 = var30 = (double)(var4 + 0);
         } else if(var6 == 9) {
            var18 = var24 = (double)(var2 + 0);
            var20 = var22 = (double)(var2 + 1);
            var26 = var28 = (double)(var4 + 0);
            var30 = var32 = (double)(var4 + 1);
         }
      } else {
         var18 = var24 = (double)(var2 + 1);
         var20 = var22 = (double)(var2 + 0);
         var26 = var28 = (double)(var4 + 1);
         var30 = var32 = (double)(var4 + 0);
      }

      if(var6 != 2 && var6 != 4) {
         if(var6 == 3 || var6 == 5) {
            ++var36;
            ++var38;
         }
      } else {
         ++var34;
         ++var40;
      }

      var5.addVertexWithUV(var18, var34, var26, var12, var10);
      var5.addVertexWithUV(var20, var36, var28, var12, var14);
      var5.addVertexWithUV(var22, var38, var30, var8, var14);
      var5.addVertexWithUV(var24, var40, var32, var8, var10);
      var5.addVertexWithUV(var24, var40, var32, var8, var10);
      var5.addVertexWithUV(var22, var38, var30, var8, var14);
      var5.addVertexWithUV(var20, var36, var28, var12, var14);
      var5.addVertexWithUV(var18, var34, var26, var12, var10);
      return true;
   }

   public boolean renderBlockLadder(Block var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      IIcon var6 = this.getBlockIconFromSide(var1, 0);
      if(this.hasOverrideBlockTexture()) {
         var6 = this.overrideBlockTexture;
      }

      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      double var7 = (double)var6.getMinU();
      double var9 = (double)var6.getMinV();
      double var11 = (double)var6.getMaxU();
      double var13 = (double)var6.getMaxV();
      int var15 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      double var16 = 0.0D;
      double var18 = 0.05000000074505806D;
      if(var15 == 5) {
         var5.addVertexWithUV((double)var2 + var18, (double)(var3 + 1) + var16, (double)(var4 + 1) + var16, var7, var9);
         var5.addVertexWithUV((double)var2 + var18, (double)(var3 + 0) - var16, (double)(var4 + 1) + var16, var7, var13);
         var5.addVertexWithUV((double)var2 + var18, (double)(var3 + 0) - var16, (double)(var4 + 0) - var16, var11, var13);
         var5.addVertexWithUV((double)var2 + var18, (double)(var3 + 1) + var16, (double)(var4 + 0) - var16, var11, var9);
      }

      if(var15 == 4) {
         var5.addVertexWithUV((double)(var2 + 1) - var18, (double)(var3 + 0) - var16, (double)(var4 + 1) + var16, var11, var13);
         var5.addVertexWithUV((double)(var2 + 1) - var18, (double)(var3 + 1) + var16, (double)(var4 + 1) + var16, var11, var9);
         var5.addVertexWithUV((double)(var2 + 1) - var18, (double)(var3 + 1) + var16, (double)(var4 + 0) - var16, var7, var9);
         var5.addVertexWithUV((double)(var2 + 1) - var18, (double)(var3 + 0) - var16, (double)(var4 + 0) - var16, var7, var13);
      }

      if(var15 == 3) {
         var5.addVertexWithUV((double)(var2 + 1) + var16, (double)(var3 + 0) - var16, (double)var4 + var18, var11, var13);
         var5.addVertexWithUV((double)(var2 + 1) + var16, (double)(var3 + 1) + var16, (double)var4 + var18, var11, var9);
         var5.addVertexWithUV((double)(var2 + 0) - var16, (double)(var3 + 1) + var16, (double)var4 + var18, var7, var9);
         var5.addVertexWithUV((double)(var2 + 0) - var16, (double)(var3 + 0) - var16, (double)var4 + var18, var7, var13);
      }

      if(var15 == 2) {
         var5.addVertexWithUV((double)(var2 + 1) + var16, (double)(var3 + 1) + var16, (double)(var4 + 1) - var18, var7, var9);
         var5.addVertexWithUV((double)(var2 + 1) + var16, (double)(var3 + 0) - var16, (double)(var4 + 1) - var18, var7, var13);
         var5.addVertexWithUV((double)(var2 + 0) - var16, (double)(var3 + 0) - var16, (double)(var4 + 1) - var18, var11, var13);
         var5.addVertexWithUV((double)(var2 + 0) - var16, (double)(var3 + 1) + var16, (double)(var4 + 1) - var18, var11, var9);
      }

      return true;
   }

   public boolean renderBlockVine(Block var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      IIcon var6 = this.getBlockIconFromSide(var1, 0);
      if(this.hasOverrideBlockTexture()) {
         var6 = this.overrideBlockTexture;
      }

      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      int var7 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var8 = (float)(var7 >> 16 & 255) / 255.0F;
      float var9 = (float)(var7 >> 8 & 255) / 255.0F;
      float var10 = (float)(var7 & 255) / 255.0F;
      var5.setColorOpaque_F(var8, var9, var10);
      double var18 = (double)var6.getMinU();
      double var19 = (double)var6.getMinV();
      double var11 = (double)var6.getMaxU();
      double var13 = (double)var6.getMaxV();
      double var15 = 0.05000000074505806D;
      int var17 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      if((var17 & 2) != 0) {
         var5.addVertexWithUV((double)var2 + var15, (double)(var3 + 1), (double)(var4 + 1), var18, var19);
         var5.addVertexWithUV((double)var2 + var15, (double)(var3 + 0), (double)(var4 + 1), var18, var13);
         var5.addVertexWithUV((double)var2 + var15, (double)(var3 + 0), (double)(var4 + 0), var11, var13);
         var5.addVertexWithUV((double)var2 + var15, (double)(var3 + 1), (double)(var4 + 0), var11, var19);
         var5.addVertexWithUV((double)var2 + var15, (double)(var3 + 1), (double)(var4 + 0), var11, var19);
         var5.addVertexWithUV((double)var2 + var15, (double)(var3 + 0), (double)(var4 + 0), var11, var13);
         var5.addVertexWithUV((double)var2 + var15, (double)(var3 + 0), (double)(var4 + 1), var18, var13);
         var5.addVertexWithUV((double)var2 + var15, (double)(var3 + 1), (double)(var4 + 1), var18, var19);
      }

      if((var17 & 8) != 0) {
         var5.addVertexWithUV((double)(var2 + 1) - var15, (double)(var3 + 0), (double)(var4 + 1), var11, var13);
         var5.addVertexWithUV((double)(var2 + 1) - var15, (double)(var3 + 1), (double)(var4 + 1), var11, var19);
         var5.addVertexWithUV((double)(var2 + 1) - var15, (double)(var3 + 1), (double)(var4 + 0), var18, var19);
         var5.addVertexWithUV((double)(var2 + 1) - var15, (double)(var3 + 0), (double)(var4 + 0), var18, var13);
         var5.addVertexWithUV((double)(var2 + 1) - var15, (double)(var3 + 0), (double)(var4 + 0), var18, var13);
         var5.addVertexWithUV((double)(var2 + 1) - var15, (double)(var3 + 1), (double)(var4 + 0), var18, var19);
         var5.addVertexWithUV((double)(var2 + 1) - var15, (double)(var3 + 1), (double)(var4 + 1), var11, var19);
         var5.addVertexWithUV((double)(var2 + 1) - var15, (double)(var3 + 0), (double)(var4 + 1), var11, var13);
      }

      if((var17 & 4) != 0) {
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)var4 + var15, var11, var13);
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)var4 + var15, var11, var19);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)var4 + var15, var18, var19);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)var4 + var15, var18, var13);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)var4 + var15, var18, var13);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)var4 + var15, var18, var19);
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)var4 + var15, var11, var19);
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)var4 + var15, var11, var13);
      }

      if((var17 & 1) != 0) {
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1) - var15, var18, var19);
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)(var4 + 1) - var15, var18, var13);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 1) - var15, var11, var13);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)(var4 + 1) - var15, var11, var19);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)(var4 + 1) - var15, var11, var19);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 1) - var15, var11, var13);
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)(var4 + 1) - var15, var18, var13);
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1) - var15, var18, var19);
      }

      if(this.blockAccess.getBlock(var2, var3 + 1, var4).isBlockNormalCube()) {
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1) - var15, (double)(var4 + 0), var18, var19);
         var5.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1) - var15, (double)(var4 + 1), var18, var13);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1) - var15, (double)(var4 + 1), var11, var13);
         var5.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1) - var15, (double)(var4 + 0), var11, var19);
      }

      return true;
   }

   public boolean renderBlockStainedGlassPane(Block var1, int var2, int var3, int var4) {
      int var5 = this.blockAccess.getHeight();
      Tessellator var6 = Tessellator.instance;
      var6.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      int var7 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var8 = (float)(var7 >> 16 & 255) / 255.0F;
      float var9 = (float)(var7 >> 8 & 255) / 255.0F;
      float var10 = (float)(var7 & 255) / 255.0F;
      if(EntityRenderer.anaglyphEnable) {
         float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
         float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
         float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
         var8 = var11;
         var9 = var12;
         var10 = var13;
      }

      var6.setColorOpaque_F(var8, var9, var10);
      boolean var67 = var1 instanceof BlockStainedGlassPane;
      IIcon var65;
      IIcon var66;
      if(this.hasOverrideBlockTexture()) {
         var65 = this.overrideBlockTexture;
         var66 = this.overrideBlockTexture;
      } else {
         int var14 = this.blockAccess.getBlockMetadata(var2, var3, var4);
         var65 = this.getBlockIconFromSideAndMetadata(var1, 0, var14);
         var66 = var67?((BlockStainedGlassPane)var1).func_150104_b(var14):((BlockPane)var1).func_150097_e();
      }

      double var68 = (double)var65.getMinU();
      double var16 = (double)var65.getInterpolatedU(7.0D);
      double var18 = (double)var65.getInterpolatedU(9.0D);
      double var20 = (double)var65.getMaxU();
      double var22 = (double)var65.getMinV();
      double var24 = (double)var65.getMaxV();
      double var26 = (double)var66.getInterpolatedU(7.0D);
      double var28 = (double)var66.getInterpolatedU(9.0D);
      double var30 = (double)var66.getMinV();
      double var32 = (double)var66.getMaxV();
      double var34 = (double)var66.getInterpolatedV(7.0D);
      double var36 = (double)var66.getInterpolatedV(9.0D);
      double var38 = (double)var2;
      double var40 = (double)(var2 + 1);
      double var42 = (double)var4;
      double var44 = (double)(var4 + 1);
      double var46 = (double)var2 + 0.5D - 0.0625D;
      double var48 = (double)var2 + 0.5D + 0.0625D;
      double var50 = (double)var4 + 0.5D - 0.0625D;
      double var52 = (double)var4 + 0.5D + 0.0625D;
      boolean var54 = var67?((BlockStainedGlassPane)var1).canPaneConnectToBlock(this.blockAccess.getBlock(var2, var3, var4 - 1)):((BlockPane)var1).canPaneConnectToBlock(this.blockAccess.getBlock(var2, var3, var4 - 1));
      boolean var55 = var67?((BlockStainedGlassPane)var1).canPaneConnectToBlock(this.blockAccess.getBlock(var2, var3, var4 + 1)):((BlockPane)var1).canPaneConnectToBlock(this.blockAccess.getBlock(var2, var3, var4 + 1));
      boolean var56 = var67?((BlockStainedGlassPane)var1).canPaneConnectToBlock(this.blockAccess.getBlock(var2 - 1, var3, var4)):((BlockPane)var1).canPaneConnectToBlock(this.blockAccess.getBlock(var2 - 1, var3, var4));
      boolean var57 = var67?((BlockStainedGlassPane)var1).canPaneConnectToBlock(this.blockAccess.getBlock(var2 + 1, var3, var4)):((BlockPane)var1).canPaneConnectToBlock(this.blockAccess.getBlock(var2 + 1, var3, var4));
      double var58 = 0.001D;
      double var60 = 0.999D;
      double var62 = 0.001D;
      boolean var64 = !var54 && !var55 && !var56 && !var57;
      if(!var56 && !var64) {
         if(!var54 && !var55) {
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var50, var16, var22);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var50, var16, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var52, var18, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var52, var18, var22);
         }
      } else if(var56 && var57) {
         if(!var54) {
            var6.addVertexWithUV(var40, (double)var3 + 0.999D, var50, var20, var22);
            var6.addVertexWithUV(var40, (double)var3 + 0.001D, var50, var20, var24);
            var6.addVertexWithUV(var38, (double)var3 + 0.001D, var50, var68, var24);
            var6.addVertexWithUV(var38, (double)var3 + 0.999D, var50, var68, var22);
         } else {
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var50, var16, var22);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var50, var16, var24);
            var6.addVertexWithUV(var38, (double)var3 + 0.001D, var50, var68, var24);
            var6.addVertexWithUV(var38, (double)var3 + 0.999D, var50, var68, var22);
            var6.addVertexWithUV(var40, (double)var3 + 0.999D, var50, var20, var22);
            var6.addVertexWithUV(var40, (double)var3 + 0.001D, var50, var20, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var50, var18, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var50, var18, var22);
         }

         if(!var55) {
            var6.addVertexWithUV(var38, (double)var3 + 0.999D, var52, var68, var22);
            var6.addVertexWithUV(var38, (double)var3 + 0.001D, var52, var68, var24);
            var6.addVertexWithUV(var40, (double)var3 + 0.001D, var52, var20, var24);
            var6.addVertexWithUV(var40, (double)var3 + 0.999D, var52, var20, var22);
         } else {
            var6.addVertexWithUV(var38, (double)var3 + 0.999D, var52, var68, var22);
            var6.addVertexWithUV(var38, (double)var3 + 0.001D, var52, var68, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var52, var16, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var52, var16, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var52, var18, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var52, var18, var24);
            var6.addVertexWithUV(var40, (double)var3 + 0.001D, var52, var20, var24);
            var6.addVertexWithUV(var40, (double)var3 + 0.999D, var52, var20, var22);
         }

         var6.addVertexWithUV(var38, (double)var3 + 0.999D, var52, var28, var30);
         var6.addVertexWithUV(var40, (double)var3 + 0.999D, var52, var28, var32);
         var6.addVertexWithUV(var40, (double)var3 + 0.999D, var50, var26, var32);
         var6.addVertexWithUV(var38, (double)var3 + 0.999D, var50, var26, var30);
         var6.addVertexWithUV(var40, (double)var3 + 0.001D, var52, var26, var32);
         var6.addVertexWithUV(var38, (double)var3 + 0.001D, var52, var26, var30);
         var6.addVertexWithUV(var38, (double)var3 + 0.001D, var50, var28, var30);
         var6.addVertexWithUV(var40, (double)var3 + 0.001D, var50, var28, var32);
      } else {
         if(!var54 && !var64) {
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var50, var18, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var50, var18, var24);
            var6.addVertexWithUV(var38, (double)var3 + 0.001D, var50, var68, var24);
            var6.addVertexWithUV(var38, (double)var3 + 0.999D, var50, var68, var22);
         } else {
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var50, var16, var22);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var50, var16, var24);
            var6.addVertexWithUV(var38, (double)var3 + 0.001D, var50, var68, var24);
            var6.addVertexWithUV(var38, (double)var3 + 0.999D, var50, var68, var22);
         }

         if(!var55 && !var64) {
            var6.addVertexWithUV(var38, (double)var3 + 0.999D, var52, var68, var22);
            var6.addVertexWithUV(var38, (double)var3 + 0.001D, var52, var68, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var52, var18, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var52, var18, var22);
         } else {
            var6.addVertexWithUV(var38, (double)var3 + 0.999D, var52, var68, var22);
            var6.addVertexWithUV(var38, (double)var3 + 0.001D, var52, var68, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var52, var16, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var52, var16, var22);
         }

         var6.addVertexWithUV(var38, (double)var3 + 0.999D, var52, var28, var30);
         var6.addVertexWithUV(var46, (double)var3 + 0.999D, var52, var28, var34);
         var6.addVertexWithUV(var46, (double)var3 + 0.999D, var50, var26, var34);
         var6.addVertexWithUV(var38, (double)var3 + 0.999D, var50, var26, var30);
         var6.addVertexWithUV(var46, (double)var3 + 0.001D, var52, var26, var34);
         var6.addVertexWithUV(var38, (double)var3 + 0.001D, var52, var26, var30);
         var6.addVertexWithUV(var38, (double)var3 + 0.001D, var50, var28, var30);
         var6.addVertexWithUV(var46, (double)var3 + 0.001D, var50, var28, var34);
      }

      if((var57 || var64) && !var56) {
         if(!var55 && !var64) {
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var52, var16, var22);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var52, var16, var24);
            var6.addVertexWithUV(var40, (double)var3 + 0.001D, var52, var20, var24);
            var6.addVertexWithUV(var40, (double)var3 + 0.999D, var52, var20, var22);
         } else {
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var52, var18, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var52, var18, var24);
            var6.addVertexWithUV(var40, (double)var3 + 0.001D, var52, var20, var24);
            var6.addVertexWithUV(var40, (double)var3 + 0.999D, var52, var20, var22);
         }

         if(!var54 && !var64) {
            var6.addVertexWithUV(var40, (double)var3 + 0.999D, var50, var20, var22);
            var6.addVertexWithUV(var40, (double)var3 + 0.001D, var50, var20, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var50, var16, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var50, var16, var22);
         } else {
            var6.addVertexWithUV(var40, (double)var3 + 0.999D, var50, var20, var22);
            var6.addVertexWithUV(var40, (double)var3 + 0.001D, var50, var20, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var50, var18, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var50, var18, var22);
         }

         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var52, var28, var36);
         var6.addVertexWithUV(var40, (double)var3 + 0.999D, var52, var28, var30);
         var6.addVertexWithUV(var40, (double)var3 + 0.999D, var50, var26, var30);
         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var50, var26, var36);
         var6.addVertexWithUV(var40, (double)var3 + 0.001D, var52, var26, var32);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var52, var26, var36);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var50, var28, var36);
         var6.addVertexWithUV(var40, (double)var3 + 0.001D, var50, var28, var32);
      } else if(!var57 && !var54 && !var55) {
         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var52, var16, var22);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var52, var16, var24);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var50, var18, var24);
         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var50, var18, var22);
      }

      if(!var54 && !var64) {
         if(!var57 && !var56) {
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var50, var18, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var50, var18, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var50, var16, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var50, var16, var22);
         }
      } else if(var54 && var55) {
         if(!var56) {
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var42, var68, var22);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var42, var68, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var44, var20, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var44, var20, var22);
         } else {
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var42, var68, var22);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var42, var68, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var50, var16, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var50, var16, var22);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var52, var18, var22);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var52, var18, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var44, var20, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var44, var20, var22);
         }

         if(!var57) {
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var44, var20, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var44, var20, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var42, var68, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var42, var68, var22);
         } else {
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var50, var16, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var50, var16, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var42, var68, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var42, var68, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var44, var20, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var44, var20, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var52, var18, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var52, var18, var22);
         }

         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var42, var28, var30);
         var6.addVertexWithUV(var46, (double)var3 + 0.999D, var42, var26, var30);
         var6.addVertexWithUV(var46, (double)var3 + 0.999D, var44, var26, var32);
         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var44, var28, var32);
         var6.addVertexWithUV(var46, (double)var3 + 0.001D, var42, var26, var30);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var42, var28, var30);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var44, var28, var32);
         var6.addVertexWithUV(var46, (double)var3 + 0.001D, var44, var26, var32);
      } else {
         if(!var56 && !var64) {
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var42, var68, var22);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var42, var68, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var52, var18, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var52, var18, var22);
         } else {
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var42, var68, var22);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var42, var68, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var50, var16, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var50, var16, var22);
         }

         if(!var57 && !var64) {
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var52, var18, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var52, var18, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var42, var68, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var42, var68, var22);
         } else {
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var50, var16, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var50, var16, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var42, var68, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var42, var68, var22);
         }

         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var42, var28, var30);
         var6.addVertexWithUV(var46, (double)var3 + 0.999D, var42, var26, var30);
         var6.addVertexWithUV(var46, (double)var3 + 0.999D, var50, var26, var34);
         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var50, var28, var34);
         var6.addVertexWithUV(var46, (double)var3 + 0.001D, var42, var26, var30);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var42, var28, var30);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var50, var28, var34);
         var6.addVertexWithUV(var46, (double)var3 + 0.001D, var50, var26, var34);
      }

      if((var55 || var64) && !var54) {
         if(!var56 && !var64) {
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var50, var16, var22);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var50, var16, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var44, var20, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var44, var20, var22);
         } else {
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var52, var18, var22);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var52, var18, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.001D, var44, var20, var24);
            var6.addVertexWithUV(var46, (double)var3 + 0.999D, var44, var20, var22);
         }

         if(!var57 && !var64) {
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var44, var20, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var44, var20, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var50, var16, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var50, var16, var22);
         } else {
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var44, var20, var22);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var44, var20, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.001D, var52, var18, var24);
            var6.addVertexWithUV(var48, (double)var3 + 0.999D, var52, var18, var22);
         }

         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var52, var28, var36);
         var6.addVertexWithUV(var46, (double)var3 + 0.999D, var52, var26, var36);
         var6.addVertexWithUV(var46, (double)var3 + 0.999D, var44, var26, var32);
         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var44, var28, var32);
         var6.addVertexWithUV(var46, (double)var3 + 0.001D, var52, var26, var36);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var52, var28, var36);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var44, var28, var32);
         var6.addVertexWithUV(var46, (double)var3 + 0.001D, var44, var26, var32);
      } else if(!var55 && !var57 && !var56) {
         var6.addVertexWithUV(var46, (double)var3 + 0.999D, var52, var16, var22);
         var6.addVertexWithUV(var46, (double)var3 + 0.001D, var52, var16, var24);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var52, var18, var24);
         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var52, var18, var22);
      }

      var6.addVertexWithUV(var48, (double)var3 + 0.999D, var50, var28, var34);
      var6.addVertexWithUV(var46, (double)var3 + 0.999D, var50, var26, var34);
      var6.addVertexWithUV(var46, (double)var3 + 0.999D, var52, var26, var36);
      var6.addVertexWithUV(var48, (double)var3 + 0.999D, var52, var28, var36);
      var6.addVertexWithUV(var46, (double)var3 + 0.001D, var50, var26, var34);
      var6.addVertexWithUV(var48, (double)var3 + 0.001D, var50, var28, var34);
      var6.addVertexWithUV(var48, (double)var3 + 0.001D, var52, var28, var36);
      var6.addVertexWithUV(var46, (double)var3 + 0.001D, var52, var26, var36);
      if(var64) {
         var6.addVertexWithUV(var38, (double)var3 + 0.999D, var50, var16, var22);
         var6.addVertexWithUV(var38, (double)var3 + 0.001D, var50, var16, var24);
         var6.addVertexWithUV(var38, (double)var3 + 0.001D, var52, var18, var24);
         var6.addVertexWithUV(var38, (double)var3 + 0.999D, var52, var18, var22);
         var6.addVertexWithUV(var40, (double)var3 + 0.999D, var52, var16, var22);
         var6.addVertexWithUV(var40, (double)var3 + 0.001D, var52, var16, var24);
         var6.addVertexWithUV(var40, (double)var3 + 0.001D, var50, var18, var24);
         var6.addVertexWithUV(var40, (double)var3 + 0.999D, var50, var18, var22);
         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var42, var18, var22);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var42, var18, var24);
         var6.addVertexWithUV(var46, (double)var3 + 0.001D, var42, var16, var24);
         var6.addVertexWithUV(var46, (double)var3 + 0.999D, var42, var16, var22);
         var6.addVertexWithUV(var46, (double)var3 + 0.999D, var44, var16, var22);
         var6.addVertexWithUV(var46, (double)var3 + 0.001D, var44, var16, var24);
         var6.addVertexWithUV(var48, (double)var3 + 0.001D, var44, var18, var24);
         var6.addVertexWithUV(var48, (double)var3 + 0.999D, var44, var18, var22);
      }

      return true;
   }

   public boolean renderBlockPane(BlockPane var1, int var2, int var3, int var4) {
      int var5 = this.blockAccess.getHeight();
      Tessellator var6 = Tessellator.instance;
      var6.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      int var7 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var8 = (float)(var7 >> 16 & 255) / 255.0F;
      float var9 = (float)(var7 >> 8 & 255) / 255.0F;
      float var10 = (float)(var7 & 255) / 255.0F;
      if(EntityRenderer.anaglyphEnable) {
         float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
         float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
         float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
         var8 = var11;
         var9 = var12;
         var10 = var13;
      }

      var6.setColorOpaque_F(var8, var9, var10);
      IIcon var64;
      IIcon var63;
      if(this.hasOverrideBlockTexture()) {
         var63 = this.overrideBlockTexture;
         var64 = this.overrideBlockTexture;
      } else {
         int var65 = this.blockAccess.getBlockMetadata(var2, var3, var4);
         var63 = this.getBlockIconFromSideAndMetadata(var1, 0, var65);
         var64 = var1.func_150097_e();
      }

      double var66 = (double)var63.getMinU();
      double var15 = (double)var63.getInterpolatedU(8.0D);
      double var17 = (double)var63.getMaxU();
      double var19 = (double)var63.getMinV();
      double var21 = (double)var63.getMaxV();
      double var23 = (double)var64.getInterpolatedU(7.0D);
      double var25 = (double)var64.getInterpolatedU(9.0D);
      double var27 = (double)var64.getMinV();
      double var29 = (double)var64.getInterpolatedV(8.0D);
      double var31 = (double)var64.getMaxV();
      double var33 = (double)var2;
      double var35 = (double)var2 + 0.5D;
      double var37 = (double)(var2 + 1);
      double var39 = (double)var4;
      double var41 = (double)var4 + 0.5D;
      double var43 = (double)(var4 + 1);
      double var45 = (double)var2 + 0.5D - 0.0625D;
      double var47 = (double)var2 + 0.5D + 0.0625D;
      double var49 = (double)var4 + 0.5D - 0.0625D;
      double var51 = (double)var4 + 0.5D + 0.0625D;
      boolean var53 = var1.canPaneConnectToBlock(this.blockAccess.getBlock(var2, var3, var4 - 1));
      boolean var54 = var1.canPaneConnectToBlock(this.blockAccess.getBlock(var2, var3, var4 + 1));
      boolean var55 = var1.canPaneConnectToBlock(this.blockAccess.getBlock(var2 - 1, var3, var4));
      boolean var56 = var1.canPaneConnectToBlock(this.blockAccess.getBlock(var2 + 1, var3, var4));
      boolean var57 = var1.shouldSideBeRendered(this.blockAccess, var2, var3 + 1, var4, 1);
      boolean var58 = var1.shouldSideBeRendered(this.blockAccess, var2, var3 - 1, var4, 0);
      double var59 = 0.01D;
      double var61 = 0.005D;
      if((!var55 || !var56) && (var55 || var56 || var53 || var54)) {
         if(var55 && !var56) {
            var6.addVertexWithUV(var33, (double)(var3 + 1), var41, var66, var19);
            var6.addVertexWithUV(var33, (double)(var3 + 0), var41, var66, var21);
            var6.addVertexWithUV(var35, (double)(var3 + 0), var41, var15, var21);
            var6.addVertexWithUV(var35, (double)(var3 + 1), var41, var15, var19);
            var6.addVertexWithUV(var35, (double)(var3 + 1), var41, var66, var19);
            var6.addVertexWithUV(var35, (double)(var3 + 0), var41, var66, var21);
            var6.addVertexWithUV(var33, (double)(var3 + 0), var41, var15, var21);
            var6.addVertexWithUV(var33, (double)(var3 + 1), var41, var15, var19);
            if(!var54 && !var53) {
               var6.addVertexWithUV(var35, (double)(var3 + 1), var51, var23, var27);
               var6.addVertexWithUV(var35, (double)(var3 + 0), var51, var23, var31);
               var6.addVertexWithUV(var35, (double)(var3 + 0), var49, var25, var31);
               var6.addVertexWithUV(var35, (double)(var3 + 1), var49, var25, var27);
               var6.addVertexWithUV(var35, (double)(var3 + 1), var49, var23, var27);
               var6.addVertexWithUV(var35, (double)(var3 + 0), var49, var23, var31);
               var6.addVertexWithUV(var35, (double)(var3 + 0), var51, var25, var31);
               var6.addVertexWithUV(var35, (double)(var3 + 1), var51, var25, var27);
            }

            if(var57 || var3 < var5 - 1 && this.blockAccess.isAirBlock(var2 - 1, var3 + 1, var4)) {
               var6.addVertexWithUV(var33, (double)(var3 + 1) + 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var51, var25, var31);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var49, var23, var31);
               var6.addVertexWithUV(var33, (double)(var3 + 1) + 0.01D, var49, var23, var29);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var33, (double)(var3 + 1) + 0.01D, var51, var25, var31);
               var6.addVertexWithUV(var33, (double)(var3 + 1) + 0.01D, var49, var23, var31);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var49, var23, var29);
            }

            if(var58 || var3 > 1 && this.blockAccess.isAirBlock(var2 - 1, var3 - 1, var4)) {
               var6.addVertexWithUV(var33, (double)var3 - 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var51, var25, var31);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var49, var23, var31);
               var6.addVertexWithUV(var33, (double)var3 - 0.01D, var49, var23, var29);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var33, (double)var3 - 0.01D, var51, var25, var31);
               var6.addVertexWithUV(var33, (double)var3 - 0.01D, var49, var23, var31);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var49, var23, var29);
            }
         } else if(!var55 && var56) {
            var6.addVertexWithUV(var35, (double)(var3 + 1), var41, var15, var19);
            var6.addVertexWithUV(var35, (double)(var3 + 0), var41, var15, var21);
            var6.addVertexWithUV(var37, (double)(var3 + 0), var41, var17, var21);
            var6.addVertexWithUV(var37, (double)(var3 + 1), var41, var17, var19);
            var6.addVertexWithUV(var37, (double)(var3 + 1), var41, var15, var19);
            var6.addVertexWithUV(var37, (double)(var3 + 0), var41, var15, var21);
            var6.addVertexWithUV(var35, (double)(var3 + 0), var41, var17, var21);
            var6.addVertexWithUV(var35, (double)(var3 + 1), var41, var17, var19);
            if(!var54 && !var53) {
               var6.addVertexWithUV(var35, (double)(var3 + 1), var49, var23, var27);
               var6.addVertexWithUV(var35, (double)(var3 + 0), var49, var23, var31);
               var6.addVertexWithUV(var35, (double)(var3 + 0), var51, var25, var31);
               var6.addVertexWithUV(var35, (double)(var3 + 1), var51, var25, var27);
               var6.addVertexWithUV(var35, (double)(var3 + 1), var51, var23, var27);
               var6.addVertexWithUV(var35, (double)(var3 + 0), var51, var23, var31);
               var6.addVertexWithUV(var35, (double)(var3 + 0), var49, var25, var31);
               var6.addVertexWithUV(var35, (double)(var3 + 1), var49, var25, var27);
            }

            if(var57 || var3 < var5 - 1 && this.blockAccess.isAirBlock(var2 + 1, var3 + 1, var4)) {
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var51, var25, var27);
               var6.addVertexWithUV(var37, (double)(var3 + 1) + 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var37, (double)(var3 + 1) + 0.01D, var49, var23, var29);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var49, var23, var27);
               var6.addVertexWithUV(var37, (double)(var3 + 1) + 0.01D, var51, var25, var27);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var49, var23, var29);
               var6.addVertexWithUV(var37, (double)(var3 + 1) + 0.01D, var49, var23, var27);
            }

            if(var58 || var3 > 1 && this.blockAccess.isAirBlock(var2 + 1, var3 - 1, var4)) {
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var51, var25, var27);
               var6.addVertexWithUV(var37, (double)var3 - 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var37, (double)var3 - 0.01D, var49, var23, var29);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var49, var23, var27);
               var6.addVertexWithUV(var37, (double)var3 - 0.01D, var51, var25, var27);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var49, var23, var29);
               var6.addVertexWithUV(var37, (double)var3 - 0.01D, var49, var23, var27);
            }
         }
      } else {
         var6.addVertexWithUV(var33, (double)(var3 + 1), var41, var66, var19);
         var6.addVertexWithUV(var33, (double)(var3 + 0), var41, var66, var21);
         var6.addVertexWithUV(var37, (double)(var3 + 0), var41, var17, var21);
         var6.addVertexWithUV(var37, (double)(var3 + 1), var41, var17, var19);
         var6.addVertexWithUV(var37, (double)(var3 + 1), var41, var66, var19);
         var6.addVertexWithUV(var37, (double)(var3 + 0), var41, var66, var21);
         var6.addVertexWithUV(var33, (double)(var3 + 0), var41, var17, var21);
         var6.addVertexWithUV(var33, (double)(var3 + 1), var41, var17, var19);
         if(var57) {
            var6.addVertexWithUV(var33, (double)(var3 + 1) + 0.01D, var51, var25, var31);
            var6.addVertexWithUV(var37, (double)(var3 + 1) + 0.01D, var51, var25, var27);
            var6.addVertexWithUV(var37, (double)(var3 + 1) + 0.01D, var49, var23, var27);
            var6.addVertexWithUV(var33, (double)(var3 + 1) + 0.01D, var49, var23, var31);
            var6.addVertexWithUV(var37, (double)(var3 + 1) + 0.01D, var51, var25, var31);
            var6.addVertexWithUV(var33, (double)(var3 + 1) + 0.01D, var51, var25, var27);
            var6.addVertexWithUV(var33, (double)(var3 + 1) + 0.01D, var49, var23, var27);
            var6.addVertexWithUV(var37, (double)(var3 + 1) + 0.01D, var49, var23, var31);
         } else {
            if(var3 < var5 - 1 && this.blockAccess.isAirBlock(var2 - 1, var3 + 1, var4)) {
               var6.addVertexWithUV(var33, (double)(var3 + 1) + 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var51, var25, var31);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var49, var23, var31);
               var6.addVertexWithUV(var33, (double)(var3 + 1) + 0.01D, var49, var23, var29);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var33, (double)(var3 + 1) + 0.01D, var51, var25, var31);
               var6.addVertexWithUV(var33, (double)(var3 + 1) + 0.01D, var49, var23, var31);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var49, var23, var29);
            }

            if(var3 < var5 - 1 && this.blockAccess.isAirBlock(var2 + 1, var3 + 1, var4)) {
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var51, var25, var27);
               var6.addVertexWithUV(var37, (double)(var3 + 1) + 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var37, (double)(var3 + 1) + 0.01D, var49, var23, var29);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var49, var23, var27);
               var6.addVertexWithUV(var37, (double)(var3 + 1) + 0.01D, var51, var25, var27);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var35, (double)(var3 + 1) + 0.01D, var49, var23, var29);
               var6.addVertexWithUV(var37, (double)(var3 + 1) + 0.01D, var49, var23, var27);
            }
         }

         if(var58) {
            var6.addVertexWithUV(var33, (double)var3 - 0.01D, var51, var25, var31);
            var6.addVertexWithUV(var37, (double)var3 - 0.01D, var51, var25, var27);
            var6.addVertexWithUV(var37, (double)var3 - 0.01D, var49, var23, var27);
            var6.addVertexWithUV(var33, (double)var3 - 0.01D, var49, var23, var31);
            var6.addVertexWithUV(var37, (double)var3 - 0.01D, var51, var25, var31);
            var6.addVertexWithUV(var33, (double)var3 - 0.01D, var51, var25, var27);
            var6.addVertexWithUV(var33, (double)var3 - 0.01D, var49, var23, var27);
            var6.addVertexWithUV(var37, (double)var3 - 0.01D, var49, var23, var31);
         } else {
            if(var3 > 1 && this.blockAccess.isAirBlock(var2 - 1, var3 - 1, var4)) {
               var6.addVertexWithUV(var33, (double)var3 - 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var51, var25, var31);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var49, var23, var31);
               var6.addVertexWithUV(var33, (double)var3 - 0.01D, var49, var23, var29);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var33, (double)var3 - 0.01D, var51, var25, var31);
               var6.addVertexWithUV(var33, (double)var3 - 0.01D, var49, var23, var31);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var49, var23, var29);
            }

            if(var3 > 1 && this.blockAccess.isAirBlock(var2 + 1, var3 - 1, var4)) {
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var51, var25, var27);
               var6.addVertexWithUV(var37, (double)var3 - 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var37, (double)var3 - 0.01D, var49, var23, var29);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var49, var23, var27);
               var6.addVertexWithUV(var37, (double)var3 - 0.01D, var51, var25, var27);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var51, var25, var29);
               var6.addVertexWithUV(var35, (double)var3 - 0.01D, var49, var23, var29);
               var6.addVertexWithUV(var37, (double)var3 - 0.01D, var49, var23, var27);
            }
         }
      }

      if((!var53 || !var54) && (var55 || var56 || var53 || var54)) {
         if(var53 && !var54) {
            var6.addVertexWithUV(var35, (double)(var3 + 1), var39, var66, var19);
            var6.addVertexWithUV(var35, (double)(var3 + 0), var39, var66, var21);
            var6.addVertexWithUV(var35, (double)(var3 + 0), var41, var15, var21);
            var6.addVertexWithUV(var35, (double)(var3 + 1), var41, var15, var19);
            var6.addVertexWithUV(var35, (double)(var3 + 1), var41, var66, var19);
            var6.addVertexWithUV(var35, (double)(var3 + 0), var41, var66, var21);
            var6.addVertexWithUV(var35, (double)(var3 + 0), var39, var15, var21);
            var6.addVertexWithUV(var35, (double)(var3 + 1), var39, var15, var19);
            if(!var56 && !var55) {
               var6.addVertexWithUV(var45, (double)(var3 + 1), var41, var23, var27);
               var6.addVertexWithUV(var45, (double)(var3 + 0), var41, var23, var31);
               var6.addVertexWithUV(var47, (double)(var3 + 0), var41, var25, var31);
               var6.addVertexWithUV(var47, (double)(var3 + 1), var41, var25, var27);
               var6.addVertexWithUV(var47, (double)(var3 + 1), var41, var23, var27);
               var6.addVertexWithUV(var47, (double)(var3 + 0), var41, var23, var31);
               var6.addVertexWithUV(var45, (double)(var3 + 0), var41, var25, var31);
               var6.addVertexWithUV(var45, (double)(var3 + 1), var41, var25, var27);
            }

            if(var57 || var3 < var5 - 1 && this.blockAccess.isAirBlock(var2, var3 + 1, var4 - 1)) {
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var39, var25, var27);
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var41, var25, var29);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var41, var23, var29);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var39, var23, var27);
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var41, var25, var27);
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var39, var25, var29);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var39, var23, var29);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var41, var23, var27);
            }

            if(var58 || var3 > 1 && this.blockAccess.isAirBlock(var2, var3 - 1, var4 - 1)) {
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var39, var25, var27);
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var41, var25, var29);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var41, var23, var29);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var39, var23, var27);
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var41, var25, var27);
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var39, var25, var29);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var39, var23, var29);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var41, var23, var27);
            }
         } else if(!var53 && var54) {
            var6.addVertexWithUV(var35, (double)(var3 + 1), var41, var15, var19);
            var6.addVertexWithUV(var35, (double)(var3 + 0), var41, var15, var21);
            var6.addVertexWithUV(var35, (double)(var3 + 0), var43, var17, var21);
            var6.addVertexWithUV(var35, (double)(var3 + 1), var43, var17, var19);
            var6.addVertexWithUV(var35, (double)(var3 + 1), var43, var15, var19);
            var6.addVertexWithUV(var35, (double)(var3 + 0), var43, var15, var21);
            var6.addVertexWithUV(var35, (double)(var3 + 0), var41, var17, var21);
            var6.addVertexWithUV(var35, (double)(var3 + 1), var41, var17, var19);
            if(!var56 && !var55) {
               var6.addVertexWithUV(var47, (double)(var3 + 1), var41, var23, var27);
               var6.addVertexWithUV(var47, (double)(var3 + 0), var41, var23, var31);
               var6.addVertexWithUV(var45, (double)(var3 + 0), var41, var25, var31);
               var6.addVertexWithUV(var45, (double)(var3 + 1), var41, var25, var27);
               var6.addVertexWithUV(var45, (double)(var3 + 1), var41, var23, var27);
               var6.addVertexWithUV(var45, (double)(var3 + 0), var41, var23, var31);
               var6.addVertexWithUV(var47, (double)(var3 + 0), var41, var25, var31);
               var6.addVertexWithUV(var47, (double)(var3 + 1), var41, var25, var27);
            }

            if(var57 || var3 < var5 - 1 && this.blockAccess.isAirBlock(var2, var3 + 1, var4 + 1)) {
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var41, var23, var29);
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var43, var23, var31);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var43, var25, var31);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var41, var25, var29);
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var43, var23, var29);
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var41, var23, var31);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var41, var25, var31);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var43, var25, var29);
            }

            if(var58 || var3 > 1 && this.blockAccess.isAirBlock(var2, var3 - 1, var4 + 1)) {
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var41, var23, var29);
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var43, var23, var31);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var43, var25, var31);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var41, var25, var29);
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var43, var23, var29);
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var41, var23, var31);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var41, var25, var31);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var43, var25, var29);
            }
         }
      } else {
         var6.addVertexWithUV(var35, (double)(var3 + 1), var43, var66, var19);
         var6.addVertexWithUV(var35, (double)(var3 + 0), var43, var66, var21);
         var6.addVertexWithUV(var35, (double)(var3 + 0), var39, var17, var21);
         var6.addVertexWithUV(var35, (double)(var3 + 1), var39, var17, var19);
         var6.addVertexWithUV(var35, (double)(var3 + 1), var39, var66, var19);
         var6.addVertexWithUV(var35, (double)(var3 + 0), var39, var66, var21);
         var6.addVertexWithUV(var35, (double)(var3 + 0), var43, var17, var21);
         var6.addVertexWithUV(var35, (double)(var3 + 1), var43, var17, var19);
         if(var57) {
            var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var43, var25, var31);
            var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var39, var25, var27);
            var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var39, var23, var27);
            var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var43, var23, var31);
            var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var39, var25, var31);
            var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var43, var25, var27);
            var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var43, var23, var27);
            var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var39, var23, var31);
         } else {
            if(var3 < var5 - 1 && this.blockAccess.isAirBlock(var2, var3 + 1, var4 - 1)) {
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var39, var25, var27);
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var41, var25, var29);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var41, var23, var29);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var39, var23, var27);
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var41, var25, var27);
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var39, var25, var29);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var39, var23, var29);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var41, var23, var27);
            }

            if(var3 < var5 - 1 && this.blockAccess.isAirBlock(var2, var3 + 1, var4 + 1)) {
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var41, var23, var29);
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var43, var23, var31);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var43, var25, var31);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var41, var25, var29);
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var43, var23, var29);
               var6.addVertexWithUV(var45, (double)(var3 + 1) + 0.005D, var41, var23, var31);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var41, var25, var31);
               var6.addVertexWithUV(var47, (double)(var3 + 1) + 0.005D, var43, var25, var29);
            }
         }

         if(var58) {
            var6.addVertexWithUV(var47, (double)var3 - 0.005D, var43, var25, var31);
            var6.addVertexWithUV(var47, (double)var3 - 0.005D, var39, var25, var27);
            var6.addVertexWithUV(var45, (double)var3 - 0.005D, var39, var23, var27);
            var6.addVertexWithUV(var45, (double)var3 - 0.005D, var43, var23, var31);
            var6.addVertexWithUV(var47, (double)var3 - 0.005D, var39, var25, var31);
            var6.addVertexWithUV(var47, (double)var3 - 0.005D, var43, var25, var27);
            var6.addVertexWithUV(var45, (double)var3 - 0.005D, var43, var23, var27);
            var6.addVertexWithUV(var45, (double)var3 - 0.005D, var39, var23, var31);
         } else {
            if(var3 > 1 && this.blockAccess.isAirBlock(var2, var3 - 1, var4 - 1)) {
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var39, var25, var27);
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var41, var25, var29);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var41, var23, var29);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var39, var23, var27);
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var41, var25, var27);
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var39, var25, var29);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var39, var23, var29);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var41, var23, var27);
            }

            if(var3 > 1 && this.blockAccess.isAirBlock(var2, var3 - 1, var4 + 1)) {
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var41, var23, var29);
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var43, var23, var31);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var43, var25, var31);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var41, var25, var29);
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var43, var23, var29);
               var6.addVertexWithUV(var45, (double)var3 - 0.005D, var41, var23, var31);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var41, var25, var31);
               var6.addVertexWithUV(var47, (double)var3 - 0.005D, var43, var25, var29);
            }
         }
      }

      return true;
   }

   public boolean renderCrossedSquares(Block var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      int var6 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var7 = (float)(var6 >> 16 & 255) / 255.0F;
      float var8 = (float)(var6 >> 8 & 255) / 255.0F;
      float var9 = (float)(var6 & 255) / 255.0F;
      if(EntityRenderer.anaglyphEnable) {
         float var10 = (var7 * 30.0F + var8 * 59.0F + var9 * 11.0F) / 100.0F;
         float var11 = (var7 * 30.0F + var8 * 70.0F) / 100.0F;
         float var12 = (var7 * 30.0F + var9 * 70.0F) / 100.0F;
         var7 = var10;
         var8 = var11;
         var9 = var12;
      }

      var5.setColorOpaque_F(var7, var8, var9);
      double var18 = (double)var2;
      double var19 = (double)var3;
      double var14 = (double)var4;
      long var16;
      if(var1 == Blocks.tallgrass) {
         var16 = (long)(var2 * 3129871) ^ (long)var4 * 116129781L ^ (long)var3;
         var16 = var16 * var16 * 42317861L + var16 * 11L;
         var18 += ((double)((float)(var16 >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
         var19 += ((double)((float)(var16 >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
         var14 += ((double)((float)(var16 >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;
      } else if(var1 == Blocks.red_flower || var1 == Blocks.yellow_flower) {
         var16 = (long)(var2 * 3129871) ^ (long)var4 * 116129781L ^ (long)var3;
         var16 = var16 * var16 * 42317861L + var16 * 11L;
         var18 += ((double)((float)(var16 >> 16 & 15L) / 15.0F) - 0.5D) * 0.3D;
         var14 += ((double)((float)(var16 >> 24 & 15L) / 15.0F) - 0.5D) * 0.3D;
      }

      IIcon var20 = this.getBlockIconFromSideAndMetadata(var1, 0, this.blockAccess.getBlockMetadata(var2, var3, var4));
      this.drawCrossedSquares(var20, var18, var19, var14, 1.0F);
      return true;
   }

   public boolean renderBlockDoublePlant(BlockDoublePlant var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      int var6 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var7 = (float)(var6 >> 16 & 255) / 255.0F;
      float var8 = (float)(var6 >> 8 & 255) / 255.0F;
      float var9 = (float)(var6 & 255) / 255.0F;
      if(EntityRenderer.anaglyphEnable) {
         float var10 = (var7 * 30.0F + var8 * 59.0F + var9 * 11.0F) / 100.0F;
         float var11 = (var7 * 30.0F + var8 * 70.0F) / 100.0F;
         float var12 = (var7 * 30.0F + var9 * 70.0F) / 100.0F;
         var7 = var10;
         var8 = var11;
         var9 = var12;
      }

      var5.setColorOpaque_F(var7, var8, var9);
      long var58 = (long)(var2 * 3129871) ^ (long)var4 * 116129781L;
      var58 = var58 * var58 * 42317861L + var58 * 11L;
      double var59 = (double)var2;
      double var14 = (double)var3;
      double var16 = (double)var4;
      var59 += ((double)((float)(var58 >> 16 & 15L) / 15.0F) - 0.5D) * 0.3D;
      var16 += ((double)((float)(var58 >> 24 & 15L) / 15.0F) - 0.5D) * 0.3D;
      int var18 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      boolean var19 = false;
      boolean var20 = BlockDoublePlant.func_149887_c(var18);
      int var60;
      if(var20) {
         if(this.blockAccess.getBlock(var2, var3 - 1, var4) != var1) {
            return false;
         }

         var60 = BlockDoublePlant.func_149890_d(this.blockAccess.getBlockMetadata(var2, var3 - 1, var4));
      } else {
         var60 = BlockDoublePlant.func_149890_d(var18);
      }

      IIcon var21 = var1.func_149888_a(var20, var60);
      this.drawCrossedSquares(var21, var59, var14, var16, 1.0F);
      if(var20 && var60 == 0) {
         IIcon var22 = var1.sunflowerIcons[0];
         double var23 = Math.cos((double)var58 * 0.8D) * 3.141592653589793D * 0.1D;
         double var25 = Math.cos(var23);
         double var27 = Math.sin(var23);
         double var29 = (double)var22.getMinU();
         double var31 = (double)var22.getMinV();
         double var33 = (double)var22.getMaxU();
         double var35 = (double)var22.getMaxV();
         double var37 = 0.3D;
         double var39 = -0.05D;
         double var41 = 0.5D + 0.3D * var25 - 0.5D * var27;
         double var43 = 0.5D + 0.5D * var25 + 0.3D * var27;
         double var45 = 0.5D + 0.3D * var25 + 0.5D * var27;
         double var47 = 0.5D + -0.5D * var25 + 0.3D * var27;
         double var49 = 0.5D + -0.05D * var25 + 0.5D * var27;
         double var51 = 0.5D + -0.5D * var25 + -0.05D * var27;
         double var53 = 0.5D + -0.05D * var25 - 0.5D * var27;
         double var55 = 0.5D + 0.5D * var25 + -0.05D * var27;
         var5.addVertexWithUV(var59 + var49, var14 + 1.0D, var16 + var51, var29, var35);
         var5.addVertexWithUV(var59 + var53, var14 + 1.0D, var16 + var55, var33, var35);
         var5.addVertexWithUV(var59 + var41, var14 + 0.0D, var16 + var43, var33, var31);
         var5.addVertexWithUV(var59 + var45, var14 + 0.0D, var16 + var47, var29, var31);
         IIcon var57 = var1.sunflowerIcons[1];
         var29 = (double)var57.getMinU();
         var31 = (double)var57.getMinV();
         var33 = (double)var57.getMaxU();
         var35 = (double)var57.getMaxV();
         var5.addVertexWithUV(var59 + var53, var14 + 1.0D, var16 + var55, var29, var35);
         var5.addVertexWithUV(var59 + var49, var14 + 1.0D, var16 + var51, var33, var35);
         var5.addVertexWithUV(var59 + var45, var14 + 0.0D, var16 + var47, var33, var31);
         var5.addVertexWithUV(var59 + var41, var14 + 0.0D, var16 + var43, var29, var31);
      }

      return true;
   }

   public boolean renderBlockStem(Block var1, int var2, int var3, int var4) {
      BlockStem var5 = (BlockStem)var1;
      Tessellator var6 = Tessellator.instance;
      var6.setBrightness(var5.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      int var7 = var5.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var8 = (float)(var7 >> 16 & 255) / 255.0F;
      float var9 = (float)(var7 >> 8 & 255) / 255.0F;
      float var10 = (float)(var7 & 255) / 255.0F;
      if(EntityRenderer.anaglyphEnable) {
         float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
         float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
         float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
         var8 = var11;
         var9 = var12;
         var10 = var13;
      }

      var6.setColorOpaque_F(var8, var9, var10);
      var5.setBlockBoundsBasedOnState(this.blockAccess, var2, var3, var4);
      int var14 = var5.getState(this.blockAccess, var2, var3, var4);
      if(var14 < 0) {
         this.renderBlockStemSmall(var5, this.blockAccess.getBlockMetadata(var2, var3, var4), this.renderMaxY, (double)var2, (double)((float)var3 - 0.0625F), (double)var4);
      } else {
         this.renderBlockStemSmall(var5, this.blockAccess.getBlockMetadata(var2, var3, var4), 0.5D, (double)var2, (double)((float)var3 - 0.0625F), (double)var4);
         this.renderBlockStemBig(var5, this.blockAccess.getBlockMetadata(var2, var3, var4), var14, this.renderMaxY, (double)var2, (double)((float)var3 - 0.0625F), (double)var4);
      }

      return true;
   }

   public boolean renderBlockCrops(Block var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      this.renderBlockCropsImpl(var1, this.blockAccess.getBlockMetadata(var2, var3, var4), (double)var2, (double)((float)var3 - 0.0625F), (double)var4);
      return true;
   }

   public void renderTorchAtAngle(Block var1, double var2, double var4, double var6, double var8, double var10, int var12) {
      Tessellator var13 = Tessellator.instance;
      IIcon var14 = this.getBlockIconFromSideAndMetadata(var1, 0, var12);
      if(this.hasOverrideBlockTexture()) {
         var14 = this.overrideBlockTexture;
      }

      double var15 = (double)var14.getMinU();
      double var17 = (double)var14.getMinV();
      double var19 = (double)var14.getMaxU();
      double var21 = (double)var14.getMaxV();
      double var23 = (double)var14.getInterpolatedU(7.0D);
      double var25 = (double)var14.getInterpolatedV(6.0D);
      double var27 = (double)var14.getInterpolatedU(9.0D);
      double var29 = (double)var14.getInterpolatedV(8.0D);
      double var31 = (double)var14.getInterpolatedU(7.0D);
      double var33 = (double)var14.getInterpolatedV(13.0D);
      double var35 = (double)var14.getInterpolatedU(9.0D);
      double var37 = (double)var14.getInterpolatedV(15.0D);
      var2 += 0.5D;
      var6 += 0.5D;
      double var39 = var2 - 0.5D;
      double var41 = var2 + 0.5D;
      double var43 = var6 - 0.5D;
      double var45 = var6 + 0.5D;
      double var47 = 0.0625D;
      double var49 = 0.625D;
      var13.addVertexWithUV(var2 + var8 * (1.0D - var49) - var47, var4 + var49, var6 + var10 * (1.0D - var49) - var47, var23, var25);
      var13.addVertexWithUV(var2 + var8 * (1.0D - var49) - var47, var4 + var49, var6 + var10 * (1.0D - var49) + var47, var23, var29);
      var13.addVertexWithUV(var2 + var8 * (1.0D - var49) + var47, var4 + var49, var6 + var10 * (1.0D - var49) + var47, var27, var29);
      var13.addVertexWithUV(var2 + var8 * (1.0D - var49) + var47, var4 + var49, var6 + var10 * (1.0D - var49) - var47, var27, var25);
      var13.addVertexWithUV(var2 + var47 + var8, var4, var6 - var47 + var10, var35, var33);
      var13.addVertexWithUV(var2 + var47 + var8, var4, var6 + var47 + var10, var35, var37);
      var13.addVertexWithUV(var2 - var47 + var8, var4, var6 + var47 + var10, var31, var37);
      var13.addVertexWithUV(var2 - var47 + var8, var4, var6 - var47 + var10, var31, var33);
      var13.addVertexWithUV(var2 - var47, var4 + 1.0D, var43, var15, var17);
      var13.addVertexWithUV(var2 - var47 + var8, var4 + 0.0D, var43 + var10, var15, var21);
      var13.addVertexWithUV(var2 - var47 + var8, var4 + 0.0D, var45 + var10, var19, var21);
      var13.addVertexWithUV(var2 - var47, var4 + 1.0D, var45, var19, var17);
      var13.addVertexWithUV(var2 + var47, var4 + 1.0D, var45, var15, var17);
      var13.addVertexWithUV(var2 + var8 + var47, var4 + 0.0D, var45 + var10, var15, var21);
      var13.addVertexWithUV(var2 + var8 + var47, var4 + 0.0D, var43 + var10, var19, var21);
      var13.addVertexWithUV(var2 + var47, var4 + 1.0D, var43, var19, var17);
      var13.addVertexWithUV(var39, var4 + 1.0D, var6 + var47, var15, var17);
      var13.addVertexWithUV(var39 + var8, var4 + 0.0D, var6 + var47 + var10, var15, var21);
      var13.addVertexWithUV(var41 + var8, var4 + 0.0D, var6 + var47 + var10, var19, var21);
      var13.addVertexWithUV(var41, var4 + 1.0D, var6 + var47, var19, var17);
      var13.addVertexWithUV(var41, var4 + 1.0D, var6 - var47, var15, var17);
      var13.addVertexWithUV(var41 + var8, var4 + 0.0D, var6 - var47 + var10, var15, var21);
      var13.addVertexWithUV(var39 + var8, var4 + 0.0D, var6 - var47 + var10, var19, var21);
      var13.addVertexWithUV(var39, var4 + 1.0D, var6 - var47, var19, var17);
   }

   public void drawCrossedSquares(IIcon var1, double var2, double var4, double var6, float var8) {
      Tessellator var9 = Tessellator.instance;
      if(this.hasOverrideBlockTexture()) {
         var1 = this.overrideBlockTexture;
      }

      double var10 = (double)var1.getMinU();
      double var12 = (double)var1.getMinV();
      double var14 = (double)var1.getMaxU();
      double var16 = (double)var1.getMaxV();
      double var18 = 0.45D * (double)var8;
      double var20 = var2 + 0.5D - var18;
      double var22 = var2 + 0.5D + var18;
      double var24 = var6 + 0.5D - var18;
      double var26 = var6 + 0.5D + var18;
      var9.addVertexWithUV(var20, var4 + (double)var8, var24, var10, var12);
      var9.addVertexWithUV(var20, var4 + 0.0D, var24, var10, var16);
      var9.addVertexWithUV(var22, var4 + 0.0D, var26, var14, var16);
      var9.addVertexWithUV(var22, var4 + (double)var8, var26, var14, var12);
      var9.addVertexWithUV(var22, var4 + (double)var8, var26, var10, var12);
      var9.addVertexWithUV(var22, var4 + 0.0D, var26, var10, var16);
      var9.addVertexWithUV(var20, var4 + 0.0D, var24, var14, var16);
      var9.addVertexWithUV(var20, var4 + (double)var8, var24, var14, var12);
      var9.addVertexWithUV(var20, var4 + (double)var8, var26, var10, var12);
      var9.addVertexWithUV(var20, var4 + 0.0D, var26, var10, var16);
      var9.addVertexWithUV(var22, var4 + 0.0D, var24, var14, var16);
      var9.addVertexWithUV(var22, var4 + (double)var8, var24, var14, var12);
      var9.addVertexWithUV(var22, var4 + (double)var8, var24, var10, var12);
      var9.addVertexWithUV(var22, var4 + 0.0D, var24, var10, var16);
      var9.addVertexWithUV(var20, var4 + 0.0D, var26, var14, var16);
      var9.addVertexWithUV(var20, var4 + (double)var8, var26, var14, var12);
   }

   public void renderBlockStemSmall(Block var1, int var2, double var3, double var5, double var7, double var9) {
      Tessellator var11 = Tessellator.instance;
      IIcon var12 = this.getBlockIconFromSideAndMetadata(var1, 0, var2);
      if(this.hasOverrideBlockTexture()) {
         var12 = this.overrideBlockTexture;
      }

      double var13 = (double)var12.getMinU();
      double var15 = (double)var12.getMinV();
      double var17 = (double)var12.getMaxU();
      double var19 = (double)var12.getInterpolatedV(var3 * 16.0D);
      double var21 = var5 + 0.5D - 0.44999998807907104D;
      double var23 = var5 + 0.5D + 0.44999998807907104D;
      double var25 = var9 + 0.5D - 0.44999998807907104D;
      double var27 = var9 + 0.5D + 0.44999998807907104D;
      var11.addVertexWithUV(var21, var7 + var3, var25, var13, var15);
      var11.addVertexWithUV(var21, var7 + 0.0D, var25, var13, var19);
      var11.addVertexWithUV(var23, var7 + 0.0D, var27, var17, var19);
      var11.addVertexWithUV(var23, var7 + var3, var27, var17, var15);
      var11.addVertexWithUV(var23, var7 + var3, var27, var17, var15);
      var11.addVertexWithUV(var23, var7 + 0.0D, var27, var17, var19);
      var11.addVertexWithUV(var21, var7 + 0.0D, var25, var13, var19);
      var11.addVertexWithUV(var21, var7 + var3, var25, var13, var15);
      var11.addVertexWithUV(var21, var7 + var3, var27, var13, var15);
      var11.addVertexWithUV(var21, var7 + 0.0D, var27, var13, var19);
      var11.addVertexWithUV(var23, var7 + 0.0D, var25, var17, var19);
      var11.addVertexWithUV(var23, var7 + var3, var25, var17, var15);
      var11.addVertexWithUV(var23, var7 + var3, var25, var17, var15);
      var11.addVertexWithUV(var23, var7 + 0.0D, var25, var17, var19);
      var11.addVertexWithUV(var21, var7 + 0.0D, var27, var13, var19);
      var11.addVertexWithUV(var21, var7 + var3, var27, var13, var15);
   }

   public boolean renderBlockLilyPad(Block var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      IIcon var6 = this.getBlockIconFromSide(var1, 1);
      if(this.hasOverrideBlockTexture()) {
         var6 = this.overrideBlockTexture;
      }

      float var7 = 0.015625F;
      double var8 = (double)var6.getMinU();
      double var10 = (double)var6.getMinV();
      double var12 = (double)var6.getMaxU();
      double var14 = (double)var6.getMaxV();
      long var16 = (long)(var2 * 3129871) ^ (long)var4 * 116129781L ^ (long)var3;
      var16 = var16 * var16 * 42317861L + var16 * 11L;
      int var18 = (int)(var16 >> 16 & 3L);
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      float var19 = (float)var2 + 0.5F;
      float var20 = (float)var4 + 0.5F;
      float var21 = (float)(var18 & 1) * 0.5F * (float)(1 - var18 / 2 % 2 * 2);
      float var22 = (float)(var18 + 1 & 1) * 0.5F * (float)(1 - (var18 + 1) / 2 % 2 * 2);
      var5.setColorOpaque_I(var1.getBlockColor());
      var5.addVertexWithUV((double)(var19 + var21 - var22), (double)((float)var3 + var7), (double)(var20 + var21 + var22), var8, var10);
      var5.addVertexWithUV((double)(var19 + var21 + var22), (double)((float)var3 + var7), (double)(var20 - var21 + var22), var12, var10);
      var5.addVertexWithUV((double)(var19 - var21 + var22), (double)((float)var3 + var7), (double)(var20 - var21 - var22), var12, var14);
      var5.addVertexWithUV((double)(var19 - var21 - var22), (double)((float)var3 + var7), (double)(var20 + var21 - var22), var8, var14);
      var5.setColorOpaque_I((var1.getBlockColor() & 16711422) >> 1);
      var5.addVertexWithUV((double)(var19 - var21 - var22), (double)((float)var3 + var7), (double)(var20 + var21 - var22), var8, var14);
      var5.addVertexWithUV((double)(var19 - var21 + var22), (double)((float)var3 + var7), (double)(var20 - var21 - var22), var12, var14);
      var5.addVertexWithUV((double)(var19 + var21 + var22), (double)((float)var3 + var7), (double)(var20 - var21 + var22), var12, var10);
      var5.addVertexWithUV((double)(var19 + var21 - var22), (double)((float)var3 + var7), (double)(var20 + var21 + var22), var8, var10);
      return true;
   }

   public void renderBlockStemBig(BlockStem var1, int var2, int var3, double var4, double var6, double var8, double var10) {
      Tessellator var12 = Tessellator.instance;
      IIcon var13 = var1.getStemIcon();
      if(this.hasOverrideBlockTexture()) {
         var13 = this.overrideBlockTexture;
      }

      double var14 = (double)var13.getMinU();
      double var16 = (double)var13.getMinV();
      double var18 = (double)var13.getMaxU();
      double var20 = (double)var13.getMaxV();
      double var22 = var6 + 0.5D - 0.5D;
      double var24 = var6 + 0.5D + 0.5D;
      double var26 = var10 + 0.5D - 0.5D;
      double var28 = var10 + 0.5D + 0.5D;
      double var30 = var6 + 0.5D;
      double var32 = var10 + 0.5D;
      if((var3 + 1) / 2 % 2 == 1) {
         double var34 = var18;
         var18 = var14;
         var14 = var34;
      }

      if(var3 < 2) {
         var12.addVertexWithUV(var22, var8 + var4, var32, var14, var16);
         var12.addVertexWithUV(var22, var8 + 0.0D, var32, var14, var20);
         var12.addVertexWithUV(var24, var8 + 0.0D, var32, var18, var20);
         var12.addVertexWithUV(var24, var8 + var4, var32, var18, var16);
         var12.addVertexWithUV(var24, var8 + var4, var32, var18, var16);
         var12.addVertexWithUV(var24, var8 + 0.0D, var32, var18, var20);
         var12.addVertexWithUV(var22, var8 + 0.0D, var32, var14, var20);
         var12.addVertexWithUV(var22, var8 + var4, var32, var14, var16);
      } else {
         var12.addVertexWithUV(var30, var8 + var4, var28, var14, var16);
         var12.addVertexWithUV(var30, var8 + 0.0D, var28, var14, var20);
         var12.addVertexWithUV(var30, var8 + 0.0D, var26, var18, var20);
         var12.addVertexWithUV(var30, var8 + var4, var26, var18, var16);
         var12.addVertexWithUV(var30, var8 + var4, var26, var18, var16);
         var12.addVertexWithUV(var30, var8 + 0.0D, var26, var18, var20);
         var12.addVertexWithUV(var30, var8 + 0.0D, var28, var14, var20);
         var12.addVertexWithUV(var30, var8 + var4, var28, var14, var16);
      }

   }

   public void renderBlockCropsImpl(Block var1, int var2, double var3, double var5, double var7) {
      Tessellator var9 = Tessellator.instance;
      IIcon var10 = this.getBlockIconFromSideAndMetadata(var1, 0, var2);
      if(this.hasOverrideBlockTexture()) {
         var10 = this.overrideBlockTexture;
      }

      double var11 = (double)var10.getMinU();
      double var13 = (double)var10.getMinV();
      double var15 = (double)var10.getMaxU();
      double var17 = (double)var10.getMaxV();
      double var19 = var3 + 0.5D - 0.25D;
      double var21 = var3 + 0.5D + 0.25D;
      double var23 = var7 + 0.5D - 0.5D;
      double var25 = var7 + 0.5D + 0.5D;
      var9.addVertexWithUV(var19, var5 + 1.0D, var23, var11, var13);
      var9.addVertexWithUV(var19, var5 + 0.0D, var23, var11, var17);
      var9.addVertexWithUV(var19, var5 + 0.0D, var25, var15, var17);
      var9.addVertexWithUV(var19, var5 + 1.0D, var25, var15, var13);
      var9.addVertexWithUV(var19, var5 + 1.0D, var25, var11, var13);
      var9.addVertexWithUV(var19, var5 + 0.0D, var25, var11, var17);
      var9.addVertexWithUV(var19, var5 + 0.0D, var23, var15, var17);
      var9.addVertexWithUV(var19, var5 + 1.0D, var23, var15, var13);
      var9.addVertexWithUV(var21, var5 + 1.0D, var25, var11, var13);
      var9.addVertexWithUV(var21, var5 + 0.0D, var25, var11, var17);
      var9.addVertexWithUV(var21, var5 + 0.0D, var23, var15, var17);
      var9.addVertexWithUV(var21, var5 + 1.0D, var23, var15, var13);
      var9.addVertexWithUV(var21, var5 + 1.0D, var23, var11, var13);
      var9.addVertexWithUV(var21, var5 + 0.0D, var23, var11, var17);
      var9.addVertexWithUV(var21, var5 + 0.0D, var25, var15, var17);
      var9.addVertexWithUV(var21, var5 + 1.0D, var25, var15, var13);
      var19 = var3 + 0.5D - 0.5D;
      var21 = var3 + 0.5D + 0.5D;
      var23 = var7 + 0.5D - 0.25D;
      var25 = var7 + 0.5D + 0.25D;
      var9.addVertexWithUV(var19, var5 + 1.0D, var23, var11, var13);
      var9.addVertexWithUV(var19, var5 + 0.0D, var23, var11, var17);
      var9.addVertexWithUV(var21, var5 + 0.0D, var23, var15, var17);
      var9.addVertexWithUV(var21, var5 + 1.0D, var23, var15, var13);
      var9.addVertexWithUV(var21, var5 + 1.0D, var23, var11, var13);
      var9.addVertexWithUV(var21, var5 + 0.0D, var23, var11, var17);
      var9.addVertexWithUV(var19, var5 + 0.0D, var23, var15, var17);
      var9.addVertexWithUV(var19, var5 + 1.0D, var23, var15, var13);
      var9.addVertexWithUV(var21, var5 + 1.0D, var25, var11, var13);
      var9.addVertexWithUV(var21, var5 + 0.0D, var25, var11, var17);
      var9.addVertexWithUV(var19, var5 + 0.0D, var25, var15, var17);
      var9.addVertexWithUV(var19, var5 + 1.0D, var25, var15, var13);
      var9.addVertexWithUV(var19, var5 + 1.0D, var25, var11, var13);
      var9.addVertexWithUV(var19, var5 + 0.0D, var25, var11, var17);
      var9.addVertexWithUV(var21, var5 + 0.0D, var25, var15, var17);
      var9.addVertexWithUV(var21, var5 + 1.0D, var25, var15, var13);
   }

   public boolean renderBlockLiquid(Block var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      int var6 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var7 = (float)(var6 >> 16 & 255) / 255.0F;
      float var8 = (float)(var6 >> 8 & 255) / 255.0F;
      float var9 = (float)(var6 & 255) / 255.0F;
      boolean var10 = var1.shouldSideBeRendered(this.blockAccess, var2, var3 + 1, var4, 1);
      boolean var11 = var1.shouldSideBeRendered(this.blockAccess, var2, var3 - 1, var4, 0);
      boolean[] var12 = new boolean[]{var1.shouldSideBeRendered(this.blockAccess, var2, var3, var4 - 1, 2), var1.shouldSideBeRendered(this.blockAccess, var2, var3, var4 + 1, 3), var1.shouldSideBeRendered(this.blockAccess, var2 - 1, var3, var4, 4), var1.shouldSideBeRendered(this.blockAccess, var2 + 1, var3, var4, 5)};
      if(!var10 && !var11 && !var12[0] && !var12[1] && !var12[2] && !var12[3]) {
         return false;
      } else {
         boolean var13 = false;
         float var14 = 0.5F;
         float var15 = 1.0F;
         float var16 = 0.8F;
         float var17 = 0.6F;
         double var18 = 0.0D;
         double var20 = 1.0D;
         Material var22 = var1.getMaterial();
         int var23 = this.blockAccess.getBlockMetadata(var2, var3, var4);
         double var24 = (double)this.getLiquidHeight(var2, var3, var4, var22);
         double var26 = (double)this.getLiquidHeight(var2, var3, var4 + 1, var22);
         double var28 = (double)this.getLiquidHeight(var2 + 1, var3, var4 + 1, var22);
         double var30 = (double)this.getLiquidHeight(var2 + 1, var3, var4, var22);
         double var32 = 0.0010000000474974513D;
         float var52;
         float var53;
         float var54;
         if(this.renderAllFaces || var10) {
            var13 = true;
            IIcon var34 = this.getBlockIconFromSideAndMetadata(var1, 1, var23);
            float var35 = (float)BlockLiquid.getFlowDirection(this.blockAccess, var2, var3, var4, var22);
            if(var35 > -999.0F) {
               var34 = this.getBlockIconFromSideAndMetadata(var1, 2, var23);
            }

            var24 -= var32;
            var26 -= var32;
            var28 -= var32;
            var30 -= var32;
            double var36;
            double var38;
            double var40;
            double var42;
            double var44;
            double var46;
            double var48;
            double var50;
            if(var35 < -999.0F) {
               var36 = (double)var34.getInterpolatedU(0.0D);
               var44 = (double)var34.getInterpolatedV(0.0D);
               var38 = var36;
               var46 = (double)var34.getInterpolatedV(16.0D);
               var40 = (double)var34.getInterpolatedU(16.0D);
               var48 = var46;
               var42 = var40;
               var50 = var44;
            } else {
               var52 = MathHelper.sin(var35) * 0.25F;
               var53 = MathHelper.cos(var35) * 0.25F;
               var54 = 8.0F;
               var36 = (double)var34.getInterpolatedU((double)(8.0F + (-var53 - var52) * 16.0F));
               var44 = (double)var34.getInterpolatedV((double)(8.0F + (-var53 + var52) * 16.0F));
               var38 = (double)var34.getInterpolatedU((double)(8.0F + (-var53 + var52) * 16.0F));
               var46 = (double)var34.getInterpolatedV((double)(8.0F + (var53 + var52) * 16.0F));
               var40 = (double)var34.getInterpolatedU((double)(8.0F + (var53 + var52) * 16.0F));
               var48 = (double)var34.getInterpolatedV((double)(8.0F + (var53 - var52) * 16.0F));
               var42 = (double)var34.getInterpolatedU((double)(8.0F + (var53 - var52) * 16.0F));
               var50 = (double)var34.getInterpolatedV((double)(8.0F + (-var53 - var52) * 16.0F));
            }

            var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
            var5.setColorOpaque_F(var15 * var7, var15 * var8, var15 * var9);
            var5.addVertexWithUV((double)(var2 + 0), (double)var3 + var24, (double)(var4 + 0), var36, var44);
            var5.addVertexWithUV((double)(var2 + 0), (double)var3 + var26, (double)(var4 + 1), var38, var46);
            var5.addVertexWithUV((double)(var2 + 1), (double)var3 + var28, (double)(var4 + 1), var40, var48);
            var5.addVertexWithUV((double)(var2 + 1), (double)var3 + var30, (double)(var4 + 0), var42, var50);
            var5.addVertexWithUV((double)(var2 + 0), (double)var3 + var24, (double)(var4 + 0), var36, var44);
            var5.addVertexWithUV((double)(var2 + 1), (double)var3 + var30, (double)(var4 + 0), var42, var50);
            var5.addVertexWithUV((double)(var2 + 1), (double)var3 + var28, (double)(var4 + 1), var40, var48);
            var5.addVertexWithUV((double)(var2 + 0), (double)var3 + var26, (double)(var4 + 1), var38, var46);
         }

         if(this.renderAllFaces || var11) {
            var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4));
            var5.setColorOpaque_F(var14, var14, var14);
            this.renderFaceYNeg(var1, (double)var2, (double)var3 + var32, (double)var4, this.getBlockIconFromSide(var1, 0));
            var13 = true;
         }

         for(int var57 = 0; var57 < 4; ++var57) {
            int var58 = var2;
            int var37 = var4;
            if(var57 == 0) {
               var37 = var4 - 1;
            }

            if(var57 == 1) {
               ++var37;
            }

            if(var57 == 2) {
               var58 = var2 - 1;
            }

            if(var57 == 3) {
               ++var58;
            }

            IIcon var59 = this.getBlockIconFromSideAndMetadata(var1, var57 + 2, var23);
            if(this.renderAllFaces || var12[var57]) {
               double var39;
               double var41;
               double var43;
               double var45;
               double var47;
               double var49;
               if(var57 == 0) {
                  var39 = var24;
                  var41 = var30;
                  var43 = (double)var2;
                  var47 = (double)(var2 + 1);
                  var45 = (double)var4 + var32;
                  var49 = (double)var4 + var32;
               } else if(var57 == 1) {
                  var39 = var28;
                  var41 = var26;
                  var43 = (double)(var2 + 1);
                  var47 = (double)var2;
                  var45 = (double)(var4 + 1) - var32;
                  var49 = (double)(var4 + 1) - var32;
               } else if(var57 == 2) {
                  var39 = var26;
                  var41 = var24;
                  var43 = (double)var2 + var32;
                  var47 = (double)var2 + var32;
                  var45 = (double)(var4 + 1);
                  var49 = (double)var4;
               } else {
                  var39 = var30;
                  var41 = var28;
                  var43 = (double)(var2 + 1) - var32;
                  var47 = (double)(var2 + 1) - var32;
                  var45 = (double)var4;
                  var49 = (double)(var4 + 1);
               }

               var13 = true;
               float var51 = var59.getInterpolatedU(0.0D);
               var52 = var59.getInterpolatedU(8.0D);
               var53 = var59.getInterpolatedV((1.0D - var39) * 16.0D * 0.5D);
               var54 = var59.getInterpolatedV((1.0D - var41) * 16.0D * 0.5D);
               float var55 = var59.getInterpolatedV(8.0D);
               var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var58, var3, var37));
               float var56 = 1.0F;
               var56 *= var57 < 2?var16:var17;
               var5.setColorOpaque_F(var15 * var56 * var7, var15 * var56 * var8, var15 * var56 * var9);
               var5.addVertexWithUV(var43, (double)var3 + var39, var45, (double)var51, (double)var53);
               var5.addVertexWithUV(var47, (double)var3 + var41, var49, (double)var52, (double)var54);
               var5.addVertexWithUV(var47, (double)(var3 + 0), var49, (double)var52, (double)var55);
               var5.addVertexWithUV(var43, (double)(var3 + 0), var45, (double)var51, (double)var55);
               var5.addVertexWithUV(var43, (double)(var3 + 0), var45, (double)var51, (double)var55);
               var5.addVertexWithUV(var47, (double)(var3 + 0), var49, (double)var52, (double)var55);
               var5.addVertexWithUV(var47, (double)var3 + var41, var49, (double)var52, (double)var54);
               var5.addVertexWithUV(var43, (double)var3 + var39, var45, (double)var51, (double)var53);
            }
         }

         this.renderMinY = var18;
         this.renderMaxY = var20;
         return var13;
      }
   }

   private float getLiquidHeight(int var1, int var2, int var3, Material var4) {
      int var5 = 0;
      float var6 = 0.0F;

      for(int var7 = 0; var7 < 4; ++var7) {
         int var8 = var1 - (var7 & 1);
         int var10 = var3 - (var7 >> 1 & 1);
         if(this.blockAccess.getBlock(var8, var2 + 1, var10).getMaterial() == var4) {
            return 1.0F;
         }

         Material var11 = this.blockAccess.getBlock(var8, var2, var10).getMaterial();
         if(var11 == var4) {
            int var12 = this.blockAccess.getBlockMetadata(var8, var2, var10);
            if(var12 >= 8 || var12 == 0) {
               var6 += BlockLiquid.getLiquidHeightPercent(var12) * 10.0F;
               var5 += 10;
            }

            var6 += BlockLiquid.getLiquidHeightPercent(var12);
            ++var5;
         } else if(!var11.isSolid()) {
            ++var6;
            ++var5;
         }
      }

      return 1.0F - var6 / (float)var5;
   }

   public void renderBlockSandFalling(Block var1, World var2, int var3, int var4, int var5, int var6) {
      float var7 = 0.5F;
      float var8 = 1.0F;
      float var9 = 0.8F;
      float var10 = 0.6F;
      Tessellator var11 = Tessellator.instance;
      var11.startDrawingQuads();
      var11.setBrightness(var1.getMixedBrightnessForBlock(var2, var3, var4, var5));
      var11.setColorOpaque_F(var7, var7, var7);
      this.renderFaceYNeg(var1, -0.5D, -0.5D, -0.5D, this.getBlockIconFromSideAndMetadata(var1, 0, var6));
      var11.setColorOpaque_F(var8, var8, var8);
      this.renderFaceYPos(var1, -0.5D, -0.5D, -0.5D, this.getBlockIconFromSideAndMetadata(var1, 1, var6));
      var11.setColorOpaque_F(var9, var9, var9);
      this.renderFaceZNeg(var1, -0.5D, -0.5D, -0.5D, this.getBlockIconFromSideAndMetadata(var1, 2, var6));
      var11.setColorOpaque_F(var9, var9, var9);
      this.renderFaceZPos(var1, -0.5D, -0.5D, -0.5D, this.getBlockIconFromSideAndMetadata(var1, 3, var6));
      var11.setColorOpaque_F(var10, var10, var10);
      this.renderFaceXNeg(var1, -0.5D, -0.5D, -0.5D, this.getBlockIconFromSideAndMetadata(var1, 4, var6));
      var11.setColorOpaque_F(var10, var10, var10);
      this.renderFaceXPos(var1, -0.5D, -0.5D, -0.5D, this.getBlockIconFromSideAndMetadata(var1, 5, var6));
      var11.draw();
   }

   public boolean renderStandardBlock(Block var1, int var2, int var3, int var4) {
      int var5 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var6 = (float)(var5 >> 16 & 255) / 255.0F;
      float var7 = (float)(var5 >> 8 & 255) / 255.0F;
      float var8 = (float)(var5 & 255) / 255.0F;
      if(EntityRenderer.anaglyphEnable) {
         float var9 = (var6 * 30.0F + var7 * 59.0F + var8 * 11.0F) / 100.0F;
         float var10 = (var6 * 30.0F + var7 * 70.0F) / 100.0F;
         float var11 = (var6 * 30.0F + var8 * 70.0F) / 100.0F;
         var6 = var9;
         var7 = var10;
         var8 = var11;
      }

      return Minecraft.isAmbientOcclusionEnabled() && var1.getLightValue() == 0?(this.partialRenderBounds?this.renderStandardBlockWithAmbientOcclusionPartial(var1, var2, var3, var4, var6, var7, var8):this.renderStandardBlockWithAmbientOcclusion(var1, var2, var3, var4, var6, var7, var8)):this.renderStandardBlockWithColorMultiplier(var1, var2, var3, var4, var6, var7, var8);
   }

   public boolean renderBlockLog(Block var1, int var2, int var3, int var4) {
      int var5 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      int var6 = var5 & 12;
      if(var6 == 4) {
         this.uvRotateEast = 1;
         this.uvRotateWest = 1;
         this.uvRotateTop = 1;
         this.uvRotateBottom = 1;
      } else if(var6 == 8) {
         this.uvRotateSouth = 1;
         this.uvRotateNorth = 1;
      }

      boolean var7 = this.renderStandardBlock(var1, var2, var3, var4);
      this.uvRotateSouth = 0;
      this.uvRotateEast = 0;
      this.uvRotateWest = 0;
      this.uvRotateNorth = 0;
      this.uvRotateTop = 0;
      this.uvRotateBottom = 0;
      return var7;
   }

   public boolean renderBlockQuartz(Block var1, int var2, int var3, int var4) {
      int var5 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      if(var5 == 3) {
         this.uvRotateEast = 1;
         this.uvRotateWest = 1;
         this.uvRotateTop = 1;
         this.uvRotateBottom = 1;
      } else if(var5 == 4) {
         this.uvRotateSouth = 1;
         this.uvRotateNorth = 1;
      }

      boolean var6 = this.renderStandardBlock(var1, var2, var3, var4);
      this.uvRotateSouth = 0;
      this.uvRotateEast = 0;
      this.uvRotateWest = 0;
      this.uvRotateNorth = 0;
      this.uvRotateTop = 0;
      this.uvRotateBottom = 0;
      return var6;
   }

   public boolean renderStandardBlockWithAmbientOcclusion(Block var1, int var2, int var3, int var4, float var5, float var6, float var7) {
      this.enableAO = true;
      boolean var8 = false;
      float var9 = 0.0F;
      float var10 = 0.0F;
      float var11 = 0.0F;
      float var12 = 0.0F;
      boolean var13 = true;
      int var14 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4);
      Tessellator var15 = Tessellator.instance;
      var15.setBrightness(983055);
      if(this.getBlockIcon(var1).getIconName().equals("grass_top")) {
         var13 = false;
      } else if(this.hasOverrideBlockTexture()) {
         var13 = false;
      }

      boolean var16;
      boolean var17;
      boolean var18;
      boolean var19;
      int var20;
      float var21;
      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3 - 1, var4, 0)) {
         if(this.renderMinY <= 0.0D) {
            --var3;
         }

         this.aoBrightnessXYNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4);
         this.aoBrightnessYZNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1);
         this.aoBrightnessYZNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1);
         this.aoBrightnessXYPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4);
         this.aoLightValueScratchXYNN = this.blockAccess.getBlock(var2 - 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZNN = this.blockAccess.getBlock(var2, var3, var4 - 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZNP = this.blockAccess.getBlock(var2, var3, var4 + 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXYPN = this.blockAccess.getBlock(var2 + 1, var3, var4).getAmbientOcclusionLightValue();
         var16 = this.blockAccess.getBlock(var2 + 1, var3 - 1, var4).getCanBlockGrass();
         var17 = this.blockAccess.getBlock(var2 - 1, var3 - 1, var4).getCanBlockGrass();
         var18 = this.blockAccess.getBlock(var2, var3 - 1, var4 + 1).getCanBlockGrass();
         var19 = this.blockAccess.getBlock(var2, var3 - 1, var4 - 1).getCanBlockGrass();
         if(!var19 && !var17) {
            this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXYNN;
            this.aoBrightnessXYZNNN = this.aoBrightnessXYNN;
         } else {
            this.aoLightValueScratchXYZNNN = this.blockAccess.getBlock(var2 - 1, var3, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4 - 1);
         }

         if(!var18 && !var17) {
            this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXYNN;
            this.aoBrightnessXYZNNP = this.aoBrightnessXYNN;
         } else {
            this.aoLightValueScratchXYZNNP = this.blockAccess.getBlock(var2 - 1, var3, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4 + 1);
         }

         if(!var19 && !var16) {
            this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXYPN;
            this.aoBrightnessXYZPNN = this.aoBrightnessXYPN;
         } else {
            this.aoLightValueScratchXYZPNN = this.blockAccess.getBlock(var2 + 1, var3, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4 - 1);
         }

         if(!var18 && !var16) {
            this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXYPN;
            this.aoBrightnessXYZPNP = this.aoBrightnessXYPN;
         } else {
            this.aoLightValueScratchXYZPNP = this.blockAccess.getBlock(var2 + 1, var3, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4 + 1);
         }

         if(this.renderMinY <= 0.0D) {
            ++var3;
         }

         var20 = var14;
         if(this.renderMinY <= 0.0D || !this.blockAccess.getBlock(var2, var3 - 1, var4).isOpaqueCube()) {
            var20 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4);
         }

         var21 = this.blockAccess.getBlock(var2, var3 - 1, var4).getAmbientOcclusionLightValue();
         var9 = (this.aoLightValueScratchXYZNNP + this.aoLightValueScratchXYNN + this.aoLightValueScratchYZNP + var21) / 4.0F;
         var12 = (this.aoLightValueScratchYZNP + var21 + this.aoLightValueScratchXYZPNP + this.aoLightValueScratchXYPN) / 4.0F;
         var11 = (var21 + this.aoLightValueScratchYZNN + this.aoLightValueScratchXYPN + this.aoLightValueScratchXYZPNN) / 4.0F;
         var10 = (this.aoLightValueScratchXYNN + this.aoLightValueScratchXYZNNN + var21 + this.aoLightValueScratchYZNN) / 4.0F;
         this.brightnessTopLeft = this.getAoBrightness(this.aoBrightnessXYZNNP, this.aoBrightnessXYNN, this.aoBrightnessYZNP, var20);
         this.brightnessTopRight = this.getAoBrightness(this.aoBrightnessYZNP, this.aoBrightnessXYZPNP, this.aoBrightnessXYPN, var20);
         this.brightnessBottomRight = this.getAoBrightness(this.aoBrightnessYZNN, this.aoBrightnessXYPN, this.aoBrightnessXYZPNN, var20);
         this.brightnessBottomLeft = this.getAoBrightness(this.aoBrightnessXYNN, this.aoBrightnessXYZNNN, this.aoBrightnessYZNN, var20);
         if(var13) {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = var5 * 0.5F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = var6 * 0.5F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = var7 * 0.5F;
         } else {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.5F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.5F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.5F;
         }

         this.colorRedTopLeft *= var9;
         this.colorGreenTopLeft *= var9;
         this.colorBlueTopLeft *= var9;
         this.colorRedBottomLeft *= var10;
         this.colorGreenBottomLeft *= var10;
         this.colorBlueBottomLeft *= var10;
         this.colorRedBottomRight *= var11;
         this.colorGreenBottomRight *= var11;
         this.colorBlueBottomRight *= var11;
         this.colorRedTopRight *= var12;
         this.colorGreenTopRight *= var12;
         this.colorBlueTopRight *= var12;
         this.renderFaceYNeg(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 0));
         var8 = true;
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3 + 1, var4, 1)) {
         if(this.renderMaxY >= 1.0D) {
            ++var3;
         }

         this.aoBrightnessXYNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4);
         this.aoBrightnessXYPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4);
         this.aoBrightnessYZPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1);
         this.aoBrightnessYZPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1);
         this.aoLightValueScratchXYNP = this.blockAccess.getBlock(var2 - 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXYPP = this.blockAccess.getBlock(var2 + 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZPN = this.blockAccess.getBlock(var2, var3, var4 - 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZPP = this.blockAccess.getBlock(var2, var3, var4 + 1).getAmbientOcclusionLightValue();
         var16 = this.blockAccess.getBlock(var2 + 1, var3 + 1, var4).getCanBlockGrass();
         var17 = this.blockAccess.getBlock(var2 - 1, var3 + 1, var4).getCanBlockGrass();
         var18 = this.blockAccess.getBlock(var2, var3 + 1, var4 + 1).getCanBlockGrass();
         var19 = this.blockAccess.getBlock(var2, var3 + 1, var4 - 1).getCanBlockGrass();
         if(!var19 && !var17) {
            this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXYNP;
            this.aoBrightnessXYZNPN = this.aoBrightnessXYNP;
         } else {
            this.aoLightValueScratchXYZNPN = this.blockAccess.getBlock(var2 - 1, var3, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4 - 1);
         }

         if(!var19 && !var16) {
            this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXYPP;
            this.aoBrightnessXYZPPN = this.aoBrightnessXYPP;
         } else {
            this.aoLightValueScratchXYZPPN = this.blockAccess.getBlock(var2 + 1, var3, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4 - 1);
         }

         if(!var18 && !var17) {
            this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXYNP;
            this.aoBrightnessXYZNPP = this.aoBrightnessXYNP;
         } else {
            this.aoLightValueScratchXYZNPP = this.blockAccess.getBlock(var2 - 1, var3, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4 + 1);
         }

         if(!var18 && !var16) {
            this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXYPP;
            this.aoBrightnessXYZPPP = this.aoBrightnessXYPP;
         } else {
            this.aoLightValueScratchXYZPPP = this.blockAccess.getBlock(var2 + 1, var3, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4 + 1);
         }

         if(this.renderMaxY >= 1.0D) {
            --var3;
         }

         var20 = var14;
         if(this.renderMaxY >= 1.0D || !this.blockAccess.getBlock(var2, var3 + 1, var4).isOpaqueCube()) {
            var20 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4);
         }

         var21 = this.blockAccess.getBlock(var2, var3 + 1, var4).getAmbientOcclusionLightValue();
         var12 = (this.aoLightValueScratchXYZNPP + this.aoLightValueScratchXYNP + this.aoLightValueScratchYZPP + var21) / 4.0F;
         var9 = (this.aoLightValueScratchYZPP + var21 + this.aoLightValueScratchXYZPPP + this.aoLightValueScratchXYPP) / 4.0F;
         var10 = (var21 + this.aoLightValueScratchYZPN + this.aoLightValueScratchXYPP + this.aoLightValueScratchXYZPPN) / 4.0F;
         var11 = (this.aoLightValueScratchXYNP + this.aoLightValueScratchXYZNPN + var21 + this.aoLightValueScratchYZPN) / 4.0F;
         this.brightnessTopRight = this.getAoBrightness(this.aoBrightnessXYZNPP, this.aoBrightnessXYNP, this.aoBrightnessYZPP, var20);
         this.brightnessTopLeft = this.getAoBrightness(this.aoBrightnessYZPP, this.aoBrightnessXYZPPP, this.aoBrightnessXYPP, var20);
         this.brightnessBottomLeft = this.getAoBrightness(this.aoBrightnessYZPN, this.aoBrightnessXYPP, this.aoBrightnessXYZPPN, var20);
         this.brightnessBottomRight = this.getAoBrightness(this.aoBrightnessXYNP, this.aoBrightnessXYZNPN, this.aoBrightnessYZPN, var20);
         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = var5;
         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = var6;
         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = var7;
         this.colorRedTopLeft *= var9;
         this.colorGreenTopLeft *= var9;
         this.colorBlueTopLeft *= var9;
         this.colorRedBottomLeft *= var10;
         this.colorGreenBottomLeft *= var10;
         this.colorBlueBottomLeft *= var10;
         this.colorRedBottomRight *= var11;
         this.colorGreenBottomRight *= var11;
         this.colorBlueBottomRight *= var11;
         this.colorRedTopRight *= var12;
         this.colorGreenTopRight *= var12;
         this.colorBlueTopRight *= var12;
         this.renderFaceYPos(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 1));
         var8 = true;
      }

      IIcon var22;
      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3, var4 - 1, 2)) {
         if(this.renderMinZ <= 0.0D) {
            --var4;
         }

         this.aoLightValueScratchXZNN = this.blockAccess.getBlock(var2 - 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZNN = this.blockAccess.getBlock(var2, var3 - 1, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZPN = this.blockAccess.getBlock(var2, var3 + 1, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXZPN = this.blockAccess.getBlock(var2 + 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoBrightnessXZNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4);
         this.aoBrightnessYZNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4);
         this.aoBrightnessYZPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4);
         this.aoBrightnessXZPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4);
         var16 = this.blockAccess.getBlock(var2 + 1, var3, var4 - 1).getCanBlockGrass();
         var17 = this.blockAccess.getBlock(var2 - 1, var3, var4 - 1).getCanBlockGrass();
         var18 = this.blockAccess.getBlock(var2, var3 + 1, var4 - 1).getCanBlockGrass();
         var19 = this.blockAccess.getBlock(var2, var3 - 1, var4 - 1).getCanBlockGrass();
         if(!var17 && !var19) {
            this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXZNN;
            this.aoBrightnessXYZNNN = this.aoBrightnessXZNN;
         } else {
            this.aoLightValueScratchXYZNNN = this.blockAccess.getBlock(var2 - 1, var3 - 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3 - 1, var4);
         }

         if(!var17 && !var18) {
            this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXZNN;
            this.aoBrightnessXYZNPN = this.aoBrightnessXZNN;
         } else {
            this.aoLightValueScratchXYZNPN = this.blockAccess.getBlock(var2 - 1, var3 + 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3 + 1, var4);
         }

         if(!var16 && !var19) {
            this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXZPN;
            this.aoBrightnessXYZPNN = this.aoBrightnessXZPN;
         } else {
            this.aoLightValueScratchXYZPNN = this.blockAccess.getBlock(var2 + 1, var3 - 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3 - 1, var4);
         }

         if(!var16 && !var18) {
            this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXZPN;
            this.aoBrightnessXYZPPN = this.aoBrightnessXZPN;
         } else {
            this.aoLightValueScratchXYZPPN = this.blockAccess.getBlock(var2 + 1, var3 + 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3 + 1, var4);
         }

         if(this.renderMinZ <= 0.0D) {
            ++var4;
         }

         var20 = var14;
         if(this.renderMinZ <= 0.0D || !this.blockAccess.getBlock(var2, var3, var4 - 1).isOpaqueCube()) {
            var20 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1);
         }

         var21 = this.blockAccess.getBlock(var2, var3, var4 - 1).getAmbientOcclusionLightValue();
         var9 = (this.aoLightValueScratchXZNN + this.aoLightValueScratchXYZNPN + var21 + this.aoLightValueScratchYZPN) / 4.0F;
         var10 = (var21 + this.aoLightValueScratchYZPN + this.aoLightValueScratchXZPN + this.aoLightValueScratchXYZPPN) / 4.0F;
         var11 = (this.aoLightValueScratchYZNN + var21 + this.aoLightValueScratchXYZPNN + this.aoLightValueScratchXZPN) / 4.0F;
         var12 = (this.aoLightValueScratchXYZNNN + this.aoLightValueScratchXZNN + this.aoLightValueScratchYZNN + var21) / 4.0F;
         this.brightnessTopLeft = this.getAoBrightness(this.aoBrightnessXZNN, this.aoBrightnessXYZNPN, this.aoBrightnessYZPN, var20);
         this.brightnessBottomLeft = this.getAoBrightness(this.aoBrightnessYZPN, this.aoBrightnessXZPN, this.aoBrightnessXYZPPN, var20);
         this.brightnessBottomRight = this.getAoBrightness(this.aoBrightnessYZNN, this.aoBrightnessXYZPNN, this.aoBrightnessXZPN, var20);
         this.brightnessTopRight = this.getAoBrightness(this.aoBrightnessXYZNNN, this.aoBrightnessXZNN, this.aoBrightnessYZNN, var20);
         if(var13) {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = var5 * 0.8F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = var6 * 0.8F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = var7 * 0.8F;
         } else {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.8F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.8F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.8F;
         }

         this.colorRedTopLeft *= var9;
         this.colorGreenTopLeft *= var9;
         this.colorBlueTopLeft *= var9;
         this.colorRedBottomLeft *= var10;
         this.colorGreenBottomLeft *= var10;
         this.colorBlueBottomLeft *= var10;
         this.colorRedBottomRight *= var11;
         this.colorGreenBottomRight *= var11;
         this.colorBlueBottomRight *= var11;
         this.colorRedTopRight *= var12;
         this.colorGreenTopRight *= var12;
         this.colorBlueTopRight *= var12;
         var22 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 2);
         this.renderFaceZNeg(var1, (double)var2, (double)var3, (double)var4, var22);
         if(fancyGrass && var22.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
            this.colorRedTopLeft *= var5;
            this.colorRedBottomLeft *= var5;
            this.colorRedBottomRight *= var5;
            this.colorRedTopRight *= var5;
            this.colorGreenTopLeft *= var6;
            this.colorGreenBottomLeft *= var6;
            this.colorGreenBottomRight *= var6;
            this.colorGreenTopRight *= var6;
            this.colorBlueTopLeft *= var7;
            this.colorBlueBottomLeft *= var7;
            this.colorBlueBottomRight *= var7;
            this.colorBlueTopRight *= var7;
            this.renderFaceZNeg(var1, (double)var2, (double)var3, (double)var4, BlockGrass.getIconSideOverlay());
         }

         var8 = true;
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3, var4 + 1, 3)) {
         if(this.renderMaxZ >= 1.0D) {
            ++var4;
         }

         this.aoLightValueScratchXZNP = this.blockAccess.getBlock(var2 - 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXZPP = this.blockAccess.getBlock(var2 + 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZNP = this.blockAccess.getBlock(var2, var3 - 1, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZPP = this.blockAccess.getBlock(var2, var3 + 1, var4).getAmbientOcclusionLightValue();
         this.aoBrightnessXZNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4);
         this.aoBrightnessXZPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4);
         this.aoBrightnessYZNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4);
         this.aoBrightnessYZPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4);
         var16 = this.blockAccess.getBlock(var2 + 1, var3, var4 + 1).getCanBlockGrass();
         var17 = this.blockAccess.getBlock(var2 - 1, var3, var4 + 1).getCanBlockGrass();
         var18 = this.blockAccess.getBlock(var2, var3 + 1, var4 + 1).getCanBlockGrass();
         var19 = this.blockAccess.getBlock(var2, var3 - 1, var4 + 1).getCanBlockGrass();
         if(!var17 && !var19) {
            this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXZNP;
            this.aoBrightnessXYZNNP = this.aoBrightnessXZNP;
         } else {
            this.aoLightValueScratchXYZNNP = this.blockAccess.getBlock(var2 - 1, var3 - 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3 - 1, var4);
         }

         if(!var17 && !var18) {
            this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXZNP;
            this.aoBrightnessXYZNPP = this.aoBrightnessXZNP;
         } else {
            this.aoLightValueScratchXYZNPP = this.blockAccess.getBlock(var2 - 1, var3 + 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3 + 1, var4);
         }

         if(!var16 && !var19) {
            this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXZPP;
            this.aoBrightnessXYZPNP = this.aoBrightnessXZPP;
         } else {
            this.aoLightValueScratchXYZPNP = this.blockAccess.getBlock(var2 + 1, var3 - 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3 - 1, var4);
         }

         if(!var16 && !var18) {
            this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXZPP;
            this.aoBrightnessXYZPPP = this.aoBrightnessXZPP;
         } else {
            this.aoLightValueScratchXYZPPP = this.blockAccess.getBlock(var2 + 1, var3 + 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3 + 1, var4);
         }

         if(this.renderMaxZ >= 1.0D) {
            --var4;
         }

         var20 = var14;
         if(this.renderMaxZ >= 1.0D || !this.blockAccess.getBlock(var2, var3, var4 + 1).isOpaqueCube()) {
            var20 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1);
         }

         var21 = this.blockAccess.getBlock(var2, var3, var4 + 1).getAmbientOcclusionLightValue();
         var9 = (this.aoLightValueScratchXZNP + this.aoLightValueScratchXYZNPP + var21 + this.aoLightValueScratchYZPP) / 4.0F;
         var12 = (var21 + this.aoLightValueScratchYZPP + this.aoLightValueScratchXZPP + this.aoLightValueScratchXYZPPP) / 4.0F;
         var11 = (this.aoLightValueScratchYZNP + var21 + this.aoLightValueScratchXYZPNP + this.aoLightValueScratchXZPP) / 4.0F;
         var10 = (this.aoLightValueScratchXYZNNP + this.aoLightValueScratchXZNP + this.aoLightValueScratchYZNP + var21) / 4.0F;
         this.brightnessTopLeft = this.getAoBrightness(this.aoBrightnessXZNP, this.aoBrightnessXYZNPP, this.aoBrightnessYZPP, var20);
         this.brightnessTopRight = this.getAoBrightness(this.aoBrightnessYZPP, this.aoBrightnessXZPP, this.aoBrightnessXYZPPP, var20);
         this.brightnessBottomRight = this.getAoBrightness(this.aoBrightnessYZNP, this.aoBrightnessXYZPNP, this.aoBrightnessXZPP, var20);
         this.brightnessBottomLeft = this.getAoBrightness(this.aoBrightnessXYZNNP, this.aoBrightnessXZNP, this.aoBrightnessYZNP, var20);
         if(var13) {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = var5 * 0.8F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = var6 * 0.8F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = var7 * 0.8F;
         } else {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.8F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.8F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.8F;
         }

         this.colorRedTopLeft *= var9;
         this.colorGreenTopLeft *= var9;
         this.colorBlueTopLeft *= var9;
         this.colorRedBottomLeft *= var10;
         this.colorGreenBottomLeft *= var10;
         this.colorBlueBottomLeft *= var10;
         this.colorRedBottomRight *= var11;
         this.colorGreenBottomRight *= var11;
         this.colorBlueBottomRight *= var11;
         this.colorRedTopRight *= var12;
         this.colorGreenTopRight *= var12;
         this.colorBlueTopRight *= var12;
         var22 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 3);
         this.renderFaceZPos(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 3));
         if(fancyGrass && var22.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
            this.colorRedTopLeft *= var5;
            this.colorRedBottomLeft *= var5;
            this.colorRedBottomRight *= var5;
            this.colorRedTopRight *= var5;
            this.colorGreenTopLeft *= var6;
            this.colorGreenBottomLeft *= var6;
            this.colorGreenBottomRight *= var6;
            this.colorGreenTopRight *= var6;
            this.colorBlueTopLeft *= var7;
            this.colorBlueBottomLeft *= var7;
            this.colorBlueBottomRight *= var7;
            this.colorBlueTopRight *= var7;
            this.renderFaceZPos(var1, (double)var2, (double)var3, (double)var4, BlockGrass.getIconSideOverlay());
         }

         var8 = true;
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2 - 1, var3, var4, 4)) {
         if(this.renderMinX <= 0.0D) {
            --var2;
         }

         this.aoLightValueScratchXYNN = this.blockAccess.getBlock(var2, var3 - 1, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXZNN = this.blockAccess.getBlock(var2, var3, var4 - 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXZNP = this.blockAccess.getBlock(var2, var3, var4 + 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXYNP = this.blockAccess.getBlock(var2, var3 + 1, var4).getAmbientOcclusionLightValue();
         this.aoBrightnessXYNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4);
         this.aoBrightnessXZNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1);
         this.aoBrightnessXZNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1);
         this.aoBrightnessXYNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4);
         var16 = this.blockAccess.getBlock(var2 - 1, var3 + 1, var4).getCanBlockGrass();
         var17 = this.blockAccess.getBlock(var2 - 1, var3 - 1, var4).getCanBlockGrass();
         var18 = this.blockAccess.getBlock(var2 - 1, var3, var4 - 1).getCanBlockGrass();
         var19 = this.blockAccess.getBlock(var2 - 1, var3, var4 + 1).getCanBlockGrass();
         if(!var18 && !var17) {
            this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXZNN;
            this.aoBrightnessXYZNNN = this.aoBrightnessXZNN;
         } else {
            this.aoLightValueScratchXYZNNN = this.blockAccess.getBlock(var2, var3 - 1, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4 - 1);
         }

         if(!var19 && !var17) {
            this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXZNP;
            this.aoBrightnessXYZNNP = this.aoBrightnessXZNP;
         } else {
            this.aoLightValueScratchXYZNNP = this.blockAccess.getBlock(var2, var3 - 1, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4 + 1);
         }

         if(!var18 && !var16) {
            this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXZNN;
            this.aoBrightnessXYZNPN = this.aoBrightnessXZNN;
         } else {
            this.aoLightValueScratchXYZNPN = this.blockAccess.getBlock(var2, var3 + 1, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4 - 1);
         }

         if(!var19 && !var16) {
            this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXZNP;
            this.aoBrightnessXYZNPP = this.aoBrightnessXZNP;
         } else {
            this.aoLightValueScratchXYZNPP = this.blockAccess.getBlock(var2, var3 + 1, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4 + 1);
         }

         if(this.renderMinX <= 0.0D) {
            ++var2;
         }

         var20 = var14;
         if(this.renderMinX <= 0.0D || !this.blockAccess.getBlock(var2 - 1, var3, var4).isOpaqueCube()) {
            var20 = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4);
         }

         var21 = this.blockAccess.getBlock(var2 - 1, var3, var4).getAmbientOcclusionLightValue();
         var12 = (this.aoLightValueScratchXYNN + this.aoLightValueScratchXYZNNP + var21 + this.aoLightValueScratchXZNP) / 4.0F;
         var9 = (var21 + this.aoLightValueScratchXZNP + this.aoLightValueScratchXYNP + this.aoLightValueScratchXYZNPP) / 4.0F;
         var10 = (this.aoLightValueScratchXZNN + var21 + this.aoLightValueScratchXYZNPN + this.aoLightValueScratchXYNP) / 4.0F;
         var11 = (this.aoLightValueScratchXYZNNN + this.aoLightValueScratchXYNN + this.aoLightValueScratchXZNN + var21) / 4.0F;
         this.brightnessTopRight = this.getAoBrightness(this.aoBrightnessXYNN, this.aoBrightnessXYZNNP, this.aoBrightnessXZNP, var20);
         this.brightnessTopLeft = this.getAoBrightness(this.aoBrightnessXZNP, this.aoBrightnessXYNP, this.aoBrightnessXYZNPP, var20);
         this.brightnessBottomLeft = this.getAoBrightness(this.aoBrightnessXZNN, this.aoBrightnessXYZNPN, this.aoBrightnessXYNP, var20);
         this.brightnessBottomRight = this.getAoBrightness(this.aoBrightnessXYZNNN, this.aoBrightnessXYNN, this.aoBrightnessXZNN, var20);
         if(var13) {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = var5 * 0.6F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = var6 * 0.6F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = var7 * 0.6F;
         } else {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.6F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.6F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.6F;
         }

         this.colorRedTopLeft *= var9;
         this.colorGreenTopLeft *= var9;
         this.colorBlueTopLeft *= var9;
         this.colorRedBottomLeft *= var10;
         this.colorGreenBottomLeft *= var10;
         this.colorBlueBottomLeft *= var10;
         this.colorRedBottomRight *= var11;
         this.colorGreenBottomRight *= var11;
         this.colorBlueBottomRight *= var11;
         this.colorRedTopRight *= var12;
         this.colorGreenTopRight *= var12;
         this.colorBlueTopRight *= var12;
         var22 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 4);
         this.renderFaceXNeg(var1, (double)var2, (double)var3, (double)var4, var22);
         if(fancyGrass && var22.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
            this.colorRedTopLeft *= var5;
            this.colorRedBottomLeft *= var5;
            this.colorRedBottomRight *= var5;
            this.colorRedTopRight *= var5;
            this.colorGreenTopLeft *= var6;
            this.colorGreenBottomLeft *= var6;
            this.colorGreenBottomRight *= var6;
            this.colorGreenTopRight *= var6;
            this.colorBlueTopLeft *= var7;
            this.colorBlueBottomLeft *= var7;
            this.colorBlueBottomRight *= var7;
            this.colorBlueTopRight *= var7;
            this.renderFaceXNeg(var1, (double)var2, (double)var3, (double)var4, BlockGrass.getIconSideOverlay());
         }

         var8 = true;
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2 + 1, var3, var4, 5)) {
         if(this.renderMaxX >= 1.0D) {
            ++var2;
         }

         this.aoLightValueScratchXYPN = this.blockAccess.getBlock(var2, var3 - 1, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXZPN = this.blockAccess.getBlock(var2, var3, var4 - 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXZPP = this.blockAccess.getBlock(var2, var3, var4 + 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXYPP = this.blockAccess.getBlock(var2, var3 + 1, var4).getAmbientOcclusionLightValue();
         this.aoBrightnessXYPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4);
         this.aoBrightnessXZPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1);
         this.aoBrightnessXZPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1);
         this.aoBrightnessXYPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4);
         var16 = this.blockAccess.getBlock(var2 + 1, var3 + 1, var4).getCanBlockGrass();
         var17 = this.blockAccess.getBlock(var2 + 1, var3 - 1, var4).getCanBlockGrass();
         var18 = this.blockAccess.getBlock(var2 + 1, var3, var4 + 1).getCanBlockGrass();
         var19 = this.blockAccess.getBlock(var2 + 1, var3, var4 - 1).getCanBlockGrass();
         if(!var17 && !var19) {
            this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXZPN;
            this.aoBrightnessXYZPNN = this.aoBrightnessXZPN;
         } else {
            this.aoLightValueScratchXYZPNN = this.blockAccess.getBlock(var2, var3 - 1, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4 - 1);
         }

         if(!var17 && !var18) {
            this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXZPP;
            this.aoBrightnessXYZPNP = this.aoBrightnessXZPP;
         } else {
            this.aoLightValueScratchXYZPNP = this.blockAccess.getBlock(var2, var3 - 1, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4 + 1);
         }

         if(!var16 && !var19) {
            this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXZPN;
            this.aoBrightnessXYZPPN = this.aoBrightnessXZPN;
         } else {
            this.aoLightValueScratchXYZPPN = this.blockAccess.getBlock(var2, var3 + 1, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4 - 1);
         }

         if(!var16 && !var18) {
            this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXZPP;
            this.aoBrightnessXYZPPP = this.aoBrightnessXZPP;
         } else {
            this.aoLightValueScratchXYZPPP = this.blockAccess.getBlock(var2, var3 + 1, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4 + 1);
         }

         if(this.renderMaxX >= 1.0D) {
            --var2;
         }

         var20 = var14;
         if(this.renderMaxX >= 1.0D || !this.blockAccess.getBlock(var2 + 1, var3, var4).isOpaqueCube()) {
            var20 = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4);
         }

         var21 = this.blockAccess.getBlock(var2 + 1, var3, var4).getAmbientOcclusionLightValue();
         var9 = (this.aoLightValueScratchXYPN + this.aoLightValueScratchXYZPNP + var21 + this.aoLightValueScratchXZPP) / 4.0F;
         var10 = (this.aoLightValueScratchXYZPNN + this.aoLightValueScratchXYPN + this.aoLightValueScratchXZPN + var21) / 4.0F;
         var11 = (this.aoLightValueScratchXZPN + var21 + this.aoLightValueScratchXYZPPN + this.aoLightValueScratchXYPP) / 4.0F;
         var12 = (var21 + this.aoLightValueScratchXZPP + this.aoLightValueScratchXYPP + this.aoLightValueScratchXYZPPP) / 4.0F;
         this.brightnessTopLeft = this.getAoBrightness(this.aoBrightnessXYPN, this.aoBrightnessXYZPNP, this.aoBrightnessXZPP, var20);
         this.brightnessTopRight = this.getAoBrightness(this.aoBrightnessXZPP, this.aoBrightnessXYPP, this.aoBrightnessXYZPPP, var20);
         this.brightnessBottomRight = this.getAoBrightness(this.aoBrightnessXZPN, this.aoBrightnessXYZPPN, this.aoBrightnessXYPP, var20);
         this.brightnessBottomLeft = this.getAoBrightness(this.aoBrightnessXYZPNN, this.aoBrightnessXYPN, this.aoBrightnessXZPN, var20);
         if(var13) {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = var5 * 0.6F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = var6 * 0.6F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = var7 * 0.6F;
         } else {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.6F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.6F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.6F;
         }

         this.colorRedTopLeft *= var9;
         this.colorGreenTopLeft *= var9;
         this.colorBlueTopLeft *= var9;
         this.colorRedBottomLeft *= var10;
         this.colorGreenBottomLeft *= var10;
         this.colorBlueBottomLeft *= var10;
         this.colorRedBottomRight *= var11;
         this.colorGreenBottomRight *= var11;
         this.colorBlueBottomRight *= var11;
         this.colorRedTopRight *= var12;
         this.colorGreenTopRight *= var12;
         this.colorBlueTopRight *= var12;
         var22 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 5);
         this.renderFaceXPos(var1, (double)var2, (double)var3, (double)var4, var22);
         if(fancyGrass && var22.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
            this.colorRedTopLeft *= var5;
            this.colorRedBottomLeft *= var5;
            this.colorRedBottomRight *= var5;
            this.colorRedTopRight *= var5;
            this.colorGreenTopLeft *= var6;
            this.colorGreenBottomLeft *= var6;
            this.colorGreenBottomRight *= var6;
            this.colorGreenTopRight *= var6;
            this.colorBlueTopLeft *= var7;
            this.colorBlueBottomLeft *= var7;
            this.colorBlueBottomRight *= var7;
            this.colorBlueTopRight *= var7;
            this.renderFaceXPos(var1, (double)var2, (double)var3, (double)var4, BlockGrass.getIconSideOverlay());
         }

         var8 = true;
      }

      this.enableAO = false;
      return var8;
   }

   public boolean renderStandardBlockWithAmbientOcclusionPartial(Block var1, int var2, int var3, int var4, float var5, float var6, float var7) {
      this.enableAO = true;
      boolean var8 = false;
      float var9 = 0.0F;
      float var10 = 0.0F;
      float var11 = 0.0F;
      float var12 = 0.0F;
      boolean var13 = true;
      int var14 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4);
      Tessellator var15 = Tessellator.instance;
      var15.setBrightness(983055);
      if(this.getBlockIcon(var1).getIconName().equals("grass_top")) {
         var13 = false;
      } else if(this.hasOverrideBlockTexture()) {
         var13 = false;
      }

      boolean var16;
      boolean var17;
      boolean var18;
      boolean var19;
      int var20;
      float var21;
      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3 - 1, var4, 0)) {
         if(this.renderMinY <= 0.0D) {
            --var3;
         }

         this.aoBrightnessXYNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4);
         this.aoBrightnessYZNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1);
         this.aoBrightnessYZNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1);
         this.aoBrightnessXYPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4);
         this.aoLightValueScratchXYNN = this.blockAccess.getBlock(var2 - 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZNN = this.blockAccess.getBlock(var2, var3, var4 - 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZNP = this.blockAccess.getBlock(var2, var3, var4 + 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXYPN = this.blockAccess.getBlock(var2 + 1, var3, var4).getAmbientOcclusionLightValue();
         var16 = this.blockAccess.getBlock(var2 + 1, var3 - 1, var4).getCanBlockGrass();
         var17 = this.blockAccess.getBlock(var2 - 1, var3 - 1, var4).getCanBlockGrass();
         var18 = this.blockAccess.getBlock(var2, var3 - 1, var4 + 1).getCanBlockGrass();
         var19 = this.blockAccess.getBlock(var2, var3 - 1, var4 - 1).getCanBlockGrass();
         if(!var19 && !var17) {
            this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXYNN;
            this.aoBrightnessXYZNNN = this.aoBrightnessXYNN;
         } else {
            this.aoLightValueScratchXYZNNN = this.blockAccess.getBlock(var2 - 1, var3, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4 - 1);
         }

         if(!var18 && !var17) {
            this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXYNN;
            this.aoBrightnessXYZNNP = this.aoBrightnessXYNN;
         } else {
            this.aoLightValueScratchXYZNNP = this.blockAccess.getBlock(var2 - 1, var3, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4 + 1);
         }

         if(!var19 && !var16) {
            this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXYPN;
            this.aoBrightnessXYZPNN = this.aoBrightnessXYPN;
         } else {
            this.aoLightValueScratchXYZPNN = this.blockAccess.getBlock(var2 + 1, var3, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4 - 1);
         }

         if(!var18 && !var16) {
            this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXYPN;
            this.aoBrightnessXYZPNP = this.aoBrightnessXYPN;
         } else {
            this.aoLightValueScratchXYZPNP = this.blockAccess.getBlock(var2 + 1, var3, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4 + 1);
         }

         if(this.renderMinY <= 0.0D) {
            ++var3;
         }

         var20 = var14;
         if(this.renderMinY <= 0.0D || !this.blockAccess.getBlock(var2, var3 - 1, var4).isOpaqueCube()) {
            var20 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4);
         }

         var21 = this.blockAccess.getBlock(var2, var3 - 1, var4).getAmbientOcclusionLightValue();
         var9 = (this.aoLightValueScratchXYZNNP + this.aoLightValueScratchXYNN + this.aoLightValueScratchYZNP + var21) / 4.0F;
         var12 = (this.aoLightValueScratchYZNP + var21 + this.aoLightValueScratchXYZPNP + this.aoLightValueScratchXYPN) / 4.0F;
         var11 = (var21 + this.aoLightValueScratchYZNN + this.aoLightValueScratchXYPN + this.aoLightValueScratchXYZPNN) / 4.0F;
         var10 = (this.aoLightValueScratchXYNN + this.aoLightValueScratchXYZNNN + var21 + this.aoLightValueScratchYZNN) / 4.0F;
         this.brightnessTopLeft = this.getAoBrightness(this.aoBrightnessXYZNNP, this.aoBrightnessXYNN, this.aoBrightnessYZNP, var20);
         this.brightnessTopRight = this.getAoBrightness(this.aoBrightnessYZNP, this.aoBrightnessXYZPNP, this.aoBrightnessXYPN, var20);
         this.brightnessBottomRight = this.getAoBrightness(this.aoBrightnessYZNN, this.aoBrightnessXYPN, this.aoBrightnessXYZPNN, var20);
         this.brightnessBottomLeft = this.getAoBrightness(this.aoBrightnessXYNN, this.aoBrightnessXYZNNN, this.aoBrightnessYZNN, var20);
         if(var13) {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = var5 * 0.5F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = var6 * 0.5F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = var7 * 0.5F;
         } else {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.5F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.5F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.5F;
         }

         this.colorRedTopLeft *= var9;
         this.colorGreenTopLeft *= var9;
         this.colorBlueTopLeft *= var9;
         this.colorRedBottomLeft *= var10;
         this.colorGreenBottomLeft *= var10;
         this.colorBlueBottomLeft *= var10;
         this.colorRedBottomRight *= var11;
         this.colorGreenBottomRight *= var11;
         this.colorBlueBottomRight *= var11;
         this.colorRedTopRight *= var12;
         this.colorGreenTopRight *= var12;
         this.colorBlueTopRight *= var12;
         this.renderFaceYNeg(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 0));
         var8 = true;
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3 + 1, var4, 1)) {
         if(this.renderMaxY >= 1.0D) {
            ++var3;
         }

         this.aoBrightnessXYNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4);
         this.aoBrightnessXYPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4);
         this.aoBrightnessYZPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1);
         this.aoBrightnessYZPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1);
         this.aoLightValueScratchXYNP = this.blockAccess.getBlock(var2 - 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXYPP = this.blockAccess.getBlock(var2 + 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZPN = this.blockAccess.getBlock(var2, var3, var4 - 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZPP = this.blockAccess.getBlock(var2, var3, var4 + 1).getAmbientOcclusionLightValue();
         var16 = this.blockAccess.getBlock(var2 + 1, var3 + 1, var4).getCanBlockGrass();
         var17 = this.blockAccess.getBlock(var2 - 1, var3 + 1, var4).getCanBlockGrass();
         var18 = this.blockAccess.getBlock(var2, var3 + 1, var4 + 1).getCanBlockGrass();
         var19 = this.blockAccess.getBlock(var2, var3 + 1, var4 - 1).getCanBlockGrass();
         if(!var19 && !var17) {
            this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXYNP;
            this.aoBrightnessXYZNPN = this.aoBrightnessXYNP;
         } else {
            this.aoLightValueScratchXYZNPN = this.blockAccess.getBlock(var2 - 1, var3, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4 - 1);
         }

         if(!var19 && !var16) {
            this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXYPP;
            this.aoBrightnessXYZPPN = this.aoBrightnessXYPP;
         } else {
            this.aoLightValueScratchXYZPPN = this.blockAccess.getBlock(var2 + 1, var3, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4 - 1);
         }

         if(!var18 && !var17) {
            this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXYNP;
            this.aoBrightnessXYZNPP = this.aoBrightnessXYNP;
         } else {
            this.aoLightValueScratchXYZNPP = this.blockAccess.getBlock(var2 - 1, var3, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4 + 1);
         }

         if(!var18 && !var16) {
            this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXYPP;
            this.aoBrightnessXYZPPP = this.aoBrightnessXYPP;
         } else {
            this.aoLightValueScratchXYZPPP = this.blockAccess.getBlock(var2 + 1, var3, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4 + 1);
         }

         if(this.renderMaxY >= 1.0D) {
            --var3;
         }

         var20 = var14;
         if(this.renderMaxY >= 1.0D || !this.blockAccess.getBlock(var2, var3 + 1, var4).isOpaqueCube()) {
            var20 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4);
         }

         var21 = this.blockAccess.getBlock(var2, var3 + 1, var4).getAmbientOcclusionLightValue();
         var12 = (this.aoLightValueScratchXYZNPP + this.aoLightValueScratchXYNP + this.aoLightValueScratchYZPP + var21) / 4.0F;
         var9 = (this.aoLightValueScratchYZPP + var21 + this.aoLightValueScratchXYZPPP + this.aoLightValueScratchXYPP) / 4.0F;
         var10 = (var21 + this.aoLightValueScratchYZPN + this.aoLightValueScratchXYPP + this.aoLightValueScratchXYZPPN) / 4.0F;
         var11 = (this.aoLightValueScratchXYNP + this.aoLightValueScratchXYZNPN + var21 + this.aoLightValueScratchYZPN) / 4.0F;
         this.brightnessTopRight = this.getAoBrightness(this.aoBrightnessXYZNPP, this.aoBrightnessXYNP, this.aoBrightnessYZPP, var20);
         this.brightnessTopLeft = this.getAoBrightness(this.aoBrightnessYZPP, this.aoBrightnessXYZPPP, this.aoBrightnessXYPP, var20);
         this.brightnessBottomLeft = this.getAoBrightness(this.aoBrightnessYZPN, this.aoBrightnessXYPP, this.aoBrightnessXYZPPN, var20);
         this.brightnessBottomRight = this.getAoBrightness(this.aoBrightnessXYNP, this.aoBrightnessXYZNPN, this.aoBrightnessYZPN, var20);
         this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = var5;
         this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = var6;
         this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = var7;
         this.colorRedTopLeft *= var9;
         this.colorGreenTopLeft *= var9;
         this.colorBlueTopLeft *= var9;
         this.colorRedBottomLeft *= var10;
         this.colorGreenBottomLeft *= var10;
         this.colorBlueBottomLeft *= var10;
         this.colorRedBottomRight *= var11;
         this.colorGreenBottomRight *= var11;
         this.colorBlueBottomRight *= var11;
         this.colorRedTopRight *= var12;
         this.colorGreenTopRight *= var12;
         this.colorBlueTopRight *= var12;
         this.renderFaceYPos(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 1));
         var8 = true;
      }

      float var22;
      float var23;
      float var24;
      float var25;
      int var26;
      int var27;
      int var28;
      int var29;
      IIcon var30;
      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3, var4 - 1, 2)) {
         if(this.renderMinZ <= 0.0D) {
            --var4;
         }

         this.aoLightValueScratchXZNN = this.blockAccess.getBlock(var2 - 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZNN = this.blockAccess.getBlock(var2, var3 - 1, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZPN = this.blockAccess.getBlock(var2, var3 + 1, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXZPN = this.blockAccess.getBlock(var2 + 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoBrightnessXZNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4);
         this.aoBrightnessYZNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4);
         this.aoBrightnessYZPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4);
         this.aoBrightnessXZPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4);
         var16 = this.blockAccess.getBlock(var2 + 1, var3, var4 - 1).getCanBlockGrass();
         var17 = this.blockAccess.getBlock(var2 - 1, var3, var4 - 1).getCanBlockGrass();
         var18 = this.blockAccess.getBlock(var2, var3 + 1, var4 - 1).getCanBlockGrass();
         var19 = this.blockAccess.getBlock(var2, var3 - 1, var4 - 1).getCanBlockGrass();
         if(!var17 && !var19) {
            this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXZNN;
            this.aoBrightnessXYZNNN = this.aoBrightnessXZNN;
         } else {
            this.aoLightValueScratchXYZNNN = this.blockAccess.getBlock(var2 - 1, var3 - 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3 - 1, var4);
         }

         if(!var17 && !var18) {
            this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXZNN;
            this.aoBrightnessXYZNPN = this.aoBrightnessXZNN;
         } else {
            this.aoLightValueScratchXYZNPN = this.blockAccess.getBlock(var2 - 1, var3 + 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3 + 1, var4);
         }

         if(!var16 && !var19) {
            this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXZPN;
            this.aoBrightnessXYZPNN = this.aoBrightnessXZPN;
         } else {
            this.aoLightValueScratchXYZPNN = this.blockAccess.getBlock(var2 + 1, var3 - 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3 - 1, var4);
         }

         if(!var16 && !var18) {
            this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXZPN;
            this.aoBrightnessXYZPPN = this.aoBrightnessXZPN;
         } else {
            this.aoLightValueScratchXYZPPN = this.blockAccess.getBlock(var2 + 1, var3 + 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3 + 1, var4);
         }

         if(this.renderMinZ <= 0.0D) {
            ++var4;
         }

         var20 = var14;
         if(this.renderMinZ <= 0.0D || !this.blockAccess.getBlock(var2, var3, var4 - 1).isOpaqueCube()) {
            var20 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1);
         }

         var21 = this.blockAccess.getBlock(var2, var3, var4 - 1).getAmbientOcclusionLightValue();
         var22 = (this.aoLightValueScratchXZNN + this.aoLightValueScratchXYZNPN + var21 + this.aoLightValueScratchYZPN) / 4.0F;
         var23 = (var21 + this.aoLightValueScratchYZPN + this.aoLightValueScratchXZPN + this.aoLightValueScratchXYZPPN) / 4.0F;
         var24 = (this.aoLightValueScratchYZNN + var21 + this.aoLightValueScratchXYZPNN + this.aoLightValueScratchXZPN) / 4.0F;
         var25 = (this.aoLightValueScratchXYZNNN + this.aoLightValueScratchXZNN + this.aoLightValueScratchYZNN + var21) / 4.0F;
         var9 = (float)((double)var22 * this.renderMaxY * (1.0D - this.renderMinX) + (double)var23 * this.renderMaxY * this.renderMinX + (double)var24 * (1.0D - this.renderMaxY) * this.renderMinX + (double)var25 * (1.0D - this.renderMaxY) * (1.0D - this.renderMinX));
         var10 = (float)((double)var22 * this.renderMaxY * (1.0D - this.renderMaxX) + (double)var23 * this.renderMaxY * this.renderMaxX + (double)var24 * (1.0D - this.renderMaxY) * this.renderMaxX + (double)var25 * (1.0D - this.renderMaxY) * (1.0D - this.renderMaxX));
         var11 = (float)((double)var22 * this.renderMinY * (1.0D - this.renderMaxX) + (double)var23 * this.renderMinY * this.renderMaxX + (double)var24 * (1.0D - this.renderMinY) * this.renderMaxX + (double)var25 * (1.0D - this.renderMinY) * (1.0D - this.renderMaxX));
         var12 = (float)((double)var22 * this.renderMinY * (1.0D - this.renderMinX) + (double)var23 * this.renderMinY * this.renderMinX + (double)var24 * (1.0D - this.renderMinY) * this.renderMinX + (double)var25 * (1.0D - this.renderMinY) * (1.0D - this.renderMinX));
         var26 = this.getAoBrightness(this.aoBrightnessXZNN, this.aoBrightnessXYZNPN, this.aoBrightnessYZPN, var20);
         var27 = this.getAoBrightness(this.aoBrightnessYZPN, this.aoBrightnessXZPN, this.aoBrightnessXYZPPN, var20);
         var28 = this.getAoBrightness(this.aoBrightnessYZNN, this.aoBrightnessXYZPNN, this.aoBrightnessXZPN, var20);
         var29 = this.getAoBrightness(this.aoBrightnessXYZNNN, this.aoBrightnessXZNN, this.aoBrightnessYZNN, var20);
         this.brightnessTopLeft = this.mixAoBrightness(var26, var27, var28, var29, this.renderMaxY * (1.0D - this.renderMinX), this.renderMaxY * this.renderMinX, (1.0D - this.renderMaxY) * this.renderMinX, (1.0D - this.renderMaxY) * (1.0D - this.renderMinX));
         this.brightnessBottomLeft = this.mixAoBrightness(var26, var27, var28, var29, this.renderMaxY * (1.0D - this.renderMaxX), this.renderMaxY * this.renderMaxX, (1.0D - this.renderMaxY) * this.renderMaxX, (1.0D - this.renderMaxY) * (1.0D - this.renderMaxX));
         this.brightnessBottomRight = this.mixAoBrightness(var26, var27, var28, var29, this.renderMinY * (1.0D - this.renderMaxX), this.renderMinY * this.renderMaxX, (1.0D - this.renderMinY) * this.renderMaxX, (1.0D - this.renderMinY) * (1.0D - this.renderMaxX));
         this.brightnessTopRight = this.mixAoBrightness(var26, var27, var28, var29, this.renderMinY * (1.0D - this.renderMinX), this.renderMinY * this.renderMinX, (1.0D - this.renderMinY) * this.renderMinX, (1.0D - this.renderMinY) * (1.0D - this.renderMinX));
         if(var13) {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = var5 * 0.8F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = var6 * 0.8F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = var7 * 0.8F;
         } else {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.8F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.8F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.8F;
         }

         this.colorRedTopLeft *= var9;
         this.colorGreenTopLeft *= var9;
         this.colorBlueTopLeft *= var9;
         this.colorRedBottomLeft *= var10;
         this.colorGreenBottomLeft *= var10;
         this.colorBlueBottomLeft *= var10;
         this.colorRedBottomRight *= var11;
         this.colorGreenBottomRight *= var11;
         this.colorBlueBottomRight *= var11;
         this.colorRedTopRight *= var12;
         this.colorGreenTopRight *= var12;
         this.colorBlueTopRight *= var12;
         var30 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 2);
         this.renderFaceZNeg(var1, (double)var2, (double)var3, (double)var4, var30);
         if(fancyGrass && var30.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
            this.colorRedTopLeft *= var5;
            this.colorRedBottomLeft *= var5;
            this.colorRedBottomRight *= var5;
            this.colorRedTopRight *= var5;
            this.colorGreenTopLeft *= var6;
            this.colorGreenBottomLeft *= var6;
            this.colorGreenBottomRight *= var6;
            this.colorGreenTopRight *= var6;
            this.colorBlueTopLeft *= var7;
            this.colorBlueBottomLeft *= var7;
            this.colorBlueBottomRight *= var7;
            this.colorBlueTopRight *= var7;
            this.renderFaceZNeg(var1, (double)var2, (double)var3, (double)var4, BlockGrass.getIconSideOverlay());
         }

         var8 = true;
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3, var4 + 1, 3)) {
         if(this.renderMaxZ >= 1.0D) {
            ++var4;
         }

         this.aoLightValueScratchXZNP = this.blockAccess.getBlock(var2 - 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXZPP = this.blockAccess.getBlock(var2 + 1, var3, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZNP = this.blockAccess.getBlock(var2, var3 - 1, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchYZPP = this.blockAccess.getBlock(var2, var3 + 1, var4).getAmbientOcclusionLightValue();
         this.aoBrightnessXZNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4);
         this.aoBrightnessXZPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4);
         this.aoBrightnessYZNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4);
         this.aoBrightnessYZPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4);
         var16 = this.blockAccess.getBlock(var2 + 1, var3, var4 + 1).getCanBlockGrass();
         var17 = this.blockAccess.getBlock(var2 - 1, var3, var4 + 1).getCanBlockGrass();
         var18 = this.blockAccess.getBlock(var2, var3 + 1, var4 + 1).getCanBlockGrass();
         var19 = this.blockAccess.getBlock(var2, var3 - 1, var4 + 1).getCanBlockGrass();
         if(!var17 && !var19) {
            this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXZNP;
            this.aoBrightnessXYZNNP = this.aoBrightnessXZNP;
         } else {
            this.aoLightValueScratchXYZNNP = this.blockAccess.getBlock(var2 - 1, var3 - 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3 - 1, var4);
         }

         if(!var17 && !var18) {
            this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXZNP;
            this.aoBrightnessXYZNPP = this.aoBrightnessXZNP;
         } else {
            this.aoLightValueScratchXYZNPP = this.blockAccess.getBlock(var2 - 1, var3 + 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3 + 1, var4);
         }

         if(!var16 && !var19) {
            this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXZPP;
            this.aoBrightnessXYZPNP = this.aoBrightnessXZPP;
         } else {
            this.aoLightValueScratchXYZPNP = this.blockAccess.getBlock(var2 + 1, var3 - 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3 - 1, var4);
         }

         if(!var16 && !var18) {
            this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXZPP;
            this.aoBrightnessXYZPPP = this.aoBrightnessXZPP;
         } else {
            this.aoLightValueScratchXYZPPP = this.blockAccess.getBlock(var2 + 1, var3 + 1, var4).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3 + 1, var4);
         }

         if(this.renderMaxZ >= 1.0D) {
            --var4;
         }

         var20 = var14;
         if(this.renderMaxZ >= 1.0D || !this.blockAccess.getBlock(var2, var3, var4 + 1).isOpaqueCube()) {
            var20 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1);
         }

         var21 = this.blockAccess.getBlock(var2, var3, var4 + 1).getAmbientOcclusionLightValue();
         var22 = (this.aoLightValueScratchXZNP + this.aoLightValueScratchXYZNPP + var21 + this.aoLightValueScratchYZPP) / 4.0F;
         var23 = (var21 + this.aoLightValueScratchYZPP + this.aoLightValueScratchXZPP + this.aoLightValueScratchXYZPPP) / 4.0F;
         var24 = (this.aoLightValueScratchYZNP + var21 + this.aoLightValueScratchXYZPNP + this.aoLightValueScratchXZPP) / 4.0F;
         var25 = (this.aoLightValueScratchXYZNNP + this.aoLightValueScratchXZNP + this.aoLightValueScratchYZNP + var21) / 4.0F;
         var9 = (float)((double)var22 * this.renderMaxY * (1.0D - this.renderMinX) + (double)var23 * this.renderMaxY * this.renderMinX + (double)var24 * (1.0D - this.renderMaxY) * this.renderMinX + (double)var25 * (1.0D - this.renderMaxY) * (1.0D - this.renderMinX));
         var10 = (float)((double)var22 * this.renderMinY * (1.0D - this.renderMinX) + (double)var23 * this.renderMinY * this.renderMinX + (double)var24 * (1.0D - this.renderMinY) * this.renderMinX + (double)var25 * (1.0D - this.renderMinY) * (1.0D - this.renderMinX));
         var11 = (float)((double)var22 * this.renderMinY * (1.0D - this.renderMaxX) + (double)var23 * this.renderMinY * this.renderMaxX + (double)var24 * (1.0D - this.renderMinY) * this.renderMaxX + (double)var25 * (1.0D - this.renderMinY) * (1.0D - this.renderMaxX));
         var12 = (float)((double)var22 * this.renderMaxY * (1.0D - this.renderMaxX) + (double)var23 * this.renderMaxY * this.renderMaxX + (double)var24 * (1.0D - this.renderMaxY) * this.renderMaxX + (double)var25 * (1.0D - this.renderMaxY) * (1.0D - this.renderMaxX));
         var26 = this.getAoBrightness(this.aoBrightnessXZNP, this.aoBrightnessXYZNPP, this.aoBrightnessYZPP, var20);
         var27 = this.getAoBrightness(this.aoBrightnessYZPP, this.aoBrightnessXZPP, this.aoBrightnessXYZPPP, var20);
         var28 = this.getAoBrightness(this.aoBrightnessYZNP, this.aoBrightnessXYZPNP, this.aoBrightnessXZPP, var20);
         var29 = this.getAoBrightness(this.aoBrightnessXYZNNP, this.aoBrightnessXZNP, this.aoBrightnessYZNP, var20);
         this.brightnessTopLeft = this.mixAoBrightness(var26, var29, var28, var27, this.renderMaxY * (1.0D - this.renderMinX), (1.0D - this.renderMaxY) * (1.0D - this.renderMinX), (1.0D - this.renderMaxY) * this.renderMinX, this.renderMaxY * this.renderMinX);
         this.brightnessBottomLeft = this.mixAoBrightness(var26, var29, var28, var27, this.renderMinY * (1.0D - this.renderMinX), (1.0D - this.renderMinY) * (1.0D - this.renderMinX), (1.0D - this.renderMinY) * this.renderMinX, this.renderMinY * this.renderMinX);
         this.brightnessBottomRight = this.mixAoBrightness(var26, var29, var28, var27, this.renderMinY * (1.0D - this.renderMaxX), (1.0D - this.renderMinY) * (1.0D - this.renderMaxX), (1.0D - this.renderMinY) * this.renderMaxX, this.renderMinY * this.renderMaxX);
         this.brightnessTopRight = this.mixAoBrightness(var26, var29, var28, var27, this.renderMaxY * (1.0D - this.renderMaxX), (1.0D - this.renderMaxY) * (1.0D - this.renderMaxX), (1.0D - this.renderMaxY) * this.renderMaxX, this.renderMaxY * this.renderMaxX);
         if(var13) {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = var5 * 0.8F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = var6 * 0.8F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = var7 * 0.8F;
         } else {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.8F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.8F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.8F;
         }

         this.colorRedTopLeft *= var9;
         this.colorGreenTopLeft *= var9;
         this.colorBlueTopLeft *= var9;
         this.colorRedBottomLeft *= var10;
         this.colorGreenBottomLeft *= var10;
         this.colorBlueBottomLeft *= var10;
         this.colorRedBottomRight *= var11;
         this.colorGreenBottomRight *= var11;
         this.colorBlueBottomRight *= var11;
         this.colorRedTopRight *= var12;
         this.colorGreenTopRight *= var12;
         this.colorBlueTopRight *= var12;
         var30 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 3);
         this.renderFaceZPos(var1, (double)var2, (double)var3, (double)var4, var30);
         if(fancyGrass && var30.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
            this.colorRedTopLeft *= var5;
            this.colorRedBottomLeft *= var5;
            this.colorRedBottomRight *= var5;
            this.colorRedTopRight *= var5;
            this.colorGreenTopLeft *= var6;
            this.colorGreenBottomLeft *= var6;
            this.colorGreenBottomRight *= var6;
            this.colorGreenTopRight *= var6;
            this.colorBlueTopLeft *= var7;
            this.colorBlueBottomLeft *= var7;
            this.colorBlueBottomRight *= var7;
            this.colorBlueTopRight *= var7;
            this.renderFaceZPos(var1, (double)var2, (double)var3, (double)var4, BlockGrass.getIconSideOverlay());
         }

         var8 = true;
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2 - 1, var3, var4, 4)) {
         if(this.renderMinX <= 0.0D) {
            --var2;
         }

         this.aoLightValueScratchXYNN = this.blockAccess.getBlock(var2, var3 - 1, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXZNN = this.blockAccess.getBlock(var2, var3, var4 - 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXZNP = this.blockAccess.getBlock(var2, var3, var4 + 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXYNP = this.blockAccess.getBlock(var2, var3 + 1, var4).getAmbientOcclusionLightValue();
         this.aoBrightnessXYNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4);
         this.aoBrightnessXZNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1);
         this.aoBrightnessXZNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1);
         this.aoBrightnessXYNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4);
         var16 = this.blockAccess.getBlock(var2 - 1, var3 + 1, var4).getCanBlockGrass();
         var17 = this.blockAccess.getBlock(var2 - 1, var3 - 1, var4).getCanBlockGrass();
         var18 = this.blockAccess.getBlock(var2 - 1, var3, var4 - 1).getCanBlockGrass();
         var19 = this.blockAccess.getBlock(var2 - 1, var3, var4 + 1).getCanBlockGrass();
         if(!var18 && !var17) {
            this.aoLightValueScratchXYZNNN = this.aoLightValueScratchXZNN;
            this.aoBrightnessXYZNNN = this.aoBrightnessXZNN;
         } else {
            this.aoLightValueScratchXYZNNN = this.blockAccess.getBlock(var2, var3 - 1, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4 - 1);
         }

         if(!var19 && !var17) {
            this.aoLightValueScratchXYZNNP = this.aoLightValueScratchXZNP;
            this.aoBrightnessXYZNNP = this.aoBrightnessXZNP;
         } else {
            this.aoLightValueScratchXYZNNP = this.blockAccess.getBlock(var2, var3 - 1, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4 + 1);
         }

         if(!var18 && !var16) {
            this.aoLightValueScratchXYZNPN = this.aoLightValueScratchXZNN;
            this.aoBrightnessXYZNPN = this.aoBrightnessXZNN;
         } else {
            this.aoLightValueScratchXYZNPN = this.blockAccess.getBlock(var2, var3 + 1, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4 - 1);
         }

         if(!var19 && !var16) {
            this.aoLightValueScratchXYZNPP = this.aoLightValueScratchXZNP;
            this.aoBrightnessXYZNPP = this.aoBrightnessXZNP;
         } else {
            this.aoLightValueScratchXYZNPP = this.blockAccess.getBlock(var2, var3 + 1, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZNPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4 + 1);
         }

         if(this.renderMinX <= 0.0D) {
            ++var2;
         }

         var20 = var14;
         if(this.renderMinX <= 0.0D || !this.blockAccess.getBlock(var2 - 1, var3, var4).isOpaqueCube()) {
            var20 = var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4);
         }

         var21 = this.blockAccess.getBlock(var2 - 1, var3, var4).getAmbientOcclusionLightValue();
         var22 = (this.aoLightValueScratchXYNN + this.aoLightValueScratchXYZNNP + var21 + this.aoLightValueScratchXZNP) / 4.0F;
         var23 = (var21 + this.aoLightValueScratchXZNP + this.aoLightValueScratchXYNP + this.aoLightValueScratchXYZNPP) / 4.0F;
         var24 = (this.aoLightValueScratchXZNN + var21 + this.aoLightValueScratchXYZNPN + this.aoLightValueScratchXYNP) / 4.0F;
         var25 = (this.aoLightValueScratchXYZNNN + this.aoLightValueScratchXYNN + this.aoLightValueScratchXZNN + var21) / 4.0F;
         var9 = (float)((double)var23 * this.renderMaxY * this.renderMaxZ + (double)var24 * this.renderMaxY * (1.0D - this.renderMaxZ) + (double)var25 * (1.0D - this.renderMaxY) * (1.0D - this.renderMaxZ) + (double)var22 * (1.0D - this.renderMaxY) * this.renderMaxZ);
         var10 = (float)((double)var23 * this.renderMaxY * this.renderMinZ + (double)var24 * this.renderMaxY * (1.0D - this.renderMinZ) + (double)var25 * (1.0D - this.renderMaxY) * (1.0D - this.renderMinZ) + (double)var22 * (1.0D - this.renderMaxY) * this.renderMinZ);
         var11 = (float)((double)var23 * this.renderMinY * this.renderMinZ + (double)var24 * this.renderMinY * (1.0D - this.renderMinZ) + (double)var25 * (1.0D - this.renderMinY) * (1.0D - this.renderMinZ) + (double)var22 * (1.0D - this.renderMinY) * this.renderMinZ);
         var12 = (float)((double)var23 * this.renderMinY * this.renderMaxZ + (double)var24 * this.renderMinY * (1.0D - this.renderMaxZ) + (double)var25 * (1.0D - this.renderMinY) * (1.0D - this.renderMaxZ) + (double)var22 * (1.0D - this.renderMinY) * this.renderMaxZ);
         var26 = this.getAoBrightness(this.aoBrightnessXYNN, this.aoBrightnessXYZNNP, this.aoBrightnessXZNP, var20);
         var27 = this.getAoBrightness(this.aoBrightnessXZNP, this.aoBrightnessXYNP, this.aoBrightnessXYZNPP, var20);
         var28 = this.getAoBrightness(this.aoBrightnessXZNN, this.aoBrightnessXYZNPN, this.aoBrightnessXYNP, var20);
         var29 = this.getAoBrightness(this.aoBrightnessXYZNNN, this.aoBrightnessXYNN, this.aoBrightnessXZNN, var20);
         this.brightnessTopLeft = this.mixAoBrightness(var27, var28, var29, var26, this.renderMaxY * this.renderMaxZ, this.renderMaxY * (1.0D - this.renderMaxZ), (1.0D - this.renderMaxY) * (1.0D - this.renderMaxZ), (1.0D - this.renderMaxY) * this.renderMaxZ);
         this.brightnessBottomLeft = this.mixAoBrightness(var27, var28, var29, var26, this.renderMaxY * this.renderMinZ, this.renderMaxY * (1.0D - this.renderMinZ), (1.0D - this.renderMaxY) * (1.0D - this.renderMinZ), (1.0D - this.renderMaxY) * this.renderMinZ);
         this.brightnessBottomRight = this.mixAoBrightness(var27, var28, var29, var26, this.renderMinY * this.renderMinZ, this.renderMinY * (1.0D - this.renderMinZ), (1.0D - this.renderMinY) * (1.0D - this.renderMinZ), (1.0D - this.renderMinY) * this.renderMinZ);
         this.brightnessTopRight = this.mixAoBrightness(var27, var28, var29, var26, this.renderMinY * this.renderMaxZ, this.renderMinY * (1.0D - this.renderMaxZ), (1.0D - this.renderMinY) * (1.0D - this.renderMaxZ), (1.0D - this.renderMinY) * this.renderMaxZ);
         if(var13) {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = var5 * 0.6F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = var6 * 0.6F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = var7 * 0.6F;
         } else {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.6F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.6F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.6F;
         }

         this.colorRedTopLeft *= var9;
         this.colorGreenTopLeft *= var9;
         this.colorBlueTopLeft *= var9;
         this.colorRedBottomLeft *= var10;
         this.colorGreenBottomLeft *= var10;
         this.colorBlueBottomLeft *= var10;
         this.colorRedBottomRight *= var11;
         this.colorGreenBottomRight *= var11;
         this.colorBlueBottomRight *= var11;
         this.colorRedTopRight *= var12;
         this.colorGreenTopRight *= var12;
         this.colorBlueTopRight *= var12;
         var30 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 4);
         this.renderFaceXNeg(var1, (double)var2, (double)var3, (double)var4, var30);
         if(fancyGrass && var30.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
            this.colorRedTopLeft *= var5;
            this.colorRedBottomLeft *= var5;
            this.colorRedBottomRight *= var5;
            this.colorRedTopRight *= var5;
            this.colorGreenTopLeft *= var6;
            this.colorGreenBottomLeft *= var6;
            this.colorGreenBottomRight *= var6;
            this.colorGreenTopRight *= var6;
            this.colorBlueTopLeft *= var7;
            this.colorBlueBottomLeft *= var7;
            this.colorBlueBottomRight *= var7;
            this.colorBlueTopRight *= var7;
            this.renderFaceXNeg(var1, (double)var2, (double)var3, (double)var4, BlockGrass.getIconSideOverlay());
         }

         var8 = true;
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2 + 1, var3, var4, 5)) {
         if(this.renderMaxX >= 1.0D) {
            ++var2;
         }

         this.aoLightValueScratchXYPN = this.blockAccess.getBlock(var2, var3 - 1, var4).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXZPN = this.blockAccess.getBlock(var2, var3, var4 - 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXZPP = this.blockAccess.getBlock(var2, var3, var4 + 1).getAmbientOcclusionLightValue();
         this.aoLightValueScratchXYPP = this.blockAccess.getBlock(var2, var3 + 1, var4).getAmbientOcclusionLightValue();
         this.aoBrightnessXYPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4);
         this.aoBrightnessXZPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1);
         this.aoBrightnessXZPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1);
         this.aoBrightnessXYPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4);
         var16 = this.blockAccess.getBlock(var2 + 1, var3 + 1, var4).getCanBlockGrass();
         var17 = this.blockAccess.getBlock(var2 + 1, var3 - 1, var4).getCanBlockGrass();
         var18 = this.blockAccess.getBlock(var2 + 1, var3, var4 + 1).getCanBlockGrass();
         var19 = this.blockAccess.getBlock(var2 + 1, var3, var4 - 1).getCanBlockGrass();
         if(!var17 && !var19) {
            this.aoLightValueScratchXYZPNN = this.aoLightValueScratchXZPN;
            this.aoBrightnessXYZPNN = this.aoBrightnessXZPN;
         } else {
            this.aoLightValueScratchXYZPNN = this.blockAccess.getBlock(var2, var3 - 1, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPNN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4 - 1);
         }

         if(!var17 && !var18) {
            this.aoLightValueScratchXYZPNP = this.aoLightValueScratchXZPP;
            this.aoBrightnessXYZPNP = this.aoBrightnessXZPP;
         } else {
            this.aoLightValueScratchXYZPNP = this.blockAccess.getBlock(var2, var3 - 1, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPNP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4 + 1);
         }

         if(!var16 && !var19) {
            this.aoLightValueScratchXYZPPN = this.aoLightValueScratchXZPN;
            this.aoBrightnessXYZPPN = this.aoBrightnessXZPN;
         } else {
            this.aoLightValueScratchXYZPPN = this.blockAccess.getBlock(var2, var3 + 1, var4 - 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPPN = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4 - 1);
         }

         if(!var16 && !var18) {
            this.aoLightValueScratchXYZPPP = this.aoLightValueScratchXZPP;
            this.aoBrightnessXYZPPP = this.aoBrightnessXZPP;
         } else {
            this.aoLightValueScratchXYZPPP = this.blockAccess.getBlock(var2, var3 + 1, var4 + 1).getAmbientOcclusionLightValue();
            this.aoBrightnessXYZPPP = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4 + 1);
         }

         if(this.renderMaxX >= 1.0D) {
            --var2;
         }

         var20 = var14;
         if(this.renderMaxX >= 1.0D || !this.blockAccess.getBlock(var2 + 1, var3, var4).isOpaqueCube()) {
            var20 = var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4);
         }

         var21 = this.blockAccess.getBlock(var2 + 1, var3, var4).getAmbientOcclusionLightValue();
         var22 = (this.aoLightValueScratchXYPN + this.aoLightValueScratchXYZPNP + var21 + this.aoLightValueScratchXZPP) / 4.0F;
         var23 = (this.aoLightValueScratchXYZPNN + this.aoLightValueScratchXYPN + this.aoLightValueScratchXZPN + var21) / 4.0F;
         var24 = (this.aoLightValueScratchXZPN + var21 + this.aoLightValueScratchXYZPPN + this.aoLightValueScratchXYPP) / 4.0F;
         var25 = (var21 + this.aoLightValueScratchXZPP + this.aoLightValueScratchXYPP + this.aoLightValueScratchXYZPPP) / 4.0F;
         var9 = (float)((double)var22 * (1.0D - this.renderMinY) * this.renderMaxZ + (double)var23 * (1.0D - this.renderMinY) * (1.0D - this.renderMaxZ) + (double)var24 * this.renderMinY * (1.0D - this.renderMaxZ) + (double)var25 * this.renderMinY * this.renderMaxZ);
         var10 = (float)((double)var22 * (1.0D - this.renderMinY) * this.renderMinZ + (double)var23 * (1.0D - this.renderMinY) * (1.0D - this.renderMinZ) + (double)var24 * this.renderMinY * (1.0D - this.renderMinZ) + (double)var25 * this.renderMinY * this.renderMinZ);
         var11 = (float)((double)var22 * (1.0D - this.renderMaxY) * this.renderMinZ + (double)var23 * (1.0D - this.renderMaxY) * (1.0D - this.renderMinZ) + (double)var24 * this.renderMaxY * (1.0D - this.renderMinZ) + (double)var25 * this.renderMaxY * this.renderMinZ);
         var12 = (float)((double)var22 * (1.0D - this.renderMaxY) * this.renderMaxZ + (double)var23 * (1.0D - this.renderMaxY) * (1.0D - this.renderMaxZ) + (double)var24 * this.renderMaxY * (1.0D - this.renderMaxZ) + (double)var25 * this.renderMaxY * this.renderMaxZ);
         var26 = this.getAoBrightness(this.aoBrightnessXYPN, this.aoBrightnessXYZPNP, this.aoBrightnessXZPP, var20);
         var27 = this.getAoBrightness(this.aoBrightnessXZPP, this.aoBrightnessXYPP, this.aoBrightnessXYZPPP, var20);
         var28 = this.getAoBrightness(this.aoBrightnessXZPN, this.aoBrightnessXYZPPN, this.aoBrightnessXYPP, var20);
         var29 = this.getAoBrightness(this.aoBrightnessXYZPNN, this.aoBrightnessXYPN, this.aoBrightnessXZPN, var20);
         this.brightnessTopLeft = this.mixAoBrightness(var26, var29, var28, var27, (1.0D - this.renderMinY) * this.renderMaxZ, (1.0D - this.renderMinY) * (1.0D - this.renderMaxZ), this.renderMinY * (1.0D - this.renderMaxZ), this.renderMinY * this.renderMaxZ);
         this.brightnessBottomLeft = this.mixAoBrightness(var26, var29, var28, var27, (1.0D - this.renderMinY) * this.renderMinZ, (1.0D - this.renderMinY) * (1.0D - this.renderMinZ), this.renderMinY * (1.0D - this.renderMinZ), this.renderMinY * this.renderMinZ);
         this.brightnessBottomRight = this.mixAoBrightness(var26, var29, var28, var27, (1.0D - this.renderMaxY) * this.renderMinZ, (1.0D - this.renderMaxY) * (1.0D - this.renderMinZ), this.renderMaxY * (1.0D - this.renderMinZ), this.renderMaxY * this.renderMinZ);
         this.brightnessTopRight = this.mixAoBrightness(var26, var29, var28, var27, (1.0D - this.renderMaxY) * this.renderMaxZ, (1.0D - this.renderMaxY) * (1.0D - this.renderMaxZ), this.renderMaxY * (1.0D - this.renderMaxZ), this.renderMaxY * this.renderMaxZ);
         if(var13) {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = var5 * 0.6F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = var6 * 0.6F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = var7 * 0.6F;
         } else {
            this.colorRedTopLeft = this.colorRedBottomLeft = this.colorRedBottomRight = this.colorRedTopRight = 0.6F;
            this.colorGreenTopLeft = this.colorGreenBottomLeft = this.colorGreenBottomRight = this.colorGreenTopRight = 0.6F;
            this.colorBlueTopLeft = this.colorBlueBottomLeft = this.colorBlueBottomRight = this.colorBlueTopRight = 0.6F;
         }

         this.colorRedTopLeft *= var9;
         this.colorGreenTopLeft *= var9;
         this.colorBlueTopLeft *= var9;
         this.colorRedBottomLeft *= var10;
         this.colorGreenBottomLeft *= var10;
         this.colorBlueBottomLeft *= var10;
         this.colorRedBottomRight *= var11;
         this.colorGreenBottomRight *= var11;
         this.colorBlueBottomRight *= var11;
         this.colorRedTopRight *= var12;
         this.colorGreenTopRight *= var12;
         this.colorBlueTopRight *= var12;
         var30 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 5);
         this.renderFaceXPos(var1, (double)var2, (double)var3, (double)var4, var30);
         if(fancyGrass && var30.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
            this.colorRedTopLeft *= var5;
            this.colorRedBottomLeft *= var5;
            this.colorRedBottomRight *= var5;
            this.colorRedTopRight *= var5;
            this.colorGreenTopLeft *= var6;
            this.colorGreenBottomLeft *= var6;
            this.colorGreenBottomRight *= var6;
            this.colorGreenTopRight *= var6;
            this.colorBlueTopLeft *= var7;
            this.colorBlueBottomLeft *= var7;
            this.colorBlueBottomRight *= var7;
            this.colorBlueTopRight *= var7;
            this.renderFaceXPos(var1, (double)var2, (double)var3, (double)var4, BlockGrass.getIconSideOverlay());
         }

         var8 = true;
      }

      this.enableAO = false;
      return var8;
   }

   private int getAoBrightness(int var1, int var2, int var3, int var4) {
      if(var1 == 0) {
         var1 = var4;
      }

      if(var2 == 0) {
         var2 = var4;
      }

      if(var3 == 0) {
         var3 = var4;
      }

      return var1 + var2 + var3 + var4 >> 2 & 16711935;
   }

   private int mixAoBrightness(int var1, int var2, int var3, int var4, double var5, double var7, double var9, double var11) {
      int var13 = (int)((double)(var1 >> 16 & 255) * var5 + (double)(var2 >> 16 & 255) * var7 + (double)(var3 >> 16 & 255) * var9 + (double)(var4 >> 16 & 255) * var11) & 255;
      int var14 = (int)((double)(var1 & 255) * var5 + (double)(var2 & 255) * var7 + (double)(var3 & 255) * var9 + (double)(var4 & 255) * var11) & 255;
      return var13 << 16 | var14;
   }

   public boolean renderStandardBlockWithColorMultiplier(Block var1, int var2, int var3, int var4, float var5, float var6, float var7) {
      this.enableAO = false;
      Tessellator var8 = Tessellator.instance;
      boolean var9 = false;
      float var10 = 0.5F;
      float var11 = 1.0F;
      float var12 = 0.8F;
      float var13 = 0.6F;
      float var14 = var11 * var5;
      float var15 = var11 * var6;
      float var16 = var11 * var7;
      float var17 = var10;
      float var18 = var12;
      float var19 = var13;
      float var20 = var10;
      float var21 = var12;
      float var22 = var13;
      float var23 = var10;
      float var24 = var12;
      float var25 = var13;
      if(var1 != Blocks.grass) {
         var17 = var10 * var5;
         var18 = var12 * var5;
         var19 = var13 * var5;
         var20 = var10 * var6;
         var21 = var12 * var6;
         var22 = var13 * var6;
         var23 = var10 * var7;
         var24 = var12 * var7;
         var25 = var13 * var7;
      }

      int var26 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4);
      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3 - 1, var4, 0)) {
         var8.setBrightness(this.renderMinY > 0.0D?var26:var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4));
         var8.setColorOpaque_F(var17, var20, var23);
         this.renderFaceYNeg(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 0));
         var9 = true;
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3 + 1, var4, 1)) {
         var8.setBrightness(this.renderMaxY < 1.0D?var26:var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4));
         var8.setColorOpaque_F(var14, var15, var16);
         this.renderFaceYPos(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 1));
         var9 = true;
      }

      IIcon var27;
      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3, var4 - 1, 2)) {
         var8.setBrightness(this.renderMinZ > 0.0D?var26:var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1));
         var8.setColorOpaque_F(var18, var21, var24);
         var27 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 2);
         this.renderFaceZNeg(var1, (double)var2, (double)var3, (double)var4, var27);
         if(fancyGrass && var27.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
            var8.setColorOpaque_F(var18 * var5, var21 * var6, var24 * var7);
            this.renderFaceZNeg(var1, (double)var2, (double)var3, (double)var4, BlockGrass.getIconSideOverlay());
         }

         var9 = true;
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3, var4 + 1, 3)) {
         var8.setBrightness(this.renderMaxZ < 1.0D?var26:var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1));
         var8.setColorOpaque_F(var18, var21, var24);
         var27 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 3);
         this.renderFaceZPos(var1, (double)var2, (double)var3, (double)var4, var27);
         if(fancyGrass && var27.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
            var8.setColorOpaque_F(var18 * var5, var21 * var6, var24 * var7);
            this.renderFaceZPos(var1, (double)var2, (double)var3, (double)var4, BlockGrass.getIconSideOverlay());
         }

         var9 = true;
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2 - 1, var3, var4, 4)) {
         var8.setBrightness(this.renderMinX > 0.0D?var26:var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4));
         var8.setColorOpaque_F(var19, var22, var25);
         var27 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 4);
         this.renderFaceXNeg(var1, (double)var2, (double)var3, (double)var4, var27);
         if(fancyGrass && var27.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
            var8.setColorOpaque_F(var19 * var5, var22 * var6, var25 * var7);
            this.renderFaceXNeg(var1, (double)var2, (double)var3, (double)var4, BlockGrass.getIconSideOverlay());
         }

         var9 = true;
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2 + 1, var3, var4, 5)) {
         var8.setBrightness(this.renderMaxX < 1.0D?var26:var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4));
         var8.setColorOpaque_F(var19, var22, var25);
         var27 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 5);
         this.renderFaceXPos(var1, (double)var2, (double)var3, (double)var4, var27);
         if(fancyGrass && var27.getIconName().equals("grass_side") && !this.hasOverrideBlockTexture()) {
            var8.setColorOpaque_F(var19 * var5, var22 * var6, var25 * var7);
            this.renderFaceXPos(var1, (double)var2, (double)var3, (double)var4, BlockGrass.getIconSideOverlay());
         }

         var9 = true;
      }

      return var9;
   }

   private boolean renderBlockCocoa(BlockCocoa var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      int var6 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      int var7 = BlockDirectional.getDirection(var6);
      int var8 = BlockCocoa.func_149987_c(var6);
      IIcon var9 = var1.getCocoaIcon(var8);
      int var10 = 4 + var8 * 2;
      int var11 = 5 + var8 * 2;
      double var12 = 15.0D - (double)var10;
      double var14 = 15.0D;
      double var16 = 4.0D;
      double var18 = 4.0D + (double)var11;
      double var20 = (double)var9.getInterpolatedU(var12);
      double var22 = (double)var9.getInterpolatedU(var14);
      double var24 = (double)var9.getInterpolatedV(var16);
      double var26 = (double)var9.getInterpolatedV(var18);
      double var28 = 0.0D;
      double var30 = 0.0D;
      switch(var7) {
      case 0:
         var28 = 8.0D - (double)(var10 / 2);
         var30 = 15.0D - (double)var10;
         break;
      case 1:
         var28 = 1.0D;
         var30 = 8.0D - (double)(var10 / 2);
         break;
      case 2:
         var28 = 8.0D - (double)(var10 / 2);
         var30 = 1.0D;
         break;
      case 3:
         var28 = 15.0D - (double)var10;
         var30 = 8.0D - (double)(var10 / 2);
      }

      double var32 = (double)var2 + var28 / 16.0D;
      double var34 = (double)var2 + (var28 + (double)var10) / 16.0D;
      double var36 = (double)var3 + (12.0D - (double)var11) / 16.0D;
      double var38 = (double)var3 + 0.75D;
      double var40 = (double)var4 + var30 / 16.0D;
      double var42 = (double)var4 + (var30 + (double)var10) / 16.0D;
      var5.addVertexWithUV(var32, var36, var40, var20, var26);
      var5.addVertexWithUV(var32, var36, var42, var22, var26);
      var5.addVertexWithUV(var32, var38, var42, var22, var24);
      var5.addVertexWithUV(var32, var38, var40, var20, var24);
      var5.addVertexWithUV(var34, var36, var42, var20, var26);
      var5.addVertexWithUV(var34, var36, var40, var22, var26);
      var5.addVertexWithUV(var34, var38, var40, var22, var24);
      var5.addVertexWithUV(var34, var38, var42, var20, var24);
      var5.addVertexWithUV(var34, var36, var40, var20, var26);
      var5.addVertexWithUV(var32, var36, var40, var22, var26);
      var5.addVertexWithUV(var32, var38, var40, var22, var24);
      var5.addVertexWithUV(var34, var38, var40, var20, var24);
      var5.addVertexWithUV(var32, var36, var42, var20, var26);
      var5.addVertexWithUV(var34, var36, var42, var22, var26);
      var5.addVertexWithUV(var34, var38, var42, var22, var24);
      var5.addVertexWithUV(var32, var38, var42, var20, var24);
      int var44 = var10;
      if(var8 >= 2) {
         var44 = var10 - 1;
      }

      var20 = (double)var9.getMinU();
      var22 = (double)var9.getInterpolatedU((double)var44);
      var24 = (double)var9.getMinV();
      var26 = (double)var9.getInterpolatedV((double)var44);
      var5.addVertexWithUV(var32, var38, var42, var20, var26);
      var5.addVertexWithUV(var34, var38, var42, var22, var26);
      var5.addVertexWithUV(var34, var38, var40, var22, var24);
      var5.addVertexWithUV(var32, var38, var40, var20, var24);
      var5.addVertexWithUV(var32, var36, var40, var20, var24);
      var5.addVertexWithUV(var34, var36, var40, var22, var24);
      var5.addVertexWithUV(var34, var36, var42, var22, var26);
      var5.addVertexWithUV(var32, var36, var42, var20, var26);
      var20 = (double)var9.getInterpolatedU(12.0D);
      var22 = (double)var9.getMaxU();
      var24 = (double)var9.getMinV();
      var26 = (double)var9.getInterpolatedV(4.0D);
      var28 = 8.0D;
      var30 = 0.0D;
      double var45;
      switch(var7) {
      case 0:
         var28 = 8.0D;
         var30 = 12.0D;
         var45 = var20;
         var20 = var22;
         var22 = var45;
         break;
      case 1:
         var28 = 0.0D;
         var30 = 8.0D;
         break;
      case 2:
         var28 = 8.0D;
         var30 = 0.0D;
         break;
      case 3:
         var28 = 12.0D;
         var30 = 8.0D;
         var45 = var20;
         var20 = var22;
         var22 = var45;
      }

      var32 = (double)var2 + var28 / 16.0D;
      var34 = (double)var2 + (var28 + 4.0D) / 16.0D;
      var36 = (double)var3 + 0.75D;
      var38 = (double)var3 + 1.0D;
      var40 = (double)var4 + var30 / 16.0D;
      var42 = (double)var4 + (var30 + 4.0D) / 16.0D;
      if(var7 != 2 && var7 != 0) {
         if(var7 == 1 || var7 == 3) {
            var5.addVertexWithUV(var34, var36, var40, var20, var26);
            var5.addVertexWithUV(var32, var36, var40, var22, var26);
            var5.addVertexWithUV(var32, var38, var40, var22, var24);
            var5.addVertexWithUV(var34, var38, var40, var20, var24);
            var5.addVertexWithUV(var32, var36, var40, var22, var26);
            var5.addVertexWithUV(var34, var36, var40, var20, var26);
            var5.addVertexWithUV(var34, var38, var40, var20, var24);
            var5.addVertexWithUV(var32, var38, var40, var22, var24);
         }
      } else {
         var5.addVertexWithUV(var32, var36, var40, var22, var26);
         var5.addVertexWithUV(var32, var36, var42, var20, var26);
         var5.addVertexWithUV(var32, var38, var42, var20, var24);
         var5.addVertexWithUV(var32, var38, var40, var22, var24);
         var5.addVertexWithUV(var32, var36, var42, var20, var26);
         var5.addVertexWithUV(var32, var36, var40, var22, var26);
         var5.addVertexWithUV(var32, var38, var40, var22, var24);
         var5.addVertexWithUV(var32, var38, var42, var20, var24);
      }

      return true;
   }

   private boolean renderBlockBeacon(BlockBeacon var1, int var2, int var3, int var4) {
      float var5 = 0.1875F;
      this.setOverrideBlockTexture(this.getBlockIcon(Blocks.glass));
      this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
      this.renderStandardBlock(var1, var2, var3, var4);
      this.renderAllFaces = true;
      this.setOverrideBlockTexture(this.getBlockIcon(Blocks.obsidian));
      this.setRenderBounds(0.125D, 0.0062500000931322575D, 0.125D, 0.875D, (double)var5, 0.875D);
      this.renderStandardBlock(var1, var2, var3, var4);
      this.setOverrideBlockTexture(this.getBlockIcon(Blocks.beacon));
      this.setRenderBounds(0.1875D, (double)var5, 0.1875D, 0.8125D, 0.875D, 0.8125D);
      this.renderStandardBlock(var1, var2, var3, var4);
      this.renderAllFaces = false;
      this.clearOverrideBlockTexture();
      return true;
   }

   public boolean renderBlockCactus(Block var1, int var2, int var3, int var4) {
      int var5 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var6 = (float)(var5 >> 16 & 255) / 255.0F;
      float var7 = (float)(var5 >> 8 & 255) / 255.0F;
      float var8 = (float)(var5 & 255) / 255.0F;
      if(EntityRenderer.anaglyphEnable) {
         float var9 = (var6 * 30.0F + var7 * 59.0F + var8 * 11.0F) / 100.0F;
         float var10 = (var6 * 30.0F + var7 * 70.0F) / 100.0F;
         float var11 = (var6 * 30.0F + var8 * 70.0F) / 100.0F;
         var6 = var9;
         var7 = var10;
         var8 = var11;
      }

      return this.renderBlockCactusImpl(var1, var2, var3, var4, var6, var7, var8);
   }

   public boolean renderBlockCactusImpl(Block var1, int var2, int var3, int var4, float var5, float var6, float var7) {
      Tessellator var8 = Tessellator.instance;
      boolean var9 = false;
      float var10 = 0.5F;
      float var11 = 1.0F;
      float var12 = 0.8F;
      float var13 = 0.6F;
      float var14 = var10 * var5;
      float var15 = var11 * var5;
      float var16 = var12 * var5;
      float var17 = var13 * var5;
      float var18 = var10 * var6;
      float var19 = var11 * var6;
      float var20 = var12 * var6;
      float var21 = var13 * var6;
      float var22 = var10 * var7;
      float var23 = var11 * var7;
      float var24 = var12 * var7;
      float var25 = var13 * var7;
      float var26 = 0.0625F;
      int var27 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4);
      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3 - 1, var4, 0)) {
         var8.setBrightness(this.renderMinY > 0.0D?var27:var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4));
         var8.setColorOpaque_F(var14, var18, var22);
         this.renderFaceYNeg(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 0));
      }

      if(this.renderAllFaces || var1.shouldSideBeRendered(this.blockAccess, var2, var3 + 1, var4, 1)) {
         var8.setBrightness(this.renderMaxY < 1.0D?var27:var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4));
         var8.setColorOpaque_F(var15, var19, var23);
         this.renderFaceYPos(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 1));
      }

      var8.setBrightness(var27);
      var8.setColorOpaque_F(var16, var20, var24);
      var8.addTranslation(0.0F, 0.0F, var26);
      this.renderFaceZNeg(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 2));
      var8.addTranslation(0.0F, 0.0F, -var26);
      var8.addTranslation(0.0F, 0.0F, -var26);
      this.renderFaceZPos(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 3));
      var8.addTranslation(0.0F, 0.0F, var26);
      var8.setColorOpaque_F(var17, var21, var25);
      var8.addTranslation(var26, 0.0F, 0.0F);
      this.renderFaceXNeg(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 4));
      var8.addTranslation(-var26, 0.0F, 0.0F);
      var8.addTranslation(-var26, 0.0F, 0.0F);
      this.renderFaceXPos(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 5));
      var8.addTranslation(var26, 0.0F, 0.0F);
      return true;
   }

   public boolean renderBlockFence(BlockFence var1, int var2, int var3, int var4) {
      boolean var5 = false;
      float var6 = 0.375F;
      float var7 = 0.625F;
      this.setRenderBounds((double)var6, 0.0D, (double)var6, (double)var7, 1.0D, (double)var7);
      this.renderStandardBlock(var1, var2, var3, var4);
      var5 = true;
      boolean var8 = false;
      boolean var9 = false;
      if(var1.canConnectFenceTo(this.blockAccess, var2 - 1, var3, var4) || var1.canConnectFenceTo(this.blockAccess, var2 + 1, var3, var4)) {
         var8 = true;
      }

      if(var1.canConnectFenceTo(this.blockAccess, var2, var3, var4 - 1) || var1.canConnectFenceTo(this.blockAccess, var2, var3, var4 + 1)) {
         var9 = true;
      }

      boolean var10 = var1.canConnectFenceTo(this.blockAccess, var2 - 1, var3, var4);
      boolean var11 = var1.canConnectFenceTo(this.blockAccess, var2 + 1, var3, var4);
      boolean var12 = var1.canConnectFenceTo(this.blockAccess, var2, var3, var4 - 1);
      boolean var13 = var1.canConnectFenceTo(this.blockAccess, var2, var3, var4 + 1);
      if(!var8 && !var9) {
         var8 = true;
      }

      var6 = 0.4375F;
      var7 = 0.5625F;
      float var14 = 0.75F;
      float var15 = 0.9375F;
      float var16 = var10?0.0F:var6;
      float var17 = var11?1.0F:var7;
      float var18 = var12?0.0F:var6;
      float var19 = var13?1.0F:var7;
      this.field_152631_f = true;
      if(var8) {
         this.setRenderBounds((double)var16, (double)var14, (double)var6, (double)var17, (double)var15, (double)var7);
         this.renderStandardBlock(var1, var2, var3, var4);
         var5 = true;
      }

      if(var9) {
         this.setRenderBounds((double)var6, (double)var14, (double)var18, (double)var7, (double)var15, (double)var19);
         this.renderStandardBlock(var1, var2, var3, var4);
         var5 = true;
      }

      var14 = 0.375F;
      var15 = 0.5625F;
      if(var8) {
         this.setRenderBounds((double)var16, (double)var14, (double)var6, (double)var17, (double)var15, (double)var7);
         this.renderStandardBlock(var1, var2, var3, var4);
         var5 = true;
      }

      if(var9) {
         this.setRenderBounds((double)var6, (double)var14, (double)var18, (double)var7, (double)var15, (double)var19);
         this.renderStandardBlock(var1, var2, var3, var4);
         var5 = true;
      }

      this.field_152631_f = false;
      var1.setBlockBoundsBasedOnState(this.blockAccess, var2, var3, var4);
      return var5;
   }

   public boolean renderBlockWall(BlockWall var1, int var2, int var3, int var4) {
      boolean var5 = var1.canConnectWallTo(this.blockAccess, var2 - 1, var3, var4);
      boolean var6 = var1.canConnectWallTo(this.blockAccess, var2 + 1, var3, var4);
      boolean var7 = var1.canConnectWallTo(this.blockAccess, var2, var3, var4 - 1);
      boolean var8 = var1.canConnectWallTo(this.blockAccess, var2, var3, var4 + 1);
      boolean var9 = var7 && var8 && !var5 && !var6;
      boolean var10 = !var7 && !var8 && var5 && var6;
      boolean var11 = this.blockAccess.isAirBlock(var2, var3 + 1, var4);
      if((var9 || var10) && var11) {
         if(var9) {
            this.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 1.0D);
            this.renderStandardBlock(var1, var2, var3, var4);
         } else {
            this.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
            this.renderStandardBlock(var1, var2, var3, var4);
         }
      } else {
         this.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
         this.renderStandardBlock(var1, var2, var3, var4);
         if(var5) {
            this.setRenderBounds(0.0D, 0.0D, 0.3125D, 0.25D, 0.8125D, 0.6875D);
            this.renderStandardBlock(var1, var2, var3, var4);
         }

         if(var6) {
            this.setRenderBounds(0.75D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
            this.renderStandardBlock(var1, var2, var3, var4);
         }

         if(var7) {
            this.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 0.25D);
            this.renderStandardBlock(var1, var2, var3, var4);
         }

         if(var8) {
            this.setRenderBounds(0.3125D, 0.0D, 0.75D, 0.6875D, 0.8125D, 1.0D);
            this.renderStandardBlock(var1, var2, var3, var4);
         }
      }

      var1.setBlockBoundsBasedOnState(this.blockAccess, var2, var3, var4);
      return true;
   }

   public boolean renderBlockDragonEgg(BlockDragonEgg var1, int var2, int var3, int var4) {
      boolean var5 = false;
      int var6 = 0;

      for(int var7 = 0; var7 < 8; ++var7) {
         byte var8 = 0;
         byte var9 = 1;
         if(var7 == 0) {
            var8 = 2;
         }

         if(var7 == 1) {
            var8 = 3;
         }

         if(var7 == 2) {
            var8 = 4;
         }

         if(var7 == 3) {
            var8 = 5;
            var9 = 2;
         }

         if(var7 == 4) {
            var8 = 6;
            var9 = 3;
         }

         if(var7 == 5) {
            var8 = 7;
            var9 = 5;
         }

         if(var7 == 6) {
            var8 = 6;
            var9 = 2;
         }

         if(var7 == 7) {
            var8 = 3;
         }

         float var10 = (float)var8 / 16.0F;
         float var11 = 1.0F - (float)var6 / 16.0F;
         float var12 = 1.0F - (float)(var6 + var9) / 16.0F;
         var6 += var9;
         this.setRenderBounds((double)(0.5F - var10), (double)var12, (double)(0.5F - var10), (double)(0.5F + var10), (double)var11, (double)(0.5F + var10));
         this.renderStandardBlock(var1, var2, var3, var4);
      }

      var5 = true;
      this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
      return var5;
   }

   public boolean renderBlockFenceGate(BlockFenceGate var1, int var2, int var3, int var4) {
      boolean var5 = true;
      int var6 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      boolean var7 = BlockFenceGate.isFenceGateOpen(var6);
      int var8 = BlockDirectional.getDirection(var6);
      float var9 = 0.375F;
      float var10 = 0.5625F;
      float var11 = 0.75F;
      float var12 = 0.9375F;
      float var13 = 0.3125F;
      float var14 = 1.0F;
      if((var8 == 2 || var8 == 0) && this.blockAccess.getBlock(var2 - 1, var3, var4) == Blocks.cobblestone_wall && this.blockAccess.getBlock(var2 + 1, var3, var4) == Blocks.cobblestone_wall || (var8 == 3 || var8 == 1) && this.blockAccess.getBlock(var2, var3, var4 - 1) == Blocks.cobblestone_wall && this.blockAccess.getBlock(var2, var3, var4 + 1) == Blocks.cobblestone_wall) {
         var9 -= 0.1875F;
         var10 -= 0.1875F;
         var11 -= 0.1875F;
         var12 -= 0.1875F;
         var13 -= 0.1875F;
         var14 -= 0.1875F;
      }

      this.renderAllFaces = true;
      float var15;
      float var16;
      float var17;
      float var18;
      if(var8 != 3 && var8 != 1) {
         var15 = 0.0F;
         var16 = 0.125F;
         var17 = 0.4375F;
         var18 = 0.5625F;
         this.setRenderBounds((double)var15, (double)var13, (double)var17, (double)var16, (double)var14, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         var15 = 0.875F;
         var16 = 1.0F;
         this.setRenderBounds((double)var15, (double)var13, (double)var17, (double)var16, (double)var14, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
      } else {
         this.uvRotateTop = 1;
         var15 = 0.4375F;
         var16 = 0.5625F;
         var17 = 0.0F;
         var18 = 0.125F;
         this.setRenderBounds((double)var15, (double)var13, (double)var17, (double)var16, (double)var14, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         var17 = 0.875F;
         var18 = 1.0F;
         this.setRenderBounds((double)var15, (double)var13, (double)var17, (double)var16, (double)var14, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.uvRotateTop = 0;
      }

      if(var7) {
         if(var8 == 2 || var8 == 0) {
            this.uvRotateTop = 1;
         }

         float var19;
         float var20;
         float var21;
         if(var8 == 3) {
            var15 = 0.0F;
            var16 = 0.125F;
            var17 = 0.875F;
            var18 = 1.0F;
            var19 = 0.5625F;
            var20 = 0.8125F;
            var21 = 0.9375F;
            this.setRenderBounds(0.8125D, (double)var9, 0.0D, 0.9375D, (double)var12, 0.125D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.8125D, (double)var9, 0.875D, 0.9375D, (double)var12, 1.0D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.5625D, (double)var9, 0.0D, 0.8125D, (double)var10, 0.125D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.5625D, (double)var9, 0.875D, 0.8125D, (double)var10, 1.0D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.5625D, (double)var11, 0.0D, 0.8125D, (double)var12, 0.125D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.5625D, (double)var11, 0.875D, 0.8125D, (double)var12, 1.0D);
            this.renderStandardBlock(var1, var2, var3, var4);
         } else if(var8 == 1) {
            var15 = 0.0F;
            var16 = 0.125F;
            var17 = 0.875F;
            var18 = 1.0F;
            var19 = 0.0625F;
            var20 = 0.1875F;
            var21 = 0.4375F;
            this.setRenderBounds(0.0625D, (double)var9, 0.0D, 0.1875D, (double)var12, 0.125D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.0625D, (double)var9, 0.875D, 0.1875D, (double)var12, 1.0D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.1875D, (double)var9, 0.0D, 0.4375D, (double)var10, 0.125D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.1875D, (double)var9, 0.875D, 0.4375D, (double)var10, 1.0D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.1875D, (double)var11, 0.0D, 0.4375D, (double)var12, 0.125D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.1875D, (double)var11, 0.875D, 0.4375D, (double)var12, 1.0D);
            this.renderStandardBlock(var1, var2, var3, var4);
         } else if(var8 == 0) {
            var15 = 0.0F;
            var16 = 0.125F;
            var17 = 0.875F;
            var18 = 1.0F;
            var19 = 0.5625F;
            var20 = 0.8125F;
            var21 = 0.9375F;
            this.setRenderBounds(0.0D, (double)var9, 0.8125D, 0.125D, (double)var12, 0.9375D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.875D, (double)var9, 0.8125D, 1.0D, (double)var12, 0.9375D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.0D, (double)var9, 0.5625D, 0.125D, (double)var10, 0.8125D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.875D, (double)var9, 0.5625D, 1.0D, (double)var10, 0.8125D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.0D, (double)var11, 0.5625D, 0.125D, (double)var12, 0.8125D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.875D, (double)var11, 0.5625D, 1.0D, (double)var12, 0.8125D);
            this.renderStandardBlock(var1, var2, var3, var4);
         } else if(var8 == 2) {
            var15 = 0.0F;
            var16 = 0.125F;
            var17 = 0.875F;
            var18 = 1.0F;
            var19 = 0.0625F;
            var20 = 0.1875F;
            var21 = 0.4375F;
            this.setRenderBounds(0.0D, (double)var9, 0.0625D, 0.125D, (double)var12, 0.1875D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.875D, (double)var9, 0.0625D, 1.0D, (double)var12, 0.1875D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.0D, (double)var9, 0.1875D, 0.125D, (double)var10, 0.4375D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.875D, (double)var9, 0.1875D, 1.0D, (double)var10, 0.4375D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.0D, (double)var11, 0.1875D, 0.125D, (double)var12, 0.4375D);
            this.renderStandardBlock(var1, var2, var3, var4);
            this.setRenderBounds(0.875D, (double)var11, 0.1875D, 1.0D, (double)var12, 0.4375D);
            this.renderStandardBlock(var1, var2, var3, var4);
         }
      } else if(var8 != 3 && var8 != 1) {
         var15 = 0.375F;
         var16 = 0.5F;
         var17 = 0.4375F;
         var18 = 0.5625F;
         this.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var12, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         var15 = 0.5F;
         var16 = 0.625F;
         this.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var12, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         var15 = 0.625F;
         var16 = 0.875F;
         this.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var10, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.setRenderBounds((double)var15, (double)var11, (double)var17, (double)var16, (double)var12, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         var15 = 0.125F;
         var16 = 0.375F;
         this.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var10, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.setRenderBounds((double)var15, (double)var11, (double)var17, (double)var16, (double)var12, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
      } else {
         this.uvRotateTop = 1;
         var15 = 0.4375F;
         var16 = 0.5625F;
         var17 = 0.375F;
         var18 = 0.5F;
         this.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var12, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         var17 = 0.5F;
         var18 = 0.625F;
         this.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var12, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         var17 = 0.625F;
         var18 = 0.875F;
         this.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var10, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.setRenderBounds((double)var15, (double)var11, (double)var17, (double)var16, (double)var12, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         var17 = 0.125F;
         var18 = 0.375F;
         this.setRenderBounds((double)var15, (double)var9, (double)var17, (double)var16, (double)var10, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
         this.setRenderBounds((double)var15, (double)var11, (double)var17, (double)var16, (double)var12, (double)var18);
         this.renderStandardBlock(var1, var2, var3, var4);
      }

      this.renderAllFaces = false;
      this.uvRotateTop = 0;
      this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
      return var5;
   }

   private boolean renderBlockHopper(BlockHopper var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      var5.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
      int var6 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
      float var7 = (float)(var6 >> 16 & 255) / 255.0F;
      float var8 = (float)(var6 >> 8 & 255) / 255.0F;
      float var9 = (float)(var6 & 255) / 255.0F;
      if(EntityRenderer.anaglyphEnable) {
         float var10 = (var7 * 30.0F + var8 * 59.0F + var9 * 11.0F) / 100.0F;
         float var11 = (var7 * 30.0F + var8 * 70.0F) / 100.0F;
         float var12 = (var7 * 30.0F + var9 * 70.0F) / 100.0F;
         var7 = var10;
         var8 = var11;
         var9 = var12;
      }

      var5.setColorOpaque_F(var7, var8, var9);
      return this.renderBlockHopperMetadata(var1, var2, var3, var4, this.blockAccess.getBlockMetadata(var2, var3, var4), false);
   }

   private boolean renderBlockHopperMetadata(BlockHopper var1, int var2, int var3, int var4, int var5, boolean var6) {
      Tessellator var7 = Tessellator.instance;
      int var8 = BlockHopper.getDirectionFromMetadata(var5);
      double var9 = 0.625D;
      this.setRenderBounds(0.0D, var9, 0.0D, 1.0D, 1.0D, 1.0D);
      if(var6) {
         var7.startDrawingQuads();
         var7.setNormal(0.0F, -1.0F, 0.0F);
         this.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 0, var5));
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(0.0F, 1.0F, 0.0F);
         this.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 1, var5));
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(0.0F, 0.0F, -1.0F);
         this.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 2, var5));
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(0.0F, 0.0F, 1.0F);
         this.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 3, var5));
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(-1.0F, 0.0F, 0.0F);
         this.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 4, var5));
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(1.0F, 0.0F, 0.0F);
         this.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 5, var5));
         var7.draw();
      } else {
         this.renderStandardBlock(var1, var2, var3, var4);
      }

      float var13;
      if(!var6) {
         var7.setBrightness(var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4));
         int var11 = var1.colorMultiplier(this.blockAccess, var2, var3, var4);
         float var12 = (float)(var11 >> 16 & 255) / 255.0F;
         var13 = (float)(var11 >> 8 & 255) / 255.0F;
         float var14 = (float)(var11 & 255) / 255.0F;
         if(EntityRenderer.anaglyphEnable) {
            float var15 = (var12 * 30.0F + var13 * 59.0F + var14 * 11.0F) / 100.0F;
            float var16 = (var12 * 30.0F + var13 * 70.0F) / 100.0F;
            float var17 = (var12 * 30.0F + var14 * 70.0F) / 100.0F;
            var12 = var15;
            var13 = var16;
            var14 = var17;
         }

         var7.setColorOpaque_F(var12, var13, var14);
      }

      IIcon var24 = BlockHopper.getHopperIcon("hopper_outside");
      IIcon var25 = BlockHopper.getHopperIcon("hopper_inside");
      var13 = 0.125F;
      if(var6) {
         var7.startDrawingQuads();
         var7.setNormal(1.0F, 0.0F, 0.0F);
         this.renderFaceXPos(var1, (double)(-1.0F + var13), 0.0D, 0.0D, var24);
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(-1.0F, 0.0F, 0.0F);
         this.renderFaceXNeg(var1, (double)(1.0F - var13), 0.0D, 0.0D, var24);
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(0.0F, 0.0F, 1.0F);
         this.renderFaceZPos(var1, 0.0D, 0.0D, (double)(-1.0F + var13), var24);
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(0.0F, 0.0F, -1.0F);
         this.renderFaceZNeg(var1, 0.0D, 0.0D, (double)(1.0F - var13), var24);
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(0.0F, 1.0F, 0.0F);
         this.renderFaceYPos(var1, 0.0D, -1.0D + var9, 0.0D, var25);
         var7.draw();
      } else {
         this.renderFaceXPos(var1, (double)((float)var2 - 1.0F + var13), (double)var3, (double)var4, var24);
         this.renderFaceXNeg(var1, (double)((float)var2 + 1.0F - var13), (double)var3, (double)var4, var24);
         this.renderFaceZPos(var1, (double)var2, (double)var3, (double)((float)var4 - 1.0F + var13), var24);
         this.renderFaceZNeg(var1, (double)var2, (double)var3, (double)((float)var4 + 1.0F - var13), var24);
         this.renderFaceYPos(var1, (double)var2, (double)((float)var3 - 1.0F) + var9, (double)var4, var25);
      }

      this.setOverrideBlockTexture(var24);
      double var26 = 0.25D;
      double var27 = 0.25D;
      this.setRenderBounds(var26, var27, var26, 1.0D - var26, var9 - 0.002D, 1.0D - var26);
      if(var6) {
         var7.startDrawingQuads();
         var7.setNormal(1.0F, 0.0F, 0.0F);
         this.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, var24);
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(-1.0F, 0.0F, 0.0F);
         this.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, var24);
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(0.0F, 0.0F, 1.0F);
         this.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, var24);
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(0.0F, 0.0F, -1.0F);
         this.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, var24);
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(0.0F, 1.0F, 0.0F);
         this.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, var24);
         var7.draw();
         var7.startDrawingQuads();
         var7.setNormal(0.0F, -1.0F, 0.0F);
         this.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, var24);
         var7.draw();
      } else {
         this.renderStandardBlock(var1, var2, var3, var4);
      }

      if(!var6) {
         double var20 = 0.375D;
         double var22 = 0.25D;
         this.setOverrideBlockTexture(var24);
         if(var8 == 0) {
            this.setRenderBounds(var20, 0.0D, var20, 1.0D - var20, 0.25D, 1.0D - var20);
            this.renderStandardBlock(var1, var2, var3, var4);
         }

         if(var8 == 2) {
            this.setRenderBounds(var20, var27, 0.0D, 1.0D - var20, var27 + var22, var26);
            this.renderStandardBlock(var1, var2, var3, var4);
         }

         if(var8 == 3) {
            this.setRenderBounds(var20, var27, 1.0D - var26, 1.0D - var20, var27 + var22, 1.0D);
            this.renderStandardBlock(var1, var2, var3, var4);
         }

         if(var8 == 4) {
            this.setRenderBounds(0.0D, var27, var20, var26, var27 + var22, 1.0D - var20);
            this.renderStandardBlock(var1, var2, var3, var4);
         }

         if(var8 == 5) {
            this.setRenderBounds(1.0D - var26, var27, var20, 1.0D, var27 + var22, 1.0D - var20);
            this.renderStandardBlock(var1, var2, var3, var4);
         }
      }

      this.clearOverrideBlockTexture();
      return true;
   }

   public boolean renderBlockStairs(BlockStairs var1, int var2, int var3, int var4) {
      var1.func_150147_e(this.blockAccess, var2, var3, var4);
      this.setRenderBoundsFromBlock(var1);
      this.renderStandardBlock(var1, var2, var3, var4);
      this.field_152631_f = true;
      boolean var5 = var1.func_150145_f(this.blockAccess, var2, var3, var4);
      this.setRenderBoundsFromBlock(var1);
      this.renderStandardBlock(var1, var2, var3, var4);
      if(var5 && var1.func_150144_g(this.blockAccess, var2, var3, var4)) {
         this.setRenderBoundsFromBlock(var1);
         this.renderStandardBlock(var1, var2, var3, var4);
      }

      this.field_152631_f = false;
      return true;
   }

   public boolean renderBlockDoor(Block var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      int var6 = this.blockAccess.getBlockMetadata(var2, var3, var4);
      if((var6 & 8) != 0) {
         if(this.blockAccess.getBlock(var2, var3 - 1, var4) != var1) {
            return false;
         }
      } else if(this.blockAccess.getBlock(var2, var3 + 1, var4) != var1) {
         return false;
      }

      boolean var7 = false;
      float var8 = 0.5F;
      float var9 = 1.0F;
      float var10 = 0.8F;
      float var11 = 0.6F;
      int var12 = var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4);
      var5.setBrightness(this.renderMinY > 0.0D?var12:var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 - 1, var4));
      var5.setColorOpaque_F(var8, var8, var8);
      this.renderFaceYNeg(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 0));
      var7 = true;
      var5.setBrightness(this.renderMaxY < 1.0D?var12:var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3 + 1, var4));
      var5.setColorOpaque_F(var9, var9, var9);
      this.renderFaceYPos(var1, (double)var2, (double)var3, (double)var4, this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 1));
      var7 = true;
      var5.setBrightness(this.renderMinZ > 0.0D?var12:var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 - 1));
      var5.setColorOpaque_F(var10, var10, var10);
      IIcon var13 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 2);
      this.renderFaceZNeg(var1, (double)var2, (double)var3, (double)var4, var13);
      var7 = true;
      this.flipTexture = false;
      var5.setBrightness(this.renderMaxZ < 1.0D?var12:var1.getMixedBrightnessForBlock(this.blockAccess, var2, var3, var4 + 1));
      var5.setColorOpaque_F(var10, var10, var10);
      var13 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 3);
      this.renderFaceZPos(var1, (double)var2, (double)var3, (double)var4, var13);
      var7 = true;
      this.flipTexture = false;
      var5.setBrightness(this.renderMinX > 0.0D?var12:var1.getMixedBrightnessForBlock(this.blockAccess, var2 - 1, var3, var4));
      var5.setColorOpaque_F(var11, var11, var11);
      var13 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 4);
      this.renderFaceXNeg(var1, (double)var2, (double)var3, (double)var4, var13);
      var7 = true;
      this.flipTexture = false;
      var5.setBrightness(this.renderMaxX < 1.0D?var12:var1.getMixedBrightnessForBlock(this.blockAccess, var2 + 1, var3, var4));
      var5.setColorOpaque_F(var11, var11, var11);
      var13 = this.getBlockIcon(var1, this.blockAccess, var2, var3, var4, 5);
      this.renderFaceXPos(var1, (double)var2, (double)var3, (double)var4, var13);
      var7 = true;
      this.flipTexture = false;
      return var7;
   }

   public void renderFaceYNeg(Block var1, double var2, double var4, double var6, IIcon var8) {
      Tessellator var9 = Tessellator.instance;
      if(this.hasOverrideBlockTexture()) {
         var8 = this.overrideBlockTexture;
      }

      double var10 = (double)var8.getInterpolatedU(this.renderMinX * 16.0D);
      double var12 = (double)var8.getInterpolatedU(this.renderMaxX * 16.0D);
      double var14 = (double)var8.getInterpolatedV(this.renderMinZ * 16.0D);
      double var16 = (double)var8.getInterpolatedV(this.renderMaxZ * 16.0D);
      if(this.renderMinX < 0.0D || this.renderMaxX > 1.0D) {
         var10 = (double)var8.getMinU();
         var12 = (double)var8.getMaxU();
      }

      if(this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D) {
         var14 = (double)var8.getMinV();
         var16 = (double)var8.getMaxV();
      }

      double var18 = var12;
      double var20 = var10;
      double var22 = var14;
      double var24 = var16;
      if(this.uvRotateBottom == 2) {
         var10 = (double)var8.getInterpolatedU(this.renderMinZ * 16.0D);
         var14 = (double)var8.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
         var12 = (double)var8.getInterpolatedU(this.renderMaxZ * 16.0D);
         var16 = (double)var8.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
         var22 = var14;
         var24 = var16;
         var18 = var10;
         var20 = var12;
         var14 = var16;
         var16 = var22;
      } else if(this.uvRotateBottom == 1) {
         var10 = (double)var8.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
         var14 = (double)var8.getInterpolatedV(this.renderMinX * 16.0D);
         var12 = (double)var8.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
         var16 = (double)var8.getInterpolatedV(this.renderMaxX * 16.0D);
         var18 = var12;
         var20 = var10;
         var10 = var12;
         var12 = var20;
         var22 = var16;
         var24 = var14;
      } else if(this.uvRotateBottom == 3) {
         var10 = (double)var8.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
         var12 = (double)var8.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
         var14 = (double)var8.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
         var16 = (double)var8.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
         var18 = var12;
         var20 = var10;
         var22 = var14;
         var24 = var16;
      }

      double var26 = var2 + this.renderMinX;
      double var28 = var2 + this.renderMaxX;
      double var30 = var4 + this.renderMinY;
      double var32 = var6 + this.renderMinZ;
      double var34 = var6 + this.renderMaxZ;
      if(this.renderFromInside) {
         var26 = var2 + this.renderMaxX;
         var28 = var2 + this.renderMinX;
      }

      if(this.enableAO) {
         var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
         var9.setBrightness(this.brightnessTopLeft);
         var9.addVertexWithUV(var26, var30, var34, var20, var24);
         var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
         var9.setBrightness(this.brightnessBottomLeft);
         var9.addVertexWithUV(var26, var30, var32, var10, var14);
         var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
         var9.setBrightness(this.brightnessBottomRight);
         var9.addVertexWithUV(var28, var30, var32, var18, var22);
         var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
         var9.setBrightness(this.brightnessTopRight);
         var9.addVertexWithUV(var28, var30, var34, var12, var16);
      } else {
         var9.addVertexWithUV(var26, var30, var34, var20, var24);
         var9.addVertexWithUV(var26, var30, var32, var10, var14);
         var9.addVertexWithUV(var28, var30, var32, var18, var22);
         var9.addVertexWithUV(var28, var30, var34, var12, var16);
      }

   }

   public void renderFaceYPos(Block var1, double var2, double var4, double var6, IIcon var8) {
      Tessellator var9 = Tessellator.instance;
      if(this.hasOverrideBlockTexture()) {
         var8 = this.overrideBlockTexture;
      }

      double var10 = (double)var8.getInterpolatedU(this.renderMinX * 16.0D);
      double var12 = (double)var8.getInterpolatedU(this.renderMaxX * 16.0D);
      double var14 = (double)var8.getInterpolatedV(this.renderMinZ * 16.0D);
      double var16 = (double)var8.getInterpolatedV(this.renderMaxZ * 16.0D);
      if(this.renderMinX < 0.0D || this.renderMaxX > 1.0D) {
         var10 = (double)var8.getMinU();
         var12 = (double)var8.getMaxU();
      }

      if(this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D) {
         var14 = (double)var8.getMinV();
         var16 = (double)var8.getMaxV();
      }

      double var18 = var12;
      double var20 = var10;
      double var22 = var14;
      double var24 = var16;
      if(this.uvRotateTop == 1) {
         var10 = (double)var8.getInterpolatedU(this.renderMinZ * 16.0D);
         var14 = (double)var8.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
         var12 = (double)var8.getInterpolatedU(this.renderMaxZ * 16.0D);
         var16 = (double)var8.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
         var22 = var14;
         var24 = var16;
         var18 = var10;
         var20 = var12;
         var14 = var16;
         var16 = var22;
      } else if(this.uvRotateTop == 2) {
         var10 = (double)var8.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
         var14 = (double)var8.getInterpolatedV(this.renderMinX * 16.0D);
         var12 = (double)var8.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
         var16 = (double)var8.getInterpolatedV(this.renderMaxX * 16.0D);
         var18 = var12;
         var20 = var10;
         var10 = var12;
         var12 = var20;
         var22 = var16;
         var24 = var14;
      } else if(this.uvRotateTop == 3) {
         var10 = (double)var8.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
         var12 = (double)var8.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
         var14 = (double)var8.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
         var16 = (double)var8.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
         var18 = var12;
         var20 = var10;
         var22 = var14;
         var24 = var16;
      }

      double var26 = var2 + this.renderMinX;
      double var28 = var2 + this.renderMaxX;
      double var30 = var4 + this.renderMaxY;
      double var32 = var6 + this.renderMinZ;
      double var34 = var6 + this.renderMaxZ;
      if(this.renderFromInside) {
         var26 = var2 + this.renderMaxX;
         var28 = var2 + this.renderMinX;
      }

      if(this.enableAO) {
         var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
         var9.setBrightness(this.brightnessTopLeft);
         var9.addVertexWithUV(var28, var30, var34, var12, var16);
         var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
         var9.setBrightness(this.brightnessBottomLeft);
         var9.addVertexWithUV(var28, var30, var32, var18, var22);
         var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
         var9.setBrightness(this.brightnessBottomRight);
         var9.addVertexWithUV(var26, var30, var32, var10, var14);
         var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
         var9.setBrightness(this.brightnessTopRight);
         var9.addVertexWithUV(var26, var30, var34, var20, var24);
      } else {
         var9.addVertexWithUV(var28, var30, var34, var12, var16);
         var9.addVertexWithUV(var28, var30, var32, var18, var22);
         var9.addVertexWithUV(var26, var30, var32, var10, var14);
         var9.addVertexWithUV(var26, var30, var34, var20, var24);
      }

   }

   public void renderFaceZNeg(Block var1, double var2, double var4, double var6, IIcon var8) {
      Tessellator var9 = Tessellator.instance;
      if(this.hasOverrideBlockTexture()) {
         var8 = this.overrideBlockTexture;
      }

      double var10 = (double)var8.getInterpolatedU(this.renderMinX * 16.0D);
      double var12 = (double)var8.getInterpolatedU(this.renderMaxX * 16.0D);
      if(this.field_152631_f) {
         var12 = (double)var8.getInterpolatedU((1.0D - this.renderMinX) * 16.0D);
         var10 = (double)var8.getInterpolatedU((1.0D - this.renderMaxX) * 16.0D);
      }

      double var14 = (double)var8.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
      double var16 = (double)var8.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
      double var18;
      if(this.flipTexture) {
         var18 = var10;
         var10 = var12;
         var12 = var18;
      }

      if(this.renderMinX < 0.0D || this.renderMaxX > 1.0D) {
         var10 = (double)var8.getMinU();
         var12 = (double)var8.getMaxU();
      }

      if(this.renderMinY < 0.0D || this.renderMaxY > 1.0D) {
         var14 = (double)var8.getMinV();
         var16 = (double)var8.getMaxV();
      }

      var18 = var12;
      double var20 = var10;
      double var22 = var14;
      double var24 = var16;
      if(this.uvRotateEast == 2) {
         var10 = (double)var8.getInterpolatedU(this.renderMinY * 16.0D);
         var12 = (double)var8.getInterpolatedU(this.renderMaxY * 16.0D);
         var14 = (double)var8.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
         var16 = (double)var8.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
         var22 = var14;
         var24 = var16;
         var18 = var10;
         var20 = var12;
         var14 = var16;
         var16 = var22;
      } else if(this.uvRotateEast == 1) {
         var10 = (double)var8.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
         var12 = (double)var8.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
         var14 = (double)var8.getInterpolatedV(this.renderMaxX * 16.0D);
         var16 = (double)var8.getInterpolatedV(this.renderMinX * 16.0D);
         var18 = var12;
         var20 = var10;
         var10 = var12;
         var12 = var20;
         var22 = var16;
         var24 = var14;
      } else if(this.uvRotateEast == 3) {
         var10 = (double)var8.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
         var12 = (double)var8.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
         var14 = (double)var8.getInterpolatedV(this.renderMaxY * 16.0D);
         var16 = (double)var8.getInterpolatedV(this.renderMinY * 16.0D);
         var18 = var12;
         var20 = var10;
         var22 = var14;
         var24 = var16;
      }

      double var26 = var2 + this.renderMinX;
      double var28 = var2 + this.renderMaxX;
      double var30 = var4 + this.renderMinY;
      double var32 = var4 + this.renderMaxY;
      double var34 = var6 + this.renderMinZ;
      if(this.renderFromInside) {
         var26 = var2 + this.renderMaxX;
         var28 = var2 + this.renderMinX;
      }

      if(this.enableAO) {
         var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
         var9.setBrightness(this.brightnessTopLeft);
         var9.addVertexWithUV(var26, var32, var34, var18, var22);
         var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
         var9.setBrightness(this.brightnessBottomLeft);
         var9.addVertexWithUV(var28, var32, var34, var10, var14);
         var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
         var9.setBrightness(this.brightnessBottomRight);
         var9.addVertexWithUV(var28, var30, var34, var20, var24);
         var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
         var9.setBrightness(this.brightnessTopRight);
         var9.addVertexWithUV(var26, var30, var34, var12, var16);
      } else {
         var9.addVertexWithUV(var26, var32, var34, var18, var22);
         var9.addVertexWithUV(var28, var32, var34, var10, var14);
         var9.addVertexWithUV(var28, var30, var34, var20, var24);
         var9.addVertexWithUV(var26, var30, var34, var12, var16);
      }

   }

   public void renderFaceZPos(Block var1, double var2, double var4, double var6, IIcon var8) {
      Tessellator var9 = Tessellator.instance;
      if(this.hasOverrideBlockTexture()) {
         var8 = this.overrideBlockTexture;
      }

      double var10 = (double)var8.getInterpolatedU(this.renderMinX * 16.0D);
      double var12 = (double)var8.getInterpolatedU(this.renderMaxX * 16.0D);
      double var14 = (double)var8.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
      double var16 = (double)var8.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
      double var18;
      if(this.flipTexture) {
         var18 = var10;
         var10 = var12;
         var12 = var18;
      }

      if(this.renderMinX < 0.0D || this.renderMaxX > 1.0D) {
         var10 = (double)var8.getMinU();
         var12 = (double)var8.getMaxU();
      }

      if(this.renderMinY < 0.0D || this.renderMaxY > 1.0D) {
         var14 = (double)var8.getMinV();
         var16 = (double)var8.getMaxV();
      }

      var18 = var12;
      double var20 = var10;
      double var22 = var14;
      double var24 = var16;
      if(this.uvRotateWest == 1) {
         var10 = (double)var8.getInterpolatedU(this.renderMinY * 16.0D);
         var16 = (double)var8.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
         var12 = (double)var8.getInterpolatedU(this.renderMaxY * 16.0D);
         var14 = (double)var8.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
         var22 = var14;
         var24 = var16;
         var18 = var10;
         var20 = var12;
         var14 = var16;
         var16 = var22;
      } else if(this.uvRotateWest == 2) {
         var10 = (double)var8.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
         var14 = (double)var8.getInterpolatedV(this.renderMinX * 16.0D);
         var12 = (double)var8.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
         var16 = (double)var8.getInterpolatedV(this.renderMaxX * 16.0D);
         var18 = var12;
         var20 = var10;
         var10 = var12;
         var12 = var20;
         var22 = var16;
         var24 = var14;
      } else if(this.uvRotateWest == 3) {
         var10 = (double)var8.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
         var12 = (double)var8.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
         var14 = (double)var8.getInterpolatedV(this.renderMaxY * 16.0D);
         var16 = (double)var8.getInterpolatedV(this.renderMinY * 16.0D);
         var18 = var12;
         var20 = var10;
         var22 = var14;
         var24 = var16;
      }

      double var26 = var2 + this.renderMinX;
      double var28 = var2 + this.renderMaxX;
      double var30 = var4 + this.renderMinY;
      double var32 = var4 + this.renderMaxY;
      double var34 = var6 + this.renderMaxZ;
      if(this.renderFromInside) {
         var26 = var2 + this.renderMaxX;
         var28 = var2 + this.renderMinX;
      }

      if(this.enableAO) {
         var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
         var9.setBrightness(this.brightnessTopLeft);
         var9.addVertexWithUV(var26, var32, var34, var10, var14);
         var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
         var9.setBrightness(this.brightnessBottomLeft);
         var9.addVertexWithUV(var26, var30, var34, var20, var24);
         var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
         var9.setBrightness(this.brightnessBottomRight);
         var9.addVertexWithUV(var28, var30, var34, var12, var16);
         var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
         var9.setBrightness(this.brightnessTopRight);
         var9.addVertexWithUV(var28, var32, var34, var18, var22);
      } else {
         var9.addVertexWithUV(var26, var32, var34, var10, var14);
         var9.addVertexWithUV(var26, var30, var34, var20, var24);
         var9.addVertexWithUV(var28, var30, var34, var12, var16);
         var9.addVertexWithUV(var28, var32, var34, var18, var22);
      }

   }

   public void renderFaceXNeg(Block var1, double var2, double var4, double var6, IIcon var8) {
      Tessellator var9 = Tessellator.instance;
      if(this.hasOverrideBlockTexture()) {
         var8 = this.overrideBlockTexture;
      }

      double var10 = (double)var8.getInterpolatedU(this.renderMinZ * 16.0D);
      double var12 = (double)var8.getInterpolatedU(this.renderMaxZ * 16.0D);
      double var14 = (double)var8.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
      double var16 = (double)var8.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
      double var18;
      if(this.flipTexture) {
         var18 = var10;
         var10 = var12;
         var12 = var18;
      }

      if(this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D) {
         var10 = (double)var8.getMinU();
         var12 = (double)var8.getMaxU();
      }

      if(this.renderMinY < 0.0D || this.renderMaxY > 1.0D) {
         var14 = (double)var8.getMinV();
         var16 = (double)var8.getMaxV();
      }

      var18 = var12;
      double var20 = var10;
      double var22 = var14;
      double var24 = var16;
      if(this.uvRotateNorth == 1) {
         var10 = (double)var8.getInterpolatedU(this.renderMinY * 16.0D);
         var14 = (double)var8.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
         var12 = (double)var8.getInterpolatedU(this.renderMaxY * 16.0D);
         var16 = (double)var8.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
         var22 = var14;
         var24 = var16;
         var18 = var10;
         var20 = var12;
         var14 = var16;
         var16 = var22;
      } else if(this.uvRotateNorth == 2) {
         var10 = (double)var8.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
         var14 = (double)var8.getInterpolatedV(this.renderMinZ * 16.0D);
         var12 = (double)var8.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
         var16 = (double)var8.getInterpolatedV(this.renderMaxZ * 16.0D);
         var18 = var12;
         var20 = var10;
         var10 = var12;
         var12 = var20;
         var22 = var16;
         var24 = var14;
      } else if(this.uvRotateNorth == 3) {
         var10 = (double)var8.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
         var12 = (double)var8.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
         var14 = (double)var8.getInterpolatedV(this.renderMaxY * 16.0D);
         var16 = (double)var8.getInterpolatedV(this.renderMinY * 16.0D);
         var18 = var12;
         var20 = var10;
         var22 = var14;
         var24 = var16;
      }

      double var26 = var2 + this.renderMinX;
      double var28 = var4 + this.renderMinY;
      double var30 = var4 + this.renderMaxY;
      double var32 = var6 + this.renderMinZ;
      double var34 = var6 + this.renderMaxZ;
      if(this.renderFromInside) {
         var32 = var6 + this.renderMaxZ;
         var34 = var6 + this.renderMinZ;
      }

      if(this.enableAO) {
         var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
         var9.setBrightness(this.brightnessTopLeft);
         var9.addVertexWithUV(var26, var30, var34, var18, var22);
         var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
         var9.setBrightness(this.brightnessBottomLeft);
         var9.addVertexWithUV(var26, var30, var32, var10, var14);
         var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
         var9.setBrightness(this.brightnessBottomRight);
         var9.addVertexWithUV(var26, var28, var32, var20, var24);
         var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
         var9.setBrightness(this.brightnessTopRight);
         var9.addVertexWithUV(var26, var28, var34, var12, var16);
      } else {
         var9.addVertexWithUV(var26, var30, var34, var18, var22);
         var9.addVertexWithUV(var26, var30, var32, var10, var14);
         var9.addVertexWithUV(var26, var28, var32, var20, var24);
         var9.addVertexWithUV(var26, var28, var34, var12, var16);
      }

   }

   public void renderFaceXPos(Block var1, double var2, double var4, double var6, IIcon var8) {
      Tessellator var9 = Tessellator.instance;
      if(this.hasOverrideBlockTexture()) {
         var8 = this.overrideBlockTexture;
      }

      double var10 = (double)var8.getInterpolatedU(this.renderMinZ * 16.0D);
      double var12 = (double)var8.getInterpolatedU(this.renderMaxZ * 16.0D);
      if(this.field_152631_f) {
         var12 = (double)var8.getInterpolatedU((1.0D - this.renderMinZ) * 16.0D);
         var10 = (double)var8.getInterpolatedU((1.0D - this.renderMaxZ) * 16.0D);
      }

      double var14 = (double)var8.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
      double var16 = (double)var8.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
      double var18;
      if(this.flipTexture) {
         var18 = var10;
         var10 = var12;
         var12 = var18;
      }

      if(this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D) {
         var10 = (double)var8.getMinU();
         var12 = (double)var8.getMaxU();
      }

      if(this.renderMinY < 0.0D || this.renderMaxY > 1.0D) {
         var14 = (double)var8.getMinV();
         var16 = (double)var8.getMaxV();
      }

      var18 = var12;
      double var20 = var10;
      double var22 = var14;
      double var24 = var16;
      if(this.uvRotateSouth == 2) {
         var10 = (double)var8.getInterpolatedU(this.renderMinY * 16.0D);
         var14 = (double)var8.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
         var12 = (double)var8.getInterpolatedU(this.renderMaxY * 16.0D);
         var16 = (double)var8.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
         var22 = var14;
         var24 = var16;
         var18 = var10;
         var20 = var12;
         var14 = var16;
         var16 = var22;
      } else if(this.uvRotateSouth == 1) {
         var10 = (double)var8.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
         var14 = (double)var8.getInterpolatedV(this.renderMaxZ * 16.0D);
         var12 = (double)var8.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
         var16 = (double)var8.getInterpolatedV(this.renderMinZ * 16.0D);
         var18 = var12;
         var20 = var10;
         var10 = var12;
         var12 = var20;
         var22 = var16;
         var24 = var14;
      } else if(this.uvRotateSouth == 3) {
         var10 = (double)var8.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
         var12 = (double)var8.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
         var14 = (double)var8.getInterpolatedV(this.renderMaxY * 16.0D);
         var16 = (double)var8.getInterpolatedV(this.renderMinY * 16.0D);
         var18 = var12;
         var20 = var10;
         var22 = var14;
         var24 = var16;
      }

      double var26 = var2 + this.renderMaxX;
      double var28 = var4 + this.renderMinY;
      double var30 = var4 + this.renderMaxY;
      double var32 = var6 + this.renderMinZ;
      double var34 = var6 + this.renderMaxZ;
      if(this.renderFromInside) {
         var32 = var6 + this.renderMaxZ;
         var34 = var6 + this.renderMinZ;
      }

      if(this.enableAO) {
         var9.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
         var9.setBrightness(this.brightnessTopLeft);
         var9.addVertexWithUV(var26, var28, var34, var20, var24);
         var9.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
         var9.setBrightness(this.brightnessBottomLeft);
         var9.addVertexWithUV(var26, var28, var32, var12, var16);
         var9.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight, this.colorBlueBottomRight);
         var9.setBrightness(this.brightnessBottomRight);
         var9.addVertexWithUV(var26, var30, var32, var18, var22);
         var9.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
         var9.setBrightness(this.brightnessTopRight);
         var9.addVertexWithUV(var26, var30, var34, var10, var14);
      } else {
         var9.addVertexWithUV(var26, var28, var34, var20, var24);
         var9.addVertexWithUV(var26, var28, var32, var12, var16);
         var9.addVertexWithUV(var26, var30, var32, var18, var22);
         var9.addVertexWithUV(var26, var30, var34, var10, var14);
      }

   }

   public void renderBlockAsItem(Block var1, int var2, float var3) {
      Tessellator var4 = Tessellator.instance;
      boolean var5 = var1 == Blocks.grass;
      if(var1 == Blocks.dispenser || var1 == Blocks.dropper || var1 == Blocks.furnace) {
         var2 = 3;
      }

      int var6;
      float var7;
      float var8;
      float var9;
      if(this.useInventoryTint) {
         var6 = var1.getRenderColor(var2);
         if(var5) {
            var6 = 16777215;
         }

         var7 = (float)(var6 >> 16 & 255) / 255.0F;
         var8 = (float)(var6 >> 8 & 255) / 255.0F;
         var9 = (float)(var6 & 255) / 255.0F;
         GL11.glColor4f(var7 * var3, var8 * var3, var9 * var3, 1.0F);
      }

      var6 = var1.getRenderType();
      this.setRenderBoundsFromBlock(var1);
      int var14;
      if(var6 != 0 && var6 != 31 && var6 != 39 && var6 != 16 && var6 != 26) {
         if(var6 == 1) {
            var4.startDrawingQuads();
            var4.setNormal(0.0F, -1.0F, 0.0F);
            IIcon var15 = this.getBlockIconFromSideAndMetadata(var1, 0, var2);
            this.drawCrossedSquares(var15, -0.5D, -0.5D, -0.5D, 1.0F);
            var4.draw();
         } else if(var6 == 19) {
            var4.startDrawingQuads();
            var4.setNormal(0.0F, -1.0F, 0.0F);
            var1.setBlockBoundsForItemRender();
            this.renderBlockStemSmall(var1, var2, this.renderMaxY, -0.5D, -0.5D, -0.5D);
            var4.draw();
         } else if(var6 == 23) {
            var4.startDrawingQuads();
            var4.setNormal(0.0F, -1.0F, 0.0F);
            var1.setBlockBoundsForItemRender();
            var4.draw();
         } else if(var6 == 13) {
            var1.setBlockBoundsForItemRender();
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            var7 = 0.0625F;
            var4.startDrawingQuads();
            var4.setNormal(0.0F, -1.0F, 0.0F);
            this.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 0));
            var4.draw();
            var4.startDrawingQuads();
            var4.setNormal(0.0F, 1.0F, 0.0F);
            this.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 1));
            var4.draw();
            var4.startDrawingQuads();
            var4.setNormal(0.0F, 0.0F, -1.0F);
            var4.addTranslation(0.0F, 0.0F, var7);
            this.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 2));
            var4.addTranslation(0.0F, 0.0F, -var7);
            var4.draw();
            var4.startDrawingQuads();
            var4.setNormal(0.0F, 0.0F, 1.0F);
            var4.addTranslation(0.0F, 0.0F, -var7);
            this.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 3));
            var4.addTranslation(0.0F, 0.0F, var7);
            var4.draw();
            var4.startDrawingQuads();
            var4.setNormal(-1.0F, 0.0F, 0.0F);
            var4.addTranslation(var7, 0.0F, 0.0F);
            this.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 4));
            var4.addTranslation(-var7, 0.0F, 0.0F);
            var4.draw();
            var4.startDrawingQuads();
            var4.setNormal(1.0F, 0.0F, 0.0F);
            var4.addTranslation(-var7, 0.0F, 0.0F);
            this.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 5));
            var4.addTranslation(var7, 0.0F, 0.0F);
            var4.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
         } else if(var6 == 22) {
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            TileEntityRendererChestHelper.instance.renderChest(var1, var2, var3);
            GL11.glEnable('\u803a');
         } else if(var6 == 6) {
            var4.startDrawingQuads();
            var4.setNormal(0.0F, -1.0F, 0.0F);
            this.renderBlockCropsImpl(var1, var2, -0.5D, -0.5D, -0.5D);
            var4.draw();
         } else if(var6 == 2) {
            var4.startDrawingQuads();
            var4.setNormal(0.0F, -1.0F, 0.0F);
            this.renderTorchAtAngle(var1, -0.5D, -0.5D, -0.5D, 0.0D, 0.0D, 0);
            var4.draw();
         } else if(var6 == 10) {
            for(var14 = 0; var14 < 2; ++var14) {
               if(var14 == 0) {
                  this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
               }

               if(var14 == 1) {
                  this.setRenderBounds(0.0D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
               }

               GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
               var4.startDrawingQuads();
               var4.setNormal(0.0F, -1.0F, 0.0F);
               this.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 0));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 1.0F, 0.0F);
               this.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 1));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 0.0F, -1.0F);
               this.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 0.0F, 1.0F);
               this.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 3));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(-1.0F, 0.0F, 0.0F);
               this.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 4));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(1.0F, 0.0F, 0.0F);
               this.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 5));
               var4.draw();
               GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }
         } else if(var6 == 27) {
            var14 = 0;
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            var4.startDrawingQuads();

            for(int var16 = 0; var16 < 8; ++var16) {
               byte var17 = 0;
               byte var18 = 1;
               if(var16 == 0) {
                  var17 = 2;
               }

               if(var16 == 1) {
                  var17 = 3;
               }

               if(var16 == 2) {
                  var17 = 4;
               }

               if(var16 == 3) {
                  var17 = 5;
                  var18 = 2;
               }

               if(var16 == 4) {
                  var17 = 6;
                  var18 = 3;
               }

               if(var16 == 5) {
                  var17 = 7;
                  var18 = 5;
               }

               if(var16 == 6) {
                  var17 = 6;
                  var18 = 2;
               }

               if(var16 == 7) {
                  var17 = 3;
               }

               float var11 = (float)var17 / 16.0F;
               float var12 = 1.0F - (float)var14 / 16.0F;
               float var13 = 1.0F - (float)(var14 + var18) / 16.0F;
               var14 += var18;
               this.setRenderBounds((double)(0.5F - var11), (double)var13, (double)(0.5F - var11), (double)(0.5F + var11), (double)var12, (double)(0.5F + var11));
               var4.setNormal(0.0F, -1.0F, 0.0F);
               this.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 0));
               var4.setNormal(0.0F, 1.0F, 0.0F);
               this.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 1));
               var4.setNormal(0.0F, 0.0F, -1.0F);
               this.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 2));
               var4.setNormal(0.0F, 0.0F, 1.0F);
               this.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 3));
               var4.setNormal(-1.0F, 0.0F, 0.0F);
               this.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 4));
               var4.setNormal(1.0F, 0.0F, 0.0F);
               this.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 5));
            }

            var4.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
         } else if(var6 == 11) {
            for(var14 = 0; var14 < 4; ++var14) {
               var8 = 0.125F;
               if(var14 == 0) {
                  this.setRenderBounds((double)(0.5F - var8), 0.0D, 0.0D, (double)(0.5F + var8), 1.0D, (double)(var8 * 2.0F));
               }

               if(var14 == 1) {
                  this.setRenderBounds((double)(0.5F - var8), 0.0D, (double)(1.0F - var8 * 2.0F), (double)(0.5F + var8), 1.0D, 1.0D);
               }

               var8 = 0.0625F;
               if(var14 == 2) {
                  this.setRenderBounds((double)(0.5F - var8), (double)(1.0F - var8 * 3.0F), (double)(-var8 * 2.0F), (double)(0.5F + var8), (double)(1.0F - var8), (double)(1.0F + var8 * 2.0F));
               }

               if(var14 == 3) {
                  this.setRenderBounds((double)(0.5F - var8), (double)(0.5F - var8 * 3.0F), (double)(-var8 * 2.0F), (double)(0.5F + var8), (double)(0.5F - var8), (double)(1.0F + var8 * 2.0F));
               }

               GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
               var4.startDrawingQuads();
               var4.setNormal(0.0F, -1.0F, 0.0F);
               this.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 0));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 1.0F, 0.0F);
               this.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 1));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 0.0F, -1.0F);
               this.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 0.0F, 1.0F);
               this.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 3));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(-1.0F, 0.0F, 0.0F);
               this.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 4));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(1.0F, 0.0F, 0.0F);
               this.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 5));
               var4.draw();
               GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }

            this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
         } else if(var6 == 21) {
            for(var14 = 0; var14 < 3; ++var14) {
               var8 = 0.0625F;
               if(var14 == 0) {
                  this.setRenderBounds((double)(0.5F - var8), 0.30000001192092896D, 0.0D, (double)(0.5F + var8), 1.0D, (double)(var8 * 2.0F));
               }

               if(var14 == 1) {
                  this.setRenderBounds((double)(0.5F - var8), 0.30000001192092896D, (double)(1.0F - var8 * 2.0F), (double)(0.5F + var8), 1.0D, 1.0D);
               }

               var8 = 0.0625F;
               if(var14 == 2) {
                  this.setRenderBounds((double)(0.5F - var8), 0.5D, 0.0D, (double)(0.5F + var8), (double)(1.0F - var8), 1.0D);
               }

               GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
               var4.startDrawingQuads();
               var4.setNormal(0.0F, -1.0F, 0.0F);
               this.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 0));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 1.0F, 0.0F);
               this.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 1));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 0.0F, -1.0F);
               this.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 0.0F, 1.0F);
               this.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 3));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(-1.0F, 0.0F, 0.0F);
               this.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 4));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(1.0F, 0.0F, 0.0F);
               this.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSide(var1, 5));
               var4.draw();
               GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }
         } else if(var6 == 32) {
            for(var14 = 0; var14 < 2; ++var14) {
               if(var14 == 0) {
                  this.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
               }

               if(var14 == 1) {
                  this.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
               }

               GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
               var4.startDrawingQuads();
               var4.setNormal(0.0F, -1.0F, 0.0F);
               this.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 0, var2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 1.0F, 0.0F);
               this.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 1, var2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 0.0F, -1.0F);
               this.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 2, var2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 0.0F, 1.0F);
               this.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 3, var2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(-1.0F, 0.0F, 0.0F);
               this.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 4, var2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(1.0F, 0.0F, 0.0F);
               this.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 5, var2));
               var4.draw();
               GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }

            this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
         } else if(var6 == 35) {
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            this.renderBlockAnvilOrient((BlockAnvil)var1, 0, 0, 0, var2 << 2, true);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
         } else if(var6 == 34) {
            for(var14 = 0; var14 < 3; ++var14) {
               if(var14 == 0) {
                  this.setRenderBounds(0.125D, 0.0D, 0.125D, 0.875D, 0.1875D, 0.875D);
                  this.setOverrideBlockTexture(this.getBlockIcon(Blocks.obsidian));
               } else if(var14 == 1) {
                  this.setRenderBounds(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
                  this.setOverrideBlockTexture(this.getBlockIcon(Blocks.beacon));
               } else if(var14 == 2) {
                  this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
                  this.setOverrideBlockTexture(this.getBlockIcon(Blocks.glass));
               }

               GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
               var4.startDrawingQuads();
               var4.setNormal(0.0F, -1.0F, 0.0F);
               this.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 0, var2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 1.0F, 0.0F);
               this.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 1, var2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 0.0F, -1.0F);
               this.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 2, var2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(0.0F, 0.0F, 1.0F);
               this.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 3, var2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(-1.0F, 0.0F, 0.0F);
               this.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 4, var2));
               var4.draw();
               var4.startDrawingQuads();
               var4.setNormal(1.0F, 0.0F, 0.0F);
               this.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 5, var2));
               var4.draw();
               GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }

            this.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            this.clearOverrideBlockTexture();
         } else if(var6 == 38) {
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            this.renderBlockHopperMetadata((BlockHopper)var1, 0, 0, 0, 0, true);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
         }
      } else {
         if(var6 == 16) {
            var2 = 1;
         }

         var1.setBlockBoundsForItemRender();
         this.setRenderBoundsFromBlock(var1);
         GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
         var4.startDrawingQuads();
         var4.setNormal(0.0F, -1.0F, 0.0F);
         this.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 0, var2));
         var4.draw();
         if(var5 && this.useInventoryTint) {
            var14 = var1.getRenderColor(var2);
            var8 = (float)(var14 >> 16 & 255) / 255.0F;
            var9 = (float)(var14 >> 8 & 255) / 255.0F;
            float var10 = (float)(var14 & 255) / 255.0F;
            GL11.glColor4f(var8 * var3, var9 * var3, var10 * var3, 1.0F);
         }

         var4.startDrawingQuads();
         var4.setNormal(0.0F, 1.0F, 0.0F);
         this.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 1, var2));
         var4.draw();
         if(var5 && this.useInventoryTint) {
            GL11.glColor4f(var3, var3, var3, 1.0F);
         }

         var4.startDrawingQuads();
         var4.setNormal(0.0F, 0.0F, -1.0F);
         this.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 2, var2));
         var4.draw();
         var4.startDrawingQuads();
         var4.setNormal(0.0F, 0.0F, 1.0F);
         this.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 3, var2));
         var4.draw();
         var4.startDrawingQuads();
         var4.setNormal(-1.0F, 0.0F, 0.0F);
         this.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 4, var2));
         var4.draw();
         var4.startDrawingQuads();
         var4.setNormal(1.0F, 0.0F, 0.0F);
         this.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(var1, 5, var2));
         var4.draw();
         GL11.glTranslatef(0.5F, 0.5F, 0.5F);
      }

   }

   public static boolean renderItemIn3d(int var0) {
      return var0 == 0?true:(var0 == 31?true:(var0 == 39?true:(var0 == 13?true:(var0 == 10?true:(var0 == 11?true:(var0 == 27?true:(var0 == 22?true:(var0 == 21?true:(var0 == 16?true:(var0 == 26?true:(var0 == 32?true:(var0 == 34?true:(var0 == 35?true:(var0 == -1?false:false))))))))))))));
   }

   public IIcon getBlockIcon(Block var1, IBlockAccess var2, int var3, int var4, int var5, int var6) {
      return this.getIconSafe(var1.getIcon(var2, var3, var4, var5, var6));
   }

   public IIcon getBlockIconFromSideAndMetadata(Block var1, int var2, int var3) {
      return this.getIconSafe(var1.getIcon(var2, var3));
   }

   public IIcon getBlockIconFromSide(Block var1, int var2) {
      return this.getIconSafe(var1.getBlockTextureFromSide(var2));
   }

   public IIcon getBlockIcon(Block var1) {
      return this.getIconSafe(var1.getBlockTextureFromSide(1));
   }

   public IIcon getIconSafe(IIcon var1) {
      if(var1 == null) {
         var1 = ((TextureMap)Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
      }

      return (IIcon)var1;
   }

}
