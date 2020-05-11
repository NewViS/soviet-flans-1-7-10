package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item$ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHoe extends Item {

   protected Item$ToolMaterial theToolMaterial;


   public ItemHoe(Item$ToolMaterial var1) {
      this.theToolMaterial = var1;
      super.maxStackSize = 1;
      this.setMaxDamage(var1.getMaxUses());
      this.setCreativeTab(CreativeTabs.tabTools);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(!var2.canPlayerEdit(var4, var5, var6, var7, var1)) {
         return false;
      } else {
         Block var11 = var3.getBlock(var4, var5, var6);
         if(var7 != 0 && var3.getBlock(var4, var5 + 1, var6).getMaterial() == Material.air && (var11 == Blocks.grass || var11 == Blocks.dirt)) {
            Block var12 = Blocks.farmland;
            var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), var12.stepSound.getStepResourcePath(), (var12.stepSound.getVolume() + 1.0F) / 2.0F, var12.stepSound.getPitch() * 0.8F);
            if(var3.isRemote) {
               return true;
            } else {
               var3.setBlock(var4, var5, var6, var12);
               var1.damageItem(1, var2);
               return true;
            }
         } else {
            return false;
         }
      }
   }

   public boolean isFull3D() {
      return true;
   }

   public String getToolMaterialName() {
      return this.theToolMaterial.toString();
   }
}
