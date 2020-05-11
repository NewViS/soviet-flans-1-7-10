package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockMelon extends Block {

   private IIcon field_150201_a;


   protected BlockMelon() {
      super(Material.gourd);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 != 1 && var1 != 0?super.blockIcon:this.field_150201_a;
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.melon;
   }

   public int quantityDropped(Random var1) {
      return 3 + var1.nextInt(5);
   }

   public int quantityDroppedWithBonus(int var1, Random var2) {
      int var3 = this.quantityDropped(var2) + var2.nextInt(1 + var1);
      if(var3 > 9) {
         var3 = 9;
      }

      return var3;
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_side");
      this.field_150201_a = var1.registerIcon(this.getTextureName() + "_top");
   }
}
