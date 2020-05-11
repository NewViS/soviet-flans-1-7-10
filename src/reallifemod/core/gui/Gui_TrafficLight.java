package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Blocks;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.api.gui.SelectableElement;
import de.ItsAMysterious.mods.reallifemod.core.tiles.TrafficlightTE;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class Gui_TrafficLight extends RLM_Gui {

   private int blockX;
   private int blockY;
   private int blockZ;
   private int mouseX;
   private int mouseY;
   public ArrayList elements = new ArrayList();


   public Gui_TrafficLight(int x, int y, int z) {
      this.blockX = x;
      this.blockY = y;
      this.blockZ = z;
   }

   public void func_73863_a(int x, int y, float f) {
      this.field_146297_k.renderEngine.bindTexture(new ResourceLocation("minecraft:textures/blocks/planks_oak.png"));
      this.func_73729_b(this.field_146294_l / 2 - 200, this.field_146295_m / 2 - 100, 0, 0, 400, 200);
      this.field_146297_k.renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/gui/Display.png"));
      this.func_73729_b(this.field_146294_l / 2 - 180, this.field_146295_m / 2 - 80, 0, 0, 165, 100);
      int var10002 = this.field_146294_l / 2 - 180 + 8;
      this.renderMap(FMLClientHandler.instance().getClient().theWorld, var10002, this.field_146295_m / 2 - 80 + 19, 150, 100);
      Iterator var4 = this.elements.iterator();

      while(var4.hasNext()) {
         SelectableElement selectable = (SelectableElement)var4.next();
         selectable.mouseOver(x, y);
         selectable.draw(this.field_146297_k, selectable.posX, selectable.posY);
      }

      super.func_73863_a(x, y, f);
   }

   public void udateScreen() {
      super.func_73876_c();
   }

   public void func_73866_w_() {
      super.func_73866_w_();

      for(int i = 0; i < 5; ++i) {
         this.field_146292_n.add(new GuiButton(i, this.field_146294_l / 2 - 180 + 150 + 5, this.field_146295_m / 2 - 80 + 20 * i, 40, 20, "slot " + i));
      }

   }

   public void renderMap(World world, int xPos, int yPos, int width, int height) {
      GL11.glPushMatrix();
      int intervalX = width / 20;
      int var10000 = height / intervalX;
      int middleX = xPos + width / 2 - 15;
      int middleY = yPos + height / 2 - 23;

      int i;
      int e;
      for(i = -10; i < 10; ++i) {
         for(e = -10; e < 10; ++e) {
            for(int y = -1; y < 1; ++y) {
               if(world.getBlock(this.blockX + i, this.blockY + y, this.blockZ + e) == RealLifeMod_Blocks.blockTar) {
                  ;
               }

               if(world.getTileEntity(this.blockX + i, this.blockY + y, this.blockZ + e) instanceof TrafficlightTE) {
                  this.elements.add(new SelectableElement(i * y, xPos + i * 5, yPos + e * 5, 5, 5));
               }
            }
         }
      }

      for(i = 0; i < 20; ++i) {
         for(e = 0; e < height / intervalX; ++e) {
            Gui.drawRect(xPos + i * intervalX, yPos - 15, xPos + i * intervalX + 1, yPos - 23 + height, Color.white.getRGB());
            Gui.drawRect(xPos - 5, yPos + e * intervalX - 15, xPos + width - 11, yPos + e * intervalX + 1 - 15, Color.white.getRGB());
         }
      }

      Iterator var13 = this.elements.iterator();

      while(var13.hasNext()) {
         SelectableElement var14 = (SelectableElement)var13.next();
         var14.draw(this.field_146297_k, xPos, yPos);
      }

      GL11.glTranslatef((float)xPos, (float)yPos, 0.0F);
      GL11.glPopMatrix();
   }
}
