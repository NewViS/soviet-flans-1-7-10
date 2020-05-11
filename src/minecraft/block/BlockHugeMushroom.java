package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHugeMushroom extends Block {

   private static final String[] field_149793_a = new String[]{"skin_brown", "skin_red"};
   private final int field_149792_b;
   private IIcon[] field_149794_M;
   private IIcon field_149795_N;
   private IIcon field_149796_O;


   public BlockHugeMushroom(Material var1, int var2) {
      super(var1);
      this.field_149792_b = var2;
   }

   public IIcon getIcon(int var1, int var2) {
      return var2 == 10 && var1 > 1?this.field_149795_N:(var2 >= 1 && var2 <= 9 && var1 == 1?this.field_149794_M[this.field_149792_b]:(var2 >= 1 && var2 <= 3 && var1 == 2?this.field_149794_M[this.field_149792_b]:(var2 >= 7 && var2 <= 9 && var1 == 3?this.field_149794_M[this.field_149792_b]:((var2 == 1 || var2 == 4 || var2 == 7) && var1 == 4?this.field_149794_M[this.field_149792_b]:((var2 == 3 || var2 == 6 || var2 == 9) && var1 == 5?this.field_149794_M[this.field_149792_b]:(var2 == 14?this.field_149794_M[this.field_149792_b]:(var2 == 15?this.field_149795_N:this.field_149796_O)))))));
   }

   public int quantityDropped(Random var1) {
      int var2 = var1.nextInt(10) - 7;
      if(var2 < 0) {
         var2 = 0;
      }

      return var2;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Item.getItemById(Block.getIdFromBlock(Blocks.brown_mushroom) + this.field_149792_b);
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Item.getItemById(Block.getIdFromBlock(Blocks.brown_mushroom) + this.field_149792_b);
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_149794_M = new IIcon[field_149793_a.length];

      for(int var2 = 0; var2 < this.field_149794_M.length; ++var2) {
         this.field_149794_M[var2] = var1.registerIcon(this.getTextureName() + "_" + field_149793_a[var2]);
      }

      this.field_149796_O = var1.registerIcon(this.getTextureName() + "_" + "inside");
      this.field_149795_N = var1.registerIcon(this.getTextureName() + "_" + "skin_stem");
   }

}
