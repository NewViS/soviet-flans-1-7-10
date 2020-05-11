package net.minecraft.client.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiFlatPresets;
import net.minecraft.client.gui.GuiFlatPresets$LayerItem;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

class GuiFlatPresets$ListSlot extends GuiSlot {

   public int field_148175_k;
   // $FF: synthetic field
   final GuiFlatPresets field_148174_l;


   public GuiFlatPresets$ListSlot(GuiFlatPresets var1) {
      super(var1.mc, var1.width, var1.height, 80, var1.height - 37, 24);
      this.field_148174_l = var1;
      this.field_148175_k = -1;
   }

   private void func_148172_a(int var1, int var2, Item var3) {
      this.func_148173_e(var1 + 1, var2 + 1);
      GL11.glEnable('\u803a');
      RenderHelper.enableGUIStandardItemLighting();
      GuiFlatPresets.access$000().renderItemIntoGUI(this.field_148174_l.fontRendererObj, this.field_148174_l.mc.getTextureManager(), new ItemStack(var3, 1, 0), var1 + 2, var2 + 2);
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable('\u803a');
   }

   private void func_148173_e(int var1, int var2) {
      this.func_148171_c(var1, var2, 0, 0);
   }

   private void func_148171_c(int var1, int var2, int var3, int var4) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_148174_l.mc.getTextureManager().bindTexture(Gui.statIcons);
      float var5 = 0.0078125F;
      float var6 = 0.0078125F;
      boolean var7 = true;
      boolean var8 = true;
      Tessellator var9 = Tessellator.instance;
      var9.startDrawingQuads();
      var9.addVertexWithUV((double)(var1 + 0), (double)(var2 + 18), (double)GuiFlatPresets.access$100(this.field_148174_l), (double)((float)(var3 + 0) * 0.0078125F), (double)((float)(var4 + 18) * 0.0078125F));
      var9.addVertexWithUV((double)(var1 + 18), (double)(var2 + 18), (double)GuiFlatPresets.access$200(this.field_148174_l), (double)((float)(var3 + 18) * 0.0078125F), (double)((float)(var4 + 18) * 0.0078125F));
      var9.addVertexWithUV((double)(var1 + 18), (double)(var2 + 0), (double)GuiFlatPresets.access$300(this.field_148174_l), (double)((float)(var3 + 18) * 0.0078125F), (double)((float)(var4 + 0) * 0.0078125F));
      var9.addVertexWithUV((double)(var1 + 0), (double)(var2 + 0), (double)GuiFlatPresets.access$400(this.field_148174_l), (double)((float)(var3 + 0) * 0.0078125F), (double)((float)(var4 + 0) * 0.0078125F));
      var9.draw();
   }

   protected int getSize() {
      return GuiFlatPresets.access$500().size();
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {
      this.field_148175_k = var1;
      this.field_148174_l.func_146426_g();
      GuiFlatPresets.access$700(this.field_148174_l).setText(((GuiFlatPresets$LayerItem)GuiFlatPresets.access$500().get(GuiFlatPresets.access$600(this.field_148174_l).field_148175_k)).field_148233_c);
   }

   protected boolean isSelected(int var1) {
      return var1 == this.field_148175_k;
   }

   protected void drawBackground() {}

   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7) {
      GuiFlatPresets$LayerItem var8 = (GuiFlatPresets$LayerItem)GuiFlatPresets.access$500().get(var1);
      this.func_148172_a(var2, var3, var8.field_148234_a);
      this.field_148174_l.fontRendererObj.drawString(var8.field_148232_b, var2 + 18 + 5, var3 + 6, 16777215);
   }
}
