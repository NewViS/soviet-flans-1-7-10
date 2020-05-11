package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHopper extends BlockContainer {

   private final Random field_149922_a = new Random();
   private IIcon field_149921_b;
   private IIcon field_149923_M;
   private IIcon field_149924_N;


   public BlockHopper() {
      super(Material.iron);
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      float var8 = 0.125F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, var8, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var8);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      this.setBlockBounds(1.0F - var8, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      this.setBlockBounds(0.0F, 0.0F, 1.0F - var8, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9) {
      int var10 = Facing.oppositeSide[var5];
      if(var10 == 1) {
         var10 = 0;
      }

      return var10;
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityHopper();
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      super.onBlockPlacedBy(var1, var2, var3, var4, var5, var6);
      if(var6.hasDisplayName()) {
         TileEntityHopper var7 = func_149920_e(var1, var2, var3, var4);
         var7.func_145886_a(var6.getDisplayName());
      }

   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      super.onBlockAdded(var1, var2, var3, var4);
      this.func_149919_e(var1, var2, var3, var4);
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var1.isRemote) {
         return true;
      } else {
         TileEntityHopper var10 = func_149920_e(var1, var2, var3, var4);
         if(var10 != null) {
            var5.func_146093_a(var10);
         }

         return true;
      }
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      this.func_149919_e(var1, var2, var3, var4);
   }

   private void func_149919_e(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      int var6 = getDirectionFromMetadata(var5);
      boolean var7 = !var1.isBlockIndirectlyGettingPowered(var2, var3, var4);
      boolean var8 = func_149917_c(var5);
      if(var7 != var8) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var6 | (var7?0:8), 4);
      }

   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      TileEntityHopper var7 = (TileEntityHopper)var1.getTileEntity(var2, var3, var4);
      if(var7 != null) {
         for(int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
            ItemStack var9 = var7.getStackInSlot(var8);
            if(var9 != null) {
               float var10 = this.field_149922_a.nextFloat() * 0.8F + 0.1F;
               float var11 = this.field_149922_a.nextFloat() * 0.8F + 0.1F;
               float var12 = this.field_149922_a.nextFloat() * 0.8F + 0.1F;

               while(var9.stackSize > 0) {
                  int var13 = this.field_149922_a.nextInt(21) + 10;
                  if(var13 > var9.stackSize) {
                     var13 = var9.stackSize;
                  }

                  var9.stackSize -= var13;
                  EntityItem var14 = new EntityItem(var1, (double)((float)var2 + var10), (double)((float)var3 + var11), (double)((float)var4 + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));
                  if(var9.hasTagCompound()) {
                     var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                  }

                  float var15 = 0.05F;
                  var14.motionX = (double)((float)this.field_149922_a.nextGaussian() * var15);
                  var14.motionY = (double)((float)this.field_149922_a.nextGaussian() * var15 + 0.2F);
                  var14.motionZ = (double)((float)this.field_149922_a.nextGaussian() * var15);
                  var1.spawnEntityInWorld(var14);
               }
            }
         }

         var1.func_147453_f(var2, var3, var4, var5);
      }

      super.breakBlock(var1, var2, var3, var4, var5, var6);
   }

   public int getRenderType() {
      return 38;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return true;
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?this.field_149923_M:this.field_149921_b;
   }

   public static int getDirectionFromMetadata(int var0) {
      return var0 & 7;
   }

   public static boolean func_149917_c(int var0) {
      return (var0 & 8) != 8;
   }

   public boolean hasComparatorInputOverride() {
      return true;
   }

   public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5) {
      return Container.calcRedstoneFromInventory(func_149920_e(var1, var2, var3, var4));
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_149921_b = var1.registerIcon("hopper_outside");
      this.field_149923_M = var1.registerIcon("hopper_top");
      this.field_149924_N = var1.registerIcon("hopper_inside");
   }

   public static IIcon getHopperIcon(String var0) {
      return var0.equals("hopper_outside")?Blocks.hopper.field_149921_b:(var0.equals("hopper_inside")?Blocks.hopper.field_149924_N:null);
   }

   public String getItemIconName() {
      return "hopper";
   }

   public static TileEntityHopper func_149920_e(IBlockAccess var0, int var1, int var2, int var3) {
      return (TileEntityHopper)var0.getTileEntity(var1, var2, var3);
   }
}
