package net.minecraft.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBlock extends Item {

   protected final Block field_150939_a;
   private IIcon field_150938_b;


   public ItemBlock(Block var1) {
      this.field_150939_a = var1;
   }

   public ItemBlock setUnlocalizedName(String var1) {
      super.setUnlocalizedName(var1);
      return this;
   }

   public int getSpriteNumber() {
      return this.field_150939_a.getItemIconName() != null?1:0;
   }

   public IIcon getIconFromDamage(int var1) {
      return this.field_150938_b != null?this.field_150938_b:this.field_150939_a.getBlockTextureFromSide(1);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      Block var11 = var3.getBlock(var4, var5, var6);
      if(var11 == Blocks.snow_layer && (var3.getBlockMetadata(var4, var5, var6) & 7) < 1) {
         var7 = 1;
      } else if(var11 != Blocks.vine && var11 != Blocks.tallgrass && var11 != Blocks.deadbush) {
         if(var7 == 0) {
            --var5;
         }

         if(var7 == 1) {
            ++var5;
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
      }

      if(var1.stackSize == 0) {
         return false;
      } else if(!var2.canPlayerEdit(var4, var5, var6, var7, var1)) {
         return false;
      } else if(var5 == 255 && this.field_150939_a.getMaterial().isSolid()) {
         return false;
      } else if(var3.canPlaceEntityOnSide(this.field_150939_a, var4, var5, var6, false, var7, var2, var1)) {
         int var12 = this.getMetadata(var1.getItemDamage());
         int var13 = this.field_150939_a.onBlockPlaced(var3, var4, var5, var6, var7, var8, var9, var10, var12);
         if(var3.setBlock(var4, var5, var6, this.field_150939_a, var13, 3)) {
            if(var3.getBlock(var4, var5, var6) == this.field_150939_a) {
               this.field_150939_a.onBlockPlacedBy(var3, var4, var5, var6, var2, var1);
               this.field_150939_a.onPostBlockPlaced(var3, var4, var5, var6, var13);
            }

            var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150939_a.stepSound.getPitch() * 0.8F);
            --var1.stackSize;
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean func_150936_a(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6, ItemStack var7) {
      Block var8 = var1.getBlock(var2, var3, var4);
      if(var8 == Blocks.snow_layer) {
         var5 = 1;
      } else if(var8 != Blocks.vine && var8 != Blocks.tallgrass && var8 != Blocks.deadbush) {
         if(var5 == 0) {
            --var3;
         }

         if(var5 == 1) {
            ++var3;
         }

         if(var5 == 2) {
            --var4;
         }

         if(var5 == 3) {
            ++var4;
         }

         if(var5 == 4) {
            --var2;
         }

         if(var5 == 5) {
            ++var2;
         }
      }

      return var1.canPlaceEntityOnSide(this.field_150939_a, var2, var3, var4, false, var5, (Entity)null, var7);
   }

   public String getUnlocalizedName(ItemStack var1) {
      return this.field_150939_a.getUnlocalizedName();
   }

   public String getUnlocalizedName() {
      return this.field_150939_a.getUnlocalizedName();
   }

   public CreativeTabs getCreativeTab() {
      return this.field_150939_a.getCreativeTabToDisplayOn();
   }

   public void getSubItems(Item var1, CreativeTabs var2, List var3) {
      this.field_150939_a.getSubBlocks(var1, var2, var3);
   }

   public void registerIcons(IIconRegister var1) {
      String var2 = this.field_150939_a.getItemIconName();
      if(var2 != null) {
         this.field_150938_b = var1.registerIcon(var2);
      }

   }

   // $FF: synthetic method
   public Item setUnlocalizedName(String var1) {
      return this.setUnlocalizedName(var1);
   }
}
