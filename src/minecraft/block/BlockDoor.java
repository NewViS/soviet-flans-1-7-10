package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDoor extends Block {

   private IIcon[] field_150017_a;
   private IIcon[] field_150016_b;


   protected BlockDoor(Material var1) {
      super(var1);
      float var2 = 0.5F;
      float var3 = 1.0F;
      this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var3, 0.5F + var2);
   }

   public IIcon getIcon(int var1, int var2) {
      return this.field_150016_b[0];
   }

   public IIcon getIcon(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      if(var5 != 1 && var5 != 0) {
         int var6 = this.func_150012_g(var1, var2, var3, var4);
         int var7 = var6 & 3;
         boolean var8 = (var6 & 4) != 0;
         boolean var9 = false;
         boolean var10 = (var6 & 8) != 0;
         if(var8) {
            if(var7 == 0 && var5 == 2) {
               var9 = !var9;
            } else if(var7 == 1 && var5 == 5) {
               var9 = !var9;
            } else if(var7 == 2 && var5 == 3) {
               var9 = !var9;
            } else if(var7 == 3 && var5 == 4) {
               var9 = !var9;
            }
         } else {
            if(var7 == 0 && var5 == 5) {
               var9 = !var9;
            } else if(var7 == 1 && var5 == 3) {
               var9 = !var9;
            } else if(var7 == 2 && var5 == 4) {
               var9 = !var9;
            } else if(var7 == 3 && var5 == 2) {
               var9 = !var9;
            }

            if((var6 & 16) != 0) {
               var9 = !var9;
            }
         }

         return var10?this.field_150017_a[var9?1:0]:this.field_150016_b[var9?1:0];
      } else {
         return this.field_150016_b[0];
      }
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_150017_a = new IIcon[2];
      this.field_150016_b = new IIcon[2];
      this.field_150017_a[0] = var1.registerIcon(this.getTextureName() + "_upper");
      this.field_150016_b[0] = var1.registerIcon(this.getTextureName() + "_lower");
      this.field_150017_a[1] = new IconFlipped(this.field_150017_a[0], true, false);
      this.field_150016_b[1] = new IconFlipped(this.field_150016_b[0], true, false);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = this.func_150012_g(var1, var2, var3, var4);
      return (var5 & 4) != 0;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 7;
   }

   public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      return super.getSelectedBoundingBoxFromPool(var1, var2, var3, var4);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      return super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      this.func_150011_b(this.func_150012_g(var1, var2, var3, var4));
   }

   public int func_150013_e(IBlockAccess var1, int var2, int var3, int var4) {
      return this.func_150012_g(var1, var2, var3, var4) & 3;
   }

   public boolean func_150015_f(IBlockAccess var1, int var2, int var3, int var4) {
      return (this.func_150012_g(var1, var2, var3, var4) & 4) != 0;
   }

   private void func_150011_b(int var1) {
      float var2 = 0.1875F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
      int var3 = var1 & 3;
      boolean var4 = (var1 & 4) != 0;
      boolean var5 = (var1 & 16) != 0;
      if(var3 == 0) {
         if(var4) {
            if(!var5) {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
            } else {
               this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
            }
         } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
         }
      } else if(var3 == 1) {
         if(var4) {
            if(!var5) {
               this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
            }
         } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
         }
      } else if(var3 == 2) {
         if(var4) {
            if(!var5) {
               this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
            } else {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
            }
         } else {
            this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         }
      } else if(var3 == 3) {
         if(var4) {
            if(!var5) {
               this.setBlockBounds(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
            } else {
               this.setBlockBounds(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
         } else {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
         }
      }

   }

   public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {}

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(super.blockMaterial == Material.iron) {
         return true;
      } else {
         int var10 = this.func_150012_g(var1, var2, var3, var4);
         int var11 = var10 & 7;
         var11 ^= 4;
         if((var10 & 8) == 0) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var11, 2);
            var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
         } else {
            var1.setBlockMetadataWithNotify(var2, var3 - 1, var4, var11, 2);
            var1.markBlockRangeForRenderUpdate(var2, var3 - 1, var4, var2, var3, var4);
         }

         var1.playAuxSFXAtEntity(var5, 1003, var2, var3, var4, 0);
         return true;
      }
   }

   public void func_150014_a(World var1, int var2, int var3, int var4, boolean var5) {
      int var6 = this.func_150012_g(var1, var2, var3, var4);
      boolean var7 = (var6 & 4) != 0;
      if(var7 != var5) {
         int var8 = var6 & 7;
         var8 ^= 4;
         if((var6 & 8) == 0) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var8, 2);
            var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
         } else {
            var1.setBlockMetadataWithNotify(var2, var3 - 1, var4, var8, 2);
            var1.markBlockRangeForRenderUpdate(var2, var3 - 1, var4, var2, var3, var4);
         }

         var1.playAuxSFXAtEntity((EntityPlayer)null, 1003, var2, var3, var4, 0);
      }
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      if((var6 & 8) == 0) {
         boolean var7 = false;
         if(var1.getBlock(var2, var3 + 1, var4) != this) {
            var1.setBlockToAir(var2, var3, var4);
            var7 = true;
         }

         if(!World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4)) {
            var1.setBlockToAir(var2, var3, var4);
            var7 = true;
            if(var1.getBlock(var2, var3 + 1, var4) == this) {
               var1.setBlockToAir(var2, var3 + 1, var4);
            }
         }

         if(var7) {
            if(!var1.isRemote) {
               this.dropBlockAsItem(var1, var2, var3, var4, var6, 0);
            }
         } else {
            boolean var8 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4) || var1.isBlockIndirectlyGettingPowered(var2, var3 + 1, var4);
            if((var8 || var5.canProvidePower()) && var5 != this) {
               this.func_150014_a(var1, var2, var3, var4, var8);
            }
         }
      } else {
         if(var1.getBlock(var2, var3 - 1, var4) != this) {
            var1.setBlockToAir(var2, var3, var4);
         }

         if(var5 != this) {
            this.onNeighborBlockChange(var1, var2, var3 - 1, var4, var5);
         }
      }

   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return (var1 & 8) != 0?null:(super.blockMaterial == Material.iron?Items.iron_door:Items.wooden_door);
   }

   public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      return super.collisionRayTrace(var1, var2, var3, var4, var5, var6);
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return var3 >= 255?false:World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4) && super.canPlaceBlockAt(var1, var2, var3, var4) && super.canPlaceBlockAt(var1, var2, var3 + 1, var4);
   }

   public int getMobilityFlag() {
      return 1;
   }

   public int func_150012_g(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      boolean var6 = (var5 & 8) != 0;
      int var7;
      int var8;
      if(var6) {
         var7 = var1.getBlockMetadata(var2, var3 - 1, var4);
         var8 = var5;
      } else {
         var7 = var5;
         var8 = var1.getBlockMetadata(var2, var3 + 1, var4);
      }

      boolean var9 = (var8 & 1) != 0;
      return var7 & 7 | (var6?8:0) | (var9?16:0);
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return super.blockMaterial == Material.iron?Items.iron_door:Items.wooden_door;
   }

   public void onBlockHarvested(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6) {
      if(var6.capabilities.isCreativeMode && (var5 & 8) != 0 && var1.getBlock(var2, var3 - 1, var4) == this) {
         var1.setBlockToAir(var2, var3 - 1, var4);
      }

   }
}
