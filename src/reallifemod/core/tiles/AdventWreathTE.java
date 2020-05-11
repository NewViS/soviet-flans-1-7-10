package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityDirectional;

public class AdventWreathTE extends TileEntityDirectional {

   public int func_145832_p() {
      if(this.field_145850_b != null) {
         if(this.field_145847_g == -1) {
            this.field_145847_g = this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, this.field_145849_e);
         }

         return this.field_145847_g;
      } else {
         return 1;
      }
   }

   static enum state {

      FirstAdvent("FirstAdvent", 0),
      SecondAdvent("SecondAdvent", 1),
      ThirdAdvent("ThirdAdvent", 2),
      FourthAdvent("FourthAdvent", 3),
      None("None", 4);
      // $FF: synthetic field
      private static final AdventWreathTE.state[] $VALUES = new AdventWreathTE.state[]{FirstAdvent, SecondAdvent, ThirdAdvent, FourthAdvent, None};


      private state(String var1, int var2) {}

   }
}
