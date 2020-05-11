package net.minecraft.client.renderer;

import java.nio.IntBuffer;
import net.minecraft.client.renderer.GLAllocation;
import org.lwjgl.opengl.GL11;

public class RenderList {

   public int renderChunkX;
   public int renderChunkY;
   public int renderChunkZ;
   private double cameraX;
   private double cameraY;
   private double cameraZ;
   private IntBuffer glLists = GLAllocation.createDirectIntBuffer(65536);
   private boolean valid;
   private boolean bufferFlipped;


   public void setupRenderList(int var1, int var2, int var3, double var4, double var6, double var8) {
      this.valid = true;
      this.glLists.clear();
      this.renderChunkX = var1;
      this.renderChunkY = var2;
      this.renderChunkZ = var3;
      this.cameraX = var4;
      this.cameraY = var6;
      this.cameraZ = var8;
   }

   public boolean rendersChunk(int var1, int var2, int var3) {
      return !this.valid?false:var1 == this.renderChunkX && var2 == this.renderChunkY && var3 == this.renderChunkZ;
   }

   public void addGLRenderList(int var1) {
      this.glLists.put(var1);
      if(this.glLists.remaining() == 0) {
         this.callLists();
      }

   }

   public void callLists() {
      if(this.valid) {
         if(!this.bufferFlipped) {
            this.glLists.flip();
            this.bufferFlipped = true;
         }

         if(this.glLists.remaining() > 0) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((double)this.renderChunkX - this.cameraX), (float)((double)this.renderChunkY - this.cameraY), (float)((double)this.renderChunkZ - this.cameraZ));
            GL11.glCallLists(this.glLists);
            GL11.glPopMatrix();
         }

      }
   }

   public void resetList() {
      this.valid = false;
      this.bufferFlipped = false;
   }
}
