package net.minecraft.item;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood$FishType;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemFishFood extends ItemFood {

   private final boolean field_150907_b;


   public ItemFishFood(boolean var1) {
      super(0, 0.0F, false);
      this.field_150907_b = var1;
   }

   public int func_150905_g(ItemStack var1) {
      ItemFishFood$FishType var2 = ItemFishFood$FishType.func_150978_a(var1);
      return this.field_150907_b && var2.func_150973_i()?var2.func_150970_e():var2.func_150975_c();
   }

   public float func_150906_h(ItemStack var1) {
      ItemFishFood$FishType var2 = ItemFishFood$FishType.func_150978_a(var1);
      return this.field_150907_b && var2.func_150973_i()?var2.func_150977_f():var2.func_150967_d();
   }

   public String getPotionEffect(ItemStack var1) {
      return ItemFishFood$FishType.func_150978_a(var1) == ItemFishFood$FishType.PUFFERFISH?PotionHelper.field_151423_m:null;
   }

   public void registerIcons(IIconRegister var1) {
      ItemFishFood$FishType[] var2 = ItemFishFood$FishType.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ItemFishFood$FishType var5 = var2[var4];
         var5.func_150968_a(var1);
      }

   }

   protected void onFoodEaten(ItemStack var1, World var2, EntityPlayer var3) {
      ItemFishFood$FishType var4 = ItemFishFood$FishType.func_150978_a(var1);
      if(var4 == ItemFishFood$FishType.PUFFERFISH) {
         var3.addPotionEffect(new PotionEffect(Potion.poison.id, 1200, 3));
         var3.addPotionEffect(new PotionEffect(Potion.hunger.id, 300, 2));
         var3.addPotionEffect(new PotionEffect(Potion.confusion.id, 300, 1));
      }

      super.onFoodEaten(var1, var2, var3);
   }

   public IIcon getIconFromDamage(int var1) {
      ItemFishFood$FishType var2 = ItemFishFood$FishType.func_150974_a(var1);
      return this.field_150907_b && var2.func_150973_i()?var2.func_150979_h():var2.func_150971_g();
   }

   public void getSubItems(Item var1, CreativeTabs var2, List var3) {
      ItemFishFood$FishType[] var4 = ItemFishFood$FishType.values();
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         ItemFishFood$FishType var7 = var4[var6];
         if(!this.field_150907_b || var7.func_150973_i()) {
            var3.add(new ItemStack(this, 1, var7.func_150976_a()));
         }
      }

   }

   public String getUnlocalizedName(ItemStack var1) {
      ItemFishFood$FishType var2 = ItemFishFood$FishType.func_150978_a(var1);
      return this.getUnlocalizedName() + "." + var2.func_150972_b() + "." + (this.field_150907_b && var2.func_150973_i()?"cooked":"raw");
   }
}
