package net.minecraft.block;

import java.util.List;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAnvil extends BlockFalling {

   public static final String[] anvilDamageNames = new String[]{"intact", "slightlyDamaged", "veryDamaged"};
   private static final String[] anvilIconNames = new String[]{"anvil_top_damaged_0", "anvil_top_damaged_1", "anvil_top_damaged_2"};
   public int anvilRenderSide;
   private IIcon[] anvilIcons;


   protected BlockAnvil() {
      super(Material.anvil);
      this.setLightOpacity(0);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public IIcon getIcon(int var1, int var2) {
      if(this.anvilRenderSide == 3 && var1 == 1) {
         int var3 = (var2 >> 2) % this.anvilIcons.length;
         return this.anvilIcons[var3];
      } else {
         return super.blockIcon;
      }
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon("anvil_base");
      this.anvilIcons = new IIcon[anvilIconNames.length];

      for(int var2 = 0; var2 < this.anvilIcons.length; ++var2) {
         this.anvilIcons[var2] = var1.registerIcon(anvilIconNames[var2]);
      }

   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      int var7 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      int var8 = var1.getBlockMetadata(var2, var3, var4) >> 2;
      ++var7;
      var7 %= 4;
      if(var7 == 0) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 2 | var8 << 2, 2);
      }

      if(var7 == 1) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 3 | var8 << 2, 2);
      }

      if(var7 == 2) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 0 | var8 << 2, 2);
      }

      if(var7 == 3) {
         var1.setBlockMetadataWithNotify(var2, var3, var4, 1 | var8 << 2, 2);
      }

   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var1.isRemote) {
         return true;
      } else {
         var5.displayGUIAnvil(var2, var3, var4);
         return true;
      }
   }

   public int getRenderType() {
      return 35;
   }

   public int damageDropped(int var1) {
      return var1 >> 2;
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4) & 3;
      if(var5 != 3 && var5 != 1) {
         this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
      } else {
         this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
      }

   }

   public void getSubBlocks(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
      var3.add(new ItemStack(var1, 1, 2));
   }

   protected void func_149829_a(EntityFallingBlock var1) {
      var1.func_145806_a(true);
   }

   public void func_149828_a(World var1, int var2, int var3, int var4, int var5) {
      var1.playAuxSFX(1022, var2, var3, var4, 0);
   }

   public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
      return true;
   }

}
