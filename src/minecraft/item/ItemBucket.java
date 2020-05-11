package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.world.World;

public class ItemBucket extends Item {

   private Block isFull;


   public ItemBucket(Block var1) {
      super.maxStackSize = 1;
      this.isFull = var1;
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      boolean var4 = this.isFull == Blocks.air;
      MovingObjectPosition var5 = this.getMovingObjectPositionFromPlayer(var2, var3, var4);
      if(var5 == null) {
         return var1;
      } else {
         if(var5.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK) {
            int var6 = var5.blockX;
            int var7 = var5.blockY;
            int var8 = var5.blockZ;
            if(!var2.canMineBlock(var3, var6, var7, var8)) {
               return var1;
            }

            if(var4) {
               if(!var3.canPlayerEdit(var6, var7, var8, var5.sideHit, var1)) {
                  return var1;
               }

               Material var9 = var2.getBlock(var6, var7, var8).getMaterial();
               int var10 = var2.getBlockMetadata(var6, var7, var8);
               if(var9 == Material.water && var10 == 0) {
                  var2.setBlockToAir(var6, var7, var8);
                  return this.func_150910_a(var1, var3, Items.water_bucket);
               }

               if(var9 == Material.lava && var10 == 0) {
                  var2.setBlockToAir(var6, var7, var8);
                  return this.func_150910_a(var1, var3, Items.lava_bucket);
               }
            } else {
               if(this.isFull == Blocks.air) {
                  return new ItemStack(Items.bucket);
               }

               if(var5.sideHit == 0) {
                  --var7;
               }

               if(var5.sideHit == 1) {
                  ++var7;
               }

               if(var5.sideHit == 2) {
                  --var8;
               }

               if(var5.sideHit == 3) {
                  ++var8;
               }

               if(var5.sideHit == 4) {
                  --var6;
               }

               if(var5.sideHit == 5) {
                  ++var6;
               }

               if(!var3.canPlayerEdit(var6, var7, var8, var5.sideHit, var1)) {
                  return var1;
               }

               if(this.tryPlaceContainedLiquid(var2, var6, var7, var8) && !var3.capabilities.isCreativeMode) {
                  return new ItemStack(Items.bucket);
               }
            }
         }

         return var1;
      }
   }

   private ItemStack func_150910_a(ItemStack var1, EntityPlayer var2, Item var3) {
      if(var2.capabilities.isCreativeMode) {
         return var1;
      } else if(--var1.stackSize <= 0) {
         return new ItemStack(var3);
      } else {
         if(!var2.inventory.addItemStackToInventory(new ItemStack(var3))) {
            var2.dropPlayerItemWithRandomChoice(new ItemStack(var3, 1, 0), false);
         }

         return var1;
      }
   }

   public boolean tryPlaceContainedLiquid(World var1, int var2, int var3, int var4) {
      if(this.isFull == Blocks.air) {
         return false;
      } else {
         Material var5 = var1.getBlock(var2, var3, var4).getMaterial();
         boolean var6 = !var5.isSolid();
         if(!var1.isAirBlock(var2, var3, var4) && !var6) {
            return false;
         } else {
            if(var1.provider.isHellWorld && this.isFull == Blocks.flowing_water) {
               var1.playSoundEffect((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), "random.fizz", 0.5F, 2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);

               for(int var7 = 0; var7 < 8; ++var7) {
                  var1.spawnParticle("largesmoke", (double)var2 + Math.random(), (double)var3 + Math.random(), (double)var4 + Math.random(), 0.0D, 0.0D, 0.0D);
               }
            } else {
               if(!var1.isRemote && var6 && !var5.isLiquid()) {
                  var1.func_147480_a(var2, var3, var4, true);
               }

               var1.setBlock(var2, var3, var4, this.isFull, 0, 3);
            }

            return true;
         }
      }
   }
}
