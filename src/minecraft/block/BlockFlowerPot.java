package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.world.World;

public class BlockFlowerPot extends BlockContainer {

   public BlockFlowerPot() {
      super(Material.circuits);
      this.setBlockBoundsForItemRender();
   }

   public void setBlockBoundsForItemRender() {
      float var1 = 0.375F;
      float var2 = var1 / 2.0F;
      this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var1, 0.5F + var2);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public int getRenderType() {
      return 33;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      ItemStack var10 = var5.inventory.getCurrentItem();
      if(var10 != null && var10.getItem() instanceof ItemBlock) {
         TileEntityFlowerPot var11 = this.func_149929_e(var1, var2, var3, var4);
         if(var11 != null) {
            if(var11.getFlowerPotItem() != null) {
               return false;
            } else {
               Block var12 = Block.getBlockFromItem(var10.getItem());
               if(!this.func_149928_a(var12, var10.getItemDamage())) {
                  return false;
               } else {
                  var11.func_145964_a(var10.getItem(), var10.getItemDamage());
                  var11.markDirty();
                  if(!var1.setBlockMetadataWithNotify(var2, var3, var4, var10.getItemDamage(), 2)) {
                     var1.markBlockForUpdate(var2, var3, var4);
                  }

                  if(!var5.capabilities.isCreativeMode && --var10.stackSize <= 0) {
                     var5.inventory.setInventorySlotContents(var5.inventory.currentItem, (ItemStack)null);
                  }

                  return true;
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean func_149928_a(Block var1, int var2) {
      return var1 != Blocks.yellow_flower && var1 != Blocks.red_flower && var1 != Blocks.cactus && var1 != Blocks.brown_mushroom && var1 != Blocks.red_mushroom && var1 != Blocks.sapling && var1 != Blocks.deadbush?var1 == Blocks.tallgrass && var2 == 2:true;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      TileEntityFlowerPot var5 = this.func_149929_e(var1, var2, var3, var4);
      return var5 != null && var5.getFlowerPotItem() != null?var5.getFlowerPotItem():Items.flower_pot;
   }

   public int getDamageValue(World var1, int var2, int var3, int var4) {
      TileEntityFlowerPot var5 = this.func_149929_e(var1, var2, var3, var4);
      return var5 != null && var5.getFlowerPotItem() != null?var5.getFlowerPotData():0;
   }

   public boolean isFlowerPot() {
      return true;
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return super.canPlaceBlockAt(var1, var2, var3, var4) && World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4);
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4)) {
         this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
         var1.setBlockToAir(var2, var3, var4);
      }

   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      TileEntityFlowerPot var7 = this.func_149929_e(var1, var2, var3, var4);
      if(var7 != null && var7.getFlowerPotItem() != null) {
         this.dropBlockAsItem(var1, var2, var3, var4, new ItemStack(var7.getFlowerPotItem(), 1, var7.getFlowerPotData()));
      }

      super.breakBlock(var1, var2, var3, var4, var5, var6);
   }

   public void onBlockHarvested(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6) {
      super.onBlockHarvested(var1, var2, var3, var4, var5, var6);
      if(var6.capabilities.isCreativeMode) {
         TileEntityFlowerPot var7 = this.func_149929_e(var1, var2, var3, var4);
         if(var7 != null) {
            var7.func_145964_a(Item.getItemById(0), 0);
         }
      }

   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.flower_pot;
   }

   private TileEntityFlowerPot func_149929_e(World var1, int var2, int var3, int var4) {
      TileEntity var5 = var1.getTileEntity(var2, var3, var4);
      return var5 != null && var5 instanceof TileEntityFlowerPot?(TileEntityFlowerPot)var5:null;
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      Object var3 = null;
      byte var4 = 0;
      switch(var2) {
      case 1:
         var3 = Blocks.red_flower;
         var4 = 0;
         break;
      case 2:
         var3 = Blocks.yellow_flower;
         break;
      case 3:
         var3 = Blocks.sapling;
         var4 = 0;
         break;
      case 4:
         var3 = Blocks.sapling;
         var4 = 1;
         break;
      case 5:
         var3 = Blocks.sapling;
         var4 = 2;
         break;
      case 6:
         var3 = Blocks.sapling;
         var4 = 3;
         break;
      case 7:
         var3 = Blocks.red_mushroom;
         break;
      case 8:
         var3 = Blocks.brown_mushroom;
         break;
      case 9:
         var3 = Blocks.cactus;
         break;
      case 10:
         var3 = Blocks.deadbush;
         break;
      case 11:
         var3 = Blocks.tallgrass;
         var4 = 2;
         break;
      case 12:
         var3 = Blocks.sapling;
         var4 = 4;
         break;
      case 13:
         var3 = Blocks.sapling;
         var4 = 5;
      }

      return new TileEntityFlowerPot(Item.getItemFromBlock((Block)var3), var4);
   }
}
