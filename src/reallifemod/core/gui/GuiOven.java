package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.core.gui.container.ContainerOven;
import de.ItsAMysterious.mods.reallifemod.core.gui.container.OvenTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiOven extends GuiContainer {

   private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("textures/gui/container/furnace.png");
   private OvenTileEntity tileOven;
   private static final String __OBFID = "CL_00000758";


   public GuiOven(InventoryPlayer inv, OvenTileEntity tile) {
      super(new ContainerOven(inv, tile));
      this.tileOven = tile;
   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
      String s = "Smelting Oven";
      this.field_146289_q.drawString(s, this.field_146999_f / 2 - this.field_146289_q.getStringWidth(s) / 2, 6, 4210752);
      this.field_146289_q.drawString(I18n.format("container.inventory", new Object[0]), 8, this.field_147000_g - 96 + 2, 4210752);
   }

   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.getTextureManager().bindTexture(furnaceGuiTextures);
      int k = (this.field_146294_l - this.field_146999_f) / 2;
      int l = (this.field_146295_m - this.field_147000_g) / 2;
      this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
      if(this.tileOven.isBurning()) {
         int i1 = this.tileOven.getBurnTimeRemainingScaled(13);
         this.func_73729_b(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
         i1 = this.tileOven.getCookProgressScaled(24);
         this.func_73729_b(k + 79, l + 34, 176, 14, i1 + 1, 16);
      }

   }

}
