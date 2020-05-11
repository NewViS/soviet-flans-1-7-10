package net.minecraft.block;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockHay extends BlockRotatedPillar {

   public BlockHay() {
      super(Material.grass);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   protected IIcon getSideIcon(int var1) {
      return super.blockIcon;
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.field_150164_N = var1.registerIcon(this.getTextureName() + "_top");
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_side");
   }
}
