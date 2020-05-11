package de.ItsAMysterious.mods.reallifemod.api.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TLMCustomCreativeTabs extends CreativeTabs {

   public TLMCustomCreativeTabs(String lable) {
      super(lable);
   }

   public Item func_78016_d() {
      return Items.stone_hoe;
   }

   @SideOnly(Side.CLIENT)
   public String func_78024_c() {
      return this.func_78013_b();
   }
}
