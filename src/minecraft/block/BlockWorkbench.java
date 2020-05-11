package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockWorkbench extends Block {

   private IIcon field_150035_a;
   private IIcon field_150034_b;


   protected BlockWorkbench() {
      super(Material.wood);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?this.field_150035_a:(var1 == 0?Blocks.planks.getBlockTextureFromSide(var1):(var1 != 2 && var1 != 4?super.blockIcon:this.field_150034_b));
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_side");
      this.field_150035_a = var1.registerIcon(this.getTextureName() + "_top");
      this.field_150034_b = var1.registerIcon(this.getTextureName() + "_front");
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var1.isRemote) {
         return true;
      } else {
         var5.displayGUIWorkbench(var2, var3, var4);
         return true;
      }
   }
}
