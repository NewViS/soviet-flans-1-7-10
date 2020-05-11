package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;

public class BlockBreakable extends Block {

   private boolean field_149996_a;
   private String field_149995_b;


   protected BlockBreakable(String var1, Material var2, boolean var3) {
      super(var2);
      this.field_149996_a = var3;
      this.field_149995_b = var1;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      Block var6 = var1.getBlock(var2, var3, var4);
      if(this == Blocks.glass || this == Blocks.stained_glass) {
         if(var1.getBlockMetadata(var2, var3, var4) != var1.getBlockMetadata(var2 - Facing.offsetsXForSide[var5], var3 - Facing.offsetsYForSide[var5], var4 - Facing.offsetsZForSide[var5])) {
            return true;
         }

         if(var6 == this) {
            return false;
         }
      }

      return !this.field_149996_a && var6 == this?false:super.shouldSideBeRendered(var1, var2, var3, var4, var5);
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.field_149995_b);
   }
}
