package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonBase extends Block {

   private final boolean isSticky;
   private IIcon innerTopIcon;
   private IIcon bottomIcon;
   private IIcon topIcon;


   public BlockPistonBase(boolean var1) {
      super(Material.piston);
      this.isSticky = var1;
      this.setStepSound(Block.soundTypePiston);
      this.setHardness(0.5F);
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   public IIcon getPistonExtensionTexture() {
      return this.topIcon;
   }

   public void func_150070_b(float var1, float var2, float var3, float var4, float var5, float var6) {
      this.setBlockBounds(var1, var2, var3, var4, var5, var6);
   }

   public IIcon getIcon(int var1, int var2) {
      int var3 = getPistonOrientation(var2);
      return var3 > 5?this.topIcon:(var1 == var3?(!isExtended(var2) && super.minX <= 0.0D && super.minY <= 0.0D && super.minZ <= 0.0D && super.maxX >= 1.0D && super.maxY >= 1.0D && super.maxZ >= 1.0D?this.topIcon:this.innerTopIcon):(var1 == Facing.oppositeSide[var3]?this.bottomIcon:super.blockIcon));
   }

   public static IIcon getPistonBaseIcon(String var0) {
      return var0 == "piston_side"?Blocks.piston.blockIcon:(var0 == "piston_top_normal"?Blocks.piston.topIcon:(var0 == "piston_top_sticky"?Blocks.sticky_piston.topIcon:(var0 == "piston_inner"?Blocks.piston.innerTopIcon:null)));
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon("piston_side");
      this.topIcon = var1.registerIcon(this.isSticky?"piston_top_sticky":"piston_top_normal");
      this.innerTopIcon = var1.registerIcon("piston_inner");
      this.bottomIcon = var1.registerIcon("piston_bottom");
   }

   public int getRenderType() {
      return 16;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      return false;
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      int var7 = determineOrientation(var1, var2, var3, var4, var5);
      var1.setBlockMetadataWithNotify(var2, var3, var4, var7, 2);
      if(!var1.isRemote) {
         this.updatePistonState(var1, var2, var3, var4);
      }

   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!var1.isRemote) {
         this.updatePistonState(var1, var2, var3, var4);
      }

   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      if(!var1.isRemote && var1.getTileEntity(var2, var3, var4) == null) {
         this.updatePistonState(var1, var2, var3, var4);
      }

   }

   private void updatePistonState(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      int var6 = getPistonOrientation(var5);
      if(var6 != 7) {
         boolean var7 = this.isIndirectlyPowered(var1, var2, var3, var4, var6);
         if(var7 && !isExtended(var5)) {
            if(canExtend(var1, var2, var3, var4, var6)) {
               var1.addBlockEvent(var2, var3, var4, this, 0, var6);
            }
         } else if(!var7 && isExtended(var5)) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 2);
            var1.addBlockEvent(var2, var3, var4, this, 1, var6);
         }

      }
   }

   private boolean isIndirectlyPowered(World var1, int var2, int var3, int var4, int var5) {
      return var5 != 0 && var1.getIndirectPowerOutput(var2, var3 - 1, var4, 0)?true:(var5 != 1 && var1.getIndirectPowerOutput(var2, var3 + 1, var4, 1)?true:(var5 != 2 && var1.getIndirectPowerOutput(var2, var3, var4 - 1, 2)?true:(var5 != 3 && var1.getIndirectPowerOutput(var2, var3, var4 + 1, 3)?true:(var5 != 5 && var1.getIndirectPowerOutput(var2 + 1, var3, var4, 5)?true:(var5 != 4 && var1.getIndirectPowerOutput(var2 - 1, var3, var4, 4)?true:(var1.getIndirectPowerOutput(var2, var3, var4, 0)?true:(var1.getIndirectPowerOutput(var2, var3 + 2, var4, 1)?true:(var1.getIndirectPowerOutput(var2, var3 + 1, var4 - 1, 2)?true:(var1.getIndirectPowerOutput(var2, var3 + 1, var4 + 1, 3)?true:(var1.getIndirectPowerOutput(var2 - 1, var3 + 1, var4, 4)?true:var1.getIndirectPowerOutput(var2 + 1, var3 + 1, var4, 5)))))))))));
   }

   public boolean onBlockEventReceived(World var1, int var2, int var3, int var4, int var5, int var6) {
      if(!var1.isRemote) {
         boolean var7 = this.isIndirectlyPowered(var1, var2, var3, var4, var6);
         if(var7 && var5 == 1) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6 | 8, 2);
            return false;
         }

         if(!var7 && var5 == 0) {
            return false;
         }
      }

      if(var5 == 0) {
         if(!this.tryExtend(var1, var2, var3, var4, var6)) {
            return false;
         }

         var1.setBlockMetadataWithNotify(var2, var3, var4, var6 | 8, 2);
         var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "tile.piston.out", 0.5F, var1.rand.nextFloat() * 0.25F + 0.6F);
      } else if(var5 == 1) {
         TileEntity var16 = var1.getTileEntity(var2 + Facing.offsetsXForSide[var6], var3 + Facing.offsetsYForSide[var6], var4 + Facing.offsetsZForSide[var6]);
         if(var16 instanceof TileEntityPiston) {
            ((TileEntityPiston)var16).clearPistonTileEntity();
         }

         var1.setBlock(var2, var3, var4, Blocks.piston_extension, var6, 3);
         var1.setTileEntity(var2, var3, var4, BlockPistonMoving.getTileEntity(this, var6, var6, false, true));
         if(this.isSticky) {
            int var8 = var2 + Facing.offsetsXForSide[var6] * 2;
            int var9 = var3 + Facing.offsetsYForSide[var6] * 2;
            int var10 = var4 + Facing.offsetsZForSide[var6] * 2;
            Block var11 = var1.getBlock(var8, var9, var10);
            int var12 = var1.getBlockMetadata(var8, var9, var10);
            boolean var13 = false;
            if(var11 == Blocks.piston_extension) {
               TileEntity var14 = var1.getTileEntity(var8, var9, var10);
               if(var14 instanceof TileEntityPiston) {
                  TileEntityPiston var15 = (TileEntityPiston)var14;
                  if(var15.getPistonOrientation() == var6 && var15.isExtending()) {
                     var15.clearPistonTileEntity();
                     var11 = var15.getStoredBlockID();
                     var12 = var15.getBlockMetadata();
                     var13 = true;
                  }
               }
            }

            if(!var13 && var11.getMaterial() != Material.air && canPushBlock(var11, var1, var8, var9, var10, false) && (var11.getMobilityFlag() == 0 || var11 == Blocks.piston || var11 == Blocks.sticky_piston)) {
               var2 += Facing.offsetsXForSide[var6];
               var3 += Facing.offsetsYForSide[var6];
               var4 += Facing.offsetsZForSide[var6];
               var1.setBlock(var2, var3, var4, Blocks.piston_extension, var12, 3);
               var1.setTileEntity(var2, var3, var4, BlockPistonMoving.getTileEntity(var11, var12, var6, false, false));
               var1.setBlockToAir(var8, var9, var10);
            } else if(!var13) {
               var1.setBlockToAir(var2 + Facing.offsetsXForSide[var6], var3 + Facing.offsetsYForSide[var6], var4 + Facing.offsetsZForSide[var6]);
            }
         } else {
            var1.setBlockToAir(var2 + Facing.offsetsXForSide[var6], var3 + Facing.offsetsYForSide[var6], var4 + Facing.offsetsZForSide[var6]);
         }

         var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "tile.piston.in", 0.5F, var1.rand.nextFloat() * 0.15F + 0.6F);
      }

      return true;
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      if(isExtended(var5)) {
         float var6 = 0.25F;
         switch(getPistonOrientation(var5)) {
         case 0:
            this.setBlockBounds(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
            break;
         case 1:
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
            break;
         case 2:
            this.setBlockBounds(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
            break;
         case 3:
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
            break;
         case 4:
            this.setBlockBounds(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            break;
         case 5:
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
         }
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

   }

   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      return super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public static int getPistonOrientation(int var0) {
      return var0 & 7;
   }

   public static boolean isExtended(int var0) {
      return (var0 & 8) != 0;
   }

   public static int determineOrientation(World var0, int var1, int var2, int var3, EntityLivingBase var4) {
      if(MathHelper.abs((float)var4.posX - (float)var1) < 2.0F && MathHelper.abs((float)var4.posZ - (float)var3) < 2.0F) {
         double var5 = var4.posY + 1.82D - (double)var4.yOffset;
         if(var5 - (double)var2 > 2.0D) {
            return 1;
         }

         if((double)var2 - var5 > 0.0D) {
            return 0;
         }
      }

      int var7 = MathHelper.floor_double((double)(var4.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      return var7 == 0?2:(var7 == 1?5:(var7 == 2?3:(var7 == 3?4:0)));
   }

   private static boolean canPushBlock(Block var0, World var1, int var2, int var3, int var4, boolean var5) {
      if(var0 == Blocks.obsidian) {
         return false;
      } else {
         if(var0 != Blocks.piston && var0 != Blocks.sticky_piston) {
            if(var0.getBlockHardness(var1, var2, var3, var4) == -1.0F) {
               return false;
            }

            if(var0.getMobilityFlag() == 2) {
               return false;
            }

            if(var0.getMobilityFlag() == 1) {
               if(!var5) {
                  return false;
               }

               return true;
            }
         } else if(isExtended(var1.getBlockMetadata(var2, var3, var4))) {
            return false;
         }

         return !(var0 instanceof ITileEntityProvider);
      }
   }

   private static boolean canExtend(World var0, int var1, int var2, int var3, int var4) {
      int var5 = var1 + Facing.offsetsXForSide[var4];
      int var6 = var2 + Facing.offsetsYForSide[var4];
      int var7 = var3 + Facing.offsetsZForSide[var4];
      int var8 = 0;

      while(true) {
         if(var8 < 13) {
            if(var6 <= 0 || var6 >= 255) {
               return false;
            }

            Block var9 = var0.getBlock(var5, var6, var7);
            if(var9.getMaterial() != Material.air) {
               if(!canPushBlock(var9, var0, var5, var6, var7, true)) {
                  return false;
               }

               if(var9.getMobilityFlag() != 1) {
                  if(var8 == 12) {
                     return false;
                  }

                  var5 += Facing.offsetsXForSide[var4];
                  var6 += Facing.offsetsYForSide[var4];
                  var7 += Facing.offsetsZForSide[var4];
                  ++var8;
                  continue;
               }
            }
         }

         return true;
      }
   }

   private boolean tryExtend(World var1, int var2, int var3, int var4, int var5) {
      int var6 = var2 + Facing.offsetsXForSide[var5];
      int var7 = var3 + Facing.offsetsYForSide[var5];
      int var8 = var4 + Facing.offsetsZForSide[var5];
      int var9 = 0;

      while(true) {
         if(var9 < 13) {
            if(var7 <= 0 || var7 >= 255) {
               return false;
            }

            Block var10 = var1.getBlock(var6, var7, var8);
            if(var10.getMaterial() != Material.air) {
               if(!canPushBlock(var10, var1, var6, var7, var8, true)) {
                  return false;
               }

               if(var10.getMobilityFlag() != 1) {
                  if(var9 == 12) {
                     return false;
                  }

                  var6 += Facing.offsetsXForSide[var5];
                  var7 += Facing.offsetsYForSide[var5];
                  var8 += Facing.offsetsZForSide[var5];
                  ++var9;
                  continue;
               }

               var10.dropBlockAsItem(var1, var6, var7, var8, var1.getBlockMetadata(var6, var7, var8), 0);
               var1.setBlockToAir(var6, var7, var8);
            }
         }

         var9 = var6;
         int var19 = var7;
         int var11 = var8;
         int var12 = 0;

         Block[] var13;
         int var14;
         int var15;
         int var16;
         for(var13 = new Block[13]; var6 != var2 || var7 != var3 || var8 != var4; var8 = var16) {
            var14 = var6 - Facing.offsetsXForSide[var5];
            var15 = var7 - Facing.offsetsYForSide[var5];
            var16 = var8 - Facing.offsetsZForSide[var5];
            Block var17 = var1.getBlock(var14, var15, var16);
            int var18 = var1.getBlockMetadata(var14, var15, var16);
            if(var17 == this && var14 == var2 && var15 == var3 && var16 == var4) {
               var1.setBlock(var6, var7, var8, Blocks.piston_extension, var5 | (this.isSticky?8:0), 4);
               var1.setTileEntity(var6, var7, var8, BlockPistonMoving.getTileEntity(Blocks.piston_head, var5 | (this.isSticky?8:0), var5, true, false));
            } else {
               var1.setBlock(var6, var7, var8, Blocks.piston_extension, var18, 4);
               var1.setTileEntity(var6, var7, var8, BlockPistonMoving.getTileEntity(var17, var18, var5, true, false));
            }

            var13[var12++] = var17;
            var6 = var14;
            var7 = var15;
         }

         var6 = var9;
         var7 = var19;
         var8 = var11;

         for(var12 = 0; var6 != var2 || var7 != var3 || var8 != var4; var8 = var16) {
            var14 = var6 - Facing.offsetsXForSide[var5];
            var15 = var7 - Facing.offsetsYForSide[var5];
            var16 = var8 - Facing.offsetsZForSide[var5];
            var1.notifyBlocksOfNeighborChange(var14, var15, var16, var13[var12++]);
            var6 = var14;
            var7 = var15;
         }

         return true;
      }
   }
}
