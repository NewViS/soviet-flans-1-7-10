package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockEnchantmentTable extends BlockContainer {

   private IIcon field_149950_a;
   private IIcon field_149949_b;


   protected BlockEnchantmentTable() {
      super(Material.rock);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
      this.setLightOpacity(0);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      super.randomDisplayTick(var1, var2, var3, var4, var5);

      for(int var6 = var2 - 2; var6 <= var2 + 2; ++var6) {
         for(int var7 = var4 - 2; var7 <= var4 + 2; ++var7) {
            if(var6 > var2 - 2 && var6 < var2 + 2 && var7 == var4 - 1) {
               var7 = var4 + 2;
            }

            if(var5.nextInt(16) == 0) {
               for(int var8 = var3; var8 <= var3 + 1; ++var8) {
                  if(var1.getBlock(var6, var8, var7) == Blocks.bookshelf) {
                     if(!var1.isAirBlock((var6 - var2) / 2 + var2, var8, (var7 - var4) / 2 + var4)) {
                        break;
                     }

                     var1.spawnParticle("enchantmenttable", (double)var2 + 0.5D, (double)var3 + 2.0D, (double)var4 + 0.5D, (double)((float)(var6 - var2) + var5.nextFloat()) - 0.5D, (double)((float)(var8 - var3) - var5.nextFloat() - 1.0F), (double)((float)(var7 - var4) + var5.nextFloat()) - 0.5D);
                  }
               }
            }
         }
      }

   }

   public boolean isOpaqueCube() {
      return false;
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 0?this.field_149949_b:(var1 == 1?this.field_149950_a:super.blockIcon);
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityEnchantmentTable();
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var1.isRemote) {
         return true;
      } else {
         TileEntityEnchantmentTable var10 = (TileEntityEnchantmentTable)var1.getTileEntity(var2, var3, var4);
         var5.displayGUIEnchantment(var2, var3, var4, var10.func_145921_b()?var10.func_145919_a():null);
         return true;
      }
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      super.onBlockPlacedBy(var1, var2, var3, var4, var5, var6);
      if(var6.hasDisplayName()) {
         ((TileEntityEnchantmentTable)var1.getTileEntity(var2, var3, var4)).func_145920_a(var6.getDisplayName());
      }

   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_" + "side");
      this.field_149950_a = var1.registerIcon(this.getTextureName() + "_" + "top");
      this.field_149949_b = var1.registerIcon(this.getTextureName() + "_" + "bottom");
   }
}
