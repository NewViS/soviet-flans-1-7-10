package net.minecraft.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockLog;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemDye extends Item {

   public static final String[] field_150923_a = new String[]{"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white"};
   public static final String[] field_150921_b = new String[]{"black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
   public static final int[] field_150922_c = new int[]{1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320};
   private IIcon[] field_150920_d;


   public ItemDye() {
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }

   public IIcon getIconFromDamage(int var1) {
      int var2 = MathHelper.clamp_int(var1, 0, 15);
      return this.field_150920_d[var2];
   }

   public String getUnlocalizedName(ItemStack var1) {
      int var2 = MathHelper.clamp_int(var1.getItemDamage(), 0, 15);
      return super.getUnlocalizedName() + "." + field_150923_a[var2];
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(!var2.canPlayerEdit(var4, var5, var6, var7, var1)) {
         return false;
      } else {
         if(var1.getItemDamage() == 15) {
            if(func_150919_a(var1, var3, var4, var5, var6)) {
               if(!var3.isRemote) {
                  var3.playAuxSFX(2005, var4, var5, var6, 0);
               }

               return true;
            }
         } else if(var1.getItemDamage() == 3) {
            Block var11 = var3.getBlock(var4, var5, var6);
            int var12 = var3.getBlockMetadata(var4, var5, var6);
            if(var11 == Blocks.log && BlockLog.func_150165_c(var12) == 3) {
               if(var7 == 0) {
                  return false;
               }

               if(var7 == 1) {
                  return false;
               }

               if(var7 == 2) {
                  --var6;
               }

               if(var7 == 3) {
                  ++var6;
               }

               if(var7 == 4) {
                  --var4;
               }

               if(var7 == 5) {
                  ++var4;
               }

               if(var3.isAirBlock(var4, var5, var6)) {
                  int var13 = Blocks.cocoa.onBlockPlaced(var3, var4, var5, var6, var7, var8, var9, var10, 0);
                  var3.setBlock(var4, var5, var6, Blocks.cocoa, var13, 2);
                  if(!var2.capabilities.isCreativeMode) {
                     --var1.stackSize;
                  }
               }

               return true;
            }
         }

         return false;
      }
   }

   public static boolean func_150919_a(ItemStack var0, World var1, int var2, int var3, int var4) {
      Block var5 = var1.getBlock(var2, var3, var4);
      if(var5 instanceof IGrowable) {
         IGrowable var6 = (IGrowable)var5;
         if(var6.func_149851_a(var1, var2, var3, var4, var1.isRemote)) {
            if(!var1.isRemote) {
               if(var6.func_149852_a(var1, var1.rand, var2, var3, var4)) {
                  var6.func_149853_b(var1, var1.rand, var2, var3, var4);
               }

               --var0.stackSize;
            }

            return true;
         }
      }

      return false;
   }

   public static void func_150918_a(World var0, int var1, int var2, int var3, int var4) {
      if(var4 == 0) {
         var4 = 15;
      }

      Block var5 = var0.getBlock(var1, var2, var3);
      if(var5.getMaterial() != Material.air) {
         var5.setBlockBoundsBasedOnState(var0, var1, var2, var3);

         for(int var6 = 0; var6 < var4; ++var6) {
            double var7 = Item.itemRand.nextGaussian() * 0.02D;
            double var9 = Item.itemRand.nextGaussian() * 0.02D;
            double var11 = Item.itemRand.nextGaussian() * 0.02D;
            var0.spawnParticle("happyVillager", (double)((float)var1 + Item.itemRand.nextFloat()), (double)var2 + (double)Item.itemRand.nextFloat() * var5.getBlockBoundsMaxY(), (double)((float)var3 + Item.itemRand.nextFloat()), var7, var9, var11);
         }

      }
   }

   public boolean itemInteractionForEntity(ItemStack var1, EntityPlayer var2, EntityLivingBase var3) {
      if(var3 instanceof EntitySheep) {
         EntitySheep var4 = (EntitySheep)var3;
         int var5 = BlockColored.func_150032_b(var1.getItemDamage());
         if(!var4.getSheared() && var4.getFleeceColor() != var5) {
            var4.setFleeceColor(var5);
            --var1.stackSize;
         }

         return true;
      } else {
         return false;
      }
   }

   public void getSubItems(Item var1, CreativeTabs var2, List var3) {
      for(int var4 = 0; var4 < 16; ++var4) {
         var3.add(new ItemStack(var1, 1, var4));
      }

   }

   public void registerIcons(IIconRegister var1) {
      this.field_150920_d = new IIcon[field_150921_b.length];

      for(int var2 = 0; var2 < field_150921_b.length; ++var2) {
         this.field_150920_d[var2] = var1.registerIcon(this.getIconString() + "_" + field_150921_b[var2]);
      }

   }

}
