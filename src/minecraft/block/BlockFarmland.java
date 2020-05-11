package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockFarmland extends Block {

   private IIcon field_149824_a;
   private IIcon field_149823_b;


   protected BlockFarmland() {
      super(Material.ground);
      this.setTickRandomly(true);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
      this.setLightOpacity(255);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return AxisAlignedBB.getBoundingBox((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 0), (double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1));
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?(var2 > 0?this.field_149824_a:this.field_149823_b):Blocks.dirt.getBlockTextureFromSide(var1);
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(!this.func_149821_m(var1, var2, var3, var4) && !var1.canLightningStrikeAt(var2, var3 + 1, var4)) {
         int var6 = var1.getBlockMetadata(var2, var3, var4);
         if(var6 > 0) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6 - 1, 2);
         } else if(!this.func_149822_e(var1, var2, var3, var4)) {
            var1.setBlock(var2, var3, var4, Blocks.dirt);
         }
      } else {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 7, 2);
      }

   }

   public void onFallenUpon(World var1, int var2, int var3, int var4, Entity var5, float var6) {
      if(!var1.isRemote && var1.rand.nextFloat() < var6 - 0.5F) {
         if(!(var5 instanceof EntityPlayer) && !var1.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
            return;
         }

         var1.setBlock(var2, var3, var4, Blocks.dirt);
      }

   }

   private boolean func_149822_e(World var1, int var2, int var3, int var4) {
      byte var5 = 0;

      for(int var6 = var2 - var5; var6 <= var2 + var5; ++var6) {
         for(int var7 = var4 - var5; var7 <= var4 + var5; ++var7) {
            Block var8 = var1.getBlock(var6, var3 + 1, var7);
            if(var8 == Blocks.wheat || var8 == Blocks.melon_stem || var8 == Blocks.pumpkin_stem || var8 == Blocks.potatoes || var8 == Blocks.carrots) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean func_149821_m(World var1, int var2, int var3, int var4) {
      for(int var5 = var2 - 4; var5 <= var2 + 4; ++var5) {
         for(int var6 = var3; var6 <= var3 + 1; ++var6) {
            for(int var7 = var4 - 4; var7 <= var4 + 4; ++var7) {
               if(var1.getBlock(var5, var6, var7).getMaterial() == Material.water) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      super.onNeighborBlockChange(var1, var2, var3, var4, var5);
      Material var6 = var1.getBlock(var2, var3 + 1, var4).getMaterial();
      if(var6.isSolid()) {
         var1.setBlock(var2, var3, var4, Blocks.dirt);
      }

   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Blocks.dirt.getItemDropped(0, var2, var3);
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Item.getItemFromBlock(Blocks.dirt);
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_149824_a = var1.registerIcon(this.getTextureName() + "_wet");
      this.field_149823_b = var1.registerIcon(this.getTextureName() + "_dry");
   }
}
