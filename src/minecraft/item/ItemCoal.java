package net.minecraft.item;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemCoal extends Item {

   private IIcon field_111220_a;


   public ItemCoal() {
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }

   public String getUnlocalizedName(ItemStack var1) {
      return var1.getItemDamage() == 1?"item.charcoal":"item.coal";
   }

   public void getSubItems(Item var1, CreativeTabs var2, List var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
   }

   public IIcon getIconFromDamage(int var1) {
      return var1 == 1?this.field_111220_a:super.getIconFromDamage(var1);
   }

   public void registerIcons(IIconRegister var1) {
      super.registerIcons(var1);
      this.field_111220_a = var1.registerIcon("charcoal");
   }
}
