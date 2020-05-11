package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCactus extends Block {

   private IIcon field_150041_a;
   private IIcon field_150040_b;


   protected BlockCactus() {
      super(Material.cactus);
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(var1.isAirBlock(var2, var3 + 1, var4)) {
         int var6;
         for(var6 = 1; var1.getBlock(var2, var3 - var6, var4) == this; ++var6) {
            ;
         }

         if(var6 < 3) {
            int var7 = var1.getBlockMetadata(var2, var3, var4);
            if(var7 == 15) {
               var1.setBlock(var2, var3 + 1, var4, this);
               var1.setBlockMetadataWithNotify(var2, var3, var4, 0, 4);
               this.onNeighborBlockChange(var1, var2, var3 + 1, var4, this);
            } else {
               var1.setBlockMetadataWithNotify(var2, var3, var4, var7 + 1, 4);
            }
         }
      }

   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      float var5 = 0.0625F;
      return AxisAlignedBB.getBoundingBox((double)((float)var2 + var5), (double)var3, (double)((float)var4 + var5), (double)((float)(var2 + 1) - var5), (double)((float)(var3 + 1) - var5), (double)((float)(var4 + 1) - var5));
   }

   public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      float var5 = 0.0625F;
      return AxisAlignedBB.getBoundingBox((double)((float)var2 + var5), (double)var3, (double)((float)var4 + var5), (double)((float)(var2 + 1) - var5), (double)(var3 + 1), (double)((float)(var4 + 1) - var5));
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?this.field_150041_a:(var1 == 0?this.field_150040_b:super.blockIcon);
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public int getRenderType() {
      return 13;
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return !super.canPlaceBlockAt(var1, var2, var3, var4)?false:this.canBlockStay(var1, var2, var3, var4);
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!this.canBlockStay(var1, var2, var3, var4)) {
         var1.func_147480_a(var2, var3, var4, true);
      }

   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      if(var1.getBlock(var2 - 1, var3, var4).getMaterial().isSolid()) {
         return false;
      } else if(var1.getBlock(var2 + 1, var3, var4).getMaterial().isSolid()) {
         return false;
      } else if(var1.getBlock(var2, var3, var4 - 1).getMaterial().isSolid()) {
         return false;
      } else if(var1.getBlock(var2, var3, var4 + 1).getMaterial().isSolid()) {
         return false;
      } else {
         Block var5 = var1.getBlock(var2, var3 - 1, var4);
         return var5 == Blocks.cactus || var5 == Blocks.sand;
      }
   }

   public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
      var5.attackEntityFrom(DamageSource.cactus, 1.0F);
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_side");
      this.field_150041_a = var1.registerIcon(this.getTextureName() + "_top");
      this.field_150040_b = var1.registerIcon(this.getTextureName() + "_bottom");
   }
}
