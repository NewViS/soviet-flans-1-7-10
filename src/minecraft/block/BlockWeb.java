package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockWeb extends Block {

   public BlockWeb() {
      super(Material.web);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
      var5.setInWeb();
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return null;
   }

   public int getRenderType() {
      return 1;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.string;
   }

   protected boolean canSilkHarvest() {
      return true;
   }
}
