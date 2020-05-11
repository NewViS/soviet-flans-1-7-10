package net.minecraft.block;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChest extends BlockContainer {

   private final Random field_149955_b = new Random();
   public final int field_149956_a;


   protected BlockChest(int var1) {
      super(Material.wood);
      this.field_149956_a = var1;
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 22;
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      if(var1.getBlock(var2, var3, var4 - 1) == this) {
         this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
      } else if(var1.getBlock(var2, var3, var4 + 1) == this) {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
      } else if(var1.getBlock(var2 - 1, var3, var4) == this) {
         this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
      } else if(var1.getBlock(var2 + 1, var3, var4) == this) {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
      } else {
         this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
      }

   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      super.onBlockAdded(var1, var2, var3, var4);
      this.func_149954_e(var1, var2, var3, var4);
      Block var5 = var1.getBlock(var2, var3, var4 - 1);
      Block var6 = var1.getBlock(var2, var3, var4 + 1);
      Block var7 = var1.getBlock(var2 - 1, var3, var4);
      Block var8 = var1.getBlock(var2 + 1, var3, var4);
      if(var5 == this) {
         this.func_149954_e(var1, var2, var3, var4 - 1);
      }

      if(var6 == this) {
         this.func_149954_e(var1, var2, var3, var4 + 1);
      }

      if(var7 == this) {
         this.func_149954_e(var1, var2 - 1, var3, var4);
      }

      if(var8 == this) {
         this.func_149954_e(var1, var2 + 1, var3, var4);
      }

   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      Block var7 = var1.getBlock(var2, var3, var4 - 1);
      Block var8 = var1.getBlock(var2, var3, var4 + 1);
      Block var9 = var1.getBlock(var2 - 1, var3, var4);
      Block var10 = var1.getBlock(var2 + 1, var3, var4);
      byte var11 = 0;
      int var12 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      if(var12 == 0) {
         var11 = 2;
      }

      if(var12 == 1) {
         var11 = 5;
      }

      if(var12 == 2) {
         var11 = 3;
      }

      if(var12 == 3) {
         var11 = 4;
      }

      if(var7 != this && var8 != this && var9 != this && var10 != this) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var11, 3);
      } else {
         if((var7 == this || var8 == this) && (var11 == 4 || var11 == 5)) {
            if(var7 == this) {
               var1.setBlockMetadataWithNotify(var2, var3, var4 - 1, var11, 3);
            } else {
               var1.setBlockMetadataWithNotify(var2, var3, var4 + 1, var11, 3);
            }

            var1.setBlockMetadataWithNotify(var2, var3, var4, var11, 3);
         }

         if((var9 == this || var10 == this) && (var11 == 2 || var11 == 3)) {
            if(var9 == this) {
               var1.setBlockMetadataWithNotify(var2 - 1, var3, var4, var11, 3);
            } else {
               var1.setBlockMetadataWithNotify(var2 + 1, var3, var4, var11, 3);
            }

            var1.setBlockMetadataWithNotify(var2, var3, var4, var11, 3);
         }
      }

      if(var6.hasDisplayName()) {
         ((TileEntityChest)var1.getTileEntity(var2, var3, var4)).func_145976_a(var6.getDisplayName());
      }

   }

   public void func_149954_e(World var1, int var2, int var3, int var4) {
      if(!var1.isRemote) {
         Block var5 = var1.getBlock(var2, var3, var4 - 1);
         Block var6 = var1.getBlock(var2, var3, var4 + 1);
         Block var7 = var1.getBlock(var2 - 1, var3, var4);
         Block var8 = var1.getBlock(var2 + 1, var3, var4);
         boolean var9 = true;
         int var10;
         Block var11;
         int var12;
         Block var13;
         boolean var14;
         byte var15;
         int var16;
         if(var5 != this && var6 != this) {
            if(var7 != this && var8 != this) {
               var15 = 3;
               if(var5.func_149730_j() && !var6.func_149730_j()) {
                  var15 = 3;
               }

               if(var6.func_149730_j() && !var5.func_149730_j()) {
                  var15 = 2;
               }

               if(var7.func_149730_j() && !var8.func_149730_j()) {
                  var15 = 5;
               }

               if(var8.func_149730_j() && !var7.func_149730_j()) {
                  var15 = 4;
               }
            } else {
               var10 = var7 == this?var2 - 1:var2 + 1;
               var11 = var1.getBlock(var10, var3, var4 - 1);
               var12 = var7 == this?var2 - 1:var2 + 1;
               var13 = var1.getBlock(var12, var3, var4 + 1);
               var15 = 3;
               var14 = true;
               if(var7 == this) {
                  var16 = var1.getBlockMetadata(var2 - 1, var3, var4);
               } else {
                  var16 = var1.getBlockMetadata(var2 + 1, var3, var4);
               }

               if(var16 == 2) {
                  var15 = 2;
               }

               if((var5.func_149730_j() || var11.func_149730_j()) && !var6.func_149730_j() && !var13.func_149730_j()) {
                  var15 = 3;
               }

               if((var6.func_149730_j() || var13.func_149730_j()) && !var5.func_149730_j() && !var11.func_149730_j()) {
                  var15 = 2;
               }
            }
         } else {
            var10 = var5 == this?var4 - 1:var4 + 1;
            var11 = var1.getBlock(var2 - 1, var3, var10);
            var12 = var5 == this?var4 - 1:var4 + 1;
            var13 = var1.getBlock(var2 + 1, var3, var12);
            var15 = 5;
            var14 = true;
            if(var5 == this) {
               var16 = var1.getBlockMetadata(var2, var3, var4 - 1);
            } else {
               var16 = var1.getBlockMetadata(var2, var3, var4 + 1);
            }

            if(var16 == 4) {
               var15 = 4;
            }

            if((var7.func_149730_j() || var11.func_149730_j()) && !var8.func_149730_j() && !var13.func_149730_j()) {
               var15 = 5;
            }

            if((var8.func_149730_j() || var13.func_149730_j()) && !var7.func_149730_j() && !var11.func_149730_j()) {
               var15 = 4;
            }
         }

         var1.setBlockMetadataWithNotify(var2, var3, var4, var15, 3);
      }
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      int var5 = 0;
      if(var1.getBlock(var2 - 1, var3, var4) == this) {
         ++var5;
      }

      if(var1.getBlock(var2 + 1, var3, var4) == this) {
         ++var5;
      }

      if(var1.getBlock(var2, var3, var4 - 1) == this) {
         ++var5;
      }

      if(var1.getBlock(var2, var3, var4 + 1) == this) {
         ++var5;
      }

      return var5 > 1?false:(this.func_149952_n(var1, var2 - 1, var3, var4)?false:(this.func_149952_n(var1, var2 + 1, var3, var4)?false:(this.func_149952_n(var1, var2, var3, var4 - 1)?false:!this.func_149952_n(var1, var2, var3, var4 + 1))));
   }

   private boolean func_149952_n(World var1, int var2, int var3, int var4) {
      return var1.getBlock(var2, var3, var4) != this?false:(var1.getBlock(var2 - 1, var3, var4) == this?true:(var1.getBlock(var2 + 1, var3, var4) == this?true:(var1.getBlock(var2, var3, var4 - 1) == this?true:var1.getBlock(var2, var3, var4 + 1) == this)));
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      super.onNeighborBlockChange(var1, var2, var3, var4, var5);
      TileEntityChest var6 = (TileEntityChest)var1.getTileEntity(var2, var3, var4);
      if(var6 != null) {
         var6.updateContainingBlockInfo();
      }

   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      TileEntityChest var7 = (TileEntityChest)var1.getTileEntity(var2, var3, var4);
      if(var7 != null) {
         for(int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
            ItemStack var9 = var7.getStackInSlot(var8);
            if(var9 != null) {
               float var10 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;
               float var11 = this.field_149955_b.nextFloat() * 0.8F + 0.1F;

               EntityItem var14;
               for(float var12 = this.field_149955_b.nextFloat() * 0.8F + 0.1F; var9.stackSize > 0; var1.spawnEntityInWorld(var14)) {
                  int var13 = this.field_149955_b.nextInt(21) + 10;
                  if(var13 > var9.stackSize) {
                     var13 = var9.stackSize;
                  }

                  var9.stackSize -= var13;
                  var14 = new EntityItem(var1, (double)((float)var2 + var10), (double)((float)var3 + var11), (double)((float)var4 + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));
                  float var15 = 0.05F;
                  var14.motionX = (double)((float)this.field_149955_b.nextGaussian() * var15);
                  var14.motionY = (double)((float)this.field_149955_b.nextGaussian() * var15 + 0.2F);
                  var14.motionZ = (double)((float)this.field_149955_b.nextGaussian() * var15);
                  if(var9.hasTagCompound()) {
                     var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                  }
               }
            }
         }

         var1.func_147453_f(var2, var3, var4, var5);
      }

      super.breakBlock(var1, var2, var3, var4, var5, var6);
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var1.isRemote) {
         return true;
      } else {
         IInventory var10 = this.func_149951_m(var1, var2, var3, var4);
         if(var10 != null) {
            var5.displayGUIChest(var10);
         }

         return true;
      }
   }

   public IInventory func_149951_m(World var1, int var2, int var3, int var4) {
      Object var5 = (TileEntityChest)var1.getTileEntity(var2, var3, var4);
      if(var5 == null) {
         return null;
      } else if(var1.getBlock(var2, var3 + 1, var4).isNormalCube()) {
         return null;
      } else if(func_149953_o(var1, var2, var3, var4)) {
         return null;
      } else if(var1.getBlock(var2 - 1, var3, var4) == this && (var1.getBlock(var2 - 1, var3 + 1, var4).isNormalCube() || func_149953_o(var1, var2 - 1, var3, var4))) {
         return null;
      } else if(var1.getBlock(var2 + 1, var3, var4) == this && (var1.getBlock(var2 + 1, var3 + 1, var4).isNormalCube() || func_149953_o(var1, var2 + 1, var3, var4))) {
         return null;
      } else if(var1.getBlock(var2, var3, var4 - 1) == this && (var1.getBlock(var2, var3 + 1, var4 - 1).isNormalCube() || func_149953_o(var1, var2, var3, var4 - 1))) {
         return null;
      } else if(var1.getBlock(var2, var3, var4 + 1) == this && (var1.getBlock(var2, var3 + 1, var4 + 1).isNormalCube() || func_149953_o(var1, var2, var3, var4 + 1))) {
         return null;
      } else {
         if(var1.getBlock(var2 - 1, var3, var4) == this) {
            var5 = new InventoryLargeChest("container.chestDouble", (TileEntityChest)var1.getTileEntity(var2 - 1, var3, var4), (IInventory)var5);
         }

         if(var1.getBlock(var2 + 1, var3, var4) == this) {
            var5 = new InventoryLargeChest("container.chestDouble", (IInventory)var5, (TileEntityChest)var1.getTileEntity(var2 + 1, var3, var4));
         }

         if(var1.getBlock(var2, var3, var4 - 1) == this) {
            var5 = new InventoryLargeChest("container.chestDouble", (TileEntityChest)var1.getTileEntity(var2, var3, var4 - 1), (IInventory)var5);
         }

         if(var1.getBlock(var2, var3, var4 + 1) == this) {
            var5 = new InventoryLargeChest("container.chestDouble", (IInventory)var5, (TileEntityChest)var1.getTileEntity(var2, var3, var4 + 1));
         }

         return (IInventory)var5;
      }
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      TileEntityChest var3 = new TileEntityChest();
      return var3;
   }

   public boolean canProvidePower() {
      return this.field_149956_a == 1;
   }

   public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      if(!this.canProvidePower()) {
         return 0;
      } else {
         int var6 = ((TileEntityChest)var1.getTileEntity(var2, var3, var4)).numPlayersUsing;
         return MathHelper.clamp_int(var6, 0, 15);
      }
   }

   public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var5 == 1?this.isProvidingWeakPower(var1, var2, var3, var4, var5):0;
   }

   private static boolean func_149953_o(World var0, int var1, int var2, int var3) {
      Iterator var4 = var0.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double)var1, (double)(var2 + 1), (double)var3, (double)(var1 + 1), (double)(var2 + 2), (double)(var3 + 1))).iterator();

      EntityOcelot var6;
      do {
         if(!var4.hasNext()) {
            return false;
         }

         Entity var5 = (Entity)var4.next();
         var6 = (EntityOcelot)var5;
      } while(!var6.isSitting());

      return true;
   }

   public boolean hasComparatorInputOverride() {
      return true;
   }

   public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5) {
      return Container.calcRedstoneFromInventory(this.func_149951_m(var1, var2, var3, var4));
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon("planks_oak");
   }
}
