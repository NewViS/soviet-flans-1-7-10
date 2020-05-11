package net.minecraft.item;

import net.minecraft.block.BlockDispenser;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor$1;
import net.minecraft.item.ItemArmor$ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemArmor extends Item {

   private static final int[] maxDamageArray = new int[]{11, 16, 15, 13};
   private static final String[] CLOTH_OVERLAY_NAMES = new String[]{"leather_helmet_overlay", "leather_chestplate_overlay", "leather_leggings_overlay", "leather_boots_overlay"};
   public static final String[] EMPTY_SLOT_NAMES = new String[]{"empty_armor_slot_helmet", "empty_armor_slot_chestplate", "empty_armor_slot_leggings", "empty_armor_slot_boots"};
   private static final IBehaviorDispenseItem dispenserBehavior = new ItemArmor$1();
   public final int armorType;
   public final int damageReduceAmount;
   public final int renderIndex;
   private final ItemArmor$ArmorMaterial material;
   private IIcon overlayIcon;
   private IIcon emptySlotIcon;


   public ItemArmor(ItemArmor$ArmorMaterial var1, int var2, int var3) {
      this.material = var1;
      this.armorType = var3;
      this.renderIndex = var2;
      this.damageReduceAmount = var1.getDamageReductionAmount(var3);
      this.setMaxDamage(var1.getDurability(var3));
      super.maxStackSize = 1;
      this.setCreativeTab(CreativeTabs.tabCombat);
      BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserBehavior);
   }

   public int getColorFromItemStack(ItemStack var1, int var2) {
      if(var2 > 0) {
         return 16777215;
      } else {
         int var3 = this.getColor(var1);
         if(var3 < 0) {
            var3 = 16777215;
         }

         return var3;
      }
   }

   public boolean requiresMultipleRenderPasses() {
      return this.material == ItemArmor$ArmorMaterial.CLOTH;
   }

   public int getItemEnchantability() {
      return this.material.getEnchantability();
   }

   public ItemArmor$ArmorMaterial getArmorMaterial() {
      return this.material;
   }

   public boolean hasColor(ItemStack var1) {
      return this.material != ItemArmor$ArmorMaterial.CLOTH?false:(!var1.hasTagCompound()?false:(!var1.getTagCompound().hasKey("display", 10)?false:var1.getTagCompound().getCompoundTag("display").hasKey("color", 3)));
   }

   public int getColor(ItemStack var1) {
      if(this.material != ItemArmor$ArmorMaterial.CLOTH) {
         return -1;
      } else {
         NBTTagCompound var2 = var1.getTagCompound();
         if(var2 == null) {
            return 10511680;
         } else {
            NBTTagCompound var3 = var2.getCompoundTag("display");
            return var3 == null?10511680:(var3.hasKey("color", 3)?var3.getInteger("color"):10511680);
         }
      }
   }

   public IIcon getIconFromDamageForRenderPass(int var1, int var2) {
      return var2 == 1?this.overlayIcon:super.getIconFromDamageForRenderPass(var1, var2);
   }

   public void removeColor(ItemStack var1) {
      if(this.material == ItemArmor$ArmorMaterial.CLOTH) {
         NBTTagCompound var2 = var1.getTagCompound();
         if(var2 != null) {
            NBTTagCompound var3 = var2.getCompoundTag("display");
            if(var3.hasKey("color")) {
               var3.removeTag("color");
            }

         }
      }
   }

   public void func_82813_b(ItemStack var1, int var2) {
      if(this.material != ItemArmor$ArmorMaterial.CLOTH) {
         throw new UnsupportedOperationException("Can\'t dye non-leather!");
      } else {
         NBTTagCompound var3 = var1.getTagCompound();
         if(var3 == null) {
            var3 = new NBTTagCompound();
            var1.setTagCompound(var3);
         }

         NBTTagCompound var4 = var3.getCompoundTag("display");
         if(!var3.hasKey("display", 10)) {
            var3.setTag("display", var4);
         }

         var4.setInteger("color", var2);
      }
   }

   public boolean getIsRepairable(ItemStack var1, ItemStack var2) {
      return this.material.func_151685_b() == var2.getItem()?true:super.getIsRepairable(var1, var2);
   }

   public void registerIcons(IIconRegister var1) {
      super.registerIcons(var1);
      if(this.material == ItemArmor$ArmorMaterial.CLOTH) {
         this.overlayIcon = var1.registerIcon(CLOTH_OVERLAY_NAMES[this.armorType]);
      }

      this.emptySlotIcon = var1.registerIcon(EMPTY_SLOT_NAMES[this.armorType]);
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      int var4 = EntityLiving.getArmorPosition(var1) - 1;
      ItemStack var5 = var3.getCurrentArmor(var4);
      if(var5 == null) {
         var3.setCurrentItemOrArmor(var4, var1.copy());
         var1.stackSize = 0;
      }

      return var1;
   }

   public static IIcon func_94602_b(int var0) {
      switch(var0) {
      case 0:
         return Items.diamond_helmet.emptySlotIcon;
      case 1:
         return Items.diamond_chestplate.emptySlotIcon;
      case 2:
         return Items.diamond_leggings.emptySlotIcon;
      case 3:
         return Items.diamond_boots.emptySlotIcon;
      default:
         return null;
      }
   }

   // $FF: synthetic method
   static int[] access$000() {
      return maxDamageArray;
   }

}
