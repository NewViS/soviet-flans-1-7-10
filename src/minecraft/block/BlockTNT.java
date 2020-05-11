package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockTNT extends Block {

   private IIcon field_150116_a;
   private IIcon field_150115_b;


   public BlockTNT() {
      super(Material.tnt);
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   public IIcon getIcon(int var1, int var2) {
      return var1 == 0?this.field_150115_b:(var1 == 1?this.field_150116_a:super.blockIcon);
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      super.onBlockAdded(var1, var2, var3, var4);
      if(var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
         this.onBlockDestroyedByPlayer(var1, var2, var3, var4, 1);
         var1.setBlockToAir(var2, var3, var4);
      }

   }

   public void onNeighborBlockChange(World var1, int var2, int var3, int var4, Block var5) {
      if(var1.isBlockIndirectlyGettingPowered(var2, var3, var4)) {
         this.onBlockDestroyedByPlayer(var1, var2, var3, var4, 1);
         var1.setBlockToAir(var2, var3, var4);
      }

   }

   public int quantityDropped(Random var1) {
      return 1;
   }

   public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5) {
      if(!var1.isRemote) {
         EntityTNTPrimed var6 = new EntityTNTPrimed(var1, (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), var5.getExplosivePlacedBy());
         var6.fuse = var1.rand.nextInt(var6.fuse / 4) + var6.fuse / 8;
         var1.spawnEntityInWorld(var6);
      }
   }

   public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5) {
      this.func_150114_a(var1, var2, var3, var4, var5, (EntityLivingBase)null);
   }

   public void func_150114_a(World var1, int var2, int var3, int var4, int var5, EntityLivingBase var6) {
      if(!var1.isRemote) {
         if((var5 & 1) == 1) {
            EntityTNTPrimed var7 = new EntityTNTPrimed(var1, (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), var6);
            var1.spawnEntityInWorld(var7);
            var1.playSoundAtEntity(var7, "game.tnt.primed", 1.0F, 1.0F);
         }

      }
   }

   public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9) {
      if(var5.getCurrentEquippedItem() != null && var5.getCurrentEquippedItem().getItem() == Items.flint_and_steel) {
         this.func_150114_a(var1, var2, var3, var4, 1, var5);
         var1.setBlockToAir(var2, var3, var4);
         var5.getCurrentEquippedItem().damageItem(1, var5);
         return true;
      } else {
         return super.onBlockActivated(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      }
   }

   public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5) {
      if(var5 instanceof EntityArrow && !var1.isRemote) {
         EntityArrow var6 = (EntityArrow)var5;
         if(var6.isBurning()) {
            this.func_150114_a(var1, var2, var3, var4, 1, var6.shootingEntity instanceof EntityLivingBase?(EntityLivingBase)var6.shootingEntity:null);
            var1.setBlockToAir(var2, var3, var4);
         }
      }

   }

   public boolean canDropFromExplosion(Explosion var1) {
      return false;
   }

   public void registerBlockIcons(IIconRegister var1) {
      super.blockIcon = var1.registerIcon(this.getTextureName() + "_side");
      this.field_150116_a = var1.registerIcon(this.getTextureName() + "_top");
      this.field_150115_b = var1.registerIcon(this.getTextureName() + "_bottom");
   }
}
