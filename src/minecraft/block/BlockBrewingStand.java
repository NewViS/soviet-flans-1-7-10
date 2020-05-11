package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockBrewingStand extends BlockContainer {

   private Random field_149961_a = new Random();
   private IIcon iconBrewingStandBase;


   public BlockBrewingStand() {
      super(Material.iron);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public int getRenderType() {
      return 25;
   }

   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityBrewingStand();
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7) {
      this.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.875F, 0.5625F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
      this.setBlockBoundsForItemRender();
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
   }

   public void setBlockBoundsForItemRender() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var1.isRemote) {
         return true;
      } else {
         TileEntityBrewingStand var10 = (TileEntityBrewingStand)var1.getTileEntity(var2, var3, var4);
         if(var10 != null) {
            var5.func_146098_a(var10);
         }

         return true;
      }
   }

   public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLivingBase var5, ItemStack var6) {
      if(var6.hasDisplayName()) {
         ((TileEntityBrewingStand)var1.getTileEntity(var2, var3, var4)).func_145937_a(var6.getDisplayName());
      }

   }

   public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
      double var6 = (double)((float)var2 + 0.4F + var5.nextFloat() * 0.2F);
      double var8 = (double)((float)var3 + 0.7F + var5.nextFloat() * 0.3F);
      double var10 = (double)((float)var4 + 0.4F + var5.nextFloat() * 0.2F);
      var1.spawnParticle("smoke", var6, var8, var10, 0.0D, 0.0D, 0.0D);
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      TileEntity var7 = var1.getTileEntity(var2, var3, var4);
      if(var7 instanceof TileEntityBrewingStand) {
         TileEntityBrewingStand var8 = (TileEntityBrewingStand)var7;

         for(int var9 = 0; var9 < var8.getSizeInventory(); ++var9) {
            ItemStack var10 = var8.getStackInSlot(var9);
            if(var10 != null) {
               float var11 = this.field_149961_a.nextFloat() * 0.8F + 0.1F;
               float var12 = this.field_149961_a.nextFloat() * 0.8F + 0.1F;
               float var13 = this.field_149961_a.nextFloat() * 0.8F + 0.1F;

               while(var10.stackSize > 0) {
                  int var14 = this.field_149961_a.nextInt(21) + 10;
                  if(var14 > var10.stackSize) {
                     var14 = var10.stackSize;
                  }

                  var10.stackSize -= var14;
                  EntityItem var15 = new EntityItem(var1, (double)((float)var2 + var11), (double)((float)var3 + var12), (double)((float)var4 + var13), new ItemStack(var10.getItem(), var14, var10.getItemDamage()));
                  float var16 = 0.05F;
                  var15.motionX = (double)((float)this.field_149961_a.nextGaussian() * var16);
                  var15.motionY = (double)((float)this.field_149961_a.nextGaussian() * var16 + 0.2F);
                  var15.motionZ = (double)((float)this.field_149961_a.nextGaussian() * var16);
                  var1.spawnEntityInWorld(var15);
               }
            }
         }
      }

      super.breakBlock(var1, var2, var3, var4, var5, var6);
   }

   public Item getItemDropped(int var1, Random var2, int var3) {
      return Items.brewing_stand;
   }

   public Item getItem(World var1, int var2, int var3, int var4) {
      return Items.brewing_stand;
   }

   public boolean hasComparatorInputOverride() {
      return true;
   }

   public int getComparatorInputOverride(World var1, int var2, int var3, int var4, int var5) {
      return Container.calcRedstoneFromInventory((IInventory)var1.getTileEntity(var2, var3, var4));
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.registerBlockIcons(var1);
      this.iconBrewingStandBase = var1.registerIcon(this.getTextureName() + "_base");
   }

   public IIcon getIconBrewingStandBase() {
      return this.iconBrewingStandBase;
   }
}
