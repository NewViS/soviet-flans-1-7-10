package net.minecraft.util;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInput;

public class MovementInputFromOptions extends MovementInput {

   private GameSettings gameSettings;


   public MovementInputFromOptions(GameSettings var1) {
      this.gameSettings = var1;
   }

   public void updatePlayerMoveState() {
      super.moveStrafe = 0.0F;
      super.moveForward = 0.0F;
      if(this.gameSettings.keyBindForward.getIsKeyPressed()) {
         ++super.moveForward;
      }

      if(this.gameSettings.keyBindBack.getIsKeyPressed()) {
         --super.moveForward;
      }

      if(this.gameSettings.keyBindLeft.getIsKeyPressed()) {
         ++super.moveStrafe;
      }

      if(this.gameSettings.keyBindRight.getIsKeyPressed()) {
         --super.moveStrafe;
      }

      super.jump = this.gameSettings.keyBindJump.getIsKeyPressed();
      super.sneak = this.gameSettings.keyBindSneak.getIsKeyPressed();
      if(super.sneak) {
         super.moveStrafe = (float)((double)super.moveStrafe * 0.3D);
         super.moveForward = (float)((double)super.moveForward * 0.3D);
      }

   }
}
