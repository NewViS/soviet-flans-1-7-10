package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.client.renderer.texture.Stitcher$Holder;
import net.minecraft.client.renderer.texture.Stitcher$Slot;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.MathHelper;

public class Stitcher {

   private final int mipmapLevelStitcher;
   private final Set setStitchHolders = new HashSet(256);
   private final List stitchSlots = new ArrayList(256);
   private int currentWidth;
   private int currentHeight;
   private final int maxWidth;
   private final int maxHeight;
   private final boolean forcePowerOf2;
   private final int maxTileDimension;


   public Stitcher(int var1, int var2, boolean var3, int var4, int var5) {
      this.mipmapLevelStitcher = var5;
      this.maxWidth = var1;
      this.maxHeight = var2;
      this.forcePowerOf2 = var3;
      this.maxTileDimension = var4;
   }

   public int getCurrentWidth() {
      return this.currentWidth;
   }

   public int getCurrentHeight() {
      return this.currentHeight;
   }

   public void addSprite(TextureAtlasSprite var1) {
      Stitcher$Holder var2 = new Stitcher$Holder(var1, this.mipmapLevelStitcher);
      if(this.maxTileDimension > 0) {
         var2.setNewDimension(this.maxTileDimension);
      }

      this.setStitchHolders.add(var2);
   }

   public void doStitch() {
      Stitcher$Holder[] var1 = (Stitcher$Holder[])this.setStitchHolders.toArray(new Stitcher$Holder[this.setStitchHolders.size()]);
      Arrays.sort(var1);
      Stitcher$Holder[] var2 = var1;
      int var3 = var1.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Stitcher$Holder var5 = var2[var4];
         if(!this.allocateSlot(var5)) {
            String var6 = String.format("Unable to fit: %s - size: %dx%d - Maybe try a lowerresolution resourcepack?", new Object[]{var5.getAtlasSprite().getIconName(), Integer.valueOf(var5.getAtlasSprite().getIconWidth()), Integer.valueOf(var5.getAtlasSprite().getIconHeight())});
            throw new StitcherException(var5, var6);
         }
      }

      if(this.forcePowerOf2) {
         this.currentWidth = MathHelper.roundUpToPowerOfTwo(this.currentWidth);
         this.currentHeight = MathHelper.roundUpToPowerOfTwo(this.currentHeight);
      }

   }

   public List getStichSlots() {
      ArrayList var1 = Lists.newArrayList();
      Iterator var2 = this.stitchSlots.iterator();

      while(var2.hasNext()) {
         Stitcher$Slot var3 = (Stitcher$Slot)var2.next();
         var3.getAllStitchSlots(var1);
      }

      ArrayList var7 = Lists.newArrayList();
      Iterator var8 = var1.iterator();

      while(var8.hasNext()) {
         Stitcher$Slot var4 = (Stitcher$Slot)var8.next();
         Stitcher$Holder var5 = var4.getStitchHolder();
         TextureAtlasSprite var6 = var5.getAtlasSprite();
         var6.initSprite(this.currentWidth, this.currentHeight, var4.getOriginX(), var4.getOriginY(), var5.isRotated());
         var7.add(var6);
      }

      return var7;
   }

   private static int getMipmapDimension(int var0, int var1) {
      return (var0 >> var1) + ((var0 & (1 << var1) - 1) == 0?0:1) << var1;
   }

   private boolean allocateSlot(Stitcher$Holder var1) {
      for(int var2 = 0; var2 < this.stitchSlots.size(); ++var2) {
         if(((Stitcher$Slot)this.stitchSlots.get(var2)).addSlot(var1)) {
            return true;
         }

         var1.rotate();
         if(((Stitcher$Slot)this.stitchSlots.get(var2)).addSlot(var1)) {
            return true;
         }

         var1.rotate();
      }

      return this.expandAndAllocateSlot(var1);
   }

   private boolean expandAndAllocateSlot(Stitcher$Holder var1) {
      int var2 = Math.min(var1.getWidth(), var1.getHeight());
      boolean var3 = this.currentWidth == 0 && this.currentHeight == 0;
      boolean var4;
      int var5;
      if(this.forcePowerOf2) {
         var5 = MathHelper.roundUpToPowerOfTwo(this.currentWidth);
         int var6 = MathHelper.roundUpToPowerOfTwo(this.currentHeight);
         int var7 = MathHelper.roundUpToPowerOfTwo(this.currentWidth + var2);
         int var8 = MathHelper.roundUpToPowerOfTwo(this.currentHeight + var2);
         boolean var9 = var7 <= this.maxWidth;
         boolean var10 = var8 <= this.maxHeight;
         if(!var9 && !var10) {
            return false;
         }

         boolean var11 = var5 != var7;
         boolean var12 = var6 != var8;
         if(var11 ^ var12) {
            var4 = !var11;
         } else {
            var4 = var9 && var5 <= var6;
         }
      } else {
         boolean var13 = this.currentWidth + var2 <= this.maxWidth;
         boolean var14 = this.currentHeight + var2 <= this.maxHeight;
         if(!var13 && !var14) {
            return false;
         }

         var4 = var13 && (var3 || this.currentWidth <= this.currentHeight);
      }

      var5 = Math.max(var1.getWidth(), var1.getHeight());
      if(MathHelper.roundUpToPowerOfTwo((var4?this.currentHeight:this.currentWidth) + var5) > (var4?this.maxHeight:this.maxWidth)) {
         return false;
      } else {
         Stitcher$Slot var15;
         if(var4) {
            if(var1.getWidth() > var1.getHeight()) {
               var1.rotate();
            }

            if(this.currentHeight == 0) {
               this.currentHeight = var1.getHeight();
            }

            var15 = new Stitcher$Slot(this.currentWidth, 0, var1.getWidth(), this.currentHeight);
            this.currentWidth += var1.getWidth();
         } else {
            var15 = new Stitcher$Slot(0, this.currentHeight, this.currentWidth, var1.getHeight());
            this.currentHeight += var1.getHeight();
         }

         var15.addSlot(var1);
         this.stitchSlots.add(var15);
         return true;
      }
   }

   // $FF: synthetic method
   static int access$000(int var0, int var1) {
      return getMipmapDimension(var0, var1);
   }
}
