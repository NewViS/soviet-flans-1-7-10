package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.PositionImpl;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.util.IRegistry;
import net.minecraft.util.RegistryDefaulted;
import net.minecraft.world.World;

public class BlockDispenser extends BlockContainer {

   public static final IRegistry dispenseBehaviorRegistry = new RegistryDefaulted(new BehaviorDefaultDispenseItem());
   protected Random field_149942_b = new Random();
   protected IIcon field_149944_M;
   protected IIcon field_149945_N;
   protected IIcon field_149946_O;


   protected BlockDispenser() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   public int tickRate(World var1) {
      return 4;
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      super.onBlockAdded(var1, var2, var3, var4);
      this.func_149938_m(var1, var2, var3, var4);
   }

   private void func_149938_m(World var1, int var2, int var3, int var4) {
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
      int var3 = var2 & 7;
      return var1 == var3?(var3 != 1 && var3 != 0?this.field_149945_N:this.field_149946_O):(var3 != 1 && var3 != 0?(var1 != 1 && var1 != 0?super.blockIcon:this.field_149944_M):this.field_149944_M);
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon("furnace_side");
      this.field_149944_M = var1.registerIcon("furnace_top");
      this.field_149945_N = var1.registerIcon(this.getTextureName() + "_front_horizontal");
      this.field_149946_O = var1.registerIcon(this.getTextureName() + "_front_vertical");
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var1.isRemote) {
         return true;
      } else {
         TileEntityDispenser var10 = (TileEntityDispenser)var1.getTileEntity(var2, var3, var4);
         if(var10 != null) {
            var5.func_146102_a(var10);
         }

         return true;
      }
   }

   protected void func_149941_e(World var1, int var2, int var3, int var4) {
      BlockSourceImpl var5 = new BlockSourceImpl(var1, var2, var3, var4);
      TileEntityDispenser var6 = (TileEntityDispenser)var5.getBlockTileEntity();
      if(var6 != null) {
         int var7 = var6.func_146017_i();
         if(var7 < 0) {
            var1.playAuxSFX(1001, var2, var3, var4, 0);
         } else {
            ItemStack var8 = var6.getStackInSlot(var7);
            IBehaviorDispenseItem var9 = this.func_149940_a(var8);
            if(var9 != IBehaviorDispenseItem.itemDispenseBehaviorProvider) {
               ItemStack var10 = var9.dispense(var5, var8);
               var6.setInventorySlotContents(var7, var10.stackSize == 0?null:var10);
            }
         }

      }
   }

   protected IBehaviorDispenseItem func_149940_a(ItemStack var1) {
      return (IBehaviorDispenseItem)dispenseBehaviorRegistry.getObject(var1.getItem());
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      boolean var6 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4) || var1.isBlockIndirectlyGettingPowered(var2, var3 + 1, var4);
      int var7 = var1.getBlockMetadata(var2, var3, var4);
      boolean var8 = (var7 & 8) != 0;
      if(var6 && !var8) {
         var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1));
         var1.setBlockMetadataWithNotify(var2, var3, var4, var7 | 8, 4);
      } else if(!var6 && var8) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, var7 & -9, 4);
      }

   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!var1.isRemote) {
         this.func_149941_e(var1, var2, var3, var4);
      }

   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityDispenser();
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      int var7 = BlockPistonBase.determineOrientation(var1, var2, var3, var4, var5);
      var1.setBlockMetadataWithNotify(var2, var3, var4, var7, 2);
      if(var6.hasDisplayName()) {
         ((TileEntityDispenser)var1.getTileEntity(var2, var3, var4)).func_146018_a(var6.getDisplayName());
      }

   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      TileEntityDispenser var7 = (TileEntityDispenser)var1.getTileEntity(var2, var3, var4);
      if(var7 != null) {
         for(int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
            ItemStack var9 = var7.getStackInSlot(var8);
            if(var9 != null) {
               float var10 = this.field_149942_b.nextFloat() * 0.8F + 0.1F;
               float var11 = this.field_149942_b.nextFloat() * 0.8F + 0.1F;
               float var12 = this.field_149942_b.nextFloat() * 0.8F + 0.1F;

               while(var9.stackSize > 0) {
                  int var13 = this.field_149942_b.nextInt(21) + 10;
                  if(var13 > var9.stackSize) {
                     var13 = var9.stackSize;
                  }

                  var9.stackSize -= var13;
                  EntityItem var14 = new EntityItem(var1, (double)((float)var2 + var10), (double)((float)var3 + var11), (double)((float)var4 + var12), new ItemStack(var9.getItem(), var13, var9.getItemDamage()));
                  if(var9.hasTagCompound()) {
                     var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                  }

                  float var15 = 0.05F;
                  var14.motionX = (double)((float)this.field_149942_b.nextGaussian() * var15);
                  var14.motionY = (double)((float)this.field_149942_b.nextGaussian() * var15 + 0.2F);
                  var14.motionZ = (double)((float)this.field_149942_b.nextGaussian() * var15);
                  var1.spawnEntityInWorld(var14);
               }
            }
         }

         var1.func_147453_f(var2, var3, var4, var5);
      }

      super.breakBlock(var1, var2, var3, var4, var5, var6);
   }

   public static IPosition func_149939_a(IBlockSource var0) {
      EnumFacing var1 = func_149937_b(var0.getBlockMetadata());
      double var2 = var0.getX() + 0.7D * (double)var1.getFrontOffsetX();
      double var4 = var0.getY() + 0.7D * (double)var1.getFrontOffsetY();
      double var6 = var0.getZ() + 0.7D * (double)var1.getFrontOffsetZ();
      return new PositionImpl(var2, var4, var6);
   }

   public static EnumFacing func_149937_b(int var0) {
      return EnumFacing.getFront(var0 & 7);
   }

   public boolean hasComparatorInputOverride() {
      return true;
   }

   public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5) {
      return Container.calcRedstoneFromInventory((IInventory)var1.getTileEntity(var2, var3, var4));
   }

}
