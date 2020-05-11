package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDungeons extends WorldGenerator {

   private static final WeightedRandomChestContent[] field_111189_a = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 4, 10), new WeightedRandomChestContent(Items.bread, 0, 1, 1, 10), new WeightedRandomChestContent(Items.wheat, 0, 1, 4, 10), new WeightedRandomChestContent(Items.gunpowder, 0, 1, 4, 10), new WeightedRandomChestContent(Items.string, 0, 1, 4, 10), new WeightedRandomChestContent(Items.bucket, 0, 1, 1, 10), new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 1), new WeightedRandomChestContent(Items.redstone, 0, 1, 4, 10), new WeightedRandomChestContent(Items.record_13, 0, 1, 1, 10), new WeightedRandomChestContent(Items.record_cat, 0, 1, 1, 10), new WeightedRandomChestContent(Items.name_tag, 0, 1, 1, 10), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 2), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 5), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)};


   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      byte var6 = 3;
      int var7 = var2.nextInt(2) + 2;
      int var8 = var2.nextInt(2) + 2;
      int var9 = 0;

      int var10;
      int var11;
      int var12;
      for(var10 = var3 - var7 - 1; var10 <= var3 + var7 + 1; ++var10) {
         for(var11 = var4 - 1; var11 <= var4 + var6 + 1; ++var11) {
            for(var12 = var5 - var8 - 1; var12 <= var5 + var8 + 1; ++var12) {
               Material var13 = var1.getBlock(var10, var11, var12).getMaterial();
               if(var11 == var4 - 1 && !var13.isSolid()) {
                  return false;
               }

               if(var11 == var4 + var6 + 1 && !var13.isSolid()) {
                  return false;
               }

               if((var10 == var3 - var7 - 1 || var10 == var3 + var7 + 1 || var12 == var5 - var8 - 1 || var12 == var5 + var8 + 1) && var11 == var4 && var1.isAirBlock(var10, var11, var12) && var1.isAirBlock(var10, var11 + 1, var12)) {
                  ++var9;
               }
            }
         }
      }

      if(var9 >= 1 && var9 <= 5) {
         for(var10 = var3 - var7 - 1; var10 <= var3 + var7 + 1; ++var10) {
            for(var11 = var4 + var6; var11 >= var4 - 1; --var11) {
               for(var12 = var5 - var8 - 1; var12 <= var5 + var8 + 1; ++var12) {
                  if(var10 != var3 - var7 - 1 && var11 != var4 - 1 && var12 != var5 - var8 - 1 && var10 != var3 + var7 + 1 && var11 != var4 + var6 + 1 && var12 != var5 + var8 + 1) {
                     var1.setBlockToAir(var10, var11, var12);
                  } else if(var11 >= 0 && !var1.getBlock(var10, var11 - 1, var12).getMaterial().isSolid()) {
                     var1.setBlockToAir(var10, var11, var12);
                  } else if(var1.getBlock(var10, var11, var12).getMaterial().isSolid()) {
                     if(var11 == var4 - 1 && var2.nextInt(4) != 0) {
                        var1.setBlock(var10, var11, var12, Blocks.mossy_cobblestone, 0, 2);
                     } else {
                        var1.setBlock(var10, var11, var12, Blocks.cobblestone, 0, 2);
                     }
                  }
               }
            }
         }

         var10 = 0;

         while(var10 < 2) {
            var11 = 0;

            while(true) {
               if(var11 < 3) {
                  label197: {
                     var12 = var3 + var2.nextInt(var7 * 2 + 1) - var7;
                     int var14 = var5 + var2.nextInt(var8 * 2 + 1) - var8;
                     if(var1.isAirBlock(var12, var4, var14)) {
                        int var15 = 0;
                        if(var1.getBlock(var12 - 1, var4, var14).getMaterial().isSolid()) {
                           ++var15;
                        }

                        if(var1.getBlock(var12 + 1, var4, var14).getMaterial().isSolid()) {
                           ++var15;
                        }

                        if(var1.getBlock(var12, var4, var14 - 1).getMaterial().isSolid()) {
                           ++var15;
                        }

                        if(var1.getBlock(var12, var4, var14 + 1).getMaterial().isSolid()) {
                           ++var15;
                        }

                        if(var15 == 1) {
                           var1.setBlock(var12, var4, var14, Blocks.chest, 0, 2);
                           WeightedRandomChestContent[] var16 = WeightedRandomChestContent.func_92080_a(field_111189_a, new WeightedRandomChestContent[]{Items.enchanted_book.func_92114_b(var2)});
                           TileEntityChest var17 = (TileEntityChest)var1.getTileEntity(var12, var4, var14);
                           if(var17 != null) {
                              WeightedRandomChestContent.generateChestContents(var2, var16, var17, 8);
                           }
                           break label197;
                        }
                     }

                     ++var11;
                     continue;
                  }
               }

               ++var10;
               break;
            }
         }

         var1.setBlock(var3, var4, var5, Blocks.mob_spawner, 0, 2);
         TileEntityMobSpawner var18 = (TileEntityMobSpawner)var1.getTileEntity(var3, var4, var5);
         if(var18 != null) {
            var18.func_145881_a().setEntityName(this.pickMobSpawner(var2));
         } else {
            System.err.println("Failed to fetch mob spawner entity at (" + var3 + ", " + var4 + ", " + var5 + ")");
         }

         return true;
      } else {
         return false;
      }
   }

   private String pickMobSpawner(Random var1) {
      int var2 = var1.nextInt(4);
      return var2 == 0?"Skeleton":(var2 == 1?"Zombie":(var2 == 2?"Zombie":(var2 == 3?"Spider":"")));
   }

}
