package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityDirectional;

public class TrafficlightTE extends TileEntityDirectional {

   public int delta;
   boolean fromRed;
   public TrafficlightTE.State state;


   public TrafficlightTE() {
      this.state = TrafficlightTE.State.RED;
   }

   public void func_145845_h() {
      if(RealLifeMod.proxy.ConfigModeTrafficLights && this.state != TrafficlightTE.State.INACTIVE) {
         ++this.delta;
         if(this.delta == 600) {
            if(this.state == TrafficlightTE.State.GREEN) {
               this.fromRed = false;
               this.state = TrafficlightTE.State.ORANGE;
               this.delta = 0;
            }

            if(this.state == TrafficlightTE.State.RED) {
               this.fromRed = true;
               this.state = TrafficlightTE.State.ORANGE;
               this.delta = 0;
            }
         }

         if(this.delta == 5) {
            if(this.state == TrafficlightTE.State.ORANGE && this.fromRed) {
               this.fromRed = false;
               this.state = TrafficlightTE.State.GREEN;
               this.delta = 0;
            }

            if(this.state == TrafficlightTE.State.ORANGE && !this.fromRed) {
               this.fromRed = true;
               this.state = TrafficlightTE.State.RED;
               this.delta = 0;
            }
         }
      }

      this.field_145850_b.markBlockForUpdate(this.field_145851_c, this.field_145848_d, this.field_145849_e);
   }

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

   public static enum State {

      GREEN("GREEN", 0),
      ORANGE("ORANGE", 1),
      RED("RED", 2),
      ORANGE_FLASH("ORANGE_FLASH", 3),
      INACTIVE("INACTIVE", 4);
      // $FF: synthetic field
      private static final TrafficlightTE.State[] $VALUES = new TrafficlightTE.State[]{GREEN, ORANGE, RED, ORANGE_FLASH, INACTIVE};


      private State(String var1, int var2) {}

   }
}
