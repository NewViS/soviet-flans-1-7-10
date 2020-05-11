package net.minecraft.client.shader;


public class TesselatorVertexState {

   private int[] rawBuffer;
   private int rawBufferIndex;
   private int vertexCount;
   private boolean hasTexture;
   private boolean hasBrightness;
   private boolean hasNormals;
   private boolean hasColor;


   public TesselatorVertexState(int[] var1, int var2, int var3, boolean var4, boolean var5, boolean var6, boolean var7) {
      this.rawBuffer = var1;
      this.rawBufferIndex = var2;
      this.vertexCount = var3;
      this.hasTexture = var4;
      this.hasBrightness = var5;
      this.hasNormals = var6;
      this.hasColor = var7;
   }

   public int[] getRawBuffer() {
      return this.rawBuffer;
   }

   public int getRawBufferIndex() {
      return this.rawBufferIndex;
   }

   public int getVertexCount() {
      return this.vertexCount;
   }

   public boolean getHasTexture() {
      return this.hasTexture;
   }

   public boolean getHasBrightness() {
      return this.hasBrightness;
   }

   public boolean getHasNormals() {
      return this.hasNormals;
   }

   public boolean getHasColor() {
      return this.hasColor;
   }
}
