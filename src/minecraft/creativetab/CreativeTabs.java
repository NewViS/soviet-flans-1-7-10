package net.minecraft.creativetab;

import java.util.Iterator;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs$1;
import net.minecraft.creativetab.CreativeTabs$10;
import net.minecraft.creativetab.CreativeTabs$11;
import net.minecraft.creativetab.CreativeTabs$12;
import net.minecraft.creativetab.CreativeTabs$2;
import net.minecraft.creativetab.CreativeTabs$3;
import net.minecraft.creativetab.CreativeTabs$4;
import net.minecraft.creativetab.CreativeTabs$5;
import net.minecraft.creativetab.CreativeTabs$6;
import net.minecraft.creativetab.CreativeTabs$7;
import net.minecraft.creativetab.CreativeTabs$8;
import net.minecraft.creativetab.CreativeTabs$9;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class CreativeTabs {

   public static final CreativeTabs[] creativeTabArray = new CreativeTabs[12];
   public static final CreativeTabs tabBlock = new CreativeTabs$1(0, "buildingBlocks");
   public static final CreativeTabs tabDecorations = new CreativeTabs$2(1, "decorations");
   public static final CreativeTabs tabRedstone = new CreativeTabs$3(2, "redstone");
   public static final CreativeTabs tabTransport = new CreativeTabs$4(3, "transportation");
   public static final CreativeTabs tabMisc = (new CreativeTabs$5(4, "misc")).func_111229_a(new EnumEnchantmentType[]{EnumEnchantmentType.all});
   public static final CreativeTabs tabAllSearch = (new CreativeTabs$6(5, "search")).setBackgroundImageName("item_search.png");
   public static final CreativeTabs tabFood = new CreativeTabs$7(6, "food");
   public static final CreativeTabs tabTools = (new CreativeTabs$8(7, "tools")).func_111229_a(new EnumEnchantmentType[]{EnumEnchantmentType.digger, EnumEnchantmentType.fishing_rod, EnumEnchantmentType.breakable});
   public static final CreativeTabs tabCombat = (new CreativeTabs$9(8, "combat")).func_111229_a(new EnumEnchantmentType[]{EnumEnchantmentType.armor, EnumEnchantmentType.armor_feet, EnumEnchantmentType.armor_head, EnumEnchantmentType.armor_legs, EnumEnchantmentType.armor_torso, EnumEnchantmentType.bow, EnumEnchantmentType.weapon});
   public static final CreativeTabs tabBrewing = new CreativeTabs$10(9, "brewing");
   public static final CreativeTabs tabMaterials = new CreativeTabs$11(10, "materials");
   public static final CreativeTabs tabInventory = (new CreativeTabs$12(11, "inventory")).setBackgroundImageName("inventory.png").setNoScrollbar().setNoTitle();
   private final int tabIndex;
   private final String tabLabel;
   private String backgroundImageName = "items.png";
   private boolean hasScrollbar = true;
   private boolean drawTitle = true;
   private EnumEnchantmentType[] field_111230_s;
   private ItemStack field_151245_t;


   public CreativeTabs(int var1, String var2) {
      this.tabIndex = var1;
      this.tabLabel = var2;
      creativeTabArray[var1] = this;
   }

   public int getTabIndex() {
      return this.tabIndex;
   }

   public String getTabLabel() {
      return this.tabLabel;
   }

   public String getTranslatedTabLabel() {
      return "itemGroup." + this.getTabLabel();
   }

   public ItemStack getIconItemStack() {
      if(this.field_151245_t == null) {
         this.field_151245_t = new ItemStack(this.getTabIconItem(), 1, this.func_151243_f());
      }

      return this.field_151245_t;
   }

   public abstract Item getTabIconItem();

   public int func_151243_f() {
      return 0;
   }

   public String getBackgroundImageName() {
      return this.backgroundImageName;
   }

   public CreativeTabs setBackgroundImageName(String var1) {
      this.backgroundImageName = var1;
      return this;
   }

   public boolean drawInForegroundOfTab() {
      return this.drawTitle;
   }

   public CreativeTabs setNoTitle() {
      this.drawTitle = false;
      return this;
   }

   public boolean shouldHidePlayerInventory() {
      return this.hasScrollbar;
   }

   public CreativeTabs setNoScrollbar() {
      this.hasScrollbar = false;
      return this;
   }

   public int getTabColumn() {
      return this.tabIndex % 6;
   }

   public boolean isTabInFirstRow() {
      return this.tabIndex < 6;
   }

   public EnumEnchantmentType[] func_111225_m() {
      return this.field_111230_s;
   }

   public CreativeTabs func_111229_a(EnumEnchantmentType ... var1) {
      this.field_111230_s = var1;
      return this;
   }

   public boolean func_111226_a(EnumEnchantmentType var1) {
      if(this.field_111230_s == null) {
         return false;
      } else {
         EnumEnchantmentType[] var2 = this.field_111230_s;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            EnumEnchantmentType var5 = var2[var4];
            if(var5 == var1) {
               return true;
            }
         }

         return false;
      }
   }

   public void displayAllReleventItems(List var1) {
      Iterator var2 = Item.itemRegistry.iterator();

      while(var2.hasNext()) {
         Item var3 = (Item)var2.next();
         if(var3 != null && var3.getCreativeTab() == this) {
            var3.getSubItems(var3, this, var1);
         }
      }

      if(this.func_111225_m() != null) {
         this.addEnchantmentBooksToList(var1, this.func_111225_m());
      }

   }

   public void addEnchantmentBooksToList(List var1, EnumEnchantmentType ... var2) {
      Enchantment[] var3 = Enchantment.enchantmentsList;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Enchantment var6 = var3[var5];
         if(var6 != null && var6.type != null) {
            boolean var7 = false;

            for(int var8 = 0; var8 < var2.length && !var7; ++var8) {
               if(var6.type == var2[var8]) {
                  var7 = true;
               }
            }

            if(var7) {
               var1.add(Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(var6, var6.getMaxLevel())));
            }
         }
      }

   }

}
