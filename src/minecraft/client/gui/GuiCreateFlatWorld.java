package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateFlatWorld$Details;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiFlatPresets;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.gen.FlatGeneratorInfo;

public class GuiCreateFlatWorld extends GuiScreen {

   private static RenderItem field_146392_a = new RenderItem();
   private final GuiCreateWorld createWorldGui;
   private FlatGeneratorInfo theFlatGeneratorInfo = FlatGeneratorInfo.getDefaultFlatGenerator();
   private String field_146393_h;
   private String field_146394_i;
   private String field_146391_r;
   private GuiCreateFlatWorld$Details createFlatWorldListSlotGui;
   private GuiButton field_146389_t;
   private GuiButton field_146388_u;
   private GuiButton field_146386_v;


   public GuiCreateFlatWorld(GuiCreateWorld var1, String var2) {
      this.createWorldGui = var1;
      this.func_146383_a(var2);
   }

   public String func_146384_e() {
      return this.theFlatGeneratorInfo.toString();
   }

   public void func_146383_a(String var1) {
      this.theFlatGeneratorInfo = FlatGeneratorInfo.createFlatGeneratorFromString(var1);
   }

   public void initGui() {
      super.buttonList.clear();
      this.field_146393_h = I18n.format("createWorld.customize.flat.title", new Object[0]);
      this.field_146394_i = I18n.format("createWorld.customize.flat.tile", new Object[0]);
      this.field_146391_r = I18n.format("createWorld.customize.flat.height", new Object[0]);
      this.createFlatWorldListSlotGui = new GuiCreateFlatWorld$Details(this);
      super.buttonList.add(this.field_146389_t = new GuiButton(2, super.width / 2 - 154, super.height - 52, 100, 20, I18n.format("createWorld.customize.flat.addLayer", new Object[0]) + " (NYI)"));
      super.buttonList.add(this.field_146388_u = new GuiButton(3, super.width / 2 - 50, super.height - 52, 100, 20, I18n.format("createWorld.customize.flat.editLayer", new Object[0]) + " (NYI)"));
      super.buttonList.add(this.field_146386_v = new GuiButton(4, super.width / 2 - 155, super.height - 52, 150, 20, I18n.format("createWorld.customize.flat.removeLayer", new Object[0])));
      super.buttonList.add(new GuiButton(0, super.width / 2 - 155, super.height - 28, 150, 20, I18n.format("gui.done", new Object[0])));
      super.buttonList.add(new GuiButton(5, super.width / 2 + 5, super.height - 52, 150, 20, I18n.format("createWorld.customize.presets", new Object[0])));
      super.buttonList.add(new GuiButton(1, super.width / 2 + 5, super.height - 28, 150, 20, I18n.format("gui.cancel", new Object[0])));
      this.field_146389_t.visible = this.field_146388_u.visible = false;
      this.theFlatGeneratorInfo.func_82645_d();
      this.func_146375_g();
   }

   protected void actionPerformed(GuiButton var1) {
      int var2 = this.theFlatGeneratorInfo.getFlatLayers().size() - this.createFlatWorldListSlotGui.field_148228_k - 1;
      if(var1.id == 1) {
         super.mc.displayGuiScreen(this.createWorldGui);
      } else if(var1.id == 0) {
         this.createWorldGui.field_146334_a = this.func_146384_e();
         super.mc.displayGuiScreen(this.createWorldGui);
      } else if(var1.id == 5) {
         super.mc.displayGuiScreen(new GuiFlatPresets(this));
      } else if(var1.id == 4 && this.func_146382_i()) {
         this.theFlatGeneratorInfo.getFlatLayers().remove(var2);
         this.createFlatWorldListSlotGui.field_148228_k = Math.min(this.createFlatWorldListSlotGui.field_148228_k, this.theFlatGeneratorInfo.getFlatLayers().size() - 1);
      }

      this.theFlatGeneratorInfo.func_82645_d();
      this.func_146375_g();
   }

   public void func_146375_g() {
      boolean var1 = this.func_146382_i();
      this.field_146386_v.enabled = var1;
      this.field_146388_u.enabled = var1;
      this.field_146388_u.enabled = false;
      this.field_146389_t.enabled = false;
   }

   private boolean func_146382_i() {
      return this.createFlatWorldListSlotGui.field_148228_k > -1 && this.createFlatWorldListSlotGui.field_148228_k < this.theFlatGeneratorInfo.getFlatLayers().size();
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.createFlatWorldListSlotGui.drawScreen(var1, var2, var3);
      this.drawCenteredString(super.fontRendererObj, this.field_146393_h, super.width / 2, 8, 16777215);
      int var4 = super.width / 2 - 92 - 16;
      this.drawString(super.fontRendererObj, this.field_146394_i, var4, 32, 16777215);
      this.drawString(super.fontRendererObj, this.field_146391_r, var4 + 2 + 213 - super.fontRendererObj.getStringWidth(this.field_146391_r), 32, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   // $FF: synthetic method
   static RenderItem access$000() {
      return field_146392_a;
   }

   // $FF: synthetic method
   static float access$100(GuiCreateFlatWorld var0) {
      return var0.zLevel;
   }

   // $FF: synthetic method
   static float access$200(GuiCreateFlatWorld var0) {
      return var0.zLevel;
   }

   // $FF: synthetic method
   static float access$300(GuiCreateFlatWorld var0) {
      return var0.zLevel;
   }

   // $FF: synthetic method
   static float access$400(GuiCreateFlatWorld var0) {
      return var0.zLevel;
   }

   // $FF: synthetic method
   static FlatGeneratorInfo access$500(GuiCreateFlatWorld var0) {
      return var0.theFlatGeneratorInfo;
   }

}
