package net.minecraft.client.renderer;

import net.minecraft.util.IIcon;

public class IconFlipped implements IIcon {

   private final IIcon baseIcon;
   private final boolean flipU;
   private final boolean flipV;


   public IconFlipped(IIcon var1, boolean var2, boolean var3) {
      this.baseIcon = var1;
      this.flipU = var2;
      this.flipV = var3;
   }

   public int getIconWidth() {
      return this.baseIcon.getIconWidth();
   }

   public int getIconHeight() {
      return this.baseIcon.getIconHeight();
   }

   public float getMinU() {
      return this.flipU?this.baseIcon.getMaxU():this.baseIcon.getMinU();
   }

   public float getMaxU() {
      return this.flipU?this.baseIcon.getMinU():this.baseIcon.getMaxU();
   }

   public float getInterpolatedU(double var1) {
      float var3 = this.getMaxU() - this.getMinU();
      return this.getMinU() + var3 * ((float)var1 / 16.0F);
   }

   public float getMinV() {
      return this.flipV?this.baseIcon.getMinV():this.baseIcon.getMinV();
   }

   public float getMaxV() {
      return this.flipV?this.baseIcon.getMinV():this.baseIcon.getMaxV();
   }

   public float getInterpolatedV(double var1) {
      float var3 = this.getMaxV() - this.getMinV();
      return this.getMinV() + var3 * ((float)var1 / 16.0F);
   }

   public String getIconName() {
      return this.baseIcon.getIconName();
   }
}
