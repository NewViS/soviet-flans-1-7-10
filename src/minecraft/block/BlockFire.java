package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;

public class BlockFire extends Block {

   private int[] field_149849_a = new int[256];
   private int[] field_149848_b = new int[256];
   private IIcon[] field_149850_M;


   protected BlockFire() {
      super(Material.fire);
      this.setTickRandomly(true);
   }

   public static void func_149843_e() {
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.planks), 5, 20);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.double_wooden_slab), 5, 20);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.wooden_slab), 5, 20);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.fence), 5, 20);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.oak_stairs), 5, 20);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.birch_stairs), 5, 20);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.spruce_stairs), 5, 20);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.jungle_stairs), 5, 20);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.log), 5, 5);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.log2), 5, 5);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.leaves), 30, 60);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.leaves2), 30, 60);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.bookshelf), 30, 20);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.tnt), 15, 100);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.tallgrass), 60, 100);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.double_plant), 60, 100);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.yellow_flower), 60, 100);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.red_flower), 60, 100);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.wool), 30, 60);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.vine), 15, 100);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.coal_block), 5, 5);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.hay_block), 60, 20);
      Blocks.fire.func_149842_a(getIdFromBlock(Blocks.carpet), 60, 20);
   }

   public void func_149842_a(int var1, int var2, int var3) {
      this.field_149849_a[var1] = var2;
      this.field_149848_b[var1] = var3;
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      return null;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 3;
   }

   public int quantityDropped(Random var1) {
      return 0;
   }

   public int tickRate(World var1) {
      return 30;
   }

   public void updateTick(World var1, int var2, int var3, int var4, Random var5) {
      if(var1.getGameRules().getGameRuleBooleanValue("doFireTick")) {
         boolean var6 = var1.getBlock(var2, var3 - 1, var4) == Blocks.netherrack;
         if(var1.provider instanceof WorldProviderEnd && var1.getBlock(var2, var3 - 1, var4) == Blocks.bedrock) {
            var6 = true;
         }

         if(!this.canPlaceBlockAt(var1, var2, var3, var4)) {
            var1.setBlockToAir(var2, var3, var4);
         }

         if(!var6 && var1.isRaining() && (var1.canLightningStrikeAt(var2, var3, var4) || var1.canLightningStrikeAt(var2 - 1, var3, var4) || var1.canLightningStrikeAt(var2 + 1, var3, var4) || var1.canLightningStrikeAt(var2, var3, var4 - 1) || var1.canLightningStrikeAt(var2, var3, var4 + 1))) {
            var1.setBlockToAir(var2, var3, var4);
         } else {
            int var7 = var1.getBlockMetadata(var2, var3, var4);
            if(var7 < 15) {
               var1.setBlockMetadataWithNotify(var2, var3, var4, var7 + var5.nextInt(3) / 2, 4);
            }

            var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1) + var5.nextInt(10));
            if(!var6 && !this.canNeighborBurn(var1, var2, var3, var4)) {
               if(!World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4) || var7 > 3) {
                  var1.setBlockToAir(var2, var3, var4);
               }

            } else if(!var6 && !this.canBlockCatchFire(var1, var2, var3 - 1, var4) && var7 == 15 && var5.nextInt(4) == 0) {
               var1.setBlockToAir(var2, var3, var4);
            } else {
               boolean var8 = var1.isBlockHighHumidity(var2, var3, var4);
               byte var9 = 0;
               if(var8) {
                  var9 = -50;
               }

               this.tryCatchFire(var1, var2 + 1, var3, var4, 300 + var9, var5, var7);
               this.tryCatchFire(var1, var2 - 1, var3, var4, 300 + var9, var5, var7);
               this.tryCatchFire(var1, var2, var3 - 1, var4, 250 + var9, var5, var7);
               this.tryCatchFire(var1, var2, var3 + 1, var4, 250 + var9, var5, var7);
               this.tryCatchFire(var1, var2, var3, var4 - 1, 300 + var9, var5, var7);
               this.tryCatchFire(var1, var2, var3, var4 + 1, 300 + var9, var5, var7);

               for(int var10 = var2 - 1; var10 <= var2 + 1; ++var10) {
                  for(int var11 = var4 - 1; var11 <= var4 + 1; ++var11) {
                     for(int var12 = var3 - 1; var12 <= var3 + 4; ++var12) {
                        if(var10 != var2 || var12 != var3 || var11 != var4) {
                           int var13 = 100;
                           if(var12 > var3 + 1) {
                              var13 += (var12 - (var3 + 1)) * 100;
                           }

                           int var14 = this.getChanceOfNeighborsEncouragingFire(var1, var10, var12, var11);
                           if(var14 > 0) {
                              int var15 = (var14 + 40 + var1.difficultySetting.getDifficultyId() * 7) / (var7 + 30);
                              if(var8) {
                                 var15 /= 2;
                              }

                              if(var15 > 0 && var5.nextInt(var13) <= var15 && (!var1.isRaining() || !var1.canLightningStrikeAt(var10, var12, var11)) && !var1.canLightningStrikeAt(var10 - 1, var12, var4) && !var1.canLightningStrikeAt(var10 + 1, var12, var11) && !var1.canLightningStrikeAt(var10, var12, var11 - 1) && !var1.canLightningStrikeAt(var10, var12, var11 + 1)) {
                                 int var16 = var7 + var5.nextInt(5) / 4;
                                 if(var16 > 15) {
                                    var16 = 15;
                                 }

                                 var1.setBlock(var10, var12, var11, this, var16, 3);
                              }
                           }
                        }
                     }
                  }
               }

            }
         }
      }
   }

   public boolean func_149698_L() {
      return false;
   }

   private void tryCatchFire(World var1, int var2, int var3, int var4, int var5, Random var6, int var7) {
      int var8 = this.field_149848_b[Block.getIdFromBlock(var1.getBlock(var2, var3, var4))];
      if(var6.nextInt(var5) < var8) {
         boolean var9 = var1.getBlock(var2, var3, var4) == Blocks.tnt;
         if(var6.nextInt(var7 + 10) < 5 && !var1.canLightningStrikeAt(var2, var3, var4)) {
            int var10 = var7 + var6.nextInt(5) / 4;
            if(var10 > 15) {
               var10 = 15;
            }

            var1.setBlock(var2, var3, var4, this, var10, 3);
         } else {
            var1.setBlockToAir(var2, var3, var4);
         }

         if(var9) {
            Blocks.tnt.onBlockDestroyedByPlayer(var1, var2, var3, var4, 1);
         }
      }

   }

   private boolean canNeighborBurn(World var1, int var2, int var3, int var4) {
      return this.canBlockCatchFire(var1, var2 + 1, var3, var4)?true:(this.canBlockCatchFire(var1, var2 - 1, var3, var4)?true:(this.canBlockCatchFire(var1, var2, var3 - 1, var4)?true:(this.canBlockCatchFire(var1, var2, var3 + 1, var4)?true:(this.canBlockCatchFire(var1, var2, var3, var4 - 1)?true:this.canBlockCatchFire(var1, var2, var3, var4 + 1)))));
   }

   private int getChanceOfNeighborsEncouragingFire(World var1, int var2, int var3, int var4) {
      byte var5 = 0;
      if(!var1.isAirBlock(var2, var3, var4)) {
         return 0;
      } else {
         int var6 = this.func_149846_a(var1, var2 + 1, var3, var4, var5);
         var6 = this.func_149846_a(var1, var2 - 1, var3, var4, var6);
         var6 = this.func_149846_a(var1, var2, var3 - 1, var4, var6);
         var6 = this.func_149846_a(var1, var2, var3 + 1, var4, var6);
         var6 = this.func_149846_a(var1, var2, var3, var4 - 1, var6);
         var6 = this.func_149846_a(var1, var2, var3, var4 + 1, var6);
         return var6;
      }
   }

   public boolean isCollidable() {
      return false;
   }

   public boolean canBlockCatchFire(IBlockAccess var1, int var2, int var3, int var4) {
      return this.field_149849_a[Block.getIdFromBlock(var1.getBlock(var2, var3, var4))] > 0;
   }

   public int func_149846_a(World var1, int var2, int var3, int var4, int var5) {
      int var6 = this.field_149849_a[Block.getIdFromBlock(var1.getBlock(var2, var3, var4))];
      return var6 > var5?var6:var5;
   }

   public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4) {
      return World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4) || this.canNeighborBurn(var1, var2, var3, var4);
   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(!World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4) && !this.canNeighborBurn(var1, var2, var3, var4)) {
         var1.setBlockToAir(var2, var3, var4);
      }

   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      if(var1.provider.dimensionId > 0 || !Blocks.portal.func_150000_e(var1, var2, var3, var4)) {
         if(!World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4) && !this.canNeighborBurn(var1, var2, var3, var4)) {
            var1.setBlockToAir(var2, var3, var4);
         } else {
            var1.scheduleBlockUpdate(var2, var3, var4, this, this.tickRate(var1) + var1.rand.nextInt(10));
         }
      }
   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      if(var5.nextInt(24) == 0) {
         var1.playSound((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), "fire.fire", 1.0F + var5.nextFloat(), var5.nextFloat() * 0.7F + 0.3F, false);
      }

      int var6;
      float var7;
      float var8;
      float var9;
      if(!World.doesBlockHaveSolidTopSurface(var1, var2, var3 - 1, var4) && !Blocks.fire.canBlockCatchFire(var1, var2, var3 - 1, var4)) {
         if(Blocks.fire.canBlockCatchFire(var1, var2 - 1, var3, var4)) {
            for(var6 = 0; var6 < 2; ++var6) {
               var7 = (float)var2 + var5.nextFloat() * 0.1F;
               var8 = (float)var3 + var5.nextFloat();
               var9 = (float)var4 + var5.nextFloat();
               var1.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
            }
         }

         if(Blocks.fire.canBlockCatchFire(var1, var2 + 1, var3, var4)) {
            for(var6 = 0; var6 < 2; ++var6) {
               var7 = (float)(var2 + 1) - var5.nextFloat() * 0.1F;
               var8 = (float)var3 + var5.nextFloat();
               var9 = (float)var4 + var5.nextFloat();
               var1.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
            }
         }

         if(Blocks.fire.canBlockCatchFire(var1, var2, var3, var4 - 1)) {
            for(var6 = 0; var6 < 2; ++var6) {
               var7 = (float)var2 + var5.nextFloat();
               var8 = (float)var3 + var5.nextFloat();
               var9 = (float)var4 + var5.nextFloat() * 0.1F;
               var1.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
            }
         }

         if(Blocks.fire.canBlockCatchFire(var1, var2, var3, var4 + 1)) {
            for(var6 = 0; var6 < 2; ++var6) {
               var7 = (float)var2 + var5.nextFloat();
               var8 = (float)var3 + var5.nextFloat();
               var9 = (float)(var4 + 1) - var5.nextFloat() * 0.1F;
               var1.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
            }
         }

         if(Blocks.fire.canBlockCatchFire(var1, var2, var3 + 1, var4)) {
            for(var6 = 0; var6 < 2; ++var6) {
               var7 = (float)var2 + var5.nextFloat();
               var8 = (float)(var3 + 1) - var5.nextFloat() * 0.1F;
               var9 = (float)var4 + var5.nextFloat();
               var1.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
            }
         }
      } else {
         for(var6 = 0; var6 < 3; ++var6) {
            var7 = (float)var2 + var5.nextFloat();
            var8 = (float)var3 + var5.nextFloat() * 0.5F + 0.5F;
            var9 = (float)var4 + var5.nextFloat();
            var1.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
         }
      }

   }

   public void registerBlockIcons(IIconRegister var1) {
      this.field_149850_M = new IIcon[]{var1.registerIcon(this.getTextureName() + "_layer_0"), var1.registerIcon(this.getTextureName() + "_layer_1")};
   }

   public IIcon getFireIcon(int var1) {
      return this.field_149850_M[var1];
   }

   public IIcon getIcon(int var1, int var2) {
      return this.field_149850_M[0];
   }

   public MapColor getMapColor(int var1) {
      return MapColor.tntColor;
   }
}
