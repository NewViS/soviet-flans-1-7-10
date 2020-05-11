package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.core.gui.container.ContainerCBO;
import de.ItsAMysterious.mods.reallifemod.core.gui.container.TileEntityCBO;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class CustomBlockGui extends GuiContainer {

   private TileEntityCBO tileCBO;
   public static Block currentBlock;
   public static int blockX;
   public static int blockY;
   public static int blockZ;
   int bx = 0;
   int by = 0;
   int bz = 0;
   private GuiTextField EditX;
   private GuiTextField EditY;
   private GuiTextField EditZ;
   public static Block theItem;
   public static String xs;
   public static String ys;
   public static String zs;
   public static int blockID;


   public CustomBlockGui(int xi, int yi, int zi, InventoryPlayer inventory, TileEntityCBO tile) {
      super(new ContainerCBO(inventory, tile));
      this.tileCBO = tile;
      blockX = xi;
      blockY = yi;
      blockZ = zi;
      currentBlock = (Block)Block.blockRegistry.getObjectById(0);
      blockID = Block.blockRegistry.getIDForObject(currentBlock);
   }

   public void func_73866_w_() {
      Keyboard.enableRepeatEvents(true);
      this.field_146292_n.add(new GuiButton(0, 20, 20, 200, 20, "Block setzen"));
      this.field_146292_n.add(new GuiButton(1, 20, 50, 200, 20, "Block lï¿½schen"));
      this.field_146292_n.add(new GuiButton(2, 20, 80, 200, 20, "Block umwandeln"));
      this.field_146292_n.add(new GuiButton(3, 20, 110, 200, 20, "Blocks auffï¿½llen"));
      this.field_146292_n.add(new GuiButton(4, this.field_146294_l - 50, this.field_146295_m - 30, 40, 20, "Abbrechen"));
      this.field_146292_n.add(new GuiButton(5, this.field_146294_l - 100, this.field_146295_m - 100, 70, 20, "Hilfe"));
      this.field_146292_n.add(new GuiButton(6, this.field_146294_l - 110, this.field_146295_m - 30, 40, 20, "OK"));
      this.field_146292_n.add(new GuiButton(7, 220, 200, 20, 10, "+"));
      this.field_146292_n.add(new GuiButton(8, 220, 210, 20, 10, "-"));
      this.EditX = new GuiTextField(this.field_146289_q, this.field_146294_l - 270, 60, 250, 20);
      this.EditX.setFocused(true);
      this.EditX.setMaxStringLength(100);
      this.EditX.setText("X");
      this.EditY = new GuiTextField(this.field_146289_q, this.field_146294_l - 270, 100, 250, 20);
      this.EditY.setFocused(false);
      this.EditY.setMaxStringLength(100);
      this.EditY.setText("Y");
      this.EditZ = new GuiTextField(this.field_146289_q, this.field_146294_l - 270, 140, 250, 20);
      this.EditZ.setFocused(false);
      this.EditZ.setMaxStringLength(100);
      this.EditZ.setText("Z");
      this.EditX.setCanLoseFocus(true);
      this.EditY.setCanLoseFocus(true);
      this.EditZ.setCanLoseFocus(true);
   }

   public void func_73863_a(int par1, int par2, float par3) {
      GL11.glColor4f(0.9F, 0.9F, 1.0F, 1.0F);
      super.drawScreen(par1, par2, par3);
      this.EditX.drawTextBox();
      this.EditY.drawTextBox();
      this.EditZ.drawTextBox();
      this.EditX.setEnableBackgroundDrawing(true);
      this.EditY.setEnableBackgroundDrawing(true);
      this.EditZ.setEnableBackgroundDrawing(true);
   }

   public void func_73876_c() {
      this.EditX.updateCursorCounter();
      this.EditY.updateCursorCounter();
      this.EditZ.updateCursorCounter();
   }

   public void func_146284_a(GuiButton button) {
      if(button.id == 4) {
         this.field_146297_k.setIngameFocus();
         this.field_146297_k.displayGuiScreen((GuiScreen)null);
      }

      if(button.id == 0) {
         ;
      }

      if(button.id == 1) {
         ;
      }

      if(button.id == 7) {
         ;
      }

      if(button.id == 8) {
         ;
      }

   }

   protected void func_73869_a(char zeichen, int id) {
      if(this.EditX.textboxKeyTyped(zeichen, id)) {
         ;
      }

      if(this.EditY.textboxKeyTyped(zeichen, id)) {
         ;
      }

      if(this.EditZ.textboxKeyTyped(zeichen, id)) {
         ;
      }

      super.keyTyped(zeichen, id);
   }

   protected void func_73864_a(int x, int y, int button) {
      this.EditX.mouseClicked(x, y, button);
      this.EditY.mouseClicked(x, y, button);
      this.EditZ.mouseClicked(x, y, button);
      super.mouseClicked(x, y, button);
   }

   protected void func_146976_a(float par1, int par2, int par3) {
      ResourceLocation texture1 = new ResourceLocation("tbme:textures/gui/PlaylistBackground.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.renderEngine.bindTexture(texture1);
      int x = (this.field_146294_l - this.field_146999_f) / 2;
      int y = (this.field_146295_m - this.field_147000_g) / 2;
   }
}
