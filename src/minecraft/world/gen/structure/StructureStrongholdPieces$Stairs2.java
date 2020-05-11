package net.minecraft.world.gen.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$PieceWeight;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$PortalRoom;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs;

public class StructureStrongholdPieces$Stairs2 extends StructureStrongholdPieces$Stairs {

   public StructureStrongholdPieces$PieceWeight strongholdPieceWeight;
   public StructureStrongholdPieces$PortalRoom strongholdPortalRoom;
   public List field_75026_c = new ArrayList();


   public StructureStrongholdPieces$Stairs2() {}

   public StructureStrongholdPieces$Stairs2(int var1, Random var2, int var3, int var4) {
      super(0, var2, var3, var4);
   }

   public ChunkPosition func_151553_a() {
      return this.strongholdPortalRoom != null?this.strongholdPortalRoom.func_151553_a():super.func_151553_a();
   }
}
