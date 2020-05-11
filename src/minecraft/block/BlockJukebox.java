package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockJukebox$TileEntityJukebox;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockJukebox extends BlockContainer {

   private IIcon field_149927_a;


   protected BlockJukebox() {
      super(Material.wood);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?this.field_149927_a:super.blockIcon;
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var1.getBlockMetadata(var2, var3, var4) == 0) {
         return false;
      } else {
         this.func_149925_e(var1, var2, var3, var4);
         return true;
      }
   }

   public void func_149926_b(World var1, int var2, int var3, int var4, ItemStack var5) {
      if(!var1.isRemote) {
         BlockJukebox$TileEntityJukebox var6 = (BlockJukebox$TileEntityJukebox)var1.getTileEntity(var2, var3, var4);
         if(var6 != null) {
            var6.func_145857_a(var5.copy());
            var1.setBlockMetadataWithNotify(var2, var3, var4, 1, 2);
         }
      }
   }

   public void func_149925_e(World var1, int var2, int var3, int var4) {
      if(!var1.isRemote) {
         BlockJukebox$TileEntityJukebox var5 = (BlockJukebox$TileEntityJukebox)var1.getTileEntity(var2, var3, var4);
         if(var5 != null) {
            ItemStack var6 = var5.func_145856_a();
            if(var6 != null) {
               var1.playAuxSFX(1005, var2, var3, var4, 0);
               var1.playRecord((String)null, var2, var3, var4);
               var5.func_145857_a((ItemStack)null);
               var1.setBlockMetadataWithNotify(var2, var3, var4, 0, 2);
               float var7 = 0.7F;
               double var8 = (double)(var1.rand.nextFloat() * var7) + (double)(1.0F - var7) * 0.5D;
               double var10 = (double)(var1.rand.nextFloat() * var7) + (double)(1.0F - var7) * 0.2D + 0.6D;
               double var12 = (double)(var1.rand.nextFloat() * var7) + (double)(1.0F - var7) * 0.5D;
               ItemStack var14 = var6.copy();
               EntityItem var15 = new EntityItem(var1, (double)var2 + var8, (double)var3 + var10, (double)var4 + var12, var14);
               var15.delayBeforeCanPickup = 10;
               var1.spawnEntityInWorld(var15);
            }
         }
      }
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      this.func_149925_e(var1, var2, var3, var4);
      super.breakBlock(var1, var2, var3, var4, var5, var6);
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      if(!var1.isRemote) {
         super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, 0);
      }
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new BlockJukebox$TileEntityJukebox();
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_side");
      this.field_149927_a = var1.registerIcon(this.getTextureName() + "_top");
   }

   public boolean hasComparatorInputOverride() {
      return true;
   }

   public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5) {
      ItemStack var6 = ((BlockJukebox$TileEntityJukebox)var1.getTileEntity(var2, var3, var4)).func_145856_a();
      return var6 == null?0:Item.getIdFromItem(var6.getItem()) + 1 - Item.getIdFromItem(Items.record_13);
   }
}
