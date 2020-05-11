package net.minecraft.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemFishingRod extends Item {

   private IIcon theIcon;


   public ItemFishingRod() {
      this.setMaxDamage(64);
      this.setMaxStackSize(1);
      this.setCreativeTab(CreativeTabs.tabTools);
   }

   public boolean isFull3D() {
      return true;
   }

   public boolean shouldRotateAroundWhenRendering() {
      return true;
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if(var3.fishEntity != null) {
         int var4 = var3.fishEntity.func_146034_e();
         var1.damageItem(var4, var3);
         var3.swingItem();
      } else {
         var2.playSoundAtEntity(var3, "random.bow", 0.5F, 0.4F / (Item.itemRand.nextFloat() * 0.4F + 0.8F));
         if(!var2.isRemote) {
            var2.spawnEntityInWorld(new EntityFishHook(var2, var3));
         }

         var3.swingItem();
      }

      return var1;
   }

   public void registerIcons(IIconRegister var1) {
      super.itemIcon = var1.registerIcon(this.getIconString() + "_uncast");
      this.theIcon = var1.registerIcon(this.getIconString() + "_cast");
   }

   public IIcon func_94597_g() {
      return this.theIcon;
   }

   public boolean isItemTool(ItemStack var1) {
      return super.isItemTool(var1);
   }

   public int getItemEnchantability() {
      return 1;
   }
}
