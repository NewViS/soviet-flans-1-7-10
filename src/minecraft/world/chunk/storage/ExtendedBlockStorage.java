package net.minecraft.world.chunk.storage;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.NibbleArray;

public class ExtendedBlockStorage {

   private int yBase;
   private int blockRefCount;
   private int tickRefCount;
   private byte[] blockLSBArray;
   private NibbleArray blockMSBArray;
   private NibbleArray blockMetadataArray;
   private NibbleArray blocklightArray;
   private NibbleArray skylightArray;


   public ExtendedBlockStorage(int var1, boolean var2) {
      this.yBase = var1;
      this.blockLSBArray = new byte[4096];
      this.blockMetadataArray = new NibbleArray(this.blockLSBArray.length, 4);
      this.blocklightArray = new NibbleArray(this.blockLSBArray.length, 4);
      if(var2) {
         this.skylightArray = new NibbleArray(this.blockLSBArray.length, 4);
      }

   }

   public Block getBlockByExtId(int var1, int var2, int var3) {
      int var4 = this.blockLSBArray[var2 << 8 | var3 << 4 | var1] & 255;
      if(this.blockMSBArray != null) {
         var4 |= this.blockMSBArray.get(var1, var2, var3) << 8;
      }

      return Block.getBlockById(var4);
   }

   public void func_150818_a(int var1, int var2, int var3, Block var4) {
      int var5 = this.blockLSBArray[var2 << 8 | var3 << 4 | var1] & 255;
      if(this.blockMSBArray != null) {
         var5 |= this.blockMSBArray.get(var1, var2, var3) << 8;
      }

      Block var6 = Block.getBlockById(var5);
      if(var6 != Blocks.air) {
         --this.blockRefCount;
         if(var6.getTickRandomly()) {
            --this.tickRefCount;
         }
      }

      if(var4 != Blocks.air) {
         ++this.blockRefCount;
         if(var4.getTickRandomly()) {
            ++this.tickRefCount;
         }
      }

      int var7 = Block.getIdFromBlock(var4);
      this.blockLSBArray[var2 << 8 | var3 << 4 | var1] = (byte)(var7 & 255);
      if(var7 > 255) {
         if(this.blockMSBArray == null) {
            this.blockMSBArray = new NibbleArray(this.blockLSBArray.length, 4);
         }

         this.blockMSBArray.set(var1, var2, var3, (var7 & 3840) >> 8);
      } else if(this.blockMSBArray != null) {
         this.blockMSBArray.set(var1, var2, var3, 0);
      }

   }

   public int getExtBlockMetadata(int var1, int var2, int var3) {
      return this.blockMetadataArray.get(var1, var2, var3);
   }

   public void setExtBlockMetadata(int var1, int var2, int var3, int var4) {
      this.blockMetadataArray.set(var1, var2, var3, var4);
   }

   public boolean isEmpty() {
      return this.blockRefCount == 0;
   }

   public boolean getNeedsRandomTick() {
      return this.tickRefCount > 0;
   }

   public int getYLocation() {
      return this.yBase;
   }

   public void setExtSkylightValue(int var1, int var2, int var3, int var4) {
      this.skylightArray.set(var1, var2, var3, var4);
   }

   public int getExtSkylightValue(int var1, int var2, int var3) {
      return this.skylightArray.get(var1, var2, var3);
   }

   public void setExtBlocklightValue(int var1, int var2, int var3, int var4) {
      this.blocklightArray.set(var1, var2, var3, var4);
   }

   public int getExtBlocklightValue(int var1, int var2, int var3) {
      return this.blocklightArray.get(var1, var2, var3);
   }

   public void removeInvalidBlocks() {
      this.blockRefCount = 0;
      this.tickRefCount = 0;

      for(int var1 = 0; var1 < 16; ++var1) {
         for(int var2 = 0; var2 < 16; ++var2) {
            for(int var3 = 0; var3 < 16; ++var3) {
               Block var4 = this.getBlockByExtId(var1, var2, var3);
               if(var4 != Blocks.air) {
                  ++this.blockRefCount;
                  if(var4.getTickRandomly()) {
                     ++this.tickRefCount;
                  }
               }
            }
         }
      }

   }

   public byte[] getBlockLSBArray() {
      return this.blockLSBArray;
   }

   public void clearMSBArray() {
      this.blockMSBArray = null;
   }

   public NibbleArray getBlockMSBArray() {
      return this.blockMSBArray;
   }

   public NibbleArray getMetadataArray() {
      return this.blockMetadataArray;
   }

   public NibbleArray getBlocklightArray() {
      return this.blocklightArray;
   }

   public NibbleArray getSkylightArray() {
      return this.skylightArray;
   }

   public void setBlockLSBArray(byte[] var1) {
      this.blockLSBArray = var1;
   }

   public void setBlockMSBArray(NibbleArray var1) {
      this.blockMSBArray = var1;
   }

   public void setBlockMetadataArray(NibbleArray var1) {
      this.blockMetadataArray = var1;
   }

   public void setBlocklightArray(NibbleArray var1) {
      this.blocklightArray = var1;
   }

   public void setSkylightArray(NibbleArray var1) {
      this.skylightArray = var1;
   }

   public NibbleArray createBlockMSBArray() {
      this.blockMSBArray = new NibbleArray(this.blockLSBArray.length, 4);
      return this.blockMSBArray;
   }
}
