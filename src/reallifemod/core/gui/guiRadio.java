package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.tiles.RadioTE;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class guiRadio extends RLM_Gui {

   private int lengthinmilliseconds;
   private int volume = 0;
   private File track;
   private List tracks;
   private int currentsong;
   private Vector3f blockPosition;
   private Entity listener;
   private PositionedSoundRecord playing;
   private RadioTE tile;
   private final SoundRegistry sndRegistry;
   private int buttonsYPos;
   private int mouseY;
   private Color color;


   public guiRadio() {
      this.track = new File(RealLifeMod.Dir, "assets/reallifemod/sounds");
      this.tracks = new ArrayList();
      this.sndRegistry = new SoundRegistry();
      this.color = Color.red;
   }

   public void func_73863_a(int x, int y, float f) {
      this.func_73733_a(0, 30, this.field_146294_l, this.field_146295_m / 2, Color.black.getRGB(), Color.black.getTransparency());
      this.func_73733_a(0, this.field_146295_m / 2, this.field_146294_l, this.field_146295_m / 2, Color.black.getTransparency(), Color.black.getRGB());
      this.func_73733_a(this.field_146294_l - 25, this.field_146295_m - 30 - 8 * this.volume / 4, this.field_146294_l - 15, this.field_146295_m - 30, Color.red.getRGB(), Color.blue.getTransparency());
      this.drawString(this.volume + "%", this.field_146294_l - 25, this.field_146295_m / 2, Color.white.getRGB());
      if(this.tracks.isEmpty()) {
         this.drawCenteredString("There are no .ogg files in the directory \'" + this.track + "\' at the moment!", this.field_146294_l / 2, 35, Color.red.getRGB());
      } else {
         for(int i = 0; i < this.tracks.size(); ++i) {
            this.drawString(((ResourceLocation)this.tracks.get(i)).toString(), 5, 30 + i * 10, Color.white.getRGB());
         }
      }

      this.drawString(this.track.toString(), 5, 10, Color.white.getRGB());
      this.drawCenteredString("Files in Directory", this.field_146294_l / 2, 20, Color.green.getRGB());
      this.drawString(this.length(), this.field_146294_l - 54, this.buttonsYPos + 4, Color.white.getRGB());
      this.drawProgressBar(110, this.buttonsYPos + 4, this.field_146294_l / 2 + 100, this.buttonsYPos + 14, 0.0F, 100.0F, 100.0F);
      if(!this.tracks.isEmpty() && this.currentsong < this.tracks.size()) {
         this.drawCenteredString("CurrentSong: " + ((ResourceLocation)this.tracks.get(this.currentsong)).toString(), this.field_146294_l / 2, this.field_146295_m - 21, Color.green.getRGB());
      }

      super.func_73863_a(x, y, f);
   }

   public void func_73876_c() {
      super.func_73876_c();
      if(this.mouseY > this.field_146295_m - 25 && this.buttonsYPos > this.field_146295_m - 25) {
         --this.buttonsYPos;
      }

      if(this.mouseY < this.field_146295_m - 25 && this.buttonsYPos < this.field_146295_m) {
         ++this.buttonsYPos;
      }

      if(this.volume < 0) {
         this.volume = 0;
      }

      if(this.volume > 100) {
         this.volume = 100;
      }

      GuiButton b = (GuiButton)this.field_146292_n.get(0);
      b.yPosition = this.buttonsYPos;
      GuiButton c = (GuiButton)this.field_146292_n.get(1);
      c.yPosition = this.buttonsYPos;
      GuiButton d = (GuiButton)this.field_146292_n.get(2);
      d.yPosition = this.buttonsYPos;
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      this.buttonsYPos = this.field_146295_m - 25;
      this.field_146292_n.add(new GuiButton(0, 5, this.buttonsYPos, 30, 20, "Play"));
      this.field_146292_n.add(new GuiButton(1, 40, this.buttonsYPos, 30, 20, "<<"));
      this.field_146292_n.add(new GuiButton(2, 75, this.buttonsYPos, 30, 20, ">>"));
      this.getFilesInFolder();
   }

   public void func_146284_a(GuiButton b) {
      if(b.id == 0 && !this.tracks.isEmpty() && this.currentsong < this.tracks.size() - 1) {
         this.playSong(new ResourceLocation("reallifemod:" + (String)((ResourceLocation)this.tracks.get(this.currentsong)).getResourcePath().toString().subSequence(0, ((ResourceLocation)this.tracks.get(this.currentsong)).getResourcePath().toString().length() - 4)), this.field_146297_k.thePlayer);
         System.out.println(((ResourceLocation)this.tracks.get(this.currentsong)).toString());
      }

      if(b.id == 1 && this.currentsong > 0) {
         --this.currentsong;
      }

      if(b.id == 2 && this.currentsong < this.tracks.size()) {
         ++this.currentsong;
      }

      super.func_146284_a(b);
   }

   public String length() {
      boolean seconds = false;
      byte minutes = 0;
      byte hours = 0;
      int seconds1 = this.lengthinmilliseconds / 100 - minutes * 60;
      int minutes1 = seconds1 - hours * 60;
      int hours1 = hours + minutes1 / 60;
      return this.lengthinmilliseconds != 0?hours1 + "." + minutes1 + "." + seconds1:"0.00.00";
   }

   public void getFilesInFolder() {
      if(this.track.exists()) {
         File[] var1 = this.track.listFiles();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            File fileEntry = var1[var3];
            System.out.println(fileEntry.getName());
            if(fileEntry.isFile() && fileEntry.getName().endsWith(".ogg")) {
               this.tracks.add(new ResourceLocation(fileEntry.getName()));
            }
         }

      } else {
         this.track.mkdir();
      }
   }

   public void drawProgressBar(int xPos, int yPos, int width, int height, float min, float max, float value) {
      Gui.drawRect(xPos, yPos, xPos + width, height, Color.black.getRGB());
      Gui.drawRect(xPos, yPos, (int)((float)this.field_146294_l - ((float)(xPos + width) - (float)(width / 100) * value)), height, Color.white.getRGB());
   }

   public void stopSounds() {
      SoundHandler manager = this.field_146297_k.getSoundHandler();
      manager.stopSounds();
   }

   public void playSong(ResourceLocation music, Entity entity) {
      this.stopSounds();
      this.listener = entity;
      SoundHandler manager = Minecraft.getMinecraft().getSoundHandler();
      manager.resumeSounds();
      this.playing = PositionedSoundRecord.func_147675_a(music, (float)entity.posX, (float)entity.posY, (float)entity.posZ);
      manager.playSound(this.playing);
   }

   public void hideButtons() {
      for(int i = 0; i < 20; ++i) {
         ++this.buttonsYPos;
      }

   }

   public void showButtons() {
      for(int i = 0; i < 20; ++i) {
         --this.buttonsYPos;
      }

   }

   public void func_146274_d() {
      super.func_146274_d();
      int i = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.displayWidth;
      int j = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.displayHeight - 1;
      int k = Mouse.getEventButton();
      this.mouseY = j;
      int v = Mouse.getDWheel() / 100;
      this.volume += v;
   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) {
      super.func_73869_a(p_73869_1_, p_73869_2_);
   }
}
