package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$DesertPyramid;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$JunglePyramid;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$SwampHut;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenScatteredFeature$Start extends StructureStart {

   public MapGenScatteredFeature$Start() {}

   public MapGenScatteredFeature$Start(World var1, Random var2, int var3, int var4) {
      super(var3, var4);
      BiomeGenBase var5 = var1.getBiomeGenForCoords(var3 * 16 + 8, var4 * 16 + 8);
      if(var5 != BiomeGenBase.jungle && var5 != BiomeGenBase.jungleHills) {
         if(var5 == BiomeGenBase.swampland) {
            ComponentScatteredFeaturePieces$SwampHut var7 = new ComponentScatteredFeaturePieces$SwampHut(var2, var3 * 16, var4 * 16);
            super.components.add(var7);
         } else {
            ComponentScatteredFeaturePieces$DesertPyramid var8 = new ComponentScatteredFeaturePieces$DesertPyramid(var2, var3 * 16, var4 * 16);
            super.components.add(var8);
         }
      } else {
         ComponentScatteredFeaturePieces$JunglePyramid var6 = new ComponentScatteredFeaturePieces$JunglePyramid(var2, var3 * 16, var4 * 16);
         super.components.add(var6);
      }

      this.updateBoundingBox();
   }
}
