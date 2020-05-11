package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCake extends Block {

   private IIcon field_150038_a;
   private IIcon field_150037_b;
   private IIcon field_150039_M;


   protected BlockCake() {
      super(Material.cake);
      this.setTickRandomly(true);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      float var6 = 0.0625F;
      float var7 = (float)(1 + var5 * 2) / 16.0F;
      float var8 = 0.5F;
      this.setBlockBounds(var7, 0.0F, var6, 1.0F - var6, var8, 1.0F - var6);
   }

   public void setBlockBoundsForItemRender() {
      float var1 = 0.0625F;
      float var2 = 0.5F;
      this.setBlockBounds(var1, 0.0F, var1, 1.0F - var1, var2, 1.0F - var1);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      float var6 = 0.0625F;
      float var7 = (float)(1 + var5 * 2) / 16.0F;
      float var8 = 0.5F;
      return AxisAlignedBB.getBoundingBox((double)((float)var2 + var7), (double)var3, (double)((float)var4 + var6), (double)((float)(var2 + 1) - var6), (double)((float)var3 + var8 - var6), (double)((float)(var4 + 1) - var6));
   }

   public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4);
      float var6 = 0.0625F;
      float var7 = (float)(1 + var5 * 2) / 16.0F;
      float var8 = 0.5F;
      return AxisAlignedBB.getBoundingBox((double)((float)var2 + var7), (double)var3, (double)((float)var4 + var6), (double)((float)(var2 + 1) - var6), (double)((float)var3 + var8), (double)((float)(var4 + 1) - var6));
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?this.field_150038_a:(var1 == 0?this.field_150037_b:(var2 > 0 && var1 == 4?this.field_150039_M:super.blockIcon));
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_side");
      this.field_150039_M = var1.registerIcon(this.getTextureName() + "_inner");
      this.field_150038_a = var1.registerIcon(this.getTextureName() + "_top");
      this.field_150037_b = var1.registerIcon(this.getTextureName() + "_bottom");
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      this.func_150036_b(var1, var2, var3, var4, var5);
      return true;
   }

   public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5) {
      this.func_150036_b(var1, var2, var3, var4, var5);
   }

   private void func_150036_b(World var1, int var2, int var3, int var4, EntityPlayer var5) {
      if(var5.canEat(false)) {
         var5.getFoodStats().addStats(2, 0.1F);
         int var6 = var1.getBlockMetadata(var2, var3, var4) + 1;
         if(var6 >= 6) {
            var1.setBlockToAir(var2, var3, var4);
         } else {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6, 2);
         }
      }

   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return !super.canPlaceBlockAt(var1, var2, var3, var4)?false:this.canBlockStay(var1, var2, var3, var4);
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!this.canBlockStay(var1, var2, var3, var4)) {
         var1.setBlockToAir(var2, var3, var4);
      }

   }

   public boolean canBlockStay(World var1, int var2, int var3, int var4) {
      return var1.getBlock(var2, var3 - 1, var4).getMaterial().isSolid();
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return null;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Items.cake;
   }
}
