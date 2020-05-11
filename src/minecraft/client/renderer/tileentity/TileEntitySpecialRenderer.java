package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class TileEntitySpecialRenderer {

   protected TileEntityRendererDispatcher field_147501_a;


   public abstract void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8);

   protected void bindTexture(ResourceLocation var1) {
      TextureManager var2 = this.field_147501_a.field_147553_e;
      if(var2 != null) {
         var2.bindTexture(var1);
      }

   }

   public void func_147497_a(TileEntityRendererDispatcher var1) {
      this.field_147501_a = var1;
   }

   public void func_147496_a(World var1) {}

   public FontRenderer func_147498_b() {
      return this.field_147501_a.getFontRenderer();
   }
}
