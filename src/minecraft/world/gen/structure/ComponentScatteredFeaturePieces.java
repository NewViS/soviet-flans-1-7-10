package net.minecraft.world.gen.structure;

import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$DesertPyramid;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$JunglePyramid;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$SwampHut;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class ComponentScatteredFeaturePieces {

   public static void registerScatteredFeaturePieces() {
      MapGenStructureIO.func_143031_a(ComponentScatteredFeaturePieces$DesertPyramid.class, "TeDP");
      MapGenStructureIO.func_143031_a(ComponentScatteredFeaturePieces$JunglePyramid.class, "TeJP");
      MapGenStructureIO.func_143031_a(ComponentScatteredFeaturePieces$SwampHut.class, "TeSH");
   }
}
