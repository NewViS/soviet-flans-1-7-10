package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockQuartz extends Block {

   public static final String[] field_150191_a = new String[]{"default", "chiseled", "lines"};
   private static final String[] field_150189_b = new String[]{"side", "chiseled", "lines", null, null};
   private IIcon[] field_150192_M;
   private IIcon field_150193_N;
   private IIcon field_150194_O;
   private IIcon field_150190_P;
   private IIcon field_150188_Q;


   public BlockQuartz() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      if(var2 != 2 && var2 != 3 && var2 != 4) {
         if(var1 != 1 && (var1 != 0 || var2 != 1)) {
            if(var1 == 0) {
               return this.field_150188_Q;
            } else {
               if(var2 < 0 || var2 >= this.field_150192_M.length) {
                  var2 = 0;
               }

               return this.field_150192_M[var2];
            }
         } else {
            return var2 == 1?this.field_150193_N:this.field_150190_P;
         }
      } else {
         return var2 == 2 && (var1 == 1 || var1 == 0)?this.field_150194_O:(var2 == 3 && (var1 == 5 || var1 == 4)?this.field_150194_O:(var2 == 4 && (var1 == 2 || var1 == 3)?this.field_150194_O:this.field_150192_M[var2]));
      }
   }

   public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9) {
      if(var9 == 2) {
         switch(var5) {
         case 0:
         case 1:
            var9 = 2;
            break;
         case 2:
         case 3:
            var9 = 4;
            break;
         case 4:
         case 5:
            var9 = 3;
         }
      }

      return var9;
   }

   public int damageDropped(int var1) {
      return var1 != 3 && var1 != 4?var1:2;
   }

   protected ItemStack createStackedBlock(int var1) {
      return var1 != 3 && var1 != 4?super.createStackedBlock(var1):new ItemStack(Item.getItemFromBlock(this), 1, 2);
   }

   public int getRenderType() {
      return 39;
   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
      var3.add(new ItemStack(var1, 1, 2));
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_150192_M = new IIcon[field_150189_b.length];

      for(int var2 = 0; var2 < this.field_150192_M.length; ++var2) {
         if(field_150189_b[var2] == null) {
            this.field_150192_M[var2] = this.field_150192_M[var2 - 1];
         } else {
            this.field_150192_M[var2] = var1.registerIcon(this.getTextureName() + "_" + field_150189_b[var2]);
         }
      }

      this.field_150190_P = var1.registerIcon(this.getTextureName() + "_" + "top");
      this.field_150193_N = var1.registerIcon(this.getTextureName() + "_" + "chiseled_top");
      this.field_150194_O = var1.registerIcon(this.getTextureName() + "_" + "lines_top");
      this.field_150188_Q = var1.registerIcon(this.getTextureName() + "_" + "bottom");
   }

   public MapColor getMapColor(int var1) {
      return MapColor.quartzColor;
   }

}
