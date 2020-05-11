package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor$ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCauldron extends Block {

   private IIcon field_150029_a;
   private IIcon field_150028_b;
   private IIcon field_150030_M;


   public BlockCauldron() {
      super(Material.iron);
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 1?this.field_150028_b:(var1 == 0?this.field_150030_M:super.blockIcon);
   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_150029_a = var1.registerIcon(this.getTextureName() + "_" + "inner");
      this.field_150028_b = var1.registerIcon(this.getTextureName() + "_top");
      this.field_150030_M = var1.registerIcon(this.getTextureName() + "_" + "bottom");
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_side");
   }

   public static IIcon getCauldronIcon(String var0) {
      return var0.equals("inner")?Blocks.cauldron.field_150029_a:(var0.equals("bottom")?Blocks.cauldron.field_150030_M:null);
   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      float var8 = 0.125F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, var8, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var8);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      this.setBlockBounds(1.0F - var8, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      this.setBlockBounds(0.0F, 0.0F, 1.0F - var8, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      this.setBlockBoundsForItemRender();
   }

   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public int getRenderType() {
      return 24;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
      int var6 = func_150027_b(var1.getBlockMetadata(var2, var3, var4));
      float var7 = (float)var3 + (6.0F + (float)(3 * var6)) / 16.0F;
      if(!var1.isRemote && var5.isBurning() && var6 > 0 && var5.boundingBox.minY <= (double)var7) {
         var5.extinguish();
         this.func_150024_a(var1, var2, var3, var4, var6 - 1);
      }

   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var1.isRemote) {
         return true;
      } else {
         ItemStack var10 = var5.inventory.getCurrentItem();
         if(var10 == null) {
            return true;
         } else {
            int var11 = var1.getBlockMetadata(var2, var3, var4);
            int var12 = func_150027_b(var11);
            if(var10.getItem() == Items.water_bucket) {
               if(var12 < 3) {
                  if(!var5.capabilities.isCreativeMode) {
                     var5.inventory.setInventorySlotContents(var5.inventory.currentItem, new ItemStack(Items.bucket));
                  }

                  this.func_150024_a(var1, var2, var3, var4, 3);
               }

               return true;
            } else {
               if(var10.getItem() == Items.glass_bottle) {
                  if(var12 > 0) {
                     if(!var5.capabilities.isCreativeMode) {
                        ItemStack var13 = new ItemStack(Items.potionitem, 1, 0);
                        if(!var5.inventory.addItemStackToInventory(var13)) {
                           var1.spawnEntityInWorld(new EntityItem(var1, (double)var2 + 0.5D, (double)var3 + 1.5D, (double)var4 + 0.5D, var13));
                        } else if(var5 instanceof EntityPlayerMP) {
                           ((EntityPlayerMP)var5).sendContainerToPlayer(var5.inventoryContainer);
                        }

                        --var10.stackSize;
                        if(var10.stackSize <= 0) {
                           var5.inventory.setInventorySlotContents(var5.inventory.currentItem, (ItemStack)null);
                        }
                     }

                     this.func_150024_a(var1, var2, var3, var4, var12 - 1);
                  }
               } else if(var12 > 0 && var10.getItem() instanceof ItemArmor && ((ItemArmor)var10.getItem()).getArmorMaterial() == ItemArmor$ArmorMaterial.CLOTH) {
                  ItemArmor var14 = (ItemArmor)var10.getItem();
                  var14.removeColor(var10);
                  this.func_150024_a(var1, var2, var3, var4, var12 - 1);
                  return true;
               }

               return false;
            }
         }
      }
   }

   public void func_150024_a(World var1, int var2, int var3, int var4, int var5) {
      var1.setBlockMetadataWithNotify(var2, var3, var4, MathHelper.clamp_int(var5, 0, 3), 2);
      var1.func_147453_f(var2, var3, var4, this);
   }

   public void fillWithRain(World var1, int var2, int var3, int var4) {
      if(var1.rand.nextInt(20) == 1) {
         int var5 = var1.getBlockMetadata(var2, var3, var4);
         if(var5 < 3) {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var5 + 1, 2);
         }

      }
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.cauldron;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Items.cauldron;
   }

   public boolean hasComparatorInputOverride() {
      return true;
   }

   public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5) {
      int var6 = var1.getBlockMetadata(var2, var3, var4);
      return func_150027_b(var6);
   }

   public static int func_150027_b(int var0) {
      return var0;
   }

   public static float getRenderLiquidLevel(int var0) {
      int var1 = MathHelper.clamp_int(var0, 0, 3);
      return (float)(6 + 3 * var1) / 16.0F;
   }
}
