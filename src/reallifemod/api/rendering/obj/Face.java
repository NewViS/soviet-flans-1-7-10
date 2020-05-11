package de.ItsAMysterious.mods.reallifemod.api.rendering.obj;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.api.rendering.obj.TextureCoordinate;
import de.ItsAMysterious.mods.reallifemod.api.rendering.obj.Vertex;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;

public class Face {

   public Vertex[] vertices;
   public Vertex[] vertexNormals;
   public Vertex faceNormal;
   public TextureCoordinate[] textureCoordinates;


   @SideOnly(Side.CLIENT)
   public void addFaceForRendering(Tessellator tessellator) {
      this.addFaceForRendering(tessellator, 5.0E-4F);
   }

   @SideOnly(Side.CLIENT)
   public void addFaceForRendering(Tessellator tessellator, float TexturOffset) {
      if(this.faceNormal == null) {
         this.faceNormal = this.calculateNormal();
      }

      tessellator.setNormal(this.faceNormal.x, this.faceNormal.y, this.faceNormal.z);
      float averageU = 0.0F;
      float averageV = 0.0F;
      if(this.textureCoordinates != null && this.textureCoordinates.length > 0) {
         for(int offsetU = 0; offsetU < this.textureCoordinates.length; ++offsetU) {
            averageU += this.textureCoordinates[offsetU].u;
            averageV += this.textureCoordinates[offsetU].v;
         }

         averageU /= (float)this.textureCoordinates.length;
         float var10000 = averageV / (float)this.textureCoordinates.length;
      }

      for(int i = 0; i < this.vertices.length; ++i) {
         if(this.textureCoordinates != null && this.textureCoordinates.length > 0) {
            float var8 = TexturOffset;
            float offsetV = TexturOffset;
            if(this.textureCoordinates[i].u > averageU) {
               var8 = -TexturOffset;
            }

            if(this.textureCoordinates[i].v > averageU) {
               offsetV = -TexturOffset;
            }

            tessellator.addVertexWithUV((double)this.vertices[i].x, (double)this.vertices[i].y, (double)this.vertices[i].z, (double)(this.textureCoordinates[i].u + var8), (double)(this.textureCoordinates[i].v + offsetV));
         } else {
            tessellator.addVertex((double)this.vertices[i].x, (double)this.vertices[i].y, (double)this.vertices[i].z);
         }
      }

   }

   public Vertex calculateNormal() {
      Vec3 v1 = Vec3.createVectorHelper((double)(this.vertices[1].x - this.vertices[0].x), (double)(this.vertices[1].y - this.vertices[0].y), (double)(this.vertices[1].z - this.vertices[0].z));
      Vec3 v2 = Vec3.createVectorHelper((double)(this.vertices[2].x - this.vertices[0].x), (double)(this.vertices[2].y - this.vertices[0].y), (double)(this.vertices[2].z - this.vertices[0].z));
      Vec3 normalVector = null;
      normalVector = v1.crossProduct(v2).normalize();
      return new Vertex((float)normalVector.xCoord, (float)normalVector.yCoord, (float)normalVector.zCoord);
   }
}
