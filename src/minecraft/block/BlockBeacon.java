package net.minecraft.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.world.World;

public class BlockBeacon extends BlockContainer {

   public BlockBeacon() {
      super(Material.glass);
      this.setHardness(3.0F);
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityBeacon();
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var1.isRemote) {
         return true;
      } else {
         TileEntityBeacon var10 = (TileEntityBeacon)var1.getTileEntity(var2, var3, var4);
         if(var10 != null) {
            var5.func_146104_a(var10);
         }

         return true;
      }
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 34;
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.registerBlockIcons(var1);
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      super.onBlockPlacedBy(var1, var2, var3, var4, var5, var6);
      if(var6.hasDisplayName()) {
         ((TileEntityBeacon)var1.getTileEntity(var2, var3, var4)).func_145999_a(var6.getDisplayName());
      }

   }
}
