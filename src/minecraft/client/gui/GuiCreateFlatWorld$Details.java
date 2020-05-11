package net.minecraft.client.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiCreateFlatWorld;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.FlatLayerInfo;
import org.lwjgl.opengl.GL11;

class GuiCreateFlatWorld$Details extends GuiSlot {

   public int field_148228_k;
   // $FF: synthetic field
   final GuiCreateFlatWorld field_148227_l;


   public GuiCreateFlatWorld$Details(GuiCreateFlatWorld var1) {
      super(var1.mc, var1.width, var1.height, 43, var1.height - 60, 24);
      this.field_148227_l = var1;
      this.field_148228_k = -1;
   }

   private void func_148225_a(int var1, int var2, ItemStack var3) {
      this.func_148226_e(var1 + 1, var2 + 1);
      GL11.glEnable('\u803a');
      if(var3 != null) {
         RenderHelper.enableGUIStandardItemLighting();
         GuiCreateFlatWorld.access$000().renderItemIntoGUI(this.field_148227_l.fontRendererObj, this.field_148227_l.mc.getTextureManager(), var3, var1 + 2, var2 + 2);
         RenderHelper.disableStandardItemLighting();
      }

      GL11.glDisable('\u803a');
   }

   private void func_148226_e(int var1, int var2) {
      this.func_148224_c(var1, var2, 0, 0);
   }

   private void func_148224_c(int var1, int var2, int var3, int var4) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_148227_l.mc.getTextureManager().bindTexture(Gui.statIcons);
      float var5 = 0.0078125F;
      float var6 = 0.0078125F;
      boolean var7 = true;
      boolean var8 = true;
      Tessellator var9 = Tessellator.instance;
      var9.startDrawingQuads();
      var9.addVertexWithUV((double)(var1 + 0), (double)(var2 + 18), (double)GuiCreateFlatWorld.access$100(this.field_148227_l), (double)((float)(var3 + 0) * 0.0078125F), (double)((float)(var4 + 18) * 0.0078125F));
      var9.addVertexWithUV((double)(var1 + 18), (double)(var2 + 18), (double)GuiCreateFlatWorld.access$200(this.field_148227_l), (double)((float)(var3 + 18) * 0.0078125F), (double)((float)(var4 + 18) * 0.0078125F));
      var9.addVertexWithUV((double)(var1 + 18), (double)(var2 + 0), (double)GuiCreateFlatWorld.access$300(this.field_148227_l), (double)((float)(var3 + 18) * 0.0078125F), (double)((float)(var4 + 0) * 0.0078125F));
      var9.addVertexWithUV((double)(var1 + 0), (double)(var2 + 0), (double)GuiCreateFlatWorld.access$400(this.field_148227_l), (double)((float)(var3 + 0) * 0.0078125F), (double)((float)(var4 + 0) * 0.0078125F));
      var9.draw();
   }

   protected int getSize() {
      return GuiCreateFlatWorld.access$500(this.field_148227_l).getFlatLayers().size();
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {
      this.field_148228_k = var1;
      this.field_148227_l.func_146375_g();
   }

   protected boolean isSelected(int var1) {
      return var1 == this.field_148228_k;
   }

   protected void drawBackground() {}

   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7) {
      FlatLayerInfo var8 = (FlatLayerInfo)GuiCreateFlatWorld.access$500(this.field_148227_l).getFlatLayers().get(GuiCreateFlatWorld.access$500(this.field_148227_l).getFlatLayers().size() - var1 - 1);
      Item var9 = Item.getItemFromBlock(var8.func_151536_b());
      ItemStack var10 = var8.func_151536_b() == Blocks.air?null:new ItemStack(var9, 1, var8.getFillBlockMeta());
      String var11 = var10 != null && var9 != null?var9.getItemStackDisplayName(var10):"Air";
      this.func_148225_a(var2, var3, var10);
      this.field_148227_l.fontRendererObj.drawString(var11, var2 + 18 + 5, var3 + 3, 16777215);
      String var12;
      if(var1 == 0) {
         var12 = I18n.format("createWorld.customize.flat.layer.top", new Object[]{Integer.valueOf(var8.getLayerCount())});
      } else if(var1 == GuiCreateFlatWorld.access$500(this.field_148227_l).getFlatLayers().size() - 1) {
         var12 = I18n.format("createWorld.customize.flat.layer.bottom", new Object[]{Integer.valueOf(var8.getLayerCount())});
      } else {
         var12 = I18n.format("createWorld.customize.flat.layer", new Object[]{Integer.valueOf(var8.getLayerCount())});
      }

      this.field_148227_l.fontRendererObj.drawString(var12, var2 + 2 + 213 - this.field_148227_l.fontRendererObj.getStringWidth(var12), var3 + 3, 16777215);
   }

   protected int getScrollBarX() {
      return super.width - 70;
   }
}
