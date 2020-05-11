package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.core.gui.Keyframe;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FountainTE;
import java.awt.Color;
import javax.vecmath.Vector2f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Mouse;

public class GuiFramebar extends Gui {

   public static FountainTE tile;
   public Minecraft mc;
   public int width;
   public int height;
   protected int endFrame;
   public int xPos;
   public int yPos;
   public int mouseX;
   public int mouseY;


   public GuiFramebar(FountainTE vtile, int varX, int varY, int vwidth, int vheight) {
      tile = vtile;
      tile.Keyframes = tile.Keyframes;
      tile.endFrame = tile.Keyframes.size();
      this.xPos = varX;
      this.yPos = varY;
      this.width = vwidth;
      this.height = vheight;
      this.mc = Minecraft.getMinecraft();
   }

   public void drawBar(int x, int y, float f1) {
      this.func_73731_b(this.mc.fontRenderer, String.valueOf(tile.endFrame), 5, this.yPos - 20, Color.white.getRGB());
      func_73734_a(this.xPos, this.yPos, this.width, this.yPos + this.height, Color.gray.getRGB());

      for(int i = 0; i < tile.Keyframes.size(); ++i) {
         this.drawFrame((Keyframe)tile.Keyframes.get(i));
      }

   }

   public void onUpdate() {
      this.handleMouseInput();
      if(tile.endFrame < 1) {
         tile.endFrame = 1;
      }

      tile.func_145831_w().markBlockForUpdate(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
   }

   public void onMouseClicked(int x, int y, int id) {}

   public void drawFrame(Keyframe frame) {
      int framewidth = tile.Keyframes.size() > 1?this.width / tile.Keyframes.size():1;
      int x = framewidth * Keyframe.frameNumber;
      if(this.isMouseOverFrame(frame, this.mouseX, this.mouseY)) {
         func_73734_a(x, this.yPos, x + framewidth, this.yPos + this.height, Color.yellow.getRGB());
      } else {
         func_73734_a(x, this.yPos, x + framewidth, this.yPos + this.height, Color.green.getRGB());
      }

   }

   public boolean isMouseOverFrame(Keyframe frame, int x, int y) {
      int framewidth = this.width / tile.endFrame;
      return frame == this.getKeyframeAtMousePos(x);
   }

   public void onMouseclicked(int x, int y, int buttonID) {
      int[] frameposes;
      if(tile.Keyframes.size() != 0) {
         frameposes = new int[this.width / tile.Keyframes.size()];
      } else {
         frameposes = new int[1];
      }

      int number;
      for(number = 0; number < frameposes.length - 1; ++number) {
         if(tile.Keyframes.size() > 0) {
            frameposes[number] = number * (this.width / tile.Keyframes.size());
         } else {
            frameposes[number] = number;
         }
      }

      number = 0;

      for(int i = 0; i < frameposes.length - 1; ++i) {
         if(x > frameposes[i] && x < frameposes[i + 1]) {
            number = i;
         }
      }

      tile.Keyframes.set(number, new Keyframe(number, 100.0D));
   }

   public Keyframe getKeyframeAtMousePos(int x) {
      int framewidth;
      if(tile.Keyframes.size() != 0) {
         framewidth = this.width / tile.endFrame;
      } else {
         framewidth = this.width;
      }

      int[] framepositions = new int[tile.Keyframes.size()];

      int j;
      for(j = 0; j < tile.Keyframes.size(); ++j) {
         Keyframe frame = (Keyframe)tile.Keyframes.get(j);
         framepositions[j] = Keyframe.frameNumber * framewidth;
      }

      for(j = 0; j < framepositions.length - 1; ++j) {
         if(x < framepositions[j] && x < framepositions[j + 1]) {
            return (Keyframe)tile.Keyframes.get(j);
         }
      }

      return null;
   }

   public Vector2f getFramePositionOnScreen(Keyframe frame) {
      float frames = (float)(this.width / tile.endFrame);
      return new Vector2f((float)Keyframe.frameNumber * frames, (float)this.yPos);
   }

   public void handleMouseInput() {
      this.mouseX = (int)(2.0F * (float)Mouse.getX());
      this.mouseY = (int)(2.0F * (float)Mouse.getY());
   }
}
