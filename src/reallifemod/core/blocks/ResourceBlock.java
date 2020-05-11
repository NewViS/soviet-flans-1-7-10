package de.ItsAMysterious.mods.reallifemod.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ResourceBlock extends Block {

   public ResourceBlock(Material p_i45394_1_) {
      super(p_i45394_1_);
   }

   public ResourceBlock(String Name, Material material) {
      super(material);
      this.func_149663_c(Name);
      this.func_149658_d("reallifemod:" + Name);
      this.func_149647_a(CreativeTabs.tabBlock);
   }
}
