package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailBase$Rail;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockRail extends BlockRailBase {

   private IIcon field_150056_b;


   protected BlockRail() {
      super(false);
   }

   public IIcon getIcon(int var1, int var2) {
      return var2 >= 6?this.field_150056_b:super.blockIcon;
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.registerBlockIcons(var1);
      this.field_150056_b = var1.registerIcon(this.getTextureName() + "_turned");
   }

   protected void func_150048_a(World var1, int var2, int var3, int var4, int var5, int var6, Block var7) {
      if(var7.canProvidePower() && (new BlockRailBase$Rail(this, var1, var2, var3, var4)).func_150650_a() == 3) {
         this.func_150052_a(var1, var2, var3, var4, false);
      }

   }
}
