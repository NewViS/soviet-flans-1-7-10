package net.minecraft.block;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSkull extends BlockContainer {

   protected BlockSkull() {
      super(Material.circuits);
      this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
   }

   public int getRenderType() {
      return -1;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.getBlockMetadata(var2, var3, var4) & 7;
      switch(var5) {
      case 1:
      default:
         this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
         break;
      case 2:
         this.setBlockBounds(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
         break;
      case 3:
         this.setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
         break;
      case 4:
         this.setBlockBounds(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
         break;
      case 5:
         this.setBlockBounds(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
      }

   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4) {
      this.setBlockBoundsBasedOnState(var1, var2, var3, var4);
      return super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4);
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      int var7 = MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
      var1.setBlockMetadataWithNotify(var2, var3, var4, var7, 2);
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntitySkull();
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Items.skull;
   }

   public int getDamageValue(World var1, int var2, int var3, int var4) {
      TileEntity var5 = var1.getTileEntity(var2, var3, var4);
      return var5 != null && var5 instanceof TileEntitySkull?((TileEntitySkull)var5).func_145904_a():super.getDamageValue(var1, var2, var3, var4);
   }

   public int damageDropped(int var1) {
      return var1;
   }

   public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7) {}

   public void onBlockHarvested(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6) {
      if(var6.capabilities.isCreativeMode) {
         var5 |= 8;
         var1.setBlockMetadataWithNotify(var2, var3, var4, var5, 4);
      }

      super.onBlockHarvested(var1, var2, var3, var4, var5, var6);
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      if(!var1.isRemote) {
         if((var6 & 8) == 0) {
            ItemStack var7 = new ItemStack(Items.skull, 1, this.getDamageValue(var1, var2, var3, var4));
            TileEntitySkull var8 = (TileEntitySkull)var1.getTileEntity(var2, var3, var4);
            if(var8.func_145904_a() == 3 && var8.func_152108_a() != null) {
               var7.setTagCompound(new NBTTagCompound());
               NBTTagCompound var9 = new NBTTagCompound();
               NBTUtil.func_152460_a(var9, var8.func_152108_a());
               var7.getTagCompound().setTag("SkullOwner", var9);
            }

            this.dropBlockAsItem(var1, var2, var3, var4, var7);
         }

         super.breakBlock(var1, var2, var3, var4, var5, var6);
      }
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.skull;
   }

   public void func_149965_a(World var1, int var2, int var3, int var4, TileEntitySkull var5) {
      if(var5.func_145904_a() == 1 && var3 >= 2 && var1.difficultySetting != EnumDifficulty.PEACEFUL && !var1.isRemote) {
         int var6;
         EntityWither var7;
         Iterator var8;
         EntityPlayer var9;
         int var10;
         for(var6 = -2; var6 <= 0; ++var6) {
            if(var1.getBlock(var2, var3 - 1, var4 + var6) == Blocks.soul_sand && var1.getBlock(var2, var3 - 1, var4 + var6 + 1) == Blocks.soul_sand && var1.getBlock(var2, var3 - 2, var4 + var6 + 1) == Blocks.soul_sand && var1.getBlock(var2, var3 - 1, var4 + var6 + 2) == Blocks.soul_sand && this.func_149966_a(var1, var2, var3, var4 + var6, 1) && this.func_149966_a(var1, var2, var3, var4 + var6 + 1, 1) && this.func_149966_a(var1, var2, var3, var4 + var6 + 2, 1)) {
               var1.setBlockMetadataWithNotify(var2, var3, var4 + var6, 8, 2);
               var1.setBlockMetadataWithNotify(var2, var3, var4 + var6 + 1, 8, 2);
               var1.setBlockMetadataWithNotify(var2, var3, var4 + var6 + 2, 8, 2);
               var1.setBlock(var2, var3, var4 + var6, getBlockById(0), 0, 2);
               var1.setBlock(var2, var3, var4 + var6 + 1, getBlockById(0), 0, 2);
               var1.setBlock(var2, var3, var4 + var6 + 2, getBlockById(0), 0, 2);
               var1.setBlock(var2, var3 - 1, var4 + var6, getBlockById(0), 0, 2);
               var1.setBlock(var2, var3 - 1, var4 + var6 + 1, getBlockById(0), 0, 2);
               var1.setBlock(var2, var3 - 1, var4 + var6 + 2, getBlockById(0), 0, 2);
               var1.setBlock(var2, var3 - 2, var4 + var6 + 1, getBlockById(0), 0, 2);
               if(!var1.isRemote) {
                  var7 = new EntityWither(var1);
                  var7.setLocationAndAngles((double)var2 + 0.5D, (double)var3 - 1.45D, (double)(var4 + var6) + 1.5D, 90.0F, 0.0F);
                  var7.renderYawOffset = 90.0F;
                  var7.func_82206_m();
                  if(!var1.isRemote) {
                     var8 = var1.getEntitiesWithinAABB(EntityPlayer.class, var7.boundingBox.expand(50.0D, 50.0D, 50.0D)).iterator();

                     while(var8.hasNext()) {
                        var9 = (EntityPlayer)var8.next();
                        var9.triggerAchievement(AchievementList.field_150963_I);
                     }
                  }

                  var1.spawnEntityInWorld(var7);
               }

               for(var10 = 0; var10 < 120; ++var10) {
                  var1.spawnParticle("snowballpoof", (double)var2 + var1.rand.nextDouble(), (double)(var3 - 2) + var1.rand.nextDouble() * 3.9D, (double)(var4 + var6 + 1) + var1.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
               }

               var1.notifyBlockChange(var2, var3, var4 + var6, getBlockById(0));
               var1.notifyBlockChange(var2, var3, var4 + var6 + 1, getBlockById(0));
               var1.notifyBlockChange(var2, var3, var4 + var6 + 2, getBlockById(0));
               var1.notifyBlockChange(var2, var3 - 1, var4 + var6, getBlockById(0));
               var1.notifyBlockChange(var2, var3 - 1, var4 + var6 + 1, getBlockById(0));
               var1.notifyBlockChange(var2, var3 - 1, var4 + var6 + 2, getBlockById(0));
               var1.notifyBlockChange(var2, var3 - 2, var4 + var6 + 1, getBlockById(0));
               return;
            }
         }

         for(var6 = -2; var6 <= 0; ++var6) {
            if(var1.getBlock(var2 + var6, var3 - 1, var4) == Blocks.soul_sand && var1.getBlock(var2 + var6 + 1, var3 - 1, var4) == Blocks.soul_sand && var1.getBlock(var2 + var6 + 1, var3 - 2, var4) == Blocks.soul_sand && var1.getBlock(var2 + var6 + 2, var3 - 1, var4) == Blocks.soul_sand && this.func_149966_a(var1, var2 + var6, var3, var4, 1) && this.func_149966_a(var1, var2 + var6 + 1, var3, var4, 1) && this.func_149966_a(var1, var2 + var6 + 2, var3, var4, 1)) {
               var1.setBlockMetadataWithNotify(var2 + var6, var3, var4, 8, 2);
               var1.setBlockMetadataWithNotify(var2 + var6 + 1, var3, var4, 8, 2);
               var1.setBlockMetadataWithNotify(var2 + var6 + 2, var3, var4, 8, 2);
               var1.setBlock(var2 + var6, var3, var4, getBlockById(0), 0, 2);
               var1.setBlock(var2 + var6 + 1, var3, var4, getBlockById(0), 0, 2);
               var1.setBlock(var2 + var6 + 2, var3, var4, getBlockById(0), 0, 2);
               var1.setBlock(var2 + var6, var3 - 1, var4, getBlockById(0), 0, 2);
               var1.setBlock(var2 + var6 + 1, var3 - 1, var4, getBlockById(0), 0, 2);
               var1.setBlock(var2 + var6 + 2, var3 - 1, var4, getBlockById(0), 0, 2);
               var1.setBlock(var2 + var6 + 1, var3 - 2, var4, getBlockById(0), 0, 2);
               if(!var1.isRemote) {
                  var7 = new EntityWither(var1);
                  var7.setLocationAndAngles((double)(var2 + var6) + 1.5D, (double)var3 - 1.45D, (double)var4 + 0.5D, 0.0F, 0.0F);
                  var7.func_82206_m();
                  if(!var1.isRemote) {
                     var8 = var1.getEntitiesWithinAABB(EntityPlayer.class, var7.boundingBox.expand(50.0D, 50.0D, 50.0D)).iterator();

                     while(var8.hasNext()) {
                        var9 = (EntityPlayer)var8.next();
                        var9.triggerAchievement(AchievementList.field_150963_I);
                     }
                  }

                  var1.spawnEntityInWorld(var7);
               }

               for(var10 = 0; var10 < 120; ++var10) {
                  var1.spawnParticle("snowballpoof", (double)(var2 + var6 + 1) + var1.rand.nextDouble(), (double)(var3 - 2) + var1.rand.nextDouble() * 3.9D, (double)var4 + var1.rand.nextDouble(), 0.0D, 0.0D, 0.0D);
               }

               var1.notifyBlockChange(var2 + var6, var3, var4, getBlockById(0));
               var1.notifyBlockChange(var2 + var6 + 1, var3, var4, getBlockById(0));
               var1.notifyBlockChange(var2 + var6 + 2, var3, var4, getBlockById(0));
               var1.notifyBlockChange(var2 + var6, var3 - 1, var4, getBlockById(0));
               var1.notifyBlockChange(var2 + var6 + 1, var3 - 1, var4, getBlockById(0));
               var1.notifyBlockChange(var2 + var6 + 2, var3 - 1, var4, getBlockById(0));
               var1.notifyBlockChange(var2 + var6 + 1, var3 - 2, var4, getBlockById(0));
               return;
            }
         }
      }

   }

   private boolean func_149966_a(World var1, int var2, int var3, int var4, int var5) {
      if(var1.getBlock(var2, var3, var4) != this) {
         return false;
      } else {
         TileEntity var6 = var1.getTileEntity(var2, var3, var4);
         return var6 != null && var6 instanceof TileEntitySkull?((TileEntitySkull)var6).func_145904_a() == var5:false;
      }
   }

   public void registerBlockIcons(IIconRegister var1) {}

   public IIcon getIcon(int var1, int var2) {
      return Blocks.soul_sand.getBlockTextureFromSide(var1);
   }

   public String getItemIconName() {
      return this.getTextureName() + "_" + ItemSkull.field_94587_a[0];
   }
}
