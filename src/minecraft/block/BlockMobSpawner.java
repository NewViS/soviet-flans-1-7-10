package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class BlockMobSpawner extends BlockContainer {

   protected BlockMobSpawner() {
      super(Material.rock);
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityMobSpawner();
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return null;
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);
      int var8 = 15 + var1.rand.nextInt(15) + var1.rand.nextInt(15);
      this.dropXpOnBlockBreak(var1, var2, var3, var4, var8);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Item.getItemById(0);
   }
}
