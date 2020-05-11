package de.ItsAMysterious.mods.reallifemod.core.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.gui.containers.ContainerFreezer;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FreezerTE;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiFreezer extends GuiContainer {

   private static final ResourceLocation field_147017_u = new ResourceLocation("textures/gui/container/generic_54.png");
   private IInventory upperChestInventory;
   private IInventory lowerChestInventory;
   private int inventoryRows;
   public static int GUI_ID;


   public GuiFreezer() {
      super((Container)null);
      GUI_ID = RealLifeMod.proxy.modGuiID++;
   }

   public GuiFreezer(InventoryPlayer p_i1083_1_, FreezerTE tile) {
      super(new ContainerFreezer(p_i1083_1_, tile));
      GUI_ID = 52;
      this.upperChestInventory = p_i1083_1_;
      this.lowerChestInventory = tile;
      this.field_146291_p = true;
      short short1 = 222;
      int i = short1 - 108;
      this.inventoryRows = 5;
      this.field_147000_g = 215;
   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
      this.field_146289_q.drawString(this.lowerChestInventory.hasCustomInventoryName()?this.lowerChestInventory.getInventoryName():I18n.format(this.lowerChestInventory.getInventoryName(), new Object[0]), 8, 6, 4210752);
      this.field_146289_q.drawString(this.upperChestInventory.hasCustomInventoryName()?this.upperChestInventory.getInventoryName():I18n.format(this.upperChestInventory.getInventoryName(), new Object[0]), 8, this.field_147000_g - 106 + 2, 4210752);
   }

   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/guiFreezer.png"));
      int k = (this.field_146294_l - this.field_146999_f) / 2;
      int l = (this.field_146295_m - this.field_147000_g) / 2;
      this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g + 100);
   }

}
