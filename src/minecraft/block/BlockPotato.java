package net.minecraft.block;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPotato extends BlockCrops {

   private IIcon[] field_149869_a;


   public IIcon getIcon(int var1, int var2) {
      if(var2 < 7) {
         if(var2 == 6) {
            var2 = 5;
         }

         return this.field_149869_a[var2 >> 1];
      } else {
         return this.field_149869_a[3];
      }
   }

   protected Item func_149866_i() {
      return Items.potato;
   }

   protected Item func_149865_P() {
      return Items.potato;
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {
      super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);
      if(!var1.isRemote) {
         if(var5 >= 7 && var1.rand.nextInt(50) == 0) {
            this.dropBlockAsItem(var1, var2, var3, var4, new ItemStack(Items.poisonous_potato));
         }

      }
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_149869_a = new IIcon[4];

      for(int var2 = 0; var2 < this.field_149869_a.length; ++var2) {
         this.field_149869_a[var2] = var1.registerIcon(this.getTextureName() + "_stage_" + var2);
      }

   }
}
