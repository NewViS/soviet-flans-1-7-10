package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class BlockPortal$Size {

   private final World field_150867_a;
   private final int field_150865_b;
   private final int field_150866_c;
   private final int field_150863_d;
   private int field_150864_e = 0;
   private ChunkCoordinates field_150861_f;
   private int field_150862_g;
   private int field_150868_h;


   public BlockPortal$Size(World var1, int var2, int var3, int var4, int var5) {
      this.field_150867_a = var1;
      this.field_150865_b = var5;
      this.field_150863_d = BlockPortal.field_150001_a[var5][0];
      this.field_150866_c = BlockPortal.field_150001_a[var5][1];

      for(int var6 = var3; var3 > var6 - 21 && var3 > 0 && this.func_150857_a(var1.getBlock(var2, var3 - 1, var4)); --var3) {
         ;
      }

      int var7 = this.func_150853_a(var2, var3, var4, this.field_150863_d) - 1;
      if(var7 >= 0) {
         this.field_150861_f = new ChunkCoordinates(var2 + var7 * Direction.offsetX[this.field_150863_d], var3, var4 + var7 * Direction.offsetZ[this.field_150863_d]);
         this.field_150868_h = this.func_150853_a(this.field_150861_f.posX, this.field_150861_f.posY, this.field_150861_f.posZ, this.field_150866_c);
         if(this.field_150868_h < 2 || this.field_150868_h > 21) {
            this.field_150861_f = null;
            this.field_150868_h = 0;
         }
      }

      if(this.field_150861_f != null) {
         this.field_150862_g = this.func_150858_a();
      }

   }

   protected int func_150853_a(int var1, int var2, int var3, int var4) {
      int var6 = Direction.offsetX[var4];
      int var7 = Direction.offsetZ[var4];

      int var5;
      Block var8;
      for(var5 = 0; var5 < 22; ++var5) {
         var8 = this.field_150867_a.getBlock(var1 + var6 * var5, var2, var3 + var7 * var5);
         if(!this.func_150857_a(var8)) {
            break;
         }

         Block var9 = this.field_150867_a.getBlock(var1 + var6 * var5, var2 - 1, var3 + var7 * var5);
         if(var9 != Blocks.obsidian) {
            break;
         }
      }

      var8 = this.field_150867_a.getBlock(var1 + var6 * var5, var2, var3 + var7 * var5);
      return var8 == Blocks.obsidian?var5:0;
   }

   protected int func_150858_a() {
      int var1;
      int var2;
      int var3;
      int var4;
      label56:
      for(this.field_150862_g = 0; this.field_150862_g < 21; ++this.field_150862_g) {
         var1 = this.field_150861_f.posY + this.field_150862_g;

         for(var2 = 0; var2 < this.field_150868_h; ++var2) {
            var3 = this.field_150861_f.posX + var2 * Direction.offsetX[BlockPortal.field_150001_a[this.field_150865_b][1]];
            var4 = this.field_150861_f.posZ + var2 * Direction.offsetZ[BlockPortal.field_150001_a[this.field_150865_b][1]];
            Block var5 = this.field_150867_a.getBlock(var3, var1, var4);
            if(!this.func_150857_a(var5)) {
               break label56;
            }

            if(var5 == Blocks.portal) {
               ++this.field_150864_e;
            }

            if(var2 == 0) {
               var5 = this.field_150867_a.getBlock(var3 + Direction.offsetX[BlockPortal.field_150001_a[this.field_150865_b][0]], var1, var4 + Direction.offsetZ[BlockPortal.field_150001_a[this.field_150865_b][0]]);
               if(var5 != Blocks.obsidian) {
                  break label56;
               }
            } else if(var2 == this.field_150868_h - 1) {
               var5 = this.field_150867_a.getBlock(var3 + Direction.offsetX[BlockPortal.field_150001_a[this.field_150865_b][1]], var1, var4 + Direction.offsetZ[BlockPortal.field_150001_a[this.field_150865_b][1]]);
               if(var5 != Blocks.obsidian) {
                  break label56;
               }
            }
         }
      }

      for(var1 = 0; var1 < this.field_150868_h; ++var1) {
         var2 = this.field_150861_f.posX + var1 * Direction.offsetX[BlockPortal.field_150001_a[this.field_150865_b][1]];
         var3 = this.field_150861_f.posY + this.field_150862_g;
         var4 = this.field_150861_f.posZ + var1 * Direction.offsetZ[BlockPortal.field_150001_a[this.field_150865_b][1]];
         if(this.field_150867_a.getBlock(var2, var3, var4) != Blocks.obsidian) {
            this.field_150862_g = 0;
            break;
         }
      }

      if(this.field_150862_g <= 21 && this.field_150862_g >= 3) {
         return this.field_150862_g;
      } else {
         this.field_150861_f = null;
         this.field_150868_h = 0;
         this.field_150862_g = 0;
         return 0;
      }
   }

   protected boolean func_150857_a(Block var1) {
      return var1.blockMaterial == Material.air || var1 == Blocks.fire || var1 == Blocks.portal;
   }

   public boolean func_150860_b() {
      return this.field_150861_f != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
   }

   public void func_150859_c() {
      for(int var1 = 0; var1 < this.field_150868_h; ++var1) {
         int var2 = this.field_150861_f.posX + Direction.offsetX[this.field_150866_c] * var1;
         int var3 = this.field_150861_f.posZ + Direction.offsetZ[this.field_150866_c] * var1;

         for(int var4 = 0; var4 < this.field_150862_g; ++var4) {
            int var5 = this.field_150861_f.posY + var4;
            this.field_150867_a.setBlock(var2, var5, var3, Blocks.portal, this.field_150865_b, 2);
         }
      }

   }

   // $FF: synthetic method
   static int access$000(BlockPortal$Size var0) {
      return var0.field_150864_e;
   }

   // $FF: synthetic method
   static int access$100(BlockPortal$Size var0) {
      return var0.field_150868_h;
   }

   // $FF: synthetic method
   static int access$200(BlockPortal$Size var0) {
      return var0.field_150862_g;
   }
}
