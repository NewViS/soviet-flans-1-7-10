package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockFurnace extends BlockContainer {

   private final Random field_149933_a = new Random();
   private final boolean field_149932_b;
   private static boolean field_149934_M;
   private IIcon field_149935_N;
   private IIcon field_149936_O;


   protected BlockFurnace(boolean var1) {
      super(Material.rock);
      this.field_149932_b = var1;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.furnace);
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      super.onBlockAdded(var1, var2, var3, var4);
      this.func_149930_e(var1, var2, var3, var4);
   }

   private void func_149930_e(World var1, int var2, int var3, int var4) {
      if(!var1.isRemote) {
         Block var5 = var1.getBlock(var2, var3, var4 - 1);
         Block var6 = var1.getBlock(var2, var3, var4 + 1);
         Block var7 = var1.getBlock(var2 - 1, var3, var4);
         Block var8 = var1.getBlock(var2 + 1, var3, var4);
         byte var9 = 3;
         if(var5.func_149730_j() && !var6.func_149730_j()) {
            var9 = 3;
         }

         if(var6.func_149730_j() && !var5.func_149730_j()) {
            var9 = 2;
         }

         if(var7.func_149730_j() && !var8.func_149730_j()) {
            var9 = 5;
         }

         if(var8.func_149730_j() && !var7.func_149730_j()) {
            var9 = 4;
         }

         var1.setBlockMetadataWithNotify(var2, var3, var4, var9, 2);
      }
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?this.field_149935_N:(var1 == 0?this.field_149935_N:(var1 != var2?super.blockIcon:this.field_149936_O));
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon("furnace_side");
      this.field_149936_O = var1.registerIcon(this.field_149932_b?"furnace_front_on":"furnace_front_off");
      this.field_149935_N = var1.registerIcon("furnace_top");
   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      if(this.field_149932_b) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         float var7 = (float)var2 + 0.5F;
         float var8 = (float)var3 + 0.0F + var5.nextFloat() * 6.0F / 16.0F;
         float var9 = (float)var4 + 0.5F;
         float var10 = 0.52F;
         float var11 = var5.nextFloat() * 0.6F - 0.3F;
         if(var6 == 4) {
            var1.spawnParticle("smoke", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
            var1.spawnParticle("flame", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
         } else if(var6 == 5) {
            var1.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
            var1.spawnParticle("flame", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
         } else if(var6 == 2) {
            var1.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
            var1.spawnParticle("flame", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
         } else if(var6 == 3) {
            var1.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
            var1.spawnParticle("flame", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
         }

      }
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var1.isRemote) {
         return true;
      } else {
         TileEntityFurnace var10 = (TileEntityFurnace)var1.getTileEntity(var2, var3, var4);
         if(var10 != null) {
            var5.func_146101_a(var10);
         }

         return true;
      }
   }

   public static void updateFurnaceBlockState(boolean var0, World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      TileEntity var6 = var1.getTileEntity(var2, var3, var4);
      field_149934_M = true;
      if(var0) {
         var1.setBlock(var2, var3, var4, Blocks.lit_furnace);
      } else {
         var1.setBlock(var2, var3, var4, Blocks.furnace);
      }

      field_149934_M = false;
      var1.setBlockMetadataWithNotify(var2, var3, var4, var5, 2);
      if(var6 != null) {
         var6.validate();
         var1.setTileEntity(var2, var3, var4, var6);
      }

   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityFurnace();
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      int var7 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      if(var7 == 0) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 2, 2);
      }

      if(var7 == 1) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 5, 2);
      }

      if(var7 == 2) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 3, 2);
      }

      if(var7 == 3) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 4, 2);
      }

      if(var6.hasDisplayName()) {
         ((TileEntityFurnace)var1.getTileEntity(var2, var3, var4)).func_145951_a(var6.getDisplayName());
      }

   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      if(!field_149934_M) {
         TileEntityFurnace var7 = (TileEntityFurnace)var1.getTileEntity(var2, var3, var4);
         if(var7 != null) {
            for(int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
               ItemStack var9 = var7.getStackInSlot(var8);
               if(var9 != null) {
                  float var10 = this.field_149933_a.nextFloat() * 0.8F + 0.1F;
                  float var11 = this.field_149933_a.nextFloat() * 0.8F + 0.1F;
                  float var12 = this.field_149933_a.nextFloat() * 0.8F + 0.1F;

                  while(var9.stackSize > 0) {
                     int var13 = this.field_149933_a.nextInt(21) + 10;
                     if(var13 > var9.stackSize) {
                        var13 = var9.stackSize;
                     }

                     var9.stackSize -= var13;
                     EntityItem var14 = new EntityItem(var1, (double)((float)var2 + var10), (double)((float)var3 + var11), (double)((float)var4 + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));
                     if(var9.hasTagCompound()) {
                        var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                     }

                     float var15 = 0.05F;
                     var14.motionX = (double)((float)this.field_149933_a.nextGaussian() * var15);
                     var14.motionY = (double)((float)this.field_149933_a.nextGaussian() * var15 + 0.2F);
                     var14.motionZ = (double)((float)this.field_149933_a.nextGaussian() * var15);
                     var1.spawnEntityInWorld(var14);
                  }
               }
            }

            var1.func_147453_f(var2, var3, var4, var5);
         }
      }

      super.breakBlock(var1, var2, var3, var4, var5, var6);
   }

   public boolean hasComparatorInputOverride() {
      return true;
   }

   public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5) {
      return Container.calcRedstoneFromInventory((IInventory)var1.getTileEntity(var2, var3, var4));
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Item.getItemFromBlock(Blocks.furnace);
   }
}
