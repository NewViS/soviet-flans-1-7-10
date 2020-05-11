package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Blocks;
import de.ItsAMysterious.mods.reallifemod.core.blocks.BlockAirExtended;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FireplaceTE;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class fireplaceBlock extends BlockContainer {

   private Random rand;


   public fireplaceBlock() {
      super(Material.wood);
      this.func_149663_c("firepit");
      this.func_149658_d("reallifemod:fire");
      this.func_149675_a(true);
   }

   public boolean func_149703_v() {
      return true;
   }

   public TileEntity func_149915_a(World world, int id) {
      return new FireplaceTE();
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public int func_149645_b() {
      return -1;
   }

   public int func_149738_a(World p_149738_1_) {
      return 1;
   }

   public boolean canBurn(ItemStack stack) {
      ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(stack);
      return itemstack != null;
   }

   public void func_149726_b(World world, int x, int y, int z) {
      world.scheduleBlockUpdate(x, y, z, this, this.func_149738_a(world) + world.rand.nextInt(1));
   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      TileEntity te = world.getTileEntity(x, y, z);
      if(te != null && te instanceof FireplaceTE) {
         FireplaceTE tet = (FireplaceTE)te;
         if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel) {
            tet.lit();
            this.func_149715_a(1.0F);
         } else if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.water_bucket) {
            int k = player.inventory.currentItem;
            player.setCurrentItemOrArmor(k, new ItemStack(Items.bucket));
            tet.extinguish();
            this.func_149715_a(0.0F);
         }

         if(!world.isRemote && player.getCurrentEquippedItem() != null) {
            if(this.canBurn(player.getCurrentEquippedItem())) {
               player.inventory.addItemStackToInventory(FurnaceRecipes.smelting().getSmeltingResult(player.getCurrentEquippedItem()));
            }

            if(player.getCurrentEquippedItem().getItem() == Item.getItemFromBlock(RealLifeMod_Blocks.cookingrod)) {
               return false;
            }
         }
      }

      world.markBlockForUpdate(x, y, z);
      return true;
   }

   public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack) {
      int facing = MathHelper.floor_double((double)(entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;
      byte newFacing = 0;
      if(facing == 0) {
         newFacing = 2;
      }

      if(facing == 1) {
         newFacing = 5;
      }

      if(facing == 2) {
         newFacing = 3;
      }

      if(facing == 3) {
         newFacing = 4;
      }

      TileEntity te = world.getTileEntity(i, j, k);
      if(te != null && te instanceof FireplaceTE) {
         FireplaceTE x = (FireplaceTE)te;
         x.setHeightToSubstract(1.0D - world.getBlock(i, j, k).getBlockBoundsMaxY());
         x.setFacingDirection(newFacing);
         world.markBlockForUpdate(i, j, k);
      }

      for(int var14 = 0; var14 < 5; ++var14) {
         for(int y = 0; y < 3; ++y) {
            for(int z = 0; z < 5; ++z) {
               if(world.getBlock(i + var14, j + y, k + z) instanceof BlockAirExtended) {
                  BlockAirExtended airblock = (BlockAirExtended)world.getBlock(i + var14, j + y, k + z);
                  BlockAirExtended.TemperaturC = 30.0F;
               }

               world.markBlockForUpdate(i + var14, j + y, k + z);
            }
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void func_149674_a(World world, int x, int y, int z, Random rand) {
      TileEntity te = world.getTileEntity(x, y, z);
      if(te != null && te instanceof FireplaceTE) {
         FireplaceTE tile = (FireplaceTE)world.getTileEntity(x, y, z);
         if(tile.burning) {
            this.func_149715_a(1.0F);
         }

         if(!tile.burning) {
            this.func_149715_a(0.0F);
         }

         if((double)world.rainingStrength > 0.2D && world.canBlockSeeTheSky(x, y, z)) {
            ;
         }

         tile.extinguish();
         this.func_149715_a(0.0F);
      }

      world.markBlockForUpdate(x, y, z);
   }

   @SideOnly(Side.CLIENT)
   public void func_149734_b(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {}
}
