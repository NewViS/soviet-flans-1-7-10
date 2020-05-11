package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;

public class BlockTar extends Block {

   Minecraft mc;


   public BlockTar() {
      super(Material.rock);
      this.func_149663_c("tarmac");
      this.func_149658_d("reallifemod:tar");
   }
}
