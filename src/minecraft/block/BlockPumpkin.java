package net.minecraft.block;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockPumpkin extends BlockDirectional {

   private boolean field_149985_a;
   private IIcon field_149984_b;
   private IIcon field_149986_M;


   protected BlockPumpkin(boolean var1) {
      super(Material.gourd);
      this.setTickRandomly(true);
      this.field_149985_a = var1;
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?this.field_149984_b:(var1 == 0?this.field_149984_b:(var2 == 2 && var1 == 2?this.field_149986_M:(var2 == 3 && var1 == 5?this.field_149986_M:(var2 == 0 && var1 == 3?this.field_149986_M:(var2 == 1 && var1 == 4?this.field_149986_M:super.blockIcon)))));
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      super.onBlockAdded(var1, var2, var3, var4);
      if(var1.getBlock(var2, var3 - 1, var4) == Blocks.snow && var1.getBlock(var2, var3 - 2, var4) == Blocks.snow) {
         if(!var1.isRemote) {
            var1.setBlock(var2, var3, var4, getBlockById(0), 0, 2);
            var1.setBlock(var2, var3 - 1, var4, getBlockById(0), 0, 2);
            var1.setBlock(var2, var3 - 2, var4, getBlockById(0), 0, 2);
            EntitySnowman var9 = new EntitySnowman(var1);
            var9.setLocationAndAngles((double)var2 + 0.5D, (double)var3 - 1.95D, (double)var4 + 0.5D, 0.0F, 0.0F);
            var1.spawnEntityInWorld(var9);
            var1.notifyBlockChange(var2, var3, var4, getBlockById(0));
            var1.notifyBlockChange(var2, var3 - 1, var4, getBlockById(0));
            var1.notifyBlockChange(var2, var3 - 2, var4, getBlockById(0));
         }

         for(int var10 = 0; var10 < 120; ++var10) {
            var1.spawnParticle("snowshovel", (double)var2 + var1.rand.nextDouble(), (double)(var3 - 2) + var1.rand.nextDouble() * 2.5D, (double)var4 + var1.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
         }
      } else if(var1.getBlock(var2, var3 - 1, var4) == Blocks.iron_block && var1.getBlock(var2, var3 - 2, var4) == Blocks.iron_block) {
         boolean var5 = var1.getBlock(var2 - 1, var3 - 1, var4) == Blocks.iron_block && var1.getBlock(var2 + 1, var3 - 1, var4) == Blocks.iron_block;
         boolean var6 = var1.getBlock(var2, var3 - 1, var4 - 1) == Blocks.iron_block && var1.getBlock(var2, var3 - 1, var4 + 1) == Blocks.iron_block;
         if(var5 || var6) {
            var1.setBlock(var2, var3, var4, getBlockById(0), 0, 2);
            var1.setBlock(var2, var3 - 1, var4, getBlockById(0), 0, 2);
            var1.setBlock(var2, var3 - 2, var4, getBlockById(0), 0, 2);
            if(var5) {
               var1.setBlock(var2 - 1, var3 - 1, var4, getBlockById(0), 0, 2);
               var1.setBlock(var2 + 1, var3 - 1, var4, getBlockById(0), 0, 2);
            } else {
               var1.setBlock(var2, var3 - 1, var4 - 1, getBlockById(0), 0, 2);
               var1.setBlock(var2, var3 - 1, var4 + 1, getBlockById(0), 0, 2);
            }

            EntityIronGolem var7 = new EntityIronGolem(var1);
            var7.setPlayerCreated(true);
            var7.setLocationAndAngles((double)var2 + 0.5D, (double)var3 - 1.95D, (double)var4 + 0.5D, 0.0F, 0.0F);
            var1.spawnEntityInWorld(var7);

            for(int var8 = 0; var8 < 120; ++var8) {
               var1.spawnParticle("snowballpoof", (double)var2 + var1.rand.nextDouble(), (double)(var3 - 2) + var1.rand.nextDouble() * 3.9D, (double)var4 + var1.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
            }

            var1.notifyBlockChange(var2, var3, var4, getBlockById(0));
            var1.notifyBlockChange(var2, var3 - 1, var4, getBlockById(0));
            var1.notifyBlockChange(var2, var3 - 2, var4, getBlockById(0));
            if(var5) {
               var1.notifyBlockChange(var2 - 1, var3 - 1, var4, getBlockById(0));
               var1.notifyBlockChange(var2 + 1, var3 - 1, var4, getBlockById(0));
            } else {
               var1.notifyBlockChange(var2, var3 - 1, var4 - 1, getBlockById(0));
               var1.notifyBlockChange(var2, var3 - 1, var4 + 1, getBlockById(0));
            }
         }
      }

   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return var1.getBlock(var2, var3, var4).blockMaterial.isReplaceable() && World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4);
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      int var7 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
      var1.setBlockMetadataWithNotify(var2, var3, var4, var7, 2);
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_149986_M = var1.registerIcon(this.getTextureName() + "_face_" + (this.field_149985_a?"on":"off"));
      this.field_149984_b = var1.registerIcon(this.getTextureName() + "_top");
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_side");
   }
}
